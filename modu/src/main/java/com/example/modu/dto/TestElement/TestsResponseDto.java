package com.example.modu.dto.TestElement;

import com.example.modu.dto.comment.CommentResponseDto;
import com.example.modu.entity.TestElement.Tester;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TestsResponseDto {
    private String username;
    private String title;
    private String image;
    private int views;
    private int likes;
    private int participates;
    private String category;
    private List<CommentResponseDto> comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TestsResponseDto(Tester tester) {
        this.username = tester.getUser().getUsername();
        this.title = tester.getTitle();
        this.image = tester.getImage();
        this.views = tester.getViews();
        this.likes = tester.getLikes();
        this.participates = tester.getParticipates();
        this.category = tester.getCategory();
        this.comments = tester.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.createdAt = tester.getCreatedAt();
        this.modifiedAt = tester.getModifiedAt();
    }

}
