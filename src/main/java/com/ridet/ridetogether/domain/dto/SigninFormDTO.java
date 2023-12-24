package com.ridet.ridetogether.domain.dto;

public class SigninFormDTO {
    private String email;
    private String password;

    public SigninFormDTO() {
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
