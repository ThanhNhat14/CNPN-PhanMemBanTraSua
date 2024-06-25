/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Customer {

    private int idCustomer;
    private String nameCustomer;
    private String numberPhoneCustomer;
    private String userName;
    private String password;
    private String addressCustomer;
    private int gender;
    private String birthday;

    public Customer() {
    }

    public Customer(int idCustomer, String nameCustomer, String numberPhoneCustomer, String userName, String password, String addressCustomer, int gender, String birthday) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.numberPhoneCustomer = numberPhoneCustomer;
        this.userName = userName;
        this.password = password;
        this.addressCustomer = addressCustomer;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Customer(String nameCustomer, String numberPhoneCustomer, String userName, String password, String addressCustomer, int gender, String birthday) {
        this.nameCustomer = nameCustomer;
        this.numberPhoneCustomer = numberPhoneCustomer;
        this.userName = userName;
        this.password = password;
        this.addressCustomer = addressCustomer;
        this.gender = gender;
        this.birthday = birthday;
    }

    

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNumberPhoneCustomer() {
        return numberPhoneCustomer;
    }

    public void setNumberPhoneCustomer(String numberPhoneCustomer) {
        this.numberPhoneCustomer = numberPhoneCustomer;
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

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Customer{" + "idCustomer=" + idCustomer + ", nameCustomer=" + nameCustomer + ", numberPhoneCustomer=" + numberPhoneCustomer + ", userName=" + userName + ", password=" + password + ", addressCustomer=" + addressCustomer + ", gender=" + gender + ", birthday=" + birthday + '}';
    }
    
    

}
