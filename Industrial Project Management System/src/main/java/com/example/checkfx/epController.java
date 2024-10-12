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
import kotlin.jvm.JvmStatic;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static com.example.checkfx.User.rs;
public class epController implements Initializable{

    @FXML
    private Button accept;

    @FXML
    private ComboBox<String> cmbbx;

    @FXML
    private Label l1;
    @FXML
    private TextArea text;
    @FXML
    private Button reject;
    static int Count1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            l1.setText(rs.getString(1));
        }catch(SQLException e){
            System.out.println(e);
        }
        ObservableList<String> list = FXCollections.observableArrayList();
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Logininfo WHERE username = ? AND pid IS NULL");
            ps.setString(1, rs.getString(1));
            ResultSet rss = ps.executeQuery();
            if(rss.next()) {
                //SELECT * FROM Logininfo WHERE username = ? AND pid IS NULL
                ps = con.prepareStatement("SELECT DISTINCT p.title FROM Project AS p JOIN Logininfo AS l ON p.pid = l.pid WHERE l.usertype = 'student' AND NOT EXISTS (SELECT 1 FROM Logininfo AS i WHERE i.usertype = 'instructor' AND i.pid = l.pid)");
                //SELECT title FROM Logininfo,Project WHERE Logininfo.pid = Project.pid AND username = ?
                //ps.setString(1, rs.getString(1));
                ResultSet rrss = ps.executeQuery();
                while (rrss.next()) {
                    list.add(rrss.getString(1));
                }
                cmbbx.setItems(list);
            }
            else{
                //SELECT * FROM Logininfo WHERE username = ? AND pid IS NULL
                ps = con.prepareStatement("SELECT title FROM Logininfo,Project WHERE Logininfo.pid = Project.pid AND username = ?");
                //SELECT title FROM Logininfo,Project WHERE Logininfo.pid = Project.pid AND username = ?
                ps.setString(1, rs.getString(1));
                ResultSet rrss = ps.executeQuery();
                while (rrss.next()) {
                    list.add(rrss.getString(1));
                }
                cmbbx.setItems(list);
            }
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }

    }
    @FXML
    void accept(){
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
            PreparedStatement ps = con.prepareStatement("UPDATE project SET status = 'IP' WHERE title = ?");
            ps.setString(1, cmbbx.getSelectionModel().getSelectedItem());
            ps.executeUpdate();
            if(ps.executeUpdate() != 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Project Accepted Successfully!");
                alert.showAndWait();
                Count1++;
            }
            if(Count1 == 3){
                ps = con.prepareStatement("UPDATE ReviewingCommittee SET status = 'Y'");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE project SET status = 'Accepted' WHERE title = ?");
                ps.setString(1, cmbbx.getSelectionModel().getSelectedItem());
                ps.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Project has now been Accepted by all members of the Reviewing Committee!");
                alert.showAndWait();
            }
            //ps = con.prepareStatement(" SELECT status FROM ReviewingCommittee");
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    @FXML
    void reject(){
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
            PreparedStatement ps = con.prepareStatement("UPDATE project SET status = 'Rejected' WHERE title = ?");
            ps.setString(1, cmbbx.getSelectionModel().getSelectedItem());
            if(ps.executeUpdate() != 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Project Rejected Successfully!");
                alert.showAndWait();
            }
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    @FXML
    void editlabel(ActionEvent event){
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
            PreparedStatement ps = con.prepareStatement("SELECT synopsis, date, pid FROM Project WHERE title = ?");
            ps.setString(1, cmbbx.getSelectionModel().getSelectedItem());
            ResultSet rrss = ps.executeQuery();
            if(rrss.next()) {
                text.setText("Synopsis: " + rrss.getString(1) + "\n" + "Submission Date: " + rrss.getString(2) + "\n" + "Project Id: " + rrss.getString(3));
            }
        } catch(SQLException | ClassNotFoundException e){
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
}

