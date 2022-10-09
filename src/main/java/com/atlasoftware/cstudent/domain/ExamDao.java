package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "exam")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String date;
    @Column(name = "course_code")
    private String courseCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;
    private String formula;
}
