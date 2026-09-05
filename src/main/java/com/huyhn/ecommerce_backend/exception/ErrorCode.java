package com.huyhn.ecommerce_backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    VALIDATION_ERROR("VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation error"),
    INVALID_PARAMETER("INVALID_PARAMETER", HttpStatus.BAD_REQUEST, "Invalid parameter"),
    DATA_INTEGRITY_VIOLATION("DATA_INTEGRITY_VIOLATION", HttpStatus.CONFLICT, "Data integrity violation"),
    ACCESS_DENIED("ACCESS_DENIED", HttpStatus.FORBIDDEN, "Access denied"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR", HttpStatus.UNAUTHORIZED, "Authentication error"),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", HttpStatus.NOT_FOUND, "Entity {{entity}} not found by id {{id}}");

    private final String message;
    private final HttpStatus status;
    private final String code;

    ErrorCode(String code, HttpStatus status, String message) {
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
