package org.sprinkler.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("org.sprinkler")
public class App {
	public static void main(String[] args) {
		 SpringApplication.run(App.class, args);
	}
}
