package com.register.register.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubjectDTO {

    private Long id;
    private List<Long> marksId=new ArrayList<>();
    private Long teacherId;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMarksId() {
        return marksId;
    }

    public void setMarksId(List<Long> marksId) {
        this.marksId = marksId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
