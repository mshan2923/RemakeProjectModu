package com.example.modu.controller;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.result.ParticipateRequestDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.entity.User;
import com.example.modu.service.TestParticipateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestParticipateController {

    private final TestParticipateService testParticipateService;

    //테스트 참가 관련

    //테스트 결과 제출 - ParticipateRequestDto에 testID, userID 빼고 => @AuthenticationPrincipal User user 으로
    @PostMapping("/participate/{testId}")
    public ResultResponseDto participate(@PathVariable Long testId,
                                         @RequestBody ParticipateRequestDto dto,
                                         @AuthenticationPrincipal User user) {
        return testParticipateService.participateTest(testId, dto, user);
    }
}
