/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Employee;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import microsoft.sql.Types;

/**
 *
 * @author admin
 */
public class DeleteProductController {

    public static boolean deleteMilkTeaAndSizesByProductId(int idProduct) {
        Connection connection = connectSQL.Connection();
//        String sql = "DELETE FROM ProductDetail WHERE idProduct = ?";
        String sql = "UPDATE ProductSize SET statusProductSize=0  WHERE idProduct=?";

        try {
            // Xóa tất cả các bản ghi liên quan từ bảng ProductDetail trước
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ps.executeUpdate();
            ps.close();

            // Sau đó xóa từ bảng Product
//            sql = "DELETE FROM Product WHERE idProduct = ?";
            sql = "UPDATE Product SET statusProduct=0  WHERE idProduct=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ps.executeUpdate();
            ps.close();

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteToppingById(int idTopping) {
        Connection connection = connectSQL.Connection();
           String sql =  "UPDATE Topping SET statusTopping=0  WHERE idTopping=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTopping);
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
