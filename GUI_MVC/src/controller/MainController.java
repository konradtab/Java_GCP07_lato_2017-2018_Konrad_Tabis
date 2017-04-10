package controller;

import application.Student;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    public Label ab = new Label( "About" );

    @FXML Menu menuAbout;
    @FXML StudentsController studentController;
    @FXML LogsController logsController;
    @FXML HistogramController histogramController;

    @Override
    public void initialize( URL location, ResourceBundle resources )
    {
        ab.setOnMouseClicked( event ->
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setTitle( "Informacje o programie" );
            alert.setHeaderText( "Ten program to GUI dla crawlera." );
            alert.setContentText( "Autor: Konrad Tabiś" );
            alert.showAndWait();
        } );
        menuAbout.setGraphic( ab );

        studentController.init( this );
        logsController.init( this );
        histogramController.init( this );
    }

    public void updateBar(ObservableList studentList)
    {
        histogramController.updateBar( studentList );
    }

    public void addLog( String status, Student st)
    {
        logsController.addLog( status, st );
    }

    public void close()
    {
        Platform.exit();
    }
}
