package com.example.checkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
public class SignUpController implements Initializable {
    @FXML
    private PasswordField supassword;
    @FXML
    private TextField suusername;
    @FXML
    private TextField pref;
    @FXML
    private ComboBox<String> cmbbx;
    @FXML
    private PasswordField cp;
    @FXML
    private String s;
    @FXML
    private Button signin;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Student","Instructor","Coordinator");
        cmbbx.setItems(list);
    }
    @FXML
    void select(ActionEvent event){
        s = cmbbx.getSelectionModel().getSelectedItem();
    }
    @FXML
    void signup(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sign up.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Sign up");
            stage.show();
            String hostname = "localhost";
            String sqlInstanceName = "LEULLOL\\JAVACONNECT";
            String sqlDatabase = "Login";
            String sqlUser = "sa";
            String sqlPassword = "leul1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    +";encrypt=true;trustServerCertificate=true";
            String username = suusername.getText();
            String password = String.valueOf(supassword.getText().hashCode());
            String confirmpassword = String.valueOf(cp.getText().hashCode());
            String usertype = s;
            String preference = pref.getText();
            if(password.equals(confirmpassword)){
                java.sql.Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
                System.out.println("Connected to the database!");
                PreparedStatement ps = con.prepareStatement("INSERT INTO Logininfo(username,password,usertype,pref) VALUES (?, ?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, usertype);
                ps.setString(4, preference);
                if(ps.executeUpdate() != 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Signed up successfully!, please click the sign in button to log in");
                    alert.showAndWait();
                }
            }
                else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please check your password!");
                alert.showAndWait();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void signin(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Sign in");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
