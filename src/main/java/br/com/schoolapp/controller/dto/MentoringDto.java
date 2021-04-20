package br.com.schoolapp.controller.dto;

public class MentoringDto {

    public long id;
    public long idMentor;
    public long idStudent;

    public MentoringDto(long idMentor, long idStudent) {
        this.idMentor = idMentor;
        this.idStudent = idStudent;
    }

    public long getId() {
        return id;
    }

    public long getIdMentor() {
        return idMentor;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setId(long id) {
        this.id = id;
    }
}
