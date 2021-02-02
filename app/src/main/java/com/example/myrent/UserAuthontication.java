package com.example.myrent;

public class UserAuthontication
{
    private String uid;
    private String email;
    private String password;
    private String label;

    public UserAuthontication() {
    }

    public UserAuthontication(String uid, String email, String password, String label) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
