package com.register.register.repository;

import com.register.register.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
}
