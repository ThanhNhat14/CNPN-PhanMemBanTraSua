/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.AccountDAO;
import Dao.VoucherDao;
import Dao.BillDAO;
import Dao.BillDetailDAO;
import Dao.CustomerDAO;
import Dao.ItemDAO;
import Dao.ProductSizeDAO;
import Dao.ToppingDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Model.Manager.Account;
import Model.Manager.Bill;
import Model.Manager.BillDetail;
import Model.Manager.Customer;
import Model.Manager.Item;
import Model.Manager.ProductSize;
import Model.Manager.Topping;
import Utils.Manager.FormatMoney;

public class QLDonHang extends javax.swing.JInternalFrame {

    public DefaultTableModel defaultTableModelDSHoaDon;
    ArrayList<Bill> dsHoaDon;
    public BillDAO billDAO;
    public AccountDAO accountDAO;
    public CustomerDAO customerDAO;
    public BillDetailDAO billDetailDAO;
    public ItemDAO itemDAO;
    public ProductSizeDAO productSizeDAO;
    public ToppingDAO toppingDAO;
    public VoucherDao voucherDAO;

    public QLDonHang() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        initComponents();

        this.billDAO = new BillDAO();
        this.accountDAO = new AccountDAO();
        this.customerDAO = new CustomerDAO();
        this.billDetailDAO = new BillDetailDAO();
        this.itemDAO = new ItemDAO();
        this.productSizeDAO = new ProductSizeDAO();
        this.toppingDAO = new ToppingDAO();
        this.voucherDAO = new VoucherDao();
        this.dsHoaDon = new ArrayList<>();

        this.defaultTableModelDSHoaDon = new DefaultTableModel();
        this.tableDSHoaDon.setModel(this.defaultTableModelDSHoaDon);
        String[] columnsDSHoaDon = {"Mã hóa đơn", "Phụ trách", "Ngày tạo", "Tổng tiền", "Trạng thái"};
        this.defaultTableModelDSHoaDon.setColumnIdentifiers(columnsDSHoaDon);
        JTableHeader headerDSHoaDon = this.tableDSHoaDon.getTableHeader();
        headerDSHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSHoaDon.setBackground(new Color(0x43CD80));
        headerDSHoaDon.setForeground(Color.WHITE);
        headerDSHoaDon.setPreferredSize(new Dimension(headerDSHoaDon.getWidth(), 30));
        this.tableDSHoaDon.setRowHeight(30);
        this.tableDSHoaDon.setSelectionBackground(new Color(0xE2E8F0));
        this.tableDSHoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.tableDSHoaDon.getColumnModel().getColumn(1).setPreferredWidth(220);
        this.tableDSHoaDon.getColumnModel().getColumn(2).setPreferredWidth(120);
        this.tableDSHoaDon.getColumnModel().getColumn(3).setPreferredWidth(120);
        this.tableDSHoaDon.getColumnModel().getColumn(4).setPreferredWidth(100);

        ArrayList<Account> dsTaiKhoan = this.accountDAO.getAllAccount();
        for (Account i : dsTaiKhoan) {
            this.comboBoxTimKiemHoaDonTheoNguoiTao.addItem(i.getFullName());
        }

        this.scrollPaneDSHoaDon.getViewport().setBackground(Color.WHITE);

        getAllBill(this.defaultTableModelDSHoaDon, this.dsHoaDon);

        this.dateChooserTimHoaDonTheoNgayBatDau.setDateFormatString("dd/MM/yyyy");
        this.dateChooserTimHoaDonTheoNgayBatDau.setFont(new Font("Arial", Font.BOLD, 14));
        this.dateChooserTimHoaDonTheoNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        this.dateChooserTimHoaDonTheoNgayKetThuc.setFont(new Font("Arial", Font.BOLD, 14));

        this.panelHienThiDSSanPhamTrongHoaDOn.setLayout(new BoxLayout(this.panelHienThiDSSanPhamTrongHoaDOn, BoxLayout.Y_AXIS));

