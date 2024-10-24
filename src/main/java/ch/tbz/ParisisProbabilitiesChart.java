package ch.tbz;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ParisisProbabilitiesChart {

    public static void main(String[] args) {
        // Hardcoded data based on the graph
        double[] groupSizes = {10,20,23,30,40,50,60};
        double[] probabilities = {12,41,51,71,89,97,99};

        // Create a Chart
        XYChart chart = new XYChartBuilder().width(800).height(600)
                .title("Parisis Theory of Probabilities")
                .xAxisTitle("Number of People")
                .yAxisTitle("Probability (%)").build();

        // Customize Chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setMarkerSize(6);

        // Add series
        XYSeries series = chart.addSeries("Probability of Same Birthday", groupSizes, probabilities);
        series.setMarker(SeriesMarkers.CIRCLE);

        // Show it
        new SwingWrapper<>(chart).displayChart();
    }
}
