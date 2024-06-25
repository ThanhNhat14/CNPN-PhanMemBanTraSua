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
public class Size {
    protected int idSize;
    protected String nameSize ;
    protected float priceOfSize;
    
    public Size(){
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }

    public float getPriceOfSize() {
        return priceOfSize;
    }

    public void setPriceOfSize(float priceOfSize) {
        this.priceOfSize = priceOfSize;
    }

    @Override
    public String toString() {
        return  nameSize + " " + priceOfSize ;
    }
            
    public static Size getResultSet(ResultSet rs) throws SQLException{
        Size s= new Size();
        s.setIdSize(rs.getInt("idSize"));
        s.setNameSize(rs.getNString("nameSize"));
        s.setPriceOfSize(rs.getFloat("priceOfSize"));
        return s;
    }
    
    
}
