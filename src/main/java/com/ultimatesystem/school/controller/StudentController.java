package com.ultimatesystem.school.controller;

import com.ultimatesystem.school.dto.StudentDto;
import com.ultimatesystem.school.dto.TeacherDto;
import com.ultimatesystem.school.model.Student;
import com.ultimatesystem.school.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @PostMapping("/students")
    @Operation(summary = "Add new Student", description = "Add new Student")
    ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        logger.info("Adding student " + student.getFirstName() + student.getName());
        return ResponseEntity.ok(studentService.add(student));
    }

    @GetMapping("/students")
    @Operation(summary = "Get all Students", description = "Get all Students list without teacher's details")
    ResponseEntity<?> readAllStudents(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        logger.warn("Exposing all Students");
        return ResponseEntity.ok(studentService.getAllStudents(pageNumber, sort));
    }

    @GetMapping("/students/{firstName}/{name}")
    @Operation(summary = "Get Student's details", description = "Get Student's all details by first name and name")
    ResponseEntity<Student> findByFirstName(@PathVariable String firstName, @PathVariable String name) {
        logger.info("Getting student with full name " + firstName + " " + name);
        return ResponseEntity.ok(studentService.getStudentByFirstNamAndName(firstName, name));
    }

    @GetMapping("/students/teachers")
    @Operation(summary = "Get list of all Student's teachers", description = "Get all Teachers of a Student by first name and name")
    ResponseEntity<List<TeacherDto>> findTeachersOfStudent(@RequestParam(required = true) String firstName, String name) {
        logger.info("Getting all teachers of a student with full name : " + firstName + " " + name);
        return ResponseEntity.ok(studentService.getTeachersOfStudent(firstName, name));
    }

    @PatchMapping("/students")
    @Operation(summary = "Update Student details", description = "Update Student details in database")
    ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        logger.info("Updating student with ID: " + student.getStudentId());
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/students/{id}")
    @Operation(summary = "Delete Student by id", description = "Delete Student from database by Student id ")
    ResponseEntity deleteById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        logger.warn("Deleting student with ID: " + id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/students")
    @Operation(summary = "Delete Student", description = "Delete Student from database by Student details ")
    ResponseEntity deleteById(@RequestBody Student student) {
        studentService.deleteStudent(student);
        logger.warn("Deleting student : " + student.getFirstName() + " " + student.getName());
        return ResponseEntity.accepted().build();
    }
}
