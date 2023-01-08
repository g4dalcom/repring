package com.project.repring.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRoleEnum role;

    @Builder
    public Member(final Long id, final String email, final String nickname, final String password, final MemberRoleEnum role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
