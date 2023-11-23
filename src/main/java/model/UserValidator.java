package model;

public class UserValidator {
    public static boolean isValidateUser(String username,String password){
        if (username.equals("Bandiu")) {
            return password.equals("1111");
        }
        return false;
    }

    private UserValidator() {
    }
}
