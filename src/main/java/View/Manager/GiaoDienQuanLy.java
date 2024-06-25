package View.Manager;

import Dao.*;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import Model.Manager.*;
import View.Manager.ManagementFunction.*;
import View.LogRes.ManagerEmployee.Login;

public class GiaoDienQuanLy extends javax.swing.JFrame {

    public DefaultTableModel defaultTableModelDSTaiKhoan;
    public DefaultTableModel defaultTableModelDSSanPham;
    public DefaultTableModel defaultTableModelDSTopping;
    public DefaultTableModel defaultTableModelDSLoaiSanPham;
    public DefaultTableModel defaultTableModelDSSize;
    public DefaultTableModel defaultTableModelDSNguyenLieu;
    public DefaultTableModel defaultTableModelDSNhaCungCap;
    public DefaultTableModel defaultTableModelDSKhachHang;
    public DefaultTableModel defaultTableModelDSNguyenLieuNhapHang;
    public DefaultTableModel defaultTableModelDSHoaDonNhapNguyenLieu;

    public static Map<String, Object> infoManager = new HashMap<>();

    public ArrayList<Account> dsTaiKhoan;
    public ArrayList<Product> dsSanPham;
    public ArrayList<Topping> dsTopping;
    public ArrayList<Category> dsLoaiSanPham;
    public ArrayList<Size> dsSize;
    public ArrayList<Ingredient> dsNguyenLieu;
    public ArrayList<Supplier> dsNhaCungCap;
    public ArrayList<Customer> dsKhachHang;

    public AccountDAO accountDAO;
    public ProductDAO productDAO;
    public ToppingDAO toppingDAO;
    public CategoryDAO categoryDAO;
    public SizeDAO sizeDAO;
    public IngredientDAO ingredientDAO;
    public SupplierDAO supplierDAO;
    public CustomerDAO customerDAO;

    public String tempImageName;
    public String tempImageNameFormSuaTaiKhoan;
    public String anhThemTopping;
    public String anhSuaTopping;
    public String anhThemSanPham;
    public String anhSuaSanPham;

    public GiaoDienQuanLy() {
        initComponents();
        setSize(1920, 1080);

        this.dsTaiKhoan = new ArrayList<>();
        this.dsLoaiSanPham = new ArrayList<>();
        this.dsLoaiSanPham = new ArrayList<>();
        this.dsSize = new ArrayList<>();
        this.dsNhaCungCap = new ArrayList<>();
        this.dsKhachHang = new ArrayList<>();

        this.accountDAO = new AccountDAO();
        this.productDAO = new ProductDAO();
        this.toppingDAO = new ToppingDAO();
        this.categoryDAO = new CategoryDAO();
        this.sizeDAO = new SizeDAO();
        this.ingredientDAO = new IngredientDAO();
        this.supplierDAO = new SupplierDAO();
        this.customerDAO = new CustomerDAO();

        changeNhanVienPanel();
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK); // Border chỉ ở dưới

    }

    public void changeNhanVienPanel() {
        this.labelQLTaiKhoanNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
        QLTaiKhoan a = new QLTaiKhoan();
        panelParentContent.removeAll();
        panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
    }

    public void changeSanPhamPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLSanPhamContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        this.labelQLSanPhamNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
        QLSanPham a = new QLSanPham();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();

    }

    public void changeToppingPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLToppingContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLTopping a = new QLTopping();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLToppingNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeDonHangPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLDonHangContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLDonHang a = new QLDonHang();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLDonHangNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeThongKePanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLThongKeContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLThongKe a = new QLThongKe();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLThongKeNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changLoaiSanPhamPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLLoaiSanPhamContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLTheLoai a = new QLTheLoai();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLLoaiSanPhamNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
