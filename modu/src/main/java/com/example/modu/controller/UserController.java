package com.example.modu.controller;

import com.example.modu.dto.user.LoginRequestDto;
import com.example.modu.dto.user.SignupRequestDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "User Controller")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    //private final JwtUtil jwtUtil;
    //private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/loginForm")
    private String loginPage()
    {
        return "login";
    }// @RestController , @RestControllerAdvice 둘중 하나 있으면 문자열로 리턴

    @GetMapping("/signupFrom")
    private String signupPage()
    {
        return "signupFrom";
    }
    @PostMapping("/signup")
    private ResponseEntity<StatusResponseDto> signup(@RequestBody SignupRequestDto signup) {
        return userService.signup(signup);
    }
    /*
    @PostMapping("/login")
    private ResponseEntity<StatusResponseDto> login(@RequestBody LoginRequestDto login) {
        return userService.login(login);
    }*/

    //======= 전부 한줄로 Service 에서 전부 처리 + if문 도
}
