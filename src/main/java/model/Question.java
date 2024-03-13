package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Question {
    String text;
    String answer;

    public Question(String text) {
        this.text = text;
        this.answer = null;
    }
}