        this.scrollPaneDSSanPhamTrongHoaDon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPaneDSSanPhamTrongHoaDon.getVerticalScrollBar().setPreferredSize(new Dimension(7, Integer.MAX_VALUE));
        this.scrollPaneDSSanPhamTrongHoaDon.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
        this.scrollPaneDSSanPhamTrongHoaDon.getHorizontalScrollBar().setBackground(Color.GRAY);
    }

    public void xuatDSHoaDonRaTable(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        defaultTableModel.setRowCount(0);
        Account a = null;
        for (Bill i : ds) {
            a = this.accountDAO.getAccountByID(i.getIdAccount());
            Object[] data = {i.getIdBill(), a.getFullName(), i.getDateOrder(), FormatMoney.formatMoney(i.getTotalPrice()), i.getStatusPurchase()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllBill(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        ds = this.billDAO.getAllBill();
        xuatDSHoaDonRaTable(defaultTableModel, ds);
    }

    public void timHoaDonTheoNguoiPhuTrach(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        String tenNguoiPhuTrach = this.comboBoxTimKiemHoaDonTheoNguoiTao.getSelectedItem() + "";
        if (tenNguoiPhuTrach != null) {
            if (tenNguoiPhuTrach.equals("Tất cả")) {
                getAllBill(this.defaultTableModelDSHoaDon, this.dsHoaDon);
            } else {
                Account account = this.accountDAO.getAccountByFullName(tenNguoiPhuTrach);
                ArrayList<Bill> kq = this.billDAO.findBillByIdAccount(account.getIdAccount());
                if (!kq.isEmpty()) {
                    xuatDSHoaDonRaTable(defaultTableModel, kq);
                } else {
                    JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
                }
            }
        }
    }

    public void timHoaDonTheoLoaiHoaDon(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        // ta tìm kiếm hóa đơn theo hóa đơn tại quán hoặc online
        String loaiHoaDonCanTim = this.comboBoxTimHoaDonTheoPhanLoai.getSelectedItem() + "";
        if (loaiHoaDonCanTim != null) {
            if (loaiHoaDonCanTim.equals("Tất cả")) {
                getAllBill(this.defaultTableModelDSHoaDon, this.dsHoaDon);
            } else if (loaiHoaDonCanTim.equals("Online")) {
                ArrayList<Bill> kq = this.billDAO.findBillWithNotNullIdCustomer();
                xuatDSHoaDonRaTable(defaultTableModel, kq);
            } else {
                ArrayList<Bill> kq = this.billDAO.findBillWithNullIdCustomer();
                xuatDSHoaDonRaTable(defaultTableModel, kq);
            }
        }
    }

//    public void timHoaDonTuNgayBatDauDenNgayKetThuc(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
//        if (!this.textFieldTimHoaDonTheoNgayStart.getText().equals("") && !this.textFieldTimHoaDonTheoNgayEnd.getText().equals("")) {
//            String startDate = this.textFieldTimHoaDonTheoNgayStart.getText() + "";
//            String endDate = this.textFieldTimHoaDonTheoNgayEnd.getText() + "";
//            ArrayList<Bill> kq = this.billDAO.findBillByDateOrder(startDate, endDate);
//            if (kq.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
//            } else {
//                xuatDSHoaDonRaTable(defaultTableModel, kq);
//            }
//        }
//    }
    //         Date a = this.jDateChooser1.getDate();
    //        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //        String dateString = sdf.format(a);
    //        System.out.println(dateString);
    public void timHoaDonTuNgayBatDauDenNgayKetThuc(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date a = this.dateChooserTimHoaDonTheoNgayBatDau.getDate();
        String dateStartString = sdf.format(a);
        Date b = this.dateChooserTimHoaDonTheoNgayKetThuc.getDate();
        String dateEndString = sdf.format(b);
        if (!dateStartString.equals("") && !dateEndString.equals("")) {
            ArrayList<Bill> kq = this.billDAO.findBillByDateOrder(dateStartString, dateEndString);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
            } else {
                xuatDSHoaDonRaTable(defaultTableModel, kq);
            }
        }
    }

    public void timHoaDonTheoNgayBatDau(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date a = this.dateChooserTimHoaDonTheoNgayBatDau.getDate();
        String dateStartString = sdf.format(a);
//        String startDate = this.textFieldTimHoaDonTheoNgayStart.getText() + "";
        ArrayList<Bill> kq = this.billDAO.findBillByStartDateOrder(dateStartString);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonRaTable(defaultTableModel, kq);
        }
    }

    public void timHoaDonTheoNgayKetThuc(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date b = this.dateChooserTimHoaDonTheoNgayKetThuc.getDate();
        String dateEndString = sdf.format(b);
        ArrayList<Bill> kq = this.billDAO.findBillByEndDateOrder(dateEndString);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonRaTable(defaultTableModel, kq);
        }
    }

    public void timHoaDonTheoNgay(DefaultTableModel defaultTableModel, ArrayList<Bill> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date b = this.dateChooserTimHoaDonTheoNgayKetThuc.getDate();
        String dateEndString = sdf.format(b);
        ArrayList<Bill> kq = this.billDAO.findBillByDay(dateEndString);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonRaTable(defaultTableModel, kq);
        }
    }

    public Bill layHoaDonKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idBill = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Bill a = this.billDAO.getBillById(idBill);
            return a;
        }
        return null;
    }

