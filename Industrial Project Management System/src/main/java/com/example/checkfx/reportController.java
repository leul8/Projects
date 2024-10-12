package com.example.checkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.checkfx.User.rs;
public class reportController implements Initializable {

    @FXML
    private Label l1;

    @FXML
    private Button submit;

    @FXML
    private TextField text;
    @FXML
    void retrievetext(ActionEvent event) {
        String s = text.getText();
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
            PreparedStatement ps = con.prepareStatement("UPDATE Logininfo SET report = ? WHERE username = (SELECT username FROM Logininfo WHERE pid = (SELECT pid FROM Logininfo WHERE username = ?) AND usertype = 'Instructor')");
            ps.setString(1, s);
            ps.setString(2, rs.getString(1));
            if(ps.executeUpdate() != 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Report submitted Successfully!");
                alert.showAndWait();
            }
        } catch(SQLException | ClassNotFoundException e){
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
    void Back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Student.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Student dashboard");
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            l1.setText(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}