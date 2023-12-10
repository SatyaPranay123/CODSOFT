import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int rounds = 0;
        int totalAttempts = 0;

        boolean playAgain = true;

        while (playAgain) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int userGuess;
            int attempts = 0;
            int attemptsLimit = 10;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound);

            do {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > generatedNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                }

            } while (userGuess != generatedNumber && attempts < attemptsLimit);

            totalAttempts += attempts;
            rounds++;

            System.out.println("Round complete. Your score for this round: " + attempts + " attempts.");

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();

            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing! Your total score: " + totalAttempts + " attempts in " + rounds + " rounds.");
            }
        }

        scanner.close();
    }
}
