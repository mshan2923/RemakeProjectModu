package com.example.modu.controller;

import com.example.modu.dto.TestElement.TestMakeRequestDto;
import com.example.modu.dto.TestElement.TestsResponseDto;
import com.example.modu.dto.user.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TesterController {

    //private final TesterService testerService;

    // 테스트 만들기 폼 페이지
    @GetMapping("/test/testMakeForm")
    public ModelAndView testMakeForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testMakeForm");
        return modelAndView;
    }


    // 테스트 만들기
    @PostMapping("/test/testMakeForm")
    public ResponseEntity<StatusResponseDto> createTester(@RequestBody TestMakeRequestDto requestDto){
            //return testerService.createTester(requestDto);
        return ResponseEntity.ok(null);
    }

    // 테스트 조회
    @GetMapping("/tests")
    public ResponseEntity<List<TestsResponseDto>> getAllTests() {
        //return ResponseEntity.ok(testerService.getAllTests());
        return ResponseEntity.ok(null);
    }
}
