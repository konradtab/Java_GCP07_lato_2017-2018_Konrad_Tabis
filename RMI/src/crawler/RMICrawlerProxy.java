package crawler;

import crawler.crawler.*;
import student.Student;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMICrawlerProxy extends UnicastRemoteObject implements CrawlerInterface
{
    private Crawler crawler;
    public RMICrawlerProxy() throws RemoteException
    {
        crawler = new Crawler( "students.txt", OrderMode.MARK );
    }

    @Override
    public void addLisIteracji( ListenerIteracji i ) throws RemoteException
    {
        crawler.addLisIteracji( i );
    }

    @Override
    public void addLisAddStudent( ListenerStudentow st ) throws RemoteException
    {
        crawler.addLisAddStudent( st );
    }

    @Override
    public void addLisDelStudent( ListenerStudentow st ) throws RemoteException
    {
        crawler.addLisDelStudent( st );
    }

    @Override
    public List<Student> extractStudents( OrderMode md ) throws RemoteException
    {
        return crawler.extractStudents( md );
    }

    @Override
    public double extractMarks( ExtremumMode md ) throws RemoteException
    {
        return crawler.extractMarks( md );
    }

    @Override
    public int extractAge( ExtremumMode md ) throws RemoteException
    {
        return crawler.extractAge( md );
    }

    @Override
    public void run() throws CrawlerException, InterruptedException, IOException, RemoteException
    {
        crawler.run();
    }
}
