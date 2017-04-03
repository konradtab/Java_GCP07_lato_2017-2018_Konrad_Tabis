package Gui_JavaFX;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * Created by Dell on 31.03.2017.
 */

public class CustomBarChart extends AnchorPane {
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Data mark2 = new XYChart.Data("2.0", 0);
    private XYChart.Data mark3 = new XYChart.Data("3.0", 0);
    private XYChart.Data mark35 = new XYChart.Data("3.5", 0);
    private XYChart.Data mark4 = new XYChart.Data("4.0", 0);
    private XYChart.Data mark45 = new XYChart.Data("4.5", 0);
    private XYChart.Data mark5 = new XYChart.Data("5.0", 0);

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);

    public CustomBarChart()
    {
        barChart.setTitle("Distribution of marks");
        xAxis.setLabel("Mark");
        yAxis.setLabel("Count");

        yAxis.setTickUnit(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(9);
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                if (object.doubleValue()%object.intValue() == 0.0)
                    return (object.intValue()) + "";
                else
                    return "";
            }

            @Override
            public Number fromString(String string) {

                return null;
            }
        });

        data.setName("Marks");
        this.getChildren().add(barChart);
    }

    public void uptadeData(int m2, int m3, int m35, int m4, int m45, int m5)
    {
        barChart.getData().removeAll(data);
        data.getData().removeAll(mark2, mark3, mark35, mark4, mark45, mark5);
        mark2 = new XYChart.Data("2.0", m2);
        mark3 = new XYChart.Data("3.0", m3);
        mark35 = new XYChart.Data("3.5", m35);
        mark4 = new XYChart.Data("4.0", m4);
        mark45 = new XYChart.Data("4.5", m45);
        mark5 = new XYChart.Data("5.0", m5);
        data.getData().add(mark2);
        data.getData().add(mark3);
        data.getData().add(mark35);
        data.getData().add(mark4);
        data.getData().add(mark45);
        data.getData().add(mark5);

        barChart.getData().addAll(data);
    }
}
