package edu.jhu.oris.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jhu.oris.portal.model.Person;
import edu.jhu.oris.portal.service.PersonService;

@RestController
@RequestMapping("/")
@ComponentScan("edu.jhu.oris.portal")
public class AppController {
	
	@Autowired
	PersonService service;
	
	@GetMapping("/personlist")
	public ResponseEntity<List<Person>> listEmployees(ModelMap model) {
		List<Person> persons = service.findAllPersons();
		return ResponseEntity.ok().body(persons);
	}

	@GetMapping(value = { "/delete?{id}" }) 
	public ResponseEntity<List<Person>> deleteEmployee(@PathVariable String id) {
		service.deletePerson(id);
		List<Person> persons = service.findAllPersons();
		return ResponseEntity.ok().body(persons);
	}

}
