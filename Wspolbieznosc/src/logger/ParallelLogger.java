package logger;

import student.Student;

public class ParallelLogger implements Logger
{
    private static Logger[] loggers = new Logger[]
            {
                    new ConsoleLogger(),
            };

    @Override
    public void log( String status, Student student )
    {
        for ( Logger el : loggers )
            el.log( status, student );
    }
}
