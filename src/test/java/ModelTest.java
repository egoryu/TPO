import lombok.SneakyThrows;
import model.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class ModelTest {
    static Universe universe;
    static Race commonHuman;
    static Race alpha;
    static Human human, target, man;
    static HyperMind hyperMind, happyHyperMind;
    static Question question1, question2, question3;
    @BeforeEach
    public void initialize() {
        universe = new Universe("Kek", null);

        commonHuman = new Race("обычные люди", "просто обычный человек");
        alpha = new Race("элита", "лучше чем другие");

        human = new Human("Егор", commonHuman);
        target = new Human("Миша", alpha);
        man = new Human("Илья", alpha);

        hyperMind = new HyperMind("Андрей", commonHuman);
        happyHyperMind = new HyperMind("Артем", alpha);

        question1 = new Question("Я");
        question2 = new Question("кто?");
        question3 = new Question("Человек?");
    }

    @Test
    @DisplayName("Creature class")
    public void checkCreature() {
        Human human = new Human("Егор", commonHuman), target = new Human("Миша", alpha);
        HyperMind hyperMind = new HyperMind("Андрей", commonHuman), happyHyperMind = new HyperMind("Артем", alpha);

        Assertions.assertEquals("Егор", human.getName());
        Assertions.assertEquals("обычные люди", human.getRace().getName());
        Assertions.assertEquals(10, human.getStatus());
        Assertions.assertEquals("Нормально", human.check());

        human.play(target);
        Assertions.assertEquals(11, human.getStatus());
        Assertions.assertEquals(9, target.getStatus());
        Assertions.assertEquals("Счастлив", human.check());

        target.play(hyperMind);
        Assertions.assertEquals(10, target.getStatus());
        Assertions.assertEquals(-1, hyperMind.getStatus());
        Assertions.assertEquals("Дипрессия", hyperMind.check());

        Exception exception = Assertions.assertThrows(Exception.class, () -> happyHyperMind.play(hyperMind));
        Assertions.assertEquals("Чужик не трогаем", exception.getMessage());

        happyHyperMind.play(target);
        Assertions.assertEquals(10, happyHyperMind.getStatus());
        Assertions.assertEquals("Нормально", happyHyperMind.check());
        Assertions.assertEquals(-20, target.getStatus());
    }

    @Test
    @DisplayName("Race class")
    public void checkRace() {
        Race race = new Race("Егор", "это я");

        Assertions.assertEquals("Егор", race.getName());
        Assertions.assertEquals("это я", race.getDescription());
        Assertions.assertEquals(false, race.getPhysicalManifestation());
    }

    @Test
    @DisplayName("Question class")
    public void checkQuestion() {
        Question question = new Question("Егор");

        Assertions.assertEquals("Егор", question.getText());
        Assertions.assertNull(question.getAnswer());
    }

    @SneakyThrows
    @Test
    @DisplayName("Dispute class")
    public void checkDispute() {
        Exception exception;

        exception = Assertions.assertThrows(Exception.class, () -> new Dispute("Смысл жизни", null, null));
        Assertions.assertEquals("Мало народу для дискусии", exception.getMessage());
        exception = Assertions.assertThrows(Exception.class, () -> new Dispute("Смысл жизни", new ArrayList<>(), null));
        Assertions.assertEquals("Мало народу для дискусии", exception.getMessage());
        exception = Assertions.assertThrows(Exception.class, () -> new Dispute("Смысл жизни", List.of(human), null));
        Assertions.assertEquals("Мало народу для дискусии", exception.getMessage());

        exception = Assertions.assertThrows(Exception.class, () -> new Dispute("Смысл жизни", List.of(human, hyperMind, target), null));
        Assertions.assertEquals("Без задач нет и дискусии", exception.getMessage());
        exception = Assertions.assertThrows(Exception.class, () -> new Dispute("Смысл жизни", List.of(human, hyperMind, target), new ArrayList<>()));
        Assertions.assertEquals("Без задач нет и дискусии", exception.getMessage());

        Dispute dispute = new Dispute("Смысл жизни", List.of(human, hyperMind, target), List.of(question1, question2));
        Assertions.assertEquals("Смысл жизни", dispute.getTheme());
        Assertions.assertEquals(List.of(human, hyperMind, target), dispute.getMembers());
        Assertions.assertEquals(List.of(question1, question2), dispute.getQuestions());

        dispute.addMember(happyHyperMind);
        Assertions.assertEquals(List.of(human, hyperMind, target, happyHyperMind), dispute.getMembers());
        exception = Assertions.assertThrows(Exception.class, () -> dispute.addMember(man));
        Assertions.assertEquals("Слишком много участников", exception.getMessage());

        dispute.removeMembers("Егор");
        Assertions.assertEquals(List.of(hyperMind, target, happyHyperMind), dispute.getMembers());
        dispute.removeMembers("Андрей");
        exception = Assertions.assertThrows(Exception.class, () -> dispute.removeMembers("Миша"));
        Assertions.assertEquals("В одиночку нельзя решать вопросы", exception.getMessage());
        exception = Assertions.assertThrows(Exception.class, () -> dispute.removeMembers("Кек"));
        Assertions.assertEquals("Такого участника нет", exception.getMessage());

        dispute.addQuestion(question3);
        Assertions.assertEquals(List.of(question1, question2, question3), dispute.getQuestions());

        dispute.setSolution("Я", "Кек");
        Assertions.assertEquals("Кек", dispute.getQuestions().get(0).getAnswer());
        exception = Assertions.assertThrows(Exception.class, () -> dispute.setSolution("Кек", "Я"));
        Assertions.assertEquals("Нельзя ответить на неизвестный вопрос", exception.getMessage());

        Assertions.assertFalse(dispute.check());
        dispute.setSolution("кто?", "Кек");
        dispute.setSolution("Человек?", "Кек");
        Assertions.assertTrue(dispute.check());
    }

    @SneakyThrows
    @Test
    @DisplayName("Universe class")
    public void checkUniverse() {
        Assertions.assertEquals("Kek", universe.getName());
        Assertions.assertNull(universe.getDisputes());
        Dispute dispute1 = new Dispute("Смысл жизни", List.of(human, hyperMind), List.of(question1)),
                dispute2 = new Dispute("Смысл жизни 2", List.of(target, happyHyperMind), List.of(question2));

        universe.addDispute(dispute1);
        universe.addDispute(dispute2);
        Assertions.assertEquals(List.of(dispute1, dispute2), universe.getDisputes());

        universe.checkDisputes();
        Assertions.assertFalse(commonHuman.getPhysicalManifestation());
        Assertions.assertFalse(alpha.getPhysicalManifestation());

        dispute1.setSolution("Я", "Лул");
        universe.checkDisputes();
        Assertions.assertTrue(commonHuman.getPhysicalManifestation());
        Assertions.assertFalse(alpha.getPhysicalManifestation());

        dispute2.setSolution("кто?", "Лул");
        universe.checkDisputes();
        Assertions.assertTrue(commonHuman.getPhysicalManifestation());
        Assertions.assertTrue(alpha.getPhysicalManifestation());
    }
}
