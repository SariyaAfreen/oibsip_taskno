
import java.io.*;
import java.util.*;

public class main {

    private static boolean[] seats = new boolean[10]; // Initialize an array of 10 seats, all empty
    private static final String FILE_NAME = "seats.dat";
    private static final String USERS_FILE = "users.dat";

    public static void main(String[] args) {
        loadSeats();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. View Seat Map");
            System.out.println("2. Reserve Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Register User");
            System.out.println("5. Login");
            System.out.println("6. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    viewSeatMap();
                    break;
                case 2:
                    reserveSeat(scanner);
                    break;
                case 3:
                    cancelReservation(scanner);
                    break;
                case 4:
                    registerUser(scanner);
                    break;
                case 5:
                    loginUser(scanner);
                    break;
                case 6:
                    saveSeats();
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void viewSeatMap() {
        System.out.println("\nCurrent Seat Map:");
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                System.out.print("X "); // Print "X" if the seat is reserved
            } else {
                System.out.print((i + 1) + " "); // Print seat number if empty
            }
        }
        System.out.println();
    }

    private static void reserveSeat(Scanner scanner) {
        System.out.print("\nEnter seat number (1-10): ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (seatNumber < 1 || seatNumber > 10) {
            System.out.println("Invalid seat number!");
        } else if (seats[seatNumber - 1]) {
            System.out.println("Seat already reserved!");
        } else {
            seats[seatNumber - 1] = true; // Reserve the seat
            System.out.println("Seat reserved!");
        }
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("\nEnter seat number (1-10): ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (seatNumber < 1 || seatNumber > 10) {
            System.out.println("Invalid seat number!");
        } else if (!seats[seatNumber - 1]) {
            System.out.println("Seat not reserved!");
        } else {
            seats[seatNumber - 1] = false; // Unreserve the seat
            System.out.println("Reservation canceled!");
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while registering the user.");
            e.printStackTrace();
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                if (user[0].equals(username) && user[1].equals(password)) {
                    System.out.println("Login successful!");
                    return;
                }
            }
            System.out.println("Invalid username or password!");
        } catch (IOException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    private static void loadSeats() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            seats = (boolean[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous reservation data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading reservation data.");
            e.printStackTrace();
        }
    }

    private static void saveSeats() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(seats);
        } catch (IOException e) {
            System.out.println("An error occurred while saving reservation data.");
            e.printStackTrace();
        }
    }
}
