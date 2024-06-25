/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.SupplierDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Supplier;

/**
 *
 * @author Admin
 */
public class QLNhaCungCap extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLNhaCungCap
     */
    public DefaultTableModel defaultTableModelDSNhaCungCap;
    public ArrayList<Supplier> dsNhaCungCap;
    public SupplierDAO supplierDAO;

    public QLNhaCungCap() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.dsNhaCungCap = new ArrayList<>();
        this.supplierDAO = new SupplierDAO();

        // Supplier
        this.defaultTableModelDSNhaCungCap = new DefaultTableModel();
        this.tableDSNhaCungCap.setModel(this.defaultTableModelDSNhaCungCap);
        String[] columnsDSNhaCungCap = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Người đại diện", "SDT"};
        this.defaultTableModelDSNhaCungCap.setColumnIdentifiers(columnsDSNhaCungCap);
        JTableHeader headerDSNhaCungCap = this.tableDSNhaCungCap.getTableHeader();
        headerDSNhaCungCap.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSNhaCungCap.setBackground(new Color(0x43CD80));
        headerDSNhaCungCap.setForeground(Color.WHITE);
        headerDSNhaCungCap.setPreferredSize(new Dimension(headerDSNhaCungCap.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSNhaCungCap.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSNhaCungCap.setRowHeight(30);
        this.tableDSNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tableDSNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(180);
        this.tableDSNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(160);
        this.tableDSNhaCungCap.getColumnModel().getColumn(3).setPreferredWidth(100);

        this.scrollPaneNhaCungCap.getViewport().setBackground(Color.WHITE);

        getAllSupplier(this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
    }

    //    // ==================== SUPPLIER ====================
    public void xuatDSNhaCungraTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        defaultTableModel.setRowCount(0);
        for (Supplier i : ds) {
            Object[] data = {i.getIdSupplier(), i.getNameSupplier(), i.getRepresentPerson(), i.getNumberPhoneSupplier()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllSupplier(DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        ds = this.supplierDAO.getAllSupplier();
        xuatDSNhaCungraTableTuArrayList(defaultTableModel, ds);
    }

    public Supplier laySupplierKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Supplier a = this.supplierDAO.getSupplierById(id);
            return a;
        } else {
            return null;
        }
    }

    public void resetFrameThemNhaCungCap() {
        this.textFieldThemTenNhaCungCap.setText("");
        this.textFieldThemTenNguoiDaiDienNhaCungCap.setText("");
        this.textFieldThemSDTNhaCungCap.setText("");
        this.textFieldThemDiaChiNhaCungCap.setText("");
        this.textAreaThemMoTaNhaCungCap.setText("");
    }

    public void themNhaCungCap(DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        String thongBaoLoi = "";
        String regexSDT = "^0(\\d{9})$";
        Pattern patternSDT = Pattern.compile(regexSDT);
        Matcher matcherSDT;
        if (this.textFieldThemTenNhaCungCap.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên nhà cung cấp.";
            this.labelLoiThemTenNhaCungCap.setText("Vui lòng nhập tên nhà cung cấp.");
        }
        if (this.textFieldThemTenNguoiDaiDienNhaCungCap.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên người đại diện.";
            this.labelLoiThemTenNguoiDaiDienNhaCungCap.setText("Vui lòng nhập tên người đại diện.");
        }
        if (this.textFieldThemSDTNhaCungCap.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập SDT nhà cung cấp.";
            this.labelLoiThemSDTNhaCungCap.setText("Vui lòng nhập SDT nhà cung cấp.");
        } else {
            matcherSDT = patternSDT.matcher(this.textFieldThemSDTNhaCungCap.getText());
            if (!matcherSDT.matches()) {
                thongBaoLoi += "SDT không hợp lệ";
                this.labelLoiThemSDTNhaCungCap.setText("SDT không hợp lệ");
            }
        }
        if (this.textFieldThemDiaChiNhaCungCap.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập địa chỉ.";
            this.labelLoiThemDiaChiNhaCungCap.setText("Vui lòng nhập địa chỉ.");
        }
        if (thongBaoLoi.equals("")) {
            String tenNhaCungCap = this.textFieldThemTenNhaCungCap.getText();
            String nguoiDaiDien = this.textFieldThemTenNguoiDaiDienNhaCungCap.getText();
            String sdtNhaCungCap = this.textFieldThemSDTNhaCungCap.getText();
            String diaChiNhaCungCap = this.textFieldThemDiaChiNhaCungCap.getText();
            String moTa = this.textAreaThemMoTaNhaCungCap.getText();
            boolean statusSupplier = true;
            Supplier a = new Supplier(tenNhaCungCap, nguoiDaiDien, sdtNhaCungCap, moTa, diaChiNhaCungCap, statusSupplier);
            boolean ktTrungNhaCungCap = this.supplierDAO.isExistSupplier(tenNhaCungCap);
            if (!ktTrungNhaCungCap) {
                this.supplierDAO.insertSupplier(a);
                getAllSupplier(defaultTableModel, ds);
                resetFrameThemNhaCungCap();
                //this.frameThemNhaCungCap.setVisible(false);
                this.frameThemNhaCungCap.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Nhà cung cấp này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void xoaNhaCungCap(JTable table, DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhà cung cấp này không.");
            if (confirm == JOptionPane.OK_OPTION) {
                Supplier a = laySupplierKhiClickVaoBang(table, defaultTableModel);
                this.supplierDAO.deleteSupplier(a.getIdSupplier());
                getAllSupplier(defaultTableModel, ds);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienThiThongTinNhaCungCapRaFameSuaNhaCungCap(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Supplier a = laySupplierKhiClickVaoBang(table, defaultTableModel);
            this.textFieldSuaTenNhaCungCap.setText(a.getNameSupplier());
            this.textFieldSuaTenNguoiDaiDienNhaCungCap.setText(a.getRepresentPerson());
            this.textFieldSuaSDTNhaCungCap.setText(a.getNumberPhoneSupplier());
            this.textFieldSuaDiaChiNhaCungCap.setText(a.getAddress());
            this.textAreaSuaMoTaNhaCungCap.setText(a.getDescription());
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để sửa", "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void suaNhaCungCap(JTable table, DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        Supplier nhaCungcapCanSua = laySupplierKhiClickVaoBang(table, defaultTableModel);
        String regexSDT = "^0(\\d{9})$";
        Pattern patternSDT = Pattern.compile(regexSDT);
        Matcher matcherSDT;
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa nhà cung cấp này không?");
        if (confirm == JOptionPane.OK_OPTION) {
            String thongBaoLoi = "";
            if (this.textFieldSuaTenNhaCungCap.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập tên nhà cung cấp.";
                this.labelLoiSuaTenNhaCungCap.setText("Vui lòng nhập tên nhà cung cấp.");
            }
            if (this.textFieldSuaTenNguoiDaiDienNhaCungCap.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập tên người đại diện.";
                this.labelLoiSuaTenNguoiDaiDienNhaCungCap.setText("Vui lòng nhập tên người đại diện.");
            }
            if (this.textFieldSuaSDTNhaCungCap.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập SDT nhà cung cấp.";
                this.labelLoiSuaSDTNhaCungCap.setText("Vui lòng nhập SDT nhà cung cấp.");
            } else {
                matcherSDT = patternSDT.matcher(this.textFieldSuaSDTNhaCungCap.getText());
                if (!matcherSDT.matches()) {
                    thongBaoLoi += "SDT không hợp lệ";
                    this.labelLoiSuaSDTNhaCungCap.setText("SDT không hợp lệ");
                }
            }
            if (this.textFieldSuaDiaChiNhaCungCap.getText().equals("")) {
                thongBaoLoi += "Vui lòng nhập địa chỉ.";
                this.labelLoiSuaDiaChiNhaCungCap.setText("Vui lòng nhập địa chỉ.");
            }
            if (thongBaoLoi.equals("")) {
                String tenNhaCungCap = this.textFieldSuaTenNhaCungCap.getText();
                String nguoiDaiDien = this.textFieldSuaTenNguoiDaiDienNhaCungCap.getText();
                String sdtNhaCungCap = this.textFieldSuaSDTNhaCungCap.getText();
                String diaChiNhaCungCap = this.textFieldSuaDiaChiNhaCungCap.getText();
                String moTa = this.textAreaSuaMoTaNhaCungCap.getText();
                boolean statusSupplier = true;
                Supplier a = new Supplier(tenNhaCungCap, nguoiDaiDien, sdtNhaCungCap, moTa, diaChiNhaCungCap, statusSupplier);
                boolean ktTrungNhaCungCap = this.supplierDAO.isExistSupplier(tenNhaCungCap);
                if (nhaCungcapCanSua.getNameSupplier().equals(tenNhaCungCap)) {
                    ktTrungNhaCungCap = false;
                }
                if (!ktTrungNhaCungCap) {
                    this.supplierDAO.updateSupplier(nhaCungcapCanSua.getIdSupplier(), a);
                    getAllSupplier(defaultTableModel, ds);
                    this.frameSuaNhaCungCap.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tên nhà cung cấp này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    public void hienThiNhaCungCapRaFrameXemChiTiet(JTable table, DefaultTableModel defaultTableModel) {
        Supplier a = laySupplierKhiClickVaoBang(table, defaultTableModel);
        if (a != null) {
            this.textFieldXemIDNhaCungCap.setText(a.getIdSupplier() + "");
            this.textFieldXemTenNhaCungCap.setText(a.getNameSupplier());
            this.textFieldXemTenNguoiDaiDienNhaCungCap.setText(a.getRepresentPerson());
            this.textFieldXemSDTNhaCungCap.setText(a.getNumberPhoneSupplier());
            this.textFieldXemDiaChiNhaCungCap.setText(a.getAddress());
            this.textAreaXemMoTaNhaCungCap.setText(a.getDescription());
        }
    }

    public void timKiemNhaCungCapTheoTen(DefaultTableModel defaultTableModel, ArrayList<Supplier> ds) {
        String tenNhaCungCapCanTim = this.textFieldTimNhaCungCap.getText();
        if (tenNhaCungCapCanTim == null) {
            JOptionPane.showMessageDialog(null, "Nhập tên nhà cung cấp cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<Supplier> kq = this.supplierDAO.findSuppliersByName(tenNhaCungCapCanTim);
            xuatDSNhaCungraTableTuArrayList(defaultTableModel, kq);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameThemNhaCungCap = new javax.swing.JFrame();
        panelThemNhaCungCap = new javax.swing.JPanel();
        labelTitleThemNhaCungCap = new javax.swing.JLabel();
        labelThemTenNhaCungCap = new javax.swing.JLabel();
        textFieldThemTenNhaCungCap = new javax.swing.JTextField();
        labelLoiThemTenNhaCungCap = new javax.swing.JLabel();
        labelThemTenNguoiDaiDienNhaCungCap = new javax.swing.JLabel();
        textFieldThemTenNguoiDaiDienNhaCungCap = new javax.swing.JTextField();
        labelLoiThemTenNguoiDaiDienNhaCungCap = new javax.swing.JLabel();
        labelThemSDTNhaCungCap = new javax.swing.JLabel();
        textFieldThemSDTNhaCungCap = new javax.swing.JTextField();
        labelLoiThemSDTNhaCungCap = new javax.swing.JLabel();
        labelThemDiaChiNhaCungCap = new javax.swing.JLabel();
        textFieldThemDiaChiNhaCungCap = new javax.swing.JTextField();
        labelLoiThemDiaChiNhaCungCap = new javax.swing.JLabel();
        labelThemMoTaNhaCungCap = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaThemMoTaNhaCungCap = new javax.swing.JTextArea();
        btnHoanThanhThemNhaCungCap = new javax.swing.JButton();
        frameSuaNhaCungCap = new javax.swing.JFrame();
        panelSuaNhaCungCap = new javax.swing.JPanel();
        labelTitleSuaNhaCungCap = new javax.swing.JLabel();
        labelSuaTenNhaCungCap = new javax.swing.JLabel();
        textFieldSuaTenNhaCungCap = new javax.swing.JTextField();
        labelLoiSuaTenNhaCungCap = new javax.swing.JLabel();
        labelSuaTenNguoiDaiDienNhaCungCap = new javax.swing.JLabel();
        textFieldSuaTenNguoiDaiDienNhaCungCap = new javax.swing.JTextField();
        labelLoiSuaTenNguoiDaiDienNhaCungCap = new javax.swing.JLabel();
        labelSuaSDTNhaCungCap = new javax.swing.JLabel();
        textFieldSuaSDTNhaCungCap = new javax.swing.JTextField();
        labelLoiSuaSDTNhaCungCap = new javax.swing.JLabel();
        labelSuaDiaChiNhaCungCap = new javax.swing.JLabel();
        textFieldSuaDiaChiNhaCungCap = new javax.swing.JTextField();
        labelLoiSuaDiaChiNhaCungCap = new javax.swing.JLabel();
        labelSuaMoTaNhaCungCap = new javax.swing.JLabel();
        scrollpaneSuaMoTaNhaCungCap = new javax.swing.JScrollPane();
        textAreaSuaMoTaNhaCungCap = new javax.swing.JTextArea();
        btnHoanThanhSuaNhaCungCap = new javax.swing.JButton();
        frameXemChiTietNhaCungCap = new javax.swing.JFrame();
        panelXemChiTietNhaCungCap = new javax.swing.JPanel();
        labelTitleXemChiTietNhaCungCap = new javax.swing.JLabel();
        labelXemTenNhaCungCap = new javax.swing.JLabel();
        textFieldXemTenNhaCungCap = new javax.swing.JTextField();
        labelXemTenNguoiDaiDienNhaCungCap = new javax.swing.JLabel();
        textFieldXemTenNguoiDaiDienNhaCungCap = new javax.swing.JTextField();
        labelXemSDTNhaCungCap = new javax.swing.JLabel();
        textFieldXemSDTNhaCungCap = new javax.swing.JTextField();
        labelXemDiaChiNhaCungCap = new javax.swing.JLabel();
        textFieldXemDiaChiNhaCungCap = new javax.swing.JTextField();
        labelXemMoTaNhaCungCap = new javax.swing.JLabel();
        scrollpaneSuaMoTaNhaCungCap1 = new javax.swing.JScrollPane();
        textAreaXemMoTaNhaCungCap = new javax.swing.JTextArea();
        labelXemIdNhaCungCap = new javax.swing.JLabel();
        textFieldXemIDNhaCungCap = new javax.swing.JTextField();
        panelQLNhaCungCapContent4 = new javax.swing.JPanel();
        labelTitleNhaCungCapContent4 = new javax.swing.JLabel();
        panelChucNangNhaCungCap4 = new javax.swing.JPanel();
        btnThemNhaCungCap4 = new javax.swing.JButton();
        btnXoaNhaCungCap4 = new javax.swing.JButton();
        btnSuaNhaCungCap4 = new javax.swing.JButton();
        btnXemChiTietNhaCungCap4 = new javax.swing.JButton();
        panelTimKiemNhaCungCap = new javax.swing.JPanel();
        labelTimNhaCungCapTheoTen = new javax.swing.JLabel();
        textFieldTimNhaCungCap = new javax.swing.JTextField();
        btnTimKiemNhaCungCap = new javax.swing.JButton();
        btnHuyTimNhaCungCap = new javax.swing.JButton();
        labelDSNhaCungCap = new javax.swing.JLabel();
        scrollPaneNhaCungCap = new javax.swing.JScrollPane();
        tableDSNhaCungCap = new javax.swing.JTable();

        frameThemNhaCungCap.setTitle("Thêm nhà cung cấp");

        panelThemNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleThemNhaCungCap.setBackground(new java.awt.Color(16, 185, 129));
        labelTitleThemNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemNhaCungCap.setText("THÊM NHÀ CUNG CẤP");
        labelTitleThemNhaCungCap.setOpaque(true);

        labelThemTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenNhaCungCap.setText("Tên nhà cung cấp");
        labelThemTenNhaCungCap.setOpaque(true);

        textFieldThemTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenNhaCungCap.setOpaque(true);
        textFieldThemTenNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiThemTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemTenNhaCungCap.setOpaque(true);

        labelThemTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenNguoiDaiDienNhaCungCap.setText("Tên người đại diện");
        labelThemTenNguoiDaiDienNhaCungCap.setOpaque(true);

        textFieldThemTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenNguoiDaiDienNhaCungCap.setOpaque(true);
        textFieldThemTenNguoiDaiDienNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenNguoiDaiDienNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiThemTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenNguoiDaiDienNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));

        labelThemSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelThemSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemSDTNhaCungCap.setText("Số điện thoại");
        labelThemSDTNhaCungCap.setOpaque(true);

        textFieldThemSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemSDTNhaCungCap.setOpaque(true);
        textFieldThemSDTNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemSDTNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiThemSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemSDTNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemSDTNhaCungCap.setOpaque(true);

        labelThemDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelThemDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemDiaChiNhaCungCap.setText("Địa chỉ");
        labelThemDiaChiNhaCungCap.setOpaque(true);

        textFieldThemDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemDiaChiNhaCungCap.setOpaque(true);
        textFieldThemDiaChiNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemDiaChiNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiThemDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemDiaChiNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemDiaChiNhaCungCap.setOpaque(true);

        labelThemMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelThemMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemMoTaNhaCungCap.setText("Mô tả");
        labelThemMoTaNhaCungCap.setOpaque(true);

        textAreaThemMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textAreaThemMoTaNhaCungCap.setColumns(20);
        textAreaThemMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaThemMoTaNhaCungCap.setRows(5);
        jScrollPane1.setViewportView(textAreaThemMoTaNhaCungCap);

        btnHoanThanhThemNhaCungCap.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemNhaCungCap.setText("THÊM");
        btnHoanThanhThemNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        btnHoanThanhThemNhaCungCap.setOpaque(true);
        btnHoanThanhThemNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemNhaCungCapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemNhaCungCapLayout = new javax.swing.GroupLayout(panelThemNhaCungCap);
        panelThemNhaCungCap.setLayout(panelThemNhaCungCapLayout);
        panelThemNhaCungCapLayout.setHorizontalGroup(
            panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelThemNhaCungCapLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelThemTenNhaCungCap)
                    .addComponent(labelThemTenNguoiDaiDienNhaCungCap)
                    .addComponent(labelThemSDTNhaCungCap)
                    .addComponent(textFieldThemSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(labelLoiThemTenNguoiDaiDienNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldThemTenNguoiDaiDienNhaCungCap)
                    .addComponent(labelLoiThemTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldThemTenNhaCungCap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelThemDiaChiNhaCungCap)
                    .addComponent(labelLoiThemDiaChiNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemMoTaNhaCungCap)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(textFieldThemDiaChiNhaCungCap))
                .addGap(34, 34, 34))
            .addGroup(panelThemNhaCungCapLayout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(btnHoanThanhThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThemNhaCungCapLayout.setVerticalGroup(
            panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelThemTenNhaCungCap)
                    .addComponent(labelThemDiaChiNhaCungCap))
                .addGap(18, 18, 18)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldThemTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldThemDiaChiNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLoiThemTenNhaCungCap)
                    .addComponent(labelLoiThemDiaChiNhaCungCap))
                .addGap(18, 18, 18)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelThemTenNguoiDaiDienNhaCungCap)
                    .addComponent(labelThemMoTaNhaCungCap))
                .addGap(18, 18, 18)
                .addGroup(panelThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelThemNhaCungCapLayout.createSequentialGroup()
                        .addComponent(textFieldThemTenNguoiDaiDienNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiThemTenNguoiDaiDienNhaCungCap)
                        .addGap(18, 18, 18)
                        .addComponent(labelThemSDTNhaCungCap)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldThemSDTNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelLoiThemSDTNhaCungCap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btnHoanThanhThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout frameThemNhaCungCapLayout = new javax.swing.GroupLayout(frameThemNhaCungCap.getContentPane());
        frameThemNhaCungCap.getContentPane().setLayout(frameThemNhaCungCapLayout);
        frameThemNhaCungCapLayout.setHorizontalGroup(
            frameThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameThemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameThemNhaCungCapLayout.setVerticalGroup(
            frameThemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaNhaCungCap.setTitle("Thay đổi thông tin nhà cung cấp");
        frameSuaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        panelSuaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaNhaCungCap.setBackground(new java.awt.Color(255, 153, 0));
        labelTitleSuaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaNhaCungCap.setText("THAY ĐỔI NHÀ CUNG CẤP");
        labelTitleSuaNhaCungCap.setOpaque(true);

        labelSuaTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenNhaCungCap.setText("Tên nhà cung cấp");
        labelSuaTenNhaCungCap.setOpaque(true);

        textFieldSuaTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenNhaCungCap.setOpaque(true);
        textFieldSuaTenNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiSuaTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaTenNhaCungCap.setOpaque(true);

        labelSuaTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenNguoiDaiDienNhaCungCap.setText("Tên người đại diện");
        labelSuaTenNguoiDaiDienNhaCungCap.setOpaque(true);

        textFieldSuaTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenNguoiDaiDienNhaCungCap.setOpaque(true);
        textFieldSuaTenNguoiDaiDienNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenNguoiDaiDienNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiSuaTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenNguoiDaiDienNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaSDTNhaCungCap.setText("Số điện thoại");
        labelSuaSDTNhaCungCap.setOpaque(true);

        textFieldSuaSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaSDTNhaCungCap.setOpaque(true);
        textFieldSuaSDTNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaSDTNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiSuaSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaSDTNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaSDTNhaCungCap.setOpaque(true);

        labelSuaDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaDiaChiNhaCungCap.setText("Địa chỉ");
        labelSuaDiaChiNhaCungCap.setOpaque(true);

        textFieldSuaDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaDiaChiNhaCungCap.setOpaque(true);
        textFieldSuaDiaChiNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaDiaChiNhaCungCapKeyPressed(evt);
            }
        });

        labelLoiSuaDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaDiaChiNhaCungCap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaDiaChiNhaCungCap.setOpaque(true);

        labelSuaMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaMoTaNhaCungCap.setText("Mô tả");
        labelSuaMoTaNhaCungCap.setOpaque(true);

        textAreaSuaMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textAreaSuaMoTaNhaCungCap.setColumns(20);
        textAreaSuaMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaSuaMoTaNhaCungCap.setRows(5);
        scrollpaneSuaMoTaNhaCungCap.setViewportView(textAreaSuaMoTaNhaCungCap);

        btnHoanThanhSuaNhaCungCap.setBackground(new java.awt.Color(255, 152, 0));
        btnHoanThanhSuaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaNhaCungCap.setText("THAY ĐỔI");
        btnHoanThanhSuaNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        btnHoanThanhSuaNhaCungCap.setOpaque(true);
        btnHoanThanhSuaNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaNhaCungCapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaNhaCungCapLayout = new javax.swing.GroupLayout(panelSuaNhaCungCap);
        panelSuaNhaCungCap.setLayout(panelSuaNhaCungCapLayout);
        panelSuaNhaCungCapLayout.setHorizontalGroup(
            panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                            .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelSuaTenNhaCungCap)
                                .addComponent(labelSuaTenNguoiDaiDienNhaCungCap)
                                .addComponent(labelSuaSDTNhaCungCap)
                                .addComponent(textFieldSuaSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addComponent(labelLoiSuaTenNguoiDaiDienNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textFieldSuaTenNguoiDaiDienNhaCungCap)
                                .addComponent(labelLoiSuaTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelLoiSuaSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textFieldSuaTenNhaCungCap))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                            .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelSuaDiaChiNhaCungCap)
                                .addComponent(labelLoiSuaDiaChiNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelSuaMoTaNhaCungCap)
                                .addComponent(scrollpaneSuaMoTaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addComponent(textFieldSuaDiaChiNhaCungCap)))
                        .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                            .addGap(346, 346, 346)
                            .addComponent(btnHoanThanhSuaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        panelSuaNhaCungCapLayout.setVerticalGroup(
            panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
            .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSuaTenNhaCungCap)
                        .addComponent(labelSuaDiaChiNhaCungCap))
                    .addGap(18, 18, 18)
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldSuaTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldSuaDiaChiNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelLoiSuaTenNhaCungCap)
                        .addComponent(labelLoiSuaDiaChiNhaCungCap))
                    .addGap(18, 18, 18)
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSuaTenNguoiDaiDienNhaCungCap)
                        .addComponent(labelSuaMoTaNhaCungCap))
                    .addGap(18, 18, 18)
                    .addGroup(panelSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollpaneSuaMoTaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelSuaNhaCungCapLayout.createSequentialGroup()
                            .addComponent(textFieldSuaTenNguoiDaiDienNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelLoiSuaTenNguoiDaiDienNhaCungCap)
                            .addGap(18, 18, 18)
                            .addComponent(labelSuaSDTNhaCungCap)
                            .addGap(18, 18, 18)
                            .addComponent(textFieldSuaSDTNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(labelLoiSuaSDTNhaCungCap)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                    .addComponent(btnHoanThanhSuaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(76, 76, 76)))
        );

        javax.swing.GroupLayout frameSuaNhaCungCapLayout = new javax.swing.GroupLayout(frameSuaNhaCungCap.getContentPane());
        frameSuaNhaCungCap.getContentPane().setLayout(frameSuaNhaCungCapLayout);
        frameSuaNhaCungCapLayout.setHorizontalGroup(
            frameSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaNhaCungCapLayout.setVerticalGroup(
            frameSuaNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameXemChiTietNhaCungCap.setTitle("Thông tin chi tiết nhà cung cấp");

        panelXemChiTietNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemChiTietNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleXemChiTietNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemChiTietNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemChiTietNhaCungCap.setText("THÔNG TIN NHÀ CUNG CẤP");
        labelTitleXemChiTietNhaCungCap.setOpaque(true);

        labelXemTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenNhaCungCap.setText("Tên nhà cung cấp");
        labelXemTenNhaCungCap.setOpaque(true);

        textFieldXemTenNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemTenNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemTenNhaCungCap.setOpaque(true);

        labelXemTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenNguoiDaiDienNhaCungCap.setText("Tên người đại diện");
        labelXemTenNguoiDaiDienNhaCungCap.setOpaque(true);

        textFieldXemTenNguoiDaiDienNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemTenNguoiDaiDienNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemTenNguoiDaiDienNhaCungCap.setOpaque(true);

        labelXemSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemSDTNhaCungCap.setText("Số điện thoại");
        labelXemSDTNhaCungCap.setOpaque(true);

        textFieldXemSDTNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemSDTNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemSDTNhaCungCap.setOpaque(true);

        labelXemDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemDiaChiNhaCungCap.setText("Địa chỉ");
        labelXemDiaChiNhaCungCap.setOpaque(true);

        textFieldXemDiaChiNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemDiaChiNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemDiaChiNhaCungCap.setOpaque(true);

        labelXemMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMoTaNhaCungCap.setText("Mô tả");
        labelXemMoTaNhaCungCap.setOpaque(true);

        textAreaXemMoTaNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textAreaXemMoTaNhaCungCap.setColumns(20);
        textAreaXemMoTaNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaXemMoTaNhaCungCap.setRows(5);
        scrollpaneSuaMoTaNhaCungCap1.setViewportView(textAreaXemMoTaNhaCungCap);

        labelXemIdNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemIdNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemIdNhaCungCap.setText("Mã nhà cung cấp");
        labelXemIdNhaCungCap.setOpaque(true);

        textFieldXemIDNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemIDNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelXemChiTietNhaCungCapLayout = new javax.swing.GroupLayout(panelXemChiTietNhaCungCap);
        panelXemChiTietNhaCungCap.setLayout(panelXemChiTietNhaCungCapLayout);
        panelXemChiTietNhaCungCapLayout.setHorizontalGroup(
            panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleXemChiTietNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelXemChiTietNhaCungCapLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldXemSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                            .addComponent(labelXemSDTNhaCungCap)
                            .addComponent(labelXemTenNguoiDaiDienNhaCungCap)
                            .addComponent(labelXemTenNhaCungCap)
                            .addComponent(labelXemIdNhaCungCap)
                            .addComponent(textFieldXemIDNhaCungCap)
                            .addComponent(textFieldXemTenNhaCungCap)
                            .addComponent(textFieldXemTenNguoiDaiDienNhaCungCap))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemChiTietNhaCungCapLayout.createSequentialGroup()
                                    .addComponent(labelXemDiaChiNhaCungCap)
                                    .addGap(321, 321, 321))
                                .addComponent(textFieldXemDiaChiNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelXemMoTaNhaCungCap))
                            .addComponent(scrollpaneSuaMoTaNhaCungCap1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelXemChiTietNhaCungCapLayout.setVerticalGroup(
            panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleXemChiTietNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemIdNhaCungCap)
                    .addComponent(labelXemDiaChiNhaCungCap))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldXemIDNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldXemDiaChiNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemTenNhaCungCap)
                    .addComponent(labelXemMoTaNhaCungCap))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietNhaCungCapLayout.createSequentialGroup()
                        .addComponent(textFieldXemTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemTenNguoiDaiDienNhaCungCap)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemTenNguoiDaiDienNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpaneSuaMoTaNhaCungCap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelXemSDTNhaCungCap)
                .addGap(26, 26, 26)
                .addComponent(textFieldXemSDTNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameXemChiTietNhaCungCapLayout = new javax.swing.GroupLayout(frameXemChiTietNhaCungCap.getContentPane());
        frameXemChiTietNhaCungCap.getContentPane().setLayout(frameXemChiTietNhaCungCapLayout);
        frameXemChiTietNhaCungCapLayout.setHorizontalGroup(
            frameXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemChiTietNhaCungCapLayout.setVerticalGroup(
            frameXemChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLNhaCungCapContent4.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleNhaCungCapContent4.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleNhaCungCapContent4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleNhaCungCapContent4.setText("QUẢN LÝ NHÀ CUNG CẤP");
        labelTitleNhaCungCapContent4.setOpaque(true);

        panelChucNangNhaCungCap4.setBackground(new java.awt.Color(255, 255, 255));

        btnThemNhaCungCap4.setBackground(new java.awt.Color(255, 255, 255));
        btnThemNhaCungCap4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemNhaCungCap4.setForeground(new java.awt.Color(0, 153, 51));
        btnThemNhaCungCap4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemNhaCungCap4.setText("THÊM");
        btnThemNhaCungCap4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemNhaCungCap4.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemNhaCungCap4.setMaximumSize(new java.awt.Dimension(81, 42));
        btnThemNhaCungCap4.setMinimumSize(new java.awt.Dimension(81, 42));
        btnThemNhaCungCap4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhaCungCap4MouseClicked(evt);
            }
        });

        btnXoaNhaCungCap4.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaNhaCungCap4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaNhaCungCap4.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaNhaCungCap4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaNhaCungCap4.setText("XÓA");
        btnXoaNhaCungCap4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaNhaCungCap4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNhaCungCap4MouseClicked(evt);
            }
        });

        btnSuaNhaCungCap4.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaNhaCungCap4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaNhaCungCap4.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaNhaCungCap4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaNhaCungCap4.setText("SỬA");
        btnSuaNhaCungCap4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaNhaCungCap4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNhaCungCap4MouseClicked(evt);
            }
        });

        btnXemChiTietNhaCungCap4.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietNhaCungCap4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietNhaCungCap4.setForeground(new java.awt.Color(153, 0, 153));
        btnXemChiTietNhaCungCap4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_xemChiTiet_20px.png"))); // NOI18N
        btnXemChiTietNhaCungCap4.setText("CHI TIẾT");
        btnXemChiTietNhaCungCap4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietNhaCungCap4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangNhaCungCap4Layout = new javax.swing.GroupLayout(panelChucNangNhaCungCap4);
        panelChucNangNhaCungCap4.setLayout(panelChucNangNhaCungCap4Layout);
        panelChucNangNhaCungCap4Layout.setHorizontalGroup(
            panelChucNangNhaCungCap4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNhaCungCap4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNhaCungCap4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChucNangNhaCungCap4Layout.createSequentialGroup()
                        .addComponent(btnXemChiTietNhaCungCap4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelChucNangNhaCungCap4Layout.createSequentialGroup()
                        .addComponent(btnThemNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        panelChucNangNhaCungCap4Layout.setVerticalGroup(
            panelChucNangNhaCungCap4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNhaCungCap4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNhaCungCap4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhaCungCap4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelChucNangNhaCungCap4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaNhaCungCap4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnXemChiTietNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelTimKiemNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        labelTimNhaCungCapTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        labelTimNhaCungCapTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimNhaCungCapTheoTen.setText("Nhập tên nhà cung cấp");
        labelTimNhaCungCapTheoTen.setOpaque(true);

        textFieldTimNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimNhaCungCap.setOpaque(true);

        btnTimKiemNhaCungCap.setBackground(new java.awt.Color(0, 51, 255));
        btnTimKiemNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiemNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemNhaCungCap.setText("Tìm");
        btnTimKiemNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimKiemNhaCungCap.setOpaque(true);
        btnTimKiemNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemNhaCungCapMouseClicked(evt);
            }
        });

        btnHuyTimNhaCungCap.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimNhaCungCap.setText("Hủy tìm");
        btnHuyTimNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimNhaCungCap.setOpaque(true);
        btnHuyTimNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimNhaCungCapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemNhaCungCapLayout = new javax.swing.GroupLayout(panelTimKiemNhaCungCap);
        panelTimKiemNhaCungCap.setLayout(panelTimKiemNhaCungCapLayout);
        panelTimKiemNhaCungCapLayout.setHorizontalGroup(
            panelTimKiemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimNhaCungCapTheoTen)
                    .addGroup(panelTimKiemNhaCungCapLayout.createSequentialGroup()
                        .addComponent(textFieldTimNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTimKiemNhaCungCapLayout.setVerticalGroup(
            panelTimKiemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimNhaCungCapTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTimKiemNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuyTimNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldTimNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        labelDSNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        labelDSNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelDSNhaCungCap.setText("DANH SÁCH NHÀ CUNG CẤP");
        labelDSNhaCungCap.setOpaque(true);

        scrollPaneNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        tableDSNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        tableDSNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSNhaCungCap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDSNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSNhaCungCapMouseClicked(evt);
            }
        });
        scrollPaneNhaCungCap.setViewportView(tableDSNhaCungCap);

        javax.swing.GroupLayout panelQLNhaCungCapContent4Layout = new javax.swing.GroupLayout(panelQLNhaCungCapContent4);
        panelQLNhaCungCapContent4.setLayout(panelQLNhaCungCapContent4Layout);
        panelQLNhaCungCapContent4Layout.setHorizontalGroup(
            panelQLNhaCungCapContent4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNhaCungCapContent4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLNhaCungCapContent4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleNhaCungCapContent4)
                    .addComponent(labelDSNhaCungCap)
                    .addGroup(panelQLNhaCungCapContent4Layout.createSequentialGroup()
                        .addComponent(panelChucNangNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTimKiemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        panelQLNhaCungCapContent4Layout.setVerticalGroup(
            panelQLNhaCungCapContent4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNhaCungCapContent4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleNhaCungCapContent4)
                .addGap(18, 18, 18)
                .addGroup(panelQLNhaCungCapContent4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTimKiemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChucNangNhaCungCap4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelDSNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1666, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelQLNhaCungCapContent4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(402, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelQLNhaCungCapContent4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoanThanhThemNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemNhaCungCapMouseClicked
        // TODO add your handling code here:
        themNhaCungCap(this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
    }//GEN-LAST:event_btnHoanThanhThemNhaCungCapMouseClicked

    private void btnHoanThanhSuaNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaNhaCungCapMouseClicked
        // TODO add your handling code here:
        suaNhaCungCap(this.tableDSNhaCungCap, this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
    }//GEN-LAST:event_btnHoanThanhSuaNhaCungCapMouseClicked

    private void btnThemNhaCungCap4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhaCungCap4MouseClicked
        // TODO add your handling code here:
        // khi nhấn nút này thì sẽ xuất hienj frame thêm nhà cung cấp
        this.frameThemNhaCungCap.setSize(900, 650);
        this.frameThemNhaCungCap.setLocationRelativeTo(null);
        this.frameThemNhaCungCap.setVisible(true);
        this.frameThemNhaCungCap.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemNhaCungCap4MouseClicked

    private void btnXoaNhaCungCap4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNhaCungCap4MouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì sẽ xóa đi một nhà cung cấp
        xoaNhaCungCap(this.tableDSNhaCungCap, this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
    }//GEN-LAST:event_btnXoaNhaCungCap4MouseClicked

    private void btnSuaNhaCungCap4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNhaCungCap4MouseClicked
        // TODO add your handling code here:
        // khi click vào nút này sẽ hiển thị frame sửa nhà cung cấp
        int row = this.tableDSNhaCungCap.getSelectedRow();
        if (row >= 0) {
            this.frameSuaNhaCungCap.setSize(900, 650);
            this.frameSuaNhaCungCap.setLocationRelativeTo(null);
            this.frameSuaNhaCungCap.setVisible(true);
            this.frameSuaNhaCungCap.getContentPane().setBackground(Color.WHITE);
            hienThiThongTinNhaCungCapRaFameSuaNhaCungCap(this.tableDSNhaCungCap, this.defaultTableModelDSNhaCungCap);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để sửa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaNhaCungCap4MouseClicked

    private void btnXemChiTietNhaCungCap4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietNhaCungCap4MouseClicked
        // TODO add your handling code here:
        int row = this.tableDSNhaCungCap.getSelectedRow();
        if (row >= 0) {
            this.frameXemChiTietNhaCungCap.setSize(900, 650);
            this.frameXemChiTietNhaCungCap.setLocationRelativeTo(null);
            this.frameXemChiTietNhaCungCap.setVisible(true);
            this.frameXemChiTietNhaCungCap.getContentPane().setBackground(Color.WHITE);
            hienThiNhaCungCapRaFrameXemChiTiet(this.tableDSNhaCungCap, this.defaultTableModelDSNhaCungCap);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemChiTietNhaCungCap4MouseClicked

    private void btnTimKiemNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemNhaCungCapMouseClicked
        // TODO add your handling code here:
        timKiemNhaCungCapTheoTen(this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
    }//GEN-LAST:event_btnTimKiemNhaCungCapMouseClicked

    private void btnHuyTimNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimNhaCungCapMouseClicked
        // TODO add your handling code here:
        getAllSupplier(this.defaultTableModelDSNhaCungCap, this.dsNhaCungCap);
        this.textFieldTimNhaCungCap.setText("");
    }//GEN-LAST:event_btnHuyTimNhaCungCapMouseClicked

    private void tableDSNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSNhaCungCapMouseClicked
        // TODO add your handling code here:
        // khi click vào table ds NhaCungCap thì nó sẽ lấy ra 1 nhà cung cấp
        //        Supplier a = laySupplierKhiClickVaoBang(this.tableDSNhaCungCap, this.defaultTableModelDSNhaCungCap);
        //        System.out.println(a);
    }//GEN-LAST:event_tableDSNhaCungCapMouseClicked

    private void textFieldThemTenNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenNhaCungCap.getText().equals("")) {
            this.labelLoiThemTenNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenNhaCungCapKeyPressed

    private void textFieldThemTenNguoiDaiDienNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenNguoiDaiDienNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenNguoiDaiDienNhaCungCap.getText().equals("")) {
            this.labelLoiThemTenNguoiDaiDienNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenNguoiDaiDienNhaCungCapKeyPressed

    private void textFieldThemSDTNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemSDTNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemSDTNhaCungCap.getText().equals("")) {
            this.labelLoiThemSDTNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldThemSDTNhaCungCapKeyPressed

    private void textFieldThemDiaChiNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemDiaChiNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemDiaChiNhaCungCap.getText().equals("")) {
            this.labelLoiThemDiaChiNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldThemDiaChiNhaCungCapKeyPressed

    private void textFieldSuaTenNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaTenNhaCungCap.getText().equals("")) {
            this.labelLoiSuaTenNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenNhaCungCapKeyPressed

    private void textFieldSuaTenNguoiDaiDienNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenNguoiDaiDienNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaTenNguoiDaiDienNhaCungCap.getText().equals("")) {
            this.labelLoiSuaTenNguoiDaiDienNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenNguoiDaiDienNhaCungCapKeyPressed

    private void textFieldSuaSDTNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaSDTNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaSDTNhaCungCap.getText().equals("")) {
            this.labelLoiSuaSDTNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldSuaSDTNhaCungCapKeyPressed

    private void textFieldSuaDiaChiNhaCungCapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaDiaChiNhaCungCapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaDiaChiNhaCungCap.getText().equals("")) {
            this.labelLoiSuaDiaChiNhaCungCap.setText("");
        }
    }//GEN-LAST:event_textFieldSuaDiaChiNhaCungCapKeyPressed

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
            java.util.logging.Logger.getLogger(QLNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhaCungCap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaNhaCungCap;
    private javax.swing.JButton btnHoanThanhThemNhaCungCap;
    private javax.swing.JButton btnHuyTimNhaCungCap;
    private javax.swing.JButton btnSuaNhaCungCap4;
    private javax.swing.JButton btnThemNhaCungCap4;
    private javax.swing.JButton btnTimKiemNhaCungCap;
    private javax.swing.JButton btnXemChiTietNhaCungCap4;
    private javax.swing.JButton btnXoaNhaCungCap4;
    private javax.swing.JFrame frameSuaNhaCungCap;
    private javax.swing.JFrame frameThemNhaCungCap;
    private javax.swing.JFrame frameXemChiTietNhaCungCap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDSNhaCungCap;
    private javax.swing.JLabel labelLoiSuaDiaChiNhaCungCap;
    private javax.swing.JLabel labelLoiSuaSDTNhaCungCap;
    private javax.swing.JLabel labelLoiSuaTenNguoiDaiDienNhaCungCap;
    private javax.swing.JLabel labelLoiSuaTenNhaCungCap;
    private javax.swing.JLabel labelLoiThemDiaChiNhaCungCap;
    private javax.swing.JLabel labelLoiThemSDTNhaCungCap;
    private javax.swing.JLabel labelLoiThemTenNguoiDaiDienNhaCungCap;
    private javax.swing.JLabel labelLoiThemTenNhaCungCap;
    private javax.swing.JLabel labelSuaDiaChiNhaCungCap;
    private javax.swing.JLabel labelSuaMoTaNhaCungCap;
    private javax.swing.JLabel labelSuaSDTNhaCungCap;
    private javax.swing.JLabel labelSuaTenNguoiDaiDienNhaCungCap;
    private javax.swing.JLabel labelSuaTenNhaCungCap;
    private javax.swing.JLabel labelThemDiaChiNhaCungCap;
    private javax.swing.JLabel labelThemMoTaNhaCungCap;
    private javax.swing.JLabel labelThemSDTNhaCungCap;
    private javax.swing.JLabel labelThemTenNguoiDaiDienNhaCungCap;
    private javax.swing.JLabel labelThemTenNhaCungCap;
    private javax.swing.JLabel labelTimNhaCungCapTheoTen;
    private javax.swing.JLabel labelTitleNhaCungCapContent4;
    private javax.swing.JLabel labelTitleSuaNhaCungCap;
    private javax.swing.JLabel labelTitleThemNhaCungCap;
    private javax.swing.JLabel labelTitleXemChiTietNhaCungCap;
    private javax.swing.JLabel labelXemDiaChiNhaCungCap;
    private javax.swing.JLabel labelXemIdNhaCungCap;
    private javax.swing.JLabel labelXemMoTaNhaCungCap;
    private javax.swing.JLabel labelXemSDTNhaCungCap;
    private javax.swing.JLabel labelXemTenNguoiDaiDienNhaCungCap;
    private javax.swing.JLabel labelXemTenNhaCungCap;
    private javax.swing.JPanel panelChucNangNhaCungCap4;
    private javax.swing.JPanel panelQLNhaCungCapContent4;
    private javax.swing.JPanel panelSuaNhaCungCap;
    private javax.swing.JPanel panelThemNhaCungCap;
    private javax.swing.JPanel panelTimKiemNhaCungCap;
    private javax.swing.JPanel panelXemChiTietNhaCungCap;
    private javax.swing.JScrollPane scrollPaneNhaCungCap;
    private javax.swing.JScrollPane scrollpaneSuaMoTaNhaCungCap;
    private javax.swing.JScrollPane scrollpaneSuaMoTaNhaCungCap1;
    private javax.swing.JTable tableDSNhaCungCap;
    private javax.swing.JTextArea textAreaSuaMoTaNhaCungCap;
    private javax.swing.JTextArea textAreaThemMoTaNhaCungCap;
    private javax.swing.JTextArea textAreaXemMoTaNhaCungCap;
    private javax.swing.JTextField textFieldSuaDiaChiNhaCungCap;
    private javax.swing.JTextField textFieldSuaSDTNhaCungCap;
    private javax.swing.JTextField textFieldSuaTenNguoiDaiDienNhaCungCap;
    private javax.swing.JTextField textFieldSuaTenNhaCungCap;
    private javax.swing.JTextField textFieldThemDiaChiNhaCungCap;
    private javax.swing.JTextField textFieldThemSDTNhaCungCap;
    private javax.swing.JTextField textFieldThemTenNguoiDaiDienNhaCungCap;
    private javax.swing.JTextField textFieldThemTenNhaCungCap;
    private javax.swing.JTextField textFieldTimNhaCungCap;
    private javax.swing.JTextField textFieldXemDiaChiNhaCungCap;
    private javax.swing.JTextField textFieldXemIDNhaCungCap;
    private javax.swing.JTextField textFieldXemSDTNhaCungCap;
    private javax.swing.JTextField textFieldXemTenNguoiDaiDienNhaCungCap;
    private javax.swing.JTextField textFieldXemTenNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
