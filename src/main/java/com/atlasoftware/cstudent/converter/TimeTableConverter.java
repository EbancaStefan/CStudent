package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.TimeTableDao;
import com.atlasoftware.cstudent.dto.TimeTableDto;

import java.util.UUID;

public class TimeTableConverter {
    public static TimeTableDao convertDtoToModel(TimeTableDto timeTableDto) {
        return new TimeTableDao(UUID.fromString(timeTableDto.getId()), StudentConverter.convertDtoToModel(timeTableDto.getStudent()), CourseActivityConverter.convertDtoToModel(timeTableDto.getCourseActivity()));
    }
    public static TimeTableDto convertModelToDto(TimeTableDao timeTableDao) {
        return new TimeTableDto(timeTableDao.getId().toString(), StudentConverter.convertModelToDto(timeTableDao.getStudent()), CourseActivityConverter.convertModelToDto(timeTableDao.getCourseActivity()));
    }
}