/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Product {
    private int idProduct;
    private String nameProduct;
    private String imageProduct;
    private int idCategory;
    private String description;
    private boolean statusProduct;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, String imageProduct, int idCategory, String description, boolean statusProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.idCategory = idCategory;
        this.description = description;
        this.statusProduct = statusProduct;
    }

    public Product(String nameProduct, String imageProduct, int idCategory, String description, boolean statusProduct) {
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.idCategory = idCategory;
        this.description = description;
        this.statusProduct = statusProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", imageProduct=" + imageProduct + ", idCategory=" + idCategory + ", description=" + description + ", statusProduct=" + statusProduct + '}';
    }

   
}
