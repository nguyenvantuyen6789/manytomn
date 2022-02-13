package com.tuyennguyen.supeo.forum.controller;

import com.tuyennguyen.supeo.forum.dto.StudentAndSubjectDTO;
import com.tuyennguyen.supeo.forum.dto.StudentDTO;
import com.tuyennguyen.supeo.forum.dto.SubjectDTO;
import com.tuyennguyen.supeo.forum.entity.Student;
import com.tuyennguyen.supeo.forum.entity.Subject;
import com.tuyennguyen.supeo.forum.repository.StudentRepository;
import com.tuyennguyen.supeo.forum.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin2/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    // add a student
    @PostMapping
    public ResponseEntity<?> add(@RequestBody String studentName) {
        Student student = new Student();
        student.setStudentName(studentName);
        studentRepo.save(student);
        return new ResponseEntity<>("Add student successful", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer studentId, @RequestBody String studentName) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student updateStudent = optionalStudent.get();
            updateStudent.setStudentName(studentName);
            studentRepo.save(updateStudent);
        }
        return new ResponseEntity<>("Update student successful", HttpStatus.OK);
    }

    // delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer studentId) {
        Optional<Student> optionalDelete = studentRepo.findById(studentId);
        if (optionalDelete.isPresent()) {
            studentRepo.delete(optionalDelete.get());
        }
        return new ResponseEntity<>("Delete student successful", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllStudent() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentRepo.findAll();
        if (!CollectionUtils.isEmpty(students)) {
            for (Student student : students) {
                if (student != null) {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setId(student.getStudentId());
                    studentDTO.setStudentName(student.getStudentName());
                    if (!CollectionUtils.isEmpty(student.getSubjects())) {
                        List<SubjectDTO> subjectDTOS = new ArrayList<>();
                        for (Subject subject : student.getSubjects()) {
                            if (subject != null) {
                                SubjectDTO subjectDTO = new SubjectDTO();
                                subjectDTO.setId(subject.getSubjectId());
                                subjectDTO.setName(subject.getSubjectName());
                                subjectDTOS.add(subjectDTO);
                            }
                        }
                        studentDTO.setSubjects(subjectDTOS);
                    }
                    studentDTOS.add(studentDTO);
                }
            }
        }
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable(name = "id") Integer studentId) {
        StudentDTO studentDTO = new StudentDTO();
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Student student = optionalStudent.get();
        if (student != null) {
            studentDTO.setId(student.getStudentId());
            studentDTO.setStudentName(student.getStudentName());
            if (!CollectionUtils.isEmpty(student.getSubjects())) {
                List<SubjectDTO> subjectDTOS = new ArrayList<>();
                for (Subject subject : student.getSubjects()) {
                    if (subject != null) {
                        SubjectDTO subjectDTO = new SubjectDTO();
                        subjectDTO.setId(subject.getSubjectId());
                        subjectDTO.setName(subject.getSubjectName());
                        subjectDTOS.add(subjectDTO);
                    }
                }
                studentDTO.setSubjects(subjectDTOS);
            }
        }
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/subject/{id}")
    public ResponseEntity<?> addSubjectToStudent(@PathVariable(name = "id") Integer subjectId, @RequestParam Integer studentId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
            if (optionalSubject.isPresent()) {
                List<Subject> subjects = student.getSubjects();
                if (subjects == null) {
                    subjects = new ArrayList<>();
                }
                subjects.add(optionalSubject.get());
                student.setSubjects(subjects);
            }
            studentRepo.save(student);
        }
        return new ResponseEntity<>("Add subject to student successful", HttpStatus.OK);
    }

    @DeleteMapping(value = "/subject/{id}")
    public ResponseEntity<?> removeSubjectOfStudent(@PathVariable(name = "id") Integer subjectId, @RequestParam Integer studentId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
            if (optionalStudent.isPresent()) {
                List<Subject> subjects = student.getSubjects();
                if (subjects == null) {
                    subjects = new ArrayList<>();
                }
                subjects.removeIf(s -> s.getSubjectId() == optionalSubject.get().getSubjectId());
                student.setSubjects(subjects);
            }
            studentRepo.save(student);
        }
        return new ResponseEntity<>("Remove subject to student successful", HttpStatus.OK);
    }

    // Lưu 1 Student với nhiều Subject
    @PostMapping(value = "/add")
    public ResponseEntity<?> AddStudentAndSubject(@RequestBody StudentAndSubjectDTO studentAndSubjectDTO) {
        Subject subject = new Subject();
        subject.setSubjectName(studentAndSubjectDTO.getSubjectName());
        subject = subjectRepo.save(subject);

        Subject subject2 = new Subject();
        subject2.setSubjectName("Subject 25");
        subject2 = subjectRepo.save(subject2);

        Subject subject3 = new Subject();
        subject3.setSubjectName("Subject 26");
        subject3 = subjectRepo.save(subject3);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        subjects.add(subject2);
        subjects.add(subject3);

        Student student = new Student();
        student.setStudentName(studentAndSubjectDTO.getStudentName());
        student.setSubjects(subjects);
        studentRepo.save(student);
        return new ResponseEntity<>("Add student and subject successful", HttpStatus.OK);
    }


    // Lưu 1 Student với nhiều Subject
    @PostMapping(value = "/add2")
    public ResponseEntity<?> AddStudentAndSubject2() {
        Student student = studentRepo.findById(5).get();
        Subject subject = subjectRepo.findById(10).get();
        System.out.println(student);
        List<Subject> subjects = student.getSubjects();
        subjects.add(subject);
        student.setSubjects(subjects);
//
//        List<Subject> subjects = new ArrayList<Subject>();
//        subjects.add(subject);
//
//        student.setSubjects(subjects);
//
        studentRepo.save(student);
        return new ResponseEntity<>("Add student and subject successful", HttpStatus.OK);
    }

}
