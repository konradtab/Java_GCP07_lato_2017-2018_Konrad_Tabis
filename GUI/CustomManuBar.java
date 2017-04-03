package Gui_JavaFX;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;

/**
 * Created by Dell on 31.03.2017.
 */

//pasek menu
public class CustomManuBar extends MenuBar {

    public CustomManuBar(){
        MenuBar menuBar=new MenuBar();
        Menu program=new Menu("Program");

        MenuItem itemClose=new MenuItem("Close Ctrl+C");
        program.getItems().add(itemClose);
        Label about1 = new Label("About");

        itemClose.setOnAction(actionEvent-> Platform.exit());
        itemClose.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));

        about1.setOnMouseClicked(event->{
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Program informations");
            alert.setContentText("Author: Konrad Tabiś");
            alert.show();
        });
        Menu about=new Menu();
        about.setGraphic(about1);

        this.getMenus().addAll(program, about);
    }

}
