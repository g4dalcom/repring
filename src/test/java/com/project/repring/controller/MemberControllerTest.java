package com.project.repring.controller;

import com.project.repring.domain.Member;
import com.project.repring.domain.MemberRoleEnum;
import com.project.repring.dto.RegisterDto;
import com.project.repring.repository.MemberRepository;
import com.project.repring.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
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

@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest extends ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("[테스트용] 사용자 검색")
    @Test
    public void findMember() throws Exception {
        Member member1 = Member.builder()
                .id(1L)
                .username("user1@email.com")
                .nickname("user1")
                .password("password")
                .role(MemberRoleEnum.MEMBER)
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
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("username").description("이메일"),
                                fieldWithPath("password").description("비밀번호"),
                                fieldWithPath("role").description("권한")
                            )
                ));
    }

    @DisplayName("회원가입을 하면 201 반환")
    @Test
    public void register() throws Exception {
        RegisterDto.Request request = new RegisterDto.Request("user1@user.com", "nickname1", "1234", "1234");

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/members/register")
                .then().log().all()
                .assertThat()
                .apply(document("member/register/success"))
                .statusCode(HttpStatus.CREATED.value());

    }
}