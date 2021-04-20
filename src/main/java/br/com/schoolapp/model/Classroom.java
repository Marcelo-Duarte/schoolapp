package br.com.schoolapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;
    private int year;

    public Classroom(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Classroom() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getYear() {
        return year;
    }

    public void incrementSize() {
        size++;
    }

    public void decrementSize() {
        size--;
    }
}
