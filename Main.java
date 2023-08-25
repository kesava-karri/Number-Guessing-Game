import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int TIME = 1; // Set time in minutes as necessary
    public static int NUM_TRIES = 3;

    private static void numberGuessingGame(int min, int max, Scanner scanner) {
        long startTime = System.currentTimeMillis(); // Epoch time

        // Generate a random integer
        Random randomObj = new Random();
        int numToBeGuessed = randomObj.nextInt(min, max);

        // Run until all the tries are utilized
        while (NUM_TRIES > 0) {
            System.out.println("Enter your guess: ");
            int guess = scanner.nextInt();
            // A flag to keep the game interesting
            String lowOrHigh = guess < numToBeGuessed ? "Too Low" : "Too High";

            if (guess == numToBeGuessed && NUM_TRIES >= 0) {
                System.out.printf("Amazing, you've guessed it right"
                        + " %d is correct number", numToBeGuessed);
                return;
            } else {
                System.out.println("\n" + lowOrHigh + "\n");
                long guessTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
                // Left over time to guess the number
                long timeLeft = TIME * 60 - guessTime;
                NUM_TRIES -= 1;

                System.out.printf("Incorrect, you've got %d chance(s)\n", NUM_TRIES);
                System.out.printf("Time left: %d seconds approxmimately\n", timeLeft);
            }
        }
        System.out.printf("\nThe number is: %d\n", numToBeGuessed);
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to the Number Guessing Game!!!\n"
                + "\nEnter the range you would like guess from");

        // min, max are the range of numbers to pick from
        Scanner inputScanner = new Scanner(System.in);
        int min = inputScanner.nextInt();
        int max = inputScanner.nextInt();

        System.out.printf("\nYou'll have %d minute(s) and %d tries to guess it right\n"
                + "Good luck, The clock started ticking\n", TIME, NUM_TRIES);
        // Note how the scanner is passed as argument to the method. This is done since I need to take inputs later in that method.
        // It would throw java.util.NoSuchElementException if scanner is closed & using a new instance in that method.
        numberGuessingGame(min, max, inputScanner);
        inputScanner.close();
    }
}