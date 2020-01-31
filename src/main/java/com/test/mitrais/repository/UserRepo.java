package com.test.mitrais.repository;

import com.test.mitrais.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailAddressOrMobileNumber(String emailAddress, String mobileNumber);
}
