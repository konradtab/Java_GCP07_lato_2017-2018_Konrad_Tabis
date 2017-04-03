package Gui_JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 01.04.2017.
 */
public class CustomListView extends AnchorPane {
    private ObservableList<String> names = FXCollections.observableArrayList();
    SimpleDateFormat format = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");

    public CustomListView(Stage stage)
    {
        ListView<String> listView = new ListView<>(names);
        listView.prefWidthProperty().bind(stage.widthProperty());
        listView.prefHeightProperty().bind(stage.heightProperty());
        this.getChildren().add(listView);
    }

    public void addStudent(Student student)
    {
        names.add(format.format(new Date()) + " [ADDED] " + student.getMark() + " " + student.getName() + " " + student.getLastname() + " " + student.getAge());
    }

    public void removeStudent(Student student)
    {
        names.add(format.format(new Date()) + " [REMOVED] " + student.getMark() + " " + student.getName() + " " + student.getLastname() + " " + student.getAge());
    }
}
