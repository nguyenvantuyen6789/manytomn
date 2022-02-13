package com.tuyennguyen.supeo.forum.repository;

import com.tuyennguyen.supeo.forum.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT u FROM Student u")
    public List<Student> getListStudent();
}
