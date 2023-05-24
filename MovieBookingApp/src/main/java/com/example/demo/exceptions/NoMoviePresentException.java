package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code=HttpStatus.NO_CONTENT, reason="No Movies Present in List, handled by Custom Exception")
@ResponseStatus(code=HttpStatus.NO_CONTENT)
public class NoMoviePresentException extends Exception{

}
