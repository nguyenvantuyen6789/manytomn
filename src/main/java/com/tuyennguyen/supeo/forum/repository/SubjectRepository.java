package com.tuyennguyen.supeo.forum.repository;

import com.tuyennguyen.supeo.forum.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
