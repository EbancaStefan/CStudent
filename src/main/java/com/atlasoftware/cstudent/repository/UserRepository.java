package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AbstractUserDao, UUID> {
    StudentDao findByRegistrationNumber(Integer registrationNumber);
    StudentDao findByEmail(String email);

}
