/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Customer;

import static Controller.Customer.HandleProducts.Toppings;
import static Controller.Customer.HandleProducts.getToppings;
import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngocn
 */
public class HandleBills {

    public static List<Map<String, Object>> AllCustomerBills = new ArrayList<>();
    public static List<Map<String, Object>> vouchers = new ArrayList<>();
    public static List<Map<String, Object>> CustomerBills = new ArrayList<>();
    public static List<Map<String, Object>> CustomerBillDetails = new ArrayList<>();
    public static List<Map<String, Object>> CustomerGlasses = new ArrayList<>();
    public static Map<String, Integer> CustomerProductSize = new HashMap<>();
    //public static List<Map<String, Object>> bill = new ArrayList<>();
    public static int[] idTopping = new int[Toppings.size()];
    public static int[] idNewBill = new int[1];

    public static void createOrder(int idAccount, int total, int quantity, int discount, String statusPurchase, String paymentMethod,
            String address, String note, int idCustomer, List<Map<String, Object>> Glasses) {
        Connection connection = connectSQL.Connection();

        String sql = "insert into Bill values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = dateFormat.format(now);
        ResultSet rs;
        PreparedStatement ps;
        try {

            // Insert the bill into the database
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ps.setString(2, dateString);
            ps.setString(3, address);
            ps.setString(4, note);
            ps.setInt(5, quantity);
            ps.setInt(6, total);
            if (discount == 0) {
                ps.setNull(7, java.sql.Types.INTEGER);
            } else {
                ps.setInt(7, discount);
            }
            ps.setString(8, statusPurchase);
            ps.setString(9, paymentMethod);
            ps.setInt(10, idCustomer);
            ps.setString(11, "");
            ps.executeUpdate();
            ps.close();

            System.out.println("Create Bill Success!!!");

            for (int i = 0; i < Glasses.size(); i++) {
                Map<String, Object> Glass = Glasses.get(i);
                int idProductSize = 0;
                sql = "SELECT idProductSize FROM ProductSize WHERE idProduct = ? AND idSize = ?";

                try {
                    ps = connection.prepareStatement(sql);
                    ps.setInt(1, (int) Glass.get("idProduct"));
                    ps.setInt(2, (int) Glass.get("idSize"));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        idProductSize = rs.getInt("idProductSize");
                    }
                    rs.close();
                    ps.close();

                    System.out.println("Get idProductSize Success!!!");
                } catch (Exception e) {
                    System.out.println("Get idProductSize Error!!!\n" + e);
                }

                sql = "insert into Item (idItemDetail, idProductSize, idTopping, quantityTopping) VALUES (?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);
                String sql1 = "insert into BillDetail values (?, ?, ?)";
                PreparedStatement ps1 = connection.prepareStatement(sql1);

                int idGlass = (int) Glass.get("idGlass");
                List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) Glass.get("Topping");

                if (Topping.size() == 0) {
                    ps.setInt(1, idGlass);
                    ps.setInt(2, idProductSize);
                    ps.setNull(3, java.sql.Types.INTEGER);
                    ps.setNull(4, java.sql.Types.INTEGER);
                    ps.executeUpdate();
                    ps.close();

                    ps1.setInt(1, getNewIdBill());
                    ps1.setInt(2, getNewIdItem());
                    ps1.setInt(3, (int) Glass.get("quantityProduct"));
                    ps1.executeUpdate();
                    ps1.close();
                }
                for (int j = 0; j < Topping.size(); j++) {
                    Map<String, Integer> topping = Topping.get(j);

                    ps.setInt(1, idGlass);
                    ps.setInt(2, idProductSize);
                    ps.setInt(3, topping.get("idTopping"));
                    ps.setInt(4, topping.get("quantity"));
                    ps.executeUpdate();

                    ps1.setInt(1, getNewIdBill());
                    ps1.setInt(2, getNewIdItem());
                    ps1.setInt(3, (int) Glass.get("quantityProduct"));
                    ps1.executeUpdate();

                }

                System.out.println("Create Item Success!!!");
                System.out.println("Create BillDetail Success!!!");

            }
            System.out.println("Create Order Success!!!");
            connection.close();
        } catch (Exception e) {
            System.out.println("Create Order Error!!!\n" + e);
        }

    }

    public static int getNewIdBill() {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT TOP 1 * FROM Bill ORDER BY idBill DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idBill");
            } else {
                System.out.println("No bills Found!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Latest Bill Success!!!");
        } catch (Exception e) {
            System.out.println("Get Latest Bill Error!!!\n" + e);
        }
        return 0;
    }

    public static int getNewIdItemDetail() {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT TOP 1 * FROM Item ORDER BY idItem DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idItemDetail");
            } else {
                System.out.println("No Item Found!");

            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Latest idItemDetail Success!!!");
        } catch (Exception e) {
            System.out.println("Get Latest idItemDetail Error!!!\n" + e);
        }
        return 0;
    }

    public static int getNewIdItem() {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT TOP 1 * FROM Item ORDER BY idItem DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idItem");
            } else {
                System.out.println("No Item Found!");

            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Latest idItemDetail Success!!!");
        } catch (Exception e) {
            System.out.println("Get Latest idItemDetail Error!!!\n" + e);
        }
        return 0;
    }

