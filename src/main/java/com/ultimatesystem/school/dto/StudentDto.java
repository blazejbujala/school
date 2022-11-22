package com.ultimatesystem.school.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentDto {

    private int studentId;
    private String firstName;
    private String name;
    private int age;
    private String eMail;
    private String faculty;

}
