package com.atlasoftware.cstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto {
    private String id;
    private String name;
    private String address;
    private String latLong;
    private String type;
    private int totalRoomNumberByFloor;
    private int totalNumberOfFloors;
    private List<StudentDto> students;
}