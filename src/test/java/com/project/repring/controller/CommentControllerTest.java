package com.project.repring.controller;

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
        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("코멘트 테스트")
                .when().post("/api/1/comments")
                .then().log().all()
                .apply(document("comment/create/success"))
                .statusCode(HttpStatus.CREATED.value());
    }
}