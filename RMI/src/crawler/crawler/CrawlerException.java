package crawler.crawler;

public class CrawlerException extends Exception
{
    public CrawlerException()
    {
        System.err.print("Nie skonfigurowany lub błędnie skonfigurowany adres.");
    }
}