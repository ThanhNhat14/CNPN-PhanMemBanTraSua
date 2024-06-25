/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Voucher {
    private int idVoucher;
    private String codeVoucher;
    private int percentDiscount;
    private int toCost;
    private boolean statusVoucher;

    public Voucher() {
    }
    
    public Voucher(int idVoucher, String codeVoucher, int percentDiscount, int toCost, boolean statusVoucher) {
        this.idVoucher = idVoucher;
        this.codeVoucher = codeVoucher;
        this.percentDiscount = percentDiscount;
        this.toCost = toCost;
        this.statusVoucher = statusVoucher;
    }
    
}
