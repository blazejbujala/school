package com.ultimatesystem.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "first_name")
    @Schema(name = "firstName", example = "Marry")
    @Size(min = 3, message = "First name must be at least 3 letters")
    private String firstName;

    @Column(name = "second_name")
    @Schema(name = "secondName", example = "Johnson")
    private String name;

    @Column(name = "age")
    @Schema(name = "age", example = "32")
    @Min(value = 18, message = "Teacher must be at least 18 years old")
    private int age;

    @Column(name ="e_mail")
    @Schema(name ="eMail", example ="xxx@gamil.com")
    @Email(message = "not proper e-mail address format")
    private String eMail;

    @Column(name = "subject")
    @Schema(name = "subject", example = "Statistics")
    private String subject;

    @ManyToMany(mappedBy = "studentTeachers")
    @Column(name = "teacher_students")
    @Schema(name = "teacherStudents")
    private List<Student> teacherStudents;

}
