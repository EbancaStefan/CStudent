package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.domain.ProfessorTitle;
import com.atlasoftware.cstudent.dto.ProfessorDto;

import java.util.stream.Collectors;

public class ProfessorConverter {
    public static ProfessorDao convertDtoToModel(ProfessorDto professorDto) {
        return new ProfessorDao(ProfessorTitle.valueOf(professorDto.getTitle()), professorDto.getPersonalWebsite(), professorDto.getCourseActivities().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()));
    }
    public static ProfessorDto convertModelToDto(ProfessorDao professorDao) {
        return new ProfessorDto(professorDao.getTitle().toString(), professorDao.getPersonalWebsite(), professorDao.getCourseActivities().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()));
    }
}