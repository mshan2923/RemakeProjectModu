package com.example.modu.controller;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.result.ParticipateRequestDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.entity.User;
import com.example.modu.service.TestParticipateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j(topic = "Test Participate Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestParticipateController {

    private final TestParticipateService testParticipateService;

    //테스트 시작(진행)// 질문들과 보기 , 질문 갯수 반환
    @GetMapping("/participate/{testId}")
    public ResponseEntity<?> getQuestions(@PathVariable Long testId)
    {
        return testParticipateService.getQuestions(testId);
    }

    //테스트 결과 제출 - ParticipateRequestDto에 testID, userID 빼고 => @AuthenticationPrincipal User user 으로
    @PostMapping("/participate/{testId}")
    public ResultResponseDto participate(@PathVariable Long testId,
                                         @AuthenticationPrincipal User user,
                                         @RequestBody ParticipateRequestDto dto) {

        return testParticipateService.participateTest(testId, new ParticipateRequestDto(new ArrayList<>()), user);
    }// 유저 없을때 따로 처리 - 저장 X , 결과만
}
