package com.qa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Person;
import com.qa.main.repo.PersonRepo;

@Service
public class PersonService {
	
	private PersonRepo repo;

	public PersonService(PersonRepo repo) {
		super();
		this.repo = repo;
	}
	
	//CREATE
	public Person createPerson(Person person) {
		return this.repo.saveAndFlush(person);
	}

	
	//READALL
	public List<Person> getAllPersons(){
		return this.repo.findAll();
	}
	
	
	//READ
	public Person getPerson(Long id) {
		return this.repo.findById(id).get();
	}
	

	//UPDATE
	public Person updatePerson(Long id, Person updatedPerson) {
		Person exists = this.repo.findById(id).get();
		exists.setFirstName(updatedPerson.getFirstName());
		exists.setLastName(updatedPerson.getLastName());
		exists.setPhoneNumber(updatedPerson.getPhoneNumber());
		exists.setFine(updatedPerson.getFine());
		return this.repo.saveAndFlush(exists);
	}
	
	
	//DELETE
	public Boolean deletePerson(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public List<Person> findPeopleWithFinesGreaterThan(double number){
		return this.repo.findPeopleWithFinesGreaterThan(number);
	}
	
	

}
