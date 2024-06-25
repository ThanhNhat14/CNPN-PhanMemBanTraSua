/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Account {
    
    private int idAccount;
    private String fullName;
    private String cccd;
    private String userName;
    private String password;
    private String birthday;
    private String address;
    private String permission;
    private int gender;
    private long salary;
    private String numberPhone;
    private String avatar;
//    private byte[] avatar; 

    public Account() {
    }

    public Account(int idAccount, String fullName, String cccd, String userName, String password, String birthday, String address, String permission, int gender, long salary, String numberPhone, String avatar) {
        this.idAccount = idAccount;
        this.fullName = fullName;
        this.cccd = cccd;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.permission = permission;
        this.gender = gender;
        this.salary = salary;
        this.numberPhone = numberPhone;
        this.avatar = avatar;
    }

    public Account(String fullName, String cccd, String userName, String password, String birthday, String address, String permission, int gender, long salary, String numberPhone, String avatar) {
        this.fullName = fullName;
        this.cccd = cccd;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.permission = permission;
        this.gender = gender;
        this.salary = salary;
        this.numberPhone = numberPhone;
        this.avatar = avatar;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AccountModel{" + "idAccount=" + idAccount + ", fullName=" + fullName + ", cccd=" + cccd + ", userName=" + userName + ", password=" + password + ", birthday=" + birthday + ", address=" + address + ", permission=" + permission + ", gender=" + gender + ", salary=" + salary + ", numberPhone=" + numberPhone + ", avatar=" + avatar + '}';
    }

    

   
    
}
