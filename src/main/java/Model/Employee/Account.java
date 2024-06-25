/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import Utils.Employee.AccountPermission;

/**
 *
 * @author admin
 */
public class Account {
    protected int id, gender;
    protected String name, username , password , CCCD, phoneNumber, address;
    protected Date birthDay;
    protected int salary;
    protected AccountPermission permission;
    
    public Account(){
    }

    public Account(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id= id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword (String password){
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.max(0,salary);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public AccountPermission getPermission() {
        return permission;
    }

    public void setPermission(AccountPermission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    
    public static Account getFromResultSet(ResultSet rs) throws SQLException{
        Account e = new Account();
        e.setId(rs.getInt("idAccount"));
        e.setUsername(rs.getNString("userName"));
        e.setPassword(rs.getNString("password"));
        e.setName(rs.getNString("fullName"));
        e.setCCCD(rs.getNString("cccd"));
        e.setPhoneNumber(rs.getNString("phoneNumber"));
        e.setBirthDay(rs.getDate("birthday"));
        e.setPermission(AccountPermission.getByID(rs.getNString("permission")));
        e.setSalary(rs.getInt("salary"));
        e.setGender(rs.getInt("gender"));
        e.setAddress(rs.getNString("address"));
        return e;           
    }
            
    public Object[] toRowTable(){
        return new Object[]{
            id, name,username, password,
            phoneNumber, birthDay, permission.getName(),
            salary
        };
    }
    
    @Override
    public int hashCode(){
        int hash=5;
        return hash;
    }
    
    public boolean equals(Object obj){
         if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Object getIdTaiKhoan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getFullName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getUserName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getBirthday() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static boolean isValidCCCD(String idCardNumber) {
        // Kiểm tra độ dài của số căn cước
        if (idCardNumber.length() != 12) {
            return false;
        }
        
        // Kiểm tra xem các ký tự có phải là số không
        for (int i = 0; i < idCardNumber.length(); i++) {
            if (!Character.isDigit(idCardNumber.charAt(i))) {
                return false;
            }
        }
        
        // Nếu vượt qua tất cả các điều kiện kiểm tra
        return true;
    }

     public static int calculateAge(String dateOfBirth) {
        // Định dạng của ngày tháng
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Chuyển đổi chuỗi ngày tháng thành đối tượng LocalDate
         LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
        
        // Lấy ngày tháng hiện tại
        LocalDate currentDate = LocalDate.now();
        
        // Tính tuổi
         Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        
        return age;
    }
     
//     public static void main(String[] args) {
//         System.out.println(calculateAge("07/05/2006"));
//    }
}
