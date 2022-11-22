package com.ultimatesystem.school.db;

import com.ultimatesystem.school.model.Student;
import com.ultimatesystem.school.model.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {
    Teacher findByFirstNameAndName(String firstName, String name);



}
