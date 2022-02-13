package com.tuyennguyen.supeo.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {

    private Integer id;
    private String studentName;
    private List<SubjectDTO> subjects;
}
