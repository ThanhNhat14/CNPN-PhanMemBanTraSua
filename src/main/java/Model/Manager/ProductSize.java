/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class ProductSize {

    private int idProductSize;
    private int idProduct;
    private int idSize;
    private int price;
    private boolean statusProductSize;

    public ProductSize() {
    }

    public ProductSize(int idProductSize, int idProduct, int idSize, int price, boolean statusProductSize) {
        this.idProductSize = idProductSize;
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.price = price;
        this.statusProductSize = statusProductSize;
    }

    public ProductSize(int idProduct, int idSize, int price, boolean statusProductSize) {
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.price = price;
        this.statusProductSize = statusProductSize;
    }

    public int getIdProductSize() {
        return idProductSize;
    }

    public void setIdProductSize(int idProductSize) {
        this.idProductSize = idProductSize;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatusProductSize() {
        return statusProductSize;
    }

    public void setStatusProductSize(boolean statusProductSize) {
        this.statusProductSize = statusProductSize;
    }

    @Override
    public String toString() {
        return "ProductSize{" + "idProductSize=" + idProductSize + ", idProduct=" + idProduct + ", idSize=" + idSize + ", price=" + price + ", statusProductSize=" + statusProductSize + '}';
    }

    
}
