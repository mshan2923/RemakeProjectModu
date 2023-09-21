package com.example.modu.dto.comment;

import com.example.modu.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String username;
    private String content;

    public CommentResponseDto(Comment comment){
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
    }
}
