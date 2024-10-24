package ch.tbz;

import java.util.HashSet;
import java.util.Random;

public class BirthdayParadox {

    private static final int DAYS_IN_YEAR = 365;
    private static final int SIMULATIONS = 10000; // Number of simulations for each group size

    public static void main(String[] args) {
        // Array of different group sizes
        int[] groupSizes = {1, 5, 10, 20, 23, 30, 40, 50, 60, 70, 75, 100};

        // Run simulations for each group size and output the results
        for (int numberOfPeople : groupSizes) {
            int matchingSimulations = runSimulations(SIMULATIONS, numberOfPeople);
            double probability = calculateProbability(SIMULATIONS, matchingSimulations);

            // Output the results, formatted clearly
            System.out.printf("People: %d\tSimulations: %d\tMatches: %d\tProbability: %.1f%%%n",
                    numberOfPeople, SIMULATIONS, matchingSimulations, probability);
        }
    }

    /**
     * Runs multiple simulations to determine how many times there is a matching birthday.
     *
     * @param simulations the number of simulations to run
     * @param numberOfPeople the number of people in each simulation group
     * @return the number of simulations where at least two people share the same birthday
     */
    private static int runSimulations(int simulations, int numberOfPeople) {
        int matches = 0;

        for (int i = 0; i < simulations; i++) {
            if (hasMatchingBirthday(numberOfPeople)) {
                matches++;
            }
        }

        return matches;
    }

    /**
     * Calculates the probability of a match based on the results of the simulations.
     *
     * @param simulations the total number of simulations
     * @param matches the number of simulations with matching birthdays
     * @return the probability as a percentage
     */
    private static double calculateProbability(int simulations, int matches) {
        return (double) matches / simulations * 100;
    }

    /**
     * Checks whether a group of people contains at least two individuals with the same birthday.
     *
     * @param numberOfPeople the size of the group
     * @return true if there is a matching birthday, false otherwise
     */
    private static boolean hasMatchingBirthday(int numberOfPeople) {
        HashSet<Integer> birthdays = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < numberOfPeople; i++) {
            int birthday = random.nextInt(DAYS_IN_YEAR);  // Random birthday between 0 and 364

            if (!birthdays.add(birthday)) {
                return true;  // Birthday is already in the set, so there is a match
            }
        }

        return false;  // No matching birthdays
    }
}
