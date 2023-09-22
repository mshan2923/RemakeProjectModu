package com.example.modu.controller;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.result.ParticipateRequestDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.service.TestParticipateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Deprecated//미사용 표시
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestParticipateController {

    private final TestParticipateService testParticipateService;


    @PostMapping("/participate/{testId}")
    public ResultResponseDto participate(@PathVariable Long testId, @RequestBody ParticipateRequestDto userChoices) {
        return testParticipateService.participateTest(testId, userChoices);
    }
}
