package OnlineExam;

import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String profile;

    private static HashMap<String, String> userDatabase = new HashMap<>();

    static {
        userDatabase.put("admin", "admin123");
        userDatabase.put("user1", "pass1");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public void updateProfile(Scanner scanner) {
        System.out.print("Enter new profile info: ");
        this.profile = scanner.nextLine();
        System.out.println("Profile updated successfully.");
    }

    public void updatePassword(Scanner scanner) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userDatabase.put(username, newPassword);
        System.out.println("Password updated successfully.");
    }
}
