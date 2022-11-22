package com.ultimatesystem.school.services;

import com.ultimatesystem.school.db.TeacherRepository;
import com.ultimatesystem.school.dto.StudentDto;
import com.ultimatesystem.school.dto.TeacherDto;
import com.ultimatesystem.school.dto.mappers.StudentMapper;
import com.ultimatesystem.school.dto.mappers.TeacherMapper;
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
public class TeacherService {

    private final int PAGE_SIZE = 10;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<TeacherDto> getAllTeachers(int page, Sort.Direction sort) {
        List<TeacherDto> teacherList = new ArrayList<>();
        teacherRepository
                .findAll(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "name")))
                .forEach(e -> teacherList.add(teacherMapper.toDto(e)));
        return teacherList;
    }

    public Teacher getTeacherByFirstNameAndName(String firstName, String name) {
        if (teacherRepository.findByFirstNameAndName(firstName, name) != null) {
            return teacherRepository.findByFirstNameAndName(firstName, name);
        } else {
            throw new NoSuchElementException("No teacher with full name: " + firstName + " " + name);
        }
    }

    public List<StudentDto> getStudentsOfTeacher(String firstName, String name) {
        if (teacherRepository.findByFirstNameAndName(firstName, name) != null) {
            Teacher teacher = teacherRepository.findByFirstNameAndName(firstName, name);
            List<StudentDto> studentsList = new ArrayList<>();
            teacher.getTeacherStudents().forEach(e -> studentsList.add(studentMapper.toDto(e)));
            return studentsList;
        } else {
            throw new NoSuchElementException("No teacher with full name: " + firstName + " " + name);
        }
    }

    public Teacher update(Teacher updatedTeacher) {
        Teacher teacher = teacherRepository.findByFirstNameAndName(updatedTeacher.getFirstName(), updatedTeacher.getName());
        if (teacher != null) {
            updatedTeacher.setTeacherId(teacher.getTeacherId());
            return teacherRepository.save(updatedTeacher);
        } else {
            throw new NoSuchElementException("No teacher in database, please add new teacher");
        }
    }

    public void deleteTeacherById(int id) {
        if (teacherRepository.findById(id).isPresent()) {
            teacherRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No teacher with id: " + id);
        }
    }

    public void deleteTeacher(Teacher teacher) {
        if (teacherRepository.findById(teacher.getTeacherId()).isPresent()) {
            teacherRepository.deleteById(teacher.getTeacherId());
        } else {
            throw new NoSuchElementException("No teacher in database");
        }
    }
}
