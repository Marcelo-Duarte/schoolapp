package br.com.schoolapp.model;

import javax.persistence.*;

@Entity
public class Mentoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    public Mentor mentor;

    @OneToOne
    public Student student;

    public Mentoring(Mentor mentor, Student student) {
        this.mentor = mentor;
        this.student = student;
    }

    public Mentoring() {}

    public long getId() {
        return id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public Student getStudent() {
        return student;
    }
}
