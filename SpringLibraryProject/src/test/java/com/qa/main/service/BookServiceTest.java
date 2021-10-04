package com.qa.main.service;

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
import com.qa.main.repo.BookRepo;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	private BookService service;
	
	@Mock
	private BookRepo repo;
	
	@Test
	public void createBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		
		Mockito.when(this.repo.saveAndFlush(book)).thenReturn(book);
		
		assertEquals(book, this.service.createBook(book));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(book);
	}
	
	@Test
	public void getAllBooks() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.repo.findAll()).thenReturn(list);
		
		assertEquals(list, this.service.getAllBooks());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void getBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Long id = book.getId();
		
		Optional<Book> bookOptional = Optional.ofNullable(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		
		Mockito.when(this.repo.findById(id)).thenReturn(bookOptional);
		
		assertEquals(book, this.service.getBook(id));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void updateBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Book book2 = new Book(1L, "Potter", "Rowling", 100);
		Long id = book.getId();
		
		Optional<Book> bookOptional = Optional.ofNullable(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		
		Mockito.when(this.repo.findById(id)).thenReturn(bookOptional);
		Mockito.when(this.repo.saveAndFlush(book)).thenReturn(book2);
		
		assertEquals(book2, this.service.updateBook(1L, book));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(book);
	}
	
	@Test
	public void deleteExistingBook() {		
		Mockito.doNothing().when(this.repo).deleteById(1l);
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		assertEquals(true, this.service.deleteBook(1L));
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void deleteNonExistingBook() {		
		Mockito.when(this.repo.existsById(105L)).thenReturn(true);
		assertEquals(false, this.service.deleteBook(105L));
		Mockito.verify(this.repo, Mockito.times(1)).existsById(105L);
	}
	
	
}
