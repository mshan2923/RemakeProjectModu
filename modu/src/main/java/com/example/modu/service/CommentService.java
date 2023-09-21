package com.example.modu.service;

import com.example.modu.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "Comment Service")
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
}
