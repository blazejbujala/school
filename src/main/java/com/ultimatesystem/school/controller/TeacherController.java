package com.ultimatesystem.school.controller;

import com.ultimatesystem.school.dto.StudentDto;
import com.ultimatesystem.school.model.Teacher;
import com.ultimatesystem.school.services.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @PostMapping("/teachers")
    @Operation(summary = "Add new Teacher", description = "Add new Teachers to the database")
    ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        logger.info("Adding teacher " + teacher.getFirstName() + teacher.getName());
        return ResponseEntity.ok(teacherService.add(teacher));
    }

    @GetMapping("/teachers")
    @Operation(summary = "Get all Teachers", description = "Get all Teachers list with details")
    ResponseEntity<?> readAllTeachers(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        logger.warn("Exposing all Teachers");
        return ResponseEntity.ok(teacherService.getAllTeachers(pageNumber, sort));
    }

    @GetMapping("/teachers/{firstName}/{name}")
    @Operation(summary = "Get Teacher", description = "Get Teacher details by first name and name")
    ResponseEntity<Teacher> findTeacherByFirstNameAndName(@PathVariable String firstName, @PathVariable String name) {
        logger.info("Getting teacher with full name " + firstName + " " + name);
        return ResponseEntity.ok(teacherService.getTeacherByFirstNameAndName(firstName, name));
    }

    @GetMapping("/teachers/students")
    @Operation(summary = "Get list of all Teacher's students", description = "Get all Students of a Teacher by first name and name")
    ResponseEntity<List<StudentDto>> findStudentsOfTeacher(@RequestParam(required = true) String firstName, String name) {
        logger.info("Getting all students of a teacher with full name " + firstName + " " + name);
        return ResponseEntity.ok(teacherService.getStudentsOfTeacher(firstName, name));
    }


    @PatchMapping("/teachers")
    @Operation(summary = "Update Teacher", description = "Update Teacher details in database")
    ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher){
        logger.info("Updating teacher with ID: " + teacher.getTeacherId());
        return ResponseEntity.ok(teacherService.update(teacher));
    }

    @DeleteMapping("/teachers/{id}")
    @Operation(summary = "Delete Teacher", description = "Delete Teacher from database by Teacher's id ")
    ResponseEntity deleteById(@PathVariable int id) {
        teacherService.deleteTeacherById(id);
        logger.warn("Deleting teacher with ID: " + id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/teachers")
    @Operation(summary = "Delete Teacher", description = "Delete Teacher from database by Teacher details ")
    ResponseEntity deleteTeacher(@RequestBody Teacher teacher) {
        teacherService.deleteTeacher(teacher);
        logger.warn("Deleting teacher : " + teacher.getFirstName() + " " + teacher.getName() );
        return ResponseEntity.accepted().build();
    }
}
