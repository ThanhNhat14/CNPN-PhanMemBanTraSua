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
import Model.Manager.Topping;

/**
 *
 * @author Admin
 */
public class ToppingDAO {
    
    public ArrayList<Topping> getAllTopping() {
        ArrayList<Topping> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Topping WHERE statusTopping = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                int idTopping = resultSet.getInt("idTopping");
                String nameTopping = resultSet.getString("nameTopping");
                int priceTopping = resultSet.getInt("priceTopping");
                String imageTopping = resultSet.getString("imageTopping");
                boolean statusTopping = resultSet.getBoolean("statusTopping");
                Topping a = new Topping(idTopping, nameTopping, priceTopping, imageTopping, statusTopping);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public boolean isExistToppingByName(String theNamTopping)
    {
        // true tức là sản phẩm đó đã tồn tại, flase là chứ tồn tại
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Topping WHERE nameTopping = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theNamTopping);
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
    
    public Topping getToppingById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Topping WHERE idTopping = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                int idTopping = resultSet.getInt("idTopping");
                String nameTopping = resultSet.getString("nameTopping");
                int priceTopping = resultSet.getInt("priceTopping");
                String imageTopping = resultSet.getString("imageTopping");
                boolean statusTopping = resultSet.getBoolean("statusTopping");
                Topping a = new Topping(idTopping, nameTopping, priceTopping, imageTopping, statusTopping);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int insertTopping(Topping a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Topping(nameTopping, priceTopping, imageTopping, statusTopping) "
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameTopping());
            preparedStatement.setInt(2, a.getPriceTopping());
            preparedStatement.setString(3, a.getImageTopping());
            preparedStatement.setBoolean(4, true);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm topping thành công.");
            System.out.println("Co " + kq + " dong trong Topping bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
//    public int deleteTopping(int id) {
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//            String sql = "DELETE FROM Topping WHERE idTopping = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            kq = preparedStatement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Đã xóa topping thành công.");
//            System.out.println("Co " + kq + " dong trong Topping bi thay doi.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    
    public int deleteTopping(int id) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Topping SET "
                    + "statusTopping = 0 "
                    + "WHERE idTopping = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa topping thành công.");
            System.out.println("Co " + kq + " dong trong Topping bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public int updateTopping(int id, Topping a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Topping SET "
                    + "nameTopping = ?, priceTopping = ?, imageTopping = ? "
                    + "WHERE idTopping = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameTopping());
            preparedStatement.setInt(2, a.getPriceTopping());
            preparedStatement.setString(3, a.getImageTopping());
//            preparedStatement.setString(4, a.getDescriptionTopping());
            preparedStatement.setInt(4, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa topping thành công.");
            System.out.println("Co " + kq + " dong trong Topping bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public ArrayList<Topping> findToppingByName(String name) {
        ArrayList<Topping> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Topping WHERE nameTopping LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                int idTopping = resultSet.getInt("idTopping");
                String nameTopping = resultSet.getString("nameTopping");
                int priceTopping = resultSet.getInt("priceTopping");
                String imageTopping = resultSet.getString("imageTopping");
                boolean statusTopping = resultSet.getBoolean("statusTopping");
                Topping a = new Topping(idTopping, nameTopping, priceTopping, imageTopping, statusTopping);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
