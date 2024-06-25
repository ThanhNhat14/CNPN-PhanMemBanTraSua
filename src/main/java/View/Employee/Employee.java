/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Employee;

import Controller.Employee.HandleBills;
import View.LogRes.ManagerEmployee.Login;
import View.LogRes.ManagerEmployee.Register;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import Controller.Employee.AddProductController;
import Controller.Employee.DeleteProductController;
import Controller.Employee.EditInfoEmployeeController;
import Controller.Employee.EditInfoProductController;
import Controller.Employee.CategoryController;
//import controller.GetInfoMilkTeaController;
import Controller.Employee.GetMilkTeaFromDatabaseController;
import Controller.Employee.GetToppingFromDatabaseController;

import static Controller.LogRes.ManagerEmployee.Login.ManagerEmployee;
import static Controller.Employee.HandleProducts.Products;
import static Controller.Employee.HandleProducts.Toppings;
import static Controller.Employee.HandleProducts.getProducts;
import static Controller.Employee.HandleProducts.getToppings;
import Controller.Employee.SizeController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.interfaces.ECKey;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.crypto.spec.RC2ParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.View;
import Model.Employee.Account;
import Model.Employee.Product;
import Model.Employee.Topping;
import Model.Employee.getRowInTable;

/**
 *
 * @author admin
 */
public class Employee extends javax.swing.JFrame {

    /**
     * Creates new form Employee
     */
    JDialog productChoose = new JDialog();
    JDialog giohang1 = new JDialog();
    JDialog inBill = new JDialog();
    JDialog PTTT = new JDialog();
    JPanel hienGioHang = new JPanel();
    JPanel chonLaiTop = new JPanel();
    JDialog chonLaiTP = new JDialog();
    public static List<Map<String, Object>> Items = new ArrayList<>();
    public static List<Map<String, Object>> IdToppings = new ArrayList<>();
    public static List<Map<String, Object>> ProductToppings = new ArrayList<>();
//    int indexP = 0;
    int tongThanhToan = 0;
    int size1Choose = 1;
    int idItemDetail = HandleBills.getNewIdItemDetail();
    int demslitem = Items.size();
    boolean giaChon = true;
    public static DefaultTableModel dtmInfo;
    public static JTable jTable_Products;
    private Point initialClick;
    JLabel tongtienThanhToan = new JLabel();
    int indexP = 0;
    String tenItemChonLaiTopping = "";
    String slItemChonLaiTopping = "";
    JLabel tenItemMoi = new JLabel();
    String danhSachTopping = "";
    public JButton jButtonThanhToanGioHang = new JButton();
    JButton xacNhanChon = new JButton();
    int checkPTTT = 0;
    int tienMatNhan = 0;
    float discount = 0;
    int thanhTien = 0;
    int sloneLine = 0;

//    JButton OKChonLaiTopping1 = new JButton();
//    public static Map<String, Object> info = new HashMap<>();
    public Employee() {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        jPanelMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Lấy tọa độ chuột khi nhấn
                initialClick = e.getPoint();
            }
        });
        jPanelMain.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Lấy tọa độ chuột khi kéo
                int x = getLocation().x + e.getX() - initialClick.x;
                int y = getLocation().y + e.getY() - initialClick.y;

                // Di chuyển cửa sổ đến vị trí mới
                setLocation(x, y);
            }
        });
        System.out.println(ManagerEmployee);
        jComboBoxStatus.addItem("Tất cả");
        setSizeTableMT();
        setSizeTableTP();
        setSizeTableTS();
        setSizeTableTPShort();
//        GetMilkTeaFromDatabaseController.getListMilkTea(jTableMilkTea);
//        GetToppingFromDatabaseController.getListTopping(jTableTopping);
        GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
        GetToppingFromDatabaseController.getListToppingShort(jTableTP);

        hideButtonInInfo();
        hideButtonYesNoInInfo();
//        showButtonInInfo();
        FunctionDisplay();
        setScroll(allProduct);
        setScroll(jScrollPane5);
        setScroll(jScrollPane8);

        if (Items.size() <= 0) {
            jlabelSLTrongGioHang.setVisible(false);
        } else {
            jlabelSLTrongGioHang.setVisible(true);

        }
        jButtonThanhToanGioHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setPTTT();
            }

        });
        bnt_XuatHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (PTTT.isVisible()) {
                    PTTT.dispose();
                }
                int totalQuantity = 0;
                System.out.println(Items);
                // Calculate total quantity of products
                for (int i = 0; i < Items.size(); i++) {
                    Map<String, Object> item = Items.get(i);
                    totalQuantity += (int) item.get("quantityProduct");
                }
                if (checkPTTT == 1) {
                    thanhTien = tongThanhToan;
                    int dis = 0;
                    String d = NhapDiscount.getText();
                    int idVoucher = 0;
                    if (tongThanhToan >= HandleBills.getToCostByCode(d)) {
                        idVoucher = HandleBills.getidVoucherByCode(d);

                        float dd = (float) HandleBills.getPercentDiscountByIdVoucher(idVoucher) / 100;
//                     
                        discount = ((float) (tongThanhToan) * dd);
                        System.out.println(discount + "djjf");
//                        NhapDiscount.setText(String.valueOf(dd));

                    } else {
                        JOptionPane.showMessageDialog(panelTop, "Mã giảm giá không đúng!!");
                        setPTTT();
                        return;

                    }

                    tongThanhToan -= (int) (discount);
                    HandleBills.createOrder((int) ManagerEmployee.get("idAccount"), tongThanhToan, totalQuantity, idVoucher, "Đã giao", "Thẻ ngân hàng(ATM)", "", "Tại quán", Items);
//                    HandleBills.createOrder(3, tongThanhToan, totalQuantity, 0, "Đã giao", "Thẻ ngân hàng(ATM)", "", "Tại quán", 1, Items);
                    idItemDetail = HandleBills.getNewIdItemDetail();
                    int newidBill = HandleBills.getNewIdBill();
                    xuatHDATM(newidBill);
                    tienMatNhan = 0;
                } else if (checkPTTT == 2) {
                    try {
                        String inputString = NhapTienMat.getText();
                        int tm = Integer.parseInt(inputString);
                        tienMatNhan = tm;
                        System.out.println(inputString);
                        System.out.println(tm);
                        if (tongThanhToan <= tienMatNhan) {
                            // Create order and get new idItemDetail
                            thanhTien = tongThanhToan;
                            String d = NhapDiscount.getText();
                            int idVoucher = 0;
                            if (tongThanhToan >= HandleBills.getToCostByCode(d)) {
                                idVoucher = HandleBills.getidVoucherByCode(d);

                                float dd = (float) HandleBills.getPercentDiscountByIdVoucher(idVoucher) / 100;
//                     
                                discount = ((float) (tongThanhToan) * dd);
                                System.out.println(discount + "djjf");
//                        NhapDiscount.setText(String.valueOf(dd));

                            } else {
                                JOptionPane.showMessageDialog(panelTop, "Mã giảm giá không đúng!!");
                                setPTTT();
                                return;

                            }
                            tongThanhToan -= (int) (discount);

                            HandleBills.createOrder((int) ManagerEmployee.get("idAccount"), tongThanhToan, totalQuantity, idVoucher, "Đã giao", "Tiền mặt", "", "Tại quán", Items);
//                            HandleBills.createOrder(3, tongThanhToan, totalQuantity, (int) discount, "Đã giao", "Tiền mặt", "", "Tại quán", 1, Items);
                            idItemDetail = HandleBills.getNewIdItemDetail();

//                System.out.println(tongThanhToan);
                            int newidBill = HandleBills.getNewIdBill();
                            xuatHD(newidBill);
                        } else {
                            JOptionPane.showMessageDialog(panelTop, "Số tiền không hợp lệ!!");

                            setPTTT();
                            return;

                        }
//                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PTTT, "Vui lòng nhập số tiền hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        setPTTT();
                        return;
                    }

                } else {
                    JOptionPane.showMessageDialog(panelTop, "PTTT Không hợp lệ!!");
                    setPTTT();
                    return;
                }

            }

        });
        checkTBChoXacNhan();

    }

    boolean checkTBChoXacNhan() {
        boolean x = HandleBills.hasPendingConfirmationStatus();
        System.out.println(x);
        if (x == true) {
            TBChoXacNhan.setVisible(x);
            TBChoXacNhan1.setVisible(x);
            TBChoXacNhan2.setVisible(x);
            TBChoXacNhan3.setVisible(x);
            TBChoXacNhan4.setVisible(x);
            jButtonBill.setBackground(Color.red);
        } else {
            TBChoXacNhan.setVisible(false);
            TBChoXacNhan1.setVisible(false);
            TBChoXacNhan2.setVisible(false);
            TBChoXacNhan3.setVisible(false);
            TBChoXacNhan4.setVisible(false);
            jButtonBill.setBackground(new Color(51, 255, 204));

        }
        return x;
    }

    void setScroll(JScrollPane js) {
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        js.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        js.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
    }

    void setPTTT() {
        if (giohang1.isVisible()) {
            giohang1.dispose();
        }
        if (inBill.isVisible()) {
            inBill.dispose();
        }

        JPanel ChonPTTT = new JPanel();
        JRadioButton PTTT_TM = new JRadioButton();
        JRadioButton PTTT_ATM = new JRadioButton();
        JCheckBox jCheckBoxDiscount = new JCheckBox();
//        JTextField NhapTienMat = new JTextField();

        // Set initial visibility of NhapTienMat
        NhapTienMat.setVisible(false);
        NhapDiscount.setVisible(false);

        ChonPTTT.setBackground(new java.awt.Color(255, 255, 255));
        ChonPTTT.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CHỌN PHƯƠNG THỨC THANH TOÁN");

        PTTT_ATM.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupPTTT.add(PTTT_ATM);
        PTTT_ATM.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        PTTT_ATM.setForeground(new java.awt.Color(0, 0, 0));
        PTTT_ATM.setText("THẺ ATM");

        PTTT_TM.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupPTTT.add(PTTT_TM);
        PTTT_TM.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        PTTT_TM.setForeground(new java.awt.Color(0, 0, 0));
        PTTT_TM.setText("TIỀN MẶT");

        NhapTienMat.setBackground(new java.awt.Color(255, 255, 255));
        NhapTienMat.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        NhapTienMat.setForeground(new java.awt.Color(0, 0, 0));
        NhapTienMat.setText("");
        NhapTienMat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        bnt_XuatHD.setBackground(new java.awt.Color(255, 255, 255));
        bnt_XuatHD.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bnt_XuatHD.setForeground(new java.awt.Color(0, 0, 0));
        bnt_XuatHD.setText("XUẤT HÓA ĐƠN");
        bnt_XuatHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        ThoatPTTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        jCheckBoxDiscount.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxDiscount.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jCheckBoxDiscount.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBoxDiscount.setText("DISCOUNT(%):");

        NhapDiscount.setBackground(new java.awt.Color(255, 255, 255));
        NhapDiscount.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        NhapDiscount.setForeground(new java.awt.Color(0, 0, 0));
        NhapDiscount.setText("");
        NhapDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout ChonPTTTLayout = new javax.swing.GroupLayout(ChonPTTT);
        ChonPTTT.setLayout(ChonPTTTLayout);
        ChonPTTTLayout.setHorizontalGroup(
                ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ChonPTTTLayout.createSequentialGroup()
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(ChonPTTTLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(PTTT_ATM, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(ChonPTTTLayout.createSequentialGroup()
                                                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(PTTT_TM, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jCheckBoxDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(NhapDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(NhapTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonPTTTLayout.createSequentialGroup()
                                                .addGap(0, 30, Short.MAX_VALUE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ThoatPTTT)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonPTTTLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bnt_XuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207))
        );
        ChonPTTTLayout.setVerticalGroup(
                ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ChonPTTTLayout.createSequentialGroup()
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ThoatPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PTTT_ATM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(PTTT_TM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(ChonPTTTLayout.createSequentialGroup()
                                                .addComponent(NhapTienMat)
                                                .addGap(1, 1, 1)))
                                .addGap(18, 18, 18)
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBoxDiscount)
                                        .addComponent(NhapDiscount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(bnt_XuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
        );
        ThoatPTTT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PTTT.dispose();
                checkPTTT = 0;
            }
        });

        PTTT = new JDialog(); // Khởi tạo lại khung inBill mỗi lần
        PTTT.setUndecorated(true);
        PTTT.setResizable(false);
        PTTT.setLocationRelativeTo(null);
        PTTT.setAlwaysOnTop(true);
        PTTT.setBounds(450, 200, 666, 224);
        PTTT.add(ChonPTTT);
        PTTT.setBackground(Color.BLACK);

        jCheckBoxDiscount.setSelected(true);
        if (jCheckBoxDiscount.isSelected()) {
            NhapDiscount.setVisible(true);

            PTTT.revalidate();
            PTTT.repaint();
        } else {
            NhapDiscount.setVisible(false);
            PTTT.revalidate();
            PTTT.repaint();
        }
        // Thêm sự kiện cho radio button PTTT_ATM
        PTTT_ATM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PTTT_ATM.isSelected()) {
                    NhapTienMat.setVisible(false);
                    checkPTTT = 1;
//                    tienMatNhan = 0;
                    PTTT.revalidate();
                    PTTT.repaint();
                    System.out.println("Bạn đã chọn thanh toán bằng thẻ ATM");
                }
            }
        });

        // Thêm sự kiện cho radio button PTTT_TM
        PTTT_TM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PTTT_TM.isSelected()) {
                    NhapTienMat.setVisible(true);
//                    tienMatNhan = Integer.parseInt(NhapTienMat.getText());
//                    tienMatNhan = 300000;
                    checkPTTT = 2;
                    PTTT.revalidate();
                    PTTT.repaint();
                    System.out.println("Bạn đã chọn thanh toán bằng tiền mặt");
                }
            }
        });

        PTTT.setVisible(true);
    }

    void xemChiTietHD(int idBill, String status) {
        // Xóa nội dung cũ của PrintBill (nếu có)
        if (ChitietHDO.getComponentCount() > 0) {
            ChitietHDO.removeAll();
        }
        inBill = new JDialog(); // Khởi tạo lại khung inBill mỗi lần
        inBill.setUndecorated(true);
        inBill.setResizable(false);
        inBill.setLocationRelativeTo(null);
        inBill.setAlwaysOnTop(true);
        inBill.setBounds(490, 80, 510, 600);

        JScrollPane jScrollPane = new JScrollPane();
        setScroll(jScrollPane);
        jScrollPane.setViewportView(ChitietHDO);

        inBill.add(jScrollPane);
        inBill.setVisible(true);

        setChiTietHD(idBill, status); // Thực hiện thiết lập lại nội dung mới cho PrintBill
        tienMatNhan = 0;
    }

    void setChiTietHD(int idBill, String status) {
        String newdateBill = HandleBills.getDateOrderByIdBill(idBill);
        int tongTien = HandleBills.getTotalpriceByIdBill(idBill);
        int idVoucher = HandleBills.getIdVoucherByIdBill(idBill);
        int discountBill = HandleBills.getPercentDiscountByIdVoucher(idVoucher);

        float priceDis = ((float) tongTien) * 100 / (100 - discountBill);
        String cancelReason = HandleBills.getCancelReasonByIdBill(idBill);
        HandleBills.getBill(idBill);
        String hinhThuc = "Tại quán";
        int transitFee2 = 0;
        if (HandleBills.getIdCustomerByIdBill(idBill) != 0) {
            hinhThuc = "OnLine";
            transitFee2 = 16000;
        }

        ChitietHDO.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill3.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill3.setText("CHI TIẾT HÓA ĐƠN");

        MaBill3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill3.setForeground(new java.awt.Color(0, 0, 0));
        MaBill3.setText("MÃ HÓA ĐƠN: " + idBill);

        NgayXuatBill3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill3.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill3.setText("NGÀY: " + newdateBill);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("THÀNH TIỀN:");

        jLabelThoatInBill3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        TrangThaiLabel.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TrangThaiLabel.setForeground(new java.awt.Color(0, 0, 0));
        TrangThaiLabel.setText("TRẠNG THÁI:");

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));

        TongThanhTienInBill3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill3.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill3.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBill3Layout = new javax.swing.GroupLayout(jPanelhienIBill3);
        jPanelhienIBill3.setLayout(jPanelhienIBill3Layout);
        jPanelhienIBill3Layout.setHorizontalGroup(
                jPanelhienIBill3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBill3Layout.setVerticalGroup(
                jPanelhienIBill3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 32, Short.MAX_VALUE)
        );

        TENQUAN2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN2.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN2.setText("TRÀ SỮA TASU");

        DiaChi2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi2.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi2.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon2.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon2.setText("Tổng giảm giá món:");

        GiaDiscount2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount2.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("TỔNG TIỀN:");

        TongTienInBill2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill2.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        powered1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        powered1.setForeground(new java.awt.Color(0, 0, 0));
        powered1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        powered1.setText("powered by n21dcat037.com");

        TrangThai.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TrangThai.setForeground(new java.awt.Color(0, 0, 0));

        HinhThuc3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc3.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc3.setText("HÌNH THỨC: " + hinhThuc);

        LiDoHuy.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LiDoHuy.setForeground(new java.awt.Color(0, 0, 0));

        TongGiamGiaMon4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon4.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon4.setText("Phí vận chuyển:");
        TongGiamGiaMon4.setVisible(false);

        transitFee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        transitFee1.setForeground(new java.awt.Color(0, 0, 0));
        transitFee1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        transitFee1.setVisible(false);
        javax.swing.GroupLayout ChitietHDOLayout = new javax.swing.GroupLayout(ChitietHDO);
        ChitietHDO.setLayout(ChitietHDOLayout);
        ChitietHDOLayout.setHorizontalGroup(
                ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jPanelhienIBill3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(DiaChi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                                                .addGap(61, 61, 61)
                                                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(NgayXuatBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(MaBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(HinhThuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                                                .addGap(58, 58, 58)
                                                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(LiDoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addGroup(ChitietHDOLayout.createSequentialGroup()
                                                                                        .addComponent(TongGiamGiaMon4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(transitFee1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(ChitietHDOLayout.createSequentialGroup()
                                                                                        .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(TrangThaiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(TongGiamGiaMon2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGap(44, 44, 44)
                                                                                        .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(TongTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(TongThanhTienInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(GiaDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(26, 26, 26))
                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                .addContainerGap(73, Short.MAX_VALUE)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                                .addComponent(TENQUAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                                                .addComponent(tenInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabelThoatInBill3)
                                                .addContainerGap())))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                                .addComponent(powered1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        ChitietHDOLayout.setVerticalGroup(
                ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelThoatInBill3)
                                        .addComponent(tenInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TENQUAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DiaChi2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MaBill3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NgayXuatBill3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HinhThuc3)
                                .addGap(20, 20, 20)
                                .addComponent(jPanelhienIBill3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(TongThanhTienInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(GiaDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TongGiamGiaMon2))
                                .addGap(18, 18, 18)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(transitFee1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TongGiamGiaMon4))
                                .addGap(18, 18, 18)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(TongTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel40))
                                .addGap(23, 23, 23)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TrangThaiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LiDoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(powered1)
                                .addGap(14, 14, 14))
        );
        if (!cancelReason.equals("")) {
            LiDoHuy.setText("Lý do hủy: " + cancelReason);
        }

        if (transitFee2 != 0) {
            TongGiamGiaMon4.setVisible(true);
            transitFee1.setVisible(true);
            transitFee1.setText(String.valueOf(new DecimalFormat("#,###").format(transitFee2)));
            TongThanhTienInBill3.setText(String.valueOf(new DecimalFormat("#,###").format(priceDis - 16000)));
        } else {

            TongThanhTienInBill3.setText(String.valueOf(new DecimalFormat("#,###").format(priceDis)));
        }
        TongTienInBill2.setText(String.valueOf(new DecimalFormat("#,###").format(tongTien)));
        GiaDiscount2.setText("-" + String.valueOf(new DecimalFormat("#,###").format((priceDis * (float) discountBill / 100))));
        TrangThai.setText(status);
        jLabelThoatInBill3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inBill.dispose();
                Items.clear();
                IdToppings.clear();
                HandleBills.ItemBills.clear();
                jPanelhienIBill3.removeAll();
                tongThanhToan = 0;
                demslitem = Items.size();
                System.out.println(demslitem);
                if (demslitem == 0) {
                    jlabelSLTrongGioHang.setVisible(false);
                }
                discount = 0;
                thanhTien = 0;
                sloneLine = 0;
            }
        });
        jPanelhienIBill3.setLayout(new BoxLayout(jPanelhienIBill3, BoxLayout.Y_AXIS));
        JPanel billpanel = new JPanel();
        billpanel.setLayout(new BoxLayout(billpanel, BoxLayout.Y_AXIS));
        OneLineBill lineBill = new OneLineBill();
        lineBill.getThem().setText("TÊN");
        lineBill.getSoluong().setText("SL");
        lineBill.getGia().setText("Đ.GIÁ");
        lineBill.getThanhTien().setText("T.TIỀN");
        billpanel.add(lineBill);
        billpanel.add(Box.createVerticalStrut(2));
//        jScrollPaneInBill.setViewportView(lineBill);
        sloneLine++;
        for (int i = 0; i < HandleBills.ItemBills.size(); i++) {
            Map<String, Object> itemb = HandleBills.ItemBills.get(i);
            OneLineBill lbit = new OneLineBill();
            int gia = (int) itemb.get("priceProduct");
            int sl = (int) itemb.get("quantityProduct");
            int tt = sl * (gia);
            lbit.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tt)));
            lbit.getThem().setText((String) itemb.get("nameProduct"));
            lbit.getSoluong().setText(String.valueOf(sl));
            lbit.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(gia)));
            billpanel.add(lbit);
            sloneLine++;

//            jScrollPaneInBill.setViewportView(lbit);
            List<Map<String, Object>> toppings = (List<Map<String, Object>>) itemb.get("Toppings");
            for (Map<String, Object> topping : toppings) {
                String nameTopping = (String) topping.get("nameTopping");
                int quantityTopping = (int) topping.get("quantityTopping");
                int priceTopping = (int) topping.get("priceTopping");
                int tttop = quantityTopping * priceTopping;
                if (quantityTopping > 0) {
                    OneLineBill lbtp = new OneLineBill();
                    lbtp.getThem().setText(" +" + nameTopping);
                    lbtp.getSoluong().setText(String.valueOf(quantityTopping));
                    lbtp.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(priceTopping)));
                    lbtp.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tttop)));
                    billpanel.add(lbtp);
                    sloneLine++;
