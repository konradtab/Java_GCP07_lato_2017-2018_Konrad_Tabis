package crawler;

import student.Student;
import student.StudentParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Crawler {

    private Set<Student> students;
    private String path;
    private OrderMode omode;
    public boolean looped = true;


    public Crawler(String path, OrderMode omode)
    {
        this.path=path;
        this.omode=omode;
        students=new HashSet();
    }

    public void setPath(String path){
        this.path=path;
    }

    //comapratory ocen wieku imienia i nazwiska
    private static class MarkCompare implements Comparator<Student>{
        public int compare(Student first,Student second){return Double.compare(first.getMark(),second.getMark());}
    }

    private static class NameCompare implements Comparator<Student>{
        public int compare(Student first, Student second){return first.getName().compareTo(second.getName());}
    }

    private static class LastNameComapre implements Comparator<Student>{
        public int compare(Student first, Student second) {return first.getLastname().compareTo(second.getLastname());}
    }

    private static class AgeCompare implements Comparator<Student>{
        public int compare(Student first, Student second) {return Integer.compare(first.getAge(),second.getAge());}
    }

    private List<IterationListener> IterationList = new ArrayList<IterationListener>();
    public void addIterationList(IterationListener e){IterationList.add(e);};

    private List<StudentListener> StudentAddList=new ArrayList<StudentListener>();
    public void addStudentAddList (StudentListener stud){StudentAddList.add(stud);};

    private List<StudentListener> StudentDelList=new ArrayList<StudentListener>();
    public void addStudentDelList (StudentListener stud){StudentDelList.add(stud);};

    private List<StudentListener> StudentNomodList=new ArrayList<StudentListener>();
    public void addStudentNomodList(StudentListener stud){StudentNomodList.add(stud);};

//    private List<Student> extractStudents (OrderMode mode)
//    {
//        List<Student> sorted=new ArrayList<Student>(students);
//        switch(mode)
//        {
//            case MARK:
//                Collections.sort(sorted,new MarkCompare() );
//                System.out.println("Sortowanie - ocena:");
//                break;
//            case FIRST_NAME:
//                Collections.sort(sorted,new NameCompare());
//                System.out.println("Sortowanie - imie:");
//                break;
//            case LAST_NAME:
//                Collections.sort(sorted,new LastNameComapre());
//                System.out.println("Sortowanie - nazwisko:");
//                break;
//            case AGE:
//                Collections.sort(sorted,new AgeCompare());
//                System.out.println("Sortowanie - wiek");
//                break;
//        }
//        return sorted;
//    }
    double extractMark(ExternumMode mode)
    {
        double extreme = 0;
        switch(mode)
        {
            case MAX:
                extreme=Collections.max(students,new MarkCompare()).getMark();
                break;
            case MIN:
                extreme=Collections.min(students,new MarkCompare()).getMark();
                break;
        }
        return extreme;
    }
    int extractAge(ExternumMode mode)
    {
        int extreme = 0;
        switch(mode)
        {
            case MAX:
                extreme=Collections.max(students,new AgeCompare()).getAge();
                break;
            case MIN:
                extreme=Collections.min(students,new AgeCompare()).getAge();
                break;
        }
        return extreme;
    }

    public synchronized void run() throws CrawlerException, InterruptedException, IOException
    {
        int iteration=0;
        List<Student> StudentList = null;

        while(looped)
        {
            iteration++;

            if(path.contains("http://")) {
                StudentList = StudentParser.parse(new URL(path));
            }else if(path.contains(".txt")){
                StudentList=StudentParser.parse(new File(path));
            }else throw new CrawlerException();

            for(IterationListener i: IterationList)
                i.handled(iteration);

            for(int i=0; i<StudentList.size();i++)
            {
                if (!students.contains(StudentList.get(i)))
                {
                    students.add(StudentList.get(i));
                    for (StudentListener n: StudentAddList)
                        n.handled(StudentList.get(i));
                }
                else
                    for (StudentListener n: StudentNomodList)
                        n.handled(StudentList.get(i));
            }

            Iterator<Student> iter =students.iterator();
            while(iter.hasNext())
            {
                Student fresh = iter.next();

                if (!StudentList.contains(fresh))
                {
                    for (StudentListener n: StudentDelList)
                        n.handled(fresh);

                    iter.remove();
                }
            }

            //extractStudents(omode);
            //System.out.println("Wiek: <" + extractAge(ExternumMode.MIN) + ", "+ extractAge(ExternumMode.MAX)+">");
            //System.out.println("Ocena: <" + extractMark(ExternumMode.MIN)+","+extractMark(ExternumMode.MAX)+">");
            Thread.sleep(10000);
        }
    }
    public synchronized void postCancel()
    {
        looped = false;
    }
}

