package com.example.modu.entity.TestElement;

import com.example.modu.entity.TestElement.Choice;
import com.example.modu.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_choice")
@NoArgsConstructor
public class UserChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "choiceId")
    private Choice choice;

    public UserChoice(User user, Choice choice) {
        this.user = user;
        this.choice = choice;
    }
}
