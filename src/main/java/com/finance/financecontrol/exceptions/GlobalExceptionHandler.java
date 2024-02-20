package com.finance.financecontrol.exceptions;

import com.finance.financecontrol.dtos.responses.HttpErrorWrapperDtoResponse;
import com.finance.financecontrol.exceptions.http.BadRequestException;
import com.finance.financecontrol.exceptions.http.TransactionNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpErrorWrapperDtoResponse> handleInternalServerError(HttpServletRequest req, Exception ex) {
        log.error("Unexpected error occurred on request: " + req.getServletPath() + " Method: " + req.getMethod(), ex);
        HttpErrorWrapperDtoResponse errorDtoResponse = new HttpErrorWrapperDtoResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error"
        );

        return new ResponseEntity<>(errorDtoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpErrorWrapperDtoResponse> handleBadRequest(HttpServletRequest req, BadRequestException ex) {
        log.error("Unexpected error occurred on request: " + req.getServletPath() + " Method: " + req.getMethod(), ex);
        HttpErrorWrapperDtoResponse errorDtoResponse = new HttpErrorWrapperDtoResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorDtoResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<HttpErrorWrapperDtoResponse> handleNotFound(HttpServletRequest req, TransactionNotFoundException ex) {
        log.error("Unexpected error occurred on request: " + req.getServletPath() + " Method: " + req.getMethod(), ex);
        HttpErrorWrapperDtoResponse errorDtoResponse = new HttpErrorWrapperDtoResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorDtoResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpErrorWrapperDtoResponse> handleNotFound(HttpServletRequest req, HttpMessageNotReadableException ex) {
        log.error("Unexpected error occurred on request: " + req.getServletPath() + " Method: " + req.getMethod(), ex);
        HttpErrorWrapperDtoResponse errorDtoResponse = new HttpErrorWrapperDtoResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorDtoResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorWrapperDtoResponse> handleValidationExceptions(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error("Unexpected error occurred on request: " + req.getServletPath() + " Method: " + req.getMethod(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<String> errorsMsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + " " + error.getDefaultMessage())
                    .toList();

            HttpErrorWrapperDtoResponse errorDtoResponse = new HttpErrorWrapperDtoResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    errorsMsg.toString()
            );
            return new ResponseEntity<>(errorDtoResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
