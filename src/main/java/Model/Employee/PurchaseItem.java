/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class PurchaseItem {
    private int idPurchase , idProduct, idTopping, idSize , quantityProduct, priceProduct, priceTopping, priceSize;
    private Product productItem;
    private Topping toppingItem;
    private Size sizeItem;
    
    public PurchaseItem(){
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdTopping() {
        return idTopping;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getPriceSize() {
        return priceSize;
    }

    public void setPriceSize(int priceSize) {
        this.priceSize = priceSize;
    }

    
    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
       if(quantityProduct >=0){
           this.quantityProduct=quantityProduct;
       }else{
           this.quantityProduct=0;
       }
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(int priceTopping) {
        this.priceTopping = priceTopping;
    }

    public Product getProductItem() {
        return productItem;
    }

    public void setProductItem(Product productItem) {
        this.productItem = productItem;
        this.idProduct=productItem.getIdProduct();
    }

    public Topping getToppingItem() {
        return toppingItem;
    }

    public void setToppingItem(Topping toppingItem) {
        this.toppingItem = toppingItem;
        this.idTopping=toppingItem.getIdTopping();
    }

    public Size getSizeItem() {
        return sizeItem;
    }

    public void setSizeItem(Size sizeItem) {
        this.sizeItem = sizeItem;
        this.idSize=sizeItem.getIdSize();
    }
    
    public static PurchaseItem getResultSet(ResultSet rs) throws SQLException{
        PurchaseItem pci = new  PurchaseItem();
        pci.setIdProduct(rs.getInt("idPurchase"));
        pci.setIdProduct(rs.getInt("idProduct"));
        pci.setIdTopping(rs.getInt("idTopping"));
        pci.setIdSize(rs.getInt("idSize"));
        pci.setPriceProduct(rs.getInt("priceProduct"));
        pci.setPriceSize(rs.getInt("priceSize"));
        pci.setPriceTopping(rs.getInt("priceTopping"));
        pci.setQuantityProduct(rs.getInt("quantityProduct"));
        return pci;
    }
    
}
