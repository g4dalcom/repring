package com.project.repring.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    protected Member() {
    }

    @Builder
    public Member(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
