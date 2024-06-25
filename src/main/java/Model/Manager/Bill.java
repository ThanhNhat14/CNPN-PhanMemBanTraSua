/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Bill {
    private int idBill;
    private int idAccount;
    private String dateOrder;
    private String deliveryAddress;
    private String note;
    private int totalAmount;
    private int totalPrice;
    private int discount;
    private String statusPurchase;
    private String paymentMethod;
    private int idCustomer;
    private String cancelReason;

    public Bill() {
    }

    public Bill(int idBill, int idAccount, String dateOrder, String deliveryAddress, String note, int totalAmount, int totalPrice, int discount, String statusPurchase, String paymentMethod, int idCustomer, String cancelReason) {
        this.idBill = idBill;
        this.idAccount = idAccount;
        this.dateOrder = dateOrder;
        this.deliveryAddress = deliveryAddress;
        this.note = note;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.statusPurchase = statusPurchase;
        this.paymentMethod = paymentMethod;
        this.idCustomer = idCustomer;
        this.cancelReason = cancelReason;
    }
    
    

    public Bill(int idAccount, String dateOrder, String deliveryAddress, String note, int totalAmount, int totalPrice, int discount, String statusPurchase, String paymentMethod, int idCustomer, String cancelReason) {
        this.idAccount = idAccount;
        this.dateOrder = dateOrder;
        this.deliveryAddress = deliveryAddress;
        this.note = note;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.statusPurchase = statusPurchase;
        this.paymentMethod = paymentMethod;
        this.idCustomer = idCustomer;
        this.cancelReason = cancelReason;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public String toString() {
        return "Bill{" + "idBill=" + idBill + ", idAccount=" + idAccount + ", dateOrder=" + dateOrder + ", deliveryAddress=" + deliveryAddress + ", note=" + note + ", totalAmount=" + totalAmount + ", totalPrice=" + totalPrice + ", discount=" + discount + ", statusPurchase=" + statusPurchase + ", paymentMethod=" + paymentMethod + ", idCustomer=" + idCustomer + ", cancelReason=" + cancelReason + '}';
    }

    
    
}


