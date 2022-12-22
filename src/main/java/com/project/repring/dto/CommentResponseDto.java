package com.project.repring.dto;

import com.project.repring.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String comment;

    private final Long postId;

    @Builder
    public CommentResponseDto(final Long id, final String comment, final Long postId) {
        this.id = id;
        this.comment = comment;
        this.postId = postId;
    }

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .comment(comment.getComment())
                .postId(comment.getPost().getId())
                .build();
    }
}
