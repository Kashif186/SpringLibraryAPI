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
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Book;
import com.qa.main.service.BookService;

import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {
	
	 @Autowired
	 private MockMvc mvc;    //postman

	 @Autowired
	 private ObjectMapper mapper;   //convert to and from JSON
	
	 @Autowired
	 private BookService service;
	
	@Test
	public void createBook() throws Exception {
		Book bookEntry = new Book("The Hunger Games", "Suzanne Collins", 500);
		Book bookReturn = new Book(2L, "The Hunger Games", "Suzanne Collins", 500);
		
		String bookEntryASJSON = this.mapper.writeValueAsString(bookEntry);
		String bookReturnASJSON = this.mapper.writeValueAsString(bookReturn);
		
		mvc.perform(post("/library/createBook")
			.contentType(MediaType.APPLICATION_JSON)
			.content(bookEntryASJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(bookReturnASJSON));
	}
	
	@Test
	public void getAllBooks() throws Exception {
		Book book = new Book(1L, "Son", "Lois Lowry", 393);
		
		List<Book> list = new ArrayList<>();

		list.add(book);
		
		String listASJSON = this.mapper.writeValueAsString(list);
		
		mvc.perform(get("/library/getAllBooks"))
				.andExpect(status().isOk())
				.andExpect(content().json(listASJSON));
	}
	
	@Test
	public void getBook() throws Exception {
		Book book = new Book(1L, "Son", "Lois Lowry", 393);
		
		String bookASJSON = this.mapper.writeValueAsString(book);
		
		mvc.perform(get("/library/getBook/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().json(bookASJSON));
	}
	
	@Test
	public void updateBook() throws Exception {
		Book bookEntry = new Book("Son", "Lois Lowry", 650);
		Book bookReturn = new Book(1L, "Son", "Lois Lowry", 650);
		
		String bookEntryASJSON = this.mapper.writeValueAsString(bookEntry);
		String bookReturnASJSON = this.mapper.writeValueAsString(bookReturn);

		mvc.perform(put("/library/updateBook/{id}", 1L)
				.content(bookEntryASJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(bookReturnASJSON));		
	}
	
	@Test
	public void deleteBook() throws Exception {		
		mvc.perform(delete("/library/deleteBook/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));	
	}
	


}
