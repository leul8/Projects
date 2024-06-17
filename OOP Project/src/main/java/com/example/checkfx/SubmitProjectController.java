package com.example.checkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.example.checkfx.User.rs;

public class SubmitProjectController {

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem mi1;

    @FXML
    private MenuItem mi2;

    @FXML
    private Label pt;

    @FXML
    private Label s;

    @FXML
    private Label sd;

    @FXML
    private Button submit;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private Label tm;
    @FXML
    void submit(ActionEvent event){
        String ProjectTitle = t1.getText();
        String team = t2.getText();
        List<String> TeamMembers = List.of(team.split(","));
        String Synopsis = t3.getText();
        String SubmissionDate = t4.getText();
        System.out.println(Synopsis);
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
            Random rand = new Random();
            int i = rand.nextInt(1,500);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Project VALUES(" + i + ", '" + ProjectTitle + "', 'In Progress')");
            ps.executeUpdate();
            for (String name : TeamMembers) {
                ps = con.prepareStatement("UPDATE Logininfo SET pid = " + i + " WHERE username = '" + name + "'");
                ps.executeUpdate();
            }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Project Submitted successfully!");
                alert.showAndWait();

        }catch(SQLException | ClassNotFoundException e){
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
    void Back(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Student.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Student dashboard");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

}
