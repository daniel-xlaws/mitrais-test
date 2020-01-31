package com.test.mitrais.controller;
import com.test.mitrais.model.User;
import com.test.mitrais.pojo.RestResult;
import com.test.mitrais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody User user) {
        RestResult restResult = new RestResult();

        try {
            validateNewUser(user);
            userService.insertUser(user);
            restResult.setMessage(user);
            restResult.setSuccess(true);
        } catch (IllegalArgumentException e) {
            restResult.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            restResult.setMessage("Internal server error");
        }

        return ResponseEntity.ok(restResult);
    }

    private void validateNewUser(User user) {
        validateMobilePhone(user.getMobileNumber());
        validateRequiredMaxLength("First name", user.getFirstName(), 50);
        validateRequiredMaxLength("Last name", user.getLastName(), 50);
        validateGender(user.getGender());
        validateEmailAddress(user.getEmailAddress());
    }

    private void validateEmailAddress(String emailAddress) {
        validateRequiredMaxLength("Email address", emailAddress, 100);
        if (!emailAddress.matches("^(.+)@(.+)$")) {
            throw new InvalidParameterException("Please enter valid email address");
        }
    }

    private void validateGender(char gender) {
        if (gender != 'F' && gender != 'M') {
            throw new InvalidParameterException("Invalid gender value");
        }
    }

    private void validateMobilePhone(String mobileNumber) {
        validateRequiredMaxLength("Mobile number", mobileNumber, 15);
        if (!mobileNumber.matches("^((\\+62)|(0))(8)(\\d){8,10}$")) {
            throw new InvalidParameterException("Please enter valid Indonesian mobile number");
        }
    }

    private void validateRequiredMaxLength(String label, String value, Integer maxLength) {
        if (value == null) {
            throw new InvalidParameterException(label + " can not be empty");
        }
        if (maxLength != null && value.length() > maxLength) {
            throw new InvalidParameterException(label + " length can`t longer than " + maxLength + " characters");
        }
    }
}
