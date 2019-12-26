package org.sprinkler.oath2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sprinkler.oath2.server.mapper.UserDao;

@RestController
@RequestMapping("test")
public class TestController {

	
	@Autowired
	UserDao useDado;
	
	@RequestMapping("test")
	public String test() {
		System.err.println(useDado.getAll());
		return "sss";
	}
	
}
