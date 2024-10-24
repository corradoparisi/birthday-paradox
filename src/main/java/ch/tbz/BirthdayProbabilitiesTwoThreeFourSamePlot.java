package ch.tbz;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BirthdayProbabilitiesTwoThreeFourSamePlot {

    private static final int DAYS_IN_YEAR = 365;
    private static final int SIMULATIONS = 10000; // Number of simulations for each group size

    public static void main(String[] args) {
        // Array of different group sizes
        int[] groupSizes = {1, 5, 10, 20, 23, 30, 40, 50, 60, 70, 75, 100};

        // Lists to store results for plotting
        List<Integer> groupSizeList = new ArrayList<>();
        List<Double> probTwoSameList = new ArrayList<>();
        List<Double> probThreeSameList = new ArrayList<>();
        List<Double> probFourSameList = new ArrayList<>();

        // Run simulations for each group size and store the results
        for (int numberOfPeople : groupSizes) {
            int twoSame = 0;
            int threeSame = 0;
            int fourSame = 0;

            for (int i = 0; i < SIMULATIONS; i++) {
                int[] result = runSingleSimulation(numberOfPeople);
                if (result[0] > 0) twoSame++;   // Count simulations with at least 2 same birthdays
                if (result[1] > 0) threeSame++; // Count simulations with at least 3 same birthdays
                if (result[2] > 0) fourSame++;  // Count simulations with at least 4 same birthdays
            }

            double probTwoSame = calculateProbability(SIMULATIONS, twoSame);
            double probThreeSame = calculateProbability(SIMULATIONS, threeSame);
            double probFourSame = calculateProbability(SIMULATIONS, fourSame);

            groupSizeList.add(numberOfPeople);
            probTwoSameList.add(probTwoSame);
            probThreeSameList.add(probThreeSame);
            probFourSameList.add(probFourSame);

            // Output the results, formatted clearly
            System.out.printf("People: %d\t\tTwo Same: %.1f%%\t\tThree Same: %.1f%%\t\tFour Same: %.1f%%%n",
                    numberOfPeople, probTwoSame, probThreeSame, probFourSame);
        }

        // Plot the results
        plotGraph(groupSizeList, probTwoSameList, probThreeSameList, probFourSameList);
    }

    /**
     * Runs a single simulation to check how many times there are 2, 3, or 4 same birthdays.
     *
     * @param numberOfPeople the number of people in the group
     * @return an array where each element indicates if there are 2, 3, or 4 same birthdays
     */
    private static int[] runSingleSimulation(int numberOfPeople) {
        int[] birthdayCounts = new int[DAYS_IN_YEAR]; // Array to track how many people share each birthday
        int[] result = {0, 0, 0};  // {twoSame, threeSame, fourSame}
        Random random = new Random();

        // Assign random birthdays to the group
        for (int i = 0; i < numberOfPeople; i++) {
            int birthday = random.nextInt(DAYS_IN_YEAR);  // Random birthday between 0 and 364
            birthdayCounts[birthday]++; // Increment the count for that specific birthday
        }

        // Check how many people share the same birthday
        for (int count : birthdayCounts) {
            if (count >= 2) result[0] = 1;  // At least 2 people share the same birthday
            if (count >= 3) result[1] = 1;  // At least 3 people share the same birthday
            if (count >= 4) result[2] = 1;  // At least 4 people share the same birthday
        }

        return result;
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
     * Plots the graph using XChart
     */
    private static void plotGraph(List<Integer> groupSizes, List<Double> probTwoSame, List<Double> probThreeSame, List<Double> probFourSame) {
        // Create a Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Birthday Paradox Probabilities").xAxisTitle("Number of People").yAxisTitle("Probability (%)").build();

        // Customize the chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setMarkerSize(8);

        // Add data series
        chart.addSeries("Two Same Birthdays", groupSizes, probTwoSame);
        chart.addSeries("Three Same Birthdays", groupSizes, probThreeSame);
        chart.addSeries("Four Same Birthdays", groupSizes, probFourSame);

        // Show it
        new SwingWrapper<>(chart).displayChart();
    }


}
