package com.huyhn.ecommerce_backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String code, String message, String details) {
    public ErrorResponse(String code, String message) {
        this(code, message, null);
    }
}
