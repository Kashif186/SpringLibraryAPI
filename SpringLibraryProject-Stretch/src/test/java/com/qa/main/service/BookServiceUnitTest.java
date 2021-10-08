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
import org.modelmapper.ModelMapper;

import com.qa.main.domain.Book;
import com.qa.main.domain.Person;
import com.qa.main.dto.BookDTO;
import com.qa.main.repo.BookRepo;
import com.qa.main.repo.PersonRepo;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceUnitTest {

	@InjectMocks
	private BookService service;
	
	@Mock
	private BookRepo repo;
	
	@Mock
	private PersonRepo personRepo;
	
	@Mock
	private ModelMapper mapper;
	
	@Test
	public void createBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		
		Mockito.when(this.repo.saveAndFlush(book)).thenReturn(book);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
		
		assertEquals(bookDTO, this.service.createBook(book));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(book);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void getAllBooks() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.findAll()).thenReturn(list);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
		
		assertEquals(dtoList, this.service.getAllBooks());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void getBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		
		Optional<Book> bookOptional = Optional.ofNullable(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		
		Mockito.when(this.repo.findById(1L)).thenReturn(bookOptional);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
		
		assertEquals(bookDTO, this.service.getBook(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void updateBook() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);

		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		
		Optional<Book> bookOptional = Optional.of(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		
		Mockito.when(this.repo.findById(1L)).thenReturn(bookOptional);
		Mockito.when(this.repo.saveAndFlush(book)).thenReturn(book);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
		
		assertEquals(bookDTO, this.service.updateBook(1L, book));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(book);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void deleteExistingBook() {		
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		Mockito.doNothing().when(this.repo).deleteById(1L);
		assertEquals(false, this.service.deleteBook(1L));
		
		Mockito.verify(this.repo, Mockito.times(2)).existsById(1L);
		
	}
	
	
	@Test
	public void findByTitle() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		
		List<Book> list = new ArrayList<>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.findByTitle("Harry Potter")).thenReturn(list);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
		
		assertEquals(dtoList, this.service.findByTitle("Harry Potter"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByTitle("Harry Potter");
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void findBooksWithPagesLessThan() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		List<Book> list = new ArrayList<>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.findBooksWithPagesLessThan(300)).thenReturn(list);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		
		assertEquals(dtoList, this.service.findBooksWithPagesLessThan(300));
		
		Mockito.verify(this.repo, Mockito.times(1)).findBooksWithPagesLessThan(300);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);

	}
	
	@Test
	public void findBooksWithPagesGreaterThan() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 500);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		List<Book> list = new ArrayList<>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.findBooksWithPagesGreaterThan(300)).thenReturn(list);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		
		assertEquals(dtoList, this.service.findBooksWithPagesGreaterThan(300));
		
		Mockito.verify(this.repo, Mockito.times(1)).findBooksWithPagesGreaterThan(300);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);

	}
	
	@Test
	public void findBooksByPerson() {		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		List<Book> list = new ArrayList<>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.findBooksByPerson(1L)).thenReturn(list);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		assertEquals(dtoList, this.service.findBooksByPerson(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findBooksByPerson(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);

	}
	
	@Test
	public void addMultipleBooks() {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		List<Book> list = new ArrayList<>();
		list.add(book);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		
		Mockito.when(this.repo.saveAndFlush(book)).thenReturn(book);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		assertEquals(dtoList, this.service.addMultipleBooks(list));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(book);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);

	}
	
	@Test
	public void loanBook() {		
		Person person = new Person(4L, "firstName","lastName", 1582645734);
		Optional<Person> person1 = Optional.of(new Person(4L, "firstName","lastName", 1582645734));
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Optional<Book> book1 = Optional.of(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		
		Mockito.when(this.personRepo.findById(4L)).thenReturn(person1);
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		Mockito.when(this.personRepo.existsById(4L)).thenReturn(true);
		Mockito.when(this.repo.loanBook(1L,person)).thenReturn(1);
		Mockito.when(this.repo.findById(1L)).thenReturn(book1);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		assertEquals(bookDTO, this.service.loanBook(1L, 4L));
		
		Mockito.verify(this.personRepo, Mockito.times(1)).existsById(4L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
		Mockito.verify(this.personRepo, Mockito.times(1)).existsById(4L);
		Mockito.verify(this.repo, Mockito.times(1)).loanBook(1L, person);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	@Test
	public void returnBook() {		
		
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Optional<Book> book1 = Optional.of(new Book(1L, "Harry Potter", "J. K. Rowling", 200));
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		Mockito.when(this.repo.returnBook(1L)).thenReturn(1);
		Mockito.when(this.repo.findById(1L)).thenReturn(book1);
		Mockito.when(this.mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

		assertEquals(bookDTO, this.service.returnBook(1L));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).returnBook(1L);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(book, BookDTO.class);
	}
	
	
	
}
