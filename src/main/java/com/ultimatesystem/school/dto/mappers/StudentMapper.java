package com.ultimatesystem.school.dto.mappers;

import com.ultimatesystem.school.dto.StudentDto;
import com.ultimatesystem.school.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public static StudentDto toDto(Student student) {
        return StudentDto.builder()
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .name(student.getName())
                .age(student.getAge())
                .eMail(student.getEMail())
                .faculty(student.getFaculty())
                .build();
    }
}
