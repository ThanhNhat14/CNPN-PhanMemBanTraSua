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
import View.Employee.Employee;

/**
 *
 * @author admin
 */
public class GetMilkTeaFromDatabaseController {

//    public static void getListMilkTeaDetail(JTable jTableDanhSach){
//        DefaultTableModel dtm =(DefaultTableModel) jTableDanhSach.getModel();
//        Connection ketNoi = DatabaseConnection.ketNoiDatabase();
//        String sql = "Select * From Product ";
//        Vector vt;
////        int count=1;
//        try {
//            PreparedStatement ps = ketNoi.prepareStatement(sql);
//            ResultSet rs =ps.executeQuery();
//            dtm.setRowCount(0);
//            while (rs.next()) {
//                vt= new Vector();
//                vt.add(rs.getInt("idProduct"));
//                vt.add(rs.getString("nameProduct"));
//                vt.add(rs.getString("description"));
//                vt.add(rs.getString("priceProduct"));
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
    public static void getListMilkTeaShort(JTable jTableDanhSach) {
        DefaultTableModel dtm = (DefaultTableModel) jTableDanhSach.getModel();
        Connection ketNoi = connectSQL.Connection();
        String sql = "SELECT pd.idProductSize, p.nameProduct, pd.idSize, pd.price "
                + "FROM Product p "
                + "INNER JOIN ProductSize pd ON p.idProduct = pd.idProduct "
                + "WHERE p.statusProduct = 1 AND pd.statusProductSize = 1";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("idProductSize"));
                row.add(rs.getString("nameProduct"));
                // Xử lý hiển thị idSize dựa trên giá trị trong cột idSize
                int idSize = rs.getInt("idSize");
                String size = (idSize == 1) ? "M" : "L";
                row.add(size);
                row.add(rs.getInt("price"));
                dtm.addRow(row);
            }
            jTableDanhSach.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Product getInfoMilkTea(int idProductDetail) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT p.*, pd.price "
                + "FROM Product p "
                + "INNER JOIN ProductSize pd ON p.idProduct = pd.idProduct "
                + "WHERE pd.idProductSize = ?";
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idProductDetail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setNameProduct(rs.getString("nameProduct"));
                product.setDescription(rs.getString("description"));
                product.setImageProduct(rs.getString("imageProduct"));
                product.setIdCategory(rs.getInt("idCategory"));
                // Lấy giá từ bảng ProductDetail
                int price = rs.getInt("price");
                product.setPriceProduct(price);
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetMilkTeaFromDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public static int getIdProductByName(String nameProduct) {
        Connection connection = connectSQL.Connection();
        String sql = "select idProduct from Product where nameProduct=?";
        int id = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nameProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idProduct");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static int getIdProductByProductSizeId(int idProductDetail) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idProduct = -1; // Giá trị mặc định nếu không tìm thấy

        try {
            connection = connectSQL.Connection();
            String sql = "SELECT idProduct FROM ProductSize WHERE idProductSize = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProductDetail);
            rs = ps.executeQuery();

            if (rs.next()) {
                idProduct = rs.getInt("idProduct");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Đóng tài nguyên
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return idProduct;
    }

}
