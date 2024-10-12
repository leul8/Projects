package com.example.checkfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class vpController implements Initializable {
    @FXML
    private TableColumn<String,String> Project;
    @FXML
    private TableView<String> tv;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Project.setCellValueFactory(cellData -> cellData.getValue().isEmpty() ? null : new SimpleStringProperty(cellData.getValue()));
        ObservableList<String> list1 = FXCollections.observableArrayList();
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
        tv.setItems(list1);
    }

}