//                    jScrollPaneInBill.setViewportView(lbtp);
                }
            }

            billpanel.add(Box.createVerticalStrut(1));
        }
        jPanelhienIBill3.add(billpanel);

    }

    void xuatHDATM(int idBill) {
        // Xóa nội dung cũ của PrintBill (nếu có)
        if (PrintBill.getComponentCount() > 0) {
            PrintBill.removeAll();
        }
        inBill = new JDialog(); // Khởi tạo lại khung inBill mỗi lần
        inBill.setUndecorated(true);
        inBill.setResizable(false);
        inBill.setLocationRelativeTo(null);
        inBill.setAlwaysOnTop(true);
        inBill.setBounds(490, 80, 510, 600);
//        System.out.println(sloneLine + "dhfdjkhj");
        if (sloneLine > 5) {
            inBill.setBounds(490, 80, 520, 720);
        }

        JScrollPane jScrollPane = new JScrollPane();
        setScroll(jScrollPane);
        jScrollPane.setViewportView(PrintBillATM);

        inBill.add(jScrollPane);
        inBill.setVisible(true);

        setPrintBillATM(idBill); // Thực hiện thiết lập lại nội dung mới cho PrintBill
        tienMatNhan = 0;
    }

    void setPrintBillATM(int idBill) {
        if (giohang1.isVisible()) {
            giohang1.dispose();
        }
        String newdateBill = HandleBills.getDateOrderByIdBill(idBill);
        int tongTien = HandleBills.getTotalpriceByIdBill(idBill);
        int idVoucher = HandleBills.getIdVoucherByIdBill(idBill);
        int discountBill = HandleBills.getPercentDiscountByIdVoucher(idVoucher);

        float priceDis = ((float) tongTien) * 100 / (100 - discountBill);

        String hinhThuc = "Tại quán";
        int transitFee1 = 0;
        if (HandleBills.getIdCustomerByIdBill(idBill) != 0) {
            hinhThuc = "OnLine";
            transitFee1 = 16000;
        }
//        List<Map<String, Object>> ItemBills=HandleBills.get;
        HandleBills.getBill(idBill);

        PrintBillATM.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill2.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill2.setText("HÓA ĐƠN THANH TOÁN");

        MaBill2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill2.setForeground(new java.awt.Color(0, 0, 0));
        MaBill2.setText("MÃ HÓA ĐƠN: " + idBill);

        NgayXuatBill2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill2.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill2.setText("NGÀY: " + newdateBill);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("THÀNH TIỀN:");

        jLabelThoatInBill2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        ThanhToanTienMat1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ThanhToanTienMat1.setForeground(new java.awt.Color(0, 0, 0));
        ThanhToanTienMat1.setText("+Thanh toán thẻ(ATM):");
        if (HandleBills.getIdCustomerByIdBill(idBill) != 0) {
            ThanhToanTienMat1.setVisible(false);
        } else {
            ThanhToanTienMat1.setVisible(true);
        }

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Cảm ơn & Hẹn gặp lại!");

        TongThanhTienInBill2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill2.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill2.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBill2Layout = new javax.swing.GroupLayout(jPanelhienIBill2);
        jPanelhienIBill2.setLayout(jPanelhienIBill2Layout);
        jPanelhienIBill2Layout.setHorizontalGroup(
                jPanelhienIBill2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBill2Layout.setVerticalGroup(
                jPanelhienIBill2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );

        TENQUAN1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN1.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN1.setText("TRÀ SỮA TASU");

        DiaChi1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi1.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi1.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon1.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon1.setText("Tổng giảm giá món:");

        GiaDiscount1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount1.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("TỔNG TIỀN:");

        TongTienInBill1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill1.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        powered.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        powered.setForeground(new java.awt.Color(0, 0, 0));
        powered.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        powered.setText("powered by n21dcat037.com");

        HinhThuc1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc1.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc1.setText("HÌNH THỨC: " + hinhThuc);

        TongGiamGiaMon3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon3.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon3.setText("Phí vận chuyển");
        TongGiamGiaMon3.setVisible(false);

        transitFee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        transitFee.setForeground(new java.awt.Color(0, 0, 0));
        transitFee.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        transitFee.setVisible(false);

        javax.swing.GroupLayout PrintBillATMLayout = new javax.swing.GroupLayout(PrintBillATM);
        PrintBillATM.setLayout(PrintBillATMLayout);
        PrintBillATMLayout.setHorizontalGroup(
                PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillATMLayout.createSequentialGroup()
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jPanelhienIBill2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillATMLayout.createSequentialGroup()
                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                                .addGap(61, 61, 61)
                                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(NgayXuatBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(MaBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(HinhThuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(ThanhToanTienMat1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(TongGiamGiaMon1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(TongGiamGiaMon3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(46, 46, 46)
                                                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                .addComponent(TongTienInBill1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(TongThanhTienInBill2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                                                                .addComponent(GiaDiscount1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(transitFee, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                                .addGap(0, 13, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillATMLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(DiaChi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(26, 26, 26))
                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                .addGap(0, 67, Short.MAX_VALUE)
                                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                                .addComponent(TENQUAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(52, 52, 52))
                                                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                                                .addComponent(tenInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabelThoatInBill2)
                                                                .addContainerGap())))
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(powered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PrintBillATMLayout.setVerticalGroup(
                PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tenInBill2)
                                        .addComponent(jLabelThoatInBill2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TENQUAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MaBill2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NgayXuatBill2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(HinhThuc1)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelhienIBill2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(TongThanhTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TongGiamGiaMon1)
                                        .addComponent(GiaDiscount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TongGiamGiaMon3)
                                        .addComponent(transitFee, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel38)
                                        .addComponent(TongTienInBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ThanhToanTienMat1)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(powered))
        );
        if (transitFee1 != 0) {
            TongGiamGiaMon3.setVisible(true);
            transitFee.setVisible(true);
            transitFee.setText(String.valueOf(new DecimalFormat("#,###").format(transitFee1)));
            TongThanhTienInBill2.setText(String.valueOf(new DecimalFormat("#,###").format(priceDis - 16000)));
        } else {
            TongThanhTienInBill2.setText(String.valueOf(new DecimalFormat("#,###").format(priceDis)));
        }
        TongTienInBill1.setText(String.valueOf(new DecimalFormat("#,###").format(tongTien)));
        GiaDiscount1.setText("-" + String.valueOf(new DecimalFormat("#,###").format(priceDis * (float) discountBill / 100)));

        jLabelThoatInBill2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inBill.dispose();
                Items.clear();
                IdToppings.clear();
                HandleBills.ItemBills.clear();
                jPanelhienIBill2.removeAll();
                tongThanhToan = 0;
                demslitem = Items.size();
                System.out.println(demslitem);
                if (demslitem == 0) {
                    jlabelSLTrongGioHang.setVisible(false);
                }
                discount = 0;
                thanhTien = 0;
                sloneLine = 0;
            }
        });
        jPanelhienIBill2.setLayout(new BoxLayout(jPanelhienIBill2, BoxLayout.Y_AXIS));
        JPanel billpanel = new JPanel();
        billpanel.setLayout(new BoxLayout(billpanel, BoxLayout.Y_AXIS));
        OneLineBill lineBill = new OneLineBill();
        lineBill.getThem().setText("TÊN");
        lineBill.getSoluong().setText("SL");
        lineBill.getGia().setText("Đ.GIÁ");
        lineBill.getThanhTien().setText("T.TIỀN");
        billpanel.add(lineBill);
        billpanel.add(Box.createVerticalStrut(2));
//        jScrollPaneInBill.setViewportView(lineBill);
        sloneLine++;
        for (int i = 0; i < HandleBills.ItemBills.size(); i++) {
            Map<String, Object> itemb = HandleBills.ItemBills.get(i);
            OneLineBill lbit = new OneLineBill();
            int gia = (int) itemb.get("priceProduct");
            int sl = (int) itemb.get("quantityProduct");
            int tt = sl * (gia);
            lbit.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tt)));
            lbit.getThem().setText((String) itemb.get("nameProduct"));
            lbit.getSoluong().setText(String.valueOf(sl));
            lbit.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(gia)));
            billpanel.add(lbit);
            sloneLine++;

//            jScrollPaneInBill.setViewportView(lbit);
            List<Map<String, Object>> toppings = (List<Map<String, Object>>) itemb.get("Toppings");
            for (Map<String, Object> topping : toppings) {
                String nameTopping = (String) topping.get("nameTopping");
                int quantityTopping = (int) topping.get("quantityTopping");
                int priceTopping = (int) topping.get("priceTopping");
                int tttop = quantityTopping * priceTopping;
                if (quantityTopping > 0) {
                    OneLineBill lbtp = new OneLineBill();
                    lbtp.getThem().setText(" +" + nameTopping);
                    lbtp.getSoluong().setText(String.valueOf(quantityTopping));
                    lbtp.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(priceTopping)));
                    lbtp.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tttop)));
                    billpanel.add(lbtp);
                    sloneLine++;
//                    jScrollPaneInBill.setViewportView(lbtp);
                }
            }

            billpanel.add(Box.createVerticalStrut(1));
        }
        jPanelhienIBill2.add(billpanel);
    }

    void xuatHD(int idBill) {
        // Xóa nội dung cũ của PrintBill (nếu có)
        if (PrintBill.getComponentCount() > 0) {
            PrintBill.removeAll();
        }
        inBill = new JDialog(); // Khởi tạo lại khung inBill mỗi lần
        inBill.setUndecorated(true);
        inBill.setResizable(false);
        inBill.setLocationRelativeTo(null);
        inBill.setAlwaysOnTop(true);
        inBill.setBounds(490, 30, 520, 750);

        JScrollPane jScrollPane = new JScrollPane();
        setScroll(jScrollPane);
        jScrollPane.setViewportView(PrintBill);

        inBill.add(jScrollPane);
        inBill.setVisible(true);

        setPrintBill(idBill); // Thực hiện thiết lập lại nội dung mới cho PrintBill
        tienMatNhan = 0;
    }

    void setPrintBill(int idBill) {
        if (giohang1.isVisible()) {
            giohang1.dispose();
        }

        String newdateBill = HandleBills.getDateOrderByIdBill(idBill);
        int tongTien = HandleBills.getTotalpriceByIdBill(idBill);
        int idVoucher = HandleBills.getIdVoucherByIdBill(idBill);
        int discountBill = HandleBills.getPercentDiscountByIdVoucher(idVoucher);

        float priceDis = ((float) tongTien) * 100 / (100 - discountBill);

        HandleBills.getBill(idBill);
        String hinhThuc = "Tại quán";
        if (HandleBills.getIdCustomerByIdBill(idBill) != 0) {
            hinhThuc = "OnLine";
        }

        PrintBill.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill.setText("HÓA ĐƠN THANH TOÁN");

        MaBill.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill.setForeground(new java.awt.Color(0, 0, 0));
        MaBill.setText("MÃ HÓA ĐƠN: " + idBill);

        NgayXuatBill.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill.setText("NGÀY: " + newdateBill);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("THÀNH TIỀN:");

        jLabelThoatInBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        ThanhToanTienMat.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ThanhToanTienMat.setForeground(new java.awt.Color(0, 0, 0));
        ThanhToanTienMat.setText("+Thanh toán tiền mặt:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tiền nhận:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tiền thừa:");

        TienNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TienNhan.setForeground(new java.awt.Color(0, 0, 0));
        TienNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        TienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TienThua.setForeground(new java.awt.Color(0, 0, 0));
        TienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Hoặc sử dụng QR bên dưới");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("QR đa năng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Thanh toán qua mọi ứng dụng");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qr_thanhtoan.jpg"))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Mã QR chỉ tồn tại trong 60 phút");

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Cảm ơn & Hẹn gặp lại!");

        TongThanhTienInBill.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBillLayout = new javax.swing.GroupLayout(jPanelhienIBill);
        jPanelhienIBill.setLayout(jPanelhienIBillLayout);
        jPanelhienIBillLayout.setHorizontalGroup(
                jPanelhienIBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBillLayout.setVerticalGroup(
                jPanelhienIBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 32, Short.MAX_VALUE)
        );

        TENQUAN.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN.setText("TRÀ SỮA TASU");

        DiaChi.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon.setText("Tổng giảm giá món:");

        GiaDiscount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("TỔNG TIỀN:");

        TongTienInBill.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        poweredby.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        poweredby.setForeground(new java.awt.Color(0, 0, 0));
        poweredby.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poweredby.setText("powered by n21dcat037.com");

        HinhThuc.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc.setText("HÌNH THỨC: " + hinhThuc);

        javax.swing.GroupLayout PrintBillLayout = new javax.swing.GroupLayout(PrintBill);
        PrintBill.setLayout(PrintBillLayout);
        PrintBillLayout.setHorizontalGroup(
                PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PrintBillLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator3))
                        .addGroup(PrintBillLayout.createSequentialGroup()
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(TENQUAN, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                                                .addComponent(tenInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(52, 52, 52)
                                                                                .addComponent(jLabelThoatInBill))))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillLayout.createSequentialGroup()
                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillLayout.createSequentialGroup()
                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                                                .addGap(61, 61, 61)
                                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(NgayXuatBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(MaBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(HinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                                                .addGap(58, 58, 58)
                                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(ThanhToanTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(TongGiamGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(46, 46, 46)
                                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(TongTienInBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(TienNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(TienThua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(TongThanhTienInBill, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                                                        .addComponent(GiaDiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(0, 13, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillLayout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addComponent(DiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                                .addGap(0, 45, Short.MAX_VALUE)
                                                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jPanelhienIBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(20, 20, 20))
                                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(poweredby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        PrintBillLayout.setVerticalGroup(
                PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PrintBillLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelThoatInBill)
                                        .addComponent(tenInBill))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TENQUAN, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MaBill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NgayXuatBill)
                                .addGap(8, 8, 8)
                                .addComponent(HinhThuc)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelhienIBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(TongThanhTienInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(GiaDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TongGiamGiaMon))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(TongTienInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(ThanhToanTienMat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel23))
                                        .addGroup(PrintBillLayout.createSequentialGroup()
                                                .addComponent(TienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poweredby))
        );
        TongThanhTienInBill.setText(String.valueOf(new DecimalFormat("#,###").format(priceDis)));
        TongTienInBill.setText(String.valueOf(new DecimalFormat("#,###").format(tongTien)));
        TienThua.setText(String.valueOf(new DecimalFormat("#,###").format((float) tienMatNhan - tongTien)));
        TienNhan.setText(String.valueOf(new DecimalFormat("#,###").format(tienMatNhan)));
        GiaDiscount.setText("-" + String.valueOf(new DecimalFormat("#,###").format(priceDis * (float) discountBill / 100)));

        jLabelThoatInBill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inBill.dispose();
                Items.clear();
                IdToppings.clear();
                HandleBills.ItemBills.clear();
                jPanelhienIBill.removeAll();
                tongThanhToan = 0;
                demslitem = Items.size();
                System.out.println(demslitem);
                if (demslitem == 0) {
                    jlabelSLTrongGioHang.setVisible(false);
                }
                discount = 0;
                thanhTien = 0;
                sloneLine = 0;
            }
        });
        jPanelhienIBill.setLayout(new BoxLayout(jPanelhienIBill, BoxLayout.Y_AXIS));
        JPanel billpanel = new JPanel();
        billpanel.setLayout(new BoxLayout(billpanel, BoxLayout.Y_AXIS));
        OneLineBill lineBill = new OneLineBill();
        lineBill.getThem().setText("TÊN");
        lineBill.getSoluong().setText("SL");
        lineBill.getGia().setText("Đ.GIÁ");
        lineBill.getThanhTien().setText("T.TIỀN");
        billpanel.add(lineBill);
        billpanel.add(Box.createVerticalStrut(2));
//        jScrollPaneInBill.setViewportView(lineBill);
        sloneLine++;
        for (int i = 0; i < HandleBills.ItemBills.size(); i++) {
            Map<String, Object> itemb = HandleBills.ItemBills.get(i);
            OneLineBill lbit = new OneLineBill();
            int gia = (int) itemb.get("priceProduct");
            int sl = (int) itemb.get("quantityProduct");
            int tt = sl * (gia);
            lbit.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tt)));
            lbit.getThem().setText((String) itemb.get("nameProduct"));
            lbit.getSoluong().setText(String.valueOf(sl));
            lbit.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(gia)));
            billpanel.add(lbit);
            sloneLine++;

//            jScrollPaneInBill.setViewportView(lbit);
            List<Map<String, Object>> toppings = (List<Map<String, Object>>) itemb.get("Toppings");
            for (Map<String, Object> topping : toppings) {
                String nameTopping = (String) topping.get("nameTopping");
                int quantityTopping = (int) topping.get("quantityTopping");
                int priceTopping = (int) topping.get("priceTopping");
                int tttop = quantityTopping * priceTopping;
                if (quantityTopping > 0) {
                    OneLineBill lbtp = new OneLineBill();
                    lbtp.getThem().setText(" +" + nameTopping);
                    lbtp.getSoluong().setText(String.valueOf(quantityTopping));
                    lbtp.getGia().setText(String.valueOf(new DecimalFormat("#,###").format(priceTopping)));
                    lbtp.getThanhTien().setText(String.valueOf(new DecimalFormat("#,###").format(tttop)));
                    billpanel.add(lbtp);
                    sloneLine++;
//                    jScrollPaneInBill.setViewportView(lbtp);
                }
            }

            billpanel.add(Box.createVerticalStrut(1));
        }
        jPanelhienIBill.add(billpanel);

    }

    void setReviewTopping() {
        JLabel tenChonLai = new JLabel();

        setScroll(jScrollPaneChonLaiTopping);
        JViewport jv = new JViewport();
        jScrollPaneChonLaiTopping.setViewport(jv);

        chonLaiTop.setBackground(new java.awt.Color(255, 255, 255));

        tenChonLai.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenChonLai.setForeground(new java.awt.Color(0, 102, 102));
        tenChonLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenChonLai.setText("XEM & CHỌN LẠI TOPPING");

        tenItemMoi.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        tenItemMoi.setForeground(new java.awt.Color(255, 255, 0));
        tenItemMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenItemMoi.setText(tenItemChonLaiTopping);

        OKChonLaiTopping.setBackground(new java.awt.Color(255, 255, 255));
        OKChonLaiTopping.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        OKChonLaiTopping.setForeground(new java.awt.Color(0, 204, 204));
        OKChonLaiTopping.setText("OK");
        OKChonLaiTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        X_thoatChonLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N
        X_thoatChonLai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonLaiTP.dispose();
            }
        });
        javax.swing.GroupLayout chonLaiToppingLayout = new javax.swing.GroupLayout(chonLaiTop);
        chonLaiTop.setLayout(chonLaiToppingLayout);
        chonLaiToppingLayout.setHorizontalGroup(
                chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                .addGroup(chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPaneChonLaiTopping))
                                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(tenItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chonLaiToppingLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(OKChonLaiTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(215, 215, 215))
                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(tenChonLai, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(X_thoatChonLai)
                                .addContainerGap())
        );
        chonLaiToppingLayout.setVerticalGroup(
                chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tenChonLai, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(chonLaiToppingLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(X_thoatChonLai)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tenItem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneChonLaiTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OKChonLaiTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    void setProductCart() {
        setScroll(jScrollPaneGioHang);
        JViewport jv = new JViewport();
        jScrollPaneGioHang.setViewport(jv);

        JLabel jLabel14 = new JLabel();
        JLabel jLabel15 = new JLabel();

        JLabel butX = new JLabel();

        hienGioHang.setBackground(new java.awt.Color(0, 102, 102));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("GIỎ HÀNG");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TỔNG TIỀN:");

        tongtienThanhToan.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tongtienThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        tongtienThanhToan.setText(String.valueOf(tongThanhToan));

        jButtonThanhToanGioHang.setBackground(new java.awt.Color(0, 102, 102));
        jButtonThanhToanGioHang.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jButtonThanhToanGioHang.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThanhToanGioHang.setText("THANH TOÁN ");

        butX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        javax.swing.GroupLayout giohangLayout = new javax.swing.GroupLayout(hienGioHang);
        hienGioHang.setLayout(giohangLayout);
        giohangLayout.setHorizontalGroup(
                giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneGioHang, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(giohangLayout.createSequentialGroup()
                                .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(giohangLayout.createSequentialGroup()
                                                .addGap(119, 119, 119)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tongtienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(giohangLayout.createSequentialGroup()
                                                .addGap(149, 149, 149)
                                                .addComponent(jButtonThanhToanGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 151, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, giohangLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, giohangLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(butX)))))
                                .addContainerGap())
        );
        giohangLayout.setVerticalGroup(
                giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(giohangLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(butX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tongtienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonThanhToanGioHang)
                                .addGap(23, 23, 23))
        );

        butX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                giohang1.dispose();
            }
        });

    }

    void setProductChoose(int indexP) {

        JPanel showProduct = new JPanel();

        JPanel jPanel3 = new JPanel();
        JLabel anhHienProduct = new JLabel();
        JLabel TenHienProduct = new JLabel();
        JLabel giaHienProduct = new JLabel();
//        JSpinner jSpinner1 = new JSpinner();
        JLabel jLabel12 = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JViewport jv = new JViewport();
        setScroll(jScrollPane1);
        jScrollPane1.setViewport(jv);
        JPanel pal = new JPanel();
        JLabel nutX = new JLabel();
        pal.setLayout(new BoxLayout(pal, BoxLayout.Y_AXIS));

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0); // Thiết lập giá trị tối thiểu là 0
        formatter.setMaximum(Integer.MAX_VALUE); // Giá trị tối đa là Integer.MAX_VALUE

        // Tạo một JFormattedTextField với NumberFormatter
        JFormattedTextField textField = new JFormattedTextField(formatter);
        textField.setColumns(10); // Thiết lập độ rộng của ô nhập liệu

        // Tạo một JSpinner với JFormattedTextField
        JSpinner jspinnerProduct = new JSpinner();
//        jspinnerProduct.setEditor(new JSpinner.DefaultEditor(jspinnerProduct));
        jspinnerProduct.setPreferredSize(new Dimension(100, 30));
        jspinnerProduct.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jspinnerProduct.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
//      
        getToppings();

        for (int i = 0; i < Toppings.size(); i++) {
            Map<String, Object> topping = Toppings.get(i);
            JPanel panelTop = new JPanel();
            JLabel anhTopping = new JLabel();
            JLabel TenToppingHien = new JLabel();
            JLabel giaToppingHien = new JLabel();

//            JSpinner jSpinner2 = new JSpinner();
            JSpinner jSpinner2 = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
//            jSpinner2.setEditor(new JSpinner.DefaultEditor(jSpinner2));
            jSpinner2.setPreferredSize(new Dimension(100, 30));

            jSpinner2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int sl = Integer.parseInt(jSpinner2.getValue().toString());
                    int idtp = Integer.parseInt((String) topping.get("idTopping"));
                    for (int j = 0; j < IdToppings.size(); j++) {

                        if ((int) IdToppings.get(j).get("idTopping") == idtp) {
                            if ((int) IdToppings.get(j).get("quantity") == 0) {
                                IdToppings.remove(j);
                            }
                            IdToppings.get(j).put("quantity", sl);
                            return;
                        }
                    }
                    Map<String, Object> IdTopping = new HashMap<>();
                    IdTopping.put("nameTopping", (String) topping.get("nameTopping"));
                    IdTopping.put("imageTopping", (String) topping.get("imageTopping"));
                    IdTopping.put("priceTopping", (int) topping.get("priceTopping"));
                    IdTopping.put("idTopping", idtp);
                    IdTopping.put("quantity", sl);

                    IdToppings.add(IdTopping);
//                    System.out.println("---------------------------------------");

                }
            });
            panelTop.setBackground(new java.awt.Color(204, 204, 204));

            anhTopping.setText("");

            anhTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) topping.get("imageTopping"))));
            anhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            TenToppingHien.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
            TenToppingHien.setForeground(new java.awt.Color(0, 0, 0));
            TenToppingHien.setText((String) topping.get("nameTopping"));

            giaToppingHien.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
            giaToppingHien.setForeground(new java.awt.Color(0, 102, 102));
            giaToppingHien.setText((String.valueOf(topping.get("priceTopping"))));

            jSpinner2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

            javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
            panelTop.setLayout(panelTopLayout);
            panelTopLayout.setHorizontalGroup(
                    panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTopLayout.createSequentialGroup()
                                    .addComponent(anhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TenToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(giaToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                    .addGap(35, 35, 35)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 62, Short.MAX_VALUE))
            );
            panelTopLayout.setVerticalGroup(
                    panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(anhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTopLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(TenToppingHien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                            .addComponent(giaToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            pal.add(panelTop);
            pal.add(Box.createVerticalStrut(6));
        }
        jScrollPane1.setViewportView(pal);

        showProduct.setBackground(new java.awt.Color(204, 204, 204));
        showProduct.setPreferredSize(new java.awt.Dimension(430, 168));
        showProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(435, 190));

        anhHienProduct.setForeground(new java.awt.Color(0, 0, 0));
        anhHienProduct.setText("");
        anhHienProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) Products.get(indexP).get("imageProduct"))));
        anhHienProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TenHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TenHienProduct.setForeground(new java.awt.Color(0, 102, 102));
        TenHienProduct.setText((String) Products.get(indexP).get("nameProduct"));

        giaHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        giaHienProduct.setForeground(new java.awt.Color(0, 102, 102));
        if (giaChon) {
            giaHienProduct.setText(String.valueOf(Products.get(indexP).get("priceM")) + "(M)");
        } else {
            giaHienProduct.setText(String.valueOf(Products.get(indexP).get("priceL")) + "(L)");
        }

        jspinnerProduct.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CHỌN TOPPING");

        xacNhanChon.setBackground(new java.awt.Color(204, 204, 204));
        xacNhanChon.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        xacNhanChon.setForeground(new java.awt.Color(0, 102, 102));
        xacNhanChon.setText("XÁC NHẬN");

        xacNhanChon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                productChoose.dispose();
//                System.out.println(jspinnerProduct.getValue());
                System.out.println(indexP);

                if ((int) jspinnerProduct.getValue() == 0) {
                    productChoose.dispose();
                    return;
                }
                Map<String, Object> ItemMap = new HashMap<>();

                ItemMap.put("idItemDetail", idItemDetail + 1);
                ItemMap.put("idProduct", Products.get(indexP).get("idProduct"));
                ItemMap.put("nameProduct", Products.get(indexP).get("nameProduct"));
                ItemMap.put("imageProduct", Products.get(indexP).get("imageProduct"));
                ItemMap.put("Topping", IdToppings);
                ItemMap.put("quantityProduct", jspinnerProduct.getValue());
                ItemMap.put("idSize", size1Choose);

                int tongTienTopping = 0;

                for (int i = 0; i < Items.size(); i++) {
                    Map<String, Object> ItemMap1 = Items.get(i);
                    if (ItemMap1.get("idProduct").equals(ItemMap.get("idProduct")) && ItemMap1.get("Topping").equals(ItemMap.get("Topping")) && ItemMap1.get("idSize").equals(ItemMap.get("idSize"))) {
                        int qt = (int) ItemMap1.get("quantityProduct") + (int) ItemMap.get("quantityProduct");
                        Items.get(i).put("quantityProduct", qt);
                        productChoose.dispose();
                        jspinnerProduct.setValue(0);
                        updateTongThanhToan2();
                        return;
                    }
                }
                List<Map<String, Object>> toppings = (List<Map<String, Object>>) ItemMap.get("Topping");
                for (Map<String, Object> topping : toppings) {
                    tongTienTopping += (int) topping.get("priceTopping") * (int) topping.get("quantity");
                }
                if (size1Choose == 2) {
                    ItemMap.put("price", ((int) Products.get(indexP).get("priceL") + tongTienTopping));
                    ItemMap.put("priceSize", ((int) Products.get(indexP).get("priceL")));

                } else if (size1Choose == 1) {
                    ItemMap.put("priceSize", ((int) Products.get(indexP).get("priceM")));
                    ItemMap.put("price", ((int) Products.get(indexP).get("priceM") + tongTienTopping));

                }
                System.out.println("djjdoo" + ItemMap.get("price"));
                Items.add(ItemMap);
                idItemDetail++;
                productChoose.dispose();
                size1Choose = 1;

                if (Items.size() <= 0) {
                    jlabelSLTrongGioHang.setVisible(true);
                    jlabelSLTrongGioHang.setText(Integer.toString(1));
                }

                demslitem += 1;
                jlabelSLTrongGioHang.setVisible(true);
                jlabelSLTrongGioHang.setText(Integer.toString(demslitem));
                jspinnerProduct.setValue(0);
