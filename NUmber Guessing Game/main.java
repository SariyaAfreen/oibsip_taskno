
import java.util.Random;
import java.util.Scanner;

public class main {

    private static final int MAX_ATTEMPTS = 10; // Maximum number of attempts
    private static final int MIN_NUMBER = 1;    // Minimum number in the range
    private static final int MAX_NUMBER = 100;  // Maximum number in the range

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int numberToGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER; // Generate a random number
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
                System.out.println("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                attempts--; // Don't count this as an attempt
                continue;
            }

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number correctly in " + attempts + " attempts.");
                hasGuessedCorrectly = true;
                break;
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry! You've used all your attempts. The number was " + numberToGuess + ".");
        }

        scanner.close();
    }
}
