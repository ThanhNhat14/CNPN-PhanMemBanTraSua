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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import View.Employee.Employee;

/**
 *
 * @author admin
 */
public class CategoryController {
    public static String getNameCategoryByid(int idCategory){
       Connection connection = connectSQL.Connection();
       String sql = "select nameCategory from Category where idCategory=?";
       String nameCategory="";
       try {
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setInt(1, idCategory);
           ResultSet rs= ps.executeQuery();
           while(rs.next()){
              nameCategory =rs.getString("nameCategory");
           }
           return nameCategory;
       } catch (SQLException ex) {
           Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return "";
   }
   
     public static int getIdCategory(String category){
       Connection connection = connectSQL.Connection();
       String sql = "select idCategory from Category where nameCategory=?";
       int id=0;
       try {
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, category);
           ResultSet rs= ps.executeQuery();
           while(rs.next()){
              id =rs.getInt("idCategory");
           }
           return id;
       } catch (SQLException ex) {
           Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return 0;
   }
   
   public static void  getNameCategory(JComboBox<String> jComboBox){
        Connection connection = connectSQL.Connection();
        String sql ="select nameCategory from Category where statuscategory=1";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
             Vector<String> categories = new Vector<>();
            while (rs.next()) {
                String categoryName = rs.getString("nameCategory");
                categories.add(categoryName);
            }

            // Thêm dữ liệu từ Vector vào JComboBox
            jComboBox.removeAllItems();
            jComboBox.addItem("");
            for (String category : categories) {
                jComboBox.addItem(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public static void getListCategory(JTable jTableDanhSach){
        DefaultTableModel dtm =(DefaultTableModel) jTableDanhSach.getModel();
        Connection ketNoi = connectSQL.Connection();
        String sql = "Select * From Category where statusCategory=1";
        Vector vt;
//        int count=1;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
            dtm.setRowCount(0);
            while (rs.next()) {
                vt= new Vector();
                vt.add(rs.getInt("idCategory"));
                vt.add(rs.getString("nameCategory"));
                
                dtm.addRow(vt);
            }
            jTableDanhSach.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
   public static boolean addCategory(String nameCategory){
        Connection connection = connectSQL.Connection();
        String sql = "Insert into Category(nameCategory,statusCategory) VALUES (?,1)";
       try {
           PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nameCategory);
          
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
       }
        return false;
   }
   
   
//      public static boolean deletaCategoryByID(int idCategory){
//        Connection connection = DatabaseConnection.ketNoiDatabase();
//        String sql = "delete from Category where idCategory=?";
//       try {
//           PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, idCategory);
//            ps.executeUpdate();
//            ps.close();
//            connection.close();
//            return true;
//       } catch (SQLException ex) {
//           Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
//       }
//        return false;
//   }
   
    public static boolean updateCategory(String nameCategory, int idCategory) {
    Connection connection = connectSQL.Connection();
    String sql = "UPDATE Category SET nameCategory=? WHERE idCategory=?";
    
    try {
        // Cập nhật thông tin chung trong bảng Product
        PreparedStatement psProduct = connection.prepareStatement(sql);
        psProduct.setString(1, nameCategory);
        psProduct.setInt(2, idCategory); // Thiết lập giá trị cho tham số thứ hai
        psProduct.executeUpdate();
        psProduct.close();

        connection.close();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(EditInfoProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    public static boolean deleteCategory(int categoryIdToDelete) {
        // khi ta xóa đi một thể loại thì nó sẽ cập nhật statusCategory thành 0 và tất cả sản phẩm của thể loại đó thành 0

        int kq = 0;
        try {
            Connection connection = connectSQL.Connection();

            // Cập nhật statusProduct của các sản phẩm liên quan thành 0
            String updateProductQuery = "UPDATE Product SET statusProduct = 0 WHERE idCategory = ?";
            PreparedStatement pstmtProduct = connection.prepareStatement(updateProductQuery);
            pstmtProduct.setInt(1, categoryIdToDelete);
            pstmtProduct.executeUpdate();

            // Cập nhật statusCategory của danh mục thành 0
            String updateCategoryQuery = "UPDATE Category SET statusCategory = 0 WHERE idCategory = ?";
            PreparedStatement pstmtCategory = connection.prepareStatement(updateCategoryQuery);
            pstmtCategory.setInt(1, categoryIdToDelete);
            kq = pstmtCategory.executeUpdate();

            if (kq > 0) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật trạng thái của loại sản phẩm thành công.");
                System.out.println("Có " + kq + " dòng trong category bị thay đổi.");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy loại sản phẩm để cập nhật.");
            }

            pstmtProduct.close();
            pstmtCategory.close();
            connection.close();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
