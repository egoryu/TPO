package model;

import lombok.Data;
import lombok.Getter;

public class Human extends Creature {

    public Human(String name, Race race) {
        super(name, race);
        this.status = 10;
    }
    @Override
    public void play(Creature creature) {
        this.status += 1;
        creature.status -= 1;
        System.out.println("Я пнул мяч в " + creature.name);
    }
}
