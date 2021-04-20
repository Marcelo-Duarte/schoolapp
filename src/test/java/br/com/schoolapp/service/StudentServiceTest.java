package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.StudentDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.ClassroomRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testSave() {
        Classroom classroom = new Classroom("name", 2021);
        Student student = new Student("name", "lastName", 2222, classroom);

        when(classroomRepository.getOne(0L)).thenReturn(classroom);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto studentDto = studentService.save(new StudentDto("name", "lastName", 2222, 0));

        Assertions.assertEquals("name", studentDto.getName());
        Assertions.assertEquals("lastName", studentDto.getLastName());
        Assertions.assertEquals(2222, studentDto.getRegistration());
        Assertions.assertEquals(0, studentDto.getIdClassroom());
    }

//    @Test
//    public void testChangeClassroom() {
//        Classroom classroom = new Classroom("newClassroom", 2021);
//        Student student = new Student("name", "lastName", 2222, new Classroom("oldClassroom", 2020));
//
//        when(studentRepository.getOne(0L)).thenReturn(student);
//        when(classroomRepository.getOne(0L)).thenReturn(classroom);
//
//        StudentDto studentResponse = studentService.changeClassroom(0, 0);
//
//        Assertions.assertEquals("newClassroom", studentResponse.);
//    }

    @Test
    public void testConvertToDto() {
        Student student = new Student("name", "lastName", 2222, new Classroom("name", 2021));

        StudentDto studentDto = studentService.convertToDto(student);

        Assertions.assertEquals("name", studentDto.getName());
        Assertions.assertEquals("lastName", studentDto.getLastName());
        Assertions.assertEquals(2222, studentDto.getRegistration());
        Assertions.assertEquals(0, studentDto.getIdClassroom());
    }

    @Test
    public void testConvertToEntity() {
        StudentDto studentDto = new StudentDto("name", "lastName", 2222, 0);

        when(classroomRepository.getOne(0L)).thenReturn(new Classroom("name", 2021));

        Student student = studentService.convertToEntity(studentDto);

        Assertions.assertEquals("name", student.getName());
        Assertions.assertEquals("lastName", student.getLastName());
        Assertions.assertEquals(2222, student.getRegistration());
        Assertions.assertEquals(0, student.getClassroom().getId());
    }
}
