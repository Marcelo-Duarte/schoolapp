package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.StudentDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.ClassroomRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void givenStudentDtoSaveConvertedStudentAndReturnCorrespondingStudentDto() {
        Classroom classroom = new Classroom("name", 2021);
        Student student = new Student("name", "lastName", 2222, classroom);

        when(classroomRepository.getOne(0L)).thenReturn(classroom);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto studentDto = studentService.save(new StudentDto(0, "name", "lastName", 2222, 0, null));

        assertEquals("name", studentDto.getName());
        assertEquals("lastName", studentDto.getLastName());
        assertEquals(2222, studentDto.getRegistration());
        assertEquals(0, studentDto.getIdClassroom());
    }

    @Test
    public void givenStudentIdMustDeleteCorrespondingStudent() {
        Classroom classroom = new Classroom("name", 2021);

        when(classroomRepository.getOne(anyLong())).thenReturn(classroom);
        when(studentRepository.getOne(anyLong())).thenReturn(new Student("name", "lastName", 1111, classroom));

        studentService.delete(0L);

        verify(studentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void givenStudentIdAndClassroomIdChangesStudentsClassroom() {
        Classroom oldClassroom = new Classroom("oldOne", 2020);
        oldClassroom.setId(0);
        Classroom newClassroom = new Classroom("newOne", 2021);
        newClassroom.setId(1);

        when(studentRepository.getOne(anyLong())).thenReturn(new Student("name", "lastName", 1111, oldClassroom));
        when(classroomRepository.getOne(anyLong())).thenReturn(newClassroom);

        StudentDto studentDto = studentService.changeClassroom(0, 0);

        assertEquals(1, studentDto.getIdClassroom());
    }

    @Test
    public void givenStudentMustReturnStudentDto() {
        Student student = new Student("name", "lastName", 2222, new Classroom("name", 2021));

        StudentDto studentDto = studentService.convertToDto(student);

        assertEquals("name", studentDto.getName());
        assertEquals("lastName", studentDto.getLastName());
        assertEquals(2222, studentDto.getRegistration());
        assertEquals(0, studentDto.getIdClassroom());
    }

    @Test
    public void givenStudentDtoMustReturnStudent() {
        StudentDto studentDto = new StudentDto(0, "name", "lastName", 2222, 0, null);

        when(classroomRepository.getOne(0L)).thenReturn(new Classroom("name", 2021));

        Student student = studentService.convertToEntity(studentDto);

        assertEquals("name", student.getName());
        assertEquals("lastName", student.getLastName());
        assertEquals(2222, student.getRegistration());
        assertEquals(0, student.getClassroom().getId());
    }
}
