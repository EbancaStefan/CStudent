package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.*;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;
import com.atlasoftware.cstudent.utils.LoginForm;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service("defaultCStudentService")
public class DefaultCStudentService implements CStudentService{
    @Autowired
    CourseActivityService courseActivityService;
    @Autowired
    CourseService courseService;
    @Autowired
    LocationService locationService;
    @Autowired
    DataInitialisationService dataInitialisationService;
    @Autowired
    TimeTableService timeTableService;
    @Autowired
    UserService userService;
    @Autowired
    ExamService examService;
    @Autowired
    LoginService loginService;
    @Autowired
    StudentRegistrationService studentRegistrationService;

    @Override
    public List<CourseActivityDao> getAllCourseActivities() { return courseActivityService.getAllCourseActivities(); }

    @Override
    public CourseActivityDao getCourseActivityByID(UUID id) { return courseActivityService.getCourseActivityByID(id); }

    @Override
    public void deleteCourseActivityByID(UUID id) { courseActivityService.deleteCourseActivityByID(id); }

    @Override
    public void deleteAllCourseActivityByID(List<UUID> ids)
    { courseActivityService.deleteAllCourseActivityByID(ids); }

    @Override
    public CourseActivityDao updateAndAddCourseActivityByID(CourseActivityDao courseActivity)
    { return courseActivityService.updateAndAddCourseActivity(courseActivity); }

    @Override
    public CourseDao getCourseByID(UUID id){ return courseService.getCourseByID(id); }

    @Override
    public List<CourseDao> getAllCourse() { return courseService.getAllCourse(); }

    @Override
    public void deleteCourseByID(UUID id) { courseService.deleteCourseByID(id); }

    @Override
    public void deleteAllCourseByID(List<UUID> ids) { courseService.deleteAllCourseByID(ids); }

    @Override
    public CourseDao updateAndAddCourseByID(CourseDao course) { return courseService.updateAndAddCourse(course); }

    @Override
    public List<LocationDao> getAllLocations() { return locationService.getAllLocations(); }

    @Override
    public LocationDao getLocationByID(UUID id) { return locationService.getLocationByID(id); }

    @Override
    public void deleteLocationByID(UUID id) { locationService.deleteLocationByID(id); }

    @Override
    public void deleteAllLocationByID(List<UUID> ids) { locationService.deleteAllLocationByID(ids); }

    @Override
    public LocationDao updateAndAddLocation(LocationDao location) { return locationService.updateAndAddLocation(location); }

    @Override
    public void initialiseData() throws IOException {
        dataInitialisationService.initialiseData();
    }

    @Override
    public List<CourseActivityDao> deliverTimeTable(StudentDao studentDao) throws TimeTableException {
        return timeTableService.deliverTimeTable(studentDao);
    }

    @Override
    public StudentDao findByRegistrationNumber(Integer registrationNumber) {
        return userService.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public List<ExamDao> getAllExam() {
        return examService.getAllExam();
    }

    @Override
    public ExamDao getExamByID(UUID id) {
        return examService.getExamByID(id);
    }

    @Override
    public void deleteExamByID(UUID id) {
        examService.deleteExamByID(id);
    }

    @Override
    public void deleteAllExamByID(List<UUID> ids) {
        examService.deleteAllExamByID(ids);
    }

    @Override
    public ExamDao updateAndAddExam(ExamDao examDao) {
        return examService.updateAndAddExam(examDao);
    }

    @Override
    public Boolean validateLoginForm(LoginForm loginForm) {
        return loginService.validateLoginForm(loginForm);
    }

    @Override
    public Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {
        return studentRegistrationService.validateRegistrationForm(studentRegistrationForm);
    }

}
