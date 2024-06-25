/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author admin
 */
public class Product {
    protected int idProduct;
    protected String nameProduct , description , imageProduct ;
    protected int priceProduct , idCategory;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    
    public Product(){
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DecimalFormat formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString() {
          return String.format("%s (%s)", nameProduct, formatter.format(priceProduct));
    }
    
     public static Product getFromResultSet(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setIdProduct(rs.getInt("idProduct"));
        p.setNameProduct(rs.getNString("nameProduct"));
        p.setDescription(rs.getNString("description"));
        p.setImageProduct(rs.getNString("imageProduct"));
        p.setPriceProduct(rs.getInt("priceProduct"));
        p.setIdCategory(rs.getInt("idCategory"));
        return p;
    }
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.idProduct != other.idProduct) {
            return false;
        }
        return true;
    }
    
    public Object[] toRowTable() {
        return new Object[]{
            this.getIdProduct(), this.getNameProduct(), this.getDescription(), this.getImageProduct(),
             this.getPriceProduct(), this.getIdCategory()
        };
    }
}
