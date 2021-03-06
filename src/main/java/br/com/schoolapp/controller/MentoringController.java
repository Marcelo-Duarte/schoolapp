package br.com.schoolapp.controller;

import br.com.schoolapp.controller.dto.MentoringDto;
import br.com.schoolapp.service.MentoringService;
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
public class MentoringController {

    @Autowired
    private MentoringService mentoringService;

    @GetMapping("/mentorings")
    @ApiOperation(value = "Returns Mentoring list")
    public List<MentoringDto> getMentorings() {
        return mentoringService.get();
    }

    @PostMapping("/addmentoring")
    @ApiOperation(value = "Adds a new Mentoring")
    @Transactional
    public ResponseEntity<MentoringDto> addMentoring(@RequestBody MentoringDto mentoringDto, UriComponentsBuilder uriBuilder) {
        MentoringDto mentoringResponse = mentoringService.save(mentoringDto);

        URI uri = uriBuilder.path("/addmentoring/{id}").buildAndExpand(mentoringResponse).toUri();
        return ResponseEntity.created(uri).body(mentoringResponse);
    }

    @DeleteMapping("/deletementoring/{idMentoring}")
    @ApiOperation(value = "Deletes a Mentoring")
    @Transactional
    public ResponseEntity deleteMentoring(@PathVariable long idMentoring) {
        mentoringService.delete(idMentoring);

        return ResponseEntity.noContent().build();
    }
}
