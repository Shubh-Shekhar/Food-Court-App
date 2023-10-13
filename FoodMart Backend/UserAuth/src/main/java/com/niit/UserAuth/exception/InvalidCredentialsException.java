package com.niit.UserAuth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Please enter correct details")
public class InvalidCredentialsException extends Exception {
}
