package com.atlasoftware.cstudent.controller;

import com.atlasoftware.cstudent.converter.ExamConverter;
import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.dto.ExamDto;
import com.atlasoftware.cstudent.service.CStudentService;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.utils.LoginForm;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CStudentController {
    @Autowired
    CStudentService cStudentService;


    @RequestMapping(value = "/load", method = RequestMethod.GET)
    private void loadData() {
        try {
            cStudentService.initialiseData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/timetable/{registrationNumber}", method = RequestMethod.GET)
    private ResponseEntity<Object> getTimeTable(@PathVariable Integer registrationNumber) {
        try {
            StudentDao student = cStudentService.findByRegistrationNumber(registrationNumber);
            List<CourseActivityDao> courseActivities = cStudentService.deliverTimeTable(student);
            courseActivities.forEach(courseActivityDao -> {
                courseActivityDao.setCourseDao(null);
            });
            return new ResponseEntity<>(courseActivities, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private ResponseEntity<Object> validateLoginForm(@RequestBody LoginForm loginForm){
        if(cStudentService.validateLoginForm(loginForm)){
            return new ResponseEntity<>("Successful Login", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addExam", method = RequestMethod.POST)
    private ResponseEntity<Object> addExam(@RequestBody ExamDto examDto) {
        cStudentService.updateAndAddExam(ExamConverter.convertDtoToModel(examDto));
        return new ResponseEntity<>("Exam added successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteExam/{examId}", method = RequestMethod.DELETE)
    private ResponseEntity<Object> deleteExam(@PathVariable String examId) {
        if (cStudentService.getExamByID(UUID.fromString(examId)) != null) {
            cStudentService.deleteExamByID(UUID.fromString(examId));
            return new ResponseEntity<>("Exam deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Exam doesnt exist", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateExam", method = RequestMethod.POST)
    private ResponseEntity<Object> updateExam(@RequestBody ExamDto examDto) {
        cStudentService.updateAndAddExam(ExamConverter.convertDtoToModel(examDto));
        return new ResponseEntity<>("Exam updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private ResponseEntity<Object> registerStudent(@RequestBody StudentRegistrationForm regDto)
    {
        try
        {
            cStudentService.validateRegistrationForm(regDto);
            return new ResponseEntity<>("Successfully created your account!", HttpStatus.OK);
        } catch(StudentRegistrationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllLocations()
    {
        return new ResponseEntity<>(cStudentService.getAllLocations(), HttpStatus.OK);
    }
}
