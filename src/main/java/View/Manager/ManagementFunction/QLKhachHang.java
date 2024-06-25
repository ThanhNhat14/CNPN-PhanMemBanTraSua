/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.CustomerDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Customer;

/**
 *
 * @author Admin
 */
public class QLKhachHang extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLKhachHang
     */
    public DefaultTableModel defaultTableModelDSKhachHang;
    public ArrayList<Customer> dsKhachHang;
    public CustomerDAO customerDAO;

    public QLKhachHang() {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.customerDAO = new CustomerDAO();

        // Customer
        this.defaultTableModelDSKhachHang = new DefaultTableModel();
        this.tableDSKhachHang.setModel(this.defaultTableModelDSKhachHang);
        String[] columnsDSKhachHang = {"Mã khách hàng", "Tên khách hàng", "SDT", "Username", "Giới tính"};
        this.defaultTableModelDSKhachHang.setColumnIdentifiers(columnsDSKhachHang);
        JTableHeader headerDSKhachHang = this.tableDSKhachHang.getTableHeader();
        headerDSKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSKhachHang.setBackground(new Color(0x43CD80));
        headerDSKhachHang.setForeground(Color.WHITE);
        headerDSKhachHang.setPreferredSize(new Dimension(headerDSKhachHang.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSKhachHang.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSKhachHang.setRowHeight(30);

        customJScrollpane(this.scrollPaneKhachHang);

        getAllCustomer(this.defaultTableModelDSKhachHang, this.dsKhachHang);

        this.scrollPaneKhachHang.getViewport().setBackground(Color.WHITE);
    }

    public void customJScrollpane(JScrollPane scrollPane) {
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
    }

    //    // ==================== CUSTOMER ====================
    public void xuatDSKhachHangraTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Customer> ds) {
        defaultTableModel.setRowCount(0);
        String gioiTinh = "";
        for (Customer i : ds) {
            if (i.getGender() == 1) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            Object[] data = {i.getIdCustomer(), i.getNameCustomer(), i.getNumberPhoneCustomer(), i.getUserName(), gioiTinh};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllCustomer(DefaultTableModel defaultTableModel, ArrayList<Customer> ds) {
        ds = this.customerDAO.getAllCustomer();
        xuatDSKhachHangraTableTuArrayList(defaultTableModel, ds);
    }

    public Customer layKhachHangKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Customer a = this.customerDAO.getCustomerById(id);
            return a;
        }
        return null;
    }

    public void hienThiThongTinKhachHang(JTable table, DefaultTableModel defaultTableModel) {
        Customer a = layKhachHangKhiClickVaoBang(table, defaultTableModel);
        if (a != null) {
            this.labelXemNoiDungMaKhachHang.setText(a.getIdCustomer() + "");
            this.labelXemNoiDungTenKhachHang.setText(a.getNameCustomer());
            this.labelXemNoiDungSDTKhachHang.setText(a.getNumberPhoneCustomer());
            this.labelXemNoiDungUsernameKhachHang.setText(a.getUserName());
            this.labelXemNoiDungDiaCHiKhachHang.setText(a.getAddressCustomer());
            int gioiTinh = a.getGender();
            switch (gioiTinh) {
                case 0:
                    this.labelXemNoiDungGioiTinhKhachHang.setText("Nữ");
                    break;
                case 1:
                    this.labelXemNoiDungGioiTinhKhachHang.setText("Nam");
                    break;
                default:
                    this.labelXemNoiDungGioiTinhKhachHang.setText("Khác");
                    break;
            }
            this.labelXemNoiDungNgaySinhKhachHang.setText(a.getBirthday());
        }
    }

    public void timKiemKhachHangTheoHoTen(DefaultTableModel defaultTableModel, ArrayList<Customer> ds) {
        ds = this.customerDAO.getAllCustomer();
        String tenKhachHangCanTim = this.textFieldTimKhachHangTheoTen.getText();
        if (tenKhachHangCanTim == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            tenKhachHangCanTim = tenKhachHangCanTim.toLowerCase();
            ArrayList<Customer> kq = this.customerDAO.getCustomersByName(tenKhachHangCanTim);
            xuatDSKhachHangraTableTuArrayList(defaultTableModel, kq);
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

        frameXemKhachHang = new javax.swing.JFrame();
        panelXemKhachHang = new javax.swing.JPanel();
        labelTitleXemKhachHang = new javax.swing.JLabel();
        labelXemIDKhachHang = new javax.swing.JLabel();
        labelXemTenKhachHang = new javax.swing.JLabel();
        labelXemSDTKhachHang = new javax.swing.JLabel();
        labelXemUsernameKhachHang = new javax.swing.JLabel();
        labelXemDiaChiKhachHang = new javax.swing.JLabel();
        labelXemGioiTinhKhachHang = new javax.swing.JLabel();
        labelXemNgaySinhKhachHang = new javax.swing.JLabel();
        labelXemNoiDungNgaySinhKhachHang = new javax.swing.JLabel();
        labelXemNoiDungMaKhachHang = new javax.swing.JLabel();
        labelXemNoiDungTenKhachHang = new javax.swing.JLabel();
        labelXemNoiDungSDTKhachHang = new javax.swing.JLabel();
        labelXemNoiDungUsernameKhachHang = new javax.swing.JLabel();
        labelXemNoiDungGioiTinhKhachHang = new javax.swing.JLabel();
        labelXemNoiDungDiaCHiKhachHang = new javax.swing.JLabel();
        panelQLKhachHang = new javax.swing.JPanel();
        labelTitleQLKhachHang = new javax.swing.JLabel();
        panelTimKiemKhachHang = new javax.swing.JPanel();
        labelTimKhachHangTheoTen = new javax.swing.JLabel();
        textFieldTimKhachHangTheoTen = new javax.swing.JTextField();
        btnTimKiemKhachHang = new javax.swing.JButton();
        btnHuyTimKhachHang = new javax.swing.JButton();
        btnXemChiTietKhachHang = new javax.swing.JButton();
        scrollPaneKhachHang = new javax.swing.JScrollPane();
        tableDSKhachHang = new javax.swing.JTable();

        frameXemKhachHang.setTitle("Thông tin khách hàng");

        panelXemKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemKhachHang.setBackground(new java.awt.Color(16, 185, 129));
        labelTitleXemKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleXemKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemKhachHang.setText("THÔNG TIN KHÁCH HÀNG");
        labelTitleXemKhachHang.setOpaque(true);

        labelXemIDKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemIDKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemIDKhachHang.setText("Mã khách hàng");
        labelXemIDKhachHang.setOpaque(true);

        labelXemTenKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenKhachHang.setText("Tên khách hàng");
        labelXemTenKhachHang.setOpaque(true);

        labelXemSDTKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemSDTKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemSDTKhachHang.setText("Số điện thoại");
        labelXemSDTKhachHang.setOpaque(true);

        labelXemUsernameKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemUsernameKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemUsernameKhachHang.setText("Username");
        labelXemUsernameKhachHang.setOpaque(true);

        labelXemDiaChiKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemDiaChiKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemDiaChiKhachHang.setText("Địa chỉ");
        labelXemDiaChiKhachHang.setOpaque(true);

        labelXemGioiTinhKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemGioiTinhKhachHang.setText("Giới tính");

        labelXemNgaySinhKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNgaySinhKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNgaySinhKhachHang.setText("Ngày sinh");
        labelXemNgaySinhKhachHang.setOpaque(true);

        labelXemNoiDungNgaySinhKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungNgaySinhKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNgaySinhKhachHang.setText("01/01/2001");
        labelXemNoiDungNgaySinhKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungNgaySinhKhachHang.setOpaque(true);

        labelXemNoiDungMaKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungMaKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaKhachHang.setText("0");
        labelXemNoiDungMaKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungMaKhachHang.setOpaque(true);

        labelXemNoiDungTenKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungTenKhachHang.setText("Khách hàng 1");
        labelXemNoiDungTenKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungTenKhachHang.setOpaque(true);

        labelXemNoiDungSDTKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungSDTKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungSDTKhachHang.setText("01234566789");
        labelXemNoiDungSDTKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungSDTKhachHang.setOpaque(true);

        labelXemNoiDungUsernameKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungUsernameKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungUsernameKhachHang.setText("khachhang1");
        labelXemNoiDungUsernameKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungUsernameKhachHang.setOpaque(true);

        labelXemNoiDungGioiTinhKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungGioiTinhKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungGioiTinhKhachHang.setText("Nam");
        labelXemNoiDungGioiTinhKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungGioiTinhKhachHang.setOpaque(true);

        labelXemNoiDungDiaCHiKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungDiaCHiKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungDiaCHiKhachHang.setText("97 Man Thiện, Phường Hiệp Phú, TP HCM");
        labelXemNoiDungDiaCHiKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungDiaCHiKhachHang.setOpaque(true);

        javax.swing.GroupLayout panelXemKhachHangLayout = new javax.swing.GroupLayout(panelXemKhachHang);
        panelXemKhachHang.setLayout(panelXemKhachHangLayout);
        panelXemKhachHangLayout.setHorizontalGroup(
            panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelXemNoiDungDiaCHiKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTitleXemKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                        .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelXemIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelXemNoiDungMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                                .addComponent(labelXemTenKhachHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE))
                            .addComponent(labelXemNoiDungTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                        .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelXemUsernameKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelXemNoiDungUsernameKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelXemGioiTinhKhachHang)
                                    .addComponent(labelXemNoiDungGioiTinhKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(labelXemDiaChiKhachHang)
                            .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                                        .addComponent(labelXemSDTKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                        .addGap(98, 98, 98))
                                    .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                                        .addComponent(labelXemNoiDungSDTKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(63, 63, 63)))
                                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelXemNgaySinhKhachHang)
                                    .addComponent(labelXemNoiDungNgaySinhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelXemKhachHangLayout.setVerticalGroup(
            panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleXemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemIDKhachHang)
                    .addComponent(labelXemTenKhachHang))
                .addGap(18, 18, 18)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelXemNoiDungMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(labelXemNoiDungTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemSDTKhachHang)
                    .addComponent(labelXemNgaySinhKhachHang))
                .addGap(18, 18, 18)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemNoiDungSDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelXemNoiDungNgaySinhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemUsernameKhachHang)
                    .addComponent(labelXemGioiTinhKhachHang))
                .addGap(18, 18, 18)
                .addGroup(panelXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelXemNoiDungGioiTinhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelXemNoiDungUsernameKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelXemDiaChiKhachHang)
                .addGap(18, 18, 18)
                .addComponent(labelXemNoiDungDiaCHiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameXemKhachHangLayout = new javax.swing.GroupLayout(frameXemKhachHang.getContentPane());
        frameXemKhachHang.getContentPane().setLayout(frameXemKhachHangLayout);
        frameXemKhachHangLayout.setHorizontalGroup(
            frameXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemKhachHangLayout.setVerticalGroup(
            frameXemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLKhachHang.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleQLKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLKhachHang.setText("QUẢN LÝ KHÁCH HÀNG");
        labelTitleQLKhachHang.setOpaque(true);

        panelTimKiemKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKhachHangTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKhachHangTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKhachHangTheoTen.setText("Nhập tên khách hàng");
        labelTimKhachHangTheoTen.setOpaque(true);

        textFieldTimKhachHangTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimKhachHangTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimKhachHangTheoTen.setOpaque(true);

        btnTimKiemKhachHang.setBackground(new java.awt.Color(0, 51, 204));
        btnTimKiemKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemKhachHang.setText("Tìm");
        btnTimKiemKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimKiemKhachHang.setOpaque(true);
        btnTimKiemKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemKhachHangMouseClicked(evt);
            }
        });

        btnHuyTimKhachHang.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimKhachHang.setText("Hủy tìm");
        btnHuyTimKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimKhachHang.setOpaque(true);
        btnHuyTimKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimKhachHangMouseClicked(evt);
            }
        });

        btnXemChiTietKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietKhachHang.setForeground(new java.awt.Color(153, 0, 153));
        btnXemChiTietKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_xemChiTiet_20px.png"))); // NOI18N
        btnXemChiTietKhachHang.setText("CHI TIẾT");
        btnXemChiTietKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemKhachHangLayout = new javax.swing.GroupLayout(panelTimKiemKhachHang);
        panelTimKiemKhachHang.setLayout(panelTimKiemKhachHangLayout);
        panelTimKiemKhachHangLayout.setHorizontalGroup(
            panelTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimKhachHangTheoTen)
                    .addGroup(panelTimKiemKhachHangLayout.createSequentialGroup()
                        .addComponent(textFieldTimKhachHangTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTietKhachHang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTimKiemKhachHangLayout.setVerticalGroup(
            panelTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimKhachHangTheoTen)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldTimKhachHangTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemChiTietKhachHang))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tableDSKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        tableDSKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPaneKhachHang.setViewportView(tableDSKhachHang);

        javax.swing.GroupLayout panelQLKhachHangLayout = new javax.swing.GroupLayout(panelQLKhachHang);
        panelQLKhachHang.setLayout(panelQLKhachHangLayout);
        panelQLKhachHangLayout.setHorizontalGroup(
            panelQLKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleQLKhachHang)
                    .addComponent(panelTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        panelQLKhachHangLayout.setVerticalGroup(
            panelQLKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLKhachHang)
                .addGap(18, 18, 18)
                .addComponent(panelTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelQLKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemKhachHangMouseClicked
        // TODO add your handling code here:
        timKiemKhachHangTheoHoTen(this.defaultTableModelDSKhachHang, this.dsKhachHang);
    }//GEN-LAST:event_btnTimKiemKhachHangMouseClicked

    private void btnHuyTimKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimKhachHangMouseClicked
        // TODO add your handling code here:
        getAllCustomer(this.defaultTableModelDSKhachHang, this.dsKhachHang);
        this.textFieldTimKhachHangTheoTen.setText("");
    }//GEN-LAST:event_btnHuyTimKhachHangMouseClicked

    private void btnXemChiTietKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietKhachHangMouseClicked
        // TODO add your handling code here:
        // hàm này sẽ hiển thị thông tin khách hàng vào frame xem khách hàng
        int row = this.tableDSKhachHang.getSelectedRow();
        if (row >= 0) {
            this.frameXemKhachHang.setSize(700, 600);
            this.frameXemKhachHang.setLocationRelativeTo(null);
            this.frameXemKhachHang.setVisible(true);
            this.frameXemKhachHang.getContentPane().setBackground(Color.WHITE);
            hienThiThongTinKhachHang(this.tableDSKhachHang, this.defaultTableModelDSKhachHang);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để xem.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemChiTietKhachHangMouseClicked

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
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyTimKhachHang;
    private javax.swing.JButton btnTimKiemKhachHang;
    private javax.swing.JButton btnXemChiTietKhachHang;
    private javax.swing.JFrame frameXemKhachHang;
    private javax.swing.JLabel labelTimKhachHangTheoTen;
    private javax.swing.JLabel labelTitleQLKhachHang;
    private javax.swing.JLabel labelTitleXemKhachHang;
    private javax.swing.JLabel labelXemDiaChiKhachHang;
    private javax.swing.JLabel labelXemGioiTinhKhachHang;
    private javax.swing.JLabel labelXemIDKhachHang;
    private javax.swing.JLabel labelXemNgaySinhKhachHang;
    private javax.swing.JLabel labelXemNoiDungDiaCHiKhachHang;
    private javax.swing.JLabel labelXemNoiDungGioiTinhKhachHang;
    private javax.swing.JLabel labelXemNoiDungMaKhachHang;
    private javax.swing.JLabel labelXemNoiDungNgaySinhKhachHang;
    private javax.swing.JLabel labelXemNoiDungSDTKhachHang;
    private javax.swing.JLabel labelXemNoiDungTenKhachHang;
    private javax.swing.JLabel labelXemNoiDungUsernameKhachHang;
    private javax.swing.JLabel labelXemSDTKhachHang;
    private javax.swing.JLabel labelXemTenKhachHang;
    private javax.swing.JLabel labelXemUsernameKhachHang;
    private javax.swing.JPanel panelQLKhachHang;
    private javax.swing.JPanel panelTimKiemKhachHang;
    private javax.swing.JPanel panelXemKhachHang;
    private javax.swing.JScrollPane scrollPaneKhachHang;
    private javax.swing.JTable tableDSKhachHang;
    private javax.swing.JTextField textFieldTimKhachHangTheoTen;
    // End of variables declaration//GEN-END:variables
}
