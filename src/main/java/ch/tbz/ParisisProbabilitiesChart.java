package ch.tbz;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ParisisProbabilitiesChart {

    public static void main(String[] args) {
        // Hardcoded data based on the graph
        double[] groupSizes = {5, 10, 15, 20, 25, 30, 35, 40,
                               45, 50, 55, 60, 65, 70, 75, 80,
                               85, 90, 95, 100};
        double[] probabilities = {3, 11, 25, 40, 58, 70, 80, 88,
                                  92, 94, 96, 97, 98, 99, 99.5,
                99.7, 99.8, 100, 100, 100};

        // Create a Chart
        XYChart chart = new XYChartBuilder().width(800).height(600)
                .title("Parisis Probabilities")
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
