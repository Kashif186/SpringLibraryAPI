package com.qa.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	
	public PersonDTO(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

}
