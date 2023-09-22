package com.example.modu.service;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.result.ParticipateRequestDto;
import com.example.modu.dto.result.ResultResponseDto;
import com.example.modu.entity.TestElement.Result;
import com.example.modu.entity.TestElement.Tester;
import com.example.modu.repository.ResultRepository;
import com.example.modu.repository.TesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestParticipateService {

    private final TesterRepository testerRepository;
    private final ResultRepository resultRepository;

    public ResultResponseDto participateTest(Long testId, ParticipateRequestDto userChoices){
        Tester tester = findTesterById(testId);

        int userScore = 0;
        for(ChoiceDto choiceDto : userChoices.getUserChoices()){
            if(choiceDto.isCorrect()){
                userScore ++;
            }
        }
        System.out.println(userScore);
        Result result = resultRepository.findByTesterAndScore(tester, userScore)
                .orElseThrow(() -> new IllegalArgumentException("해당 점수에 대한 결과를 찾을 수 없습니다."));

        return new ResultResponseDto(result);
    }

    // testId로 해당 test 가져오기
    private Tester findTesterById(Long testId) {
        return testerRepository.findById(testId).orElseThrow(
                () -> new IllegalArgumentException("해당 테스트를 찾을 수 없습니다.")
        );
    }
}
