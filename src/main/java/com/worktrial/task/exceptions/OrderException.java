package com.worktrial.task.exceptions;

import org.springframework.http.HttpStatus;

public class OrderException extends BaseException {
    public enum OrderError {
        NOT_FOUND(HttpStatus.NOT_FOUND, "Order not found!"),
        EMPTY_ORDER_LIST(HttpStatus.NOT_FOUND, "Order list is empty!");

        OrderError(HttpStatus httpStatus, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
        }

        private final HttpStatus httpStatus;
        private final String message;
    }
    public OrderException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public OrderException(OrderError orderError) {
        super(orderError.httpStatus, orderError.message);
    }
}
