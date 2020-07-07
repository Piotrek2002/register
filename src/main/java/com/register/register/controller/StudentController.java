package com.register.register.controller;

import com.register.register.entity.*;
import com.register.register.repository.ClassRepository;
import com.register.register.repository.MarkRepository;
import com.register.register.repository.StudentRepository;
import com.register.register.repository.SubjectRepository;
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
    private final SubjectRepository subjectRepository;

    public StudentController(StudentRepository studentRepository, MarkRepository markRepository, ClassRepository classRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<StudentDTO> classList() {

        return studentRepository.findAll().stream().map(this::studentDTO).collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setaClass(classRepository.findClassById(studentDTO.getClassId()));
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        studentRepository.save(student);
        return "Uczeń został dodany do klas " + student.getaClass().getClassName();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public StudentDTO find(@PathVariable Long id) {
        Student student = studentRepository.findStudentById(id);
        return studentDTO(student);
    }

    @GetMapping("/average/{studentId}/{subjectId}")
    @ResponseBody
    public Double average(@PathVariable Long studentId, @PathVariable Long subjectId) {
        List<Mark> marks = markRepository.findMarkByStudentIdAndSubjectId(studentId, subjectId);
        double sum = 0;
        for (Mark mark : marks) {
            sum += mark.getValue();
        }
        return sum / marks.size();
    }

    @GetMapping("/average/{studentId}")
    @ResponseBody
    public Double averageAll(@PathVariable Long studentId) {
        List<Subject> subjects = subjectRepository.findAll();
        double sumAverage = 0;
        double sum=0;
        double s=0;
        for (Subject subject : subjects) {
            List<Mark> marks = markRepository.findMarkByStudentIdAndSubjectId(studentId, subject.getId());
            for (Mark mark : marks) {
                sum += mark.getValue();
            }
            s=sum / marks.size();
            sumAverage+= s;
        }

        return sumAverage/subjects.size();
    }


    private StudentDTO studentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setClassId(student.getaClass().getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setMarksId(student.getMarks().stream().map(Mark::getId).collect(Collectors.toList()));
        return studentDTO;
    }


}
