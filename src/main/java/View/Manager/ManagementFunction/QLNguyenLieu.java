/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.IngredientDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Ingredient;

/**
 *
 * @author Admin
 */
public class QLNguyenLieu extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLNguyenLieu
     */
    public DefaultTableModel defaultTableModelDSNguyenLieu;
    public ArrayList<Ingredient> dsNguyenLieu;
    public IngredientDAO ingredientDAO;

    public QLNguyenLieu() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.ingredientDAO = new IngredientDAO();

        // Ingredient
        this.defaultTableModelDSNguyenLieu = new DefaultTableModel();
        this.tableDSNguyenLieu.setModel(this.defaultTableModelDSNguyenLieu);
        String[] columnsDSNguyenLieu = {"Mã nguyên liệu", "Tên nguyên liệu", "Đơn vị", "Số lượng"};
        this.defaultTableModelDSNguyenLieu.setColumnIdentifiers(columnsDSNguyenLieu);
        JTableHeader headerDSNguyenLieu = this.tableDSNguyenLieu.getTableHeader();
        headerDSNguyenLieu.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSNguyenLieu.setBackground(new Color(0x43CD80));
        headerDSNguyenLieu.setForeground(Color.WHITE);
        headerDSNguyenLieu.setPreferredSize(new Dimension(headerDSNguyenLieu.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSNguyenLieu.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSNguyenLieu.setRowHeight(30);
        this.tableDSNguyenLieu.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.tableDSNguyenLieu.getColumnModel().getColumn(1).setPreferredWidth(250);
        this.tableDSNguyenLieu.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.tableDSNguyenLieu.getColumnModel().getColumn(3).setPreferredWidth(120);

        this.scrollPaneNguyenLieu.getViewport().setBackground(Color.WHITE);

        getAllIngredient(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }

    // ==================== INGREDIENT ====================
    public void xuatDSNguyenLieuRaTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        defaultTableModel.setRowCount(0);
        for (Ingredient i : ds) {
            Object[] data = {i.getIdIngredient(), i.getNameIngredient(), i.getUnitIngredient(), i.getQuantityIngredient()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllIngredient(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        ds = this.ingredientDAO.getAllIngredient();
        xuatDSNguyenLieuRaTableTuArrayList(defaultTableModel, ds);
    }

    public void resetFrameTheNguyenLieu() {
        this.textFieldThemTenNguyenLieu.setText("");
        this.textFieldThemDonViNguyenLieu.setText("");
        this.textFieldThemSoLuongNguyenLieu.setText("");
    }

    public void themNguyenLieu(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        String thongBaoLoi = "";
        if (this.textFieldThemTenNguyenLieu.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên nguyên liệu.";
            this.labelLoiThemTenNguyenLieu.setText("Vui lòng nhập tên nguyên liệu");
        }
        if (this.textFieldThemDonViNguyenLieu.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập đơn vị của nguyên liệu.";
            this.labelLoiThemDonViNguyenLieu.setText("Vui lòng nhập đơn vị của nguyên liệu.");
        }
        if (this.textFieldThemSoLuongNguyenLieu.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập số lượng của nguyên liệu";
            this.labelLoiThemSoLuongNguyenLieu.setText("Vui lòng nhập số lượng của nguyên liệu");
        } else {
            if (!this.textFieldThemSoLuongNguyenLieu.getText().matches("\\d+")) {
                thongBaoLoi += "Lương phải là số nguyên dương";
                this.labelLoiThemSoLuongNguyenLieu.setText("Lương phải là số nguyên dương");
            }
        }
        if (thongBaoLoi.equals("")) {
            String tenNguyenLieu = this.textFieldThemTenNguyenLieu.getText();
            String donVi = this.textFieldThemDonViNguyenLieu.getText();
            float soLuong = Float.parseFloat(this.textFieldThemSoLuongNguyenLieu.getText() + "");
            boolean statusIngredient = true;
            Ingredient a = new Ingredient(tenNguyenLieu, donVi, soLuong, statusIngredient);
            boolean ktTrungNguyenLieu = this.ingredientDAO.isExistIngredient(a.getNameIngredient());
            if (!ktTrungNguyenLieu) {
                this.ingredientDAO.insertIngredient(a);
                getAllIngredient(defaultTableModel, ds);
                this.frameThemNguyenLieu.dispose();
//                this.textFieldThemTenNguyenLieu.setText("");
//                this.textFieldThemDonViNguyenLieu.setText("");
//                this.textFieldThemSoLuongNguyenLieu.setText("");
                resetFrameTheNguyenLieu();
            } else {
                JOptionPane.showMessageDialog(null, "Nguyên liệu này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Ingredient layMotNguyenLieuKhiClickVaoBangNguyenLieu(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Ingredient a = this.ingredientDAO.getIngredientById(id);
            return a;
        } else {
            return null;
        }
    }

    public void xoaNguyenLieu(JTable table, DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa nguyên liệu này không");
            if (confirm == JOptionPane.OK_OPTION) {
                Ingredient a = layMotNguyenLieuKhiClickVaoBangNguyenLieu(table, defaultTableModel);
                System.out.println(a);
                if (a != null) {
                    this.ingredientDAO.deleteIngredientById(a.getIdIngredient());
                    getAllIngredient(defaultTableModel, ds);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một lnguyên liệu để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void hienThiThongTinNguyenLieuRaFrameSuaNguyenLieu(JTable table, DefaultTableModel defaultTableModel) {
        Ingredient a = layMotNguyenLieuKhiClickVaoBangNguyenLieu(table, defaultTableModel);
        if (a != null) {
            this.textFieldSuaTenNguyenLieu.setText(a.getNameIngredient());
            this.textFieldSuaDonViNguyenLieu.setText(a.getUnitIngredient());
            this.textFieldSuaSoLuongNguyenLieu.setText(a.getQuantityIngredient() + "");
            this.labelLoiSuaTenNguyenLieu.setText("");
            this.labelLoiSuaSoLuongNguyenLieu.setText("");
            this.labelLoiSuaDonViNguyenLieu.setText("");
        }
    }

    public void resetFrameSuaNguyenLieu() {
        this.textFieldSuaTenNguyenLieu.setText("");
        this.textFieldSuaDonViNguyenLieu.setText("");
        this.textFieldSuaSoLuongNguyenLieu.setText("");
    }

    public void suaNguyenLieu(JTable table, DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nguyên liệu này không");
            if (confirm == JOptionPane.OK_OPTION) {
                Ingredient a = layMotNguyenLieuKhiClickVaoBangNguyenLieu(table, defaultTableModel);
                if (a != null) {
                    String thongBaoLoi = "";
                    if (this.textFieldSuaTenNguyenLieu.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập tên nguyên liệu.";
                        this.labelLoiSuaTenNguyenLieu.setText("Vui lòng nhập tên nguyên liệu");
                    }
                    if (this.textFieldSuaDonViNguyenLieu.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập đơn vị của nguyên liệu.";
                        this.labelLoiSuaDonViNguyenLieu.setText("Vui lòng nhập đơn vị của nguyên liệu.");
                    }
                    if (this.textFieldSuaSoLuongNguyenLieu.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập số lượng của nguyên liệu";
                        this.labelLoiSuaSoLuongNguyenLieu.setText("Vui lòng nhập số lượng của nguyên liệu");
                    } else {
                        if (!this.textFieldSuaSoLuongNguyenLieu.getText().matches("\\d+")) {
                            thongBaoLoi += "Lương phải là số nguyên dương";
                            this.labelLoiSuaSoLuongNguyenLieu.setText("Lương phải là số nguyên dương");
                        }
                    }
                    if (thongBaoLoi.equals("")) {
                        String tenNguyenLieu = this.textFieldSuaTenNguyenLieu.getText();
                        String donVi = this.textFieldSuaDonViNguyenLieu.getText();
                        float soLuong = Float.parseFloat(this.textFieldSuaSoLuongNguyenLieu.getText() + "");
                        boolean statusIngredient = true;
                        Ingredient b = new Ingredient(tenNguyenLieu, donVi, soLuong, statusIngredient);
                        boolean ktTrungNguyenLieu = this.ingredientDAO.isExistIngredient(b.getNameIngredient());
                        if (a.getNameIngredient().equals(tenNguyenLieu)) {
                            ktTrungNguyenLieu = false;
                        }
                        if (!ktTrungNguyenLieu) {
                            this.ingredientDAO.updateIngredientById(a.getIdIngredient(), b);
                            getAllIngredient(defaultTableModel, ds);
                            resetFrameSuaNguyenLieu();
                            this.frameSuaNguyenLieu.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Nguyên liệu này đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nguyên liệu để thay đổi", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timKiemNguyenLieuTheoTen(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        ds = this.ingredientDAO.getAllIngredient();
        String tenNguyenLieuCanTim = this.textFieldTimKiemNguyenLieu.getText();
        if (tenNguyenLieuCanTim.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nguyên liệu cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<Ingredient> kq = new ArrayList<>();
            tenNguyenLieuCanTim = tenNguyenLieuCanTim.toLowerCase();
            for (Ingredient i : ds) {
                String tenNguyenLieu = i.getNameIngredient().toLowerCase();
                if (tenNguyenLieu.contains(tenNguyenLieuCanTim)) {
                    kq.add(i);
                }
            }
            xuatDSNguyenLieuRaTableTuArrayList(defaultTableModel, kq);
        }
    }

    public void xemThongTinNguyenLieu(JTable table, DefaultTableModel defaultTableModel) {
        // không cần hàm này
        int row = table.getSelectedRow();
        if (row >= 0) {
            Ingredient a = layMotNguyenLieuKhiClickVaoBangNguyenLieu(table, defaultTableModel);
            if (a != null) {
                this.textFieldXemMaNguyenLieu.setText(a.getIdIngredient() + "");
                this.textFieldXemTenNguyenLieu.setText(a.getNameIngredient());
                this.textFieldXemDonViDoNguyenLieu.setText(a.getUnitIngredient());
                this.textFieldXemSoLuongNguyenLieu.setText(a.getQuantityIngredient() + "");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nguyên liệu để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
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

        frameThemNguyenLieu = new javax.swing.JFrame();
        panelThemNguyenLieu = new javax.swing.JPanel();
        labelTitleThemNguyenLieu = new javax.swing.JLabel();
        labelThemTenNguyenLieu = new javax.swing.JLabel();
        textFieldThemTenNguyenLieu = new javax.swing.JTextField();
        labelLoiThemTenNguyenLieu = new javax.swing.JLabel();
        labelThenDonViNguyenLieu = new javax.swing.JLabel();
        textFieldThemDonViNguyenLieu = new javax.swing.JTextField();
        labelLoiThemDonViNguyenLieu = new javax.swing.JLabel();
        btnHoanThanhThemNguyenLieu = new javax.swing.JButton();
        labelThemSoLuongNguyenLieu = new javax.swing.JLabel();
        textFieldThemSoLuongNguyenLieu = new javax.swing.JTextField();
        labelLoiThemSoLuongNguyenLieu = new javax.swing.JLabel();
        frameSuaNguyenLieu = new javax.swing.JFrame();
        panelSuaNguyenLieu = new javax.swing.JPanel();
        labelTitleSuaNguyenLieu = new javax.swing.JLabel();
        labelSuaTenNguyenLieu = new javax.swing.JLabel();
        textFieldSuaTenNguyenLieu = new javax.swing.JTextField();
        labelLoiSuaTenNguyenLieu = new javax.swing.JLabel();
        labelSuaDonViNguyenLieu = new javax.swing.JLabel();
        textFieldSuaDonViNguyenLieu = new javax.swing.JTextField();
        labelLoiSuaDonViNguyenLieu = new javax.swing.JLabel();
        labelSuaSoLuongNguyenLieu = new javax.swing.JLabel();
        textFieldSuaSoLuongNguyenLieu = new javax.swing.JTextField();
        labelLoiSuaSoLuongNguyenLieu = new javax.swing.JLabel();
        btnHoanThanhSuaNguyenLieu = new javax.swing.JButton();
        frameXemNguyenLieu = new javax.swing.JFrame();
        panelXemNguyenLieu = new javax.swing.JPanel();
        labelTitleXemNguyenLieu = new javax.swing.JLabel();
        labelXemMaNguyenLieu = new javax.swing.JLabel();
        textFieldXemMaNguyenLieu = new javax.swing.JTextField();
        labelXemTenNguyenLieu = new javax.swing.JLabel();
        textFieldXemTenNguyenLieu = new javax.swing.JTextField();
        labelXemDonViDoNguyenLieu = new javax.swing.JLabel();
        textFieldXemDonViDoNguyenLieu = new javax.swing.JTextField();
        labelXemSoLuongNguyenLieu = new javax.swing.JLabel();
        textFieldXemSoLuongNguyenLieu = new javax.swing.JTextField();
        panelQLNguyenLieu = new javax.swing.JPanel();
        labelTitleQLNguyenLieu = new javax.swing.JLabel();
        panelChucNangNguyenLieu = new javax.swing.JPanel();
        btnThemNguyenLieu = new javax.swing.JButton();
        btnXoaNguyenLieu = new javax.swing.JButton();
        btnSuaNguyenLieu = new javax.swing.JButton();
        btnXemChiTietNguyenLieu = new javax.swing.JButton();
        panelTimKiemNguyenLieu = new javax.swing.JPanel();
        labelTimKiemNguyenLieu = new javax.swing.JLabel();
        textFieldTimKiemNguyenLieu = new javax.swing.JTextField();
        btnTimKiemNguyenLieu = new javax.swing.JButton();
        btnHuyTimNguyenLieu = new javax.swing.JButton();
        scrollPaneNguyenLieu = new javax.swing.JScrollPane();
        tableDSNguyenLieu = new javax.swing.JTable();
        labelDSNguyenLieu = new javax.swing.JLabel();

        frameThemNguyenLieu.setTitle("Thêm nguyên liệu");

        panelThemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleThemNguyenLieu.setBackground(new java.awt.Color(67, 205, 128));
        labelTitleThemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemNguyenLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemNguyenLieu.setText("THÊM NGUYÊN LIỆU");
        labelTitleThemNguyenLieu.setOpaque(true);

        labelThemTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenNguyenLieu.setText("Tên nguyên liệu");
        labelThemTenNguyenLieu.setOpaque(true);

        textFieldThemTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenNguyenLieu.setOpaque(true);
        textFieldThemTenNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiThemTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemTenNguyenLieu.setOpaque(true);

        labelThenDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelThenDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThenDonViNguyenLieu.setText("Đơn vị đo của nguyên liệu");
        labelThenDonViNguyenLieu.setOpaque(true);

        textFieldThemDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemDonViNguyenLieu.setOpaque(true);
        textFieldThemDonViNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemDonViNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiThemDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemDonViNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemDonViNguyenLieu.setOpaque(true);

        btnHoanThanhThemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemNguyenLieu.setForeground(new java.awt.Color(67, 205, 128));
        btnHoanThanhThemNguyenLieu.setText("THÊM");
        btnHoanThanhThemNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        btnHoanThanhThemNguyenLieu.setOpaque(true);
        btnHoanThanhThemNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemNguyenLieuMouseClicked(evt);
            }
        });

        labelThemSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelThemSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemSoLuongNguyenLieu.setText("Số lượng");
        labelThemSoLuongNguyenLieu.setOpaque(true);

        textFieldThemSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemSoLuongNguyenLieu.setOpaque(true);
        textFieldThemSoLuongNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemSoLuongNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiThemSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemSoLuongNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemSoLuongNguyenLieu.setOpaque(true);

        javax.swing.GroupLayout panelThemNguyenLieuLayout = new javax.swing.GroupLayout(panelThemNguyenLieu);
        panelThemNguyenLieu.setLayout(panelThemNguyenLieuLayout);
        panelThemNguyenLieuLayout.setHorizontalGroup(
            panelThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNguyenLieuLayout.createSequentialGroup()
                .addGroup(panelThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThemNguyenLieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleThemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThemNguyenLieuLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelThemTenNguyenLieu)
                            .addComponent(textFieldThemTenNguyenLieu)
                            .addComponent(labelLoiThemTenNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelThenDonViNguyenLieu)
                            .addComponent(textFieldThemDonViNguyenLieu)
                            .addComponent(labelLoiThemDonViNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelThemSoLuongNguyenLieu)
                            .addComponent(textFieldThemSoLuongNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(labelLoiThemSoLuongNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelThemNguyenLieuLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(btnHoanThanhThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        panelThemNguyenLieuLayout.setVerticalGroup(
            panelThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelThemTenNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemTenNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(labelThenDonViNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemDonViNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemDonViNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(labelThemSoLuongNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemSoLuongNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemSoLuongNguyenLieu)
                .addGap(33, 33, 33)
                .addComponent(btnHoanThanhThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameThemNguyenLieuLayout = new javax.swing.GroupLayout(frameThemNguyenLieu.getContentPane());
        frameThemNguyenLieu.getContentPane().setLayout(frameThemNguyenLieuLayout);
        frameThemNguyenLieuLayout.setHorizontalGroup(
            frameThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameThemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameThemNguyenLieuLayout.setVerticalGroup(
            frameThemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaNguyenLieu.setTitle("Thay đổi thông tin nguyên liệu");

        panelSuaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaNguyenLieu.setBackground(new java.awt.Color(255, 204, 0));
        labelTitleSuaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaNguyenLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaNguyenLieu.setText("THAY ĐỔI NGUYÊN LIỆU");
        labelTitleSuaNguyenLieu.setOpaque(true);

        labelSuaTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenNguyenLieu.setText("Tên nguyên liệu");
        labelSuaTenNguyenLieu.setOpaque(true);

        textFieldSuaTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenNguyenLieu.setOpaque(true);
        textFieldSuaTenNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiSuaTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaTenNguyenLieu.setOpaque(true);

        labelSuaDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaDonViNguyenLieu.setText("Đơn vị đo nguyên liệu");
        labelSuaDonViNguyenLieu.setOpaque(true);

        textFieldSuaDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaDonViNguyenLieu.setOpaque(true);
        textFieldSuaDonViNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaDonViNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiSuaDonViNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaDonViNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaDonViNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaDonViNguyenLieu.setOpaque(true);

        labelSuaSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaSoLuongNguyenLieu.setText("Số lượng");
        labelSuaSoLuongNguyenLieu.setOpaque(true);

        textFieldSuaSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaSoLuongNguyenLieu.setOpaque(true);
        textFieldSuaSoLuongNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaSoLuongNguyenLieuKeyPressed(evt);
            }
        });

        labelLoiSuaSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiSuaSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaSoLuongNguyenLieu.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiSuaSoLuongNguyenLieu.setOpaque(true);

        btnHoanThanhSuaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaNguyenLieu.setForeground(new java.awt.Color(255, 204, 0));
        btnHoanThanhSuaNguyenLieu.setText("THAY ĐỔI");
        btnHoanThanhSuaNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        btnHoanThanhSuaNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaNguyenLieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaNguyenLieuLayout = new javax.swing.GroupLayout(panelSuaNguyenLieu);
        panelSuaNguyenLieu.setLayout(panelSuaNguyenLieuLayout);
        panelSuaNguyenLieuLayout.setHorizontalGroup(
            panelSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuaNguyenLieuLayout.createSequentialGroup()
                .addGroup(panelSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelSuaNguyenLieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleSuaNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSuaNguyenLieuLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiSuaSoLuongNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldSuaSoLuongNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLoiSuaDonViNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldSuaTenNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLoiSuaTenNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldSuaDonViNguyenLieu)
                            .addGroup(panelSuaNguyenLieuLayout.createSequentialGroup()
                                .addGroup(panelSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSuaTenNguyenLieu)
                                    .addComponent(labelSuaDonViNguyenLieu)
                                    .addComponent(labelSuaSoLuongNguyenLieu))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(panelSuaNguyenLieuLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(btnHoanThanhSuaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        panelSuaNguyenLieuLayout.setVerticalGroup(
            panelSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSuaTenNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldSuaTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaTenNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(labelSuaDonViNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldSuaDonViNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaDonViNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(labelSuaSoLuongNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldSuaSoLuongNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiSuaSoLuongNguyenLieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnHoanThanhSuaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout frameSuaNguyenLieuLayout = new javax.swing.GroupLayout(frameSuaNguyenLieu.getContentPane());
        frameSuaNguyenLieu.getContentPane().setLayout(frameSuaNguyenLieuLayout);
        frameSuaNguyenLieuLayout.setHorizontalGroup(
            frameSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaNguyenLieuLayout.setVerticalGroup(
            frameSuaNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameXemNguyenLieu.setTitle("Thông tin chi tiết nguyên liệu");

        panelXemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemNguyenLieu.setBackground(new java.awt.Color(204, 0, 204));
        labelTitleXemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleXemNguyenLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemNguyenLieu.setText("THÔNG TIN NGUYÊN LIỆU");
        labelTitleXemNguyenLieu.setOpaque(true);

        labelXemMaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaNguyenLieu.setText("Mã nguyên liệu");
        labelXemMaNguyenLieu.setOpaque(true);

        textFieldXemMaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemMaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemMaNguyenLieu.setOpaque(true);

        labelXemTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenNguyenLieu.setText("Tên nguyên liệu");
        labelXemTenNguyenLieu.setOpaque(true);

        textFieldXemTenNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemTenNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemTenNguyenLieu.setOpaque(true);

        labelXemDonViDoNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelXemDonViDoNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemDonViDoNguyenLieu.setText("Đơn vị đo nguyên liệu");
        labelXemDonViDoNguyenLieu.setOpaque(true);

        textFieldXemDonViDoNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemDonViDoNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemDonViDoNguyenLieu.setOpaque(true);

        labelXemSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelXemSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemSoLuongNguyenLieu.setText("Số lượng");
        labelXemSoLuongNguyenLieu.setOpaque(true);

        textFieldXemSoLuongNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemSoLuongNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemSoLuongNguyenLieu.setOpaque(true);

        javax.swing.GroupLayout panelXemNguyenLieuLayout = new javax.swing.GroupLayout(panelXemNguyenLieu);
        panelXemNguyenLieu.setLayout(panelXemNguyenLieuLayout);
        panelXemNguyenLieuLayout.setHorizontalGroup(
            panelXemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleXemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelXemNguyenLieuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelXemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelXemMaNguyenLieu)
                    .addComponent(textFieldXemMaNguyenLieu)
                    .addComponent(labelXemTenNguyenLieu)
                    .addComponent(textFieldXemTenNguyenLieu)
                    .addComponent(labelXemDonViDoNguyenLieu)
                    .addComponent(textFieldXemDonViDoNguyenLieu)
                    .addComponent(labelXemSoLuongNguyenLieu)
                    .addComponent(textFieldXemSoLuongNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelXemNguyenLieuLayout.setVerticalGroup(
            panelXemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleXemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelXemMaNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldXemMaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelXemTenNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldXemTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelXemDonViDoNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldXemDonViDoNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelXemSoLuongNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(textFieldXemSoLuongNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameXemNguyenLieuLayout = new javax.swing.GroupLayout(frameXemNguyenLieu.getContentPane());
        frameXemNguyenLieu.getContentPane().setLayout(frameXemNguyenLieuLayout);
        frameXemNguyenLieuLayout.setHorizontalGroup(
            frameXemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemNguyenLieuLayout.setVerticalGroup(
            frameXemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLNguyenLieu.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleQLNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLNguyenLieu.setText("QUẢN LÝ NGUYÊN LIỆU");
        labelTitleQLNguyenLieu.setOpaque(true);

        panelChucNangNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        btnThemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnThemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemNguyenLieu.setForeground(new java.awt.Color(0, 153, 51));
        btnThemNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemNguyenLieu.setText("THÊM");
        btnThemNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemNguyenLieu.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNguyenLieuMouseClicked(evt);
            }
        });

        btnXoaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaNguyenLieu.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaNguyenLieu.setText("XÓA");
        btnXoaNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNguyenLieuMouseClicked(evt);
            }
        });

        btnSuaNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaNguyenLieu.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaNguyenLieu.setText("SỬA");
        btnSuaNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaNguyenLieuMouseClicked(evt);
            }
        });

        btnXemChiTietNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietNguyenLieu.setForeground(new java.awt.Color(153, 0, 153));
        btnXemChiTietNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_xemChiTiet_20px.png"))); // NOI18N
        btnXemChiTietNguyenLieu.setText("CHI TIẾT");
        btnXemChiTietNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietNguyenLieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangNguyenLieuLayout = new javax.swing.GroupLayout(panelChucNangNguyenLieu);
        panelChucNangNguyenLieu.setLayout(panelChucNangNguyenLieuLayout);
        panelChucNangNguyenLieuLayout.setHorizontalGroup(
            panelChucNangNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChucNangNguyenLieuLayout.createSequentialGroup()
                        .addComponent(btnXemChiTietNguyenLieu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelChucNangNguyenLieuLayout.createSequentialGroup()
                        .addComponent(btnThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        panelChucNangNguyenLieuLayout.setVerticalGroup(
            panelChucNangNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelChucNangNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSuaNguyenLieu)))
                .addGap(18, 18, 18)
                .addComponent(btnXemChiTietNguyenLieu)
                .addContainerGap())
        );

        panelTimKiemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKiemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemNguyenLieu.setText("Nhập tên nguyên liệu");
        labelTimKiemNguyenLieu.setOpaque(true);

        textFieldTimKiemNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimKiemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimKiemNguyenLieu.setOpaque(true);

        btnTimKiemNguyenLieu.setBackground(new java.awt.Color(0, 51, 255));
        btnTimKiemNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiemNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemNguyenLieu.setText("Tìm");
        btnTimKiemNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimKiemNguyenLieu.setOpaque(true);
        btnTimKiemNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemNguyenLieuMouseClicked(evt);
            }
        });

        btnHuyTimNguyenLieu.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimNguyenLieu.setText("Hủy tìm");
        btnHuyTimNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimNguyenLieu.setOpaque(true);
        btnHuyTimNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimNguyenLieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemNguyenLieuLayout = new javax.swing.GroupLayout(panelTimKiemNguyenLieu);
        panelTimKiemNguyenLieu.setLayout(panelTimKiemNguyenLieuLayout);
        panelTimKiemNguyenLieuLayout.setHorizontalGroup(
            panelTimKiemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimKiemNguyenLieu)
                    .addGroup(panelTimKiemNguyenLieuLayout.createSequentialGroup()
                        .addComponent(textFieldTimKiemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTimKiemNguyenLieuLayout.setVerticalGroup(
            panelTimKiemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimKiemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldTimKiemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTimNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPaneNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        tableDSNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        tableDSNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneNguyenLieu.setViewportView(tableDSNguyenLieu);

        labelDSNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelDSNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelDSNguyenLieu.setForeground(new java.awt.Color(0, 0, 204));
        labelDSNguyenLieu.setText("DANH SÁCH NGUYÊN LIỆU");
        labelDSNguyenLieu.setOpaque(true);

        javax.swing.GroupLayout panelQLNguyenLieuLayout = new javax.swing.GroupLayout(panelQLNguyenLieu);
        panelQLNguyenLieu.setLayout(panelQLNguyenLieuLayout);
        panelQLNguyenLieuLayout.setHorizontalGroup(
            panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNguyenLieuLayout.createSequentialGroup()
                .addGroup(panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQLNguyenLieuLayout.createSequentialGroup()
                        .addGroup(panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelQLNguyenLieuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitleQLNguyenLieu))
                            .addComponent(panelChucNangNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelTimKiemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQLNguyenLieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDSNguyenLieu)
                            .addComponent(scrollPaneNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(537, Short.MAX_VALUE))
        );
        panelQLNguyenLieuLayout.setVerticalGroup(
            panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelQLNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTimKiemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChucNangNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(labelDSNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1468, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNguyenLieuMouseClicked
        // TODO add your handling code here:
        // hiển thị frame thêm nguyên liệu
        this.frameThemNguyenLieu.setSize(600, 550);
        this.frameThemNguyenLieu.setLocationRelativeTo(null);
        this.frameThemNguyenLieu.setVisible(true);
        this.frameThemNguyenLieu.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemNguyenLieuMouseClicked

    private void btnXoaNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNguyenLieuMouseClicked
        // TODO add your handling code here:
        // bấm vào nút này sẽ thực hiện xóa một nguyên liệu
        xoaNguyenLieu(this.tableDSNguyenLieu, this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnXoaNguyenLieuMouseClicked

    private void btnSuaNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hiện ra frame để sửa thông tin nguyen liệu
        int row = this.tableDSNguyenLieu.getSelectedRow();
        if (row >= 0) {
            this.frameSuaNguyenLieu.setSize(600, 550);
            this.frameSuaNguyenLieu.setLocationRelativeTo(null);
            this.frameSuaNguyenLieu.setVisible(true);
            this.frameSuaNguyenLieu.getContentPane().setBackground(Color.WHITE);
            hienThiThongTinNguyenLieuRaFrameSuaNguyenLieu(this.tableDSNguyenLieu, this.defaultTableModelDSNguyenLieu);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyen liệu để sửa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaNguyenLieuMouseClicked

    private void btnXemChiTietNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietNguyenLieuMouseClicked
        // TODO add your handling code here:
        int row = this.tableDSNguyenLieu.getSelectedRow();
        if (row >= 0) {
            this.frameXemNguyenLieu.setSize(600, 550);
            this.frameXemNguyenLieu.setLocationRelativeTo(null);
            this.frameXemNguyenLieu.setVisible(true);
            this.frameXemNguyenLieu.getContentPane().setBackground(Color.WHITE);
            xemThongTinNguyenLieu(this.tableDSNguyenLieu, this.defaultTableModelDSNguyenLieu);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyên liệu để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemChiTietNguyenLieuMouseClicked

    private void btnTimKiemNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemNguyenLieuMouseClicked
        // TODO add your handling code here:
        timKiemNguyenLieuTheoTen(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnTimKiemNguyenLieuMouseClicked

    private void btnHuyTimNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimNguyenLieuMouseClicked
        // TODO add your handling code here:
        this.textFieldTimKiemNguyenLieu.setText("");
        getAllIngredient(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnHuyTimNguyenLieuMouseClicked

    private void btnHoanThanhThemNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hoàn thành việc thêm sản phẩm
        themNguyenLieu(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnHoanThanhThemNguyenLieuMouseClicked

    private void btnHoanThanhSuaNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaNguyenLieuMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này nó sẽ tiến hàng sửa thông tin nguyên liệu
        suaNguyenLieu(this.tableDSNguyenLieu, this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnHoanThanhSuaNguyenLieuMouseClicked

    private void textFieldSuaSoLuongNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaSoLuongNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaSoLuongNguyenLieu.getText().equals("")) {
            this.labelLoiSuaSoLuongNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldSuaSoLuongNguyenLieuKeyPressed

    private void textFieldSuaTenNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaTenNguyenLieu.getText().equals("")) {
            this.labelLoiSuaTenNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenNguyenLieuKeyPressed

    private void textFieldSuaDonViNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaDonViNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaDonViNguyenLieu.getText().equals("")) {
            this.labelLoiSuaDonViNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldSuaDonViNguyenLieuKeyPressed

    private void textFieldThemTenNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenNguyenLieu.getText().equals("")) {
            this.labelLoiThemTenNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenNguyenLieuKeyPressed

    private void textFieldThemDonViNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemDonViNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemDonViNguyenLieu.getText().equals("")) {
            this.labelLoiThemDonViNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldThemDonViNguyenLieuKeyPressed

    private void textFieldThemSoLuongNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemSoLuongNguyenLieuKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemSoLuongNguyenLieu.getText().equals("")) {
            this.labelLoiThemSoLuongNguyenLieu.setText("");
        }
    }//GEN-LAST:event_textFieldThemSoLuongNguyenLieuKeyPressed

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
            java.util.logging.Logger.getLogger(QLNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNguyenLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaNguyenLieu;
    private javax.swing.JButton btnHoanThanhThemNguyenLieu;
    private javax.swing.JButton btnHuyTimNguyenLieu;
    private javax.swing.JButton btnSuaNguyenLieu;
    private javax.swing.JButton btnThemNguyenLieu;
    private javax.swing.JButton btnTimKiemNguyenLieu;
    private javax.swing.JButton btnXemChiTietNguyenLieu;
    private javax.swing.JButton btnXoaNguyenLieu;
    private javax.swing.JFrame frameSuaNguyenLieu;
    private javax.swing.JFrame frameThemNguyenLieu;
    private javax.swing.JFrame frameXemNguyenLieu;
    private javax.swing.JLabel labelDSNguyenLieu;
    private javax.swing.JLabel labelLoiSuaDonViNguyenLieu;
    private javax.swing.JLabel labelLoiSuaSoLuongNguyenLieu;
    private javax.swing.JLabel labelLoiSuaTenNguyenLieu;
    private javax.swing.JLabel labelLoiThemDonViNguyenLieu;
    private javax.swing.JLabel labelLoiThemSoLuongNguyenLieu;
    private javax.swing.JLabel labelLoiThemTenNguyenLieu;
    private javax.swing.JLabel labelSuaDonViNguyenLieu;
    private javax.swing.JLabel labelSuaSoLuongNguyenLieu;
    private javax.swing.JLabel labelSuaTenNguyenLieu;
    private javax.swing.JLabel labelThemSoLuongNguyenLieu;
    private javax.swing.JLabel labelThemTenNguyenLieu;
    private javax.swing.JLabel labelThenDonViNguyenLieu;
    private javax.swing.JLabel labelTimKiemNguyenLieu;
    private javax.swing.JLabel labelTitleQLNguyenLieu;
    private javax.swing.JLabel labelTitleSuaNguyenLieu;
    private javax.swing.JLabel labelTitleThemNguyenLieu;
    private javax.swing.JLabel labelTitleXemNguyenLieu;
    private javax.swing.JLabel labelXemDonViDoNguyenLieu;
    private javax.swing.JLabel labelXemMaNguyenLieu;
    private javax.swing.JLabel labelXemSoLuongNguyenLieu;
    private javax.swing.JLabel labelXemTenNguyenLieu;
    private javax.swing.JPanel panelChucNangNguyenLieu;
    private javax.swing.JPanel panelQLNguyenLieu;
    private javax.swing.JPanel panelSuaNguyenLieu;
    private javax.swing.JPanel panelThemNguyenLieu;
    private javax.swing.JPanel panelTimKiemNguyenLieu;
    private javax.swing.JPanel panelXemNguyenLieu;
    private javax.swing.JScrollPane scrollPaneNguyenLieu;
    private javax.swing.JTable tableDSNguyenLieu;
    private javax.swing.JTextField textFieldSuaDonViNguyenLieu;
    private javax.swing.JTextField textFieldSuaSoLuongNguyenLieu;
    private javax.swing.JTextField textFieldSuaTenNguyenLieu;
    private javax.swing.JTextField textFieldThemDonViNguyenLieu;
    private javax.swing.JTextField textFieldThemSoLuongNguyenLieu;
    private javax.swing.JTextField textFieldThemTenNguyenLieu;
    private javax.swing.JTextField textFieldTimKiemNguyenLieu;
    private javax.swing.JTextField textFieldXemDonViDoNguyenLieu;
    private javax.swing.JTextField textFieldXemMaNguyenLieu;
    private javax.swing.JTextField textFieldXemSoLuongNguyenLieu;
    private javax.swing.JTextField textFieldXemTenNguyenLieu;
    // End of variables declaration//GEN-END:variables
}