//                System.out.println(Items);
//                  int soluongProduct=(int) jspinnerProduct.getValue();
////                  System.out.println(soluongProduct);
            }
        });
        nutX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productChoose.dispose();
                jspinnerProduct.setValue(0);
            }
        });

        nutX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(anhHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(giaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jspinnerProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nutX)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1))))
                                .addContainerGap())
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(xacNhanChon, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(anhHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(nutX)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(giaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jspinnerProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xacNhanChon)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout showProductLayout = new javax.swing.GroupLayout(showProduct);
        showProduct.setLayout(showProductLayout);
        showProductLayout.setHorizontalGroup(
                showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showProductLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
        );
        showProductLayout.setVerticalGroup(
                showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showProductLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );

        productChoose.setUndecorated(true);
        productChoose.setAlwaysOnTop(true);
        productChoose.add(showProduct);
        //ten.setLocationRelativeTo(null);
        productChoose.setBounds(460, 100, 590, 600);

    }

    void setProduct(int idCategory) {
        if (idCategory != 0) {
            getProducts("", idCategory);
        } else {
            getProducts("");
        }

        JViewport jv = new JViewport();
        allProduct.setViewport(jv);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < Products.size(); i++) {
            Map<String, Object> Product = Products.get(i);

            JLabel anhHienProduct = new JLabel();
            anhHienProduct.setName((String) Product.get("idProduct"));
            JLabel TenHienProduct = new JLabel();
            JLabel moTaHienProduct = new JLabel();
            JLabel giaHienProduct = new JLabel();
            JRadioButton sizeMHienProduct = new JRadioButton();
            JRadioButton sizeLHienProduct = new JRadioButton();
            ButtonGroup group = new ButtonGroup();
            sizeMHienProduct.setSelected(true);
            group.add(sizeMHienProduct);
            group.add(sizeLHienProduct);
            JPanel showProduct = new JPanel();
            JPanel jPanel3 = new JPanel();
            jPanel3.setName(String.valueOf(i));
            jPanel3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    IdToppings = new ArrayList<>();
                    String giaC = giaHienProduct.getText();
//                    JRadioButton jr1 =sizeMHienProduct;
//                     giaChon=true;
                    if (productChoose.isVisible()) {
                        productChoose.dispose();
                    }
                    productChoose = new JDialog();
                    if (sizeMHienProduct.isSelected()) {
                        size1Choose = 1;
                        giaChon = true;
                    } else {
                        size1Choose = 2;
                        giaChon = false;
                    }
                    setProductChoose(Integer.parseInt(jPanel3.getName()));
//                    sizeMHienProduct.setSelected(true);
                    if (!productChoose.isVisible()) {
                        productChoose.setVisible(true);
                    }
//                    

                }
            });

            showProduct.setBackground(new java.awt.Color(221, 221, 221));
            showProduct.setPreferredSize(new java.awt.Dimension(400, 168));

            jPanel3.setBackground(new java.awt.Color(204, 204, 204));
            jPanel3.setPreferredSize(new java.awt.Dimension(400, 168));

            anhHienProduct.setForeground(new java.awt.Color(0, 0, 0));
            anhHienProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) Product.get("imageProduct")))); // NOI18N
            anhHienProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            TenHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
            TenHienProduct.setForeground(new java.awt.Color(0, 102, 102));
            TenHienProduct.setText((String) Product.get("nameProduct"));

            moTaHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
            moTaHienProduct.setForeground(new java.awt.Color(102, 102, 102));
            moTaHienProduct.setText((String) Product.get("description"));

            giaHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
            giaHienProduct.setForeground(new java.awt.Color(0, 102, 102));
            giaHienProduct.setText(String.valueOf(Product.get("priceM")) + " VND");

            sizeMHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
            sizeMHienProduct.setForeground(new java.awt.Color(0, 0, 0));
            sizeMHienProduct.setText("Size M");

            sizeLHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
            sizeLHienProduct.setForeground(new java.awt.Color(0, 0, 0));
            sizeLHienProduct.setBackground(new java.awt.Color(204, 204, 204));
            sizeMHienProduct.setBackground(new java.awt.Color(204, 204, 204));
            if ((int) Product.get("priceL") == 0) {
                sizeLHienProduct.setVisible(false);
            }
            sizeLHienProduct.setText("Size L");

            String priceMstr = String.valueOf(Product.get("priceM")) + " VND";
            String priceLstr = String.valueOf(Product.get("priceL") + " VND");

            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    JRadioButton jr = sizeMHienProduct;
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (selectedButton == jr) {
                            giaHienProduct.setText(priceMstr);
                            jr = selectedButton;
//                            size1Choose = 1;
                            return;
                        } else {
                            giaHienProduct.setText(priceLstr);
                            jr = selectedButton;

                            return;
                        }
                    }
                }
            };

            sizeMHienProduct.addItemListener(itemListener);
            sizeLHienProduct.addItemListener(itemListener);

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(giaHienProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(anhHienProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(moTaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(sizeMHienProduct)
                                                    .addGap(49, 49, 49)
                                                    .addComponent(sizeLHienProduct)))
                                    .addContainerGap(15, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(anhHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(moTaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(sizeMHienProduct)
                                                            .addComponent(sizeLHienProduct))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(giaHienProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            i++;
            JPanel jPanel5 = new JPanel();
            if (i == Products.size()) {
                jPanel5.setVisible(false);
                i--;
            }
            Product = Products.get(i);

            JLabel anhHienProduct1 = new JLabel();
            anhHienProduct1.setName((String) Product.get("idProduct"));
            JLabel TenHienProduct1 = new JLabel();
            JLabel moTaHienProduct1 = new JLabel();
            JLabel giaHienProduct1 = new JLabel();
            JRadioButton sizeMHienProduct1 = new JRadioButton();
            JRadioButton sizeLHienProduct1 = new JRadioButton();
            ButtonGroup group1 = new ButtonGroup();
            sizeMHienProduct1.setSelected(true);
            group1.add(sizeMHienProduct1);
            group1.add(sizeLHienProduct1);

            jPanel5.setBackground(new java.awt.Color(204, 204, 204));
            jPanel5.setPreferredSize(new java.awt.Dimension(435, 190));
            jPanel5.setName(String.valueOf(i));
            jPanel5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    IdToppings = new ArrayList<>();
                    String giaC = giaHienProduct.getText();
//                    JRadioButton jr1 =sizeMHienProduct;
//                     giaChon=true;
                    if (productChoose.isVisible()) {
                        productChoose.dispose();
                    }
                    productChoose = new JDialog();
                    if (sizeMHienProduct1.isSelected()) {
                        size1Choose = 1;
                        giaChon = true;
                    } else {
                        size1Choose = 2;
                        giaChon = false;
                    }
                    setProductChoose(Integer.parseInt(jPanel5.getName()));
//                    sizeMHienProduct1.setSelected(true);
                    if (!productChoose.isVisible()) {
                        productChoose.setVisible(true);
                    }

                }
            });

            anhHienProduct1.setForeground(new java.awt.Color(0, 0, 0));
            anhHienProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) Product.get("imageProduct")))); // NOI18N

            anhHienProduct1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            TenHienProduct1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
            TenHienProduct1.setForeground(new java.awt.Color(0, 102, 102));
            TenHienProduct1.setText((String) Product.get("nameProduct"));

            moTaHienProduct1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
            moTaHienProduct1.setForeground(new java.awt.Color(102, 102, 102));
            moTaHienProduct1.setText((String) Product.get("description"));

            giaHienProduct1.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
            giaHienProduct1.setForeground(new java.awt.Color(0, 102, 102));
            giaHienProduct1.setText(String.valueOf(Product.get("priceM")) + " VND");

            sizeMHienProduct1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
            sizeMHienProduct1.setForeground(new java.awt.Color(0, 0, 0));
            sizeMHienProduct1.setText("Size M");

            sizeLHienProduct1.setBackground(new java.awt.Color(204, 204, 204));
            sizeMHienProduct1.setBackground(new java.awt.Color(204, 204, 204));

            sizeLHienProduct1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
            sizeLHienProduct1.setForeground(new java.awt.Color(0, 0, 0));
            if ((int) Product.get("priceL") == 0) {
                sizeLHienProduct1.setVisible(false);
            }
            sizeLHienProduct1.setText("Size L");

            String priceMstr1 = String.valueOf(Product.get("priceM")) + " VND";
            String priceLstr1 = String.valueOf(Product.get("priceL")) + " VND";

            ItemListener itemListener1 = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    JRadioButton jr = sizeMHienProduct1;
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (selectedButton == jr) {
                            giaHienProduct1.setText(priceMstr1);
//                            size1Choose = 1;
                            return;
                        } else {
                            giaHienProduct1.setText(priceLstr1);
//                            size1Choose = 2;
                            return;
                        }
                    }
                }
            };

            sizeMHienProduct1.addItemListener(itemListener1);
            sizeLHienProduct1.addItemListener(itemListener1);

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(giaHienProduct1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(anhHienProduct1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TenHienProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(moTaHienProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(sizeMHienProduct1)
                                                    .addGap(49, 49, 49)
                                                    .addComponent(sizeLHienProduct1)))
                                    .addContainerGap(15, Short.MAX_VALUE))
            );
            jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(anhHienProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(TenHienProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(moTaHienProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(sizeMHienProduct1)
                                                            .addComponent(sizeLHienProduct1))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(giaHienProduct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );

            javax.swing.GroupLayout showProductLayout = new javax.swing.GroupLayout(showProduct);
            showProduct.setLayout(showProductLayout);
            showProductLayout.setHorizontalGroup(
                    showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(showProductLayout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            showProductLayout.setVerticalGroup(
                    showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
            );

            panel.add(showProduct);
            panel.add(Box.createVerticalStrut(6));

        }
        if (Products.size() < 6) {
            JPanel pn = new JPanel();
            pn.setBackground(new java.awt.Color(204, 204, 204));
            pn.setPreferredSize(new java.awt.Dimension(400, 336));

            for (int j = 0; j < 6 - Products.size(); j++) {
                panel.add(pn);

            }
        }
//        JPanel pn = new JPanel();
//        pn.setBackground(new java.awt.Color(221, 221, 221));
//        pn.setPreferredSize(new java.awt.Dimension(400, 336));
//        panel.add(pn);
        allProduct.setViewportView(panel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    void openLoginForm() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        }).start();
    }

    void openRegisterForm() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Register().setVisible(true);
            }
        }).start();
    }

    public void FunctionDisplay() {
        if (!fullNameText.getText().equals((String) ManagerEmployee.get("fullName"))) {
            showButtonInInfo();
            fullNameText.setText((String) ManagerEmployee.get("fullName"));
            fullNameInfo.setText("Họ và tên: ");
            jTextFieldInfoName.setText((String) ManagerEmployee.get("fullName"));
            jTextFieldInfoName.setEditable(false);
            birthdayInfo.setText("Ngày sinh: ");
            jTextFieldInfoBirthDate.setText((String) ManagerEmployee.get("birthday"));
            jTextFieldInfoBirthDate.setEditable(false);
            Integer gend = (Integer) ManagerEmployee.get("gender");
            if (gend != null) {
                // Thực hiện các thao tác khi idAccount không null
                int id = gend.intValue();
                if (gend == 1) {
                    genderInfo.setText("Giới tính: ");
                    jTextFieldInfoGender.setText("Nam");
                    jTextFieldInfoGender.setEditable(false);
                } else if (gend == 0) {
                    genderInfo.setText("Giới tính: ");
                    jTextFieldInfoGender.setText("Nữ");
                    jTextFieldInfoGender.setEditable(false);
                } else if (gend == 2) {
                    genderInfo.setText("Giới tính: ");
                    jTextFieldInfoGender.setText("Khác");
                    jTextFieldInfoGender.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra với giới tính!!");
                }
            } else {
                // Xử lý khi idAccount là null
                JOptionPane.showMessageDialog(this, "Bạn cần đăng nhập trước khi thao tác!!!");
            }

            if ((int) ManagerEmployee.get("gender") == 1) {
                genderInfo.setText("Giới tính: ");
                jTextFieldInfoGender.setText("Nam");
                jTextFieldInfoGender.setEditable(false);
            } else if ((int) ManagerEmployee.get("gender") == 2) {
                genderInfo.setText("Giới tính: ");
                jTextFieldInfoGender.setText("Nữ");
                jTextFieldInfoGender.setEditable(false);
            } else if ((int) ManagerEmployee.get("gender") == 3) {
                genderInfo.setText("Giới tính: ");
                jTextFieldInfoGender.setText("Khác");
                jTextFieldInfoGender.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần đăng nhập trước khi thao tác!!!");
            }

            cccdInfo.setText("CCCD: ");
            jTextFieldInfoCCCD.setText((String) ManagerEmployee.get("cccd"));
            jTextFieldInfoCCCD.setEditable(false);

            addressInfo.setText("Quê quán: ");
            jTextAreaAddress.setText((String) ManagerEmployee.get("address"));
            jTextAreaAddress.setEditable(false);
            jTextAreaAddress.setEditable(false);
            jTextAreaAddress.setLineWrap(true); // Tự động xuống dòng
            jTextAreaAddress.setWrapStyleWord(true); // Xuống dòng dựa trên từ
//            
            long salary = (long) ManagerEmployee.get("salary");
            if (salary == -1) {
                JOptionPane.showMessageDialog(this, "Bạn cần đăng nhập trước khi thao tác!!!");
            } else {
                jTextFieldInfoSalary.setText(String.valueOf(new DecimalFormat("#,###").format(salary)) + "VND");
                jTextFieldInfoSalary.setEditable(false);
            }

            permissionInfo.setText("Chức vụ: ");
            jTextFieldInfoRole.setText((String) ManagerEmployee.get("permission"));
            jTextFieldInfoRole.setEditable(false);

//            permissionInfo.setText("Chức vụ: " );
            jTextFieldInfoPhone.setText((String) ManagerEmployee.get("numberPhone"));
            jTextFieldInfoPhone.setEditable(false);

            String urlimg = (String) ManagerEmployee.get("avatar");
            java.net.URL imageURL = null;

            if (urlimg != null && !urlimg.isEmpty()) {
                imageURL = getClass().getResource(urlimg);
                if (imageURL != null) {
                    ImageIcon imageIcon = new ImageIcon(imageURL);
                    ImageIcon imageIconLoad = new ImageIcon("src/main/resources/icons/arrow_64px.png");
                    pic.setIcon(imageIcon);
                    focusJLableImage(pic, imageIconLoad, imageIcon);
                    focusToChangeAvatar(pic, (int) ManagerEmployee.get("idAccount"), imageIconLoad);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể tìm thấy hình ảnh đại diện!!");
                    ImageIcon imageIcon = new ImageIcon("");
                    ImageIcon imageIconLoad = new ImageIcon("src/main/resources/icons/arrow_64px.png");
                    pic.setIcon(imageIcon);
                    focusJLableImage(pic, imageIconLoad, imageIcon);
                    focusToChangeAvatar(pic, (int) ManagerEmployee.get("idAccount"), imageIconLoad);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tìm thấy hình ảnh đại diện!!");
                ImageIcon imageIcon = new ImageIcon("");
                ImageIcon imageIconLoad = new ImageIcon("src/main/resources/icons/arrow_64px.png");
                pic.setIcon(imageIcon);
                focusJLableImage(pic, imageIconLoad, imageIcon);
                focusToChangeAvatar(pic, (int) ManagerEmployee.get("idAccount"), imageIconLoad);
            }

        }
        this.func.removeAll();
        this.func.add(this.logoutBtn);
        this.func.repaint();
        this.func.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showProduct = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        anhHienProduct = new javax.swing.JLabel();
        TenHienProduct = new javax.swing.JLabel();
        giaHienProduct = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel13 = new javax.swing.JLabel();
        panelTop = new javax.swing.JPanel();
        anhTopping = new javax.swing.JLabel();
        TenToppingHien = new javax.swing.JLabel();
        giaToppingHien = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        giohang = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tongtien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPaneGioHang = new javax.swing.JScrollPane();
        butX = new javax.swing.JLabel();
        chonLaiTopping = new javax.swing.JPanel();
        tenChonLai = new javax.swing.JLabel();
        jScrollPaneChonLaiTopping = new javax.swing.JScrollPane();
        tenItem = new javax.swing.JLabel();
        OKChonLaiTopping = new javax.swing.JButton();
        X_thoatChonLai = new javax.swing.JLabel();
        PrintBill = new javax.swing.JPanel();
        tenInBill = new javax.swing.JLabel();
        MaBill = new javax.swing.JLabel();
        NgayXuatBill = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelThoatInBill = new javax.swing.JLabel();
        ThanhToanTienMat = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TienNhan = new javax.swing.JLabel();
        TienThua = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        TongThanhTienInBill = new javax.swing.JLabel();
        jPanelhienIBill = new javax.swing.JPanel();
        TENQUAN = new javax.swing.JLabel();
        DiaChi = new javax.swing.JLabel();
        TongGiamGiaMon = new javax.swing.JLabel();
        GiaDiscount = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TongTienInBill = new javax.swing.JLabel();
        poweredby = new javax.swing.JLabel();
        HinhThuc = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ChonPTTT = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        PTTT_ATM = new javax.swing.JRadioButton();
        PTTT_TM = new javax.swing.JRadioButton();
        NhapTienMat = new javax.swing.JTextField();
        bnt_XuatHD = new javax.swing.JButton();
        ThoatPTTT = new javax.swing.JLabel();
        jCheckBoxDiscount = new javax.swing.JCheckBox();
        NhapDiscount = new javax.swing.JTextField();
        buttonGroupPTTT = new javax.swing.ButtonGroup();
        PrintBillATM = new javax.swing.JPanel();
        tenInBill2 = new javax.swing.JLabel();
        MaBill2 = new javax.swing.JLabel();
        NgayXuatBill2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabelThoatInBill2 = new javax.swing.JLabel();
        ThanhToanTienMat1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        TongThanhTienInBill2 = new javax.swing.JLabel();
        jPanelhienIBill2 = new javax.swing.JPanel();
        TENQUAN1 = new javax.swing.JLabel();
        DiaChi1 = new javax.swing.JLabel();
        TongGiamGiaMon1 = new javax.swing.JLabel();
        GiaDiscount1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        TongTienInBill1 = new javax.swing.JLabel();
        powered = new javax.swing.JLabel();
        HinhThuc1 = new javax.swing.JLabel();
        TongGiamGiaMon3 = new javax.swing.JLabel();
        transitFee = new javax.swing.JLabel();
        ChitietHDO = new javax.swing.JPanel();
        tenInBill3 = new javax.swing.JLabel();
        MaBill3 = new javax.swing.JLabel();
        NgayXuatBill3 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabelThoatInBill3 = new javax.swing.JLabel();
        TrangThaiLabel = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        TongThanhTienInBill3 = new javax.swing.JLabel();
        jPanelhienIBill3 = new javax.swing.JPanel();
        TENQUAN2 = new javax.swing.JLabel();
        DiaChi2 = new javax.swing.JLabel();
        TongGiamGiaMon2 = new javax.swing.JLabel();
        GiaDiscount2 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        TongTienInBill2 = new javax.swing.JLabel();
        powered1 = new javax.swing.JLabel();
        TrangThai = new javax.swing.JLabel();
        HinhThuc3 = new javax.swing.JLabel();
        LiDoHuy = new javax.swing.JLabel();
        TongGiamGiaMon4 = new javax.swing.JLabel();
        transitFee1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanelNavbar = new javax.swing.JPanel();
        fullNameText = new javax.swing.JLabel();
        jButtonChoose = new javax.swing.JButton();
        jButtonBill = new javax.swing.JButton();
        jButtonInfoStaff = new javax.swing.JButton();
        func = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        jButtonManageProduct = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();
        jButtonManageCategory = new javax.swing.JButton();
        jLabelbuttoncloseMenuNavbar = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jPanelHome = new javax.swing.JPanel();
        jLabelHomeMenu = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        TBChoXacNhan2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanelBill = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelBillMenu = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPaneStatusBill = new javax.swing.JScrollPane();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        TBChoXacNhan1 = new javax.swing.JLabel();
        jPanelInfo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        fullNameInfo = new javax.swing.JLabel();
        cccdInfo = new javax.swing.JLabel();
        birthdayInfo = new javax.swing.JLabel();
        genderInfo = new javax.swing.JLabel();
        permissionInfo = new javax.swing.JLabel();
        jTextFieldInfoName = new javax.swing.JTextField();
        jTextFieldInfoBirthDate = new javax.swing.JTextField();
        jTextFieldInfoGender = new javax.swing.JTextField();
        jTextFieldInfoCCCD = new javax.swing.JTextField();
        jTextFieldInfoRole = new javax.swing.JTextField();
        jButtonInfoSuaTen = new javax.swing.JButton();
        jButtonInfoSuaGioTinh = new javax.swing.JButton();
        jButtonInfoSuaCCCD = new javax.swing.JButton();
        jButtonInfoNoSuaTen = new javax.swing.JButton();
        jButtonInfoYesSuaTen = new javax.swing.JButton();
        jButtonInfoYesSuaGioiTinh = new javax.swing.JButton();
        jButtonInfoNoSuaGioiTinh = new javax.swing.JButton();
        jButtonInfoNoSuaCCCD = new javax.swing.JButton();
        jButtonInfoYesSuaCCCD = new javax.swing.JButton();
        jButtonInfoSuaNgaySinh = new javax.swing.JButton();
        jButtonInfoNoSuaNgaySinh = new javax.swing.JButton();
        jButtonInfoYesSuaNgaySinh = new javax.swing.JButton();
        addressInfo = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaAddress = new javax.swing.JTextArea();
        jButtonInfoSuaDiaChi = new javax.swing.JButton();
        jButtonInfoNoSuaDiaChi = new javax.swing.JButton();
        jButtonInfoYesSuaDiaChi = new javax.swing.JButton();
        PhoneInfo = new javax.swing.JLabel();
        jTextFieldInfoPhone = new javax.swing.JTextField();
        salaryInfo = new javax.swing.JLabel();
        jTextFieldInfoSalary = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabelInfoMenu = new javax.swing.JLabel();
        TBChoXacNhan = new javax.swing.JLabel();
        jPanelManageProduct = new javax.swing.JPanel();
        jLabelQuanLiMon = new javax.swing.JLabel();
        jTabbedPaneManageProduct = new javax.swing.JTabbedPane();
        jPanelQuanLiTraSua = new javax.swing.JPanel();
        jPanelFunctionMilkTea = new javax.swing.JPanel();
        jButtonThemTraSua = new javax.swing.JButton();
        jButtonSuaTraSua = new javax.swing.JButton();
        jButtonXoaTraSua = new javax.swing.JButton();
        jButtonXemTraSua = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTS = new javax.swing.JTable();
        jTextFieldSearchInManageProduct = new javax.swing.JTextField();
        jButtonSearchInManageProduct = new javax.swing.JButton();
        jPanelXulyMilkTea = new javax.swing.JPanel();
        jPanelClear = new javax.swing.JPanel();
        jPanelSuaTraSua = new javax.swing.JPanel();
        jLabelSuaTS = new javax.swing.JLabel();
        jLabelSuaHinhAnh1 = new javax.swing.JLabel();
        jButtonSuaHinhAnh = new javax.swing.JButton();
        jLabelSuaTen = new javax.swing.JLabel();
        jTextFieldSuaTenMon = new javax.swing.JTextField();
        jLabelSuaGia = new javax.swing.JLabel();
        jTextFieldSuaGiaMon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabelMoTaGia = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaSuaMoTa = new javax.swing.JTextArea();
        jButtonHoanTatSua = new javax.swing.JButton();
        jButtonHuySua = new javax.swing.JButton();
        jLabelSuaLoai1 = new javax.swing.JLabel();
        jTextFieldSuaHinhAnh2 = new javax.swing.JTextField();
        jComboBoxSuaLoaiMon = new javax.swing.JComboBox<>();
        jPanelThemTraSua = new javax.swing.JPanel();
        jLabelThemTS = new javax.swing.JLabel();
        jLabelTenTraSua = new javax.swing.JLabel();
        jTextFieldTenTraSua = new javax.swing.JTextField();
        jLabelTenDoanhMuc = new javax.swing.JLabel();
        jComboBoxDoanhMuc = new javax.swing.JComboBox<>();
        jLabelThemHinhAnh = new javax.swing.JLabel();
        jButtonThemAnh = new javax.swing.JButton();
        jTextFieldThemHinhAnh = new javax.swing.JTextField();
        jLabelMoTa = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaMoTa = new javax.swing.JTextArea();
        jButtonHuyThem = new javax.swing.JButton();
        jButtonHoanTatThem = new javax.swing.JButton();
        jLabelGiaTraSua = new javax.swing.JLabel();
        jTextFieldGiaTraSuaSizeM = new javax.swing.JTextField();
        jTextFieldGiaTraSuaSizeL = new javax.swing.JTextField();
        jPanelXoaTraSua = new javax.swing.JPanel();
        jLabelXoaTS = new javax.swing.JLabel();
        jPanelXemTraSua = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelShowImage = new javax.swing.JLabel();
        jLabelXemTenThucUong1 = new javax.swing.JLabel();
        jLabelXemTenThucUong2 = new javax.swing.JLabel();
        jLabelXemLoaiThucUong1 = new javax.swing.JLabel();
        jLabelXemLoaiThucUong2 = new javax.swing.JLabel();
        jLabelXemGiaThucUong1 = new javax.swing.JLabel();
        jLabelXemGiaThucUong2 = new javax.swing.JLabel();
        jLabelXemMotaThucUong1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaXemMotaThucUong2 = new javax.swing.JTextArea();
        jPanelQuanLiTopping = new javax.swing.JPanel();
        jPanelFunctionTopping = new javax.swing.JPanel();
        jButtonThemTopping = new javax.swing.JButton();
        jButtonSuaTopping = new javax.swing.JButton();
        jButtonXoaTopping = new javax.swing.JButton();
        jButtonXemTopping = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableTP = new javax.swing.JTable();
        jTextFieldSearchInManagTopping = new javax.swing.JTextField();
        jButtonSearchInManageTopping = new javax.swing.JButton();
        jPanelXulyTopping = new javax.swing.JPanel();
        jPanelClear1 = new javax.swing.JPanel();
        jPanelThemTopping = new javax.swing.JPanel();
        jLabelThemTS1 = new javax.swing.JLabel();
        jLabelTenTopping = new javax.swing.JLabel();
        jTextFieldTenTopping = new javax.swing.JTextField();
        jLabelThemAnhTopping = new javax.swing.JLabel();
        jButtonThemAnhTopping = new javax.swing.JButton();
        jTextFieldThemAnhTopping = new javax.swing.JTextField();
        jButtonHuyThemTopping = new javax.swing.JButton();
        jButtonHoanTatThemTopping = new javax.swing.JButton();
        jLabelGiaTopping = new javax.swing.JLabel();
        jTextFieldGiaTopping = new javax.swing.JTextField();
        jPanelSuaTopping = new javax.swing.JPanel();
        jLabelSuaTS1 = new javax.swing.JLabel();
        jLabelSuaHinhAnhTopping = new javax.swing.JLabel();
        jButtonSuaHinhAnhToppinglogo = new javax.swing.JButton();
        jLabelSuaTenTopping = new javax.swing.JLabel();
        jTextFieldSuaTenTopping = new javax.swing.JTextField();
        jLabelSuaGiaTopping = new javax.swing.JLabel();
        jTextFieldSuaGiaTopping = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonHoanTatSuaTopping = new javax.swing.JButton();
        jButtonHuySuaTopping = new javax.swing.JButton();
        jTextFieldSuaHinhAnhTopping = new javax.swing.JTextField();
        jPanelXemTopping = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelShowImageTopping = new javax.swing.JLabel();
        jLabelXemTenTopping1 = new javax.swing.JLabel();
        jLabelXemTenTopping2 = new javax.swing.JLabel();
        jLabelXemGiaTopping1 = new javax.swing.JLabel();
        jLabelXemGiaTopping2 = new javax.swing.JLabel();
        jLabelManageProductMenu = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TBChoXacNhan4 = new javax.swing.JLabel();
        jPanelChoose = new javax.swing.JPanel();
        jLabelChooseMenu = new javax.swing.JLabel();
        allProduct = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        giohangchon = new javax.swing.JLabel();
        jcomboboxHientheoloai = new javax.swing.JComboBox<>();
        jlabelSLTrongGioHang = new javax.swing.JLabel();
        TBChoXacNhan3 = new javax.swing.JLabel();

        showProduct.setBackground(new java.awt.Color(255, 255, 255));
        showProduct.setPreferredSize(new java.awt.Dimension(430, 168));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(435, 190));

        anhHienProduct.setForeground(new java.awt.Color(0, 0, 0));
        anhHienProduct.setText("ảnh");
        anhHienProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TenHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TenHienProduct.setForeground(new java.awt.Color(0, 102, 102));
        TenHienProduct.setText("tên");

        giaHienProduct.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        giaHienProduct.setForeground(new java.awt.Color(0, 102, 102));
        giaHienProduct.setText("giá");

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CHỌN TOPPING");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(anhHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(giaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(anhHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TenHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(giaHienProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout showProductLayout = new javax.swing.GroupLayout(showProduct);
        showProduct.setLayout(showProductLayout);
        showProductLayout.setHorizontalGroup(
            showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
        );
        showProductLayout.setVerticalGroup(
            showProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
        );

        panelTop.setBackground(new java.awt.Color(204, 204, 204));

        anhTopping.setText("jLabel13");
        anhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TenToppingHien.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TenToppingHien.setForeground(new java.awt.Color(0, 0, 0));
        TenToppingHien.setText("jLabel14");

        giaToppingHien.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        giaToppingHien.setForeground(new java.awt.Color(0, 102, 102));
        giaToppingHien.setText("jLabel14");

        jSpinner2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addComponent(anhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TenToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(giaToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TenToppingHien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(giaToppingHien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        giohang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("GIỎ HÀNG");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("TỔNG TIỀN:");

        tongtien.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tongtien.setForeground(new java.awt.Color(0, 102, 102));
        tongtien.setText("jLabel16");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("THANH TOÁN ");

        butX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        javax.swing.GroupLayout giohangLayout = new javax.swing.GroupLayout(giohang);
        giohang.setLayout(giohangLayout);
        giohangLayout.setHorizontalGroup(
            giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneGioHang, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(giohangLayout.createSequentialGroup()
                .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(giohangLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(giohangLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 151, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, giohangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, giohangLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(butX)))))
                .addContainerGap())
        );
        giohangLayout.setVerticalGroup(
            giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giohangLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(butX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(giohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        chonLaiTopping.setBackground(new java.awt.Color(255, 255, 255));

        tenChonLai.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenChonLai.setForeground(new java.awt.Color(0, 102, 102));
        tenChonLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenChonLai.setText("XEM & CHỌN LẠI TOPPING");

        tenItem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        tenItem.setForeground(new java.awt.Color(0, 0, 0));
        tenItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenItem.setText("jLabel17");

        OKChonLaiTopping.setBackground(new java.awt.Color(255, 255, 255));
        OKChonLaiTopping.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        OKChonLaiTopping.setForeground(new java.awt.Color(0, 204, 204));
        OKChonLaiTopping.setText("OK");
        OKChonLaiTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        X_thoatChonLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        javax.swing.GroupLayout chonLaiToppingLayout = new javax.swing.GroupLayout(chonLaiTopping);
        chonLaiTopping.setLayout(chonLaiToppingLayout);
        chonLaiToppingLayout.setHorizontalGroup(
            chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chonLaiToppingLayout.createSequentialGroup()
                .addGroup(chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chonLaiToppingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneChonLaiTopping))
                    .addGroup(chonLaiToppingLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(tenItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chonLaiToppingLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(OKChonLaiTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
            .addGroup(chonLaiToppingLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(tenChonLai, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(X_thoatChonLai)
                .addContainerGap())
        );
        chonLaiToppingLayout.setVerticalGroup(
            chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chonLaiToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chonLaiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenChonLai, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(chonLaiToppingLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(X_thoatChonLai)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenItem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneChonLaiTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OKChonLaiTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        PrintBill.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill.setText("HÓA ĐƠN THANH TOÁN");

        MaBill.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill.setForeground(new java.awt.Color(0, 0, 0));
        MaBill.setText("MÃ HÓA ĐƠN:");

        NgayXuatBill.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill.setText("NGÀY:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("THÀNH TIỀN:");

        jLabelThoatInBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        ThanhToanTienMat.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ThanhToanTienMat.setForeground(new java.awt.Color(0, 0, 0));
        ThanhToanTienMat.setText("+Thanh toán tiền mặt:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tiền nhận:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tiền thừa:");

        TienNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TienNhan.setForeground(new java.awt.Color(0, 0, 0));
        TienNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        TienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TienThua.setForeground(new java.awt.Color(0, 0, 0));
        TienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Hoặc sử dụng QR bên dưới");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("QR đa năng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Thanh toán qua mọi ứng dụng");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qr_thanhtoan.jpg"))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Mã QR chỉ tồn tại trong 60 phút");

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Cảm ơn & Hẹn gặp lại!");

        TongThanhTienInBill.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBillLayout = new javax.swing.GroupLayout(jPanelhienIBill);
        jPanelhienIBill.setLayout(jPanelhienIBillLayout);
        jPanelhienIBillLayout.setHorizontalGroup(
            jPanelhienIBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBillLayout.setVerticalGroup(
            jPanelhienIBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        TENQUAN.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN.setText("TRÀ SỮA TASU");

        DiaChi.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon.setText("Tổng giảm giá món:");

        GiaDiscount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("TỔNG TIỀN:");

        TongTienInBill.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        poweredby.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        poweredby.setForeground(new java.awt.Color(0, 0, 0));
        poweredby.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poweredby.setText("powered by n21dcat037.com");

        HinhThuc.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc.setText("HÌNH THỨC:");

        javax.swing.GroupLayout PrintBillLayout = new javax.swing.GroupLayout(PrintBill);
        PrintBill.setLayout(PrintBillLayout);
        PrintBillLayout.setHorizontalGroup(
            PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintBillLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3))
            .addGroup(PrintBillLayout.createSequentialGroup()
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PrintBillLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TENQUAN, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PrintBillLayout.createSequentialGroup()
                                        .addComponent(tenInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabelThoatInBill))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillLayout.createSequentialGroup()
                        .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillLayout.createSequentialGroup()
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrintBillLayout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(NgayXuatBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(MaBill, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(HinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PrintBillLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ThanhToanTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TongGiamGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(46, 46, 46)
                                        .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TongTienInBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TienNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TienThua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TongThanhTienInBill, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                            .addComponent(GiaDiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 13, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(DiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PrintBillLayout.createSequentialGroup()
                                .addGap(0, 45, Short.MAX_VALUE)
                                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanelhienIBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20))
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(poweredby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PrintBillLayout.setVerticalGroup(
            PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintBillLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelThoatInBill)
                    .addComponent(tenInBill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TENQUAN, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MaBill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NgayXuatBill)
                .addGap(8, 8, 8)
                .addComponent(HinhThuc)
                .addGap(18, 18, 18)
                .addComponent(jPanelhienIBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(TongThanhTienInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GiaDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TongGiamGiaMon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(TongTienInBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ThanhToanTienMat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrintBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrintBillLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23))
                    .addGroup(PrintBillLayout.createSequentialGroup()
                        .addComponent(TienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poweredby))
        );

        ChonPTTT.setBackground(new java.awt.Color(255, 255, 255));
        ChonPTTT.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CHỌN PHƯƠNG THỨC THANH TOÁN");

        PTTT_ATM.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupPTTT.add(PTTT_ATM);
        PTTT_ATM.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        PTTT_ATM.setForeground(new java.awt.Color(0, 0, 0));
        PTTT_ATM.setText("THẺ ATM");

        PTTT_TM.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupPTTT.add(PTTT_TM);
        PTTT_TM.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        PTTT_TM.setForeground(new java.awt.Color(0, 0, 0));
        PTTT_TM.setText("TIỀN MẶT");

        NhapTienMat.setBackground(new java.awt.Color(255, 255, 255));
        NhapTienMat.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        NhapTienMat.setForeground(new java.awt.Color(0, 0, 0));
        NhapTienMat.setText("jTextField1");
        NhapTienMat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        bnt_XuatHD.setBackground(new java.awt.Color(255, 255, 255));
        bnt_XuatHD.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bnt_XuatHD.setForeground(new java.awt.Color(0, 0, 0));
        bnt_XuatHD.setText("XUẤT HÓA ĐƠN");
        bnt_XuatHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        ThoatPTTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        jCheckBoxDiscount.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxDiscount.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jCheckBoxDiscount.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBoxDiscount.setText("DISCOUNT(%):");

        NhapDiscount.setBackground(new java.awt.Color(255, 255, 255));
        NhapDiscount.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        NhapDiscount.setForeground(new java.awt.Color(0, 0, 0));
        NhapDiscount.setText("jTextField1");
        NhapDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout ChonPTTTLayout = new javax.swing.GroupLayout(ChonPTTT);
        ChonPTTT.setLayout(ChonPTTTLayout);
        ChonPTTTLayout.setHorizontalGroup(
            ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonPTTTLayout.createSequentialGroup()
                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChonPTTTLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PTTT_ATM, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ChonPTTTLayout.createSequentialGroup()
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PTTT_TM, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NhapDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NhapTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonPTTTLayout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ThoatPTTT)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChonPTTTLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bnt_XuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207))
        );
        ChonPTTTLayout.setVerticalGroup(
            ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChonPTTTLayout.createSequentialGroup()
                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThoatPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PTTT_ATM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PTTT_TM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ChonPTTTLayout.createSequentialGroup()
                        .addComponent(NhapTienMat)
                        .addGap(1, 1, 1)))
                .addGap(18, 18, 18)
                .addGroup(ChonPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxDiscount)
                    .addComponent(NhapDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(bnt_XuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        PrintBillATM.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill2.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill2.setText("HÓA ĐƠN THANH TOÁN");

        MaBill2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill2.setForeground(new java.awt.Color(0, 0, 0));
        MaBill2.setText("MÃ HÓA ĐƠN:");

        NgayXuatBill2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill2.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill2.setText("NGÀY:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("THÀNH TIỀN:");

        jLabelThoatInBill2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        ThanhToanTienMat1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ThanhToanTienMat1.setForeground(new java.awt.Color(0, 0, 0));
        ThanhToanTienMat1.setText("+Thanh toán thẻ(ATM):");

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Cảm ơn & Hẹn gặp lại!");

        TongThanhTienInBill2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill2.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill2.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBill2Layout = new javax.swing.GroupLayout(jPanelhienIBill2);
        jPanelhienIBill2.setLayout(jPanelhienIBill2Layout);
        jPanelhienIBill2Layout.setHorizontalGroup(
            jPanelhienIBill2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBill2Layout.setVerticalGroup(
            jPanelhienIBill2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        TENQUAN1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN1.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN1.setText("TRÀ SỮA TASU");

        DiaChi1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi1.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi1.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon1.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon1.setText("Tổng giảm giá món:");

        GiaDiscount1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount1.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("TỔNG TIỀN:");

        TongTienInBill1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill1.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        powered.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        powered.setForeground(new java.awt.Color(0, 0, 0));
        powered.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        powered.setText("powered by n21dcat037.com");

        HinhThuc1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc1.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc1.setText("HÌNH THỨC:");

        TongGiamGiaMon3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon3.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon3.setText("Phí vận chuyển");

        transitFee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        transitFee.setForeground(new java.awt.Color(0, 0, 0));
        transitFee.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout PrintBillATMLayout = new javax.swing.GroupLayout(PrintBillATM);
        PrintBillATM.setLayout(PrintBillATMLayout);
        PrintBillATMLayout.setHorizontalGroup(
            PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintBillATMLayout.createSequentialGroup()
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PrintBillATMLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelhienIBill2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillATMLayout.createSequentialGroup()
                        .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NgayXuatBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MaBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HinhThuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PrintBillATMLayout.createSequentialGroup()
                                        .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ThanhToanTienMat1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TongGiamGiaMon1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TongGiamGiaMon3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(46, 46, 46)
                                        .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(TongTienInBill1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(TongThanhTienInBill2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                .addComponent(GiaDiscount1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(transitFee, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrintBillATMLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(DiaChi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addGroup(PrintBillATMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrintBillATMLayout.createSequentialGroup()
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addComponent(TENQUAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(PrintBillATMLayout.createSequentialGroup()
                                .addComponent(tenInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelThoatInBill2)
                                .addContainerGap())))
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(powered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PrintBillATMLayout.setVerticalGroup(
            PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintBillATMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenInBill2)
                    .addComponent(jLabelThoatInBill2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TENQUAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MaBill2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NgayXuatBill2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(HinhThuc1)
                .addGap(18, 18, 18)
                .addComponent(jPanelhienIBill2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(TongThanhTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TongGiamGiaMon1)
                    .addComponent(GiaDiscount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TongGiamGiaMon3)
                    .addComponent(transitFee, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(PrintBillATMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(TongTienInBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ThanhToanTienMat1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(powered))
        );

        ChitietHDO.setBackground(new java.awt.Color(255, 255, 255));

        tenInBill3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tenInBill3.setForeground(new java.awt.Color(0, 0, 0));
        tenInBill3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenInBill3.setText("CHI TIẾT HÓA ĐƠN");

        MaBill3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MaBill3.setForeground(new java.awt.Color(0, 0, 0));
        MaBill3.setText("MÃ HÓA ĐƠN:");

        NgayXuatBill3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        NgayXuatBill3.setForeground(new java.awt.Color(0, 0, 0));
        NgayXuatBill3.setText("NGÀY:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("THÀNH TIỀN:");

        jLabelThoatInBill3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N

        TrangThaiLabel.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TrangThaiLabel.setForeground(new java.awt.Color(0, 0, 0));
        TrangThaiLabel.setText("TRẠNG THÁI:");

        jSeparator6.setForeground(new java.awt.Color(153, 153, 153));

        TongThanhTienInBill3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongThanhTienInBill3.setForeground(new java.awt.Color(0, 0, 0));
        TongThanhTienInBill3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanelhienIBill3.setBackground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanelhienIBill3Layout = new javax.swing.GroupLayout(jPanelhienIBill3);
        jPanelhienIBill3.setLayout(jPanelhienIBill3Layout);
        jPanelhienIBill3Layout.setHorizontalGroup(
            jPanelhienIBill3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanelhienIBill3Layout.setVerticalGroup(
            jPanelhienIBill3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        TENQUAN2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        TENQUAN2.setForeground(new java.awt.Color(0, 0, 0));
        TENQUAN2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TENQUAN2.setText("TRÀ SỮA TASU");

        DiaChi2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        DiaChi2.setForeground(new java.awt.Color(0, 0, 0));
        DiaChi2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiaChi2.setText("Địa chỉ: 123, Đ. Lê Văn Việt, P. Hiệp Phú, TP. Hồ Chí Minh");

        TongGiamGiaMon2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon2.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon2.setText("Tổng giảm giá món:");

        GiaDiscount2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GiaDiscount2.setForeground(new java.awt.Color(0, 0, 0));
        GiaDiscount2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("TỔNG TIỀN:");

        TongTienInBill2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        TongTienInBill2.setForeground(new java.awt.Color(0, 0, 0));
        TongTienInBill2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        powered1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        powered1.setForeground(new java.awt.Color(0, 0, 0));
        powered1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        powered1.setText("powered by n21dcat037.com");

        TrangThai.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TrangThai.setForeground(new java.awt.Color(0, 0, 0));

        HinhThuc3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        HinhThuc3.setForeground(new java.awt.Color(0, 0, 0));
        HinhThuc3.setText("HÌNH THỨC:");

        LiDoHuy.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        LiDoHuy.setForeground(new java.awt.Color(0, 0, 0));

        TongGiamGiaMon4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        TongGiamGiaMon4.setForeground(new java.awt.Color(0, 0, 0));
        TongGiamGiaMon4.setText("Phí vận chuyển:");

        transitFee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        transitFee1.setForeground(new java.awt.Color(0, 0, 0));
        transitFee1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout ChitietHDOLayout = new javax.swing.GroupLayout(ChitietHDO);
        ChitietHDO.setLayout(ChitietHDOLayout);
        ChitietHDOLayout.setHorizontalGroup(
            ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ChitietHDOLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelhienIBill3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(DiaChi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                        .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NgayXuatBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MaBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HinhThuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChitietHDOLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LiDoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                            .addComponent(TongGiamGiaMon4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(transitFee1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(ChitietHDOLayout.createSequentialGroup()
                                            .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(TrangThaiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TongGiamGiaMon2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(44, 44, 44)
                                            .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(TongTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(TongThanhTienInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(GiaDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addGroup(ChitietHDOLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChitietHDOLayout.createSequentialGroup()
                        .addComponent(TENQUAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                        .addComponent(tenInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelThoatInBill3)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChitietHDOLayout.createSequentialGroup()
                .addComponent(powered1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ChitietHDOLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ChitietHDOLayout.setVerticalGroup(
            ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChitietHDOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelThoatInBill3)
                    .addComponent(tenInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TENQUAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiaChi2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MaBill3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NgayXuatBill3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HinhThuc3)
                .addGap(20, 20, 20)
                .addComponent(jPanelhienIBill3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(TongThanhTienInBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GiaDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TongGiamGiaMon2))
                .addGap(18, 18, 18)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transitFee1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TongGiamGiaMon4))
                .addGap(18, 18, 18)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChitietHDOLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TongTienInBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40))
                .addGap(23, 23, 23)
                .addGroup(ChitietHDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TrangThaiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LiDoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(powered1)
                .addGap(14, 14, 14))
        );

        jLabel41.setText("jLabel41");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NHÂN VIÊN");
        setBackground(new java.awt.Color(27, 43, 32));

        jPanelNavbar.setBackground(new java.awt.Color(0, 150, 150));
        jPanelNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelNavbarMouseClicked(evt);
            }
        });

        fullNameText.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        fullNameText.setForeground(new java.awt.Color(204, 255, 204));
        fullNameText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullNameText.setText("XIN CHÀO");

        jButtonChoose.setBackground(new java.awt.Color(51, 255, 204));
        jButtonChoose.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonChoose.setForeground(new java.awt.Color(0, 102, 102));
        jButtonChoose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/choices_32px.png"))); // NOI18N
        jButtonChoose.setText("CHỌN MÓN");
        jButtonChoose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseActionPerformed(evt);
            }
        });

        jButtonBill.setBackground(new java.awt.Color(51, 255, 204));
        jButtonBill.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonBill.setForeground(new java.awt.Color(0, 102, 102));
        jButtonBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bill_32px.png"))); // NOI18N
        jButtonBill.setText("QUẢN LÝ ĐƠN HÀNG");
        jButtonBill.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBillActionPerformed(evt);
            }
        });

        jButtonInfoStaff.setBackground(new java.awt.Color(51, 255, 204));
        jButtonInfoStaff.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonInfoStaff.setForeground(new java.awt.Color(0, 102, 102));
        jButtonInfoStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/info_32px.png"))); // NOI18N
        jButtonInfoStaff.setText("THÔNG TIN NHÂN VIÊN");
        jButtonInfoStaff.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonInfoStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoStaffActionPerformed(evt);
            }
        });

        func.setBackground(new java.awt.Color(0, 153, 153));
        func.setToolTipText("");
        func.setLayout(new java.awt.CardLayout());

        loginBtn.setBackground(new java.awt.Color(204, 255, 204));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(0, 153, 0));
        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Login-in-icon.png"))); // NOI18N
        loginBtn.setText("ĐĂNG NHẬP");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        func.add(loginBtn, "card1");

        logoutBtn.setBackground(new java.awt.Color(255, 204, 204));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 51, 51));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Login-out-icon.png"))); // NOI18N
        logoutBtn.setText("ĐĂNG XUẤT");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        func.add(logoutBtn, "card2");

        jButtonManageProduct.setBackground(new java.awt.Color(51, 255, 204));
        jButtonManageProduct.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonManageProduct.setForeground(new java.awt.Color(0, 102, 102));
        jButtonManageProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/product-chain_-32px.png"))); // NOI18N
        jButtonManageProduct.setText("QUẢN LÝ MÓN");
        jButtonManageProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonManageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManageProductActionPerformed(evt);
            }
        });

        jButtonHome.setBackground(new java.awt.Color(51, 255, 204));
        jButtonHome.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonHome.setForeground(new java.awt.Color(0, 102, 102));
        jButtonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        jButtonHome.setText("TRANG CHỦ");
        jButtonHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        jButtonManageCategory.setBackground(new java.awt.Color(51, 255, 204));
        jButtonManageCategory.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonManageCategory.setForeground(new java.awt.Color(0, 102, 102));
        jButtonManageCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manageCategory_32px.png"))); // NOI18N
        jButtonManageCategory.setText("QUẢN LÝ DANH MỤC");
        jButtonManageCategory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonManageCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManageCategoryActionPerformed(evt);
            }
        });

        jLabelbuttoncloseMenuNavbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x_15px.png"))); // NOI18N
        jLabelbuttoncloseMenuNavbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelbuttoncloseMenuNavbarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelNavbarLayout = new javax.swing.GroupLayout(jPanelNavbar);
        jPanelNavbar.setLayout(jPanelNavbarLayout);
        jPanelNavbarLayout.setHorizontalGroup(
            jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                .addGroup(jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNavbarLayout.createSequentialGroup()
                        .addGroup(jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(func, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelNavbarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fullNameText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                                .addGroup(jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonManageCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonInfoStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNavbarLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonManageProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNavbarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelbuttoncloseMenuNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelNavbarLayout.setVerticalGroup(
            jPanelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavbarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelbuttoncloseMenuNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonManageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonManageCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBill, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonInfoStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(func, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanelMain.setBackground(new java.awt.Color(0, 153, 153));
        jPanelMain.setLayout(new java.awt.CardLayout());

        jPanelHome.setBackground(new java.awt.Color(27, 43, 32));
        jPanelHome.setForeground(new java.awt.Color(27, 43, 32));
        jPanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHomeMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list_32_green.png"))); // NOI18N
        jLabelHomeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHomeMenuMouseClicked(evt);
            }
        });
        jPanelHome.add(jLabelHomeMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 18, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_150.png"))); // NOI18N
        jPanelHome.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 940, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setText("n21dcat037-Ho Thanh Nhat");
        jPanelHome.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, -1, 10));

        TBChoXacNhan2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TBChoXacNhan2.setForeground(new java.awt.Color(255, 0, 0));
        TBChoXacNhan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notification.png"))); // NOI18N
        jPanelHome.add(TBChoXacNhan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel33.setFont(new java.awt.Font("Rockwell Nova Extra Bold", 3, 48)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("TASU MILKTEA");
        jPanelHome.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 940, -1));

        jLabel34.setFont(new java.awt.Font("Rockwell Nova Light", 3, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Tea Amazing, Savor Unique");
        jPanelHome.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 940, 40));

        jLabel35.setFont(new java.awt.Font("Rockwell Nova Light", 3, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Since 2003");
        jPanelHome.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 940, 40));

        jLabel36.setFont(new java.awt.Font("Rockwell Nova Light", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("TASU © 2024 All rights reserved. Designed by G10-D21AT");
        jPanelHome.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 940, -1));

        jLabel39.setFont(new java.awt.Font("Rockwell Nova Light", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/location.png"))); // NOI18N
        jLabel39.setText("123, Le Van Viet Street, Hiep Phu Ward, Ho Chi Minh City");
        jPanelHome.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 950, 30));

        jPanelMain.add(jPanelHome, "card5");

        jPanelBill.setBackground(new java.awt.Color(204, 204, 204));
        jPanelBill.setForeground(new java.awt.Color(51, 51, 51));
        jPanelBill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TẤT CẢ HÓA ĐƠN ");
        jPanelBill.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, 820, 58));

        jLabelBillMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list_32_green.png"))); // NOI18N
        jLabelBillMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBillMenuMouseClicked(evt);
            }
        });
        jPanelBill.add(jLabelBillMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 14, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("n21dcat037-Ho Thanh Nhat");
        jPanelBill.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(782, 632, -1, -1));
        jPanelBill.add(jScrollPaneStatusBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 105, 910, 521));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("TRẠNG THÁI:");
        jPanelBill.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 64, 191, 35));

        jComboBoxStatus.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ xác nhận", "Đã giao", "Đã hủy" }));
        jPanelBill.add(jComboBoxStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 64, 290, -1));

        TBChoXacNhan1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TBChoXacNhan1.setForeground(new java.awt.Color(255, 0, 0));
        TBChoXacNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notification.png"))); // NOI18N
        jPanelBill.add(TBChoXacNhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jPanelMain.add(jPanelBill, "card3");

        jPanelInfo.setBackground(new java.awt.Color(204, 204, 204));
        jPanelInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("THÔNG TIN NHÂN VIÊN");
        jPanelInfo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 0, -1, 48));

        jPanel1.setBackground(new java.awt.Color(0, 140, 140));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        pic.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pic.setForeground(new java.awt.Color(51, 51, 51));
        pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pic.setText("Ảnh 2x3");
        pic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fullNameInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        fullNameInfo.setForeground(new java.awt.Color(255, 255, 0));
        fullNameInfo.setText("Họ và tên: ");

        cccdInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        cccdInfo.setForeground(new java.awt.Color(255, 255, 0));
        cccdInfo.setText("CCCD: ");

        birthdayInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        birthdayInfo.setForeground(new java.awt.Color(255, 255, 0));
        birthdayInfo.setText("Ngày sinh: ");

        genderInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        genderInfo.setForeground(new java.awt.Color(255, 255, 0));
        genderInfo.setText("Giới tính: ");

        permissionInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        permissionInfo.setForeground(new java.awt.Color(255, 255, 0));
        permissionInfo.setText("Chức vụ: ");

        jTextFieldInfoName.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoName.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoName.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoName.setText("name");
        jTextFieldInfoName.setBorder(null);
        jTextFieldInfoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoNameActionPerformed(evt);
            }
        });

        jTextFieldInfoBirthDate.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoBirthDate.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoBirthDate.setText("bd");
        jTextFieldInfoBirthDate.setBorder(null);
        jTextFieldInfoBirthDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoBirthDateActionPerformed(evt);
            }
        });

        jTextFieldInfoGender.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoGender.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoGender.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoGender.setText("gender");
        jTextFieldInfoGender.setBorder(null);
        jTextFieldInfoGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoGenderActionPerformed(evt);
            }
        });

        jTextFieldInfoCCCD.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoCCCD.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoCCCD.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoCCCD.setText("cccd");
        jTextFieldInfoCCCD.setBorder(null);
        jTextFieldInfoCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoCCCDActionPerformed(evt);
            }
        });

        jTextFieldInfoRole.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoRole.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoRole.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoRole.setText("role");
        jTextFieldInfoRole.setBorder(null);
        jTextFieldInfoRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoRoleActionPerformed(evt);
            }
        });

        jButtonInfoSuaTen.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoSuaTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setting-32px.png"))); // NOI18N
        jButtonInfoSuaTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoSuaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoSuaTenActionPerformed(evt);
            }
        });

        jButtonInfoSuaGioTinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoSuaGioTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setting-32px.png"))); // NOI18N
        jButtonInfoSuaGioTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoSuaGioTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoSuaGioTinhActionPerformed(evt);
            }
        });

        jButtonInfoSuaCCCD.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoSuaCCCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setting-32px.png"))); // NOI18N
        jButtonInfoSuaCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoSuaCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoSuaCCCDActionPerformed(evt);
            }
        });

        jButtonInfoNoSuaTen.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoNoSuaTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shape_24px.png"))); // NOI18N
        jButtonInfoNoSuaTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoNoSuaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoNoSuaTenActionPerformed(evt);
            }
        });

        jButtonInfoYesSuaTen.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoYesSuaTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-mark_24px.png"))); // NOI18N
        jButtonInfoYesSuaTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoYesSuaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoYesSuaTenActionPerformed(evt);
            }
        });

        jButtonInfoYesSuaGioiTinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoYesSuaGioiTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-mark_24px.png"))); // NOI18N
        jButtonInfoYesSuaGioiTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoYesSuaGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoYesSuaGioiTinhActionPerformed(evt);
            }
        });

        jButtonInfoNoSuaGioiTinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoNoSuaGioiTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shape_24px.png"))); // NOI18N
        jButtonInfoNoSuaGioiTinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoNoSuaGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoNoSuaGioiTinhActionPerformed(evt);
            }
        });

        jButtonInfoNoSuaCCCD.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoNoSuaCCCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shape_24px.png"))); // NOI18N
        jButtonInfoNoSuaCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoNoSuaCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoNoSuaCCCDActionPerformed(evt);
            }
        });

        jButtonInfoYesSuaCCCD.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoYesSuaCCCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-mark_24px.png"))); // NOI18N
        jButtonInfoYesSuaCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoYesSuaCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoYesSuaCCCDActionPerformed(evt);
            }
        });

        jButtonInfoSuaNgaySinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoSuaNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setting-32px.png"))); // NOI18N
        jButtonInfoSuaNgaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoSuaNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoSuaNgaySinhActionPerformed(evt);
            }
        });

        jButtonInfoNoSuaNgaySinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoNoSuaNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shape_24px.png"))); // NOI18N
        jButtonInfoNoSuaNgaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoNoSuaNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoNoSuaNgaySinhActionPerformed(evt);
            }
        });

        jButtonInfoYesSuaNgaySinh.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoYesSuaNgaySinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-mark_24px.png"))); // NOI18N
        jButtonInfoYesSuaNgaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoYesSuaNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoYesSuaNgaySinhActionPerformed(evt);
            }
        });

        addressInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        addressInfo.setForeground(new java.awt.Color(255, 255, 0));
        addressInfo.setText("Quê quán: ");

        jTextAreaAddress.setBackground(new java.awt.Color(0, 140, 140));
        jTextAreaAddress.setColumns(20);
        jTextAreaAddress.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextAreaAddress.setForeground(new java.awt.Color(255, 255, 153));
        jTextAreaAddress.setRows(5);
        jScrollPane11.setViewportView(jTextAreaAddress);

        jButtonInfoSuaDiaChi.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoSuaDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setting-32px.png"))); // NOI18N
        jButtonInfoSuaDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoSuaDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoSuaDiaChiActionPerformed(evt);
            }
        });

        jButtonInfoNoSuaDiaChi.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoNoSuaDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shape_24px.png"))); // NOI18N
        jButtonInfoNoSuaDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoNoSuaDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoNoSuaDiaChiActionPerformed(evt);
            }
        });

        jButtonInfoYesSuaDiaChi.setBackground(new java.awt.Color(0, 140, 140));
        jButtonInfoYesSuaDiaChi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-mark_24px.png"))); // NOI18N
        jButtonInfoYesSuaDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 140)));
        jButtonInfoYesSuaDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoYesSuaDiaChiActionPerformed(evt);
            }
        });

        PhoneInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        PhoneInfo.setForeground(new java.awt.Color(255, 255, 0));
        PhoneInfo.setText("Số điện thoại: ");

        jTextFieldInfoPhone.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoPhone.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoPhone.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoPhone.setText("role");
        jTextFieldInfoPhone.setBorder(null);
        jTextFieldInfoPhone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldInfoPhoneMouseClicked(evt);
            }
        });
        jTextFieldInfoPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoPhoneActionPerformed(evt);
            }
        });

        salaryInfo.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        salaryInfo.setForeground(new java.awt.Color(255, 255, 0));
        salaryInfo.setText("Lương/tháng: ");

        jTextFieldInfoSalary.setBackground(new java.awt.Color(0, 140, 140));
        jTextFieldInfoSalary.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldInfoSalary.setForeground(new java.awt.Color(255, 255, 153));
        jTextFieldInfoSalary.setText("salary");
        jTextFieldInfoSalary.setBorder(null);
        jTextFieldInfoSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoSalaryActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 153));
        jLabel9.setText("<html><u>Thay đổi mật khẩu!</u> </html> ");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo38x35.jpg"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("n21dcat037-Ho Thanh Nhat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldInfoPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(permissionInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldInfoRole, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PhoneInfo))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInfoSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(salaryInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fullNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(genderInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(birthdayInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldInfoGender, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldInfoBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldInfoName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(121, 121, 121)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonInfoNoSuaNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonInfoNoSuaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonInfoNoSuaGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonInfoNoSuaCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cccdInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInfoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonInfoNoSuaDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonInfoYesSuaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoYesSuaNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoYesSuaGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoYesSuaCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoYesSuaDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonInfoSuaDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoSuaCCCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoSuaGioTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoSuaNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoSuaTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(permissionInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInfoRole, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(salaryInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInfoSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldInfoPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(4, 4, 4))))
                    .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonInfoYesSuaTen)
                                    .addComponent(jButtonInfoNoSuaTen)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fullNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldInfoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonInfoSuaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(jButtonInfoSuaNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(birthdayInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldInfoBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jButtonInfoYesSuaNgaySinh))))
                            .addComponent(jButtonInfoNoSuaNgaySinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonInfoNoSuaGioiTinh)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(genderInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldInfoGender, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonInfoYesSuaGioiTinh)))
                    .addComponent(jButtonInfoSuaGioTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cccdInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldInfoCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonInfoYesSuaCCCD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonInfoNoSuaCCCD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonInfoSuaCCCD, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonInfoSuaDiaChi)
                            .addComponent(jButtonInfoNoSuaDiaChi)
                            .addComponent(jButtonInfoYesSuaDiaChi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addressInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );

        jPanelInfo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, -1, -1));

        jLabelInfoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list_32_green.png"))); // NOI18N
        jLabelInfoMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInfoMenuMouseClicked(evt);
            }
        });
        jPanelInfo.add(jLabelInfoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, -1, -1));

        TBChoXacNhan.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TBChoXacNhan.setForeground(new java.awt.Color(255, 0, 0));
        TBChoXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notification.png"))); // NOI18N
        jPanelInfo.add(TBChoXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jPanelMain.add(jPanelInfo, "card4");

        jPanelManageProduct.setBackground(new java.awt.Color(204, 204, 204));
        jPanelManageProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelQuanLiMon.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabelQuanLiMon.setForeground(new java.awt.Color(204, 0, 0));
        jLabelQuanLiMon.setText("QUẢN LÝ MÓN");
        jPanelManageProduct.add(jLabelQuanLiMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 6, 188, 55));

        jTabbedPaneManageProduct.setBackground(new java.awt.Color(153, 153, 153));
        jTabbedPaneManageProduct.setForeground(new java.awt.Color(0, 204, 0));
        jTabbedPaneManageProduct.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

        jPanelQuanLiTraSua.setBackground(new java.awt.Color(153, 153, 153));

        jPanelFunctionMilkTea.setBackground(new java.awt.Color(153, 153, 153));
        jPanelFunctionMilkTea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153)));

        jButtonThemTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonThemTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonThemTraSua.setForeground(new java.awt.Color(0, 0, 204));
        jButtonThemTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add_24px.png"))); // NOI18N
        jButtonThemTraSua.setText("THÊM THÔNG TIN");
        jButtonThemTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 2));
        jButtonThemTraSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemTraSuaActionPerformed(evt);
            }
        });

        jButtonSuaTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSuaTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonSuaTraSua.setForeground(new java.awt.Color(255, 255, 0));
        jButtonSuaTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_24px.png"))); // NOI18N
        jButtonSuaTraSua.setText("SỬA THÔNG TIN");
        jButtonSuaTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 2));
        jButtonSuaTraSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaTraSuaActionPerformed(evt);
            }
        });

        jButtonXoaTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonXoaTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonXoaTraSua.setForeground(new java.awt.Color(255, 102, 0));
        jButtonXoaTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin_24px.png"))); // NOI18N
        jButtonXoaTraSua.setText("XÓA THÔNG TIN");
        jButtonXoaTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
        jButtonXoaTraSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTraSuaActionPerformed(evt);
            }
        });

        jButtonXemTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonXemTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonXemTraSua.setForeground(new java.awt.Color(0, 204, 204));
        jButtonXemTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye_24px.png"))); // NOI18N
        jButtonXemTraSua.setText("XEM THÔNG TIN");
        jButtonXemTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        jButtonXemTraSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXemTraSuaActionPerformed(evt);
            }
        });

        jTableTS.setAutoCreateRowSorter(true);
        jTableTS.setBackground(new java.awt.Color(221, 221, 221));
        jTableTS.setForeground(new java.awt.Color(102, 102, 102));
        jTableTS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Trà Sữa", "Size", "Giá Trà Sữa"
            }
        ));
        jTableTS.setRowHeight(30);
        jScrollPane5.setViewportView(jTableTS);

        jTextFieldSearchInManageProduct.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldSearchInManageProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldSearchInManageProduct.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldSearchInManageProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextFieldSearchInManageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchInManageProductActionPerformed(evt);
            }
        });

        jButtonSearchInManageProduct.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSearchInManageProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search_32px.png"))); // NOI18N
        jButtonSearchInManageProduct.setBorder(null);
        jButtonSearchInManageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchInManageProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFunctionMilkTeaLayout = new javax.swing.GroupLayout(jPanelFunctionMilkTea);
        jPanelFunctionMilkTea.setLayout(jPanelFunctionMilkTeaLayout);
        jPanelFunctionMilkTeaLayout.setHorizontalGroup(
            jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFunctionMilkTeaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFunctionMilkTeaLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanelFunctionMilkTeaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldSearchInManageProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSearchInManageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
            .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelFunctionMilkTeaLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonThemTraSua, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addComponent(jButtonSuaTraSua, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                    .addGap(41, 41, 41)
                    .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonXoaTraSua, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(jButtonXemTraSua, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        jPanelFunctionMilkTeaLayout.setVerticalGroup(
            jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFunctionMilkTeaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSearchInManageProduct)
                    .addComponent(jButtonSearchInManageProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelFunctionMilkTeaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonThemTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonXoaTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelFunctionMilkTeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSuaTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonXemTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(404, Short.MAX_VALUE)))
        );

        jPanelXulyMilkTea.setBackground(new java.awt.Color(153, 153, 153));
        jPanelXulyMilkTea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanelXulyMilkTea.setLayout(new java.awt.CardLayout());

        jPanelClear.setBackground(new java.awt.Color(153, 153, 153));
        jPanelClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        javax.swing.GroupLayout jPanelClearLayout = new javax.swing.GroupLayout(jPanelClear);
        jPanelClear.setLayout(jPanelClearLayout);
        jPanelClearLayout.setHorizontalGroup(
            jPanelClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanelClearLayout.setVerticalGroup(
            jPanelClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jPanelXulyMilkTea.add(jPanelClear, "card4");

        jPanelSuaTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSuaTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabelSuaTS.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelSuaTS.setForeground(new java.awt.Color(255, 255, 0));
        jLabelSuaTS.setText("SỬA THÔNG TIN MÓN");

        jLabelSuaHinhAnh1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaHinhAnh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));

        jButtonSuaHinhAnh.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSuaHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuaHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/image_32px.png"))); // NOI18N
        jButtonSuaHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        jButtonSuaHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaHinhAnhActionPerformed(evt);
            }
        });

        jLabelSuaTen.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaTen.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelSuaTen.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuaTen.setText("Tên món:");

        jTextFieldSuaTenMon.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaTenMon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaTenMon.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaTenMon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabelSuaGia.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaGia.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelSuaGia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuaGia.setText("Giá món:");

        jTextFieldSuaGiaMon.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaGiaMon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaGiaMon.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaGiaMon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("VND");

        jLabelMoTaGia.setBackground(new java.awt.Color(153, 153, 153));
        jLabelMoTaGia.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelMoTaGia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMoTaGia.setText("Mô tả:");

        jTextAreaSuaMoTa.setBackground(new java.awt.Color(153, 153, 153));
        jTextAreaSuaMoTa.setColumns(20);
        jTextAreaSuaMoTa.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextAreaSuaMoTa.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaSuaMoTa.setRows(5);
        jTextAreaSuaMoTa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        jScrollPane7.setViewportView(jTextAreaSuaMoTa);

        jButtonHoanTatSua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHoanTatSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonHoanTatSua.setForeground(new java.awt.Color(0, 0, 204));
        jButtonHoanTatSua.setText("HOÀN TẤT");
        jButtonHoanTatSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));
        jButtonHoanTatSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoanTatSuaActionPerformed(evt);
            }
        });

        jButtonHuySua.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHuySua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonHuySua.setForeground(new java.awt.Color(204, 51, 0));
        jButtonHuySua.setText("HỦY SỬA");
        jButtonHuySua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0), 3));
        jButtonHuySua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuySuaActionPerformed(evt);
            }
        });

        jLabelSuaLoai1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaLoai1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelSuaLoai1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuaLoai1.setText("Loại món:");

        jTextFieldSuaHinhAnh2.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaHinhAnh2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaHinhAnh2.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaHinhAnh2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jComboBoxSuaLoaiMon.setBackground(new java.awt.Color(153, 153, 153));
        jComboBoxSuaLoaiMon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jComboBoxSuaLoaiMon.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxSuaLoaiMon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        jComboBoxSuaLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSuaLoaiMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuaTraSuaLayout = new javax.swing.GroupLayout(jPanelSuaTraSua);
        jPanelSuaTraSua.setLayout(jPanelSuaTraSuaLayout);
        jPanelSuaTraSuaLayout.setHorizontalGroup(
            jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuaTraSuaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSuaTS, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelSuaHinhAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                        .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                                .addComponent(jTextFieldSuaHinhAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSuaHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                            .addComponent(jTextFieldSuaTenMon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelSuaTraSuaLayout.createSequentialGroup()
                                .addComponent(jLabelSuaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBoxSuaLoaiMon, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelSuaLoai1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelMoTaGia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane7))
                    .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelSuaGia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldSuaGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButtonHoanTatSua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonHuySua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanelSuaTraSuaLayout.setVerticalGroup(
            jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelSuaTS, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelSuaTraSuaLayout.createSequentialGroup()
                        .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSuaHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldSuaHinhAnh2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addComponent(jLabelSuaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSuaTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSuaLoai1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelSuaHinhAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxSuaLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSuaGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSuaGia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMoTaGia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelSuaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonHoanTatSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHuySua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanelXulyMilkTea.add(jPanelSuaTraSua, "card6");

        jPanelThemTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jPanelThemTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        jLabelThemTS.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelThemTS.setForeground(new java.awt.Color(0, 0, 204));
        jLabelThemTS.setText("THÊM THÔNG TIN LOẠI TRÀ SỮA MỚI");

        jLabelTenTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelTenTraSua.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTenTraSua.setText("Tên loại món: ");

        jTextFieldTenTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldTenTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldTenTraSua.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldTenTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextFieldTenTraSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenTraSuaActionPerformed(evt);
            }
        });

        jLabelTenDoanhMuc.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelTenDoanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTenDoanhMuc.setText("Tên doanh mục món: ");

        jComboBoxDoanhMuc.setBackground(new java.awt.Color(153, 153, 153));
        jComboBoxDoanhMuc.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jComboBoxDoanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxDoanhMuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBoxDoanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDoanhMucActionPerformed(evt);
            }
        });

        jLabelThemHinhAnh.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelThemHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        jLabelThemHinhAnh.setText("Hình ảnh: ");

        jButtonThemAnh.setBackground(new java.awt.Color(153, 153, 153));
        jButtonThemAnh.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThemAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/image_32px.png"))); // NOI18N
        jButtonThemAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemAnhActionPerformed(evt);
            }
        });

        jTextFieldThemHinhAnh.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldThemHinhAnh.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldThemHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldThemHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelMoTa.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelMoTa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMoTa.setText("Mô tả:");

        jTextAreaMoTa.setBackground(new java.awt.Color(153, 153, 153));
        jTextAreaMoTa.setColumns(20);
        jTextAreaMoTa.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextAreaMoTa.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaMoTa.setRows(5);
        jTextAreaMoTa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(jTextAreaMoTa);

        jButtonHuyThem.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHuyThem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonHuyThem.setForeground(new java.awt.Color(204, 0, 0));
        jButtonHuyThem.setText("HỦY");
        jButtonHuyThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        jButtonHuyThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyThemActionPerformed(evt);
            }
        });

        jButtonHoanTatThem.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHoanTatThem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonHoanTatThem.setForeground(new java.awt.Color(0, 204, 0));
        jButtonHoanTatThem.setText("HOÀN TẤT");
        jButtonHoanTatThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0), 3));
        jButtonHoanTatThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoanTatThemActionPerformed(evt);
            }
        });

        jLabelGiaTraSua.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelGiaTraSua.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGiaTraSua.setText("Giá (VND):");

        jTextFieldGiaTraSuaSizeM.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldGiaTraSuaSizeM.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldGiaTraSuaSizeM.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldGiaTraSuaSizeM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextFieldGiaTraSuaSizeL.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldGiaTraSuaSizeL.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldGiaTraSuaSizeL.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldGiaTraSuaSizeL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelThemTraSuaLayout = new javax.swing.GroupLayout(jPanelThemTraSua);
        jPanelThemTraSua.setLayout(jPanelThemTraSuaLayout);
        jPanelThemTraSuaLayout.setHorizontalGroup(
            jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelThemTS, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                        .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtonHoanTatThem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButtonHuyThem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))))
            .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                        .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelThemTraSuaLayout.createSequentialGroup()
                                .addComponent(jLabelGiaTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldGiaTraSuaSizeM, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldGiaTraSuaSizeL))
                            .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                                        .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabelTenTraSua, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                            .addComponent(jLabelTenDoanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                                        .addComponent(jLabelThemHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)))
                                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTenTraSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxDoanhMuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                                        .addComponent(jTextFieldThemHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(35, 35, 35))))
        );
        jPanelThemTraSuaLayout.setVerticalGroup(
            jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelThemTS, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTenTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTenTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTenDoanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDoanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThemTraSuaLayout.createSequentialGroup()
                        .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jButtonThemAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldThemHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelThemHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGiaTraSua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGiaTraSuaSizeM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGiaTraSuaSizeL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelThemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHoanTatThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHuyThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanelXulyMilkTea.add(jPanelThemTraSua, "card2");

        jPanelXoaTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jPanelXoaTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 51)));

        jLabelXoaTS.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelXoaTS.setForeground(new java.awt.Color(255, 102, 0));
        jLabelXoaTS.setText("XÓA THÔNG TIN TRÀ SỮA");

        javax.swing.GroupLayout jPanelXoaTraSuaLayout = new javax.swing.GroupLayout(jPanelXoaTraSua);
        jPanelXoaTraSua.setLayout(jPanelXoaTraSuaLayout);
        jPanelXoaTraSuaLayout.setHorizontalGroup(
            jPanelXoaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXoaTraSuaLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabelXoaTS)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanelXoaTraSuaLayout.setVerticalGroup(
            jPanelXoaTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXoaTraSuaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelXoaTS, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(437, Short.MAX_VALUE))
        );

        jPanelXulyMilkTea.add(jPanelXoaTraSua, "card5");

        jPanelXemTraSua.setBackground(new java.awt.Color(153, 153, 153));
        jPanelXemTraSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("THÔNG TIN TRÀ SỮA");

        jLabelShowImage.setBackground(new java.awt.Color(153, 153, 153));
        jLabelShowImage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelShowImage.setForeground(new java.awt.Color(255, 255, 255));
        jLabelShowImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabelXemTenThucUong1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemTenThucUong1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemTenThucUong1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemTenThucUong1.setText("Tên thức uống:");

        jLabelXemTenThucUong2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemTenThucUong2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemTenThucUong2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelXemLoaiThucUong1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemLoaiThucUong1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemLoaiThucUong1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemLoaiThucUong1.setText("Loại thức uống:");

        jLabelXemLoaiThucUong2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemLoaiThucUong2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemLoaiThucUong2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelXemGiaThucUong1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemGiaThucUong1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemGiaThucUong1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemGiaThucUong1.setText("Giá thức uống:");

        jLabelXemGiaThucUong2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemGiaThucUong2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemGiaThucUong2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelXemMotaThucUong1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemMotaThucUong1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelXemMotaThucUong1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemMotaThucUong1.setText("Mô tả:");

        jTextAreaXemMotaThucUong2.setBackground(new java.awt.Color(153, 153, 153));
        jTextAreaXemMotaThucUong2.setColumns(20);
        jTextAreaXemMotaThucUong2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextAreaXemMotaThucUong2.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaXemMotaThucUong2.setRows(5);
        jTextAreaXemMotaThucUong2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaXemMotaThucUong2FocusGained(evt);
            }
        });
        jScrollPane6.setViewportView(jTextAreaXemMotaThucUong2);

        javax.swing.GroupLayout jPanelXemTraSuaLayout = new javax.swing.GroupLayout(jPanelXemTraSua);
        jPanelXemTraSua.setLayout(jPanelXemTraSuaLayout);
        jPanelXemTraSuaLayout.setHorizontalGroup(
            jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemTraSuaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(121, 121, 121))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemTraSuaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelXemMotaThucUong1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelXemGiaThucUong1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelXemLoaiThucUong1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelXemTenThucUong1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelShowImage, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelXemTenThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jLabelXemLoaiThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jLabelXemGiaThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanelXemTraSuaLayout.setVerticalGroup(
            jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXemTraSuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelShowImage, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelXemTenThucUong1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabelXemTenThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelXemLoaiThucUong1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabelXemLoaiThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelXemGiaThucUong1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabelXemGiaThucUong2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelXemTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelXemTraSuaLayout.createSequentialGroup()
                        .addComponent(jLabelXemMotaThucUong1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanelXemTraSuaLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanelXulyMilkTea.add(jPanelXemTraSua, "card6");

        javax.swing.GroupLayout jPanelQuanLiTraSuaLayout = new javax.swing.GroupLayout(jPanelQuanLiTraSua);
        jPanelQuanLiTraSua.setLayout(jPanelQuanLiTraSuaLayout);
        jPanelQuanLiTraSuaLayout.setHorizontalGroup(
            jPanelQuanLiTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuanLiTraSuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFunctionMilkTea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelXulyMilkTea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelQuanLiTraSuaLayout.setVerticalGroup(
            jPanelQuanLiTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuanLiTraSuaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelQuanLiTraSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelXulyMilkTea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFunctionMilkTea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPaneManageProduct.addTab("TRÀ SỮA", jPanelQuanLiTraSua);

        jPanelQuanLiTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelQuanLiTopping.setForeground(new java.awt.Color(255, 255, 255));

        jPanelFunctionTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelFunctionTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153)));

        jButtonThemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonThemTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonThemTopping.setForeground(new java.awt.Color(0, 0, 204));
        jButtonThemTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add_24px.png"))); // NOI18N
        jButtonThemTopping.setText("THÊM THÔNG TIN");
        jButtonThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 2));
        jButtonThemTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemToppingActionPerformed(evt);
            }
        });

        jButtonSuaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSuaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonSuaTopping.setForeground(new java.awt.Color(255, 255, 0));
        jButtonSuaTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_24px.png"))); // NOI18N
        jButtonSuaTopping.setText("SỬA THÔNG TIN");
        jButtonSuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 2));
        jButtonSuaTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaToppingActionPerformed(evt);
            }
        });

        jButtonXoaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonXoaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonXoaTopping.setForeground(new java.awt.Color(255, 102, 0));
        jButtonXoaTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bin_24px.png"))); // NOI18N
        jButtonXoaTopping.setText("XÓA THÔNG TIN");
        jButtonXoaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
        jButtonXoaTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaToppingActionPerformed(evt);
            }
        });

        jButtonXemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonXemTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonXemTopping.setForeground(new java.awt.Color(0, 204, 204));
        jButtonXemTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye_24px.png"))); // NOI18N
        jButtonXemTopping.setText("XEM THÔNG TIN");
        jButtonXemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        jButtonXemTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXemToppingActionPerformed(evt);
            }
        });

        jTableTP.setBackground(new java.awt.Color(255, 255, 255));
        jTableTP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Topping", "Giá Topping"
            }
        ));
        jTableTP.setRowHeight(30);
        jScrollPane8.setViewportView(jTableTP);

        jTextFieldSearchInManagTopping.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldSearchInManagTopping.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldSearchInManagTopping.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldSearchInManagTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextFieldSearchInManagTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchInManagToppingActionPerformed(evt);
            }
        });

        jButtonSearchInManageTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSearchInManageTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search_32px.png"))); // NOI18N
        jButtonSearchInManageTopping.setBorder(null);
        jButtonSearchInManageTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchInManageToppingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFunctionToppingLayout = new javax.swing.GroupLayout(jPanelFunctionTopping);
        jPanelFunctionTopping.setLayout(jPanelFunctionToppingLayout);
        jPanelFunctionToppingLayout.setHorizontalGroup(
            jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFunctionToppingLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFunctionToppingLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldSearchInManagTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSearchInManageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelFunctionToppingLayout.createSequentialGroup()
                    .addGap(219, 219, 219)
                    .addGroup(jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonXoaTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(jButtonXemTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        jPanelFunctionToppingLayout.setVerticalGroup(
            jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFunctionToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSearchInManagTopping)
                    .addComponent(jButtonSearchInManageTopping, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelFunctionToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelFunctionToppingLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButtonXoaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonXemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(401, Short.MAX_VALUE)))
        );

        jPanelXulyTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelXulyTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanelXulyTopping.setLayout(new java.awt.CardLayout());

        jPanelClear1.setBackground(new java.awt.Color(153, 153, 153));
        jPanelClear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        javax.swing.GroupLayout jPanelClear1Layout = new javax.swing.GroupLayout(jPanelClear1);
        jPanelClear1.setLayout(jPanelClear1Layout);
        jPanelClear1Layout.setHorizontalGroup(
            jPanelClear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jPanelClear1Layout.setVerticalGroup(
            jPanelClear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        jPanelXulyTopping.add(jPanelClear1, "card4");

        jPanelThemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        jLabelThemTS1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelThemTS1.setForeground(new java.awt.Color(0, 0, 204));
        jLabelThemTS1.setText("THÊM THÔNG TIN LOẠI TOPPING MỚI");

        jLabelTenTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelTenTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTenTopping.setText("Tên loại topping: ");

        jTextFieldTenTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldTenTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldTenTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldTenTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelThemAnhTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelThemAnhTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelThemAnhTopping.setText("Hình ảnh: ");

        jButtonThemAnhTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonThemAnhTopping.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThemAnhTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/image_32px.png"))); // NOI18N
        jButtonThemAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonThemAnhTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemAnhToppingActionPerformed(evt);
            }
        });

        jTextFieldThemAnhTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldThemAnhTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldThemAnhTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldThemAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonHuyThemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHuyThemTopping.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonHuyThemTopping.setForeground(new java.awt.Color(204, 0, 0));
        jButtonHuyThemTopping.setText("HỦY");
        jButtonHuyThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        jButtonHuyThemTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyThemToppingActionPerformed(evt);
            }
        });

        jButtonHoanTatThemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHoanTatThemTopping.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonHoanTatThemTopping.setForeground(new java.awt.Color(0, 204, 0));
        jButtonHoanTatThemTopping.setText("HOÀN TẤT");
        jButtonHoanTatThemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0), 3));
        jButtonHoanTatThemTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoanTatThemToppingActionPerformed(evt);
            }
        });

        jLabelGiaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelGiaTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGiaTopping.setText("Giá (VND):");

        jTextFieldGiaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldGiaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldGiaTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldGiaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelThemToppingLayout = new javax.swing.GroupLayout(jPanelThemTopping);
        jPanelThemTopping.setLayout(jPanelThemToppingLayout);
        jPanelThemToppingLayout.setHorizontalGroup(
            jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemToppingLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelThemTS1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThemToppingLayout.createSequentialGroup()
                            .addComponent(jButtonHoanTatThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(jButtonHuyThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(jPanelThemToppingLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelThemToppingLayout.createSequentialGroup()
                        .addComponent(jTextFieldThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelThemToppingLayout.setVerticalGroup(
            jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelThemTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThemAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabelGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanelThemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHoanTatThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHuyThemTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanelXulyTopping.add(jPanelThemTopping, "card2");

        jPanelSuaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabelSuaTS1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelSuaTS1.setForeground(new java.awt.Color(255, 255, 0));
        jLabelSuaTS1.setText("SỬA THÔNG TIN TOPPING");

        jLabelSuaHinhAnhTopping.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaHinhAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));

        jButtonSuaHinhAnhToppinglogo.setBackground(new java.awt.Color(153, 153, 153));
        jButtonSuaHinhAnhToppinglogo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuaHinhAnhToppinglogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/image_32px.png"))); // NOI18N
        jButtonSuaHinhAnhToppinglogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        jButtonSuaHinhAnhToppinglogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaHinhAnhToppinglogoActionPerformed(evt);
            }
        });

        jLabelSuaTenTopping.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaTenTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelSuaTenTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuaTenTopping.setText("Tên món:");

        jTextFieldSuaTenTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaTenTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaTenTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaTenTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabelSuaGiaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jLabelSuaGiaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabelSuaGiaTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuaGiaTopping.setText("Giá món:");

        jTextFieldSuaGiaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaGiaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaGiaTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaGiaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("VND");

        jButtonHoanTatSuaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHoanTatSuaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonHoanTatSuaTopping.setForeground(new java.awt.Color(0, 0, 204));
        jButtonHoanTatSuaTopping.setText("HOÀN TẤT");
        jButtonHoanTatSuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));
        jButtonHoanTatSuaTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoanTatSuaToppingActionPerformed(evt);
            }
        });

        jButtonHuySuaTopping.setBackground(new java.awt.Color(153, 153, 153));
        jButtonHuySuaTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButtonHuySuaTopping.setForeground(new java.awt.Color(204, 51, 0));
        jButtonHuySuaTopping.setText("HỦY SỬA");
        jButtonHuySuaTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0), 3));
        jButtonHuySuaTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuySuaToppingActionPerformed(evt);
            }
        });

        jTextFieldSuaHinhAnhTopping.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldSuaHinhAnhTopping.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextFieldSuaHinhAnhTopping.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSuaHinhAnhTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        javax.swing.GroupLayout jPanelSuaToppingLayout = new javax.swing.GroupLayout(jPanelSuaTopping);
        jPanelSuaTopping.setLayout(jPanelSuaToppingLayout);
        jPanelSuaToppingLayout.setHorizontalGroup(
            jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelSuaHinhAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSuaHinhAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSuaHinhAnhToppinglogo, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButtonHoanTatSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonHuySuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabelSuaTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuaToppingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSuaGiaTopping, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSuaTenTopping, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSuaTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                        .addComponent(jTextFieldSuaGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        jPanelSuaToppingLayout.setVerticalGroup(
            jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabelSuaTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuaToppingLayout.createSequentialGroup()
                        .addComponent(jLabelSuaHinhAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuaToppingLayout.createSequentialGroup()
                        .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSuaHinhAnhToppinglogo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSuaHinhAnhTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)))
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSuaTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSuaTenTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSuaGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSuaGiaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanelSuaToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonHoanTatSuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHuySuaTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanelXulyTopping.add(jPanelSuaTopping, "card3");

        jPanelXemTopping.setBackground(new java.awt.Color(153, 153, 153));
        jPanelXemTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 204));
        jLabel7.setText("THÔNG TIN TRÀ SỮA");

        jLabelShowImageTopping.setBackground(new java.awt.Color(153, 153, 153));
        jLabelShowImageTopping.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelShowImageTopping.setForeground(new java.awt.Color(255, 255, 255));
        jLabelShowImageTopping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jLabelXemTenTopping1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemTenTopping1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelXemTenTopping1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemTenTopping1.setText("Tên topping:");

        jLabelXemTenTopping2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemTenTopping2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelXemTenTopping2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelXemGiaTopping1.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemGiaTopping1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelXemGiaTopping1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelXemGiaTopping1.setText("Giá topping:");

        jLabelXemGiaTopping2.setBackground(new java.awt.Color(153, 153, 153));
        jLabelXemGiaTopping2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelXemGiaTopping2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelXemToppingLayout = new javax.swing.GroupLayout(jPanelXemTopping);
        jPanelXemTopping.setLayout(jPanelXemToppingLayout);
        jPanelXemToppingLayout.setHorizontalGroup(
            jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXemToppingLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelXemGiaTopping1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelXemTenTopping1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 288, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemToppingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemToppingLayout.createSequentialGroup()
                        .addGroup(jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelXemGiaTopping2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelXemTenTopping2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXemToppingLayout.createSequentialGroup()
                        .addGroup(jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(jPanelXemToppingLayout.createSequentialGroup()
                                .addComponent(jLabelShowImageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addGap(121, 121, 121))))
        );
        jPanelXemToppingLayout.setVerticalGroup(
            jPanelXemToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXemToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelShowImageTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabelXemTenTopping1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelXemTenTopping2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabelXemGiaTopping1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelXemGiaTopping2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );

        jPanelXulyTopping.add(jPanelXemTopping, "card6");

        javax.swing.GroupLayout jPanelQuanLiToppingLayout = new javax.swing.GroupLayout(jPanelQuanLiTopping);
        jPanelQuanLiTopping.setLayout(jPanelQuanLiToppingLayout);
        jPanelQuanLiToppingLayout.setHorizontalGroup(
            jPanelQuanLiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuanLiToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFunctionTopping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelXulyTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelQuanLiToppingLayout.setVerticalGroup(
            jPanelQuanLiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelQuanLiToppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelQuanLiToppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelXulyTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFunctionTopping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPaneManageProduct.addTab("TOPPING", jPanelQuanLiTopping);

        jPanelManageProduct.add(jTabbedPaneManageProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabelManageProductMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list_32_green.png"))); // NOI18N
        jLabelManageProductMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelManageProductMenuMouseClicked(evt);
            }
        });
        jPanelManageProduct.add(jLabelManageProductMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo90x72.jpg"))); // NOI18N
        jPanelManageProduct.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 55));

        TBChoXacNhan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notification.png"))); // NOI18N
        jPanelManageProduct.add(TBChoXacNhan4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 6, -1, 30));

        jPanelMain.add(jPanelManageProduct, "card6");

        jPanelChoose.setBackground(new java.awt.Color(204, 204, 204));
        jPanelChoose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelChooseMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/list_32_green.png"))); // NOI18N
        jLabelChooseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChooseMenuMouseClicked(evt);
            }
        });
        jPanelChoose.add(jLabelChooseMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        allProduct.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH TRÀ SỮA");
        allProduct.setViewportView(jLabel1);

        jPanelChoose.add(allProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 889, 547));

        giohangchon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart-icon.png"))); // NOI18N
        giohangchon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giohangchonMouseClicked(evt);
            }
        });
        jPanelChoose.add(giohangchon, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 21, -1, 48));

        jcomboboxHientheoloai.setBackground(new java.awt.Color(204, 204, 204));
        jcomboboxHientheoloai.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jcomboboxHientheoloai.setForeground(new java.awt.Color(0, 102, 102));
        jcomboboxHientheoloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxHientheoloai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));
        jcomboboxHientheoloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboboxHientheoloaiActionPerformed(evt);
            }
        });
        jPanelChoose.add(jcomboboxHientheoloai, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 21, 238, 48));

        jlabelSLTrongGioHang.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jlabelSLTrongGioHang.setForeground(new java.awt.Color(255, 51, 0));
        jlabelSLTrongGioHang.setText("0");
        jPanelChoose.add(jlabelSLTrongGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 20, 14));

        TBChoXacNhan3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TBChoXacNhan3.setForeground(new java.awt.Color(255, 0, 0));
        TBChoXacNhan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notification.png"))); // NOI18N
        jPanelChoose.add(TBChoXacNhan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 20, 30));

        jPanelMain.add(jPanelChoose, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 646, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed

        new Thread(new Runnable() {
            @Override
            public void run() {
                Login e = new Login();
                e.setVisible(true);
//                e.changePanelChoose();
//                hideButtonInInfo();

            }
        }).start();
        dispose();

    }//GEN-LAST:event_logoutBtnActionPerformed

    private void jButtonInfoStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoStaffActionPerformed
        if (!fullNameText.getText().equals((String) ManagerEmployee.get("fullName"))) {
            JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập để thao tác!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        changePanelInfo();
    }//GEN-LAST:event_jButtonInfoStaffActionPerformed

    private void updateBillPanelByStatus(String selectedStatus) {
        if (selectedStatus.equals("Tất cả")) {
            HandleBills.getAllBills();
        } else {
            HandleBills.getBillWithStatus(selectedStatus);
        }

        JPanel billJPanel = new JPanel();
        billJPanel.setLayout(new BoxLayout(billJPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < HandleBills.BillDetails.size(); i++) {
            Map<String, Object> onebill = HandleBills.BillDetails.get(i);
            StatusBill statusBill = new StatusBill();
            statusBill.getMaHD().setText(String.valueOf(onebill.get("idBill")));
            statusBill.getNgayHD().setText((String) onebill.get("dateOrder"));
            statusBill.getStatusHD().setText((String) onebill.get("statusPurchase"));
            if (onebill.get("statusPurchase").equals("Đã giao")) {
                statusBill.getStatusHD().setForeground(Color.green);
            } else if (onebill.get("statusPurchase").equals("Đã hủy")) {
                statusBill.getStatusHD().setForeground(Color.red);
            } else if (onebill.get("statusPurchase").equals("Chờ xác nhận")) {
                statusBill.getStatusHD().setForeground(new Color(204, 153, 0));
            }

            // Add listeners for buttons
            addListenersToStatusBill(statusBill, onebill);

            billJPanel.add(statusBill);
            billJPanel.add(Box.createVerticalStrut(3));
        }

        if (HandleBills.BillDetails.size() < 7) {
            JPanel pn = new JPanel();
            pn.setBackground(new java.awt.Color(255, 255, 255));
            pn.setPreferredSize(new java.awt.Dimension(860, 450));
            for (int j = 0; j < 7 - HandleBills.BillDetails.size(); j++) {
                billJPanel.add(pn);
            }
        }
        jScrollPaneStatusBill.setViewportView(billJPanel);
    }

    private void addListenersToStatusBill(StatusBill statusBill, Map<String, Object> onebill) {
        statusBill.getXUATHD().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inBill.isVisible()) {
                    inBill.dispose();
                }
                String status = HandleBills.getStatusPurchaseById((int) onebill.get("idBill"));

                if (status.equals("Đã giao")) {
                    if (onebill.get("paymentMethod").equals("Tiền mặt")) {
                        xuatHD((int) onebill.get("idBill"));
                    } else if (onebill.get("paymentMethod").equals("Thẻ ngân hàng(ATM)") || onebill.get("paymentMethod").equals("Thanh toán khi nhận hàng") || onebill.get("paymentMethod").equals("Chuyển khoản ngân hàng")) {
                        xuatHDATM((int) onebill.get("idBill"));
                    }
                } else {
                    JOptionPane.showMessageDialog(panelTop, "Không thể xuất hóa đơn chưa thanh toán/chưa giao/đã hủy!!");
                }
            }
        });

        statusBill.getStatusXacNhan().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String status = HandleBills.getStatusPurchaseById((int) onebill.get("idBill"));
                        if (status.equals("Đã giao") || status.equals("Đã hủy")) {
                            JOptionPane.showMessageDialog(panelTop, "Đơn này đã được xác nhận/đã giao/đã hủy!");
                        } else {
                            HandleBills.updateBillStatusById((int) onebill.get("idBill"), "Đã xác nhận", (int) ManagerEmployee.get("idAccount"));
                            statusBill.getStatusHD().setForeground(Color.black);
                            statusBill.getStatusHD().setText("Đã xác nhận");
                            checkTBChoXacNhan();
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            HandleBills.updateBillStatusById((int) onebill.get("idBill"), "Đã giao", (int) ManagerEmployee.get("idAccount"));
                            statusBill.getStatusHD().setText("Đã giao");
                            statusBill.getStatusHD().setForeground(Color.green);
                            changePanelBill();
                            updateBillPanelByStatus((String) jComboBoxStatus.getSelectedItem());
                        }
                    }
                }).start();
            }
        });

        statusBill.getStatusHuy().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String status = HandleBills.getStatusPurchaseById((int) onebill.get("idBill"));
                if (status.equals("Đã giao")) {
                    JOptionPane.showMessageDialog(panelTop, "Không thể hủy đơn đã được giao!");
                } else if (status.equals("Đã hủy")) {
                    JOptionPane.showMessageDialog(panelTop, "Không thể hủy đơn đã hủy!");
                } else {
                    JTextArea textArea = new JTextArea(3, 15);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(scrollPane, BorderLayout.CENTER);
                    panel.setPreferredSize(new Dimension(300, 150));

                    int result = JOptionPane.showConfirmDialog(null, panel, "Nhập ghi chú cho đơn hàng này:", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String cancelReason = textArea.getText().trim();
                        if (!cancelReason.isEmpty()) {
                            HandleBills.updateBillStatusById((int) onebill.get("idBill"), "Đã hủy", cancelReason);
                            statusBill.getStatusHD().setText("Đã hủy");
                            statusBill.getStatusHD().setForeground(Color.red);
                            checkTBChoXacNhan();
                            updateBillPanelByStatus((String) jComboBoxStatus.getSelectedItem());
                        }
                    }
                }
            }
        });

        statusBill.getChiTietHD().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (inBill.isVisible()) {
                    inBill.dispose();
                }
                jPanelhienIBill3.removeAll();
                String status = HandleBills.getStatusPurchaseById((int) onebill.get("idBill"));
                xemChiTietHD((int) onebill.get("idBill"), status);
            }
        });
    }
    private void jButtonBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBillActionPerformed
        if (!fullNameText.getText().equals((String) ManagerEmployee.get("fullName"))) {
            JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập để thao tác!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        checkTBChoXacNhan();
        changePanelBill();
        JViewport jv1 = new JViewport();
        jScrollPaneStatusBill.setViewport(jv1);

        jComboBoxStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) jComboBoxStatus.getSelectedItem();
                if (selected != null) {
                    updateBillPanelByStatus(selected);
                }
            }
        });
        String selectedStatus = (String) jComboBoxStatus.getSelectedItem();
        if (selectedStatus != null) {
            updateBillPanelByStatus(selectedStatus);
        }

        if (checkTBChoXacNhan()) {
            jComboBoxStatus.setSelectedIndex(0);
        } else {
            jComboBoxStatus.setSelectedIndex(3);
        }
    }//GEN-LAST:event_jButtonBillActionPerformed

    private void jButtonChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseActionPerformed
        if (!fullNameText.getText().equals((String) ManagerEmployee.get("fullName"))) {
            JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập để thao tác!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        CategoryController.getNameCategory(jcomboboxHientheoloai);
        jcomboboxHientheoloai.removeItemAt(0);
        jcomboboxHientheoloai.addItem("Tất cả");
        jcomboboxHientheoloai.setSelectedItem("Tất cả");
        setProduct(0);
        jcomboboxHientheoloai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lấy mục được chọn từ JComboBox
                int category = 0;
                String selected = (String) jcomboboxHientheoloai.getSelectedItem();
                if (selected != null && selected.equals("Tất cả")) {
                    category = 0;
                    setProduct(0);
                } else if (selected != null) {
                    category = CategoryController.getIdCategory(selected);
                    setProduct(category);
                }
            }
        });

        changePanelChoose();

    }//GEN-LAST:event_jButtonChooseActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        openLoginForm();
        dispose();
        Items.clear();

    }//GEN-LAST:event_loginBtnActionPerformed

    private void jButtonManageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManageProductActionPerformed
        if (!fullNameText.getText().equals((String) ManagerEmployee.get("fullName"))) {
            JOptionPane.showMessageDialog(null, "Bạn cần đăng nhập để thao tác!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        changePanelManageProduct();
        focusJTextField(jTextFieldSearchInManageProduct, "Tìm theo tên/giá/mã...");
        focusJTextField(jTextFieldSearchInManagTopping, "Tìm theo tên/giá/mã...");
        focusJTextFieldWhite(jTextFieldGiaTraSuaSizeM, "Size M");
        focusJTextFieldWhite(jTextFieldGiaTraSuaSizeL, "Size L");
    }//GEN-LAST:event_jButtonManageProductActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        changePanelHome();
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonThemTraSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemTraSuaActionPerformed
        // TODO add your handling code here:
        changePanelAddProduct(jPanelXulyMilkTea, jPanelThemTraSua);
        CategoryController.getNameCategory(jComboBoxDoanhMuc);
        jTextAreaMoTa.setLineWrap(true); // Tự động xuống dòng
        jTextAreaMoTa.setWrapStyleWord(true);
    }//GEN-LAST:event_jButtonThemTraSuaActionPerformed

    private void jButtonSuaTraSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaTraSuaActionPerformed
        // Chuyển sang giao diện sửa sản phẩm
        changePanelEditProduct(jPanelXulyMilkTea, jPanelSuaTraSua);

        // Lấy dòng đã chọn từ jTableTS
        int rowSelected = jTableTS.getSelectedRow();
        if (rowSelected == -1) {
            // Xử lý khi không có dòng nào được chọn
            changePanelClear(jPanelXulyMilkTea);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần sửa");
            return;
        }

        // Lấy idProductDetail từ dòng đã chọn
        int idProductDetail = Integer.parseInt(jTableTS.getValueAt(rowSelected, 0).toString());
        String getSize = jTableTS.getValueAt(rowSelected, 2).toString();

        // Truy vấn trong bảng ProductDetail để lấy thông tin chi tiết của sản phẩm
        Product product = GetMilkTeaFromDatabaseController.getInfoMilkTea(idProductDetail);
        if (product != null) {
            // Hiển thị thông tin sản phẩm trên giao diện người dùng
            String imageURLPath = product.getImageProduct();
            java.net.URL imageURL = getClass().getResource(imageURLPath);
            if (imageURL != null) {
                ImageIcon imageIcon = new ImageIcon(imageURL);
                jLabelSuaHinhAnh1.setIcon(imageIcon);
                jTextFieldSuaHinhAnh2.setText(imageURLPath);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tìm thấy hình ảnh.");
            }

            // Hiển thị các thông tin khác của sản phẩm để cho phép sửa đổi
            String name = product.getNameProduct();
            jTextFieldSuaTenMon.setText(name);
            String nameCategory = CategoryController.getNameCategoryByid(product.getIdCategory());
            CategoryController.getNameCategory(jComboBoxSuaLoaiMon);
            jComboBoxSuaLoaiMon.setSelectedItem(nameCategory);
            jLabelSuaGia.setText("Giá/Size (" + getSize + "):");
            int price = product.getPriceProduct();
            jTextFieldSuaGiaMon.setText(String.valueOf(price));

            String descript = product.getDescription();
            jTextAreaSuaMoTa.setText(descript);
            jTextAreaSuaMoTa.setEditable(true); // Cho phép chỉnh sửa mô tả
            jTextAreaSuaMoTa.setLineWrap(true);
            jTextAreaSuaMoTa.setWrapStyleWord(true);

        } else {
            // Xử lý khi không tìm thấy sản phẩm
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm.");
        }
    }//GEN-LAST:event_jButtonSuaTraSuaActionPerformed

    private void jButtonXoaTraSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTraSuaActionPerformed
        // TODO add your handling code here:
        int[] rows = getRowInTable.getRows(jTableTS);
        if (rows.length == 0 || rows[0] == -1) {
            // Nếu không có dòng nào được chọn, hiển thị thông báo và kết thúc hàm
            changePanelClear(jPanelXulyMilkTea);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần xóa!");
            return;
        }

        // Xác nhận người dùng muốn xóa
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            boolean success = false; // Biến để kiểm tra xem có xóa thành công ít nhất một dòng hay không
            // Người dùng chọn "Yes", thực hiện xóa
            for (int row : rows) {
                int idProductDetail = Integer.parseInt(jTableTS.getValueAt(row, 0).toString());
                int idProduct = GetMilkTeaFromDatabaseController.getIdProductByProductSizeId(idProductDetail);
                boolean check = DeleteProductController.deleteMilkTeaAndSizesByProductId(idProduct);
                if (check == true) {
                    success = true; // Đã xóa thành công ít nhất một dòng
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Đã xóa thành công!!");
                GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
//                GetMilkTeaFromDatabaseController.getListMilkTea(jTableMilkTea);
                changePanelClear(jPanelXulyMilkTea);
            }
        }
    }//GEN-LAST:event_jButtonXoaTraSuaActionPerformed

    private void jButtonXemTraSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXemTraSuaActionPerformed
        changePanelViewProduct(jPanelXulyMilkTea, jPanelXemTraSua);

        // Lấy dòng đã chọn từ jTableTS
        int rowSelected = jTableTS.getSelectedRow();
        if (rowSelected == -1) {
            // Xử lý khi không có dòng nào được chọn
            changePanelClear(jPanelXulyMilkTea);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần xem");
            return;
        }

        // Lấy idProductDetail từ dòng đã chọn
        int idProductDetail = Integer.parseInt(jTableTS.getValueAt(rowSelected, 0).toString());
        String getSize = jTableTS.getValueAt(rowSelected, 2).toString();
        // Truy vấn trong bảng ProductDetail để lấy thông tin chi tiết của sản phẩm
        Product product = GetMilkTeaFromDatabaseController.getInfoMilkTea(idProductDetail);
        if (product != null) {
            // Hiển thị thông tin sản phẩm trên giao diện người dùng
            String imageURLPath = product.getImageProduct();
            java.net.URL imageURL = getClass().getResource(imageURLPath);
            if (imageURL != null) {
                ImageIcon imageIcon = new ImageIcon(imageURL);
                jLabelShowImage.setIcon(imageIcon);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tìm thấy hình ảnh.");
            }

            // Hiển thị các thông tin khác của sản phẩm
            String name = product.getNameProduct();
            jLabelXemTenThucUong2.setText(name);
            String nameCategory = CategoryController.getNameCategoryByid(product.getIdCategory());
            jLabelXemLoaiThucUong2.setText(nameCategory);
            int price = product.getPriceProduct();
            jLabelXemGiaThucUong1.setText("Giá / Size (" + getSize + "):");
            jLabelXemGiaThucUong2.setText(String.valueOf(price));

            String descript = product.getDescription();
            jTextAreaXemMotaThucUong2.setText(descript);
            jTextAreaXemMotaThucUong2.setEditable(false);
            jTextAreaXemMotaThucUong2.setLineWrap(true);
            jTextAreaXemMotaThucUong2.setWrapStyleWord(true);
        } else {
            // Xử lý khi không tìm thấy sản phẩm
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm.");
        }
    }//GEN-LAST:event_jButtonXemTraSuaActionPerformed

