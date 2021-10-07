package com.qa.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {
	private Long id;
	private String title;
	private String author;
	private int totalPages;
	private PersonDTO person;
	
	public BookDTO(Long id, String title, String author, int totalPages, PersonDTO person) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.person = person;
	} 
	
	
	
	
}
