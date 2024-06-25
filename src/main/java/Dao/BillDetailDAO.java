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
import Model.Manager.BillDetail;

/**
 *
 * @author Admin
 */
public class BillDetailDAO {

    public ArrayList<BillDetail> getAllBillDetailByIdBill(int theIDBill) {
        ArrayList<BillDetail> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillDetail WHERE idBill = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIDBill);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillDetail = resultSet.getInt("idBillDetail");
                int idBill = resultSet.getInt("idBill");
                int idItem = resultSet.getInt("idItem");
                int quantityProduct = resultSet.getInt("quantityProduct");
                BillDetail a = new BillDetail(idBillDetail, idBill, idItem, quantityProduct);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public BillDetail getBillDetailByIdItem(int idItemCanTim) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT TOP 1 * FROM BillDetail WHERE idItem = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idItemCanTim);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBillDetail = resultSet.getInt("idBillDetail");
                int idBill = resultSet.getInt("idBill");
                int idItem = resultSet.getInt("idItem");
                int quantityProduct = resultSet.getInt("quantityProduct");
                BillDetail a = new BillDetail(idBillDetail, idBill, idItem, quantityProduct);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
