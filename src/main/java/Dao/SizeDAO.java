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
import Model.Manager.Size;

/**
 *
 * @author Admin
 */
public class SizeDAO {

    public ArrayList<Size> getAllSize() {
        ArrayList<Size> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Size";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSize = resultSet.getInt("idSize");
                String nameOfSize = resultSet.getString("nameOfSize");
//                float priceOfSize = resultSet.getFloat("priceOfSize");
                Size a = new Size(idSize, nameOfSize);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Size getSizeById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Size WHERE idSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSize = resultSet.getInt("idSize");
                String nameOfSize = resultSet.getString("nameOfSize");
//                float priceOfSize = resultSet.getFloat("priceOfSize");
                Size a = new Size(idSize, nameOfSize);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int countSizeByName(String nameSize)
    {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
             String sql = "SELECT COUNT(*) FROM Size WHERE nameOfSize = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kq = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int insertSize(Size a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Size(nameOfSize) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameOfSize());
//            preparedStatement.setFloat(2, a.getPriceOfSize());
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm size sản phẩm thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int deleteSize(int idSizeNeedToDelete) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            
            // xóa tất cả các productSize tương ứng vs size cần xóa
            String deleteProductSizeQuery = "DELETE FROM ProductSize WHERE idSize = ? ";
            PreparedStatement pstmtProductSize = connection.prepareStatement(deleteProductSizeQuery);
            pstmtProductSize.setInt(1, idSizeNeedToDelete);
            pstmtProductSize.executeUpdate();
            
            String sql = "DELETE FROM Size WHERE idSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idSizeNeedToDelete);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa size thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateSize(int theId, Size a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Size SET "
                    + "nameOfSize = ? "
                    + "WHERE idSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameOfSize());
            preparedStatement.setInt(2, theId);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa size sản phẩm thành công.");
            System.out.println("Co " + kq + " dong trong Size bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public Size getSizeByName(String name) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Size WHERE nameOfSize = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSize = resultSet.getInt("idSize");
                String nameOfSize = resultSet.getString("nameOfSize");
                Size a = new Size(idSize, nameOfSize);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
