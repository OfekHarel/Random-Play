package com.horizon.randomplay.components.series;

public class Episode {
    
    private final int number;
    private final String name;
    private Long id = null;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}