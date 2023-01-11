package com.project.repring.controller;

import com.project.repring.domain.Member;
import com.project.repring.dto.MemberDto;
import com.project.repring.dto.RegisterDto;
import com.project.repring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(Member.builder()
                .id(id)
                .build());
    }

    @PostMapping("/api/members/register")
    public ResponseEntity<MemberDto.Response> register(@RequestBody RegisterDto.Request request) {
        return ResponseEntity.created(URI.create("/")).body(memberService.register(request));
    }
}
