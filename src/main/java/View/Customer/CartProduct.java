/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Customer;

import static Controller.Customer.HandleProducts.Toppings;
import static Controller.Customer.HandleProducts.getToppings;
import java.awt.Container;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static View.Customer.CustomerDisplay.total;
import static View.Customer.CustomerDisplay.notification;
import static View.Customer.CustomerDisplay.setTotalPriceCart;
import static View.Customer.CustomerDisplay.SelectedToppings;
import View.Customer.Notification.Message;
import View.Customer.Notification.Warning;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author ngocn
 */
public class CartProduct extends javax.swing.JPanel {

    private int[] priceInt = new int[1];
    private int[] quantityInt = new int[1];
    private int quantityDefaut = 0;
    private int indexFrame;
    private int TotalPrice;
    private int size;
    private int Price;
    private int sizeDefaut;
    private String sizeAndToppingText = "";
    private JRadioButton lastSelected = new JRadioButton();
    private JButton confirm = new JButton();
    private List<Map<String, Integer>> selectedToppings = new ArrayList<>();
    private JPanel moreSelectPanel = new JPanel();

    private Map<String, Object> ItemChange = new HashMap<>();
    private final Map<String, Object> ItemDefaut = new HashMap<>();

    private JDialog EditProduct = new JDialog();

    /**
     * Creates new form cartProduct
     */
    public CartProduct() {
        initComponents();
        confirm = confirmBtn;
    }

    public JLabel getProductNameLabel() {
        return productName;
    }

    public JLabel getDeleteProduct() {
        return deleteProduct;
    }

    public JLabel getPlus() {
        return plus;
    }

    public JLabel getMinus() {
        return minus;
    }

    public void setPriceSize(String size) {
        jLabel3.setName(size);
    }

    public void setProductImg(String productImg) {
        this.productImg.setIcon(new javax.swing.ImageIcon(getClass().getResource(productImg)));
        this.productImg.setName(productImg);
    }

    public void setProductName(String productName, int index) {
        this.productName.setText(productName);
        this.productName.setToolTipText(String.valueOf(index));
    }

    public String getProductName() {
        return this.productName.getText();
    }

    public void setProductPrice(String productPrice) {
        priceInt[0] = Integer.parseInt(productPrice);
        this.productPrice.setText("₫" + productPrice);
        this.confirm.setText("Xác nhận - ₫" + productPrice);
    }

    public void setConfirmPrice(String productPrice) {
        priceInt[0] = Integer.parseInt(productPrice);
        this.productPrice.setText("₫" + productPrice);
        this.confirmBtn.setText("Xác nhận - ₫" + productPrice);
    }

    public void setProductDefautPrice(String price) {
        this.productPrice.setName(price);
    }

    public int getProductPrice() {
        return priceInt[0];
    }

    public void setSizeAndTopping(String sizeAndTopping) {
        this.sizeAndTopping.setText(sizeAndTopping);
    }

    public void setQuantity(String quantity) {
        quantityInt[0] = Integer.parseInt(quantity);
        this.quantity.setText(quantity);
        this.quantity1.setText(quantity);
    }

    public void setIndex(int index) {
        this.indexFrame = index;
    }

    public int getIndex() {
        return indexFrame;
    }

    public int getQuantityInt() {
        return quantityInt[0];
    }

    void setEditFrame() {
        EditProduct = new JDialog();
        EditProduct.setUndecorated(true);
        EditProduct.setAlwaysOnTop(true);
        EditProduct.add(editProduct);
        EditProduct.setBounds(458, 885, 621, 0);
    }

