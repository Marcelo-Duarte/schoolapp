package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.MentoringDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.model.Mentor;
import br.com.schoolapp.model.Mentoring;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.MentorRepository;
import br.com.schoolapp.repository.MentoringRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MentoringServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    MentorRepository mentorRepository;

    @Mock
    MentoringRepository mentoringRepository;

    @InjectMocks
    MentoringService mentoringService;

    // Testa método save
    @Test
    public void givenMentoringDtoMustSaveMentoringAndReturnMentoringDto() {
        Student student = new Student("name", "lastName", 1111, new Classroom("name", 2021));
        Mentor mentor = new Mentor("name", "lastname", 1111);
        Mentoring mentoring = new Mentoring(mentor, student);

        when(mentorRepository.getOne(anyLong())).thenReturn(mentor);
        when(studentRepository.getOne(anyLong())).thenReturn(student);
        when(mentoringRepository.save(any())).thenReturn(mentoring);

        MentoringDto mentoringDto = mentoringService.save(new MentoringDto(0, 0, 0));

        assertEquals(0, mentoringDto.getIdMentor());
        assertEquals(0, mentoringDto.getIdStudent());
    }

    // Testa método delete
    @Test
    public void givenMentoringIdDeletesCorrespondingMentoring() {
        when(mentoringRepository.getOne(anyLong())).thenReturn(new Mentoring(new Mentor(), new Student()));
        when(studentRepository.getOne(anyLong())).thenReturn(new Student());

        mentoringService.delete(0);

        verify(mentoringRepository, times(1)).deleteById(0L);
    }

    // Testa método convertToDto
//    @Test
//    public void givenMentoringMustReturnMentoringDto() {
//        MentoringDto mentoringDto = mentoringService
//                .convertToDto(new Mentoring(new Mentor("mentorName", "mentorLastName", 1111), new Student("studentName", "studentLastName", 2222, new Classroom())));
//
//        assertEquals("mentorName", mentoringDto.);
//    }
}
