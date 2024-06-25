/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.Manager.ProductSize;

/**
 *
 * @author Admin
 */
public class ProductSizeDAO {

    public ArrayList<ProductSize> getAllProductSize() {
        ArrayList<ProductSize> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM ProductSize WHERE statusProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProductDetail = resultSet.getInt("idProductSize");
                int idProduct = resultSet.getInt("idProduct");
                int idSize = resultSet.getInt("idSize");
                int price = resultSet.getInt("price");
                boolean statusProductSize = resultSet.getBoolean("statusProductSize");
                ProductSize a = new ProductSize(idProductDetail, idProduct, idSize, price, statusProductSize);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public boolean isDuplicateProductSize(int theIdProduct, int theIdSize) {
        // true tức là trùng, false là ko trùng
        // hàm này dùng trong khi sửa productSize
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM ProductSize WHERE idProduct = ? AND idSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIdProduct);
            preparedStatement.setInt(2, theIdSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            preparedStatement.close();
            connection.close();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertProductSize(ProductSize a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String checkSql = "SELECT COUNT(*) FROM ProductSize WHERE idProduct = ? AND idSize = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setInt(1, a.getIdProduct());
            checkStatement.setInt(2, a.getIdSize());
            ResultSet resultSetCheck = checkStatement.executeQuery();
            if (resultSetCheck.next() && resultSetCheck.getInt(1) == 0) {
                String sql = "INSERT INTO ProductSize(idProduct, idSize, price, statusProductSize) VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, a.getIdProduct());
                preparedStatement.setInt(2, a.getIdSize());
                preparedStatement.setInt(3, a.getPrice());
                preparedStatement.setBoolean(4, true);
                kq = preparedStatement.executeUpdate();
                System.out.println("Co " + kq + " dong trong ProductSize bi thay doi");
                JOptionPane.showMessageDialog(null, "Đã thêm chi tiết sản phẩm thành công.");
            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm với size này đã tồn tại.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ProductSize getProductSizeById(int theId) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM ProductSize WHERE idProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProductDetail = resultSet.getInt("idProductSize");
                int idProduct = resultSet.getInt("idProduct");
                int idSize = resultSet.getInt("idSize");
                int price = resultSet.getInt("price");
                boolean statusProductSize = resultSet.getBoolean("statusProductSize");
                ProductSize a = new ProductSize(idProductDetail, idProduct, idSize, price, statusProductSize);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public int deleteProductSize(int theID) {
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//            String sql = "DELETE FROM ProductSize WHERE idProductSize = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, theID);
//            kq = preparedStatement.executeUpdate();
//            System.out.println("Co " + kq + " dong trong ProductSize bi thay doi");
//            JOptionPane.showMessageDialog(null, "Đã xóa chi tiết sản phẩm thành công.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    
    public int deleteProductSize(int theID) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE ProductSize SET "
                    + "statusProductSize = 0 "
                    + "WHERE idProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theID);
            kq = preparedStatement.executeUpdate();
            System.out.println("Co " + kq + " dong trong ProductSize bi thay doi");
            JOptionPane.showMessageDialog(null, "Đã xóa chi tiết sản phẩm thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateProductSize(int theId, ProductSize a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE ProductSize SET "
                    + "price = ? "
                    + "WHERE idProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, a.getPrice());
            preparedStatement.setInt(2, theId);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa chi tiết sản phẩm thành công.");
            System.out.println("Co " + kq + " dong trong ProductSize bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<ProductSize> findProductSizeByProductName(String productName) {
        ArrayList<ProductSize> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT pd.* "
                    + "FROM Product p "
                    + "JOIN ProductSize pd ON p.idProduct = pd.idProduct "
                    + "WHERE p.nameProduct LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + productName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 int idProductDetail = resultSet.getInt("idProductSize");
                int idProduct = resultSet.getInt("idProduct");
                int idSize = resultSet.getInt("idSize");
                int price = resultSet.getInt("price");
                boolean statusProductSize = resultSet.getBoolean("statusProductSize");
                ProductSize a = new ProductSize(idProductDetail, idProduct, idSize, price, statusProductSize);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<ProductSize> findProductSizeByCategoryName(String categoryName) {
        ArrayList<ProductSize> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT pd.* " +
                     "FROM Product p " +
                     "JOIN Category c ON p.idCategory = c.idCategory " +
                     "JOIN ProductSize pd ON p.idProduct = pd.idProduct " +
                     "WHERE c.nameCategory LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + categoryName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 int idProductDetail = resultSet.getInt("idProductSize");
                int idProduct = resultSet.getInt("idProduct");
                int idSize = resultSet.getInt("idSize");
                int price = resultSet.getInt("price");
                boolean statusProductSize = resultSet.getBoolean("statusProductSize");
                ProductSize a = new ProductSize(idProductDetail, idProduct, idSize, price, statusProductSize);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public String findProductNameByIdProductSize(int theIdProductSize)
    {
        // hàm này sẽ giúp ta lấy được tên của sản phẩm thông qua idProductSize
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT p.nameProduct FROM ProductSize ps "
                    + "INNER JOIN Product p ON ps.idProduct = p.idProduct "
                    + "WHERE ps.idProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIdProductSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                String tenSanPham = resultSet.getNString("nameProduct");
                return tenSanPham;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String findSizeNameByIdProductSize(int theIdProductSize)
    {
        // hàm này giúp ta lấy đc tên ten size theo idProductSize
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT s.nameOfSize FROM Size s "
                    + "INNER JOIN ProductSize ps ON s.idSize = ps.idSize "
                    + "WHERE ps.idProductSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIdProductSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                String kq = resultSet.getString("nameOfSize");
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
