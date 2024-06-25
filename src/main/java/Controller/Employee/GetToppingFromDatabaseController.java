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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Employee.Product;
import Model.Employee.Topping;
import View.Employee.Employee;

/**
 *
 * @author admin
 */
public class GetToppingFromDatabaseController {
    
//    public static void getListTopping(JTable jTableDanhSach){
//        DefaultTableModel dtm =(DefaultTableModel) jTableDanhSach.getModel();
//        Connection ketNoi = DatabaseConnection.ketNoiDatabase();
//        String sql = "Select * From Topping";
//        Vector vt;
////        int count=1;
//        try {
//            PreparedStatement ps = ketNoi.prepareStatement(sql);
//            ResultSet rs =ps.executeQuery();
//            dtm.setRowCount(0);
//            while (rs.next()) {
//                vt= new Vector();
//                vt.add(rs.getInt("idTopping"));
//                vt.add(rs.getString("nameTopping"));
//                vt.add(rs.getString("priceTopping"));
//                
//                dtm.addRow(vt);
//            }
//            jTableDanhSach.setModel(dtm);
//            rs.close();
//            ps.close();
//            ketNoi.close();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
     public static void getListToppingShort(JTable jTableDanhSach){
        DefaultTableModel dtm =(DefaultTableModel) jTableDanhSach.getModel();
        Connection ketNoi = connectSQL.Connection();
        String sql = "Select * From Topping where statusTopping=1";
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
            dtm.setRowCount(0);
            while (rs.next()) {
                vt= new Vector();
                vt.add(rs.getInt("idTopping"));
                vt.add(rs.getString("nameTopping"));
                vt.add(rs.getString("priceTopping"));
                
                dtm.addRow(vt);
            }
            jTableDanhSach.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
      public static Topping getInfoTopping(int idTopping){
        Connection connection = connectSQL.Connection();
        String sql = "select * from Topping where idTopping=?";
        Topping topping = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTopping);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                topping = new Topping();
                topping.setIdTopping(rs.getInt("idTopping"));
                topping.setNameTopping(rs.getString("nameTopping"));
                topping.setImageTopping(rs.getString("imageTopping"));
                topping.setPriceTopping(rs.getInt("priceTopping"));
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetMilkTeaFromDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topping;
    }
    
    public static int getIdToppingByName(String nameTopping ){
       Connection connection = connectSQL.Connection();
       String sql = "select idTopping from Topping where nameTopping=?";
       int id=0;
       try {
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, nameTopping);
           ResultSet rs= ps.executeQuery();
           while(rs.next()){
              id =rs.getInt("idTopping");
           }
          return id;
       } catch (SQLException ex) {
           Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return 0;
    }
    
     
}
