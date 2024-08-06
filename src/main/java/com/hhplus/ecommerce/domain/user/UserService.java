package com.hhplus.ecommerce.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final UserFinder userFinder;

    // 사용자 조회
    public User findUser(Long customerId) {
        return userFinder.find(customerId);
    }

    // 사용자 추가
    public User save(User user) {
        return userRepo.save(user);
    }
}
