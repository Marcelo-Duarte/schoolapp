package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.ClassroomDto;
import br.com.schoolapp.model.Classroom;
import br.com.schoolapp.repository.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

    @Mock
    ClassroomRepository classroomRepository;

    @InjectMocks
    ClassroomService classroomService;

    @Test
    public void givenClassroomIdMustDeleteCorrespondingClassroom() {
        classroomService.delete(0);

        verify(classroomRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void givenClassroomDtoMustReturnClassroom() {
        Classroom classroom = classroomService.convertToEntity(new ClassroomDto(0,"name", 0, 2021));

        assertEquals("name", classroom.getName());
        assertEquals(0, classroom.getSize());
        assertEquals(2021, classroom.getYear());
    }

//    @Test
//    public void givenClassroomMustReturnClassroomDto() {
//        ClassroomDto classroomDto = classroomService.convertToDto(new Classroom("name", 2021));
//
//        assertEquals("name", classroomDto.getName());
//        assertEquals(2021, classroomDto.getYear());
//    }
}
