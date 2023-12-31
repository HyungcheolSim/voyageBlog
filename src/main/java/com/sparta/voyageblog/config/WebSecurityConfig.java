package com.sparta.voyageblog.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.voyageblog.filter.ExceptionHandlerFilter;
import com.sparta.voyageblog.filter.JwtAuthenticationFilter;
import com.sparta.voyageblog.filter.JwtAuthorizationFilter;
import com.sparta.voyageblog.jwt.JwtUtil;
import com.sparta.voyageblog.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 지원을 가능하게 함 //실행되는 filter chain 을 확인할 수 있는 옵션 debug=true;
//@EnableMethodSecurity(securedEnabled = true) //@Secured 어노테이션 활성화
public class WebSecurityConfig {

  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationConfiguration authenticationConfiguration;
  private final ObjectMapper objectMapper;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }


  @Bean
  public ExceptionHandlerFilter exceptionHandlerFilter() {
    return new ExceptionHandlerFilter();
  }


  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
    filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
    return filter;
  }

  @Bean
  public JwtAuthorizationFilter jwtAuthorizationFilter() {
    return new JwtAuthorizationFilter(jwtUtil, userDetailsService, objectMapper);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // CSRF 설정
    http.csrf(AbstractHttpConfigurer::disable);

    // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
    http.sessionManagement((sessionManagement) ->
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

    http.authorizeHttpRequests((authorizeHttpRequests) ->
        authorizeHttpRequests
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .permitAll() // resources 접근 허용 설정
            .requestMatchers(HttpMethod.GET, "/api/posts/**")
            .permitAll() //posts 의 get 요청들 2개 빼고 인가받도록
            .requestMatchers("/api/auth/**").permitAll()
            // '/api/user/'로 시작하는 요청 모두 인증 후 접근해야함
            .anyRequest().authenticated() // 그 외 모든 요청 인증처리

    );
    //기존 form login 비활성화
    //http.formLogin((formLogin) ->formLogin.loginPage("/api/user/login-page").permitAll());
    http.formLogin(AbstractHttpConfigurer::disable);

    // 필터 관리
    // // 1. 에러필터 2. 아디비번필터
    http.addFilterBefore(exceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtAuthorizationFilter(),
            UsernamePasswordAuthenticationFilter.class) // 1. 에러필터 2. Author 필터 3. 아디비번필터
        .addFilterBefore(jwtAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class); // 1. 에러필터 2. Author 필터 3. Authen 필터 4. 아디비번필터

    return http.build();
  }
}