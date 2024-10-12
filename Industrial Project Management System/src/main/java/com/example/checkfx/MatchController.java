package com.example.checkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.checkfx.User.rs;

public class MatchController implements Initializable {

    @FXML
    private ComboBox<String> cmbbx1;

    @FXML
    private ComboBox<String> cmbbx2;
    @FXML
    private Label l3;
    @FXML
    private Label l1;
    @FXML
    private Label l4;
    @FXML
    private Button mb;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            l1.setText(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> list1 = FXCollections.observableArrayList();
        ObservableList<String> list2 = FXCollections.observableArrayList();
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
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT p.title FROM Project AS p JOIN Logininfo AS l ON p.pid = l.pid WHERE l.usertype = 'student' AND NOT EXISTS (SELECT 1 FROM Logininfo AS i WHERE i.usertype = 'instructor' AND i.pid = l.pid) AND p.status = 'Accepted'");
            ResultSet rrss = ps.executeQuery();
            while(rrss.next()) {
                list1.add(rrss.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        cmbbx1.setItems(list1);
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
            PreparedStatement ps = con.prepareStatement("SELECT username,pref FROM Logininfo WHERE usertype = 'Instructor' AND pid is null");
            ResultSet rrss = ps.executeQuery();
            while(rrss.next()) {
                list2.add(rrss.getString(1) + ", " + rrss.getString(2));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        cmbbx2.setItems(list2);
    }
    @FXML
    void match(){
        String availableproject = cmbbx1.getSelectionModel().getSelectedItem();
        String[] s1 = cmbbx2.getSelectionModel().getSelectedItem().split(",");
        String availableinstructor = s1[0];
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
            PreparedStatement ps1 = con.prepareStatement("UPDATE Logininfo SET pid = ( SELECT pid FROM Project WHERE title = ? ) WHERE username = ?");
            //PreparedStatement ps2 = con.prepareStatement("UPDATE Project SET status = 'Accepted' WHERE pid = (SELECT Project.pid FROM Logininfo, Project WHERE Logininfo.pid = Project.pid AND title = ?)");
            //ps2.setString(1, availableproject);
            //ps2.executeUpdate();
            ps1.setString(1, availableproject);
            ps1.setString(2, availableinstructor);
            if(ps1.executeUpdate() != 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Project Matched Successfully!");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    @FXML
    void Back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Coordinator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Coordinator dashboard");
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
}

