/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Run;


import View.LogRes.Customer.CustomerLogin;
import View.LogRes.ManagerEmployee.Login;

/**
 *
 * @author ngocn&
 */
public class run {

    public static void main(String[] args) {
        CustomerLogin customerLogin = new CustomerLogin();
        Login ManageEmployeelogin = new Login();
        ManageEmployeelogin.setVisible(true);
        customerLogin.setVisible(true);

    }
}
