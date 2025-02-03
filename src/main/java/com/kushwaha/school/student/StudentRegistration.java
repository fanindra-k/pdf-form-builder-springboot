package com.kushwaha.school.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class StudentRegistration {

    private StudentPdf studentPdf;
    @Autowired
    public StudentRegistration(StudentPdf studentPdf) {
        this.studentPdf = studentPdf;
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> getStudentPdf() {
        StudentPdfDto dto = new StudentPdfDto(
                "John Doe",
                LocalDate.of(2010, 1, 15), // Date of Birth (YYYY, MM, DD)
                "New York",
                "Male",
                "123 Main St",
                "New York",
                "10001",
                "123-456-7890",
                "john.doe@example.com"
        );

        return studentPdf.generateStudentPdf(dto);
    }

}
