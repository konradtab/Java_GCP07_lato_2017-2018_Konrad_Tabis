package loggers;

import application.Student;

@FunctionalInterface
public interface Logger
{
    void log(String status, Student student);
}



