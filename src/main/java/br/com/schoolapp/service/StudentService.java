package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.StudentDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.ClassroomRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<StudentDto> get() {
        List<StudentDto> studentsDto = new ArrayList<>();

        studentRepository.findAll().forEach(student -> studentsDto.add(convertToDto(student)));

        return studentsDto;
    }

    public StudentDto save(StudentDto studentDto) {
        try {
            classroomRepository.getOne(studentDto.getIdClassroom()).incrementSize();

            return convertToDto(studentRepository.save(convertToEntity(studentDto)));
        } catch (EntityNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public StudentDto changeClassroom(long studentId, long classroomId) {
        try {
            Student student = studentRepository.getOne(studentId);

            Classroom classroom = classroomRepository.getOne(classroomId);

            student.getClassroom().decrementSize();

            student.setClassroom(classroom);

            student.getClassroom().incrementSize();

            return convertToDto(student);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void delete(long idStudent) {
        try {
            classroomRepository.getOne(studentRepository.getOne(idStudent).getClassroom().getId()).decrementSize();
            studentRepository.deleteById(idStudent);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public Student convertToEntity(StudentDto studentDto) throws EntityNotFoundException {
        return new Student(studentDto.getName(), studentDto.getLastName(), studentDto.getRegistration(), classroomRepository.getOne(studentDto.getIdClassroom()));
    }

    public StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getLastName(), student.getRegistration(), student.getClassroom().getId(), null);

        try {
            studentDto.setMentorName(student.getMentoring().getMentor().getName());

            return studentDto;
        } catch (NullPointerException exception) {
            return studentDto;
        }
    }
}
