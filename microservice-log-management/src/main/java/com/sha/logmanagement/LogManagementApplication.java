package com.sha.logmanagement;

import com.sha.logmanagement.config.CassandraConfig;
import com.sha.logmanagement.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({WebConfig.class, CassandraConfig.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LogManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogManagementApplication.class, args);
	}

}
