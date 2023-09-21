package com.example.modu.dto.TestElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChoiceDto {
    private String content;
    private String image;
    private boolean isCorrect;
}
