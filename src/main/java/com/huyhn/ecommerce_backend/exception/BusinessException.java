package com.huyhn.ecommerce_backend.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Map<String, Object> args;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, Map.of());
    }

    public BusinessException(ErrorCode errorCode, Map<String, ?> args) {
        super(ErrorMessageFormatter.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
        this.args = Map.copyOf(args);
    }
}
