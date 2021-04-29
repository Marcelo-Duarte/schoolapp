package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.MentorDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.model.Mentor;
import br.com.schoolapp.model.Mentoring;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.MentorRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private MentorService mentorService;

    @Test
    public void givenMentorIdMustDeleteCorrespondingMentor() {
        Mentor mentor = new Mentor("name", "lastName", 1111);
        Student student = new Student("name", "lastName", 1111, new Classroom("name", 2021));
        Mentoring mentoring = new Mentoring(mentor, student);

        mentor.assignMentoring(mentoring);
        student.setMentoring(mentoring);

        when(mentorRepository.getOne(anyLong())).thenReturn(mentor);
        when(studentRepository.getOne(anyLong())).thenReturn(student);

        mentorService.delete(0);

        verify(mentorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void givenMentorMustReturnMentorDto() {
        Mentor mentor = new Mentor("name", "lastName", 2222);

        when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

        MentorDto mentorDto = mentorService.save(new MentorDto(0, "name", "lastName", 2222, new ArrayList<>()));

        assertEquals("name", mentorDto.getName());
        assertEquals("lastName", mentorDto.getLastName());
        assertEquals(2222, mentorDto.getRegistration());
    }

    @Test
    public void givenMentorDtoMustReturnMentor() {
        MentorDto mentorDto = new MentorDto(0, "name", "lastName", 1111, new ArrayList<>());

        Mentor mentor = mentorService.convertToEntity(mentorDto);

        assertEquals("name", mentor.getName());
        assertEquals("lastName", mentor.getLastname());
        assertEquals(1111, mentor.getRegistration());
    }
}
