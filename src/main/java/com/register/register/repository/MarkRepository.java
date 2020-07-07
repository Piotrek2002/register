package com.register.register.repository;

import com.register.register.entity.Class;
import com.register.register.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query("select m from Mark m where m.id=?1")
    Mark findMarkById(Long id);
    @Query("select m from Mark m where m.student.id=?1 and m.subject.id=?2")
    List<Mark> findMarkByStudentIdAndSubjectId(Long studentId,Long subjectId);
}
