package com.register.register.repository;

import com.register.register.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("select t from Teacher t where t.id=?1")
    Teacher findTeacherById(Long id);
}
