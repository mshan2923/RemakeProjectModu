package com.example.modu.dto.TestElement;

import com.example.modu.dto.result.ResultRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class TestMakeRequestDto {
    private Long userId;
    private String title;
    private String content;
    private String image;
    private String category;
    private List<QuestionDto> questions;
    private List<ResultRequestDto> results;


}
