package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.repository.UserRepository;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("defaultUserService")
public class DefaultUserService implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public StudentDao updateStudentData(StudentDao studentDao)
    {
        return (StudentDao) userRepository.save(studentDao);
    }

    @Override
    public StudentDao findByRegistrationNumber(Integer registrationNumber) {
        return userRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public StudentDao findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
