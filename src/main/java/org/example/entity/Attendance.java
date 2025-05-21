package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    private final String id = UUID.randomUUID().toString();
    private LocalDate date;
    private String groupId;
    private User teacher;
    private List<StudentAttendanceDaily> attendedStudents;
}
