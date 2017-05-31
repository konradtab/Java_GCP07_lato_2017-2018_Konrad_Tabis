package crawler;

import crawler.crawler.*;
import student.Student;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CrawlerInterface extends Remote
{
    void addLisIteracji( ListenerIteracji i ) throws RemoteException;
    void addLisAddStudent( ListenerStudentow st ) throws RemoteException;
    void addLisDelStudent( ListenerStudentow st ) throws RemoteException;
    List<Student> extractStudents( OrderMode md ) throws RemoteException;
    double extractMarks( ExtremumMode md ) throws RemoteException;
    int extractAge( ExtremumMode md ) throws RemoteException;
    void run() throws CrawlerException, InterruptedException, IOException, RemoteException;
}
