package com.qa.main.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.main.domain.Book;
import com.qa.main.domain.Person;
import com.qa.main.repo.BookRepo;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceUnitTest {

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
		
		Optional<Book> bookOptional = Optional.of(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		
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
	
	@Test
	public void findByTitle() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.repo.findByTitle("Harry Potter")).thenReturn(list);
		assertEquals(list, this.service.findByTitle("Harry Potter"));
		Mockito.verify(this.repo, Mockito.times(1)).findByTitle("Harry Potter");
	}
	
	@Test
	public void findBooksWithPagesLessThan() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.repo.findBooksWithPagesLessThan(300)).thenReturn(list);
		assertEquals(list, this.service.findBooksWithPagesLessThan(300));
		Mockito.verify(this.repo, Mockito.times(1)).findBooksWithPagesLessThan(300);
	}
	
	@Test
	public void findBooksWithPagesGreaterThan() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 500);
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.repo.findBooksWithPagesGreaterThan(300)).thenReturn(list);
		assertEquals(list, this.service.findBooksWithPagesGreaterThan(300));
		Mockito.verify(this.repo, Mockito.times(1)).findBooksWithPagesGreaterThan(300);
	}
	
	@Test
	public void findBooksByPerson() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Long person_id = 4L;
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		Mockito.when(this.repo.findBooksByPerson(person_id)).thenReturn(list);
		assertEquals(list, this.service.findBooksByPerson(person_id));
		Mockito.verify(this.repo, Mockito.times(1)).findBooksByPerson(person_id);
	}
	
	@Test
	public void loanBook() {		
		Person person = new Person(4L, "firstName","lastName", 1582645734);
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200, person);
		Mockito.when(this.repo.loanBook(1L, 4L)).thenReturn(book);
		assertEquals(book, this.service.loanBook(1L, 4L));
		Mockito.verify(this.repo, Mockito.times(1)).loanBook(1L, 4L);
	}
	
	@Test
	public void returnBook() {		

		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Mockito.when(this.repo.returnBook(1L)).thenReturn(book);
		assertEquals(book, this.service.returnBook(1L));
		Mockito.verify(this.repo, Mockito.times(1)).returnBook(1L);
	}
	
	
	
}
