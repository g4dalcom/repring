package com.project.repring.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String comment;

    public CommentRequestDto() {
    }

    public CommentRequestDto(final String comment) {
        this.comment = comment;
    }
}
