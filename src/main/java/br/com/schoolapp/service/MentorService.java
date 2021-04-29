package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.MentorDto;
import br.com.schoolapp.model.Mentor;
import br.com.schoolapp.model.Mentoring;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.MentorRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MentoringService mentoringService;

    public List<MentorDto> get() {
        List<MentorDto> mentorsDto = new ArrayList<>();

        mentorRepository.findAll().forEach(mentor -> mentorsDto.add(convertToDto(mentor)));

        return mentorsDto;
    }

    public MentorDto save(MentorDto mentorDto) {
        return convertToDto(mentorRepository.save(convertToEntity(mentorDto)));
    }

    public void delete(long idMentor) {
        try {
            List<Student> students = mentorRepository.getOne(idMentor)
                    .getMentorings().stream().map(Mentoring::getStudent)
                    .collect(Collectors.toList());

            students.forEach(student -> studentRepository.getOne(student.getId()).setMentoring(null));

            mentorRepository.deleteById(idMentor);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public Mentor convertToEntity(MentorDto mentorDto) {
        return new Mentor(mentorDto.getName(), mentorDto.getLastName(), mentorDto.getRegistration());
    }

    public MentorDto convertToDto(Mentor mentor) {
        MentorDto mentorDto = new MentorDto(mentor.getId(), mentor.getName(), mentor.getLastname(), mentor.getRegistration(), new ArrayList<>());

        try {
            List<Student> students = (mentor.getMentorings().stream()
                    .map(Mentoring::getStudent)
                    .collect(Collectors.toList()));

            mentorDto.setStudentsNames(students.stream().map(Student::getName).collect(Collectors.toList()));
        } catch (NullPointerException exception) {} finally {
            return mentorDto;
        }
    }
}
