package com.example.modu.service;

import com.example.modu.dto.TestElement.TestMakeRequestDto;
import com.example.modu.dto.TestElement.TestsResponseDto;
import com.example.modu.entity.User;
import com.example.modu.repository.TesterRepository;
import com.example.modu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j(topic = "Tester Service")
@RequiredArgsConstructor
public class TesterService {
    private final TesterRepository testerRepository;
    private final UserRepository userRepository;

    public TestsResponseDto createTester(TestMakeRequestDto requestDto)
    {
        return null;
    }
    public List<TestsResponseDto> getAllTests()
    {
        return null;
    }
    public User getCurrentUser()
    {
        return null;
    }
}
