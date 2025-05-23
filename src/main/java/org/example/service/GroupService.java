package org.example.service;

import org.example.db.Db;
import org.example.entity.Group;
import org.example.entity.User;
import org.example.enums.GroupStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GroupService {
    public boolean addGroup(Group group){
        return Db.groups.add(group);
    }

    public Group getGroupById(UUID id){
        for (Group group : Db.groups) {
            if (group.getId().equals(id)){
                return group;
            }
        }
        return null;
    }
    public boolean isAlreadyExistGroupName(String name){
        for (Group group : Db.groups) {
            if (group.getName().equals(name)){
                return true;
            }
        }
        return false;
    }



    public List<Group> getGroupsByTeacher(User teacher){
        List<Group> groups = new ArrayList<>();
        for (Group group : Db.groups) {
            if (group.getStatus().equals(GroupStatus.ACTIVE) && group.getTeacher().equals(teacher)){
                groups.add(group);
            }
        }
        return groups;
    }
    public List<Group> getAllGroups(){
        return new ArrayList<>(Db.groups);
    }

    public List<User> getStudentsByGroup(Group group){
        Set<User> students = group.getStudents();
        List<User> newList = new ArrayList<>();
        newList.addAll(students);
        return newList;
    }
    public boolean removeStudentInGroup(Group group, User student){
        for (Group group1 : Db.groups) {
            if (group1.equals(group)){
                for (User groupStudent : group.getStudents()) {
                    if (groupStudent.equals(student)){
                        Set<User> students = group.getStudents();
                        students.remove(student);
                        student.setIsAddedToGroup(false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
