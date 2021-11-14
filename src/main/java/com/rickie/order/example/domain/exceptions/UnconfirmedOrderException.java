package com.rickie.order.example.domain.exceptions;

public class UnconfirmedOrderException extends IllegalStateException {
    public UnconfirmedOrderException() {
        super(("Cannot ship an order which has not been confirmed yet."));
    }
}
