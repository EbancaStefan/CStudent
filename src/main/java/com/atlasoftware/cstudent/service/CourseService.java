package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.CourseDao;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    List<CourseDao> getAllCourse();
    CourseDao getCourseByID(UUID id);
    CourseDao getCourseByCourseCode(String courseCode);
    void deleteCourseByID(UUID id);
    void deleteAllCourseByID(List<UUID> ids);
    CourseDao updateAndAddCourse(CourseDao course);


}
