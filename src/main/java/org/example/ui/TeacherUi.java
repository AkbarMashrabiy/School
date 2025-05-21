package org.example.ui;

import static org.example.db.Db.*;
import org.example.entity.User;

public class TeacherUi {
    private static User currentUser;

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
//                    addStudent()
                }
                case 2 -> {
//                    removeStudent();
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
}
