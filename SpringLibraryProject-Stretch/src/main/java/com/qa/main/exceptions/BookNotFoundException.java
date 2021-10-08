package com.qa.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book not found")
public class BookNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 5417775652096279227L;
	
}
