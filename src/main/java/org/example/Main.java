package org.example;

import static org.example.db.Db.*;
import org.example.entity.User;
import org.example.enums.Role;
import org.example.service.AuthService;
import org.example.ui.AdminUi;
import org.example.ui.StudentUi;
import org.example.ui.TeacherUi;

import java.util.UUID;


public class Main {
    public static AuthService authService = new AuthService();

    static {
        User admin = new User(UUID.randomUUID(),"admin","admin", "admin", Role.ADMIN, false);
        User teacher1 = new User(UUID.randomUUID(),"teacher1","teacher1", "teacher1", Role.TEACHER, false);
        User teacher2 = new User(UUID.randomUUID(),"teacher2","teacher2", "teacher2", Role.TEACHER, false);
    }
    public static void main(String[] args) {
        boolean isExisted = false;
        while (!isExisted){
            System.out.print("""
                    1 Sign in
                    \s
                    0 Exit
                    >>>>\s""");

            switch (scnInt.nextInt()){
                case 1 ->{
                    signIn();
                }
                case 0 -> isExisted = true;
                default -> System.out.println("Invalid command!");
            }
        }

    }

    private static void signIn() {
        System.out.println("Enter your email: ");
        String email = scnStr.nextLine();
        System.out.println("Enter your password: ");
        String password = scnStr.nextLine();

        User user = authService.login(email, password);
        if (user == null){
            System.out.println("Email or password wrong please try again");
            return;
        }
        if (user.getRole().equals(Role.STUDENT)){
            StudentUi.studentUi(user);
        }else if (user.getRole().equals(Role.ADMIN)){
            AdminUi.adminUi(user);
        }else if (user.getRole().equals(Role.TEACHER)){
            TeacherUi.teacherUi(user);
        }
    }
}