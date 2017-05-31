package crawler;

import crawler.crawler.CrawlerException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainCrawlerServer
{
    public static void main( String[] args ) throws CrawlerException, IOException, InterruptedException
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry( 2000 );
            RMICrawlerProxy cr = new RMICrawlerProxy();
            reg.rebind( "myCrawler", cr );
        }
        catch( RemoteException e ) {e.printStackTrace();}
    }
}
