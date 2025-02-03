package com.kushwaha.school.student;


import java.time.LocalDate;

public record StudentPdfDto(
    String fullName,
    LocalDate dob,
    String placeOfBirth,
    String gender,
    String homeAddress,
    String city,
    String zipcode,
    String phoneNumber,
    String email
) {
}
