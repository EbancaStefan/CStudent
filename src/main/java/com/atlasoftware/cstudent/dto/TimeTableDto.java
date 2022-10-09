package com.atlasoftware.cstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeTableDto {
    private String id;
    private StudentDto student;
    private CourseActivityDto courseActivity;
}