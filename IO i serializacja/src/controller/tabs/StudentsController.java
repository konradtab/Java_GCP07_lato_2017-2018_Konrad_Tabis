package controller.tabs;

import application.Student;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;

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
        main.updateBar(studentList);
    }

    @FXML
    private void removeStudent()
    {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Student st = tableView.getSelectionModel().getSelectedItem();
        if ( selectedIndex >= 0 )
            tableView.getItems().remove( selectedIndex );

        main.addLog( "REMOVED", st );
        main.updateBar(studentList);
    }
}
