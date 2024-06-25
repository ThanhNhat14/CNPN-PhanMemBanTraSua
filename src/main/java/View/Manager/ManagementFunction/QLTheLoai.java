/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.CategoryDAO;
import Dao.SizeDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Category;
import Model.Manager.Size;

/**
 *
 * @author Admin
 */
public class QLTheLoai extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLTheLoai
     */
    public DefaultTableModel defaultTableModelDSLoaiSanPham;
    public DefaultTableModel defaultTableModelDSSize;
    public ArrayList<Category> dsLoaiSanPham;
    public ArrayList<Size> dsSize;

    public CategoryDAO categoryDAO;
    public SizeDAO sizeDAO;

    public QLTheLoai() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.categoryDAO = new CategoryDAO();
        this.sizeDAO = new SizeDAO();

        // Category
        this.defaultTableModelDSLoaiSanPham = new DefaultTableModel();
        this.tableDSLoaiSanPham.setModel(this.defaultTableModelDSLoaiSanPham);
        String[] columnsDSLoaiSanPham = {"Mã loại sản phẩm", "Tên loại sản phẩm"};
        this.defaultTableModelDSLoaiSanPham.setColumnIdentifiers(columnsDSLoaiSanPham);
        JTableHeader headerDSLoaiSanPham = this.tableDSLoaiSanPham.getTableHeader();
        headerDSLoaiSanPham.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSLoaiSanPham.setBackground(new Color(0x43CD80));
        headerDSLoaiSanPham.setForeground(Color.WHITE);
        headerDSLoaiSanPham.setPreferredSize(new Dimension(headerDSLoaiSanPham.getWidth(), 30));
        DefaultTableCellRenderer rendererDSLoaiSanPham = (DefaultTableCellRenderer) this.tableDSLoaiSanPham.getTableHeader().getDefaultRenderer();
        rendererDSLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSLoaiSanPham.setRowHeight(30);

        // Size
        this.defaultTableModelDSSize = new DefaultTableModel();
        this.tableDSSize.setModel(this.defaultTableModelDSSize);
        String[] columnsDSSize = {"Mã size", "Tên size"};
        this.defaultTableModelDSSize.setColumnIdentifiers(columnsDSSize);
        JTableHeader headerDSSize = this.tableDSSize.getTableHeader();
        headerDSSize.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSSize.setBackground(new Color(0x43CD80));
        headerDSSize.setForeground(Color.WHITE);
        headerDSSize.setPreferredSize(new Dimension(headerDSSize.getWidth(), 30));
        DefaultTableCellRenderer rendererDSSize = (DefaultTableCellRenderer) this.tableDSSize.getTableHeader().getDefaultRenderer();
        rendererDSSize.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSSize.setRowHeight(30);

        this.scrollpanelDSLoaiSanPham.getViewport().setBackground(Color.WHITE);
        this.scrollPanelDSSize.getViewport().setBackground(Color.WHITE);

        getAllCategory(this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
        getAllSize(this.defaultTableModelDSSize, this.dsSize);

    }

    //    // =============== CATEGORY ===============
    public void xuatDSLoaiSanPhamraTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        defaultTableModel.setRowCount(0);
        for (Category i : ds) {
            Object[] data = {i.getIdCategory(), i.getNameCategory()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllCategory(DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        ds = this.categoryDAO.getAllCategory();
        xuatDSLoaiSanPhamraTableTuArrayList(defaultTableModel, ds);
    }

    public void themLoaiSanPham(DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        // hàm thêm một loại sản phẩm mới vào database
        String thongBaoLoi = "";
        if (this.textFieldThemTenLoaiSanPham.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên loại sản phẩm";
            this.labelLoiThemTenLoaiSanPham.setText("Vui lòng nhập tên loại sản phẩm");
        }
        if (thongBaoLoi.equals("")) {
            String tenLoaiSanPham = this.textFieldThemTenLoaiSanPham.getText();
            boolean statusCategory = true;
            Category a = new Category(tenLoaiSanPham, statusCategory);
            if (this.categoryDAO.countCategoryByName(tenLoaiSanPham) == 0) {
                this.categoryDAO.insertCategory(a);
                this.frameThemLoaiSanPham.dispose();
                getAllCategory(defaultTableModel, ds);
            } else {
                JOptionPane.showMessageDialog(null, "Loại sản phẩm này đã tồn tại.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public Category layMotCategoryKhiClickVaoTableDSCategory(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idCategory = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Category a = this.categoryDAO.getCategoryById(idCategory);
            return a;
        }
        return null;
    }

    public void xoaMotLoaiSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Category loaiSanPhamCanXoa = layMotCategoryKhiClickVaoTableDSCategory(table, defaultTableModel);
            if (loaiSanPhamCanXoa != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa loại sản phẩm này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    this.categoryDAO.deleteCategory(loaiSanPhamCanXoa.getIdCategory());
                    getAllCategory(defaultTableModel, ds);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một loại sản phẩm để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienThiLoaiSanPhamVaoFrameSuaLoaiSanPham(JTable table, DefaultTableModel defaultTableModel) {
        // hàm này sẽ giúp hiện thông tin của lại sản phảm khi ta click vào table loaiSanPham
        int row = table.getSelectedRow();
        if (row >= 0) {
            Category a = layMotCategoryKhiClickVaoTableDSCategory(table, defaultTableModel);
            this.textFieldSuaTenLoaiSanPham.setText(a.getNameCategory());
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một loại sản phẩm để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void suaLoaiSanPham(JTable table, DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Category loaiSanPhamCanSua = layMotCategoryKhiClickVaoTableDSCategory(table, defaultTableModel);
            if (loaiSanPhamCanSua != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thay đổi loại sản phẩm này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    String thongBaoLoi = "";
                    if (this.textFieldSuaTenLoaiSanPham.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập tên loại sản phẩm";
                        this.labelLoiSuaTenLoaiSanPham.setText("Vui lòng nhập tên loại sản phẩm");
                    }
                    if (thongBaoLoi.equals("")) {
                        String tenLoaiSanPham = this.textFieldSuaTenLoaiSanPham.getText();
                        boolean statusCategory = true;
                        Category a = new Category(tenLoaiSanPham, statusCategory);
                        this.categoryDAO.updatecategory(loaiSanPhamCanSua.getIdCategory(), a);
                        getAllCategory(defaultTableModel, ds);
                        this.frameSuaLoaiSanPham.setVisible(false);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một loại sản phẩm để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timLoaiSanPhamTheoTen(DefaultTableModel defaultTableModel, ArrayList<Category> ds) {
        String tenLoaiSanPhamCanTim = this.textFieldTimKiemLoaiSanPham.getText();
        if (tenLoaiSanPhamCanTim.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập loại sản phẩm cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<Category> kq = this.categoryDAO.findCategorybyName(tenLoaiSanPhamCanTim);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có loại sản phẩm phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                xuatDSLoaiSanPhamraTableTuArrayList(defaultTableModel, kq);
            }
        }
    }

    // ==================== SIZE ====================
    public void xuatDSSizeRaTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Size> ds) {
        defaultTableModel.setRowCount(0);
        for (Size i : ds) {
//            Object[] data = {i.getIdSize(), i.getNameOfSize(), i.getPriceOfSize()};
            Object[] data = {i.getIdSize(), i.getNameOfSize()};

            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllSize(DefaultTableModel defaultTableModel, ArrayList<Size> ds) {
        ds = this.sizeDAO.getAllSize();
        xuatDSSizeRaTableTuArrayList(defaultTableModel, ds);
    }

    public Size laySizeKhiClickVaoTableSize(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idSize = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Size a = this.sizeDAO.getSizeById(idSize);
            return a;
        } else {
            return null;
        }
    }

    public void themSize(DefaultTableModel defaultTableModel, ArrayList<Size> ds) {
        String thongBaoLoi = "";
        if (this.textFieldThemTenSize.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên size\n";
            this.labelLoiThemTenSize.setText("Vui lòng nhập tên size");
        }
        if (thongBaoLoi.equals("")) {
            String nameOfSize = this.textFieldThemTenSize.getText();
            if (this.sizeDAO.countSizeByName(nameOfSize) == 0) {
                Size a = new Size(nameOfSize);
                this.sizeDAO.insertSize(a);
                this.frameThemSize.dispose();
                getAllSize(defaultTableModel, ds);
            } else {
                JOptionPane.showMessageDialog(null, "Size này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void xoaSize(JTable table, DefaultTableModel defaultTableModel, ArrayList<Size> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Size sizeCanXoa = laySizeKhiClickVaoTableSize(table, defaultTableModel);
            if (sizeCanXoa != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa size này không?");
                if (confirm == JOptionPane.OK_OPTION) {
                    this.sizeDAO.deleteSize(sizeCanXoa.getIdSize());
                    getAllSize(defaultTableModel, ds);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một size sản phẩm để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void suaSize(JTable table, DefaultTableModel defaultTableModel, ArrayList<Size> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Size sizeCanSua = laySizeKhiClickVaoTableSize(table, defaultTableModel);
            if (sizeCanSua != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa size này không?");
                if (confirm == JOptionPane.OK_OPTION) {
                    String thongBaoLoi = "";
                    if (this.textFieldThemTenSize.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập tên size\n";
                        this.labelLoiThemTenSize.setText("Vui lòng nhập tên size");
                    }
                    if (thongBaoLoi.equals("")) {
                        String nameOfSize = this.textFieldThemTenSize.getText();
                        Size a = new Size(nameOfSize);
                        this.sizeDAO.updateSize(sizeCanSua.getIdSize(), a);
                        this.frameSuaSize.setVisible(false);
                        getAllSize(defaultTableModel, ds);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một size sản phẩm để sửa", "ERROR", JOptionPane.ERROR_MESSAGE);

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

        frameThemSize = new javax.swing.JFrame();
        panelThemSize = new javax.swing.JPanel();
        labelTitleThemSize = new javax.swing.JLabel();
        labelThemTenSize = new javax.swing.JLabel();
        textFieldThemTenSize = new javax.swing.JTextField();
        labelLoiThemTenSize = new javax.swing.JLabel();
        btnHoanThanhThemSize = new javax.swing.JButton();
        frameSuaSize = new javax.swing.JFrame();
        panelSuaSize = new javax.swing.JPanel();
        labelTitleSuaSize = new javax.swing.JLabel();
        labelSuaTenSize = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        labelLoiSuaTenSize = new javax.swing.JLabel();
        btnHoanThanhSuaSize = new javax.swing.JButton();
        frameThemLoaiSanPham = new javax.swing.JFrame();
        labelTitleThemLoaiSanPham = new javax.swing.JLabel();
        panelNoiDungThemLoaiSanPham = new javax.swing.JPanel();
        labelThemTenLoaiSanPham = new javax.swing.JLabel();
        textFieldThemTenLoaiSanPham = new javax.swing.JTextField();
        labelLoiThemTenLoaiSanPham = new javax.swing.JLabel();
        btnHoanThanhThemLoaiSanPham = new javax.swing.JButton();
        frameSuaLoaiSanPham = new javax.swing.JFrame();
        panelSuaLoaiSanPham = new javax.swing.JPanel();
        labelTitleSuaLoaiSanPham = new javax.swing.JLabel();
        labelSuaTenLoaiSnaPham = new javax.swing.JLabel();
        textFieldSuaTenLoaiSanPham = new javax.swing.JTextField();
        labelLoiSuaTenLoaiSanPham = new javax.swing.JLabel();
        btnHoanThanhSuaLoaiSanPham = new javax.swing.JButton();
        panelQLLoaiSanPhamContent = new javax.swing.JPanel();
        labelTitleQLLoaiSanPham = new javax.swing.JLabel();
        panelTimKiemLoaiSanPham = new javax.swing.JPanel();
        labelTimKiemLoaiSanPham = new javax.swing.JLabel();
        textFieldTimKiemLoaiSanPham = new javax.swing.JTextField();
        btnTimLoaiSanPham = new javax.swing.JButton();
        btnHuyTimLoaiSanPham = new javax.swing.JButton();
        scrollpanelDSLoaiSanPham = new javax.swing.JScrollPane();
        tableDSLoaiSanPham = new javax.swing.JTable();
        panelChucNangLoaiSanPham = new javax.swing.JPanel();
        lbelChucNangLoaiSanPham = new javax.swing.JLabel();
        btnThemLoaiSanPham = new javax.swing.JButton();
        btnXoaLoaiSanPham = new javax.swing.JButton();
        btnSuaLoaiSanPham = new javax.swing.JButton();
        labelTitleQLSize = new javax.swing.JLabel();
        panelChucNangSize = new javax.swing.JPanel();
        btnThemSize = new javax.swing.JButton();
        labelChucNangSize = new javax.swing.JLabel();
        btnXoaSize = new javax.swing.JButton();
        btnSuaSize = new javax.swing.JButton();
        scrollPanelDSSize = new javax.swing.JScrollPane();
        tableDSSize = new javax.swing.JTable();

        frameThemSize.setTitle("Thêm size sản phẩm");

        panelThemSize.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleThemSize.setBackground(new java.awt.Color(67, 205, 128));
        labelTitleThemSize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemSize.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemSize.setText("THÊM SIZE SẢN PHẨM");
        labelTitleThemSize.setOpaque(true);

        labelThemTenSize.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenSize.setText("Tên size");
        labelThemTenSize.setOpaque(true);

        textFieldThemTenSize.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenSize.setOpaque(true);
        textFieldThemTenSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenSizeKeyPressed(evt);
            }
        });

        labelLoiThemTenSize.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenSize.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenSize.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemTenSize.setOpaque(true);

        btnHoanThanhThemSize.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemSize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemSize.setForeground(new java.awt.Color(67, 205, 128));
        btnHoanThanhThemSize.setText("THÊM");
        btnHoanThanhThemSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        btnHoanThanhThemSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemSizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemSizeLayout = new javax.swing.GroupLayout(panelThemSize);
        panelThemSize.setLayout(panelThemSizeLayout);
        panelThemSizeLayout.setHorizontalGroup(
            panelThemSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleThemSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldThemTenSize)
                    .addComponent(labelLoiThemTenSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelThemSizeLayout.createSequentialGroup()
                        .addComponent(labelThemTenSize)
                        .addGap(0, 437, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelThemSizeLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(btnHoanThanhThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );
        panelThemSizeLayout.setVerticalGroup(
            panelThemSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelThemTenSize)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemTenSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnHoanThanhThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout frameThemSizeLayout = new javax.swing.GroupLayout(frameThemSize.getContentPane());
        frameThemSize.getContentPane().setLayout(frameThemSizeLayout);
        frameThemSizeLayout.setHorizontalGroup(
            frameThemSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameThemSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameThemSizeLayout.setVerticalGroup(
            frameThemSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaSize.setTitle("Sửa thông tin size sản phẩm");

        panelSuaSize.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaSize.setBackground(new java.awt.Color(255, 153, 0));
        labelTitleSuaSize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaSize.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaSize.setText("THAY ĐỔI SIZE SẢN PHẨM");
        labelTitleSuaSize.setOpaque(true);

        labelSuaTenSize.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenSize.setText("Tên size");
        labelSuaTenSize.setOpaque(true);

        textField.setBackground(new java.awt.Color(255, 255, 255));
        textField.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        labelLoiSuaTenSize.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenSize.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenSize.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaTenSize.setOpaque(true);

        btnHoanThanhSuaSize.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaSize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaSize.setForeground(new java.awt.Color(255, 153, 0));
        btnHoanThanhSuaSize.setText("THAY ĐỔI");
        btnHoanThanhSuaSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        btnHoanThanhSuaSize.setOpaque(true);

        javax.swing.GroupLayout panelSuaSizeLayout = new javax.swing.GroupLayout(panelSuaSize);
        panelSuaSize.setLayout(panelSuaSizeLayout);
        panelSuaSizeLayout.setHorizontalGroup(
            panelSuaSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuaSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleSuaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textField)
                    .addComponent(labelLoiSuaTenSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSuaSizeLayout.createSequentialGroup()
                        .addComponent(labelSuaTenSize)
                        .addGap(0, 441, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelSuaSizeLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(btnHoanThanhSuaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        panelSuaSizeLayout.setVerticalGroup(
            panelSuaSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSuaTenSize)
                .addGap(18, 18, 18)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaTenSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(btnHoanThanhSuaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout frameSuaSizeLayout = new javax.swing.GroupLayout(frameSuaSize.getContentPane());
        frameSuaSize.getContentPane().setLayout(frameSuaSizeLayout);
        frameSuaSizeLayout.setHorizontalGroup(
            frameSuaSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaSizeLayout.setVerticalGroup(
            frameSuaSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameThemLoaiSanPham.setTitle("Thêm loại sản phẩm");

        labelTitleThemLoaiSanPham.setBackground(new java.awt.Color(67, 205, 128));
        labelTitleThemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemLoaiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemLoaiSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemLoaiSanPham.setText("THÊM LOẠI SẢN PHẨM");
        labelTitleThemLoaiSanPham.setOpaque(true);

        panelNoiDungThemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelThemTenLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenLoaiSanPham.setText("Tên loại sản phẩm");
        labelThemTenLoaiSanPham.setOpaque(true);

        textFieldThemTenLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenLoaiSanPham.setOpaque(true);
        textFieldThemTenLoaiSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenLoaiSanPhamKeyPressed(evt);
            }
        });

        labelLoiThemTenLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemTenLoaiSanPham.setOpaque(true);

        btnHoanThanhThemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHoanThanhThemLoaiSanPham.setForeground(new java.awt.Color(67, 205, 128));
        btnHoanThanhThemLoaiSanPham.setText("THÊM");
        btnHoanThanhThemLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        btnHoanThanhThemLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemLoaiSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelNoiDungThemLoaiSanPhamLayout = new javax.swing.GroupLayout(panelNoiDungThemLoaiSanPham);
        panelNoiDungThemLoaiSanPham.setLayout(panelNoiDungThemLoaiSanPhamLayout);
        panelNoiDungThemLoaiSanPhamLayout.setHorizontalGroup(
            panelNoiDungThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiDungThemLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNoiDungThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldThemTenLoaiSanPham)
                    .addGroup(panelNoiDungThemLoaiSanPhamLayout.createSequentialGroup()
                        .addComponent(labelThemTenLoaiSanPham)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelLoiThemTenLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelNoiDungThemLoaiSanPhamLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(btnHoanThanhThemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        panelNoiDungThemLoaiSanPhamLayout.setVerticalGroup(
            panelNoiDungThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiDungThemLoaiSanPhamLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelThemTenLoaiSanPham)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemTenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemTenLoaiSanPham)
                .addGap(58, 58, 58)
                .addComponent(btnHoanThanhThemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameThemLoaiSanPhamLayout = new javax.swing.GroupLayout(frameThemLoaiSanPham.getContentPane());
        frameThemLoaiSanPham.getContentPane().setLayout(frameThemLoaiSanPhamLayout);
        frameThemLoaiSanPhamLayout.setHorizontalGroup(
            frameThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNoiDungThemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTitleThemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        frameThemLoaiSanPhamLayout.setVerticalGroup(
            frameThemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNoiDungThemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaLoaiSanPham.setTitle("Thay đổi thông tin loại sản phẩm");

        panelSuaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaLoaiSanPham.setBackground(new java.awt.Color(255, 204, 0));
        labelTitleSuaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaLoaiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaLoaiSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaLoaiSanPham.setText("THAY ĐỔI LOẠI SẢN PHẨM");
        labelTitleSuaLoaiSanPham.setOpaque(true);

        labelSuaTenLoaiSnaPham.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenLoaiSnaPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenLoaiSnaPham.setText("Tên loại sản phẩm");
        labelSuaTenLoaiSnaPham.setOpaque(true);

        textFieldSuaTenLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenLoaiSanPham.setOpaque(true);
        textFieldSuaTenLoaiSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenLoaiSanPhamKeyPressed(evt);
            }
        });

        labelLoiSuaTenLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaTenLoaiSanPham.setOpaque(true);

        btnHoanThanhSuaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHoanThanhSuaLoaiSanPham.setForeground(new java.awt.Color(255, 204, 0));
        btnHoanThanhSuaLoaiSanPham.setText("THAY ĐỔI");
        btnHoanThanhSuaLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        btnHoanThanhSuaLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaLoaiSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaLoaiSanPhamLayout = new javax.swing.GroupLayout(panelSuaLoaiSanPham);
        panelSuaLoaiSanPham.setLayout(panelSuaLoaiSanPhamLayout);
        panelSuaLoaiSanPhamLayout.setHorizontalGroup(
            panelSuaLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuaLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleSuaLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldSuaTenLoaiSanPham)
                    .addGroup(panelSuaLoaiSanPhamLayout.createSequentialGroup()
                        .addComponent(labelSuaTenLoaiSnaPham)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelLoiSuaTenLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelSuaLoaiSanPhamLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(btnHoanThanhSuaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );
        panelSuaLoaiSanPhamLayout.setVerticalGroup(
            panelSuaLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(labelSuaTenLoaiSnaPham)
                .addGap(18, 18, 18)
                .addComponent(textFieldSuaTenLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaTenLoaiSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(btnHoanThanhSuaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout frameSuaLoaiSanPhamLayout = new javax.swing.GroupLayout(frameSuaLoaiSanPham.getContentPane());
        frameSuaLoaiSanPham.getContentPane().setLayout(frameSuaLoaiSanPhamLayout);
        frameSuaLoaiSanPhamLayout.setHorizontalGroup(
            frameSuaLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaLoaiSanPhamLayout.setVerticalGroup(
            frameSuaLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLLoaiSanPhamContent.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLLoaiSanPham.setText("QUẢN LÝ LOẠI SẢN PHẨM");

        panelTimKiemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKiemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemLoaiSanPham.setText("Tìm kiếm");
        labelTimKiemLoaiSanPham.setOpaque(true);

        textFieldTimKiemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimKiemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimKiemLoaiSanPham.setOpaque(true);

        btnTimLoaiSanPham.setBackground(new java.awt.Color(0, 51, 255));
        btnTimLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimLoaiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTimLoaiSanPham.setText("Tìm");
        btnTimLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimLoaiSanPham.setOpaque(true);
        btnTimLoaiSanPham.setPreferredSize(new java.awt.Dimension(32, 24));
        btnTimLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimLoaiSanPhamMouseClicked(evt);
            }
        });

        btnHuyTimLoaiSanPham.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimLoaiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimLoaiSanPham.setText("Hủy tìm");
        btnHuyTimLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimLoaiSanPham.setOpaque(true);
        btnHuyTimLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimLoaiSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemLoaiSanPhamLayout = new javax.swing.GroupLayout(panelTimKiemLoaiSanPham);
        panelTimKiemLoaiSanPham.setLayout(panelTimKiemLoaiSanPhamLayout);
        panelTimKiemLoaiSanPhamLayout.setHorizontalGroup(
            panelTimKiemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTimKiemLoaiSanPhamLayout.createSequentialGroup()
                        .addComponent(textFieldTimKiemLoaiSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTimKiemLoaiSanPhamLayout.createSequentialGroup()
                        .addComponent(labelTimKiemLoaiSanPham)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTimKiemLoaiSanPhamLayout.setVerticalGroup(
            panelTimKiemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemLoaiSanPhamLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelTimKiemLoaiSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTimKiemLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyTimLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldTimKiemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
        );

        scrollpanelDSLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        tableDSLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tableDSLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSLoaiSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollpanelDSLoaiSanPham.setViewportView(tableDSLoaiSanPham);

        panelChucNangLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        lbelChucNangLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbelChucNangLoaiSanPham.setText("Chức năng");

        btnThemLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnThemLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemLoaiSanPham.setForeground(new java.awt.Color(0, 153, 51));
        btnThemLoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemLoaiSanPham.setText("THÊM");
        btnThemLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        btnThemLoaiSanPham.setOpaque(true);
        btnThemLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemLoaiSanPhamMouseClicked(evt);
            }
        });

        btnXoaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaLoaiSanPham.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaLoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaLoaiSanPham.setText("XÓA");
        btnXoaLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnXoaLoaiSanPham.setOpaque(true);
        btnXoaLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaLoaiSanPhamMouseClicked(evt);
            }
        });

        btnSuaLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaLoaiSanPham.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaLoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaLoaiSanPham.setText("SỬA");
        btnSuaLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        btnSuaLoaiSanPham.setOpaque(true);
        btnSuaLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaLoaiSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangLoaiSanPhamLayout = new javax.swing.GroupLayout(panelChucNangLoaiSanPham);
        panelChucNangLoaiSanPham.setLayout(panelChucNangLoaiSanPhamLayout);
        panelChucNangLoaiSanPhamLayout.setHorizontalGroup(
            panelChucNangLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbelChucNangLoaiSanPham)
                    .addGroup(panelChucNangLoaiSanPhamLayout.createSequentialGroup()
                        .addComponent(btnThemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        panelChucNangLoaiSanPhamLayout.setVerticalGroup(
            panelChucNangLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbelChucNangLoaiSanPham)
                .addGap(18, 18, 18)
                .addGroup(panelChucNangLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemLoaiSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        labelTitleQLSize.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleQLSize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLSize.setText("QUẢN LÝ SIZE SẢN PHẨM");
        labelTitleQLSize.setOpaque(true);

        panelChucNangSize.setBackground(new java.awt.Color(255, 255, 255));

        btnThemSize.setBackground(new java.awt.Color(255, 255, 255));
        btnThemSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemSize.setForeground(new java.awt.Color(0, 153, 51));
        btnThemSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemSize.setText("THÊM");
        btnThemSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        btnThemSize.setOpaque(true);
        btnThemSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSizeMouseClicked(evt);
            }
        });

        labelChucNangSize.setBackground(new java.awt.Color(255, 255, 255));
        labelChucNangSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelChucNangSize.setText("Chức năng");
        labelChucNangSize.setOpaque(true);

        btnXoaSize.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaSize.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaSize.setText("XÓA");
        btnXoaSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnXoaSize.setOpaque(true);
        btnXoaSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSizeMouseClicked(evt);
            }
        });

        btnSuaSize.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaSize.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaSize.setText("SỬA");
        btnSuaSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        btnSuaSize.setOpaque(true);
        btnSuaSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSizeMouseClicked(evt);
            }
        });
        btnSuaSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangSizeLayout = new javax.swing.GroupLayout(panelChucNangSize);
        panelChucNangSize.setLayout(panelChucNangSizeLayout);
        panelChucNangSizeLayout.setHorizontalGroup(
            panelChucNangSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelChucNangSize)
                    .addGroup(panelChucNangSizeLayout.createSequentialGroup()
                        .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChucNangSizeLayout.setVerticalGroup(
            panelChucNangSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChucNangSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelChucNangSize)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelChucNangSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSize)
                    .addComponent(btnXoaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSize))
                .addGap(11, 11, 11))
        );

        scrollPanelDSSize.setBackground(new java.awt.Color(255, 255, 255));

        tableDSSize.setBackground(new java.awt.Color(255, 255, 255));
        tableDSSize.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSSize.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSSize.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPanelDSSize.setViewportView(tableDSSize);

        javax.swing.GroupLayout panelQLLoaiSanPhamContentLayout = new javax.swing.GroupLayout(panelQLLoaiSanPhamContent);
        panelQLLoaiSanPhamContent.setLayout(panelQLLoaiSanPhamContentLayout);
        panelQLLoaiSanPhamContentLayout.setHorizontalGroup(
            panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLLoaiSanPhamContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTitleQLLoaiSanPham)
                    .addComponent(panelChucNangLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTimKiemLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollpanelDSLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                .addGap(80, 80, 80)
                .addGroup(panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTitleQLSize)
                    .addComponent(panelChucNangSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPanelDSSize))
                .addContainerGap(457, Short.MAX_VALUE))
        );
        panelQLLoaiSanPhamContentLayout.setVerticalGroup(
            panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLLoaiSanPhamContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTitleQLLoaiSanPham)
                    .addComponent(labelTitleQLSize))
                .addGap(18, 18, 18)
                .addGroup(panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTimKiemLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChucNangSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelQLLoaiSanPhamContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQLLoaiSanPhamContentLayout.createSequentialGroup()
                        .addComponent(panelChucNangLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollpanelDSLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                    .addComponent(scrollPanelDSSize))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1575, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(14, Short.MAX_VALUE)
                    .addComponent(panelQLLoaiSanPhamContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(26, Short.MAX_VALUE)
                    .addComponent(panelQLLoaiSanPhamContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        timLoaiSanPhamTheoTen(this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
    }//GEN-LAST:event_btnTimLoaiSanPhamMouseClicked

    private void btnHuyTimLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        getAllCategory(this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
        this.textFieldTimKiemLoaiSanPham.setText("");
    }//GEN-LAST:event_btnHuyTimLoaiSanPhamMouseClicked

    private void btnThemLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiện lên frame thêm loại sản phẩm
        this.frameThemLoaiSanPham.setSize(550, 350);
        this.frameThemLoaiSanPham.setLocationRelativeTo(null);
        this.frameThemLoaiSanPham.setVisible(true);
        this.frameThemLoaiSanPham.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemLoaiSanPhamMouseClicked

    private void btnXoaLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì nó sẽ xóa bỏ loại sản phẩm
        xoaMotLoaiSanPham(this.tableDSLoaiSanPham, this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
    }//GEN-LAST:event_btnXoaLoaiSanPhamMouseClicked

    private void btnSuaLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiện lên frame chỉnh sửa loại sản phẩm
        int row = this.tableDSLoaiSanPham.getSelectedRow();
        if (row >= 0) {
            this.frameSuaLoaiSanPham.setSize(550, 350);
            this.frameSuaLoaiSanPham.setLocationRelativeTo(null);
            this.frameSuaLoaiSanPham.setVisible(true);
            this.frameSuaLoaiSanPham.getContentPane().setBackground(Color.WHITE);
            hienThiLoaiSanPhamVaoFrameSuaLoaiSanPham(this.tableDSLoaiSanPham, this.defaultTableModelDSLoaiSanPham);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại sản phẩm cần sửa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaLoaiSanPhamMouseClicked

    private void btnThemSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSizeMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì sẽ hiện lên frame thêm size cho sản phẩm
        this.frameThemSize.setSize(500, 400);
        this.frameThemSize.setLocationRelativeTo(null);
        this.frameThemSize.setVisible(true);
    }//GEN-LAST:event_btnThemSizeMouseClicked

    private void btnXoaSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSizeMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này nó sẽ xóa đi một size khỏi cơ sở dữ liệu
        xoaSize(this.tableDSSize, this.defaultTableModelDSSize, this.dsSize);
    }//GEN-LAST:event_btnXoaSizeMouseClicked

    private void btnSuaSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSizeMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì sẽ hiện lên frame sửa thông size cho sản phẩm
        this.frameSuaSize.setSize(500, 400);
        this.frameSuaSize.setLocationRelativeTo(null);
        this.frameSuaSize.setVisible(true);
        this.frameSuaSize.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnSuaSizeMouseClicked

    private void btnHoanThanhThemSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemSizeMouseClicked
        // TODO add your handling code here:
        // click vào nút này thì sẽ hoàn thành thêm size mới vào database
        themSize(this.defaultTableModelDSSize, this.dsSize);
    }//GEN-LAST:event_btnHoanThanhThemSizeMouseClicked

    private void btnHoanThanhThemLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        // khi ta nhấn nút btnHoanThanhThemLoaiSanPham thì nó sẽ lưu sản phẩm vào database
        themLoaiSanPham(this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
    }//GEN-LAST:event_btnHoanThanhThemLoaiSanPhamMouseClicked

    private void btnHoanThanhSuaLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này nó sẽ sửa một loại sản phẩm
        suaLoaiSanPham(this.tableDSLoaiSanPham, this.defaultTableModelDSLoaiSanPham, this.dsLoaiSanPham);
    }//GEN-LAST:event_btnHoanThanhSuaLoaiSanPhamMouseClicked

    private void btnSuaSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaSizeActionPerformed

    private void textFieldThemTenLoaiSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenLoaiSanPhamKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenLoaiSanPham.getText().equals("")) {
            this.labelLoiThemTenLoaiSanPham.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenLoaiSanPhamKeyPressed

    private void textFieldThemTenSizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenSizeKeyPressed
        // TODO add your handling code here:
         if (!this.labelLoiThemTenSize.getText().equals("")) {
            this.labelLoiThemTenSize.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenSizeKeyPressed

    private void textFieldSuaTenLoaiSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenLoaiSanPhamKeyPressed
        // TODO add your handling code here:
         if (!this.labelLoiSuaTenLoaiSanPham.getText().equals("")) {
            this.labelLoiSuaTenLoaiSanPham.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenLoaiSanPhamKeyPressed

    private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyPressed
        // TODO add your handling code here:
         if (!this.labelLoiSuaTenSize.getText().equals("")) {
            this.labelLoiSuaTenSize.setText("");
        }
    }//GEN-LAST:event_textFieldKeyPressed

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
            java.util.logging.Logger.getLogger(QLTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLTheLoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLTheLoai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaLoaiSanPham;
    private javax.swing.JButton btnHoanThanhSuaSize;
    private javax.swing.JButton btnHoanThanhThemLoaiSanPham;
    private javax.swing.JButton btnHoanThanhThemSize;
    private javax.swing.JButton btnHuyTimLoaiSanPham;
    private javax.swing.JButton btnSuaLoaiSanPham;
    private javax.swing.JButton btnSuaSize;
    private javax.swing.JButton btnThemLoaiSanPham;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnTimLoaiSanPham;
    private javax.swing.JButton btnXoaLoaiSanPham;
    private javax.swing.JButton btnXoaSize;
    private javax.swing.JFrame frameSuaLoaiSanPham;
    private javax.swing.JFrame frameSuaSize;
    private javax.swing.JFrame frameThemLoaiSanPham;
    private javax.swing.JFrame frameThemSize;
    private javax.swing.JLabel labelChucNangSize;
    private javax.swing.JLabel labelLoiSuaTenLoaiSanPham;
    private javax.swing.JLabel labelLoiSuaTenSize;
    private javax.swing.JLabel labelLoiThemTenLoaiSanPham;
    private javax.swing.JLabel labelLoiThemTenSize;
    private javax.swing.JLabel labelSuaTenLoaiSnaPham;
    private javax.swing.JLabel labelSuaTenSize;
    private javax.swing.JLabel labelThemTenLoaiSanPham;
    private javax.swing.JLabel labelThemTenSize;
    private javax.swing.JLabel labelTimKiemLoaiSanPham;
    private javax.swing.JLabel labelTitleQLLoaiSanPham;
    private javax.swing.JLabel labelTitleQLSize;
    private javax.swing.JLabel labelTitleSuaLoaiSanPham;
    private javax.swing.JLabel labelTitleSuaSize;
    private javax.swing.JLabel labelTitleThemLoaiSanPham;
    private javax.swing.JLabel labelTitleThemSize;
    private javax.swing.JLabel lbelChucNangLoaiSanPham;
    private javax.swing.JPanel panelChucNangLoaiSanPham;
    private javax.swing.JPanel panelChucNangSize;
    private javax.swing.JPanel panelNoiDungThemLoaiSanPham;
    private javax.swing.JPanel panelQLLoaiSanPhamContent;
    private javax.swing.JPanel panelSuaLoaiSanPham;
    private javax.swing.JPanel panelSuaSize;
    private javax.swing.JPanel panelThemSize;
    private javax.swing.JPanel panelTimKiemLoaiSanPham;
    private javax.swing.JScrollPane scrollPanelDSSize;
    private javax.swing.JScrollPane scrollpanelDSLoaiSanPham;
    private javax.swing.JTable tableDSLoaiSanPham;
    private javax.swing.JTable tableDSSize;
    private javax.swing.JTextField textField;
    private javax.swing.JTextField textFieldSuaTenLoaiSanPham;
    private javax.swing.JTextField textFieldThemTenLoaiSanPham;
    private javax.swing.JTextField textFieldThemTenSize;
    private javax.swing.JTextField textFieldTimKiemLoaiSanPham;
    // End of variables declaration//GEN-END:variables
}
