package com.qa.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.Book;
import com.qa.main.service.BookService;

@RestController
@RequestMapping("/library")
public class BookController {

	private BookService service;
	
	public BookController(BookService service) {
		super();
		this.service = service;
	}

	//http://localhost:9003/library/	
	
	//CREATE
	@PostMapping("/createBook")
	public Book createBook(@RequestBody Book book){
		return this.service.createBook(book);
	}
	
	
	//READALL
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		return this.service.getAllBooks();
	}
	
	//READ
	@GetMapping("/getBook/{id}")
	public Book getBook(@PathVariable Long id) {
		return this.service.getBook(id);
	}
	
	//UPDATE
	@PutMapping("/updateBook/{id}")
	public Book updateBook(@PathVariable Long id,@RequestBody Book updatedBook) {
		return this.service.updateBook(id, updatedBook);
	}

	//DELETE
	@DeleteMapping("/deleteBook/{id}")
	public boolean deleteBook(@PathVariable Long id) {
		return this.service.deleteBook(id);
	}
}
