package com.ultimatesystem.school.services;

import com.ultimatesystem.school.db.StudentRepository;
import com.ultimatesystem.school.dto.StudentDto;
import com.ultimatesystem.school.dto.TeacherDto;
import com.ultimatesystem.school.dto.mappers.StudentMapper;
import com.ultimatesystem.school.dto.mappers.TeacherMapper;
import com.ultimatesystem.school.model.Student;
import com.ultimatesystem.school.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class StudentService {
    private static final int PAGE_SIZE = 20;
    private final StudentRepository studentRepository;

    private StudentMapper studentMapper;
    private TeacherMapper teacherMapper;

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents(int page, Sort.Direction sort) {
        List<StudentDto> studentsList = new ArrayList<>();
        studentRepository
                .findAll(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "name")))
                .forEach(e ->  studentsList.add(studentMapper.toDto(e)));
        return studentsList;
    }

    public Student getStudentByFirstNamAndName(String firstName, String name) {
        if (studentRepository.findByFirstNameAndName(firstName, name) != null) {
            return studentRepository.findByFirstNameAndName(firstName, name);
        } else {
            throw new NoSuchElementException("No student with first name: " + firstName);
        }
    }

    public List<TeacherDto> getTeachersOfStudent(String firstName, String name) {
        if (studentRepository.findByFirstNameAndName(firstName, name) != null) {
            Student student = studentRepository.findByFirstNameAndName(firstName, name);
            List<TeacherDto> teacherList = new ArrayList<>();
            student.getStudentTeachers().forEach(e -> teacherList.add(teacherMapper.toDto(e)));
            return teacherList;
        } else {
            throw new NoSuchElementException("No student with full name: " + firstName + " " + name);
        }
    }

    public Student update(Student updatedStudent) {
        Student student = studentRepository
                .findByFirstNameAndName(updatedStudent.getFirstName(), updatedStudent.getName());
        if (student != null) {
            updatedStudent.setStudentId(student.getStudentId());
            return studentRepository.save(updatedStudent);
        } else {
            throw new NoSuchElementException("No student in database, please add new student");
        }
    }

    public void deleteStudentById(int id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No student with id: " + id);
        }
    }

    public void deleteStudent(Student student) {
        if (studentRepository.findById(student.getStudentId()).isPresent()) {
            studentRepository.deleteById(student.getStudentId());
        } else {
            throw new NoSuchElementException("No student in database");
        }
    }
}
