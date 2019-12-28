package org.sprinkler.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-producer")
public interface ServiceProvider {

	@GetMapping("/getPortInfo")
	String getPortInfo();
}
