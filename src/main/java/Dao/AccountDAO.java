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
import Model.Manager.Account;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idAccount = resultSet.getInt("idAccount");
                String fullName = resultSet.getString("fullName");
                String cccd = resultSet.getString("cccd");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String permission = resultSet.getString("permission");
                int gender = resultSet.getInt("gender");
                long salary = resultSet.getLong("salary");
                String numberPhone = resultSet.getString("numberPhone");
                String avatar = resultSet.getString("avatar");
                Account a = new Account(idAccount, fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar);

                ds.add(a);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean isDuplicateCCCD(String cccd) {
        // true tức là trùng, false là ko trùng
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Account WHERE cccd = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cccd);
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

    public boolean isDuplicateUserName(String userName) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Account WHERE userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
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
    
    public boolean isDuplicateNumberPhone(String sdt) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Account WHERE numberPhone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sdt);
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

    public int insertAccountToDatabase(Account a) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO Account (fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a.getFullName());
            preparedStatement.setString(2, a.getCccd());
            preparedStatement.setString(3, a.getUserName());
            preparedStatement.setString(4, a.getPassword());
            preparedStatement.setString(5, a.getBirthday());
            preparedStatement.setString(6, a.getAddress());
            preparedStatement.setString(7, a.getPermission());
            preparedStatement.setInt(8, a.getGender());
            preparedStatement.setLong(9, a.getSalary());
            preparedStatement.setString(10, a.getNumberPhone());
            preparedStatement.setString(11, a.getAvatar());
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm tài khoản thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

    public Account getAccountByID(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Account WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idAccount = resultSet.getInt("idAccount");
                String fullName = resultSet.getString("fullName");
                String cccd = resultSet.getString("cccd");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String permission = resultSet.getString("permission");
                int gender = resultSet.getInt("gender");
                long salary = resultSet.getLong("salary");
                String numberPhone = resultSet.getString("numberPhone");
                String avatar = resultSet.getString("avatar");
                Account a = new Account(idAccount, fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar);
//                System.out.println(a);
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteAccountInDatabase(int id) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "DELETE FROM Account WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xóa tài khoản thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateAccount(int idAccountCanSua, Account newAccount) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Account SET "
                    + "fullName = ?, cccd = ?, userName = ?, birthday = ?, address = ?, permission = ?, gender = ?, salary = ?, numberPhone = ?, avatar = ? "
                    + "WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newAccount.getFullName());
            preparedStatement.setString(2, newAccount.getCccd());
            preparedStatement.setString(3, newAccount.getUserName());
            preparedStatement.setString(4, newAccount.getBirthday());
            preparedStatement.setString(5, newAccount.getAddress());
            preparedStatement.setString(6, newAccount.getPermission());
            preparedStatement.setInt(7, newAccount.getGender());
            preparedStatement.setLong(8, newAccount.getSalary());
            preparedStatement.setString(9, newAccount.getNumberPhone());
            preparedStatement.setString(10, newAccount.getAvatar());
            preparedStatement.setInt(11, idAccountCanSua);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa tài khoản thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public Account getAccountByFullName(String theFullName) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Account WHERE fullName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theFullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idAccount = resultSet.getInt("idAccount");
                String fullName = resultSet.getString("fullName");
                String cccd = resultSet.getString("cccd");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String permission = resultSet.getString("permission");
                int gender = resultSet.getInt("gender");
                long salary = resultSet.getLong("salary");
                String numberPhone = resultSet.getString("numberPhone");
                String avatar = resultSet.getString("avatar");
                Account a = new Account(idAccount, fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar);
//                System.out.println(a);
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Account> findAccountByFullName(String theFullName) {
        ArrayList<Account> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Account WHERE fullName LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + theFullName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idAccount = resultSet.getInt("idAccount");
                String fullName = resultSet.getString("fullName");
                String cccd = resultSet.getString("cccd");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String permission = resultSet.getString("permission");
                int gender = resultSet.getInt("gender");
                long salary = resultSet.getLong("salary");
                String numberPhone = resultSet.getString("numberPhone");
                String avatar = resultSet.getString("avatar");
                Account a = new Account(idAccount, fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar);
                ds.add(a);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Account> findAccountByPermission(String thePermission) {
        ArrayList<Account> kq = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Account WHERE permission = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, thePermission);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idAccount = resultSet.getInt("idAccount");
                String fullName = resultSet.getString("fullName");
                String cccd = resultSet.getString("cccd");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String permission = resultSet.getString("permission");
                int gender = resultSet.getInt("gender");
                long salary = resultSet.getLong("salary");
                String numberPhone = resultSet.getString("numberPhone");
                String avatar = resultSet.getString("avatar");
                Account a = new Account(idAccount, fullName, cccd, userName, password, birthday, address, permission, gender, salary, numberPhone, avatar);
                kq.add(a);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updateManagerAccount(int theID, Account newAccount) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Account SET "
                    + "fullName = ?, cccd = ?, userName = ?, birthday = ?, address = ?, gender = ?, salary = ?, numberPhone = ?, avatar = ? "
                    + "WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newAccount.getFullName());
            preparedStatement.setString(2, newAccount.getCccd());
            preparedStatement.setString(3, newAccount.getUserName());
            preparedStatement.setString(4, newAccount.getBirthday());
            preparedStatement.setString(5, newAccount.getAddress());
            preparedStatement.setInt(6, newAccount.getGender());
            preparedStatement.setLong(7, newAccount.getSalary());
            preparedStatement.setString(8, newAccount.getNumberPhone());
            preparedStatement.setString(9, newAccount.getAvatar());
            preparedStatement.setInt(10, theID);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa tài khoản thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int updatePasswordManager(int theID, String newPassword) {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "UPDATE Account SET "
                    + "password = ?"
                    + "WHERE idAccount = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, theID);
            kq = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã chỉnh sửa mật khẩu thành công.");
            System.out.println("Co " + kq + " dong bi thay doi.");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}
