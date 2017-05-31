package gui.loggers;

import crawler.loggers.Logger;
import gui.controller.tabs.StudentsController;
import javafx.application.Platform;
import student.Student;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GuiLogger extends UnicastRemoteObject implements Logger
{
    StudentsController stctrl;

    public GuiLogger( StudentsController sc ) throws RemoteException
    {
        stctrl = sc;
    }

    @Override
    public void log( String status, Student student ) throws RemoteException
    {
        Platform.runLater( () ->
        {
            switch( status )
            {
                case "DODANO":
                    stctrl.addCrSt( student );
                    break;
                case "USUNIETO":
                    stctrl.delCrSt( student );
                    break;
            }
        } );
    }
}
