package org.example.entity;

import lombok.*;
import org.example.enums.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Boolean isAddedToGroup = false;

    @Override
    public String toString() {
        return "User[" +
                "fullName = " + fullName  +
                "   role = " + role  +
                ']';
    }
}
