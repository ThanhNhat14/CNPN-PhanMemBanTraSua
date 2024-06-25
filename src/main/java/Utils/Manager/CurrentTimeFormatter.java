/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.Manager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CurrentTimeFormatter {
    public static String getCurrentTimeFormatted() {
        // Lấy thời gian hiện tại
        Date currentTime = new Date();

        // Định dạng thời gian theo yêu cầu (dd/MM/yyyy hh:mm)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        // Chuyển đổi thời gian thành chuỗi đã định dạng
        String formattedTime = dateFormat.format(currentTime);

        return formattedTime;
    }

    public static void main(String[] args) {
        // Sử dụng hàm để lấy và định dạng thời gian hiện tại
        String currentTimeFormatted = getCurrentTimeFormatted();
        System.out.println("Thời gian hiện tại: " + currentTimeFormatted);
    }
}
