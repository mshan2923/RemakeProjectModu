package com.example.modu.dto.TestElement;

import com.example.modu.dto.comment.CommentResponseDto;
import com.example.modu.dto.result.QuestionResponseDto;
import com.example.modu.entity.TestElement.Question;
import com.example.modu.entity.TestElement.Tester;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestDetailResponseDto extends com.example.modu.dto.TestElement.TestsResponseDto {
    private String content;
    private List<CommentResponseDto> comments;
    private List<QuestionResponseDto> questions;

    public TestDetailResponseDto(Tester tester) {
        super(tester);
        this.content = tester.getContent();
        this.comments = tester.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        questions = tester.getQuestions().stream().map(QuestionResponseDto::new).toList();
    }
}
