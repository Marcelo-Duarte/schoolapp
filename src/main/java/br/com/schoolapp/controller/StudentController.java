package br.com.schoolapp.controller;

import br.com.schoolapp.controller.dto.StudentDto;
import br.com.schoolapp.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    @ApiOperation(value = "Returns Students list")
    public List<StudentDto> getStudents() {
        return studentService.get();
    }

    @PostMapping("/addstudent")
    @ApiOperation(value = "Adds a new Student")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto, UriComponentsBuilder uriBuilder) {
        StudentDto studentResponse = studentService.save(studentDto);

        URI uri = uriBuilder.path("/addstudent/{id}").buildAndExpand(studentResponse).toUri();

        return ResponseEntity.created(uri).body(studentResponse);
    }

    @PutMapping("/changeclassroom/{idStudent}")
    @ApiOperation(value = "Changes a Student's classroom")
    public ResponseEntity<StudentDto> changeClassroom(@PathVariable long idStudent, @RequestBody long idClassroom) {
        StudentDto studentDto = studentService.changeClassroom(idStudent, idClassroom);

        return ResponseEntity.ok().body(studentDto);
    }

    @DeleteMapping("/deletestudent/{idStudent}")
    @ApiOperation(value = "Deletes a Student")
    public ResponseEntity deleteStudent(@PathVariable long idStudent) {
        studentService.delete(idStudent);

        return ResponseEntity.noContent().build();
    }
}
