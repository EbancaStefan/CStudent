package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.CourseType;
import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.domain.ExamType;
import com.atlasoftware.cstudent.dto.CourseDto;
import com.atlasoftware.cstudent.dto.ExamDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class ExamConverter {
    public static ExamDao convertDtoToModel(ExamDto examDto) {
        return new ExamDao(UUID.fromString(examDto.getId()), examDto.getDate(), examDto.getCourseCode(), ExamType.valueOf(examDto.getExamType()), examDto.getFormula());
    }
    public static ExamDto convertModelToDto(ExamDao examDao) {
        return new ExamDto(examDao.getId().toString(), examDao.getDate(), examDao.getCourseCode(), examDao.getExamType().toString(), examDao.getFormula());
    }
}
