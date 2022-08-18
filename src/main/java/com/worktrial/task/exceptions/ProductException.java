package com.worktrial.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductException extends BaseException {
    public enum ProductError {
        NOT_FOUND(HttpStatus.NOT_FOUND, "Customer not found"),
        EMPTY_PRODUCT_LIST(HttpStatus.NOT_FOUND, "Product list is empty!");

        private final HttpStatus httpStatus;
        private final String message;

        ProductError(HttpStatus httpStatus, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
        }
    }

    public ProductException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public ProductException(ProductError productError) {
        super(productError.httpStatus, productError.message);
    }
}
