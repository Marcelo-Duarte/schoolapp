package br.com.schoolapp.controller.dto;

import java.util.List;

public class MentorDto {

    private long id;
    private String name;
    private String lastName;
    private int registration;
    private List<String> studentsNames;

    public MentorDto(String name, String lastName, int registration) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.registration = registration;
    }

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

    public List<String> getStudentsNames() {
        return studentsNames;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStudentsNames(List<String> studentsNames) {
        this.studentsNames = studentsNames;
    }
}
