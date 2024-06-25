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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import Model.Manager.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO {

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Category WHERE statusCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String nameCategory = resultSet.getString("nameCategory");
                boolean statusCategory = resultSet.getBoolean("statusCategory");
                Category a = new Category(idCategory, nameCategory, statusCategory);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Category getCategoryById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Category WHERE idCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String nameCategory = resultSet.getString("nameCategory");
                boolean statusCategory = resultSet.getBoolean("statusCategory");
                Category a = new Category(idCategory, nameCategory, statusCategory);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countCategoryByName(String categoryName) {
        // hàm này sẽ kiểm tra xem một category đã tồn tại trong csdl hay chưa
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Category WHERE nameCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kq = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int insertCategory(Category a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Category (nameCategory, statusCategory) "
                    + "VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameCategory());
            preparedStatement.setBoolean(2, true);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm loại sản phẩm thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }


//    public int deleteCategory(int categoryIdToDelete) {
//        // khi ta xóa đi một thể loại thì nó cx xóa đi tất cả sản phẩm của thể loại đó và tất cả productSize
//
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//
//            // xóa tất cả productSize tương ứng
//            String deleteProductSizeQuery = "DELETE FROM ProductSize WHERE idProduct IN (SELECT idProduct FROM Product WHERE idCategory = ?)";
//            PreparedStatement pstmtProductSize = connection.prepareStatement(deleteProductSizeQuery);
//            pstmtProductSize.setInt(1, categoryIdToDelete);
//            pstmtProductSize.executeUpdate();
//
//            // Xóa các bản ghi trong bảng Product có idCategory tương ứng
//            String deleteProductQuery = "DELETE FROM Product WHERE idCategory = ?";
//            PreparedStatement pstmtProduct = connection.prepareStatement(deleteProductQuery);
//            pstmtProduct.setInt(1, categoryIdToDelete);
//            pstmtProduct.executeUpdate();
//
//            String sql = "DELETE FROM Category WHERE idCategory = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, categoryIdToDelete);
//            kq = preparedStatement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Đã xóa một loại sản phẩm thành công.");
//            System.out.println("Co " + kq + " dong trong category bi thay doi.");
//
//            pstmtProductSize.close();
//            pstmtProduct.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    
    public int deleteCategory(int categoryIdToDelete) {
        // khi ta xóa đi một thể loại thì nó cx xóa đi tất cả sản phẩm của thể loại đó và tất cả productSize

        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();

            // xóa tất cả productSize tương ứng
            String deleteProductSizeQuery = "UPDATE ProductSize SET "
                    + "statusProductSize = 0 "
                    + "WHERE idProduct IN (SELECT idProduct FROM Product WHERE idCategory = ?)";
            PreparedStatement pstmtProductSize = connection.prepareStatement(deleteProductSizeQuery);
            pstmtProductSize.setInt(1, categoryIdToDelete);
            pstmtProductSize.executeUpdate();

            // Xóa các bản ghi trong bảng Product có idCategory tương ứng
            String deleteProductQuery = "UPDATE Product SET "
                    + "statusProduct = 0 "
                    + "WHERE idCategory = ?";
            PreparedStatement pstmtProduct = connection.prepareStatement(deleteProductQuery);
            pstmtProduct.setInt(1, categoryIdToDelete);
            pstmtProduct.executeUpdate();

            String sql = "UPDATE Category SET "
                    + "statusCategory = 0 "
                    + "WHERE idCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryIdToDelete);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa một loại sản phẩm thành công.");
            System.out.println("Co " + kq + " dong trong category bi thay doi.");

            pstmtProductSize.close();
            pstmtProduct.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updatecategory(int id, Category a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Category SET "
                    + "nameCategory = ? "
                    + "WHERE idCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameCategory());
            preparedStatement.setInt(2, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa loại sản phẩm thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Category> findCategorybyName(String name) {
        ArrayList<Category> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Category WHERE nameCategory LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String nameCategory = resultSet.getString("nameCategory");
                boolean statusCategory = resultSet.getBoolean("statusCategory");
                Category a = new Category(idCategory, nameCategory, statusCategory);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Category getCategoryByName(String name) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Category WHERE nameCategory = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String nameCategory = resultSet.getString("nameCategory");
                boolean statusCategory = resultSet.getBoolean("statusCategory");
                Category a = new Category(idCategory, nameCategory, statusCategory);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // phương thức truy vấn idCategory và nameCategory, dùng cho vẽ biểu đồ
    public Map<Integer, String> queryCategoryMapping() {
        Map<Integer, String> idToNameMap = new HashMap<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT idCategory, nameCategory FROM Category";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("idCategory");
                String nameCategory = resultSet.getString("nameCategory");
                idToNameMap.put(idCategory, nameCategory);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idToNameMap;
    }

}
