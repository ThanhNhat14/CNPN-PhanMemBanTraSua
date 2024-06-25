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
import Model.Manager.Supplier;

/**
 *
 * @author Admin
 */
public class SupplierDAO {

    public ArrayList<Supplier> getAllSupplier() {
        // ta chỉ lấy các nhà cung cấp có trạng thái là true
        ArrayList<Supplier> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Supplier WHERE statusSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSupplier = resultSet.getInt("idSupplier");
                String nameSupplier = resultSet.getString("nameSupplier");
                String representPerson = resultSet.getString("representPerson");
                String numberPhoneSupplier = resultSet.getString("numberPhoneSupplier");
                String description = resultSet.getString("description");
                String address = resultSet.getString("address");
                boolean statusSupplier = resultSet.getBoolean("statusSupplier");
                Supplier a = new Supplier(idSupplier, nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean isExistSupplier(String theNameSupplier) {
        // true là nguyên liệu đã tồn tại, flase là chưa tồn tại
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Supplier WHERE nameSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theNameSupplier);
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

    public Supplier getSupplierById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Supplier WHERE idSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSupplier = resultSet.getInt("idSupplier");
                String nameSupplier = resultSet.getString("nameSupplier");
                String representPerson = resultSet.getString("representPerson");
                String numberPhoneSupplier = resultSet.getString("numberPhoneSupplier");
                String description = resultSet.getString("description");
                String address = resultSet.getString("address");
                boolean statusSupplier = resultSet.getBoolean("statusSupplier");
                Supplier a = new Supplier(idSupplier, nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertSupplier(Supplier a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Supplier(nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameSupplier());
            preparedStatement.setString(2, a.getRepresentPerson());
            preparedStatement.setString(3, a.getNumberPhoneSupplier());
            preparedStatement.setString(4, a.getDescription());
            preparedStatement.setString(5, a.getAddress());
            preparedStatement.setBoolean(6, a.isStatusSupplier());
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm nhà cung cấp thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

//    public int deleteSupplier(int id) {
//        int kq = 0;
//        try {
//            Connection connection = connectSQL.Connection();
//            String sql = "DELETE FROM Supplier WHERE idSupplier = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            kq = preparedStatement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Đã xóa nhà cung cấp thành công.");
//            System.out.println("Co " + kq + " dong trong Supplier bi thay doi.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return kq;
//    }
    public int deleteSupplier(int id) {
        // khi xóa thì ta sẽ ko thực xóa nó đi trong database
        // ta chỉ set trạng thái từ true thành false
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
//            String sql = "DELETE FROM Supplier WHERE idSupplier = ?";
            String sql = "UPDATE Supplier SET "
                    + "statusSupplier = 0"
                    + "WHERE idSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa nhà cung cấp thành công.");
            System.out.println("Co " + kq + " dong trong Supplier bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateSupplier(int id, Supplier a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Supplier SET "
                    + "nameSupplier = ?, representPerson = ?, numberPhoneSupplier = ?, description = ?, address = ?"
                    + "WHERE idSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getNameSupplier());
            preparedStatement.setString(2, a.getRepresentPerson());
            preparedStatement.setString(3, a.getNumberPhoneSupplier());
            preparedStatement.setString(4, a.getDescription());
            preparedStatement.setString(5, a.getAddress());
            preparedStatement.setInt(6, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa nhà cung cấp thành công.");
            System.out.println("Co " + kq + " dong trong Supplier bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Supplier> findSuppliersByName(String name) {
        ArrayList<Supplier> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Supplier WHERE nameSupplier LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSupplier = resultSet.getInt("idSupplier");
                String nameSupplier = resultSet.getString("nameSupplier");
                String representPerson = resultSet.getString("representPerson");
                String numberPhoneSupplier = resultSet.getString("numberPhoneSupplier");
                String description = resultSet.getString("description");
                String address = resultSet.getString("address");
                boolean statusSupplier = resultSet.getBoolean("statusSupplier");
                Supplier a = new Supplier(idSupplier, nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Supplier getSupplierByName(String name) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Supplier WHERE nameSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idSupplier = resultSet.getInt("idSupplier");
                String nameSupplier = resultSet.getString("nameSupplier");
                String representPerson = resultSet.getString("representPerson");
                String numberPhoneSupplier = resultSet.getString("numberPhoneSupplier");
                String description = resultSet.getString("description");
                String address = resultSet.getString("address");
                boolean statusSupplier = resultSet.getBoolean("statusSupplier");
                Supplier a = new Supplier(idSupplier, nameSupplier, representPerson, numberPhoneSupplier, description, address, statusSupplier);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
