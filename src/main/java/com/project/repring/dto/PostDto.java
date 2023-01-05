package com.project.repring.dto;

import com.project.repring.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String title;
        private String content;

        @Builder
        public Request(final String title, final String content) {
            this.title = title;
            this.content = content;
        }

        // DB에 저장하기 위한 DTO -> Entity 변환 메서드
        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String title;
        private String content;

        @Builder
        public Response(final Long id, final String title, final String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        // DB 조회를 위한 Entity -> DTO 변환 메서드
        public static PostDto.Response of(Post post) {
            return Response.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();
        }
    }
}
