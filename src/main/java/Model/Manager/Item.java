/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Item {
    private int idItem;
    private int idItemDetail;
    private int idProductSize;
    private int idTopping;
    private int quantityTopping;

    public Item() {
    }

    public Item(int idItem, int idItemDetail, int idProductSize, int idTopping, int quantityTopping) {
        this.idItem = idItem;
        this.idItemDetail = idItemDetail;
        this.idProductSize = idProductSize;
        this.idTopping = idTopping;
        this.quantityTopping = quantityTopping;
    }

    public Item(int idItemDetail, int idProductSize, int idTopping, int quantityTopping) {
        this.idItemDetail = idItemDetail;
        this.idProductSize = idProductSize;
        this.idTopping = idTopping;
        this.quantityTopping = quantityTopping;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdItemDetail() {
        return idItemDetail;
    }

    public void setIdItemDetail(int idItemDetail) {
        this.idItemDetail = idItemDetail;
    }

    public int getIdProductSize() {
        return idProductSize;
    }

    public void setIdProductSize(int idProductSize) {
        this.idProductSize = idProductSize;
    }

    public int getIdTopping() {
        return idTopping;
    }

    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public int getQuantityTopping() {
        return quantityTopping;
    }

    public void setQuantityTopping(int quantityTopping) {
        this.quantityTopping = quantityTopping;
    }

    @Override
    public String toString() {
        return "Item{" + "idItem=" + idItem + ", idItemDetail=" + idItemDetail + ", idProductSize=" + idProductSize + ", idTopping=" + idTopping + ", quantityTopping=" + quantityTopping + '}';
    }
    
    
}
