package com.example.checkfx;

import javafx.scene.control.Alert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements AuthenticationService{
    public static ResultSet rs;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public boolean authenticate(String username, String password) {
        try{
            String hostname = "localhost";
            String sqlInstanceName = "LEULLOL\\JAVACONNECT";
            String sqlDatabase = "Login";
            String sqlUser = "sa";
            String sqlPassword = "leul1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    +";encrypt=true;trustServerCertificate=true";
            java.sql.Connection con = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Logininfo WHERE username = '" + username + "' AND password = '" + password + "'");
            rs = ps.executeQuery();
            if(rs != null)
                return true;
            }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error connecting to the database");
        }catch(ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }
}
