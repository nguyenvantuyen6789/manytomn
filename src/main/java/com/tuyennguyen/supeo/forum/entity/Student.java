package com.tuyennguyen.supeo.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String studentName;

//    @Fetch(value = FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "tbl_student_subject", schema = "supeo_forum",
//            joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "subject_id")})
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Subject> subjects = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_student_subject",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST})
    private List<Subject> subjects = new ArrayList<>();

}
