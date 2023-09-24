package com.example.modu.controller;

import com.example.modu.dto.comment.CommentRequestDto;
import com.example.modu.dto.user.StatusResponseDto;
import com.example.modu.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("{testerId}/comment")
    public ResponseEntity<StatusResponseDto> createComment(@PathVariable Long testerId, @RequestBody CommentRequestDto requestDto){
        return commentService.createComment(testerId, requestDto);
    }


}
