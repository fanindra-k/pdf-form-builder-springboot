package com.kushwaha.school.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(
            StudentDto studentDto
    ) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent =  studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }


    public StudentResponseDto findStudentById(
            Integer studentId
    ){
        return studentRepository.findById(studentId)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }


    public List<StudentResponseDto> findAllStudents(){

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDto> findAllStudentsByName(
            String name
    ){
        return studentRepository.findByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }
}
