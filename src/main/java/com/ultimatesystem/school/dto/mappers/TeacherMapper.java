package com.ultimatesystem.school.dto.mappers;

import com.ultimatesystem.school.dto.TeacherDto;
import com.ultimatesystem.school.model.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherMapper {

    public static TeacherDto toDto (Teacher teacher){
        return TeacherDto.builder()
                .teacherId(teacher.getTeacherId())
                .firstName(teacher.getFirstName())
                .name(teacher.getName())
                .age(teacher.getAge())
                .eMail(teacher.getEMail())
                .subject(teacher.getSubject())
                .build();
    }
}
