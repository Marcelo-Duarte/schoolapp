package br.com.schoolapp.controller;

import br.com.schoolapp.controller.dto.ClassroomDto;
import br.com.schoolapp.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/classrooms")
    public List<ClassroomDto> getClassrooms() {
        return classroomService.get();
    }

    @PostMapping("/addclassroom")
    @Transactional
    public ResponseEntity<ClassroomDto> addClassroom(@RequestBody ClassroomDto classroomDto, UriComponentsBuilder uriBuilder) {
        ClassroomDto classroomResponse = classroomService.save(classroomDto);

        URI uri = uriBuilder.path("/addclassroom/{id}").buildAndExpand(classroomResponse).toUri();
        return ResponseEntity.created(uri).body(classroomResponse);
    }

    @DeleteMapping("/deleteclassroom/{idClassroom}")
    @Transactional
    public ResponseEntity deleteClassroom(@PathVariable long idClassroom) {
        classroomService.delete(idClassroom);

        return ResponseEntity.noContent().build();
    }
}
