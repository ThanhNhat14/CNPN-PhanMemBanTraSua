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
import Model.Manager.Ingredient;

/**
 *
 * @author Admin
 */
public class IngredientDAO {

    public ArrayList<Ingredient> getAllIngredient() {
        ArrayList<Ingredient> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Ingredient WHERE statusIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idIngredient = resultSet.getInt("idIngredient");
                String nameIngredient = resultSet.getString("nameIngredient");
                String unitIngredient = resultSet.getString("unitIngredient");
                float quantityIngredient = resultSet.getFloat("quantityIngredient");
                boolean statusIngredient = resultSet.getBoolean("statusIngredient");
                Ingredient a = new Ingredient(idIngredient, nameIngredient, unitIngredient, quantityIngredient, statusIngredient);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean isExistIngredient(String theNamIngredient) {
        // true là nguyên liệu đã tồn tại, flase là chưa tồn tại
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Ingredient WHERE nameIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theNamIngredient);
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

    public int insertIngredient(Ingredient a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Ingredient(nameIngredient, unitIngredient, quantityIngredient, statusIngredient) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameIngredient());
            preparedStatement.setString(2, a.getUnitIngredient());
            preparedStatement.setFloat(3, a.getQuantityIngredient());
            preparedStatement.setBoolean(4, true);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm nguyên liệu thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public Ingredient getIngredientById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Ingredient WHERE idIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idIngredient = resultSet.getInt("idIngredient");
                String nameIngredient = resultSet.getString("nameIngredient");
                String unitIngredient = resultSet.getString("unitIngredient");
                float quantityIngredient = resultSet.getFloat("quantityIngredient");
                boolean statusIngredient = resultSet.getBoolean("statusIngredient");
                Ingredient a = new Ingredient(idIngredient, nameIngredient, unitIngredient, quantityIngredient, statusIngredient);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public int deleteIngredientById(int id) {
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//            String sql = "DELETE FROM Ingredient WHERE idIngredient = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            kq = preparedStatement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Đã xóa nguyen liệu thành công.");
//            System.out.println("Co " + kq + " dong trong Ingredient bi thay doi.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    public int deleteIngredientById(int id) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Ingredient SET "
                    + "statusIngredient = 0, quantityIngredient = 0 "
                    + "WHERE idIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa nguyen liệu thành công.");
            System.out.println("Co " + kq + " dong trong Ingredient bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateIngredientById(int id, Ingredient otherIngredient) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Ingredient SET "
                    + "nameIngredient = ?, unitIngredient = ?, quantityIngredient = ? "
                    + "WHERE idIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, otherIngredient.getNameIngredient());
            preparedStatement.setString(2, otherIngredient.getUnitIngredient());
            preparedStatement.setFloat(3, otherIngredient.getQuantityIngredient());
            preparedStatement.setInt(4, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa nguyên liệu thành công.");
            System.out.println("Co " + kq + " dong trong bảng Ingredient bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateQuantityIngredient(int ingredientId, float quantityToAdd) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Ingredient SET quantityIngredient = quantityIngredient + ? WHERE idIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, quantityToAdd);
            preparedStatement.setInt(2, ingredientId);
            kq = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Ingredient> findIngredientByName(String name) {
        ArrayList<Ingredient> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Ingredient WHERE nameIngredient LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idIngredient = resultSet.getInt("idIngredient");
                String nameIngredient = resultSet.getString("nameIngredient");
                String unitIngredient = resultSet.getString("unitIngredient");
                float quantityIngredient = resultSet.getFloat("quantityIngredient");
                boolean statusIngredient = resultSet.getBoolean("statusIngredient");
                Ingredient a = new Ingredient(idIngredient, nameIngredient, unitIngredient, quantityIngredient, statusIngredient);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
