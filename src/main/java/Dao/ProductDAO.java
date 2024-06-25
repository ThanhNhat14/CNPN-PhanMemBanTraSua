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
import Model.Manager.Product;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Product WHERE statusProduct = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String nameProduct = resultSet.getString("nameProduct");
                String imageProduct = resultSet.getString("imageProduct");
                int idCategory = resultSet.getInt("idCategory");
                String description = resultSet.getString("description");
                boolean statusProduct = resultSet.getBoolean("statusProduct");
                Product a = new Product(idProduct, nameProduct, imageProduct, idCategory, description, statusProduct);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Product getProductById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Product WHERE idProduct = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String nameProduct = resultSet.getString("nameProduct");
                String imageProduct = resultSet.getString("imageProduct");
                int idCategory = resultSet.getInt("idCategory");
                String description = resultSet.getString("description");
                boolean statusProduct = resultSet.getBoolean("statusProduct");
                Product a = new Product(idProduct, nameProduct, imageProduct, idCategory, description, statusProduct);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isExistProductByNameProductAndIdCategory(String theNameProduct, int theIdCategory) {
        // true tức là sản phẩm đó đã tồn tại, flase là chứ tồn tại
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Product WHERE nameProduct = ? AND idCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theNameProduct);
            preparedStatement.setInt(2, theIdCategory);
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

    public int insertProduct(Product a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Product(nameProduct, imageProduct, idCategory, description, statusProduct) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameProduct());
            preparedStatement.setString(2, a.getImageProduct());
            preparedStatement.setInt(3, a.getIdCategory());
            preparedStatement.setString(4, a.getDescription());
            preparedStatement.setBoolean(5, true);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm thành công.");
            System.out.println("Co " + kq + " dong trong Product bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
//    public int deleteProduct(int id) {
//        // ta xóa Product thì cx sẽ xóa luôn các ProductDetail tương ứng
//        // vì vây sẽ xóa ProductDetail xong mới xóa Product
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//            // Xóa các chi tiết sản phẩm tương ứng từ bảng ProductSize
//            String sqlDeleteProductSize = "DELETE FROM ProductSize WHERE idProduct = ?";
//            PreparedStatement preparedStatementDeleteProductSize = connection.prepareStatement(sqlDeleteProductSize);
//            preparedStatementDeleteProductSize.setInt(1, id);
//            int rowsAffectedProductDetail = preparedStatementDeleteProductSize.executeUpdate();
//            // Xóa sản phẩm từ bảng Product
//            String sqlDeleteProduct = "DELETE FROM Product WHERE idProduct = ?";
//            PreparedStatement preparedStatementDeleteProduct = connection.prepareStatement(sqlDeleteProduct);
//            preparedStatementDeleteProduct.setInt(1, id);
//            int rowsAffectedProduct = preparedStatementDeleteProduct.executeUpdate();
//            // Đảm bảo rằng cả hai lệnh xóa đều thành công
//            if (rowsAffectedProductDetail > 0 && rowsAffectedProduct > 0) {
//                kq = rowsAffectedProduct;
//                JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm và các chi tiết sản phẩm tương ứng thành công.");
//            } else {
//                JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công hoặc sản phẩm không tồn tại.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }

    public int deleteProduct(int id) {
        // ta xóa Product thì cx sẽ xóa luôn các ProductDetail tương ứng
        // vì vây sẽ xóa ProductDetail xong mới xóa Product
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            // Xóa các chi tiết sản phẩm tương ứng từ bảng ProductSize
            String sqlDeleteProductSize = "UPDATE ProductSize SET "
                    + "statusProductSize = 0 "
                    + "WHERE idProduct = ?";
            PreparedStatement preparedStatementDeleteProductSize = connection.prepareStatement(sqlDeleteProductSize);
            preparedStatementDeleteProductSize.setInt(1, id);
            int rowsAffectedProductDetail = preparedStatementDeleteProductSize.executeUpdate();
            // Xóa sản phẩm từ bảng Product
            String sqlDeleteProduct = "UPDATE Product SET "
                    + "statusProduct = 0 "
                    + "WHERE idProduct = ?";
            PreparedStatement preparedStatementDeleteProduct = connection.prepareStatement(sqlDeleteProduct);
            preparedStatementDeleteProduct.setInt(1, id);
            int rowsAffectedProduct = preparedStatementDeleteProduct.executeUpdate();
            // Đảm bảo rằng cả hai lệnh xóa đều thành công
            if (rowsAffectedProductDetail > 0 && rowsAffectedProduct > 0) {
                kq = rowsAffectedProduct;
                JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm và các chi tiết sản phẩm tương ứng thành công.");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công hoặc sản phẩm không tồn tại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateProduct(int id, Product a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Product SET "
                    + "nameProduct = ?, imageProduct = ?, idCategory = ?, description = ?"
                    + "WHERE idProduct = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameProduct());
            preparedStatement.setString(2, a.getImageProduct());
            preparedStatement.setInt(3, a.getIdCategory());
            preparedStatement.setString(4, a.getDescription());
            preparedStatement.setInt(5, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa sản phẩm thành công.");
            System.out.println("Co " + kq + " dong trong Product bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Product> findProductByName(String name) {
        ArrayList<Product> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Product WHERE nameProduct LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String nameProduct = resultSet.getString("nameProduct");
                String imageProduct = resultSet.getString("imageProduct");
                int idCategory = resultSet.getInt("idCategory");
                String description = resultSet.getString("description");
                boolean statusProduct = resultSet.getBoolean("statusProduct");
                Product a = new Product(idProduct, nameProduct, imageProduct, idCategory, description, statusProduct);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Product> findProductByCategory(int theIdCategory) {
        ArrayList<Product> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Product WHERE idCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIdCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String nameProduct = resultSet.getString("nameProduct");
                String imageProduct = resultSet.getString("imageProduct");
                int idCategory = resultSet.getInt("idCategory");
                String description = resultSet.getString("description");
                boolean statusProduct = resultSet.getBoolean("statusProduct");
                Product a = new Product(idProduct, nameProduct, imageProduct, idCategory, description, statusProduct);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
