/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.Manager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Admin
 */
public class EncryptPassword {
    public static String encryptPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Chuyển đổi mật khẩu thành mảng byte
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
    
    public static void main(String[] args) {
        String a = "hongnguyen123";
        String kqa = EncryptPassword.encryptPassword(a);
        String kqb = EncryptPassword.encryptPassword("hongnguyen123");
        System.out.println(kqa);
        System.out.println(kqa.length());
        System.out.println(kqa.equals(kqb));
    }
}
