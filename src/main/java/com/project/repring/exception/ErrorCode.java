package com.project.repring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /* 400 Bad Request */
    INVALID_POST_TITLE(HttpStatus.BAD_REQUEST, "400", "제목은 1자 이상 50자 이하여야 합니다."),
    INVALID_POST_CONTENT(HttpStatus.BAD_REQUEST, "400", "내용은 300자 미만이어야 합니다."),
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "400", "존재하지 않는 게시글입니다."),
    NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, "400", "존재하지 않는 코멘트입니다."),

    /* 500 Server Error */
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버가 원활하지 않습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
