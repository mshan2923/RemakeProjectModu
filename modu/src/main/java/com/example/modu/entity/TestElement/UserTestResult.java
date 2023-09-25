package com.example.modu.entity.TestElement;

import com.example.modu.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
//@Setter
@Table(name = "userTestResult")
@NoArgsConstructor
public class UserTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testerId")
    private Tester tester;

    public UserTestResult(int score) {
        this.score = score;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTester(Tester tester){
        this.tester = tester;
    }

    public UserTestResult(User user, int score, Tester tester)
    {
        this.user = user;
        this.score = score;
        this.tester = tester;
    }
}
