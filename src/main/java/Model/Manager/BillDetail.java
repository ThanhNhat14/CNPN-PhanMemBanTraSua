/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class BillDetail {
    private int idBillDetail;
    private int idBill;
    private int idItem;
    private int quantityProduct;

    public BillDetail() {
    }

    public BillDetail(int idBillDetail, int idBill, int idItem, int quantityProduct) {
        this.idBillDetail = idBillDetail;
        this.idBill = idBill;
        this.idItem = idItem;
        this.quantityProduct = quantityProduct;
    }

    public BillDetail(int idBill, int idItem, int quantityProduct) {
        this.idBill = idBill;
        this.idItem = idItem;
        this.quantityProduct = quantityProduct;
    }

    public int getIdBillDetail() {
        return idBillDetail;
    }

    public void setIdBillDetail(int idBillDetail) {
        this.idBillDetail = idBillDetail;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    @Override
    public String toString() {
        return "BillDetail{" + "idBillDetail=" + idBillDetail + ", idBill=" + idBill + ", idItem=" + idItem + ", quantityProduct=" + quantityProduct + '}';
    }
    
    
}
