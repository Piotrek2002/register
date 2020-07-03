package com.register.register.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDTO {

    private Long id;

    private List<Long> studentsId=new ArrayList<>();

    private List<Long> subjectsId=new ArrayList<>();
    private String className;

    private Long teacherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(List<Long> studentsId) {
        this.studentsId = studentsId;
    }

    public List<Long> getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(List<Long> subjectsId) {
        this.subjectsId = subjectsId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
