package com.example.checkfx;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ypController implements Initializable {
    @FXML
    private TableColumn<String, String> Project;
    @FXML
    private TableColumn<String, String> status;
    @FXML
    private TableView<String> tv;
    @FXML
    private TableView<String> stv;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Project.setCellValueFactory(cellData -> cellData.getValue().isEmpty() ? null : new SimpleStringProperty(cellData.getValue()));
        ObservableList<String> list2 = FXCollections.observableArrayList();
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
            PreparedStatement ps = con.prepareStatement("SELECT title, status FROM Logininfo, Project WHERE Logininfo.pid = Project.pid AND username = ?");
            ps.setString(1, StudentController.User);
            ResultSet rrss = ps.executeQuery();

           if(rrss.next()) {
                list1.add(rrss.getString(1) + "                                                                            " + rrss.getString(2) );
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tv.setItems(list1);
    }
}

