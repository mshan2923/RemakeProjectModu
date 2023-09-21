package com.example.modu.service;

import com.example.modu.dto.TestElement.ChoiceDto;
import com.example.modu.dto.TestElement.QuestionDto;
import com.example.modu.dto.TestElement.TestMakeRequestDto;
import com.example.modu.dto.TestElement.TestsResponseDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.entity.TestElement.Choice;
import com.example.modu.entity.TestElement.Question;
import com.example.modu.entity.TestElement.Tester;
import com.example.modu.entity.User;
import com.example.modu.repository.TesterRepository;
import com.example.modu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "Tester Service")
@RequiredArgsConstructor
public class TesterService {
    private final TesterRepository testerRepository;
    private final UserRepository userRepository;

    public ResponseEntity<StatusResponseDto> createTester(TestMakeRequestDto requestDto)
    {
        {
            //인증 절차 / 지금은 첫 유저를 값을 넣음

        }

        User currentUser = getCurrentUser();// ======== 지금은 첫 유저를 값을 넣음
        if(currentUser==null){
            throw new IllegalStateException("로그인한 사용자만 테스트를 작성할 수 있습니다.");
        }

        Tester tester = new Tester(requestDto);
        tester.setUser(currentUser);
        currentUser.addTest(tester);


        for(QuestionDto questionDto : requestDto.getQuestions()){
            Question question = new Question(questionDto);
            question.setTester(tester);
            tester.getQuestions().add(question);

            for(ChoiceDto choiceDto : questionDto.getChoices()){
                Choice choice = new Choice(choiceDto);
                choice.setQuestion(question);
                question.getChoices().add(choice);
            }
        }

        testerRepository.save(tester);

        return ResponseEntity.ok(new StatusResponseDto("생성 성공", 200));
    }
    public ResponseEntity<List<TestsResponseDto>> getAllTests()
    {
        List<Tester> testers = testerRepository.findAll();
        return ResponseEntity.ok(testers.stream()
                .map(TestsResponseDto::new)
                .collect(Collectors.toList()));
    }
    public User getCurrentUser()
    {
        if (userRepository.count() <= 0)
            throw new NoSuchElementException("유저가 없어요!");

        return userRepository.findAll().stream().findFirst().get();
    }
}
