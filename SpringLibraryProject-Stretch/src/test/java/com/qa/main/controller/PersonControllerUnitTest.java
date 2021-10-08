package com.qa.main.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Person;
import com.qa.main.domain.Person;
import com.qa.main.dto.PersonDTO;
import com.qa.main.dto.PersonDTO;
import com.qa.main.dto.PersonDTO;
import com.qa.main.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerUnitTest {


	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private PersonService service;
	
	@Test
	public void createPerson() throws Exception {
		Person person = new Person(1L, "Person1", "Person1");
		PersonDTO personDTO = new PersonDTO(1L, "Person1", "Person1");
		
		String personASJSON = this.mapper.writeValueAsString(person);
		String personDTOASJSON = this.mapper.writeValueAsString(personDTO);
		
		Mockito.when(this.service.createPerson(person)).thenReturn(personDTO);
		
		mvc.perform(post("/library/createPerson")
			.contentType(MediaType.APPLICATION_JSON)
			.content(personASJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(personDTOASJSON));
	}
	
	@Test
	public void getAllPeople() throws Exception {
		PersonDTO personDTO = new PersonDTO(1L, "Person1", "Person1");
		
		List<PersonDTO> list = new ArrayList<PersonDTO>();

		list.add(personDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.getAllPeople()).thenReturn(list);
		
		mvc.perform(get("/library/getAllPeople"))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));
	}
	
	@Test
	public void getPerson() throws Exception {
		PersonDTO person = new PersonDTO(1L, "Person1", "Person1");
		Long id = person.getId();
		
		String personASJSON = this.mapper.writeValueAsString(person);
		
		Mockito.when(this.service.getPerson(id)).thenReturn(person);
		
		mvc.perform(get("/library/getPerson/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().json(personASJSON));
	}
	
	@Test
	public void updatePerson() throws Exception {
		Person person = new Person(1L, "Person1", "Person1");
		
		PersonDTO personDTO = new PersonDTO(1L, "Person1", "Person1");
		String personDTOASJSON = this.mapper.writeValueAsString(personDTO);
		
		String personASJSON = this.mapper.writeValueAsString(person);
		
		Mockito.when(this.service.updatePerson(1L, person)).thenReturn(personDTO);

		mvc.perform(put("/library/updatePerson/{id}", 1L)
				.content(personASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(personDTOASJSON));		
	}
	
	@Test
	public void deletePerson() throws Exception {		
		Mockito.when(this.service.deletePerson(1L)).thenReturn(true);
		mvc.perform(delete("/library/deletePerson/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));	
	}
	
	@Test
	public void addMultiplePeople() throws Exception {	
		PersonDTO personDTO = new PersonDTO(1L, "Person1", "Person1");
		PersonDTO personDTO2 = new PersonDTO(2L, "Person2", "Person2");
		Person person = new Person(1L, "Person1", "Person1");
		Person person2 = new Person(2L, "Person2", "Person2");
		
		List<Person> list = new ArrayList<Person>();
		list.add(person);
		list.add(person2);
		
		List<PersonDTO> dtoList = new ArrayList<PersonDTO>();
		dtoList.add(personDTO);
		dtoList.add(personDTO2);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		String dtoListASJSON = this.mapper.writeValueAsString(dtoList);
		
		Mockito.when(this.service.addMultiplePeople(list)).thenReturn(dtoList);
		
		mvc.perform(post("/library/addMultiplePeople")
				.content(listASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(dtoListASJSON));
	}
	
	@Test
	public void findPeopleWithFinesGreaterThan() throws Exception {	
		PersonDTO personDTO = new PersonDTO(1L, "Person1", "Person1");
		
		List<PersonDTO> list = new ArrayList<PersonDTO>();

		list.add(personDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.findPeopleWithFinesGreaterThan(10)).thenReturn(list);
		mvc.perform(get("/library//findPeopleWithFinesGreaterThan/{number}", 10))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));	
	}
	
}
