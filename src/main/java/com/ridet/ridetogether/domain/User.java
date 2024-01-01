package com.ridet.ridetogether.domain;

import com.ridet.ridetogether.Gender;
import com.ridet.ridetogether.UserRole;

/**
 * <h2>회원</h2>
 * <h4>사용자 계정 정보</h4>
 */
public class User {
    private Integer id; // id는 Repository단에서 User 저장시 생성됨
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private UserRole role;
    private boolean active;

    public User(String email, String password, String name, Gender gender, UserRole role, boolean active) {
        this.id = null;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.active = active;
    }

    public static class Builder {
        private String email;
        private String password;
        private String name;
        private Gender gender;
        private UserRole role;
        private boolean active;

        public Builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            return new User(email, password, name, gender, role, active);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
