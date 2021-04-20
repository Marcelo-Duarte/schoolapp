package br.com.schoolapp.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private int registration;

    @ManyToOne
    @JoinColumn(name = "classsroom_id")
    private Classroom classroom;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Mentoring mentoring;

    public Student(String name, String lastName, int registration, Classroom classroom) {
        this.name = name;
        this.lastName = lastName;
        this.registration = registration;
        this.classroom = classroom;
    }

    public Student() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRegistration() {
        return registration;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Mentoring getMentoring() {
        return mentoring;
    }

    public void setMentoring(Mentoring mentoring) {
        this.mentoring = mentoring;
    }
}
