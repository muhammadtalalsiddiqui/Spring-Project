package com.dataproviderservice;

import com.dataproviderservice.DTO.AuthResponseDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
@RequestMapping("/data-provider-service")
@EnableDiscoveryClient
public class DataProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProviderServiceApplication.class, args);
		System.out.println("Hello Talal---------------------------->");


	}
	@Bean
	@LoadBalanced

	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
