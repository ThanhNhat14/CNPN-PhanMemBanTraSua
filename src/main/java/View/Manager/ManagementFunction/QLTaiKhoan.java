/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import static Controller.LogRes.ManagerEmployee.Login.ManagerEmployee;
import Dao.AccountDAO;
import Dao.CustomerDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Account;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Utils.Manager.EncryptPassword;
import Utils.Manager.FormatMoney;

/**
 *
 * @author Admin
 */
public class QLTaiKhoan extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLTaiKhoan
     */
    public String tempImageNameFormSuaTaiKhoan;
    public String tempImageName;

    public DefaultTableModel defaultTableModelDSTaiKhoan;
    public ArrayList<Account> dsTaiKhoan;
    public AccountDAO accountDAO;
    public CustomerDAO customerDAO;

    public Account currentAccount;
    public static Map<String, Object> infoManager = new HashMap<>();

    public QLTaiKhoan() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.dsTaiKhoan = new ArrayList<>();
        this.accountDAO = new AccountDAO();
        this.currentAccount = new Account();
        this.customerDAO = new CustomerDAO();

        // Tài khoản
        this.defaultTableModelDSTaiKhoan = new DefaultTableModel();
        this.tableDSTaiKhoan.setModel(this.defaultTableModelDSTaiKhoan);
        String[] columnsDSTaiKhoan = {"Mã tài khoản", "Họ tên", "Phân quyền", "User name", "Ngày sinh", "Giới tính"};
        this.defaultTableModelDSTaiKhoan.setColumnIdentifiers(columnsDSTaiKhoan);
        JTableHeader headerDSTaiKhoan = this.tableDSTaiKhoan.getTableHeader();
        headerDSTaiKhoan.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSTaiKhoan.setBackground(new Color(0x43CD80));
        headerDSTaiKhoan.setForeground(Color.WHITE);
        headerDSTaiKhoan.setPreferredSize(new Dimension(headerDSTaiKhoan.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSTaiKhoan.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSTaiKhoan.setRowHeight(30);
        this.tableDSTaiKhoan.getColumnModel().getColumn(0).setPreferredWidth(90);
        this.tableDSTaiKhoan.getColumnModel().getColumn(1).setPreferredWidth(210);
        this.tableDSTaiKhoan.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.tableDSTaiKhoan.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.tableDSTaiKhoan.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.tableDSTaiKhoan.getColumnModel().getColumn(5).setPreferredWidth(100);

        this.scrollPanelDStaiKhoan.getViewport().setBackground(Color.WHITE);

        this.dateChooserThemNgaySinhTaiKhoan.setDateFormatString("dd/MM/yyyy");
        this.dateChooserThemNgaySinhTaiKhoan.setFont(new Font("Arial", Font.BOLD, 15));
        this.dateChooserSuaNgaySinhTaiKhoan.setDateFormatString("dd/MM/yyyy");
        this.dateChooserSuaNgaySinhTaiKhoan.setFont(new Font("Arial", Font.BOLD, 15));

        this.currentAccount = getCurrentAccount();

        getAllAccount(this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
    }

    // lấy tài khoản đang đăng nhập
    // ta ko đc xóa hoặc sửa tài khoản đang đăng nhập
    public Account getCurrentAccount() {
        int idAccount = (Integer) ManagerEmployee.get("idAccount");
        Account a = this.accountDAO.getAccountByID(idAccount);
        return a;
    }

    // ==================== TÀI KHOẢN ===================
    public void xuatDSTaiKhoanRaTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        defaultTableModel.setRowCount(0);
        String gioiTinh = "";
        for (Account i : ds) {
            if (i.getGender() == 1) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            Object[] data = {i.getIdAccount(), i.getFullName(), i.getPermission(), i.getUserName(), i.getBirthday(), gioiTinh};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllAccount(DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        ds = this.accountDAO.getAllAccount();
        xuatDSTaiKhoanRaTableTuArrayList(defaultTableModel, ds);

    }

//    public void hienThiAvatarAccountTrongFrameThemTaiKhoan() {
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
//                this.tempImageName = relativePath;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemAnhDaiDienTaiKhoan.getWidth(), this.labelThemAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                this.labelThemAnhDaiDienTaiKhoan.setIcon(imageIcon);
//            }
//        }
//    }
    public void hienThiAvatarAccountTrongFrameThemTaiKhoan() {
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.tempImageName = relativePath;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemAnhDaiDienTaiKhoan.getWidth(), this.labelThemAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
                this.labelThemAnhDaiDienTaiKhoan.setIcon(imageIcon);
            }
        }
    }

    // ==== Lấy thông tin tài khoản khi Click vào table tài khoản =====
    public Account layThongTinAccountKhiClickVaoTableAccount(DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        int row = this.tableDSTaiKhoan.getSelectedRow();
        if (row >= 0) {
            int idAccount = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Account a = this.accountDAO.getAccountByID(idAccount);
            return a;
        }
        return null;
    }

    public void resetFrameThemTaiKhoan() {
        this.tempImageName = "";
        this.textFieldThemHoTenTaiKhoan.setText("");
        this.labelLoiThemHoTenTaiKhoan.setText("");
        this.textFieldThemCCCDTaiKhoan.setText("");
        this.labelLoiThemCCCDTaiKhoan.setText("");
        this.textFieldThemUsernameTaiKhoan.setText("");
        this.labelLoiThemUsernameTaiKhoan.setText("");
        this.passwordFieldThemMKTaiKhoan.setText("");
        this.labelLoiThemMKTaiKhoan.setText("");
        this.buttonGroupThemGioiTinhTaiKhoan.clearSelection();
        this.comboBoxThemPhanQuyenTaiKhoan.setSelectedIndex(0);
        this.textFieldThemLuongTaiKhoan.setText("");
        this.labelLoiThemLuongTaiKhoan.setText("");
        this.textFieldThemSDTTaiKhoan.setText("");
        this.labelLoiThemSDTTaiKhoan.setText("");
        this.textFieldThemDiaChiTaiKhoan.setText("");
        this.labelLoiThemDiaChiTaiKhoan.setText("");
        this.labelLoiThemGioiTinhTaiKhoan.setText("");
        this.labelLoiThemNgaySinhTaiKhoan.setText("");
    }

    public void themTaiKhoan(DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        String thongBaoLoi = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String regexSDT = "^0(\\d{9})$";
        String regexCCCD = "^\\d{12}$";
        String regexMatKhau = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        String regexLuong = "^[1-9]\\\\d*|0$";
        Pattern patternSDT = Pattern.compile(regexSDT);
        Matcher matcherSDT;
        Pattern patternCCCD = Pattern.compile(regexCCCD);
        Matcher matcherCCCD;
        Pattern patternMatKhau = Pattern.compile(regexMatKhau);
        Matcher matcherMatKhau;
        Pattern patternLuong = Pattern.compile(regexLuong);
        Matcher matcherLuong;

        if (this.textFieldThemHoTenTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập họ tên tài khoản.";
            this.labelLoiThemHoTenTaiKhoan.setText("Vui lòng nhập họ tên tài khoản.");
        }
        if (this.textFieldThemCCCDTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập CCCD tên tài khoản.";
            this.labelLoiThemCCCDTaiKhoan.setText("Vui lòng nhập CCCD tên tài khoản.");
        } else {
            matcherCCCD = patternCCCD.matcher(this.textFieldThemCCCDTaiKhoan.getText());
            if (!matcherCCCD.matches()) {
                thongBaoLoi += "CCCD không hợp lệ";
                this.labelLoiThemCCCDTaiKhoan.setText("CCCD phải có 12 ký tự số");
            }
        }
        if (this.textFieldThemUsernameTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập username.";
            this.labelLoiThemUsernameTaiKhoan.setText("Vui lòng nhập username.");
        }
        if (String.valueOf(this.passwordFieldThemMKTaiKhoan.getPassword()).equals("")) {
            thongBaoLoi += "Vui lòng nhập mật khẩu.";
            this.labelLoiThemMKTaiKhoan.setText("Vui lòng nhập mật khẩu.");
        } else {
            matcherMatKhau = patternMatKhau.matcher(String.valueOf(this.passwordFieldThemMKTaiKhoan.getPassword()));
            if (!matcherMatKhau.matches()) {
                thongBaoLoi += "Mật khẩu không hợp lệ";
                this.labelLoiThemMKTaiKhoan.setText("Mật khẩu phải có ít nhất 6 ký tự số và chữ");
            }
        }
        if (this.dateChooserThemNgaySinhTaiKhoan.getDate() == null) {
            thongBaoLoi += "Vui lòng chọn ngày sinh";
            this.labelLoiThemNgaySinhTaiKhoan.setText("Vui lòng chọn ngày sinh");
        }
        if (this.buttonGroupThemGioiTinhTaiKhoan.getSelection() == null) {
            thongBaoLoi += "Vui lòng chọn giới tính.";
            this.labelLoiThemGioiTinhTaiKhoan.setText("Vui lòng nhập giới tính.");
        }
        if (this.comboBoxThemPhanQuyenTaiKhoan.getSelectedItem() == null) {
            thongBaoLoi += "Vui lòng chọn phân quyền.";
            this.labelLoiThemPhanQuyentaiKhoan.setText("Vui lòng chọn phân quyền.");
        }
        if (this.textFieldThemLuongTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập lương.";
            this.labelLoiThemLuongTaiKhoan.setText("Vui lòng nhập lương.");
        } else {
            if (!this.textFieldThemLuongTaiKhoan.getText().matches("\\d+")) {
                thongBaoLoi += "Lương phải là số nguyên dương";
                this.labelLoiThemLuongTaiKhoan.setText("Lương phải là số nguyên dương");
            }
        }
        if (this.textFieldThemSDTTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập SDT";
            this.labelLoiThemSDTTaiKhoan.setText("Vui lòng nhập SDT");
        } else {
            matcherSDT = patternSDT.matcher(this.textFieldThemSDTTaiKhoan.getText());
            if (!matcherSDT.matches()) {
                thongBaoLoi += "SDT không hợp lệ";
                this.labelLoiThemSDTTaiKhoan.setText("SDT không hợp lệ");
            }
        }
        if (this.textFieldThemDiaChiTaiKhoan.getText().isEmpty()) {
            thongBaoLoi += "Vui lòng nhập địa chỉ.";
            this.labelLoiThemDiaChiTaiKhoan.setText("Vui lòng nhập địa chỉ.");
        }
        if (thongBaoLoi.equals("")) {
            String hoTen = this.textFieldThemHoTenTaiKhoan.getText();
            String cccd = this.textFieldThemCCCDTaiKhoan.getText();
            String userName = this.textFieldThemUsernameTaiKhoan.getText();
            String password = String.valueOf(this.passwordFieldThemMKTaiKhoan.getPassword());
            password = EncryptPassword.encryptPassword(password);
            Date date = this.dateChooserThemNgaySinhTaiKhoan.getDate();
            String ngaySinh = sdf.format(date);
            int gioiTinh = 1;
            if (this.radioThemGioiTinhNuTaiKhoan.isSelected()) {
                gioiTinh = 0;
            }
            String permisson = "EMPLOYEE";
            String avatar = "";
            if (this.tempImageName != null) {
                avatar = this.tempImageName;
                System.out.println(avatar);
            }
            long luong = Long.parseLong(this.textFieldThemLuongTaiKhoan.getText());
            String sdt = this.textFieldThemSDTTaiKhoan.getText();
            String diaChi = this.textFieldThemDiaChiTaiKhoan.getText();
            Account a = new Account(hoTen, cccd, userName, password, ngaySinh, diaChi, permisson, gioiTinh, luong, sdt, avatar);
            boolean ktTrungCCCD = this.accountDAO.isDuplicateCCCD(a.getCccd());
            boolean ktTrungUserName = this.accountDAO.isDuplicateUserName(a.getUserName());
            boolean ktTrungUserNameKhachHang = this.customerDAO.isDuplicateUserName(userName);
            boolean ktTrungSDT = this.accountDAO.isDuplicateNumberPhone(sdt);
            if (ktTrungCCCD == false && ktTrungUserName == false && ktTrungSDT == false && ktTrungUserNameKhachHang == false) {
                this.accountDAO.insertAccountToDatabase(a);
                this.frameThemTaiKhoan.dispose();
                resetFrameThemTaiKhoan();
                getAllAccount(defaultTableModel, ds);
            } else {
                if (ktTrungCCCD == true) {
                    JOptionPane.showMessageDialog(null, "CCCD đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (ktTrungUserName == true) {
                    JOptionPane.showMessageDialog(null, "Username đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (ktTrungUserNameKhachHang == true) {
                    JOptionPane.showMessageDialog(null, "Username đã tồn tại trong bảng khách hàng.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (ktTrungSDT == true) {
                    JOptionPane.showMessageDialog(null, "SDT đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void xoaTaiKhoan(JTable table, DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Account taiKhoanCanXoa = layThongTinAccountKhiClickVaoTableAccount(defaultTableModel, ds);
            if (taiKhoanCanXoa != null) {
                if (taiKhoanCanXoa.getIdAccount() == this.currentAccount.getIdAccount()) {
                    JOptionPane.showMessageDialog(null, "Không thể xóa tài khoản đang đăng nhập", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản này không.");
                    if (confirm == 0) {
                        this.accountDAO.deleteAccountInDatabase(taiKhoanCanXoa.getIdAccount());
                        getAllAccount(defaultTableModel, ds);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "'Vui lòng chọn một tài khoản để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

//    public void hienThiThongTinChiTietTaiKhoan(JTable table, DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
//        int row = table.getSelectedRow();
//        if (row >= 0) {
//            Account taiKhoanCanXem = layThongTinAccountKhiClickVaoTableAccount(defaultTableModel, ds);
//            if (taiKhoanCanXem != null) {
//                this.labelXemNoiDungMaTaiKhoan.setText(taiKhoanCanXem.getIdAccount() + "");
//                this.textFieldXemChiTietHoTenTaiKhoan.setText(taiKhoanCanXem.getFullName());
//                this.textFieldXemChiTietCCCDTaiKhoan.setText(taiKhoanCanXem.getCccd());
//                this.textFieldXemChiTietUsernameTaiKhoan.setText(taiKhoanCanXem.getUserName());
//                this.labelXemNoiDungNgaySinhTaiKhoan.setText(taiKhoanCanXem.getBirthday());
//                int gioiTinh = taiKhoanCanXem.getGender();
//                if (gioiTinh == 1) {
//                    this.radioXemChiTietGioiTinhNamTaiKhoan.setSelected(true);
//                    this.radioXemChiTietGioiTinhNuTaiKhoan.setSelected(false);
//                } else {
//                    this.radioXemChiTietGioiTinhNuTaiKhoan.setSelected(true);
//                    this.radioXemChiTietGioiTinhNamTaiKhoan.setSelected(false);
//                }
//                if (taiKhoanCanXem.getAvatar() != null) {
//                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(taiKhoanCanXem.getAvatar()).getImage().getScaledInstance(this.labelXemChiTietAnhDaiDienTaiKhoan.getWidth(), this.labelXemChiTietAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                    this.labelXemChiTietAnhDaiDienTaiKhoan.setIcon(imageIcon);
//                } else {
//                    this.labelXemChiTietAnhDaiDienTaiKhoan.setIcon(null);
//                    this.tempImageName = "";
//                }
//                this.textFieldXemChiTietLuongTaiKhoan.setText(FormatMoney.formatMoney(Integer.parseInt(taiKhoanCanXem.getSalary() + "")));
//                this.textFieldXemChiTietSDTTaiKhoan.setText(taiKhoanCanXem.getNumberPhone());
//                this.textFieldXemChiTietDiaChiTaiKhoan.setText(taiKhoanCanXem.getAddress());
//                if (taiKhoanCanXem.getPermission().equals("MANAGER")) {
//                    this.comboBoxXemChiTietPhanQuyenTaiKhoan.setSelectedItem("Quản lý");
//                } else {
//                    this.comboBoxXemChiTietPhanQuyenTaiKhoan.setSelectedItem("Nhân viên");
//                }
//            }
//        }
//    }
    public void hienThiThongTinChiTietTaiKhoan(JTable table, DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Account taiKhoanCanXem = layThongTinAccountKhiClickVaoTableAccount(defaultTableModel, ds);
            if (taiKhoanCanXem != null) {
                this.labelXemNoiDungMaTaiKhoan.setText(taiKhoanCanXem.getIdAccount() + "");
                this.textFieldXemChiTietHoTenTaiKhoan.setText(taiKhoanCanXem.getFullName());
                this.textFieldXemChiTietCCCDTaiKhoan.setText(taiKhoanCanXem.getCccd());
                this.textFieldXemChiTietUsernameTaiKhoan.setText(taiKhoanCanXem.getUserName());
                this.labelXemNoiDungNgaySinhTaiKhoan.setText(taiKhoanCanXem.getBirthday());
                int gioiTinh = taiKhoanCanXem.getGender();
                if (gioiTinh == 1) {
                    this.radioXemChiTietGioiTinhNamTaiKhoan.setSelected(true);
                    this.radioXemChiTietGioiTinhNuTaiKhoan.setSelected(false);
                } else {
                    this.radioXemChiTietGioiTinhNuTaiKhoan.setSelected(true);
                    this.radioXemChiTietGioiTinhNamTaiKhoan.setSelected(false);
                }
                if (taiKhoanCanXem.getAvatar() != null) {
//                    String urlImage = "E:\\NhapMonCongNghePhanMem\\Code\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + taiKhoanCanXem.getAvatar();
//                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelXemChiTietAnhDaiDienTaiKhoan.getWidth(), this.labelXemChiTietAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                    this.labelXemChiTietAnhDaiDienTaiKhoan.setIcon(imageIcon);
                    String urlImage = taiKhoanCanXem.getAvatar();
                    this.labelXemChiTietAnhDaiDienTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
                } else {
                    this.labelXemChiTietAnhDaiDienTaiKhoan.setIcon(null);
                    this.tempImageName = "";
                }
                this.textFieldXemChiTietLuongTaiKhoan.setText(FormatMoney.formatMoney(Integer.parseInt(taiKhoanCanXem.getSalary() + "")));
                this.textFieldXemChiTietSDTTaiKhoan.setText(taiKhoanCanXem.getNumberPhone());
                this.textFieldXemChiTietDiaChiTaiKhoan.setText(taiKhoanCanXem.getAddress());
                if (taiKhoanCanXem.getPermission().equals("MANAGER")) {
                    this.comboBoxXemChiTietPhanQuyenTaiKhoan.setSelectedItem("Quản lý");
                } else {
                    this.comboBoxXemChiTietPhanQuyenTaiKhoan.setSelectedItem("Nhân viên");
                }
            }
        }
    }

    // hàm hiển thị thông tin tài khoản cần sửa vào Frame thayDoiTongTintaiKhoan khi bẫm nút sửa tài khoản
//    public void hienThiThongTinAccountVaoFrameThayDoiThongTinTaiKhoan(int idAccount) {
//        Account taiKhoanCanSua = this.accountDAO.getAccountByID(idAccount);
//        this.textFieldSuaHoTenTaiKhoan.setText(taiKhoanCanSua.getFullName());
//        this.textFieldSuaCCCDTaiKhoan.setText(taiKhoanCanSua.getCccd());
//        this.textFieldSuaUsernameTaiKhoan.setText(taiKhoanCanSua.getUserName());
//        String[] ngaySinh = taiKhoanCanSua.getBirthday().split("/");
//        int ngay = Integer.parseInt(ngaySinh[0]);
//        int thang = Integer.parseInt(ngaySinh[1]);
//        int nam = Integer.parseInt(ngaySinh[2]);
//        this.comboBoxSuaNgaySinhTaiKhoan.setSelectedItem(ngay);
//        this.comboBoxSuaThangSinhTaiKhoan.setSelectedItem(thang);
//        this.comboBoxSuaNamSinhTaiKhoan.setSelectedItem(nam);
//        int gioiTinh = taiKhoanCanSua.getGender();
//        if (gioiTinh == 1) {
//            this.radioSuaGioiTinhNamTaiKhoan.setSelected(true);
//            this.radioSuaGioiTinhNuTaiKhoan.setSelected(false);
//        } else {
//            this.radioSuaGioiTinhNuTaiKhoan.setSelected(true);
//            this.radioSuaGioiTinhNamTaiKhoan.setSelected(false);
//        }
//        String phanQuyen = taiKhoanCanSua.getPermission();
//        if (phanQuyen.equals("EMPLOYEE")) {
//            this.comboBoxSuaPhanQuyenTaiKhoan.setSelectedItem("EMPLOYEE");
//        } else if (phanQuyen.equals("MANAGER")) {
//            this.comboBoxSuaPhanQuyenTaiKhoan.setSelectedItem("MANAGER");
//        }
//        if (taiKhoanCanSua.getAvatar() != null) {
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(taiKhoanCanSua.getAvatar()).getImage().getScaledInstance(this.labelSuaAnhDaiDienTaiKhoan.getWidth(), this.labelSuaAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//            this.labelSuaAnhDaiDienTaiKhoan.setIcon(imageIcon);
//            this.tempImageNameFormSuaTaiKhoan = taiKhoanCanSua.getAvatar();
//        } else {
//            this.labelSuaAnhDaiDienTaiKhoan.setIcon(null);
//            this.tempImageNameFormSuaTaiKhoan = "";
//        }
//        this.textFieldSuaLuongTaiKhoan.setText(taiKhoanCanSua.getSalary() + "");
//        this.textFieldSuaSDTTaiKhoan.setText(taiKhoanCanSua.getNumberPhone());
//        this.textFieldSuaDiaChiTaiKhoan.setText(taiKhoanCanSua.getAddress());
//    }
    public void hienThiThongTinAccountVaoFrameThayDoiThongTinTaiKhoan(int idAccount) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Account taiKhoanCanSua = this.accountDAO.getAccountByID(idAccount);
        this.textFieldSuaHoTenTaiKhoan.setText(taiKhoanCanSua.getFullName());
        this.textFieldSuaCCCDTaiKhoan.setText(taiKhoanCanSua.getCccd());
        this.textFieldSuaUsernameTaiKhoan.setText(taiKhoanCanSua.getUserName());
        Date date = new Date();
        try {
            date = sdf.parse(taiKhoanCanSua.getBirthday());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dateChooserSuaNgaySinhTaiKhoan.setDate(date);
        int gioiTinh = taiKhoanCanSua.getGender();
        if (gioiTinh == 1) {
            this.radioSuaGioiTinhNamTaiKhoan.setSelected(true);
            this.radioSuaGioiTinhNuTaiKhoan.setSelected(false);
        } else {
            this.radioSuaGioiTinhNuTaiKhoan.setSelected(true);
            this.radioSuaGioiTinhNamTaiKhoan.setSelected(false);
        }
        String phanQuyen = taiKhoanCanSua.getPermission();
        if (phanQuyen.equals("EMPLOYEE")) {
            this.comboBoxSuaPhanQuyenTaiKhoan.setSelectedItem("EMPLOYEE");
        } else if (phanQuyen.equals("MANAGER")) {
            this.comboBoxSuaPhanQuyenTaiKhoan.setSelectedItem("MANAGER");
        }
        if (taiKhoanCanSua.getAvatar() != null) {
//            String urlImage = "E:\\NhapMonCongNghePhanMem\\Code\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + taiKhoanCanSua.getAvatar();
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelSuaAnhDaiDienTaiKhoan.getWidth(), this.labelSuaAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//            this.labelSuaAnhDaiDienTaiKhoan.setIcon(imageIcon);
//            this.tempImageNameFormSuaTaiKhoan = taiKhoanCanSua.getAvatar();
            String urlImage = taiKhoanCanSua.getAvatar();
            this.labelSuaAnhDaiDienTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));

        } else {
            this.labelSuaAnhDaiDienTaiKhoan.setIcon(null);
            this.tempImageNameFormSuaTaiKhoan = "";
        }
        this.textFieldSuaLuongTaiKhoan.setText(taiKhoanCanSua.getSalary() + "");
        this.textFieldSuaSDTTaiKhoan.setText(taiKhoanCanSua.getNumberPhone());
        this.textFieldSuaDiaChiTaiKhoan.setText(taiKhoanCanSua.getAddress());
    }

    // hàm reset các label thong báo lỗi trong frame sửa thông tin tai khoản
    public void resetLabelLoiTrongFrameSuaThongTinTaiKhoan() {
        this.labelLoiSuaHoTenTaiKhoan.setText("");
        this.labelLoiSuaCCCDTaiKhoan.setText("");
        this.labelLoiSuaUsernameTaiKhoan.setText("");
        this.labelLoiSuaGioiTinhTaiKhoan.setText("");
        this.labelLoiSuaPhanQuyentaiKhoan.setText("");
        this.labelLoiSuaLuongTaiKhoan.setText("");
        this.labelLoiSuaDiaChiTaiKhoan.setText("");
        this.labelLoiSuaSDTTaiKhoan.setText("");
    }

    // hàm này sẽ sửa thông tin tài khoản khi ta bấm vào nút HoanThanhSuaTaiKhoan
    public void suaThongTinTaiKhoan(JTable table, DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idAccount = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Account taiKhoanCanSua = this.accountDAO.getAccountByID(idAccount);
            String thongBaoLoi = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String regexSDT = "^0(\\d{9})$";
            String regexCCCD = "^\\d{12}$";
            String regexMatKhau = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
            Pattern patternSDT = Pattern.compile(regexSDT);
            Matcher matcherSDT;
            Pattern patternCCCD = Pattern.compile(regexCCCD);
            Matcher matcherCCCD;
            Pattern patternMatKhau = Pattern.compile(regexMatKhau);
            Matcher matcherMatKhau;

            if (this.textFieldSuaHoTenTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập họ tên tài khoản.";
                this.labelLoiSuaHoTenTaiKhoan.setText("Vui lòng nhập họ tên tài khoản.");
            }
            if (this.textFieldSuaCCCDTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập CCCD tên tài khoản.";
                this.labelLoiSuaCCCDTaiKhoan.setText("Vui lòng nhập CCCD tên tài khoản.");
            } else {
                matcherCCCD = patternCCCD.matcher(this.textFieldSuaCCCDTaiKhoan.getText());
                if (!matcherCCCD.matches()) {
                    thongBaoLoi += "CCCD không hợp lệ";
                    this.labelLoiSuaCCCDTaiKhoan.setText("CCCD phải có 12 ký tự số");
                }
            }
            if (this.textFieldSuaUsernameTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập username.";
                this.labelLoiSuaUsernameTaiKhoan.setText("Vui lòng nhập username.");
            }
            if (this.dateChooserSuaNgaySinhTaiKhoan.getDate() == null) {
                thongBaoLoi += "Vui lòng chọn ngày sinh";
                this.labelLoiSuaNgaySinhTaiKhoan.setText("Vui lòng chọn ngày sinh");
            }
            if (this.buttonGroupSuaGioiTinhTaiKhoan.getSelection() == null) {
                thongBaoLoi += "Vui lòng chọn giới tính.";
                this.labelLoiSuaGioiTinhTaiKhoan.setText("Vui lòng nhập giới tính.");
            }
            if (this.comboBoxSuaPhanQuyenTaiKhoan.getSelectedItem() == null) {
                thongBaoLoi += "Vui lòng chọn phân quyền.";
                this.labelLoiSuaPhanQuyentaiKhoan.setText("Vui lòng chọn phân quyền.");
            }
            if (this.textFieldSuaLuongTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập lương.";
                this.labelLoiSuaLuongTaiKhoan.setText("Vui lòng nhập lương.");
            } else {
                if (!this.textFieldSuaLuongTaiKhoan.getText().matches("\\d+")) {
                    thongBaoLoi += "Lương phải là số nguyên dương";
                    this.labelLoiSuaLuongTaiKhoan.setText("Lương phải là số nguyên dương");
                }
            }
            if (this.textFieldSuaSDTTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập SDT";
                this.labelLoiSuaSDTTaiKhoan.setText("Vui lòng nhập SDT");
            } else {
                matcherSDT = patternSDT.matcher(this.textFieldSuaSDTTaiKhoan.getText());
                if (!matcherSDT.matches()) {
                    thongBaoLoi += "SDT không hợp lệ";
                    this.labelLoiSuaSDTTaiKhoan.setText("SDT không hợp lệ");
                }
            }
            if (this.textFieldSuaDiaChiTaiKhoan.getText().isEmpty()) {
                thongBaoLoi += "Vui lòng nhập địa chỉ.";
                this.labelLoiSuaDiaChiTaiKhoan.setText("Vui lòng nhập địa chỉ.");
            }
            if (thongBaoLoi.equals("")) {
                String hoTen = this.textFieldSuaHoTenTaiKhoan.getText();
                String cccd = this.textFieldSuaCCCDTaiKhoan.getText();
                String userName = this.textFieldSuaUsernameTaiKhoan.getText();
                Date date = this.dateChooserSuaNgaySinhTaiKhoan.getDate();
                String ngaySinh = sdf.format(date);
                int gioiTinh = 1;
                if (this.radioSuaGioiTinhNuTaiKhoan.isSelected()) {
                    gioiTinh = 0;
                }
                String permisson = this.comboBoxSuaPhanQuyenTaiKhoan.getSelectedItem() + "";
                String avatar = "";
                if (this.tempImageNameFormSuaTaiKhoan != null) {
                    avatar = tempImageNameFormSuaTaiKhoan;
                }
                long luong = Long.parseLong(this.textFieldSuaLuongTaiKhoan.getText());
                String sdt = this.textFieldSuaSDTTaiKhoan.getText();
                String diaChi = this.textFieldSuaDiaChiTaiKhoan.getText();
                Account newAccount = new Account(hoTen, cccd, userName, taiKhoanCanSua.getPassword(), ngaySinh, diaChi, permisson, gioiTinh, luong, sdt, avatar);

                boolean ktTrungCCCD = this.accountDAO.isDuplicateCCCD(cccd);
                boolean ktTrungUserName = this.accountDAO.isDuplicateUserName(userName);
                boolean ktTrungUserNameKhachHang = this.customerDAO.isDuplicateUserName(userName);
                boolean ktTrungSDT = this.accountDAO.isDuplicateNumberPhone(sdt);
                if (cccd.equals(taiKhoanCanSua.getCccd())) {
                    // kiểm tra xem cccd mới có giống như cccd của taiKhoanCanSua hay ko
                    // nếu giống thì ta ko cần ktTrungCCCD
                    ktTrungCCCD = false;
                }
                if (userName.equals(taiKhoanCanSua.getUserName())) {
                    // kiểm tra xem userName mới có giống như userName của taiKhoanCanSua hay ko
                    // nếu giống thì ta ko cần ktTrungUserName
                    ktTrungUserName = false;
                }
                if (sdt.equals(taiKhoanCanSua.getNumberPhone())) {
                    ktTrungSDT = false;
                }
                if (ktTrungCCCD == false && ktTrungUserName == false && ktTrungSDT == false && ktTrungUserNameKhachHang == false) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thay đổi tài khoản này không.");
                    if (confirm == JOptionPane.OK_OPTION) {
                        this.accountDAO.updateAccount(idAccount, newAccount);
                        getAllAccount(defaultTableModel, ds);
                        // this.frameSuaTaiKhoan.setVisible(false);
                        this.frameSuaTaiKhoan.dispose();
                        this.tempImageNameFormSuaTaiKhoan = "";
                    }
                } else {
                    if (ktTrungCCCD == true) {
                        JOptionPane.showMessageDialog(null, "CCCD đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    if (ktTrungUserName == true) {
                        JOptionPane.showMessageDialog(null, "Username đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    if (ktTrungUserNameKhachHang == true) {
                        JOptionPane.showMessageDialog(null, "Username đã tồn tại trong bảng khách hàng.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    if (ktTrungSDT == true) {
                        JOptionPane.showMessageDialog(null, "SDT đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void hienThiFormSuaAccount() {
        int row = this.tableDSTaiKhoan.getSelectedRow();
        if (row >= 0) {
            int idAccount = Integer.parseInt(this.defaultTableModelDSTaiKhoan.getValueAt(row, 0) + "");
            if (idAccount == this.currentAccount.getIdAccount()) {
                JOptionPane.showMessageDialog(null, "Không thể sửa tài khoản đang đăng nhập tại đây", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.frameSuaTaiKhoan.setSize(900, 800);
                this.frameSuaTaiKhoan.setLocationRelativeTo(null);
                this.frameSuaTaiKhoan.setVisible(true);
                hienThiThongTinAccountVaoFrameThayDoiThongTinTaiKhoan(idAccount);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void timKiemTaiKhoanTheoTen(JTable table, DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        if (this.textFieldTimKiemTaiKhoanTheoTen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String theFullName = this.textFieldTimKiemTaiKhoanTheoTen.getText();
            ArrayList<Account> kq = this.accountDAO.findAccountByFullName(theFullName);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có tài khoản cần tìm");
            } else {
                xuatDSTaiKhoanRaTableTuArrayList(defaultTableModel, kq);
            }
        }
    }

    public void timTaiKhoanTheoPhanQuyen(DefaultTableModel defaultTableModel, ArrayList<Account> ds) {
        String phanQuyen = this.comboBoxTimTheoChucVu.getSelectedItem() + "";
        ArrayList<Account> kq = new ArrayList<>();
        if (phanQuyen.equals("Tất cả")) {
            getAllAccount(defaultTableModel, ds);
        } else if (phanQuyen.equals("Nhân viên")) {
            kq = this.accountDAO.findAccountByPermission("EMPLOYEE");
            xuatDSTaiKhoanRaTableTuArrayList(defaultTableModel, kq);
        } else {
            kq = this.accountDAO.findAccountByPermission("MANAGER");
            xuatDSTaiKhoanRaTableTuArrayList(defaultTableModel, kq);
        }
    }

    public void hienThiMatKhau(JPasswordField passwordField, JCheckBox checkBox) {
        if (!String.valueOf(passwordField.getPassword()).equals("")) {
            if (checkBox.isSelected() == true) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameSuaTaiKhoan = new javax.swing.JFrame();
        labelTitleSuaTaiKhoanForm = new javax.swing.JLabel();
        panelSuaTaiKhoanContainer = new javax.swing.JPanel();
        labelSuaHoTenTaiKhoan = new javax.swing.JLabel();
        textFieldSuaHoTenTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaHoTenTaiKhoan = new javax.swing.JLabel();
        labelSuaCCCdTaiKhoan = new javax.swing.JLabel();
        textFieldSuaCCCDTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaCCCDTaiKhoan = new javax.swing.JLabel();
        labelSuaUsernameTaiKhoan = new javax.swing.JLabel();
        textFieldSuaUsernameTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaUsernameTaiKhoan = new javax.swing.JLabel();
        labelLoiSuaMKTaiKhoan = new javax.swing.JLabel();
        labelSuaNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelLoiSuaNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelSuaGioiTinhTaiKhoan = new javax.swing.JLabel();
        labelSuaAnhDaiDienTaiKhoan = new javax.swing.JLabel();
        btnSuaAvatarTaiKhoan = new javax.swing.JButton();
        labelSuaDiaChiTaiKhoan = new javax.swing.JLabel();
        textFieldSuaDiaChiTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaDiaChiTaiKhoan = new javax.swing.JLabel();
        labelLoiSuaGioiTinhTaiKhoan = new javax.swing.JLabel();
        labelSuaPhanQuyenTaiKhoan = new javax.swing.JLabel();
        comboBoxSuaPhanQuyenTaiKhoan = new javax.swing.JComboBox<>();
        labelLoiSuaPhanQuyentaiKhoan = new javax.swing.JLabel();
        labelSuaLuongTaiKhoan = new javax.swing.JLabel();
        textFieldSuaLuongTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaLuongTaiKhoan = new javax.swing.JLabel();
        labelSuaSDTTaiKhoan = new javax.swing.JLabel();
        textFieldSuaSDTTaiKhoan = new javax.swing.JTextField();
        labelLoiSuaSDTTaiKhoan = new javax.swing.JLabel();
        btnHoanThanhSuaTaiKhoan = new javax.swing.JButton();
        radioSuaGioiTinhNamTaiKhoan = new javax.swing.JRadioButton();
        radioSuaGioiTinhNuTaiKhoan = new javax.swing.JRadioButton();
        dateChooserSuaNgaySinhTaiKhoan = new com.toedter.calendar.JDateChooser();
        frameXemChiTietTaiKhoan = new javax.swing.JFrame();
        labelTitleXemChiTietTaiKhoanForm = new javax.swing.JLabel();
        panelXemChiTietTaiKhoanContainer = new javax.swing.JPanel();
        labelXemChiTietHoTenTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietHoTenTaiKhoan = new javax.swing.JTextField();
        labelXemChiTietCCCdTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietCCCDTaiKhoan = new javax.swing.JTextField();
        labelXemChiTietUsernameTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietUsernameTaiKhoan = new javax.swing.JTextField();
        labelXemChiTietNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelXemChiTietGioiTinhTaiKhoan = new javax.swing.JLabel();
        labelXemChiTietAnhDaiDienTaiKhoan = new javax.swing.JLabel();
        labelXemChiTietDiaChiTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietDiaChiTaiKhoan = new javax.swing.JTextField();
        labelXemChiTietPhanQuyenTaiKhoan = new javax.swing.JLabel();
        comboBoxXemChiTietPhanQuyenTaiKhoan = new javax.swing.JComboBox<>();
        labelXemChiTietLuongTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietLuongTaiKhoan = new javax.swing.JTextField();
        labelXemChiTietSDTTaiKhoan = new javax.swing.JLabel();
        textFieldXemChiTietSDTTaiKhoan = new javax.swing.JTextField();
        radioXemChiTietGioiTinhNamTaiKhoan = new javax.swing.JRadioButton();
        radioXemChiTietGioiTinhNuTaiKhoan = new javax.swing.JRadioButton();
        labelXemNoiDungNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelXemMaTaiKhoan = new javax.swing.JLabel();
        labelXemNoiDungMaTaiKhoan = new javax.swing.JLabel();
        buttonGroupSuaGioiTinhTaiKhoan = new javax.swing.ButtonGroup();
        frameThemTaiKhoan = new javax.swing.JFrame();
        labelTitleThemTaiKhoanForm = new javax.swing.JLabel();
        panelThemTaiKhoanContainer = new javax.swing.JPanel();
        labelThemHoTenTaiKhoan = new javax.swing.JLabel();
        textFieldThemHoTenTaiKhoan = new javax.swing.JTextField();
        labelLoiThemHoTenTaiKhoan = new javax.swing.JLabel();
        labelThemCCCdTaiKhoan = new javax.swing.JLabel();
        textFieldThemCCCDTaiKhoan = new javax.swing.JTextField();
        labelLoiThemCCCDTaiKhoan = new javax.swing.JLabel();
        labelThemUsernameTaiKhoan = new javax.swing.JLabel();
        textFieldThemUsernameTaiKhoan = new javax.swing.JTextField();
        labelLoiThemUsernameTaiKhoan = new javax.swing.JLabel();
        labelThemMatKhauTaiKhoan = new javax.swing.JLabel();
        passwordFieldThemMKTaiKhoan = new javax.swing.JPasswordField();
        labelLoiThemMKTaiKhoan = new javax.swing.JLabel();
        labelThemNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelLoiThemNgaySinhTaiKhoan = new javax.swing.JLabel();
        labelThemGioiTinhTaiKhoan = new javax.swing.JLabel();
        labelThemAnhDaiDienTaiKhoan = new javax.swing.JLabel();
        btnThemAvatarTaiKhoan = new javax.swing.JButton();
        labelThemDiaChiTaiKhoan = new javax.swing.JLabel();
        textFieldThemDiaChiTaiKhoan = new javax.swing.JTextField();
        labelLoiThemDiaChiTaiKhoan = new javax.swing.JLabel();
        labelLoiThemGioiTinhTaiKhoan = new javax.swing.JLabel();
        labelThemPhanQuyenTaiKhoan = new javax.swing.JLabel();
        comboBoxThemPhanQuyenTaiKhoan = new javax.swing.JComboBox<>();
        labelLoiThemPhanQuyentaiKhoan = new javax.swing.JLabel();
        labelThemLuongTaiKhoan = new javax.swing.JLabel();
        textFieldThemLuongTaiKhoan = new javax.swing.JTextField();
        labelLoiThemLuongTaiKhoan = new javax.swing.JLabel();
        labelThemSDTTaiKhoan = new javax.swing.JLabel();
        textFieldThemSDTTaiKhoan = new javax.swing.JTextField();
        labelLoiThemSDTTaiKhoan = new javax.swing.JLabel();
        btnHoanThanhThemTaiKhoan = new javax.swing.JButton();
        radioThemGioiTinhNamTaiKhoan = new javax.swing.JRadioButton();
        radioThemGioiTinhNuTaiKhoan = new javax.swing.JRadioButton();
        checkBoxHienThemMatKhauTaiKhoan = new javax.swing.JCheckBox();
        dateChooserThemNgaySinhTaiKhoan = new com.toedter.calendar.JDateChooser();
        buttonGroupThemGioiTinhTaiKhoan = new javax.swing.ButtonGroup();
        panelQLNhanVienContent17 = new javax.swing.JPanel();
        labelTitleQLTaiKhoanContent17 = new javax.swing.JLabel();
        panelChucNangNhanVien17 = new javax.swing.JPanel();
        btnThemTaiKhoan17 = new javax.swing.JButton();
        btnXoaTaiKhoan = new javax.swing.JButton();
        btnSuaTaiKhoan = new javax.swing.JButton();
        btnXemChiTietTaiKhoan = new javax.swing.JButton();
        labelChucNangNhanVien = new javax.swing.JLabel();
        labelTimKiemNhanVien = new javax.swing.JLabel();
        panelTimKiemTaiKhoan = new javax.swing.JPanel();
        comboBoxTimTheoChucVu = new javax.swing.JComboBox<>();
        labelTimKiemTheoChucVu = new javax.swing.JLabel();
        labelTimKiemTaiKhoanTheoTen = new javax.swing.JLabel();
        textFieldTimKiemTaiKhoanTheoTen = new javax.swing.JTextField();
        btnTimKiemTaiKhoan = new javax.swing.JButton();
        btnHuyTimTaiKhoan = new javax.swing.JButton();
        scrollPanelDStaiKhoan = new javax.swing.JScrollPane();
        tableDSTaiKhoan = new javax.swing.JTable();

        frameSuaTaiKhoan.setTitle("Thay đổi thông tin tài khoản");
        frameSuaTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleSuaTaiKhoanForm.setBackground(new java.awt.Color(255, 152, 0));
        labelTitleSuaTaiKhoanForm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleSuaTaiKhoanForm.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleSuaTaiKhoanForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleSuaTaiKhoanForm.setText("THAY ĐỔI THÔNG TIN TÀI KHOẢN");
        labelTitleSuaTaiKhoanForm.setOpaque(true);

        panelSuaTaiKhoanContainer.setBackground(new java.awt.Color(255, 255, 255));

        labelSuaHoTenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaHoTenTaiKhoan.setText("Họ tên");
        labelSuaHoTenTaiKhoan.setOpaque(true);

        textFieldSuaHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaHoTenTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaHoTenTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaHoTenTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaCCCdTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaCCCdTaiKhoan.setText("CCCD");

        textFieldSuaCCCDTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaCCCDTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaCCCDTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaCCCDTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaCCCDTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaUsernameTaiKhoan.setText("Username");

        textFieldSuaUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaUsernameTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaUsernameTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaUsernameTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelLoiSuaMKTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaMKTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaNgaySinhTaiKhoan.setText("Ngày sinh");

        labelLoiSuaNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaNgaySinhTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaGioiTinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaGioiTinhTaiKhoan.setText("Giới tính");

        labelSuaAnhDaiDienTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelSuaAnhDaiDienTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSuaAnhDaiDienTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSuaAnhDaiDienTaiKhoan.setText("Ảnh đại diện");
        labelSuaAnhDaiDienTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        labelSuaAnhDaiDienTaiKhoan.setOpaque(true);

        btnSuaAvatarTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaAvatarTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaAvatarTaiKhoan.setText("Chọn ảnh");
        btnSuaAvatarTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaAvatarTaiKhoanMouseClicked(evt);
            }
        });

        labelSuaDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaDiaChiTaiKhoan.setText("Địa chỉ");

        textFieldSuaDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaDiaChiTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaDiaChiTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaDiaChiTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelLoiSuaGioiTinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaGioiTinhTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaPhanQuyenTaiKhoan.setText("Phân quyền");

        comboBoxSuaPhanQuyenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxSuaPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxSuaPhanQuyenTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMPLOYEE", "MANAGER" }));
        comboBoxSuaPhanQuyenTaiKhoan.setOpaque(true);

        labelLoiSuaPhanQuyentaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaPhanQuyentaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaLuongTaiKhoan.setText("Lương");

        textFieldSuaLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaLuongTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaLuongTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaLuongTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelSuaSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelSuaSDTTaiKhoan.setText("Số điện thoại");

        textFieldSuaSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldSuaSDTTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldSuaSDTTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiSuaSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiSuaSDTTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        btnHoanThanhSuaTaiKhoan.setBackground(new java.awt.Color(255, 152, 0));
        btnHoanThanhSuaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaTaiKhoan.setText("THAY ĐỔI");
        btnHoanThanhSuaTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaTaiKhoanMouseClicked(evt);
            }
        });

        radioSuaGioiTinhNamTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupSuaGioiTinhTaiKhoan.add(radioSuaGioiTinhNamTaiKhoan);
        radioSuaGioiTinhNamTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioSuaGioiTinhNamTaiKhoan.setText("Nam");
        radioSuaGioiTinhNamTaiKhoan.setOpaque(true);

        radioSuaGioiTinhNuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupSuaGioiTinhTaiKhoan.add(radioSuaGioiTinhNuTaiKhoan);
        radioSuaGioiTinhNuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioSuaGioiTinhNuTaiKhoan.setText("Nữ");
        radioSuaGioiTinhNuTaiKhoan.setOpaque(true);

        javax.swing.GroupLayout panelSuaTaiKhoanContainerLayout = new javax.swing.GroupLayout(panelSuaTaiKhoanContainer);
        panelSuaTaiKhoanContainer.setLayout(panelSuaTaiKhoanContainerLayout);
        panelSuaTaiKhoanContainerLayout.setHorizontalGroup(
            panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(btnHoanThanhSuaTaiKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelSuaPhanQuyenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(labelSuaGioiTinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaNgaySinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaUsernameTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaCCCdTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuaHoTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserSuaNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaPhanQuyentaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaGioiTinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaUsernameTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaCCCDTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaHoTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiSuaMKTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textFieldSuaHoTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(textFieldSuaCCCDTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textFieldSuaUsernameTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelLoiSuaNgaySinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(comboBoxSuaPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                                .addComponent(radioSuaGioiTinhNamTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioSuaGioiTinhNuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSuaAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaAvatarTaiKhoan)
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(labelLoiSuaLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelSuaLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldSuaLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelSuaSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiSuaSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldSuaSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelSuaDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiSuaDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldSuaDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(89, 89, 89))
        );
        panelSuaTaiKhoanContainerLayout.setVerticalGroup(
            panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSuaHoTenTaiKhoan)
                            .addComponent(textFieldSuaHoTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiSuaHoTenTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSuaCCCdTaiKhoan)
                            .addComponent(textFieldSuaCCCDTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiSuaCCCDTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSuaUsernameTaiKhoan)
                            .addComponent(textFieldSuaUsernameTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiSuaUsernameTaiKhoan))
                    .addComponent(labelSuaAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSuaTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(labelLoiSuaMKTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelSuaNgaySinhTaiKhoan)
                                .addComponent(labelSuaLuongTaiKhoan)
                                .addComponent(textFieldSuaLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateChooserSuaNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLoiSuaNgaySinhTaiKhoan)
                            .addComponent(labelLoiSuaLuongTaiKhoan))
                        .addGap(18, 18, 18)
                        .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSuaGioiTinhTaiKhoan)
                            .addComponent(labelSuaSDTTaiKhoan)
                            .addComponent(textFieldSuaSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioSuaGioiTinhNamTaiKhoan)
                            .addComponent(radioSuaGioiTinhNuTaiKhoan)))
                    .addComponent(btnSuaAvatarTaiKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLoiSuaGioiTinhTaiKhoan)
                    .addComponent(labelLoiSuaSDTTaiKhoan))
                .addGap(18, 18, 18)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSuaPhanQuyenTaiKhoan)
                    .addComponent(comboBoxSuaPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSuaDiaChiTaiKhoan)
                    .addComponent(textFieldSuaDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSuaTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLoiSuaPhanQuyentaiKhoan)
                    .addComponent(labelLoiSuaDiaChiTaiKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnHoanThanhSuaTaiKhoan)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout frameSuaTaiKhoanLayout = new javax.swing.GroupLayout(frameSuaTaiKhoan.getContentPane());
        frameSuaTaiKhoan.getContentPane().setLayout(frameSuaTaiKhoanLayout);
        frameSuaTaiKhoanLayout.setHorizontalGroup(
            frameSuaTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameSuaTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleSuaTaiKhoanForm, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addComponent(panelSuaTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        frameSuaTaiKhoanLayout.setVerticalGroup(
            frameSuaTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSuaTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleSuaTaiKhoanForm, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSuaTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameXemChiTietTaiKhoan.setTitle("Xem thông tin tài khoản");
        frameXemChiTietTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemChiTietTaiKhoanForm.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleXemChiTietTaiKhoanForm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemChiTietTaiKhoanForm.setForeground(new java.awt.Color(204, 0, 204));
        labelTitleXemChiTietTaiKhoanForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemChiTietTaiKhoanForm.setText("XEM THÔNG TIN TÀI KHOẢN");
        labelTitleXemChiTietTaiKhoanForm.setOpaque(true);

        panelXemChiTietTaiKhoanContainer.setBackground(new java.awt.Color(255, 255, 255));

        labelXemChiTietHoTenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelXemChiTietHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietHoTenTaiKhoan.setText("Họ tên");
        labelXemChiTietHoTenTaiKhoan.setOpaque(true);

        textFieldXemChiTietHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietHoTenTaiKhoan.setBorder(null);

        labelXemChiTietCCCdTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietCCCdTaiKhoan.setText("CCCD");

        textFieldXemChiTietCCCDTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietCCCDTaiKhoan.setBorder(null);

        labelXemChiTietUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietUsernameTaiKhoan.setText("Username");

        textFieldXemChiTietUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietUsernameTaiKhoan.setBorder(null);

        labelXemChiTietNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietNgaySinhTaiKhoan.setText("Ngày sinh");

        labelXemChiTietGioiTinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietGioiTinhTaiKhoan.setText("Giới tính");

        labelXemChiTietAnhDaiDienTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelXemChiTietAnhDaiDienTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelXemChiTietAnhDaiDienTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelXemChiTietAnhDaiDienTaiKhoan.setText("Ảnh");
        labelXemChiTietAnhDaiDienTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemChiTietAnhDaiDienTaiKhoan.setOpaque(true);

        labelXemChiTietDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietDiaChiTaiKhoan.setText("Địa chỉ");

        textFieldXemChiTietDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietDiaChiTaiKhoan.setBorder(null);

        labelXemChiTietPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietPhanQuyenTaiKhoan.setText("Phân quyền");

        comboBoxXemChiTietPhanQuyenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxXemChiTietPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxXemChiTietPhanQuyenTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));
        comboBoxXemChiTietPhanQuyenTaiKhoan.setOpaque(true);

        labelXemChiTietLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietLuongTaiKhoan.setText("Lương");

        textFieldXemChiTietLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietLuongTaiKhoan.setBorder(null);

        labelXemChiTietSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemChiTietSDTTaiKhoan.setText("Số điện thoại");

        textFieldXemChiTietSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemChiTietSDTTaiKhoan.setBorder(null);

        radioXemChiTietGioiTinhNamTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        radioXemChiTietGioiTinhNamTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioXemChiTietGioiTinhNamTaiKhoan.setText("Nam");
        radioXemChiTietGioiTinhNamTaiKhoan.setOpaque(true);

        radioXemChiTietGioiTinhNuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        radioXemChiTietGioiTinhNuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioXemChiTietGioiTinhNuTaiKhoan.setText("Nữ");
        radioXemChiTietGioiTinhNuTaiKhoan.setOpaque(true);

        labelXemNoiDungNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNgaySinhTaiKhoan.setText("01/01/2001");

        labelXemMaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaTaiKhoan.setText("Mã tài khoản");

        labelXemNoiDungMaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaTaiKhoan.setText("0");

        javax.swing.GroupLayout panelXemChiTietTaiKhoanContainerLayout = new javax.swing.GroupLayout(panelXemChiTietTaiKhoanContainer);
        panelXemChiTietTaiKhoanContainer.setLayout(panelXemChiTietTaiKhoanContainerLayout);
        panelXemChiTietTaiKhoanContainerLayout.setHorizontalGroup(
            panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelXemChiTietPhanQuyenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietGioiTinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietNgaySinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietUsernameTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietCCCdTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemChiTietHoTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldXemChiTietCCCDTaiKhoan)
                            .addComponent(textFieldXemChiTietHoTenTaiKhoan)
                            .addComponent(textFieldXemChiTietUsernameTaiKhoan)
                            .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxXemChiTietPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                                        .addComponent(radioXemChiTietGioiTinhNamTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioXemChiTietGioiTinhNuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelXemNoiDungNgaySinhTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelXemMaTaiKhoan)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemNoiDungMaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelXemChiTietSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemChiTietSDTTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelXemChiTietDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemChiTietDiaChiTaiKhoan))
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelXemChiTietAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelXemChiTietLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldXemChiTietLuongTaiKhoan)))
                .addContainerGap())
        );
        panelXemChiTietTaiKhoanContainerLayout.setVerticalGroup(
            panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelXemMaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelXemNoiDungMaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelXemChiTietHoTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldXemChiTietHoTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelXemChiTietCCCdTaiKhoan)
                            .addComponent(textFieldXemChiTietCCCDTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelXemChiTietUsernameTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldXemChiTietUsernameTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelXemChiTietAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelXemNoiDungNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelXemChiTietLuongTaiKhoan)
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(textFieldXemChiTietLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelXemChiTietNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelXemChiTietGioiTinhTaiKhoan)
                        .addComponent(labelXemChiTietSDTTaiKhoan)
                        .addComponent(radioXemChiTietGioiTinhNamTaiKhoan)
                        .addComponent(radioXemChiTietGioiTinhNuTaiKhoan))
                    .addGroup(panelXemChiTietTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(textFieldXemChiTietSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemChiTietPhanQuyenTaiKhoan)
                    .addComponent(comboBoxXemChiTietPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelXemChiTietDiaChiTaiKhoan)
                    .addComponent(textFieldXemChiTietDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameXemChiTietTaiKhoanLayout = new javax.swing.GroupLayout(frameXemChiTietTaiKhoan.getContentPane());
        frameXemChiTietTaiKhoan.getContentPane().setLayout(frameXemChiTietTaiKhoanLayout);
        frameXemChiTietTaiKhoanLayout.setHorizontalGroup(
            frameXemChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemChiTietTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameXemChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleXemChiTietTaiKhoanForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelXemChiTietTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        frameXemChiTietTaiKhoanLayout.setVerticalGroup(
            frameXemChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemChiTietTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleXemChiTietTaiKhoanForm, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelXemChiTietTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelTitleThemTaiKhoanForm.setBackground(new java.awt.Color(16, 185, 129));
        labelTitleThemTaiKhoanForm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleThemTaiKhoanForm.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleThemTaiKhoanForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleThemTaiKhoanForm.setText("THÊM TÀI KHOẢN");
        labelTitleThemTaiKhoanForm.setOpaque(true);

        panelThemTaiKhoanContainer.setBackground(new java.awt.Color(255, 255, 255));

        labelThemHoTenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelThemHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemHoTenTaiKhoan.setText("Họ tên");
        labelThemHoTenTaiKhoan.setOpaque(true);

        textFieldThemHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemHoTenTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemHoTenTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemHoTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemHoTenTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemCCCdTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemCCCdTaiKhoan.setText("CCCD");

        textFieldThemCCCDTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemCCCDTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemCCCDTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemCCCDTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemCCCDTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemUsernameTaiKhoan.setText("Username");

        textFieldThemUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemUsernameTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemUsernameTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemUsernameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemUsernameTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemMatKhauTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemMatKhauTaiKhoan.setText("Mật khẩu");

        passwordFieldThemMKTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        passwordFieldThemMKTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldThemMKTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemMKTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemMKTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemNgaySinhTaiKhoan.setText("Ngày sinh");

        labelLoiThemNgaySinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemNgaySinhTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemGioiTinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemGioiTinhTaiKhoan.setText("Giới tính");

        labelThemAnhDaiDienTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelThemAnhDaiDienTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelThemAnhDaiDienTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelThemAnhDaiDienTaiKhoan.setText("Ảnh đại diện");
        labelThemAnhDaiDienTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        labelThemAnhDaiDienTaiKhoan.setOpaque(true);

        btnThemAvatarTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnThemAvatarTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemAvatarTaiKhoan.setText("Chọn ảnh");
        btnThemAvatarTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemAvatarTaiKhoanMouseClicked(evt);
            }
        });

        labelThemDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemDiaChiTaiKhoan.setText("Địa chỉ");

        textFieldThemDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemDiaChiTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemDiaChiTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemDiaChiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemDiaChiTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelLoiThemGioiTinhTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemGioiTinhTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemPhanQuyenTaiKhoan.setText("Phân quyền");

        comboBoxThemPhanQuyenTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxThemPhanQuyenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxThemPhanQuyenTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMPLOYEE", "MANAGER" }));
        comboBoxThemPhanQuyenTaiKhoan.setOpaque(true);

        labelLoiThemPhanQuyentaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemPhanQuyentaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemLuongTaiKhoan.setText("Lương");

        textFieldThemLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemLuongTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemLuongTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemLuongTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemLuongTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        labelThemSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemSDTTaiKhoan.setText("Số điện thoại");

        textFieldThemSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemSDTTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemSDTTaiKhoanKeyPressed(evt);
            }
        });

        labelLoiThemSDTTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiThemSDTTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));

        btnHoanThanhThemTaiKhoan.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemTaiKhoan.setText("THÊM");
        btnHoanThanhThemTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemTaiKhoanMouseClicked(evt);
            }
        });

        radioThemGioiTinhNamTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupThemGioiTinhTaiKhoan.add(radioThemGioiTinhNamTaiKhoan);
        radioThemGioiTinhNamTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioThemGioiTinhNamTaiKhoan.setText("Nam");
        radioThemGioiTinhNamTaiKhoan.setOpaque(true);

        radioThemGioiTinhNuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupThemGioiTinhTaiKhoan.add(radioThemGioiTinhNuTaiKhoan);
        radioThemGioiTinhNuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        radioThemGioiTinhNuTaiKhoan.setText("Nữ");
        radioThemGioiTinhNuTaiKhoan.setOpaque(true);

        checkBoxHienThemMatKhauTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxHienThemMatKhauTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkBoxHienThemMatKhauTaiKhoan.setText("Hiện");
        checkBoxHienThemMatKhauTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxHienThemMatKhauTaiKhoanMouseClicked(evt);
            }
        });

        dateChooserThemNgaySinhTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelThemTaiKhoanContainerLayout = new javax.swing.GroupLayout(panelThemTaiKhoanContainer);
        panelThemTaiKhoanContainer.setLayout(panelThemTaiKhoanContainerLayout);
        panelThemTaiKhoanContainerLayout.setHorizontalGroup(
            panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(btnHoanThanhThemTaiKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelThemPhanQuyenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(labelThemGioiTinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemNgaySinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemMatKhauTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemUsernameTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemCCCdTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemHoTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserThemNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemPhanQuyentaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemGioiTinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemUsernameTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemCCCDTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemHoTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoiThemMKTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textFieldThemHoTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(textFieldThemCCCDTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textFieldThemUsernameTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelLoiThemNgaySinhTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(passwordFieldThemMKTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(comboBoxThemPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                                .addComponent(radioThemGioiTinhNamTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioThemGioiTinhNuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkBoxHienThemMatKhauTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelThemAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemAvatarTaiKhoan)
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(labelLoiThemLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelThemLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldThemLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelThemSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiThemSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldThemSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addComponent(labelThemDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiThemDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldThemDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(89, 89, 89))
        );
        panelThemTaiKhoanContainerLayout.setVerticalGroup(
            panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelThemHoTenTaiKhoan)
                            .addComponent(textFieldThemHoTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiThemHoTenTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelThemCCCdTaiKhoan)
                            .addComponent(textFieldThemCCCDTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiThemCCCDTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelThemUsernameTaiKhoan)
                            .addComponent(textFieldThemUsernameTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiThemUsernameTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelThemMatKhauTaiKhoan)
                            .addComponent(passwordFieldThemMKTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxHienThemMatKhauTaiKhoan))
                    .addComponent(labelThemAnhDaiDienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThemTaiKhoanContainerLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(labelLoiThemMKTaiKhoan)
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelThemNgaySinhTaiKhoan)
                                .addComponent(labelThemLuongTaiKhoan)
                                .addComponent(textFieldThemLuongTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateChooserThemNgaySinhTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLoiThemNgaySinhTaiKhoan)
                            .addComponent(labelLoiThemLuongTaiKhoan))
                        .addGap(18, 18, 18)
                        .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelThemGioiTinhTaiKhoan)
                            .addComponent(labelThemSDTTaiKhoan)
                            .addComponent(textFieldThemSDTTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioThemGioiTinhNamTaiKhoan)
                            .addComponent(radioThemGioiTinhNuTaiKhoan)))
                    .addComponent(btnThemAvatarTaiKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLoiThemGioiTinhTaiKhoan)
                    .addComponent(labelLoiThemSDTTaiKhoan))
                .addGap(18, 18, 18)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelThemPhanQuyenTaiKhoan)
                    .addComponent(comboBoxThemPhanQuyenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelThemDiaChiTaiKhoan)
                    .addComponent(textFieldThemDiaChiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThemTaiKhoanContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLoiThemPhanQuyentaiKhoan)
                    .addComponent(labelLoiThemDiaChiTaiKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnHoanThanhThemTaiKhoan)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout frameThemTaiKhoanLayout = new javax.swing.GroupLayout(frameThemTaiKhoan.getContentPane());
        frameThemTaiKhoan.getContentPane().setLayout(frameThemTaiKhoanLayout);
        frameThemTaiKhoanLayout.setHorizontalGroup(
            frameThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitleThemTaiKhoanForm, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addComponent(panelThemTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        frameThemTaiKhoanLayout.setVerticalGroup(
            frameThemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameThemTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleThemTaiKhoanForm, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelThemTaiKhoanContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLNhanVienContent17.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLTaiKhoanContent17.setBackground(new java.awt.Color(248, 248, 255));
        labelTitleQLTaiKhoanContent17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitleQLTaiKhoanContent17.setText("QUẢN LÝ TÀI KHOẢN");
        labelTitleQLTaiKhoanContent17.setOpaque(true);

        panelChucNangNhanVien17.setBackground(new java.awt.Color(255, 255, 255));
        panelChucNangNhanVien17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnThemTaiKhoan17.setBackground(new java.awt.Color(255, 255, 255));
        btnThemTaiKhoan17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemTaiKhoan17.setForeground(new java.awt.Color(0, 153, 51));
        btnThemTaiKhoan17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemTaiKhoan17.setText("THÊM");
        btnThemTaiKhoan17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemTaiKhoan17.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemTaiKhoan17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTaiKhoan17MouseClicked(evt);
            }
        });

        btnXoaTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaTaiKhoan.setForeground(new java.awt.Color(204, 51, 0));
        btnXoaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_delete.png"))); // NOI18N
        btnXoaTaiKhoan.setText("XÓA");
        btnXoaTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnXoaTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaTaiKhoanMouseClicked(evt);
            }
        });

        btnSuaTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnSuaTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSuaTaiKhoan.setForeground(new java.awt.Color(255, 153, 0));
        btnSuaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_edit.png"))); // NOI18N
        btnSuaTaiKhoan.setText("SỬA");
        btnSuaTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnSuaTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTaiKhoanMouseClicked(evt);
            }
        });

        btnXemChiTietTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietTaiKhoan.setForeground(new java.awt.Color(153, 0, 153));
        btnXemChiTietTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_xemChiTiet_20px.png"))); // NOI18N
        btnXemChiTietTaiKhoan.setText("CHI TIẾT");
        btnXemChiTietTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietTaiKhoanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangNhanVien17Layout = new javax.swing.GroupLayout(panelChucNangNhanVien17);
        panelChucNangNhanVien17.setLayout(panelChucNangNhanVien17Layout);
        panelChucNangNhanVien17Layout.setHorizontalGroup(
            panelChucNangNhanVien17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNhanVien17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNhanVien17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChucNangNhanVien17Layout.createSequentialGroup()
                        .addComponent(btnXemChiTietTaiKhoan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelChucNangNhanVien17Layout.createSequentialGroup()
                        .addComponent(btnThemTaiKhoan17, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))))
        );
        panelChucNangNhanVien17Layout.setVerticalGroup(
            panelChucNangNhanVien17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangNhanVien17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucNangNhanVien17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemTaiKhoan17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemChiTietTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelChucNangNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelChucNangNhanVien.setForeground(new java.awt.Color(51, 0, 204));
        labelChucNangNhanVien.setText("Chức năng");

        labelTimKiemNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTimKiemNhanVien.setForeground(new java.awt.Color(51, 0, 204));
        labelTimKiemNhanVien.setText("Tìm kiếm");

        panelTimKiemTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        panelTimKiemTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        comboBoxTimTheoChucVu.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTimTheoChucVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimTheoChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nhân viên", "Quản lý" }));
        comboBoxTimTheoChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimTheoChucVuActionPerformed(evt);
            }
        });

        labelTimKiemTheoChucVu.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTheoChucVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTheoChucVu.setText("Tìm theo chức vụ");
        labelTimKiemTheoChucVu.setOpaque(true);

        labelTimKiemTaiKhoanTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTaiKhoanTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTaiKhoanTheoTen.setText("Tìm theo tên");
        labelTimKiemTaiKhoanTheoTen.setOpaque(true);

        textFieldTimKiemTaiKhoanTheoTen.setBackground(new java.awt.Color(255, 255, 255));
        textFieldTimKiemTaiKhoanTheoTen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        btnTimKiemTaiKhoan.setBackground(new java.awt.Color(0, 102, 255));
        btnTimKiemTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemTaiKhoan.setText("Tìm kiếm");
        btnTimKiemTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemTaiKhoanMouseClicked(evt);
            }
        });

        btnHuyTimTaiKhoan.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimTaiKhoan.setText("Hủy tìm");
        btnHuyTimTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimTaiKhoanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemTaiKhoanLayout = new javax.swing.GroupLayout(panelTimKiemTaiKhoan);
        panelTimKiemTaiKhoan.setLayout(panelTimKiemTaiKhoanLayout);
        panelTimKiemTaiKhoanLayout.setHorizontalGroup(
            panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTimKiemTheoChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(comboBoxTimTheoChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimKiemTaiKhoanTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTimKiemTaiKhoanLayout.createSequentialGroup()
                        .addComponent(textFieldTimKiemTaiKhoanTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemTaiKhoan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyTimTaiKhoan)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        panelTimKiemTaiKhoanLayout.setVerticalGroup(
            panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimKiemTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTimKiemTaiKhoanTheoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(labelTimKiemTheoChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTimKiemTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxTimTheoChucVu)
                    .addComponent(textFieldTimKiemTaiKhoanTheoTen)
                    .addComponent(btnHuyTimTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiemTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        scrollPanelDStaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        tableDSTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        tableDSTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPanelDStaiKhoan.setViewportView(tableDSTaiKhoan);

        javax.swing.GroupLayout panelQLNhanVienContent17Layout = new javax.swing.GroupLayout(panelQLNhanVienContent17);
        panelQLNhanVienContent17.setLayout(panelQLNhanVienContent17Layout);
        panelQLNhanVienContent17Layout.setHorizontalGroup(
            panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelQLNhanVienContent17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPanelDStaiKhoan)
                    .addGroup(panelQLNhanVienContent17Layout.createSequentialGroup()
                        .addGroup(panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelChucNangNhanVien17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelChucNangNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelTimKiemTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTimKiemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQLNhanVienContent17Layout.createSequentialGroup()
                        .addComponent(labelTitleQLTaiKhoanContent17)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(257, 257, 257))
        );
        panelQLNhanVienContent17Layout.setVerticalGroup(
            panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNhanVienContent17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLTaiKhoanContent17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelChucNangNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTimKiemNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelQLNhanVienContent17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTimKiemTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChucNangNhanVien17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(scrollPanelDStaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelQLNhanVienContent17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLNhanVienContent17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemTaiKhoan17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTaiKhoan17MouseClicked
        // hiển thị form thêm mới 1 Account
//        hienThiFormThemAccount();
        this.frameThemTaiKhoan.setSize(920, 800);
        this.frameThemTaiKhoan.setLocationRelativeTo(null);
        this.frameThemTaiKhoan.setVisible(true);
        resetFrameThemTaiKhoan();
        this.frameThemTaiKhoan.getContentPane().setBackground(Color.WHITE);

    }//GEN-LAST:event_btnThemTaiKhoan17MouseClicked

    private void btnXoaTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanMouseClicked
        xoaTaiKhoan(this.tableDSTaiKhoan, this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
    }//GEN-LAST:event_btnXoaTaiKhoanMouseClicked

    private void btnSuaTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTaiKhoanMouseClicked
        hienThiFormSuaAccount();
        resetLabelLoiTrongFrameSuaThongTinTaiKhoan();
    }//GEN-LAST:event_btnSuaTaiKhoanMouseClicked

    private void btnXemChiTietTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietTaiKhoanMouseClicked
        int row = this.tableDSTaiKhoan.getSelectedRow();
        if (row >= 0) {
            this.frameXemChiTietTaiKhoan.setSize(900, 750);
            this.frameXemChiTietTaiKhoan.setLocationRelativeTo(null);
            this.frameXemChiTietTaiKhoan.setVisible(true);
            this.frameXemChiTietTaiKhoan.getContentPane().setBackground(Color.WHITE);
            hienThiThongTinChiTietTaiKhoan(this.tableDSTaiKhoan, this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản để xem", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemChiTietTaiKhoanMouseClicked

    private void btnSuaAvatarTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaAvatarTaiKhoanMouseClicked
        // TODO add your handling code here:
        // khi ta bấm nút này nó sẽ hiển thị hình ảnh được chon ra labelSuaAnhDaiDienTaiKhoan
        // đồng thời nó cx lấy ra đường dẫn ủa file và lưu vào this.tempImageName

//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                this.tempImageNameFormSuaTaiKhoan = tenAnh;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaAnhDaiDienTaiKhoan.getWidth(), this.labelSuaAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                this.labelSuaAnhDaiDienTaiKhoan.setIcon(imageIcon);
//            }
//        }
        JFileChooser fileDuocChon = new JFileChooser();
        int returnVal = fileDuocChon.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File anhDuocChon = fileDuocChon.getSelectedFile();
            String tenAnh = anhDuocChon.getAbsolutePath();
            if (tenAnh != null) {
//                String[] parts = tenAnh.split("[\\\\/]");
//                String relativePath = parts[parts.length - 2] + "\\" + parts[parts.length - 1];
                String[] parts = tenAnh.split("[\\\\/]");
                String relativePath = "/" + parts[parts.length - 3] + "/" + parts[parts.length - 2] + "/" + parts[parts.length - 1];
                this.tempImageNameFormSuaTaiKhoan = relativePath;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelSuaAnhDaiDienTaiKhoan.getWidth(), this.labelSuaAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
                this.labelSuaAnhDaiDienTaiKhoan.setIcon(imageIcon);
            }
        }
    }//GEN-LAST:event_btnSuaAvatarTaiKhoanMouseClicked

    private void btnHoanThanhSuaTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaTaiKhoanMouseClicked
        // TODO add your handling code here:
        int row = this.tableDSTaiKhoan.getSelectedRow();
        if (row >= 0) {
            suaThongTinTaiKhoan(this.tableDSTaiKhoan, this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
        }
    }//GEN-LAST:event_btnHoanThanhSuaTaiKhoanMouseClicked

    private void btnThemAvatarTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemAvatarTaiKhoanMouseClicked
        // TODO add your handling code here:
//        JFileChooser fileDuocChon = new JFileChooser();
//        int returnVal = fileDuocChon.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File anhDuocChon = fileDuocChon.getSelectedFile();
//            String tenAnh = anhDuocChon.getAbsolutePath();
//            if (tenAnh != null) {
//                this.tempImageName = tenAnh;
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelThemAnhDaiDienTaiKhoan.getWidth(), this.labelThemAnhDaiDienTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                this.labelThemAnhDaiDienTaiKhoan.setIcon(imageIcon);
//            }
//        }
        hienThiAvatarAccountTrongFrameThemTaiKhoan();
    }//GEN-LAST:event_btnThemAvatarTaiKhoanMouseClicked

    private void btnHoanThanhThemTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemTaiKhoanMouseClicked
        // TODO add your handling code here:
        themTaiKhoan(this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
    }//GEN-LAST:event_btnHoanThanhThemTaiKhoanMouseClicked

    private void btnTimKiemTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemTaiKhoanMouseClicked
        // TODO add your handling code here:
        timKiemTaiKhoanTheoTen(this.tableDSTaiKhoan, this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
    }//GEN-LAST:event_btnTimKiemTaiKhoanMouseClicked

    private void btnHuyTimTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimTaiKhoanMouseClicked
        // TODO add your handling code here:
        getAllAccount(this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
        this.textFieldTimKiemTaiKhoanTheoTen.setText("");
    }//GEN-LAST:event_btnHuyTimTaiKhoanMouseClicked

    private void checkBoxHienThemMatKhauTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxHienThemMatKhauTaiKhoanMouseClicked
        // TODO add your handling code here:
        hienThiMatKhau(this.passwordFieldThemMKTaiKhoan, this.checkBoxHienThemMatKhauTaiKhoan);
    }//GEN-LAST:event_checkBoxHienThemMatKhauTaiKhoanMouseClicked

    private void comboBoxTimTheoChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimTheoChucVuActionPerformed
        // TODO add your handling code here:
        timTaiKhoanTheoPhanQuyen(this.defaultTableModelDSTaiKhoan, this.dsTaiKhoan);
    }//GEN-LAST:event_comboBoxTimTheoChucVuActionPerformed

    private void textFieldThemHoTenTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemHoTenTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemHoTenTaiKhoan.getText().equals("")) {
            this.labelLoiThemHoTenTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemHoTenTaiKhoanKeyPressed

    private void textFieldThemCCCDTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemCCCDTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemCCCDTaiKhoan.getText().equals("")) {
            this.labelLoiThemCCCDTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemCCCDTaiKhoanKeyPressed

    private void textFieldThemUsernameTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemUsernameTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemUsernameTaiKhoan.getText().equals("")) {
            this.labelLoiThemUsernameTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemUsernameTaiKhoanKeyPressed

    private void passwordFieldThemMKTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldThemMKTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemMKTaiKhoan.getText().equals("")) {
            this.labelLoiThemMKTaiKhoan.setText("");
        }
    }//GEN-LAST:event_passwordFieldThemMKTaiKhoanKeyPressed

    private void textFieldThemLuongTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemLuongTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemLuongTaiKhoan.getText().equals("")) {
            this.labelLoiThemLuongTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemLuongTaiKhoanKeyPressed

    private void textFieldThemSDTTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemSDTTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemSDTTaiKhoan.getText().equals("")) {
            this.labelLoiThemSDTTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemSDTTaiKhoanKeyPressed

    private void textFieldThemDiaChiTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemDiaChiTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemDiaChiTaiKhoan.getText().equals("")) {
            this.labelLoiThemDiaChiTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldThemDiaChiTaiKhoanKeyPressed

    private void textFieldSuaHoTenTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaHoTenTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaHoTenTaiKhoan.getText().equals("")) {
            this.labelLoiSuaHoTenTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaHoTenTaiKhoanKeyPressed

    private void textFieldSuaCCCDTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaCCCDTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaCCCDTaiKhoan.getText().equals("")) {
            this.labelLoiSuaCCCDTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaCCCDTaiKhoanKeyPressed

    private void textFieldSuaUsernameTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaUsernameTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaUsernameTaiKhoan.getText().equals("")) {
            this.labelLoiSuaUsernameTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaUsernameTaiKhoanKeyPressed

    private void textFieldSuaLuongTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaLuongTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaLuongTaiKhoan.getText().equals("")) {
            this.labelLoiSuaLuongTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaLuongTaiKhoanKeyPressed

    private void textFieldSuaSDTTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaSDTTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaSDTTaiKhoan.getText().equals("")) {
            this.labelLoiSuaSDTTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaSDTTaiKhoanKeyPressed

    private void textFieldSuaDiaChiTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuaDiaChiTaiKhoanKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiSuaDiaChiTaiKhoan.getText().equals("")) {
            this.labelLoiSuaDiaChiTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldSuaDiaChiTaiKhoanKeyPressed

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
            java.util.logging.Logger.getLogger(QLTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaTaiKhoan;
    private javax.swing.JButton btnHoanThanhThemTaiKhoan;
    private javax.swing.JButton btnHuyTimTaiKhoan;
    private javax.swing.JButton btnSuaAvatarTaiKhoan;
    private javax.swing.JButton btnSuaTaiKhoan;
    private javax.swing.JButton btnThemAvatarTaiKhoan;
    private javax.swing.JButton btnThemTaiKhoan17;
    private javax.swing.JButton btnTimKiemTaiKhoan;
    private javax.swing.JButton btnXemChiTietTaiKhoan;
    private javax.swing.JButton btnXoaTaiKhoan;
    private javax.swing.ButtonGroup buttonGroupSuaGioiTinhTaiKhoan;
    private javax.swing.ButtonGroup buttonGroupThemGioiTinhTaiKhoan;
    private javax.swing.JCheckBox checkBoxHienThemMatKhauTaiKhoan;
    private javax.swing.JComboBox<String> comboBoxSuaPhanQuyenTaiKhoan;
    private javax.swing.JComboBox<String> comboBoxThemPhanQuyenTaiKhoan;
    private javax.swing.JComboBox<String> comboBoxTimTheoChucVu;
    private javax.swing.JComboBox<String> comboBoxXemChiTietPhanQuyenTaiKhoan;
    private com.toedter.calendar.JDateChooser dateChooserSuaNgaySinhTaiKhoan;
    private com.toedter.calendar.JDateChooser dateChooserThemNgaySinhTaiKhoan;
    private javax.swing.JFrame frameSuaTaiKhoan;
    private javax.swing.JFrame frameThemTaiKhoan;
    private javax.swing.JFrame frameXemChiTietTaiKhoan;
    private javax.swing.JLabel labelChucNangNhanVien;
    private javax.swing.JLabel labelLoiSuaCCCDTaiKhoan;
    private javax.swing.JLabel labelLoiSuaDiaChiTaiKhoan;
    private javax.swing.JLabel labelLoiSuaGioiTinhTaiKhoan;
    private javax.swing.JLabel labelLoiSuaHoTenTaiKhoan;
    private javax.swing.JLabel labelLoiSuaLuongTaiKhoan;
    private javax.swing.JLabel labelLoiSuaMKTaiKhoan;
    private javax.swing.JLabel labelLoiSuaNgaySinhTaiKhoan;
    private javax.swing.JLabel labelLoiSuaPhanQuyentaiKhoan;
    private javax.swing.JLabel labelLoiSuaSDTTaiKhoan;
    private javax.swing.JLabel labelLoiSuaUsernameTaiKhoan;
    private javax.swing.JLabel labelLoiThemCCCDTaiKhoan;
    private javax.swing.JLabel labelLoiThemDiaChiTaiKhoan;
    private javax.swing.JLabel labelLoiThemGioiTinhTaiKhoan;
    private javax.swing.JLabel labelLoiThemHoTenTaiKhoan;
    private javax.swing.JLabel labelLoiThemLuongTaiKhoan;
    private javax.swing.JLabel labelLoiThemMKTaiKhoan;
    private javax.swing.JLabel labelLoiThemNgaySinhTaiKhoan;
    private javax.swing.JLabel labelLoiThemPhanQuyentaiKhoan;
    private javax.swing.JLabel labelLoiThemSDTTaiKhoan;
    private javax.swing.JLabel labelLoiThemUsernameTaiKhoan;
    private javax.swing.JLabel labelSuaAnhDaiDienTaiKhoan;
    private javax.swing.JLabel labelSuaCCCdTaiKhoan;
    private javax.swing.JLabel labelSuaDiaChiTaiKhoan;
    private javax.swing.JLabel labelSuaGioiTinhTaiKhoan;
    private javax.swing.JLabel labelSuaHoTenTaiKhoan;
    private javax.swing.JLabel labelSuaLuongTaiKhoan;
    private javax.swing.JLabel labelSuaNgaySinhTaiKhoan;
    private javax.swing.JLabel labelSuaPhanQuyenTaiKhoan;
    private javax.swing.JLabel labelSuaSDTTaiKhoan;
    private javax.swing.JLabel labelSuaUsernameTaiKhoan;
    private javax.swing.JLabel labelThemAnhDaiDienTaiKhoan;
    private javax.swing.JLabel labelThemCCCdTaiKhoan;
    private javax.swing.JLabel labelThemDiaChiTaiKhoan;
    private javax.swing.JLabel labelThemGioiTinhTaiKhoan;
    private javax.swing.JLabel labelThemHoTenTaiKhoan;
    private javax.swing.JLabel labelThemLuongTaiKhoan;
    private javax.swing.JLabel labelThemMatKhauTaiKhoan;
    private javax.swing.JLabel labelThemNgaySinhTaiKhoan;
    private javax.swing.JLabel labelThemPhanQuyenTaiKhoan;
    private javax.swing.JLabel labelThemSDTTaiKhoan;
    private javax.swing.JLabel labelThemUsernameTaiKhoan;
    private javax.swing.JLabel labelTimKiemNhanVien;
    private javax.swing.JLabel labelTimKiemTaiKhoanTheoTen;
    private javax.swing.JLabel labelTimKiemTheoChucVu;
    private javax.swing.JLabel labelTitleQLTaiKhoanContent17;
    private javax.swing.JLabel labelTitleSuaTaiKhoanForm;
    private javax.swing.JLabel labelTitleThemTaiKhoanForm;
    private javax.swing.JLabel labelTitleXemChiTietTaiKhoanForm;
    private javax.swing.JLabel labelXemChiTietAnhDaiDienTaiKhoan;
    private javax.swing.JLabel labelXemChiTietCCCdTaiKhoan;
    private javax.swing.JLabel labelXemChiTietDiaChiTaiKhoan;
    private javax.swing.JLabel labelXemChiTietGioiTinhTaiKhoan;
    private javax.swing.JLabel labelXemChiTietHoTenTaiKhoan;
    private javax.swing.JLabel labelXemChiTietLuongTaiKhoan;
    private javax.swing.JLabel labelXemChiTietNgaySinhTaiKhoan;
    private javax.swing.JLabel labelXemChiTietPhanQuyenTaiKhoan;
    private javax.swing.JLabel labelXemChiTietSDTTaiKhoan;
    private javax.swing.JLabel labelXemChiTietUsernameTaiKhoan;
    private javax.swing.JLabel labelXemMaTaiKhoan;
    private javax.swing.JLabel labelXemNoiDungMaTaiKhoan;
    private javax.swing.JLabel labelXemNoiDungNgaySinhTaiKhoan;
    private javax.swing.JPanel panelChucNangNhanVien17;
    private javax.swing.JPanel panelQLNhanVienContent17;
    private javax.swing.JPanel panelSuaTaiKhoanContainer;
    private javax.swing.JPanel panelThemTaiKhoanContainer;
    private javax.swing.JPanel panelTimKiemTaiKhoan;
    private javax.swing.JPanel panelXemChiTietTaiKhoanContainer;
    private javax.swing.JPasswordField passwordFieldThemMKTaiKhoan;
    private javax.swing.JRadioButton radioSuaGioiTinhNamTaiKhoan;
    public javax.swing.JRadioButton radioSuaGioiTinhNuTaiKhoan;
    private javax.swing.JRadioButton radioThemGioiTinhNamTaiKhoan;
    public javax.swing.JRadioButton radioThemGioiTinhNuTaiKhoan;
    private javax.swing.JRadioButton radioXemChiTietGioiTinhNamTaiKhoan;
    public javax.swing.JRadioButton radioXemChiTietGioiTinhNuTaiKhoan;
    private javax.swing.JScrollPane scrollPanelDStaiKhoan;
    private javax.swing.JTable tableDSTaiKhoan;
    private javax.swing.JTextField textFieldSuaCCCDTaiKhoan;
    private javax.swing.JTextField textFieldSuaDiaChiTaiKhoan;
    private javax.swing.JTextField textFieldSuaHoTenTaiKhoan;
    private javax.swing.JTextField textFieldSuaLuongTaiKhoan;
    private javax.swing.JTextField textFieldSuaSDTTaiKhoan;
    private javax.swing.JTextField textFieldSuaUsernameTaiKhoan;
    private javax.swing.JTextField textFieldThemCCCDTaiKhoan;
    private javax.swing.JTextField textFieldThemDiaChiTaiKhoan;
    private javax.swing.JTextField textFieldThemHoTenTaiKhoan;
    private javax.swing.JTextField textFieldThemLuongTaiKhoan;
    private javax.swing.JTextField textFieldThemSDTTaiKhoan;
    private javax.swing.JTextField textFieldThemUsernameTaiKhoan;
    private javax.swing.JTextField textFieldTimKiemTaiKhoanTheoTen;
    private javax.swing.JTextField textFieldXemChiTietCCCDTaiKhoan;
    private javax.swing.JTextField textFieldXemChiTietDiaChiTaiKhoan;
    private javax.swing.JTextField textFieldXemChiTietHoTenTaiKhoan;
    private javax.swing.JTextField textFieldXemChiTietLuongTaiKhoan;
    private javax.swing.JTextField textFieldXemChiTietSDTTaiKhoan;
    private javax.swing.JTextField textFieldXemChiTietUsernameTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
