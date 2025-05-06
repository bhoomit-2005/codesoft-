import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int rounds = 0;
        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int secretNumber = random.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = 7;
            boolean guessedCorrectly = false;
            rounds++;

            System.out.println("\nRound " + rounds + ": Guess the number between 1 and 100. You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess;

                // Validate input
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                } else {
                    System.out.println("❌ Invalid input. Please enter a number.");
                    scanner.next(); // Clear invalid input
                    continue;
                }

                if (guess < 1 || guess > 100) {
                    System.out.println("⚠️ Please enter a number between 1 and 100.");
                    continue;
                }

                if (guess == secretNumber) {
                    System.out.println("✅ Correct! The number was " + secretNumber + ".");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("🔼 Too low.");
                } else {
                    System.out.println("🔽 Too high.");
                }

                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts remaining: " + attemptsLeft);
                } else {
                    System.out.println("❌ Out of attempts! The number was " + secretNumber + ".");
                }
            }

            System.out.println("📊 Score: " + score + " out of " + rounds + " rounds.");

            // Ask if user wants to play again
            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // Consume leftover newline
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\n🏁 Game over. Final score: " + score + " out of " + rounds + " rounds.");
        System.out.println("🎉 Thanks for playing!");
        scanner.close();
    }
}
