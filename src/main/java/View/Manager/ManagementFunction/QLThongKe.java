/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Manager.ManagementFunction;

import Dao.BillDAO;
import Dao.CategoryDAO;
import Dao.CustomerDAO;
import Dao.ProductDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import Model.Manager.Bill;
import Model.Manager.Product;
import Utils.Manager.FormatMoney;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Font;
import org.jfree.chart.title.LegendTitle;

public class QLThongKe extends javax.swing.JInternalFrame {

    public CustomerDAO customerDAO;
    public ProductDAO productDAO;
    public CategoryDAO categoryDAO;
    public BillDAO billDAO;
    public ArrayList<Bill> dsHoaDon;
    public ArrayList<Product> dsSanPham;

//    public JFreecahrt
    public QLThongKe() {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
        initComponents();

        this.customerDAO = new CustomerDAO();
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
        this.billDAO = new BillDAO();
        this.dsHoaDon = new ArrayList<>();
        this.dsSanPham = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String currentDay = dateFormat.format(currentDate);

        this.labelKQSoLuongKhachHang.setText(this.customerDAO.countCustomerAccount() + "");
        this.labelKQSoDonHangTrongNgay.setText(this.billDAO.countBillCreateOnToday(currentDay) + "");
        int doanhThuTrongNgay = this.billDAO.calculateTotalPriceBillOnToday(currentDay);
        this.labelKQDoanhThuTrongNgay.setText(FormatMoney.formatMoney(doanhThuTrongNgay));

        this.dsHoaDon = this.billDAO.getAllBill();
        this.dsSanPham = this.productDAO.getAllProduct();

        // Vẽ biểu đồ cột về doanh thu theo ngày trong tuần
        DefaultCategoryDataset dataset = createDataset(this.dsHoaDon);
        JFreeChart chart = ChartFactory.createBarChart(
                "DOANH THU THEO NGÀY TRONG TUẦN", // Tiêu đề biểu đồ
                "Ngày", // Label của trục x
                "Doanh thu", // Label của trục y
                dataset // Dataset chứa dữ liệu
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(730, 500));
        this.panelBieuDoThongKeHoaDon.setLayout(new FlowLayout());
        this.panelBieuDoThongKeHoaDon.add(chartPanel);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        // Vẽ biểu đồ trong về số lượng sản phẩm của từng loại sản phẩm
        DefaultPieDataset datasetSanPham = createDatasetSanPham(this.dsSanPham);
        // Tạo biểu đồ tròn
        JFreeChart chartSanPham = ChartFactory.createPieChart(
                "Product Category Distribution", // Tiêu đề biểu đồ
                datasetSanPham, // Dữ liệu cho biểu đồ
                true, // Có hiển thị nhãn không
                true, // Có hiển thị thông tin ghi chú không
                false // Có tạo URL không
        );
        // Tạo panel chứa biểu đồ và thêm vào giao diện
        ChartPanel chartPanelSanPham = new ChartPanel(chartSanPham);
        chartPanelSanPham.setPreferredSize(new Dimension(400, 500));
        this.panelBieuDoThongKeSoSanPham.setLayout(new FlowLayout());
        this.panelBieuDoThongKeSoSanPham.add(chartPanelSanPham);
        PiePlot plotSanPham = (PiePlot) chartSanPham.getPlot();
        plotSanPham.setBackgroundPaint(Color.WHITE);
        plotSanPham.setOutlinePaint(null);
        LegendTitle legend = chartSanPham.getLegend();
        legend.setItemFont(new Font("Arial", Font.PLAIN, 14));
    }

//    private DefaultCategoryDataset createDataset(ArrayList<Bill> bills) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        HashMap<String, Integer> dailyRevenue = new HashMap<>(); // Lưu trữ doanh thu của mỗi ngày trong tuần
//
//        // Khởi tạo mảng tên của các ngày trong tuần theo thứ tự từ Thứ Hai đến Chủ Nhật
//        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//
//        // Khởi tạo các giá trị doanh thu ban đầu cho tất cả các ngày trong tuần là 0
//        for (String dayOfWeek : daysOfWeek) {
//            dailyRevenue.put(dayOfWeek, 0);
//        }
//
//        for (Bill bill : bills) {
//            try {
//                Date orderDate = dateFormat.parse(bill.getDateOrder());
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(orderDate);
//                int dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK); // Lấy chỉ số của ngày trong tuần (Chủ nhật là 1, Thứ Hai là 2, ..., Thứ Bảy là 7
//                int adjustedIndex = (dayOfWeekIndex + 5) % 7; // Điều chỉnh chỉ số để bắt đầu từ Thứ Hai (0) đến Chủ Nhật (6)
//                String dayOfWeekName = daysOfWeek[adjustedIndex]; // Lấy tên của ngày trong tuần từ mảng daysOfWeek
//                int revenue = dailyRevenue.get(dayOfWeekName);
//                revenue += bill.getTotalPrice(); // Cộng thêm doanh thu của hóa đơn vào ngày tương ứng
//                dailyRevenue.put(dayOfWeekName, revenue);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        // Thêm dữ liệu vào dataset theo thứ tự của các ngày trong tuần
//        for (String dayOfWeek : daysOfWeek) {
//            dataset.addValue(dailyRevenue.get(dayOfWeek), "Revenue", dayOfWeek);
//        }
//        return dataset;
//    }
    private DefaultCategoryDataset createDataset(ArrayList<Bill> bills) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar currentCalendar = Calendar.getInstance();
        int currentWeek = currentCalendar.get(Calendar.WEEK_OF_YEAR); // Tuần hiện tại

