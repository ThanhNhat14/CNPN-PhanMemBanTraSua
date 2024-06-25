/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.CategoryDAO;
import Dao.ProductDAO;
import Dao.ProductSizeDAO;
import Dao.SizeDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Category;
import Model.Manager.Product;
import Model.Manager.ProductSize;
import Model.Manager.Size;
import Utils.Manager.FormatMoney;
import Utils.Manager.IsPositiveIntegerManager;

/**
 *
 * @author Admin
 */
public class QLMenu extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLMenu
     */
    public ArrayList<Category> dsTheLoaiSP;
    public CategoryDAO categoryDAO;
    
    public ArrayList<Size> dsSize;
    public SizeDAO sizeDAO;
    
    public ProductDAO productDAO;
    public DefaultTableModel defaultTableModelDSSP;
    public ArrayList<Product> dsSanPham;
    
    public ProductSizeDAO productDetailDAO;
    public DefaultTableModel defaultTableModelDSChiTietSP;
    public ArrayList<ProductSize> dsChiTietSanPham;
    
    public QLMenu() {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        initComponents();
        
        this.dsSanPham = new ArrayList<>();
        this.dsTheLoaiSP = new ArrayList<>();
        
        this.dsSize = new ArrayList<>();
        this.sizeDAO = new SizeDAO();
        
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
        
        this.productDetailDAO = new ProductSizeDAO();
        this.defaultTableModelDSChiTietSP = new DefaultTableModel();
        this.dsChiTietSanPham = new ArrayList<>();
        
        this.defaultTableModelDSSP = new DefaultTableModel();
        this.tableDSSanPhamThemCTSP.setModel(this.defaultTableModelDSSP);
        String[] columnsDSSanPham = {"Mã sản phẩm", "Tên sản phẩm", "Thể loại"};
        this.defaultTableModelDSSP.setColumnIdentifiers(columnsDSSanPham);
        JTableHeader headerDSSanPham = this.tableDSSanPhamThemCTSP.getTableHeader();
        headerDSSanPham.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSSanPham.setBackground(new Color(0x43CD80));
        headerDSSanPham.setForeground(Color.WHITE);
        headerDSSanPham.setPreferredSize(new Dimension(headerDSSanPham.getWidth(), 30));
        this.tableDSSanPhamThemCTSP.setRowHeight(30);
        this.tableDSSanPhamThemCTSP.setSelectionBackground(new Color(0xE2E8F0));
        this.tableDSSanPhamThemCTSP.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.tableDSSanPhamThemCTSP.getColumnModel().getColumn(1).setPreferredWidth(220);
        this.tableDSSanPhamThemCTSP.getColumnModel().getColumn(2).setPreferredWidth(130);
        
        this.tableDSChiTietSP.setModel(this.defaultTableModelDSChiTietSP);
        String[] columnsDSChiTietSP = {"Mã CTSP", "Tên sản phẩm", "Size", "Giá bán"};
        this.defaultTableModelDSChiTietSP.setColumnIdentifiers(columnsDSChiTietSP);
        JTableHeader headerDSChiTietSanPham = this.tableDSChiTietSP.getTableHeader();
        headerDSChiTietSanPham.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSChiTietSanPham.setBackground(new Color(0x43CD80));
        headerDSChiTietSanPham.setForeground(Color.WHITE);
        headerDSChiTietSanPham.setPreferredSize(new Dimension(headerDSChiTietSanPham.getWidth(), 30));
        this.tableDSChiTietSP.setRowHeight(30);
        this.tableDSChiTietSP.setSelectionBackground(new Color(0xE2E8F0));
        this.tableDSChiTietSP.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.tableDSChiTietSP.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.tableDSChiTietSP.getColumnModel().getColumn(3).setPreferredWidth(110);
        this.tableDSChiTietSP.getColumnModel().getColumn(1).setPreferredWidth(280);
        
        this.dsSize = this.sizeDAO.getAllSize();
        for (Size i : this.dsSize) {
            this.comboBoxChonSizeChiTietSP.addItem(i.getNameOfSize());
        }
        for (Size i : this.dsSize) {
            this.comboBoxSuaSizeChiTietSP.addItem(i.getNameOfSize());
        }
        
        this.dsTheLoaiSP = this.categoryDAO.getAllCategory();
        for (Category i : this.dsTheLoaiSP) {
            this.comboBoxTimKiemTheoLoaiSanPham.addItem(i.getNameCategory());
        }
        
        getAllProduct(this.defaultTableModelDSSP, this.dsSanPham);
        getAllProductDetail(this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);
        
        this.scrollPaneMenu.getViewport().setBackground(Color.WHITE);
        this.scrollPaneDSSanPham.getViewport().setBackground(Color.WHITE);
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
    
    public void hienThiSanPhamRaPanelThemChiTietSP(JTable table, DefaultTableModel defaultTableModel) {
        Product product = layProductKhiClickVaoTableProduct(table, defaultTableModel);
        if (product != null) {
            this.labelHienThiMaSP.setText(product.getIdProduct() + "");
            this.labelHienThiTenSP.setText(product.getNameProduct());
            Category category = this.categoryDAO.getCategoryById(product.getIdCategory());
            this.labelHienThiTheLoaiSP.setText(category.getNameCategory());
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void resetPanelThemChiTietSp() {
        this.labelHienThiMaSP.setText("0");
        this.labelHienThiTenSP.setText("Sản phẩm 1");
        this.labelHienThiTheLoaiSP.setText("Loại sản phẩm 1");
        this.labelLoiThemGiaChiTietSP.setText("");
    }
    
    public void themChiTietSanPham(JTable table, DefaultTableModel defaultTableModel) {
        Product product = layProductKhiClickVaoTableProduct(table, defaultTableModel);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String thongBaoLoi = "";
            if (this.labelHienThiMaSP.getText().equals("0")) {
                thongBaoLoi += "Vui lòng chọn một sản phẩm";
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (this.comboBoxChonSizeChiTietSP.getSelectedItem().equals("")) {
                thongBaoLoi += "Vui lòng chọn size";
                this.labelLoiThemSizeChiTietSP.setText("Vui lòng chọn size");
            }
            if (this.textFieldThemGiaBanChiTietSP.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập giá bán";
                this.labelLoiThemGiaChiTietSP.setText("Vui lòng nhập giá bán");
            } else {
                if (!IsPositiveIntegerManager.isPositiveInteger(this.textFieldThemGiaBanChiTietSP.getText())) {
                    thongBaoLoi += "Giá topping phải là một số nguyên dương\n";
                    this.labelLoiThemGiaChiTietSP.setText("Giá topping phải là một số nguyên dương");
                }
            }
            if (thongBaoLoi.equals("")) {
                String nameSize = this.comboBoxChonSizeChiTietSP.getSelectedItem() + "";
                Size size = this.sizeDAO.getSizeByName(nameSize);
                int idSize = size.getIdSize();
                int price = Integer.parseInt(this.textFieldThemGiaBanChiTietSP.getText() + "");
                boolean statusProductSize = true;
                ProductSize a = new ProductSize(product.getIdProduct(), idSize, price, statusProductSize);
                this.productDetailDAO.insertProductSize(a);
                resetPanelThemChiTietSp();
            }
        }
    }
    
    public void timSanPhamTheoTen(DefaultTableModel defaultTableModel, ArrayList<Product> ds) {
        String tenSPCanTim = this.textFieldTimTheoTenSPThemCTSP.getText();
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

    // ==================== GIÁ SẢN PHẨM THEO SIZE ====================
    public void xuatDSChiTietSPRaTable(DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        defaultTableModel.setRowCount(0);
        Product a = new Product();
        Size b = new Size();
        for (ProductSize i : ds) {
            a = this.productDAO.getProductById(i.getIdProduct());
            b = this.sizeDAO.getSizeById(i.getIdSize());
            Object[] data = {i.getIdProductSize(), a.getNameProduct(), b.getNameOfSize(), FormatMoney.formatMoney(i.getPrice())};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }
    
    public void getAllProductDetail(DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        ds = this.productDetailDAO.getAllProductSize();
        xuatDSChiTietSPRaTable(defaultTableModel, ds);
    }
    
    public ProductSize layProductDetailKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row <= 0) {
            return null;
        } else {
            int theId = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            ProductSize a = this.productDetailDAO.getProductSizeById(theId);
            return a;
        }
    }
    
    public void xoaChiTietSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        int row = table.getSelectedRow();
        if (row > 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không");
            if (confirm == JOptionPane.OK_OPTION) {
                ProductSize a = layProductDetailKhiClickVaoBang(table, defaultTableModel);
                this.productDetailDAO.deleteProductSize(a.getIdProductSize());
                getAllProductDetail(defaultTableModel, ds);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hienThiChiTietSanPhamRaFrameSuaSanPham(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row > 0) {
            ProductSize a = layProductDetailKhiClickVaoBang(table, defaultTableModel);
            if (a != null) {
                this.labelHienThiSuaMaChiTietSP.setText(a.getIdProductSize() + "");
                this.labelHienThiSuaMaSanPham.setText(a.getIdProduct() + "");
                Product b = this.productDAO.getProductById(a.getIdProduct());
                this.labelHienThiSuaTenSanPham.setText(b.getNameProduct());
                Size c = this.sizeDAO.getSizeById(a.getIdSize());
                this.comboBoxSuaSizeChiTietSP.setSelectedItem(c.getNameOfSize());
                this.textFieldSuaGiaChiTietSanPham.setText(a.getPrice() + "");
            }
        }
    }
    
    public void resetFrameSuaChiTietSanPham() {
        this.labelHienThiSuaMaChiTietSP.setText("0");
        this.labelHienThiSuaMaSanPham.setText("0");
        this.labelHienThiSuaTenSanPham.setText("Sản phẩm 1");
        this.textFieldSuaGiaChiTietSanPham.setText("");
        this.labelLoiSuaGiaCHiTietSP.setText("");
    }
    
    public void suaChiTietSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        int row = table.getSelectedRow();
        if (row > 0) {
            ProductSize a = layProductDetailKhiClickVaoBang(table, defaultTableModel);
            String thongBaoLoi = "";
            if (this.comboBoxSuaSizeChiTietSP.getSelectedItem().equals("")) {
                thongBaoLoi += "Vui lòng chọn size";
            }
            if (this.textFieldSuaGiaChiTietSanPham.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập giá bán";
                this.labelLoiSuaGiaCHiTietSP.setText("Vui lòng nhập giá bán");
            } else {
                if (!IsPositiveIntegerManager.isPositiveInteger(this.textFieldSuaGiaChiTietSanPham.getText())) {
                    thongBaoLoi += "Giá topping phải là một số nguyên dương\n";
                    this.labelLoiSuaGiaCHiTietSP.setText("Giá topping phải là một số nguyên dương");
                }
            }
            if (thongBaoLoi.equals("")) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa sản phẩm này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    String tenSize = this.comboBoxSuaSizeChiTietSP.getSelectedItem() + "";
                    Size size = this.sizeDAO.getSizeByName(tenSize);
                    int giaBan = Integer.parseInt(this.textFieldSuaGiaChiTietSanPham.getText());
                    boolean statusProductSize = true;
                    ProductSize productDetail = new ProductSize(a.getIdProduct(), size.getIdSize(), giaBan, statusProductSize);
                    boolean ktTrungSanPhamTheoSize = this.productDetailDAO.isDuplicateProductSize(a.getIdProduct(), size.getIdSize());
                    System.out.println(size.getNameOfSize());
                    if (size.getIdSize() == a.getIdSize()) {
                        ktTrungSanPhamTheoSize = false;
                    }
                    if (ktTrungSanPhamTheoSize == false) {
                        this.productDetailDAO.updateProductSize(a.getIdProductSize(), productDetail);
                        getAllProductDetail(defaultTableModel, ds);
                        resetFrameSuaChiTietSanPham();
                        this.frameSuaChiTietSanPham.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sản phẩm theo size này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void timChiTietSanPhamTheoTenSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        if (this.textFieldTimTheoTenSanPham.getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.labelLoiTimTheoTenSanPham.setText("Vui lòng nhập tên sản phẩm cần tìm");
        } else {
            String tenSPCanTim = this.textFieldTimTheoTenSanPham.getText();
            ArrayList<ProductSize> kq = this.productDetailDAO.findProductSizeByProductName(tenSPCanTim);
            xuatDSChiTietSPRaTable(defaultTableModel, kq);
        }
    }
    
    public void timKiemChiTietSanPhamTheoTenLoaiSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<ProductSize> ds) {
        String tenTheLoaiCanTim = this.comboBoxTimKiemTheoLoaiSanPham.getSelectedItem() + "";
        if (tenTheLoaiCanTim.equals("Tất cả")) {
            getAllProductDetail(defaultTableModel, ds);
        } else {
            ArrayList<ProductSize> kq = this.productDetailDAO.findProductSizeByCategoryName(tenTheLoaiCanTim);
            xuatDSChiTietSPRaTable(defaultTableModel, kq);
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

        frameSuaChiTietSanPham = new javax.swing.JFrame();
        panelSuaChiTietSanPham = new javax.swing.JPanel();
        labelTitleSuaChiTietSanPham = new javax.swing.JLabel();
        labelSuaMaChiTietSanPham = new javax.swing.JLabel();
        labelHienThiSuaMaChiTietSP = new javax.swing.JLabel();
        labelSuaMaSanPham = new javax.swing.JLabel();
        labelHienThiSuaMaSanPham = new javax.swing.JLabel();
        labelSuaTenSanPham = new javax.swing.JLabel();
        labelHienThiSuaTenSanPham = new javax.swing.JLabel();
        labelSuaSizeChiTietSP = new javax.swing.JLabel();
        comboBoxSuaSizeChiTietSP = new javax.swing.JComboBox<>();
        labelLoiSuaGiaChiTietSanPham = new javax.swing.JLabel();
        textFieldSuaGiaChiTietSanPham = new javax.swing.JTextField();
        labelLoiSuaGiaCHiTietSP = new javax.swing.JLabel();
        btnHoanThanhSuaChiTietSP = new javax.swing.JButton();
        panelQLMenu = new javax.swing.JPanel();
        labelTitleQLMenu = new javax.swing.JLabel();
        panelChucNangMenu = new javax.swing.JPanel();
        panelBtns = new javax.swing.JPanel();
        btnTrangChuMenu = new javax.swing.JButton();
        btnThemChiTietSanPham = new javax.swing.JButton();
        panelParentMenu = new javax.swing.JPanel();
        panelTrangChuMenu = new javax.swing.JPanel();
        scrollPaneMenu = new javax.swing.JScrollPane();
        tableDSChiTietSP = new javax.swing.JTable();
        panelTimKiemTrangChuMenu = new javax.swing.JPanel();
        labelTimKiemTheoLoaiSanPham = new javax.swing.JLabel();
        comboBoxTimKiemTheoLoaiSanPham = new javax.swing.JComboBox<>();
        labelLoiTimKiemTheoLoaiSanPham = new javax.swing.JLabel();
        labelTimKiemTheoTenSanPham = new javax.swing.JLabel();
        textFieldTimTheoTenSanPham = new javax.swing.JTextField();
        labelLoiTimTheoTenSanPham = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        btnHuyTim = new javax.swing.JButton();
        btnXoaChiTietSanPham = new javax.swing.JButton();
        btnSuaChiTietSanPham = new javax.swing.JButton();
        panelThemChiTietSP = new javax.swing.JPanel();
        panelHienThiSanPham = new javax.swing.JPanel();
        labelTimKiemTheoTenSPThemCTSP = new javax.swing.JLabel();
        textFieldTimTheoTenSPThemCTSP = new javax.swing.JTextField();
        labelLoiTimTheoTenSPThemCTSP = new javax.swing.JLabel();
        btnChonSanPham = new javax.swing.JButton();
        btnTimTheoTenSPThemCTSP = new javax.swing.JButton();
        btnHuyTimTheoTenSPThemCTSP = new javax.swing.JButton();
        scrollPaneDSSanPham = new javax.swing.JScrollPane();
        tableDSSanPhamThemCTSP = new javax.swing.JTable();
        panelNoiDungThemCTSP = new javax.swing.JPanel();
        labelXemMaSP = new javax.swing.JLabel();
        labelHienThiMaSP = new javax.swing.JLabel();
        labelXemTenSP = new javax.swing.JLabel();
        labelHienThiTenSP = new javax.swing.JLabel();
        labelXemTheLoaiSP = new javax.swing.JLabel();
        labelHienThiTheLoaiSP = new javax.swing.JLabel();
        labelChonSize = new javax.swing.JLabel();
        comboBoxChonSizeChiTietSP = new javax.swing.JComboBox<>();
        labelGiaBanChiTietSP = new javax.swing.JLabel();
        textFieldThemGiaBanChiTietSP = new javax.swing.JTextField();
        labelLoiThemSizeChiTietSP = new javax.swing.JLabel();
        labelLoiThemGiaChiTietSP = new javax.swing.JLabel();
        btnHoanThanhThemChiTietSP = new javax.swing.JButton();

        frameSuaChiTietSanPham.setTitle("Thay đổi thông tin chi tiết sản phẩm");

        panelSuaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaChiTietSanPham.setForeground(new java.awt.Color(68, 100, 187));
        labelTitleSuaChiTietSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaChiTietSanPham.setText("THAY ĐỔI CHI TIẾT SẢN PHẨM");

        labelSuaMaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaMaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaMaChiTietSanPham.setText("Mã chi tiết sản phẩm");
        labelSuaMaChiTietSanPham.setOpaque(true);

        labelHienThiSuaMaChiTietSP.setBackground(new java.awt.Color(226, 232, 240));
        labelHienThiSuaMaChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiSuaMaChiTietSP.setText("0");
        labelHienThiSuaMaChiTietSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiSuaMaChiTietSP.setOpaque(true);

        labelSuaMaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaMaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaMaSanPham.setText("Mã sản phẩm");
        labelSuaMaSanPham.setOpaque(true);

        labelHienThiSuaMaSanPham.setBackground(new java.awt.Color(226, 232, 240));
        labelHienThiSuaMaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiSuaMaSanPham.setText("0");
        labelHienThiSuaMaSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiSuaMaSanPham.setOpaque(true);

        labelSuaTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenSanPham.setText("Tên sản phẩm");
        labelSuaTenSanPham.setOpaque(true);

        labelHienThiSuaTenSanPham.setBackground(new java.awt.Color(226, 232, 240));
        labelHienThiSuaTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiSuaTenSanPham.setText("Sản phẩm 1");
        labelHienThiSuaTenSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiSuaTenSanPham.setOpaque(true);

        labelSuaSizeChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaSizeChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaSizeChiTietSP.setText("Size");
        labelSuaSizeChiTietSP.setOpaque(true);

        comboBoxSuaSizeChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxSuaSizeChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelLoiSuaGiaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaGiaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelLoiSuaGiaChiTietSanPham.setText("Giá bán");
        labelLoiSuaGiaChiTietSanPham.setOpaque(true);

        textFieldSuaGiaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaGiaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaGiaChiTietSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaGiaChiTietSanPhamKeyPressed(evt);
            }
        });

        labelLoiSuaGiaCHiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaGiaCHiTietSP.setForeground(new java.awt.Color(255, 0, 0));

        btnHoanThanhSuaChiTietSP.setBackground(new java.awt.Color(68, 100, 187));
        btnHoanThanhSuaChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaChiTietSP.setText("THAY ĐỔI");
        btnHoanThanhSuaChiTietSP.setBorder(null);
        btnHoanThanhSuaChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaChiTietSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaChiTietSanPhamLayout = new javax.swing.GroupLayout(panelSuaChiTietSanPham);
        panelSuaChiTietSanPham.setLayout(panelSuaChiTietSanPhamLayout);
        panelSuaChiTietSanPhamLayout.setHorizontalGroup(
            panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaChiTietSanPhamLayout.createSequentialGroup()
                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaChiTietSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleSuaChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSuaChiTietSanPhamLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSuaMaChiTietSanPham)
                            .addGroup(panelSuaChiTietSanPhamLayout.createSequentialGroup()
                                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelHienThiSuaMaChiTietSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(comboBoxSuaSizeChiTietSP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelHienThiSuaMaSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelSuaMaSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(labelSuaSizeChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelLoiSuaGiaCHiTietSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelSuaTenSanPham)
                                    .addComponent(labelHienThiSuaTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                    .addComponent(labelLoiSuaGiaChiTietSanPham)
                                    .addComponent(textFieldSuaGiaChiTietSanPham))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelSuaChiTietSanPhamLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btnHoanThanhSuaChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        panelSuaChiTietSanPhamLayout.setVerticalGroup(
            panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSuaMaChiTietSanPham)
                .addGap(18, 18, 18)
                .addComponent(labelHienThiSuaMaChiTietSP)
                .addGap(18, 18, 18)
                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSuaMaSanPham)
                    .addComponent(labelSuaTenSanPham))
                .addGap(18, 18, 18)
                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHienThiSuaMaSanPham)
                    .addComponent(labelHienThiSuaTenSanPham))
                .addGap(18, 18, 18)
                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSuaSizeChiTietSP)
                    .addComponent(labelLoiSuaGiaChiTietSanPham))
                .addGap(18, 18, 18)
                .addGroup(panelSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSuaSizeChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldSuaGiaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaGiaCHiTietSP)
                .addGap(31, 31, 31)
                .addComponent(btnHoanThanhSuaChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameSuaChiTietSanPhamLayout = new javax.swing.GroupLayout(frameSuaChiTietSanPham.getContentPane());
        frameSuaChiTietSanPham.getContentPane().setLayout(frameSuaChiTietSanPhamLayout);
        frameSuaChiTietSanPhamLayout.setHorizontalGroup(
            frameSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaChiTietSanPhamLayout.setVerticalGroup(
            frameSuaChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitleQLMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLMenu.setText("QUẢN LÝ MENU SẢN PHẨM");
        labelTitleQLMenu.setOpaque(true);

        panelChucNangMenu.setBackground(new java.awt.Color(255, 255, 255));

        panelBtns.setBackground(new java.awt.Color(255, 255, 255));

        btnTrangChuMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnTrangChuMenu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTrangChuMenu.setForeground(new java.awt.Color(215, 97, 21));
        btnTrangChuMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_trangChu_30px.png"))); // NOI18N
        btnTrangChuMenu.setText("Trang chủ");
        btnTrangChuMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangChuMenuMouseClicked(evt);
            }
        });

        btnThemChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnThemChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemChiTietSanPham.setForeground(new java.awt.Color(0, 153, 51));
        btnThemChiTietSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemChiTietSanPham.setText("Thêm");
        btnThemChiTietSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemChiTietSanPham.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemChiTietSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBtnsLayout = new javax.swing.GroupLayout(panelBtns);
        panelBtns.setLayout(panelBtnsLayout);
        panelBtnsLayout.setHorizontalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangChuMenu)
                .addGap(18, 18, 18)
                .addComponent(btnThemChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnsLayout.setVerticalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrangChuMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelChucNangMenuLayout = new javax.swing.GroupLayout(panelChucNangMenu);
        panelChucNangMenu.setLayout(panelChucNangMenuLayout);
        panelChucNangMenuLayout.setHorizontalGroup(
            panelChucNangMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChucNangMenuLayout.setVerticalGroup(
            panelChucNangMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelParentMenu.setLayout(new java.awt.CardLayout());

        scrollPaneMenu.setBackground(new java.awt.Color(255, 255, 255));

        tableDSChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneMenu.setViewportView(tableDSChiTietSP);

        panelTimKiemTrangChuMenu.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKiemTheoLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTheoLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTheoLoaiSanPham.setText("Loại sản phẩm");
        labelTimKiemTheoLoaiSanPham.setOpaque(true);

        comboBoxTimKiemTheoLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTimKiemTheoLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimKiemTheoLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboBoxTimKiemTheoLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxTimKiemTheoLoaiSanPhamMouseClicked(evt);
            }
        });
        comboBoxTimKiemTheoLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimKiemTheoLoaiSanPhamActionPerformed(evt);
            }
        });

        labelLoiTimKiemTheoLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiTimKiemTheoLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiTimKiemTheoLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiTimKiemTheoLoaiSanPham.setOpaque(true);

        labelTimKiemTheoTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTheoTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTheoTenSanPham.setText("Tên sản phẩm");
        labelTimKiemTheoTenSanPham.setOpaque(true);

        textFieldTimTheoTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimTheoTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelLoiTimTheoTenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiTimTheoTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiTimTheoTenSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiTimTheoTenSanPham.setOpaque(true);

        btnTim.setBackground(new java.awt.Color(0, 51, 255));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm");
        btnTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimMouseClicked(evt);
            }
        });

        btnHuyTim.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTim.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTim.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTim.setText("Hủy tìm");
        btnHuyTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimMouseClicked(evt);
            }
        });

        btnXoaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaChiTietSanPham.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaChiTietSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaChiTietSanPham.setText("XÓA");
        btnXoaChiTietSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaChiTietSanPhamMouseClicked(evt);
            }
        });

        btnSuaChiTietSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaChiTietSanPham.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaChiTietSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaChiTietSanPham.setText("SỬA");
        btnSuaChiTietSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaChiTietSanPham.setPreferredSize(new java.awt.Dimension(80, 42));
        btnSuaChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaChiTietSanPhamMouseClicked(evt);
            }
        });
        btnSuaChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChiTietSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemTrangChuMenuLayout = new javax.swing.GroupLayout(panelTimKiemTrangChuMenu);
        panelTimKiemTrangChuMenu.setLayout(panelTimKiemTrangChuMenuLayout);
        panelTimKiemTrangChuMenuLayout.setHorizontalGroup(
            panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemTrangChuMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelTimKiemTheoLoaiSanPham)
                        .addComponent(comboBoxTimKiemTheoLoaiSanPham, 0, 351, Short.MAX_VALUE)
                        .addComponent(labelLoiTimKiemTheoLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTimKiemTheoTenSanPham)
                        .addComponent(textFieldTimTheoTenSanPham)
                        .addComponent(labelLoiTimTheoTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTimKiemTrangChuMenuLayout.createSequentialGroup()
                        .addComponent(btnTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTim))
                    .addGroup(panelTimKiemTrangChuMenuLayout.createSequentialGroup()
                        .addComponent(btnXoaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelTimKiemTrangChuMenuLayout.setVerticalGroup(
            panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemTrangChuMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimKiemTheoLoaiSanPham)
                .addGap(18, 18, 18)
                .addComponent(comboBoxTimKiemTheoLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiTimKiemTheoLoaiSanPham)
                .addGap(18, 18, 18)
                .addComponent(labelTimKiemTheoTenSanPham)
                .addGap(18, 18, 18)
                .addComponent(textFieldTimTheoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiTimTheoTenSanPham)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTim)
                    .addComponent(btnHuyTim))
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaChiTietSanPham)
                    .addComponent(btnSuaChiTietSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(258, 258, 258))
        );

        javax.swing.GroupLayout panelTrangChuMenuLayout = new javax.swing.GroupLayout(panelTrangChuMenu);
        panelTrangChuMenu.setLayout(panelTrangChuMenuLayout);
        panelTrangChuMenuLayout.setHorizontalGroup(
            panelTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTimKiemTrangChuMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTrangChuMenuLayout.setVerticalGroup(
            panelTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTrangChuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrangChuMenuLayout.createSequentialGroup()
                        .addComponent(panelTimKiemTrangChuMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(scrollPaneMenu))
                .addContainerGap())
        );

        panelParentMenu.add(panelTrangChuMenu, "card2");

        panelHienThiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKiemTheoTenSPThemCTSP.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTheoTenSPThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTheoTenSPThemCTSP.setText("Tên sản phẩm");
        labelTimKiemTheoTenSPThemCTSP.setOpaque(true);

        textFieldTimTheoTenSPThemCTSP.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimTheoTenSPThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelLoiTimTheoTenSPThemCTSP.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiTimTheoTenSPThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiTimTheoTenSPThemCTSP.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiTimTheoTenSPThemCTSP.setOpaque(true);

        btnChonSanPham.setBackground(new java.awt.Color(16, 185, 129));
        btnChonSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnChonSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnChonSanPham.setText("Chọn");
        btnChonSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonSanPhamMouseClicked(evt);
            }
        });

        btnTimTheoTenSPThemCTSP.setBackground(new java.awt.Color(51, 102, 255));
        btnTimTheoTenSPThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimTheoTenSPThemCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnTimTheoTenSPThemCTSP.setText("Tìm");
        btnTimTheoTenSPThemCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimTheoTenSPThemCTSPMouseClicked(evt);
            }
        });

        btnHuyTimTheoTenSPThemCTSP.setBackground(new java.awt.Color(255, 0, 51));
        btnHuyTimTheoTenSPThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimTheoTenSPThemCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimTheoTenSPThemCTSP.setText("Hủy tìm");
        btnHuyTimTheoTenSPThemCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimTheoTenSPThemCTSPMouseClicked(evt);
            }
        });

        tableDSSanPhamThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSSanPhamThemCTSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSSanPhamThemCTSP.setSelectionBackground(new java.awt.Color(226, 232, 240));
        scrollPaneDSSanPham.setViewportView(tableDSSanPhamThemCTSP);

        javax.swing.GroupLayout panelHienThiSanPhamLayout = new javax.swing.GroupLayout(panelHienThiSanPham);
        panelHienThiSanPham.setLayout(panelHienThiSanPhamLayout);
        panelHienThiSanPhamLayout.setHorizontalGroup(
            panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneDSSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                        .addGroup(panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLoiTimTheoTenSPThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                                    .addComponent(labelTimKiemTheoTenSPThemCTSP)
                                    .addGap(18, 18, 18)
                                    .addComponent(textFieldTimTheoTenSPThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                                    .addComponent(btnTimTheoTenSPThemCTSP)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnHuyTimTheoTenSPThemCTSP)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnChonSanPham))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelHienThiSanPhamLayout.setVerticalGroup(
            panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                        .addComponent(labelTimKiemTheoTenSPThemCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))
                    .addGroup(panelHienThiSanPhamLayout.createSequentialGroup()
                        .addComponent(textFieldTimTheoTenSPThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiTimTheoTenSPThemCTSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelHienThiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChonSanPham)
                    .addComponent(btnTimTheoTenSPThemCTSP)
                    .addComponent(btnHuyTimTheoTenSPThemCTSP))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneDSSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelNoiDungThemCTSP.setBackground(new java.awt.Color(255, 255, 255));

        labelXemMaSP.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaSP.setText("Mã sản phẩm");
        labelXemMaSP.setOpaque(true);

        labelHienThiMaSP.setBackground(new java.awt.Color(241, 245, 249));
        labelHienThiMaSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiMaSP.setText("0");
        labelHienThiMaSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiMaSP.setOpaque(true);

        labelXemTenSP.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenSP.setText("Tên sản phẩm");
        labelXemTenSP.setOpaque(true);

        labelHienThiTenSP.setBackground(new java.awt.Color(241, 245, 249));
        labelHienThiTenSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiTenSP.setText("Sản phẩm 1");
        labelHienThiTenSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiTenSP.setOpaque(true);

        labelXemTheLoaiSP.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTheLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTheLoaiSP.setText("Thể loại");
        labelXemTheLoaiSP.setOpaque(true);

        labelHienThiTheLoaiSP.setBackground(new java.awt.Color(241, 245, 249));
        labelHienThiTheLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelHienThiTheLoaiSP.setText("Loại sản phẩm 1");
        labelHienThiTheLoaiSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelHienThiTheLoaiSP.setOpaque(true);

        labelChonSize.setBackground(new java.awt.Color(255, 255, 255));
        labelChonSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelChonSize.setText("Chọn size");
        labelChonSize.setOpaque(true);

        comboBoxChonSizeChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxChonSizeChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelGiaBanChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        labelGiaBanChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelGiaBanChiTietSP.setText("Giá bán  (VNĐ)");
        labelGiaBanChiTietSP.setOpaque(true);

        textFieldThemGiaBanChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemGiaBanChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelLoiThemSizeChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemSizeChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemSizeChiTietSP.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemSizeChiTietSP.setOpaque(true);

        labelLoiThemGiaChiTietSP.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemGiaChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemGiaChiTietSP.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemGiaChiTietSP.setOpaque(true);

        btnHoanThanhThemChiTietSP.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemChiTietSP.setText("THÊM");
        btnHoanThanhThemChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemChiTietSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelNoiDungThemCTSPLayout = new javax.swing.GroupLayout(panelNoiDungThemCTSP);
        panelNoiDungThemCTSP.setLayout(panelNoiDungThemCTSPLayout);
        panelNoiDungThemCTSPLayout.setHorizontalGroup(
            panelNoiDungThemCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiDungThemCTSPLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelNoiDungThemCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNoiDungThemCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelGiaBanChiTietSP)
                        .addComponent(labelChonSize)
                        .addComponent(labelXemTheLoaiSP)
                        .addComponent(labelXemTenSP)
                        .addComponent(labelXemMaSP)
                        .addComponent(labelHienThiMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelHienThiTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                        .addComponent(labelHienThiTheLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxChonSizeChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldThemGiaBanChiTietSP)
                        .addComponent(labelLoiThemSizeChiTietSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelLoiThemGiaChiTietSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnHoanThanhThemChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelNoiDungThemCTSPLayout.setVerticalGroup(
            panelNoiDungThemCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiDungThemCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelXemMaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHienThiMaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelXemTenSP)
                .addGap(18, 18, 18)
                .addComponent(labelHienThiTenSP)
                .addGap(18, 18, 18)
                .addComponent(labelXemTheLoaiSP)
                .addGap(18, 18, 18)
                .addComponent(labelHienThiTheLoaiSP)
                .addGap(18, 18, 18)
                .addComponent(labelChonSize)
                .addGap(18, 18, 18)
                .addComponent(comboBoxChonSizeChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemSizeChiTietSP)
                .addGap(18, 18, 18)
                .addComponent(labelGiaBanChiTietSP)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemGiaBanChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemGiaChiTietSP)
                .addGap(18, 18, 18)
                .addComponent(btnHoanThanhThemChiTietSP)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelThemChiTietSPLayout = new javax.swing.GroupLayout(panelThemChiTietSP);
        panelThemChiTietSP.setLayout(panelThemChiTietSPLayout);
        panelThemChiTietSPLayout.setHorizontalGroup(
            panelThemChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemChiTietSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelHienThiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelNoiDungThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThemChiTietSPLayout.setVerticalGroup(
            panelThemChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemChiTietSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemChiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHienThiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNoiDungThemCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelParentMenu.add(panelThemChiTietSP, "card3");

        javax.swing.GroupLayout panelQLMenuLayout = new javax.swing.GroupLayout(panelQLMenu);
        panelQLMenu.setLayout(panelQLMenuLayout);
        panelQLMenuLayout.setHorizontalGroup(
            panelQLMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleQLMenu)
                    .addComponent(panelParentMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChucNangMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panelQLMenuLayout.setVerticalGroup(
            panelQLMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLMenu)
                .addGap(18, 18, 18)
                .addComponent(panelChucNangMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelParentMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMenuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hiện ra panel để hiện trang chủ hoa đơn nhập hàng
        this.panelParentMenu.removeAll();
        this.panelParentMenu.add(this.panelTrangChuMenu);
        this.panelParentMenu.repaint();
        this.panelParentMenu.revalidate();
        getAllProductDetail(this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);
    }//GEN-LAST:event_btnTrangChuMenuMouseClicked

    private void btnThemChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemChiTietSanPhamMouseClicked
        // TODO add your handling code here:

        this.panelParentMenu.removeAll();
        this.panelParentMenu.add(this.panelThemChiTietSP);
        this.panelParentMenu.repaint();
        this.panelParentMenu.revalidate();
//        String thoiGianTaoHoaDon = CurrentTimeFormatter.getCurrentTimeFormatted();
//        this.labelNoiDungThemNgayTaoHoaDonNhapNL.setText(thoiGianTaoHoaDon);
//        int soDong = this.billImportIngredientDAO.countRowInBillImportIngredientTable();
//        if (soDong == 0) {
//            this.labelNoiDungThemMaHoaDonNhapNL.setText("1");
//        } else {
//            int currentIdBillImport = this.billImportIngredientDAO.findMaxIdBillImportIngredient();
//            this.labelNoiDungThemMaHoaDonNhapNL.setText((currentIdBillImport + 1) + "");
//        }
    }//GEN-LAST:event_btnThemChiTietSanPhamMouseClicked

    private void btnXoaChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        // xóa chi tiết sản phẩm
        xoaChiTietSanPham(this.tableDSChiTietSP, this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);
    }//GEN-LAST:event_btnXoaChiTietSanPhamMouseClicked

    private void btnSuaChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiện lên frame sửa thông tin chi tiết sản phẩm
        int row = this.tableDSChiTietSP.getSelectedRow();
        if (row >= 0) {
            this.frameSuaChiTietSanPham.setSize(650, 600);
            this.frameSuaChiTietSanPham.setLocationRelativeTo(null);
            this.frameSuaChiTietSanPham.setVisible(true);
            this.frameSuaChiTietSanPham.getContentPane().setBackground(Color.WHITE);
            hienThiChiTietSanPhamRaFrameSuaSanPham(this.tableDSChiTietSP, this.defaultTableModelDSChiTietSP);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_btnSuaChiTietSanPhamMouseClicked

    private void btnSuaChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChiTietSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaChiTietSanPhamActionPerformed

    private void btnChonSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonSanPhamMouseClicked
        // TODO add your handling code here:
        hienThiSanPhamRaPanelThemChiTietSP(this.tableDSSanPhamThemCTSP, this.defaultTableModelDSSP);
    }//GEN-LAST:event_btnChonSanPhamMouseClicked

    private void btnHoanThanhThemChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemChiTietSPMouseClicked
        // TODO add your handling code here:
        themChiTietSanPham(this.tableDSSanPhamThemCTSP, this.defaultTableModelDSSP);
    }//GEN-LAST:event_btnHoanThanhThemChiTietSPMouseClicked

    private void btnHoanThanhSuaChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaChiTietSPMouseClicked
        // TODO add your handling code here:
        suaChiTietSanPham(this.tableDSChiTietSP, this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);

    }//GEN-LAST:event_btnHoanThanhSuaChiTietSPMouseClicked

    private void btnTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimMouseClicked
        // TODO add your handling code here:
        timChiTietSanPhamTheoTenSanPham(this.tableDSChiTietSP, this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);
    }//GEN-LAST:event_btnTimMouseClicked

    private void btnHuyTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimMouseClicked
        // TODO add your handling code here:
        getAllProductDetail(this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);
        this.textFieldTimTheoTenSanPham.setText("");
    }//GEN-LAST:event_btnHuyTimMouseClicked

    private void comboBoxTimKiemTheoLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxTimKiemTheoLoaiSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTimKiemTheoLoaiSanPhamMouseClicked

    private void comboBoxTimKiemTheoLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimKiemTheoLoaiSanPhamActionPerformed
        // TODO add your handling code here:
        timKiemChiTietSanPhamTheoTenLoaiSanPham(this.tableDSChiTietSP, this.defaultTableModelDSChiTietSP, this.dsChiTietSanPham);

    }//GEN-LAST:event_comboBoxTimKiemTheoLoaiSanPhamActionPerformed

    private void btnTimTheoTenSPThemCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimTheoTenSPThemCTSPMouseClicked
        // TODO add your handling code here:
        timSanPhamTheoTen(this.defaultTableModelDSSP, this.dsSanPham);
    }//GEN-LAST:event_btnTimTheoTenSPThemCTSPMouseClicked

    private void btnHuyTimTheoTenSPThemCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimTheoTenSPThemCTSPMouseClicked
        // TODO add your handling code here:
        getAllProduct(this.defaultTableModelDSSP, this.dsSanPham);
        this.textFieldTimTheoTenSPThemCTSP.setText("");
    }//GEN-LAST:event_btnHuyTimTheoTenSPThemCTSPMouseClicked

    private void textFieldSuaGiaChiTietSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaGiaChiTietSanPhamKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaGiaCHiTietSP.getText().equals("")) {
            this.labelLoiSuaGiaCHiTietSP.setText("");
        }
    }//GEN-LAST:event_textFieldSuaGiaChiTietSanPhamKeyPressed

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
            java.util.logging.Logger.getLogger(QLMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonSanPham;
    private javax.swing.JButton btnHoanThanhSuaChiTietSP;
    private javax.swing.JButton btnHoanThanhThemChiTietSP;
    private javax.swing.JButton btnHuyTim;
    private javax.swing.JButton btnHuyTimTheoTenSPThemCTSP;
    private javax.swing.JButton btnSuaChiTietSanPham;
    private javax.swing.JButton btnThemChiTietSanPham;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimTheoTenSPThemCTSP;
    private javax.swing.JButton btnTrangChuMenu;
    private javax.swing.JButton btnXoaChiTietSanPham;
    private javax.swing.JComboBox<String> comboBoxChonSizeChiTietSP;
    private javax.swing.JComboBox<String> comboBoxSuaSizeChiTietSP;
    private javax.swing.JComboBox<String> comboBoxTimKiemTheoLoaiSanPham;
    private javax.swing.JFrame frameSuaChiTietSanPham;
    private javax.swing.JLabel labelChonSize;
    private javax.swing.JLabel labelGiaBanChiTietSP;
    private javax.swing.JLabel labelHienThiMaSP;
    private javax.swing.JLabel labelHienThiSuaMaChiTietSP;
    private javax.swing.JLabel labelHienThiSuaMaSanPham;
    private javax.swing.JLabel labelHienThiSuaTenSanPham;
    private javax.swing.JLabel labelHienThiTenSP;
    private javax.swing.JLabel labelHienThiTheLoaiSP;
    private javax.swing.JLabel labelLoiSuaGiaCHiTietSP;
    private javax.swing.JLabel labelLoiSuaGiaChiTietSanPham;
    private javax.swing.JLabel labelLoiThemGiaChiTietSP;
    private javax.swing.JLabel labelLoiThemSizeChiTietSP;
    private javax.swing.JLabel labelLoiTimKiemTheoLoaiSanPham;
    private javax.swing.JLabel labelLoiTimTheoTenSPThemCTSP;
    private javax.swing.JLabel labelLoiTimTheoTenSanPham;
    private javax.swing.JLabel labelSuaMaChiTietSanPham;
    private javax.swing.JLabel labelSuaMaSanPham;
    private javax.swing.JLabel labelSuaSizeChiTietSP;
    private javax.swing.JLabel labelSuaTenSanPham;
    private javax.swing.JLabel labelTimKiemTheoLoaiSanPham;
    private javax.swing.JLabel labelTimKiemTheoTenSPThemCTSP;
    private javax.swing.JLabel labelTimKiemTheoTenSanPham;
    private javax.swing.JLabel labelTitleQLMenu;
    private javax.swing.JLabel labelTitleSuaChiTietSanPham;
    private javax.swing.JLabel labelXemMaSP;
    private javax.swing.JLabel labelXemTenSP;
    private javax.swing.JLabel labelXemTheLoaiSP;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JPanel panelChucNangMenu;
    private javax.swing.JPanel panelHienThiSanPham;
    private javax.swing.JPanel panelNoiDungThemCTSP;
    private javax.swing.JPanel panelParentMenu;
    private javax.swing.JPanel panelQLMenu;
    private javax.swing.JPanel panelSuaChiTietSanPham;
    private javax.swing.JPanel panelThemChiTietSP;
    private javax.swing.JPanel panelTimKiemTrangChuMenu;
    private javax.swing.JPanel panelTrangChuMenu;
    private javax.swing.JScrollPane scrollPaneDSSanPham;
    private javax.swing.JScrollPane scrollPaneMenu;
    private javax.swing.JTable tableDSChiTietSP;
    private javax.swing.JTable tableDSSanPhamThemCTSP;
    private javax.swing.JTextField textFieldSuaGiaChiTietSanPham;
    private javax.swing.JTextField textFieldThemGiaBanChiTietSP;
    private javax.swing.JTextField textFieldTimTheoTenSPThemCTSP;
    private javax.swing.JTextField textFieldTimTheoTenSanPham;
    // End of variables declaration//GEN-END:variables
}
