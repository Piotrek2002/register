package com.register.register.controller;

import com.register.register.entity.*;
import com.register.register.entity.Class;
import com.register.register.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/techer")
public class TeacherController {
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    public TeacherController(SubjectRepository subjectRepository, ClassRepository classRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }


    @GetMapping("/list")
    @ResponseBody
    public List<TeacherDTO> classList(){

        return teacherRepository.findAll().stream().map(this::teacherDTO).collect(Collectors.toList());
    }
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody TeacherDTO teacherDTO){
        Teacher teacher=new Teacher();
        teacher.setaClass(classRepository.findClassById(teacherDTO.getClassId()));
        teacher.setName(teacherDTO.getName());
        teacher.setSurname(teacherDTO.getSurname());
        teacher.setSubject(subjectRepository.findSubjectById(teacherDTO.getSubjectId()));
        teacherRepository.save(teacher);
        Class aClass=classRepository.findClassById(teacherDTO.getClassId());
        aClass.setTeacher(teacher);
        classRepository.save(aClass);
        return "Nauczyciel został dodany do klas "+teacher.getaClass().getClassName();
    }
    @GetMapping("/find/{id}")
    @ResponseBody
    public TeacherDTO find(@PathVariable Long id){
        Teacher teacher=teacherRepository.findTeacherById(id);
        return teacherDTO(teacher);
    }



    private TeacherDTO teacherDTO(Teacher teacher){
        TeacherDTO teacherDTO=new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setClassId(teacher.getaClass().getId());
        teacherDTO.setSubjectId(teacher.getSubject().getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setSurname(teacher.getSurname());

        return teacherDTO;
    }
}
