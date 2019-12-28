package org.sprinkler.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "mt")
public class MtestController {

	@Autowired
	Mtservice mtservice;


	@GetMapping("/c/get/{id}")
	public String get(@PathVariable String id) throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ObjectMapper objectMapper = new ObjectMapper();
		String user = objectMapper.writeValueAsString(authentication.getPrincipal());
		
		System.out.println(user);
		return "sssss";
	}

	@RequestMapping(value = "getPerson")
	public String getPerson() {
		mtservice.addPerson();
		return "getPerson";
	}
}
