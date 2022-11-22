package com.ultimatesystem.school.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private int studentId;

    @Column(name = "first_name")
    @Schema(name = "firstName", example = "Jack")
    @Size(min = 3)
    private String firstName;

    @Column(name = "second_name")
    @Schema(name = "secondName", example = "Sparrow")
    private String name;

    @Column(name = "age")
    @Schema(name = "age", example = "30")
    @Min(value = 18, message = "Student must be at least 18 years old")
    private int age;

    @Column(name = "e_mail")
    @Schema(name = "eMail", example = "jackSparrow@gamil.com")
    @Email
    private String eMail;

    @Column(name = "faculty")
    @Schema(name = "faculty", example = "economy")
    private String faculty;

    @Column(name = "student_teachers")
    @Schema(name = "studentTeachers")
    @ManyToMany(mappedBy = "teacherStudents")
    private List<Teacher> studentTeachers;
}
