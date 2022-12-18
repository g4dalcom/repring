package com.project.repring.controller;

import com.project.repring.dto.PostRequestDto;
import com.project.repring.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ExtendWith(RestDocumentationExtension.class)
public class PostControllerTest extends ControllerTest {

    private static final PostRequestDto NEW_POST_REQUEST = new PostRequestDto("타이틀", "본문내용");

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

}