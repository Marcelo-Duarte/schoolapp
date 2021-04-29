package br.com.schoolapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MentorDto {

    private long id;
    private String name;
    private String lastName;
    private int registration;
    private List<String> studentsNames;
}
