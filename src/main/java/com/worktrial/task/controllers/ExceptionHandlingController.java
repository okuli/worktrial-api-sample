package com.worktrial.task.controllers;

import com.worktrial.task.dtos.ErrorResponseDto;
import com.worktrial.task.exceptions.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlingController implements ErrorController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponseDto> handleBaseException(BaseException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(exception);
    }

    private ResponseEntity<ErrorResponseDto> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new ErrorResponseDto(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

    private ResponseEntity<ErrorResponseDto> createHttpResponse(BaseException baseException) {
        return new ResponseEntity<>(new ErrorResponseDto(baseException.getHttpStatus().value(), baseException.getHttpStatus(),
                baseException.getHttpStatus().getReasonPhrase().toUpperCase(), baseException.getMessage()), baseException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleNotValidMethodArgument(MethodArgumentNotValidException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralError(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }
}
