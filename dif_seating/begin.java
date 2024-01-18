package dif_seating;
import java.util.*;

import java.util.Scanner;
import java.util.Scanner;

public class begin {

    private UserAuthentication userAuth;

    public begin() {
        this.userAuth = new UserAuthentication();
    }

    public void begins() {
        Scanner scanner = new Scanner(System.in);

        boolean isAuthenticated = false;
        do {
            System.out.println("Enter your username:");
            String username = scanner.next();
            System.out.println("Enter your password:");
            String password = scanner.next();

            isAuthenticated = userAuth.authenticateUser(username, password);

            if (!isAuthenticated) {
                System.out.println("Invalid username or password. Please try again.");
            }

        } while (!isAuthenticated);

        System.out.println("Login successful!\n");

        System.out.println("---------------------------------------------------------------------");
        System.out.println("WELCOME TO SEATING MANAGEMENT SYSTEM");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Choose a Seating Type:");
        System.out.println("1. Exam Seating arrangement");
        System.out.println("2. Bus Seating arrangement");
        System.out.println("3. Theatre Seating arrangement");
        System.out.println("Enter your choice (1-3):");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                seating examSeating = new exam_seating();
                examSeating.handle_Seating();
                break;
            case 2:
                seating busSeating = new bus_seating();
                busSeating.handle_Seating();
                break;
            case 3:
                seating TheatreSeating = new theatre_seating();
                TheatreSeating.handle_Seating();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

 

    static class UserAuthentication {
        private static final String name = "admin";
        private static final String password = "admin123";

        public boolean authenticateUser(String username, String password) {
            return name.equals(username) && password.equals(password);
        }
    }
    public static void main(String[] args) {
        begin b1 = new begin();
        b1.begins();
    }
}
