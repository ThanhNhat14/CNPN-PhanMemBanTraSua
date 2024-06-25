/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.CategoryDAO;
import Dao.ProductDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Category;
import Model.Manager.Product;

/**
 *
 * @author Admin
 */
public class QLSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLSanPham
     */
    public ArrayList<Category> dsTheLoaiSP;
    public CategoryDAO categoryDAO;
    
    public DefaultTableModel defaultTableModelDSSanPham;
    public ArrayList<Product> dsSanPham;
    public ProductDAO productDAO;
    
    public String anhThemSanPham;
    public String anhSuaSanPham;
    
    public QLSanPham() {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        getContentPane().setBackground(Color.WHITE);
        initComponents();
        
        this.labelSuaAnhSanPham.setSize(262, 247);
        
        this.dsSanPham = new ArrayList<>();
        this.productDAO = new ProductDAO();

        // Sản phẩm
        this.defaultTableModelDSSanPham = new DefaultTableModel();
        this.tableDSSanPham.setModel(this.defaultTableModelDSSanPham);
        String[] columnsDSSanPham = {"Mã sản phẩm", "Tên sản phẩm", "Thể loại"};
        this.defaultTableModelDSSanPham.setColumnIdentifiers(columnsDSSanPham);
        JTableHeader headerDSSanPham = this.tableDSSanPham.getTableHeader();
        headerDSSanPham.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSSanPham.setBackground(new Color(0x43CD80));
        headerDSSanPham.setForeground(Color.WHITE);
        headerDSSanPham.setPreferredSize(new Dimension(headerDSSanPham.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSSanPham.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSSanPham.setRowHeight(30);
        this.tableDSSanPham.getColumnModel().getColumn(0).setPreferredWidth(35);
        this.tableDSSanPham.getColumnModel().getColumn(1).setPreferredWidth(160);
        this.tableDSSanPham.getColumnModel().getColumn(2).setPreferredWidth(110);
        
        this.dsTheLoaiSP = new ArrayList<>();
        this.categoryDAO = new CategoryDAO();
        
        this.scrolPaneDSSanPham.getViewport().setBackground(Color.WHITE);
        
        getAllcategory();
        
        for (Category i : this.dsTheLoaiSP) {
            this.comboBoxThemLoaiSanPham.addItem(i.getNameCategory());
        }
        
        for (Category i : this.dsTheLoaiSP) {
            this.comboBoxSuaLoaiSanPham.addItem(i.getNameCategory());
        }
        
        for (Category i : this.dsTheLoaiSP) {
            this.comboBoxTimSanPhamTheoLoai.addItem(i.getNameCategory());
        }
        
        getAllProduct(this.defaultTableModelDSSanPham, this.dsSanPham);
    }
    
    public void getAllcategory() {
        this.dsTheLoaiSP = this.categoryDAO.getAllCategory();
    }

    // ==================== SẢN PHẨM ====================
    public void xuatDSSanPhamRaTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        defaultTableModel.setRowCount(0);
        for (Product i : ds) {
            Category a = this.categoryDAO.getCategoryById(i.getIdCategory());
            Object[] data = {i.getIdProduct(), i.getNameProduct(), a.getNameCategory()};
//            Object[] data = {i.getIdProduct(), i.getNameProduct(), i.getPriceProduct()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }
    
    public void getAllProduct(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        ds = this.productDAO.getAllProduct();
        xuatDSSanPhamRaTableTuArrayList(defaultTableModel, ds);
    }

    // hàm này sẽ lấy ra 1 sản phẩm khi ta click vào 1 hàng trong table dsSanPham
    public Product layProductKhiClickVaoTableProduct(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Product a = this.productDAO.getProductById(id);
            return a;
        } else {
            return null;
        }
    }

//    // hàm hiển thị sản phẩm vào panel xem chi tiết thông tin sản phẩm khi ta click vào table san phẩm
//    public void hienThiThongTinSanPhamVaoPanelXemThongTinSanPham(JTable table, DefaultTableModel defaultTableModel) {
//        Product a = layProductKhiClickVaoTableProduct(table, defaultTableModel);
//        Category b = this.categoryDAO.getCategoryById(a.getIdCategory());
//        if (a.getImageProduct() != null) {
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(a.getImageProduct()).getImage().getScaledInstance(this.labelXemAnhSanPham.getWidth(), this.labelXemAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
//            this.labelXemAnhSanPham.setIcon(imageIcon);
//        }
//        this.labelXemNoiDungMaSanPham.setText(a.getIdProduct() + "");
//        this.labelXemNoiDungTenSanPham.setText(a.getNameProduct());
//        this.labelXemNoiDungLoaiSanPham.setText(b.getNameCategory());
//        this.textAreaXemMoTaSanPham.setText(a.getDescription());
//
//    }
    public void hienThiThongTinSanPhamVaoPanelXemThongTinSanPham(JTable table, DefaultTableModel defaultTableModel) {
        Product a = layProductKhiClickVaoTableProduct(table, defaultTableModel);
        Category b = this.categoryDAO.getCategoryById(a.getIdCategory());
        if (a.getImageProduct() != null) {
            String urlImage = a.getImageProduct();
            this.labelXemAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
        } else {
            this.labelXemAnhSanPham.setIcon(null);
        }
        this.labelXemNoiDungMaSanPham.setText(a.getIdProduct() + "");
        this.labelXemNoiDungTenSanPham.setText(a.getNameProduct());
        this.labelXemNoiDungLoaiSanPham.setText(b.getNameCategory());
        this.textAreaXemMoTaSanPham.setText(a.getDescription());
        
    }
    
    public void resetFrameThemSanPham() {
        this.textFieldThemTenSanPham.setText("");
        this.comboBoxThemLoaiSanPham.setSelectedIndex(0);
        this.textAreaThemMoTaSanPham.setText("");
        this.labelThemAnhSanPham.setIcon(null);
        this.anhThemSanPham = null;
    }


//    public void themAnhSanPham() {
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
//                this.anhThemSanPham = relativePath;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemAnhSanPham.getWidth(), this.labelThemAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
//                this.labelThemAnhSanPham.setIcon(imageIcon);
//            }
//        }
//    }
    public void themAnhSanPham() {
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.anhThemSanPham = relativePath;
                System.out.println(relativePath);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemAnhSanPham.getWidth(), this.labelThemAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
                this.labelThemAnhSanPham.setIcon(imageIcon);
            }
        }
    }
    
    public void themSanPham(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        String thongBaoLoi = "";
        if (this.textFieldThemTenSanPham.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên sản phẩm\n";
            this.labelLoiThemTenSanPham.setText("Vui lòng nhập tên sản phẩm");
        }
        if (this.comboBoxThemLoaiSanPham.getSelectedItem() == null) {
            thongBaoLoi += "Vui lòng chọn loại sản phẩm\n";
            this.labelLoiThemLoaiSanPham.setText("Vui lòng chọn loại sản phẩm");
        }
        if (thongBaoLoi.equals("")) {
            String nameProduct = this.textFieldThemTenSanPham.getText();
            String imageProduct = this.anhThemSanPham;
            String tenLoaiSanPham = this.comboBoxThemLoaiSanPham.getSelectedItem() + "";
            Category b = this.categoryDAO.getCategoryByName(tenLoaiSanPham);
            int idCategory = b.getIdCategory();
            String description = this.textAreaThemMoTaSanPham.getText();
            boolean statusProduct = true;
            Product a = new Product(nameProduct, imageProduct, idCategory, description, statusProduct);
            boolean ktTrungSanPham = this.productDAO.isExistProductByNameProductAndIdCategory(nameProduct, idCategory);
            if (ktTrungSanPham == false) {
                this.productDAO.insertProduct(a);
                getAllProduct(defaultTableModel, ds);
                // this.frameThemSanPham.setVisible(false);
                this.frameThemSanPham.dispose();
                resetFrameThemSanPham();
            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void xoaSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Product a = this.productDAO.getProductById(id);
            if (a != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    this.productDAO.deleteProduct(id);
                    getAllProduct(defaultTableModel, ds);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
//    public void hienThiSanPhamRaFrameSuaSanPham(JTable table, DefaultTableModel defaultTableModel) {
//        int row = table.getSelectedRow();
//        if (row >= 0) {
//            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
//            Product a = this.productDAO.getProductById(id);
//            if (a != null) {
//                this.textFieldSuaTenSanPham.setText(a.getNameProduct());
//                Category b = this.categoryDAO.getCategoryById(a.getIdCategory());
//                this.comboBoxSuaLoaiSanPham.setSelectedItem(b.getNameCategory());
//                this.textAreaSuaMoTaSanPham.setText(a.getDescription());
//                if (a.getImageProduct() != null) {
//                    String urlImage = "E:\\NhapMonCongNghePhanMem\\Code\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + a.getImageProduct();
//                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelSuaAnhSanPham.getWidth(), this.labelSuaAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
//                    this.labelSuaAnhSanPham.setIcon(imageIcon);
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public void hienThiSanPhamRaFrameSuaSanPham(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Product a = this.productDAO.getProductById(id);
            if (a != null) {
                this.labelLoiSuaTenSanPham.setText("");
                this.textFieldSuaTenSanPham.setText(a.getNameProduct());
                Category b = this.categoryDAO.getCategoryById(a.getIdCategory());
                this.comboBoxSuaLoaiSanPham.setSelectedItem(b.getNameCategory());
                this.textAreaSuaMoTaSanPham.setText(a.getDescription());
                if (a.getImageProduct() != null) {
                    String urlImage = a.getImageProduct();
                    this.labelSuaAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


//    public void suaAnhSanPham() {
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
//                this.anhSuaSanPham = relativePath;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaAnhSanPham.getWidth(), this.labelSuaAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
//                this.labelSuaAnhSanPham.setIcon(imageIcon);
//            }
//        }
//    }
    public void suaAnhSanPham() {
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.anhSuaSanPham = relativePath;
                System.out.println(relativePath);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaAnhSanPham.getWidth(), this.labelSuaAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
                this.labelSuaAnhSanPham.setIcon(imageIcon);
            }
        }
    }
    
    public void suaSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Product a = this.productDAO.getProductById(id);
            if (a != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa sản phẩm này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    String thongBaoLoi = "";
                    if (this.textFieldSuaTenSanPham.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập tên sản phẩm\n";
                        this.labelLoiSuaTenSanPham.setText("Vui lòng nhập tên sản phẩm");
                    }
                    if (this.comboBoxSuaLoaiSanPham.getSelectedItem() == null) {
                        thongBaoLoi += "Vui lòng chọn loại sản phẩm\n";
                        this.labelLoiSuaLoaiSanPham.setText("Vui lòng chọn loại sản phẩm");
                    }
                    if (thongBaoLoi.equals("")) {
                        String nameProduct = this.textFieldSuaTenSanPham.getText();
                        String imageProduct = this.anhSuaSanPham;
                        if (this.anhSuaSanPham == null) {
                            imageProduct = a.getImageProduct();
                        }
                        String tenLoaiSanPham = this.comboBoxSuaLoaiSanPham.getSelectedItem() + "";
                        Category b = this.categoryDAO.getCategoryByName(tenLoaiSanPham);
                        int idCategory = b.getIdCategory();
                        String description = this.textAreaSuaMoTaSanPham.getText();
                        boolean statusProduct = true;
                        Product c = new Product(nameProduct, imageProduct, idCategory, description, statusProduct);
                        boolean ktTrungSanPham = this.productDAO.isExistProductByNameProductAndIdCategory(nameProduct, idCategory);
                        if (nameProduct.equals(a.getNameProduct()) && idCategory == a.getIdCategory()) {
                            ktTrungSanPham = false;
                        }
                        if (ktTrungSanPham == false) {
                            this.productDAO.updateProduct(id, c);
                            getAllProduct(defaultTableModel, ds);
                            //this.frameSuaSanPham.setVisible(false);
                            this.frameSuaSanPham.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Sản phẩm này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void timSanPhamTheoTen(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        String tenSPCanTim = this.textFieldTimKiemSanPhamTheoTen.getText();
        if (tenSPCanTim.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } else {
            ArrayList<Product> kq = this.productDAO.findProductByName(tenSPCanTim);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                xuatDSSanPhamRaTableTuArrayList(defaultTableModel, kq);
            }
        }
    }
    
    public void timSanPhamTheoTheLoai(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        String loaiSanPhamCanTim = this.comboBoxTimSanPhamTheoLoai.getSelectedItem() + "";
        if (loaiSanPhamCanTim.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên loại sản phẩm cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (loaiSanPhamCanTim.equals("Tất cả")) {
                getAllProduct(defaultTableModel, ds);
            } else {
                Category a = this.categoryDAO.getCategoryByName(loaiSanPhamCanTim);
                ArrayList<Product> kq = this.productDAO.findProductByCategory(a.getIdCategory());
                System.out.println("So luong: " + kq.size());
                if (kq.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không có sản phẩm phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    xuatDSSanPhamRaTableTuArrayList(defaultTableModel, kq);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameThemSanPham = new javax.swing.JFrame();
        panelThemSanPham = new javax.swing.JPanel();
        labelTitleThemSanPham = new javax.swing.JLabel();
        labelThemTenSanPham = new javax.swing.JLabel();
        textFieldThemTenSanPham = new javax.swing.JTextField();
        labelLoiThemTenSanPham = new javax.swing.JLabel();
        labelThemTheLoaiSanPham = new javax.swing.JLabel();
        comboBoxThemLoaiSanPham = new javax.swing.JComboBox<>();
        labelLoiThemLoaiSanPham = new javax.swing.JLabel();
        labelLoiThemGiaSanPham = new javax.swing.JLabel();
        labelThemMoTaSanPham = new javax.swing.JLabel();
        scrollPaneThemMoTaSanPham = new javax.swing.JScrollPane();
        textAreaThemMoTaSanPham = new javax.swing.JTextArea();
        labelThemAnhSanPham = new javax.swing.JLabel();
        btnThemAnhSanPham = new javax.swing.JButton();
        btnHoanThanhThemAnhSanPham = new javax.swing.JButton();
        frameSuaSanPham = new javax.swing.JFrame();
        panelSuaSanPham = new javax.swing.JPanel();
        labelTitleSuaSanPham = new javax.swing.JLabel();
        labelSuaTenSanPham = new javax.swing.JLabel();
        textFieldSuaTenSanPham = new javax.swing.JTextField();
        labelLoiSuaTenSanPham = new javax.swing.JLabel();
        labelSuaTheLoaiSanPham = new javax.swing.JLabel();
        comboBoxSuaLoaiSanPham = new javax.swing.JComboBox<>();
        labelLoiSuaLoaiSanPham = new javax.swing.JLabel();
        labelLoiSuaGiaSanPham = new javax.swing.JLabel();
        labelSuaMoTaSanPham = new javax.swing.JLabel();
        scrollPaneSuaMoTaSanPham = new javax.swing.JScrollPane();
        textAreaSuaMoTaSanPham = new javax.swing.JTextArea();
        labelSuaAnhSanPham = new javax.swing.JLabel();
        btnSuaAnhSanPham = new javax.swing.JButton();
        btnHoanThanhSuaSanPham = new javax.swing.JButton();
        panelQLSanPhamContent = new javax.swing.JPanel();
        labelTitleQLSanPhamContent = new javax.swing.JLabel();
        labelChucNangSanPhamContent = new javax.swing.JLabel();
        panelChucNangSanPham = new javax.swing.JPanel();
        btnXoaSanPham = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        labelChuacNangQLSanPham = new javax.swing.JLabel();
        panelTimKiemSanPham = new javax.swing.JPanel();
        comboBoxTimSanPhamTheoLoai = new javax.swing.JComboBox<>();
        labelTimKiemSanPhamTheoLoai = new javax.swing.JLabel();
        labelTimKiemSanPhamTheoTen = new javax.swing.JLabel();
        textFieldTimKiemSanPhamTheoTen = new javax.swing.JTextField();
        btnTimKiemSanPham = new javax.swing.JButton();
        btnHuyTimSanPham = new javax.swing.JButton();
        labelDanhSachSanPhamContent = new javax.swing.JLabel();
        scrolPaneDSSanPham = new javax.swing.JScrollPane();
        tableDSSanPham = new javax.swing.JTable();
        scrollPaneXemSanPham = new javax.swing.JScrollPane();
        panelXemSanPham = new javax.swing.JPanel();
        labelXemMaSanPham = new javax.swing.JLabel();
        labelXemNoiDungMaSanPham = new javax.swing.JLabel();
        labelXemTenSanPham = new javax.swing.JLabel();
        labelXemNoiDungTenSanPham = new javax.swing.JLabel();
        labelXemLoaiSanPham = new javax.swing.JLabel();
        labelXemNoiDungLoaiSanPham = new javax.swing.JLabel();
        labelXemNoiDungTinhTrangSanPham = new javax.swing.JLabel();
        labelXemMoTaSanPham = new javax.swing.JLabel();
        scrolPaneXemMoTaSanPham = new javax.swing.JScrollPane();
        textAreaXemMoTaSanPham = new javax.swing.JTextArea();
        labelXemAnhSanPham = new javax.swing.JLabel();

        frameThemSanPham.setTitle("Thêm sản phẩm");

        panelThemSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleThemSanPham.setBackground(new java.awt.Color(16, 185, 129));
        labelTitleThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemSanPham.setText("THÊM SẢN PHẨM");
        labelTitleThemSanPham.setOpaque(true);

        labelThemTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenSanPham.setText("Tên sản phẩm");
        labelThemTenSanPham.setOpaque(true);

        textFieldThemTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenSanPhamKeyPressed(evt);
            }
        });

        labelLoiThemTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemTenSanPham.setOpaque(true);

        labelThemTheLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTheLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTheLoaiSanPham.setText("Thể loại");
        labelThemTheLoaiSanPham.setOpaque(true);

        comboBoxThemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxThemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelLoiThemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemLoaiSanPham.setOpaque(true);

        labelLoiThemGiaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemGiaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemGiaSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemGiaSanPham.setOpaque(true);

        labelThemMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelThemMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemMoTaSanPham.setText("Mô tả");
        labelThemMoTaSanPham.setOpaque(true);

        textAreaThemMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textAreaThemMoTaSanPham.setColumns(20);
        textAreaThemMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaThemMoTaSanPham.setRows(5);
        scrollPaneThemMoTaSanPham.setViewportView(textAreaThemMoTaSanPham);

        labelThemAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelThemAnhSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        labelThemAnhSanPham.setOpaque(true);

        btnThemAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnThemAnhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemAnhSanPham.setForeground(new java.awt.Color(0, 204, 51));
        btnThemAnhSanPham.setText("Ảnh");
        btnThemAnhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemAnhSanPhamMouseClicked(evt);
            }
        });

        btnHoanThanhThemAnhSanPham.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemAnhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemAnhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemAnhSanPham.setText("THÊM");
        btnHoanThanhThemAnhSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        btnHoanThanhThemAnhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemAnhSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemSanPhamLayout = new javax.swing.GroupLayout(panelThemSanPham);
        panelThemSanPham.setLayout(panelThemSanPhamLayout);
        panelThemSanPhamLayout.setHorizontalGroup(
            panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelThemSanPhamLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelThemTenSanPham)
                    .addComponent(textFieldThemTenSanPham)
                    .addComponent(labelLoiThemTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemTheLoaiSanPham)
                    .addComponent(comboBoxThemLoaiSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemGiaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemMoTaSanPham)
                    .addComponent(scrollPaneThemMoTaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelThemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemAnhSanPham))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThemSanPhamLayout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(btnHoanThanhThemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(293, 293, 293))
        );
        panelThemSanPhamLayout.setVerticalGroup(
            panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelThemSanPhamLayout.createSequentialGroup()
                        .addComponent(labelTitleThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelThemSanPhamLayout.createSequentialGroup()
                                .addComponent(labelThemTenSanPham)
                                .addGap(19, 19, 19)
                                .addComponent(textFieldThemTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelLoiThemTenSanPham)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelThemTheLoaiSanPham)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxThemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelLoiThemLoaiSanPham))
                            .addComponent(labelThemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(labelLoiThemGiaSanPham))
                    .addComponent(btnThemAnhSanPham))
                .addGap(18, 18, 18)
                .addComponent(labelThemMoTaSanPham)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneThemMoTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHoanThanhThemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameThemSanPhamLayout = new javax.swing.GroupLayout(frameThemSanPham.getContentPane());
        frameThemSanPham.getContentPane().setLayout(frameThemSanPhamLayout);
        frameThemSanPhamLayout.setHorizontalGroup(
            frameThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameThemSanPhamLayout.setVerticalGroup(
            frameThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaSanPham.setTitle("Thay đổi thông tin sản phẩm");

        panelSuaSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaSanPham.setText("THAY ĐỔI THÔNG TIN SẢN PHẨM");
        labelTitleSuaSanPham.setOpaque(true);

        labelSuaTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenSanPham.setText("Tên sản phẩm");
        labelSuaTenSanPham.setOpaque(true);

        textFieldSuaTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenSanPhamKeyPressed(evt);
            }
        });

        labelLoiSuaTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaTenSanPham.setOpaque(true);

        labelSuaTheLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTheLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTheLoaiSanPham.setText("Thể loại");
        labelSuaTheLoaiSanPham.setOpaque(true);

        comboBoxSuaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxSuaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxSuaLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuaLoaiSanPhamActionPerformed(evt);
            }
        });

        labelLoiSuaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaLoaiSanPham.setOpaque(true);

        labelLoiSuaGiaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaGiaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaGiaSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaGiaSanPham.setOpaque(true);

        labelSuaMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaMoTaSanPham.setText("Mô tả");
        labelSuaMoTaSanPham.setOpaque(true);

        textAreaSuaMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textAreaSuaMoTaSanPham.setColumns(20);
        textAreaSuaMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaSuaMoTaSanPham.setRows(5);
        scrollPaneSuaMoTaSanPham.setViewportView(textAreaSuaMoTaSanPham);

        labelSuaAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaAnhSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelSuaAnhSanPham.setOpaque(true);

        btnSuaAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaAnhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaAnhSanPham.setText("Ảnh");
        btnSuaAnhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaAnhSanPhamMouseClicked(evt);
            }
        });

        btnHoanThanhSuaSanPham.setBackground(new java.awt.Color(254, 122, 54));
        btnHoanThanhSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaSanPham.setText("THAY ĐỔI");
        btnHoanThanhSuaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaSanPhamLayout = new javax.swing.GroupLayout(panelSuaSanPham);
        panelSuaSanPham.setLayout(panelSuaSanPhamLayout);
        panelSuaSanPhamLayout.setHorizontalGroup(
            panelSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelSuaSanPhamLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSuaTenSanPham)
                    .addComponent(textFieldSuaTenSanPham)
                    .addComponent(labelLoiSuaTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaTheLoaiSanPham)
                    .addComponent(comboBoxSuaLoaiSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaGiaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaMoTaSanPham)
                    .addComponent(scrollPaneSuaMoTaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSuaAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaAnhSanPham))
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaSanPhamLayout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addComponent(btnHoanThanhSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );
        panelSuaSanPhamLayout.setVerticalGroup(
            panelSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSuaSanPhamLayout.createSequentialGroup()
                        .addComponent(labelSuaTenSanPham)
                        .addGap(20, 20, 20)
                        .addComponent(textFieldSuaTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiSuaTenSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSuaTheLoaiSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxSuaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelLoiSuaLoaiSanPham))
                    .addComponent(labelSuaAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaAnhSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaGiaSanPham)
                .addGap(18, 18, 18)
                .addComponent(labelSuaMoTaSanPham)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneSuaMoTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHoanThanhSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameSuaSanPhamLayout = new javax.swing.GroupLayout(frameSuaSanPham.getContentPane());
        frameSuaSanPham.getContentPane().setLayout(frameSuaSanPhamLayout);
        frameSuaSanPhamLayout.setHorizontalGroup(
            frameSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaSanPhamLayout.setVerticalGroup(
            frameSuaSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLSanPhamContent.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLSanPhamContent.setBackground(new java.awt.Color(248, 248, 255));
        labelTitleQLSanPhamContent.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitleQLSanPhamContent.setText("QUẢN LÝ SẢN PHẨM");
        labelTitleQLSanPhamContent.setOpaque(true);

        labelChucNangSanPhamContent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelChucNangSanPhamContent.setForeground(new java.awt.Color(51, 0, 255));
        labelChucNangSanPhamContent.setText("Chức năng");

        panelChucNangSanPham.setBackground(new java.awt.Color(255, 255, 255));

        btnXoaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaSanPham.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaSanPham.setText("XÓA");
        btnXoaSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSanPhamMouseClicked(evt);
            }
        });

        btnThemSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemSanPham.setForeground(new java.awt.Color(0, 153, 51));
        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemSanPham.setText("THÊM");
        btnThemSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemSanPham.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSanPhamMouseClicked(evt);
            }
        });

        btnSuaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaSanPham.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaSanPham.setText("SỬA");
        btnSuaSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaSanPham.setPreferredSize(new java.awt.Dimension(80, 42));
        btnSuaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSanPhamMouseClicked(evt);
            }
        });
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangSanPhamLayout = new javax.swing.GroupLayout(panelChucNangSanPham);
        panelChucNangSanPham.setLayout(panelChucNangSanPhamLayout);
        panelChucNangSanPhamLayout.setHorizontalGroup(
            panelChucNangSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelChucNangSanPhamLayout.setVerticalGroup(
            panelChucNangSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelChucNangSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                        .addComponent(btnSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        labelChuacNangQLSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelChuacNangQLSanPham.setForeground(new java.awt.Color(51, 0, 255));
        labelChuacNangQLSanPham.setText("Tìm kiếm");

        panelTimKiemSanPham.setBackground(new java.awt.Color(255, 255, 255));

        comboBoxTimSanPhamTheoLoai.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTimSanPhamTheoLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimSanPhamTheoLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboBoxTimSanPhamTheoLoai.setOpaque(true);
        comboBoxTimSanPhamTheoLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxTimSanPhamTheoLoaiMouseClicked(evt);
            }
        });
        comboBoxTimSanPhamTheoLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimSanPhamTheoLoaiActionPerformed(evt);
            }
        });

        labelTimKiemSanPhamTheoLoai.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemSanPhamTheoLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemSanPhamTheoLoai.setText("Tìm theo thể loại");
        labelTimKiemSanPhamTheoLoai.setOpaque(true);

        labelTimKiemSanPhamTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemSanPhamTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemSanPhamTheoTen.setText("Tìm theo tên sản phẩm");
        labelTimKiemSanPhamTheoTen.setOpaque(true);

        textFieldTimKiemSanPhamTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimKiemSanPhamTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimKiemSanPhamTheoTen.setOpaque(true);

        btnTimKiemSanPham.setBackground(new java.awt.Color(0, 141, 218));
        btnTimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemSanPham.setText("Tìm kiếm");
        btnTimKiemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemSanPhamMouseClicked(evt);
            }
        });

        btnHuyTimSanPham.setBackground(new java.awt.Color(255, 0, 51));
        btnHuyTimSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimSanPham.setText("Hủy tìm");
        btnHuyTimSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemSanPhamLayout = new javax.swing.GroupLayout(panelTimKiemSanPham);
        panelTimKiemSanPham.setLayout(panelTimKiemSanPhamLayout);
        panelTimKiemSanPhamLayout.setHorizontalGroup(
            panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTimKiemSanPhamTheoLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(comboBoxTimSanPhamTheoLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTimKiemSanPhamLayout.createSequentialGroup()
                        .addComponent(textFieldTimKiemSanPhamTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimSanPham))
                    .addComponent(labelTimKiemSanPhamTheoTen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTimKiemSanPhamLayout.setVerticalGroup(
            panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimKiemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTimKiemSanPhamTheoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(labelTimKiemSanPhamTheoLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTimKiemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxTimSanPhamTheoLoai)
                    .addComponent(textFieldTimKiemSanPhamTheoTen)
                    .addComponent(btnHuyTimSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelDanhSachSanPhamContent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelDanhSachSanPhamContent.setForeground(new java.awt.Color(204, 51, 0));
        labelDanhSachSanPhamContent.setText("DANH SÁCH SẢN PHẨM");

        scrolPaneDSSanPham.setBackground(new java.awt.Color(255, 255, 255));

        tableDSSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tableDSSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDSSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSSanPhamMouseClicked(evt);
            }
        });
        scrolPaneDSSanPham.setViewportView(tableDSSanPham);

        scrollPaneXemSanPham.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneXemSanPham.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelXemSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelXemMaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaSanPham.setText("Mã sản phẩm");
        labelXemMaSanPham.setOpaque(true);

        labelXemNoiDungMaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungMaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaSanPham.setText("0");
        labelXemNoiDungMaSanPham.setOpaque(true);

        labelXemTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenSanPham.setText("Tên sản phẩm");
        labelXemTenSanPham.setOpaque(true);

        labelXemNoiDungTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungTenSanPham.setText("Sản phẩm 1");
        labelXemNoiDungTenSanPham.setOpaque(true);

        labelXemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemLoaiSanPham.setText("Thể loại");
        labelXemLoaiSanPham.setOpaque(true);

        labelXemNoiDungLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungLoaiSanPham.setText("Loại 1");
        labelXemNoiDungLoaiSanPham.setOpaque(true);

        labelXemNoiDungTinhTrangSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungTinhTrangSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungTinhTrangSanPham.setOpaque(true);

        labelXemMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMoTaSanPham.setText("Mô tả");
        labelXemMoTaSanPham.setOpaque(true);

        textAreaXemMoTaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textAreaXemMoTaSanPham.setColumns(20);
        textAreaXemMoTaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaXemMoTaSanPham.setRows(5);
        scrolPaneXemMoTaSanPham.setViewportView(textAreaXemMoTaSanPham);

        labelXemAnhSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelXemAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/products/topping-hat-sen-10571615605962.jpg"))); // NOI18N
        labelXemAnhSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemAnhSanPham.setOpaque(true);

        javax.swing.GroupLayout panelXemSanPhamLayout = new javax.swing.GroupLayout(panelXemSanPham);
        panelXemSanPham.setLayout(panelXemSanPhamLayout);
        panelXemSanPhamLayout.setHorizontalGroup(
            panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(scrolPaneXemMoTaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                        .addGroup(panelXemSanPhamLayout.createSequentialGroup()
                            .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelXemTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelXemMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelXemNoiDungMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelXemNoiDungTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                    .addComponent(labelXemMoTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelXemSanPhamLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(labelXemNoiDungTinhTrangSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXemSanPhamLayout.createSequentialGroup()
                        .addComponent(labelXemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemNoiDungLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelXemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelXemSanPhamLayout.setVerticalGroup(
            panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemMaSanPham)
                    .addComponent(labelXemNoiDungMaSanPham))
                .addGap(28, 28, 28)
                .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemTenSanPham)
                    .addComponent(labelXemNoiDungTenSanPham))
                .addGap(28, 28, 28)
                .addGroup(panelXemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemLoaiSanPham)
                    .addComponent(labelXemNoiDungLoaiSanPham))
                .addGap(20, 20, 20)
                .addComponent(labelXemNoiDungTinhTrangSanPham)
                .addGap(18, 18, 18)
                .addComponent(labelXemMoTaSanPham)
                .addGap(18, 18, 18)
                .addComponent(scrolPaneXemMoTaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelXemAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232))
        );

        scrollPaneXemSanPham.setViewportView(panelXemSanPham);

        javax.swing.GroupLayout panelQLSanPhamContentLayout = new javax.swing.GroupLayout(panelQLSanPhamContent);
        panelQLSanPhamContent.setLayout(panelQLSanPhamContentLayout);
        panelQLSanPhamContentLayout.setHorizontalGroup(
            panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLSanPhamContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQLSanPhamContentLayout.createSequentialGroup()
                        .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChucNangSanPhamContent)
                            .addComponent(panelChucNangSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChuacNangQLSanPham)
                            .addComponent(panelTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelTitleQLSanPhamContent)
                    .addComponent(labelDanhSachSanPhamContent)
                    .addGroup(panelQLSanPhamContentLayout.createSequentialGroup()
                        .addComponent(scrolPaneDSSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneXemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        panelQLSanPhamContentLayout.setVerticalGroup(
            panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLSanPhamContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLSanPhamContent)
                .addGap(18, 18, 18)
                .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChucNangSanPhamContent)
                    .addComponent(labelChuacNangQLSanPham))
                .addGap(18, 18, 18)
                .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTimKiemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChucNangSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(labelDanhSachSanPhamContent)
                .addGap(18, 18, 18)
                .addGroup(panelQLSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneXemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(scrolPaneDSSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelQLSanPhamContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelQLSanPhamContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSanPhamMouseClicked
        // TODO add your handling code here:
        // xóa sản phẩm
        xoaSanPham(this.tableDSSanPham, this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_btnXoaSanPhamMouseClicked

    private void btnThemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiện ra frame thêm sản phẩm
        this.frameThemSanPham.setSize(800, 750);
        this.frameThemSanPham.setLocationRelativeTo(null);
        this.frameThemSanPham.setVisible(true);
        this.frameThemSanPham.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemSanPhamMouseClicked

    private void btnSuaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiện lên frame sửa thông tin sản phẩm
        int row = this.tableDSSanPham.getSelectedRow();
        if (row >= 0) {
            this.frameSuaSanPham.setSize(800, 750);
            this.frameSuaSanPham.setLocationRelativeTo(null);
            this.frameSuaSanPham.setVisible(true);
            this.frameSuaSanPham.getContentPane().setBackground(Color.WHITE);
            hienThiSanPhamRaFrameSuaSanPham(this.tableDSSanPham, this.defaultTableModelDSSanPham);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_btnSuaSanPhamMouseClicked

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void comboBoxTimSanPhamTheoLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxTimSanPhamTheoLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTimSanPhamTheoLoaiMouseClicked

    private void comboBoxTimSanPhamTheoLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimSanPhamTheoLoaiActionPerformed
        // TODO add your handling code here:
        timSanPhamTheoTheLoai(this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_comboBoxTimSanPhamTheoLoaiActionPerformed

    private void btnTimKiemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemSanPhamMouseClicked
        // TODO add your handling code here:
        timSanPhamTheoTen(this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_btnTimKiemSanPhamMouseClicked

    private void btnHuyTimSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimSanPhamMouseClicked
        // TODO add your handling code here:
        getAllProduct(this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_btnHuyTimSanPhamMouseClicked

    private void tableDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSSanPhamMouseClicked
        // TODO add your handling code here:
        hienThiThongTinSanPhamVaoPanelXemThongTinSanPham(this.tableDSSanPham, this.defaultTableModelDSSanPham);
    }//GEN-LAST:event_tableDSSanPhamMouseClicked

    private void btnThemAnhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemAnhSanPhamMouseClicked
        // TODO add your handling code here:
        // click vào đây sẽ chọn ảnh cho sản phẩm
        themAnhSanPham();
    }//GEN-LAST:event_btnThemAnhSanPhamMouseClicked

    private void btnHoanThanhThemAnhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemAnhSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hoàn thành việc thêm sản phẩm
        themSanPham(this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_btnHoanThanhThemAnhSanPhamMouseClicked

    private void btnSuaAnhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaAnhSanPhamMouseClicked
        // TODO add your handling code here:
        suaAnhSanPham();
    }//GEN-LAST:event_btnSuaAnhSanPhamMouseClicked

    private void btnHoanThanhSuaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaSanPhamMouseClicked
        // TODO add your handling code here:
        suaSanPham(this.tableDSSanPham, this.defaultTableModelDSSanPham, this.dsSanPham);
    }//GEN-LAST:event_btnHoanThanhSuaSanPhamMouseClicked

    private void comboBoxSuaLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuaLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSuaLoaiSanPhamActionPerformed

    private void textFieldThemTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenSanPhamKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenSanPham.getText().equals("")) {
            this.labelLoiThemTenSanPham.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenSanPhamKeyPressed

    private void textFieldSuaTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenSanPhamKeyPressed
        // TODO add your handling code here:
         if (!this.labelLoiSuaTenSanPham.getText().equals("")) {
            this.labelLoiSuaTenSanPham.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenSanPhamKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaSanPham;
    private javax.swing.JButton btnHoanThanhThemAnhSanPham;
    private javax.swing.JButton btnHuyTimSanPham;
    private javax.swing.JButton btnSuaAnhSanPham;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnThemAnhSanPham;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimKiemSanPham;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JComboBox<String> comboBoxSuaLoaiSanPham;
    private javax.swing.JComboBox<String> comboBoxThemLoaiSanPham;
    private javax.swing.JComboBox<String> comboBoxTimSanPhamTheoLoai;
    private javax.swing.JFrame frameSuaSanPham;
    private javax.swing.JFrame frameThemSanPham;
    private javax.swing.JLabel labelChuacNangQLSanPham;
    private javax.swing.JLabel labelChucNangSanPhamContent;
    private javax.swing.JLabel labelDanhSachSanPhamContent;
    private javax.swing.JLabel labelLoiSuaGiaSanPham;
    private javax.swing.JLabel labelLoiSuaLoaiSanPham;
    private javax.swing.JLabel labelLoiSuaTenSanPham;
    private javax.swing.JLabel labelLoiThemGiaSanPham;
    private javax.swing.JLabel labelLoiThemLoaiSanPham;
    private javax.swing.JLabel labelLoiThemTenSanPham;
    private javax.swing.JLabel labelSuaAnhSanPham;
    private javax.swing.JLabel labelSuaMoTaSanPham;
    private javax.swing.JLabel labelSuaTenSanPham;
    private javax.swing.JLabel labelSuaTheLoaiSanPham;
    private javax.swing.JLabel labelThemAnhSanPham;
    private javax.swing.JLabel labelThemMoTaSanPham;
    private javax.swing.JLabel labelThemTenSanPham;
    private javax.swing.JLabel labelThemTheLoaiSanPham;
    private javax.swing.JLabel labelTimKiemSanPhamTheoLoai;
    private javax.swing.JLabel labelTimKiemSanPhamTheoTen;
    private javax.swing.JLabel labelTitleQLSanPhamContent;
    private javax.swing.JLabel labelTitleSuaSanPham;
    private javax.swing.JLabel labelTitleThemSanPham;
    private javax.swing.JLabel labelXemAnhSanPham;
    private javax.swing.JLabel labelXemLoaiSanPham;
    private javax.swing.JLabel labelXemMaSanPham;
    private javax.swing.JLabel labelXemMoTaSanPham;
    private javax.swing.JLabel labelXemNoiDungLoaiSanPham;
    private javax.swing.JLabel labelXemNoiDungMaSanPham;
    private javax.swing.JLabel labelXemNoiDungTenSanPham;
    private javax.swing.JLabel labelXemNoiDungTinhTrangSanPham;
    private javax.swing.JLabel labelXemTenSanPham;
    private javax.swing.JPanel panelChucNangSanPham;
    private javax.swing.JPanel panelQLSanPhamContent;
    private javax.swing.JPanel panelSuaSanPham;
    private javax.swing.JPanel panelThemSanPham;
    private javax.swing.JPanel panelTimKiemSanPham;
    private javax.swing.JPanel panelXemSanPham;
    private javax.swing.JScrollPane scrolPaneDSSanPham;
    private javax.swing.JScrollPane scrolPaneXemMoTaSanPham;
    private javax.swing.JScrollPane scrollPaneSuaMoTaSanPham;
    private javax.swing.JScrollPane scrollPaneThemMoTaSanPham;
    private javax.swing.JScrollPane scrollPaneXemSanPham;
    private javax.swing.JTable tableDSSanPham;
    private javax.swing.JTextArea textAreaSuaMoTaSanPham;
    private javax.swing.JTextArea textAreaThemMoTaSanPham;
    private javax.swing.JTextArea textAreaXemMoTaSanPham;
    private javax.swing.JTextField textFieldSuaTenSanPham;
    private javax.swing.JTextField textFieldThemTenSanPham;
    private javax.swing.JTextField textFieldTimKiemSanPhamTheoTen;
    // End of variables declaration//GEN-END:variables
}
