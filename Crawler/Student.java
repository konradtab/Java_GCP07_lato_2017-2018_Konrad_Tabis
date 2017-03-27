package Crawler;
/**
 * Created by Dell on 19.03.2017.
 */

public class Student {
    private double mark;
    private String name;
    private String lastname;
    private int age;

    //gettery
    public double getMark(){
        return this.mark;
    }
    public String getName(){
        return this.name;
    }
    public String getLastname(){
        return this.lastname;
    }
    public int getAge(){
        return this.age;
    }

    //settery
    public void setMark(double mark){
        this.mark=mark;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }
    public void setAge(int age){
        this.age=age;
    }

    public boolean equals (Object Ex)
    {
        if(this==Ex)
            return true;
        if(Ex==null)
            return false;
        if ( getClass() != Ex.getClass() )
            return false;

        Student tmp = (Student) Ex;
        if ( age != tmp.age )
            return false;
        if ( name == null )
        {
            if ( tmp.name != null )
                return false;
        }
        else
        if ( !name.equals( tmp.name ) )
            return false;

        if ( lastname == null )
        {
            if ( tmp.lastname != null )
                return false;
        }
        else
        if ( !lastname.equals( tmp.lastname ) )
            return false;

        if ( Double.doubleToLongBits( mark ) != Double.doubleToLongBits( tmp.mark ) )
            return false;

        return true;
    }
    public int hashCode()
    {
        final int primo=31;
        int score=1;

        score=primo*score+age;
        score=primo*score+((name==null)?0:name.hashCode());
        score=primo*score+((lastname==null)?0:lastname.hashCode());

        long temp;
        temp = Double.doubleToLongBits( mark );
        score = primo * score + (int) ( temp ^ ( temp >>> 32 ) );

        return score;
    }

}