    void setEditView(int idItem) {
        selectedToppings = new ArrayList<>();
        Map<String, Object> Item = CustomerDisplay.Glasses.get(idItem);
        ItemDefaut.putAll(Item);
        //System.out.println(Item);
        moreSelectPanel = new JPanel();
        this.ItemChange.putAll(Item);
        JViewport emptyViewport = new JViewport();
        moreSelect.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        moreSelect.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        moreSelect.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
        moreSelect.setViewport(emptyViewport);
        moreSelect.setViewportView(moreSelectPanel);

        productImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) Item.get("imageProduct"))));
        productName1.setText((String) Item.get("nameProduct"));
        productPrice1.setText(productPrice.getName());
        quantityDefaut = (int) Item.get("quantityProduct");
        setQuantity(String.valueOf(Item.get("quantityProduct")));
        if (getQuantityInt() != 1) {
            minus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-1.png")));
            minus1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        total[0] = ((int) Item.get("price")) * ((int) Item.get("quantityProduct"));
        priceInt[0] = total[0];
        Price = total[0];
        TotalPrice = total[1];
        total[1] -= total[0];

        int size1Price = Integer.parseInt(jLabel3.getName());
        sizeAndToppingText = "Size: Vừa | Topping: ";
        moreSelectPanel.setLayout(new BoxLayout(moreSelectPanel, BoxLayout.Y_AXIS));
        JLabel sizeLable = new JLabel();
        sizeLable.setFont(new java.awt.Font("Segoe UI", 0, 14));
        sizeLable.setForeground(new java.awt.Color(146, 146, 148));
        sizeLable.setText("Size (Chọn 1)");
        moreSelectPanel.add(sizeLable);
        moreSelectPanel.add(Box.createVerticalStrut(2));
        sizeDefaut = (int) Item.get("idSize");

        Size size0 = new Size();
        size0.setTextSize("Vừa", "0");
        //size0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        size0.getSizeCheck().setSelected(true);
        moreSelectPanel.add(size0);
        moreSelectPanel.add(Box.createVerticalStrut(2));

        if (size1Price != 0) {
            size = 1;
            Size size1 = new Size();
            size1.setTextSize("Lớn", String.valueOf(size1Price));
            //size1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            moreSelectPanel.add(size1);
            moreSelectPanel.add(Box.createVerticalStrut(2));

            ButtonGroup group = new ButtonGroup();
            group.add(size0.getSizeCheck());
            group.add(size1.getSizeCheck());
            if ((int) Item.get("idSize") == 2) {
                size = 2;
                size1.getSizeCheck().setSelected(true);
                sizeAndToppingText = "Size: Lớn | Topping: ";
            }

            lastSelected = size0.getSizeCheck();
            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (selectedButton == size1.getSizeCheck()) {
                            total[0] += (size1Price * getQuantityInt());
                            setProductPrice(String.valueOf(total[0]));
                            lastSelected = selectedButton;
                            sizeAndToppingText = sizeAndToppingText.replace("Vừa", "Lớn");
                            size = 2;
                            return;
                        } else {
                            total[0] -= (size1Price * getQuantityInt());
                            setProductPrice(String.valueOf(total[0]));
                            lastSelected = selectedButton;
                            sizeAndToppingText = sizeAndToppingText.replace("Lớn", "Vừa");
                            size = 1;
                            return;
                        }
                    }
                }
            };

            size0.getSizeCheck().addItemListener(itemListener);
            size1.getSizeCheck().addItemListener(itemListener);
            //size2.getSizeCheck().addItemListener(itemListener);
        } else {
            size0.getSizeCheck().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    size0.getSizeCheck().setSelected(true);
                }
            });
        }

        JLabel toppingLable = new JLabel();
        toppingLable.setFont(new java.awt.Font("Segoe UI", 0, 14));
        toppingLable.setForeground(new java.awt.Color(146, 146, 148));
        getToppings();
        toppingLable.setText("Topping (Tối đa " + Toppings.size() + " loại)");
        moreSelectPanel.add(toppingLable);
        selectedToppings.addAll((List<Map<String, Integer>>) Item.get("Topping"));
        ItemChange.put("Topping", selectedToppings);
        SelectedToppings = new ArrayList<>();
        SelectedToppings.addAll(selectedToppings);

        int[] priceTopping = new int[Toppings.size()];
        for (int i = 0; i < Toppings.size(); i++) {
            Map<String, Object> Topping = Toppings.get(i);

            if ((boolean) Topping.get("status")) {
                Topping topping = new Topping();
                for (int j = 0; j < SelectedToppings.size(); j++) {
                    Map<String, Integer> tp = SelectedToppings.get(j);
                    int id = Integer.parseInt((String) Topping.get("idTopping"));
                    if (id == (tp.get("idTopping"))) {
                        topping.setQuantity(String.valueOf(tp.get("quantity")));
                        topping.checkQuantity();
                        break;
                    }
                }

                //idToppingSelected.put(Integer.parseInt(String.valueOf(Topping.get("idTopping"))), false);
                //topping.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                topping.setTopping((String) Topping.get("nameTopping"), String.valueOf(Topping.get("priceTopping")), String.valueOf(Topping.get("idTopping")));
                moreSelectPanel.add(topping);

                final int indexTopping = i;
                priceTopping[indexTopping] = Integer.parseInt(String.valueOf(Topping.get("priceTopping")));

                topping.getPlus().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int quantityTopping = topping.getQuantity();

                        total[0] /= getQuantityInt();
                        total[0] -= (topping.getPriceTopping() * (quantityTopping - 1));
                        total[0] += (topping.getPriceTopping() * quantityTopping);
                        total[0] *= getQuantityInt();
                        System.out.println(total[0]);
                        setProductPrice(String.valueOf(total[0]));
                    }

                });

                topping.getMinus().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (topping.getCheck()) {
                            int quantityTopping = topping.getQuantity();

                            total[0] /= getQuantityInt();
                            total[0] -= (topping.getPriceTopping() * (quantityTopping + 1));
                            total[0] += (topping.getPriceTopping() * quantityTopping);
                            total[0] *= getQuantityInt();
                            setProductPrice(String.valueOf(total[0]));
                        }
                    }

                });
                moreSelectPanel.add(Box.createVerticalStrut(2));
            }
        }
        setProductPrice(String.valueOf(priceInt[0]));

        setEditFrame();
        EditProduct.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 670; i += 10) {
                    EditProduct.setSize(621, i);
                    EditProduct.setLocation(EditProduct.getX(), EditProduct.getY() - 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                CustomerDisplay.invisibleFrame.setAlwaysOnTop(false);
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editProduct = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        thisProduct = new javax.swing.JPanel();
        productImg1 = new javax.swing.JLabel();
        productName1 = new javax.swing.JLabel();
        productDescription = new javax.swing.JLabel();
        productDescriptionMore = new javax.swing.JLabel();
        productPrice1 = new javax.swing.JLabel();
        quantity1 = new javax.swing.JLabel();
        plus1 = new javax.swing.JLabel();
        minus1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        moreSelect = new javax.swing.JScrollPane();
        confirmBtn = new javax.swing.JButton();
        productImg = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        sizeAndTopping = new javax.swing.JLabel();
        more = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        plus = new javax.swing.JLabel();
        minus = new javax.swing.JLabel();
        deleteProduct = new javax.swing.JLabel();

        editProduct.setBackground(new java.awt.Color(255, 255, 255));
        editProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sửa món");

        thisProduct.setBackground(new java.awt.Color(255, 255, 255));

        productImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dự án mới.png"))); // NOI18N
        productImg1.setToolTipText("");

        productName1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        productName1.setForeground(new java.awt.Color(0, 0, 0));
        productName1.setText("Trà sữa Truyền Thống");

        productDescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productDescription.setForeground(new java.awt.Color(153, 153, 153));
        productDescription.setText("Với vị Trà hảo hạng quyện cùng Sữa tạo nên sự kết hợp hài hòa, lôi cuốn. Truyền Thống lúc nào cũng là lựa chọn hàng đầu cho mọi cuộc gặp gỡ.");
        productDescription.setToolTipText("Với vị Trà hảo hạng quyện cùng Sữa tạo nên sự kết hợp hài hòa, lôi cuốn. Truyền Thống lúc nào cũng là lựa chọn hàng đầu cho mọi cuộc gặp gỡ.");

        productDescriptionMore.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productDescriptionMore.setForeground(new java.awt.Color(153, 153, 153));
        productDescriptionMore.setText("xem thêm");
        productDescriptionMore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productDescriptionMore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productDescriptionMoreMouseClicked(evt);
            }
        });

        productPrice1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        productPrice1.setForeground(new java.awt.Color(27, 43, 32));
        productPrice1.setText("23.000");

        quantity1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quantity1.setForeground(new java.awt.Color(0, 0, 0));
        quantity1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantity1.setText("1");

        plus1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        plus1.setForeground(new java.awt.Color(0, 0, 0));
        plus1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        plus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-1.png"))); // NOI18N
        plus1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        plus1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plus1MouseClicked(evt);
            }
        });

        minus1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        minus1.setForeground(new java.awt.Color(0, 0, 0));
        minus1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-0.png"))); // NOI18N
        minus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minus1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout thisProductLayout = new javax.swing.GroupLayout(thisProduct);
        thisProduct.setLayout(thisProductLayout);
        thisProductLayout.setHorizontalGroup(
            thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thisProductLayout.createSequentialGroup()
                .addComponent(productImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thisProductLayout.createSequentialGroup()
                        .addComponent(productName1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                        .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(thisProductLayout.createSequentialGroup()
                                .addComponent(productPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(thisProductLayout.createSequentialGroup()
                                .addComponent(productDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(productDescriptionMore)))
                        .addGap(15, 15, 15))))
        );
        thisProductLayout.setVerticalGroup(
            thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                .addComponent(productName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productDescription)
                    .addComponent(productDescriptionMore))
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thisProductLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(productPrice1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(productImg1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(minus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(plus1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        moreSelect.setBackground(new java.awt.Color(255, 255, 255));
        moreSelect.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        moreSelect.setToolTipText("");
        moreSelect.setVerifyInputWhenFocusTarget(false);

        confirmBtn.setBackground(new java.awt.Color(27, 43, 32));
        confirmBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(210, 154, 95));
        confirmBtn.setText("Xác nhận - ");
        confirmBtn.setBorder(null);
        confirmBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editProductLayout = new javax.swing.GroupLayout(editProduct);
        editProduct.setLayout(editProductLayout);
        editProductLayout.setHorizontalGroup(
            editProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editProductLayout.createSequentialGroup()
                .addGroup(editProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editProductLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6))
                    .addGroup(editProductLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(editProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thisProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(moreSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(1, 1, 1))
            .addGroup(editProductLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editProductLayout.setVerticalGroup(
            editProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editProductLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(editProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(thisProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        productImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dự án mới.png"))); // NOI18N
        productImg.setToolTipText("");

        productName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        productName.setForeground(new java.awt.Color(0, 0, 0));
        productName.setText("Trà sữa Truyền Thống");
        productName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sizeAndTopping.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sizeAndTopping.setForeground(new java.awt.Color(153, 153, 153));
        sizeAndTopping.setText("Với vị Trà hảo hạng quyện cùng Sữa tạo nên sự kết hợp hài hòa, lôi cuốn. Truyền Thống lúc nào cũng là lựa chọn hàng đầu cho mọi cuộc gặp gỡ.");
        sizeAndTopping.setToolTipText("Với vị Trà hảo hạng quyện cùng Sữa tạo nên sự kết hợp hài hòa, lôi cuốn. Truyền Thống lúc nào cũng là lựa chọn hàng đầu cho mọi cuộc gặp gỡ.");

        more.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        more.setForeground(new java.awt.Color(153, 153, 153));
        more.setText("xem thêm");
        more.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        more.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moreMouseClicked(evt);
            }
        });

        productPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        productPrice.setForeground(new java.awt.Color(27, 43, 32));
        productPrice.setText("23.000");

        quantity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quantity.setForeground(new java.awt.Color(0, 0, 0));
        quantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantity.setText("1");

        plus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        plus.setForeground(new java.awt.Color(0, 0, 0));
        plus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        plus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-1.png"))); // NOI18N
        plus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        plus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plusMouseClicked(evt);
            }
        });

        minus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        minus.setForeground(new java.awt.Color(0, 0, 0));
        minus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-1.png"))); // NOI18N
        minus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minusMouseClicked(evt);
            }
        });

        deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        deleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(productImg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(productName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteProduct)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sizeAndTopping, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(more)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productName)
                    .addComponent(deleteProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeAndTopping)
                    .addComponent(more))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productPrice)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(productImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void moreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moreMouseClicked

        //JOptionPane.showMessageDialog(this, sizeAndTopping.getText(), "Size và topping", JOptionPane.PLAIN_MESSAGE);
        notification.setAlwaysOnTop(true);
        //        JOptionPane.showMessageDialog(notification, productDescription.getText(), "Mô tả sản phẩm", JOptionPane.PLAIN_MESSAGE);
        Message message = new Message(notification, true);
        message.setTitle("Size và topping");
        message.setContent(sizeAndTopping.getText());
        message.setVisible(true);

    }//GEN-LAST:event_moreMouseClicked

    private void minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusMouseClicked
        if (quantityInt[0] == 1) {
            Container container = getParent();
            if (container instanceof JPanel) {
                JPanel parentPanel = (JPanel) container;
                // Xóa CartProduct khỏi giao diện
                parentPanel.remove(this);
                // Cập nhật lại giao diện người dùng
                parentPanel.revalidate();
                parentPanel.repaint();
            }
            quantityInt[0]--;
            total[1] -= priceInt[0];
            //setTotalPrice(total[1]);
        } else {
            total[1] -= priceInt[0];
            priceInt[0] /= quantityInt[0];
            quantityInt[0]--;
            priceInt[0] *= quantityInt[0];
            total[1] += priceInt[0];
            quantity.setText(String.valueOf(quantityInt[0]));
            productPrice.setText("₫" + String.valueOf(priceInt[0]));
            //setTotalPrice(total[1]);
        }
    }//GEN-LAST:event_minusMouseClicked

    private void plusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusMouseClicked

        total[1] -= priceInt[0];
        priceInt[0] /= quantityInt[0];
        quantityInt[0]++;
        priceInt[0] *= quantityInt[0];
        total[1] += priceInt[0];
        quantity.setText(String.valueOf(quantityInt[0]));
        productPrice.setText("₫" + String.valueOf(priceInt[0]));

    }//GEN-LAST:event_plusMouseClicked

    private void deleteProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteProductMouseClicked
        Container container = getParent();
        if (container instanceof JPanel) {
            JPanel parentPanel = (JPanel) container;
            // Xóa CartProduct khỏi giao diện
            parentPanel.remove(this);
            // Cập nhật lại giao diện người dùng
            parentPanel.revalidate();
            parentPanel.repaint();
        }
        quantityInt[0] = 0;
        total[1] -= priceInt[0];
    }//GEN-LAST:event_deleteProductMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        total[1] = TotalPrice;
        setTotalPriceCart(String.valueOf(total[1]));
        setQuantity(String.valueOf(quantityDefaut));
        setProductPrice(String.valueOf(Price));

        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerDisplay.invisibleFrame.setAlwaysOnTop(true);
                for (int i = 670; i >= 0; i -= 10) {
                    EditProduct.setSize(621, i);
                    EditProduct.setLocation(EditProduct.getX(), EditProduct.getY() + 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                CustomerDisplay.invisibleFrame.setAlwaysOnTop(false);
                EditProduct.dispose();
                CustomerDisplay.Glasses.set(indexFrame, ItemDefaut);
            }
        }).start();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void productDescriptionMoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productDescriptionMoreMouseClicked

