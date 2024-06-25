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
import Model.Manager.BillImportIngredientItem;

/**
 *
 * @author Admin
 */
public class BillImportIngredientItemDAO {
    
    public ArrayList<BillImportIngredientItem> getAllBillImportIngredientItem(int theId)
    {
        ArrayList<BillImportIngredientItem> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM BillImportIngredientItem WHERE idBillImportIngredient = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                int idBillImportIngredientItem = resultSet.getInt("idBillImportIngredientItem");
                int idBillImportIngredient = resultSet.getInt("idBillImportIngredient");
                int idIngredient = resultSet.getInt("idIngredient");
                float quantity = resultSet.getFloat("quantity");
                int priceImport = resultSet.getInt("priceImport");
                BillImportIngredientItem a = new BillImportIngredientItem(idBillImportIngredientItem, idBillImportIngredient, idIngredient, quantity, priceImport);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public int insertBillImportIngredientItem(BillImportIngredientItem a)
    {
        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();
            String sql = "INSERT INTO BillImportIngredientItem(idBillImportIngredient, idIngredient, quantity, priceImport) "
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, a.getIdBillImportIngredient());
            preparedStatement.setInt(2, a.getIdIngredient());
            preparedStatement.setFloat(3, a.getQuantity());
            preparedStatement.setInt(4, a.getPriceImport());
            kq = preparedStatement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Đã thêm hóa đơn thành công thành công.");
            System.out.println("Co " + kq + " dong trong BillImpoetIngredientItem bi thay doi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
}