        // Khởi tạo mảng tên của các ngày trong tuần theo thứ tự từ Thứ Hai đến Chủ Nhật
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Khởi tạo các giá trị doanh thu ban đầu cho tất cả các ngày trong tuần là 0
        HashMap<String, Integer> dailyRevenue = new HashMap<>();
        for (String dayOfWeek : daysOfWeek) {
            dailyRevenue.put(dayOfWeek, 0);
        }

        for (Bill bill : bills) {
            try {
                Date orderDate = dateFormat.parse(bill.getDateOrder());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(orderDate);
                int orderWeek = calendar.get(Calendar.WEEK_OF_YEAR); // Tuần của hóa đơn
                if (orderWeek == currentWeek) { // Kiểm tra hóa đơn thuộc tuần hiện tại không
                    int dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK); // Lấy chỉ số của ngày trong tuần
                    int adjustedIndex = (dayOfWeekIndex + 5) % 7; // Điều chỉnh chỉ số để bắt đầu từ Thứ Hai (0) đến Chủ Nhật (6)
                    String dayOfWeekName = daysOfWeek[adjustedIndex]; // Lấy tên của ngày trong tuần từ mảng daysOfWeek
                    int revenue = dailyRevenue.get(dayOfWeekName);
                    revenue += bill.getTotalPrice(); // Cộng thêm doanh thu của hóa đơn vào ngày tương ứng
                    dailyRevenue.put(dayOfWeekName, revenue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Thêm dữ liệu vào dataset theo thứ tự của các ngày trong tuần
        for (String dayOfWeek : daysOfWeek) {
            dataset.addValue(dailyRevenue.get(dayOfWeek), "Revenue", dayOfWeek);
        }
        return dataset;
    }

    private DefaultPieDataset createDatasetSanPham(ArrayList<Product> products) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Kết nối đến cơ sở dữ liệu và truy vấn ánh xạ id loại sản phẩm với tên loại sản phẩm
        Map<Integer, String> idToNameMap = this.categoryDAO.queryCategoryMapping(); // Hãy thay thế hàm này bằng hàm truy vấn thực tế từ cơ sở dữ liệu

        int totalProducts = products.size();
        // Đếm số lượng sản phẩm theo từng loại sản phẩm
        Map<String, Integer> productCountByCategory = new HashMap<>();
        for (Product product : products) {
            int categoryId = product.getIdCategory();
            String categoryName = idToNameMap.get(categoryId); // Lấy tên loại sản phẩm từ id loại sản phẩm
            if (categoryName != null) {
                int count = productCountByCategory.getOrDefault(categoryName, 0);
                productCountByCategory.put(categoryName, count + 1);
            }
        }

        // Thêm dữ liệu vào bộ dữ liệu của biểu đồ tròn
        for (Map.Entry<String, Integer> entry : productCountByCategory.entrySet()) {
//            dataset.setValue(entry.getKey(), entry.getValue());
            double percentage = (double) entry.getValue() / totalProducts * 100.0;
            dataset.setValue(entry.getKey() + " (" + String.format("%.1f", percentage) + "%)", entry.getValue());
        }

        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelQLThongKeContent = new javax.swing.JPanel();
        panelTitleThongKe = new javax.swing.JLabel();
        panelDoanhThuTrongNgayTK = new javax.swing.JPanel();
        labelDoanhThuTrongNgay = new javax.swing.JLabel();
        labelKQDoanhThuTrongNgay = new javax.swing.JLabel();
        panelSoDonHangTrongNgay = new javax.swing.JPanel();
        labelSoDonHangTrongNgay = new javax.swing.JLabel();
        labelKQSoDonHangTrongNgay = new javax.swing.JLabel();
        panelSoLuongKhachHang = new javax.swing.JPanel();
        labelSoLuongKhachHang = new javax.swing.JLabel();
        labelKQSoLuongKhachHang = new javax.swing.JLabel();
        panelBieuDoThongKeHoaDon = new javax.swing.JPanel();
        panelBieuDoThongKeSoSanPham = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTitleThongKe.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        panelTitleThongKe.setText("THỐNG KÊ");

        panelDoanhThuTrongNgayTK.setBackground(new java.awt.Color(81, 151, 255));

        labelDoanhThuTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelDoanhThuTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        labelDoanhThuTrongNgay.setText("Doanh thu trong ngày");

        labelKQDoanhThuTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelKQDoanhThuTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        labelKQDoanhThuTrongNgay.setText("100000");

        javax.swing.GroupLayout panelDoanhThuTrongNgayTKLayout = new javax.swing.GroupLayout(panelDoanhThuTrongNgayTK);
        panelDoanhThuTrongNgayTK.setLayout(panelDoanhThuTrongNgayTKLayout);
        panelDoanhThuTrongNgayTKLayout.setHorizontalGroup(
            panelDoanhThuTrongNgayTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuTrongNgayTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoanhThuTrongNgayTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDoanhThuTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(labelKQDoanhThuTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDoanhThuTrongNgayTKLayout.setVerticalGroup(
            panelDoanhThuTrongNgayTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuTrongNgayTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDoanhThuTrongNgay)
                .addGap(18, 18, 18)
                .addComponent(labelKQDoanhThuTrongNgay)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        panelSoDonHangTrongNgay.setBackground(new java.awt.Color(255, 89, 0));
        panelSoDonHangTrongNgay.setForeground(new java.awt.Color(255, 255, 255));

        labelSoDonHangTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSoDonHangTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        labelSoDonHangTrongNgay.setText("Số đơn hàng trong ngày");

        labelKQSoDonHangTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelKQSoDonHangTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        labelKQSoDonHangTrongNgay.setText("20");

        javax.swing.GroupLayout panelSoDonHangTrongNgayLayout = new javax.swing.GroupLayout(panelSoDonHangTrongNgay);
        panelSoDonHangTrongNgay.setLayout(panelSoDonHangTrongNgayLayout);
        panelSoDonHangTrongNgayLayout.setHorizontalGroup(
            panelSoDonHangTrongNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoDonHangTrongNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSoDonHangTrongNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSoDonHangTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(labelKQSoDonHangTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSoDonHangTrongNgayLayout.setVerticalGroup(
            panelSoDonHangTrongNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoDonHangTrongNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSoDonHangTrongNgay)
                .addGap(18, 18, 18)
                .addComponent(labelKQSoDonHangTrongNgay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSoLuongKhachHang.setBackground(new java.awt.Color(255, 110, 180));

        labelSoLuongKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        labelSoLuongKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSoLuongKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        labelSoLuongKhachHang.setText("Tài khoản khách hàng");

        labelKQSoLuongKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelKQSoLuongKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        labelKQSoLuongKhachHang.setText("10");

        javax.swing.GroupLayout panelSoLuongKhachHangLayout = new javax.swing.GroupLayout(panelSoLuongKhachHang);
        panelSoLuongKhachHang.setLayout(panelSoLuongKhachHangLayout);
        panelSoLuongKhachHangLayout.setHorizontalGroup(
            panelSoLuongKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoLuongKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSoLuongKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSoLuongKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(labelKQSoLuongKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSoLuongKhachHangLayout.setVerticalGroup(
            panelSoLuongKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoLuongKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSoLuongKhachHang)
                .addGap(18, 18, 18)
                .addComponent(labelKQSoLuongKhachHang)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBieuDoThongKeHoaDonLayout = new javax.swing.GroupLayout(panelBieuDoThongKeHoaDon);
        panelBieuDoThongKeHoaDon.setLayout(panelBieuDoThongKeHoaDonLayout);
        panelBieuDoThongKeHoaDonLayout.setHorizontalGroup(
            panelBieuDoThongKeHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBieuDoThongKeHoaDonLayout.setVerticalGroup(
            panelBieuDoThongKeHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBieuDoThongKeSoSanPhamLayout = new javax.swing.GroupLayout(panelBieuDoThongKeSoSanPham);
        panelBieuDoThongKeSoSanPham.setLayout(panelBieuDoThongKeSoSanPhamLayout);
        panelBieuDoThongKeSoSanPhamLayout.setHorizontalGroup(
            panelBieuDoThongKeSoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        panelBieuDoThongKeSoSanPhamLayout.setVerticalGroup(
            panelBieuDoThongKeSoSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelQLThongKeContentLayout = new javax.swing.GroupLayout(panelQLThongKeContent);
        panelQLThongKeContent.setLayout(panelQLThongKeContentLayout);
        panelQLThongKeContentLayout.setHorizontalGroup(
            panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitleThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                                .addComponent(panelDoanhThuTrongNgayTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelSoDonHangTrongNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelSoLuongKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelBieuDoThongKeHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panelBieuDoThongKeSoSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(337, Short.MAX_VALUE))
        );
        panelQLThongKeContentLayout.setVerticalGroup(
            panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitleThongKe)
                .addGap(27, 27, 27)
                .addGroup(panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                        .addGroup(panelQLThongKeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelSoLuongKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDoanhThuTrongNgayTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelSoDonHangTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panelBieuDoThongKeHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelQLThongKeContentLayout.createSequentialGroup()
                        .addComponent(panelBieuDoThongKeSoSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 450, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelQLThongKeContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelQLThongKeContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(QLThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDoanhThuTrongNgay;
    private javax.swing.JLabel labelKQDoanhThuTrongNgay;
    private javax.swing.JLabel labelKQSoDonHangTrongNgay;
    private javax.swing.JLabel labelKQSoLuongKhachHang;
    private javax.swing.JLabel labelSoDonHangTrongNgay;
    private javax.swing.JLabel labelSoLuongKhachHang;
    private javax.swing.JPanel panelBieuDoThongKeHoaDon;
    private javax.swing.JPanel panelBieuDoThongKeSoSanPham;
    private javax.swing.JPanel panelDoanhThuTrongNgayTK;
    private javax.swing.JPanel panelQLThongKeContent;
    private javax.swing.JPanel panelSoDonHangTrongNgay;
    private javax.swing.JPanel panelSoLuongKhachHang;
    private javax.swing.JLabel panelTitleThongKe;
    // End of variables declaration//GEN-END:variables
}
