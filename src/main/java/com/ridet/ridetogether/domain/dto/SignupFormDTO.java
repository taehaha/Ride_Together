package com.ridet.ridetogether.domain.dto;

import com.ridet.ridetogether.Gender;
import com.ridet.ridetogether.UserRole;

//TODO: Builder 추가
public class SignupFormDTO {
    public String email;
    public String password;
    public String name;
    public Gender gender;
    public UserRole userRole;

    public SignupFormDTO() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
