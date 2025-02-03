package com.kushwaha.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schoolDto
    ) {
        var school = toSchool(schoolDto);
        var savedSchool = schoolRepository.save(school);
        return schoolDto;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

    private School toSchool(SchoolDto schoolDto) {
        var school = new School();
        school.setName(schoolDto.name());
        return school;
    }
    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }
}
