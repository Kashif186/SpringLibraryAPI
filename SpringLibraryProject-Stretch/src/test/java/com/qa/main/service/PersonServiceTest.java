package com.qa.main.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.main.domain.Person;
import com.qa.main.dto.PersonDTO;
import com.qa.main.repo.PersonRepo;


@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepo repo;
	
	@Mock
	private PersonRepo personRepo;
	
	@Mock
	private ModelMapper mapper;
	

	@Test
	public void createPerson() {
		Person person = new Person(1L, "first name", "last name", 43423424, 10.23);
		PersonDTO personDTO = new PersonDTO(1L, "first name", "last name");
		
		Mockito.when(this.repo.saveAndFlush(person)).thenReturn(person);
		Mockito.when(this.mapper.map(person, PersonDTO.class)).thenReturn(personDTO);
		
		assertEquals(personDTO, this.service.createPerson(person));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(person);
		Mockito.verify(this.mapper, Mockito.times(1)).map(person, PersonDTO.class);
	}
	
	
}
