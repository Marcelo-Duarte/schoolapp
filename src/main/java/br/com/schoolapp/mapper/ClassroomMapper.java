package br.com.schoolapp.mapper;

import br.com.schoolapp.controller.dto.ClassroomDto;
import br.com.schoolapp.model.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    ClassroomDto toClassroomDto(Classroom classroom);
}