//    public void hienThiHoaDonRaFrame(JTable table, DefaultTableModel defaultTableModel) {
//        Bill a = layHoaDonKhiClickVaoBang(table, defaultTableModel);
//        this.panelHienThiDSSanPhamTrongHoaDOn.removeAll();
//        this.panelHienThiDSSanPhamTrongHoaDOn.revalidate();
//        this.panelHienThiDSSanPhamTrongHoaDOn.repaint();
//        if (a != null) {
//            this.labelXemNoiDungMaHoaDon.setText(a.getIdBill() + "");
//            Account b = this.accountDAO.getAccountByID(a.getIdAccount());
//            this.labelXemNoiDungNguoiPhuTrachHoaDon.setText(b.getFullName());
//            this.labelXemNoiDungSoLuongSanPhamHoaDon.setText(a.getTotalAmount() + "");
//            this.labelXemNoiDungKhuyenMaiHoaDon.setText(a.getDiscount() + "");
//            this.labelXemNoiDungDiaChiNhanHangHoaDon.setText(a.getDeliveryAddress());
//            this.labelXemNoiDungGhiChuHoaDon.setText(a.getNote());
//            this.labelXemNoiDungTongTienHoaDon.setText(FormatMoney.formatMoney(a.getTotalPrice()));
//            this.labelXemNoiDungTrangThaiHoaDon.setText(a.getStatusPurchase());
//            this.labelXemNoiDungPhuongThucThanhToanHoaDon.setText(a.getPaymentMethod());
//            if (a.getIdCustomer() > 0) {
//                Customer c = this.customerDAO.getCustomerById(a.getIdCustomer());
//                this.labelXemNoiDungKhachHangHoaDon.setText(c.getNameCustomer());
//            } else {
//                this.labelXemNoiDungKhachHangHoaDon.setText("Khách tại quán");
//            }
//            ArrayList<BillDetail> dsBillDetail = this.billDetailDAO.getAllBillDetailByIdBill(a.getIdBill());
//            ArrayList<Integer> dsIdItemDetail = new ArrayList<>();
//            for (BillDetail billDetail : dsBillDetail) {
//                Item item = this.itemDAO.getItemById(billDetail.getIdItem());
//                dsIdItemDetail.add(item.getIdItemDetail());
//            }
//            Set <Integer> set = new HashSet<>(dsIdItemDetail);
//            dsIdItemDetail = new ArrayList<>(set);
//            ArrayList<Item> dsItems = new ArrayList<>();
//            String tenSanPham;
//            String tenSize;
//            String dsTopping = "";
//            int tongTienMotSanPham = 0;
//            ProductSize productSize;
//            for (BillDetail i : dsBillDetail) {
//                Item item = this.itemDAO.getItemById(i.getIdItem());
//                int theIdItemDetail = item.getIdItemDetail();
//                productSize = this.productSizeDAO.getProductSizeById(item.getIdProductSize());
//                tenSanPham = this.productSizeDAO.findProductNameByIdProductSize(item.getIdProductSize());
//                tenSize = "Size " + this.productSizeDAO.findSizeNameByIdProductSize(item.getIdProductSize());
//                dsItems = this.itemDAO.getItemsByIdItemDetail(theIdItemDetail);
//                tongTienMotSanPham += productSize.getPrice() * i.getQuantityProduct();
//                if (!dsItems.isEmpty()) {
//                    for (Item e : dsItems) {
//                        dsTopping += this.toppingDAO.getToppingById(e.getIdTopping()).getNameTopping() + " x " + e.getQuantityTopping() + ", ";
//                    }
//                    for (Item e : dsItems) {
//                        tongTienMotSanPham += this.toppingDAO.getToppingById(e.getIdTopping()).getPriceTopping() * e.getQuantityTopping();
//                    }
//                }
//                PanelSanPham panelSanPham = new PanelSanPham(tenSanPham, tenSize, dsTopping, FormatMoney.formatMoney(tongTienMotSanPham), i.getQuantityProduct());
//                this.panelHienThiDSSanPhamTrongHoaDOn.add(panelSanPham);
//                tongTienMotSanPham = 0;
//                dsItems = null;
//                dsTopping = "";
//            }
//        }
//    }
    public void hienThiHoaDonRaFrame(JTable table, DefaultTableModel defaultTableModel) {
        Bill a = layHoaDonKhiClickVaoBang(table, defaultTableModel);
        this.panelHienThiDSSanPhamTrongHoaDOn.removeAll();
        this.panelHienThiDSSanPhamTrongHoaDOn.revalidate();
        this.panelHienThiDSSanPhamTrongHoaDOn.repaint();
        if (a != null) {
            this.labelXemNoiDungMaHoaDon.setText(a.getIdBill() + "");
            Account b = this.accountDAO.getAccountByID(a.getIdAccount());
            this.labelXemNoiDungNguoiPhuTrachHoaDon.setText(b.getFullName());
            this.labelXemNoiDungSoLuongSanPhamHoaDon.setText(a.getTotalAmount() + "");
            this.labelXemNoiDungKhuyenMaiHoaDon.setText(a.getDiscount() + "");
            this.labelXemNoiDungDiaChiNhanHangHoaDon.setText(a.getDeliveryAddress());
            this.labelXemNoiDungGhiChuHoaDon.setText(a.getNote());

//            if (a.getDiscount() > 0) {
//                this.labelXemNoiDungGhiChuHoaDon.setText("Giảm giá: " + String.valueOf(a.getDiscount()) + "%");
//            }
            this.labelXemNoiDungTongTienHoaDon.setText(FormatMoney.formatMoney(a.getTotalPrice()));
            this.labelXemNoiDungTrangThaiHoaDon.setText(a.getStatusPurchase());
            this.labelXemNoiDungPhuongThucThanhToanHoaDon.setText(a.getPaymentMethod());
            this.labelNoiDungNguyenNhanHuyDonHang.setText(a.getCancelReason());
            if (a.getIdCustomer() > 0) {
                Customer c = this.customerDAO.getCustomerById(a.getIdCustomer());
                this.labelXemNoiDungKhachHangHoaDon.setText(c.getNameCustomer());
                this.labelXemNoiDungPhiVanChuyen.setText("16,000");
            } else {
                this.labelXemNoiDungKhachHangHoaDon.setText("Khách tại quán");
                this.labelXemNoiDungPhiVanChuyen.setText("0");
            }
            ArrayList<BillDetail> dsBillDetail = this.billDetailDAO.getAllBillDetailByIdBill(a.getIdBill());
            ArrayList<Integer> dsIdItemDetail = new ArrayList<>();
            ArrayList<Integer> dsSoLuongMotBillDetail = new ArrayList<>();
            for (BillDetail billDetail : dsBillDetail) {
                Item item = this.itemDAO.getItemById(billDetail.getIdItem());
                dsIdItemDetail.add(item.getIdItemDetail());
                dsSoLuongMotBillDetail.add(billDetail.getQuantityProduct());
            }
            Set<Integer> set = new HashSet<>(dsIdItemDetail);
            dsIdItemDetail = new ArrayList<>(set);
            ArrayList<Item> dsItems = new ArrayList<>();
            String tenSanPham;
            String tenSize;
            String dsTopping = "";
            int soLuongMotItem = 1;
            int tongTienMotSanPham = 0;
            int tempDem = 0;
            ProductSize productSize;

            //vcdsavdsv
//            for (BillDetail i : dsBillDetail) {
//                Item item = this.itemDAO.getItemById(i.getIdItem());
//                int theIdItemDetail = item.getIdItemDetail();
//                productSize = this.productSizeDAO.getProductSizeById(item.getIdProductSize());
//                tenSanPham = this.productSizeDAO.findProductNameByIdProductSize(item.getIdProductSize());
//                tenSize = "Size " + this.productSizeDAO.findSizeNameByIdProductSize(item.getIdProductSize());
//                dsItems = this.itemDAO.getItemsByIdItemDetail(theIdItemDetail);
//                tongTienMotSanPham += productSize.getPrice() * i.getQuantityProduct();
//                if (!dsItems.isEmpty()) {
//                    for (Item e : dsItems) {
//                        dsTopping += this.toppingDAO.getToppingById(e.getIdTopping()).getNameTopping() + " x " + e.getQuantityTopping() + ", ";
//                    }
//                    for (Item e : dsItems) {
//                        tongTienMotSanPham += this.toppingDAO.getToppingById(e.getIdTopping()).getPriceTopping() * e.getQuantityTopping();
//                    }
//                }
//                PanelSanPham panelSanPham = new PanelSanPham(tenSanPham, tenSize, dsTopping, FormatMoney.formatMoney(tongTienMotSanPham), i.getQuantityProduct());
//                this.panelHienThiDSSanPhamTrongHoaDOn.add(panelSanPham);
//                tongTienMotSanPham = 0;
//                dsItems = null;
//                dsTopping = "";
//            }
            // hdgfhgf
            for (int i : dsIdItemDetail) {
                dsItems = this.itemDAO.getItemsByIdItemDetail(i);
                soLuongMotItem = dsSoLuongMotBillDetail.get(tempDem);
                tenSanPham = this.productSizeDAO.findProductNameByIdProductSize(dsItems.get(0).getIdProductSize());
                tenSize = "Size " + this.productSizeDAO.findSizeNameByIdProductSize(dsItems.get(0).getIdProductSize());
                for (Item e : dsItems) {
                    if (e.getIdTopping() != -1) {
                        if (this.toppingDAO.getToppingById(e.getIdTopping()) != null) {
                            dsTopping += this.toppingDAO.getToppingById(e.getIdTopping()).getNameTopping() + " x " + e.getQuantityTopping() + ", ";
                        }
                    } else {
                        dsTopping += "";
                    }
                }
                for (Item e : dsItems) {
                    if (e.getQuantityTopping() != -1 && e.getIdTopping() != -1) {
                        if (this.toppingDAO.getToppingById(e.getIdTopping()) != null) {
                            tongTienMotSanPham += this.toppingDAO.getToppingById(e.getIdTopping()).getPriceTopping() * e.getQuantityTopping();
                        }
                    }
//                    else {
//                        tongTienMotSanPham += 0;
//                    }
                }
                productSize = this.productSizeDAO.getProductSizeById(dsItems.get(0).getIdProductSize());
                BillDetail temp = this.billDetailDAO.getBillDetailByIdItem(dsItems.get(0).getIdItem());
                tongTienMotSanPham += productSize.getPrice() * temp.getQuantityProduct();
                PanelSanPham panelSanPham = new PanelSanPham(tenSanPham, tenSize, dsTopping, FormatMoney.formatMoney(tongTienMotSanPham), soLuongMotItem);
                this.panelHienThiDSSanPhamTrongHoaDOn.add(panelSanPham);
                tongTienMotSanPham = 0;
                dsItems = null;
                dsTopping = "";
                tempDem++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameXemChiTietHoaDon = new javax.swing.JFrame();
        panelXemChiTietHoaDon = new javax.swing.JPanel();
        labelTitleXemChiTietHoaDon = new javax.swing.JLabel();
        labelXemMaHoaDom = new javax.swing.JLabel();
        labelXemNoiDungMaHoaDon = new javax.swing.JLabel();
        labelXemNguoiPhuTrachHoaDon = new javax.swing.JLabel();
        labelXemNoiDungNguoiPhuTrachHoaDon = new javax.swing.JLabel();
        labelXemSoLuongSanPhamHoaDon = new javax.swing.JLabel();
        labelXemNoiDungSoLuongSanPhamHoaDon = new javax.swing.JLabel();
        labelXemKhuyenMaiHoaDon = new javax.swing.JLabel();
        labelXemNoiDungKhuyenMaiHoaDon = new javax.swing.JLabel();
        labelXemKhachHangHoaDon = new javax.swing.JLabel();
        labelXemNoiDungKhachHangHoaDon = new javax.swing.JLabel();
        labelXemTrangThaiDonHang = new javax.swing.JLabel();
        labelXemNoiDungTrangThaiHoaDon = new javax.swing.JLabel();
        labelXemPhuongThucThanhToanHoaDon = new javax.swing.JLabel();
        labelXemNoiDungPhuongThucThanhToanHoaDon = new javax.swing.JLabel();
        labelXemDiaChiNhaHangHoaDon = new javax.swing.JLabel();
        labelXemNoiDungDiaChiNhanHangHoaDon = new javax.swing.JLabel();
        labelGhiChuHoaDon = new javax.swing.JLabel();
        labelXemNoiDungGhiChuHoaDon = new javax.swing.JLabel();
        labelXemTongTienHoaDon = new javax.swing.JLabel();
        labelXemNoiDungTongTienHoaDon = new javax.swing.JLabel();
        scrollPaneDSSanPhamTrongHoaDon = new javax.swing.JScrollPane();
        panelHienThiDSSanPhamTrongHoaDOn = new javax.swing.JPanel();
        labelTenQuan = new javax.swing.JLabel();
        labelNguyenNhanHuyDonHang = new javax.swing.JLabel();
        labelNoiDungNguyenNhanHuyDonHang = new javax.swing.JLabel();
        labelPhiVanChuyen = new javax.swing.JLabel();
        labelXemNoiDungPhiVanChuyen = new javax.swing.JLabel();
        panelQLDonHangContent = new javax.swing.JPanel();
        labelTitleQLDonHangContent = new javax.swing.JLabel();
        panelTimKiemDonHang = new javax.swing.JPanel();
        labelTimKiemTheoNguoiTaoHoaDon = new javax.swing.JLabel();
        comboBoxTimKiemHoaDonTheoNguoiTao = new javax.swing.JComboBox<>();
        labelTimKiemHoaDonTheoPhanLoai = new javax.swing.JLabel();
        comboBoxTimHoaDonTheoPhanLoai = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        labelTimTheoNgayStart = new javax.swing.JLabel();
        labelTimHoaDonTheoNgayEnd = new javax.swing.JLabel();
        btnTimHoaDon = new javax.swing.JButton();
        btnHuyTim = new javax.swing.JButton();
        btnXemChiTietHoaDon = new javax.swing.JButton();
        dateChooserTimHoaDonTheoNgayBatDau = new com.toedter.calendar.JDateChooser();
        dateChooserTimHoaDonTheoNgayKetThuc = new com.toedter.calendar.JDateChooser();
        scrollPaneDSHoaDon = new javax.swing.JScrollPane();
        tableDSHoaDon = new javax.swing.JTable();
        labelDSHoaDon = new javax.swing.JLabel();

        frameXemChiTietHoaDon.setTitle("Hóa đơn mua hàng");

        panelXemChiTietHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemChiTietHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemChiTietHoaDon.setText("THÔNG TIN CHI TIẾT HÓA ĐƠN BÁN HÀNG");

        labelXemMaHoaDom.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaHoaDom.setText("Mã hóa đơn");

        labelXemNoiDungMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaHoaDon.setText("0");

        labelXemNguoiPhuTrachHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNguoiPhuTrachHoaDon.setText("Phụ trách");

        labelXemNoiDungNguoiPhuTrachHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNguoiPhuTrachHoaDon.setText("Nhân viên");

        labelXemSoLuongSanPhamHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemSoLuongSanPhamHoaDon.setText("Số sản phẩm");

        labelXemNoiDungSoLuongSanPhamHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungSoLuongSanPhamHoaDon.setText("0");

        labelXemKhuyenMaiHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemKhuyenMaiHoaDon.setText("Khuyến mãi (%)");

        labelXemNoiDungKhuyenMaiHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungKhuyenMaiHoaDon.setText("0");

        labelXemKhachHangHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemKhachHangHoaDon.setText("Khách hàng");

        labelXemNoiDungKhachHangHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungKhachHangHoaDon.setText("Khách hàng 1");

        labelXemTrangThaiDonHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemTrangThaiDonHang.setText("Trạng thái");

        labelXemNoiDungTrangThaiHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungTrangThaiHoaDon.setText("Đã giao");

        labelXemPhuongThucThanhToanHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemPhuongThucThanhToanHoaDon.setText("Thanh toán");

        labelXemNoiDungPhuongThucThanhToanHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungPhuongThucThanhToanHoaDon.setText("Tiền mặt");

        labelXemDiaChiNhaHangHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemDiaChiNhaHangHoaDon.setText("Địa chỉ nhận hàng");

        labelXemNoiDungDiaChiNhanHangHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungDiaChiNhanHangHoaDon.setText("97 Man Thiện, Phường Hiệp Phú, Quận 9");

        labelGhiChuHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelGhiChuHoaDon.setText("Ghi chú");

        labelXemNoiDungGhiChuHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungGhiChuHoaDon.setText("Ghi chú");

        labelXemTongTienHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelXemTongTienHoaDon.setText("TỔNG TIỀN (VNĐ)");

        labelXemNoiDungTongTienHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelXemNoiDungTongTienHoaDon.setForeground(new java.awt.Color(255, 0, 0));
        labelXemNoiDungTongTienHoaDon.setText("100000");

        scrollPaneDSSanPhamTrongHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneDSSanPhamTrongHoaDon.setBorder(null);

        panelHienThiDSSanPhamTrongHoaDOn.setBackground(new java.awt.Color(255, 255, 255));
        panelHienThiDSSanPhamTrongHoaDOn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout panelHienThiDSSanPhamTrongHoaDOnLayout = new javax.swing.GroupLayout(panelHienThiDSSanPhamTrongHoaDOn);
        panelHienThiDSSanPhamTrongHoaDOn.setLayout(panelHienThiDSSanPhamTrongHoaDOnLayout);
        panelHienThiDSSanPhamTrongHoaDOnLayout.setHorizontalGroup(
            panelHienThiDSSanPhamTrongHoaDOnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        panelHienThiDSSanPhamTrongHoaDOnLayout.setVerticalGroup(
            panelHienThiDSSanPhamTrongHoaDOnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        scrollPaneDSSanPhamTrongHoaDon.setViewportView(panelHienThiDSSanPhamTrongHoaDOn);

        labelTenQuan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTenQuan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTenQuan.setText("TRÀ SỮA TASU");

        labelNguyenNhanHuyDonHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNguyenNhanHuyDonHang.setText("Nguyên nhân hủy");

        labelNoiDungNguyenNhanHuyDonHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungNguyenNhanHuyDonHang.setText("    ");

        labelPhiVanChuyen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelPhiVanChuyen.setText("Phí vận chuyển");

        labelXemNoiDungPhiVanChuyen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungPhiVanChuyen.setText("0");

        javax.swing.GroupLayout panelXemChiTietHoaDonLayout = new javax.swing.GroupLayout(panelXemChiTietHoaDon);
        panelXemChiTietHoaDon.setLayout(panelXemChiTietHoaDonLayout);
        panelXemChiTietHoaDonLayout.setHorizontalGroup(
            panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneDSSanPhamTrongHoaDon)
                    .addComponent(labelTitleXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTenQuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelXemKhuyenMaiHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(labelXemSoLuongSanPhamHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemNguoiPhuTrachHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemMaHoaDom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelXemNoiDungMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelXemNoiDungNguoiPhuTrachHoaDon)
                            .addComponent(labelXemNoiDungSoLuongSanPhamHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelXemNoiDungKhuyenMaiHoaDon))
                        .addGap(190, 190, 190)
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelXemKhachHangHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(labelXemTrangThaiDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemPhuongThucThanhToanHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPhiVanChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelXemNoiDungKhachHangHoaDon)
                            .addComponent(labelXemNoiDungTrangThaiHoaDon)
                            .addComponent(labelXemNoiDungPhuongThucThanhToanHoaDon)
                            .addComponent(labelXemNoiDungPhiVanChuyen)))
                    .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                        .addComponent(labelXemTongTienHoaDon)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemNoiDungTongTienHoaDon))
                    .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelNguyenNhanHuyDonHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelGhiChuHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelXemDiaChiNhaHangHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelXemNoiDungDiaChiNhanHangHoaDon)
                            .addComponent(labelXemNoiDungGhiChuHoaDon)
                            .addComponent(labelNoiDungNguyenNhanHuyDonHang))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelXemChiTietHoaDonLayout.setVerticalGroup(
            panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTenQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitleXemChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemMaHoaDom)
                    .addComponent(labelXemNoiDungMaHoaDon)
                    .addComponent(labelXemKhachHangHoaDon)
                    .addComponent(labelXemNoiDungKhachHangHoaDon))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemNguoiPhuTrachHoaDon)
                    .addComponent(labelXemNoiDungNguoiPhuTrachHoaDon)
                    .addComponent(labelXemTrangThaiDonHang)
                    .addComponent(labelXemNoiDungTrangThaiHoaDon))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemSoLuongSanPhamHoaDon)
                    .addComponent(labelXemNoiDungSoLuongSanPhamHoaDon)
                    .addComponent(labelXemPhuongThucThanhToanHoaDon)
                    .addComponent(labelXemNoiDungPhuongThucThanhToanHoaDon))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemKhuyenMaiHoaDon)
                    .addComponent(labelXemNoiDungKhuyenMaiHoaDon)
                    .addComponent(labelPhiVanChuyen)
                    .addComponent(labelXemNoiDungPhiVanChuyen))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemDiaChiNhaHangHoaDon)
                    .addComponent(labelXemNoiDungDiaChiNhanHangHoaDon))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGhiChuHoaDon)
                    .addComponent(labelXemNoiDungGhiChuHoaDon))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNguyenNhanHuyDonHang)
                    .addComponent(labelNoiDungNguyenNhanHuyDonHang))
                .addGap(13, 13, 13)
                .addGroup(panelXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemTongTienHoaDon)
                    .addComponent(labelXemNoiDungTongTienHoaDon))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneDSSanPhamTrongHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout frameXemChiTietHoaDonLayout = new javax.swing.GroupLayout(frameXemChiTietHoaDon.getContentPane());
        frameXemChiTietHoaDon.getContentPane().setLayout(frameXemChiTietHoaDonLayout);
        frameXemChiTietHoaDonLayout.setHorizontalGroup(
            frameXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemChiTietHoaDonLayout.setVerticalGroup(
            frameXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitleQLDonHangContent.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTitleQLDonHangContent.setText("QUẢN LÝ ĐƠN HÀNG");

        panelTimKiemDonHang.setBackground(new java.awt.Color(255, 255, 255));

        labelTimKiemTheoNguoiTaoHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        labelTimKiemTheoNguoiTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemTheoNguoiTaoHoaDon.setText("Người tạo");
        labelTimKiemTheoNguoiTaoHoaDon.setOpaque(true);

        comboBoxTimKiemHoaDonTheoNguoiTao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimKiemHoaDonTheoNguoiTao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboBoxTimKiemHoaDonTheoNguoiTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimKiemHoaDonTheoNguoiTaoActionPerformed(evt);
            }
        });

        labelTimKiemHoaDonTheoPhanLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiemHoaDonTheoPhanLoai.setText("Loại hóa đơn");

        comboBoxTimHoaDonTheoPhanLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimHoaDonTheoPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Online", "Tại quán" }));
        comboBoxTimHoaDonTheoPhanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimHoaDonTheoPhanLoaiActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOpaque(true);
        jSeparator1.setPreferredSize(new java.awt.Dimension(4, 10));

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOpaque(true);
        jSeparator2.setPreferredSize(new java.awt.Dimension(4, 10));

        labelTimTheoNgayStart.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimTheoNgayStart.setText("Từ ngày");

        labelTimHoaDonTheoNgayEnd.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimHoaDonTheoNgayEnd.setText("Đến ngày");

        btnTimHoaDon.setBackground(new java.awt.Color(0, 51, 255));
        btnTimHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTimHoaDon.setText("Tìm");
        btnTimHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimHoaDonMouseClicked(evt);
            }
        });

        btnHuyTim.setBackground(new java.awt.Color(255, 0, 51));
        btnHuyTim.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTim.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTim.setText("Hủy tìm");
        btnHuyTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimMouseClicked(evt);
            }
        });

        btnXemChiTietHoaDon.setBackground(new java.awt.Color(16, 185, 129));
        btnXemChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTietHoaDon.setText("Chi tiết");
        btnXemChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemDonHangLayout = new javax.swing.GroupLayout(panelTimKiemDonHang);
        panelTimKiemDonHang.setLayout(panelTimKiemDonHangLayout);
        panelTimKiemDonHangLayout.setHorizontalGroup(
            panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimKiemTheoNguoiTaoHoaDon)
                    .addComponent(comboBoxTimKiemHoaDonTheoNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimKiemHoaDonTheoPhanLoai)
                    .addComponent(comboBoxTimHoaDonTheoPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimTheoNgayStart)
                    .addComponent(dateChooserTimHoaDonTheoNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTimHoaDonTheoNgayEnd)
                    .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                        .addComponent(dateChooserTimHoaDonTheoNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                                .addComponent(btnTimHoaDon)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuyTim))
                            .addComponent(btnXemChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5))
        );
        panelTimKiemDonHangLayout.setVerticalGroup(
            panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTimKiemTheoNguoiTaoHoaDon)
                                    .addComponent(labelTimKiemHoaDonTheoPhanLoai))
                                .addGap(18, 18, 18)
                                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboBoxTimKiemHoaDonTheoNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxTimHoaDonTheoPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTimKiemDonHangLayout.createSequentialGroup()
                                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTimTheoNgayStart)
                                    .addComponent(labelTimHoaDonTheoNgayEnd))
                                .addGap(18, 18, 18)
                                .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTimKiemDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTimHoaDon)
                                        .addComponent(btnHuyTim))
                                    .addComponent(dateChooserTimHoaDonTheoNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(dateChooserTimHoaDonTheoNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTietHoaDon)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tableDSHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        tableDSHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneDSHoaDon.setViewportView(tableDSHoaDon);

        labelDSHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelDSHoaDon.setText("DANH SÁCH HÓA ĐƠN");

        javax.swing.GroupLayout panelQLDonHangContentLayout = new javax.swing.GroupLayout(panelQLDonHangContent);
        panelQLDonHangContent.setLayout(panelQLDonHangContentLayout);
        panelQLDonHangContentLayout.setHorizontalGroup(
            panelQLDonHangContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLDonHangContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLDonHangContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTitleQLDonHangContent, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTimKiemDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDSHoaDon)
                    .addComponent(scrollPaneDSHoaDon))
                .addContainerGap(551, Short.MAX_VALUE))
        );
        panelQLDonHangContentLayout.setVerticalGroup(
            panelQLDonHangContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLDonHangContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLDonHangContent)
                .addGap(18, 18, 18)
                .addComponent(panelTimKiemDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDSHoaDon)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneDSHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1671, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLDonHangContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLDonHangContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxTimKiemHoaDonTheoNguoiTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimKiemHoaDonTheoNguoiTaoActionPerformed
        // TODO add your handling code here:
        timHoaDonTheoNguoiPhuTrach(this.defaultTableModelDSHoaDon, this.dsHoaDon);
    }//GEN-LAST:event_comboBoxTimKiemHoaDonTheoNguoiTaoActionPerformed

    private void comboBoxTimHoaDonTheoPhanLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimHoaDonTheoPhanLoaiActionPerformed
        // TODO add your handling code here:
        timHoaDonTheoLoaiHoaDon(this.defaultTableModelDSHoaDon, this.dsHoaDon);
    }//GEN-LAST:event_comboBoxTimHoaDonTheoPhanLoaiActionPerformed

    private void btnTimHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimHoaDonMouseClicked
        // TODO add your handling code here:

        if (this.dateChooserTimHoaDonTheoNgayBatDau.getDate() != null && this.dateChooserTimHoaDonTheoNgayKetThuc.getDate() != null) {
            int result = this.dateChooserTimHoaDonTheoNgayBatDau.getDate().compareTo(this.dateChooserTimHoaDonTheoNgayKetThuc.getDate());
            if (result < 0) {
                timHoaDonTuNgayBatDauDenNgayKetThuc(this.defaultTableModelDSHoaDon, this.dsHoaDon);
            } else if (result > 0) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải <= ngày kết thúc", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // nếu ngày bắt đầu trùng ngày kết thúc thì ta sẽ tìm hóa đơn trong ngày hom đó
                timHoaDonTheoNgay(this.defaultTableModelDSHoaDon, this.dsHoaDon);
            }
        } else if (this.dateChooserTimHoaDonTheoNgayBatDau.getDate() != null && this.dateChooserTimHoaDonTheoNgayKetThuc.getDate() == null) {
            timHoaDonTheoNgayBatDau(this.defaultTableModelDSHoaDon, this.dsHoaDon);
        } else if (this.dateChooserTimHoaDonTheoNgayBatDau.getDate() == null && this.dateChooserTimHoaDonTheoNgayKetThuc.getDate() != null) {
            timHoaDonTheoNgayKetThuc(this.defaultTableModelDSHoaDon, this.dsHoaDon);
        } else {
            JOptionPane.showMessageDialog(null, "Nhập ngày cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimHoaDonMouseClicked

    private void btnHuyTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimMouseClicked
        // TODO add your handling code here:
        getAllBill(this.defaultTableModelDSHoaDon, this.dsHoaDon);
        this.dateChooserTimHoaDonTheoNgayBatDau.setDate(null);
        this.dateChooserTimHoaDonTheoNgayKetThuc.setDate(null);
    }//GEN-LAST:event_btnHuyTimMouseClicked

    private void btnXemChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonMouseClicked
        // TODO add your handling code here:
        // hiển thi frame xem chi tiết hóa đơn
        int row = this.tableDSHoaDon.getSelectedRow();
        if (row >= 0) {
            this.frameXemChiTietHoaDon.setSize(900, 750);
            this.frameXemChiTietHoaDon.setLocationRelativeTo(null);
            this.frameXemChiTietHoaDon.setVisible(true);
            this.frameXemChiTietHoaDon.getContentPane().setBackground(Color.WHITE);
            hienThiHoaDonRaFrame(this.tableDSHoaDon, this.defaultTableModelDSHoaDon);
        } else {
            JOptionPane.showMessageDialog(null, "Chọn một hóa đơn để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXemChiTietHoaDonMouseClicked

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
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDonHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyTim;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnXemChiTietHoaDon;
    private javax.swing.JComboBox<String> comboBoxTimHoaDonTheoPhanLoai;
    private javax.swing.JComboBox<String> comboBoxTimKiemHoaDonTheoNguoiTao;
    private com.toedter.calendar.JDateChooser dateChooserTimHoaDonTheoNgayBatDau;
    private com.toedter.calendar.JDateChooser dateChooserTimHoaDonTheoNgayKetThuc;
    private javax.swing.JFrame frameXemChiTietHoaDon;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelDSHoaDon;
    private javax.swing.JLabel labelGhiChuHoaDon;
    private javax.swing.JLabel labelNguyenNhanHuyDonHang;
    private javax.swing.JLabel labelNoiDungNguyenNhanHuyDonHang;
    private javax.swing.JLabel labelPhiVanChuyen;
    private javax.swing.JLabel labelTenQuan;
    private javax.swing.JLabel labelTimHoaDonTheoNgayEnd;
    private javax.swing.JLabel labelTimKiemHoaDonTheoPhanLoai;
    private javax.swing.JLabel labelTimKiemTheoNguoiTaoHoaDon;
    private javax.swing.JLabel labelTimTheoNgayStart;
    private javax.swing.JLabel labelTitleQLDonHangContent;
    private javax.swing.JLabel labelTitleXemChiTietHoaDon;
    private javax.swing.JLabel labelXemDiaChiNhaHangHoaDon;
    private javax.swing.JLabel labelXemKhachHangHoaDon;
    private javax.swing.JLabel labelXemKhuyenMaiHoaDon;
    private javax.swing.JLabel labelXemMaHoaDom;
    private javax.swing.JLabel labelXemNguoiPhuTrachHoaDon;
    private javax.swing.JLabel labelXemNoiDungDiaChiNhanHangHoaDon;
    private javax.swing.JLabel labelXemNoiDungGhiChuHoaDon;
    private javax.swing.JLabel labelXemNoiDungKhachHangHoaDon;
    private javax.swing.JLabel labelXemNoiDungKhuyenMaiHoaDon;
    private javax.swing.JLabel labelXemNoiDungMaHoaDon;
    private javax.swing.JLabel labelXemNoiDungNguoiPhuTrachHoaDon;
    private javax.swing.JLabel labelXemNoiDungPhiVanChuyen;
    private javax.swing.JLabel labelXemNoiDungPhuongThucThanhToanHoaDon;
    private javax.swing.JLabel labelXemNoiDungSoLuongSanPhamHoaDon;
    private javax.swing.JLabel labelXemNoiDungTongTienHoaDon;
    private javax.swing.JLabel labelXemNoiDungTrangThaiHoaDon;
    private javax.swing.JLabel labelXemPhuongThucThanhToanHoaDon;
    private javax.swing.JLabel labelXemSoLuongSanPhamHoaDon;
    private javax.swing.JLabel labelXemTongTienHoaDon;
    private javax.swing.JLabel labelXemTrangThaiDonHang;
    private javax.swing.JPanel panelHienThiDSSanPhamTrongHoaDOn;
    private javax.swing.JPanel panelQLDonHangContent;
    private javax.swing.JPanel panelTimKiemDonHang;
    private javax.swing.JPanel panelXemChiTietHoaDon;
    private javax.swing.JScrollPane scrollPaneDSHoaDon;
    private javax.swing.JScrollPane scrollPaneDSSanPhamTrongHoaDon;
    private javax.swing.JTable tableDSHoaDon;
    // End of variables declaration//GEN-END:variables
}