//    public void labelWithScrollpane(JLabel lbit){
//        lbit.setHorizontalAlignment(SwingConstants.LEFT);
//        JScrollPane scrollPane = new JScrollPane(lbit);
//        getContentPane().add(scrollPane);
//        setLocationRelativeTo(null);
//    }
//    
    private void jButtonThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemAnhActionPerformed
        // TODO add your handling code here:
        String defaultDirectory = new File("src/main/resources/img").getAbsolutePath();
        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "png", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String fileName = f.getName();

//            String fileName_full = "D:\\alang\\D21CQAT01-N\\HKII-Nam3\\CongNghePhanMem\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + f.getName();
            jTextFieldThemHinhAnh.setText(fileName);
//            jLabelHienAnh.setIcon(new ImageIcon(fileName_full));
        }
    }//GEN-LAST:event_jButtonThemAnhActionPerformed

    private void jButtonHoanTatThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoanTatThemActionPerformed
        // TODO add your handling code here:
        if (jTextFieldTenTraSua.getText().isEmpty() || jTextFieldThemHinhAnh.getText().isEmpty() || jTextAreaMoTa.getText().isEmpty() || jComboBoxDoanhMuc.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc phương thức nếu một trong các trường thông tin thiếu
        }

        int idcategory = 0;
        if (jComboBoxDoanhMuc.getSelectedItem() != null) {
            idcategory = CategoryController.getIdCategory(jComboBoxDoanhMuc.getSelectedItem().toString());
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn doanh mục cho món!!");
            return;
        }
        String name = jTextFieldTenTraSua.getText();
        String image = "/img/" + jTextFieldThemHinhAnh.getText();

        String description = jTextAreaMoTa.getText();

        java.util.List<Integer> sizes = SizeController.getSizes();
        int idSizeM = sizes.get(0);
        int idSizeL = sizes.get(1);
        System.out.println(idSizeM);
        System.out.println(idSizeL);
        int priceM = 0;
        int priceL = 0;
        try {
            priceM = Integer.parseInt(jTextFieldGiaTraSuaSizeM.getText());
            priceL = Integer.parseInt(jTextFieldGiaTraSuaSizeL.getText());
            AddProductController.addMilkTea(name, image, idcategory, description);
            int idProduct = GetMilkTeaFromDatabaseController.getIdProductByName(name);
            AddProductController.addMilkTeaWithSize(idProduct, idSizeM, priceM);

            if (priceL != 0) {
                AddProductController.addMilkTeaWithSize(idProduct, idSizeL, priceL);

            }
            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
//            GetMilkTeaFromDatabaseController.getListMilkTea(jTableMilkTea);
            jTextAreaMoTa.setText("");
            jTextFieldGiaTraSuaSizeM.setText("");
            jTextFieldGiaTraSuaSizeL.setText("");
            jTextFieldTenTraSua.setText("");
            jComboBoxDoanhMuc.setSelectedIndex(0);
            jTextFieldThemHinhAnh.setText("");
            changePanelClear(jPanelXulyMilkTea);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá tiền hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonHoanTatThemActionPerformed

    private void jButtonHuyThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyThemActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện hành động khi người dùng xác nhận hủy
            jTextAreaMoTa.setText("");
            jTextFieldGiaTraSuaSizeM.setText("");
            jTextFieldTenTraSua.setText("");
            jComboBoxDoanhMuc.setSelectedIndex(0);
            jTextFieldThemHinhAnh.setText("");
        }
    }//GEN-LAST:event_jButtonHuyThemActionPerformed

    private void jTextAreaXemMotaThucUong2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaXemMotaThucUong2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaXemMotaThucUong2FocusGained

    private void jButtonSuaHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaHinhAnhActionPerformed
        // TODO add your handling code here:
        String defaultDirectory = new File("src/main/resources/img").getAbsolutePath();
        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "png", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String fileName = f.getName();

