package com.example.checkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

import javafx.stage.Stage;

import static com.example.checkfx.User.rs;
import static javafx.scene.paint.Color.rgb;

public class SignInController  {
    @FXML
    private Label welcomeText;
    @FXML
    private PasswordField Password;

    @FXML
    private Button Signin;

    @FXML
    private TextField Username;
    @FXML
    private Label Userlabel;
    @FXML
    private Button button;
    @FXML
    private Label temp;
    @FXML
    private Button signup;
    void Student(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Student.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Student Dashboard");
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void Instructor(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Instructor.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Instructor Dashboard");
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void Coordinator(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Coordinator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Coordinator Dashboard");
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void Login(ActionEvent event) {
        User user1 = new User(Username.getText(),Password.getText());
        if(user1.authenticate(Username.getText(), Password.getText())){
            try {
                if (rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Signed in successfully!");
                    alert.showAndWait();
                    if (rs.getString(3).equals("Student")) {
                        Student();
                    } else if (rs.getString(3).equals("Instructor")) {
                        Instructor();
                    } else if (rs.getString(3).equals("Coordinator")) {
                        Coordinator();
                    }
                    System.out.println("Success!");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Wrong username or password. Please try again!");
                    alert.showAndWait();
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        }

            /*try{
                String hostname = "localhost";
                String sqlInstanceName = "LEULLOL\\JAVACONNECT";
                String sqlDatabase = "Login";
                String sqlUser = "sa";
                String sqlPassword = "leul1234";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                        + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                        +";encrypt=true;trustServerCertificate=true";
                String username = Username.getText();
                String password = Password.getText();
                java.sql.Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
                System.out.println("Connected to the database!");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Logininfo WHERE username = '" + username + "' AND password = '" + password + "'");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Signed in successfully!");
                    alert.showAndWait();
                    if(rs.getString(3).equals("Student")){
                       Student();
                    }
                   else if(rs.getString(3).equals("Instructor")){
                        Instructor();
                    }
                    else if(rs.getString(3).equals("Coordinator")){
                        Coordinator();
                    }
                    System.out.println("Success!");
                }
                else{
                    System.out.println("Wrong username or password");
                }}catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error connecting to the database");
            }catch(ClassNotFoundException e) {
                System.out.println(e);
            }
            catch(Exception e){
                System.out.println(e);
            }*/
    }

@FXML
    void signup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Sign up.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Sign up");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
}
}
