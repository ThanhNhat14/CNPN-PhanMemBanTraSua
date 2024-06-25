/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Customer{
    protected int id;
    protected String username , password , name,address , phoneNumber;
    
    public Customer(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameString() {
        return name;
    }

    public void setNameString(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneName() {
        return phoneNumber;
    }

    public void setPhoneName(String phoneNumber) {
        this.phoneNumber= phoneNumber;
    }

    
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Customer{" + "address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }

    
    
    public static Account getFromResultSet(ResultSet rs) throws SQLException{
        Account c = new Account();
        c.setId(rs.getInt("idCustomer"));
        c.setUsername(rs.getNString("userName"));
        c.setPassword(rs.getNString("password"));
        c.setName(rs.getNString("nameCustomer"));
        c.setPhoneNumber(rs.getNString("numberPhoneCustomer"));
//        c.setPermission(AccountPermission.getByID(rs.getNString("permission")));
        return c;           
    }
    
     public Object[] toRowTable(){
        return new Object[]{
            id, name,username, password,
            phoneNumber,
            
        };
    }
}
