package monitor;

public class MonitorException extends Exception
{
    public MonitorException()
    {
        System.err.println( "Błędna liczba wątków" );
    }
}
