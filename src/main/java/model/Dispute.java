package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Dispute {
    String theme;
    List<Creature> members;
    List<Question> questions;

    public Dispute(String theme, List<Creature> creatures, List<Question> questions) throws Exception {
        if (creatures == null || creatures.size() < 2) {
            throw new Exception("Мало народу для дискусии");
        }

        if (questions == null || questions.isEmpty()) {
            throw new Exception("Без задач нет и дискусии");
        }

        this.theme = theme;
        this.members = new ArrayList<>(creatures);
        this.questions = new ArrayList<>(questions);
    }

    public void addMember(Creature newMember) throws Exception {
        if (this.members.size() >= 4) {
            throw new Exception("Слишком много участников");
        }

        this.members.add(newMember);
    }

    public void removeMembers(String name) throws Exception {
        Optional<Creature> candidate = this.members.stream().filter(val -> val.name.equals(name)).findFirst();

        if (candidate.isEmpty()) {
            throw new Exception("Такого участника нет");
        }

        if (this.members.size() == 2) {
            throw new Exception("В одиночку нельзя решать вопросы");
        }

        this.members.remove(candidate.get());
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setSolution(String text, String answer) throws Exception {
        Optional<Question> question = questions.stream().filter(val -> val.text.equals(text)).findFirst();

        if (question.isEmpty()) {
            throw new Exception("Нельзя ответить на неизвестный вопрос");
        }

        question.get().setAnswer(answer);
    }

    public boolean check() {
        long notSolved = questions.stream().filter(val -> val.answer == null).count();

        return notSolved == 0;
    }
}
