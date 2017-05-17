package loggers;

import application.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedLogger implements Logger, Closeable, Serializable
{
    private transient String fileName;
    private transient ObjectOutputStream oos = null;
    private transient FileOutputStream fout = null;
    private transient FileInputStream fint = null;
    private transient ObjectInputStream ois = null;

    public SerializedLogger( String name )
    {
        fileName = name;
        File sf = new File( fileName );

        //deserializacja do listy studentów z istniejacego pliku
        List<LoggedStudent> list = null;
        if ( sf.isFile() )
        {
            try
            {
                fint = new FileInputStream( fileName );
                ois = new ObjectInputStream( fint );
            }
            catch ( IOException e ) {e.printStackTrace();}

            list = listStudents();
            try
            {
                fint.close();
                ois.close();
            }
            catch ( IOException e ) {e.printStackTrace();}
        }

        //serializacja zdeserializowanych studentów
        try
        {
            fout = new FileOutputStream( fileName );
            oos = new ObjectOutputStream( fout );
            if ( list != null )
                for ( int i = 0; i < list.size(); i++ )
                    serialize( list.get( i ) );
        }
        catch ( IOException e ) {e.printStackTrace();}

        try
        {
            fint = new FileInputStream( fileName );
            ois = new ObjectInputStream( fint );
        }
        catch ( IOException e ) {e.printStackTrace();}
    }

    @Override
    public void log( String status, Student student )
    {

        LoggedStudent l_student = new LoggedStudent()
        {{
            this.setMark( student.getMark() );
            this.setFirstName( student.getFirstName() );
            this.setLastName( student.getLastName() );
            this.setAge( student.getAge() );
            if ( status.equals( "ADDED" ) )
                this.setStatus( Status.ADDED );
            else
                this.setStatus( Status.REMOVED );
        }};

        serialize( l_student );

    }

    public void serialize( LoggedStudent l_st )
    {
        try
        {
            oos.writeObject( l_st );
            oos.flush();
        }
        catch ( Exception e ) {e.printStackTrace();}
    }

    @Override
    public void close() throws IOException
    {
        oos.close();
        fout.close();
        fint.close();
        ois.close();
    }

    public List<LoggedStudent> listStudents()
    {
        List<LoggedStudent> loggedStudentsList = new ArrayList<>();

        try
        {
            LoggedStudent readCase = null;
            FileInputStream finta = new FileInputStream( fileName );
            ObjectInputStream oisa = new ObjectInputStream( finta );

            while ( true )
            {
                try
                {
                    readCase = ( LoggedStudent ) oisa.readObject();

                    if ( readCase != null )
                        loggedStudentsList.add( readCase );
                }
                catch ( EOFException e ) {break;}
            }
            oisa.close();
        }
        catch ( Exception ex ) {ex.printStackTrace();}

        return loggedStudentsList;
    }
}