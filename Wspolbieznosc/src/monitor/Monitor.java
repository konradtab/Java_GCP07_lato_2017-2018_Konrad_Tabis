package monitor;

import crawler.Crawler;
import crawler.CrawlerException;
import crawler.OrderMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Monitor
{
    private List<String> FileList;
    private int ThreadNumber;
    public CustomThread[] tabThreads = null;
    public boolean looped = false;

    public Monitor( List<String> list, int n ) throws MonitorException
    {
        FileList = list;
        if (n < FileList.size() )
            throw ( new MonitorException() );
        else
            ThreadNumber = list.size();
    }

    List<EventStudent> lisAddSt = new ArrayList<>();
    public void studentAddedEvent(EventStudent st )
    {
        lisAddSt.add( st );
    }

    List<EventStudent> lisDelSt = new ArrayList<>();
    public void studentRemovedEvent(EventStudent st )
    {
        lisDelSt.add( st );
    }

    public synchronized void run() throws InterruptedException, IOException, CrawlerException
    {
        if ( tabThreads == null )
        {
            looped = true;
            tabThreads = new CustomThread[ThreadNumber];
            for (int i = 0; i < ThreadNumber; i++ )
            {
                tabThreads[i] = new CustomThread( FileList.get( i ), OrderMode.MARK );
                tabThreads[i].setName( "Crawler " + i );
                tabThreads[i].start();
            }
        }

    }

    public synchronized void cancel() throws InterruptedException
    {
        if ( tabThreads != null )
        {
            looped = false;
            for (int i = 0; i < ThreadNumber; i++ )
            {
                tabThreads[i].interrupt();
                tabThreads[i].join();
            }
        }
    }

    class CustomThread extends Thread
    {
        private Crawler cr;

        public CustomThread( String source, OrderMode md )
        {
            cr = new Crawler( source, md );
            cr.addIterationList( ( it ) ->
                    System.out.println( "\n" + getName() + " Iteracja: " + it ) );

            cr.addStudentAddList( ( st ) ->
            {
                for ( EventStudent el : lisAddSt )
                    el.handled( st );
            } );

            cr.addStudentDelList( ( st ) ->
            {
                for ( EventStudent el : lisDelSt )
                    el.handled( st );
            } );
        }

        public synchronized void run()
        {
            while (looped)
            {
                try
                {
                    cr.run();
                }
                catch ( CrawlerException e )
                {
                    e.printStackTrace();
                }
                catch ( InterruptedException e )
                {
                    System.out.println( "Przerwano" );
                }
                catch ( IOException e ) {}
            }
        }
    }

}
