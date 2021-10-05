package com.qa.main.domain;



import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class BookUnitTest {
	
	@InjectMocks
	private Book book;
	
	@Test
	public void bookcontructor() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		assertEquals(b, new Book(1L, "Harry Potter", "J. K. Rowling", 200));
	}
	
	@Test
	public void bookcontructor2() {
		Book b = new Book();
		assertEquals(b, new Book());
	}
	
	@Test
	public void bookcontructor3() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling");
		assertEquals(b, new Book(1L, "Harry Potter", "J. K. Rowling"));
	}
	
	@Test
	public void getId() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		assertEquals(1L, b.getId(),0);
	}
	
	@Test
	public void getTitle() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		assertEquals("Harry Potter", b.getTitle());
	}
	
	@Test
	public void getAuthor() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		assertEquals("J. K. Rowling", b.getAuthor());
	}
	
	@Test
	public void getTotalPages() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		assertEquals(200, b.getTotalPages());
	}
	
	
	@Test
	public void setId() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		b.setId(4L);
		assertEquals(4L, b.getId(),0);
	}
	
	@Test
	public void setTitle() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		b.setTitle("Potter");
		assertEquals("Potter", b.getTitle());
	}
	
	@Test
	public void setAuthor() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		b.setAuthor("AAA");
		assertEquals("AAA", b.getAuthor());
	}
	
	@Test
	public void setTotalPages() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		b.setTotalPages(100);
		assertEquals(100, b.getTotalPages());
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Book.class)
			.suppress(Warning.NONFINAL_FIELDS)
			.usingGetClass()
			.verify();
	}
	
	@Test
	public void tostring() {
		Book b = new Book(1L, "Harry Potter", "J. K. Rowling",200);
		assertEquals("Book [id=1, title=Harry Potter, author=J. K. Rowling, totalPages=200]", b.toString());
	}

}
