package gui.controller;

import crawler.CrawlerInterface;
import crawler.crawler.CrawlerException;
import crawler.crawler.ListenerIteracji;
import crawler.crawler.ListenerStudentow;
import crawler.loggers.ConsoleLogger;
import crawler.loggers.Logger;
import gui.controller.tabs.HistogramController;
import gui.controller.tabs.LogsController;
import gui.controller.tabs.StudentsController;
import gui.loggers.GuiLogger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import student.Student;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class MainController implements Initializable, Serializable
{
    public transient Label ab = new Label( "About" );

    @FXML Menu menuAbout;
    @FXML StudentsController studentController;
    @FXML LogsController logsController;
    @FXML HistogramController histogramController;

    public CrawlerInterface crawler;

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

        try
        {
            Registry myReg = LocateRegistry.getRegistry( "localhost", 2000 );
            crawler = ( CrawlerInterface ) myReg.lookup( "myCrawler" );

            final Logger[] loggers = new Logger[]
                {
                        new ConsoleLogger(),
                        new GuiLogger( studentController )
                };

            crawler.addLisIteracji( ( ListenerIteracji & Serializable ) ( iteracja ) ->
                    System.out.println( "\nIteracja: " + iteracja ) );

            crawler.addLisAddStudent( ( ListenerStudentow & Serializable ) ( st ) ->
            {
                for( Logger el : loggers )
                    try {el.log( "DODANO", st );}
                    catch( RemoteException e ) {e.printStackTrace();}
            } );

            crawler.addLisDelStudent( ( ListenerStudentow & Serializable ) ( st ) ->
            {
                for( Logger el : loggers )
                    try {el.log( "USUNIETO", st );}
                    catch( RemoteException e ) {e.printStackTrace();}
            } );

            Thread th = new Thread( () ->
            {
                try {crawler.run();}
                catch( CrawlerException | IOException | InterruptedException e ) {e.printStackTrace();}
            } );
            th.setDaemon( true );
            th.start();
        }
        catch( NotBoundException | IOException e ) {e.printStackTrace();}
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
