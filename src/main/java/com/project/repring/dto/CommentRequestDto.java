package com.project.repring.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentRequestDto {
    @NotBlank
    private String comment;

    public CommentRequestDto() {
    }

    public CommentRequestDto(final String comment) {
        this.comment = comment;
    }
}
