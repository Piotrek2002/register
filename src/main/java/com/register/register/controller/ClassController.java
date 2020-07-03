package com.register.register.controller;

import com.register.register.entity.*;
import com.register.register.entity.Class;
import com.register.register.repository.ClassRepository;
import com.register.register.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/class")
public class ClassController {
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    public ClassController(ClassRepository classRepository, TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ClassDTO> classList(){

        return classRepository.findAll().stream().map(this::classDTO).collect(Collectors.toList());
    }
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody ClassDTO classDTO){
        Class aClass=new Class();
        List<Long> teachersId=classRepository.findAll().stream().map(c->c.getTeacher().getId()).collect(Collectors.toList());
        for (Long id: teachersId){
            if (id.equals(classDTO.getTeacherId())){
                return "ten nauczuciel jest już wychowawcą";
            }
        }
        aClass.setTeacher(teacherRepository.findTeacherById(classDTO.getTeacherId()));
        aClass.setClassName(classDTO.getClassName());
        classRepository.save(aClass);
        return "Utworzono klase";
    }
    @GetMapping("/find/{id}")
    @ResponseBody
    public ClassDTO find(@PathVariable Long id){
        Class aClass=classRepository.findClassById(id);
        return classDTO(aClass);
    }


    private ClassDTO classDTO(Class aClass){
        ClassDTO classDTO=new ClassDTO();
        classDTO.setId(aClass.getId());
        classDTO.setTeacherId(aClass.getTeacher().getId());
        classDTO.setClassName(aClass.getClassName());
        classDTO.setStudentsId(aClass.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        classDTO.setSubjectsId(aClass.getSubjects().stream().map(Subject::getId).collect(Collectors.toList()));
        return classDTO;
    }


}
