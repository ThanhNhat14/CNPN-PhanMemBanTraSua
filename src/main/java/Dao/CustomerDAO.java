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
import Model.Manager.Customer;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCustomer = resultSet.getInt("idCustomer");
                String nameCustomer = resultSet.getString("nameCustomer");
                String numberPhoneCustomer = resultSet.getString("numberPhoneCustomer");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String addressCustomer = resultSet.getString("addressCustomer");
                int gender = resultSet.getInt("gender");
                String birthday = resultSet.getString("birthday");
                Customer a = new Customer(idCustomer, nameCustomer, numberPhoneCustomer, userName, password, addressCustomer, gender, birthday);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public Customer getCustomerById(int id) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Customer WHERE idCustomer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCustomer = resultSet.getInt("idCustomer");
                String nameCustomer = resultSet.getString("nameCustomer");
                String numberPhoneCustomer = resultSet.getString("numberPhoneCustomer");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String addressCustomer = resultSet.getString("addressCustomer");
                int gender = resultSet.getInt("gender");
                String birthday = resultSet.getString("birthday");
                Customer a = new Customer(idCustomer, nameCustomer, numberPhoneCustomer, userName, password, addressCustomer, gender, birthday);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Customer> getCustomersByName(String name) {
        ArrayList<Customer> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Customer WHERE nameCustomer LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCustomer = resultSet.getInt("idCustomer");
                String nameCustomer = resultSet.getString("nameCustomer");
                String numberPhoneCustomer = resultSet.getString("numberPhoneCustomer");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String addressCustomer = resultSet.getString("addressCustomer");
                int gender = resultSet.getInt("gender");
                String birthday = resultSet.getString("birthday");
                Customer a = new Customer(idCustomer, nameCustomer, numberPhoneCustomer, userName, password, addressCustomer, gender, birthday);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int countCustomerAccount() {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                kq = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public boolean isDuplicateUserName(String userName) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT COUNT(*) FROM Customer WHERE userName = ?";
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
}
