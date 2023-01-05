package com.project.repring.controller;

import com.project.repring.dto.PostDto;
import com.project.repring.repository.PostRepository;
import com.project.repring.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ExtendWith(RestDocumentationExtension.class)
public class PostControllerTest extends ControllerTest {

    private static final PostDto.Request NEW_POST_REQUEST = new PostDto.Request("타이틀", "본문내용");

    @MockBean
    PostRepository postRepository;

    @DisplayName("게시글 등록 API")
    @Test
    void createPost() {
        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(NEW_POST_REQUEST)
                .when().post("/api/posts")
                .then().log().all()
                .apply(document("post/create/success"))
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("전체 게시글 불러오기 API")
    @Test
    void findAllPosts() {
        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/posts")
                .then().log().all()
                .apply(document("post/find/all/success"))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("게시글 불러오기 API")
    @Test
    void findPost() {
        PostDto.Response postResponseDto = PostDto.Response.builder()
                        .id(1L)
                        .title("테스트 제목")
                        .content("테스트 본문")
                        .build();
        doReturn(postResponseDto)
                .when(postService)
                .findPost(anyLong());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/posts/1")
                .then().log().all()
                .apply(document("post/find/one/success"))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("게시글 수정 API")
    @Test
    void updatePost() {
        PostDto.Response postResponseDto = PostDto.Response.builder()
                        .id(1L)
                        .title("수정 제목")
                        .content("수정 본문")
                        .build();
        doReturn(postResponseDto)
                .when(postService)
                .updatePost(anyLong(), any());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(postResponseDto)
                .when().put("/api/posts/1")
                .then().log().all()
                .apply(document("post/update/success"))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("게시글 삭제 API")
    @Test
    void deletePost() {
        doNothing().when(postService)
                .deletePost(anyLong());

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/posts/1")
                .then().log().all()
                .apply(document("post/delete/success"))
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}