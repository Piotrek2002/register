package com.register.register.repository;

import com.register.register.entity.Class;
import com.register.register.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query("select m from Mark m where m.id=?1")
    Mark findMarkById(Long id);
}
