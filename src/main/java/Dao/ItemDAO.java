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
import Model.Manager.Item;

/**
 *
 * @author Admin
 */
public class ItemDAO {

    public Item getItemById(int theId) {
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Item WHERE idItem = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idItem = resultSet.getInt("idItem");
                int idItemDetail = resultSet.getInt("idItemDetail");
                int idProductSize = resultSet.getInt("idProductSize");
                int idTopping = resultSet.getInt("idTopping");
                int quantityTopping = resultSet.getInt("quantityTopping");
                Item a = new Item(idItem, idItemDetail, idProductSize, idTopping, quantityTopping);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Item> getItemsByIdItemDetail(int theIdItemDetail) {
        ArrayList<Item> ds = new ArrayList<>();
        try {
            Connection connection = connectSQL.Connection();
            String sql = "SELECT * FROM Item WHERE idItemDetail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, theIdItemDetail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idItem = resultSet.getInt("idItem");
                int idItemDetail = resultSet.getInt("idItemDetail");
                int idProductSize = resultSet.getInt("idProductSize");
                int idTopping = resultSet.getInt("idTopping");
                int quantityTopping = resultSet.getInt("quantityTopping");
                Item a = new Item(idItem, idItemDetail, idProductSize, idTopping, quantityTopping);
                ds.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
