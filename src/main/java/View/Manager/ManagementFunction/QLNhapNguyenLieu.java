/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.AccountDAO;
import Dao.BillImportIngredientDAO;
import Dao.BillImportIngredientItemDAO;
import Dao.IngredientDAO;
import Dao.SupplierDAO;
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
import Model.Manager.Account;
import Model.Manager.BillImportIngredient;
import Model.Manager.BillImportIngredientItem;
import Model.Manager.Ingredient;
import Model.Manager.Supplier;
import Utils.Manager.CurrentTimeFormatter;
import Utils.Manager.FormatMoney;

/**
 *
 * @author Admin
 */
public class QLNhapNguyenLieu extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLNhapNguyenLieu
     */
    public DefaultTableModel defaultTableModelDSHoaDonNhapNguyenLieu;
    public ArrayList<BillImportIngredient> dsHoaDonNhapNguyenLieu;
    public BillImportIngredientDAO billImportIngredientDAO;

    public BillImportIngredientItemDAO billImportIngredientItemDAO;

    public DefaultTableModel defaultTableModelDSNguyenLieuTrongHoaDonNhapNL;
    public ArrayList<BillImportIngredientItem> dsBillImportIngredientItems;

    public DefaultTableModel defaultTableModelTempDSNguyenLieuTrongHoaDonNhapNL;
    public ArrayList<BillImportIngredientItem> dsBillTempIngredient;

    public DefaultTableModel defaultTableModelXemDSNguyenLieuTrongHoaDon;

    public DefaultTableModel defaultTableModelDSNhaCungCap;
    public ArrayList<Supplier> dsNhaCungCap;
    public SupplierDAO supplierDAO;

    public DefaultTableModel defaultTableModelDSNguyenLieu;
    public ArrayList<Ingredient> dsNguyenLieu;
    public IngredientDAO ingredientDAO;

