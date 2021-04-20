package br.com.schoolapp.controller.dto;

public class StudentDto {

    private long id;
    private String name;
    private String lastName;
    private int registration;
    private long idClassroom;
    private String mentorName;

    public StudentDto(String name, String lastName, int registration, long idClassroom) {
        this.name = name;
        this.lastName = lastName;
        this.registration = registration;
        this.idClassroom = idClassroom;
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

    public long getIdClassroom() {
        return idClassroom;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
}