//        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
    }
    public void changeThayDoiThongTinPanel() {
        QLThongTinTaiKhoanHienTai a = new QLThongTinTaiKhoanHienTai();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeNhaCungCapTinPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLNhaCungCapContent);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLNhaCungCap a = new QLNhaCungCap();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLNhaCungCapNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeNguyenLieuPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLNguyenLieu);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLNguyenLieu a = new QLNguyenLieu();
        this.panelParentContent.removeAll();
        this.panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLNguyenLieuNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeKhachHangPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLKhachHang);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLKhachHang a = new QLKhachHang();
        panelParentContent.removeAll();
        panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLKhachHang.setBackground(new Color(0x99FFFF));
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeNhapNguyenLieuPanel() {
//        this.panelParentContent.removeAll();
//        this.panelParentContent.add(this.panelQLNhapHang);
//        this.panelParentContent.repaint();
//        this.panelParentContent.revalidate();
        QLNhapNguyenLieu a = new QLNhapNguyenLieu();
        panelParentContent.removeAll();
        panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLNhapNguyenLieuNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLMenuNavbar.setBackground(Color.WHITE);
    }

    public void changeMenuPanel() {
        QLMenu a = new QLMenu();
        panelParentContent.removeAll();
        panelParentContent.add(a).setVisible(true);
        this.panelParentContent.repaint();
        this.panelParentContent.revalidate();
        this.labelQLMenuNavbar.setBackground(new Color(0x99FFFF));
        this.labelQLNhapNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLKhachHang.setBackground(Color.WHITE);
        this.labelQLNguyenLieuNavbar.setBackground(Color.WHITE);
        this.labelQLNhaCungCapNavbar.setBackground(Color.WHITE);
        this.labelQLThongKeNavbar.setBackground(Color.WHITE);
        this.labelQLTaiKhoanNavbar.setBackground(Color.WHITE);
        this.labelQLSanPhamNavbar.setBackground(Color.WHITE);
        this.labelQLToppingNavbar.setBackground(Color.WHITE);
        this.labelQLDonHangNavbar.setBackground(Color.WHITE);
        this.labelQLLoaiSanPhamNavbar.setBackground(Color.WHITE);

    }

    public void dieuChinhKichThuocIconTopping() {
        ImageIcon iconGoc = new ImageIcon("E:\\NhapMonCongNghePhanMem\\DoAnContainer\\DoAnTraSuaTaSu_CNPM\\src\\main\\java\\img\\icons\\up-arrow.png");
        Image hinhAnhTopping = iconGoc.getImage();
        Image hinhAnhToppingThuNho = hinhAnhTopping.getScaledInstance(this.labelQLToppingNavbar.getWidth() / 10, this.labelQLToppingNavbar.getHeight() / 2, Image.SCALE_SMOOTH);
        ImageIcon iconTopping = new ImageIcon(hinhAnhToppingThuNho);
        this.labelQLToppingNavbar.setIcon(iconTopping);
    }

    public void dieuChinhKichThuocIconLogo() {
        ImageIcon iconGoc = new ImageIcon("C:\\Users\\Admin\\Desktop\\resources\\images\\logo.png");
        Image logo = iconGoc.getImage();
        Image logoThuNho = logo.getScaledInstance(this.labelQLToppingNavbar.getWidth(), this.labelQLToppingNavbar.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconLogo = new ImageIcon(logoThuNho);
        this.labelTenDoAn.setIcon(iconLogo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNavbar = new javax.swing.JPanel();
        panelThongTinTaiKhoan = new javax.swing.JPanel();
        labelTenDoAn = new javax.swing.JLabel();
        labelTaiKhoanHienTai = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        labelQLThongKeNavbar = new javax.swing.JLabel();
        labelQLDonHangNavbar = new javax.swing.JLabel();
        labelQLSanPhamNavbar = new javax.swing.JLabel();
        labelQLTaiKhoanNavbar = new javax.swing.JLabel();
        labelQLToppingNavbar = new javax.swing.JLabel();
        labelQLNhaCungCapNavbar = new javax.swing.JLabel();
        labelQLNguyenLieuNavbar = new javax.swing.JLabel();
        labelQLNhapNguyenLieuNavbar = new javax.swing.JLabel();
        labelQLLoaiSanPhamNavbar = new javax.swing.JLabel();
        labelQLKhachHang = new javax.swing.JLabel();
        labelQLMenuNavbar = new javax.swing.JLabel();
        panelParentContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRÀ SỮA TASU_NHÓM 10_D21CQAT01-N");

        panelNavbar.setBackground(new java.awt.Color(255, 255, 255));

        panelThongTinTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        labelTenDoAn.setBackground(new java.awt.Color(255, 255, 255));
        labelTenDoAn.setFont(new java.awt.Font("Cambria Math", 1, 22)); // NOI18N
        labelTenDoAn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTenDoAn.setText("TRÀ SỮA TASU");
        labelTenDoAn.setOpaque(true);
        labelTenDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTenDoAnMouseClicked(evt);
            }
        });

        labelTaiKhoanHienTai.setBackground(new java.awt.Color(255, 255, 255));
        labelTaiKhoanHienTai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTaiKhoanHienTai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTaiKhoanHienTai.setText("Tài khoản quản lý");
        labelTaiKhoanHienTai.setOpaque(true);
        labelTaiKhoanHienTai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTaiKhoanHienTaiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThongTinTaiKhoanLayout = new javax.swing.GroupLayout(panelThongTinTaiKhoan);
        panelThongTinTaiKhoan.setLayout(panelThongTinTaiKhoanLayout);
        panelThongTinTaiKhoanLayout.setHorizontalGroup(
            panelThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinTaiKhoanLayout.createSequentialGroup()
                .addGroup(panelThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTenDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(labelTaiKhoanHienTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelThongTinTaiKhoanLayout.setVerticalGroup(
            panelThongTinTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinTaiKhoanLayout.createSequentialGroup()
                .addComponent(labelTenDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTaiKhoanHienTai)
                .addContainerGap())
        );

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 0, 0));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_dangXuat_30px.png"))); // NOI18N
        btnDangXuat.setText("ĐĂNG XUẤT");
        btnDangXuat.setBorder(null);
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
        });

        labelQLThongKeNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLThongKeNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLThongKeNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLThongKeNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_thongKe.png"))); // NOI18N
        labelQLThongKeNavbar.setText("    THỐNG KÊ");
        labelQLThongKeNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLThongKeNavbar.setOpaque(true);
        labelQLThongKeNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLThongKeNavbarMouseClicked(evt);
            }
        });

        labelQLDonHangNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLDonHangNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLDonHangNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLDonHangNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_purchase_30px.png"))); // NOI18N
        labelQLDonHangNavbar.setText("    ĐƠN HÀNG");
        labelQLDonHangNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLDonHangNavbar.setOpaque(true);
        labelQLDonHangNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLDonHangNavbarMouseClicked(evt);
            }
        });

        labelQLSanPhamNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLSanPhamNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLSanPhamNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLSanPhamNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_product_30px.png"))); // NOI18N
        labelQLSanPhamNavbar.setText("    SẢN PHẨM");
        labelQLSanPhamNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLSanPhamNavbar.setOpaque(true);
        labelQLSanPhamNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLSanPhamNavbarMouseClicked(evt);
            }
        });

        labelQLTaiKhoanNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLTaiKhoanNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLTaiKhoanNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLTaiKhoanNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_account.png"))); // NOI18N
        labelQLTaiKhoanNavbar.setText("    TÀI KHOẢN");
        labelQLTaiKhoanNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLTaiKhoanNavbar.setOpaque(true);
        labelQLTaiKhoanNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLTaiKhoanNavbarMouseClicked(evt);
            }
        });

        labelQLToppingNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLToppingNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLToppingNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLToppingNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_topping.png"))); // NOI18N
        labelQLToppingNavbar.setText("    TOPPING");
        labelQLToppingNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLToppingNavbar.setOpaque(true);
        labelQLToppingNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLToppingNavbarMouseClicked(evt);
            }
        });

        labelQLNhaCungCapNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLNhaCungCapNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLNhaCungCapNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLNhaCungCapNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icons8_supplier_25px.png"))); // NOI18N
        labelQLNhaCungCapNavbar.setText("    NHÀ CUNG CẤP");
        labelQLNhaCungCapNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLNhaCungCapNavbar.setOpaque(true);
        labelQLNhaCungCapNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLNhaCungCapNavbarMouseClicked(evt);
            }
        });

        labelQLNguyenLieuNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLNguyenLieuNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLNguyenLieuNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLNguyenLieuNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/ingridient.jpg"))); // NOI18N
        labelQLNguyenLieuNavbar.setText("    NGUYÊN LIỆU");
        labelQLNguyenLieuNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLNguyenLieuNavbar.setOpaque(true);
        labelQLNguyenLieuNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLNguyenLieuNavbarMouseClicked(evt);
            }
        });

        labelQLNhapNguyenLieuNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLNhapNguyenLieuNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLNhapNguyenLieuNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLNhapNguyenLieuNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_nhap_hang.jpg"))); // NOI18N
        labelQLNhapNguyenLieuNavbar.setText("    NHẬP HÀNG");
        labelQLNhapNguyenLieuNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLNhapNguyenLieuNavbar.setOpaque(true);
        labelQLNhapNguyenLieuNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLNhapNguyenLieuNavbarMouseClicked(evt);
            }
        });

        labelQLLoaiSanPhamNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLLoaiSanPhamNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLLoaiSanPhamNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLLoaiSanPhamNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_category_30px.png"))); // NOI18N
        labelQLLoaiSanPhamNavbar.setText("    THỂ LOẠI");
        labelQLLoaiSanPhamNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLLoaiSanPhamNavbar.setOpaque(true);
        labelQLLoaiSanPhamNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLLoaiSanPhamNavbarMouseClicked(evt);
            }
        });

        labelQLKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelQLKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_customer_30px.png"))); // NOI18N
        labelQLKhachHang.setText("    KHÁCH HÀNG");
        labelQLKhachHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLKhachHang.setOpaque(true);
        labelQLKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLKhachHangMouseClicked(evt);
            }
        });

        labelQLMenuNavbar.setBackground(new java.awt.Color(255, 255, 255));
        labelQLMenuNavbar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelQLMenuNavbar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQLMenuNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_menu_25px.png"))); // NOI18N
        labelQLMenuNavbar.setText("     MENU");
        labelQLMenuNavbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        labelQLMenuNavbar.setOpaque(true);
        labelQLMenuNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelQLMenuNavbarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelNavbarLayout = new javax.swing.GroupLayout(panelNavbar);
        panelNavbar.setLayout(panelNavbarLayout);
        panelNavbarLayout.setHorizontalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNavbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelThongTinTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLTaiKhoanNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLSanPhamNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLMenuNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLToppingNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLDonHangNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLThongKeNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLNguyenLieuNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLNhaCungCapNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLNhapNguyenLieuNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLLoaiSanPhamNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelQLKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelNavbarLayout.setVerticalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavbarLayout.createSequentialGroup()
                .addComponent(panelThongTinTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelQLTaiKhoanNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLSanPhamNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLMenuNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelQLToppingNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLDonHangNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLThongKeNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLNhaCungCapNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelQLNguyenLieuNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLNhapNguyenLieuNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLLoaiSanPhamNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQLKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelParentContent.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelParentContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelParentContent, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelQLTaiKhoanNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLTaiKhoanNavbarMouseClicked
        changeNhanVienPanel();
    }//GEN-LAST:event_labelQLTaiKhoanNavbarMouseClicked

    private void labelQLSanPhamNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLSanPhamNavbarMouseClicked
        changeSanPhamPanel();
    }//GEN-LAST:event_labelQLSanPhamNavbarMouseClicked

    private void labelQLDonHangNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLDonHangNavbarMouseClicked
        changeDonHangPanel();
    }//GEN-LAST:event_labelQLDonHangNavbarMouseClicked

    private void labelQLThongKeNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLThongKeNavbarMouseClicked
        changeThongKePanel();
    }//GEN-LAST:event_labelQLThongKeNavbarMouseClicked

    private void labelQLToppingNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLToppingNavbarMouseClicked
        changeToppingPanel();
    }//GEN-LAST:event_labelQLToppingNavbarMouseClicked

    private void labelQLNhaCungCapNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLNhaCungCapNavbarMouseClicked
        // TODO add your handling code here:
        changeNhaCungCapTinPanel();
    }//GEN-LAST:event_labelQLNhaCungCapNavbarMouseClicked


    private void labelQLLoaiSanPhamNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLLoaiSanPhamNavbarMouseClicked
        changLoaiSanPhamPanel();
    }//GEN-LAST:event_labelQLLoaiSanPhamNavbarMouseClicked

    private void labelQLNguyenLieuNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLNguyenLieuNavbarMouseClicked
        // TODO add your handling code here:
        // click vào label này sẽ hiện ra panql quản lý nguyen liệu
        changeNguyenLieuPanel();
    }//GEN-LAST:event_labelQLNguyenLieuNavbarMouseClicked

    private void labelQLKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLKhachHangMouseClicked
        // TODO add your handling code here:
        // click vào label này thì sẽ hiện ra panel QL Khách hàng
        changeKhachHangPanel();
    }//GEN-LAST:event_labelQLKhachHangMouseClicked

    private void labelQLNhapNguyenLieuNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLNhapNguyenLieuNavbarMouseClicked
        // TODO add your handling code here:
        // khi click vào label này nó hiển thị panel quản lý nhập nguyên liệu
        changeNhapNguyenLieuPanel();
    }//GEN-LAST:event_labelQLNhapNguyenLieuNavbarMouseClicked

    private void labelTenDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTenDoAnMouseClicked
        // TODO add your handling code here:
