package com.qa.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.Person;
import com.qa.main.service.PersonService;

@RestController
@RequestMapping("/library")
public class PersonController {
	
	private PersonService service;

	public PersonController(PersonService service) {
		super();
		this.service = service;
	}
	
	//http://localhost:9003/library/	
	
	//CREATE
		@PostMapping("/createPerson")
		public ResponseEntity<Person> createPerson(@RequestBody Person Person){
			return new ResponseEntity<Person>(this.service.createPerson(Person), HttpStatus.CREATED);
		}
		
		
		//READALL
		@GetMapping("/getAllPersons")
		public ResponseEntity<List<Person>> getAllPersons(){
			return new ResponseEntity<List<Person>>(this.service.getAllPersons(), HttpStatus.OK);
		}
		
		//READ
		@GetMapping("/getPerson/{id}")
		public ResponseEntity<Person> getPerson(@PathVariable Long id) {
			return new ResponseEntity<Person>(this.service.getPerson(id), HttpStatus.OK);
		}
		
		//UPDATE
		@PutMapping("/updatePerson/{id}")
		public ResponseEntity<Person> updatePerson(@PathVariable Long id,@RequestBody Person updatedPerson) {
			return new ResponseEntity<Person>(this.service.updatePerson(id, updatedPerson), HttpStatus.ACCEPTED);
		}

		//DELETE
		@DeleteMapping("/deletePerson/{id}")
		public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {
			return new ResponseEntity<Boolean>(this.service.deletePerson(id), HttpStatus.OK);
		}
	@PostMapping("/addMultiplePeople")
	public ResponseEntity<List<Person>> addMultiplePeople(@RequestBody  List<Person> people){
		for (Person p : people) {
			this.service.createPerson(p);
		}
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
	}
	
	@GetMapping("/findPeopleWithFinesGreaterThan/{id}")
	public ResponseEntity<List<Person>> findPeopleWithFinesGreaterThan(@PathVariable double number){
		return new ResponseEntity<List<Person>>(this.service.findPeopleWithFinesGreaterThan(number), HttpStatus.OK);
	}
	

}
