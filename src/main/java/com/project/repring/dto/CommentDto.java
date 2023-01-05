package com.project.repring.dto;

import com.project.repring.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String comment;

        @Builder
        public Request(final String comment) {
            this.comment = comment;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long commentId;
        private String comment;
        private Long postId;

        @Builder
        public Response(final Long commentId, final String comment, final Long postId) {
            this.commentId = commentId;
            this.comment = comment;
            this.postId = postId;
        }

        // DB 조회를 위한 Entity -> DTO 변환 메서드
        public CommentDto.Response of(Comment comment) {
            return Response.builder()
                    .comment(comment.getComment())
                    .postId(comment.getPost().getId())
                    .build();
        }
    }
}