//    public DefaultTableModel defaultTableModelDSTaiKhoan;
    public ArrayList<Account> dsTaiKhoan;
    public AccountDAO accountDAO;

    public int tongTienHoaDon;

    public QLNhapNguyenLieu() {

        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

        initComponents();

        this.tongTienHoaDon = 0;

        this.dsNhaCungCap = new ArrayList<>();
        this.supplierDAO = new SupplierDAO();
        this.defaultTableModelDSNhaCungCap = new DefaultTableModel();

        this.dsNguyenLieu = new ArrayList<>();
        this.defaultTableModelDSNguyenLieu = new DefaultTableModel();
        this.ingredientDAO = new IngredientDAO();

        this.dsBillImportIngredientItems = new ArrayList<>();
        this.billImportIngredientDAO = new BillImportIngredientDAO();
        this.dsBillTempIngredient = new ArrayList<>();

        this.billImportIngredientItemDAO = new BillImportIngredientItemDAO();

        this.dsTaiKhoan = new ArrayList<>();
        this.accountDAO = new AccountDAO();

        this.dsHoaDonNhapNguyenLieu = new ArrayList<>();

        // ds hóa đon nhập nguyên liệu
        this.defaultTableModelDSHoaDonNhapNguyenLieu = new DefaultTableModel();
        this.tableDSHoaDonNhapNguyenLieu.setModel(this.defaultTableModelDSHoaDonNhapNguyenLieu);
        String[] columns = {"Mã hóa đơn", "Nhà cung cấp", "Phụ trách", "Ngày tạo", "Tổng tiền"};
        this.defaultTableModelDSHoaDonNhapNguyenLieu.setColumnIdentifiers(columns);
        JTableHeader headerDSHoaDonNhapHang = this.tableDSHoaDonNhapNguyenLieu.getTableHeader();
        headerDSHoaDonNhapHang.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSHoaDonNhapHang.setBackground(new Color(0x43CD80));
        headerDSHoaDonNhapHang.setForeground(Color.WHITE);
        headerDSHoaDonNhapHang.setPreferredSize(new Dimension(headerDSHoaDonNhapHang.getWidth(), 30));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tableDSHoaDonNhapNguyenLieu.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        this.tableDSHoaDonNhapNguyenLieu.setRowHeight(30);
        this.tableDSHoaDonNhapNguyenLieu.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.tableDSHoaDonNhapNguyenLieu.getColumnModel().getColumn(1).setPreferredWidth(170);
        this.tableDSHoaDonNhapNguyenLieu.getColumnModel().getColumn(2).setPreferredWidth(170);
        this.tableDSHoaDonNhapNguyenLieu.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.tableDSHoaDonNhapNguyenLieu.getColumnModel().getColumn(4).setPreferredWidth(120);

        // ds nguyên liệu trong frame thêm nguyên liệu
        this.tableDSNguyenLieuNhapNL.setModel(this.defaultTableModelDSNguyenLieu);
        String[] columnsDSNguyenLieu = {"Mã NL", "Tên nguyên liệu", "Số lượng"};
        this.defaultTableModelDSNguyenLieu.setColumnIdentifiers(columnsDSNguyenLieu);
        JTableHeader headerDSNguyenLieu = this.tableDSNguyenLieuNhapNL.getTableHeader();
        headerDSNguyenLieu.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSNguyenLieu.setBackground(new Color(0x43CD80));
        headerDSNguyenLieu.setForeground(Color.WHITE);

        // ds nguyên liệu tạm thời trong hóa đơn nhập hàng
        this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL = new DefaultTableModel();
        this.tableDSNguyenLieuTrongHoaDonNhapNL.setModel(this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL);
        String[] columnsDSNguyenLieuTrongHoaDon = {"Mã NL", "Tên NL", "Giá nhập", "Số lượng"};
        this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL.setColumnIdentifiers(columnsDSNguyenLieuTrongHoaDon);
        JTableHeader headerDSNguyenLieuTrongHoaDon = this.tableDSNguyenLieuTrongHoaDonNhapNL.getTableHeader();
        headerDSNguyenLieuTrongHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
        headerDSNguyenLieuTrongHoaDon.setBackground(new Color(0x43CD80));
        headerDSNguyenLieuTrongHoaDon.setForeground(Color.WHITE);

        // danh sách các nguyên liệu khi ta click vào nút xem hóa đơn
        this.defaultTableModelXemDSNguyenLieuTrongHoaDon = new DefaultTableModel();
        this.tableXemDSNguyenLieuHoaDonNhapNL.setModel(this.defaultTableModelXemDSNguyenLieuTrongHoaDon);
        String[] columnsXemDSNguyenLieuTrongHoaDon = {"Mã NL", "Tên NL", "Giá nhập", "Số lượng"};
        this.defaultTableModelXemDSNguyenLieuTrongHoaDon.setColumnIdentifiers(columnsXemDSNguyenLieuTrongHoaDon);
        JTableHeader headerXemDSNguyenLieuTrongHoaDon = this.tableXemDSNguyenLieuHoaDonNhapNL.getTableHeader();
        headerXemDSNguyenLieuTrongHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
        headerXemDSNguyenLieuTrongHoaDon.setBackground(new Color(0x43CD80));
        headerXemDSNguyenLieuTrongHoaDon.setForeground(Color.WHITE);

        // lấy ds nhà cung cấp
        this.dsNhaCungCap = this.supplierDAO.getAllSupplier();

        // thêm ds nhà cung cấp vào comboBox chọn nhà cung cấp
        for (Supplier i : this.dsNhaCungCap) {
            this.comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.addItem(i.getNameSupplier());
            this.comboBoxThemNhaCungCapNhapNL.addItem(i.getNameSupplier());
        }

        // lấy ds account và thêm vào comboBox
        this.dsTaiKhoan = this.accountDAO.getAllAccount();
        for (Account i : this.dsTaiKhoan) {
            this.comboBoxThemNguoiPhuTrachNhapNL.addItem(i.getFullName());
            this.comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.addItem(i.getFullName());
        }

        getAllIngredient(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
        getAllBilImportIndredient(this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);

        this.scrollPaneDSHoaDonNhapNguyenLieu.getViewport().setBackground(Color.WHITE);
        this.scrollPaneDSNguyenLieuNhapNL.getViewport().setBackground(Color.WHITE);
        this.scrollPaneDSNguyenLieuTrongHoaDonNhapNL.getViewport().setBackground(Color.WHITE);

        this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.setDateFormatString("dd/MM/yyyy");
        this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.setFont(new Font("Arial", Font.BOLD, 15));
        this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.setDateFormatString("dd/MM/yyyy");
        this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.setFont(new Font("Arial", Font.BOLD, 15));
    }

    // lấy ra danh sách nhà cung cấp
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

    // ==================== INGREDIENT ====================
    // xuất ds nguyên liệu
    public void xuatDSNguyenLieuRaTableTuArrayList(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        defaultTableModel.setRowCount(0);
        for (Ingredient i : ds) {
            Object[] data = {i.getIdIngredient(), i.getNameIngredient(), i.getQuantityIngredient()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllIngredient(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        ds = this.ingredientDAO.getAllIngredient();
        xuatDSNguyenLieuRaTableTuArrayList(defaultTableModel, ds);
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

    public void hienThiNguyenLieuKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Ingredient a = layMotNguyenLieuKhiClickVaoBangNguyenLieu(table, defaultTableModel);
            if (a != null) {
                this.labelNoiDungThemMaNguyenLieuNhapNL.setText(a.getIdIngredient() + "");
                this.labelNoiDungThemTenNguyenLieuNhapNL.setText(a.getNameIngredient());
                this.labelNoiDungThemDonViNguyenLieuNhapNL.setText(a.getUnitIngredient());
            }
        }
    }

    public void timKiemNguyenLieuTheoTen(DefaultTableModel defaultTableModel, ArrayList<Ingredient> ds) {
        String tenNguyenLieuCanTim = this.textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu.getText() + "";
        ArrayList<Ingredient> kq = this.ingredientDAO.findIngredientByName(tenNguyenLieuCanTim);
        if (!kq.isEmpty()) {
            xuatDSNguyenLieuRaTableTuArrayList(defaultTableModel, kq);
        } else {
            JOptionPane.showMessageDialog(null, "Không có nguyên liệu cần tìm");
        }
    }

    // ==================== BillImportIngredientItem Temp ====================
    public void xuatNguyenLieuTamThoiVaoTableHoaDonNhapNguyenLieu(DefaultTableModel defaultTableModel, ArrayList<BillImportIngredientItem> ds) {
        defaultTableModel.setRowCount(0);
        for (BillImportIngredientItem i : ds) {
            Ingredient a = this.ingredientDAO.getIngredientById(i.getIdIngredient());
            Object[] data = {i.getIdIngredient(), a.getNameIngredient(), i.getPriceImport(), i.getQuantity()};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllBillImportIngredientItem(DefaultTableModel defaultTableModel, ArrayList<BillImportIngredientItem> ds) {

    }

    public void resetPanelThemNL() {
        this.textFieldThemSoLuongNguyenLieuNhapNL.setText("");
        this.textFieldThemGiaNguyenLieuNhapNL.setText("");
        this.labelNoiDungThemMaNguyenLieuNhapNL.setText("mã NL");
        this.labelNoiDungThemTenNguyenLieuNhapNL.setText("tên nguyên liệu");
        this.labelNoiDungThemDonViNguyenLieuNhapNL.setText("đơn vị");
        this.labelLoiThemSoLuongNguyenLieuNhapNL.setText("");
        this.labelLoiThemGiaNguyenLieuNhapNL.setText("");
    }

    public void themNguyenLieuVaoBangTemp(DefaultTableModel defaultTableModel, ArrayList<BillImportIngredientItem> ds) {
        // sau khi người dùng nhập thông tin xong, thì nguyên liệu cần nhập sẽ nằm trong bảng temp chờ thêm vào csdl
        String thongBaoLoi = "";
        String regexLuong = "^[1-9]\\\\d*|0$";
//        if (!this.textFieldThemLuongTaiKhoan.getText().matches("\\d+")) {
//                thongBaoLoi += "Lương phải là số nguyên dương";
//                this.labelLoiThemLuongTaiKhoan.setText("Lương phải là số nguyên dương");
//            }
        if (this.textFieldThemSoLuongNguyenLieuNhapNL.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập số lượng nguyên liệu";
            this.labelLoiThemSoLuongNguyenLieuNhapNL.setText("Vui lòng nhập số lượng nguyên liệu");
        } else {
            if (!this.textFieldThemSoLuongNguyenLieuNhapNL.getText().matches("\\d+")) {
                thongBaoLoi += "Số lượng phải là số nguyên dương";
                this.labelLoiThemSoLuongNguyenLieuNhapNL.setText("Số lượng phải là số nguyên dương");
            }
        }
        if (this.textFieldThemGiaNguyenLieuNhapNL.getText().equals("")) {
            thongBaoLoi += "Vui lòng nhập giá nguyên liệu";
            this.labelLoiThemGiaNguyenLieuNhapNL.setText("Vui lòng nhập giá nguyên liệu");
        } else {
                 if (!this.textFieldThemGiaNguyenLieuNhapNL.getText().matches("\\d+")) {
                thongBaoLoi += "Giá phải là số nguyên dương";
                this.labelLoiThemGiaNguyenLieuNhapNL.setText("Giá phải là số nguyên dương");
            }
        }
        if (thongBaoLoi.equals("")) {
            int idIngredient = Integer.parseInt(this.labelNoiDungThemMaNguyenLieuNhapNL.getText());
            float quantity = Float.parseFloat(this.textFieldThemSoLuongNguyenLieuNhapNL.getText());
            int priceImport = Integer.parseInt(this.textFieldThemGiaNguyenLieuNhapNL.getText());
            this.tongTienHoaDon += quantity * priceImport;
            this.labelHienThiTongTienHoaDonNhapNL.setText(FormatMoney.formatMoney(this.tongTienHoaDon));
            BillImportIngredientItem a = new BillImportIngredientItem(idIngredient, quantity, priceImport);
            ds.add(a);
            xuatNguyenLieuTamThoiVaoTableHoaDonNhapNguyenLieu(defaultTableModel, ds);
            resetPanelThemNL();
        }
    }

    public void xoaNguyenLieuKhoiBangTemp(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredientItem> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idNguyenLieuCanXoa = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getIdIngredient() == idNguyenLieuCanXoa) {
                    this.tongTienHoaDon -= (ds.get(i).getQuantity() * ds.get(i).getPriceImport());
                    this.labelHienThiTongTienHoaDonNhapNL.setText(FormatMoney.formatMoney(this.tongTienHoaDon));
                    ds.remove(i);
                    i--;
                }
            }
            xuatNguyenLieuTamThoiVaoTableHoaDonNhapNguyenLieu(defaultTableModel, ds);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyên liệu để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void xuatDSHoaDonNhapNguyenLieuRaTable(DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        defaultTableModel.setRowCount(0);
        for (BillImportIngredient i : ds) {
            Supplier a = this.supplierDAO.getSupplierById(i.getIdSupplier());
            Account b = this.accountDAO.getAccountByID(i.getIdAccount());
            Object[] data = {i.getIdBillImportIngredient(), a.getNameSupplier(), b.getFullName(), i.getDateCreate(), FormatMoney.formatMoney(i.getTotalPrice())};
            defaultTableModel.addRow(data);
        }
        defaultTableModel.fireTableDataChanged();
    }

    public void getAllBilImportIndredient(DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        ds = this.billImportIngredientDAO.getAllBillImportIngredient();
//        defaultTableModel.setRowCount(0);
//        for (BillImportIngredient i : ds) {
//            Supplier a = this.supplierDAO.getSupplierById(i.getIdSupplier());
//            Account b = this.accountDAO.getAccountByID(i.getIdAccount());
//            Object[] data = {i.getIdBillImportIngredient(), a.getNameSupplier(), b.getFullName(), i.getDateCreate(), i.getTotalPrice()};
//            defaultTableModel.addRow(data);
//        }
//        defaultTableModel.fireTableDataChanged();
        xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, ds);
    }

    public void hienThiNguyenLieuRaPanelKhiClickVaoBangNLTemp(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idIngredient = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            Ingredient a = this.ingredientDAO.getIngredientById(idIngredient);
            this.labelNoiDungThemMaNguyenLieuNhapNL.setText(idIngredient + "");
            this.labelNoiDungThemTenNguyenLieuNhapNL.setText(a.getNameIngredient());
            this.labelNoiDungThemDonViNguyenLieuNhapNL.setText(a.getUnitIngredient());
            this.textFieldThemGiaNguyenLieuNhapNL.setText(defaultTableModel.getValueAt(row, 2) + "");
            this.textFieldThemSoLuongNguyenLieuNhapNL.setText(defaultTableModel.getValueAt(row, 3) + "");
        }
    }

    public void suaNguyenLieuTrongBangTemp(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredientItem> ds) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int idNguyenLieuCanXoa = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getIdIngredient() == idNguyenLieuCanXoa) {
                    this.tongTienHoaDon -= (ds.get(i).getQuantity() * ds.get(i).getPriceImport());
                    this.labelHienThiTongTienHoaDonNhapNL.setText(FormatMoney.formatMoney(this.tongTienHoaDon));
                    String thongBaoLoi = "";
                    if (this.textFieldThemSoLuongNguyenLieuNhapNL.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập số lượng nguyên liệu";
                        this.labelLoiThemSoLuongNguyenLieuNhapNL.setText("Vui lòng nhập số lượng nguyên liệu");
                    }
                    if (this.textFieldThemGiaNguyenLieuNhapNL.getText().equals("")) {
                        thongBaoLoi += "Vui lòng nhập giá nguyên liệu";
                        this.labelLoiThemGiaNguyenLieuNhapNL.setText("Vui lòng nhập giá nguyên liệu");
                    }
                    if (thongBaoLoi.equals("")) {
                        int idIngredient = Integer.parseInt(this.labelNoiDungThemMaNguyenLieuNhapNL.getText());
                        float quantity = Float.parseFloat(this.textFieldThemSoLuongNguyenLieuNhapNL.getText());
                        int priceImport = Integer.parseInt(this.textFieldThemGiaNguyenLieuNhapNL.getText());
                        BillImportIngredientItem a = new BillImportIngredientItem(idIngredient, quantity, priceImport);
                        this.tongTienHoaDon += (a.getQuantity() * a.getPriceImport());
                        this.labelHienThiTongTienHoaDonNhapNL.setText(FormatMoney.formatMoney(this.tongTienHoaDon));
                        ds.set(i, a);
                        break;
                    }
                }
            }
            xuatNguyenLieuTamThoiVaoTableHoaDonNhapNguyenLieu(defaultTableModel, ds);
            resetPanelThemNL();

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyên liệu để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void themHoaDonNhapNguyenLieu(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        int idBill = Integer.parseInt(this.labelNoiDungThemMaHoaDonNhapNL.getText());
        // ta sẽ thêm BillImportIngredient trc, sau đó mới thêm BilImportIngredientItem
        String theFullName = this.comboBoxThemNguoiPhuTrachNhapNL.getSelectedItem() + "";
        Account a = this.accountDAO.getAccountByFullName(theFullName);
        String nameSupplier = this.comboBoxThemNhaCungCapNhapNL.getSelectedItem() + "";
        Supplier b = this.supplierDAO.getSupplierByName(nameSupplier);
        String dateOrder = this.labelNoiDungThemNgayTaoHoaDonNhapNL.getText();
        int toTalPrice = this.tongTienHoaDon;
        BillImportIngredient c = new BillImportIngredient(a.getIdAccount(), dateOrder, b.getIdSupplier(), toTalPrice);
        this.billImportIngredientDAO.insertBillImportIngredient(c);

        // ta lấy ds các nguyên liệu cần nhập từ bảng temp
        for (BillImportIngredientItem i : this.dsBillImportIngredientItems) {
//            System.out.println(i);
            i.setIdBillImportIngredient(idBill);
            this.billImportIngredientItemDAO.insertBillImportIngredientItem(i);
            this.ingredientDAO.updateQuantityIngredient(i.getIdIngredient(), i.getQuantity());
        }

        // sau khi thêm xong ta sẽ reset lại toàn bộ
        this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL.setRowCount(0);
        resetPanelThemNL();
    }

    public BillImportIngredient layBillImportIngredientKhiClickVaoBang(JTable table, DefaultTableModel defaultTableModel) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int theId = Integer.parseInt(defaultTableModel.getValueAt(row, 0) + "");
            BillImportIngredient a = this.billImportIngredientDAO.getBillImportIngredientById(theId);
            return a;
        }
        return null;
    }

    public void xemThongTinHoaDon(JTable table, DefaultTableModel defaultTableModel) {
        this.defaultTableModelXemDSNguyenLieuTrongHoaDon.setRowCount(0);
        int row = table.getSelectedRow();
        if (row >= 0) {

            BillImportIngredient a = layBillImportIngredientKhiClickVaoBang(table, defaultTableModel);
            if (a != null) {
                this.labelXemNoiDungMaHoaDonNhapNL.setText(a.getIdBillImportIngredient() + "");
                Account account = this.accountDAO.getAccountByID(a.getIdAccount());
                this.labelXemNoiDungNguoiPhuTrachHoaDonNhapNL.setText(account.getFullName());
                Supplier supplier = this.supplierDAO.getSupplierById(a.getIdSupplier());
                this.labelXemNoiDungNhaCungCapHoaDonNhapNL.setText(supplier.getNameSupplier());
                this.labelXemNoiDungNgayTaoHoaDonNhapNL.setText(a.getDateCreate());
                this.labelXemNoiDungTongTienHoaDonNhapNL.setText(FormatMoney.formatMoney(a.getTotalPrice()));
                ArrayList<BillImportIngredientItem> billItems = this.billImportIngredientItemDAO.getAllBillImportIngredientItem(a.getIdBillImportIngredient());
                for (BillImportIngredientItem i : billItems) {
                    Ingredient b = this.ingredientDAO.getIngredientById(i.getIdIngredient());
                    Object[] data = {i.getIdIngredient(), b.getNameIngredient(), i.getPriceImport(), i.getQuantity()};
                    this.defaultTableModelXemDSNguyenLieuTrongHoaDon.addRow(data);
                }
                defaultTableModel.fireTableDataChanged();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void timKiemHoaDonNhapNguyenLieuTheoNhaCungCap(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        if (this.comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.getSelectedItem().equals("Tất cả")) {
            getAllBilImportIndredient(defaultTableModel, ds);
        } else {
            String tenNhaCungCap = this.comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.getSelectedItem() + "";
            Supplier a = this.supplierDAO.getSupplierByName(tenNhaCungCap);
            int idNhaCungCap = a.getIdSupplier();
            ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientsBySupplier(idNhaCungCap);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
            } else {
                xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
            }
        }
    }

    public void timKiemHoaDonNhapNguyenLieuTheoNguoiPhuTrach(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        if (this.comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.getSelectedItem().equals("Tất cả")) {
            getAllBilImportIndredient(defaultTableModel, ds);
        } else {
            String tenNguoiPhuTrach = this.comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.getSelectedItem() + "";
            Account a = this.accountDAO.getAccountByFullName(tenNguoiPhuTrach);
            int idNguoiPhuTrach = a.getIdAccount();
            ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientsByAccount(idNguoiPhuTrach);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
            } else {
                xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
            }
        }
    }

    public void timKiemHoaDonNhapNguyenLieuTheoNgayTaoHoaDon(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date a = this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate();
        String dateStartString = sdf.format(a);
        Date b = this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate();
        String dateEndString = sdf.format(b);
        if (!dateStartString.equals("") && !dateEndString.equals("")) {
            ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientsByDateCreate(dateStartString, dateEndString);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
            } else {
                xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
            }
        }
    }

    public void timKiemHoaDonNhapNLTheoNgayBatDau(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date a = this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate();
        String dateStartString = sdf.format(a);
        ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientsByStartDateCreate(dateStartString);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
        }

    }

    public void timKiemHoaDonNhapNLTheoNgayketThuc(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date b = this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate();
        String dateEndString = sdf.format(b);
        ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientsByEndDateCreate(dateEndString);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
        }
    }

    public void timKiemHoaDonNhapHangTheoNgayDuyNhat(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date b = this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate();
        String date = sdf.format(b);
        ArrayList<BillImportIngredient> kq = this.billImportIngredientDAO.findBillImportIngredientByDate(date);
        if (kq.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn cần tìm");
        } else {
            xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, kq);
        }
    }

    // SẮP XẾP HÓA ĐƠN TĂNG DẦN THEO GIÁ
    public void sapXepHoaDonNhapNLTangTheoGia(JTable table, DefaultTableModel defaultTableModel, ArrayList<BillImportIngredient> ds) {
        ds = this.billImportIngredientDAO.getAllBillImportIngredient();
        ArrayList<BillImportIngredient> newDs = new ArrayList<>(ds);
        newDs.sort((h1, h2) -> Integer.compare(h1.getTotalPrice(), h2.getTotalPrice()));
        xuatDSHoaDonNhapNguyenLieuRaTable(defaultTableModel, newDs);

//        String gtTimKiem = this.comboBoxSapXepHoaDonTheoGia.getSelectedItem() + "";
//        if (gtTimKiem.equals("Mặc định")) {
//            getAllBilImportIndredient(this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
//        } else if (gtTimKiem.equals("Tăng dần")) {
//            sapXepHoaDonNhapNLTangTheoGia(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameXemChiTietHoaDon = new javax.swing.JFrame();
        panelXemChiTietHoaDonNhapNL = new javax.swing.JPanel();
        labelTitleXemHoaDonNhapNL = new javax.swing.JLabel();
        labelXemMaHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNoiDungMaHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNhaCungCapHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNoiDungNhaCungCapHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNguoiPhuTrachHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNoiDungNguoiPhuTrachHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNgayTaoHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNoiDungNgayTaoHoaDonNhapNL = new javax.swing.JLabel();
        scrollPaneXemDSNguyenLieuHoaDonNhapNL = new javax.swing.JScrollPane();
        tableXemDSNguyenLieuHoaDonNhapNL = new javax.swing.JTable();
        labelXemTongTienHoaDonNhapNL = new javax.swing.JLabel();
        labelXemNoiDungTongTienHoaDonNhapNL = new javax.swing.JLabel();
        labelTenQuan = new javax.swing.JLabel();
        panelQLNhapHang = new javax.swing.JPanel();
        labelTitleQLNhapHang = new javax.swing.JLabel();
        panelChucnangNhapNguyenLieu = new javax.swing.JPanel();
        btnTongQuatNhapNguyenLieu = new javax.swing.JButton();
        btnThemHoaDonNhapNguyenLieu = new javax.swing.JButton();
        btnXemChiTietHoaDonNhapNguyenLieu = new javax.swing.JButton();
        panelNoiDungQLNhapNguyenLieu = new javax.swing.JPanel();
        panelTrangChuNhapNguyenLieu = new javax.swing.JPanel();
        panelTimKiemHoaDonNhapNguyenLieu = new javax.swing.JPanel();
        labelTimNhaCungCapTrangChuNhapNguyenLieu = new javax.swing.JLabel();
        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu = new javax.swing.JComboBox<>();
        labelTimNguoiPhuTrachTrangChuNhapNguyenLieu = new javax.swing.JLabel();
        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu = new javax.swing.JComboBox<>();
        labelStartTimNgayLapHoaDonNhapNguyenLieu = new javax.swing.JLabel();
        labelEndTimNgayLapHoaDonNhapNguyenLieu = new javax.swing.JLabel();
        btnTimHoaDonNhapNL = new javax.swing.JButton();
        btnHuyTimHoaDonNhapNL = new javax.swing.JButton();
        dateChosserTimTheoNgayBatDauHoaDonNhapNL = new com.toedter.calendar.JDateChooser();
        dateChooserTimTheoNgayKetThucHoaDonNhapNL = new com.toedter.calendar.JDateChooser();
        scrollPaneDSHoaDonNhapNguyenLieu = new javax.swing.JScrollPane();
        tableDSHoaDonNhapNguyenLieu = new javax.swing.JTable();
        panelThemHoaDonNhapNguyenLieu = new javax.swing.JPanel();
        textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu = new javax.swing.JTextField();
        btnTimNguyenLieuNhapNguyenLieu = new javax.swing.JButton();
        scrollPaneDSNguyenLieuNhapNL = new javax.swing.JScrollPane();
        tableDSNguyenLieuNhapNL = new javax.swing.JTable();
        panelThemNguyenLieuHoaDonNhapHang = new javax.swing.JPanel();
        labelThemMaNguyenLieuNhapNL = new javax.swing.JLabel();
        labelNoiDungThemMaNguyenLieuNhapNL = new javax.swing.JLabel();
        labelThemTenNguyenLieuNhapNL = new javax.swing.JLabel();
        labelNoiDungThemTenNguyenLieuNhapNL = new javax.swing.JLabel();
        labelThemSoLuongNguyenLieuNhapNL = new javax.swing.JLabel();
        textFieldThemSoLuongNguyenLieuNhapNL = new javax.swing.JTextField();
        labelLoiThemSoLuongNguyenLieuNhapNL = new javax.swing.JLabel();
        labelThemDonViNguyenLieuNhapNL = new javax.swing.JLabel();
        labelNoiDungThemDonViNguyenLieuNhapNL = new javax.swing.JLabel();
        labelThemGiaNguyenLieuNhapNL = new javax.swing.JLabel();
        textFieldThemGiaNguyenLieuNhapNL = new javax.swing.JTextField();
        labelLoiThemGiaNguyenLieuNhapNL = new javax.swing.JLabel();
        btnHoanThanhThemNguyenLieuNhapNL = new javax.swing.JButton();
        btnXoaNguyenLieuNhapNL = new javax.swing.JButton();
        btnHoanThanhSuaNguyenLieuNhapNL = new javax.swing.JButton();
        scrollPaneDSNguyenLieuTrongHoaDonNhapNL = new javax.swing.JScrollPane();
        tableDSNguyenLieuTrongHoaDonNhapNL = new javax.swing.JTable();
        panelTinhTongTienHoaDonNhapNL = new javax.swing.JPanel();
        labelThemMaHoaDonNhapNL = new javax.swing.JLabel();
        labelNoiDungThemMaHoaDonNhapNL = new javax.swing.JLabel();
        labelThemNguoiPhuTrachHoaDonNhapNL = new javax.swing.JLabel();
        comboBoxThemNguoiPhuTrachNhapNL = new javax.swing.JComboBox<>();
        labelThemNhaCungCapNhapNL = new javax.swing.JLabel();
        comboBoxThemNhaCungCapNhapNL = new javax.swing.JComboBox<>();
        labelTongTienDonhangNhapNL = new javax.swing.JLabel();
        labelHienThiTongTienHoaDonNhapNL = new javax.swing.JLabel();
        btnXacNhanNhapNL = new javax.swing.JButton();
        labelThemNgayTaoHoaDonNhapNL = new javax.swing.JLabel();
        labelNoiDungThemNgayTaoHoaDonNhapNL = new javax.swing.JLabel();
        btnHuyTimNguyenLieuNhapNL = new javax.swing.JButton();
        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu = new javax.swing.JButton();
        labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL = new javax.swing.JLabel();

        frameXemChiTietHoaDon.setTitle("Chi tiết hóa đơn");

        panelXemChiTietHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));

        labelTitleXemHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleXemHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleXemHoaDonNhapNL.setForeground(new java.awt.Color(204, 0, 204));
        labelTitleXemHoaDonNhapNL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleXemHoaDonNhapNL.setText("THÔNG TIN CHI TIẾT HÓA ĐƠN NHẬP NGUYÊN LIỆU");
        labelTitleXemHoaDonNhapNL.setOpaque(true);

        labelXemMaHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemMaHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemMaHoaDonNhapNL.setText("Mã hóa đơn");
        labelXemMaHoaDonNhapNL.setOpaque(true);

        labelXemNoiDungMaHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungMaHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungMaHoaDonNhapNL.setText("jLabel1");
        labelXemNoiDungMaHoaDonNhapNL.setOpaque(true);

        labelXemNhaCungCapHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNhaCungCapHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNhaCungCapHoaDonNhapNL.setText("Nhà cung cấp");
        labelXemNhaCungCapHoaDonNhapNL.setOpaque(true);

        labelXemNoiDungNhaCungCapHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungNhaCungCapHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNhaCungCapHoaDonNhapNL.setText("jLabel1");

        labelXemNguoiPhuTrachHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNguoiPhuTrachHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNguoiPhuTrachHoaDonNhapNL.setText("Người phụ trách");

        labelXemNoiDungNguoiPhuTrachHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungNguoiPhuTrachHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNguoiPhuTrachHoaDonNhapNL.setText("jLabel1");

        labelXemNgayTaoHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNgayTaoHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNgayTaoHoaDonNhapNL.setText("Ngày tạo");
        labelXemNgayTaoHoaDonNhapNL.setOpaque(true);

        labelXemNoiDungNgayTaoHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungNgayTaoHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelXemNoiDungNgayTaoHoaDonNhapNL.setText("jLabel1");
        labelXemNoiDungNgayTaoHoaDonNhapNL.setOpaque(true);

        scrollPaneXemDSNguyenLieuHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));

        tableXemDSNguyenLieuHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        tableXemDSNguyenLieuHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableXemDSNguyenLieuHoaDonNhapNL.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneXemDSNguyenLieuHoaDonNhapNL.setViewportView(tableXemDSNguyenLieuHoaDonNhapNL);

        labelXemTongTienHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemTongTienHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelXemTongTienHoaDonNhapNL.setText("TỔNG TIỀN (VNĐ)");
        labelXemTongTienHoaDonNhapNL.setOpaque(true);

        labelXemNoiDungTongTienHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelXemNoiDungTongTienHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelXemNoiDungTongTienHoaDonNhapNL.setForeground(new java.awt.Color(255, 0, 0));
        labelXemNoiDungTongTienHoaDonNhapNL.setText("0");
        labelXemNoiDungTongTienHoaDonNhapNL.setOpaque(true);

        labelTenQuan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTenQuan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTenQuan.setText("TRÀ SỮA TASU");

        javax.swing.GroupLayout panelXemChiTietHoaDonNhapNLLayout = new javax.swing.GroupLayout(panelXemChiTietHoaDonNhapNL);
        panelXemChiTietHoaDonNhapNL.setLayout(panelXemChiTietHoaDonNhapNLLayout);
        panelXemChiTietHoaDonNhapNLLayout.setHorizontalGroup(
            panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitleXemHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                                .addComponent(labelXemNguoiPhuTrachHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelXemNoiDungNguoiPhuTrachHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelXemNhaCungCapHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(labelXemMaHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                                        .addComponent(labelXemNoiDungMaHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(labelXemNgayTaoHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelXemNoiDungNgayTaoHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(labelXemNoiDungNhaCungCapHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                                .addComponent(labelXemTongTienHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelXemNoiDungTongTienHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneXemDSNguyenLieuHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(labelTenQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelXemChiTietHoaDonNhapNLLayout.setVerticalGroup(
            panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXemChiTietHoaDonNhapNLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTenQuan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitleXemHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemMaHoaDonNhapNL)
                    .addComponent(labelXemNoiDungMaHoaDonNhapNL)
                    .addComponent(labelXemNgayTaoHoaDonNhapNL)
                    .addComponent(labelXemNoiDungNgayTaoHoaDonNhapNL))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemNhaCungCapHoaDonNhapNL)
                    .addComponent(labelXemNoiDungNhaCungCapHoaDonNhapNL))
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelXemNguoiPhuTrachHoaDonNhapNL)
                    .addComponent(labelXemNoiDungNguoiPhuTrachHoaDonNhapNL))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneXemDSNguyenLieuHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelXemChiTietHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelXemTongTienHoaDonNhapNL)
                    .addComponent(labelXemNoiDungTongTienHoaDonNhapNL))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameXemChiTietHoaDonLayout = new javax.swing.GroupLayout(frameXemChiTietHoaDon.getContentPane());
        frameXemChiTietHoaDon.getContentPane().setLayout(frameXemChiTietHoaDonLayout);
        frameXemChiTietHoaDonLayout.setHorizontalGroup(
            frameXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameXemChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameXemChiTietHoaDonLayout.setVerticalGroup(
            frameXemChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameXemChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXemChiTietHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelQLNhapHang.setBackground(new java.awt.Color(248, 248, 255));

        labelTitleQLNhapHang.setBackground(new java.awt.Color(255, 255, 255));
        labelTitleQLNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitleQLNhapHang.setText("QUẢN LÝ NHẬP NGUYÊN LIỆU");
        labelTitleQLNhapHang.setOpaque(true);

        panelChucnangNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        btnTongQuatNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnTongQuatNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTongQuatNhapNguyenLieu.setForeground(new java.awt.Color(215, 97, 21));
        btnTongQuatNhapNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_trangChu_30px.png"))); // NOI18N
        btnTongQuatNhapNguyenLieu.setText("Trang chủ");
        btnTongQuatNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTongQuatNhapNguyenLieuMouseClicked(evt);
            }
        });

        btnThemHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnThemHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemHoaDonNhapNguyenLieu.setForeground(new java.awt.Color(0, 153, 51));
        btnThemHoaDonNhapNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_add_25px.png"))); // NOI18N
        btnThemHoaDonNhapNguyenLieu.setText("Thêm");
        btnThemHoaDonNhapNguyenLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        btnThemHoaDonNhapNguyenLieu.setMargin(new java.awt.Insets(2, 16, 3, 16));
        btnThemHoaDonNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHoaDonNhapNguyenLieuMouseClicked(evt);
            }
        });

        btnXemChiTietHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXemChiTietHoaDonNhapNguyenLieu.setForeground(new java.awt.Color(153, 0, 153));
        btnXemChiTietHoaDonNhapNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon_xemChiTiet_20px.png"))); // NOI18N
        btnXemChiTietHoaDonNhapNguyenLieu.setText("Chi tiết");
        btnXemChiTietHoaDonNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXemChiTietHoaDonNhapNguyenLieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChucnangNhapNguyenLieuLayout = new javax.swing.GroupLayout(panelChucnangNhapNguyenLieu);
        panelChucnangNhapNguyenLieu.setLayout(panelChucnangNhapNguyenLieuLayout);
        panelChucnangNhapNguyenLieuLayout.setHorizontalGroup(
            panelChucnangNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucnangNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTongQuatNhapNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(btnThemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXemChiTietHoaDonNhapNguyenLieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChucnangNhapNguyenLieuLayout.setVerticalGroup(
            panelChucnangNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChucnangNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChucnangNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelChucnangNhapNguyenLieuLayout.createSequentialGroup()
                        .addGroup(panelChucnangNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTongQuatNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemChiTietHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelNoiDungQLNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        panelNoiDungQLNhapNguyenLieu.setLayout(new java.awt.CardLayout());

        panelTrangChuNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        panelTimKiemHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        labelTimNhaCungCapTrangChuNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelTimNhaCungCapTrangChuNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimNhaCungCapTrangChuNhapNguyenLieu.setText("Nhà cung cấp");
        labelTimNhaCungCapTrangChuNhapNguyenLieu.setOpaque(true);

        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuMouseClicked(evt);
            }
        });
        comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuActionPerformed(evt);
            }
        });

        labelTimNguoiPhuTrachTrangChuNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelTimNguoiPhuTrachTrangChuNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimNguoiPhuTrachTrangChuNhapNguyenLieu.setText("Người phụ trách");
        labelTimNguoiPhuTrachTrangChuNhapNguyenLieu.setOpaque(true);

        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuMouseClicked(evt);
            }
        });
        comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuActionPerformed(evt);
            }
        });

        labelStartTimNgayLapHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelStartTimNgayLapHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelStartTimNgayLapHoaDonNhapNguyenLieu.setText("Từ ngày");
        labelStartTimNgayLapHoaDonNhapNguyenLieu.setOpaque(true);

        labelEndTimNgayLapHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        labelEndTimNgayLapHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelEndTimNgayLapHoaDonNhapNguyenLieu.setText("Đến ngày");
        labelEndTimNgayLapHoaDonNhapNguyenLieu.setOpaque(true);

        btnTimHoaDonNhapNL.setBackground(new java.awt.Color(0, 51, 255));
        btnTimHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimHoaDonNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnTimHoaDonNhapNL.setText("Tìm");
        btnTimHoaDonNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        btnTimHoaDonNhapNL.setOpaque(true);
        btnTimHoaDonNhapNL.setSelected(true);
        btnTimHoaDonNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimHoaDonNhapNLMouseClicked(evt);
            }
        });

        btnHuyTimHoaDonNhapNL.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyTimHoaDonNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimHoaDonNhapNL.setText("Hủy tìm");
        btnHuyTimHoaDonNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnHuyTimHoaDonNhapNL.setOpaque(true);
        btnHuyTimHoaDonNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimHoaDonNhapNLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiemHoaDonNhapNguyenLieuLayout = new javax.swing.GroupLayout(panelTimKiemHoaDonNhapNguyenLieu);
        panelTimKiemHoaDonNhapNguyenLieu.setLayout(panelTimKiemHoaDonNhapNguyenLieuLayout);
        panelTimKiemHoaDonNhapNguyenLieuLayout.setHorizontalGroup(
            panelTimKiemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChosserTimTheoNgayBatDauHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooserTimTheoNgayKetThucHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                        .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu, 0, 283, Short.MAX_VALUE)
                            .addComponent(labelTimNhaCungCapTrangChuNhapNguyenLieu)
                            .addComponent(labelTimNguoiPhuTrachTrangChuNhapNguyenLieu)
                            .addComponent(comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelStartTimNgayLapHoaDonNhapNguyenLieu)
                            .addComponent(labelEndTimNgayLapHoaDonNhapNguyenLieu)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimKiemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                                .addComponent(btnTimHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuyTimHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTimKiemHoaDonNhapNguyenLieuLayout.setVerticalGroup(
            panelTimKiemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimNhaCungCapTrangChuNhapNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelTimNguoiPhuTrachTrangChuNhapNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelStartTimNgayLapHoaDonNhapNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(dateChosserTimTheoNgayBatDauHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelEndTimNgayLapHoaDonNhapNguyenLieu)
                .addGap(18, 18, 18)
                .addComponent(dateChooserTimTheoNgayKetThucHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTimKiemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTimHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(261, 261, 261))
        );

        scrollPaneDSHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));

        tableDSHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        tableDSHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSHoaDonNhapNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneDSHoaDonNhapNguyenLieu.setViewportView(tableDSHoaDonNhapNguyenLieu);

        javax.swing.GroupLayout panelTrangChuNhapNguyenLieuLayout = new javax.swing.GroupLayout(panelTrangChuNhapNguyenLieu);
        panelTrangChuNhapNguyenLieu.setLayout(panelTrangChuNhapNguyenLieuLayout);
        panelTrangChuNhapNguyenLieuLayout.setHorizontalGroup(
            panelTrangChuNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTimKiemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneDSHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        panelTrangChuNhapNguyenLieuLayout.setVerticalGroup(
            panelTrangChuNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrangChuNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTrangChuNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTimKiemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneDSHoaDonNhapNguyenLieu))
                .addContainerGap())
        );

        panelNoiDungQLNhapNguyenLieu.add(panelTrangChuNhapNguyenLieu, "card2");

        panelThemHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(248, 248, 255));

        textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        btnTimNguyenLieuNhapNguyenLieu.setBackground(new java.awt.Color(51, 51, 255));
        btnTimNguyenLieuNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimNguyenLieuNhapNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTimNguyenLieuNhapNguyenLieu.setText("Tìm");
        btnTimNguyenLieuNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimNguyenLieuNhapNguyenLieuMouseClicked(evt);
            }
        });

        tableDSNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        tableDSNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSNguyenLieuNhapNL.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneDSNguyenLieuNhapNL.setViewportView(tableDSNguyenLieuNhapNL);

        panelThemNguyenLieuHoaDonNhapHang.setBackground(new java.awt.Color(255, 255, 255));

        labelThemMaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemMaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemMaNguyenLieuNhapNL.setText("Mã nguyên liệu");
        labelThemMaNguyenLieuNhapNL.setOpaque(true);

        labelNoiDungThemMaNguyenLieuNhapNL.setBackground(new java.awt.Color(241, 245, 249));
        labelNoiDungThemMaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungThemMaNguyenLieuNhapNL.setText("mã nl");
        labelNoiDungThemMaNguyenLieuNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelNoiDungThemMaNguyenLieuNhapNL.setOpaque(true);

        labelThemTenNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemTenNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemTenNguyenLieuNhapNL.setText("Tên nguyên liệu");
        labelThemTenNguyenLieuNhapNL.setOpaque(true);

        labelNoiDungThemTenNguyenLieuNhapNL.setBackground(new java.awt.Color(241, 245, 249));
        labelNoiDungThemTenNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungThemTenNguyenLieuNhapNL.setText("tên nguyên liệu");
        labelNoiDungThemTenNguyenLieuNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelNoiDungThemTenNguyenLieuNhapNL.setOpaque(true);

        labelThemSoLuongNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemSoLuongNguyenLieuNhapNL.setText("Số lượng");

        textFieldThemSoLuongNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemSoLuongNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemSoLuongNguyenLieuNhapNL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemSoLuongNguyenLieuNhapNLKeyPressed(evt);
            }
        });

        labelLoiThemSoLuongNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemSoLuongNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelLoiThemSoLuongNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemSoLuongNguyenLieuNhapNL.setOpaque(true);

        labelThemDonViNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemDonViNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemDonViNguyenLieuNhapNL.setText("Đơn vị");
        labelThemDonViNguyenLieuNhapNL.setOpaque(true);

        labelNoiDungThemDonViNguyenLieuNhapNL.setBackground(new java.awt.Color(241, 245, 249));
        labelNoiDungThemDonViNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungThemDonViNguyenLieuNhapNL.setText("đơn vị");
        labelNoiDungThemDonViNguyenLieuNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelNoiDungThemDonViNguyenLieuNhapNL.setOpaque(true);

        labelThemGiaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemGiaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemGiaNguyenLieuNhapNL.setText("Giá nhập");
        labelThemGiaNguyenLieuNhapNL.setOpaque(true);

        textFieldThemGiaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        textFieldThemGiaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        textFieldThemGiaNguyenLieuNhapNL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldThemGiaNguyenLieuNhapNLKeyPressed(evt);
            }
        });

        labelLoiThemGiaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelLoiThemGiaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelLoiThemGiaNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 0, 0));
        labelLoiThemGiaNguyenLieuNhapNL.setOpaque(true);

        btnHoanThanhThemNguyenLieuNhapNL.setBackground(new java.awt.Color(16, 185, 129));
        btnHoanThanhThemNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHoanThanhThemNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhThemNguyenLieuNhapNL.setText("Thêm");
        btnHoanThanhThemNguyenLieuNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhThemNguyenLieuNhapNLMouseClicked(evt);
            }
        });

        btnXoaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoaNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNguyenLieuNhapNL.setText("Xóa");
        btnXoaNguyenLieuNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNguyenLieuNhapNLMouseClicked(evt);
            }
        });

        btnHoanThanhSuaNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 153, 0));
        btnHoanThanhSuaNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHoanThanhSuaNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanhSuaNguyenLieuNhapNL.setText("Sửa");
        btnHoanThanhSuaNguyenLieuNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanThanhSuaNguyenLieuNhapNLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemNguyenLieuHoaDonNhapHangLayout = new javax.swing.GroupLayout(panelThemNguyenLieuHoaDonNhapHang);
        panelThemNguyenLieuHoaDonNhapHang.setLayout(panelThemNguyenLieuHoaDonNhapHangLayout);
        panelThemNguyenLieuHoaDonNhapHangLayout.setHorizontalGroup(
            panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelNoiDungThemTenNguyenLieuNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                            .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                                    .addComponent(textFieldThemSoLuongNguyenLieuNhapNL)
                                    .addGap(33, 33, 33))
                                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                                    .addComponent(labelThemSoLuongNguyenLieuNhapNL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelThemDonViNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelNoiDungThemDonViNguyenLieuNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(labelThemGiaNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldThemGiaNguyenLieuNhapNL)
                        .addComponent(labelLoiThemGiaNguyenLieuNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelLoiThemSoLuongNguyenLieuNhapNL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelThemMaNguyenLieuNhapNL)
                        .addComponent(labelNoiDungThemMaNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelThemTenNguyenLieuNhapNL))
                    .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                        .addComponent(btnHoanThanhThemNguyenLieuNhapNL)
                        .addGap(18, 18, 18)
                        .addComponent(btnHoanThanhSuaNguyenLieuNhapNL)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaNguyenLieuNhapNL)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelThemNguyenLieuHoaDonNhapHangLayout.setVerticalGroup(
            panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelThemMaNguyenLieuNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelNoiDungThemMaNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelThemTenNguyenLieuNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelNoiDungThemTenNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelThemSoLuongNguyenLieuNhapNL)
                    .addComponent(labelThemDonViNguyenLieuNhapNL))
                .addGap(18, 18, 18)
                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNoiDungThemDonViNguyenLieuNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldThemSoLuongNguyenLieuNhapNL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemSoLuongNguyenLieuNhapNL)
                .addGap(15, 15, 15)
                .addComponent(labelThemGiaNguyenLieuNhapNL)
                .addGap(18, 18, 18)
                .addComponent(textFieldThemGiaNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoiThemGiaNguyenLieuNhapNL)
                .addGap(18, 18, 18)
                .addGroup(panelThemNguyenLieuHoaDonNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoanThanhThemNguyenLieuNhapNL)
                    .addComponent(btnXoaNguyenLieuNhapNL)
                    .addComponent(btnHoanThanhSuaNguyenLieuNhapNL))
                .addGap(36, 36, 36))
        );

        scrollPaneDSNguyenLieuTrongHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));

        tableDSNguyenLieuTrongHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        tableDSNguyenLieuTrongHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableDSNguyenLieuTrongHoaDonNhapNL.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDSNguyenLieuTrongHoaDonNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSNguyenLieuTrongHoaDonNhapNLMouseClicked(evt);
            }
        });
        scrollPaneDSNguyenLieuTrongHoaDonNhapNL.setViewportView(tableDSNguyenLieuTrongHoaDonNhapNL);

        panelTinhTongTienHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));

        labelThemMaHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemMaHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemMaHoaDonNhapNL.setText("Mã hóa đơn");
        labelThemMaHoaDonNhapNL.setOpaque(true);

        labelNoiDungThemMaHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelNoiDungThemMaHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungThemMaHoaDonNhapNL.setText("0");
        labelNoiDungThemMaHoaDonNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        labelNoiDungThemMaHoaDonNhapNL.setOpaque(true);

        labelThemNguoiPhuTrachHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemNguoiPhuTrachHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemNguoiPhuTrachHoaDonNhapNL.setText("Người phụ trách");
        labelThemNguoiPhuTrachHoaDonNhapNL.setOpaque(true);

        comboBoxThemNguoiPhuTrachNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxThemNguoiPhuTrachNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelThemNhaCungCapNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelThemNhaCungCapNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemNhaCungCapNhapNL.setText("Nhà cung cấp");
        labelThemNhaCungCapNhapNL.setOpaque(true);

        comboBoxThemNhaCungCapNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxThemNhaCungCapNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        labelTongTienDonhangNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelTongTienDonhangNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTongTienDonhangNhapNL.setText("Tổng tiền (VNĐ)");
        labelTongTienDonhangNhapNL.setOpaque(true);

        labelHienThiTongTienHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelHienThiTongTienHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelHienThiTongTienHoaDonNhapNL.setForeground(new java.awt.Color(255, 0, 0));
        labelHienThiTongTienHoaDonNhapNL.setText("0");
        labelHienThiTongTienHoaDonNhapNL.setOpaque(true);

        btnXacNhanNhapNL.setBackground(new java.awt.Color(16, 185, 129));
        btnXacNhanNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXacNhanNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhanNhapNL.setText("Nhập hàng");
        btnXacNhanNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXacNhanNhapNLMouseClicked(evt);
            }
        });

        labelThemNgayTaoHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelThemNgayTaoHoaDonNhapNL.setText("Ngày tạo");

        labelNoiDungThemNgayTaoHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNoiDungThemNgayTaoHoaDonNhapNL.setText("12/04/2024 12:25");
        labelNoiDungThemNgayTaoHoaDonNhapNL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout panelTinhTongTienHoaDonNhapNLLayout = new javax.swing.GroupLayout(panelTinhTongTienHoaDonNhapNL);
        panelTinhTongTienHoaDonNhapNL.setLayout(panelTinhTongTienHoaDonNhapNLLayout);
        panelTinhTongTienHoaDonNhapNLLayout.setHorizontalGroup(
            panelTinhTongTienHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTinhTongTienHoaDonNhapNLLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelTinhTongTienHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXacNhanNhapNL)
                    .addComponent(labelThemMaHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxThemNguoiPhuTrachNhapNL, 0, 337, Short.MAX_VALUE)
                    .addComponent(labelThemNhaCungCapNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxThemNhaCungCapNhapNL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHienThiTongTienHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemNgayTaoHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNoiDungThemNgayTaoHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTongTienDonhangNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNoiDungThemMaHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelThemNguoiPhuTrachHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTinhTongTienHoaDonNhapNLLayout.setVerticalGroup(
            panelTinhTongTienHoaDonNhapNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTinhTongTienHoaDonNhapNLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelThemMaHoaDonNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelNoiDungThemMaHoaDonNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelThemNguoiPhuTrachHoaDonNhapNL)
                .addGap(18, 18, 18)
                .addComponent(comboBoxThemNguoiPhuTrachNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelThemNhaCungCapNhapNL)
                .addGap(18, 18, 18)
                .addComponent(comboBoxThemNhaCungCapNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelThemNgayTaoHoaDonNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelNoiDungThemNgayTaoHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTongTienDonhangNhapNL)
                .addGap(18, 18, 18)
                .addComponent(labelHienThiTongTienHoaDonNhapNL)
                .addGap(18, 18, 18)
                .addComponent(btnXacNhanNhapNL)
                .addGap(94, 94, 94))
        );

        btnHuyTimNguyenLieuNhapNL.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyTimNguyenLieuNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuyTimNguyenLieuNhapNL.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyTimNguyenLieuNhapNL.setText("Hủy tìm");
        btnHuyTimNguyenLieuNhapNL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyTimNguyenLieuNhapNLMouseClicked(evt);
            }
        });

        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu.setBackground(new java.awt.Color(255, 0, 255));
        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu.setText("Chọn");
        btnChonNguyenLieuVaoHoaDonNhapNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonNguyenLieuVaoHoaDonNhapNguyenLieuMouseClicked(evt);
            }
        });

        labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL.setBackground(new java.awt.Color(255, 255, 255));
        labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL.setText("Nhập tên nguyên liệu");
        labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL.setOpaque(true);

        javax.swing.GroupLayout panelThemHoaDonNhapNguyenLieuLayout = new javax.swing.GroupLayout(panelThemHoaDonNhapNguyenLieu);
        panelThemHoaDonNhapNguyenLieu.setLayout(panelThemHoaDonNhapNguyenLieuLayout);
        panelThemHoaDonNhapNguyenLieuLayout.setHorizontalGroup(
            panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneDSNguyenLieuTrongHoaDonNhapNL)
                    .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                        .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPaneDSNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                                .addComponent(btnChonNguyenLieuVaoHoaDonNhapNguyenLieu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimNguyenLieuNhapNguyenLieu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHuyTimNguyenLieuNhapNL))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                                .addComponent(labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(panelThemNguyenLieuHoaDonNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelTinhTongTienHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        panelThemHoaDonNhapNguyenLieuLayout.setVerticalGroup(
            panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTinhTongTienHoaDonNhapNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                        .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createSequentialGroup()
                                .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelThemHoaDonNhapNguyenLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnChonNguyenLieuVaoHoaDonNhapNguyenLieu)
                                    .addComponent(btnTimNguyenLieuNhapNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHuyTimNguyenLieuNhapNL))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPaneDSNguyenLieuNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelThemNguyenLieuHoaDonNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPaneDSNguyenLieuTrongHoaDonNhapNL, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelNoiDungQLNhapNguyenLieu.add(panelThemHoaDonNhapNguyenLieu, "card3");

        javax.swing.GroupLayout panelQLNhapHangLayout = new javax.swing.GroupLayout(panelQLNhapHang);
        panelQLNhapHang.setLayout(panelQLNhapHangLayout);
        panelQLNhapHangLayout.setHorizontalGroup(
            panelQLNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNhapHangLayout.createSequentialGroup()
                .addGroup(panelQLNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNoiDungQLNhapNguyenLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelQLNhapHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelQLNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitleQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelChucnangNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelQLNhapHangLayout.setVerticalGroup(
            panelQLNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLNhapHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleQLNhapHang)
                .addGap(18, 18, 18)
                .addComponent(panelChucnangNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelNoiDungQLNhapNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1474, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTongQuatNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTongQuatNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hiện ra panel để hiện trang chủ hoa đơn nhập hàng
        this.panelNoiDungQLNhapNguyenLieu.removeAll();
        this.panelNoiDungQLNhapNguyenLieu.add(this.panelTrangChuNhapNguyenLieu);
        this.panelNoiDungQLNhapNguyenLieu.repaint();
        this.panelNoiDungQLNhapNguyenLieu.revalidate();
        getAllBilImportIndredient(this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
    }//GEN-LAST:event_btnTongQuatNhapNguyenLieuMouseClicked

    private void btnThemHoaDonNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHoaDonNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hiện ra panel để ta thêm hóa đơn nhập hàng
        // đòng thời nó cx lấy ra luôn thời gian ta tạo hóa đơn nhập nguyên liệu
        this.panelNoiDungQLNhapNguyenLieu.removeAll();
        this.panelNoiDungQLNhapNguyenLieu.add(this.panelThemHoaDonNhapNguyenLieu);
        this.panelNoiDungQLNhapNguyenLieu.repaint();
        this.panelNoiDungQLNhapNguyenLieu.revalidate();
        String thoiGianTaoHoaDon = CurrentTimeFormatter.getCurrentTimeFormatted();
        this.labelNoiDungThemNgayTaoHoaDonNhapNL.setText(thoiGianTaoHoaDon);
        int soDong = this.billImportIngredientDAO.countRowInBillImportIngredientTable();
        if (soDong == 0) {
            this.labelNoiDungThemMaHoaDonNhapNL.setText("1");
        } else {
            int currentIdBillImport = this.billImportIngredientDAO.findMaxIdBillImportIngredient();
            this.labelNoiDungThemMaHoaDonNhapNL.setText((currentIdBillImport + 1) + "");
        }

    }//GEN-LAST:event_btnThemHoaDonNhapNguyenLieuMouseClicked

    private void btnXemChiTietHoaDonNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXemChiTietHoaDonNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ hiện ra frame xem thông itn chi tiết hóa đơn nhập nguyên liệu
        this.scrollPaneXemDSNguyenLieuHoaDonNhapNL.getViewport().setBackground(Color.WHITE);
        int row = this.tableDSHoaDonNhapNguyenLieu.getSelectedRow();
        if (row >= 0) {
            this.frameXemChiTietHoaDon.setSize(880, 780);
            this.frameXemChiTietHoaDon.setLocationRelativeTo(null);
            this.frameXemChiTietHoaDon.setVisible(true);
            xemThongTinHoaDon(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để xem", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnXemChiTietHoaDonNhapNguyenLieuMouseClicked

    private void btnTimHoaDonNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimHoaDonNhapNLMouseClicked
        // TODO add your handling code here:
        if (this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate() != null && this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate() != null) {
            int result = this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate().compareTo(this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate());
            if (result < 0) {
                timKiemHoaDonNhapNguyenLieuTheoNgayTaoHoaDon(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
            } else if (result > 0) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải <= ngày kết thúc", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // nếu ngày bắt đầu trùng ngày kết thúc thì ta sẽ tìm hóa đơn trong ngày hom đó
                timKiemHoaDonNhapHangTheoNgayDuyNhat(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
            }
        } else if (this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate() != null && this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate() == null) {
            timKiemHoaDonNhapNLTheoNgayBatDau(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
        } else if (this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.getDate() == null && this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.getDate() != null) {
            timKiemHoaDonNhapNLTheoNgayketThuc(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
        } else {
            JOptionPane.showMessageDialog(null, "Nhập ngày cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnTimHoaDonNhapNLMouseClicked

    private void btnHuyTimHoaDonNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimHoaDonNhapNLMouseClicked
        // TODO add your handling code here:
        this.dateChosserTimTheoNgayBatDauHoaDonNhapNL.setDate(null);
        this.dateChooserTimTheoNgayKetThucHoaDonNhapNL.setDate(null);
        getAllBilImportIndredient(this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
    }//GEN-LAST:event_btnHuyTimHoaDonNhapNLMouseClicked

    private void btnChonNguyenLieuVaoHoaDonNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonNguyenLieuVaoHoaDonNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // khi click vào nút này, nó sẽ chọn một nguyên liệu vào khung để ta thêm các thông tin cần thiết
        hienThiNguyenLieuKhiClickVaoBang(this.tableDSNguyenLieuNhapNL, this.defaultTableModelDSNguyenLieu);
    }//GEN-LAST:event_btnChonNguyenLieuVaoHoaDonNhapNguyenLieuMouseClicked

    private void btnHoanThanhThemNguyenLieuNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhThemNguyenLieuNhapNLMouseClicked
        // TODO add your handling code here:
        // click vào nút này thì sẽ thêm một BillImportIngredientItem vào table
        // và lưu nó vào trong ds temp
        int row = this.tableDSNguyenLieuNhapNL.getSelectedRow();
        if (row >= 0) {
            themNguyenLieuVaoBangTemp(this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL, this.dsBillImportIngredientItems);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nguyên liệu để nhập", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHoanThanhThemNguyenLieuNhapNLMouseClicked

    private void btnXoaNguyenLieuNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNguyenLieuNhapNLMouseClicked
        // TODO add your handling code here:
        // click vào nút này nó sẽ xóa đi một nguyên liệu trong bảng temp nguyên liệu
        xoaNguyenLieuKhoiBangTemp(this.tableDSNguyenLieuTrongHoaDonNhapNL, this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL, this.dsBillImportIngredientItems);
    }//GEN-LAST:event_btnXoaNguyenLieuNhapNLMouseClicked

    private void tableDSNguyenLieuTrongHoaDonNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSNguyenLieuTrongHoaDonNhapNLMouseClicked
        // TODO add your handling code here:
        hienThiNguyenLieuRaPanelKhiClickVaoBangNLTemp(this.tableDSNguyenLieuTrongHoaDonNhapNL, this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL);
    }//GEN-LAST:event_tableDSNguyenLieuTrongHoaDonNhapNLMouseClicked

    private void btnHoanThanhSuaNguyenLieuNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanThanhSuaNguyenLieuNhapNLMouseClicked
        // TODO add your handling code here:
        // click vào nút này sẽ sửa nguyên liệu cần nhập trong bảng temp
        suaNguyenLieuTrongBangTemp(this.tableDSNguyenLieuTrongHoaDonNhapNL, this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL, this.dsBillImportIngredientItems);
    }//GEN-LAST:event_btnHoanThanhSuaNguyenLieuNhapNLMouseClicked

    private void btnXacNhanNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXacNhanNhapNLMouseClicked
        // TODO add your handling code here:
        // click vào nút này thì sẽ thêm mới một hóa đơn nhập hàng vào csdl
        if (this.defaultTableModelDSNguyenLieuTrongHoaDonNhapNL.getRowCount() != 0) {
            themHoaDonNhapNguyenLieu(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);
            getAllIngredient(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
            this.labelHienThiTongTienHoaDonNhapNL.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn nguyên liệu cho hóa đơn nhập hàng", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXacNhanNhapNLMouseClicked

    private void comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào đâu để thực hiện tìm kiếm hóa đơn nhập nguyên liệu theo nhà cung cấp
    }//GEN-LAST:event_comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuMouseClicked

    private void comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        // click vào đây thì sẽ tìm kiếm hóa đơn nhập nguyên liệu theo người ohuj trách
    }//GEN-LAST:event_comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuMouseClicked

    private void comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuActionPerformed
        // TODO add your handling code here:
        timKiemHoaDonNhapNguyenLieuTheoNhaCungCap(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);

    }//GEN-LAST:event_comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieuActionPerformed

    private void comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuActionPerformed
        // TODO add your handling code here:
        timKiemHoaDonNhapNguyenLieuTheoNguoiPhuTrach(this.tableDSHoaDonNhapNguyenLieu, this.defaultTableModelDSHoaDonNhapNguyenLieu, this.dsHoaDonNhapNguyenLieu);

    }//GEN-LAST:event_comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieuActionPerformed

    private void btnTimNguyenLieuNhapNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimNguyenLieuNhapNguyenLieuMouseClicked
        // TODO add your handling code here:
        if (this.textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nhập tên nguyên liệu cần tìm", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            timKiemNguyenLieuTheoTen(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
        }
    }//GEN-LAST:event_btnTimNguyenLieuNhapNguyenLieuMouseClicked

    private void btnHuyTimNguyenLieuNhapNLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyTimNguyenLieuNhapNLMouseClicked
        // TODO add your handling code here:
        this.textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu.setText("");
        getAllIngredient(this.defaultTableModelDSNguyenLieu, this.dsNguyenLieu);
    }//GEN-LAST:event_btnHuyTimNguyenLieuNhapNLMouseClicked

    private void textFieldThemSoLuongNguyenLieuNhapNLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemSoLuongNguyenLieuNhapNLKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemSoLuongNguyenLieuNhapNL.getText().equals("")) {
            this.labelLoiThemSoLuongNguyenLieuNhapNL.setText("");
        }
    }//GEN-LAST:event_textFieldThemSoLuongNguyenLieuNhapNLKeyPressed

    private void textFieldThemGiaNguyenLieuNhapNLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldThemGiaNguyenLieuNhapNLKeyPressed
        // TODO add your handling code here:
        if (!this.labelLoiThemGiaNguyenLieuNhapNL.getText().equals("")) {
            this.labelLoiThemGiaNguyenLieuNhapNL.setText("");
        }
    }//GEN-LAST:event_textFieldThemGiaNguyenLieuNhapNLKeyPressed

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
            java.util.logging.Logger.getLogger(QLNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhapNguyenLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNguyenLieuVaoHoaDonNhapNguyenLieu;
    private javax.swing.JButton btnHoanThanhSuaNguyenLieuNhapNL;
    private javax.swing.JButton btnHoanThanhThemNguyenLieuNhapNL;
    private javax.swing.JButton btnHuyTimHoaDonNhapNL;
    private javax.swing.JButton btnHuyTimNguyenLieuNhapNL;
    private javax.swing.JButton btnThemHoaDonNhapNguyenLieu;
    private javax.swing.JButton btnTimHoaDonNhapNL;
    private javax.swing.JButton btnTimNguyenLieuNhapNguyenLieu;
    private javax.swing.JButton btnTongQuatNhapNguyenLieu;
    private javax.swing.JButton btnXacNhanNhapNL;
    private javax.swing.JButton btnXemChiTietHoaDonNhapNguyenLieu;
    private javax.swing.JButton btnXoaNguyenLieuNhapNL;
    private javax.swing.JComboBox<String> comboBoxThemNguoiPhuTrachNhapNL;
    private javax.swing.JComboBox<String> comboBoxThemNhaCungCapNhapNL;
    private javax.swing.JComboBox<String> comboBoxTimTheoNguoiPhuTachTrangChuNhapNguyenLieu;
    private javax.swing.JComboBox<String> comboBoxTimTheoNhaCungCapTrangChuNhapNguyenLieu;
    private com.toedter.calendar.JDateChooser dateChooserTimTheoNgayKetThucHoaDonNhapNL;
    private com.toedter.calendar.JDateChooser dateChosserTimTheoNgayBatDauHoaDonNhapNL;
    private javax.swing.JFrame frameXemChiTietHoaDon;
    private javax.swing.JLabel labelEndTimNgayLapHoaDonNhapNguyenLieu;
    private javax.swing.JLabel labelHienThiTongTienHoaDonNhapNL;
    private javax.swing.JLabel labelLoiThemGiaNguyenLieuNhapNL;
    private javax.swing.JLabel labelLoiThemSoLuongNguyenLieuNhapNL;
    private javax.swing.JLabel labelNoiDungThemDonViNguyenLieuNhapNL;
    private javax.swing.JLabel labelNoiDungThemMaHoaDonNhapNL;
    private javax.swing.JLabel labelNoiDungThemMaNguyenLieuNhapNL;
    private javax.swing.JLabel labelNoiDungThemNgayTaoHoaDonNhapNL;
    private javax.swing.JLabel labelNoiDungThemTenNguyenLieuNhapNL;
    private javax.swing.JLabel labelStartTimNgayLapHoaDonNhapNguyenLieu;
    private javax.swing.JLabel labelTenQuan;
    private javax.swing.JLabel labelThemDonViNguyenLieuNhapNL;
    private javax.swing.JLabel labelThemGiaNguyenLieuNhapNL;
    private javax.swing.JLabel labelThemMaHoaDonNhapNL;
    private javax.swing.JLabel labelThemMaNguyenLieuNhapNL;
    private javax.swing.JLabel labelThemNgayTaoHoaDonNhapNL;
    private javax.swing.JLabel labelThemNguoiPhuTrachHoaDonNhapNL;
    private javax.swing.JLabel labelThemNhaCungCapNhapNL;
    private javax.swing.JLabel labelThemSoLuongNguyenLieuNhapNL;
    private javax.swing.JLabel labelThemTenNguyenLieuNhapNL;
    private javax.swing.JLabel labelTimNguoiPhuTrachTrangChuNhapNguyenLieu;
    private javax.swing.JLabel labelTimNhaCungCapTrangChuNhapNguyenLieu;
    private javax.swing.JLabel labelTinKiemNguyenLieuTheoTenThemHoaDonNhapNL;
    private javax.swing.JLabel labelTitleQLNhapHang;
    private javax.swing.JLabel labelTitleXemHoaDonNhapNL;
    private javax.swing.JLabel labelTongTienDonhangNhapNL;
    private javax.swing.JLabel labelXemMaHoaDonNhapNL;
    private javax.swing.JLabel labelXemNgayTaoHoaDonNhapNL;
    private javax.swing.JLabel labelXemNguoiPhuTrachHoaDonNhapNL;
    private javax.swing.JLabel labelXemNhaCungCapHoaDonNhapNL;
    private javax.swing.JLabel labelXemNoiDungMaHoaDonNhapNL;
    private javax.swing.JLabel labelXemNoiDungNgayTaoHoaDonNhapNL;
    private javax.swing.JLabel labelXemNoiDungNguoiPhuTrachHoaDonNhapNL;
    private javax.swing.JLabel labelXemNoiDungNhaCungCapHoaDonNhapNL;
    private javax.swing.JLabel labelXemNoiDungTongTienHoaDonNhapNL;
    private javax.swing.JLabel labelXemTongTienHoaDonNhapNL;
    private javax.swing.JPanel panelChucnangNhapNguyenLieu;
    private javax.swing.JPanel panelNoiDungQLNhapNguyenLieu;
    private javax.swing.JPanel panelQLNhapHang;
    private javax.swing.JPanel panelThemHoaDonNhapNguyenLieu;
    private javax.swing.JPanel panelThemNguyenLieuHoaDonNhapHang;
    private javax.swing.JPanel panelTimKiemHoaDonNhapNguyenLieu;
    private javax.swing.JPanel panelTinhTongTienHoaDonNhapNL;
    private javax.swing.JPanel panelTrangChuNhapNguyenLieu;
    private javax.swing.JPanel panelXemChiTietHoaDonNhapNL;
    private javax.swing.JScrollPane scrollPaneDSHoaDonNhapNguyenLieu;
    private javax.swing.JScrollPane scrollPaneDSNguyenLieuNhapNL;
    private javax.swing.JScrollPane scrollPaneDSNguyenLieuTrongHoaDonNhapNL;
    private javax.swing.JScrollPane scrollPaneXemDSNguyenLieuHoaDonNhapNL;
    private javax.swing.JTable tableDSHoaDonNhapNguyenLieu;
    private javax.swing.JTable tableDSNguyenLieuNhapNL;
    private javax.swing.JTable tableDSNguyenLieuTrongHoaDonNhapNL;
    private javax.swing.JTable tableXemDSNguyenLieuHoaDonNhapNL;
    private javax.swing.JTextField textFieldThemGiaNguyenLieuNhapNL;
    private javax.swing.JTextField textFieldThemSoLuongNguyenLieuNhapNL;
    private javax.swing.JTextField textFieldTimNguyenLieuThemHoaDonNhapNguyenLieu;
    // End of variables declaration//GEN-END:variables
}
