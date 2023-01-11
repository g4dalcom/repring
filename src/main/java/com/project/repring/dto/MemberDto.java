package com.project.repring.dto;

import com.project.repring.domain.Member;
import com.project.repring.domain.MemberRoleEnum;
import lombok.*;

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginResponse {
        private Long member_id;
        private String username;
        private String nickname;
        private MemberRoleEnum role;
        private String accessToken;
        private String refreshToken;

        @Builder
        public LoginResponse(final Long member_id, final String username, final String nickname, final MemberRoleEnum role, final String accessToken, final String refreshToken) {
            this.member_id = member_id;
            this.username = username;
            this.nickname = nickname;
            this.role = role;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public static LoginResponse of(Member member, String accessToken, String refreshToken) {
            return LoginResponse.builder()
                    .member_id(member.getId())
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
}
