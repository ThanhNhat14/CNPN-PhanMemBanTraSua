/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.ToppingDAO;
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
import Model.Manager.Topping;
import Utils.Manager.FormatMoney;
import Utils.Manager.IsPositiveIntegerManager;

/**
 *
 * @author Admin
 */
public class QLTopping extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLTopping
     */
    public DefaultTableModel defaultTableModelDSTopping;
    public ArrayList<Topping> dsTopping;
    public ToppingDAO toppingDAO;

    public String anhThemTopping;
    public String anhSuaTopping;

    public QLTopping() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        initComponents();

        this.toppingDAO = new ToppingDAO();
        this.dsTopping = new ArrayList<>();

        // Topping
        this.defaultTableModelDSTopping = new DefaultTableModel();
        this.tableDSTopping.setModel(this.defaultTableModelDSTopping);
        String[] columnsDSTopping = {"Mã topping", "Tên topping", "Giá"};
        this.defaultTableModelDSTopping.setColumnIdentifiers(columnsDSTopping);
        JTableHeader headerDSTopping = this.tableDSTopping.getTableHeader();
        headerDSTopping.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSTopping.setBackground(new Color(0x43CD80));
        headerDSTopping.setForeground(Color.WHITE);
        headerDSTopping.setPreferredSize(new Dimension(headerDSTopping.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSTopping.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSTopping.setRowHeight(30);
        this.tableDSTopping.getColumnModel().getColumn(0).setPreferredWidth(90);
        this.tableDSTopping.getColumnModel().getColumn(1).setPreferredWidth(210);
        this.tableDSTopping.getColumnModel().getColumn(2).setPreferredWidth(150);

        this.scrollpaneDSTopping.getViewport().setBackground(Color.WHITE);

        getAllTopping(this.defaultTableModelDSTopping, this.dsTopping);
    }

    // ==================== TOPPING ====================
    public void xuatDSToppingRaTableTuDatabase(DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        defaultTableModel.setRowCount(0);
        for (Topping i : ds) {
            Object[] data = {i.getIdTopping(), i.getNameTopping(), FormatMoney.formatMoney(i.getPriceTopping())};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllTopping(DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        ds = this.toppingDAO.getAllTopping();
        xuatDSToppingRaTableTuDatabase(defaultTableModel, ds);
    }

//    public void themAnhDaiDienTopping() {
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
//                this.anhThemTopping = relativePath;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemImageTopping.getWidth(), this.labelThemImageTopping.getHeight(), Image.SCALE_SMOOTH));
//                this.labelThemImageTopping.setIcon(imageIcon);
//            }
//        }
//    }
    public void themAnhDaiDienTopping() {
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.anhThemTopping = relativePath;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemImageTopping.getWidth(), this.labelThemImageTopping.getHeight(), Image.SCALE_SMOOTH));
                this.labelThemImageTopping.setIcon(imageIcon);
            }
        }
    }

    public void resetFrameThemTopping() {
        this.textFieldThemTenTopping.setText("");
        this.textFieldThemGiaTopping.setText("");
        this.anhThemTopping = "";
    }

    public void themTopping(DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        String thongBaoLoi = "";
        if (this.textFieldThemTenTopping.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập tên topping\n";
            this.labelLoiThemTenTopping.setText("Vui lòng nhập tên topping");
        }
        if (this.textFieldThemGiaTopping.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập giá topping\n";
            this.labelLoiThemGiaTopping.setText("Vui lòng nhập giá topping.");
        } else {
            if (!IsPositiveIntegerManager.isPositiveInteger(this.textFieldThemGiaTopping.getText())) {
                thongBaoLoi += "Giá topping phải là một số nguyên dương\n";
                this.labelLoiThemGiaTopping.setText("Giá topping phải là một số nguyên dương");
            }
        }
        if (thongBaoLoi.equals("")) {
            String tenTopping = this.textFieldThemTenTopping.getText();
            int gia = Integer.parseInt(this.textFieldThemGiaTopping.getText() + "");
            String image = this.anhThemTopping;
            boolean statusTopping = true;
            Topping a = new Topping(tenTopping, gia, image, statusTopping);
            boolean ktTrungTopping = this.toppingDAO.isExistToppingByName(tenTopping);
            if (!ktTrungTopping) {
                this.toppingDAO.insertTopping(a);
                getAllTopping(defaultTableModel, ds);
                resetFrameThemTopping();
                this.frameThemTopping.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Tên topping đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Topping layToppingKhiClickVaoBangTopping(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Topping a = this.toppingDAO.getToppingById(id);
            return a;
        } else {
            return null;
        }
    }

    public void xoaTopping(JTable table, DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Topping a = this.toppingDAO.getToppingById(id);
            if (a != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa topping này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    this.toppingDAO.deleteTopping(id);
                    getAllTopping(defaultTableModel, ds);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để xóa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public void hienThiThongTinToppingVaoFrameSuaTopping(JTable table, DefaultTableModel defaultTableModel) {
//        int row = table.getSelectedRow();
//        if (row >= 0) {
//            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
//            Topping a = this.toppingDAO.getToppingById(id);
//            if (a != null) {
//                this.textFieldSuaTenTopping.setText(a.getNameTopping());
//                this.textFieldSuaGiaTopping.setText(a.getPriceTopping() + "");
//                if (a.getImageTopping() != null) {
//                    String urlImage = a.getImageTopping();
////            ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelXemAnhSanPham.getWidth(), this.labelXemAnhSanPham.getHeight(), Image.SCALE_SMOOTH));
////            this.labelXemAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
////                    String urlImage = "E:\\NhapMonCongNghePhanMem\\Code\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + a.getImageTopping();
////                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelSuaImageTopping.getWidth(), this.labelSuaImageTopping.getHeight(), Image.SCALE_SMOOTH));
//                    this.labelSuaImageTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
//                } else {
//                    this.labelSuaImageTopping.setIcon(null);
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public void resetFrameSuaTopping() {
        this.labelLoiSuaTenTopping.setText("");
        this.labelLoiSuaGiaTopping.setText("");
    }

    public void hienThiThongTinToppingVaoFrameSuaTopping(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Topping a = this.toppingDAO.getToppingById(id);
            if (a != null) {
                resetFrameSuaTopping();
                this.textFieldSuaTenTopping.setText(a.getNameTopping());
                this.textFieldSuaGiaTopping.setText(a.getPriceTopping() + "");
                if (a.getImageTopping() != null) {
                    String urlImage = a.getImageTopping();
                    this.labelSuaImageTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
                } else {
                    this.labelSuaImageTopping.setIcon(null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public void suaAnhDaiDienTopping() {
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
//                this.anhSuaTopping = relativePath;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaImageTopping.getWidth(), this.labelSuaImageTopping.getHeight(), Image.SCALE_SMOOTH));
//                this.labelSuaImageTopping.setIcon(imageIcon);
//            }
//        }
//    }
    public void suaAnhDaiDienTopping() {
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.anhSuaTopping = relativePath;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaImageTopping.getWidth(), this.labelSuaImageTopping.getHeight(), Image.SCALE_SMOOTH));
                this.labelSuaImageTopping.setIcon(imageIcon);
            }
        }
    }

    public void suaTopping(JTable table, DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Topping a = this.toppingDAO.getToppingById(id);
            if (a != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa topping này không");
                if (confirm == JOptionPane.OK_OPTION) {
                    String thongBaoLoi = "";
                    if (this.textFieldSuaTenTopping.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập tên topping\n";
                        this.labelLoiSuaTenTopping.setText("Vui lòng nhập tên topping");
                    }
                    if (this.textFieldSuaGiaTopping.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập giá topping\n";
                        this.labelLoiSuaGiaTopping.setText("Vui lòng nhập giá topping.");
                    } else {
                        if (!IsPositiveIntegerManager.isPositiveInteger(this.textFieldSuaGiaTopping.getText())) {
                            thongBaoLoi += "Giá topping phải là một số nguyên dương\n";
                            this.labelLoiSuaGiaTopping.setText("Giá topping phải là một số nguyên dương");
                        }
                    }
                    if (thongBaoLoi.equals("")) {
                        String tenTopping = this.textFieldSuaTenTopping.getText();
                        int gia = Integer.parseInt(this.textFieldSuaGiaTopping.getText() + "");
                        String image = this.anhSuaTopping;
                        boolean statusTopping = true;
                        Topping b = new Topping(tenTopping, gia, image, statusTopping);
                        boolean ktTrungTopping = this.toppingDAO.isExistToppingByName(tenTopping);
                        if (a.getNameTopping().equals(tenTopping)) {
                            ktTrungTopping = false;
                        }
                        if (!ktTrungTopping) {
                            this.toppingDAO.updateTopping(id, b);
                            getAllTopping(defaultTableModel, ds);
                            this.frameSuaTopping.dispose();
                            this.anhSuaTopping = "";
                        } else {
                            JOptionPane.showMessageDialog(null, "Tên topping đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để xóa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void xemTopping(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Topping a = this.toppingDAO.getToppingById(id);
            if (a != null) {
                this.labelXemNoiDungMaTopping.setText(a.getIdTopping() + "");
                this.labelXemNoiDungTenTopping.setText(a.getNameTopping());
                this.labelXemNoiDungGiaTopping.setText(FormatMoney.formatMoney(a.getPriceTopping()));
//                if (a.getImageTopping() != null) {
//                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(a.getImageTopping()).getImage().getScaledInstance(this.labelNoiDungChiTietAnhTopping.getWidth(), this.labelNoiDungChiTietAnhTopping.getHeight(), Image.SCALE_SMOOTH));
//                    this.labelNoiDungChiTietAnhTopping.setIcon(imageIcon);
//                } else {
//                    this.labelNoiDungChiTietAnhTopping.setIcon(null);
//                }

                if (a.getImageTopping() != null) {
                    String urlImage = a.getImageTopping();
                    // ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelNoiDungChiTietAnhTopping.getWidth(), this.labelNoiDungChiTietAnhTopping.getHeight(), Image.SCALE_SMOOTH));
                    this.labelNoiDungChiTietAnhTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
                } else {
                    this.labelNoiDungChiTietAnhTopping.setIcon(null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để sửa.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timToppingTheoTen(DefaultTableModel defaultTableModel, ArrayList<Topping> ds) {
        String tenCanTim = this.textFieldTimToppingTheoTen.getText();
        if (tenCanTim.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên topping cần tìm.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<Topping> kq = this.toppingDAO.findToppingByName(tenCanTim);
            if (!kq.isEmpty()) {
                xuatDSToppingRaTableTuDatabase(defaultTableModel, kq);
            } else {
                JOptionPane.showMessageDialog(null, "Không có topping cần tìm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameThemTopping = new javax.swing.JFrame();
        panelThemTopping = new javax.swing.JPanel();
        labelTitleThemTopping = new javax.swing.JLabel();
        labelThemTenTopping = new javax.swing.JLabel();
        textFieldThemTenTopping = new javax.swing.JTextField();
        labelLoiThemTenTopping = new javax.swing.JLabel();
        labelThemGiaTopping = new javax.swing.JLabel();
        textFieldThemGiaTopping = new javax.swing.JTextField();
        labelLoiThemGiaTopping = new javax.swing.JLabel();
        labelThemImageTopping = new javax.swing.JLabel();
        btnThemAnhTopping = new javax.swing.JButton();
        btnHoanThanhThemTopping = new javax.swing.JButton();
        frameSuaTopping = new javax.swing.JFrame();
        panelSuaTopping = new javax.swing.JPanel();
        labelTitleSuaTopping = new javax.swing.JLabel();
        labelSuaImageTopping = new javax.swing.JLabel();
        labelLoiSuaTenTopping = new javax.swing.JLabel();
        textFieldSuaTenTopping = new javax.swing.JTextField();
        labelSuaTenTopping = new javax.swing.JLabel();
        labelSuaGiaTopping = new javax.swing.JLabel();
        labelLoiSuaGiaTopping = new javax.swing.JLabel();
        btnSuaAnhTopping = new javax.swing.JButton();
        textFieldSuaGiaTopping = new javax.swing.JTextField();
        btnHoanThanhSuaTopping = new javax.swing.JButton();
        frameXemTopping = new javax.swing.JFrame();
        panelXemTopping = new javax.swing.JPanel();
        labelTileXemTopping = new javax.swing.JLabel();
        labelXemIDTopping = new javax.swing.JLabel();
        textFieldXemMaTopping = new javax.swing.JTextField();
        labelXemTenTopping = new javax.swing.JLabel();
        textFieldXemTenTopping = new javax.swing.JTextField();
        labelXemGiaTopping = new javax.swing.JLabel();
        textFieldXemGiaTopping = new javax.swing.JTextField();
        labelXemMoTaTopping = new javax.swing.JLabel();
        scrollPaneXemMoTaTopping = new javax.swing.JScrollPane();
        textAreaXemMoTaTopping = new javax.swing.JTextArea();
        labelXemAnhTopping = new javax.swing.JLabel();
        panelQLToppingContent = new javax.swing.JPanel();
        labelTitleQLTopping = new javax.swing.JLabel();
        panelChucNangTopping = new javax.swing.JPanel();
        btnThemTopping = new javax.swing.JButton();
        btnXoaTopping = new javax.swing.JButton();
        btnSuaTopping = new javax.swing.JButton();
        panelTimKiemTopping = new javax.swing.JPanel();
        lbaelTimKiemToppingTheoTen = new javax.swing.JLabel();
        textFieldTimToppingTheoTen = new javax.swing.JTextField();
        btnTimTopping = new javax.swing.JButton();
        btnHuyTimTopping = new javax.swing.JButton();
        labelDSTopping = new javax.swing.JLabel();
        scrollpaneDSTopping = new javax.swing.JScrollPane();
        tableDSTopping = new javax.swing.JTable();
        labelChucNangTopping = new javax.swing.JLabel();
        labelTitleTimKiemBtn = new javax.swing.JLabel();
        panelXemChiTietTopping = new javax.swing.JPanel();
        labelNoiDungChiTietAnhTopping = new javax.swing.JLabel();
        labelXemMaTopping = new javax.swing.JLabel();
        labelXemNoiDungMaTopping = new javax.swing.JLabel();
        labelXemChiTietTenTopping = new javax.swing.JLabel();
        labelXemNoiDungTenTopping = new javax.swing.JLabel();
        labelXemChiTietGiaTopping = new javax.swing.JLabel();
        labelXemNoiDungGiaTopping = new javax.swing.JLabel();

        frameThemTopping.setTitle("Thêm topping");

        panelThemTopping.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleThemTopping.setBackground(new java.awt.Color(16, 185, 129));
        labelTitleThemTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemTopping.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemTopping.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemTopping.setText("THÊM TOPPING");
        labelTitleThemTopping.setOpaque(true);

        labelThemTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenTopping.setText("Tên topping");
        labelThemTenTopping.setOpaque(true);

        textFieldThemTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemTenTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        textFieldThemTenTopping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemTenToppingKeyPressed(evt);
            }
        });

        labelLoiThemTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemTenTopping.setForeground(new java.awt.Color(255, 0, 0));

        labelThemGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelThemGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemGiaTopping.setText("Giá");
        labelThemGiaTopping.setOpaque(true);

        textFieldThemGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemGiaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 205, 128)));
        textFieldThemGiaTopping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemGiaToppingKeyPressed(evt);
            }
        });

        labelLoiThemGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemGiaTopping.setForeground(new java.awt.Color(255, 0, 0));

        labelThemImageTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelThemImageTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        labelThemImageTopping.setOpaque(true);

        btnThemAnhTopping.setBackground(new java.awt.Color(255, 255, 255));
        btnThemAnhTopping.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemAnhTopping.setText("Ảnh");
        btnThemAnhTopping.setOpaque(true);
        btnThemAnhTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemAnhToppingMouseClicked(evt);
            }
        });

        btnHoanThanhThemTopping.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemTopping.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemTopping.setText("THÊM");
        btnHoanThanhThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHoanThanhThemTopping.setOpaque(true);
        btnHoanThanhThemTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemToppingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemToppingLayout = new javax.swing.GroupLayout(panelThemTopping);
        panelThemTopping.setLayout(panelThemToppingLayout);
        panelThemToppingLayout.setHorizontalGroup(
            panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleThemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThemToppingLayout.createSequentialGroup()
                        .addGroup(panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelThemTenTopping)
                            .addComponent(textFieldThemTenTopping)
                            .addComponent(labelLoiThemTenTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelThemGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldThemGiaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(labelLoiThemGiaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemAnhTopping)
                            .addComponent(labelThemImageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)))
                .addContainerGap())
            .addGroup(panelThemToppingLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(btnHoanThanhThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThemToppingLayout.setVerticalGroup(
            panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(panelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelThemToppingLayout.createSequentialGroup()
                        .addComponent(labelThemTenTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldThemTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(labelLoiThemTenTopping)
                        .addGap(18, 18, 18)
                        .addComponent(labelThemGiaTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldThemGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiThemGiaTopping))
                    .addComponent(labelThemImageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemAnhTopping)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnHoanThanhThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout frameThemToppingLayout = new javax.swing.GroupLayout(frameThemTopping.getContentPane());
        frameThemTopping.getContentPane().setLayout(frameThemToppingLayout);
        frameThemToppingLayout.setHorizontalGroup(
            frameThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameThemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameThemToppingLayout.setVerticalGroup(
            frameThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelThemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameSuaTopping.setTitle("Thay đổi thông tin Topping");

        panelSuaTopping.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaTopping.setBackground(new java.awt.Color(255, 152, 0));
        labelTitleSuaTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaTopping.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaTopping.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaTopping.setText("THAY ĐỔI THÔNG TIN TOPPING");
        labelTitleSuaTopping.setOpaque(true);

        labelSuaImageTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaImageTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelSuaImageTopping.setOpaque(true);

        labelLoiSuaTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaTenTopping.setForeground(new java.awt.Color(255, 0, 0));

        textFieldSuaTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaTenTopping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaTenToppingKeyPressed(evt);
            }
        });

        labelSuaTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaTenTopping.setText("Tên topping");
        labelSuaTenTopping.setOpaque(true);

        labelSuaGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaGiaTopping.setText("Giá");
        labelSuaGiaTopping.setOpaque(true);

        labelLoiSuaGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaGiaTopping.setForeground(new java.awt.Color(255, 0, 0));

        btnSuaAnhTopping.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaAnhTopping.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaAnhTopping.setText("Ảnh");
        btnSuaAnhTopping.setOpaque(true);
        btnSuaAnhTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaAnhToppingMouseClicked(evt);
            }
        });

        textFieldSuaGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldSuaGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaGiaTopping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaGiaToppingKeyPressed(evt);
            }
        });

        btnHoanThanhSuaTopping.setBackground(new java.awt.Color(255, 152, 0));
        btnHoanThanhSuaTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaTopping.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaTopping.setText("THAY ĐỔI");
        btnHoanThanhSuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHoanThanhSuaTopping.setOpaque(true);
        btnHoanThanhSuaTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaToppingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuaToppingLayout = new javax.swing.GroupLayout(panelSuaTopping);
        panelSuaTopping.setLayout(panelSuaToppingLayout);
        panelSuaToppingLayout.setHorizontalGroup(
            panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaToppingLayout.createSequentialGroup()
                .addGroup(panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSuaToppingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleSuaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSuaToppingLayout.createSequentialGroup()
                        .addGroup(panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSuaToppingLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelSuaGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldSuaGiaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                    .addComponent(labelSuaTenTopping)
                                    .addComponent(textFieldSuaTenTopping)
                                    .addComponent(labelLoiSuaGiaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelLoiSuaTenTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(79, 79, 79)
                                .addGroup(panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSuaAnhTopping)
                                    .addComponent(labelSuaImageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelSuaToppingLayout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addComponent(btnHoanThanhSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSuaToppingLayout.setVerticalGroup(
            panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSuaToppingLayout.createSequentialGroup()
                        .addComponent(labelSuaTenTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldSuaTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(labelLoiSuaTenTopping)
                        .addGap(18, 18, 18)
                        .addComponent(labelSuaGiaTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldSuaGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(labelLoiSuaGiaTopping)
                        .addGap(46, 46, 46))
                    .addGroup(panelSuaToppingLayout.createSequentialGroup()
                        .addComponent(labelSuaImageTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(btnSuaAnhTopping)
                .addGap(41, 41, 41)
                .addComponent(btnHoanThanhSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout frameSuaToppingLayout = new javax.swing.GroupLayout(frameSuaTopping.getContentPane());
        frameSuaTopping.getContentPane().setLayout(frameSuaToppingLayout);
        frameSuaToppingLayout.setHorizontalGroup(
            frameSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameSuaToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameSuaToppingLayout.setVerticalGroup(
            frameSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        frameXemTopping.setTitle("THÔNG TIN TOPPING");

        panelXemTopping.setBackground(new java.awt.Color(255, 255, 255));

        labelTileXemTopping.setBackground(new java.awt.Color(204, 0, 204));
        labelTileXemTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTileXemTopping.setForeground(new java.awt.Color(255, 255, 255));
        labelTileXemTopping.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTileXemTopping.setText("THÔNG TIN TOPPING");
        labelTileXemTopping.setOpaque(true);

        labelXemIDTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemIDTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemIDTopping.setText("Mã topping");
        labelXemIDTopping.setOpaque(true);

        textFieldXemMaTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemMaTopping.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        textFieldXemMaTopping.setOpaque(true);

        labelXemTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTenTopping.setText("Tên topping");
        labelXemTenTopping.setOpaque(true);

        textFieldXemTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelXemGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemGiaTopping.setText("Giá");
        labelXemGiaTopping.setOpaque(true);

        textFieldXemGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelXemMoTaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMoTaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMoTaTopping.setText("Mô tả");
        labelXemMoTaTopping.setOpaque(true);

        scrollPaneXemMoTaTopping.setBackground(new java.awt.Color(255, 255, 255));

        textAreaXemMoTaTopping.setBackground(new java.awt.Color(255, 255, 255));
        textAreaXemMoTaTopping.setColumns(20);
        textAreaXemMoTaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textAreaXemMoTaTopping.setRows(5);
        scrollPaneXemMoTaTopping.setViewportView(textAreaXemMoTaTopping);

        labelXemAnhTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 204)));
        labelXemAnhTopping.setOpaque(true);

        javax.swing.GroupLayout panelXemToppingLayout = new javax.swing.GroupLayout(panelXemTopping);
        panelXemTopping.setLayout(panelXemToppingLayout);
        panelXemToppingLayout.setHorizontalGroup(
            panelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTileXemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelXemToppingLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelXemGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelXemIDTopping)
                    .addComponent(labelXemTenTopping)
                    .addComponent(labelXemMoTaTopping)
                    .addComponent(textFieldXemMaTopping)
                    .addComponent(textFieldXemTenTopping)
                    .addComponent(textFieldXemGiaTopping)
                    .addComponent(scrollPaneXemMoTaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(labelXemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        panelXemToppingLayout.setVerticalGroup(
            panelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTileXemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemToppingLayout.createSequentialGroup()
                        .addComponent(labelXemIDTopping)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldXemMaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemTenTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemGiaTopping)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemToppingLayout.createSequentialGroup()
                        .addComponent(labelXemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addComponent(labelXemMoTaTopping)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneXemMoTaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout frameXemToppingLayout = new javax.swing.GroupLayout(frameXemTopping.getContentPane());
        frameXemTopping.getContentPane().setLayout(frameXemToppingLayout);
        frameXemToppingLayout.setHorizontalGroup(
            frameXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemToppingLayout.setVerticalGroup(
            frameXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLToppingContent.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLTopping.setBackground(new java.awt.Color(248, 248, 255));
        labelTitleQLTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitleQLTopping.setText("QUẢN LÝ TOPPING");
        labelTitleQLTopping.setOpaque(true);

        panelChucNangTopping.setBackground(new java.awt.Color(255, 255, 255));

        btnThemTopping.setBackground(new java.awt.Color(255, 255, 255));
        btnThemTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemTopping.setForeground(new java.awt.Color(0, 153, 51));
        btnThemTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemTopping.setText("THÊM");
        btnThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemTopping.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemToppingMouseClicked(evt);
            }
        });

        btnXoaTopping.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaTopping.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaTopping.setText("XÓA");
        btnXoaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaToppingMouseClicked(evt);
            }
        });

        btnSuaTopping.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaTopping.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaTopping.setText("SỬA");
        btnSuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaToppingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangToppingLayout = new javax.swing.GroupLayout(panelChucNangTopping);
        panelChucNangTopping.setLayout(panelChucNangToppingLayout);
        panelChucNangToppingLayout.setHorizontalGroup(
            panelChucNangToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChucNangToppingLayout.setVerticalGroup(
            panelChucNangToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangToppingLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelChucNangToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelChucNangToppingLayout.createSequentialGroup()
                        .addGroup(panelChucNangToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(btnXoaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelTimKiemTopping.setBackground(new java.awt.Color(255, 255, 255));

        lbaelTimKiemToppingTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        lbaelTimKiemToppingTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbaelTimKiemToppingTheoTen.setText("Nhập tên topping");
        lbaelTimKiemToppingTheoTen.setOpaque(true);

        textFieldTimToppingTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimToppingTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldTimToppingTheoTen.setOpaque(true);

        btnTimTopping.setBackground(new java.awt.Color(0, 51, 204));
        btnTimTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimTopping.setForeground(new java.awt.Color(255, 255, 255));
        btnTimTopping.setText("Tìm");
        btnTimTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimTopping.setOpaque(true);
        btnTimTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimToppingMouseClicked(evt);
            }
        });

        btnHuyTimTopping.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimTopping.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimTopping.setText("Hủy tìm");
        btnHuyTimTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimTopping.setOpaque(true);
        btnHuyTimTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimToppingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemToppingLayout = new javax.swing.GroupLayout(panelTimKiemTopping);
        panelTimKiemTopping.setLayout(panelTimKiemToppingLayout);
        panelTimKiemToppingLayout.setHorizontalGroup(
            panelTimKiemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbaelTimKiemToppingTheoTen)
                .addGap(18, 18, 18)
                .addComponent(textFieldTimToppingTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuyTimTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        panelTimKiemToppingLayout.setVerticalGroup(
            panelTimKiemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTimKiemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldTimToppingTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuyTimTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbaelTimKiemToppingTheoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        labelDSTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelDSTopping.setForeground(new java.awt.Color(0, 51, 255));
        labelDSTopping.setText("DANH SÁCH TOPPING");

        tableDSTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSTopping.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSTopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSToppingMouseClicked(evt);
            }
        });
        scrollpaneDSTopping.setViewportView(tableDSTopping);

        labelChucNangTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelChucNangTopping.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelChucNangTopping.setForeground(new java.awt.Color(204, 0, 204));
        labelChucNangTopping.setText("Chức năng");
        labelChucNangTopping.setOpaque(true);

        labelTitleTimKiemBtn.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleTimKiemBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleTimKiemBtn.setForeground(new java.awt.Color(204, 0, 204));
        labelTitleTimKiemBtn.setText("Tìm kiếm");
        labelTitleTimKiemBtn.setOpaque(true);

        panelXemChiTietTopping.setBackground(new java.awt.Color(255, 255, 255));

        labelNoiDungChiTietAnhTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelNoiDungChiTietAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelNoiDungChiTietAnhTopping.setOpaque(true);

        labelXemMaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaTopping.setText("Mã topping");
        labelXemMaTopping.setOpaque(true);

        labelXemNoiDungMaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungMaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaTopping.setText("0");
        labelXemNoiDungMaTopping.setOpaque(true);

        labelXemChiTietTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemChiTietTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietTenTopping.setText("Tên topping");
        labelXemChiTietTenTopping.setOpaque(true);

        labelXemNoiDungTenTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungTenTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungTenTopping.setText("Topping 1");
        labelXemNoiDungTenTopping.setOpaque(true);

        labelXemChiTietGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemChiTietGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietGiaTopping.setText("Giá");
        labelXemChiTietGiaTopping.setOpaque(true);

        labelXemNoiDungGiaTopping.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungGiaTopping.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungGiaTopping.setText("10000");
        labelXemNoiDungGiaTopping.setOpaque(true);

        javax.swing.GroupLayout panelXemChiTietToppingLayout = new javax.swing.GroupLayout(panelXemChiTietTopping);
        panelXemChiTietTopping.setLayout(panelXemChiTietToppingLayout);
        panelXemChiTietToppingLayout.setHorizontalGroup(
            panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNoiDungChiTietAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelXemChiTietToppingLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelXemChiTietGiaTopping, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietTenTopping, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(labelXemMaTopping, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelXemNoiDungMaTopping)
                            .addComponent(labelXemNoiDungTenTopping)
                            .addComponent(labelXemNoiDungGiaTopping))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelXemChiTietToppingLayout.setVerticalGroup(
            panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNoiDungChiTietAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemMaTopping)
                    .addComponent(labelXemNoiDungMaTopping))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemChiTietTenTopping)
                    .addComponent(labelXemNoiDungTenTopping))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemChiTietGiaTopping)
                    .addComponent(labelXemNoiDungGiaTopping))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelQLToppingContentLayout = new javax.swing.GroupLayout(panelQLToppingContent);
        panelQLToppingContent.setLayout(panelQLToppingContentLayout);
        panelQLToppingContentLayout.setHorizontalGroup(
            panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLToppingContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelQLToppingContentLayout.createSequentialGroup()
                        .addComponent(scrollpaneDSTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelXemChiTietTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelTitleQLTopping)
                    .addComponent(labelDSTopping)
                    .addGroup(panelQLToppingContentLayout.createSequentialGroup()
                        .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChucNangTopping)
                            .addComponent(panelChucNangTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitleTimKiemBtn)
                            .addComponent(panelTimKiemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(551, Short.MAX_VALUE))
        );
        panelQLToppingContentLayout.setVerticalGroup(
            panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLToppingContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLTopping)
                .addGap(18, 18, 18)
                .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChucNangTopping)
                    .addComponent(labelTitleTimKiemBtn))
                .addGap(18, 18, 18)
                .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTimKiemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChucNangTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelDSTopping)
                .addGap(18, 18, 18)
                .addGroup(panelQLToppingContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelXemChiTietTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollpaneDSTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelQLToppingContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLToppingContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemToppingMouseClicked
        // TODO add your handling code here:
        // khi click vào nút btnThemTopping thí nó sẽ hiện ra frame thêm topping
        this.frameThemTopping.setSize(700, 650);
        this.frameThemTopping.setLocationRelativeTo(null);
        this.frameThemTopping.setVisible(true);
        this.frameThemTopping.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThemToppingMouseClicked

    private void btnXoaToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaToppingMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì sẽ xóa topping
        xoaTopping(this.tableDSTopping, this.defaultTableModelDSTopping, this.dsTopping);
    }//GEN-LAST:event_btnXoaToppingMouseClicked

    private void btnSuaToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaToppingMouseClicked
        // TODO add your handling code here:
        // khi click vào btn này sẽ hiện frame sửa thông tin topping
        int row = this.tableDSTopping.getSelectedRow();
        if (row >= 0) {
            this.frameSuaTopping.setSize(700, 650);
            this.frameSuaTopping.setLocationRelativeTo(null);
            this.frameSuaTopping.setVisible(true);
            this.frameSuaTopping.getContentPane().setBackground(Color.WHITE);
            hienThiThongTinToppingVaoFrameSuaTopping(this.tableDSTopping, this.defaultTableModelDSTopping);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn topping để sửa", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnSuaToppingMouseClicked

    private void btnTimToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimToppingMouseClicked
        // TODO add your handling code here:
        timToppingTheoTen(this.defaultTableModelDSTopping, this.dsTopping);
    }//GEN-LAST:event_btnTimToppingMouseClicked

    private void btnHuyTimToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimToppingMouseClicked
        // TODO add your handling code here:
        getAllTopping(this.defaultTableModelDSTopping, this.dsTopping);
        this.textFieldTimToppingTheoTen.setText("");
    }//GEN-LAST:event_btnHuyTimToppingMouseClicked

    private void btnThemAnhToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemAnhToppingMouseClicked
        // TODO add your handling code here:
        themAnhDaiDienTopping();
    }//GEN-LAST:event_btnThemAnhToppingMouseClicked

    private void btnHoanThanhThemToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemToppingMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này thì nó sẽ thêm topping vào database
        themTopping(this.defaultTableModelDSTopping, this.dsTopping);
    }//GEN-LAST:event_btnHoanThanhThemToppingMouseClicked

    private void btnSuaAnhToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaAnhToppingMouseClicked
        // TODO add your handling code here:
        suaAnhDaiDienTopping();
    }//GEN-LAST:event_btnSuaAnhToppingMouseClicked

    private void btnHoanThanhSuaToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaToppingMouseClicked
        // TODO add your handling code here:
        suaTopping(this.tableDSTopping, this.defaultTableModelDSTopping, this.dsTopping);
    }//GEN-LAST:event_btnHoanThanhSuaToppingMouseClicked

    private void tableDSToppingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSToppingMouseClicked
        // TODO add your handling code here:
        // click vào bảng thì sẽ hiển thị chi tiết topping ra panel xem chi tiết
        xemTopping(this.tableDSTopping, this.defaultTableModelDSTopping);
    }//GEN-LAST:event_tableDSToppingMouseClicked

    private void textFieldThemTenToppingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemTenToppingKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemTenTopping.getText().equals("")) {
            this.labelLoiThemTenTopping.setText("");
        }
    }//GEN-LAST:event_textFieldThemTenToppingKeyPressed

    private void textFieldThemGiaToppingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemGiaToppingKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemGiaTopping.getText().equals("")) {
            this.labelLoiThemGiaTopping.setText("");
        }
    }//GEN-LAST:event_textFieldThemGiaToppingKeyPressed

    private void textFieldSuaTenToppingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaTenToppingKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaTenTopping.getText().equals("")) {
            this.labelLoiSuaTenTopping.setText("");
        }
    }//GEN-LAST:event_textFieldSuaTenToppingKeyPressed

    private void textFieldSuaGiaToppingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaGiaToppingKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaGiaTopping.getText().equals("")) {
            this.labelLoiSuaGiaTopping.setText("");
        }
    }//GEN-LAST:event_textFieldSuaGiaToppingKeyPressed

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
            java.util.logging.Logger.getLogger(QLTopping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLTopping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLTopping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLTopping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLTopping().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaTopping;
    private javax.swing.JButton btnHoanThanhThemTopping;
    private javax.swing.JButton btnHuyTimTopping;
    private javax.swing.JButton btnSuaAnhTopping;
    private javax.swing.JButton btnSuaTopping;
    private javax.swing.JButton btnThemAnhTopping;
    private javax.swing.JButton btnThemTopping;
    private javax.swing.JButton btnTimTopping;
    private javax.swing.JButton btnXoaTopping;
    private javax.swing.JFrame frameSuaTopping;
    private javax.swing.JFrame frameThemTopping;
    private javax.swing.JFrame frameXemTopping;
    private javax.swing.JLabel labelChucNangTopping;
    private javax.swing.JLabel labelDSTopping;
    private javax.swing.JLabel labelLoiSuaGiaTopping;
    private javax.swing.JLabel labelLoiSuaTenTopping;
    private javax.swing.JLabel labelLoiThemGiaTopping;
    private javax.swing.JLabel labelLoiThemTenTopping;
    private javax.swing.JLabel labelNoiDungChiTietAnhTopping;
    private javax.swing.JLabel labelSuaGiaTopping;
    private javax.swing.JLabel labelSuaImageTopping;
    private javax.swing.JLabel labelSuaTenTopping;
    private javax.swing.JLabel labelThemGiaTopping;
    private javax.swing.JLabel labelThemImageTopping;
    private javax.swing.JLabel labelThemTenTopping;
    private javax.swing.JLabel labelTileXemTopping;
    private javax.swing.JLabel labelTitleQLTopping;
    private javax.swing.JLabel labelTitleSuaTopping;
    private javax.swing.JLabel labelTitleThemTopping;
    private javax.swing.JLabel labelTitleTimKiemBtn;
    private javax.swing.JLabel labelXemAnhTopping;
    private javax.swing.JLabel labelXemChiTietGiaTopping;
    private javax.swing.JLabel labelXemChiTietTenTopping;
    private javax.swing.JLabel labelXemGiaTopping;
    private javax.swing.JLabel labelXemIDTopping;
    private javax.swing.JLabel labelXemMaTopping;
    private javax.swing.JLabel labelXemMoTaTopping;
    private javax.swing.JLabel labelXemNoiDungGiaTopping;
    private javax.swing.JLabel labelXemNoiDungMaTopping;
    private javax.swing.JLabel labelXemNoiDungTenTopping;
    private javax.swing.JLabel labelXemTenTopping;
    private javax.swing.JLabel lbaelTimKiemToppingTheoTen;
    private javax.swing.JPanel panelChucNangTopping;
    private javax.swing.JPanel panelQLToppingContent;
    private javax.swing.JPanel panelSuaTopping;
    private javax.swing.JPanel panelThemTopping;
    private javax.swing.JPanel panelTimKiemTopping;
    private javax.swing.JPanel panelXemChiTietTopping;
    private javax.swing.JPanel panelXemTopping;
    private javax.swing.JScrollPane scrollPaneXemMoTaTopping;
    private javax.swing.JScrollPane scrollpaneDSTopping;
    private javax.swing.JTable tableDSTopping;
    private javax.swing.JTextArea textAreaXemMoTaTopping;
    private javax.swing.JTextField textFieldSuaGiaTopping;
    private javax.swing.JTextField textFieldSuaTenTopping;
    private javax.swing.JTextField textFieldThemGiaTopping;
    private javax.swing.JTextField textFieldThemTenTopping;
    private javax.swing.JTextField textFieldTimToppingTheoTen;
    private javax.swing.JTextField textFieldXemGiaTopping;
    private javax.swing.JTextField textFieldXemMaTopping;
    private javax.swing.JTextField textFieldXemTenTopping;
    // End of variables declaration//GEN-END:variables
}
