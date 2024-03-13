package model;

import lombok.SneakyThrows;

public class HyperMind extends Creature {
    public HyperMind(String name, Race race) {
        super(name, race);
    }


    @SneakyThrows
    @Override
    public void play(Creature creature) {
        if (!creature.race.name.equals(this.race.name)) {
            throw new Exception("Чужик не трогаем");
        }

        this.status += 10;
        creature.status -= 30;
        System.out.println("Я ударил " + creature.name);
    }
}