//        notification.setAlwaysOnTop(true);
//        //        JOptionPane.showMessageDialog(notification, productDescription.getText(), "Mô tả sản phẩm", JOptionPane.PLAIN_MESSAGE);
//        Message message = new Message(notification, true);
//        message.setTitle("Mô tả sản phẩm");
//        message.setContent(productDescription.getText());
//        message.setVisible(true);
    }//GEN-LAST:event_productDescriptionMoreMouseClicked

    private void plus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus1MouseClicked

        int soLuong = getQuantityInt();

        total[0] /= soLuong;
        soLuong++;
        setQuantity(String.valueOf(soLuong));
        quantity.setText(String.valueOf(soLuong));
        if (soLuong != 1) {
            minus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-1.png")));
            minus1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        total[0] *= soLuong;
        setProductPrice(String.valueOf(total[0]));
    }//GEN-LAST:event_plus1MouseClicked

    private void minus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minus1MouseClicked

        int soLuong = getQuantityInt();

        if (soLuong == 2) {
            minus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-0.png")));
            minus1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }

        if (soLuong == 1) {
            notification.setAlwaysOnTop(true);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Số lượng đã tối thiểu!!!");
            warning.setVisible(true);
            return;
        }
        total[0] /= soLuong;
        soLuong--;
        setQuantity(String.valueOf(soLuong));
        quantity.setText(String.valueOf(soLuong));
        total[0] *= soLuong;
        setProductPrice(String.valueOf(total[0]));

    }//GEN-LAST:event_minus1MouseClicked

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed

        this.ItemChange.put("Topping", SelectedToppings);
        List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) this.ItemChange.get("Topping");
        total[1] += total[0];
        this.ItemChange.put("quantityProduct", getQuantityInt());
        int price = total[0] / getQuantityInt();
        this.ItemChange.put("price", price);
        this.ItemChange.put("idSize", size);
        for (int i = 0; i < CustomerDisplay.Glasses.size(); i++) {
            Map<String, Object> item = CustomerDisplay.Glasses.get(i);
            if (ItemChange.get("idProduct").equals(item.get("idProduct")) && ItemChange.get("idSize").equals(item.get("idSize"))
                    && ItemChange.get("Topping").equals(item.get("Topping"))) {
                //setQuantity(String.valueOf(quantityDefaut));
                notification.setAlwaysOnTop(true);
                Warning warning = new Warning(notification, true);
                warning.setTitle("Thông báo");
                warning.setWarning("Sản phẩm này đã có sẵn!!!");
                warning.setVisible(true);
                return;
            }
        }
        int count = 0;
        for (int j = 0; j < Topping.size(); j++) {
            Map<String, Integer> topping = Topping.get(j);
            int idTopping = topping.get("idTopping") - 1;
            String quantity = String.valueOf(topping.get("quantity"));
            count++;
            sizeAndToppingText += (String) Toppings.get(idTopping).get("nameTopping");
            sizeAndToppingText += (" x" + quantity);
            if (count != Topping.size()) {
                sizeAndToppingText += ", ";
            }
        }
        sizeAndToppingText += ".";
        setSizeAndTopping(sizeAndToppingText);
        setTotalPriceCart(String.valueOf(total[1]));
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerDisplay.invisibleFrame.setAlwaysOnTop(true);
                for (int i = 670; i >= 0; i -= 10) {
                    EditProduct.setSize(621, i);
                    EditProduct.setLocation(EditProduct.getX(), EditProduct.getY() + 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                CustomerDisplay.invisibleFrame.setAlwaysOnTop(false);
                EditProduct.dispose();
                CustomerDisplay.Glasses.set(indexFrame, ItemChange);
            }
        }).start();
    }//GEN-LAST:event_confirmBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton confirmBtn;
    private javax.swing.JLabel deleteProduct;
    private javax.swing.JPanel editProduct;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel minus;
    private javax.swing.JLabel minus1;
    private javax.swing.JLabel more;
    private javax.swing.JScrollPane moreSelect;
    private javax.swing.JLabel plus;
    private javax.swing.JLabel plus1;
    private javax.swing.JLabel productDescription;
    private javax.swing.JLabel productDescriptionMore;
    private javax.swing.JLabel productImg;
    private javax.swing.JLabel productImg1;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productName1;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productPrice1;
    private javax.swing.JLabel quantity;
    private javax.swing.JLabel quantity1;
    private javax.swing.JLabel sizeAndTopping;
    private static javax.swing.JPanel thisProduct;
    // End of variables declaration//GEN-END:variables
}
