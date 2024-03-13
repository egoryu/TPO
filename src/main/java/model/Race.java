package model;

import lombok.Getter;

@Getter
public class Race {
    String name;
    String description;
    Boolean physicalManifestation;

    public Race(String name, String description) {
        this.name = name;
        this.description = description;
        this.physicalManifestation = false;
    }
}
