package com.project.repring.dto;

import com.project.repring.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;

    public CommentResponseDto() {
    }

    @Builder
    public CommentResponseDto(final Long id, final String comment) {
        this.id = id;
        this.comment = comment;
    }

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .comment(comment.getComment())
                .build();
    }
}
