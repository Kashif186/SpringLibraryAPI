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
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Book;
import com.qa.main.dto.BookDTO;
import com.qa.main.dto.PersonDTO;
import com.qa.main.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private BookService service;
	
	@Test
	public void createBook() throws Exception {
		Book book = new Book("Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		String bookDTOASJSON = this.mapper.writeValueAsString(bookDTO);
		
		Mockito.when(this.service.createBook(book)).thenReturn(bookDTO);
		
		mvc.perform(post("/library/createBook")
			.contentType(MediaType.APPLICATION_JSON)
			.content(bookASJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(bookDTOASJSON));
	}
	
	@Test
	public void getAllBooks() throws Exception {
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		list.add(bookDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.getAllBooks()).thenReturn(list);
		
		mvc.perform(get("/library/getAllBooks"))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));
	}
	
	@Test
	public void getBook() throws Exception {
		BookDTO book = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		Long id = book.getId();
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		
		Mockito.when(this.service.getBook(id)).thenReturn(book);
		
		mvc.perform(get("/library/getBook/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().json(bookASJSON));
	}
	
	@Test
	public void updateBook() throws Exception {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		String bookDTOASJSON = this.mapper.writeValueAsString(bookDTO);
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		
		Mockito.when(this.service.updateBook(1L, book)).thenReturn(bookDTO);

		mvc.perform(put("/library/updateBook/{id}", 1L)
				.content(bookASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(bookDTOASJSON));		
	}
	
	@Test
	public void deleteBook() throws Exception {		
		Mockito.when(this.service.deleteBook(1L)).thenReturn(true);
		mvc.perform(delete("/library/deleteBook/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));	
	}
	
	@Test
	public void findByTitle() throws Exception {	
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		list.add(bookDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.findByTitle("Harry Potter")).thenReturn(list);
		mvc.perform(get("/library//booktitle/{title}", "Harry Potter"))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));	
	}
	
	@Test
	public void findBooksWithPagesLessThan() throws Exception {	
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		list.add(bookDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.findBooksWithPagesLessThan(300)).thenReturn(list);
		mvc.perform(get("/library//findBookWithPagesLessThan/{number}", 300))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));	
	}
	
	@Test
	public void findBooksWithPagesGreaterThan() throws Exception {	
		BookDTO bookDTO = new BookDTO("Harry Potter", "J. K. Rowling", 200);
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		list.add(bookDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.findBooksWithPagesGreaterThan(100)).thenReturn(list);
		mvc.perform(get("/library//findBooksWithPagesGreaterThan/{number}", 100))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));	
	}
	
	@Test
	public void findBooksByPerson() throws Exception {	
		PersonDTO p = new PersonDTO(3L, "Person1", "Person2");
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200, p);
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		list.add(bookDTO);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.findBooksByPerson(3L)).thenReturn(list);
		
		mvc.perform(get("/library/findBooksByPerson/{id}", 3L))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));	
	}
	
	@Test
	public void addMultipleBooks() throws Exception {	
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);
		BookDTO bookDTO2 = new BookDTO(2L, "Harry Potter 2", "J. K. Rowling", 400);
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
		Book book2 = new Book(2L, "Harry Potter 2", "J. K. Rowling", 400);
		
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		list.add(book2);
		
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		dtoList.add(bookDTO);
		dtoList.add(bookDTO2);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		String dtoListASJSON = this.mapper.writeValueAsString(dtoList);
		Mockito.when(this.service.addMultipleBooks(list)).thenReturn(dtoList);
		
		mvc.perform(post("/library//addMultipleBooks")
				.content(listASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(dtoListASJSON));
	}
	
	@Test
	public void loanBook() throws Exception {	
		PersonDTO p = new PersonDTO(3L, "Person1", "Person2");
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200, p);

		String bookDTOASJSON = this.mapper.writeValueAsString(bookDTO);
	
		
		Mockito.when(this.service.loanBook(1L, 3L)).thenReturn(bookDTO);
		
		mvc.perform(put("/library/loanBook/{bookId}/{personId}", 1L, 3L))
				.andExpect(status().isAccepted())
				.andExpect(content().json(bookDTOASJSON));	
	}
	
	@Test
	public void returnBook() throws Exception {	
		BookDTO bookDTO = new BookDTO(1L, "Harry Potter", "J. K. Rowling", 200);

		String bookDTOASJSON = this.mapper.writeValueAsString(bookDTO);
	
		Mockito.when(this.service.returnBook(1L)).thenReturn(bookDTO);
		
		mvc.perform(put("/library/returnBook/{id}", 1L))
				.andExpect(status().isAccepted())
				.andExpect(content().json(bookDTOASJSON));	
	}


}
