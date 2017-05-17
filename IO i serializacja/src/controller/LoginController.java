package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    private Properties prop = new Properties();
    private String fileName = "config.properties";

    @FXML private TextField fieldUsername, fieldNewUsername;
    @FXML private PasswordField fieldPassword, fieldNewPassword;
    @FXML private Label labelError, labelNewError;

    @Override
    public void initialize( URL location, ResourceBundle resources )
    {
        LoginController app = new LoginController();

        File configFile = new File( fileName );
        if ( !configFile.isFile() )
            app.createConfig( fileName );
    }

    @FXML
    public void btnLogInAction( ActionEvent event ) throws IOException
    {
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        InputStream input = null;

        try
        {
            File file = new File( fileName );
            input = new FileInputStream( fileName );

            if ( file.isFile() )
            {
                prop.load( input );

                if ( prop.containsValue( username ) )
                {
                    String file_password = prop.getProperty( username + "password" );
                    String hashedPassword = hashMD5( password );

                    if ( file_password.equals( hashedPassword ) )
                    {
                        ( ( Node ) event.getSource() ).getScene().getWindow().hide();
                        Parent parent = FXMLLoader.load( getClass().getResource( "/view/MainView.fxml" ) );
                        Stage stage = new Stage();
                        Scene scene = new Scene( parent );
                        stage.setScene( scene );
                        stage.setTitle( "GUI MVC" );
                        stage.show();
                    } else
                        labelError.setText( "Invalid username or password!" );
                } else
                    labelError.setText( "Invalid username or password!" );
            }
        }
        catch ( IOException ex ) {ex.printStackTrace();}
        finally
        {
            if ( input != null )
            {
                try
                {
                    input.close();
                }
                catch ( IOException e ) {e.printStackTrace();}
            }
        }
    }

    @FXML
    public void btnSaveAction()
    {
        String username = fieldNewUsername.getText();
        String password = fieldNewPassword.getText();

        FileWriter output = null;
        try
        {
            output = new FileWriter( fileName, true );
            prop.clear();

            if ( !prop.contains( username ) )
            {
                String hashedPassword = hashMD5( password );

                prop.setProperty( username, username );
                prop.setProperty( username + "password", hashedPassword );

                prop.store( output, null );
                labelNewError.setText( "" );
                fieldNewUsername.clear();
                fieldNewPassword.clear();
            } else
                labelNewError.setText( "This username is already used!" );

        }
        catch ( IOException io ) {io.printStackTrace();}
        finally
        {
            if ( output != null )
            {
                try
                {
                    output.close();
                }
                catch ( IOException e ) {e.printStackTrace();}
            }
        }
    }

    @FXML
    public void btnClearAction()
    {
        fieldNewUsername.clear();
        fieldNewPassword.clear();
    }

    private void createConfig( String fileName )
    {
        OutputStream output = null;

        try
        {
            output = new FileOutputStream( fileName );

            String password = "root";
            String hashedPassword = hashMD5( password );

            prop.setProperty( "root", "root" );
            prop.setProperty( "rootpassword", hashedPassword );
            prop.store( output, null );
        }
        catch ( IOException io ){io.printStackTrace();}
        finally
        {
            if ( output != null )
            {
                try
                {
                    output.close();
                }
                catch ( IOException e ){e.printStackTrace();}
            }
        }
    }


    public String hashMD5( String passwordToHash )
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            md.update( passwordToHash.getBytes() );
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for ( int i = 0; i < bytes.length; i++ )
            {
                sb.append( Integer.toString( ( bytes[i] & 0xff ) + 0x100, 16 ).substring( 1 ) );
            }
            generatedPassword = sb.toString();
        }
        catch ( NoSuchAlgorithmException e ) {e.printStackTrace();}

        return generatedPassword;
    }
}
