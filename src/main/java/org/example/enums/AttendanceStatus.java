package org.example.enums;

public enum AttendanceStatus {
    ATTENDED("  attended  "),
    NOT_ATTENDED("not attended");
    private String description;

    AttendanceStatus(String description) {
        this.description = description;
    }

}
