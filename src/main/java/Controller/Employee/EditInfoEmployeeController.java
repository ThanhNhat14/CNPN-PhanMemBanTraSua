/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Employee;

import Connection.connectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Employee.Employee;

/**
 *
 * @author admin
 */
public class EditInfoEmployeeController {
    public static boolean editAvatarEmployee(String newURL, int idAccount){
        Connection connnection = connectSQL.Connection();
        String sql = "update Account set avatar=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newURL);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getNewAvatar(int idAccount){
        Connection connnection = connectSQL.Connection();
        String sql = "select avatar From Account where idAccount=?";
         String avatar="";
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                avatar=rs.getNString("avatar");
            }
            System.out.println(avatar);
            rs.close();
             ps.close();
             connnection.close();
             return avatar;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Không tìm thấy avatar";
    }
    
    public static boolean updateNameEmployee(String newName, int idAccount ){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set fullname=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newName);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public static boolean updateBirthdayEmployee(String newBirthday, int idAccount ){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set birthday=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newBirthday);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateGenderEmployee(int gender, int idAccount ){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set gender=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setInt( 1, gender);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public static boolean updateCCCDEmployee(String newCCCD, int idAccount ){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set cccd=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newCCCD);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public static boolean updateAddressEmployee(String newAddress, int idAccount ){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set address=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newAddress);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
    public static boolean updatePhoneEmployee(String newPhone, int idAccount){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set numberPhone=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newPhone);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public static boolean checkNumberPhoneExist(String sdt){
       Connection connnection = connectSQL.Connection();
        String sql = "SELECT COUNT(*) FROM Account WHERE numberPhone=?";
        
        try {
            PreparedStatement ps = connnection.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count>0;
            }
            ps.close();
            rs.close();
            connnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      public static boolean updatePasswordEmployee(String newPassword ,int idAccount){
         Connection connnection = connectSQL.Connection();
        String sql = "update Account set password=? where idAccount=?";
        
        try {
            PreparedStatement ps= connnection.prepareStatement(sql);
             ps.setString(1, newPassword);
             ps.setInt(2, idAccount);
             ps.executeUpdate();
             ps.close();
             connnection.close();
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(EditInfoEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
     
}
