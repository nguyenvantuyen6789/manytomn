package com.tuyennguyen.supeo.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO implements Serializable {
    private Integer id;
    private String name;
}
