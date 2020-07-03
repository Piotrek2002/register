package com.register.register.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private Long id;

    private Long ClassId;

    private List<Long> marksId=new ArrayList<>();

    private String name;

    private String surname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return ClassId;
    }

    public void setClassId(Long classId) {
        ClassId = classId;
    }

    public List<Long> getMarksId() {
        return marksId;
    }

    public void setMarksId(List<Long> marksId) {
        this.marksId = marksId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
