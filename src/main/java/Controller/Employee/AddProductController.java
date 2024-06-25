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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class AddProductController {

    public static void addMilkTea(String nameProduct, String imageProduct, int idCategory, String description) {
        Connection connection = connectSQL.Connection();
        String sql = "INSERT INTO Product(nameProduct, imageProduct, idCategory, description, statusProduct) VALUES (?, ?, ?, ?, 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nameProduct);
            ps.setString(2, imageProduct);
            ps.setInt(3, idCategory);
            ps.setString(4, description);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addMilkTeaWithSize(int idProduct, int idSize, int price) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = connectSQL.Connection();

            // Kiểm tra xem sản phẩm với idProduct và idSize đã tồn tại trong CSDL chưa
            String checkExistSql = "SELECT COUNT(*) AS count FROM ProductSize WHERE idProduct = ? AND idSize = ?";
            ps = connection.prepareStatement(checkExistSql);
            ps.setInt(1, idProduct);
            ps.setInt(2, idSize);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    // Nếu sản phẩm đã tồn tại, thực hiện cập nhật giá tiền
                    String updateSql = "UPDATE ProductSize SET price = ?, statusProductSize = 1 WHERE idProduct = ? AND idSize = ?";
                    ps = connection.prepareStatement(updateSql);
                    ps.setInt(1, price);
                    ps.setInt(2, idProduct);
                    ps.setInt(3, idSize);
                    ps.executeUpdate();
                } else {
                    // Nếu sản phẩm chưa tồn tại, thêm mới vào CSDL
                    String insertSql = "INSERT INTO ProductSize (idProduct, idSize, price, statusProductSize) VALUES (?, ?, ?, 1)";
                    ps = connection.prepareStatement(insertSql);
                    ps.setInt(1, idProduct);
                    ps.setInt(2, idSize);
                    ps.setInt(3, price);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
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
                ex.printStackTrace();
            }
        }
    }

    public static void addTopping(String name, String image, int price) {
        Connection connection = connectSQL.Connection();
        String sql = "Insert into Topping(nameTopping, imageTopping, priceTopping,statusTopping) VALUES (?,?,?,1)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setInt(3, price);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
