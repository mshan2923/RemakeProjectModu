package com.example.modu.entity.TestElement;

import com.example.modu.dto.TestElement.ChoiceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "choice")
@NoArgsConstructor
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "isCorrect",nullable = false)
    private boolean isCorrect;

//    @Column(name = "score",nullable = false)
//    private int score;

    //질문 FK -> 질문 하나에 여러 선택지
    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    public Choice(ChoiceDto dto){
        this.content = dto.getContent();
        this.isCorrect = dto.isCorrect();
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
