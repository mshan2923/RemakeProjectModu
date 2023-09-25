package com.example.modu.controller;

import com.example.modu.dto.TestElement.TestsResponseDto;
import com.example.modu.dto.user.LoginRequestDto;
import com.example.modu.dto.user.SignupRequestDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.dto.user.UserUpdateRequestDto;
import com.example.modu.entity.User;
import com.example.modu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "User Controller")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    //private final JwtUtil jwtUtil;
    //private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/loginForm")
    private ResponseEntity<String> loginPage()
    {
        //new SecurityContextLoginModule().
        //----       + 인증 인가
        return ResponseEntity.ok("로그인!");//---> 실패해야 뜸
        //============================================= 로그인 실패해도 뜸
    }// @RestController , @RestControllerAdvice 둘중 하나 있으면 문자열로 리턴
    /*
    @GetMapping("/signupForm")
    private String signupPage()
    {
        return "signupForm";
    }*/
    @PostMapping("/signup")
    private ResponseEntity<StatusResponseDto> signup(@RequestBody SignupRequestDto signup) {
        return userService.signup(signup);
    }
    @GetMapping("/logout")
    private ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response)
    {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("Logout");
    }
    @PutMapping("/update")
    private ResponseEntity<StatusResponseDto> update(@AuthenticationPrincipal User user,
                                                     @RequestBody UserUpdateRequestDto update)
    {
        return userService.update(user, update);
    }
    @DeleteMapping("/delete")
    private ResponseEntity<StatusResponseDto> deleteUser(@AuthenticationPrincipal User user)
    {
        return userService.deleteUser(user);//============ 아직 구현 안함!
    }

    @PostMapping("/login")
    private ResponseEntity<StatusResponseDto> login(@RequestBody LoginRequestDto login) {
        return userService.login(login);
    }

    //======= 전부 한줄로 Service 에서 전부 처리 + if문 도

    @GetMapping("/mypage")
    private String myPage(@AuthenticationPrincipal User user)
    {
        return userService.myPage(user);
    }
    @GetMapping("/tests")
    private ResponseEntity<List<TestsResponseDto>> makedTests(@AuthenticationPrincipal User user)
    {
        return userService.makedTests(user);
    }
}
