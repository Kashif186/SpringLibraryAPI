package com.qa.main.service;

import java.util.List;

import com.qa.main.domain.Book;
import com.qa.main.repo.BookRepo;

public class BookService {

	private BookRepo repo;
	
	public BookService(BookRepo repo) {
		super();
		this.repo = repo;
	}
	
	
	//CREATE
	public Book createBook(Book book) {
		return this.repo.saveAndFlush(book);
	}

	
	//READALL
	public List<Book> getAllBooks(){
		return this.repo.findAll();
	}
	
	
	//READ
	public Book getBook(Long id) {
		return this.repo.findById(id).get();
	}
	

	//UPDATE
	public Book updateBook(Long id, Book updatedBook) {
		Book exists = this.repo.findById(id).get();
		exists.setTitle(updatedBook.getTitle());
		exists.setAuthor(updatedBook.getAuthor());
		exists.setTotalPages(updatedBook.getTotalPages());
		return this.repo.saveAndFlush(exists);
	}
	
	
	//DELETE
	public Boolean deleteBook(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
