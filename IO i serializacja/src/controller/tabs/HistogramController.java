package controller.tabs;

import application.CustomStringConverter;
import application.Student;
import controller.MainController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class HistogramController
{
    private MainController main;

    private XYChart.Series data = new XYChart.Series();
    private XYChart.Data d1 = new XYChart.Data( "2.0", 0 );
    private XYChart.Data d2 = new XYChart.Data( "3.0", 0 );
    private XYChart.Data d3 = new XYChart.Data( "3.5", 0 );
    private XYChart.Data d4 = new XYChart.Data( "4.0", 0 );
    private XYChart.Data d5 = new XYChart.Data( "4.5", 0 );
    private XYChart.Data d6 = new XYChart.Data( "5.0", 0 );
    public int mark2_0 = 0, mark3_0 = 0, mark3_5 = 0, mark4_0 = 0, mark4_5 = 0, mark5_0 = 0;

    @FXML NumberAxis yAxis;
    @FXML BarChart<String, Number> markChart;

    public void init( MainController mainController )
    {
        main = mainController;

        yAxis.setTickLabelFormatter( new CustomStringConverter() );
        data.setName( "Marks" );
    }

    public void updateBar(ObservableList<Student> studentList)
    {
        mark2_0 = mark3_0 = mark3_5 = mark4_0 = mark4_5 = mark5_0 = 0;

        for ( Student el : studentList )
        {
            if ( el.getMark() == 2.0 ) mark2_0++;
            else if ( el.getMark() == 3.0 ) mark3_0++;
            else if ( el.getMark() == 3.5 ) mark3_5++;
            else if ( el.getMark() == 4.0 ) mark4_0++;
            else if ( el.getMark() == 4.5 ) mark4_5++;
            else if ( el.getMark() == 5.0 ) mark5_0++;
        }

        markChart.getData().removeAll( data );
        data.getData().removeAll( d1, d2, d3, d4, d5, d6 );
        d1 = new XYChart.Data( "2.0", mark2_0 );
        d2 = new XYChart.Data( "3.0", mark3_0 );
        d3 = new XYChart.Data( "3.5", mark3_5 );
        d4 = new XYChart.Data( "4.0", mark4_0 );
        d5 = new XYChart.Data( "4.5", mark4_5 );
        d6 = new XYChart.Data( "5.0", mark5_0 );
        data.getData().add( d1 );
        data.getData().add( d2 );
        data.getData().add( d3 );
        data.getData().add( d4 );
        data.getData().add( d5 );
        data.getData().add( d6 );

        markChart.getData().addAll( data );
    }
}
