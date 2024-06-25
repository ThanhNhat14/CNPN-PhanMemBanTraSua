/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.LogRes.ManagerEmployee;

import Connection.connectSQL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngocn
 */
public class Register {

    public static byte regErr;
    public static List<Map<String, String>> Customers = new ArrayList<>();
    public static List<Map<String, Object>> ManagerEmployees = new ArrayList<>();

    public static void register(String username, String password, String phone) {
        regErr = 0;
        getCustomers();
        getManagerEmployees();
        for (int i = 0; i < Customers.size(); i++) {
            Map<String, String> Customer = Customers.get(i);
            String customerUN = (String) Customer.get("userName");
            String customerP = (String) Customer.get("numberPhoneCustomer");
            if (username.equals(customerUN)) {
                regErr = 1;
                Customers.clear();
                return;
            } else if (phone.equals(customerP)) {
                regErr = 2;
                Customers.clear();
                return;
            }
        }
        for (int i = 0; i < ManagerEmployees.size(); i++) {
            Map<String, Object> ManagerEmployee = ManagerEmployees.get(i);
            String ManagerEmployeeUN = (String) ManagerEmployee.get("userName");
            String ManagerEmployeeP = (String) ManagerEmployee.get("numberPhone");
            if (username.equals(ManagerEmployeeUN)) {
                regErr = 1;
                ManagerEmployees.clear();
                return;
            } else if (phone.equals(ManagerEmployeeP)) {
                regErr = 2;
                ManagerEmployees.clear();
                return;
            }
        }

        String encryptedPassword = encryptPassword(password);
        Connection connection = connectSQL.Connection();
        String sql = "insert into Customer values (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "");
            ps.setString(2, phone);
            ps.setString(3, username);
            ps.setString(4, encryptedPassword);
            ps.setString(5, "Số nhà, Phường/Xã, Quận/Huyện, Thành phố Hồ Chí Minh");
            ps.setString(6, "");
            ps.setString(7, "");
            ps.executeUpdate();

            System.out.println("Register Success!!!");
        } catch (Exception e) {

            System.out.println("Register Error!!!\n" + e);
        }
    }

    public static void getCustomers() {
        Connection connection = connectSQL.Connection();
        String sql = "select * from Customer";
        Customers.clear();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> Customer = new HashMap<>();
                Customer.put("nameCustomer", rs.getString("nameCustomer"));
                Customer.put("numberPhoneCustomer", rs.getString("numberPhoneCustomer"));
                Customer.put("userName", rs.getString("userName"));
                Customer.put("password", rs.getString("password"));
                Customer.put("addressCustomer", rs.getString("addressCustomer"));
                Customers.add(Customer);
            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Take Customer Success!!!");
        } catch (Exception e) {
            System.out.println("Take Customer Error!!!");
        }
    }

    public static void getManagerEmployees() {
        Connection connection = connectSQL.Connection();
        String sql = "select * from Account";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> ManagerEmployee = new HashMap<>();
                ManagerEmployee.put("fullName", rs.getString("fullName"));
                ManagerEmployee.put("cccd", rs.getString("cccd"));
                ManagerEmployee.put("userName", rs.getString("userName"));
                ManagerEmployee.put("password", rs.getString("password"));
                ManagerEmployee.put("birthday", rs.getString("birthday"));
                ManagerEmployee.put("address", rs.getString("address"));
                ManagerEmployee.put("permission", rs.getString("permission"));
                ManagerEmployee.put("gender", rs.getBoolean("gender"));
                ManagerEmployee.put("salary", rs.getLong("salary"));
                ManagerEmployee.put("numberPhone", rs.getString("numberPhone"));
                ManagerEmployees.add(ManagerEmployee);
            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Take Manager Employee Success!!!");
        } catch (Exception e) {
            System.out.println("Take Manager Employee Error!!!");
        }
    }

    public static String encryptPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Chuyển đổi mật khẩu thành mảng byte
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
//        String password = "123456";
//
//        String encryptedPassword = encryptPassword(password);
//        System.out.println("Encrypted Password: " + encryptedPassword);
    }
}
