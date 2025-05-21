package org.example.service;

import org.example.db.Db;
import org.example.entity.User;
import org.example.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    public boolean addUser(User user){
        return Db.users.add(user);
    }

    public User getUserById(UUID userId){
        for (User user : Db.users) {
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }


    public List<User> getAllTeachers(){
        List<User> teachers = new ArrayList<>();
        for (User user : Db.users) {
            if (user.getRole() != null && user.getRole().equals(Role.TEACHER)){
                teachers.add(user);
            }
        }
        return teachers;
    }
    public List<User> getAllStudents(){
        List<User> students = new ArrayList<>();
        for (User user : Db.users) {
            if (user.getRole() != null && user.getRole().equals(Role.STUDENT)){
                students.add(user);
            }
        }
        return students;
    }


    public boolean isAlreadyExistUser(String email){
        for (User user : Db.users) {
            if (user.getEmail()!= null && user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

}
