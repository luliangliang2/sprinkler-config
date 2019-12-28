package org.sprinkler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sprinkler.feignclient.ServiceProvider;

@RestController
@RequestMapping(value = "mt")
public class MtestController {

	@Autowired
	Mtservice mtservice;


	@GetMapping("/c/get/{id}")
	public String get(@PathVariable String id) {
		return "sssss";
	}

	
	@Autowired
	ServiceProvider serviceProvider;
	
	@RequestMapping(value = "getPerson")
	public String getPerson() {
		System.err.println(serviceProvider.getPortInfo());
		mtservice.addPerson();
		return "getPerson";
	}
}
