package org.springbatch.config;

import jakarta.transaction.Transactional;
import org.springbatch.entity.Customer;
import org.springbatch.listener.StepSkipListener;
import org.springbatch.partition.ColumnRangePartitioner;
import org.springbatch.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private CustomerWriter customerWriter;
    @Autowired
    CustomerRepository customerRepository;

    // creating item reader object
    @Bean
    @StepScope
    public FlatFileItemReader<Customer> reader(@Value("#{jobParameters[path]}")String path) {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource(new File(path)));
        itemReader.setLinesToSkip(1);
        itemReader.setName("csvReader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    //Read csv file and map to customer object
    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        //Reading csv file
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

        //mapping to customer object
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper =  new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
    //creating item processor object
    @Bean
    public CustomerProcessor itemProcessor() {
        return new CustomerProcessor();
    }

    //creating writer object
   /* @Bean
    public RepositoryItemWriter<Customer> writer() {
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
        writer.setRepository(customerRepository);
        writer.setMethodName("save");
        return writer;
    }*/
    @Bean
    public ColumnRangePartitioner partitioner() {
       return new ColumnRangePartitioner();
    }
    @Bean
    public PartitionHandler partitionHandler(FlatFileItemReader<Customer> reader,JobRepository jobRepository, PlatformTransactionManager transactionManager){
        TaskExecutorPartitionHandler taskExecutorPartitionHandler= new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(4);
        taskExecutorPartitionHandler.setTaskExecutor(taskExecutor());
        taskExecutorPartitionHandler.setStep(slaveStep(reader,jobRepository,transactionManager));
        return taskExecutorPartitionHandler;
    }
    //creating step object
    @Bean
    public Step slaveStep(FlatFileItemReader<Customer> reader,JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-step",jobRepository)
                .<Customer, Customer>chunk(250,transactionManager)
                .reader(reader)
                .processor(itemProcessor())
                .writer(customerWriter)
                .faultTolerant()
                .listener(skipListener())
                .skipPolicy(skipPolicy())
                .build();
    }
    @Bean
    public Step masterStep(FlatFileItemReader<Customer> reader,JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("masterStep",jobRepository)
                .partitioner(slaveStep(reader,jobRepository, transactionManager).getName(),partitioner())
                .partitionHandler(partitionHandler(reader,jobRepository, transactionManager))
                .build();
    }
    @Bean
    public Job runJob(FlatFileItemReader<Customer> reader,JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("importCustomers",jobRepository)
                .flow(slaveStep(reader,jobRepository,transactionManager)).end().build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setQueueCapacity(4);
       /* SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);*/
        return taskExecutor;
    }

    @Bean
    public ExceptionSkipPolicy skipPolicy() {
        return new ExceptionSkipPolicy();
    }

    @Bean
    public StepSkipListener skipListener() {
        return new StepSkipListener();
    }
}
