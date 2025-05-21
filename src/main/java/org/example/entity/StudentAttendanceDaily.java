package org.example.entity;

import org.example.enums.AttendanceStatus;

import java.util.UUID;

public class StudentAttendanceDaily {
    private UUID studentId;
    private String studentName;
    private AttendanceStatus status;
}
