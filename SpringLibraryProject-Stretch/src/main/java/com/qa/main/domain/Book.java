package com.qa.main.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private int totalPages;
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person; 
	
	public Book(Long id, String title, String author, int totalPages) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
	}

	public Book() {
		super();
	}
	

	public Book(String title, String author, int totalPages) {
		super();
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
	}

	public Book(Long id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, person, title, totalPages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(person, other.person)
				&& Objects.equals(title, other.title) && totalPages == other.totalPages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", totalPages=" + totalPages + ", person="
				+ person + "]";
	}


	
	
}
