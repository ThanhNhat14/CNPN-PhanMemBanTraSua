/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ngocn
 */
public class connectSQL {

    public static Connection Connection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "database=TRASUA_TASU;"
                    + "user=sa;"
                    + "password=123;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            System.out.println("OK!!!");
            connection = DriverManager.getConnection(connectionUrl);
        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Deo OK!!!");
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {

        Connection();
        
    }

}
