package com.example.checkfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class tvController implements Initializable {
    @FXML
    private TableView<String> tv;
    @FXML
    private TableColumn<String,String> Project;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Project.setCellValueFactory(cellData -> cellData.getValue().isEmpty() ? null : new SimpleStringProperty(cellData.getValue()));
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
            Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM prevdoneproj");
            ResultSet r = ps.executeQuery();
            ;
           while(r.next()) {
               list.add(r.getString(1));
           }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        tv.setItems(list);

    }
}
