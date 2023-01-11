package com.project.repring.service;

import com.project.repring.domain.Member;
import com.project.repring.dto.MemberDto;
import com.project.repring.dto.RegisterDto;
import com.project.repring.exception.CustomException;
import com.project.repring.exception.ErrorCode;
import com.project.repring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto.Response register(RegisterDto.Request register) {
        if (memberRepository.existsByUsername(register.getUsername())) {
            throw new CustomException(ErrorCode.EXIST_USERNAME);
        }

        if (!register.getPassword().equals(register.getPasswordConfirm())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        Member member = register.toMember(passwordEncoder);
        memberRepository.save(member);
        return MemberDto.Response.of(member);
    }
}
