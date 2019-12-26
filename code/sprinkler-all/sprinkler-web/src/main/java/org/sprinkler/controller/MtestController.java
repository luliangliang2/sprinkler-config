package org.sprinkler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="mt")	
public class MtestController {

	@Autowired
	Mtservice mtservice;
	
	

	
	@RequestMapping(value="getPerson")
	public String getPerson() {
		mtservice.addPerson();
		return "getPerson";
	}
}
