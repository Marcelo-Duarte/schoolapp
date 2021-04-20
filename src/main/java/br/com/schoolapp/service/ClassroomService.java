package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.ClassroomDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<ClassroomDto> get() {
        List<ClassroomDto> classroomsDto = new ArrayList<>();

        classroomRepository.findAll().forEach(classroom -> classroomsDto.add(convertToDto(classroom)));

        return classroomsDto;
    }

    public ClassroomDto save(ClassroomDto classroomDto) {
        return convertToDto(classroomRepository.save(convertToEntity(classroomDto)));
    }

    public void delete(long idClassroom) {
        try {
            classroomRepository.deleteById(idClassroom);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public Classroom convertToEntity(ClassroomDto classroomDto) {
        return new Classroom(classroomDto.getName(), classroomDto.getYear());
    }

    public ClassroomDto convertToDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto(classroom.getName(), classroom.getSize(), classroom.getYear());
        classroomDto.setId(classroom.getId());

        return classroomDto;
    }
}
