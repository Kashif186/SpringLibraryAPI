package com.qa.main.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.qa.main.domain.Person;
import com.qa.main.dto.PersonDTO;
import com.qa.main.exceptions.BookNotFoundException;
import com.qa.main.exceptions.PersonNotFoundException;
import com.qa.main.repo.PersonRepo;

@Service
public class PersonService {

	private PersonRepo repo;
	private ModelMapper mapper;

	public PersonService(PersonRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public PersonDTO mapToDTO(Person p) {
		return this.mapper.map(p, PersonDTO.class);
	}

	public Person mapFromDTO(PersonDTO p) {
		return this.mapper.map(p, Person.class);
	}

	// CREATE
	public PersonDTO createPerson(Person person) {
		this.repo.saveAndFlush(person);
		return this.mapToDTO(person);
	}

	// READALL
	public List<PersonDTO> getAllPeople() {
		List<PersonDTO> newList = new ArrayList<PersonDTO>();
		for (Person p : this.repo.findAll()) {
			newList.add(this.mapToDTO(p));
		}
		;

		if (newList.size() == 0) {
			throw new PersonNotFoundException();
		}

		return newList;
	}

	// READ
	public PersonDTO getPerson(Long id) {
		Person person = this.repo.findById(id).orElseThrow(PersonNotFoundException::new);
		return this.mapToDTO(person);
	}

	// UPDATE
	public PersonDTO updatePerson(Long id, Person updatedPerson) {
		Person exists = this.repo.findById(id).orElseThrow(BookNotFoundException::new);
		exists.setFirstName(updatedPerson.getFirstName());
		exists.setLastName(updatedPerson.getLastName());
		this.repo.saveAndFlush(exists);
		return this.mapToDTO(exists);
	}

	// DELETE
	public Boolean deletePerson(Long id) {
		if (!this.repo.existsById(id)) {
			throw new PersonNotFoundException();
		}

		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<PersonDTO> findPeopleWithFinesGreaterThan(double number) {
		List<PersonDTO> newList = new ArrayList<PersonDTO>();
		for (Person p : this.repo.findPeopleWithFinesGreaterThan(number)) {
			newList.add(this.mapToDTO(p));
		}
		;

		if (newList.size() == 0) {
			throw new PersonNotFoundException();
		}

		return newList;
	}

	public List<PersonDTO> addMultiplePeople(List<Person> people) {
		List<PersonDTO> dtoList = new ArrayList<PersonDTO>();

		for (Person p : people) {
			dtoList.add(this.mapToDTO(p));
		}

		return dtoList;
	}

}
