package com.example.modu.controller;

import com.example.modu.dto.TestElement.TestDetailResponseDto;
import com.example.modu.dto.TestElement.TestMakeRequestDto;
import com.example.modu.dto.TestElement.TestsResponseDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.entity.User;
import com.example.modu.service.TesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TesterController {

    private final TesterService testerService;
    /*
    // 테스트 만들기 폼 페이지
    @GetMapping("/test/testMakeForm")
    public String testMakeForm(){
        return "testMakeForm";
    }
    */

    // 테스트 만들기
    @PostMapping("/test/testMakeForm")
    public ResponseEntity<StatusResponseDto> createTester(@RequestBody TestMakeRequestDto requestDto){
        return testerService.createTester(requestDto);
    }

    // 테스트 조회
    @GetMapping("/tests")
    public List<TestsResponseDto> getAllTests(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return testerService.getAllTests(page, size);
    }

    // 테스트 상세 조회
    @GetMapping("/test/{testId}")
    public TestDetailResponseDto getTestById(@PathVariable Long testId) {
        return testerService.getTestById(testId);
    }

    // 테스트 삭제
    @DeleteMapping("/test/{testId}")
    public ResponseEntity<StatusResponseDto> deleteTester(@PathVariable Long testId){
        try{
            testerService.deleteTester(testId);
            return ResponseEntity.ok(new StatusResponseDto("테스트가 성공적으로 삭제됨.", 200));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new StatusResponseDto(e.getMessage(), 403));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StatusResponseDto("테스트 삭제 중 오류 발생.", 500));
        }
    }


    @PostMapping("/test/like")
    public ResponseEntity<StatusResponseDto> likeTester(@AuthenticationPrincipal User user)
    {
        return testerService.likeTest(user);
    }
}
