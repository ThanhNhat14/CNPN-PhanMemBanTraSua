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
public class Combo {
    protected int idCombo;
    protected String nameCombo , contentCombo ;
    protected int priceCombo;

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public String getNameCombo() {
        return nameCombo;
    }

    public void setNameCombo(String nameCombo) {
        this.nameCombo = nameCombo;
    }

    public String getContentCombo() {
        return contentCombo;
    }

    public void setContentCombo(String contentCombo) {
        this.contentCombo = contentCombo;
    }

    public int getPriceCombo() {
        return priceCombo;
    }

    public void setPriceCombo(int priceCombo) {
        this.priceCombo = priceCombo;
    }

    @Override
    public String toString() {
        return "Combo{" + "Combo:" + nameCombo + ", Gồm có: " + contentCombo + ", Giá:" + priceCombo + '}';
    }
    
     public static Category getFromResultSet(ResultSet rs) throws  SQLException{
        Category c=new Category();
        c.setId(rs.getInt("idCategory"));
        c.setName("nameCategory");
        return c;
    }
    
    public static Combo getResultSet(ResultSet rs) throws SQLException{
        Combo cb = new Combo();
        cb.setIdCombo(rs.getInt("idCombo"));
        cb.setNameCombo(rs.getNString("nameCombo"));
        cb.setContentCombo(rs.getNString("contentCombo"));
        cb.setPriceCombo(rs.getInt("priceCombo"));
        return cb;
    }
    
}
