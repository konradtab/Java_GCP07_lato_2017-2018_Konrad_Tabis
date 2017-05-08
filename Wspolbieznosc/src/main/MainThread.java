package main;


import crawler.CrawlerException;
import logger.ParallelLogger;
import monitor.Monitor;
import monitor.MonitorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainThread
{
    public static void main( String[] args ) throws IOException, InterruptedException, MonitorException, CrawlerException
    {
        List<String> FileList = new ArrayList<>();
        FileList.add( "students1.txt" );
        FileList.add( "students2.txt" );
        FileList.add( "students3.txt" );
        FileList.add( "students4.txt" );
        FileList.add( "students5.txt" );
        FileList.add( "students6.txt" );
        FileList.add( "students7.txt" );
        FileList.add( "students8.txt" );
        FileList.add( "students9.txt" );
        FileList.add( "students10.txt" );

        ParallelLogger parLog = new ParallelLogger();

        Monitor monit = new Monitor( FileList, 10 );

        monit.studentAddedEvent( (st ) ->
        {
            parLog.log( "ADDED", st );
            System.out.println( "" );
        } );

        monit.studentRemovedEvent( (st ) ->
        {
            parLog.log( "REMOVED", st );
            System.out.println( "" );
        } );

        monit.run();
        Thread.sleep( 30000 );
        monit.cancel();

    }
}