//    public static void getAllOrders() {
//        AllCustomerBills.clear();
//        Connection connection = connectSQL.Connection();
//        String sql = "SELECT idCustomer FROM Customer";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                getCustomerOrders(rs.getInt("idCustomer"));
//
//            }
//            rs.close();
//            ps.close();
//            connection.close();
//
//            System.out.println("Get idCustomer Success!!!");
//        } catch (Exception e) {
//            System.out.println("Get idCustomer Error!!!\n" + e);
//        }
//    }
    public static void getOrders(int idCustomer) {
        getToppings();
        getBills(idCustomer);
        getProductSize();
        System.out.println("Get Order Success!!!");
    }

    public static void getBills(int idCustomer) {
        CustomerBills.clear();
        Connection connection = connectSQL.Connection();
        String sql = "SELECT * FROM Bill";

        if (idCustomer != 0) {
            sql = "SELECT * FROM Bill WHERE idCustomer = " + String.valueOf(idCustomer);
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> bill = new HashMap<>();
                bill.put("idBill", rs.getString("idBill"));
                bill.put("idCustomer", rs.getString("idCustomer"));
                bill.put("dateOrder", rs.getString("dateOrder"));
                bill.put("deliveryAddress", rs.getString("deliveryAddress"));
                bill.put("totalAmount", rs.getInt("totalAmount"));
                bill.put("totalPrice", rs.getInt("totalPrice"));
                if (rs.getInt("idVoucher") != 0) {
                    String sql1 = "SELECT percentDiscount FROM Voucher WHERE idVoucher = " + String.valueOf(rs.getInt("idVoucher"));
                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        bill.put("discount", rs1.getInt("percentDiscount"));
                    }
                    rs1.close();
                    ps1.close();
                } else {
                    bill.put("discount", 0);
                }

                bill.put("statusPurchase", rs.getString("statusPurchase"));
                bill.put("paymentMethod", rs.getString("paymentMethod"));
                bill.put("cancelReason", rs.getString("cancelReason"));
                bill.put("note", rs.getString("note"));
                CustomerBills.add(bill);

            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Bill Success!!!");
        } catch (Exception e) {
            System.out.println("Get Bill Error!!!\n" + e);
        }
    }
