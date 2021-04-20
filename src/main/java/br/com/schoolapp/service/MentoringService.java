package br.com.schoolapp.service;

import br.com.schoolapp.controller.dto.MentoringDto;
import br.com.schoolapp.model.Mentoring;
import br.com.schoolapp.model.Student;
import br.com.schoolapp.repository.MentorRepository;
import br.com.schoolapp.repository.MentoringRepository;
import br.com.schoolapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MentoringService {

    @Autowired
    private MentoringRepository mentoringRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<MentoringDto> get() {
        List<MentoringDto> mentoringsDto = new ArrayList<>();

        mentoringRepository.findAll().forEach(mentoring -> mentoringsDto.add(convertToDto(mentoring)));

        return mentoringsDto;
    }

    public MentoringDto save(MentoringDto mentoringDto) {
        try {
            if(mentorRepository.getOne(mentoringDto.getIdMentor()).getMentorings().size() < 3) {
                studentRepository.getOne(mentoringDto.getIdStudent());

                Mentoring mentoring = mentoringRepository.save(convertToEntity(mentoringDto));

                mentorRepository.getOne(mentoringDto.getIdMentor())
                        .assignMentoring(mentoring);

                studentRepository.getOne(mentoringDto.getIdStudent())
                        .setMentoring(mentoring);

                return convertToDto(mentoring);
            }

            return null;
        } catch(EntityNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void delete(long idMentoring) throws EntityNotFoundException {
        try {
            Student student = studentRepository.getOne(mentoringRepository.getOne(idMentoring).getStudent().getId());

            student.setMentoring(null);

            mentoringRepository.deleteById(idMentoring);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public Mentoring convertToEntity(MentoringDto mentoringDto) {
        return new Mentoring(mentorRepository.getOne(mentoringDto.getIdMentor()), studentRepository.getOne(mentoringDto.getIdStudent()));
    }

    public MentoringDto convertToDto(Mentoring mentoring) {
        MentoringDto mentoringDto = new MentoringDto(mentoring.getMentor().getId(), mentoring.getStudent().getId());
        mentoringDto.setId(mentoring.getId());

        return mentoringDto;
    }
}
