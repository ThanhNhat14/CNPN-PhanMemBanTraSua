/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class BillImportIngredient {
    
    private int idBillImportIngredient;
    private int idAccount;
    private String dateCreate;
    private int idSupplier;
    private int totalPrice;

    public BillImportIngredient() {
    }

    public BillImportIngredient(int idBillImportIngredient, int idAccount, String dateCreate, int idSupplier, int totalPrice) {
        this.idBillImportIngredient = idBillImportIngredient;
        this.idAccount = idAccount;
        this.dateCreate = dateCreate;
        this.idSupplier = idSupplier;
        this.totalPrice = totalPrice;
    }

    public BillImportIngredient(int idAccount, String dateCreate, int idSupplier, int totalPrice) {
        this.idAccount = idAccount;
        this.dateCreate = dateCreate;
        this.idSupplier = idSupplier;
        this.totalPrice = totalPrice;
    }

    

    public int getIdBillImportIngredient() {
        return idBillImportIngredient;
    }

    public void setIdBillImportIngredient(int idBillImportIngredient) {
        this.idBillImportIngredient = idBillImportIngredient;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BillImportIngredient{" + "idBillImportIngredient=" + idBillImportIngredient + ", idAccount=" + idAccount + ", dateCreate=" + dateCreate + ", idSupplier=" + idSupplier + ", totalPrice=" + totalPrice + '}';
    }
    
    
    
}
