package br.com.schoolapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {

    private long id;
    private String name;
    private String lastName;
    private int registration;
    private long idClassroom;
    private String mentorName;
}
