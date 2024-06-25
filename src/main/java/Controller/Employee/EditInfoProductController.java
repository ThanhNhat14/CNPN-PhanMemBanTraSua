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

/**
 *
 * @author admin
 */
public class EditInfoProductController {
  public static boolean updateInfoMilkTeaDatabase(int idProduct, String nameProduct, String imageProduct, int priceProduct, String description, int idCategory) {
    Connection connection = connectSQL.Connection();
    String sqlProduct = "UPDATE Product SET nameProduct=?, imageProduct=?, description=?, idCategory=? WHERE idProduct=?";
    
    try {
        // Cập nhật thông tin chung trong bảng Product
        PreparedStatement psProduct = connection.prepareStatement(sqlProduct);
        psProduct.setString(1, nameProduct);
        psProduct.setString(2, imageProduct);
        psProduct.setString(3, description);
        psProduct.setInt(4, idCategory);
        psProduct.setInt(5, idProduct);
        psProduct.executeUpdate();
        psProduct.close();

        connection.close();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(EditInfoProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    public static boolean updatePriceForSize(int idProduct, int priceProduct, int size) {
    Connection connection = connectSQL.Connection();
    String sqlProductDetail = "UPDATE ProductSize SET price=? WHERE idProduct=? AND idSize=?";
    
    try {
        // Cập nhật giá theo size trong bảng ProductDetail
        PreparedStatement psProductDetail = connection.prepareStatement(sqlProductDetail);
        psProductDetail.setInt(1, priceProduct);
        psProductDetail.setInt(2, idProduct);
        psProductDetail.setInt(3, size);
        psProductDetail.executeUpdate();
        psProductDetail.close();

        connection.close();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(EditInfoProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    public static boolean updtaeInfoToppingDatabase(int idTopping, String nameTopping, String imageTopping, int priceTopping) {
         Connection connection = connectSQL.Connection();
        String sql ="Update Topping set nameTopping=?, imageTopping=?, priceTopping=? where idTopping=?";
    
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nameTopping);
            ps.setString(2, imageTopping);
            ps.setInt(3, priceTopping);
            ps.setInt(4, idTopping);
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
