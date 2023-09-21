package com.example.modu.service;

import com.example.modu.dto.user.LoginRequestDto;
import com.example.modu.dto.user.SignupRequestDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.entity.User;
import com.example.modu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
@Slf4j(topic = "User Service")
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<StatusResponseDto> signup(SignupRequestDto requestDto)
    {
        Optional<User> checkUsername = userRepository.findByUsername(requestDto.getUsername());
        if (checkUsername.isPresent())
            throw new IllegalArgumentException("중복된 사용자가 존재 합니다.");

        String pattern = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
        if (!Pattern.matches(pattern, requestDto.getPassword()))
        {
            throw  new PatternSyntaxException("비밀 번호 조건에 부합 되지 않음", pattern, -1);
        }
        
        String cryptPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), cryptPassword, requestDto.getEmail());
        
        userRepository.save(user);
        
        return ResponseEntity.ok(new StatusResponseDto("회원가입 성공" , 200));
    }

    public  ResponseEntity<StatusResponseDto> login(LoginRequestDto requestDto)
    {
        Optional<User> target = userRepository.findByUsername(requestDto.getUsername());
        if (target.isEmpty())
            throw new IllegalArgumentException("사용자가 존재 하지 않습니다.");

        if (! passwordEncoder.matches(requestDto.getPassword(), target.get().getPassword()))
        {
            throw new IllegalArgumentException("틀린 비밀번호");
        }

        {
           // 인증 쿠키 발급
        }

        return ResponseEntity.ok(new StatusResponseDto("로그인 성공", 200));
    }

}
