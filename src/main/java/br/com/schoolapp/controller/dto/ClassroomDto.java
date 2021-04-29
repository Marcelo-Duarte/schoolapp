package br.com.schoolapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassroomDto {

    private long id;
    private String name;
    private int size;
    private int year;

}
