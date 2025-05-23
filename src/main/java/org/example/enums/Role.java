package org.example.enums;

public enum Role {
    ADMIN ("Admin"),
    STUDENT("Student"),
    TEACHER("Teacher");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
