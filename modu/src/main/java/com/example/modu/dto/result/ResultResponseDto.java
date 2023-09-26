package com.example.modu.dto.result;

import com.example.modu.entity.TestElement.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultResponseDto {
    private int score;
    private int maxScore;
    private String nickname;

    public ResultResponseDto(int score, int maxScore, String nickname) {
        this.score = score;
        this.maxScore = maxScore;
        this.nickname = nickname;
    }
}
