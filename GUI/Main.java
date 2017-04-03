package Gui_JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600,600);

        Scene scene=new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        CustomManuBar customManuBar=new CustomManuBar();
        customManuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        ((GridPane) scene.getRoot()).getChildren().addAll(customManuBar);

        CustomTabPane tab = new CustomTabPane(primaryStage);
        gridPane.add(tab, 0, 1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
