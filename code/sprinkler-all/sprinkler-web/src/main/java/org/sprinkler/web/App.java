package org.sprinkler.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.sprinkler.orm.annotations.EnableFreeagerAtomikos;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(value= {"org.sprinkler","org.sprinkler.config"})
@EnableFeignClients(basePackages = "org.sprinkler.feignclient")
@EnableFreeagerAtomikos(dataSource = { "database1", "database2" })
public class App {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(App.class, args).getEnvironment();
	}
	

}
