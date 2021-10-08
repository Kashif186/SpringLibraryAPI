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
import com.qa.main.dto.PersonDTO;
import com.qa.main.service.PersonService;

@RestController
@RequestMapping("/library")
public class PersonController {

	private PersonService service;

	public PersonController(PersonService service) {
		super();
		this.service = service;
	}

	// http://localhost:9003/library/

	// CREATE
	@PostMapping("/createPerson")
	public ResponseEntity<PersonDTO> createPerson(@RequestBody Person person) {
		return new ResponseEntity<PersonDTO>(this.service.createPerson(person), HttpStatus.CREATED);
	}

	// READALL
	@GetMapping("/getAllPeople")
	public ResponseEntity<List<PersonDTO>> getAllPeople() {
		return new ResponseEntity<List<PersonDTO>>(this.service.getAllPeople(), HttpStatus.OK);
	}

	// READ
	@GetMapping("/getPerson/{id}")
	public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
		return new ResponseEntity<PersonDTO>(this.service.getPerson(id), HttpStatus.OK);
	}

	// UPDATE
	@PutMapping("/updatePerson/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
		return new ResponseEntity<PersonDTO>(this.service.updatePerson(id, updatedPerson), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/deletePerson/{id}")
	public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.deletePerson(id), HttpStatus.OK);
	}

	@PostMapping("/addMultiplePeople")
	public ResponseEntity<List<PersonDTO>> addMultiplePeople(@RequestBody List<Person> people) {
		return new ResponseEntity<List<PersonDTO>>(this.service.addMultiplePeople(people), HttpStatus.OK);
	}

	@GetMapping("/findPeopleWithFinesGreaterThan/{number}")
	public ResponseEntity<List<PersonDTO>> findPeopleWithFinesGreaterThan(@PathVariable double number) {
		return new ResponseEntity<List<PersonDTO>>(this.service.findPeopleWithFinesGreaterThan(number), HttpStatus.OK);
	}

}
