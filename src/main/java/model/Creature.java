package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class Creature implements Playable {
    String name;
    Race race;
    int status;

    public Creature(String name, Race race) {
        this.name = name;
        this.race = race;
        this.status = 0;
    }

    public String check() {
        if (this.status < 0) {
            return "Дипрессия";
        }

        if (this.status <= 10) {
            return "Нормально";
        }

        return "Счастлив";
    }
}
