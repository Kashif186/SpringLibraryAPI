package com.qa.main.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.qa.main.dto.BookDTO;
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
	public ResponseEntity<BookDTO> createBook(@RequestBody Book book){
		return new ResponseEntity<BookDTO>(this.service.createBook(book), HttpStatus.CREATED);
	}
	
	
	//READALL
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<BookDTO>> getAllBooks(){
		return new ResponseEntity<List<BookDTO>>(this.service.getAllBooks(), HttpStatus.OK);
	}
	
	//READ
	@GetMapping("/getBook/{id}")
	public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
		return new ResponseEntity<BookDTO>(this.service.getBook(id), HttpStatus.OK);
	}
	
	//UPDATE
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@RequestBody Book updatedBook) {
		return new ResponseEntity<BookDTO>(this.service.updateBook(id, updatedBook), HttpStatus.ACCEPTED);
	}

	//DELETE
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(this.service.deleteBook(id), HttpStatus.OK);
	}
	
	//STRETCH
	
	//FInd By Name
	@GetMapping("/booktitle")
	public ResponseEntity<List<BookDTO>> findByTitle(@PathParam(value = "title") String title){
		return new ResponseEntity<List<BookDTO>>(this.service.findByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping("/findBookWithPagesLessThan/{number}")
	public ResponseEntity<List<BookDTO>> findBooksWithPagesLessThan(@PathVariable int number){
		return new ResponseEntity<List<BookDTO>>(this.service.findBooksWithPagesLessThan(number), HttpStatus.OK);
	}
	
	@GetMapping("/findBooksWithPagesGreaterThan/{number}")
	public ResponseEntity<List<BookDTO>> findBooksWithPagesGreaterThan(@PathVariable int number){
		return new ResponseEntity<List<BookDTO>>(this.service.findBooksWithPagesGreaterThan(number), HttpStatus.OK);
	}
	
	@GetMapping("/findBooksByPerson/{id}")
	public ResponseEntity<List<BookDTO>> findBooksByPerson(@PathVariable Long id){
		return new ResponseEntity<List<BookDTO>>(this.service.findBooksByPerson(id), HttpStatus.OK);
	}
	
	
	@PostMapping("/addMultipleBooks")
	public ResponseEntity<List<BookDTO>> addMultipleBooks(@RequestBody  List<Book> books){
		
		return new ResponseEntity<List<BookDTO>>(this.service.addMultipleBooks(books), HttpStatus.OK);
	}
	
	@PutMapping("/loanBook/{bookId}/{personId}")
	public ResponseEntity<BookDTO> loanBook(@PathVariable Long bookId,@PathVariable Long personId) {
		return new ResponseEntity<BookDTO>(this.service.loanBook(bookId, personId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/returnBook/{bookId}")
	public ResponseEntity<BookDTO> returnBook(@PathVariable Long bookId) {
		return new ResponseEntity<BookDTO>(this.service.returnBook(bookId), HttpStatus.ACCEPTED);
	}
	
	
	
}
