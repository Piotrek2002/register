package com.register.register.controller;

import com.register.register.entity.Mark;
import com.register.register.entity.MarkDTO;
import com.register.register.repository.MarkRepository;
import com.register.register.repository.StudentRepository;
import com.register.register.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mark")
public class MarkController {
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public MarkController(MarkRepository markRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<MarkDTO> classList(){

        return markRepository.findAll().stream().map(this::markDTO).collect(Collectors.toList());
    }

    private MarkDTO markDTO(Mark mark){
        MarkDTO markDTO= new MarkDTO();
        markDTO.setId(mark.getId());
        markDTO.setStudentId(mark.getStudent().getId());
        markDTO.setSubjectId(mark.getSubject().getId());
        markDTO.setValue(mark.getValue());
        return markDTO;
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public MarkDTO find(@PathVariable Long id){
        Mark mark=markRepository.findMarkById(id);
        return markDTO(mark);
    }
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody MarkDTO markDTO){
        Mark mark=new Mark();
        mark.setStudent(studentRepository.findStudentById(markDTO.getStudentId()));
        mark.setSubject(subjectRepository.findSubjectById(markDTO.getSubjectId()));
        mark.setValue(markDTO.getValue());
        markRepository.save(mark);
        return "Uczeń "+mark.getStudent().getName()+" "+mark.getStudent().getSurname()+" otrzymał "+mark.getValue();
    }
}
