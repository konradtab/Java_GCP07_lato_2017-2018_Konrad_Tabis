package crawler.loggers;

import student.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConsoleLogger extends UnicastRemoteObject implements Logger
{
    public ConsoleLogger() throws RemoteException
    {
    }

    @Override
    public void log( String status, Student student ) throws RemoteException
    {
        System.out.print( "\n" + status + " " + student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge() );
    }
}