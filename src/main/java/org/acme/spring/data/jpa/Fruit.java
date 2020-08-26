package org.acme.spring.data.jpa;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Fruit {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;

    public Box getBox() {
        return box;
    }

    @Embedded
    private Box box;

    public Fruit() {
    }

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
