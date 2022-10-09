package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.dto.StudentDto;

import java.util.stream.Collectors;

public class StudentConverter {
    public static StudentDao convertDtoToModel(StudentDto studentDto) {
        return new StudentDao(studentDto.getGroup(), studentDto.getRegistrationNumber(), studentDto.isSemigroup(),
                LocationConverter.convertDtoToModel(studentDto.getLocation()), RoomConverter.convertDtoToModel(studentDto.getRoom()), studentDto.getCourses(), studentDto.getTimeTable().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()), studentDto.getSection());
    }
    public static StudentDto convertModelToDto(StudentDao studentDao) {
        return new StudentDto(studentDao.getGroup(), studentDao.getRegistrationNumber(), studentDao.getSemigroup(),
                LocationConverter.convertModelToDto(studentDao.getLocationDao()), RoomConverter.convertModelToDto(studentDao.getRoomDao()), studentDao.getCourses(), studentDao.getTimeTable().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()), studentDao.getSection());
    }
}
