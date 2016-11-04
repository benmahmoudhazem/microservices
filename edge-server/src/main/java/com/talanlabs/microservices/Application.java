package com.talanlabs.microservices;

import com.talanlabs.microservices.filter.LogRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author Thibaud Leprêtre
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogRequestFilter logRequestFilter() {
        return new LogRequestFilter();
    }

}
