package com.hhplus.ecommerce.support;

import com.hhplus.ecommerce.support.exception.MalformedHeaderException;
import com.hhplus.ecommerce.support.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static com.hhplus.ecommerce.support.exception.ErrorCode.*;

@Slf4j
@Configuration
public class JwtTokenProvider {
//    @Value("${jwt.secret}")
//    private String secretKey; // 암호화 키

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //암호화해서 세팅

    @Value("${jwt.expiryMin}")
    private Long expiryMin; // 만료일 상수

     @Value("${jwt.refreshWeek}")
    private Long refreshWeek; // 만료일 상수

    // 토큰 생성
    public TokenInfo generateToken(
            Collection<? extends GrantedAuthority> authorityInfo,
            String id
    ) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiryMin * 60 * 1000);
        Date refreshDate = new Date(now.getTime() + refreshWeek * 1000 * 60 * 60 * 24 * 7);

        // 사용자 권한 정보
        String authorities = authorityInfo.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // AccessToken : 권한 확인(보안용, 인증정보 포함)
        String accessToken = Jwts.builder()
                .setSubject(id)
                .claim("auth", authorities)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();

        // RefreshToken : AccessToken 만료시 새로운 토큰을 발급하기 위한 토큰(로그인 확인)
        String refreshToken = Jwts.builder()
                .setExpiration(refreshDate)
                .signWith(key)
                .compact();

        return new TokenInfo(
                "Bearer",
                accessToken,
                refreshToken
               );
    }

    // Authentication 객체 생성
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken); // Claim 추출

        if (claims.get("auth") == null) { // 권한 정보 없는 토큰
            throw new TokenException(INVALID_TOKEN.getMessage());
        }

        // 권한 정보를 추출
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체 생성
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        // Authentication 객체 반환한다.
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰의 Payload에 저장된 Claim 추출
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenException(EXPIRED_TOKEN.getMessage());
        }
    }

    // 토큰 검증 메서드
    public boolean validateToken(String token) throws TokenException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new TokenException(INVALID_TOKEN.getMessage());
        } catch (ExpiredJwtException e) {
            throw new TokenException(EXPIRED_TOKEN.getMessage());
        }
    }

    // request에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.debug("bearertoken : {}", bearerToken);

        if (StringUtils.hasText(bearerToken)) {
            if (bearerToken.startsWith("Bearer") && bearerToken.length() > 7) {
                int tokenStartIndex = 7;
                return bearerToken.substring(tokenStartIndex);
            }
            throw new MalformedHeaderException(MALFORMED_HEADER.getMessage());
        }

        return bearerToken;
    }

}
