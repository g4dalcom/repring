package com.project.repring.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    public final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
