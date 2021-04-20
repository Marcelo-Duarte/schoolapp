package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.MentorDto;
import br.com.schoolapp.model.Mentor;
import br.com.schoolapp.repository.MentorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

    @Mock
    private MentorRepository mentorRepository;

    @InjectMocks
    private MentorService mentorService;

    @Test
    public void testSave() {
        Mentor mentor = new Mentor("name", "lastName", 2222);

        when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

        MentorDto mentorDto = mentorService.save(new MentorDto("name", "lastName", 2222));

        Assertions.assertEquals("name", mentorDto.getName());
        Assertions.assertEquals("lastName", mentorDto.getLastName());
        Assertions.assertEquals(2222, mentorDto.getRegistration());
    }
}