//        changeThayDoiThongTinPanel();
    }//GEN-LAST:event_labelTenDoAnMouseClicked

    private void labelQLMenuNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelQLMenuNavbarMouseClicked
        // TODO add your handling code here:
        changeMenuPanel();
    }//GEN-LAST:event_labelQLMenuNavbarMouseClicked

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        // TODO add your handling code here:
        // bấm vào nút vào này thì sẽ đăng xuất
        this.dispose();
        Login a = new Login();
        a.setVisible(true);
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void labelTaiKhoanHienTaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTaiKhoanHienTaiMouseClicked
        // TODO add your handling code here:
          changeThayDoiThongTinPanel();
    }//GEN-LAST:event_labelTaiKhoanHienTaiMouseClicked

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
            java.util.logging.Logger.getLogger(GiaoDienQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(GiaoDienQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
//              
//              

                new GiaoDienQuanLy();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JLabel labelQLDonHangNavbar;
    private javax.swing.JLabel labelQLKhachHang;
    private javax.swing.JLabel labelQLLoaiSanPhamNavbar;
    private javax.swing.JLabel labelQLMenuNavbar;
    private javax.swing.JLabel labelQLNguyenLieuNavbar;
    private javax.swing.JLabel labelQLNhaCungCapNavbar;
    private javax.swing.JLabel labelQLNhapNguyenLieuNavbar;
    private javax.swing.JLabel labelQLSanPhamNavbar;
    private javax.swing.JLabel labelQLTaiKhoanNavbar;
    private javax.swing.JLabel labelQLThongKeNavbar;
    private javax.swing.JLabel labelQLToppingNavbar;
    private javax.swing.JLabel labelTaiKhoanHienTai;
    private javax.swing.JLabel labelTenDoAn;
    private javax.swing.JPanel panelNavbar;
    private javax.swing.JPanel panelParentContent;
    private javax.swing.JPanel panelThongTinTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
