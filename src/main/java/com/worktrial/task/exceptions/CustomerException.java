package com.worktrial.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomerException extends BaseException {
    public enum CustomerError {
        NOT_FOUND(HttpStatus.NOT_FOUND, "Customer not found!"),
        EMPTY_CUSTOMER_LIST(HttpStatus.NOT_FOUND, "Customer list is empty!");

        private final HttpStatus httpStatus;
        private final String message;

        CustomerError(HttpStatus httpStatus, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
        }
    }

    public CustomerException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public CustomerException(CustomerError customerError) {
        super(customerError.httpStatus, customerError.message);
    }
}
