package Crawler;

import java.io.IOException;

public class Program {

    public static void main(String[] args) throws InterruptedException, IOException, CrawlerException {
        final Logger[] loggers =new Logger[]
                {
                        new ConsoleLogger(),
                        new MailLogger()
                };
        Crawler central = new Crawler ("students.txt",OrderMode.AGE);
        central.addIterationList(iteration -> System.out.println("\nIteration " + iteration));
        central.addStudentAddList(stud -> {for(Logger el:loggers)el.log("Add",stud);});
        central.addStudentDelList(stud -> {for(Logger el:loggers)el.log("Delete",stud);});
        central.run();
    }
}