//            String fileName_full = "D:\\alang\\D21CQAT01-N\\HKII-Nam3\\CongNghePhanMem\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + f.getName();
            jTextFieldSuaHinhAnh2.setText(fileName);
//            jLabelHienAnh.setIcon(new ImageIcon(fileName_full));
        }
    }//GEN-LAST:event_jButtonSuaHinhAnhActionPerformed


    private void jButtonHoanTatSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoanTatSuaActionPerformed
        int rowSelected = getRowInTable.getRow(jTableTS);
        int idProductSize = 0;
        int size = 0;
        String sizeString;
        int oldPrice = 0;
        if (rowSelected != -1) {
            idProductSize = Integer.parseInt(jTableTS.getValueAt(rowSelected, 0).toString());
            sizeString = jTableTS.getValueAt(rowSelected, 2).toString();
            size = sizeString.equals("M") ? 1 : 2;
            oldPrice = Integer.parseInt(jTableTS.getValueAt(rowSelected, 3).toString());
        } else {
            // Xử lý khi không có dòng nào được chọn
            changePanelClear(jPanelXulyMilkTea);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần sửa");
            return;
        }

        // Lấy thông tin sản phẩm từ idProduct và size
        Product product = GetMilkTeaFromDatabaseController.getInfoMilkTea(idProductSize);
        int idProduct = product.getIdProduct();
        // Lấy thông tin mới từ giao diện người dùng
        String nameProduct = jTextFieldSuaTenMon.getText().trim();
        String inputText = jTextFieldSuaHinhAnh2.getText().trim();
        String imageProduct;
        if (inputText.contains("/img/")) {
            imageProduct = inputText;
        } else {
            imageProduct = "/img/" + inputText;
        }
        String priceText = jTextFieldSuaGiaMon.getText().trim();
        String description = jTextAreaSuaMoTa.getText().trim();
        String categorySelectedItem = jComboBoxSuaLoaiMon.getSelectedItem().toString().trim();

        // Kiểm tra xem có thông tin nào mới được cập nhật không
        if (nameProduct.isEmpty() && priceText.isEmpty() && description.isEmpty() && categorySelectedItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ít nhất một trường thông tin mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc phương thức nếu không có trường thông tin mới nào được nhập
        }

        // Chuyển đổi giá sản phẩm từ chuỗi sang số nguyên
        int priceProduct;
        if (!priceText.isEmpty()) {
            try {
                priceProduct = Integer.parseInt(priceText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Nếu giá sản phẩm không được nhập, sử dụng giá sản phẩm cũ
            priceProduct = product.getPriceProduct();
        }

        // Lấy ID của danh mục từ tên danh mục được chọn
        int idCategory;
        if (!categorySelectedItem.isEmpty()) {
            idCategory = CategoryController.getIdCategory(categorySelectedItem);
        } else {
            // Nếu danh mục không được chọn, sử dụng danh mục của sản phẩm cũ
            idCategory = product.getIdCategory();
        }

        // Kiểm tra xem liệu có hình ảnh mới được chọn không, nếu không, sử dụng hình ảnh cũ
        if (imageProduct.isEmpty()) {
            imageProduct = product.getImageProduct(); // Sử dụng tên tệp ảnh cũ
        }

        // Kiểm tra xem liệu có mô tả mới được nhập không, nếu không, sử dụng mô tả cũ
        if (description.isEmpty()) {
            description = product.getDescription();
        }

        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hoàn tất sửa món này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Cập nhật thông tin sản phẩm vào cơ sở dữ liệu
            boolean check = EditInfoProductController.updateInfoMilkTeaDatabase(idProduct, nameProduct, imageProduct, priceProduct, description, idCategory);
            if (check) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin " + nameProduct + " thành công!!");
                // Nếu giá sản phẩm đã được cập nhật, cập nhật giá cho idSize tương ứng
                if (priceProduct != oldPrice) {
                    System.out.println(oldPrice);
                    System.out.println(priceProduct);
                    boolean updatePrice = EditInfoProductController.updatePriceForSize(idProduct, priceProduct, size);
                    if (updatePrice) {
                        JOptionPane.showMessageDialog(this, "Cập nhật giá thành công cho size " + sizeString);

                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật giá cho size " + sizeString);
                    }
                }
                // Cập nhật lại bảng thức uống sau khi cập nhật
                changePanelClear(jPanelXulyMilkTea);
                GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật thông tin món.");
            }
        }
    }//GEN-LAST:event_jButtonHoanTatSuaActionPerformed

    private void jComboBoxDoanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDoanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDoanhMucActionPerformed

    private void jButtonHuySuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuySuaActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            changePanelClear(jPanelXulyMilkTea);
        }
    }//GEN-LAST:event_jButtonHuySuaActionPerformed

    private void jTextFieldSearchInManageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchInManageProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchInManageProductActionPerformed

    public static void focusJTextField(JTextField jTextField, String defaultText) {
        jTextField.setText(defaultText);
        jTextField.setForeground(Color.GRAY);
        jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField.getText().equals(defaultText)) {
                    jTextField.setText("");
                    jTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField.getText().isEmpty()) {
                    jTextField.setForeground(Color.GRAY);
                    jTextField.setText(defaultText);
                }
            }
        });
    }

    public static void focusJTextFieldWhite(JTextField jTextField, String defaultText) {
        jTextField.setText(defaultText);
        jTextField.setForeground(Color.WHITE);
        jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField.getText().equals(defaultText)) {
                    jTextField.setText("");
                    jTextField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField.getText().isEmpty()) {
                    jTextField.setForeground(Color.WHITE);
                    jTextField.setText(defaultText);
                }
            }
        });
    }

    public static void focusToChangeAvatar(JLabel jl, int id, ImageIcon iconLoad) {
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String defaultDirectory = new File("src/main/resources/avatar").getAbsolutePath();
                JFileChooser fileChooser = new JFileChooser(defaultDirectory);
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "png", "jpg");
                fileChooser.setFileFilter(imageFilter);
                fileChooser.setMultiSelectionEnabled(false);
                int x = fileChooser.showDialog(null, "Chon file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    String fileName = f.getName();
                    String newURLAvatar = "/avatar/" + fileName;
                    int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hoàn tất sửa avatar?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        boolean a = EditInfoEmployeeController.editAvatarEmployee(newURLAvatar, id);
                        if (a) {
                            JOptionPane.showMessageDialog(null, "Cập nhật avatar thành công!");
                            String path = EditInfoEmployeeController.getNewAvatar(id);
                            java.net.URL newImageURL = getClass().getResource(path);
                            ImageIcon newIconAnh = new ImageIcon(newImageURL);
//                        jl.setIcon(new ImageIcon(newImageURL));
                            focusJLableImage(jl, iconLoad, newIconAnh);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể cập nhật avatar!!!");
                        }
                    }
                }
            }
        });
    }

    public static void focusJLable(JLabel jl) {
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Khi di chuột vào, làm mờ nhãn
                jl.setOpaque(true); // Đặt nhãn có khả năng che phủ nền
                jl.setBackground(new Color(255, 255, 255, 200)); // Màu nền với độ trong suốt 200 (0 - 255)
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Khi di chuột ra, trở lại bình thường
                jl.setOpaque(false); // Đặt nhãn không có khả năng che phủ nền
            }
        });
    }

    public static void focusJLableImage(JLabel jl, ImageIcon iconLoad, ImageIcon iconAnh) {
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Khi di chuột vào, làm mờ nhãn và hiển thị hình ảnh mới
                jl.setIcon(iconLoad); // Đường dẫn đến hình ảnh mờ
                jl.setOpaque(true); // Đặt nhãn có khả năng che phủ nền
                jl.setBackground(new Color(255, 255, 255, 200)); // Màu nền với độ trong suốt 200 (0 - 255)

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Khi di chuột ra, trở lại bình thường
                jl.setIcon(iconAnh); // Sử dụng hình ảnh ban đầu
                jl.setOpaque(false); // Đặt nhãn không có khả năng che phủ nền
            }
        });
    }

    public static String removeAccents(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalizedString = normalizedString.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Thay thế ký tự "Đ" bằng "D"
        normalizedString = normalizedString.replaceAll("Đ", "D").replaceAll("đ", "d");

        return normalizedString;
    }

    private void jButtonSearchInManageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchInManageProductActionPerformed
        // Lấy từ khóa tìm kiếm và loại bỏ dấu, chuyển về chữ thường
        String textOutput = jTextFieldSearchInManageProduct.getText().trim().toLowerCase();
        String searchText = removeAccents(textOutput);

        // Kiểm tra xem searchText có bằng chuỗi mặc định không
        if (textOutput.equals("tìm theo tên/giá/mã...")) {
            // Nếu không có từ khóa tìm kiếm, hiển thị lại bảng đầy đủ
            jTableTS.setRowSorter(null);
            GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTableTS.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) jTableTS.getRowSorter();

        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            jTableTS.setRowSorter(sorter);
        }

        RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(Entry<? extends Object, ? extends Object> entry) {
                // Duyệt qua tất cả các cột trong mỗi hàng
                for (int i = 0; i < entry.getValueCount(); i++) {
                    // Lấy giá trị trong ô
                    String cellValue = entry.getStringValue(i).toLowerCase();

                    // Loại bỏ dấu
                    String normalizedCellValue = removeAccents(cellValue);

                    // Kiểm tra xem từ khóa tìm kiếm có xuất hiện trong giá trị ô không
                    if (normalizedCellValue.contains(searchText)) {
                        return true; // Nếu có, bao gồm hàng này trong kết quả tìm kiếm
                    }
                }
                return false; // Nếu không, loại bỏ hàng này khỏi kết quả tìm kiếm
            }
        };

        sorter.setRowFilter(rowFilter);
    }//GEN-LAST:event_jButtonSearchInManageProductActionPerformed

    private void jButtonThemToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemToppingActionPerformed
        // TODO add your handling code here:
        changePanelAddProduct(jPanelXulyTopping, jPanelThemTopping);
