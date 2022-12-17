package com.project.repring.dto;

import com.project.repring.domain.Post;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public PostRequestDto() {
    }

    @Builder
    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}
