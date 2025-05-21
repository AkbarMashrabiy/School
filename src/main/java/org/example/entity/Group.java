package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.GroupStatus;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private UUID id;
    private String name;
    private User teacher;
    private Integer maxLessonInMonth;
    private GroupStatus status;
    private Set<User> students;

    @Override
    public String toString() {
        return "Group[" +
                "teacher=" + teacher +
                ", maxLessonInMonth=" + maxLessonInMonth +
                ", students=" + students +
                ", name='" + name + '\'' +
                ']';
    }
}
