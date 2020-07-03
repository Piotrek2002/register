package com.register.register.controller;

import com.register.register.entity.*;
import com.register.register.entity.Class;
import com.register.register.repository.ClassRepository;
import com.register.register.repository.MarkRepository;
import com.register.register.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;
    private final ClassRepository classRepository;

    public StudentController(StudentRepository studentRepository, MarkRepository markRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
        this.classRepository = classRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<StudentDTO> classList(){

        return studentRepository.findAll().stream().map(this::studentDTO).collect(Collectors.toList());
    }
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody StudentDTO studentDTO){
        Student student=new Student();
        student.setaClass(classRepository.findClassById(studentDTO.getClassId()));
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        studentRepository.save(student);
        return "Uczeń został dodany do klas "+student.getaClass().getClassName();
    }
    @GetMapping("/find/{id}")
    @ResponseBody
    public StudentDTO find(@PathVariable Long id){
        Student student=studentRepository.findStudentById(id);
        return studentDTO(student);
    }



    private StudentDTO studentDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setClassId(student.getaClass().getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setMarksId(student.getMarks().stream().map(Mark::getId).collect(Collectors.toList()));
        return studentDTO;
    }
}
