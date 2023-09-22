package com.example.modu.dto.result;

import com.example.modu.entity.TestElement.Result;

public class ResultResponseDto {
    private String image;
    private String content;
    private int score;

    public ResultResponseDto(Result result) {
        this.image = result.getImage();
        this.content = result.getContent();
        this.score = result.getScore();
    }
}
