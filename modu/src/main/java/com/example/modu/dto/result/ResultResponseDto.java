package com.example.modu.dto.result;

import com.example.modu.entity.TestElement.Result;
import com.example.modu.entity.TestElement.UserTestResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultResponseDto {
    private int score;
    private int maxScore;
    private Long userId;
    private Long testerId;

    public ResultResponseDto(UserTestResult userTestResult) {
        this.score = userTestResult.getScore();
        this.maxScore = userTestResult.getMaxScore();
        this.userId = userTestResult.getUser().getId();
        this.testerId = userTestResult.getTester().getId();
    }
}
