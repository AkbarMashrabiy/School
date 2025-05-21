package org.example.ui;

import org.example.entity.User;

import static org.example.db.Db.scnInt;

public class StudentUi {
    private static User currentUser;
    public static void studentUi(User user){
        currentUser = user;
        boolean isExisted = false;
        while (!isExisted) {
            System.out.println("""
                    1 My marks
                    2 My attendance
                    \s
                    0 exit
                    >>>>\s""");

            switch (scnInt.nextInt()){
                case 1 -> {
//                    myMarks()
                }
                case 2 -> {
//                    myAttendance();
                }
                case 0 -> {
                    isExisted = true;
                }
                default -> System.out.println("Invalid command!");
            }
        }


    }
}
