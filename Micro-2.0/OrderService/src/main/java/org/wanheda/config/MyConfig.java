package org.wanheda.config;

import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MyConfig {

    @Bean
    public WebClient webClient(LoadBalancedExchangeFilterFunction lbFunction) {

        return WebClient.builder()
                .filter(lbFunction)
                .build();
    }
}
