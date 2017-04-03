package Gui_JavaFX;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Dell on 31.03.2017.
 */
public class CustomTableView extends AnchorPane {
    TableView<Student> table =new TableView<>();

    public CustomTableView(Stage stage){
        table.prefWidthProperty().bind(stage.widthProperty());

        TableColumn mark=new TableColumn("Mark");
        mark.setCellValueFactory(new PropertyValueFactory<Student,Double>("mark"));

        TableColumn fname=new TableColumn("First Name");
        fname.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));

        TableColumn lname=new TableColumn("Last Name");
        lname.setCellValueFactory(new PropertyValueFactory<Student,String>("lastname"));

        TableColumn age=new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<Student,Integer>("age"));

        table.getColumns().addAll(mark,fname,lname,age);

        this.getChildren().add(table);
    }
    void addStudent(ObservableList<Student> st)
    {
        table.setItems(st);
    }

    public Student removeStudent()
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Student st = table.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0)
            table.getItems().remove(selectedIndex);

        return st;
    }
}
