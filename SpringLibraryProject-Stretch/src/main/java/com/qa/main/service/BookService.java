package com.qa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Book;
import com.qa.main.domain.Person;
import com.qa.main.repo.BookRepo;
import com.qa.main.repo.PersonRepo;

@Service
public class BookService {

	private BookRepo repo;
	private PersonRepo personRepo;
	
	
	public BookService(BookRepo repo, PersonRepo personRepo) {
		super();
		this.repo = repo;
		this.personRepo = personRepo;
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
	
	
	//STRETCH
	
	public List<Book> findByTitle(String title){
		return this.repo.findByTitle(title);
	}
	
	public List<Book> findBooksWithPagesLessThan(int page){
		return this.repo.findBooksWithPagesLessThan(page);
	}
	
	public List<Book> findBooksWithPagesGreaterThan(int page){
		return this.repo.findBooksWithPagesGreaterThan(page);
	}
	
	public List<Book> findBooksByPerson(Long id){
		return this.repo.findBooksByPerson(id);
	}
	
	public Book loanBook(Long bookId, Long personId) {
		Person p = this.personRepo.findById(personId).get();
		
		if (this.repo.loanBook(bookId, p) == 1) {
			return this.repo.findById(bookId).get();
		} else {
			return null;
		}
		
		
		
	}
	
	public Book returnBook(Long bookId) {
		if (this.repo.returnBook(bookId) == 1) {
			return this.repo.findById(bookId).get();
		} else {
			return null; //throw custom exception here
		}
	}
	

}
