/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.connectSQL;
import Model.Manager.Bill;
import Model.Manager.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class VoucherDao {
    public ArrayList<Voucher> getAllVoucher() {
        ArrayList<Voucher> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Voucher";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idVoucher = resultSet.getInt("idVoucher");
                String codeVoucher = resultSet.getString("codeVoucher");
                int percentDiscount = resultSet.getInt("percentDiscount");
                int toCost = resultSet.getInt("toCost");
                boolean statusVoucher = resultSet.getBoolean("statusVoucher");
                Voucher a = new Voucher(idVoucher, codeVoucher, percentDiscount, toCost, statusVoucher);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
