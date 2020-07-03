package com.register.register.repository;

import com.register.register.entity.Student;
import com.register.register.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("select s from Subject s where s.id=?1")
    Subject findSubjectById(Long id);
}
