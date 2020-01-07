package org.sprinkler.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-producer")
public interface ServiceProvider {

	@RequestMapping(value="/getPortInfo",method=RequestMethod.POST)
	String getPortInfo();
}
