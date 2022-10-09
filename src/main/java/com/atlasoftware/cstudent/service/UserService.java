package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;

import java.util.List;
import java.util.UUID;

public interface UserService {

    StudentDao updateStudentData(StudentDao studentDao);
    StudentDao findByRegistrationNumber(Integer registrationNumber);
    StudentDao findByEmail(String email);
}
