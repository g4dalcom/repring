package com.project.repring.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(new ObjectMapper().writeValueAsString("로그인이 필요합니다."));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);    // 유효하지 않은 자격증명으로 접근하면 401 에러

    }
}
