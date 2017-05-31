package gui.controller.tabs;

import gui.controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import student.Student;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsController implements Serializable
{
    private transient MainController main;
    private transient ObservableList<String> logs = FXCollections.observableArrayList();
    @FXML private transient ListView<String> listView = new ListView<>(logs);
    transient SimpleDateFormat dateformat = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" );

    public void init( MainController mainController )
    {
        main = mainController;
        listView.setItems( logs );
    }

    public void addLog( String status, Student student )
    {
        logs.add( dateformat.format( new Date() ) + " [" + status + "] " + student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge() );
    }
}