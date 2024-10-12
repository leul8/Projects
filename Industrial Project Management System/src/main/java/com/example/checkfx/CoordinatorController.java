package com.example.checkfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.checkfx.User.rs;

public class CoordinatorController implements Initializable {

    @FXML
    private Button match;
    @FXML
    private Label l1;
    @FXML
    private Button vp;
    @FXML
    void viewprojects(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewProjects.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("View Projects");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void match(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Match.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 550));
            stage.setTitle("Match");
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
             l1.setText(rs.getString(1));
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}

