package com.kushwaha.school.student;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

   private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid @RequestBody StudentDto studentDto
    ){
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(
            @PathVariable("student-id") Integer studentId
    ){
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents(){

        return studentService.findAllStudents();
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findAllStudentsByName(
            @PathVariable("student-name") String name
    ){
        return studentService.findAllStudentsByName(name);
    }

}
