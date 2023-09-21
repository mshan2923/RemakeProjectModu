package com.example.modu.service;

import com.example.modu.dto.TestElement.*;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.entity.TestElement.Choice;
import com.example.modu.entity.TestElement.Question;
import com.example.modu.entity.TestElement.Tester;
import com.example.modu.entity.User;
import com.example.modu.repository.TesterRepository;
import com.example.modu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    // 테스트 조회
    public List<TestsResponseDto> getAllTests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tester> testers = testerRepository.findAll(pageable);
        return testers.stream().map(TestsResponseDto::new).collect(Collectors.toList());
    }

    // 테스트 상세 조회
    public TestDetailResponseDto getTestById(Long testId) {
        Tester tester = findTesterById(testId);
        return new TestDetailResponseDto(tester);
    }

    // 테스트 삭제
    public void deleteTester(Long testId) {
        User currentUser = getCurrentUser();
        Tester tester = findTesterById(testId);

        validateUserAuthority(tester, currentUser);

        testerRepository.delete(tester);
    }

    // 현재 로그인한 회원 정보 가져오기
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) { //---------------
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String currentUsername = userDetails.getUsername();
            return userRepository.findByUsername(currentUsername).orElseThrow(
                    () -> new IllegalArgumentException("인증된 사용자를 찾을 수 없습니다.")
            );
        } else {
            throw new IllegalStateException("올바른 인증 정보가 아닙니다.");
        }
    }

    // 본인이 작성한 테스트인지 확인
    private void validateUserAuthority(Tester tester, User currentUser) {
        if (!tester.getUser().equals(currentUser)) {
            throw new IllegalArgumentException("본인의 테스트만 수정/삭제 할 수 있습니다.");
        }
    }

    // testId로 해당 test 가져오기
    private Tester findTesterById(Long testId) {
        return testerRepository.findById(testId).orElseThrow(
                () -> new IllegalArgumentException("해당 테스트를 찾을 수 없습니다.")
        );
    }
}
