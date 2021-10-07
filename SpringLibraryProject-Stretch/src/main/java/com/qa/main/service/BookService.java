package com.qa.main.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.main.domain.Book;
import com.qa.main.domain.Person;
import com.qa.main.dto.BookDTO;
import com.qa.main.exceptions.BookNotFoundException;
import com.qa.main.exceptions.PersonNotFoundException;
import com.qa.main.repo.BookRepo;
import com.qa.main.repo.PersonRepo;

@Service
public class BookService {

	private BookRepo repo;
	private PersonRepo personRepo;
	private ModelMapper mapper;
	
	@Autowired
	public BookService(BookRepo repo, PersonRepo personRepo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.personRepo = personRepo;
		this.mapper = mapper;
	}

	public BookDTO mapToDTO(Book b) {
		return this.mapper.map(b, BookDTO.class);
	}
	
	
	public Book mapFromDTO(BookDTO b) {
		return this.mapper.map(b, Book.class);
	}

	//CREATE
	public BookDTO createBook(Book book) {
		this.repo.saveAndFlush(book);
		return this.mapToDTO(book);
	}

	
	//READALL
	public List<BookDTO> getAllBooks(){
		List<BookDTO> newList = new ArrayList<BookDTO>();
		for (Book b:  this.repo.findAll()) {
			newList.add(this.mapToDTO(b));
		};
		
		if(newList.size() == 0) {
			throw new BookNotFoundException();
		}
		return newList;
	}
	
	
	//READ
	public BookDTO getBook(Long id) {
		Book book =  this.repo.findById(id).orElseThrow(BookNotFoundException::new);
		return this.mapToDTO(book);
	}
	

	//UPDATE
	public BookDTO updateBook(Long id, Book updatedBook) {
		Book exists = this.repo.findById(id).orElseThrow(BookNotFoundException::new);
		exists.setTitle(updatedBook.getTitle());
		exists.setAuthor(updatedBook.getAuthor());
		exists.setTotalPages(updatedBook.getTotalPages());
		this.repo.saveAndFlush(exists);
		return this.mapToDTO(exists);
	}
	
	
	//DELETE
	public Boolean deleteBook(Long id) {
		if (!this.repo.existsById(id)) {
			throw new BookNotFoundException();
		}
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	
	//STRETCH
	
	public List<BookDTO> findByTitle(String title){
		List<BookDTO> newList = new ArrayList<BookDTO>();
		for (Book b:  this.repo.findByTitle(title)) {
			newList.add(this.mapToDTO(b));
		};
		
		if (newList.size()==0) {
			throw new BookNotFoundException();
		}
		return newList;
	}
	
	public List<BookDTO> findBooksWithPagesLessThan(int page){
		List<BookDTO> newList = new ArrayList<BookDTO>();
		for (Book b:  this.repo.findBooksWithPagesLessThan(page)) {
			newList.add(this.mapToDTO(b));
		};
		
		if (newList.size()==0) {
			throw new BookNotFoundException();
		}
		
		return newList;
	}
	
	public List<BookDTO> findBooksWithPagesGreaterThan(int page){
		List<BookDTO> newList = new ArrayList<BookDTO>();
		for (Book b:  this.repo.findBooksWithPagesGreaterThan(page)) {
			newList.add(this.mapToDTO(b));
		};
		
		if (newList.size()==0) {
			throw new BookNotFoundException();
		}
		
		return newList;
	}
	
	public List<BookDTO> findBooksByPerson(Long id){
		List<BookDTO> newList = new ArrayList<BookDTO>();
		for (Book b:  this.repo.findBooksByPerson(id)) {
			newList.add(this.mapToDTO(b));
		};
		
		if (newList.size()==0) {
			throw new BookNotFoundException();
		}
		
		return newList;
	}
	
	public List<BookDTO> addMultipleBooks(List<Book> books) {
		List<BookDTO> dtoList = new ArrayList<BookDTO>();
		
		for (Book b : books) {
			dtoList.add(this.mapToDTO(b));
		}
		
		return dtoList;
	}
	
	public BookDTO loanBook(Long bookId, Long personId) {
		Person p = this.personRepo.findById(personId).get();
		
		if (!this.repo.existsById(bookId)) {
			throw new BookNotFoundException();
		}
		
		if (!this.personRepo.existsById(personId)) {
			throw new PersonNotFoundException();
		}
		
		if (this.repo.loanBook(bookId, p) == 1) {
			Book book = this.repo.findById(bookId).get();
			return this.mapToDTO(book);
		} else {
			return null;
		}
	}
	
	public BookDTO returnBook(Long bookId) {
		
		if (!this.repo.existsById(bookId)) {
			throw new BookNotFoundException();
		}
		
		if (this.repo.returnBook(bookId) == 1) {
			Book book = this.repo.findById(bookId).get();
			return this.mapToDTO(book);
		} else {
			return null; //throw custom exception here
		}
	}
	

}
