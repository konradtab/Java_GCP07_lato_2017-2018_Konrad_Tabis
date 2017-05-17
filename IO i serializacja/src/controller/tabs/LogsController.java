package controller.tabs;

import application.Student;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import loggers.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsController
{
    private MainController main;
    private ObservableList<String> logs = FXCollections.observableArrayList();
    @FXML private ListView<String> listView = new ListView<>(logs);
    SimpleDateFormat dateformat = new SimpleDateFormat( "dd.MM.yyyy_HH.mm.ss.SS" );

    TextLogger tl = new TextLogger( "textlogger.txt" );
    CompressedLogger cl = new CompressedLogger( "compressedlogger.zip" );
    SerializedLogger sl = new SerializedLogger( "serializedlogger.bin" );
    BinaryLogger bl = new BinaryLogger( "binarylogger.bin" );

    Logger[] loggers = new Logger[]{tl, cl, sl, bl};

    public void init( MainController mainController )
    {
        main = mainController;
        listView.setItems( logs );
    }

    public void addLog( String status, Student student )
    {
        logs.add( dateformat.format( new Date() ) + " [" + status + "] " + student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge() );
        for (Logger el: loggers)
            el.log( status, student );
    }

    public void closeAll() throws IOException
    {
        tl.close();
        cl.close();
        sl.close();
        bl.close();
    }
}