package com.example.modu.service;

import com.example.modu.dto.user.SignupRequestDto;
import com.example.modu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "User Service")
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean signup(SignupRequestDto requestDto)
    {
        return false;
    }

}
