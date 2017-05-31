package gui.controller.tabs;

import crawler.CrawlerInterface;
import gui.controller.MainController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import student.Student;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StudentsController implements Serializable
{
    public transient final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private transient MainController main;
    @FXML transient TableView<Student> tableView;
    @FXML transient TableColumn columnMark, columnFirstName, columnLastName, columnAge;
    @FXML transient TextField fieldMark, fieldFirstName, fieldLastName, fieldAge;

    public void init( MainController mainController )
    {
        main = mainController;

        columnMark.setCellValueFactory( new PropertyValueFactory<Student, Double>( "Mark" ) );
        columnFirstName.setCellValueFactory( new PropertyValueFactory<Student, String>( "FirstName" ) );
        columnLastName.setCellValueFactory( new PropertyValueFactory<Student, String>( "LastName" ) );
        columnAge.setCellValueFactory( new PropertyValueFactory<Student, Integer>( "Age" ) );
        tableView.setItems( studentList );
    }

    @FXML
    private void addStudent()
    {
        Student st = new Student()
        {{
            this.setMark( Double.parseDouble( fieldMark.getText() ) );
            this.setFirstName( fieldFirstName.getText() );
            this.setLastName( fieldLastName.getText() );
            this.setAge( Integer.parseInt( fieldAge.getText() ) );
        }};
        studentList.add( st );

        fieldMark.clear();
        fieldFirstName.clear();
        fieldLastName.clear();
        fieldAge.clear();

        main.addLog( "ADDED", st );
        main.updateBar( studentList );
    }

    @FXML
    private void removeStudent()
    {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Student st = tableView.getSelectionModel().getSelectedItem();
        if( selectedIndex >= 0 )
            studentList.remove( st );

        main.addLog( "REMOVED", st );
        main.updateBar( studentList );
    }

    public void addCrSt( Student st )
    {
        studentList.add( st );
        main.addLog( "ADDED", st );
        main.updateBar( studentList );
    }

    public void delCrSt( Student st )
    {
        studentList.remove( st );
        main.addLog( "REMOVED", st );
        main.updateBar( studentList );
    }
}