//         jTextAreaMoTaTopping.setLineWrap(true); // Tự động xuống dòng
//        jTextAreaMoTaTopping.setWrapStyleWord(true);
    }//GEN-LAST:event_jButtonThemToppingActionPerformed

    private void jButtonSuaToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaToppingActionPerformed
        // TODO add your handling code here:
        changePanelEditProduct(jPanelXulyTopping, jPanelSuaTopping);

        int rowSelected = getRowInTable.getRow(jTableTP);
        int idTopping = 0;
        if (rowSelected != -1) {
            idTopping = Integer.parseInt(jTableTP.getValueAt(rowSelected, 0).toString());
        } else {
            // Xử lý khi không có dòng nào được chọn
            changePanelClear(jPanelXulyTopping);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần sửa");
            return;
        }

        Topping topping = GetToppingFromDatabaseController.getInfoTopping(idTopping);
        if (topping != null) {
            //Nữa anh em sửa đường dẫn này theo máy nghe
//              String imageURL = "D:\\alang\\D21CQAT01-N\\HKII-Nam3\\CongNghePhanMem\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + product.getImageProduct();

            String imageURL = topping.getImageTopping();
            jLabelSuaHinhAnhTopping.setIcon(new javax.swing.ImageIcon(getClass().getResource(imageURL)));
            String linkImage = topping.getImageTopping();
            jTextFieldSuaHinhAnhTopping.setText(imageURL);
            String name = topping.getNameTopping().toString();
//              labelWithScrollpane(jLabelXemTenThucUong2);
            jTextFieldSuaTenTopping.setText(name);
            int price = topping.getPriceTopping();
            String p = String.valueOf(price);
            jTextFieldSuaGiaTopping.setText(p);

            String descript = topping.getDescriptionTopping();
//              jTextAreaSuaMoTaTopping.setText(descript);
////              jTextAreaSuaMoTa.setEditable(false);
//              jTextAreaSuaMoTaTopping.setLineWrap(true); // Tự động xuống dòng
//              jTextAreaSuaMoTaTopping.setWrapStyleWord(true); // Xuống dòng dựa trên từ

        } else {
            // Xử lý khi không tìm thấy sản phẩm
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra");
            return;
        }
    }//GEN-LAST:event_jButtonSuaToppingActionPerformed

    private void jButtonXoaToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaToppingActionPerformed
        int[] rows = getRowInTable.getRows(jTableTP);
        if (rows.length == 0 || rows[0] == -1) {
            // Nếu không có dòng nào được chọn, hiển thị thông báo và kết thúc hàm
            changePanelClear(jPanelXulyTopping);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần xóa!");
            return;
        }

        // Xác nhận người dùng muốn xóa
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            boolean success = false; // Biến để kiểm tra xem có xóa thành công ít nhất một dòng hay không
            // Người dùng chọn "Yes", thực hiện xóa
            for (int i = rows.length - 1; i >= 0; i--) {
                int row = rows[i];
                if (row >= 0 && row < jTableTP.getRowCount()) { // Kiểm tra xem chỉ mục có hợp lệ không
                    int idTopping = Integer.parseInt(jTableTP.getValueAt(row, 0).toString());
                    boolean check = DeleteProductController.deleteToppingById(idTopping);
                    if (check == true) {
                        success = true; // Đã xóa thành công ít nhất một dòng
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");
                    }
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Đã xóa thành công!!");
                GetToppingFromDatabaseController.getListToppingShort(jTableTP);
//                GetToppingFromDatabaseController.getListTopping(jTableTopping);
                changePanelClear(jPanelXulyTopping);
            }
        }
    }//GEN-LAST:event_jButtonXoaToppingActionPerformed

    private void jButtonXemToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXemToppingActionPerformed
        // TODO add your handling code here:
        changePanelViewProduct(jPanelXulyTopping, jPanelXemTopping);
        int rowSelected = getRowInTable.getRow(jTableTP);
        int idTopping = 0;
        if (rowSelected != -1) {
            idTopping = Integer.parseInt(jTableTP.getValueAt(rowSelected, 0).toString());
        } else {
            // Xử lý khi không có dòng nào được chọn
            changePanelClear(jPanelXulyTopping);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần xem");
            return;
        }

        Topping topping = GetToppingFromDatabaseController.getInfoTopping(idTopping);
        if (topping != null) {
            // Tạo đường dẫn đến tệp hình ảnh trong thư mục resources
            String imageURLPath = topping.getImageTopping();
            // Tạo URL từ đường dẫn hình ảnh
            java.net.URL imageURL = getClass().getResource(imageURLPath);
            if (imageURL != null) {
                ImageIcon imageIcon = new ImageIcon(imageURL);
                // Đặt hình ảnh cho JLabel
                jLabelShowImageTopping.setIcon(imageIcon);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tìm thấy hình ảnh.");
            }

            // Các phần còn lại của mã để hiển thị thông tin sản phẩm
            String name = topping.getNameTopping();
            jLabelXemTenTopping2.setText(name);
            int price = topping.getPriceTopping();
            jLabelXemGiaTopping2.setText(String.valueOf(price));
            String descript = topping.getDescriptionTopping();
//        jTextAreaXemMotaTopping.setText(descript);
//        jTextAreaXemMotaTopping.setEditable(false);
//        jTextAreaXemMotaTopping.setLineWrap(true); // Tự động xuống dòng
//        jTextAreaXemMotaTopping.setWrapStyleWord(true); // Xuống dòng dựa trên từ
        } else {
            // Xử lý khi không tìm thấy sản phẩm
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tìm kiếm sản phẩm.");
        }
    }//GEN-LAST:event_jButtonXemToppingActionPerformed

    private void jTextFieldSearchInManagToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchInManagToppingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchInManagToppingActionPerformed

    private void jButtonSearchInManageToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchInManageToppingActionPerformed
        // Lấy từ khóa tìm kiếm và loại bỏ dấu, chuyển về chữ thường
        String output = jTextFieldSearchInManagTopping.getText().trim().toLowerCase();
        String searchText = removeAccents(output);

        // Kiểm tra xem searchText có bằng chuỗi mặc định không
        if (output.equals("tìm theo tên/giá/mã...")) {
            // Nếu không có từ khóa tìm kiếm, hiển thị lại bảng đầy đủ
            jTableTP.setRowSorter(null);
            GetToppingFromDatabaseController.getListToppingShort(jTableTP);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTableTP.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) jTableTP.getRowSorter();

        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            jTableTP.setRowSorter(sorter);
        }

        RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                // Duyệt qua tất cả các cột trong mỗi hàng
                for (int i = 0; i < entry.getValueCount(); i++) {
                    // Lấy giá trị trong ô
                    String cellValue = entry.getStringValue(i).toLowerCase();

                    // Loại bỏ dấu
                    String normalizedCellValue = Normalizer.normalize(cellValue, Normalizer.Form.NFD)
                            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

                    // Kiểm tra xem từ khóa tìm kiếm có xuất hiện trong giá trị ô không
                    if (normalizedCellValue.contains(searchText)) {
                        return true; // Nếu có, bao gồm hàng này trong kết quả tìm kiếm
                    }
                }
                return false; // Nếu không, loại bỏ hàng này khỏi kết quả tìm kiếm
            }
        };

        sorter.setRowFilter(rowFilter);
    }//GEN-LAST:event_jButtonSearchInManageToppingActionPerformed

    private void jButtonThemAnhToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemAnhToppingActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String defaultDirectory = new File("src/main/resources/img").getAbsolutePath();
        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "png", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String fileName = f.getName();

//            String fileName_full = "D:\\alang\\D21CQAT01-N\\HKII-Nam3\\CongNghePhanMem\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + f.getName();
            jTextFieldThemAnhTopping.setText(fileName);
//            jLabelHienAnh.setIcon(new ImageIcon(fileName_full));
        }
    }//GEN-LAST:event_jButtonThemAnhToppingActionPerformed

    private void jButtonHuyThemToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyThemToppingActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện hành động khi người dùng xác nhận hủy
//            jTextAreaMoTaTopping.setText("");
            jTextFieldGiaTopping.setText("");
            jTextFieldTenTopping.setText("");
            jTextFieldThemAnhTopping.setText("");
        }

    }//GEN-LAST:event_jButtonHuyThemToppingActionPerformed

    private void jButtonHoanTatThemToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoanTatThemToppingActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (jTextFieldTenTopping.getText().isEmpty() || jTextFieldThemAnhTopping.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc phương thức nếu một trong các trường thông tin thiếu
        }

        String name = jTextFieldTenTopping.getText();
        String image = "/img/" + jTextFieldThemAnhTopping.getText();

