package com.horizon.randomplay.components.series;

public class Episode {
    
    private final int number;
    private String name;
    private Long id = null;


    public Episode(int number, String name, Long id) {
        this.number = number;
        this.name = name;
        this.id = id;
    }

    public Episode(int number, String name) {
        this(number, name, null);
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

    public void setName(String name) {
        this.name = name;
    }
}