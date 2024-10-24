package ch.tbz;

import java.util.Random;

public class BirthdayProbabilitiesWithoutCollections {

    public static void main(String[] args) {
        // Number of simulations
        int simulations = 1000;
        // Number of people in the group (e.g., 23 people)
        int numberOfPeople = 23;
        int matches = 0;

        for (int i = 0; i < simulations; i++) {
            if (hasBirthdayMatch(numberOfPeople)) {
                matches++;
            }
        }

        // Calculate the probability
        double probability = (double) matches / simulations * 100;
        // Output the results with labels and separated by tabs
        System.out.println("Simulations: " + simulations + "\tPeople: " + numberOfPeople + "\tMatches: " + matches + "\tProbability: " + probability + "%");
    }

    // Method to check if there is a birthday match in a group
    private static boolean hasBirthdayMatch(int numberOfPeople) {
        // Array to track whether a specific birthday has already been assigned
        boolean[] birthdays = new boolean[365];
        Random random = new Random();

        for (int i = 0; i < numberOfPeople; i++) {
            // Random birthday between 0 and 364 (365 days in a year)
            int birthday = random.nextInt(365);

            // If this birthday was already assigned, we have a match
            if (birthdays[birthday]) {
                return true;
            }

            // Otherwise, mark this birthday as assigned
            birthdays[birthday] = true;
        }

        // No match found
        return false;
    }
}
