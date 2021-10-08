package com.qa.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person not found")
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2078516613858398501L;

}
