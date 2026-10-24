package com.CPTM.stock.exception;

public record DefaultErrorMessage(
        int status,
        String message
) {
}
