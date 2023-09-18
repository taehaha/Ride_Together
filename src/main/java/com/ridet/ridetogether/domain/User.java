package com.ridet.ridetogether.domain;

import com.ridet.ridetogether.Gender;
import com.ridet.ridetogether.UserRole;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private UserRole role;
    private boolean isActive;

    public User() {
    }

    public User(Long id, String email, String password, String name, Gender gender, UserRole role, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
