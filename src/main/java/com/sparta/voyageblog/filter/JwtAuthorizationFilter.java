package com.sparta.voyageblog.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.voyageblog.dto.ApiResponseDto;
import com.sparta.voyageblog.jwt.JwtUtil;
import com.sparta.voyageblog.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getJwtFromHeader(req);

        if (token != null) {
            if (!jwtUtil.validateToken(token)) {
                ApiResponseDto responseDto = new ApiResponseDto("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(responseDto));
                return;
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(req, response);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);
        // username -> user 조회 -> userDetails 에 담고 -> authentication 의 principal 에 담고
        // -> securityContent 에 담고 -> SecurityContextHolder 에 담고
        // -> 이제 @AuthenticationPrincipal 로 조회할 수 있음
        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}