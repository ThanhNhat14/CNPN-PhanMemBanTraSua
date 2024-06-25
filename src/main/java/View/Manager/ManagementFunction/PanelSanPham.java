
package View.Manager.ManagementFunction;


public class PanelSanPham extends javax.swing.JPanel {

   
    public PanelSanPham(String tenSanPham, String tenSize, String dsTopping, String tongTienMotSanPham, int soLuong) {
        initComponents();
        
        this.labelTenSanPham.setText(tenSanPham);
        this.labelSizeSanPham.setText(tenSize);
        this.labelXemDSTopping.setText(dsTopping);
        this.labelGiaMotSanPhamTrongHoaDon.setText(tongTienMotSanPham);
        this.labelXemNoiDungSoLuongSanPham.setText(soLuong + "");
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTenSanPham = new javax.swing.JLabel();
        labelSizeSanPham = new javax.swing.JLabel();
        labelTongTienMotSanPhamTrongHoaDon = new javax.swing.JLabel();
        labelDSTopping = new javax.swing.JLabel();
        labelXemDSTopping = new javax.swing.JLabel();
        labelGiaMotSanPhamTrongHoaDon = new javax.swing.JLabel();
        labelXemSoLuongSanPham = new javax.swing.JLabel();
        labelXemNoiDungSoLuongSanPham = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        labelTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelTenSanPham.setText("Sản phẩm 1");

        labelSizeSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelSizeSanPham.setText("Size M");

        labelTongTienMotSanPhamTrongHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelTongTienMotSanPhamTrongHoaDon.setText("Giá");

        labelDSTopping.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelDSTopping.setText("Topping");

        labelXemDSTopping.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelXemDSTopping.setText("Topping 1, Topping 2");

        labelGiaMotSanPhamTrongHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelGiaMotSanPhamTrongHoaDon.setText("40 000");

        labelXemSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelXemSoLuongSanPham.setText("Số lượng");

        labelXemNoiDungSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        labelXemNoiDungSoLuongSanPham.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTenSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(labelSizeSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemSoLuongSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemNoiDungSoLuongSanPham))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTongTienMotSanPhamTrongHoaDon)
                        .addGap(18, 18, 18)
                        .addComponent(labelGiaMotSanPhamTrongHoaDon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDSTopping)
                        .addGap(18, 18, 18)
                        .addComponent(labelXemDSTopping)))
                .addContainerGap(484, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTenSanPham)
                    .addComponent(labelSizeSanPham)
                    .addComponent(labelXemSoLuongSanPham)
                    .addComponent(labelXemNoiDungSoLuongSanPham))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDSTopping)
                    .addComponent(labelXemDSTopping))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTongTienMotSanPhamTrongHoaDon)
                    .addComponent(labelGiaMotSanPhamTrongHoaDon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDSTopping;
    private javax.swing.JLabel labelGiaMotSanPhamTrongHoaDon;
    private javax.swing.JLabel labelSizeSanPham;
    private javax.swing.JLabel labelTenSanPham;
    private javax.swing.JLabel labelTongTienMotSanPhamTrongHoaDon;
    private javax.swing.JLabel labelXemDSTopping;
    private javax.swing.JLabel labelXemNoiDungSoLuongSanPham;
    private javax.swing.JLabel labelXemSoLuongSanPham;
    // End of variables declaration//GEN-END:variables
}
