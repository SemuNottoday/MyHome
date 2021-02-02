package com.example.myrent;

public class OwnerAuthontication
{
    private String id;
    private String email;
    private String password;
    private String label;

    public OwnerAuthontication() {
    }

    public OwnerAuthontication(String id, String email, String password, String label) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
