/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Employee;

import Connection.connectSQL;
import static Controller.Employee.HandleProducts.Toppings;
import static Controller.Employee.HandleProducts.getToppings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static List<Map<String, Object>> CustomerBills = new ArrayList<>();
    public static List<Map<String, Object>> CustomerBillDetails = new ArrayList<>();
    public static List<Map<String, Object>> CustomerGlasses = new ArrayList<>();
    public static Map<String, Integer> CustomerProductSize = new HashMap<>();
    //public static List<Map<String, Object>> bill = new ArrayList<>();
    public static int[] idTopping = new int[Toppings.size()];
    public static int[] idNewBill = new int[1];

    public static void createOrder(int idAccount, int total, int quantity, int idVoucher, String statusPurchase, String paymentMethod,
            String address, String note, List<Map<String, Object>> Glasses) {
        Connection connection = connectSQL.Connection();

        String sql = "insert into Bill values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
            if (idVoucher == 0) {
                ps.setNull(7, java.sql.Types.INTEGER);
            } else {
                ps.setInt(7, idVoucher);
            }

            ps.setString(8, statusPurchase);
            ps.setString(9, paymentMethod);
            ps.setNull(10, java.sql.Types.INTEGER);
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
                    ps.setInt(1, Integer.parseInt(Glass.get("idProduct").toString()));
                    ps.setInt(2, Integer.parseInt(Glass.get("idSize").toString()));

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

                int idGlass = (int) Glass.get("idItemDetail");
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
                bill.put("totalAmount", rs.getInt("totalAmount"));
                bill.put("totalPrice", rs.getInt("totalPrice"));
                bill.put("discount", rs.getInt("discount"));
                bill.put("statusPurchase", rs.getString("statusPurchase"));
                bill.put("paymentMethod", rs.getString("paymentMethod"));
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
                CustomerBillDetails = (List<Map<String, Object>>) bill.get("billDetails");
                for (int j = 0; j < CustomerBillDetails.size(); j++) {
                    Map<String, Object> billDetail = CustomerBillDetails.get(j);
                    ps.setInt(1, (int) billDetail.get("Item"));
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        if (idItemDetail != 0 && idItemDetail != rs.getInt("idItemDetail")) {
                            CustomerBillDetails.get(n).put("Item", Item);
                            CustomerBillDetails.get(n).put("quantityProduct", (int) CustomerBillDetails.get(j - 1).get("quantityProduct"));
                            n++;
                            price = 0;
                            Topping = new ArrayList<>();
                            Item = new HashMap<>();
                        }
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
                                idItemDetail = rs1.getInt("idItemDetail");
                                Item.put("idItemDetail", rs1.getInt("idItemDetail"));
                                Item.put("ProductSize", rs1.getInt("idProductSize"));
                                Item.put("price", 0);
                                Topping = new ArrayList<>();
                                Item.put("Topping", Topping);
                                CustomerBillDetails.get(n).put("Item", Item);
                                CustomerBillDetails.get(n).put("quantityProduct", (int) billDetail.get("quantityProduct"));
                                n++;
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
            PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                        CustomerBills.get(i).put("Item", Item);
                    }

                }

            }
            ps.close();
            System.out.println("Get Product Success!!!");
        } catch (Exception e) {
            System.out.println("Get Product Error!!!\n" + e);
        }

    }

    public static void changeBillStatus(int idBill, int idCustomer, String status) {
        Connection connection = connectSQL.Connection();
        String sql = "UPDATE bill SET dateOrder = ?, statusPurchase = ? WHERE idBill = ? AND idCustomer = ?";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(now);
        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, dateString);
            ps.setString(2, status);
            ps.setInt(3, idBill);
            ps.setInt(4, idCustomer);

            ps.executeUpdate();

            System.out.println("Change Bill Status Success!!!");
        } catch (Exception e) {
            System.out.println("Change Bill Status Fail!!!\n" + e);
        }
    }

    public static Map<String, Object> getLatestBill() {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT TOP 1 idBill, dateOrder FROM Bill ORDER BY dateOrder DESC";
        Map<String, Object> latestBill = new HashMap<>();

        try {
            System.out.println("Executing query: " + sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                latestBill.put("idBill", rs.getInt("idBill"));
                latestBill.put("dateOrder", rs.getString("dateOrder"));
                System.out.println("Latest Bill ID: " + rs.getInt("idBill"));
                System.out.println("Latest Bill Date Order: " + rs.getString("dateOrder"));
            } else {
                System.out.println("No bills found in the database!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Latest Bill Success!!!");
        } catch (Exception e) {
            System.out.println("Get Latest Bill Error!!!\n" + e);
            e.printStackTrace();
        }

        return latestBill;
    }

    public static String getDateOrderByIdBill(int idBill) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT dateOrder FROM Bill WHERE idBill = ?";
        String dateOrder = null;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dateOrder = rs.getString("dateOrder");
            } else {
                System.out.println("No bill found with this idBill!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Date Order Success!!!");
        } catch (Exception e) {
            System.out.println("Get Date Order Error!!!\n" + e);
            e.printStackTrace();
        }

        return dateOrder;
    }

    public static int getTotalpriceByIdBill(int idBill) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT totalPrice FROM Bill WHERE idBill = ?";
        int totalPrice = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalPrice = rs.getInt("totalPrice");
            } else {
                System.out.println("No bill found with this idBill!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Date Order Success!!!");
        } catch (Exception e) {
            System.out.println("Get Date Order Error!!!\n" + e);
            e.printStackTrace();
        }

        return totalPrice;
    }

    public static Map<String, Object> getItemAndToppings(int idBill) {
        Connection connection = connectSQL.Connection();
        Map<String, Object> result = new HashMap<>();

        try {
            // Lấy thông tin của một Item từ bảng BillDetail
            String sqlItem = "SELECT * FROM BillDetail WHERE idBill = ?";
            PreparedStatement psItem = connection.prepareStatement(sqlItem);
            psItem.setInt(1, idBill);
            ResultSet rsItem = psItem.executeQuery();

            // Nếu có Item
            if (rsItem.next()) {
                int idItemDetail = rsItem.getInt("idItemDetail");
                int idProductSize = rsItem.getInt("idProductSize");
                int quantityProduct = rsItem.getInt("quantityProduct");

                // Lấy thông tin của Item từ bảng ProductSize và Product
                String sqlItemInfo = "SELECT ps.price AS priceSize, p.nameProduct "
                        + "FROM ProductSize ps "
                        + "JOIN Product p ON ps.idProduct = p.idProduct "
                        + "WHERE ps.idProductSize = ?";
                PreparedStatement psItemInfo = connection.prepareStatement(sqlItemInfo);
                psItemInfo.setInt(1, idProductSize);
                ResultSet rsItemInfo = psItemInfo.executeQuery();

                if (rsItemInfo.next()) {
                    result.put("nameProduct", rsItemInfo.getString("nameProduct"));
                    result.put("priceSize", rsItemInfo.getInt("priceSize"));
                }

                rsItemInfo.close();
                psItemInfo.close();

                // Lấy thông tin của các Topping từ bảng Item
                String sqlToppings = "SELECT t.nameTopping, t.priceTopping, i.quantityTopping "
                        + "FROM Item i "
                        + "JOIN Topping t ON i.idTopping = t.idTopping "
                        + "WHERE i.idItemDetail = ?";
                PreparedStatement psToppings = connection.prepareStatement(sqlToppings);
                psToppings.setInt(1, idItemDetail);
                ResultSet rsToppings = psToppings.executeQuery();

                List<Map<String, Object>> toppings = new ArrayList<>();
                while (rsToppings.next()) {
                    Map<String, Object> topping = new HashMap<>();
                    topping.put("nameTopping", rsToppings.getString("nameTopping"));
                    topping.put("priceTopping", rsToppings.getInt("priceTopping"));
                    topping.put("quantityTopping", rsToppings.getInt("quantityTopping"));
                    toppings.add(topping);
                }
                result.put("toppings", toppings);

                rsToppings.close();
                psToppings.close();

                // Lưu thông tin số lượng sản phẩm
                result.put("quantityProduct", quantityProduct);
            }

            rsItem.close();
            psItem.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error while fetching item and toppings: " + e);
        }

        return result;
    }
    public static List<Map<String, Object>> ItemBills = new ArrayList<>();
    public static List<Map<String, Object>> ToppingBills = new ArrayList<>();

    public static void getBill(int idBill) {
        Connection connection = connectSQL.Connection();
        try {
            // Clear previous data
            ItemBills.clear();
            ToppingBills.clear();

            // Map to keep track of items by idProductSize
            Map<Integer, Map<String, Object>> itemMap = new HashMap<>();

            // Query to get items in the bill
            String sqlItem = "SELECT * FROM BillDetail WHERE idBill = ?";
            PreparedStatement psItem = connection.prepareStatement(sqlItem);
            psItem.setInt(1, idBill);
            ResultSet rsItem = psItem.executeQuery();

            while (rsItem.next()) {
                int idItem = rsItem.getInt("idItem");
                int quantityProduct = rsItem.getInt("quantityProduct");

                // Query to get item details and associated toppings
                String sqlItemDetails = "SELECT * FROM Item WHERE idItem = ?";
                PreparedStatement psItemDetails = connection.prepareStatement(sqlItemDetails);
                psItemDetails.setInt(1, idItem);
                ResultSet rsItemDetails = psItemDetails.executeQuery();

                while (rsItemDetails.next()) {
                    int idProductSize = rsItemDetails.getInt("idProductSize");
                    int idTopping = rsItemDetails.getInt("idTopping");
                    int quantityTopping = rsItemDetails.getInt("quantityTopping");

                    Map<String, Object> item;
                    if (itemMap.containsKey(idProductSize)) {
                        item = itemMap.get(idProductSize);
                        // Update quantityProduct if idProductSize already exists
//                        int existingQuantity = (int) item.get("quantityProduct");
                        item.put("quantityProduct", quantityProduct);
                    } else {
                        item = new HashMap<>();
                        item.put("quantityProduct", quantityProduct);
                        item.put("Toppings", new ArrayList<Map<String, Object>>());

                        // Fetch price and nameProduct
                        Map<String, Object> productDetails = getProductDetails(idProductSize);
                        item.put("priceProduct", productDetails.get("price"));
                        item.put("nameProduct", productDetails.get("nameProduct"));

                        itemMap.put(idProductSize, item);
                    }

                    if (idTopping != 0) {
                        List<Map<String, Object>> toppings = (List<Map<String, Object>>) item.get("Toppings");
                        Map<String, Object> toppingDetails = getToppingDetails(idTopping);
                        Map<String, Object> topping = new HashMap<>();
                        topping.put("idTopping", idTopping);
                        topping.put("idProductSize", idProductSize);
                        topping.put("quantityTopping", quantityTopping * quantityProduct);
                        topping.put("nameTopping", toppingDetails.get("nameTopping"));
                        topping.put("priceTopping", toppingDetails.get("priceTopping"));
                        toppings.add(topping);
                    }
                }
            }

            ItemBills.addAll(itemMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the bill items and toppings
        for (Map<String, Object> item : ItemBills) {
            System.out.println(item);
        }
    }

    public static int getIdVoucherByIdBill(int idBill) {
        Connection connection = connectSQL.Connection();
        int idVoucher = 0;
        try {
            // Query to get discount from Bill table based on idBill
            String sqlDiscount = "SELECT idVoucher FROM Bill WHERE idBill = ?";
            PreparedStatement psDiscount = connection.prepareStatement(sqlDiscount);
            psDiscount.setInt(1, idBill);
            ResultSet rsDiscount = psDiscount.executeQuery();

            // Check if result set has a value and get the discount
            if (rsDiscount.next()) {
                idVoucher = rsDiscount.getInt("idVoucher");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idVoucher;
    }

    public static int getPercentDiscountByIdVoucher(int idVoucher) {
        Connection connection = connectSQL.Connection();
        int discount = 0;
        try {
            // Query to get discount from Bill table based on idBill
            String sqlDiscount = "SELECT percentDiscount FROM Voucher WHERE idVoucher = ?";
            PreparedStatement psDiscount = connection.prepareStatement(sqlDiscount);
            psDiscount.setInt(1, idVoucher);
            ResultSet rsDiscount = psDiscount.executeQuery();

            // Check if result set has a value and get the discount
            if (rsDiscount.next()) {
                discount = rsDiscount.getInt("percentDiscount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discount;
    }

    public static int getidVoucherByCode(String code) {
        Connection connection = connectSQL.Connection();
        int idVoucher = 0;
        try {
            // Query to get discount from Bill table based on idBill
            String sqlDiscount = "SELECT idVoucher FROM Voucher WHERE codeVoucher = ?";
            PreparedStatement psDiscount = connection.prepareStatement(sqlDiscount);
            psDiscount.setString(1, code);
            ResultSet rsDiscount = psDiscount.executeQuery();

            // Check if result set has a value and get the discount
            if (rsDiscount.next()) {
                idVoucher = rsDiscount.getInt("idVoucher");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idVoucher;
    }

    public static int getToCostByCode(String code) {
        Connection connection = connectSQL.Connection();
        int toCost = 0;
        try {
            // Query to get discount from Bill table based on idBill
            String sqlDiscount = "SELECT toCost FROM Voucher WHERE codeVoucher = ?";
            PreparedStatement psDiscount = connection.prepareStatement(sqlDiscount);
            psDiscount.setString(1, code);
            ResultSet rsDiscount = psDiscount.executeQuery();

            // Check if result set has a value and get the discount
            if (rsDiscount.next()) {
                toCost = rsDiscount.getInt("toCost");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toCost;
    }

    public static String getCodeVoucherByid(int idVoucher) {
        Connection connection = connectSQL.Connection();
        String codeVoucher = "";
        try {
            // Query to get discount from Bill table based on idBill
            String sqlDiscount = "SELECT codeVoucher FROM Voucher WHERE  idVoucher= ?";
            PreparedStatement psDiscount = connection.prepareStatement(sqlDiscount);
            psDiscount.setInt(1, idVoucher);
            ResultSet rsDiscount = psDiscount.executeQuery();

            // Check if result set has a value and get the discount
            if (rsDiscount.next()) {
                codeVoucher = rsDiscount.getString("codeVoucher");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codeVoucher;
    }
// Helper method to fetch price and nameProduct

    public static Map<String, Object> getProductDetails(int idProductSize) {
        Connection connection = connectSQL.Connection();
        Map<String, Object> productDetails = new HashMap<>();

        try {
            // Query to get price and idProduct from ProductSize table
            String sqlProductSize = "SELECT price, idProduct FROM ProductSize WHERE idProductSize = ?";
            PreparedStatement psProductSize = connection.prepareStatement(sqlProductSize);
            psProductSize.setInt(1, idProductSize);
            ResultSet rsProductSize = psProductSize.executeQuery();

            if (rsProductSize.next()) {
                int price = rsProductSize.getInt("price");
                int idProduct = rsProductSize.getInt("idProduct");
                productDetails.put("price", price);

                // Query to get nameProduct from Product table
                String sqlProduct = "SELECT nameProduct FROM Product WHERE idProduct = ?";
                PreparedStatement psProduct = connection.prepareStatement(sqlProduct);
                psProduct.setInt(1, idProduct);
                ResultSet rsProduct = psProduct.executeQuery();

                if (rsProduct.next()) {
                    String nameProduct = rsProduct.getString("nameProduct");
                    productDetails.put("nameProduct", nameProduct);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDetails;
    }

    public static List<Map<String, Object>> BillDetails = new ArrayList<>();

    public static void getBillWithStatus(String status) {
        Connection connection = connectSQL.Connection();
        try {
            // Clear previous data
            BillDetails.clear();

            // Query to get bills with the given status
            String sqlBill = "SELECT idBill, dateOrder, statusPurchase,paymentMethod FROM Bill WHERE statusPurchase = ?";
            PreparedStatement psBill = connection.prepareStatement(sqlBill);
            psBill.setString(1, status);
            ResultSet rsBill = psBill.executeQuery();

            while (rsBill.next()) {
                Map<String, Object> billDetail = new HashMap<>();
                billDetail.put("idBill", rsBill.getInt("idBill"));
                billDetail.put("dateOrder", rsBill.getString("dateOrder"));
                billDetail.put("statusPurchase", rsBill.getString("statusPurchase"));
                billDetail.put("paymentMethod", rsBill.getString("paymentMethod"));

                BillDetails.add(billDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the bill details
        for (Map<String, Object> billDetail : BillDetails) {
            System.out.println("Bill Details: " + billDetail);
        }
    }

    public static void getAllBills() {
        Connection connection = connectSQL.Connection();
        try {
            // Clear previous data
            BillDetails.clear();

            // Query to get all bills
            String sqlBill = "SELECT idBill, dateOrder, statusPurchase,paymentMethod FROM Bill";
            PreparedStatement psBill = connection.prepareStatement(sqlBill);
            ResultSet rsBill = psBill.executeQuery();

            while (rsBill.next()) {
                Map<String, Object> billDetail = new HashMap<>();
                billDetail.put("idBill", rsBill.getInt("idBill"));
                billDetail.put("dateOrder", rsBill.getString("dateOrder"));
                billDetail.put("statusPurchase", rsBill.getString("statusPurchase"));
                billDetail.put("paymentMethod", rsBill.getString("paymentMethod"));
                BillDetails.add(billDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the bill details
        for (Map<String, Object> billDetail : BillDetails) {
            System.out.println("Bill Details: " + billDetail);
        }
    }

// Helper method to fetch nameTopping and priceTopping
    public static Map<String, Object> getToppingDetails(int idTopping) {
        Connection connection = connectSQL.Connection();
        Map<String, Object> toppingDetails = new HashMap<>();

        try {
            // Query to get nameTopping and priceTopping from Topping table
            String sqlTopping = "SELECT nameTopping, priceTopping FROM Topping WHERE idTopping = ?";
            PreparedStatement psTopping = connection.prepareStatement(sqlTopping);
            psTopping.setInt(1, idTopping);
            ResultSet rsTopping = psTopping.executeQuery();

            if (rsTopping.next()) {
                String nameTopping = rsTopping.getString("nameTopping");
                int priceTopping = rsTopping.getInt("priceTopping");
                toppingDetails.put("nameTopping", nameTopping);
                toppingDetails.put("priceTopping", priceTopping);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toppingDetails;
    }

    public static void updateBillStatusById(int idBill, String newStatus, int idAccount) {
        Connection connection = connectSQL.Connection();
        try {
            // Query to update the status of a specific bill based on idBill
            String sqlUpdateStatus = "UPDATE Bill SET statusPurchase = ?,idAccount=? WHERE idBill = ?";

            // Prepare and execute the update query
            try (PreparedStatement psUpdateStatus = connection.prepareStatement(sqlUpdateStatus)) {
                psUpdateStatus.setString(1, newStatus);
                psUpdateStatus.setInt(2, idAccount);
                psUpdateStatus.setInt(3, idBill);

                // Execute the update
                int rowsAffected = psUpdateStatus.executeUpdate();

                // Check if any rows were affected
                if (rowsAffected > 0) {
                    System.out.println("Successfully updated the status of Bill ID " + idBill + " to " + newStatus);
                } else {
                    System.out.println("Bill ID " + idBill + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBillStatusById(int idBill, String newStatus, String cancelReason) {
        Connection connection = connectSQL.Connection();
        try {
            // Query to update the status and note of a specific bill based on idBill
            String sqlUpdateStatus = "UPDATE Bill SET statusPurchase = ?, cancelReason = ? WHERE idBill = ?";

            // Prepare and execute the update query
            try (PreparedStatement psUpdateStatus = connection.prepareStatement(sqlUpdateStatus)) {
                psUpdateStatus.setString(1, newStatus);
                psUpdateStatus.setString(2, cancelReason);
                psUpdateStatus.setInt(3, idBill);

                // Execute the update
                int rowsAffected = psUpdateStatus.executeUpdate();

                // Check if any rows were affected
                if (rowsAffected > 0) {
                    System.out.println("Successfully updated the status and note of Bill ID " + idBill);
                } else {
                    System.out.println("Bill ID " + idBill + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getStatusPurchaseById(int idBill) {
        Connection connection = connectSQL.Connection();
        String statusPurchase = null;
        try {
            // Query to get the statusPurchase by idBill
            String sql = "SELECT statusPurchase FROM Bill WHERE idBill = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();

            // Retrieve statusPurchase if record exists
            if (rs.next()) {
                statusPurchase = rs.getString("statusPurchase");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return statusPurchase;
    }

    public static boolean hasPendingConfirmationStatus() {
        Connection connection = connectSQL.Connection();
        boolean exists = false;
        try {
            // Query to check if there's any bill with status "Chờ xác nhận"
            String sql = "SELECT TOP 1 1 FROM Bill WHERE statusPurchase = N'Chờ xác nhận'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // If result set has any rows, it means there is at least one bill with the desired status
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public static int getIdCustomerByIdBill(int idBill) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT idCustomer FROM Bill WHERE idBill = ?";
        int idCustomer = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idCustomer = rs.getInt("idCustomer");
            } else {
                System.out.println("No bill found with this idBill!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Date Order Success!!!");
        } catch (Exception e) {
            System.out.println("Get Date Order Error!!!\n" + e);
            e.printStackTrace();
        }

        return idCustomer;
    }

    public static String getCancelReasonByIdBill(int idBill) {
        Connection connection = connectSQL.Connection();
        String sql = "SELECT cancelReason FROM Bill WHERE idBill = ?";
        String cancelReason = "";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cancelReason = rs.getString("cancelReason");
            } else {
                System.out.println("No bill found with this idBill!");
            }
            rs.close();
            ps.close();
            connection.close();

            System.out.println("Get Date Order Success!!!");
        } catch (Exception e) {
            System.out.println("Get Date Order Error!!!\n" + e);
            e.printStackTrace();
        }

        return cancelReason;
    }

    public static void main(String[] args) {
//        getBill(32);
//        System.out.println(ItemBills);
//        getOrders(0);
//
//        for (int i = 0; i < CustomerBills.size(); i++) {
//            Map<String, Object> test = CustomerBills.get(i);
//            CustomerBillDetails = (List<Map<String, Object>>) test.get("billDetails");
//            for (int j = 0; j < CustomerBillDetails.size(); j++) {
//                Map<String, Object> billDetail = CustomerBillDetails.get(j);
//                //Map<String, Object> glass = (Map<String, Object>) billDetail.get("Glass");
//                System.out.println(billDetail);

//                int[] tp = (int[]) glass.get("idTopping");
//                for (int k = 0; k < tp.length; k++) {
//                    System.out.println(tp[k]);
//                }
        //System.out.println(glass.get("idTopping"));
        //System.out.println(j);
        //Map<String, Integer> productSize = (Map<String, Integer>) glass.get("productSize");
//            }
//        }
    }

}
//{Item={ProductSize={imageProduct=/img/products/tra-sua-chocolate-cao-cap-43961616125427.JPG, idProduct=1, idSize=1, nameProduct=Tr? s?a Truy?n Th?ng}, Topping=[{idTopping=1, quantityTopping=3}, {idTopping=2, quantityTopping=2}, {idTopping=3, quantityTopping=2}], price=174000, idItemDetail=1}, quantityProduct=3}
//{Item={ProductSize={imageProduct=/img/products/tra-sua-chocolate-52461616126267.JPG, idProduct=3, idSize=1, nameProduct=Tr? s?a Khoai M?n}, Topping=[{idTopping=1, quantityTopping=2}, {idTopping=9, quantityTopping=3}, {idTopping=7, quantityTopping=2}], price=126000, idItemDetail=2}, quantityProduct=2}
