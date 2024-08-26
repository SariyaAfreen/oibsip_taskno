package OnlineExam;

import java.util.Scanner;

public class OnlineExamSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        if (user.login()) {
            System.out.println("Login successful!");

            boolean sessionActive = true;
            while (sessionActive) {
                System.out.println("\n1. Update Profile");
                System.out.println("2. Update Password");
                System.out.println("3. Start Exam");
                System.out.println("4. Logout");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        user.updateProfile(scanner);
                        break;
                    case 2:
                        user.updatePassword(scanner);
                        break;
                    case 3:
                        Exam exam = new Exam();
                        exam.startExam(scanner);
                        break;
                    case 4:
                        sessionActive = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }

        scanner.close();
    }
}
