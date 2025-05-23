package org.example.ui;

import static org.example.db.Db.*;

import org.example.entity.Group;
import org.example.entity.User;
import org.example.service.GroupService;
import org.example.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherUi {
    private static User currentUser;
    private static UserService userService = new UserService();
    private static GroupService groupService = new GroupService();

    public static void teacherUi(User user) {
        currentUser = user;
        boolean isExisted = false;
        while (!isExisted) {
            System.out.println("""
                    1 Add student
                    2 Remove student
                    3 Attendance
                    4 Show attendance
                    5 Mark
                    \s
                    0 exit
                    >>>>\s""");

            switch (scnInt.nextInt()){
                case 1 -> {
                    addStudent();
                }
                case 2 -> {
                    removeStudent();
                }
                case 3 -> {
//                    attendance();
                }
                case 4 -> {
//                    showAttendance();
                }
                case 5 ->{
//                    mark();
                }
                case 0 -> {
                    isExisted = true;
                }
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private static void removeStudent() {
        List<Group> groups = groupService.getGroupsByTeacher(currentUser);
        if (isHaveGroup(groups)) return;
        showList(groups);
        System.out.println("Choose group, Enter index: ");
        int index = scnInt.nextInt() -1;

        Group group = null;
        if (index>=0 && index < groups.size()) {
            group = groups.get(index);
        }else System.out.println("Wrong Index!");

        List<User> students = groupService.getStudentsByGroup(group);
        if (students.isEmpty()) {
            System.out.println("You are unavailable, ask to admin for add student");
            return;
        }
        showList(students);
        System.out.println("Choose student, Enter index: ");
        int studentIndex = scnInt.nextInt()-1;
        if (index>=0 && index < students.size()) {
            User student = students.get(studentIndex);
            if (groupService.removeStudentInGroup(group, student)){
                groupService.removeStudentInGroup(group, student);
                System.out.println("Student successfully removed!");
            }
        }else {
            System.out.println("Wrong Index");
            return;
        }
    }


    private static void addStudent() {
        List<Group> groups = groupService.getGroupsByTeacher(currentUser);
        if (isHaveGroup(groups)) return;
        showList(groups);
        System.out.println("Choose group, Enter index: ");
        int index = scnInt.nextInt() -1;

        Group group = null;
        if (index>=0 && index < groups.size()) {
            group = groups.get(index);
        }else System.out.println("Wrong Index!");

        List<User> notJoinedStudents = userService.getAllNotJoinedStudent();
        if (notJoinedStudents.isEmpty()) {
            System.out.println("You are unavailable, ask to admin for add student");
            return;
        }
        showList(notJoinedStudents);
        System.out.println("Choose student, Enter index: ");
        int studentIndex = scnInt.nextInt()-1;
        User student = null;
        if (index>=0 && index < notJoinedStudents.size()) {
            student = notJoinedStudents.get(studentIndex);
        }else {
            System.out.println("Wrong Index");
            return;
        }

        addStudentToGroup(group, student);
    }

    private static boolean isHaveGroup(List<Group> groups) {
        if (groups.isEmpty()) {
            System.out.println("You have not group yet, ask to admin");
            return true;
        }
        return false;
    }

    private static void addStudentToGroup(Group group, User student) {
        if (group.getStudents() == null){
            Set<User> newStudent = new HashSet<>();
            newStudent.add(student);
            group.setStudents(newStudent);
            student.setIsAddedToGroup(true);
            System.out.println("Student successfully edded to group!\n");
        }else {
            Set<User> students = group.getStudents();
            students.add(student);
            group.setStudents(students);
            student.setIsAddedToGroup(true);
            System.out.println("Student successfully edded to group!\n");
        }
    }

    private static void showList(List notJoinedStudents) {
        for (int i = 0; i < notJoinedStudents.size(); i++) {
            System.out.println((1+i) + ") " + notJoinedStudents.get(i));
        }
    }
}
