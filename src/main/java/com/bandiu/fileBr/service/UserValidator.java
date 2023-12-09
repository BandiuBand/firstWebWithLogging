package com.bandiu.fileBr.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    public static boolean isValidateUser(String username,String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, getEncodedPasswordFromDB(username));
    }

    private static String getEncodedPasswordFromDB(String username){
        //toDo
        return null;
    }
    private UserValidator() {
    }
}


