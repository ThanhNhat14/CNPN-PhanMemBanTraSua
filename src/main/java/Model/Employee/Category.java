/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import Utils.StringToSlug;

/**
 *
 * @author admin
 */
public class Category {
    protected int id;
    protected String name ;
    
    public Category(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSlug() {
        return StringToSlug.convert(name);
    }

    @Override
    public String toString() {
        return  name;
    }
    
    public static Category getFromResultSet(ResultSet rs) throws  SQLException{
        Category c=new Category();
        c.setId(rs.getInt("idCategory"));
        c.setName("nameCategory");
        return c;
    }
    
    public Object[] toRowTable() {
        return new Object[]{
            this.getId(), this.getName()
        };
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
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 
}
