package com.project.repring.controller;

import com.project.repring.domain.Member;
import com.project.repring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc // MockMvc 자동 설정
@AutoConfigureRestDocs // rest docs 자동 설정
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void getMember() throws Exception {
        Member member1 = Member.builder()
                .id(1L)
                .email("user1@email.com")
                .name("user1")
                .build();

        lenient().when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member1));

        mockMvc.perform(get("/api/members/{id}", member1.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-member",
                        pathParameters(
                                parameterWithName("id").description("Member ID")
                        ),
                        responseFields(
                                fieldWithPath("id").description("아이디"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("email").description("이메일")
                            )
                ));
    }
}