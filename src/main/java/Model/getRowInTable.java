/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author admin
 */
public class getRowInTable {
    
    public static int getRow(JTable table) {
         int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                return -1;
            }
        return  selectedRowIndex;
    }
    
    public static int[] getRows(JTable table) {
    int[] selectedRowIndices = table.getSelectedRows();
    if (selectedRowIndices.length == 0) {
        return new int[]{-1}; // Trả về một mảng có một phần tử chứa giá trị -1 nếu không có dòng nào được chọn
    }
    return selectedRowIndices;
    }
}
