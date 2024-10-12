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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.checkfx.User.rs;

public class CommController implements Initializable {

    @FXML
    private Button form;

    @FXML
    private Label in;

    @FXML
    private TextField infi;

    @FXML
    private Label l1;

    @FXML
    void gettext(ActionEvent event) {
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
            Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!");
            Random rand = new Random();
            int i = rand.nextInt(1, 500);
            PreparedStatement ps = con.prepareStatement("INSERT INTO ReviewingCommittee VALUES(?,'IP')");
            ps.setString(1, String.valueOf(i));
            ps.executeUpdate();
            String[] arr = infi.getText().split(",");
            for (String name : arr) {
                ps = con.prepareStatement("UPDATE Logininfo SET rid = ? WHERE username = ?");
                ps.setString(1, String.valueOf(i));
                ps.setString(2, name);
                ps.executeUpdate();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Reviewing Committee has been Formed Successfully!");
            alert.showAndWait();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
        @FXML
        void Back(ActionEvent event) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Instructor.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
                stage.setTitle("Instructor dashboard");
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
     }
     @FXML
    void Logout(ActionEvent event) {
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
             stage.setTitle("IPMS");
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



