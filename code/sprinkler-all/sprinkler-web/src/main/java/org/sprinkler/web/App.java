package org.sprinkler.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.sprinkler.orm.annotations.EnableFreeagerAtomikos;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFreeagerAtomikos(dataSource = { "database1", "database2" })
@ComponentScan(basePackages = { "org.sprinkler" })
public class App {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(App.class, args).getEnvironment();
	}

}
