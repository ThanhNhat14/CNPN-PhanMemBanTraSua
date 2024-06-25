/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import Model.Manager.BillImportIngredient;

/**
 *
 * @author Admin
 */
public class BillImportIngredientDAO {

    public int countRowInBillImportIngredientTable() {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM BillImportIngredient";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kq = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    // hàm trả về hóa đơn có id cao nhất
    public int findMaxIdBillImportIngredient() {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT MAX(idBillImportIngredient) FROM BillImportIngredient";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kq = resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<BillImportIngredient> getAllBillImportIngredient() {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int insertBillImportIngredient(BillImportIngredient a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO BillImportIngredient(idAccount, dateCreate, idSupplier, totalPrice) "
                    + " VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, a.getIdAccount());
            preparedStatement.setString(2, a.getDateCreate());
            preparedStatement.setInt(3, a.getIdSupplier());
            preparedStatement.setInt(4, a.getTotalPrice());
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm hóa đơn thành công.");
            System.out.println("Co " + kq + " dong trong BillImpoetIngredient bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public BillImportIngredient getBillImportIngredientById(int theId) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient WHERE idBillImportIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<BillImportIngredient> findBillImportIngredientsBySupplier(int theId) {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient WHERE idSupplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<BillImportIngredient> findBillImportIngredientsByAccount(int theID) {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<BillImportIngredient> findBillImportIngredientsByDateCreate(String startDateStr, String endDateStr) {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient WHERE CONVERT(date, dateCreate, 103) BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
//                System.out.println(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<BillImportIngredient> findBillImportIngredientsByStartDateCreate(String startDateStr) {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient "
                    + "WHERE CONVERT(DATE, dateCreate, 103) >= CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
//                System.out.println(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<BillImportIngredient> findBillImportIngredientsByEndDateCreate(String endDateStr)
    {
        ArrayList<BillImportIngredient> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = null;
        try {
            endDate = dateFormat.parse(endDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient "
                    + "WHERE CONVERT(DATE, dateCreate, 103) <= CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
//                System.out.println(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<BillImportIngredient> findBillImportIngredientByDate(String date)
    {
         ArrayList<BillImportIngredient> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = null;
        try {
            endDate = dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient "
                    + "WHERE CONVERT(DATE, dateCreate, 103) == CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idAccount = resultSet.getInt("idAccount");
                String dateCreate = resultSet.getString("dateCreate");
                int idSupplier = resultSet.getInt("idSupplier");
                int totalPrice = resultSet.getInt("totalPrice");
                BillImportIngredient a = new BillImportIngredient(idBillImportIngredient, idAccount, dateCreate, idSupplier, totalPrice);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
