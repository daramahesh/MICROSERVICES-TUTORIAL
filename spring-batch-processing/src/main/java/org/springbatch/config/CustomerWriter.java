package org.springbatch.config;

import org.springbatch.entity.Customer;
import org.springbatch.repository.CustomerRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter implements ItemWriter<Customer> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
        System.out.println("thread name : " +Thread.currentThread().getName());
        customerRepository.saveAll(chunk);
    }
}
