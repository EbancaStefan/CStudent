package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("defaultStudentRegistrationService")
public class DefaultStudentRegistrationService implements StudentRegistrationService{


    @Autowired
    DefaultUserService userService;
    @Autowired
    DefaultLocationService locationRepositoryService;
    @Autowired
    DefaultRoomService roomRepositoryService;



    private Boolean validateEmailByRegex(String email) {

        Pattern pattern = Pattern.compile("([A-Za-z]+[.]{1}+[A-Za-z]+)+@stud.ubbcluj.ro");
        Matcher matcher = pattern.matcher(email);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateEmailByName(String firstName, String lastName, String email) {
        String[] parts = email.split("@");
        String[] names = parts[0].split("\\.");
        Boolean validLastName = false;
        if (names[names.length - 1].equals(lastName))
            validLastName = true;
        if (validLastName == false)
            return false;
        for (int i = 0; i <= names.length - 2; i++) {
            if (firstName.contains(names[i]) == false) {
                return false;
            }
        }

        return true;
    }

    private Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(password);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateRegistrationNumber(Integer registrationNumber) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(registrationNumber.toString());
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateRoomNumber(Integer roomNumber) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(roomNumber.toString());
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateLocation(String studentHousingName, Integer roomNumber) throws StudentRegistrationException {

        if (validateRoomNumber(roomNumber) == false) {
            throw new StudentRegistrationException("Numarul camerei nu exista!");
        }
        LocationDao location = locationRepositoryService.findByLocationName(studentHousingName);
        if (location != null) {
            if (roomNumber / 100 <= location.getTotalNumberOfFloors() && roomNumber>0) {
                if (roomNumber % 100 <= location.getTotalRoomNumberByFloor() - 1) {
                    return true;
                }
                throw new StudentRegistrationException("Numarul camerei nu exista!");
            }
            throw new StudentRegistrationException("Numarul camerei nu exista!");

        }
        throw new StudentRegistrationException("Caminul nu exista!");
    }


    private Boolean basicDataValidation(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {

        if (validateEmailByRegex(studentRegistrationForm.getEmail())) {
            if (validateEmailByName(studentRegistrationForm.getFirstName(), studentRegistrationForm.getLastName(), studentRegistrationForm.getEmail())) {
                if (validatePassword(studentRegistrationForm.getPassword())) {
                    if (validateRegistrationNumber(studentRegistrationForm.getRegistrationNumber())) {
                        if (studentRegistrationForm.getStudentHousingName() != null) {
                            if (validateLocation(studentRegistrationForm.getStudentHousingName(), studentRegistrationForm.getRoomNumber())) {
                                return true;
                            }
                        } else {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    private Boolean dbDataValidation(StudentRegistrationForm studentRegistrationForm) {


        if (studentRegistrationForm.getEmail().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getEmail())) {
            if (studentRegistrationForm.getLastName().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getLastName())) ;
            {
                if (studentRegistrationForm.getFirstName().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getFirstName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {
        Boolean successfulRegistration = false;
        String message;
            if (basicDataValidation(studentRegistrationForm)) {
                if (dbDataValidation(studentRegistrationForm)) {
                    populateStudentTable(studentRegistrationForm);
                }

            } else {
                throw new StudentRegistrationException("Ati introdus date invalide");
            }

        return successfulRegistration;
    }

    private Boolean verifiedEmail() {
        return false;
    }

    private Boolean sendVerificationEmail(String email) {
        return false;
    }

    private Boolean notifyFrontEnd(Boolean successfulRegistration,String message) {
        return false;
    }

    private StudentDao populateStudentTable(StudentRegistrationForm studentRegistrationForm) {
        StudentDao oldStudentInfo = userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber());
        StudentDao newStudentInfo = new StudentDao(oldStudentInfo.getGroup(),
                oldStudentInfo.getRegistrationNumber(),
                studentRegistrationForm.isSemigroup(),
                locationRepositoryService.findByLocationName(studentRegistrationForm.getStudentHousingName()),
                roomRepositoryService.findByLocationNameAndName(studentRegistrationForm.getStudentHousingName(),
                        studentRegistrationForm.getRoomNumber().toString()),
                userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getCourses(),
                userService.userRepository.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getTimeTable(),
                userService.userRepository.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getSection());
        return userService.updateStudentData(newStudentInfo);
    }
}
