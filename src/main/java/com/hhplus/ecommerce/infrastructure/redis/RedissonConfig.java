package com.hhplus.ecommerce.infrastructure.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/*
 * RedissonClient Configuration
 */
@Configuration
public class RedissonConfig { // Redisson 클라이언트 인스턴스를 생성하고 구성
    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    private static final String REDISSON_HOST_PREFIX = "redis://";

    @Bean
    public RedissonClient redissonClient() { // Redisson 라이브러리를 사용하여 Redis 서버와 통신하는 주요 객체
        Config config = new Config();
        config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + redisHost + ":" + redisPort) // 주소설정
                .setRetryInterval(1500)  // 연결 실패 시 재시도 간격 : 기본 값 1.5초
                .setRetryAttempts(10);    // 연결 실패 시 재시도 횟수 : 기본값 10
        return Redisson.create(config);
    }
}
