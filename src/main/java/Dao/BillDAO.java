/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Model.Manager.Bill;

/**
 *
 * @author Admin
 */
public class BillDAO {

    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = 0;
                if (resultSet.getInt("idVoucher") != 0) {
                    String sql1 = "SELECT percentDiscount FROM Voucher WHERE idVoucher = " + String.valueOf(resultSet.getInt("idVoucher"));
                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        discount = rs1.getInt("percentDiscount");
                    }
                    rs1.close();
                    ps1.close();
                } else {
                    discount = 0;
                }
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Bill getBillById(int theId) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE idBill = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = 0;
                if (resultSet.getInt("idVoucher") != 0) {
                    String sql1 = "SELECT percentDiscount FROM Voucher WHERE idVoucher = " + String.valueOf(resultSet.getInt("idVoucher"));
                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        discount = rs1.getInt("percentDiscount");
                    }
                    rs1.close();
                    ps1.close();
                } else {
                    discount = 0;
                }
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Bill> findBillByIdAccount(int theId) {
        ArrayList<Bill> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillWithNotNullIdCustomer() {
        ArrayList<Bill> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE idCustomer IS NOT NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillWithNullIdCustomer() {
        ArrayList<Bill> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE idCustomer IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillByDateOrder(String startDateStr, String endDateStr) {
        ArrayList<Bill> ds = new ArrayList<>();
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
            String sql = "SELECT * FROM Bill WHERE CONVERT(date, dateOrder, 103) BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillByStartDateOrder(String startDateStr) {
        ArrayList<Bill> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE CONVERT(DATE, dateOrder, 103) >= CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillByEndDateOrder(String startDateStr) {
        ArrayList<Bill> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE CONVERT(DATE, dateOrder, 103) <= CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Bill> findBillByDay(String day) {
        ArrayList<Bill> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Bill WHERE CONVERT(DATE, dateOrder, 103) == CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int countBillCreateOnToday(String today) {
        int kq = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date todayDate = null;
            todayDate = dateFormat.parse(today);
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) AS count FROM Bill "
                    + "WHERE CONVERT(DATE, dateOrder, 103) = CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(todayDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                kq = resultSet.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int calculateTotalPriceBillOnToday(String today) {
        int kq = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date todayDate = new Date(); // Lấy ngày hiện tại
            todayDate = dateFormat.parse(today);
            Connection connection = connectSQL.Connection();
            String sql = "SELECT SUM(totalPrice) FROM Bill "
                    + "WHERE CONVERT(DATE, dateOrder, 103) = CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(todayDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                kq = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Bill> findBillCreateOnToday(String today) {
        ArrayList<Bill> ds = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = null;
        try {
            todayDate = dateFormat.parse(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredient "
                    + "WHERE CONVERT(DATE, dateOrder, 103) = CONVERT(DATE, ?, 103)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(todayDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBill = resultSet.getInt("idBill");
                int idAccount = resultSet.getInt("idAccount");
                String dateOrder = resultSet.getString("dateOrder");
                String deliveryAddress = resultSet.getString("deliveryAddress");
                String note = resultSet.getString("note");
                int totalAmount = resultSet.getInt("totalAmount");
                int totalPrice = resultSet.getInt("totalPrice");
                int discount = resultSet.getInt("discount");
                String statusPurchase = resultSet.getString("statusPurchase");
                String paymentMethod = resultSet.getString("paymentMethod");
                int idCustomer = resultSet.getInt("idCustomer");
                String cancelReason = resultSet.getString("cancelReason");
                Bill a = new Bill(idBill, idAccount, dateOrder, deliveryAddress, note, totalAmount, totalPrice, discount, statusPurchase, paymentMethod, idCustomer, cancelReason);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
