package crawler;

import crawler.crawler.*;
import student.Student;
import student.StudentsParser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Crawler
{
    private String source;
    private Set<Student> students;
    private OrderMode mode;

    public Crawler(String src, OrderMode md)
    {
        source = src;
        mode = md;
        students = new HashSet();
    }

    private List<ListenerIteracji> LisIteracji = new ArrayList<ListenerIteracji>();
    public void addLisIteracji (ListenerIteracji i) {LisIteracji.add(i);}

    private List<ListenerStudentow> LisAddStudent = new ArrayList<ListenerStudentow>();
    public void addLisAddStudent (ListenerStudentow st) {LisAddStudent.add(st);}

    private List<ListenerStudentow> LisDelStudent = new ArrayList<ListenerStudentow>();
    public void addLisDelStudent (ListenerStudentow st) {LisDelStudent.add(st);}

    public List<Student> extractStudents (OrderMode md)
    {
        List<Student> posortowani = new ArrayList<Student>(students);
        switch (md)
        {
            case MARK:
                Collections.sort(posortowani, new PorownajOceny());
                System.out.println("Posortowane według oceny: ");
                break;

            case FIRST_NAME:
                Collections.sort(posortowani, new PorownajImienia());
                System.out.println("Posortowane według imienia: ");
                break;

            case LAST_NAME:
                Collections.sort(posortowani, new PorownajNazwiska());
                System.out.println("Posortowane według nazwiska: ");
                break;

            case AGE:
                Collections.sort(posortowani, new PorownajWiek());
                System.out.println("Posortowane według wieku: ");
                break;
        }
        return posortowani;
    }

    public double extractMarks (ExtremumMode md)
    {
        double extremum = 0;
        switch (md)
        {
            case MAX:
                extremum = Collections.max(students, new PorownajOceny()).getMark();
                break;

            case MIN:
                extremum = Collections.min(students, new PorownajOceny()).getMark();
                break;
        }
        return extremum;
    }

    public int extractAge (ExtremumMode md)
    {
        int extremum = 0;
        switch (md)
        {
            case MAX:
                extremum = Collections.max(students, new PorownajWiek()).getAge();
                break;

            case MIN:
                extremum = Collections.min(students, new PorownajWiek()).getAge();
                break;
        }
        return extremum;
    }

    public void run() throws CrawlerException, InterruptedException, IOException
    {
        int iteracja = 0;
        List<Student> nowiStudenci;
        boolean modified;
        while (true)
        {
            iteracja++;
            modified = false;
            if (source.contains("http://"))
                nowiStudenci = StudentsParser.parse(new URL(source));
            else if (source.contains(".txt"))
                nowiStudenci = StudentsParser.parse(new File(source));
            else throw new CrawlerException();

            for (int i=0; i<nowiStudenci.size(); i++)
            {
                if (!students.contains(nowiStudenci.get(i)))
                {
                    students.add(nowiStudenci.get(i));
                    for (ListenerStudentow el: LisAddStudent)
                        el.handled(nowiStudenci.get(i));

                    modified = true;
                }
            }

            Iterator<Student> it = students.iterator();
            while (it.hasNext())
            {
                Student pobrany = it.next();

                if (!nowiStudenci.contains(pobrany))
                {
                    for (ListenerStudentow el: LisDelStudent)
                        el.handled(pobrany);

                    it.remove();
                    modified = true;
                }
            }

            if (modified)
            {
                for (ListenerIteracji el: LisIteracji)
                    el.handled(iteracja);

                extractStudents(mode);
                Iterator<Student> ite = students.iterator();
                while (ite.hasNext())
                {
                    Student pobrany = ite.next();
                    System.out.println( pobrany.getMark() + " " + pobrany.getFirstName() + " " + pobrany.getLastName() + " " + pobrany.getAge());
                }

                System.out.println("Wiek: <" + extractAge(ExtremumMode.MIN) + ", " + extractAge(ExtremumMode.MAX) + ">");
                System.out.println("Ocena: <" + extractMarks(ExtremumMode.MIN) + ", " + extractMarks(ExtremumMode.MAX) + ">\n");
            }
            Thread.sleep(1000);
        }
    }

    private static class PorownajOceny implements Comparator<Student>
    {
        @Override
        public int compare(Student o1, Student o2){return Double.compare(o1.getMark(), o2.getMark());}
    }

    private static class PorownajImienia implements Comparator<Student>
    {
        @Override
        public int compare(Student o1, Student o2) {return o1.getFirstName().compareTo(o2.getFirstName());}
    }

    private static class PorownajNazwiska implements Comparator<Student>
    {
        @Override
        public int compare(Student o1, Student o2) {return o1.getLastName().compareTo(o2.getLastName());}
    }

    private static class PorownajWiek implements Comparator<Student>
    {
        @Override
        public int compare(Student o1, Student o2) {return Integer.compare(o1.getAge(), o2.getAge());}
    }
}