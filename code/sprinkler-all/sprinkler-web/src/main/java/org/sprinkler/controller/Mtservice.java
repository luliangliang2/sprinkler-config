package org.sprinkler.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sprinkler.mapper.db1.Person;
import org.sprinkler.mapper.db2.Person2;

@Service
public class Mtservice {
	
	
	@Autowired
	Person person;
	
	
	@Autowired
	Person2 person2;
	
	@Transactional
	public void addPerson() {
		try {
			System.err.println(person.getPerson());
			System.out.println(person2.getPerson());
//			person.addPerson();
//			person2.addPerson();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	
	}
}
