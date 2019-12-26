package org.sprinkler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	
	@Autowired
	RestTemplate restTemplate;
	
    @GetMapping("/c/get/{id}")
    public String get(@PathVariable String id) {
    	System.err.println(id);
       String result = restTemplate.getForObject("http://service-producer/getPortInfo", String.class);
       System.out.println(result);
        return "sssss";
    }
    
	@Value("${server.port}")
    String serverPort;
    
    @GetMapping("/getPortInfo")
    public String produce() {
        return "调用服务的端口号为：" + serverPort;
    }
}
