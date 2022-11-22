package com.ultimatesystem.school.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDto {

    private int teacherId;
    private String firstName;
    private String name;
    private int age;
    private String eMail;
    private String subject;

}
