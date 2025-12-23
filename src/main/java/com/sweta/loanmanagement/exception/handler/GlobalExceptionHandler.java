package com.sweta.loanmanagement.exception.handler;

import com.sweta.loanmanagement.dto.ErrorResponse;
import com.sweta.loanmanagement.exception.CustomerNotFoundException;
import com.sweta.loanmanagement.exception.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleCustomerNotFound(CustomerNotFoundException ex){
        ErrorResponse error= new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<ErrorResponse>handleLoanNotFound(LoanNotFoundException ex){
        ErrorResponse error=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
