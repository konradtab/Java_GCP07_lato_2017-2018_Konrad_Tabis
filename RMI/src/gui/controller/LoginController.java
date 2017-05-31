package gui.controller;

import gui.application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Scene;

public class LoginController implements Initializable
{
    @FXML
    private TextField fieldUsername, fieldNewUsername;
    @FXML
    private PasswordField fieldPassword, fieldNewPassword;
    @FXML
    private Label labelError, labelNewError;

    List<User> usersList = new ArrayList<>();

    @Override
    public void initialize( URL location, ResourceBundle resources )
    {
        usersList.add( new User()
        {{
            this.setUsername( "a" );
            this.setPassword( "a" );
        }} );
    }

    @FXML
    public void btnLogInAction( ActionEvent event ) throws IOException
    {
        User u = new User()
        {{
            this.setUsername( fieldUsername.getText() );
            this.setPassword( fieldPassword.getText() );
        }};

        for ( User el : usersList )
        {
            if ( ( el.getUsername().equals( u.getUsername() ) ) && ( el.getPassword().equals( u.getPassword() ) ) )
            {
                ( ( Node ) event.getSource() ).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load( getClass().getResource( "/gui/view/MainView.fxml" ) );
                Stage stage = new Stage();
                Scene scene = new Scene( parent );
                stage.setScene( scene );
                stage.setTitle( "GUI MVC" );
                stage.show();
            } else
                labelError.setText( "Invalid username or password!" );
        }
    }

    @FXML
    public void btnSaveAction()
    {
        User u = new User()
        {{
            this.setUsername( fieldNewUsername.getText() );
            this.setPassword( fieldNewPassword.getText() );
        }};

        boolean used = false;

        for ( User el : usersList )
        {
            if ( el.getUsername().equals( u.getUsername() ) )
            {
                labelNewError.setText( "This username is already used!" );
                used = true;
            } else
                labelNewError.setText( "" );
        }

        if ( !used )
        {
            usersList.add( new User()
            {{
                this.setUsername( fieldNewUsername.getText() );
                this.setPassword( fieldNewPassword.getText() );
            }} );
            btnClearAction();
        }
    }

    @FXML
    public void btnClearAction()
    {
        fieldNewUsername.clear();
        fieldNewPassword.clear();
    }
}
