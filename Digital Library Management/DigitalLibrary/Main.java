package DigitalLibrary;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adding a default admin user
        users.add(new User("admin", "admin123", true));

        System.out.println("Login as:");
        System.out.println("1. Admin");
        System.out.println("2. User");
        int role = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = null;

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser == null) {
            System.out.println("Invalid username or password!");
            return;
        }

        if (loggedInUser.isAdmin()) {
            Admin admin = new Admin(books, scanner);
            admin.menu();
        } else {
            System.out.println("User functionalities are not implemented in this example.");
        }
    }
}
