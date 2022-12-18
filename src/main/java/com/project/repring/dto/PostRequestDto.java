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

    /* DB에 등록할 때 쓰기 위한 DTO -> Entity 변환 메서드 */
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}
