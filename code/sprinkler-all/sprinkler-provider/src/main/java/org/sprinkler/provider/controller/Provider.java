package org.sprinkler.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Provider {

	@Value("${server.port}")
    String serverPort;
    
	@RequestMapping(value="/getPortInfo")
    public String produce() {
		System.err.println("被调用！！！");
        return "调用服务的端口号为：" + serverPort;
    }
}
