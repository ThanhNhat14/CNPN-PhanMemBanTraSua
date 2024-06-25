/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Customer;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngocn
 */
public class HandleProducts {

    public static List<Map<String, Object>> Products = new ArrayList<>();
    public static List<Map<String, Object>> Toppings = new ArrayList<>();
    public static List<Map<String, Object>> Categorys = new ArrayList<>();
    public static Map<Integer, String> ProductName = new HashMap<>();
    public static Map<Integer, String> ToppingName = new HashMap<>();   
 
    public static void getProducts(String key) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT p.*, pd.* "
                   + "FROM Product p "
                   + "INNER JOIN ProductSize pd ON p.idProduct = pd.idProduct";
        Products.clear();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> Product = new HashMap<>();
                if (key.equals("")) {
                    if(rs.getInt("idSize") == 2){
                        Products.get(Products.size() - 1).put("priceL", rs.getInt("price"));
                        continue;
                    }
                    Product.put("idProduct", rs.getString("idProduct"));
                    Product.put("nameProduct", rs.getString("nameProduct"));
                    ProductName.put(rs.getInt("idProduct"), rs.getString("nameProduct"));
                    Product.put("imageProduct", rs.getString("imageProduct"));
                    Product.put("idCategory", rs.getInt("idCategory"));
                    Product.put("description", rs.getString("description"));
                    Product.put("status", rs.getBoolean("statusProduct"));
                    Product.put("priceM", rs.getInt("price"));
                    Product.put("priceL", 0);
                    Products.add(Product);
                } else {
                    String normalizedKey = removeAccents(key);
                    String normalizedProductName = removeAccents(rs.getString("nameProduct"));
                    if (normalizedProductName.contains(normalizedKey)) {
                        if(rs.getInt("idSize") == 2){
                        Products.get(Products.size() - 1).put("priceL", rs.getInt("price"));
                        continue;
                    }
                    Product.put("idProduct", rs.getString("idProduct"));
                    Product.put("nameProduct", rs.getString("nameProduct"));
                    Product.put("imageProduct", rs.getString("imageProduct"));
                    Product.put("idCategory", rs.getInt("idCategory"));
                    Product.put("description", rs.getString("description"));
                    Product.put("status", rs.getBoolean("statusProduct"));
                    Product.put("priceM", rs.getInt("price"));
                    Products.add(Product);
                    }
                }

            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Take Product Success!!!");
        } catch (Exception e) {
            System.out.println("Take Product Error!!!\n" + e);
        }
    }

    public static void getToppings() {
        Connection connection = connectSQL.Connection();
        String sql = "select * from Topping";
        Toppings.clear();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> Topping = new HashMap<>();
                Topping.put("idTopping", rs.getString("idTopping"));
                ToppingName.put(rs.getInt("idTopping"), rs.getString("nameTopping"));
                Topping.put("nameTopping", rs.getString("nameTopping"));
                Topping.put("priceTopping", rs.getInt("priceTopping"));
                Topping.put("status", rs.getBoolean("statusTopping"));
                Toppings.add(Topping);

            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Get Topping Success!!!");
        } catch (Exception e) {
            System.out.println("Get Topping Error!!!");
        }
    }
    
    public static void getCategorys() {
        Connection connection = connectSQL.Connection();
        String sql = "select * from Category";
        Categorys.clear();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> Category = new HashMap<>();
                Category.put("idCategory", rs.getInt("idCategory"));
                Category.put("nameCategory", rs.getString("nameCategory"));
                Category.put("statusCategory", rs.getBoolean("statusCategory"));
                Categorys.add(Category);

            }
            rs.close();
            ps.close();
            connection.close();
            System.out.println("Get Topping Success!!!");
        } catch (Exception e) {
            System.out.println("Get Topping Error!!!");
        }
    }

    public static String removeAccents(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalizedString = normalizedString.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        normalizedString = normalizedString.replaceAll("Đ", "D").replaceAll("đ", "d");

        return normalizedString.toLowerCase();
    }

    public static void main(String[] args) {
        getToppings();
        for(int i = 0; i < Toppings.size(); i++){
            Map<String, Object> topping = Toppings.get(i);
            System.out.println(topping);
        }
//        String normalizedKey = removeAccents("dao");
//        String normalizedProductName = removeAccents("Đào");
//
//        System.out.println("Normalized key: " + normalizedKey);
//        System.out.println("Normalized product name: " + normalizedProductName);
//
//        System.out.println(normalizedProductName.toLowerCase().contains(normalizedKey.toLowerCase())); 
    }

}
