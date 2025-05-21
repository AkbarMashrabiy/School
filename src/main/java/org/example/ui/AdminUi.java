package org.example.ui;

import org.example.entity.Group;
import org.example.entity.User;
import org.example.enums.GroupStatus;
import org.example.enums.Role;
import org.example.service.GroupService;
import org.example.service.UserService;

import java.util.List;
import java.util.UUID;

import static org.example.db.Db.scnInt;
import static org.example.db.Db.scnStr;

public class AdminUi {
    private static User currentUser;
    private static final UserService userService = new UserService();
    private static final GroupService groupService = new GroupService();

    public static void adminUi(User user) {
        currentUser = user;
        boolean isExisted = false;
        while (!isExisted) {
            System.out.println("""
                    1 Add teacher
                    2 Add student
                    3 Edit
                    4 Delete
                    5 Create group
                    6 Edit group
                    \s
                    0 exit
                    >>>>\s""");

            switch (scnInt.nextInt()) {
                case 1 -> {
                    addTeacher();
                }
                case 2 -> {
                    addStudent();
                }
                case 3 -> {
                    edit();
                }
                case 4 -> {
                    delete();
                }
                case 5 -> {
                    createGroup();
                }
                case 6 -> {
//                    editGroup();
                }
                case 0 -> {
                    isExisted = true;
                }
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private static void createGroup() {
        System.out.println("Enter Group name");
        String name = scnStr.nextLine();

        System.out.println("Enter max lessons in Moth");
        Integer maxLesson = scnInt.nextInt();

        List<User> teachers = userService.getAllTeachers();
        showAllTeachers();
        if (teachers.isEmpty()) return;
        System.out.println("Choose teacher, Enter Index: ");
        int index = scnInt.nextInt()-1;
        groupService.addGroup(new Group(UUID.randomUUID(),name, teachers.get(index), maxLesson, GroupStatus.ACTIVE, null));
        System.out.println("Group successfully created!");
    }



    private static void delete() {
        while (true) {
            System.out.print("""
                    1 Delete Teacher
                    2 Delete Student
                    \s
                    0 Exit
                    >>>>\s""");

            switch (scnInt.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    deleteTeacher();
                }
                case 2 -> {
                    deleteStudent();
                }
            }
        }
    }

    private static void deleteStudent() {
        List<User> students = userService.getAllStudents();
        showAllStudents();
        if (students.isEmpty()) return;

        System.out.print("\n Enter Index:  ");
        int index = scnInt.nextInt();
        if (index > 0 && index <= students.size()) {
            User user = students.get(index - 1);
            user.setId(null);
            user.setRole(null);
            user.setEmail(null);
            user.setPassword(null);
            user.setFullName(null);
            System.out.println("Student successfully deleted!");
        } else System.out.println("Wrong Index!");
    }

    private static void deleteTeacher() {
        List<User> teachers = userService.getAllTeachers();
        showAllTeachers();
        if (teachers.isEmpty()) return;

        System.out.print("\n Enter Index:  ");
        int index = scnInt.nextInt();
        if (index > 0 && index <= teachers.size()) {
            User user = teachers.get(index - 1);
            user.setId(null);
            user.setRole(null);
            user.setEmail(null);
            user.setPassword(null);
            user.setFullName(null);
            System.out.println("Teacher successfully deleted!");
        } else System.out.println("Wrong Index!");

    }

    public static void showAllStudents() {
        List<User> students = userService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("We have nothing Teachers in our school");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ") " + students.get(i));
        }
    }

    public static void showAllTeachers() {
        List<User> teachers = userService.getAllTeachers();
        if (teachers.isEmpty()) {
            System.out.println("We have nothing Teachers in our school");
            return;
        }
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ") " + teachers.get(i));
        }
    }

    private static void edit() {
        while (true) {
            System.out.print("""
                    1 Edit Teacher
                    2 Edit Student
                    \s
                    0 Exit
                    >>>>\s""");

            switch (scnInt.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    editTeacher();
                }
                case 2 -> {
                    editStudent();
                }
            }
        }
    }

    private static void editTeacher() {
        List<User> teachers = userService.getAllTeachers();
        showAllTeachers();
        if (teachers.isEmpty()) return;

        System.out.print("\n Enter Index:  ");
        int index = scnInt.nextInt();
        if (index > 0 && index <= teachers.size()) {
            editUser(teachers, index);
        } else System.out.println("Wrong Index!");
    }

    private static void editStudent() {
        List<User> allStudents = userService.getAllStudents();
        showAllStudents();
        if (allStudents.isEmpty()) return;

        System.out.print("\n Enter Index:  ");
        int index = scnInt.nextInt();
        if (index > 0 && index <= allStudents.size()) {
            editUser(allStudents, index);
        } else System.out.println("Wrong Index!");
    }

    private static void editUser(List<User> allStudents, int index) {
        User user = allStudents.get(index - 1);
        user.setEmail(null);
        System.out.println("Enter full name");
        String fullName = scnStr.nextLine();

        System.out.println("Enter email");
        String email = scnStr.nextLine();

        System.out.println("Enter password");
        String password = scnStr.nextLine();

        if (userService.isAlreadyExistUser(email)) {
            System.out.println("This Email already has been exist!");
            return;
        }
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("User Successfully edited!");
    }

    private static void addTeacher() {
        System.out.println("Enter teacher's full name");
        String fullName = scnStr.nextLine();

        System.out.println("Enter teacher's email");
        String email = scnStr.nextLine();

        System.out.println("Enter teacher's password");
        String password = scnStr.nextLine();

        if (userService.isAlreadyExistUser(email)) {
            System.out.println("This user already has been exist!");
            return;
        }
        userService.addUser(new User(UUID.randomUUID(), fullName, email, password, Role.TEACHER,false));
        System.out.println("Teacher successfully added to Storage");
    }

    private static void addStudent() {
        System.out.println("Enter student's full name");
        String fullName = scnStr.nextLine();

        System.out.println("Enter student's email");
        String email = scnStr.nextLine();

        System.out.println("Enter student's password");
        String password = scnStr.nextLine();

        if (userService.isAlreadyExistUser(email)) {
            System.out.println("This user already has been exist!");
            return;
        }
        userService.addUser(new User(UUID.randomUUID(), fullName, email, password, Role.STUDENT, false));
        System.out.println("Student successfully added to Storage");
    }

}
