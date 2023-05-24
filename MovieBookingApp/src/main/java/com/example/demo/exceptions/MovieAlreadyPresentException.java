package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Movie Already Present, handled by Custom Exception")
public class MovieAlreadyPresentException extends Exception{
	

}
