package org.example.db;

import org.example.entity.Group;
import org.example.entity.Mark;
import org.example.entity.User;

import java.util.*;

public interface Db {
    Scanner scnInt = new Scanner(System.in);
    Scanner scnStr = new Scanner(System.in);

    Set<User> users = new HashSet<>();
    Set<Group> groups = new HashSet<>();
    Set<Mark> marks = new HashSet<>();
}

