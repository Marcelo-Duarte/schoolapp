package br.com.schoolapp.controller;

import br.com.schoolapp.controller.dto.MentorDto;
import br.com.schoolapp.service.MentorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/mentors")
    @ApiOperation(value = "Returns Mentors list")
    public List<MentorDto> getMentors() {
        return mentorService.get();
    }

    @PostMapping("/addmentor")
    @ApiOperation(value = "Adds a new Mentor")
    @Transactional
    public ResponseEntity<MentorDto> addMentor(@RequestBody MentorDto mentorDto, UriComponentsBuilder uriBuilder) {
        MentorDto mentorResponse = mentorService.save(mentorDto);

        URI uri = uriBuilder.path("/addmentor/{id}").buildAndExpand(mentorResponse).toUri();
        return ResponseEntity.created(uri).body(mentorResponse);
    }

    @DeleteMapping("/deletementor/{idMentor}")
    @ApiOperation(value = "Deletes a Mentor")
    @Transactional
    public ResponseEntity deleteMentor(@PathVariable long idMentor) {
        mentorService.delete(idMentor);

        return ResponseEntity.noContent().build();
    }
}
