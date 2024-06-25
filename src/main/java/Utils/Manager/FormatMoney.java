/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.Manager;

import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class FormatMoney {
    public static String formatMoney(int amount)
    {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedAmount = formatter.format(amount);
        return formattedAmount;
    }
}
