package com.test.mitrais.service;

import com.test.mitrais.model.User;
import com.test.mitrais.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void insertUser(User user) {
        User existingUser = userRepo.findByEmailAddressOrMobileNumber(user.getEmailAddress(), user.getMobileNumber());
        if (existingUser != null) {
            if (existingUser.getMobileNumber().equalsIgnoreCase(user.getMobileNumber())) {
                throw new IllegalArgumentException("Mobile number already registered");
            }
            if (existingUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress())) {
                throw new IllegalArgumentException("Email address already registered");
            }
        }
        userRepo.save(user);
    }
}
