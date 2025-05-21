package org.example.service;

import org.example.entity.User;

import static org.example.db.Db.users;

public class AuthService {


    public User login(String email, String password){
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
