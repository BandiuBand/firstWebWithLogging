package model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserValidator {
    public static boolean isValidateUser(String username,String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, getEncodedPasswordFromDB(username));
    }

    private static String getEncodedPasswordFromDB(String username){
        //toDo
    }
    private UserValidator() {
    }
}


