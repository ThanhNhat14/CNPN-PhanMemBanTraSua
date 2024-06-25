/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.LogRes.ManagerEmployee;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import static Controller.LogRes.ManagerEmployee.Register.encryptPassword;

/**
 *
 * @author ngocn
 */
public class Login {

    public static Map<String, Object> ManagerEmployee = new HashMap<>();

    public static int setDisplayManagerEmployee = 0;

    public static String login(String username, String password) {
        Connection connection = connectSQL.Connection();
        setDisplayManagerEmployee = 0;
        String sql = "select * from Account";
        String encryptedPassword = encryptPassword(password);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("userName").equals(username) && rs.getString("password").equals(encryptedPassword)) {
                    getAccLogin(username, true);
                    String permission = (String) ManagerEmployee.get("permission");
                    if (permission.equals("EMPLOYEE")) {
                        setDisplayManagerEmployee = 1;
                    }
                    return "true";
                }
            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Get Account Success!!!");
        } catch (Exception e) {
            System.out.println("Get Account Fail!!!");
        }
        return "Tên đăng nhập hoặc mật khẩu không chính xác!!!";
    }

    public static void getAccLogin(String username, boolean x) {
        ManagerEmployee.clear();
        Connection connection = connectSQL.Connection();
        if (x) {
            String sql = "select * from Account where userName = '" + username + "'";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    ManagerEmployee.put("fullName", rs.getString("fullName"));
                    ManagerEmployee.put("birthday", rs.getString("birthday"));
                    ManagerEmployee.put("gender", rs.getInt("gender"));
                    ManagerEmployee.put("cccd", rs.getString("cccd"));
                    ManagerEmployee.put("address", rs.getString("address"));
                    ManagerEmployee.put("userName", rs.getString("userName"));
                    ManagerEmployee.put("password", rs.getString("password"));
                    ManagerEmployee.put("permission", rs.getString("permission"));
                    ManagerEmployee.put("salary", rs.getLong("salary"));
                    ManagerEmployee.put("numberPhone", rs.getString("numberPhone"));
                    ManagerEmployee.put("avatar", rs.getString("avatar"));
                    ManagerEmployee.put("idAccount", rs.getInt("idAccount"));
                }
                rs.close();
                ps.close();
                connection.close();
                System.out.println("Get Manager Employee Success!!!");
            } catch (Exception e) {
                System.out.println(e + "\nGet Manager Employee Fail!!!");
            }
        } else {
            return;
        }

    }

    public static void editCustomer(String userName, String nameCustomer, String numberPhoneCustomer, String addressCustomer, int gender, String birth) {
        Connection connection = connectSQL.Connection();
        String sql = "UPDATE Customer SET nameCustomer = ?, numberPhoneCustomer = ?, addressCustomer = ?, gender = ?, birthday = ? WHERE userName = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, chuanHoaTen(nameCustomer));
            ps.setString(2, numberPhoneCustomer);
            ps.setString(3, addressCustomer);
            ps.setInt(4, gender);
            ps.setString(5, birth);
            ps.setString(6, userName);

            ps.executeUpdate();

            System.out.println("Edit Customer Success!!!");
        } catch (Exception e) {
            System.out.println("Edit Customer Fail!!!\n" + e);
        }
    }

    public static void changePassword(int idCustomer, String password) {
        Connection connection = connectSQL.Connection();
        String sql = "UPDATE Customer SET password = ? WHERE idCustomer = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, encryptPassword(password));
            ps.setInt(2, idCustomer);

            ps.executeUpdate();

            System.out.println("Change Password Success!!!");
        } catch (Exception e) {
            System.out.println("Change Password Fail!!!\n" + e);
        }
    }

    public static boolean checkPassword(int idCustomer, String password) {
        Connection connection = connectSQL.Connection();
        String sql = "select password from Customer where idCustomer = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCustomer);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(encryptPassword(password))) {
                    rs.close();
                    ps.close();
                    connection.close();
                    System.out.println("Check Password Success!!!");
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println(e + "\nCheck Password Fail!!!\n" + e);
        }

        return false;
    }

    public static String chuanHoaTen(String hoTen) {
        if (hoTen.equals("")) {
            return "";
        }

        hoTen = hoTen.trim(); // xóa khoảng trắng đầu và cuối.

        char[] chars = hoTen.toCharArray();// chuyến String -> char.

        chars[0] = Character.toUpperCase(chars[0]);// Viết hoa ký tự đầu.

        // Chữ đầu sau dấu cách viết hoa.
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == ' ') {
                chars[i] = Character.toUpperCase(chars[i]);
            } else {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }

        hoTen = new String(chars); // chuyển Char -> String.
        hoTen = hoTen.replaceAll("\\s+", " ");//Xóa khoảng trắng thừa.
        return hoTen;
    }

    public static void main(String[] args) {
        changePassword(2, "123");
    }
}
