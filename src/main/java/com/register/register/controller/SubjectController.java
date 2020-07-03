package com.register.register.controller;

import com.register.register.entity.*;
import com.register.register.entity.Class;
import com.register.register.repository.MarkRepository;
import com.register.register.repository.SubjectRepository;
import com.register.register.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectRepository subjectRepository;


    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;

    }
    @GetMapping("/list")
    @ResponseBody
    public List<SubjectDTO> classList(){

        return subjectRepository.findAll().stream().map(this::subjectDTO).collect(Collectors.toList());
    }
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody SubjectDTO subjectDTO){
        Subject subject=new Subject();
        subject.setName(subjectDTO.getName());
        subjectRepository.save(subject);
        return "Przedmiot został dodany "+subject.getName();
    }
    @GetMapping("/find/{id}")
    @ResponseBody
    public SubjectDTO find(@PathVariable Long id){
        Subject subject=subjectRepository.findSubjectById(id);
        return subjectDTO(subject);
    }



    private SubjectDTO subjectDTO(Subject subject){
        SubjectDTO subjectDTO=new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setMarksId(subject.getMarks().stream().map(Mark::getId).collect(Collectors.toList()));
        subjectDTO.setTeacherId(subject.getTeacher().getId());
        subjectDTO.setName(subject.getName());


        return subjectDTO;
    }
}
