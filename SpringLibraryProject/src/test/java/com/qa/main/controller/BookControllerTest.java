package com.qa.main.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.main.domain.Book;
import com.qa.main.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	
	@InjectMocks
	private BookController controller;
	
	@Mock
	private BookService service;
	
	@Test
	public void createBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		
		Mockito.when(this.service.createBook(book)).thenReturn(book);
		
		assertEquals(book, this.controller.createBook(book));
		
		Mockito.verify(this.service, Mockito.times(1)).createBook(book);
	}
	
	@Test
	public void getAllBooks() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.service.getAllBooks()).thenReturn(list);
		
		assertEquals(list, this.controller.getAllBooks());
		
		Mockito.verify(this.service, Mockito.times(1)).getAllBooks();
	}
	
	@Test
	public void getBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Long id = book.getId();
		
		Mockito.when(this.service.getBook(id)).thenReturn(book);
		
		assertEquals(book, this.controller.getBook(id));
		
		Mockito.verify(this.service, Mockito.times(1)).getBook(id);
	}
	
	@Test
	public void updateBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Long id = book.getId();
		
		Mockito.when(this.service.updateBook(id, book)).thenReturn(book);

		assertEquals(book, this.controller.updateBook(1L, book));
		
		Mockito.verify(this.service, Mockito.times(1)).updateBook(id, book);
	}
	
	@Test
	public void deleteExistingBook() {		
		Mockito.when(this.service.deleteBook(1L)).thenReturn(true);
		assertEquals(true, this.controller.deleteBook(1L));
		Mockito.verify(this.service, Mockito.times(1)).deleteBook(1L);
	}
	
	@Test
	public void deleteNonExistingBook() {		
		Mockito.when(this.service.deleteBook(1L)).thenReturn(false);
		assertEquals(false, this.controller.deleteBook(1L));
		Mockito.verify(this.service, Mockito.times(1)).deleteBook(1L);
	}

}
