package com.project.repring.service;

import com.project.repring.domain.Member;
import com.project.repring.domain.RefreshToken;
import com.project.repring.dto.LoginDto;
import com.project.repring.dto.MemberDto;
import com.project.repring.dto.RegisterDto;
import com.project.repring.dto.TokenDto;
import com.project.repring.exception.CustomException;
import com.project.repring.exception.ErrorCode;
import com.project.repring.jwt.TokenProvider;
import com.project.repring.repository.MemberRepository;
import com.project.repring.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository tokenRepository;


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

    public MemberDto.LoginResponse login(LoginDto request) {
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);

        TokenDto token = tokenProvider.generateToken(request.getUsername());
        log.info("token = {}", token);

        RefreshToken refreshToken = RefreshToken.builder()
                        .refreshToken(token.getRefreshToken())
                        .member(member)
                        .build();
        tokenRepository.save(refreshToken);

        return MemberDto.LoginResponse.of(member, token.getAccessToken(), token.getRefreshToken());
    }

}
