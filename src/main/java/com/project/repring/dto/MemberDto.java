package com.project.repring.dto;

import com.project.repring.domain.Member;
import com.project.repring.domain.MemberRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long member_id;
        private String username;
        private String nickname;
        private MemberRoleEnum role;

        public static Response of(Member member) {
            return Response.builder()
                    .member_id(member.getId())
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .build();
        }
    }
}
