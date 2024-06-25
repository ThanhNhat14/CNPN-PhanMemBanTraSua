/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.plaf.metal.OceanTheme;

/**
 *
 * @author admin
 */
public class Purchase {
    protected int idOrder, idAccount;
    protected Date datePurchase;
    protected int totalAmount, totalPrice , discount;
    protected String statusPurchase, paymentMethod;
    
    public Purchase(){
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatusPurchase() {
        return statusPurchase;
    }

    public void setStatusPurchase(String statusPurchase) {
        this.statusPurchase = statusPurchase;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public static Purchase getResultSet(ResultSet rs) throws  SQLException{
        Purchase pc = new Purchase();
        pc.setIdOrder(rs.getInt("idOrder"));
        pc.setIdAccount(rs.getInt("idAccount"));
        pc.setDatePurchase(rs.getDate("datePuchase"));
        pc.setTotalAmount(rs.getInt("totalAmount"));
        pc.setDiscount(rs.getInt("discount"));
        pc.setStatusPurchase(rs.getNString("statusPurchase"));
        pc.setPaymentMethod(rs.getNString("paymentMethod"));
        return pc;
        
    }
    
}