//{totalPriceGlass=60000, Glass={imageProduct=/img/products/tra-dao-macchiato-10791616127309.JPG, productSize={idProduct=16, idSize=1}, idTopping=[I@9f116cc, quantityTopping=1}, quantityProduct=2, idGlass=1}

    public static void getBillDetails() {
        CustomerBillDetails.clear();

        Connection connection = connectSQL.Connection();
        String sql = "SELECT * FROM BillDetail WHERE idBill = ?";
        //sql = "SELECT * FROM BillDetail WHERE idBill = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            for (int i = 0; i < CustomerBills.size(); i++) {
                CustomerBillDetails = new ArrayList<>();
                Map<String, Object> bill = CustomerBills.get(i);
                ps.setInt(1, Integer.parseInt((String) bill.get("idBill")));
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Map<String, Object> billDetail = new HashMap<>();
                    billDetail.put("Item", rs.getInt("idItem"));
                    billDetail.put("quantityProduct", rs.getInt("quantityProduct"));

                    CustomerBillDetails.add(billDetail);
                }
                CustomerBills.get(i).put("billDetails", CustomerBillDetails);
            }
            ps.close();
            connection.close();

            System.out.println("Get BillDetail Success!!!");
        } catch (Exception e) {
            System.out.println("Get BillDetail Error!!!\n" + e);
        }
    }

    public static void getItems() {
        getBillDetails();
        Connection connection = connectSQL.Connection();
        //String sql = "SELECT * FROM Item WHERE idItem = ?";
        String sql = "SELECT p.*, pd.* "
                + "FROM Item p "
                + "INNER JOIN Topping pd ON p.idTopping = pd.idTopping WHERE p.idItem = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            for (int i = 0; i < CustomerBills.size(); i++) {
                CustomerBillDetails = new ArrayList<>();
                Map<String, Object> bill = CustomerBills.get(i);
                List<Map<String, Object>> Topping = new ArrayList<>();
                Map<String, Object> Item = new HashMap<>();
                int idItemDetail = 0, n = 0, price = 0;
                boolean confirm = false;
                CustomerBillDetails = (List<Map<String, Object>>) bill.get("billDetails");
                for (int j = 0; j < CustomerBillDetails.size(); j++) {
                    Map<String, Object> billDetail = CustomerBillDetails.get(j);
                    ps.setInt(1, (int) billDetail.get("Item"));
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        if (idItemDetail != 0 && idItemDetail != rs.getInt("idItemDetail") && confirm) {
                            CustomerBillDetails.get(n).put("Item", Item);
                            CustomerBillDetails.get(n).put("quantityProduct", (int) CustomerBillDetails.get(j - 1).get("quantityProduct"));
                            n++;
                            price = 0;
                            Topping = new ArrayList<>();
                            Item = new HashMap<>();
                        }
                        confirm = true;
                        idItemDetail = rs.getInt("idItemDetail");
                        Item.put("idItemDetail", rs.getInt("idItemDetail"));
                        Item.put("ProductSize", rs.getInt("idProductSize"));
                        Map<String, Object> topping = new HashMap<>();
                        topping.put("idTopping", rs.getInt("idTopping"));
                        topping.put("quantityTopping", rs.getInt("quantityTopping"));
                        price += (rs.getInt("priceTopping") * rs.getInt("quantityTopping"));
                        Topping.add(topping);
                        Item.put("Topping", Topping);
                        Item.put("price", price);
                        if (j == CustomerBillDetails.size() - 1) {
                            CustomerBillDetails.get(n).put("Item", Item);
                            CustomerBillDetails.get(n).put("quantityProduct", (int) billDetail.get("quantityProduct"));
                            n++;
                            rs.close();
                        }
                    } else {
                        String sql1 = "SELECT * FROM Item WHERE idItem = ?";

                        try {
                            PreparedStatement ps1 = connection.prepareStatement(sql1);
                            ps1.setInt(1, (int) billDetail.get("Item"));
                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                if (confirm) {
                                    CustomerBillDetails.get(n).put("Item", Item);
                                    CustomerBillDetails.get(n).put("quantityProduct", (int) CustomerBillDetails.get(j - 1).get("quantityProduct"));
                                    n++;
                                    price = 0;
                                    Topping = new ArrayList<>();
                                    Item = new HashMap<>();
                                }
                                idItemDetail = rs1.getInt("idItemDetail");
                                Item.put("idItemDetail", rs1.getInt("idItemDetail"));
                                Item.put("ProductSize", rs1.getInt("idProductSize"));
                                Item.put("price", 0);
                                Topping = new ArrayList<>();
                                confirm = false;
                                Item.put("Topping", Topping);
                                CustomerBillDetails.get(n).put("Item", Item);
                                CustomerBillDetails.get(n).put("quantityProduct", (int) billDetail.get("quantityProduct"));
                                n++;
                                price = 0;
                                Topping = new ArrayList<>();
                                Item = new HashMap<>();
                            }
                            rs1.close();
                            ps1.close();
                        } catch (Exception e) {
                            System.out.println("Fail!!!\n" + e);
                        }
                    }
                }
                CustomerBillDetails.subList(n, CustomerBillDetails.size()).clear();
                CustomerBills.get(i).put("billDetails", CustomerBillDetails);
            }
            ps.close();
            connection.close();

            System.out.println("Get Item Success!!!");
        } catch (Exception e) {
            System.out.println("Get Item Error!!!\n" + e);
        }
    }

    public static void getProductSize() {
        getItems();
        Connection connection = connectSQL.Connection();
        String sql = "SELECT p.*, pd.* "
                + "FROM Product p "
                + "INNER JOIN ProductSize pd ON p.idProduct = pd.idProduct WHERE pd.idProductSize = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < CustomerBills.size(); i++) {
                Map<String, Object> bill = CustomerBills.get(i);
                CustomerBillDetails = (List<Map<String, Object>>) bill.get("billDetails");
                for (int j = 0; j < CustomerBillDetails.size(); j++) {
                    Map<String, Object> billDetail = CustomerBillDetails.get(j);
                    Map<String, Object> Item = (Map<String, Object>) billDetail.get("Item");
                    int price = (int) Item.get("price");
                    ps.setInt(1, (int) Item.get("ProductSize"));
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        Map<String, Object> ProductSize = new HashMap<>();
                        ProductSize.put("idProduct", rs.getInt("idProduct"));
                        ProductSize.put("idSize", rs.getInt("idSize"));
                        ProductSize.put("imageProduct", rs.getString("imageProduct"));
                        ProductSize.put("nameProduct", rs.getString("nameProduct"));
                        price = (price + rs.getInt("price")) * (int) billDetail.get("quantityProduct");
                        Item.put("ProductSize", ProductSize);
                        Item.put("price", price);

                        CustomerBillDetails.get(j).put("Item", Item);
                    }

                }

                CustomerBills.get(i).put("billDetails", CustomerBillDetails);
            }
            ps.close();
            System.out.println("Get Product Success!!!");
        } catch (Exception e) {
            System.out.println("Get Product Error!!!\n" + e);
        }

    }

    public static void repurchase(int idBill, String paymentMethod, int discount, int total) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT * FROM bill WHERE idBill = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sql = "insert into Bill values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps4 = connection.prepareStatement(sql);
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dateString = dateFormat.format(now);
                try {
                    ps4.setInt(1, 1);
                    ps4.setString(2, dateString);
                    ps4.setString(3, rs.getString("deliveryAddress"));
                    ps4.setString(4, rs.getString("note"));
                    ps4.setInt(5, rs.getInt("totalAmount"));
                    ps4.setInt(6, total);
                    if (discount == 0) {
                        ps4.setNull(7, java.sql.Types.INTEGER);
                    } else {
                        ps4.setInt(7, discount);
                    }
                    ps4.setString(8, "Chờ xác nhận");
                    ps4.setString(9, paymentMethod);
                    ps4.setInt(10, rs.getInt("idCustomer"));
                    ps4.setString(11, rs.getString("cancelReason"));
                    ps4.executeUpdate();
                    ps4.close();
                    System.out.println("Create Order Success!!!");
                } catch (Exception e) {
                    System.out.println("Create Order Error!!!\n" + e);
                }
                int newIdBill = getNewIdBill();
                sql = "SELECT p.*, pd.* \n"
                        + "FROM BillDetail p\n"
                        + "INNER JOIN Item pd ON p.idItem = pd.idItem\n"
                        + "WHERE p.idBill = ?";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(sql);
                    ps1.setInt(1, idBill);
                    ResultSet rs1 = ps1.executeQuery();
                    sql = "insert into Item values (?, ?, ?, ?)";
                    PreparedStatement ps2 = connection.prepareStatement(sql);
                    sql = "insert into BillDetail values (?, ?, ?)";
                    PreparedStatement ps3 = connection.prepareStatement(sql);
                    int oldIdItemDetail = 0;
                    while (rs1.next()) {
                        int newIdItemDetail = getNewIdItemDetail();
                        if (oldIdItemDetail != rs1.getInt("idItemDetail")) {
                            newIdItemDetail = getNewIdItemDetail() + 1;
                            oldIdItemDetail = rs1.getInt("idItemDetail");
                        }
                        try {
                            ps2.setInt(1, newIdItemDetail);
                            ps2.setInt(2, rs1.getInt("idProductSize"));
                            ps2.setInt(3, rs1.getInt("idTopping"));
                            ps2.setInt(4, rs1.getInt("quantityTopping"));
                            ps2.executeUpdate();
                            ps3.setInt(1, newIdBill);
                            ps3.setInt(2, getNewIdItem());
                            ps3.setInt(3, rs1.getInt("quantityProduct"));
                            ps3.executeUpdate();

                            System.out.println("Copy Item Success!!!");
                        } catch (Exception e) {
                            ps2.setInt(1, newIdItemDetail);
                            ps2.setInt(2, rs1.getInt("idProductSize"));
                            ps2.setNull(3, java.sql.Types.INTEGER);
                            ps2.setNull(4, java.sql.Types.INTEGER);
                            ps2.executeUpdate();
                            ps3.setInt(1, newIdBill);
                            ps3.setInt(2, getNewIdItem());
                            ps3.setInt(3, rs1.getInt("quantityProduct"));
                            ps3.executeUpdate();

                            System.out.println("Copy Item Success!!!");
                        }

                    }
                    ps1.close();
                    ps2.close();
                    ps3.close();
                    rs1.close();
                    System.out.println("Copy Bill Success!!!");
                } catch (Exception e) {
                    System.out.println("Copy Bill Error!!!\n" + e);
                }

            }

            ps.close();
            rs.close();
            System.out.println("Repurchase Success!!!");
        } catch (Exception e) {
            System.out.println("Repurchase Error!!!\n" + e);
        }
    }

    public static void cancelOrder(int idBill, int idCustomer, String status, String cancelReason) {
        Connection connection = connectSQL.Connection();
        String sql = "UPDATE bill SET dateOrder = ?, statusPurchase = ?, cancelReason = ? WHERE idBill = ? AND idCustomer = ?";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = dateFormat.format(now);
        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, dateString);
            ps.setString(2, status);
            ps.setString(3, cancelReason);
            ps.setInt(4, idBill);
            ps.setInt(5, idCustomer);

            ps.executeUpdate();

            System.out.println("Change Bill Status Success!!!");
        } catch (Exception e) {
            System.out.println("Change Bill Status Fail!!!\n" + e);
        }
    }

    public static void getVoucher() {
        vouchers.clear();
        Connection connection = connectSQL.Connection();
        String sql = "select * from Voucher where statusVoucher = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> voucher = new HashMap<>();
                voucher.put("idVoucher", rs.getInt("idVoucher"));
                voucher.put("codeVoucher", rs.getString("codeVoucher"));
                voucher.put("percentDiscount", rs.getInt("percentDiscount"));
                voucher.put("toCost", rs.getInt("toCost"));
                vouchers.add(voucher);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        getVoucher();
        for (int i = 0; i < vouchers.size(); i++) {
            Map<String, Object> voucher = vouchers.get(i);
            System.out.println(voucher);
        }

//        getOrders(1);
//
//        for (int i = 0; i < CustomerBills.size(); i++) {
//            Map<String, Object> test = CustomerBills.get(i);
//            System.out.println(test);
//            CustomerBillDetails = (List<Map<String, Object>>) test.get("billDetails");
//            for (int j = 0; j < CustomerBillDetails.size(); j++) {
//                Map<String, Object> billDetail = CustomerBillDetails.get(j);
//                //Map<String, Object> glass = (Map<String, Object>) billDetail.get("Glass");
//                System.out.println(billDetail);
////                int[] tp = (int[]) glass.get("idTopping");
////                for (int k = 0; k < tp.length; k++) {
////                    System.out.println(tp[k]);
////                }
//                //System.out.println(glass.get("idTopping"));
//                //System.out.println(j);
//                //Map<String, Integer> productSize = (Map<String, Integer>) glass.get("productSize");
//            }
//        }
    }

}
//{Item={ProductSize={imageProduct=/img/products/tra-sua-chocolate-cao-cap-43961616125427.JPG, idProduct=1, idSize=1, nameProduct=Tr? s?a Truy?n Th?ng}, Topping=[{idTopping=1, quantityTopping=3}, {idTopping=2, quantityTopping=2}, {idTopping=3, quantityTopping=2}], price=174000, idItemDetail=1}, quantityProduct=3}
//{Item={ProductSize={imageProduct=/img/products/tra-sua-chocolate-52461616126267.JPG, idProduct=3, idSize=1, nameProduct=Tr? s?a Khoai M?n}, Topping=[{idTopping=1, quantityTopping=2}, {idTopping=9, quantityTopping=3}, {idTopping=7, quantityTopping=2}], price=126000, idItemDetail=2}, quantityProduct=2}
