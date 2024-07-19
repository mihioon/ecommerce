package com.hhplus.ecommerce.support;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // 주소로 필터링 : 인증이 필요한 페이지를 주소로 구분
        http
//                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
//                .httpBasic(httpBasic -> httpBasic.disable())  // HTTP Basic 인증 비활성화
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 사용하지 않음

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        "/public/**",
                                        "/login",
                                        "/swagger-resources/**",
                                        "/swagger-ui/**",
                                        "/v2/api-docs",
                                        "/api-docs/**"
                                ).permitAll()  // 공개 경로 허용
                                .anyRequest().authenticated()  // 나머지 모든 요청은 인증 요구
                )
                .formLogin(formLogin ->
                        formLogin
//                            .loginPage("/login") // 커스텀 로그인 페이지(미설정시 기본 폼)
                            .permitAll() // 로그인 페이지 접근 허용
                )
        ; // http 세팅 끝

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignore(){
        // 예외 경로 설정 : 보안 필터 자체에서
        return web -> web.ignoring().requestMatchers("/static/**");
    }
}
