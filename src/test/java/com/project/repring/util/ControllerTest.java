package com.project.repring.util;

import com.project.repring.controller.MemberController;
import com.project.repring.controller.PostController;
import com.project.repring.service.PostService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@WebMvcTest({
        PostController.class,
        MemberController.class
})
@ExtendWith(RestDocumentationExtension.class)
public class ControllerTest {

    protected MockMvcRequestSpecification restDocs;

    @MockBean
    PostService postService;

    /**
     * @param webApplicationContext @AutoConfigureMockMvc 로 구현 가능
     * @param restDocumentation @AutoConfigureRestDocs 로 구현 가능
     */
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        restDocs = RestAssuredMockMvc.given()
                .mockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .apply(documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint()))
                        .build())
                .log().all();
    }
}