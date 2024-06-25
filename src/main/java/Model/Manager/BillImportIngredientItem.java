/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class BillImportIngredientItem {
    private int idBillImportIngredientItem;
    private int idBillImportIngredient;
    private int idIngredient;
    private float quantity;
    private int priceImport;

    public BillImportIngredientItem() {
    }

    public BillImportIngredientItem(int idBillImportIngredientItem, int idBillImportIngredient, int idIngredient, float quantity, int priceImport) {
        this.idBillImportIngredientItem = idBillImportIngredientItem;
        this.idBillImportIngredient = idBillImportIngredient;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
        this.priceImport = priceImport;
    }

    public BillImportIngredientItem(int idBillImportIngredient, int idIngredient, float quantity, int priceImport) {
        this.idBillImportIngredient = idBillImportIngredient;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
        this.priceImport = priceImport;
    }

    public BillImportIngredientItem(int idIngredient, float quantity, int priceImport) {
        this.idIngredient = idIngredient;
        this.quantity = quantity;
        this.priceImport = priceImport;
    }
    
    

    public int getIdBillImportIngredientItem() {
        return idBillImportIngredientItem;
    }

    public void setIdBillImportIngredientItem(int idBillImportIngredientItem) {
        this.idBillImportIngredientItem = idBillImportIngredientItem;
    }

    public int getIdBillImportIngredient() {
        return idBillImportIngredient;
    }

    public void setIdBillImportIngredient(int idBillImportIngredient) {
        this.idBillImportIngredient = idBillImportIngredient;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
    }

    @Override
    public String toString() {
        return "BillImportIngredientItem{" + "idBillImportIngredientItem=" + idBillImportIngredientItem + ", idBillImportIngredient=" + idBillImportIngredient + ", idIngredient=" + idIngredient + ", quantity=" + quantity + ", priceImport=" + priceImport + '}';
    }
    
    
    
}
