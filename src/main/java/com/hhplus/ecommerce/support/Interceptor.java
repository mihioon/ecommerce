package com.hhplus.ecommerce.support;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwt;

    // 경로 제외 : WebConfig에서 설정
    @Override
    public boolean preHandle( //// 요청 전처리
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        // 토큰 검증 - bearer
        String token = jwt.resolveToken(request);

        if (token != null && jwt.validateToken(token)) { // 토큰 검증
            Authentication authentication = jwt.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 저장
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false; // 요청 차단
        }
        return true; // true : 요청 진행, false : 요청 차단
    }

    @Override
    public void postHandle( //응답 후처리 (응답을 조작하거나 추가 작업)
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        //log.info("");
    }

    @Override
    public void afterCompletion( // 요청/응답 처리가 완료된 후 작업
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
