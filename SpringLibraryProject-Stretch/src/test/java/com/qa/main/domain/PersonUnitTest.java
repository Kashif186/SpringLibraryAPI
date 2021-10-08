package com.qa.main.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PersonUnitTest {

	@InjectMocks
	private Person person;
	
	@Test
	public void personcontructor() {
		Person p = new Person(1L, "first name", "last name", 43423424, 10.23);
		assertEquals(p, new Person(1L, "first name", "last name", 43423424, 10.23));
	}
	
	@Test
	public void personcontructor2() {
		Person p = new Person("first name", "last name", 43423424);
		assertEquals(p, new Person("first name", "last name", 43423424));
	}
	
	@Test
	public void personcontructor3() {
		Person p = new Person("first name", "last name");
		assertEquals(p, new Person("first name", "last name"));
	}
	
	@Test
	public void personcontructor4() {
		Person p = new Person(1L, "first name", "last name", 4342);
		assertEquals(p, new Person(1L, "first name", "last name", 4342));
	}
	
	@Test
	public void personcontructor5() {
		Person p = new Person();
		assertEquals(p, new Person());
	}
	
	@Test
	public void personcontructor6() {
		Person p = new Person(1L, "first name", "last name");
		assertEquals(p, new Person(1L, "first name", "last name"));
	}
	
	@Test
	public void getId() {
		Person p = new Person(1L, "first name", "last name");
		assertEquals(1L, p.getId(),0);
	}
	
	@Test
	public void getFirstName() {
		Person p = new Person(1L, "first name", "last name");
		assertEquals("first name", p.getFirstName());
	}
	
	@Test
	public void getLastName() {
		Person p = new Person(1L, "first name", "last name");		
		assertEquals("last name", p.getLastName());
	}
}