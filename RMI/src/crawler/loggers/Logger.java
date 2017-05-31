package crawler.loggers;

import student.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;

@FunctionalInterface
public interface Logger extends Remote
{
    void log( String status, Student student ) throws RemoteException;
}



