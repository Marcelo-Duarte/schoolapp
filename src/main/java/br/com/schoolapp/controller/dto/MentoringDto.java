package br.com.schoolapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MentoringDto {

    public long id;
    public long idMentor;
    public long idStudent;
}
