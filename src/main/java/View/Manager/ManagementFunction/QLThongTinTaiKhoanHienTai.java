/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import static Controller.LogRes.ManagerEmployee.Login.ManagerEmployee;
import Dao.AccountDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import Model.Manager.Account;
import Utils.Manager.EncryptPassword;

/**
 *
 * @author Admin
 */
public class QLThongTinTaiKhoanHienTai extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLThongTinTaiKhoanHienTai
     */
    public static Map<String, Object> infoManager = new HashMap<>();

    public Account account;
    public String imgAccount;
    public AccountDAO accountDAO;

    public QLThongTinTaiKhoanHienTai() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.account = new Account();
        this.accountDAO = new AccountDAO();

        this.account = getCurrentAccount();
        this.imgAccount = this.account.getAvatar();

        this.labelAnhDaiDienChuTaiKhoan.setSize(215, 260);
        hienThiThongTinTaiKhoanQuanLy(this.account);

        //System.out.println(EncryptPassword.encryptPassword(this.account.getPassword()));
        this.dateChooserXemNgaySinhTaiKhoanDangDangNhap.setDateFormatString("dd/MM/yyyy");
        this.dateChooserXemNgaySinhTaiKhoanDangDangNhap.setFont(new Font("Arial", Font.BOLD, 15));

        // labelAnhDaiDienChuTaiKhoan
    }

    public Account getCurrentAccount() {
        int idAccount = (Integer) ManagerEmployee.get("idAccount");
        Account a = this.accountDAO.getAccountByID(idAccount);
        return a;
    }

    public void hienThiThongTinTaiKhoanQuanLy(Account a) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.labelXemNoiDungMaTaiKhoanDangDangNhap.setText(a.getIdAccount() + "");
        this.textFieldXemHoTenTaiKhoanDangDangNhap.setText(a.getFullName());
        this.textFieldXemCCCDTaiKhoanDangDangNhap.setText(a.getCccd());
        this.textFieldXemUserNameTaiKhoanDangDangNhap.setText(a.getUserName());
        this.textFieldXemLuongtaiKhoanDangDangNhap.setText(a.getSalary() + "");
        int gioiTinh = a.getGender();
        if (gioiTinh == 1) {
            this.comboBoxXemGioiTinhTaiKhoanDangDangNhap.setSelectedItem("Nam");
        } else if (gioiTinh == 0) {
            this.comboBoxXemGioiTinhTaiKhoanDangDangNhap.setSelectedItem("Nữ");
        }
        this.textFieldXemSDTTaiKhoanDangDangNhap.setText(a.getNumberPhone());
        this.textFieldXemDiaChiTaiKhoanDangDangNhap.setText(a.getAddress());
        Date date = new Date();
        try {
            date = sdf.parse(a.getBirthday());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dateChooserXemNgaySinhTaiKhoanDangDangNhap.setDate(date);
        try {
            if (a.getAvatar() == null) {
                this.labelAnhDaiDienChuTaiKhoan.setIcon(null);
                this.imgAccount = "";
            } else {
//                String urlImage = "E:\\NhapMonCongNghePhanMem\\Code\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + a.getAvatar();
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(this.labelAnhDaiDienChuTaiKhoan.getWidth(), this.labelAnhDaiDienChuTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
//                this.labelAnhDaiDienChuTaiKhoan.setIcon(imageIcon);
                String urlImage = a.getAvatar();
                this.labelAnhDaiDienChuTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImage)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiAvatarAccount() {
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
                this.imgAccount = relativePath;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(this.labelAnhDaiDienChuTaiKhoan.getWidth(), this.labelAnhDaiDienChuTaiKhoan.getHeight(), Image.SCALE_SMOOTH));
                this.labelAnhDaiDienChuTaiKhoan.setIcon(imageIcon);
            }
        }
    }

    public void suaThongTinTaiKhoanQuanLy() {
        String regexSDT = "^0(\\d{9})$";
        String regexCCCD = "^\\d{12}$";
        String regexMatKhau = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        Pattern patternSDT = Pattern.compile(regexSDT);
        Matcher matcherSDT;
        Pattern patternCCCD = Pattern.compile(regexCCCD);
        Matcher matcherCCCD;
        Pattern patternMatKhau = Pattern.compile(regexMatKhau);
        Matcher matcherMatKhau;
        String thongBaoLoi = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fullName = this.textFieldXemHoTenTaiKhoanDangDangNhap.getText();
        String cccd = this.textFieldXemCCCDTaiKhoanDangDangNhap.getText();
        String userName = this.textFieldXemUserNameTaiKhoanDangDangNhap.getText();
        Date date = this.dateChooserXemNgaySinhTaiKhoanDangDangNhap.getDate();
        String birthday = sdf.format(date);
        String address = this.textFieldXemDiaChiTaiKhoanDangDangNhap.getText();
        int gender = 1;
        if (this.comboBoxXemGioiTinhTaiKhoanDangDangNhap.getSelectedItem() == "Nữ") {
            gender = 0;
        } else if (this.comboBoxXemGioiTinhTaiKhoanDangDangNhap.getSelectedItem() == "Khác") {
            gender = 2;
        }
        int salary = Integer.parseInt(this.textFieldXemLuongtaiKhoanDangDangNhap.getText() + "");
        String numberPhone = this.textFieldXemSDTTaiKhoanDangDangNhap.getText();
        String avatar = this.imgAccount;
        if (fullName.equals("")) {
            thongBaoLoi += "Vui lòng nhập tên.";
            this.labelLoiXemHoTenChuTaiKhoan.setText("Vui lòng nhập tên.");
        }
        if (cccd.equals("")) {
            thongBaoLoi += "Vui lòng nhập CCCD.";
            this.labelLoiXemCCCDTaiKhoanDangDangNhap.setText("Vui lòng nhập CCCD.");
        } else {
            matcherCCCD = patternCCCD.matcher(this.textFieldXemCCCDTaiKhoanDangDangNhap.getText());
            if (!matcherCCCD.matches()) {
                thongBaoLoi += "CCCD không hợp lệ";
                this.labelLoiXemCCCDTaiKhoanDangDangNhap.setText("CCCD phải có 12 ký tự số");
            }
        }
        if (userName.equals("")) {
            thongBaoLoi += "Vui lòng nhập user name";
            this.labelLoiXemUsernameTaiKhoanDangDangNhap.setText("Vui lòng nhập user name");
        }
        if (address.equals("")) {
            thongBaoLoi += "Vui lòng nhập địa chỉ.";
            this.labelLoiXemDiaChiTaiKhoanDangDangNhap.setText("Vui lòng nhập địa chỉ.");
        }
        if (this.textFieldXemLuongtaiKhoanDangDangNhap.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập lương.";
            this.labelLoiXemLuongtaiKhoanDangDangNhap.setText("Vui lòng nhập lương.");
        } else {
            if (!this.textFieldXemLuongtaiKhoanDangDangNhap.getText().matches("\\d+")) {
                thongBaoLoi += "Lương phải là số nguyên dương";
                this.labelLoiXemLuongtaiKhoanDangDangNhap.setText("Lương phải là số nguyên dương");
            }
        }
        if (numberPhone.equals("")) {
            thongBaoLoi += "Vui lòng nhập SDT";
            this.labelLoiXemSDTTaiKhoanDangDangNhap.setText("Vui lòng nhập SDT");
        } else {
            matcherSDT = patternSDT.matcher(this.textFieldXemSDTTaiKhoanDangDangNhap.getText());
            if (!matcherSDT.matches()) {
                thongBaoLoi += "SDT không hợp lệ";
                this.labelLoiXemSDTTaiKhoanDangDangNhap.setText("SDT không hợp lệ");
            }
        }
        if (thongBaoLoi.equals("")) {
            boolean ktTrungCCCD = this.accountDAO.isDuplicateCCCD(cccd);
            boolean ktTrungUserName = this.accountDAO.isDuplicateUserName(userName);
            boolean ktTrungSDT = this.accountDAO.isDuplicateNumberPhone(numberPhone);
            if (cccd.equals(this.account.getCccd())) {
                // kiểm tra xem cccd mới có giống như cccd của taiKhoanCanSua hay ko
                // nếu giống thì ta ko cần ktTrungCCCD
                ktTrungCCCD = false;
            }
            if (userName.equals(this.account.getUserName())) {
                // kiểm tra xem userName mới có giống như userName của taiKhoanCanSua hay ko
                // nếu giống thì ta ko cần ktTrungUserName
                ktTrungUserName = false;
            }
            if (numberPhone.equals(this.account.getNumberPhone())) {
                ktTrungSDT = false;
            }
            if (ktTrungCCCD == false && ktTrungUserName == false && ktTrungSDT == false) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thay đổi tài khoản này không.");
                if (confirm == JOptionPane.OK_OPTION) {
                    Account a = new Account(fullName, cccd, userName, this.account.getPassword(), birthday, address, "MANAGER", gender, salary, numberPhone, avatar);
                    int id = Integer.parseInt(this.labelXemNoiDungMaTaiKhoanDangDangNhap.getText() + "");
                    this.accountDAO.updateManagerAccount(id, a);
                }
            } else {
                if (ktTrungCCCD == true) {
                    JOptionPane.showMessageDialog(null, "CCCD đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (ktTrungUserName == true) {
                    JOptionPane.showMessageDialog(null, "username đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (ktTrungSDT == true) {
                    JOptionPane.showMessageDialog(null, "SDT đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void suaMatKhau() {
        String regexMatKhau = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        Pattern patternMatKhau = Pattern.compile(regexMatKhau);
        Matcher matcherMatKhau;
        String thongBaoLoi = "";
        if (String.valueOf(this.passFieldMKHienTaiQuanLy.getPassword()).equals("")) {
            thongBaoLoi += "Vui lòng nhập mật khẩu cũ.";
            this.labelLoiNhapMKHienTaiQL.setText("Vui lòng nhập mật khẩu cũ");
        } else {
            String matKhauCu = EncryptPassword.encryptPassword(String.valueOf(this.passFieldMKHienTaiQuanLy.getPassword()));
            if (!matKhauCu.equals(this.account.getPassword())) {
                thongBaoLoi += "Mật khẩu không đúng";
                this.labelLoiNhapMKHienTaiQL.setText("Mật khẩu không đúng");
            }
        }
        if (String.valueOf(this.passFieldNhapMKMoiQL.getPassword()).equals("")) {
            thongBaoLoi += "Vui lòng nhập mật khẩu mới";
            this.labelLoiNhapMKMoiQL.setText("Vui lòng nhập mật khẩu mới");
        } else {
            matcherMatKhau = patternMatKhau.matcher(String.valueOf(this.passFieldNhapMKMoiQL.getPassword()));
            if (!matcherMatKhau.matches()) {
                thongBaoLoi += "Mật khẩu không hợp lệ";
                this.labelLoiNhapMKMoiQL.setText("Mật khẩu phải có ít nhất 6 ký tự số và chữ");
            }
        }
        if (String.valueOf(this.passFieldNhapLaiMKQL.getPassword()).equals("")) {
            thongBaoLoi += "Vui lòng nhập lại mật khẩu mới";
            this.labelLoiNhapLaiMKQL.setText("Vui lòng nhập lại mật khẩu mới");
        } else {
            matcherMatKhau = patternMatKhau.matcher(String.valueOf(this.passFieldNhapLaiMKQL.getPassword()));
            if (!matcherMatKhau.matches()) {
                thongBaoLoi += "Mật khẩu không hợp lệ";
                this.labelLoiNhapLaiMKQL.setText("Mật khẩu phải có ít nhất 6 ký tự số và chữ");
            }
        }
        if (!String.valueOf(this.passFieldNhapMKMoiQL.getPassword()).equals("") && !String.valueOf(this.passFieldNhapLaiMKQL.getPassword()).equals("")) {
            if (!String.valueOf(this.passFieldNhapLaiMKQL.getPassword()).equals(String.valueOf(this.passFieldNhapMKMoiQL.getPassword()))) {
                thongBaoLoi += "Xác thực mật khẩu không khớp.";
                this.labelLoiNhapLaiMKQL.setText("Xác thực mật khẩu không khớp.");
            }
        }
        if (thongBaoLoi.equals("")) {
            String mkMoi = String.valueOf(this.passFieldNhapMKMoiQL.getPassword());
            mkMoi = EncryptPassword.encryptPassword(mkMoi);
            int theID = this.account.getIdAccount();
            this.accountDAO.updatePasswordManager(theID, mkMoi);
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

        frameDoiMatKhauQuanLy = new javax.swing.JFrame();
        panelDoiMatKhauQuanLy = new javax.swing.JPanel();
        labelTitleDoiMKQuanLy = new javax.swing.JLabel();
        labelXemMKCuQuanLy = new javax.swing.JLabel();
        passFieldMKHienTaiQuanLy = new javax.swing.JPasswordField();
        checkBoxHienMKHienTaiQL = new javax.swing.JCheckBox();
        labelNhapMKMoiQL = new javax.swing.JLabel();
        passFieldNhapMKMoiQL = new javax.swing.JPasswordField();
        checkBoxHienMKMoiQL = new javax.swing.JCheckBox();
        labelNhapLaiMKQL = new javax.swing.JLabel();
        passFieldNhapLaiMKQL = new javax.swing.JPasswordField();
        labelLoiNhapMKHienTaiQL = new javax.swing.JLabel();
        labelLoiNhapMKMoiQL = new javax.swing.JLabel();
        checkBoxHienNhapLaiMKQL = new javax.swing.JCheckBox();
        labelLoiNhapLaiMKQL = new javax.swing.JLabel();
        btnHoanThanhSuaMKQL = new javax.swing.JButton();
        panelQLThayDoiThongTinTaiKhoan = new javax.swing.JPanel();
        labelThayDoiHoTenChuTaiKhoan = new javax.swing.JLabel();
        textFieldXemHoTenTaiKhoanDangDangNhap = new javax.swing.JTextField();
        labelLoiXemHoTenChuTaiKhoan = new javax.swing.JLabel();
        labelAnhDaiDienChuTaiKhoan = new javax.swing.JLabel();
        btnThayDoiAnhDaiDienChuTaiKhoan = new javax.swing.JButton();
        btnHoanThanhThayDoiThongTinChuTaiKhoan = new javax.swing.JButton();
        labelXemMaTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemNoiDungMaTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelTitleQLTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemCCCDTaiKhoanDangDangNhap = new javax.swing.JLabel();
        textFieldXemCCCDTaiKhoanDangDangNhap = new javax.swing.JTextField();
        labelLoiXemCCCDTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemPhanQuyenTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemSDTTaiKhoanDangDangNhap = new javax.swing.JLabel();
        textFieldXemSDTTaiKhoanDangDangNhap = new javax.swing.JTextField();
        btnThayDoiMatKhau = new javax.swing.JButton();
        labelXemUsernameTaiKhoanDangDangNhap = new javax.swing.JLabel();
        textFieldXemUserNameTaiKhoanDangDangNhap = new javax.swing.JTextField();
        labelLoiXemUsernameTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelLoiXemSDTTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemDiaChiTaiKhoanDangDangNhap = new javax.swing.JLabel();
        textFieldXemDiaChiTaiKhoanDangDangNhap = new javax.swing.JTextField();
        labelLoiXemDiaChiTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemLuongtaiKhoanDangDangNhap = new javax.swing.JLabel();
        textFieldXemLuongtaiKhoanDangDangNhap = new javax.swing.JTextField();
        labelLoiXemLuongtaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemGioiTinhTaiKhoanDangDangNhap = new javax.swing.JLabel();
        comboBoxXemGioiTinhTaiKhoanDangDangNhap = new javax.swing.JComboBox<>();
        labelLoiXemgioiTinhTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelXemNgaySinhTaiKhoanDangDangNhap = new javax.swing.JLabel();
        labelLoiXemNgaySinhTaiKhoanDangDangNhap = new javax.swing.JLabel();
        dateChooserXemNgaySinhTaiKhoanDangDangNhap = new com.toedter.calendar.JDateChooser();
        labelTenNhom = new javax.swing.JLabel();

        frameDoiMatKhauQuanLy.setTitle("Đổi mật khẩu");

        panelDoiMatKhauQuanLy.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleDoiMKQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleDoiMKQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleDoiMKQuanLy.setForeground(new java.awt.Color(100, 92, 255));
        labelTitleDoiMKQuanLy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleDoiMKQuanLy.setText("ĐỔI MẬT KHẨU");
        labelTitleDoiMKQuanLy.setOpaque(true);

        labelXemMKCuQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMKCuQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMKCuQuanLy.setText("Mật khẩu hiện tại");
        labelXemMKCuQuanLy.setOpaque(true);

        passFieldMKHienTaiQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        passFieldMKHienTaiQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        passFieldMKHienTaiQuanLy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFieldMKHienTaiQuanLyKeyPressed(evt);
            }
        });

        checkBoxHienMKHienTaiQL.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxHienMKHienTaiQL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkBoxHienMKHienTaiQL.setText("Hiện");
        checkBoxHienMKHienTaiQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxHienMKHienTaiQLMouseClicked(evt);
            }
        });

        labelNhapMKMoiQL.setBackground(new java.awt.Color(255, 255, 255));
        labelNhapMKMoiQL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNhapMKMoiQL.setText("Mật khẩu mới");
        labelNhapMKMoiQL.setOpaque(true);

        passFieldNhapMKMoiQL.setBackground(new java.awt.Color(255, 255, 255));
        passFieldNhapMKMoiQL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        passFieldNhapMKMoiQL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFieldNhapMKMoiQLKeyPressed(evt);
            }
        });

        checkBoxHienMKMoiQL.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxHienMKMoiQL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkBoxHienMKMoiQL.setText("Hiện");
        checkBoxHienMKMoiQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxHienMKMoiQLMouseClicked(evt);
            }
        });

        labelNhapLaiMKQL.setBackground(new java.awt.Color(255, 255, 255));
        labelNhapLaiMKQL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNhapLaiMKQL.setText("Nhập lại mật khẩu");
        labelNhapLaiMKQL.setOpaque(true);

        passFieldNhapLaiMKQL.setBackground(new java.awt.Color(255, 255, 255));
        passFieldNhapLaiMKQL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        passFieldNhapLaiMKQL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFieldNhapLaiMKQLKeyPressed(evt);
            }
        });

        labelLoiNhapMKHienTaiQL.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiNhapMKHienTaiQL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiNhapMKHienTaiQL.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiNhapMKHienTaiQL.setOpaque(true);

        labelLoiNhapMKMoiQL.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiNhapMKMoiQL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiNhapMKMoiQL.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiNhapMKMoiQL.setOpaque(true);

        checkBoxHienNhapLaiMKQL.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxHienNhapLaiMKQL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkBoxHienNhapLaiMKQL.setText("Hiện");
        checkBoxHienNhapLaiMKQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxHienNhapLaiMKQLMouseClicked(evt);
            }
        });

        labelLoiNhapLaiMKQL.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiNhapLaiMKQL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiNhapLaiMKQL.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiNhapLaiMKQL.setOpaque(true);

        btnHoanThanhSuaMKQL.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhSuaMKQL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhSuaMKQL.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaMKQL.setText("THAY ĐỔI");
        btnHoanThanhSuaMKQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaMKQLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDoiMatKhauQuanLyLayout = new javax.swing.GroupLayout(panelDoiMatKhauQuanLy);
        panelDoiMatKhauQuanLy.setLayout(panelDoiMatKhauQuanLyLayout);
        panelDoiMatKhauQuanLyLayout.setHorizontalGroup(
            panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleDoiMKQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                        .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelXemMKCuQuanLy)
                                    .addComponent(labelNhapMKMoiQL, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNhapLaiMKQL, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                                        .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(labelLoiNhapLaiMKQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelLoiNhapMKMoiQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelLoiNhapMKHienTaiQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(passFieldNhapLaiMKQL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                            .addComponent(passFieldNhapMKMoiQL, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(passFieldMKHienTaiQuanLy, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkBoxHienMKHienTaiQL, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkBoxHienMKMoiQL, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkBoxHienNhapLaiMKQL, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(btnHoanThanhSuaMKQL)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDoiMatKhauQuanLyLayout.setVerticalGroup(
            panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoiMatKhauQuanLyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleDoiMKQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelXemMKCuQuanLy)
                .addGap(18, 18, 18)
                .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passFieldMKHienTaiQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxHienMKHienTaiQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiNhapMKHienTaiQL)
                .addGap(18, 18, 18)
                .addComponent(labelNhapMKMoiQL)
                .addGap(18, 18, 18)
                .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passFieldNhapMKMoiQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxHienMKMoiQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiNhapMKMoiQL)
                .addGap(18, 18, 18)
                .addComponent(labelNhapLaiMKQL)
                .addGap(18, 18, 18)
                .addGroup(panelDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passFieldNhapLaiMKQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxHienNhapLaiMKQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiNhapLaiMKQL)
                .addGap(34, 34, 34)
                .addComponent(btnHoanThanhSuaMKQL)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameDoiMatKhauQuanLyLayout = new javax.swing.GroupLayout(frameDoiMatKhauQuanLy.getContentPane());
        frameDoiMatKhauQuanLy.getContentPane().setLayout(frameDoiMatKhauQuanLyLayout);
        frameDoiMatKhauQuanLyLayout.setHorizontalGroup(
            frameDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameDoiMatKhauQuanLyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoiMatKhauQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameDoiMatKhauQuanLyLayout.setVerticalGroup(
            frameDoiMatKhauQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameDoiMatKhauQuanLyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoiMatKhauQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLThayDoiThongTinTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        labelThayDoiHoTenChuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelThayDoiHoTenChuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThayDoiHoTenChuTaiKhoan.setText("Họ tên");
        labelThayDoiHoTenChuTaiKhoan.setOpaque(true);

        textFieldXemHoTenTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemHoTenTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemHoTenTaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemHoTenTaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        labelLoiXemHoTenChuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemHoTenChuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemHoTenChuTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemHoTenChuTaiKhoan.setOpaque(true);

        labelAnhDaiDienChuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        labelAnhDaiDienChuTaiKhoan.setText("Ảnh");
        labelAnhDaiDienChuTaiKhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelAnhDaiDienChuTaiKhoan.setMaximumSize(new java.awt.Dimension(88, 22));
        labelAnhDaiDienChuTaiKhoan.setMinimumSize(new java.awt.Dimension(88, 22));
        labelAnhDaiDienChuTaiKhoan.setOpaque(true);

        btnThayDoiAnhDaiDienChuTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        btnThayDoiAnhDaiDienChuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThayDoiAnhDaiDienChuTaiKhoan.setForeground(new java.awt.Color(204, 0, 204));
        btnThayDoiAnhDaiDienChuTaiKhoan.setText("Chọn ảnh");
        btnThayDoiAnhDaiDienChuTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThayDoiAnhDaiDienChuTaiKhoanMouseClicked(evt);
            }
        });

        btnHoanThanhThayDoiThongTinChuTaiKhoan.setBackground(new java.awt.Color(255, 95, 0));
        btnHoanThanhThayDoiThongTinChuTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoanThanhThayDoiThongTinChuTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThayDoiThongTinChuTaiKhoan.setText("CẬP NHẬT");
        btnHoanThanhThayDoiThongTinChuTaiKhoan.setOpaque(true);
        btnHoanThanhThayDoiThongTinChuTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThayDoiThongTinChuTaiKhoanMouseClicked(evt);
            }
        });
        btnHoanThanhThayDoiThongTinChuTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhThayDoiThongTinChuTaiKhoanActionPerformed(evt);
            }
        });

        labelXemMaTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaTaiKhoanDangDangNhap.setText("Mã tài khoản");
        labelXemMaTaiKhoanDangDangNhap.setOpaque(true);

        labelXemNoiDungMaTaiKhoanDangDangNhap.setBackground(new java.awt.Color(241, 245, 249));
        labelXemNoiDungMaTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaTaiKhoanDangDangNhap.setText("0");
        labelXemNoiDungMaTaiKhoanDangDangNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungMaTaiKhoanDangDangNhap.setOpaque(true);

        labelTitleQLTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleQLTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLTaiKhoanDangDangNhap.setForeground(new java.awt.Color(51, 0, 255));
        labelTitleQLTaiKhoanDangDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleQLTaiKhoanDangDangNhap.setText("THÔNG TIN TÀI KHOẢN QUẢN LÝ");
        labelTitleQLTaiKhoanDangDangNhap.setOpaque(true);

        labelXemCCCDTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemCCCDTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemCCCDTaiKhoanDangDangNhap.setText("CCCD");
        labelXemCCCDTaiKhoanDangDangNhap.setOpaque(true);

        textFieldXemCCCDTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemCCCDTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemCCCDTaiKhoanDangDangNhap.setText("098192561782");
        textFieldXemCCCDTaiKhoanDangDangNhap.setOpaque(true);
        textFieldXemCCCDTaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemCCCDTaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        labelLoiXemCCCDTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemCCCDTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemCCCDTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemCCCDTaiKhoanDangDangNhap.setOpaque(true);

        labelXemPhanQuyenTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemPhanQuyenTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemPhanQuyenTaiKhoanDangDangNhap.setText("Phân quyền");
        labelXemPhanQuyenTaiKhoanDangDangNhap.setOpaque(true);

        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap.setBackground(new java.awt.Color(241, 245, 249));
        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap.setText("Quản lý");
        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap.setOpaque(true);

        labelXemSDTTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemSDTTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemSDTTaiKhoanDangDangNhap.setText("SDT");
        labelXemSDTTaiKhoanDangDangNhap.setOpaque(true);

        textFieldXemSDTTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemSDTTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemSDTTaiKhoanDangDangNhap.setText("0981235627");
        textFieldXemSDTTaiKhoanDangDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldXemSDTTaiKhoanDangDangNhapActionPerformed(evt);
            }
        });
        textFieldXemSDTTaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemSDTTaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        btnThayDoiMatKhau.setBackground(new java.awt.Color(16, 185, 129));
        btnThayDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThayDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoiMatKhau.setText("ĐỔI MẬT KHẨU");
        btnThayDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThayDoiMatKhauMouseClicked(evt);
            }
        });

        labelXemUsernameTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemUsernameTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemUsernameTaiKhoanDangDangNhap.setText("User name");
        labelXemUsernameTaiKhoanDangDangNhap.setOpaque(true);

        textFieldXemUserNameTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemUserNameTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemUserNameTaiKhoanDangDangNhap.setText("username");
        textFieldXemUserNameTaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemUserNameTaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        labelLoiXemUsernameTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemUsernameTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemUsernameTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemUsernameTaiKhoanDangDangNhap.setOpaque(true);

        labelLoiXemSDTTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemSDTTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemSDTTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemSDTTaiKhoanDangDangNhap.setOpaque(true);

        labelXemDiaChiTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemDiaChiTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemDiaChiTaiKhoanDangDangNhap.setText("Địa chỉ");
        labelXemDiaChiTaiKhoanDangDangNhap.setOpaque(true);

        textFieldXemDiaChiTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemDiaChiTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemDiaChiTaiKhoanDangDangNhap.setText("Đại Thuận, Mỹ Hiệp, Phù Mỹ, Bình Đinh");
        textFieldXemDiaChiTaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemDiaChiTaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        labelLoiXemDiaChiTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemDiaChiTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemDiaChiTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemDiaChiTaiKhoanDangDangNhap.setOpaque(true);

        labelXemLuongtaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemLuongtaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemLuongtaiKhoanDangDangNhap.setText("Lương");
        labelXemLuongtaiKhoanDangDangNhap.setOpaque(true);

        textFieldXemLuongtaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        textFieldXemLuongtaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldXemLuongtaiKhoanDangDangNhap.setText("10000000");
        textFieldXemLuongtaiKhoanDangDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldXemLuongtaiKhoanDangDangNhapKeyPressed(evt);
            }
        });

        labelLoiXemLuongtaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemLuongtaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemLuongtaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemLuongtaiKhoanDangDangNhap.setOpaque(true);

        labelXemGioiTinhTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemGioiTinhTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemGioiTinhTaiKhoanDangDangNhap.setText("Giới tính");
        labelXemGioiTinhTaiKhoanDangDangNhap.setOpaque(true);

        comboBoxXemGioiTinhTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxXemGioiTinhTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxXemGioiTinhTaiKhoanDangDangNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));

        labelLoiXemgioiTinhTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemgioiTinhTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemgioiTinhTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemgioiTinhTaiKhoanDangDangNhap.setOpaque(true);

        labelXemNgaySinhTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNgaySinhTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNgaySinhTaiKhoanDangDangNhap.setText("Ngày sinh");
        labelXemNgaySinhTaiKhoanDangDangNhap.setOpaque(true);

        labelLoiXemNgaySinhTaiKhoanDangDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiXemNgaySinhTaiKhoanDangDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLoiXemNgaySinhTaiKhoanDangDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiXemNgaySinhTaiKhoanDangDangNhap.setOpaque(true);

        labelTenNhom.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTenNhom.setText("NHÓM 10 (D21CQAT01-N)");

        javax.swing.GroupLayout panelQLThayDoiThongTinTaiKhoanLayout = new javax.swing.GroupLayout(panelQLThayDoiThongTinTaiKhoan);
        panelQLThayDoiThongTinTaiKhoan.setLayout(panelQLThayDoiThongTinTaiKhoanLayout);
        panelQLThayDoiThongTinTaiKhoanLayout.setHorizontalGroup(
            panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                        .addComponent(labelAnhDaiDienChuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelXemMaTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(labelThayDoiHoTenChuTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelXemCCCDTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelXemUsernameTaiKhoanDangDangNhap)
                                    .addComponent(labelXemPhanQuyenTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelXemDiaChiTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelLoiXemDiaChiTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textFieldXemDiaChiTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)))
                                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(textFieldXemHoTenTaiKhoanDangDangNhap, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelLoiXemUsernameTaiKhoanDangDangNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(textFieldXemUserNameTaiKhoanDangDangNhap, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelLoiXemCCCDTaiKhoanDangDangNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(textFieldXemCCCDTaiKhoanDangDangNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                                    .addComponent(labelLoiXemHoTenChuTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelXemGioiTinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelXemSDTTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(labelXemNgaySinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                                .addComponent(labelXemNoiDungMaTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelXemLuongtaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelLoiXemLuongtaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textFieldXemLuongtaiKhoanDangDangNhap)
                                            .addComponent(comboBoxXemGioiTinhTaiKhoanDangDangNhap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelLoiXemgioiTinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textFieldXemSDTTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                            .addComponent(labelLoiXemSDTTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelLoiXemNgaySinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateChooserXemNgaySinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                .addComponent(btnHoanThanhThayDoiThongTinChuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnThayDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(338, 338, 338))
                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThayDoiAnhDaiDienChuTaiKhoan)
                            .addComponent(labelTitleQLTaiKhoanDangDangNhap)
                            .addComponent(labelTenNhom))
                        .addGap(0, 1156, Short.MAX_VALUE))))
        );
        panelQLThayDoiThongTinTaiKhoanLayout.setVerticalGroup(
            panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelXemNoiDungMaTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelXemLuongtaiKhoanDangDangNhap)
                                .addComponent(textFieldXemLuongtaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(labelXemMaTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLoiXemLuongtaiKhoanDangDangNhap)
                        .addGap(8, 8, 8)
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelThayDoiHoTenChuTaiKhoan)
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textFieldXemHoTenTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelXemGioiTinhTaiKhoanDangDangNhap)
                                .addComponent(comboBoxXemGioiTinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiXemHoTenChuTaiKhoan)
                            .addComponent(labelLoiXemgioiTinhTaiKhoanDangDangNhap))
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelXemCCCDTaiKhoanDangDangNhap)
                                    .addComponent(textFieldXemCCCDTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelXemSDTTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelLoiXemCCCDTaiKhoanDangDangNhap)
                                .addGap(18, 18, 18)
                                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textFieldXemUserNameTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelXemNgaySinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelXemUsernameTaiKhoanDangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textFieldXemSDTTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelLoiXemSDTTaiKhoanDangDangNhap)
                                .addGap(18, 18, 18)
                                .addComponent(dateChooserXemNgaySinhTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLoiXemUsernameTaiKhoanDangDangNhap)
                            .addComponent(labelLoiXemNgaySinhTaiKhoanDangDangNhap))
                        .addGap(82, 82, 82))
                    .addComponent(labelAnhDaiDienChuTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemPhanQuyenTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThayDoiAnhDaiDienChuTaiKhoan))
                .addGap(18, 18, 18)
                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemDiaChiTaiKhoanDangDangNhap)
                    .addComponent(textFieldXemDiaChiTaiKhoanDangDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiXemDiaChiTaiKhoanDangDangNhap)
                .addGap(18, 18, 18)
                .addGroup(panelQLThayDoiThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoanThanhThayDoiThongTinChuTaiKhoan)
                    .addComponent(btnThayDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(428, 428, 428)
                .addComponent(labelTenNhom)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelQLThayDoiThongTinTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelQLThayDoiThongTinTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoanThanhThayDoiThongTinChuTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhThayDoiThongTinChuTaiKhoanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnHoanThanhThayDoiThongTinChuTaiKhoanActionPerformed

    private void btnThayDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThayDoiMatKhauMouseClicked
        // TODO add your handling code here:
        this.frameDoiMatKhauQuanLy.setSize(550, 600);
        this.frameDoiMatKhauQuanLy.setLocationRelativeTo(null);
        this.frameDoiMatKhauQuanLy.setVisible(true);
        this.frameDoiMatKhauQuanLy.getContentPane().setBackground(Color.WHITE);
    }//GEN-LAST:event_btnThayDoiMatKhauMouseClicked

    private void btnThayDoiAnhDaiDienChuTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThayDoiAnhDaiDienChuTaiKhoanMouseClicked
        // TODO add your handling code here:
        hienThiAvatarAccount();

    }//GEN-LAST:event_btnThayDoiAnhDaiDienChuTaiKhoanMouseClicked

    private void btnHoanThanhThayDoiThongTinChuTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThayDoiThongTinChuTaiKhoanMouseClicked
        // TODO add your handling code here:
        // click nút này để hoàn thành đôi thông tin tài khoaanr trừ mật khẩu
        suaThongTinTaiKhoanQuanLy();
    }//GEN-LAST:event_btnHoanThanhThayDoiThongTinChuTaiKhoanMouseClicked

    private void btnHoanThanhSuaMKQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaMKQLMouseClicked
        // TODO add your handling code here:
        suaMatKhau();
    }//GEN-LAST:event_btnHoanThanhSuaMKQLMouseClicked

    private void checkBoxHienMKHienTaiQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxHienMKHienTaiQLMouseClicked
        // TODO add your handling code here:
        hienThiMatKhau(this.passFieldMKHienTaiQuanLy, this.checkBoxHienMKHienTaiQL);
    }//GEN-LAST:event_checkBoxHienMKHienTaiQLMouseClicked

    private void checkBoxHienMKMoiQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxHienMKMoiQLMouseClicked
        // TODO add your handling code here:
        hienThiMatKhau(this.passFieldNhapMKMoiQL, this.checkBoxHienMKMoiQL);
    }//GEN-LAST:event_checkBoxHienMKMoiQLMouseClicked

    private void checkBoxHienNhapLaiMKQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxHienNhapLaiMKQLMouseClicked
        // TODO add your handling code here:
        hienThiMatKhau(this.passFieldNhapLaiMKQL, this.checkBoxHienNhapLaiMKQL);
    }//GEN-LAST:event_checkBoxHienNhapLaiMKQLMouseClicked

    private void textFieldXemSDTTaiKhoanDangDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldXemSDTTaiKhoanDangDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldXemSDTTaiKhoanDangDangNhapActionPerformed

    private void textFieldXemHoTenTaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemHoTenTaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemHoTenChuTaiKhoan.getText().equals("")) {
            this.labelLoiXemHoTenChuTaiKhoan.setText("");
        }
    }//GEN-LAST:event_textFieldXemHoTenTaiKhoanDangDangNhapKeyPressed

    private void textFieldXemCCCDTaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemCCCDTaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemCCCDTaiKhoanDangDangNhap.getText().equals("")) {
            this.labelLoiXemCCCDTaiKhoanDangDangNhap.setText("");
        }
    }//GEN-LAST:event_textFieldXemCCCDTaiKhoanDangDangNhapKeyPressed

    private void textFieldXemUserNameTaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemUserNameTaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemUsernameTaiKhoanDangDangNhap.getText().equals("")) {
            this.labelLoiXemUsernameTaiKhoanDangDangNhap.setText("");
        }
    }//GEN-LAST:event_textFieldXemUserNameTaiKhoanDangDangNhapKeyPressed

    private void textFieldXemDiaChiTaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemDiaChiTaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemDiaChiTaiKhoanDangDangNhap.getText().equals("")) {
            this.labelLoiXemDiaChiTaiKhoanDangDangNhap.setText("");
        }
    }//GEN-LAST:event_textFieldXemDiaChiTaiKhoanDangDangNhapKeyPressed

    private void textFieldXemLuongtaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemLuongtaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemLuongtaiKhoanDangDangNhap.getText().equals("")) {
            this.labelLoiXemLuongtaiKhoanDangDangNhap.setText("");
        }
    }//GEN-LAST:event_textFieldXemLuongtaiKhoanDangDangNhapKeyPressed

    private void textFieldXemSDTTaiKhoanDangDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldXemSDTTaiKhoanDangDangNhapKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiXemSDTTaiKhoanDangDangNhap.getText().equals("")) {
            this.labelLoiXemSDTTaiKhoanDangDangNhap.setText("");
        }
    }//GEN-LAST:event_textFieldXemSDTTaiKhoanDangDangNhapKeyPressed

    private void passFieldMKHienTaiQuanLyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFieldMKHienTaiQuanLyKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiNhapMKHienTaiQL.getText().equals("")) {
            this.labelLoiNhapMKHienTaiQL.setText("");
        }
    }//GEN-LAST:event_passFieldMKHienTaiQuanLyKeyPressed

    private void passFieldNhapMKMoiQLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFieldNhapMKMoiQLKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiNhapMKMoiQL.getText().equals("")) {
            this.labelLoiNhapMKMoiQL.setText("");
        }
    }//GEN-LAST:event_passFieldNhapMKMoiQLKeyPressed

    private void passFieldNhapLaiMKQLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFieldNhapLaiMKQLKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiNhapLaiMKQL.getText().equals("")) {
            this.labelLoiNhapLaiMKQL.setText("");
        }
    }//GEN-LAST:event_passFieldNhapLaiMKQLKeyPressed

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
            java.util.logging.Logger.getLogger(QLThongTinTaiKhoanHienTai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLThongTinTaiKhoanHienTai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLThongTinTaiKhoanHienTai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLThongTinTaiKhoanHienTai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLThongTinTaiKhoanHienTai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanhSuaMKQL;
    private javax.swing.JButton btnHoanThanhThayDoiThongTinChuTaiKhoan;
    private javax.swing.JButton btnThayDoiAnhDaiDienChuTaiKhoan;
    private javax.swing.JButton btnThayDoiMatKhau;
    private javax.swing.JCheckBox checkBoxHienMKHienTaiQL;
    private javax.swing.JCheckBox checkBoxHienMKMoiQL;
    private javax.swing.JCheckBox checkBoxHienNhapLaiMKQL;
    private javax.swing.JComboBox<String> comboBoxXemGioiTinhTaiKhoanDangDangNhap;
    private com.toedter.calendar.JDateChooser dateChooserXemNgaySinhTaiKhoanDangDangNhap;
    private javax.swing.JFrame frameDoiMatKhauQuanLy;
    private javax.swing.JLabel labelAnhDaiDienChuTaiKhoan;
    private javax.swing.JLabel labelLoiNhapLaiMKQL;
    private javax.swing.JLabel labelLoiNhapMKHienTaiQL;
    private javax.swing.JLabel labelLoiNhapMKMoiQL;
    private javax.swing.JLabel labelLoiXemCCCDTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemDiaChiTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemHoTenChuTaiKhoan;
    private javax.swing.JLabel labelLoiXemLuongtaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemNgaySinhTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemSDTTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemUsernameTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelLoiXemgioiTinhTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelNhapLaiMKQL;
    private javax.swing.JLabel labelNhapMKMoiQL;
    private javax.swing.JLabel labelTenNhom;
    private javax.swing.JLabel labelThayDoiHoTenChuTaiKhoan;
    private javax.swing.JLabel labelTitleDoiMKQuanLy;
    private javax.swing.JLabel labelTitleQLTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemCCCDTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemDiaChiTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemGioiTinhTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemLuongtaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemMKCuQuanLy;
    private javax.swing.JLabel labelXemMaTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemNgaySinhTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemNoiDungMaTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemNoiDungPhanQuyenTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemPhanQuyenTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemSDTTaiKhoanDangDangNhap;
    private javax.swing.JLabel labelXemUsernameTaiKhoanDangDangNhap;
    private javax.swing.JPanel panelDoiMatKhauQuanLy;
    private javax.swing.JPanel panelQLThayDoiThongTinTaiKhoan;
    private javax.swing.JPasswordField passFieldMKHienTaiQuanLy;
    private javax.swing.JPasswordField passFieldNhapLaiMKQL;
    private javax.swing.JPasswordField passFieldNhapMKMoiQL;
    private javax.swing.JTextField textFieldXemCCCDTaiKhoanDangDangNhap;
    private javax.swing.JTextField textFieldXemDiaChiTaiKhoanDangDangNhap;
    private javax.swing.JTextField textFieldXemHoTenTaiKhoanDangDangNhap;
    private javax.swing.JTextField textFieldXemLuongtaiKhoanDangDangNhap;
    private javax.swing.JTextField textFieldXemSDTTaiKhoanDangDangNhap;
    private javax.swing.JTextField textFieldXemUserNameTaiKhoanDangDangNhap;
    // End of variables declaration//GEN-END:variables
}
