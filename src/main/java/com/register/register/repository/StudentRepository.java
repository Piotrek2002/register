package com.register.register.repository;

import com.register.register.entity.Class;
import com.register.register.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.id=?1")
    Student findStudentById(Long id);
}
