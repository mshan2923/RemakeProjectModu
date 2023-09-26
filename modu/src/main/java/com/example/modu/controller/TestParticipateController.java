package com.example.modu.controller;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.result.ParticipateRequestDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.dto.result.TestStartResponseDto;
import com.example.modu.service.TestParticipateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestParticipateController {

    private final TestParticipateService testParticipateService;

    // 테스트 완료
    @PostMapping("/participate/{testId}")
    public ResultResponseDto participate(@PathVariable Long testId,
                                         @RequestBody ParticipateRequestDto dto) {
        return testParticipateService.participateTest(testId, dto);
    }

//    // 테스트 참여
//    @GetMapping("/participate/{testId}")
//    public TestStartResponseDto testStart(@PathVariable Long testId){
//        return testParticipateService.testStart(testId);
//    }
}
