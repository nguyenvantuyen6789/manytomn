package com.tuyennguyen.supeo.forum.controller;

import com.tuyennguyen.supeo.forum.dto.SubjectDTO;
import com.tuyennguyen.supeo.forum.entity.Student;
import com.tuyennguyen.supeo.forum.entity.Subject;
import com.tuyennguyen.supeo.forum.repository.StudentRepository;
import com.tuyennguyen.supeo.forum.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin2/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private StudentRepository studentRepo;

    // add a subject
    @PostMapping
    public ResponseEntity<?> add(@RequestBody String subjectName) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subjectRepo.save(subject);
        return new ResponseEntity<>("Add subject successful", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer subjectId, @RequestBody String subjectName) {
        Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
        if (optionalSubject.isPresent()) {
            Subject updateSubject = optionalSubject.get();
            updateSubject.setSubjectName(subjectName);
            subjectRepo.save(updateSubject);
        }
        return new ResponseEntity<>("Update subject successful", HttpStatus.OK);
    }

    // delete a subject
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer subjectId) {
        Optional<Subject> optionalDelete = subjectRepo.findById(subjectId);
        if (optionalDelete.isPresent()) {
            List<Student> students = studentRepo.findAll();
            if (!CollectionUtils.isEmpty(students)) {
                for (Student student : students) {
                    if (student != null && !CollectionUtils.isEmpty(student.getSubjects())) {
                        List<Subject> subjects = student.getSubjects();
                        subjects.removeIf(s -> s.getSubjectId() == subjectId);
                        student.setSubjects(subjects);
                        studentRepo.save(student);
                    }
                }
            }
            subjectRepo.delete(optionalDelete.get());
        }
        return new ResponseEntity<>("Delete subject successful", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllSubject() {
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        List<Subject> subjects = subjectRepo.findAll();
        if (!CollectionUtils.isEmpty(subjects)) {
            for (Subject subject : subjects) {
                if (subject != null) {
                    SubjectDTO subjectDTO = new SubjectDTO();
                    subjectDTO.setId(subject.getSubjectId());
                    subjectDTO.setName(subject.getSubjectName());
                    subjectDTOS.add(subjectDTO);
                }
            }
        }
        return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable(name = "id") Integer subjectId) {
        SubjectDTO subjectDTO = new SubjectDTO();
        Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
        Subject subject = optionalSubject.get();
        if (subject != null) {
            subjectDTO.setId(subject.getSubjectId());
            subjectDTO.setName(subject.getSubjectName());
        }
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

}
