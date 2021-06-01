package com.horizon.randomplay.components;

public class Episode {
    
    private final int number;
    private final String name;

    public Episode(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}