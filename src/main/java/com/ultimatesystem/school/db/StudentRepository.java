package com.ultimatesystem.school.db;

import com.ultimatesystem.school.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    Student findByFirstNameAndName(String firstName, String name);

}
