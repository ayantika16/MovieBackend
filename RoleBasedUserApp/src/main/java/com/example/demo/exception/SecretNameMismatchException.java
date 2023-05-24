package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Petname not matched, handled by custom exception")
public class SecretNameMismatchException extends Exception{

}
