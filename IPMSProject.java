/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ipms.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author leul
 */
public class IPMSProject {

    public static void main(String[] args) throws ClassNotFoundException {
        String hostname = "localhost";
            String sqlInstanceName = "LEULLOL\\JAVACONNECT"; //computer name 
            String sqlDatabase = "Login";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "leul1234"; //passwrod sa account=
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
   +";encrypt=true;trustServerCertificate=true";
            try{
            Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!"); 
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error connecting to the databse");
            }
    }
}
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaApplication1 {

    public static void main(String[] args) throws ClassNotFoundException {
  
            String hostname = "localhost";
            String sqlInstanceName = "LEULLOL\\JAVACONNECT"; //computer name 
            String sqlDatabase = "Login";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "leul1234"; //passwrod sa account=
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
   +";encrypt=true;trustServerCertificate=true";
            try{
            Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connected to the database!"); 
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error connecting to the databse");
            }
    }
}
*/