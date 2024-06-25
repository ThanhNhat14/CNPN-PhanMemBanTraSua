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
public class Topping {
      protected int idTopping;
    protected String nameTopping, imageTopping , descriptionTopping ;
    protected int priceTopping;
    
    public Topping(){
    }

    public int getIdTopping() {
        return idTopping;
    }

    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
    }

    public int getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(int priceTopping) {
        this.priceTopping = priceTopping;
    }

    public String getImageTopping() {
        return imageTopping;
    }

    public void setImageTopping(String imageTopping) {
        this.imageTopping = imageTopping;
    }

    public String getDescriptionTopping() {
        return descriptionTopping;
    }

    public void setDescriptionTopping(String descriptionTopping) {
        this.descriptionTopping = descriptionTopping;
    }
    

    @Override
    public String toString() {
        return nameTopping + " " + priceTopping ;
    }

    
    public static Topping getResultSet(ResultSet rs) throws SQLException{
        Topping tp= new Topping();
        tp.setIdTopping(rs.getInt("idTopping"));
        tp.setNameTopping(rs.getNString("nameTopping"));
        tp.setPriceTopping(rs.getInt("priceTopping"));
        return tp;
    }
    
}
