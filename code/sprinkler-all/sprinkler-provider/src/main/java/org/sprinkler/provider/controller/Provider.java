package org.sprinkler.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Provider {

	@Value("${server.port}")
    String serverPort;
    
    @GetMapping("/getPortInfo")
    public String produce() {
        return "调用服务的端口号为：" + serverPort;
    }
}