//        String description = jTextAreaMoTaTopping.getText();
        int price = 0;

        try {
            price = Integer.parseInt(jTextFieldGiaTopping.getText());
            AddProductController.addTopping(name, image, price);
            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            GetToppingFromDatabaseController.getListToppingShort(jTableTP);
//            GetToppingFromDatabaseController.getListTopping(jTableTopping);
//            jTextAreaMoTaTopping.setText("");
            jTextFieldGiaTopping.setText("");
            jTextFieldTenTopping.setText("");
            jTextFieldThemAnhTopping.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá tiền hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        changePanelClear(jPanelXulyTopping);
    }//GEN-LAST:event_jButtonHoanTatThemToppingActionPerformed

    private void jButtonSuaHinhAnhToppinglogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaHinhAnhToppinglogoActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String defaultDirectory = new File("src/main/resources/img").getAbsolutePath();
        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "png", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String fileName = f.getName();

//            String fileName_full = "D:\\alang\\D21CQAT01-N\\HKII-Nam3\\CongNghePhanMem\\DoAnTraSuaTaSu_CNPM\\src\\main\\resources\\img\\" + f.getName();
            jTextFieldSuaHinhAnhTopping.setText(fileName);
//            jLabelHienAnh.setIcon(new ImageIcon(fileName_full));
        }
    }//GEN-LAST:event_jButtonSuaHinhAnhToppinglogoActionPerformed

    private void jButtonHoanTatSuaToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoanTatSuaToppingActionPerformed
        // TODO add your handling code here:
        int rowSelected = getRowInTable.getRow(jTableTP);
        int idTopping = 0;
        if (rowSelected != -1) {
            idTopping = Integer.parseInt(jTableTP.getValueAt(rowSelected, 0).toString());
        } else {
            // Xử lý khi không có dòng nào được chọn
//            changePanelClear(jPanelClear1);
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng thức uống cần sửa");
            return;
        }
        Topping topping = GetToppingFromDatabaseController.getInfoTopping(idTopping);

        String nameTopping = jTextFieldSuaTenTopping.getText().trim();
        String inputText = jTextFieldSuaHinhAnhTopping.getText().trim(); // Lấy tên tệp ảnh mới được chọn

        String imageTopping;
        if (inputText.contains("/img/")) {
            imageTopping = inputText;
        } else {
            imageTopping = "/img/" + inputText;
        }
        String priceText = jTextFieldSuaGiaTopping.getText().trim();
//          String description = jTextAreaSuaMoTaTopping.getText().trim();

        // Kiểm tra các trường dữ liệu có rỗng không
        if (nameTopping.isEmpty() && priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ít nhất một trường thông tin mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc phương thức nếu không có trường thông tin mới nào được nhập
        }

        // Chuyển đổi giá sản phẩm từ chuỗi sang số nguyên
        int priceTopping;
        if (!priceText.isEmpty()) {
            try {
                priceTopping = Integer.parseInt(priceText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Nếu giá sản phẩm không được nhập, sử dụng giá sản phẩm cũ
            priceTopping = topping.getPriceTopping();
        }

        // Kiểm tra xem liệu có hình ảnh mới được chọn không, nếu không, sử dụng hình ảnh cũ
        if (imageTopping.isEmpty()) {
            imageTopping = topping.getImageTopping(); // Sử dụng tên tệp ảnh cũ
        }

        // Kiểm tra xem liệu có mô tả mới được nhập không, nếu không, sử dụng mô tả cũ
//          if (description.isEmpty()) {
//              description = topping.getDescriptionTopping();
//          }
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hoàn tất sửa món này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            boolean check = EditInfoProductController.updtaeInfoToppingDatabase(idTopping, nameTopping, imageTopping, priceTopping);
            if (check) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin món thành công!!");
                // Cập nhật lại bảng thức uống sau khi cập nhật
                GetToppingFromDatabaseController.getListToppingShort(jTableTP);
//                    GetToppingFromDatabaseController.getListTopping(jTableTopping);
//                changePanelClear(jPanelClear1);
                changePanelClear(jPanelXulyTopping);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật thông tin món.");
            }
        }

    }//GEN-LAST:event_jButtonHoanTatSuaToppingActionPerformed

    private void jButtonHuySuaToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuySuaToppingActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            changePanelClear(jPanelClear1);
        }
    }//GEN-LAST:event_jButtonHuySuaToppingActionPerformed

    private void jButtonManageCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManageCategoryActionPerformed
        // Kiểm tra xem ManagerEmployee có được khởi tạo không
        if (ManagerEmployee != null) {
            // Kiểm tra xem ManagerEmployee có chứa khóa "idAccount" không
            if (ManagerEmployee.containsKey("idAccount")) {
                int idAccount = (int) ManagerEmployee.get("idAccount");
                if (idAccount != 0) {
                    System.out.println(idAccount);
                    ManageCategory e = new ManageCategory();
                    e.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Xử lý khi cửa sổ đóng lại
                            jPanelMain.addMouseMotionListener(new MouseAdapter() {
                                @Override
                                public void mouseDragged(MouseEvent e) {
                                    // Lấy tọa độ chuột khi kéo
                                    int x = getLocation().x + e.getX() - initialClick.x;
                                    int y = getLocation().y + e.getY() - initialClick.y;

                                    // Di chuyển cửa sổ đến vị trí mới
                                    setLocation(x, y);
                                }
                            });
                            setSizeTableMT();
                            setSizeTableTP();
                            setSizeTableTS();
                            setSizeTableTPShort();
//        GetMilkTeaFromDatabaseController.getListMilkTea(jTableMilkTea);
//        GetToppingFromDatabaseController.getListTopping(jTableTopping);
                            GetMilkTeaFromDatabaseController.getListMilkTeaShort(jTableTS);
                            GetToppingFromDatabaseController.getListToppingShort(jTableTP);

                            hideButtonInInfo();
                            hideButtonYesNoInInfo();
//        showButtonInInfo();
                            FunctionDisplay();
                            setScroll(allProduct);
                            setScroll(jScrollPane5);

                        }
                    });
                    e.setBounds(410, 150, 705, 415);
                    e.setVisible(true);
                } else {
                    System.out.println("Giá trị idAccount không hợp lệ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần đăng nhập để thao tác!");
                System.out.println("Không tìm thấy khóa 'idAccount' trong ManagerEmployee");
            }
        } else {
            System.out.println("ManagerEmployee chưa được khởi tạo");
        }

    }//GEN-LAST:event_jButtonManageCategoryActionPerformed

    private void jPanelNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNavbarMouseClicked

    }//GEN-LAST:event_jPanelNavbarMouseClicked

    private void jLabelbuttoncloseMenuNavbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelbuttoncloseMenuNavbarMouseClicked
        // TODO add your handling code here:
        closeMenuNavbar();
    }//GEN-LAST:event_jLabelbuttoncloseMenuNavbarMouseClicked

    private void jLabelHomeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHomeMenuMouseClicked
        // TODO add your handling code here:
        openMenuNavbar();
    }//GEN-LAST:event_jLabelHomeMenuMouseClicked

    private void jLabelChooseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChooseMenuMouseClicked
        // TODO add your handling code here:
        openMenuNavbar();
    }//GEN-LAST:event_jLabelChooseMenuMouseClicked

    private void jLabelBillMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBillMenuMouseClicked
        // TODO add your handling code here:
        openMenuNavbar();
    }//GEN-LAST:event_jLabelBillMenuMouseClicked

    private void jLabelInfoMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInfoMenuMouseClicked
        // TODO add your handling code here:
        openMenuNavbar();
    }//GEN-LAST:event_jLabelInfoMenuMouseClicked

    private void jLabelManageProductMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelManageProductMenuMouseClicked
        // TODO add your handling code here:
        openMenuNavbar();
    }//GEN-LAST:event_jLabelManageProductMenuMouseClicked

    private void jTextFieldInfoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoNameActionPerformed

    private void jTextFieldInfoBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoBirthDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoBirthDateActionPerformed

    private void jTextFieldInfoGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoGenderActionPerformed

    private void jTextFieldInfoCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoCCCDActionPerformed

    private void jTextFieldInfoRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoRoleActionPerformed

    ///Các biến lấy lại giá trị
    private String initialName;
    private String initialBirtday;
    private String initialGender;
    private String initialCCCD;
    private String initialAddress;
    private JDateChooser jDateChooser;
    private JComboBox<String> comboBox;

    private void jButtonInfoSuaGioTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoSuaGioTinhActionPerformed
        initialGender = jTextFieldInfoGender.getText();
        int x = jTextFieldInfoGender.getX();
        int y = jTextFieldInfoGender.getY();
        int width = jTextFieldInfoGender.getWidth();
        int height = jTextFieldInfoGender.getHeight();

        // Kiểm tra container trước khi thực hiện các thao tác
        if (jTextFieldInfoGender.getParent() != null) {
            Container container = jTextFieldInfoGender.getParent();
            container.remove(jTextFieldInfoGender);

            comboBox = new JComboBox<>();
            comboBox.addItem("Nữ");
            comboBox.addItem("Nam");
            comboBox.addItem("Khác");
            comboBox.setBounds(x, y, width, height);
            Font font = new Font("Segoe Ui", Font.PLAIN, 22);
            comboBox.setFont(font);
            container.add(comboBox);
            container.revalidate();
            container.repaint();
            jButtonInfoNoSuaGioiTinh.setVisible(true);
            jButtonInfoYesSuaGioiTinh.setVisible(true);
        } else {
            // Xử lý trường hợp container không tồn tại
            System.out.println("Container is null!");
        }
    }//GEN-LAST:event_jButtonInfoSuaGioTinhActionPerformed

    private void jButtonInfoSuaCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoSuaCCCDActionPerformed
        initialCCCD = jTextFieldInfoCCCD.getText();
        jTextFieldInfoCCCD.setEditable(true);
        jTextFieldInfoCCCD.setBackground(Color.WHITE);
        jTextFieldInfoCCCD.setForeground(Color.BLACK);
        jButtonInfoYesSuaCCCD.setVisible(true);
        jButtonInfoNoSuaCCCD.setVisible(true);


    }//GEN-LAST:event_jButtonInfoSuaCCCDActionPerformed

    private void jButtonInfoSuaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoSuaTenActionPerformed
        initialName = jTextFieldInfoName.getText();
        jTextFieldInfoName.setEditable(true);
        jTextFieldInfoName.setBackground(Color.WHITE);
        jTextFieldInfoName.setForeground(Color.BLACK);
        jButtonInfoYesSuaTen.setVisible(true);
        jButtonInfoNoSuaTen.setVisible(true);
    }//GEN-LAST:event_jButtonInfoSuaTenActionPerformed

    private void jButtonInfoNoSuaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoNoSuaTenActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Yes"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện các hành động khi người dùng chấp nhận hủy sửa
            jTextFieldInfoName.setEditable(false);
            jTextFieldInfoName.setBackground(new Color(0, 140, 140));
            jTextFieldInfoName.setForeground(new Color(255, 255, 153));
            jTextFieldInfoName.setText(initialName);
            jButtonInfoNoSuaTen.setVisible(false);
            jButtonInfoYesSuaTen.setVisible(false);
        } else {
            jButtonInfoYesSuaTen.setVisible(true);
            jButtonInfoNoSuaTen.setVisible(true);
            jTextFieldInfoName.setEditable(true);
            jTextFieldInfoName.setBackground(Color.WHITE);
            jTextFieldInfoName.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jButtonInfoNoSuaTenActionPerformed

    private void jButtonInfoYesSuaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoYesSuaTenActionPerformed
        try {
            String newName = Controller.LogRes.ManagerEmployee.Login.chuanHoaTen(jTextFieldInfoName.getText());

            int idAccount = (int) ManagerEmployee.get("idAccount");
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi tên nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                boolean x = EditInfoEmployeeController.updateNameEmployee(newName, idAccount);
                if (x == true) {
                    JOptionPane.showMessageDialog(this, "Sửa tên nhân viên thành công!");
                    jTextFieldInfoName.setEditable(false);
                    jTextFieldInfoName.setBackground(new Color(0, 140, 140));
                    jTextFieldInfoName.setForeground(new Color(255, 255, 153));
                    jButtonInfoYesSuaTen.setVisible(false);
                    jButtonInfoNoSuaTen.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, sủa tên nhân viên thất bại!");
                    jTextFieldInfoName.setEditable(false);
                    jTextFieldInfoName.setBackground(new Color(0, 140, 140));
                    jTextFieldInfoName.setForeground(new Color(255, 255, 153));
                }
            } else {
                jTextFieldInfoName.setEditable(false);
                jTextFieldInfoName.setBackground(new Color(0, 140, 140));
                jTextFieldInfoName.setForeground(new Color(255, 255, 153));
                jTextFieldInfoName.setText(initialName);
                jButtonInfoNoSuaTen.setVisible(false);
                jButtonInfoYesSuaTen.setVisible(false);
            }
        } catch (HeadlessException headlessException) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");

        }
    }//GEN-LAST:event_jButtonInfoYesSuaTenActionPerformed

    private void jButtonInfoYesSuaGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoYesSuaGioiTinhActionPerformed
        try {
            int newGender = comboBox.getSelectedIndex();
            int idAccount = (int) ManagerEmployee.get("idAccount");
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi giới tính nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (comboBox != null && comboBox.getParent() != null) {
                    boolean c = EditInfoEmployeeController.updateGenderEmployee(newGender, idAccount);
                    if (c) {
                        JOptionPane.showMessageDialog(this, "Sửa giới tính nhân viên thành công!");

                        int x = comboBox.getX();
                        int y = comboBox.getY();
                        int width = comboBox.getWidth();
                        int height = comboBox.getHeight();
                        Container container = comboBox.getParent();
                        container.remove(comboBox);

                        // Thêm lại jTextFieldInfoGender vào container
                        container.add(jTextFieldInfoGender);

                        jTextFieldInfoGender.setEditable(false);
                        jTextFieldInfoGender.setBackground(new Color(0, 140, 140));
                        jTextFieldInfoGender.setForeground(new Color(255, 255, 153));
                        jTextFieldInfoGender.setText((String) comboBox.getSelectedItem());
                        jButtonInfoNoSuaGioiTinh.setVisible(false);
                        jButtonInfoYesSuaGioiTinh.setVisible(false);

                        // Cập nhật lại giao diện
                        container.revalidate();
                        container.repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, sửa giới tính nhân viên thất bại!");
                        jTextFieldInfoGender.setBackground(new Color(0, 140, 140));
                        jTextFieldInfoGender.setForeground(new Color(255, 255, 153));
                    }
                } else {
                    // Xử lý trường hợp comboBox hoặc container là null
                    System.out.println("comboBox or its parent container is null!");
                }
            } else {
                jButtonInfoYesSuaGioiTinh.setVisible(true);
                jButtonInfoNoSuaGioiTinh.setVisible(true);
                jTextFieldInfoGender.setEditable(true);
                jTextFieldInfoGender.setBackground(Color.WHITE);
                jTextFieldInfoGender.setForeground(Color.BLACK);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");
        }

    }//GEN-LAST:event_jButtonInfoYesSuaGioiTinhActionPerformed

    private void jButtonInfoNoSuaGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoNoSuaGioiTinhActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Yes"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện các hành động khi người dùng chấp nhận hủy sửa
            if (comboBox != null && comboBox.getParent() != null) {
                int x = comboBox.getX();
                int y = comboBox.getY();
                int width = comboBox.getWidth();
                int height = comboBox.getHeight();
                Container container = comboBox.getParent();
                container.remove(comboBox);

                // Thêm lại jTextFieldInfoBirthDate vào container
                container.add(jTextFieldInfoGender);

                jTextFieldInfoGender.setEditable(false);
                jTextFieldInfoGender.setBackground(new Color(0, 140, 140));
                jTextFieldInfoGender.setForeground(new Color(255, 255, 153));
                jTextFieldInfoGender.setText(initialGender);
                jButtonInfoNoSuaGioiTinh.setVisible(false);
                jButtonInfoYesSuaGioiTinh.setVisible(false);

                // Cập nhật lại giao diện
                container.revalidate();
                container.repaint();
            } else {
                // Xử lý trường hợp comboBox hoặc container là null
                System.out.println("comboBox or its parent container is null!");
            }
        } else {
            jButtonInfoYesSuaGioiTinh.setVisible(true);
            jButtonInfoNoSuaGioiTinh.setVisible(true);
            jTextFieldInfoGender.setEditable(true);
            jTextFieldInfoGender.setBackground(Color.WHITE);
            jTextFieldInfoGender.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jButtonInfoNoSuaGioiTinhActionPerformed

    private void jButtonInfoNoSuaCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoNoSuaCCCDActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Yes"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện các hành động khi người dùng chấp nhận hủy sửa
            jTextFieldInfoCCCD.setEditable(false);
            jTextFieldInfoCCCD.setBackground(new Color(0, 140, 140));
            jTextFieldInfoCCCD.setForeground(new Color(255, 255, 153));
            jTextFieldInfoCCCD.setText(initialCCCD);
            jButtonInfoNoSuaCCCD.setVisible(false);
            jButtonInfoYesSuaCCCD.setVisible(false);
        } else {
            jButtonInfoYesSuaCCCD.setVisible(true);
            jButtonInfoNoSuaCCCD.setVisible(true);
            jTextFieldInfoCCCD.setEditable(true);
            jTextFieldInfoCCCD.setBackground(Color.WHITE);
            jTextFieldInfoCCCD.setForeground(Color.BLACK);
        }


    }//GEN-LAST:event_jButtonInfoNoSuaCCCDActionPerformed

    private void jButtonInfoYesSuaCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoYesSuaCCCDActionPerformed
        try {
            String newCCCD = jTextFieldInfoCCCD.getText();
            int idAccount = (int) ManagerEmployee.get("idAccount");
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi CCCD nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (!Account.isValidCCCD(newCCCD)) {
                    System.out.println(newCCCD);
                    JOptionPane.showMessageDialog(this, "Căn cước căn dân của bạn không hợp lệ!!");
                    jTextFieldInfoCCCD.setEditable(false);
                    jTextFieldInfoCCCD.setBackground(new Color(0, 140, 140));
                    jTextFieldInfoCCCD.setForeground(new Color(255, 255, 153));
                    jTextFieldInfoCCCD.setText(initialCCCD);
                    jButtonInfoNoSuaCCCD.setVisible(false);
                    jButtonInfoYesSuaCCCD.setVisible(false);
                    return;
                }
                boolean x = EditInfoEmployeeController.updateCCCDEmployee(newCCCD, idAccount);
                if (x == true) {
                    JOptionPane.showMessageDialog(this, "Sửa CCCD nhân viên thành công!");
                    jTextFieldInfoCCCD.setEditable(false);
                    jTextFieldInfoCCCD.setBackground(new Color(0, 140, 140));
                    jTextFieldInfoCCCD.setForeground(new Color(255, 255, 153));
                    jButtonInfoYesSuaCCCD.setVisible(false);
                    jButtonInfoNoSuaCCCD.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, sủa CCCD nhân viên thất bại!");
                    jTextFieldInfoCCCD.setEditable(false);
                    jTextFieldInfoCCCD.setBackground(new Color(0, 140, 140));
                    jTextFieldInfoCCCD.setForeground(new Color(255, 255, 153));
                }
            } else {
                jTextFieldInfoCCCD.setEditable(false);
                jTextFieldInfoCCCD.setBackground(new Color(0, 140, 140));
                jTextFieldInfoCCCD.setForeground(new Color(255, 255, 153));
                jTextFieldInfoCCCD.setText(initialCCCD);
                jButtonInfoNoSuaCCCD.setVisible(false);
                jButtonInfoYesSuaCCCD.setVisible(false);
            }
        } catch (HeadlessException headlessException) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");

        }
    }//GEN-LAST:event_jButtonInfoYesSuaCCCDActionPerformed


    private void jButtonInfoSuaNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoSuaNgaySinhActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        initialBirtday = jTextFieldInfoBirthDate.getText();
        int x = jTextFieldInfoBirthDate.getX();
        int y = jTextFieldInfoBirthDate.getY();
        int width = jTextFieldInfoBirthDate.getWidth();
        int height = jTextFieldInfoBirthDate.getHeight();

        // Kiểm tra container trước khi thực hiện các thao tác
        if (jTextFieldInfoBirthDate.getParent() != null) {
            Container container = jTextFieldInfoBirthDate.getParent();
            container.remove(jTextFieldInfoBirthDate);

            jDateChooser = new JDateChooser();
            jDateChooser.setBounds(x, y, width, height);
            jDateChooser.setDateFormatString("dd/MM/yyyy");
            Font font = new Font("Segoe Ui", Font.PLAIN, 16);
            jDateChooser.setFont(font);
            try {
                jDateChooser.setDate(sdf.parse((String) ManagerEmployee.get("birthday")));
            } catch (ParseException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            container.add(jDateChooser);
            container.revalidate();
            container.repaint();
            jButtonInfoNoSuaNgaySinh.setVisible(true);
            jButtonInfoYesSuaNgaySinh.setVisible(true);
        } else {
            // Xử lý trường hợp container không tồn tại
            System.out.println("Container is null!");
        }
    }//GEN-LAST:event_jButtonInfoSuaNgaySinhActionPerformed

    private void jButtonInfoNoSuaNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoNoSuaNgaySinhActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Yes"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện các hành động khi người dùng chấp nhận hủy sửa
            if (jDateChooser.getParent() != null) {
                int x = jDateChooser.getX();
                int y = jDateChooser.getY();
                int width = jDateChooser.getWidth();
                int height = jDateChooser.getHeight();
                Container container = jDateChooser.getParent();
                container.remove(jDateChooser);

                // Thêm lại jTextFieldInfoBirthDate vào container
                container.add(jTextFieldInfoBirthDate);

                jTextFieldInfoBirthDate.setEditable(false);
                jTextFieldInfoBirthDate.setBackground(new Color(0, 140, 140));
                jTextFieldInfoBirthDate.setForeground(new Color(255, 255, 153));
                jTextFieldInfoBirthDate.setText(initialBirtday);
                jButtonInfoNoSuaNgaySinh.setVisible(false);
                jButtonInfoYesSuaNgaySinh.setVisible(false);

                // Cập nhật lại giao diện
                container.revalidate();
                container.repaint();
            } else {
                // Xử lý trường hợp container không tồn tại
                System.out.println("Container is null!");
            }
        } else {
            jButtonInfoYesSuaNgaySinh.setVisible(true);
            jButtonInfoNoSuaNgaySinh.setVisible(true);
            jTextFieldInfoBirthDate.setEditable(true);
            jTextFieldInfoBirthDate.setBackground(Color.WHITE);
            jTextFieldInfoBirthDate.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_jButtonInfoNoSuaNgaySinhActionPerformed

    private void jButtonInfoYesSuaNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoYesSuaNgaySinhActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = sdf.format(jDateChooser.getDate());
            System.out.println(Account.calculateAge(newDate));
            if (Account.calculateAge(newDate) < 18) {
                JOptionPane.showMessageDialog(this, "Bạn chưa đủ 18 tuổi. Thử lại!!");
                return;
            } else {
                int idAccount = (int) ManagerEmployee.get("idAccount");
                int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi ngày sinh nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    boolean c = EditInfoEmployeeController.updateBirthdayEmployee(newDate, idAccount);
                    if (c) {
                        JOptionPane.showMessageDialog(this, "Sửa ngày sinh nhân viên thành công!");

                        if (jDateChooser.getParent() != null) {
                            int x = jDateChooser.getX();
                            int y = jDateChooser.getY();
                            int width = jDateChooser.getWidth();
                            int height = jDateChooser.getHeight();
                            Container container = jDateChooser.getParent();
                            container.remove(jDateChooser);

                            // Thêm lại jTextFieldInfoBirthDate vào container
                            container.add(jTextFieldInfoBirthDate);

                            jTextFieldInfoBirthDate.setEditable(false);
                            jTextFieldInfoBirthDate.setBackground(new Color(0, 140, 140));
                            jTextFieldInfoBirthDate.setForeground(new Color(255, 255, 153));
                            jTextFieldInfoBirthDate.setText(newDate);
                            jButtonInfoNoSuaNgaySinh.setVisible(false);
                            jButtonInfoYesSuaNgaySinh.setVisible(false);

                            // Cập nhật lại giao diện
                            container.revalidate();
                            container.repaint();
                        } else {
                            // Xử lý trường hợp container không tồn tại
                            System.out.println("Container is null!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, sửa ngày sinh nhân viên thất bại!");
                        jTextFieldInfoBirthDate.setBackground(new Color(0, 140, 140));
                        jTextFieldInfoBirthDate.setForeground(new Color(255, 255, 153));
                    }
                } else {
                    jButtonInfoYesSuaNgaySinh.setVisible(true);
                    jButtonInfoNoSuaNgaySinh.setVisible(true);
                    jTextFieldInfoBirthDate.setEditable(true);
                    jTextFieldInfoBirthDate.setBackground(Color.WHITE);
                    jTextFieldInfoBirthDate.setForeground(Color.BLACK);
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");
        }

    }//GEN-LAST:event_jButtonInfoYesSuaNgaySinhActionPerformed

    private void jButtonInfoSuaDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoSuaDiaChiActionPerformed
        initialAddress = jTextAreaAddress.getText();
        jTextAreaAddress.setEditable(true);
        jTextAreaAddress.setBackground(Color.WHITE);
        jTextAreaAddress.setForeground(Color.BLACK);
        jButtonInfoYesSuaDiaChi.setVisible(true);
        jButtonInfoNoSuaDiaChi.setVisible(true);
    }//GEN-LAST:event_jButtonInfoSuaDiaChiActionPerformed

    private void jButtonInfoNoSuaDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoNoSuaDiaChiActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        // Nếu người dùng chọn "Yes"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện các hành động khi người dùng chấp nhận hủy sửa
            jTextAreaAddress.setEditable(false);
            jTextAreaAddress.setBackground(new Color(0, 140, 140));
            jTextAreaAddress.setForeground(new Color(255, 255, 153));
            jTextAreaAddress.setText(initialAddress);
            jButtonInfoNoSuaDiaChi.setVisible(false);
            jButtonInfoYesSuaDiaChi.setVisible(false);
        } else {
            jButtonInfoYesSuaDiaChi.setVisible(true);
            jButtonInfoNoSuaDiaChi.setVisible(true);
            jTextAreaAddress.setEditable(true);
            jTextAreaAddress.setBackground(Color.WHITE);
            jTextAreaAddress.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jButtonInfoNoSuaDiaChiActionPerformed

    private void jButtonInfoYesSuaDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoYesSuaDiaChiActionPerformed
        try {
            String newAddress = jTextAreaAddress.getText();
            int idAccount = (int) ManagerEmployee.get("idAccount");
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi quê quán nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                boolean x = EditInfoEmployeeController.updateAddressEmployee(newAddress, idAccount);
                if (x == true) {
                    JOptionPane.showMessageDialog(this, "Sửa quê quán nhân viên thành công!");
                    jTextAreaAddress.setEditable(false);
                    jTextAreaAddress.setBackground(new Color(0, 140, 140));
                    jTextAreaAddress.setForeground(new Color(255, 255, 153));
                    jButtonInfoYesSuaDiaChi.setVisible(false);
                    jButtonInfoNoSuaDiaChi.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, sủa quê quán nhân viên thất bại!");
                    jTextAreaAddress.setEditable(false);
                    jTextAreaAddress.setBackground(new Color(0, 140, 140));
                    jTextAreaAddress.setForeground(new Color(255, 255, 153));
                }
            } else {
                jTextAreaAddress.setEditable(false);
                jTextAreaAddress.setBackground(new Color(0, 140, 140));
                jTextAreaAddress.setForeground(new Color(255, 255, 153));
                jTextAreaAddress.setText(initialAddress);
                jButtonInfoNoSuaDiaChi.setVisible(false);
                jButtonInfoYesSuaDiaChi.setVisible(false);
            }
        } catch (HeadlessException headlessException) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!!");

        }
    }//GEN-LAST:event_jButtonInfoYesSuaDiaChiActionPerformed

    private void jTextFieldInfoPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoPhoneActionPerformed

//    public static Container frameEmployeeContainer;
    private void jTextFieldInfoPhoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldInfoPhoneMouseClicked
        int check = JOptionPane.showConfirmDialog(this, "Bạn muốn đổi số điện thoại?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            ChangeNumberPhoneEmployee e = new ChangeNumberPhoneEmployee();
            e.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
//                
                    jTextFieldInfoPhone.setText((String) ManagerEmployee.get("numberPhone"));
                }
            });
            e.setBounds(650, 190, 250, 350);
            e.setVisible(true);

        }
    }//GEN-LAST:event_jTextFieldInfoPhoneMouseClicked

    private void jTextFieldInfoSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoSalaryActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        int check = JOptionPane.showConfirmDialog(this, "Bạn muốn đổi mật khẩu ?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (check == JOptionPane.YES_OPTION) {
            ChangePassEmployee e = new ChangePassEmployee();
            e.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    System.out.println(ManagerEmployee.get("password"));
                    if (ChangePassEmployee.check == true) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Login e = new Login();
                                e.setVisible(true);

                            }
                        }).start();
                        dispose();
                    }

