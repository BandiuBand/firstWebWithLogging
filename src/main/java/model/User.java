package model;


import java.util.Date;


public class User{



    private String username;


    private String passwordHash;

    private String email;

    private Date createDate;

    private String verificationToken;
    private boolean isValidated;

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public User(String username, String passwordHash, String email, Date createDate, String verificationToken, boolean isValidated) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createDate = createDate;
        this.verificationToken = verificationToken;
        this.isValidated = isValidated;
    }
}
