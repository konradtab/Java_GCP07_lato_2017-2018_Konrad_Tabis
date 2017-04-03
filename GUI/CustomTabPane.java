package Gui_JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Dell on 31.03.2017.
 */
public class CustomTabPane extends AnchorPane {
    public final ObservableList<Student> studentList = FXCollections.observableArrayList();
    CustomBarChart barChart;
    CustomListView loglistView;
    public static int mark2 = 0, mark3 = 0, mark35 = 0, mark4 = 0, mark45 = 0, mark5 = 0;
    public CustomTabPane(Stage stage){
        Tab students = new Tab();
        students.setText("Students");

        Tab log=new Tab();
        log.setText("Log");

        Tab histogram=new Tab();
        histogram.setText("Histogram");

        CustomTableView tableView =new CustomTableView(stage);
        loglistView=new CustomListView(stage);
        barChart=new CustomBarChart();

        tableView.addStudent(studentList);

        TextField mark=new TextField();
        mark.setText("Mark");
        mark.setMaxWidth(110);

        TextField name=new TextField();
        name.setText("First Name");
        name.setMaxWidth(110);

        TextField lname=new TextField();
        lname.setText("Last Name");
        lname.setMaxWidth(110);

        TextField age=new TextField();
        age.setText("Age");
        age.setMaxWidth(110);

        Button addButton = new Button("Add");
        addButton.setOnAction(e ->
        {
            Student student=new Student(){{
                this.setMark(Double.parseDouble(mark.getText()));
                this.setName(name.getText());
                this.setLastname(lname.getText());
                this.setAge(Integer.parseInt(age.getText()));
            }};
            loglistView.addStudent(student);
            studentList.add(student);

            mark.clear();
            name.clear();
            lname.clear();
            age.clear();

            updateBar();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e ->
        {
            removeStudent(tableView.removeStudent());
        });

        HBox hBox=new HBox();
        hBox.getChildren().addAll(mark,name,lname,age,addButton,deleteButton);
        hBox.setSpacing(3);
        hBox.setPadding(new Insets(5,0,0,15));

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(tableView,hBox);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        students.setContent(vBox);
        log.setContent(loglistView);
        histogram.setContent(barChart);

        tabPane.getTabs().addAll(students, log, histogram);
        tabPane.prefWidthProperty().bind(stage.widthProperty());
        tabPane.prefHeightProperty().bind(stage.heightProperty());
        this.getChildren().add(tabPane);
    }
    public void removeStudent(Student student)
    {
        loglistView.removeStudent(student);
        updateBar();
    }
    public void updateBar()
    {
        mark2 = mark3 = mark35 = mark4 = mark45 = mark5 = 0;

        for (Student el: studentList)
        {
            if (el.getMark() == 2.0) mark2++;
            else if (el.getMark() == 3.0) mark3++;
            else if (el.getMark() == 3.5) mark35++;
            else if (el.getMark() == 4.0) mark4++;
            else if (el.getMark() == 4.5) mark45++;
            else if (el.getMark() == 5.0) mark5++;
        }
        barChart.uptadeData(mark2, mark3, mark35, mark4, mark45, mark5);
    }
}
