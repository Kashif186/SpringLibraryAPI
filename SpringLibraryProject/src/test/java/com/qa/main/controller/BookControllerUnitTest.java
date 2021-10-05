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
import com.qa.main.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest
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
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		
		Mockito.when(this.service.createBook(book)).thenReturn(book);
		
		mvc.perform(post("/library/createBook")
			.contentType(MediaType.APPLICATION_JSON)
			.content(bookASJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(bookASJSON));
	}
	
	@Test
	public void getAllBooks() throws Exception {
		Book book = new Book("Harry Potter", "J. K. Rowling", 200);
		
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		Mockito.when(this.service.getAllBooks()).thenReturn(list);
		
		mvc.perform(get("/library/getAllBooks"))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));
	}
	
	@Test
	public void getBook() throws Exception {
		Book book = new Book(1L, "Harry Potter", "J. K. Rowling", 200);
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
		Long id = book.getId();
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		
		Mockito.when(this.service.updateBook(id, book)).thenReturn(book);

		mvc.perform(put("/library/updateBook/{id}", 1L)
				.content(bookASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(bookASJSON));		
	}
	
	@Test
	public void deleteBook() throws Exception {		
		Mockito.when(this.service.deleteBook(1L)).thenReturn(true);
		mvc.perform(delete("/library/deleteBook/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));	
	}
	


}
