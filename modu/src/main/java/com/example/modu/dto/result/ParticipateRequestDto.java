package com.example.modu.dto.result;

import com.example.modu.dto.TestElement.ChoiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ParticipateRequestDto {
    private List<ChoiceDto> userChoices;
}