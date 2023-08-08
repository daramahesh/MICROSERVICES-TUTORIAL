package org.springbatch.controller;

import jakarta.transaction.Transactional;
import org.springbatch.entity.Customer;
import org.springbatch.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    private CustomerRepository repository;
    private final String folderLocation = "C:/eidikoportal/files/";
    @PostMapping("/importCustomers")
   // @Transactional(rollbackOn = Exception.class)
    public void importCsvToDBJob(@RequestParam("file")MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();

        Files.copy(file.getInputStream(), Paths.get(folderLocation).resolve(originalFileName), StandardCopyOption.REPLACE_EXISTING);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("path",folderLocation+originalFileName)
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return repository.findAll();
    }
}
