package logger;

import student.Student;

@FunctionalInterface
public interface Logger
{
    void log(String status, Student student);
}
