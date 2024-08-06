package com.hhplus.ecommerce.domain.user;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.hhplus.ecommerce.support.exception.ErrorCode.USER_IS_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class UserFinder {
    private final UserRepository userRepo;

    public User find(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new InputValidationException(USER_IS_NOT_FOUND.getMessage()));
    }
}
