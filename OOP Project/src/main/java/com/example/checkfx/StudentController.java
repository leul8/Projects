package com.example.checkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.SQLException;
import static com.example.checkfx.User.rs;

public class StudentController implements Initializable,ProjectSubmissionService {

    @FXML
    private Text label;
    @FXML
    private Label user;
    @FXML
    private MenuButton menu;

    @FXML
    private Button sap;

    @FXML
    private Button spr;

    @FXML
    private Button vpp;
    @FXML
    private MenuItem DeleteAccount;

    @FXML
    private MenuItem Logout;
    public static String User;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            User = rs.getString(1);
            user.setText(User);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    @FXML
    void Logout(ActionEvent event){
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
    @FXML
    void DeleteAccount(){
        try {
            String hostname = "localhost";
            String sqlInstanceName = "LEULLOL\\JAVACONNECT";
            String sqlDatabase = "Login";
            String sqlUser = "sa";
            String sqlPassword = "leul1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    + ";encrypt=true;trustServerCertificate=true";
            java.sql.Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!");
            PreparedStatement ps = con.prepareStatement("DELETE FROM Logininfo WHERE username = '" + User + "'");
            if(ps.executeUpdate() != 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Account Deleted successfully!");
                alert.showAndWait();
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    @FXML
    public void ViewPreviousProjects(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrevdoneProjects.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Previously done Projects");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void SubmitProjectTitle() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Submit a project.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Submit");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void yourprojects(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("YourProjects.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Your Projects");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

