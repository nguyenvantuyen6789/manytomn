package com.tuyennguyen.supeo.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAndSubjectDTO implements Serializable {
    private String studentName;
    private String subjectName;
}
