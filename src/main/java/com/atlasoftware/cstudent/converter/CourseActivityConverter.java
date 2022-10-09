package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.ActivityType;
import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.dto.CourseActivityDto;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CourseActivityConverter {
    public static CourseActivityDao convertDtoToModel(CourseActivityDto courseActivityDto) {
        return new CourseActivityDao(UUID.fromString(courseActivityDto.getId()),ActivityType.valueOf(courseActivityDto.getActivityType()),ProfessorConverter.convertDtoToModel(courseActivityDto.getProfessor()),CourseConverter.convertDtoToModel(courseActivityDto.getCourse()),courseActivityDto.getCourseCode(), courseActivityDto.getCourseName(),courseActivityDto.getFormula(),
                LocalTime.parse(courseActivityDto.getStartTime()), LocalTime.parse(courseActivityDto.getEndTime()), DayOfWeek.valueOf(courseActivityDto.getDay()),RoomConverter.convertDtoToModel(courseActivityDto.getRoom()),courseActivityDto.isWeekly(),courseActivityDto.isWeek(),courseActivityDto.isOnline());
    }
    public static CourseActivityDto convertModelToDto(CourseActivityDao courseActivityDao) {
        return new CourseActivityDto(String.valueOf(courseActivityDao.getId()),String.valueOf(courseActivityDao.getActivityType()),ProfessorConverter.convertModelToDto(courseActivityDao.getProfessorDao()),CourseConverter.convertModelToDto(courseActivityDao.getCourseDao()),courseActivityDao.getCourseCode(), courseActivityDao.getCourseName(),
                courseActivityDao.getFormula(),courseActivityDao.getStartTime().toString(),courseActivityDao.getEndTime().toString(),courseActivityDao.getDay().toString(),RoomConverter.convertModelToDto(courseActivityDao.getRoomDao()),courseActivityDao.getWeekly(), courseActivityDao.getWeek(), courseActivityDao.getOnline());
    }
}
