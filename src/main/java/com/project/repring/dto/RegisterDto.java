package com.project.repring.dto;

import com.project.repring.domain.Member;
import com.project.repring.domain.MemberRoleEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String username;
        private String nickname;
        private String password;
        private String passwordConfirm;

        @Builder
        public Request(final String username, final String nickname, final String password, final String passwordConfirm) {
            this.username = username;
            this.nickname = nickname;
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .username(username)
                    .nickname(nickname)
                    .password(passwordEncoder.encode(password))
                    .role(MemberRoleEnum.MEMBER)
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String username;
        private String nickname;
        private MemberRoleEnum role;

        public Response(RegisterDto.Request registerDto, Member member) {
            username = registerDto.getUsername();
            nickname = registerDto.getNickname();
            role = member.getRole();
        }

        @Builder
        public Response(final String username, final String nickname, final MemberRoleEnum role) {
            this.username = username;
            this.nickname = nickname;
            this.role = role;
        }

        public static MemberDto.Response of(Member member) {
            return MemberDto.Response.builder()
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .build();
        }
    }
}
