package com.ridet.ridetogether;

public enum UserRole {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "사용자");


    private final String key;
    private final String description;

    UserRole(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
