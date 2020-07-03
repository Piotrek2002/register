package com.register.register.repository;

import com.register.register.entity.Class;
import com.register.register.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    @Query("select c from Class c where c.id=?1")
    Class findClassById(Long id);
}
