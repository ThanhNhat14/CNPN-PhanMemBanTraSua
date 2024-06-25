/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Employee;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import View.Employee.Employee;

/**
 *
 * @author admin
 */
public class SizeController {
 
    public static List<Integer> getSizes() {
    List<Integer> idSizes = new ArrayList<>();
    Connection ketNoi = connectSQL.Connection();
    String sql = "SELECT idSize FROM Size";
    try {
        PreparedStatement ps = ketNoi.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idSize = rs.getInt("idSize");
            idSizes.add(idSize);
        }
        rs.close();
        ps.close();
        ketNoi.close();
    } catch (SQLException ex) {
        Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return idSizes;
}
    
}
