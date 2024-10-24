package ch.tbz;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.Random;

public class BirthdayProbabilitiesPlotWithoutCollections {

    private static final int DAYS_IN_YEAR = 365;
    private static final int SIMULATIONS = 10000; // Number of simulations for each group size

    public static void main(String[] args) {
        // Array of different group sizes (converted to double[])
        double[] groupSizes = {1, 5, 10, 20, 23, 30, 40, 50, 60, 70, 75, 100};
        double[] probabilities = new double[groupSizes.length]; // Array to store calculated probabilities

        // Run simulations for each group size and store the results in the array
        for (int i = 0; i < groupSizes.length; i++) {
            int numberOfPeople = (int) groupSizes[i];  // Convert double to int for simulations
            int matchingSimulations = runSimulations(SIMULATIONS, numberOfPeople);
            probabilities[i] = calculateProbability(SIMULATIONS, matchingSimulations);

            // Output the results, formatted clearly
            System.out.printf("People: %d\tSimulations: %d\tMatches: %d\tProbability: %.1f%%%n",
                    numberOfPeople, SIMULATIONS, matchingSimulations, probabilities[i]);
        }

        // Plot the results
        plotGraph(groupSizes, probabilities);
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
        boolean[] birthdays = new boolean[DAYS_IN_YEAR]; // Boolean array instead of HashSet
        Random random = new Random();

        for (int i = 0; i < numberOfPeople; i++) {
            int birthday = random.nextInt(DAYS_IN_YEAR);  // Random birthday between 0 and 364

            if (birthdays[birthday]) {
                return true;  // Birthday is already marked as true, so we have a match
            }

            birthdays[birthday] = true;  // Mark the birthday as taken
        }

        return false;  // No matching birthdays
    }

    /**
     * Plots the graph using XChart
     */
    private static void plotGraph(double[] groupSizes, double[] probabilities) {
        // Create a Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Birthday Paradox Probabilities").xAxisTitle("Number of People").yAxisTitle("Probability (%)").build();

        // Customize the chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setMarkerSize(8);

        // Add data series
        chart.addSeries("Probability of Same Birthday", groupSizes, probabilities);

        // Show it
        new SwingWrapper<>(chart).displayChart();
    }
}
