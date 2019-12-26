package org.sprinkler.oath2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sprinkler.orm.annotations.EnableFreeagerAtomikos;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableFreeagerAtomikos(dataSource = { "database1" })
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");// spring boot oath2 认证服务
		SpringApplication.run(App.class, args);
	}
}
