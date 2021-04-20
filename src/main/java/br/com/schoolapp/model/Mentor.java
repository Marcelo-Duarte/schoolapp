package br.com.schoolapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastname;
    private int registration;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Mentoring> mentorings;

    public Mentor(String name, String lastname, int registration) {
        this.name = name;
        this.lastname = lastname;
        this.registration = registration;
    }

    public Mentor() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getRegistration() {
        return registration;
    }

    public List<Mentoring> getMentorings() {
        return mentorings;
    }

    public void assignMentoring(Mentoring newMentoring) {
        mentorings.add(newMentoring);
    }
}
