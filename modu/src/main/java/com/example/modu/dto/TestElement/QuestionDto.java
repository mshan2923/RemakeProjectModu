package com.example.modu.dto.TestElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionDto {
    private String title;
    private String image;
    private List<ChoiceDto> choices;
}
