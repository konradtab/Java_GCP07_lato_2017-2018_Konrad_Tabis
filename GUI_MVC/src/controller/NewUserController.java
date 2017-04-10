package controller;

import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Dell on 09.04.2017.
 */
public class NewUserController  {
    ObservableList<String> sexStatusList= FXCollections.observableArrayList("Man","Woman");
    @FXML
    Label infoLabel;
    @FXML
    TextField loginTextField,ageTextField,locationTextField;
    @FXML
    ChoiceBox sexChoiceBox;
    @FXML
    private void initialize(){
        sexChoiceBox.setItems(sexStatusList);
    }
    @FXML
    PasswordField passwordTextField;

    List<User> usersList = new ArrayList<>();


    @FXML
    public void newUserButtonClick(ActionEvent event) throws IOException {
        User u=new User()
        {{
           this.setUsername(loginTextField.getText());
           this.setPassword(passwordTextField.getText());
           try {
               this.setAge(Integer.parseInt(ageTextField.getText()));
           } catch (NumberFormatException e) {
               infoLabel.setText("Wrong Age");
           }
           this.setSex((String)sexChoiceBox.getValue());
           this.setAddress(locationTextField.getText());
        }};

        boolean used = false;

        for ( User el : usersList )
        {
            if ( el.getUsername().equals( u.getUsername() ) )
            {
               infoLabel.setText( "This username is already used!" );
                used = true;
            } else
                infoLabel.setText( "" );
        }

        if ( !used )
        {
            usersList.add(u);

            ( (Node) event.getSource() ).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load( getClass().getResource( "/view/MainView.fxml" ) );
            Stage stage = new Stage();
            Scene scene = new Scene( parent );
            stage.setScene( scene );
            stage.setTitle( "GUI MVC" );
            stage.show();
        }
    }
    public void clearButtonClick(){
        loginTextField.clear();
        passwordTextField.clear();
        ageTextField.clear();
        infoLabel.setText("");
        locationTextField.clear();
    }
    public void cancelButtonClick(ActionEvent event) throws IOException{
        ( (Node) event.getSource() ).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load( getClass().getResource( "/view/LoginView.fxml" ) );
        Stage stage = new Stage();
        Scene scene = new Scene( parent );
        stage.setScene( scene );
        stage.setTitle( "GUI MVC" );
        stage.show();
    }

}