//                
                }
            });
            e.setBounds(650, 160, 286, 438);
            e.setVisible(true);
        }

    }//GEN-LAST:event_jLabel9MouseClicked

    private void jComboBoxSuaLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSuaLoaiMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSuaLoaiMonActionPerformed

    private void jTextFieldTenTraSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenTraSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenTraSuaActionPerformed

    private void giohangchonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giohangchonMouseClicked

        if (!giohang1.isVisible()) {
            giohang1 = new JDialog();
            giohang1.setUndecorated(true);
            giohang1.setResizable(false);
            giohang1.setLocationRelativeTo(null);
            giohang1.setAlwaysOnTop(true);
            giohang1.setBounds(470, 80, 600, 600);
            giohang1.add(hienGioHang);
            giohang1.setVisible(true);
            setProductCart();

            JPanel gh = new JPanel();
            gh.setLayout(new BoxLayout(gh, BoxLayout.Y_AXIS));

            for (int i = 0; i < Items.size(); i++) {
                Map<String, Object> item = Items.get(i);
                System.out.println(item);
                Item it = new Item();
                int soLuongItem = (int) item.get("quantityProduct");
                int price = (int) item.get("price") * soLuongItem;
                String sizeItem = "";
                if ((int) item.get("idSize") == 2) {
                    sizeItem = "Size L- ";
                } else if ((int) item.get("idSize") == 1) {
                    sizeItem = "Size M- ";
                }
                String anhItem = item.get("imageProduct").toString();

                List<Map<String, Object>> toppings = (List<Map<String, Object>>) item.get("Topping");
                for (Map<String, Object> topping : toppings) {
                    String nameTopping = (String) topping.get("nameTopping");
                    int quantityTopping = (int) topping.get("quantity");
                    if (quantityTopping > 0) {
                        danhSachTopping += nameTopping + " x" + String.valueOf(quantityTopping) + " ";
                    }
                }

                it.getTenItem().setText((String) item.get("nameProduct"));
                it.getTongGiaItem().setText(sizeItem + String.valueOf(price) + "(VND)");
                it.getjSpinner1().setValue(soLuongItem);
                it.getAnh().setIcon(new javax.swing.ImageIcon(getClass().getResource(anhItem)));
                it.getTentopping().setText(danhSachTopping);
                danhSachTopping = "";
                it.getTentopping().setName(String.valueOf(i));
                gh.add(it);
                gh.add(Box.createVerticalStrut(2));
                updateTongThanhToan2();

                it.getjSpinner1().addChangeListener(new ChangeListener() {
                    int slc = (int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("quantityProduct");
                    int priceOld = (int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("price");

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        priceOld = (int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("price");
                        slc = (int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("quantityProduct");
                        Items.get(Integer.parseInt(it.getTentopping().getName())).put("quantityProduct", it.getjSpinner1().getValue());
                        int slm = (int) it.getjSpinner1().getValue();
                        System.out.println(slc + " " + slm);
                        int priceNew = ((int) item.get("price")) * slm;
//                         Items.get( Integer.parseInt( it.getTentopping().getName())).put("price", priceNew);

                        String sizeItem = "";
                        if ((int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("idSize") == 2) {
                            sizeItem = "Size L- ";
                        } else if ((int) Items.get(Integer.parseInt(it.getTentopping().getName())).get("idSize") == 1) {
                            sizeItem = "Size M- ";
                        }
                        it.getTongGiaItem().setText(sizeItem + String.valueOf(priceNew) + "(VND)");

                        updateTongThanhToan2();
                    }
                });

                it.getXoaItem().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Items.remove(Items.get(Integer.parseInt(it.getTentopping().getName())));
                        updateTongThanhToan2();
                        gh.remove(it);
                        demslitem -= 1;
                        jlabelSLTrongGioHang.setVisible(true);
                        jlabelSLTrongGioHang.setText(Integer.toString(demslitem));
                        if (demslitem == 0) {
                            jlabelSLTrongGioHang.setVisible(false);
                        }
                        gh.revalidate();
                        gh.repaint();
                        if (Items.size() < 2) {
                            JPanel pn = new JPanel();
                            pn.setBackground(new java.awt.Color(0, 51, 51));
                            pn.setPreferredSize(new java.awt.Dimension(585, 290));

                            for (int j = 0; j < 2 - Items.size(); j++) {
                                gh.add(pn);
                            }
                        }
                    }
                });

                it.getChitiet().addMouseListener(new MouseAdapter() {
                    int index = Integer.parseInt(it.getTentopping().getName());

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Map<String, Object> item = Items.get(index);
                        if (!chonLaiTP.isVisible()) {
                            chonLaiTP = new JDialog();
                            chonLaiTP.setUndecorated(true);
                            chonLaiTP.setResizable(false);
                            chonLaiTP.setLocationRelativeTo(null);
                            chonLaiTP.setAlwaysOnTop(true);
                            chonLaiTP.setBounds(490, 100, 580, 520);
                            chonLaiTP.setBackground(Color.white);
                            chonLaiTP.add(chonLaiTop);
                            chonLaiTP.setVisible(true);
                            setReviewTopping();

                            tenItemChonLaiTopping = (String) item.get("nameProduct");
                            slItemChonLaiTopping = String.valueOf(item.get("quantityProduct"));
                            tenItem.setText(tenItemChonLaiTopping + " x" + slItemChonLaiTopping);

                            JPanel rct = new JPanel();
                            rct.setLayout(new BoxLayout(rct, BoxLayout.Y_AXIS));

                            getToppings();
                            for (int i = 0; i < Toppings.size(); i++) {
                                Map<String, Object> topping = Toppings.get(i);
                                ReViewTopping rc = new ReViewTopping();
                                List<Map<String, Object>> toppings = (List<Map<String, Object>>) item.get("Topping");
                                int idtp = Integer.parseInt((String) topping.get("idTopping"));
                                for (Map<String, Object> tp : toppings) {
                                    int idTopping = (int) tp.get("idTopping");
                                    int quantityTopping = (int) tp.get("quantity");
                                    if (idtp == idTopping) {
                                        rc.getjSpinnerSLTP().setValue(quantityTopping);
                                        if (quantityTopping > 0) {
                                            rc.setBackground(new java.awt.Color(0, 0, 153));
                                            rc.getTenTopping().setForeground(Color.white);
                                            rct.revalidate();
                                            rct.repaint();
                                        } else {
                                            rc.setBackground(new java.awt.Color(255, 255, 255));
                                            rc.getTenTopping().setForeground(Color.black);
                                            rct.revalidate();
                                            rct.repaint();
                                        }
                                    }
                                }
                                rc.getTenTopping().setText((String) topping.get("nameTopping"));
                                rc.getjSpinnerSLTP().setName(String.valueOf(idtp));
                                rc.getAnhTopping().setIcon(new javax.swing.ImageIcon(getClass().getResource((String) topping.get("imageTopping"))));
                                rc.getAnhTopping().setName((String) topping.get("imageTopping"));
                                rc.getGiaTopping().setText(String.valueOf(topping.get("priceTopping")));

                                rc.getjSpinnerSLTP().addChangeListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        int sl = (int) rc.getjSpinnerSLTP().getValue();
                                        if (sl > 0) {
                                            rc.setBackground(new java.awt.Color(0, 0, 153));
                                            rc.getTenTopping().setForeground(Color.white);
                                            rct.revalidate();
                                            rct.repaint();
                                        } else {
                                            rc.setBackground(new java.awt.Color(255, 255, 255));
                                            rc.getTenTopping().setForeground(Color.black);
                                            rct.revalidate();
                                            rct.repaint();
                                        }

                                    }
                                });

                                OKChonLaiTopping.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        List<Map<String, Object>> updatedToppings = new ArrayList<>();
                                        Map<String, Object> item = Items.get(index);
                                        int toppingTotalPrice = 0;

                                        for (Component component : rct.getComponents()) {
                                            if (component instanceof ReViewTopping) {
                                                ReViewTopping rc = (ReViewTopping) component;
                                                int idTopping = Integer.parseInt(rc.getjSpinnerSLTP().getName());
                                                int quantity = (int) rc.getjSpinnerSLTP().getValue();
                                                String img = rc.getAnhTopping().getName();
                                                String ten = rc.getTenTopping().getText();
                                                int priceTp = Integer.parseInt(rc.getGiaTopping().getText());

                                                if (quantity > 0) {
                                                    Map<String, Object> updatedTopping = new HashMap<>();
                                                    updatedTopping.put("idTopping", idTopping);
                                                    updatedTopping.put("quantity", quantity);
                                                    updatedTopping.put("nameTopping", ten);
                                                    updatedTopping.put("priceTopping", priceTp);
                                                    updatedTopping.put("imageTopping", img);
                                                    updatedToppings.add(updatedTopping);

                                                    toppingTotalPrice += priceTp * quantity;
                                                }
                                            }
                                        }

                                        Items.get(Integer.parseInt(it.getTentopping().getName())).put("Topping", updatedToppings);
                                        int basePrice = (int) item.get("priceSize");
                                        int quantityProduct = (int) item.get("quantityProduct");
                                        int newPrice = (basePrice + toppingTotalPrice);
                                        Items.get(Integer.parseInt(it.getTentopping().getName())).put("price", newPrice);

                                        updateTongThanhToan2();
                                        chonLaiTP.dispose();
                                        gh.revalidate();
                                        gh.repaint();

                                        String updatedDanhSachTopping = "";
                                        for (Map<String, Object> topping : updatedToppings) {
                                            String nameTopping = (String) topping.get("nameTopping");
                                            int quantityTopping = (int) topping.get("quantity");
                                            if (quantityTopping > 0) {
                                                updatedDanhSachTopping += nameTopping + " x" + String.valueOf(quantityTopping) + " ";
                                            }
                                        }
                                        updateTongThanhToan();
                                        it.getTentopping().setText(updatedDanhSachTopping);
                                        it.getTongGiaItem().setText(String.valueOf(newPrice) + "(VND)");

                                    }

                                });

                                rct.add(rc);
                                rct.add(Box.createVerticalStrut(2));
                            }

                            for (Map<String, Object> topping : toppings) {
                                String nameTopping = (String) topping.get("nameTopping");
                                int quantityTopping = (int) topping.get("quantity");
                                if (quantityTopping > 0) {
                                    danhSachTopping += nameTopping + " x" + String.valueOf(quantityTopping) + " ";
                                }
                            }
                            it.getTentopping().setText(danhSachTopping);
                            danhSachTopping = "";
                            giohang1.dispose();
                            updateTongThanhToan();
                            gh.revalidate();
                            gh.repaint();
                            jScrollPaneChonLaiTopping.setViewportView(rct);
                        }
                    }
                });
            }

            tenItemChonLaiTopping = "";
            slItemChonLaiTopping = "";
            jScrollPaneGioHang.setViewportView(gh);
            if (Items.size() < 2) {
                JPanel pn = new JPanel();
                pn.setBackground(new java.awt.Color(0, 51, 51));
                pn.setPreferredSize(new java.awt.Dimension(585, 290));
                for (int j = 0; j < 2 - Items.size(); j++) {
                    gh.add(pn);
                }
            }
        }


    }//GEN-LAST:event_giohangchonMouseClicked

    private void jcomboboxHientheoloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxHientheoloaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxHientheoloaiActionPerformed

    private void updateTongThanhToan() {
        tongThanhToan = 0;
        for (Map<String, Object> item : Items) {
            tongThanhToan += (int) item.get("price");

        }

        tongtienThanhToan.setText(new DecimalFormat("#,###").format(tongThanhToan));

    }

    private void updateTongThanhToan2() {
        tongThanhToan = 0;
        for (Map<String, Object> item : Items) {
            tongThanhToan += ((int) item.get("price") * (int) item.get("quantityProduct"));
            System.out.println(item);
        }
        System.out.println(tongThanhToan);
        tongtienThanhToan.setText(new DecimalFormat("#,###").format(tongThanhToan));

    }

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
            java.util.logging.Logger.getLogger(Employee.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                    java.util.logging.Logger.getLogger(Employee.class
                            .getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                new Employee().setVisible(true);
            }
        });
    }

    public void changePanelHome() {
        this.jPanelMain.removeAll();
        this.jPanelMain.add(this.jPanelHome);
        this.jPanelMain.repaint();
        this.jPanelMain.revalidate();

    }

    public void changePanelInfo() {
        this.jPanelMain.removeAll();
        this.jPanelMain.add(this.jPanelInfo);
        this.jPanelMain.repaint();
        this.jPanelMain.revalidate();

    }

    public void changePanelChoose() {
        this.jPanelMain.removeAll();
        this.jPanelMain.add(this.jPanelChoose);
        this.jPanelMain.repaint();
        this.jPanelMain.revalidate();
    }

    public void changePanelManageProduct() {
        this.jPanelMain.removeAll();
        this.jPanelMain.add(this.jPanelManageProduct);
        this.jPanelMain.repaint();
        this.jPanelMain.revalidate();
    }

//    public void changePanelManageCategory(){
//        this.jPanelMain.removeAll();
//        this.jPanelMain.add(this.jPanelManageCategory);
//        this.jPanelMain.repaint();
//        this.jPanelMain.revalidate();
//    }
    public void changePanelBill() {
        this.jPanelMain.removeAll();
        this.jPanelMain.add(this.jPanelBill);
        this.jPanelMain.repaint();
        this.jPanelMain.revalidate();
    }

    public void changePanelClear(JPanel jp) {
        jp.removeAll();
        jp.add(this.jPanelClear);
        jp.repaint();
        jp.revalidate();
    }

    public void changePanelAddProduct(JPanel jp, JPanel jp2) {
        jp.removeAll();
        jp.add(jp2);
        jp.repaint();
        jp.revalidate();
    }

    public void changePanelEditProduct(JPanel jp, JPanel jp2) {
        jp.removeAll();
        jp.add(jp2);
        jp.repaint();
        jp.revalidate();
    }

    public void changePanelDeleteProduct(JPanel jp, JPanel jp2) {
        jp.removeAll();
        jp.add(jp2);
        jp.repaint();
        jp.revalidate();
    }

    public void changePanelViewProduct(JPanel jp, JPanel jp2) {
        jp.removeAll();
        jp.add(jp2);
        jp.repaint();
        jp.revalidate();
    }

    public void setSizeTableMT() {
        Font font = new Font("Serif", Font.PLAIN, 16);

    }

    public void setSizeTableTP() {
        Font font = new Font("Serif", Font.PLAIN, 16);

    }

    public void setSizeTableTS() {
        Font font = new Font("Serif", Font.PLAIN, 16);
        jTableTS.setFont(font);
        jTableTP.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTableTS.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableTS.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTableTS.getColumnModel().getColumn(3).setPreferredWidth(100);

    }

    public void setSizeTableTPShort() {
        Font font = new Font("Serif", Font.PLAIN, 16);
        jTableTP.setFont(font);
        jTableTP.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableTP.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableTP.getColumnModel().getColumn(2).setPreferredWidth(100);

    }

    private static Image makeImageTransparent(Image image, float transparency) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }

    public static void focusLabel(JLabel lable) {

        lable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Thay đổi độ mờ của biểu tượng khi di chuột vào
                ImageIcon icon = (ImageIcon) lable.getIcon();
                Image image = icon.getImage();
                Image newImage = makeImageTransparent(image, 0.5f); // Độ mờ 50%
                lable.setIcon(new ImageIcon(newImage));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Trở lại biểu tượng ban đầu khi di chuột ra
                ImageIcon icon = (ImageIcon) lable.getIcon();
                Image image = icon.getImage();
                lable.setIcon(new ImageIcon(image));
            }
        });

    }

    public void showButtonInInfo() {
        jButtonInfoSuaTen.setVisible(true);
        jButtonInfoSuaCCCD.setVisible(true);
        jButtonInfoSuaNgaySinh.setVisible(true);
        jButtonInfoSuaGioTinh.setVisible(true);
        jButtonInfoSuaDiaChi.setVisible(true);
        hideButtonYesNoInInfo();

    }

    public void hideButtonInInfo() {
        jButtonInfoSuaTen.setVisible(false);
        jButtonInfoSuaCCCD.setVisible(false);
        jButtonInfoSuaGioTinh.setVisible(false);
        jButtonInfoSuaNgaySinh.setVisible(false);
        jButtonInfoSuaDiaChi.setVisible(false);
    }

//    public void showButtonYesNoInInfo(){
//       jButtonInfoYesSuaTen.setVisible(true);
//       jButtonInfoNoSuaTen.setVisible(true);
//
//    }
    public void hideButtonYesNoInInfo() {
        jButtonInfoYesSuaTen.setVisible(false);
        jButtonInfoNoSuaTen.setVisible(false);
        jButtonInfoYesSuaCCCD.setVisible(false);
        jButtonInfoNoSuaCCCD.setVisible(false);
        jButtonInfoYesSuaGioiTinh.setVisible(false);
        jButtonInfoNoSuaGioiTinh.setVisible(false);
        jButtonInfoYesSuaNgaySinh.setVisible(false);
        jButtonInfoNoSuaNgaySinh.setVisible(false);
        jButtonInfoNoSuaDiaChi.setVisible(false);
        jButtonInfoYesSuaDiaChi.setVisible(false);

    }

    ////
    int daiNavbar = 658;
    int rongNavbar = 260;

    void openMenuNavbar() {
//        searchText.setEnabled(false);
//        productsLayout.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int targetWidth = rongNavbar; // Chiều rộng mục tiêu là widthMenu

                for (int i = 0; i <= targetWidth; i++) {
                    jPanelNavbar.setSize(i, daiNavbar);
                    //jPanelNavbar.setLocation(jPanelNavbar.getLocation().x - 1, jPanelNavbar.getLocation().y);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Employee.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    void closeMenuNavbar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = rongNavbar; i >= 0; i--) {
                    jPanelNavbar.setSize(i, daiNavbar);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Employee.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
//        searchText.setEnabled(true);
//        productsLayout.setEnabled(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChitietHDO;
    private javax.swing.JPanel ChonPTTT;
    private javax.swing.JLabel DiaChi;
    private javax.swing.JLabel DiaChi1;
    private javax.swing.JLabel DiaChi2;
    private javax.swing.JLabel GiaDiscount;
    private javax.swing.JLabel GiaDiscount1;
    private javax.swing.JLabel GiaDiscount2;
    private javax.swing.JLabel HinhThuc;
    private javax.swing.JLabel HinhThuc1;
    private javax.swing.JLabel HinhThuc3;
    private javax.swing.JLabel LiDoHuy;
    private javax.swing.JLabel MaBill;
    private javax.swing.JLabel MaBill2;
    private javax.swing.JLabel MaBill3;
    private javax.swing.JLabel NgayXuatBill;
    private javax.swing.JLabel NgayXuatBill2;
    private javax.swing.JLabel NgayXuatBill3;
    private javax.swing.JTextField NhapDiscount;
    private javax.swing.JTextField NhapTienMat;
    private javax.swing.JButton OKChonLaiTopping;
    private javax.swing.JRadioButton PTTT_ATM;
    private javax.swing.JRadioButton PTTT_TM;
    private javax.swing.JLabel PhoneInfo;
    private javax.swing.JPanel PrintBill;
    private javax.swing.JPanel PrintBillATM;
    private javax.swing.JLabel TBChoXacNhan;
    private javax.swing.JLabel TBChoXacNhan1;
    private javax.swing.JLabel TBChoXacNhan2;
    private javax.swing.JLabel TBChoXacNhan3;
    private javax.swing.JLabel TBChoXacNhan4;
    private javax.swing.JLabel TENQUAN;
    private javax.swing.JLabel TENQUAN1;
    private javax.swing.JLabel TENQUAN2;
    private javax.swing.JLabel TenHienProduct;
    private javax.swing.JLabel TenToppingHien;
    private javax.swing.JLabel ThanhToanTienMat;
    private javax.swing.JLabel ThanhToanTienMat1;
    private javax.swing.JLabel ThoatPTTT;
    private javax.swing.JLabel TienNhan;
    private javax.swing.JLabel TienThua;
    private javax.swing.JLabel TongGiamGiaMon;
    private javax.swing.JLabel TongGiamGiaMon1;
    private javax.swing.JLabel TongGiamGiaMon2;
    private javax.swing.JLabel TongGiamGiaMon3;
    private javax.swing.JLabel TongGiamGiaMon4;
    private javax.swing.JLabel TongThanhTienInBill;
    private javax.swing.JLabel TongThanhTienInBill2;
    private javax.swing.JLabel TongThanhTienInBill3;
    private javax.swing.JLabel TongTienInBill;
    private javax.swing.JLabel TongTienInBill1;
    private javax.swing.JLabel TongTienInBill2;
    private javax.swing.JLabel TrangThai;
    private javax.swing.JLabel TrangThaiLabel;
    private javax.swing.JLabel X_thoatChonLai;
    private javax.swing.JLabel addressInfo;
    private javax.swing.JScrollPane allProduct;
    private javax.swing.JLabel anhHienProduct;
    private javax.swing.JLabel anhTopping;
    private javax.swing.JLabel birthdayInfo;
    private javax.swing.JButton bnt_XuatHD;
    private javax.swing.JLabel butX;
    private javax.swing.ButtonGroup buttonGroupPTTT;
    private javax.swing.JLabel cccdInfo;
    private javax.swing.JPanel chonLaiTopping;
    private javax.swing.JLabel fullNameInfo;
    private javax.swing.JLabel fullNameText;
    private javax.swing.JPanel func;
    private javax.swing.JLabel genderInfo;
    private javax.swing.JLabel giaHienProduct;
    private javax.swing.JLabel giaToppingHien;
    private javax.swing.JPanel giohang;
    private javax.swing.JLabel giohangchon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBill;
    private javax.swing.JButton jButtonChoose;
    private javax.swing.JButton jButtonHoanTatSua;
    private javax.swing.JButton jButtonHoanTatSuaTopping;
    private javax.swing.JButton jButtonHoanTatThem;
    private javax.swing.JButton jButtonHoanTatThemTopping;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonHuySua;
    private javax.swing.JButton jButtonHuySuaTopping;
    private javax.swing.JButton jButtonHuyThem;
    private javax.swing.JButton jButtonHuyThemTopping;
    private javax.swing.JButton jButtonInfoNoSuaCCCD;
    private javax.swing.JButton jButtonInfoNoSuaDiaChi;
    private javax.swing.JButton jButtonInfoNoSuaGioiTinh;
    private javax.swing.JButton jButtonInfoNoSuaNgaySinh;
    private javax.swing.JButton jButtonInfoNoSuaTen;
    private javax.swing.JButton jButtonInfoStaff;
    private javax.swing.JButton jButtonInfoSuaCCCD;
    private javax.swing.JButton jButtonInfoSuaDiaChi;
    private javax.swing.JButton jButtonInfoSuaGioTinh;
    private javax.swing.JButton jButtonInfoSuaNgaySinh;
    private javax.swing.JButton jButtonInfoSuaTen;
    private javax.swing.JButton jButtonInfoYesSuaCCCD;
    private javax.swing.JButton jButtonInfoYesSuaDiaChi;
    private javax.swing.JButton jButtonInfoYesSuaGioiTinh;
    private javax.swing.JButton jButtonInfoYesSuaNgaySinh;
    private javax.swing.JButton jButtonInfoYesSuaTen;
    private javax.swing.JButton jButtonManageCategory;
    private javax.swing.JButton jButtonManageProduct;
    private javax.swing.JButton jButtonSearchInManageProduct;
    private javax.swing.JButton jButtonSearchInManageTopping;
    private javax.swing.JButton jButtonSuaHinhAnh;
    private javax.swing.JButton jButtonSuaHinhAnhToppinglogo;
    private javax.swing.JButton jButtonSuaTopping;
    private javax.swing.JButton jButtonSuaTraSua;
    private javax.swing.JButton jButtonThemAnh;
    private javax.swing.JButton jButtonThemAnhTopping;
    private javax.swing.JButton jButtonThemTopping;
    private javax.swing.JButton jButtonThemTraSua;
    private javax.swing.JButton jButtonXemTopping;
    private javax.swing.JButton jButtonXemTraSua;
    private javax.swing.JButton jButtonXoaTopping;
    private javax.swing.JButton jButtonXoaTraSua;
    private javax.swing.JCheckBox jCheckBoxDiscount;
    private javax.swing.JComboBox<String> jComboBoxDoanhMuc;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxSuaLoaiMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBillMenu;
    private javax.swing.JLabel jLabelChooseMenu;
    private javax.swing.JLabel jLabelGiaTopping;
    private javax.swing.JLabel jLabelGiaTraSua;
    private javax.swing.JLabel jLabelHomeMenu;
    private javax.swing.JLabel jLabelInfoMenu;
    private javax.swing.JLabel jLabelManageProductMenu;
    private javax.swing.JLabel jLabelMoTa;
    private javax.swing.JLabel jLabelMoTaGia;
    private javax.swing.JLabel jLabelQuanLiMon;
    private javax.swing.JLabel jLabelShowImage;
    private javax.swing.JLabel jLabelShowImageTopping;
    private javax.swing.JLabel jLabelSuaGia;
    private javax.swing.JLabel jLabelSuaGiaTopping;
    private javax.swing.JLabel jLabelSuaHinhAnh1;
    private javax.swing.JLabel jLabelSuaHinhAnhTopping;
    private javax.swing.JLabel jLabelSuaLoai1;
    private javax.swing.JLabel jLabelSuaTS;
    private javax.swing.JLabel jLabelSuaTS1;
    private javax.swing.JLabel jLabelSuaTen;
    private javax.swing.JLabel jLabelSuaTenTopping;
    private javax.swing.JLabel jLabelTenDoanhMuc;
    private javax.swing.JLabel jLabelTenTopping;
    private javax.swing.JLabel jLabelTenTraSua;
    private javax.swing.JLabel jLabelThemAnhTopping;
    private javax.swing.JLabel jLabelThemHinhAnh;
    private javax.swing.JLabel jLabelThemTS;
    private javax.swing.JLabel jLabelThemTS1;
    private javax.swing.JLabel jLabelThoatInBill;
    private javax.swing.JLabel jLabelThoatInBill2;
    private javax.swing.JLabel jLabelThoatInBill3;
    private javax.swing.JLabel jLabelXemGiaThucUong1;
    private javax.swing.JLabel jLabelXemGiaThucUong2;
    private javax.swing.JLabel jLabelXemGiaTopping1;
    private javax.swing.JLabel jLabelXemGiaTopping2;
    private javax.swing.JLabel jLabelXemLoaiThucUong1;
    private javax.swing.JLabel jLabelXemLoaiThucUong2;
    private javax.swing.JLabel jLabelXemMotaThucUong1;
    private javax.swing.JLabel jLabelXemTenThucUong1;
    private javax.swing.JLabel jLabelXemTenThucUong2;
    private javax.swing.JLabel jLabelXemTenTopping1;
    private javax.swing.JLabel jLabelXemTenTopping2;
    private javax.swing.JLabel jLabelXoaTS;
    private javax.swing.JLabel jLabelbuttoncloseMenuNavbar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBill;
    private javax.swing.JPanel jPanelChoose;
    private javax.swing.JPanel jPanelClear;
    private javax.swing.JPanel jPanelClear1;
    private javax.swing.JPanel jPanelFunctionMilkTea;
    private javax.swing.JPanel jPanelFunctionTopping;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelManageProduct;
    private javax.swing.JPanel jPanelNavbar;
    private javax.swing.JPanel jPanelQuanLiTopping;
    private javax.swing.JPanel jPanelQuanLiTraSua;
    private javax.swing.JPanel jPanelSuaTopping;
    private javax.swing.JPanel jPanelSuaTraSua;
    private javax.swing.JPanel jPanelThemTopping;
    private javax.swing.JPanel jPanelThemTraSua;
    private javax.swing.JPanel jPanelXemTopping;
    private javax.swing.JPanel jPanelXemTraSua;
    private javax.swing.JPanel jPanelXoaTraSua;
    private javax.swing.JPanel jPanelXulyMilkTea;
    private javax.swing.JPanel jPanelXulyTopping;
    private javax.swing.JPanel jPanelhienIBill;
    private javax.swing.JPanel jPanelhienIBill2;
    private javax.swing.JPanel jPanelhienIBill3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneChonLaiTopping;
    private javax.swing.JScrollPane jScrollPaneGioHang;
    private javax.swing.JScrollPane jScrollPaneStatusBill;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPaneManageProduct;
    private javax.swing.JTable jTableTP;
    private javax.swing.JTable jTableTS;
    private javax.swing.JTextArea jTextAreaAddress;
    private javax.swing.JTextArea jTextAreaMoTa;
    private javax.swing.JTextArea jTextAreaSuaMoTa;
    private javax.swing.JTextArea jTextAreaXemMotaThucUong2;
    private javax.swing.JTextField jTextFieldGiaTopping;
    private javax.swing.JTextField jTextFieldGiaTraSuaSizeL;
    private javax.swing.JTextField jTextFieldGiaTraSuaSizeM;
    private javax.swing.JTextField jTextFieldInfoBirthDate;
    private javax.swing.JTextField jTextFieldInfoCCCD;
    private javax.swing.JTextField jTextFieldInfoGender;
    private javax.swing.JTextField jTextFieldInfoName;
    private javax.swing.JTextField jTextFieldInfoPhone;
    private javax.swing.JTextField jTextFieldInfoRole;
    private javax.swing.JTextField jTextFieldInfoSalary;
    private javax.swing.JTextField jTextFieldSearchInManagTopping;
    private javax.swing.JTextField jTextFieldSearchInManageProduct;
    private javax.swing.JTextField jTextFieldSuaGiaMon;
    private javax.swing.JTextField jTextFieldSuaGiaTopping;
    private javax.swing.JTextField jTextFieldSuaHinhAnh2;
    private javax.swing.JTextField jTextFieldSuaHinhAnhTopping;
    private javax.swing.JTextField jTextFieldSuaTenMon;
    private javax.swing.JTextField jTextFieldSuaTenTopping;
    private javax.swing.JTextField jTextFieldTenTopping;
    private javax.swing.JTextField jTextFieldTenTraSua;
    private javax.swing.JTextField jTextFieldThemAnhTopping;
    private javax.swing.JTextField jTextFieldThemHinhAnh;
    private javax.swing.JComboBox<String> jcomboboxHientheoloai;
    private javax.swing.JLabel jlabelSLTrongGioHang;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel panelTop;
    private javax.swing.JLabel permissionInfo;
    private javax.swing.JLabel pic;
    private javax.swing.JLabel powered;
    private javax.swing.JLabel powered1;
    private javax.swing.JLabel poweredby;
    private javax.swing.JLabel salaryInfo;
    private javax.swing.JPanel showProduct;
    private javax.swing.JLabel tenChonLai;
    private javax.swing.JLabel tenInBill;
    private javax.swing.JLabel tenInBill2;
    private javax.swing.JLabel tenInBill3;
    private javax.swing.JLabel tenItem;
    private javax.swing.JLabel tongtien;
    private javax.swing.JLabel transitFee;
    private javax.swing.JLabel transitFee1;
    // End of variables declaration//GEN-END:variables

}
