package com.project.repring.controller;

import com.project.repring.dto.CommentRequestDto;
import com.project.repring.repository.CommentRepository;
import com.project.repring.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ExtendWith(RestDocumentationExtension.class)
class CommentControllerTest extends ControllerTest {

    @MockBean
    CommentRepository commentRepository;

    @DisplayName("코멘트 등록 API")
    @Test
    void createComment() {

        CommentRequestDto commentRequestDto = new CommentRequestDto("코멘트 테스트");

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(commentRequestDto)
                .when().post("/api/1/comments")
                .then().log().all()
                .apply(document("comment/create/success"))
                .statusCode(HttpStatus.CREATED.value());
    }


    @DisplayName("코멘트 조회 API")
    @Test
    void findAllComments() {
        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("api/1/comments")
                .then().log().all()
                .apply(document("comment/find/success"))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("코멘트 수정 API")
    @Test
    void updateComment() {

        CommentRequestDto commentRequestDto = new CommentRequestDto("코멘트 수정 테스트");

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(commentRequestDto)
                .when().put("/api/1/comments/1")
                .then().log().all()
                .apply(document("comment/update/success"))
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("코멘트 삭제 API")
    @Test
    void deleteComment() {
        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/1/comments/1")
                .then().log().all()
                .apply(document("comment/delete/success"))
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}