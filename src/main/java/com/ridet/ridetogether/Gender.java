package com.ridet.ridetogether;

public enum Gender {
    MALE("MALE","남자"),
    FEMALE("FEMALE", "여자");

    private final String key;
    private final String description;

    Gender(String key, String description) {
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