package org.example.service;

import org.example.db.Db;
import org.example.entity.Group;
import org.example.enums.GroupStatus;

import java.util.ArrayList;
import java.util.List;
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

    public List<Group> getAllActiveGroups(){
        List<Group> groups = new ArrayList<>();
        for (Group group : Db.groups) {
            if (group.getStatus().equals(GroupStatus.ACTIVE)){
                groups.add(group);
            }
        }
        return groups;
    }
    public List<Group> getAllGroups(){
        return new ArrayList<>(Db.groups);
    }


}
