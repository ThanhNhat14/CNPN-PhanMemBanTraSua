/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static Controller.Customer.HandleProducts.Products;
import static Controller.Customer.HandleProducts.Toppings;
import static Controller.Customer.HandleProducts.getProducts;
import static Controller.Customer.HandleProducts.getToppings;
import static Controller.LogRes.Customer.Login.editCustomer;
import static Controller.LogRes.Customer.Login.getAccLogin;
import static Controller.LogRes.Customer.Login.Customer;
import static Controller.LogRes.Customer.Login.checkPassword;
import static Controller.LogRes.Customer.Login.changePassword;
import View.Customer.Notification.Message;
import View.Customer.Notification.Warning;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Controller.Customer.HandleBills.*;
import static Controller.Customer.HandleBills.repurchase;
import static Controller.Customer.HandleBills.createOrder;
import static Controller.Customer.HandleBills.CustomerBills;
import static Controller.Customer.HandleBills.CustomerBillDetails;
import static Controller.Customer.HandleBills.getOrders;
import static Controller.Customer.HandleBills.getNewIdItemDetail;
import static Controller.Customer.HandleProducts.Categorys;
import static Controller.Customer.HandleProducts.getCategorys;
import View.LogRes.Customer.CustomerLogin;
import java.text.DecimalFormat;
import static Controller.Customer.HandleBills.cancelOrder;

/**
 *
 * @author ngocn
 */
public class CustomerDisplay extends javax.swing.JFrame {

    private static JDialog dialogMenu;
    private static JDialog dialogProfileMenu;

    private static JViewport emptyViewport;

    public static JFrame notification;
    private static JFrame frameOrders;
    //private static JFrame[] cart = new JFrame[1];

    private static JPanel cartProductsPanel;
    private static JPanel ordersPanel;

    private static JLabel[] totalPriceLabel;
    private static JLabel[] quantityCartLabel;

    private static JRadioButton lastSelected;

    public static List<Map<String, Object>> Glasses;
    public static List<Map<String, Integer>> SelectedToppings;
    private static List<CartProduct> listCartProductsPanel;
    private static Map<Integer, Boolean> idToppingSelected;
    //private static Map<Integer, CartProduct> CartProductsPanel = new HashMap<>();

    public static int[] total;
    int voucherCost;
    int finalCost;
    int finalIdVoucher;
    int finalQuantity;
    String finalPaymentMethodsSelected;
    String finalNoteText;
    public static int[] numberItemCart;
    private static int[] idGlass;
    private static int[] idProductSelected;
    private static int[] sizeProductSelected;
    private static int[] idGlassSize;
    private static String nameProductSelected;
    private static String imageProductSelected;
    private static String sizeAndTopping;

    /**
     * Creates new form CustomerDisplay
     */
    private Point initialClick;

    public CustomerDisplay() {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
//        Layout.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                // Lấy tọa độ chuột khi nhấn
//                initialClick = e.getPoint();
//            }
//
//        });
//        Layout.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                // Lấy tọa độ chuột khi kéo
//                int x = getLocation().x + e.getX() - initialClick.x;
//                int y = getLocation().y + e.getY() - initialClick.y;
//
//                // Di chuyển cửa sổ đến vị trí mới
//                setLocation(x, y);
//            }
//        });
    }

    public CustomerDisplay(int something) {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
//        Layout.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                // Lấy tọa độ chuột khi nhấn
//                initialClick = e.getPoint();
//            }
//
//        });
//        Layout.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                // Lấy tọa độ chuột khi kéo
//                int x = getLocation().x + e.getX() - initialClick.x;
//                int y = getLocation().y + e.getY() - initialClick.y;
//
//                // Di chuyển cửa sổ đến vị trí mới
//                setLocation(x, y);
//            }
//        });

        //setAlwaysOnTop(true);
        setSomeThing();
        setProducts("");
        setDialogMenu();
        //setDialogCart();
        setDialogProfileMenu();
        setNameMenu();
        setQuantityCart(0);
    }

    void setSomeThing() {
        dialogMenu = new JDialog();
        frameOrders = new JFrame();
        dialogProfileMenu = new JDialog();

        emptyViewport = new JViewport();

        notification = new JFrame();

        cartProductsPanel = new JPanel();
        ordersPanel = new JPanel();

        totalPriceLabel = new JLabel[1];
        quantityCartLabel = new JLabel[1];

        lastSelected = new JRadioButton();

        Glasses = new ArrayList<>();
        listCartProductsPanel = new ArrayList<>();
        idToppingSelected = new HashMap<>();

        total = new int[2];
        voucherCost = 0;
        finalCost = 0;
        finalQuantity = 0;
        finalIdVoucher = 0;
        finalPaymentMethodsSelected = "";
        finalNoteText = "";
        numberItemCart = new int[1];
        idGlass = new int[1];
        idProductSelected = new int[1];
        sizeProductSelected = new int[1];
        idGlassSize = new int[1];
        nameProductSelected = "";
        imageProductSelected = "";
        sizeAndTopping = "";
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        group.add(otherGender);
        totalPriceLabel[0] = totalPriceCart;
        quantityCartLabel[0] = quantityCart;
        idGlass[0] = getNewIdItemDetail();

        setScrollPane(productsAll);
        setScrollPane(cartProducts);
        setScrollPane(paymentProducts);
        setScrollPane(waitConfirms);
        cartProductsPanel.setLayout(new BoxLayout(cartProductsPanel, BoxLayout.Y_AXIS));
    }

    public int quantityNumber() {
        String quantityString = quantity.getText();
        int quantityInt = Integer.parseInt(quantityString);
        return quantityInt;
    }

    public void setProductImg(JLabel productImg) {
        this.productImg = productImg;
    }

    public void setProductPrice(JLabel productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductName(JLabel productName) {
        this.productName = productName;
    }

    public void setProductDescription(JLabel productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductDescriptionMore(JLabel productDescriptionMore) {
        this.productDescriptionMore = productDescriptionMore;
    }

    public void setQuantityCart(int num) {
        numberItemCart[0] = num;
        if (num != 0) {
            quantityCartLabel[0].setText(String.valueOf(num));
        } else {
            quantityCartLabel[0].setText("");
        }
        quantityCart = quantityCartLabel[0];
    }

    void setNameMenu() {
        if (!Customer.get("nameCustomer").equals("")) {
            customerName.setText(String.valueOf(Customer.get("nameCustomer")));
        } else if (!Customer.get("userName").equals("")) {
            customerName.setText(String.valueOf(Customer.get("userName")));
        } else {
            customerName.setText(String.valueOf(Customer.get("numberPhoneCustomer")));
        }
    }

    void setInvisibleFrame() {
        invisibleFrame.setUndecorated(true);
        invisibleFrame.setSize(new java.awt.Dimension(621, 1344));
        invisibleFrame.setBackground(new Color(0, 0, 0, 60));
        invisibleFrame.setUndecorated(true);
        invisibleFrame.setResizable(false);
        invisibleFrame.setLocationRelativeTo(null);
        invisibleFrame.setVisible(true);
    }

    void setInvisibleFrame1() {
        invisibleFrame1.setUndecorated(true);
        invisibleFrame1.setSize(new java.awt.Dimension(621, 1344));
        invisibleFrame1.setBackground(new Color(0, 0, 0, 60));
        invisibleFrame1.setUndecorated(true);
        invisibleFrame1.setResizable(false);
        invisibleFrame1.setLocationRelativeTo(null);
        invisibleFrame1.setVisible(true);
    }

    public JFrame getSelectProduct(String productImg, String priceM, String priceL, String productName, String productDescription) {

        SelectedToppings = new ArrayList<>();
        if (numberItemCart[0] == 0) {
            setQuantityCart(numberItemCart[0]);
            total[1] = 0;
            listCartProductsPanel.clear();
            Glasses.clear();
            idGlass[0] = getNewIdItemDetail();
        }
        total[0] = Integer.parseInt(priceM);
        sizeProductSelected[0] = 1;
        sizeAndTopping = "Size: Vừa | Topping: ";
        idToppingSelected.clear();
        setInvisibleFrame();
        selectProduct.setAlwaysOnTop(true);
        selectProduct.setUndecorated(true);
        selectProduct.setResizable(false);
        selectProduct.setAlwaysOnTop(true);
        selectProduct.setLocationRelativeTo(null);
        //selectProduct.setBounds(458, 205, 621, 670);
        selectProduct.setBounds(458, 885, 621, 0);
        moreSelect.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        moreSelect.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        moreSelect.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
        this.productImg.setIcon(new javax.swing.ImageIcon(getClass().getResource(productImg)));
        imageProductSelected = productImg;
        this.productImg.setName(productImg);
        this.productPrice.setText("₫" + priceM);
        setTotalPrice(String.valueOf(total[0]));
        this.productName.setText(productName);
        nameProductSelected = productName;
        this.productDescription.setText(productDescription);
        this.productPrice.setName("0");

        int size1Price = Integer.parseInt(priceL) - total[0];
        //int size2Price = (int) (Math.ceil((size1Price * 2) / 1000) * 1000);

        moreSelectPanel.setLayout(new BoxLayout(moreSelectPanel, BoxLayout.Y_AXIS));
        JLabel sizeLable = new JLabel();
        sizeLable.setFont(new java.awt.Font("Segoe UI", 0, 14));
        sizeLable.setForeground(new java.awt.Color(146, 146, 148));
        sizeLable.setText("Size (Chọn 1)");
        moreSelectPanel.add(sizeLable);
        moreSelectPanel.add(Box.createVerticalStrut(2));

        Size size0 = new Size();
        size0.setTextSize("Vừa", "0");
        //size0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        size0.getSizeCheck().setSelected(true);
        moreSelectPanel.add(size0);
        moreSelectPanel.add(Box.createVerticalStrut(2));

        if (Integer.parseInt(priceL) != 0) {
            Size size1 = new Size();
            size1.setTextSize("Lớn", String.valueOf(size1Price));
            this.productPrice.setName(String.valueOf(size1Price));
            //size1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            moreSelectPanel.add(size1);
            moreSelectPanel.add(Box.createVerticalStrut(2));

            ButtonGroup group = new ButtonGroup();
            group.add(size0.getSizeCheck());
            group.add(size1.getSizeCheck());

            lastSelected = size0.getSizeCheck();
            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (selectedButton == size1.getSizeCheck()) {
                            total[0] += (size1Price * quantityNumber());
                            setTotalPrice(String.valueOf(total[0]));
                            lastSelected = selectedButton;
                            sizeAndTopping = sizeAndTopping.replace("Vừa", "Lớn");
                            sizeProductSelected[0] = 2;
                            return;
                        } else {
                            total[0] -= (size1Price * quantityNumber());
                            setTotalPrice(String.valueOf(total[0]));
                            lastSelected = selectedButton;
                            sizeAndTopping = sizeAndTopping.replace("Lớn", "Vừa");
                            sizeProductSelected[0] = 1;
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
        toppingLable.setText("Topping (Tối đa " + Toppings.size() + ")");
        moreSelectPanel.add(toppingLable);

        int[] priceTopping = new int[Toppings.size()];
        for (int i = 0; i < Toppings.size(); i++) {
            Map<String, Object> Topping = Toppings.get(i);
            if ((boolean) Topping.get("status")) {
                Topping topping = new Topping();
                //idToppingSelected.put(Integer.parseInt(String.valueOf(Topping.get("idTopping"))), false);
                //topping.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                topping.setTopping((String) Topping.get("nameTopping"), String.valueOf(Topping.get("priceTopping")), String.valueOf(Topping.get("idTopping")));
                moreSelectPanel.add(topping);

                final int index = i;
                priceTopping[index] = Integer.parseInt(String.valueOf(Topping.get("priceTopping")));

                topping.getPlus().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int quantityProduct = Integer.parseInt(quantity.getText());
                        int quantityTopping = topping.getQuantity();

                        total[0] /= quantityProduct;
                        total[0] -= (topping.getPriceTopping() * (quantityTopping - 1));
                        total[0] += (topping.getPriceTopping() * quantityTopping);
                        total[0] *= quantityProduct;
                        setTotalPrice(String.valueOf(total[0]));
                    }

                });

                topping.getMinus().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (topping.getCheck()) {
                            int quantityProduct = Integer.parseInt(quantity.getText());
                            int quantityTopping = topping.getQuantity();

                            total[0] /= quantityProduct;
                            total[0] -= (topping.getPriceTopping() * (quantityTopping + 1));
                            total[0] += (topping.getPriceTopping() * quantityTopping);
                            total[0] *= quantityProduct;
                            setTotalPrice(String.valueOf(total[0]));
                        }
                    }

                });
                moreSelectPanel.add(Box.createVerticalStrut(2));
            }
        }
        selectProduct.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 670; i += 10) {
                    selectProduct.setSize(621, i);
                    selectProduct.setLocation(selectProduct.getX(), selectProduct.getY() - 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        return selectProduct;
    }

    public static void setTotalPrice(String price) {
        add.setText("Thêm vào giỏ hàng - ₫" + price);
    }

    void setScrollPane(JScrollPane js) {
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        js.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
        js.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 5));
    }

    void setProducts(String key) {
        getCategorys();
        getProducts(key);
        JPanel productsList = new JPanel();
        productsList.setLayout(new BoxLayout(productsList, BoxLayout.Y_AXIS));
        for (int j = 0; j < Products.size(); j++) {
            Map<String, Object> Product = Products.get(j);
            if ((boolean) Product.get("status")) {
                Product product = new Product();
                product.setTextProductName((String) Product.get("nameProduct"), String.valueOf(Product.get("idCategory")));
                product.setTextProductDescription((String) Product.get("description"));
                product.setImage((String) Product.get("imageProduct"));
                product.setTextProductPrice(String.valueOf("₫" + Product.get("priceM")));
                product.setPriceL(String.valueOf(Product.get("priceL")));
                product.getProductAdd().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        idProductSelected[0] = Integer.parseInt((String) Product.get("idProduct"));
                    }

                });

                productsList.add(product);
                if (j != Products.size()) {
                    productsList.add(Box.createVerticalStrut(2));
                }
            }
        }
        productsAll.setViewportView(productsList);

        for (int i = 0; i < Categorys.size(); i++) {
            Map<String, Object> Category = Categorys.get(i);
            if ((boolean) Category.get("statusCategory")) {
                JScrollPane scrollPane = new JScrollPane();
                setScrollPane(scrollPane);

                emptyViewport = new JViewport();
                scrollPane.setViewport(emptyViewport);
                productsList = new JPanel();
                productsList.setLayout(new BoxLayout(productsList, BoxLayout.Y_AXIS));
//        productsList.setPreferredSize(new Dimension(621, 100));
                scrollPane.setViewportView(productsList);
                for (int j = 0; j < Products.size(); j++) {
                    Map<String, Object> Product = Products.get(j);
                    int a = (int) Product.get("idCategory");
                    int b = (int) Category.get("idCategory");
                    if (a == b && (boolean) Product.get("status")) {
                        Product product = new Product();
                        product.setTextProductName((String) Product.get("nameProduct"), String.valueOf(Product.get("idCategory")));
                        product.setTextProductDescription((String) Product.get("description"));
                        product.setImage((String) Product.get("imageProduct"));
                        product.setTextProductPrice(String.valueOf("₫" + Product.get("priceM")));
                        product.setPriceL(String.valueOf(Product.get("priceL")));
                        product.getProductAdd().addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                idProductSelected[0] = Integer.parseInt((String) Product.get("idProduct"));
                            }

                        });

                        productsList.add(product);
                        productsList.add(Box.createVerticalStrut(2));
                    }
                }
                scrollPane.setViewportView(productsList);

                int tabIndex = productsLayout.indexOfTab((String) Category.get("nameCategory"));
                if (tabIndex != -1) {
                    // Lấy nội dung hiện tại của tab
                    Component component = productsLayout.getComponentAt(tabIndex);

                    // Kiểm tra nếu nội dung là một JScrollPane
                    if (component instanceof JScrollPane) {
                        // Thay đổi nội dung của JScrollPane tại đây
                        JScrollPane scrollPane1 = (JScrollPane) component;
                        scrollPane1.setViewportView(productsList);
                    } else {
                        // Xử lý khi nội dung không phải là JScrollPane
                    }
                } else {
                    productsLayout.addTab((String) Category.get("nameCategory"), scrollPane);
                }
            }
        }
    }

//    void setTab(int index) {
//        Iterator<Map<String, Object>> iterator = Products.iterator();
//        while (iterator.hasNext()) {
//            Map<String, Object> Product = iterator.next();
//            int x = (Integer) Product.get("idCategory");
//            if (!(x == index)) {
//                iterator.remove();
//            }
//        }
//    }
    public static int widthMenu = 298;
    public static int heightMenu = 865;

    void setDialogMenu() {
//        if(Customer.get("nameCustomer").equals("")) customerName.setText((String) Customer.get("userName"));
//        else customerName.setText((String) Customer.get("nameCustomer"));
        dialogMenu = new JDialog();
        dialogMenu.setUndecorated(true);
        dialogMenu.setAlwaysOnTop(true);
        dialogMenu.add(menu);
        dialogMenu.setBounds(458, 0, 0, 865);

    }

    void openMenu() {
        setNameMenu();
        setDialogMenu();
        dialogMenu.setVisible(true);
//        searchText.setEnabled(false);
//        productsLayout.setEnabled(false);
        infomation.setEnabled(false);
        waitConfirmsLabel.setEnabled(false);
        waitOrder.setEnabled(false);
        historyOrder.setEnabled(false);
        cancelOrder.setEnabled(false);
        changePass.setEnabled(false);
        logoutBtn.setEnabled(false);
        setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= widthMenu; i += 2) {
                    dialogMenu.setSize(i, heightMenu);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                getOrders((int) Customer.get("idCustomer"));
                infomation.setEnabled(true);
                waitConfirmsLabel.setEnabled(true);
                waitOrder.setEnabled(true);
                historyOrder.setEnabled(true);
                cancelOrder.setEnabled(true);
                changePass.setEnabled(true);
                logoutBtn.setEnabled(true);
            }
        }).start();
    }

    void closeMenu() {
        infomation.setEnabled(false);
        waitConfirmsLabel.setEnabled(false);
        waitOrder.setEnabled(false);
        historyOrder.setEnabled(false);
        cancelOrder.setEnabled(false);
        changePass.setEnabled(false);
        logoutBtn.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = widthMenu; i >= 0; i -= 2) {
                    dialogMenu.setSize(i, heightMenu);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dialogMenu.dispose();
                invisibleFrame1.setAlwaysOnTop(false);
                invisibleFrame1.dispose();
                setEnabled(true);
                if (invisibleFrame.isVisible()) {
                    invisibleFrame.dispose();
                }
            }
        }).start();
//        searchText.setEnabled(true);
//        productsLayout.setEnabled(true);
    }

    public static int widthProfileMenu = 621;
    public static int heightProfileMenu = 350;

    void setDialogProfileMenu() {
        dialogProfileMenu = new JDialog();
        dialogProfileMenu.setUndecorated(true);
        invisibleFrame1.setAlwaysOnTop(true);
        dialogProfileMenu.setAlwaysOnTop(true);
        dialogProfileMenu.add(profile);
        dialogProfileMenu.setBounds(458, 0, 621, 0);

    }

    void openProfileMenu() {

        setDialogProfileMenu();
        if (dialogMenu.isVisible()) {
            dialogMenu.setAlwaysOnTop(false);
            setAlwaysOnTop(false);
        } else {
            setInvisibleFrame1();
            invisibleFrame.setAlwaysOnTop(true);
        }
        //setEnabled(false);
        dialogProfileMenu.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= heightProfileMenu; i += 5) {
                    dialogProfileMenu.setSize(widthProfileMenu, i);
                    //dialogProfileMenu.setLocation(dialogProfileMenu.getX(), dialogProfileMenu.getY() - 1);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    void closeProfileMenu() {
        confirmChangePassword.setForeground(new java.awt.Color(210, 154, 95));
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = heightProfileMenu; i >= 0; i -= 5) {
                    dialogProfileMenu.setSize(widthProfileMenu, i);
                    //dialogProfileMenu.setLocation(dialogProfileMenu.getX(), dialogProfileMenu.getY() + 10); // Di chuyển dialog xuống dưới
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dialogProfileMenu.dispose();
                //setEnabled(true);
            }
        }).start();
        if (dialogMenu.isVisible()) {
            dialogMenu.setAlwaysOnTop(true);
            invisibleFrame.setAlwaysOnTop(true);
        } else {
            invisibleFrame1.setAlwaysOnTop(false);
            invisibleFrame1.dispose();
        }
    }

    public static int widthDialogOrders = 621;
    public static int heightDialogOrders = 670;

    void setDialogOrders() {
        frameOrders = new JFrame();
        frameOrders.setUndecorated(true);
        frameOrders.setAlwaysOnTop(true);
        frameOrders.add(waitConfirmsMenu);
        frameOrders.setBounds(458, 875, 621, 0);

    }

    void openOrders() {
        setDialogOrders();
        frameOrders.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= heightDialogOrders; i += 10) {
                    frameOrders.setSize(widthDialogOrders, i);
                    frameOrders.setLocation(frameOrders.getX(), frameOrders.getY() - 10);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    void closeOrders() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = heightDialogOrders; i >= 0; i -= 10) {
                    frameOrders.setSize(widthDialogOrders, i);
                    frameOrders.setLocation(frameOrders.getX(), frameOrders.getY() + 10); // Di chuyển dialog xuống dưới
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                frameOrders.dispose();
                //setEnabled(true);
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

        selectProduct = new javax.swing.JFrame();
        addNewProduct = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        thisProduct = new javax.swing.JPanel();
        productImg = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        productDescription = new javax.swing.JLabel();
        productDescriptionMore = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        plus = new javax.swing.JLabel();
        minus = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        moreSelect = new javax.swing.JScrollPane();
        moreSelectPanel = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        invisibleFrame = new javax.swing.JFrame();
        cart = new javax.swing.JFrame();
        cartPanelLayout = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cartProducts = new javax.swing.JScrollPane();
        order = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totalPriceCart = new javax.swing.JLabel();
        payment = new javax.swing.JFrame();
        paymentPanelLayout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        paymentProducts = new javax.swing.JScrollPane();
        pay = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        totalPricePayment = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        nameAndPhonePayment = new javax.swing.JLabel();
        addressPayment = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        totalText = new javax.swing.JLabel();
        totalCost = new javax.swing.JLabel();
        deliveryChargesCost = new javax.swing.JLabel();
        deliveryChargesText = new javax.swing.JLabel();
        voucherCost1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        voucherComboBox = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        paymentMethods = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        note = new javax.swing.JTextArea();
        voucherWarning = new javax.swing.JLabel();
        totalPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        viewMoreOrder = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        QR = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        introduceFrame = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        emptyPanel = new javax.swing.JPanel();
        cancelReason = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        continueBtn = new javax.swing.JLabel();
        cancel = new javax.swing.JLabel();
        changePaymentMethod = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        continueBtn1 = new javax.swing.JLabel();
        cancel1 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        voucher1 = new javax.swing.JComboBox<>();
        voucherWarning1 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        repurchaseCost = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        totalText1 = new javax.swing.JLabel();
        totalCost1 = new javax.swing.JLabel();
        deliveryChargesCost1 = new javax.swing.JLabel();
        deliveryChargesText1 = new javax.swing.JLabel();
        voucherCost2 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        invisibleFrame1 = new javax.swing.JFrame();
        profile = new javax.swing.JPanel();
        watch = new javax.swing.JPanel();
        fullNameLable = new javax.swing.JLabel();
        genderLable = new javax.swing.JLabel();
        birthLable = new javax.swing.JLabel();
        phoneLable = new javax.swing.JLabel();
        addressLable = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        editBtn = new javax.swing.JLabel();
        phoneProfile = new javax.swing.JLabel();
        apartmentNumberProfile = new javax.swing.JLabel();
        birthProfile = new javax.swing.JLabel();
        genderProfile = new javax.swing.JLabel();
        fullNameProfile = new javax.swing.JLabel();
        wardsProfile = new javax.swing.JLabel();
        districtProfile = new javax.swing.JLabel();
        phoneProfile5 = new javax.swing.JLabel();
        edit = new javax.swing.JPanel();
        address = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        birth = new javax.swing.JLabel();
        otherGender = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        gender = new javax.swing.JLabel();
        fullName = new javax.swing.JLabel();
        phoneText = new javax.swing.JTextField();
        fullNameText = new javax.swing.JTextField();
        dayBirth = new javax.swing.JComboBox<>();
        monthBirth = new javax.swing.JComboBox<>();
        yearBirth = new javax.swing.JComboBox<>();
        confirmBtn = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        apartmentNumber = new javax.swing.JTextField();
        wards = new javax.swing.JComboBox<>();
        district = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        changePassword = new javax.swing.JPanel();
        birth1 = new javax.swing.JLabel();
        gender1 = new javax.swing.JLabel();
        fullName1 = new javax.swing.JLabel();
        confirmChangePassword = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        showpass = new javax.swing.JCheckBox();
        newPasswordConfirm = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        oldPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        warningChangePassword = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        exitMenu = new javax.swing.JLabel();
        customerName = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        infomation = new javax.swing.JLabel();
        waitConfirmsLabel = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        waitOrder = new javax.swing.JLabel();
        historyOrder = new javax.swing.JLabel();
        cancelOrder = new javax.swing.JLabel();
        changePass = new javax.swing.JLabel();
        introduce = new javax.swing.JLabel();
        Layout = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuIcon = new javax.swing.JLabel();
        cartIcon = new javax.swing.JLabel();
        quantityCart = new javax.swing.JLabel();
        trasuaTASU = new javax.swing.JLabel();
        searchIcon = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        productsLayout = new javax.swing.JTabbedPane();
        productsAll = new javax.swing.JScrollPane();
        waitConfirmsMenu = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        orderStatus = new javax.swing.JLabel();
        waitConfirms = new javax.swing.JScrollPane();

        selectProduct.setSize(new java.awt.Dimension(621, 675));

        addNewProduct.setBackground(new java.awt.Color(255, 255, 255));
        addNewProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        addNewProduct.setPreferredSize(new java.awt.Dimension(621, 660));

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
        jLabel3.setText("Thêm món mới");

        thisProduct.setBackground(new java.awt.Color(255, 255, 255));

        productImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dự án mới.png"))); // NOI18N
        productImg.setToolTipText("");

        productName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        productName.setForeground(new java.awt.Color(0, 0, 0));
        productName.setText("Trà sữa Truyền Thống");

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
        minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-0.png"))); // NOI18N
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout thisProductLayout = new javax.swing.GroupLayout(thisProduct);
        thisProduct.setLayout(thisProductLayout);
        thisProductLayout.setHorizontalGroup(
            thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thisProductLayout.createSequentialGroup()
                .addComponent(productImg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thisProductLayout.createSequentialGroup()
                        .addComponent(productName)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                        .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(thisProductLayout.createSequentialGroup()
                                .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(thisProductLayout.createSequentialGroup()
                                .addComponent(productDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(productDescriptionMore)))
                        .addGap(15, 15, 15))))
        );
        thisProductLayout.setVerticalGroup(
            thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                .addComponent(productName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productDescription)
                    .addComponent(productDescriptionMore))
                .addGroup(thisProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thisProductLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(productPrice))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(productImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(minus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(plus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        moreSelect.setBackground(new java.awt.Color(255, 255, 255));
        moreSelect.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        moreSelect.setToolTipText("");
        moreSelect.setVerifyInputWhenFocusTarget(false);

        moreSelectPanel.setBackground(new java.awt.Color(239, 238, 243));
        moreSelectPanel.setForeground(new java.awt.Color(239, 238, 243));

        javax.swing.GroupLayout moreSelectPanelLayout = new javax.swing.GroupLayout(moreSelectPanel);
        moreSelectPanel.setLayout(moreSelectPanelLayout);
        moreSelectPanelLayout.setHorizontalGroup(
            moreSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        moreSelectPanelLayout.setVerticalGroup(
            moreSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );

        moreSelect.setViewportView(moreSelectPanel);

        add.setBackground(new java.awt.Color(27, 43, 32));
        add.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        add.setForeground(new java.awt.Color(210, 154, 95));
        add.setText("Thêm vào giỏ hàng ");
        add.setBorder(null);
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addNewProductLayout = new javax.swing.GroupLayout(addNewProduct);
        addNewProduct.setLayout(addNewProductLayout);
        addNewProductLayout.setHorizontalGroup(
            addNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thisProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(moreSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
            .addGroup(addNewProductLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(addNewProductLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addNewProductLayout.setVerticalGroup(
            addNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewProductLayout.createSequentialGroup()
                .addGroup(addNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(thisProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moreSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout selectProductLayout = new javax.swing.GroupLayout(selectProduct.getContentPane());
        selectProduct.getContentPane().setLayout(selectProductLayout);
        selectProductLayout.setHorizontalGroup(
            selectProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        selectProductLayout.setVerticalGroup(
            selectProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        invisibleFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        invisibleFrame.setSize(new java.awt.Dimension(621, 1344));
        invisibleFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invisibleFrameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout invisibleFrameLayout = new javax.swing.GroupLayout(invisibleFrame.getContentPane());
        invisibleFrame.getContentPane().setLayout(invisibleFrameLayout);
        invisibleFrameLayout.setHorizontalGroup(
            invisibleFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        invisibleFrameLayout.setVerticalGroup(
            invisibleFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        cartPanelLayout.setBackground(new java.awt.Color(255, 255, 255));
        cartPanelLayout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cartPanelLayout.setPreferredSize(new java.awt.Dimension(621, 660));

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Giỏ hàng");

        cartProducts.setBackground(new java.awt.Color(255, 255, 255));
        cartProducts.setForeground(new java.awt.Color(255, 255, 255));
        cartProducts.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cartProducts.setToolTipText("");
        cartProducts.setPreferredSize(new java.awt.Dimension(630, 581));
        cartProducts.setVerifyInputWhenFocusTarget(false);

        order.setBackground(new java.awt.Color(27, 43, 32));
        order.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        order.setForeground(new java.awt.Color(210, 154, 95));
        order.setText("Đặt hàng");
        order.setBorder(null);
        order.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Xóa tất cả");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart-icon.png"))); // NOI18N
        jLabel13.setToolTipText("");

        totalPriceCart.setFont(new java.awt.Font("Serif", 1, 20)); // NOI18N
        totalPriceCart.setForeground(new java.awt.Color(0, 0, 0));
        totalPriceCart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalPriceCart.setText("₫0");

        javax.swing.GroupLayout cartPanelLayoutLayout = new javax.swing.GroupLayout(cartPanelLayout);
        cartPanelLayout.setLayout(cartPanelLayoutLayout);
        cartPanelLayoutLayout.setHorizontalGroup(
            cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPanelLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cartPanelLayoutLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalPriceCart, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cartPanelLayoutLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(255, 255, 255)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        cartPanelLayoutLayout.setVerticalGroup(
            cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayoutLayout.createSequentialGroup()
                .addGroup(cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(totalPriceCart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cartLayout = new javax.swing.GroupLayout(cart.getContentPane());
        cart.getContentPane().setLayout(cartLayout);
        cartLayout.setHorizontalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartPanelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cartLayout.setVerticalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartPanelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
        );

        paymentPanelLayout.setBackground(new java.awt.Color(255, 255, 255));
        paymentPanelLayout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        paymentPanelLayout.setPreferredSize(new java.awt.Dimension(621, 660));
        paymentPanelLayout.setPreferredSize(new Dimension(621, 670));

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Thanh toán");

        paymentProducts.setBackground(new java.awt.Color(255, 255, 255));
        paymentProducts.setForeground(new java.awt.Color(255, 255, 255));
        paymentProducts.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paymentProducts.setToolTipText("");
        paymentProducts.setPreferredSize(new java.awt.Dimension(630, 150));
        paymentProducts.setVerifyInputWhenFocusTarget(false);

        pay.setBackground(new java.awt.Color(27, 43, 32));
        pay.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        pay.setForeground(new java.awt.Color(210, 154, 95));
        pay.setText("Đặt đơn");
        pay.setBorder(null);
        pay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tổng cộng: ");
        jLabel24.setToolTipText("");

        totalPricePayment.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        totalPricePayment.setForeground(new java.awt.Color(0, 0, 0));
        totalPricePayment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalPricePayment.setText("0₫");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Địa chỉ giao hàng");

        nameAndPhonePayment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nameAndPhonePayment.setForeground(new java.awt.Color(0, 0, 0));
        nameAndPhonePayment.setText("tên | sđt");

        addressPayment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        addressPayment.setForeground(new java.awt.Color(0, 0, 0));
        addressPayment.setText("Địa chỉ giao hàng");

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));

        totalText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalText.setForeground(new java.awt.Color(0, 0, 0));
        totalText.setText("Tổng cộng (x món)");

        totalCost.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalCost.setForeground(new java.awt.Color(0, 0, 0));
        totalCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalCost.setText("0₫");

        deliveryChargesCost.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deliveryChargesCost.setForeground(new java.awt.Color(0, 0, 0));
        deliveryChargesCost.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deliveryChargesCost.setText("16000₫");

        deliveryChargesText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deliveryChargesText.setForeground(new java.awt.Color(0, 0, 0));
        deliveryChargesText.setText("Phí giao hàng");

        voucherCost1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voucherCost1.setForeground(new java.awt.Color(0, 0, 0));
        voucherCost1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        voucherCost1.setText("-0₫");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Mã khuyến mãi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalText)
                    .addComponent(deliveryChargesText)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voucherCost1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryChargesCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalText)
                    .addComponent(totalCost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryChargesText)
                    .addComponent(deliveryChargesCost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(voucherCost1))
                .addGap(37, 37, 37))
        );

        voucherComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        voucherComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn mã giảm ---" }));
        voucherComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherComboBoxActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Mã giảm: ");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Phương thức thanh toán: ");

        paymentMethods.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        paymentMethods.setMaximumRowCount(3);
        paymentMethods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn phương thức thanh toán ---", "Thanh toán khi nhận hàng", "Chuyển khoản ngân hàng" }));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Ghi chú:");

        note.setColumns(1);
        note.setRows(1);
        note.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noteKeyPressed(evt);
            }
        });
        note.setLineWrap(true);
        note.setWrapStyleWord(true);
        jScrollPane1.setViewportView(note);

        voucherWarning.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        voucherWarning.setForeground(new java.awt.Color(255, 0, 51));
        voucherWarning.setText("Chưa đủ điều kiện để sử dụng mã!");

        javax.swing.GroupLayout paymentPanelLayoutLayout = new javax.swing.GroupLayout(paymentPanelLayout);
        paymentPanelLayout.setLayout(paymentPanelLayoutLayout);
        paymentPanelLayoutLayout.setHorizontalGroup(
            paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(paymentPanelLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayoutLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(238, 238, 238)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayoutLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalPricePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paymentPanelLayoutLayout.createSequentialGroup()
                        .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paymentPanelLayoutLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameAndPhonePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paymentPanelLayoutLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(voucherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(voucherWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paymentPanelLayoutLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paymentMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paymentPanelLayoutLayout.setVerticalGroup(
            paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayoutLayout.createSequentialGroup()
                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameAndPhonePayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressPayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(voucherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voucherWarning))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(paymentMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentPanelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(totalPricePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout paymentLayout = new javax.swing.GroupLayout(payment.getContentPane());
        payment.getContentPane().setLayout(paymentLayout);
        paymentLayout.setHorizontalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentPanelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paymentLayout.setVerticalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentPanelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );

        totalPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(146, 146, 148));
        jLabel18.setText("Tổng tiền hàng");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Thành tiền");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(210, 154, 95));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("₫23000");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(146, 146, 148));
        jLabel25.setText("Phí vận chuyển");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(146, 146, 148));
        jLabel26.setText("Giảm giá");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(146, 146, 148));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("₫7000");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(146, 146, 148));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("₫16000");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(146, 146, 148));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("₫0");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(146, 146, 148));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Thanh toán khi nhận hàng");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Phương thức thanh toán");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Mã hóa đơn");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("1");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(146, 146, 148));
        jLabel38.setText("Thời gian đặt hàng");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(146, 146, 148));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("01-05-2024");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(210, 154, 95));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Hủy đơn");

        javax.swing.GroupLayout totalPanelLayout = new javax.swing.GroupLayout(totalPanel);
        totalPanel.setLayout(totalPanelLayout);
        totalPanelLayout.setHorizontalGroup(
            totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator9)
                    .addComponent(jSeparator7)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, totalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(totalPanelLayout.createSequentialGroup()
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, totalPanelLayout.createSequentialGroup()
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(349, 349, 349)
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                    .addGroup(totalPanelLayout.createSequentialGroup()
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(31, 31, 31))
        );
        totalPanelLayout.setVerticalGroup(
            totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewMoreOrder.setBackground(new java.awt.Color(255, 255, 255));
        viewMoreOrder.setPreferredSize(new java.awt.Dimension(597, 34));
        viewMoreOrder.setVerifyInputWhenFocusTarget(false);

        jLabel20.setBackground(new java.awt.Color(255, 0, 51));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(210, 154, 95));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Hủy đơn");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel20.setIconTextGap(6);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(146, 146, 148));
        jLabel23.setText("Ngày");

        javax.swing.GroupLayout viewMoreOrderLayout = new javax.swing.GroupLayout(viewMoreOrder);
        viewMoreOrder.setLayout(viewMoreOrderLayout);
        viewMoreOrderLayout.setHorizontalGroup(
            viewMoreOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMoreOrderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(30, 30, 30))
        );
        viewMoreOrderLayout.setVerticalGroup(
            viewMoreOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMoreOrderLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(viewMoreOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewMoreOrder.getAccessibleContext().setAccessibleName("");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Xác nhận");
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel41MouseExited(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_Mb.png"))); // NOI18N

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Rick.png"))); // NOI18N
        jLabel43.setText("jLabel43");
        jLabel43.setMaximumSize(new java.awt.Dimension(325, 325));
        jLabel43.setMinimumSize(new java.awt.Dimension(325, 325));
        jLabel43.setPreferredSize(new java.awt.Dimension(325, 325));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Ngân hàng: MB BANK");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Chủ tài khoản: VO VAN NGOC");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Số tài khoản: 0123456789");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Cần thanh toán: 100000 VND");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel41))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel42))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addContainerGap())
        );

        javax.swing.GroupLayout QRLayout = new javax.swing.GroupLayout(QR.getContentPane());
        QR.getContentPane().setLayout(QRLayout);
        QRLayout.setHorizontalGroup(
            QRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        QRLayout.setVerticalGroup(
            QRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        introduceFrame.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("ĐỒ ÁN NHẬP MÔN CÔNG NGHỆ PHẦN MỀM");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("PHẦN MỀM BÁN TRÀ SỮA");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Thành viên");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Võ Văn Ngọc - N21DCAT035");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Võ Hồng Nguyên - N21DCAT036");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Nguyễn Thanh Nhật - N21DCAT037");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Ngô Hoàng Phan - N19DCAT059");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel48))
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addContainerGap(784, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout introduceFrameLayout = new javax.swing.GroupLayout(introduceFrame.getContentPane());
        introduceFrame.getContentPane().setLayout(introduceFrameLayout);
        introduceFrameLayout.setHorizontalGroup(
            introduceFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        introduceFrameLayout.setVerticalGroup(
            introduceFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout emptyPanelLayout = new javax.swing.GroupLayout(emptyPanel);
        emptyPanel.setLayout(emptyPanelLayout);
        emptyPanelLayout.setHorizontalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        cancelReason.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new Color(0, 0, 0, 60));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Lý do hủy đơn: ");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- Chọn lý do hủy đơn ----", "Thời gian chờ quá lâu", "Muốn đổi hình thức thanh toán", "Muốn thay đổi địa chỉ nhận hàng", "Đổi ý không muốn mua nữa" }));

        continueBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        continueBtn.setForeground(new java.awt.Color(0, 0, 0));
        continueBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        continueBtn.setText("Xác nhận");
        continueBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 150, 0), 1, true));
        continueBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continueBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueBtnMouseExited(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancel.setForeground(new java.awt.Color(0, 0, 0));
        cancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancel.setText("Hủy");
        cancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, 371, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(continueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continueBtn)
                    .addComponent(cancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(859, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cancelReasonLayout = new javax.swing.GroupLayout(cancelReason.getContentPane());
        cancelReason.getContentPane().setLayout(cancelReasonLayout);
        cancelReasonLayout.setHorizontalGroup(
            cancelReasonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cancelReasonLayout.setVerticalGroup(
            cancelReasonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changePaymentMethod.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new Color(0, 0, 0, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Phương thước thanh toán:");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn phương thước thanh toán ---", "Thanh toán khi nhận hàng", "Chuyển khoản ngân hàng" }));

        continueBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        continueBtn1.setForeground(new java.awt.Color(0, 0, 0));
        continueBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        continueBtn1.setText("Xác nhận");
        continueBtn1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 150, 0), 1, true));
        continueBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continueBtn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueBtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueBtn1MouseExited(evt);
            }
        });

        cancel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancel1.setForeground(new java.awt.Color(0, 0, 0));
        cancel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancel1.setText("Hủy");
        cancel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        cancel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancel1MouseExited(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Mã giảm: ");

        voucher1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voucher1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn mã giảm ---", "TASU5" }));
        voucher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucher1ActionPerformed(evt);
            }
        });

        voucherWarning1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        voucherWarning1.setForeground(new java.awt.Color(255, 0, 51));
        voucherWarning1.setText("Chưa đủ điều kiện để sử dụng mã!");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Tổng tiền:");

        repurchaseCost.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        repurchaseCost.setForeground(new java.awt.Color(0, 0, 0));
        repurchaseCost.setText("10000");

        jPanel10.setBackground(new java.awt.Color(240, 240, 240));

        totalText1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalText1.setForeground(new java.awt.Color(0, 0, 0));
        totalText1.setText("Tổng cộng (x món)");

        totalCost1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalCost1.setForeground(new java.awt.Color(0, 0, 0));
        totalCost1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalCost1.setText("0₫");

        deliveryChargesCost1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deliveryChargesCost1.setForeground(new java.awt.Color(0, 0, 0));
        deliveryChargesCost1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deliveryChargesCost1.setText("16000₫");

        deliveryChargesText1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deliveryChargesText1.setForeground(new java.awt.Color(0, 0, 0));
        deliveryChargesText1.setText("Phí giao hàng");

        voucherCost2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voucherCost2.setForeground(new java.awt.Color(0, 0, 0));
        voucherCost2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        voucherCost2.setText("-0₫");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Mã khuyến mãi");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalText1)
                    .addComponent(deliveryChargesText1)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voucherCost2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryChargesCost1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCost1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalText1)
                    .addComponent(totalCost1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryChargesText1)
                    .addComponent(deliveryChargesCost1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(voucherCost2))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repurchaseCost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(continueBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voucher1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voucherWarning1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(voucher1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voucherWarning1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continueBtn1)
                    .addComponent(cancel1)
                    .addComponent(jLabel59)
                    .addComponent(repurchaseCost))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(830, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changePaymentMethodLayout = new javax.swing.GroupLayout(changePaymentMethod.getContentPane());
        changePaymentMethod.getContentPane().setLayout(changePaymentMethodLayout);
        changePaymentMethodLayout.setHorizontalGroup(
            changePaymentMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changePaymentMethodLayout.setVerticalGroup(
            changePaymentMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        invisibleFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        invisibleFrame1.setSize(new java.awt.Dimension(621, 1344));
        invisibleFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invisibleFrame1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout invisibleFrame1Layout = new javax.swing.GroupLayout(invisibleFrame1.getContentPane());
        invisibleFrame1.getContentPane().setLayout(invisibleFrame1Layout);
        invisibleFrame1Layout.setHorizontalGroup(
            invisibleFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        invisibleFrame1Layout.setVerticalGroup(
            invisibleFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setBackground(new java.awt.Color(255, 255, 255));
        profile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        profile.setPreferredSize(new java.awt.Dimension(621, 500));
        profile.setLayout(new java.awt.CardLayout());

        watch.setBackground(new java.awt.Color(255, 255, 255));
        watch.setPreferredSize(new java.awt.Dimension(621, 250));

        fullNameLable.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        fullNameLable.setForeground(new java.awt.Color(0, 0, 0));
        fullNameLable.setText("Họ và tên: ");

        genderLable.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        genderLable.setForeground(new java.awt.Color(0, 0, 0));
        genderLable.setText("Giới tính: ");

        birthLable.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        birthLable.setForeground(new java.awt.Color(0, 0, 0));
        birthLable.setText("Ngày sinh: ");

        phoneLable.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        phoneLable.setForeground(new java.awt.Color(0, 0, 0));
        phoneLable.setText("Số điện thoại: ");

        addressLable.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        addressLable.setForeground(new java.awt.Color(0, 0, 0));
        addressLable.setText("Địa chỉ: ");

        jLabel8.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Thông tin tài khoản");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        editBtn.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        editBtn.setForeground(new java.awt.Color(210, 154, 95));
        editBtn.setText("Chỉnh sửa");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editBtnMouseExited(evt);
            }
        });

        phoneProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        phoneProfile.setForeground(new java.awt.Color(0, 0, 0));

        apartmentNumberProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        apartmentNumberProfile.setForeground(new java.awt.Color(0, 0, 0));
        apartmentNumberProfile.setText("Số nhà");

        birthProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        birthProfile.setForeground(new java.awt.Color(0, 0, 0));

        genderProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        genderProfile.setForeground(new java.awt.Color(0, 0, 0));

        fullNameProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        fullNameProfile.setForeground(new java.awt.Color(0, 0, 0));

        wardsProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        wardsProfile.setForeground(new java.awt.Color(0, 0, 0));
        wardsProfile.setText("Phường/Xã");

        districtProfile.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        districtProfile.setForeground(new java.awt.Color(0, 0, 0));
        districtProfile.setText("Quận/Huyện");

        phoneProfile5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        phoneProfile5.setForeground(new java.awt.Color(0, 0, 0));
        phoneProfile5.setText("Thành Phố Hồ Chí Minh");

        javax.swing.GroupLayout watchLayout = new javax.swing.GroupLayout(watch);
        watch.setLayout(watchLayout);
        watchLayout.setHorizontalGroup(
            watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(watchLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneLable)
                    .addComponent(addressLable)
                    .addComponent(birthLable)
                    .addComponent(genderLable)
                    .addComponent(fullNameLable))
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(watchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(175, 175, 175)
                        .addComponent(jLabel9)
                        .addContainerGap())
                    .addGroup(watchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(wardsProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phoneProfile5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phoneProfile)
                            .addComponent(districtProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(apartmentNumberProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullNameProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(genderProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(birthProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, watchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editBtn)
                .addGap(273, 273, 273))
        );
        watchLayout.setVerticalGroup(
            watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, watchLayout.createSequentialGroup()
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(watchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(watchLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fullNameLable)
                            .addComponent(fullNameProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genderLable)
                            .addComponent(genderProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(birthLable))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, watchLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(birthProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLable)
                    .addComponent(phoneProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(watchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLable)
                    .addComponent(apartmentNumberProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wardsProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(districtProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneProfile5)
                .addGap(33, 33, 33)
                .addComponent(editBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        profile.add(watch, "card1");

        edit.setBackground(new java.awt.Color(255, 255, 255));
        edit.setPreferredSize(new java.awt.Dimension(621, 330));

        address.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        address.setForeground(new java.awt.Color(0, 0, 0));
        address.setText("Địa chỉ: ");

        phone.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        phone.setForeground(new java.awt.Color(0, 0, 0));
        phone.setText("Số điện thoại: ");

        birth.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        birth.setForeground(new java.awt.Color(0, 0, 0));
        birth.setText("Ngày sinh: ");

        otherGender.setBackground(new java.awt.Color(255, 255, 255));
        otherGender.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        otherGender.setForeground(new java.awt.Color(0, 0, 0));
        otherGender.setText("Khác");

        female.setBackground(new java.awt.Color(255, 255, 255));
        female.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        female.setForeground(new java.awt.Color(0, 0, 0));
        female.setText("Nữ");

        male.setBackground(new java.awt.Color(255, 255, 255));
        male.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        male.setForeground(new java.awt.Color(0, 0, 0));
        male.setText("Nam");

        gender.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        gender.setForeground(new java.awt.Color(0, 0, 0));
        gender.setText("Giới tính: ");

        fullName.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        fullName.setForeground(new java.awt.Color(0, 0, 0));
        fullName.setText("Họ và tên: ");

        fullNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameTextActionPerformed(evt);
            }
        });

        dayBirth.setMaximumRowCount(32);
        dayBirth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày" }));
        dayBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayBirthActionPerformed(evt);
            }
        });
        dayBirth.setMaximumRowCount(10);
        for(int i = 1; i <= 31; i++){
            if(i < 10) dayBirth.addItem("0" + String.valueOf(i));
            else dayBirth.addItem(String.valueOf(i));
        }

        monthBirth.setMaximumRowCount(13);
        monthBirth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng" }));
        monthBirth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                monthBirthFocusGained(evt);
            }
        });
        monthBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBirthActionPerformed(evt);
            }
        });
        for(int i = 1; i <= 12; i++){
            if(i < 10) monthBirth.addItem("0" + String.valueOf(i));
            else monthBirth.addItem(String.valueOf(i));
        }
        monthBirth.setMaximumRowCount(10);

        yearBirth.setMaximumRowCount(100);
        yearBirth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm" }));
        yearBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBirthActionPerformed(evt);
            }
        });
        for(int i = 0; i <= 100; i++){
            yearBirth.addItem(String.valueOf(2023 - i));
        }
        yearBirth.setMaximumRowCount(10);

        confirmBtn.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        confirmBtn.setForeground(new java.awt.Color(210, 154, 95));
        confirmBtn.setText("Xác nhận");
        confirmBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmBtnMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Thông tin tài khoản");

        apartmentNumber.setText("Số nhà");
        apartmentNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                apartmentNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                apartmentNumberFocusLost(evt);
            }
        });

        wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phường/Xã" }));
        wards.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wardsMouseClicked(evt);
            }
        });

        district.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quận/Huyện", "Huyện Bình Chánh", "Huyện Cần Giờ", "Huyện Củ Chi", "Huyện Hóc Môn", "Huyện Nhà Bè", "Quận 1", "Quận 10", "Quận 11", "Quận 12", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận Bình Tân", "Quận Bình Thạnh", "Quận Gò Vấp", "Quận Phú Nhuận", "Quận Tân Bình", "Quận Tân Phú", "Thành Phố Thủ Đức" }));
        district.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Thành Phố Hồ Chí Minh");
        jLabel7.setToolTipText("");

        javax.swing.GroupLayout editLayout = new javax.swing.GroupLayout(edit);
        edit.setLayout(editLayout);
        editLayout.setHorizontalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gender)
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birth)
                            .addComponent(phone)
                            .addComponent(fullName))
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editLayout.createSequentialGroup()
                                        .addComponent(male)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(female)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(otherGender))
                                    .addComponent(fullNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(editLayout.createSequentialGroup()
                                        .addComponent(dayBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yearBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(editLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wards, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(district, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(apartmentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))))
                    .addComponent(address))
                .addGap(17, 225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(202, 202, 202))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                        .addComponent(confirmBtn)
                        .addGap(273, 273, 273))))
        );
        editLayout.setVerticalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(20, 20, 20)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullName)
                    .addComponent(fullNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender)
                    .addComponent(female)
                    .addComponent(male)
                    .addComponent(otherGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(birth)
                    .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone)
                    .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address)
                    .addComponent(apartmentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(district, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addComponent(confirmBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        profile.add(edit, "card2");

        changePassword.setBackground(new java.awt.Color(255, 255, 255));
        changePassword.setPreferredSize(new java.awt.Dimension(621, 330));

        birth1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        birth1.setForeground(new java.awt.Color(0, 0, 0));
        birth1.setText("Nhập lại mật khẩu mới:");

        gender1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        gender1.setForeground(new java.awt.Color(0, 0, 0));
        gender1.setText("Nhập mật khẩu mới:");

        fullName1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        fullName1.setForeground(new java.awt.Color(0, 0, 0));
        fullName1.setText("Nhập mật khẩu cũ:");

        confirmChangePassword.setBackground(new java.awt.Color(0, 255, 0));
        confirmChangePassword.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        confirmChangePassword.setForeground(new java.awt.Color(210, 154, 95));
        confirmChangePassword.setText("Xác nhận");
        confirmChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmChangePasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmChangePasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmChangePasswordMouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Đổi mật khẩu");

        showpass.setBackground(new java.awt.Color(255, 255, 255));
        showpass.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        showpass.setForeground(new java.awt.Color(0, 0, 0));
        showpass.setText("Hiển thị mật khẩu");
        showpass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                showpassStateChanged(evt);
            }
        });
        showpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showpassMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showpassMouseExited(evt);
            }
        });
        showpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassActionPerformed(evt);
            }
        });

        newPasswordConfirm.setBackground(new java.awt.Color(204, 204, 204));
        newPasswordConfirm.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        newPasswordConfirm.setForeground(new java.awt.Color(0, 0, 0));
        newPasswordConfirm.setText("Nhập lại mật khẩu mới");
        newPasswordConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        newPasswordConfirm.setEchoChar('\u0000');
        newPasswordConfirm.setMargin(new java.awt.Insets(2, 7, 2, 6));
        newPasswordConfirm.setMinimumSize(new java.awt.Dimension(290, 40));
        newPasswordConfirm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPasswordConfirmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newPasswordConfirmFocusLost(evt);
            }
        });
        newPasswordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordConfirmActionPerformed(evt);
            }
        });
        newPasswordConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newPasswordConfirmKeyPressed(evt);
            }
        });

        newPassword.setBackground(new java.awt.Color(204, 204, 204));
        newPassword.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        newPassword.setForeground(new java.awt.Color(0, 0, 0));
        newPassword.setText("Nhập mật khẩu mới");
        newPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        newPassword.setEchoChar('\u0000');
        newPassword.setMargin(new java.awt.Insets(2, 7, 2, 6));
        newPassword.setMinimumSize(new java.awt.Dimension(290, 40));
        newPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newPasswordFocusLost(evt);
            }
        });
        newPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordActionPerformed(evt);
            }
        });
        newPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newPasswordKeyPressed(evt);
            }
        });

        oldPassword.setBackground(new java.awt.Color(204, 204, 204));
        oldPassword.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        oldPassword.setForeground(new java.awt.Color(0, 0, 0));
        oldPassword.setText("Nhập mật khẩu cũ");
        oldPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        oldPassword.setEchoChar('\u0000');
        oldPassword.setMargin(new java.awt.Insets(2, 7, 2, 6));
        oldPassword.setMinimumSize(new java.awt.Dimension(290, 40));
        oldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                oldPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                oldPasswordFocusLost(evt);
            }
        });
        oldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldPasswordActionPerformed(evt);
            }
        });
        oldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                oldPasswordKeyPressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        warningChangePassword.setBackground(new java.awt.Color(255, 0, 51));
        warningChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        warningChangePassword.setForeground(new java.awt.Color(255, 0, 0));
        warningChangePassword.setText("Không được để trống thông tin!!!");

        javax.swing.GroupLayout changePasswordLayout = new javax.swing.GroupLayout(changePassword);
        changePassword.setLayout(changePasswordLayout);
        changePasswordLayout.setHorizontalGroup(
            changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePasswordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(215, 215, 215)
                .addComponent(jLabel5)
                .addContainerGap())
            .addGroup(changePasswordLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePasswordLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmChangePassword)
                            .addGap(154, 154, 154))
                        .addGroup(changePasswordLayout.createSequentialGroup()
                            .addComponent(birth1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(showpass)
                                .addComponent(newPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(warningChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(changePasswordLayout.createSequentialGroup()
                        .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gender1)
                            .addComponent(fullName1))
                        .addGap(28, 28, 28)
                        .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(oldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        changePasswordLayout.setVerticalGroup(
            changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordLayout.createSequentialGroup()
                .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(changePasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(17, 17, 17)
                .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullName1)
                    .addComponent(oldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender1)
                    .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birth1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showpass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningChangePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmChangePassword)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        profile.add(changePassword, "card2");

        getContentPane().add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 621, 0));

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menu.setForeground(new java.awt.Color(255, 255, 255));
        menu.setPreferredSize(new java.awt.Dimension(300, 872));

        jPanel2.setBackground(new java.awt.Color(27, 43, 32));

        exitMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px_yl.png"))); // NOI18N
        exitMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMenuMouseClicked(evt);
            }
        });

        customerName.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        customerName.setForeground(new java.awt.Color(210, 154, 95));
        customerName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-icon.png"))); // NOI18N
        customerName.setText("USER NAME");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitMenu)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(customerName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitMenu)
                .addGap(18, 18, 18)
                .addComponent(customerName)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        infomation.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        infomation.setForeground(new java.awt.Color(0, 0, 0));
        infomation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infomation.setText("Thông tin cá nhân");
        infomation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infomation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infomationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infomationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infomationMouseExited(evt);
            }
        });

        waitConfirmsLabel.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        waitConfirmsLabel.setForeground(new java.awt.Color(0, 0, 0));
        waitConfirmsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waitConfirmsLabel.setText("Chờ xác nhận");
        waitConfirmsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        waitConfirmsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waitConfirmsLabelsLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                waitConfirmsLabelsLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                waitConfirmsLabelsLabelMouseExited(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(255, 204, 204));
        logoutBtn.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 51, 51));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Login-out-icon.png"))); // NOI18N
        logoutBtn.setText("ĐĂNG XUẤT");
        logoutBtn.setBorder(null);
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setEnabled(false);
        logoutBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        waitOrder.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        waitOrder.setForeground(new java.awt.Color(0, 0, 0));
        waitOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waitOrder.setText("Đã xác nhận");
        waitOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        waitOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waitOrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                waitOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                waitOrderMouseExited(evt);
            }
        });

        historyOrder.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        historyOrder.setForeground(new java.awt.Color(0, 0, 0));
        historyOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        historyOrder.setText("Lịch sử mua hàng");
        historyOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        historyOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyOrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                historyOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                historyOrderMouseExited(evt);
            }
        });

        cancelOrder.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        cancelOrder.setForeground(new java.awt.Color(0, 0, 0));
        cancelOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelOrder.setText("Đơn hủy");
        cancelOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelOrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelOrderMouseExited(evt);
            }
        });

        changePass.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        changePass.setForeground(new java.awt.Color(0, 0, 0));
        changePass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changePass.setText("Đổi mật khẩu");
        changePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changePassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changePassMouseExited(evt);
            }
        });

        introduce.setFont(new java.awt.Font("Serif", 0, 20)); // NOI18N
        introduce.setForeground(new java.awt.Color(0, 0, 0));
        introduce.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        introduce.setText("Giới thiệu");
        introduce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        introduce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                introduceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                introduceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                introduceMouseExited(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelOrder)
                    .addComponent(waitConfirmsLabel)
                    .addComponent(infomation)
                    .addComponent(waitOrder)
                    .addComponent(historyOrder)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePass)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(introduce)))
                .addGap(59, 59, 59))
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(infomation)
                .addGap(18, 18, 18)
                .addComponent(waitConfirmsLabel)
                .addGap(18, 18, 18)
                .addComponent(waitOrder)
                .addGap(18, 18, 18)
                .addComponent(cancelOrder)
                .addGap(18, 18, 18)
                .addComponent(historyOrder)
                .addGap(18, 18, 18)
                .addComponent(changePass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(introduce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 872));

        Layout.setBackground(new java.awt.Color(255, 255, 255));
        Layout.setMaximumSize(new java.awt.Dimension(32767, 872));

        jPanel1.setBackground(new java.awt.Color(27, 43, 32));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_150.png"))); // NOI18N

        menuIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        menuIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuIconMouseClicked(evt);
            }
        });

        cartIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart-icon.png"))); // NOI18N
        cartIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cartIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartIconMouseClicked(evt);
            }
        });

        quantityCart.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        quantityCart.setForeground(new java.awt.Color(255, 0, 51));
        quantityCart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        quantityCart.setText("0");
        quantityCart.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuIcon)
                .addGap(189, 189, 189)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(quantityCart, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cartIcon)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuIcon))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(cartIcon))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quantityCart)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trasuaTASU.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        trasuaTASU.setForeground(new java.awt.Color(30, 30, 30));
        trasuaTASU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/crown-gold-icon.png"))); // NOI18N
        trasuaTASU.setText("Trà Sữa TASU - Lê Văn Việt");

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-icon.png"))); // NOI18N
        searchIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });

        searchText.setBackground(new java.awt.Color(210, 210, 210));
        searchText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchText.setForeground(new java.awt.Color(150, 150, 150));
        searchText.setText("Tìm kiếm tại trà sữa TASU");
        searchText.setFocusTraversalPolicyProvider(true);
        searchText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextFocusLost(evt);
            }
        });
        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
        });

        productsLayout.setBackground(new java.awt.Color(204, 204, 204));
        productsLayout.setForeground(new java.awt.Color(0, 0, 0));
        productsLayout.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        productsLayout.setMaximumSize(new java.awt.Dimension(32767, 800));
        productsLayout.setMinimumSize(new java.awt.Dimension(100, 87));
        productsLayout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsLayoutMouseClicked(evt);
            }
        });

        productsAll.setBackground(new java.awt.Color(204, 204, 204));
        productsAll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        productsAll.setMaximumSize(new java.awt.Dimension(32767, 800));
        productsAll.setMinimumSize(new java.awt.Dimension(10, 10));
        productsLayout.addTab("Tất Cả", productsAll);

        javax.swing.GroupLayout LayoutLayout = new javax.swing.GroupLayout(Layout);
        Layout.setLayout(LayoutLayout);
        LayoutLayout.setHorizontalGroup(
            LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(productsLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(LayoutLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addComponent(trasuaTASU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayoutLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        LayoutLayout.setVerticalGroup(
            LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trasuaTASU, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productsLayout, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
        );

        getContentPane().add(Layout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 621, 867));

        waitConfirmsMenu.setBackground(new java.awt.Color(255, 255, 255));
        waitConfirmsMenu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        waitConfirmsMenu.setPreferredSize(new java.awt.Dimension(621, 660));

        jLabel16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_15px.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        orderStatus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderStatus.setForeground(new java.awt.Color(0, 0, 0));
        orderStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderStatus.setText("Chờ xác nhận");

        waitConfirms.setBackground(new java.awt.Color(221, 221, 221));
        waitConfirms.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        waitConfirms.setToolTipText("");
        waitConfirms.setPreferredSize(new java.awt.Dimension(619, 619));
        waitConfirms.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout waitConfirmsMenuLayout = new javax.swing.GroupLayout(waitConfirmsMenu);
        waitConfirmsMenu.setLayout(waitConfirmsMenuLayout);
        waitConfirmsMenuLayout.setHorizontalGroup(
            waitConfirmsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitConfirmsMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap())
            .addComponent(waitConfirms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        waitConfirmsMenuLayout.setVerticalGroup(
            waitConfirmsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitConfirmsMenuLayout.createSequentialGroup()
                .addGroup(waitConfirmsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(orderStatus)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitConfirms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(waitConfirmsMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1050, -1, 0));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFocusGained
        if (searchText.getText().equals("Tìm kiếm tại trà sữa TASU")) {
            searchText.setText("");
            Color customColor = new Color(100, 100, 100);
            searchText.setForeground(customColor);
        }
    }//GEN-LAST:event_searchTextFocusGained

    private void searchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFocusLost
        if (searchText.getText().equals("")) {
            searchText.setText("Tìm kiếm tại trà sữa TASU");
            Color customColor = new Color(150, 150, 150);
            searchText.setForeground(customColor);
        }
    }//GEN-LAST:event_searchTextFocusLost

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed

        if (evt.getKeyCode() == 10) {
            String key = searchText.getText();
            setProducts(key);
        }

    }//GEN-LAST:event_searchTextKeyPressed

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked

        String key;
        if (searchText.getText().equals("Tìm kiếm tại trà sữa TASU")) {
            key = "";
        } else {
            key = searchText.getText();
        }
        setProducts(key);
    }//GEN-LAST:event_searchIconMouseClicked

    private void productsLayoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsLayoutMouseClicked
        //setProducts("");
    }//GEN-LAST:event_productsLayoutMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        closeSelectProduct();
    }//GEN-LAST:event_jLabel2MouseClicked

    void closeSelectProduct() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 670; i >= 0; i -= 10) {
                    selectProduct.setSize(621, i);
                    selectProduct.setLocation(selectProduct.getX(), selectProduct.getY() + 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                selectProduct.dispose();
                invisibleFrame.dispose();
            }
        }).start();
    }

    private void productDescriptionMoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productDescriptionMoreMouseClicked

        notification.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(notification, productDescription.getText(), "Mô tả sản phẩm", JOptionPane.PLAIN_MESSAGE);
        Message message = new Message(notification, true);
        message.setTitle("Mô tả sản phẩm");
        message.setContent(productDescription.getText());
        message.setVisible(true);
    }//GEN-LAST:event_productDescriptionMoreMouseClicked

    private void menuIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuIconMouseClicked
        setInvisibleFrame1();
        invisibleFrame1.setEnabled(false);
        openMenu();
    }//GEN-LAST:event_menuIconMouseClicked

    private void exitMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuMouseClicked
        closeMenu();
    }//GEN-LAST:event_exitMenuMouseClicked

    private void plusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusMouseClicked

        int soLuong = Integer.parseInt(quantity.getText());

        total[0] /= soLuong;
        soLuong++;
        quantity.setText(String.valueOf(soLuong));
        if (soLuong != 1) {
            minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-1.png")));
            minus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        total[0] *= soLuong;
        setTotalPrice(String.valueOf(total[0]));
    }//GEN-LAST:event_plusMouseClicked

    private void minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusMouseClicked

        int soLuong = Integer.parseInt(quantity.getText());

        if (soLuong == 2) {
            minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-0.png")));
            minus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }

        if (soLuong == 1) {
            notification.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(notification, "Số lượng đã tối thiểu!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Số lượng đã tối thiểu!!!");
            warning.setVisible(true);
            return;
        }
        total[0] /= soLuong;
        soLuong--;
        quantity.setText(String.valueOf(soLuong));
        total[0] *= soLuong;
        setTotalPrice(String.valueOf(total[0]));


    }//GEN-LAST:event_minusMouseClicked

    public static void setTotalPriceCart(String price) {
        totalPriceLabel[0].setText("₫" + (new DecimalFormat("#,###").format(Integer.parseInt(price))));
        //totalPriceCart = totalPriceLabel[0];
    }

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        Map<String, Object> Glass = new HashMap<>();
        Glass.put("idGlass", idGlass[0]);
        Glass.put("idProduct", idProductSelected[0]);
        Glass.put("nameProduct", nameProductSelected);
        Glass.put("imageProduct", imageProductSelected);
        Glass.put("idSize", sizeProductSelected[0]);
        int price = total[0] / Integer.parseInt((String) quantity.getText());
        Glass.put("price", price);
        Glass.put("quantityProduct", 1);
        Glass.put("Topping", SelectedToppings);
//        Glass.put("quantityTopping", 1);
        for (int i = 0; i < Glasses.size(); i++) {
            Map<String, Object> glass = Glasses.get(i);
            int quantityProductDetail = Integer.parseInt(glass.get("quantityProduct").toString());
            int idGlass = Integer.parseInt(glass.get("idGlass").toString());
            Glasses.get(i).put("quantityProduct", 1);
            Glasses.get(i).put("idGlass", Glass.get("idGlass"));
            if (glass.equals(Glass)) {
                closeSelectProduct();
                Glasses.get(i).put("idGlass", idGlass);
                total[0] = listCartProductsPanel.get(i).getProductPrice();
                total[0] /= listCartProductsPanel.get(i).getQuantityInt();
                total[1] -= listCartProductsPanel.get(i).getProductPrice();
                listCartProductsPanel.get(i).setQuantity(String.valueOf(quantityProductDetail + Integer.parseInt((String) quantity.getText())));
                Glasses.get(i).put("quantityProduct", quantityProductDetail + Integer.parseInt((String) quantity.getText()));
                total[0] *= listCartProductsPanel.get(i).getQuantityInt();
                listCartProductsPanel.get(i).setProductPrice(String.valueOf(total[0]));
                total[1] += total[0];
                total[0] = 0;

                return;
            }
            Glasses.get(i).put("idGlass", idGlass);
            Glasses.get(i).put("quantityProduct", quantityProductDetail);
        }
        Glass.put("quantityProduct", Integer.parseInt((String) quantity.getText()));
        Glass.put("idGlass", ++idGlass[0]);
        Glasses.add(Glass);

        CartProduct cartProduct = new CartProduct();
        //cartProduct.setName(String.valueOf(numberItemCart[0]));
        cartProduct.setIndex(Glasses.size() - 1);
        cartProduct.setProductName(productName.getText(), Glasses.size() - 1);
        cartProduct.setProductPrice(String.valueOf(total[0]));
        cartProduct.setProductDefautPrice(productPrice.getText());
        total[1] += total[0];
        List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) Glass.get("Topping");
        int count = 0;
        for (int j = 0; j < Topping.size(); j++) {
            Map<String, Integer> topping = Topping.get(j);
            int idTopping = topping.get("idTopping") - 1;
            String quantity = String.valueOf(topping.get("quantity"));
            count++;
            sizeAndTopping += (String) Toppings.get(idTopping).get("nameTopping");
            sizeAndTopping += (" x" + quantity);
            if (count != Topping.size()) {
                sizeAndTopping += ", ";
            }
        }
        sizeAndTopping += ".";
        cartProduct.setSizeAndTopping(sizeAndTopping);
        total[0] = 0;
        cartProduct.setProductImg(productImg.getName());
        cartProduct.setQuantity(String.valueOf(quantity.getText()));
        cartProduct.setPriceSize(productPrice.getName());

        cartProduct.getProductNameLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = cartProduct.getIndex();
                invisibleFrame.setAlwaysOnTop(true);
                cartProduct.setEditView(index);
            }

        });

        cartProduct.getPlus().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int quantity = (int) Glasses.get(cartProduct.getIndex()).get("quantityProduct");
                Glasses.get(cartProduct.getIndex()).put("quantityProduct", quantity + 1);
                setTotalPriceCart(String.valueOf(total[1]));
            }

        });

        cartProduct.getMinus().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int quantity = cartProduct.getQuantityInt();
                Glasses.get(cartProduct.getIndex()).put("quantityProduct", quantity);
                setTotalPriceCart(String.valueOf(total[1]));
                if (quantity == 0) {
                    if (numberItemCart[0] == 1) {
                        numberItemCart[0]--;
                        setQuantityCart(numberItemCart[0]);
                        listCartProductsPanel.clear();
                        Glasses.clear();
                        return;
                    }
                    listCartProductsPanel.remove(cartProduct.getIndex());
                    for (int i = cartProduct.getIndex(); i < listCartProductsPanel.size(); i++) {
                        CartProduct cp = listCartProductsPanel.get(i);
                        int id = cp.getIndex() - 1;
                        cp.setIndex(id);
                        listCartProductsPanel.set(i, cp);
                    }
                    numberItemCart[0]--;
                    setQuantityCart(numberItemCart[0]);
                    for (int i = cartProduct.getIndex(); i < Glasses.size() - 1; i++) {
                        Map<String, Object> glass = Glasses.get(i + 1);
                        int id = (int) glass.get("idGlass") - 1;
                        idGlass[0] = id;
                        glass.put("idGlass", id);
                        Glasses.set(i, glass);
                    }
                    Glasses.remove(Glasses.size() - 1);
                }
            }

        });

        cartProduct.getDeleteProduct().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (numberItemCart[0] == 1) {
                    numberItemCart[0]--;
                    setQuantityCart(numberItemCart[0]);
                    listCartProductsPanel.clear();
                    Glasses.clear();
                    return;
                }
                setTotalPriceCart(String.valueOf(total[1]));
                listCartProductsPanel.remove(cartProduct.getIndex());
                for (int i = cartProduct.getIndex(); i < listCartProductsPanel.size(); i++) {
                    CartProduct cp = listCartProductsPanel.get(i);
                    int id = cp.getIndex() - 1;
                    cp.setIndex(id);
                    listCartProductsPanel.set(i, cp);
                }
                numberItemCart[0]--;
                setQuantityCart(numberItemCart[0]);
                for (int i = cartProduct.getIndex(); i < Glasses.size() - 1; i++) {
                    Map<String, Object> glass = Glasses.get(i + 1);
                    int id = (int) glass.get("idGlass") - 1;
                    idGlass[0] = id;
                    glass.put("idGlass", id);
                    Glasses.set(i, glass);
                }
                Glasses.remove(Glasses.size() - 1);
            }

        });
        listCartProductsPanel.add(cartProduct);
        cartProductsPanel.add(listCartProductsPanel.get(numberItemCart[0]));
        cartProductsPanel.add(Box.createVerticalStrut(2));
        numberItemCart[0]++;

        setQuantityCart(numberItemCart[0]);
        closeSelectProduct();
    }//GEN-LAST:event_addActionPerformed

    private void infomationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infomationMouseClicked
        dialogMenu.setEnabled(false);
        logoutBtn.setEnabled(false);
        changePanelWatch();
        openProfileMenu();
    }//GEN-LAST:event_infomationMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        closeProfileMenu();
        invisibleFrame1.setAlwaysOnTop(false);
        dialogMenu.setEnabled(true);
        logoutBtn.setEnabled(true);
        cart.setEnabled(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked

        changePanelEdit();
    }//GEN-LAST:event_editBtnMouseClicked

    private void confirmBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmBtnMouseClicked
        if (!phoneText.getText().matches("\\d{10}")) {
            notification.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(notification, "Số điện thoại không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Số điện thoại không hợp lệ!!!");
            warning.setInvisibleOnTop();
            warning.setVisible(true);
            return;
        }
        if (!fullNameText.getText().equals("") && fullNameText.getText().matches(".*\\d.*")) {
            notification.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(notification, "Họ tên không hợp lệ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Họ tên không hợp lệ!!!");
            warning.setInvisibleOnTop();
            warning.setVisible(true);
            return;
        }

        String[] addressStr = new String[4];
        String address = ", , , ";
        addressStr[0] = apartmentNumber.getText();
        addressStr[1] = (String) wards.getSelectedItem();
        addressStr[2] = (String) district.getSelectedItem();
        addressStr[3] = "Thành Phố Hồ Chí Minh";
        String birth = "";
        int gender = 0;

        if (apartmentNumber.getText().equals("")) {
            addressStr[0] = "Số nhà";
        }
        address = addressStr[0] + ", " + addressStr[1] + ", " + addressStr[2] + ", " + addressStr[3];

        if (dayBirth.getSelectedIndex() != 0 && monthBirth.getSelectedIndex() != 0 && yearBirth.getSelectedIndex() != 0) {
            birth = dayBirth.getSelectedItem() + "/" + monthBirth.getSelectedItem() + "/" + yearBirth.getSelectedItem();
        }
        if (male.isSelected()) {
            gender = 1;
        } else if (female.isSelected()) {
            gender = 2;
        } else if (otherGender.isSelected()) {
            gender = 3;
        }

        editCustomer((String) Customer.get("userName"), fullNameText.getText(), phoneText.getText(), address, gender, birth);
        getAccLogin((String) Customer.get("userName"), false);
        changePanelWatch();
        setNameMenu();
    }//GEN-LAST:event_confirmBtnMouseClicked

    private void cartIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseClicked

        //openCart();
        emptyViewport = new JViewport();
        cartProducts.setViewport(emptyViewport);
        cartProducts.setViewportView(cartProductsPanel);
        cartProductsPanel.add(emptyPanel);
        setInvisibleFrame();
        cart.setAlwaysOnTop(true);
        cart.setUndecorated(true);
        cart.setResizable(false);
        cart.setLocationRelativeTo(null);
        cart.setBounds(458, 885, 621, 0);
        //cart[0] = cart;
        cart.setVisible(true);

        //cart.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 670; i += 10) {
                    cart.setSize(621, i);
                    cart.setLocation(cart.getX(), cart.getY() - 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        totalPriceLabel[0] = totalPriceCart;
        totalPriceLabel[0].setText("₫" + new DecimalFormat("#,###").format(total[1]));
    }//GEN-LAST:event_cartIconMouseClicked

    void closeCart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 670; i >= 0; i -= 10) {
                    cart.setSize(621, i);
                    cart.setLocation(cart.getX(), cart.getY() + 10);
                    try {
                        Thread.sleep(1);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                cart.dispose();
            }
        }).start();
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        closeCart();
        invisibleFrame.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    void openPaymentPanelLayout() {
        emptyViewport = new JViewport();
        paymentProducts.setViewport(emptyViewport);
        if (!Customer.get("nameCustomer").equals("")) {
            nameAndPhonePayment.setText((String) Customer.get("nameCustomer") + " | " + (String) Customer.get("numberPhoneCustomer"));
        } else {
            nameAndPhonePayment.setText((String) Customer.get("userName") + " | " + (String) Customer.get("numberPhoneCustomer"));
        }
        int quantity = 0;
        addressPayment.setText((String) Customer.get("addressCustomer"));
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < Glasses.size(); i++) {
            Map<String, Object> Glass = Glasses.get(i);
            Order order = new Order();
            order.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
            order.setQuantity(String.valueOf(Glass.get("quantityProduct")));
            int price = (int) Glass.get("price") * (int) Glass.get("quantityProduct");
            quantity += (int) Glass.get("quantityProduct");
            order.setTextProductPrice(String.valueOf(price));
            if ((int) Glass.get("idSize") == 1) {
                order.setSize("Vừa");
            } else {
                order.setSize("Lớn");
            }
            String toppingText = "";
            getToppings();
            List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) Glass.get("Topping");
            int count = 0;
            for (int j = 0; j < Topping.size(); j++) {
                Map<String, Integer> topping = Topping.get(j);
                int idTopping = topping.get("idTopping") - 1;
                String quantityText = String.valueOf(topping.get("quantity"));
                count++;
                toppingText += (String) Toppings.get(idTopping).get("nameTopping");
                toppingText += (" x" + quantityText);

                if (count != Topping.size()) {
                    toppingText += ", ";
                }
            }
            order.setTextProductName((String) Glass.get("nameProduct"));
            order.setImage(String.valueOf((String) Glass.get("imageProduct")));
            order.setTopping(toppingText);
            orderPanel.add(order);
            orderPanel.add(Box.createVerticalStrut(2));
        }
        getVoucher();
        finalIdVoucher = 0;
        voucherComboBox.removeAllItems();
        voucherComboBox.addItem("--- Chọn mã giảm ---");
        for (int i = 0; i < vouchers.size(); i++) {
            Map<String, Object> voucher = vouchers.get(i);
            voucherComboBox.addItem((String) voucher.get("codeVoucher"));
            if (total[1] >= (int) voucher.get("toCost")) {
                voucherComboBox.setSelectedIndex(i + 1);
                finalIdVoucher = (int) voucher.get("idVoucher");
            }
        }

        totalText.setText("Tổng cộng (" + quantity + " món)");
        totalCost.setText(String.valueOf(total[1]) + "₫");
        deliveryChargesCost.setText("16000₫");
        finalCost = Integer.parseInt(totalCost.getText().substring(0, totalCost.getText().length() - 1))
                + Integer.parseInt(deliveryChargesCost.getText().substring(0, deliveryChargesCost.getText().length() - 1))
                - voucherCost;
        totalPricePayment.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
        paymentProducts.setViewportView(orderPanel);
        //cartProductsPanel.remove(emptyPanel);
        payment.setAlwaysOnTop(true);
        payment.setUndecorated(true);
        payment.setResizable(false);
        payment.setLocationRelativeTo(null);
        payment.setBounds(458, 205, 621, 670);
        payment.setVisible(true);
    }

    private void orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderActionPerformed

        String address = String.valueOf(Customer.get("addressCustomer"));
        if (Glasses.size() == 0) {
            notification.setAlwaysOnTop(true);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Giỏ hàng trống!!!");
            warning.setVisible(true);
            return;
        } else if (address.indexOf("Quận/Huyện") != -1 || address.indexOf("Phường/Xã") != -1 || address.indexOf("Số nhà") != -1) {
            cart.setEnabled(false);
            changePanelEdit();
            openProfileMenu();
            notification.setAlwaysOnTop(true);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Thông tin địa chỉ còn thiếu!!!");
            warning.setVisible(true);
            return;
        } else {
            openPaymentPanelLayout();
        }

    }//GEN-LAST:event_orderActionPerformed

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jLabel12MouseExited

    private void infomationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infomationMouseEntered
        infomation.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_infomationMouseEntered

    private void infomationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infomationMouseExited
        infomation.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_infomationMouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        if (Glasses.size() == 0) {
            notification.setAlwaysOnTop(true);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Giỏ hàng trống!!!");
            warning.setVisible(true);
            return;
        }
        emptyViewport = new JViewport();
        cartProducts.setViewport(emptyViewport);
        cartProductsPanel = new JPanel();
        cartProductsPanel.setLayout(new BoxLayout(cartProductsPanel, BoxLayout.Y_AXIS));
        cartProductsPanel.setBackground(new java.awt.Color(255, 255, 255));
        numberItemCart[0] = 0;
        setQuantityCart(numberItemCart[0]);
        total[1] = 0;
        setTotalPriceCart(String.valueOf(total[1]));
        listCartProductsPanel.clear();
        Glasses.clear();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed

        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomerLogin login = new CustomerLogin();
                login.setVisible(true);
                login.setAlwaysOnTop(true);
                setAlwaysOnTop(false);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CustomerDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialogMenu.dispose();
                setEnabled(true);
                invisibleFrame1.dispose();
                dispose();
            }
        }).start();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void historyOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyOrderMouseExited
        historyOrder.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_historyOrderMouseExited

    private void historyOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyOrderMouseEntered
        historyOrder.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_historyOrderMouseEntered

    private void waitOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitOrderMouseExited
        waitOrder.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_waitOrderMouseExited

    private void waitOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitOrderMouseEntered
        waitOrder.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_waitOrderMouseEntered

    private void waitConfirmsLabelsLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitConfirmsLabelsLabelMouseExited
        waitConfirmsLabel.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_waitConfirmsLabelsLabelMouseExited

    private void waitConfirmsLabelsLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitConfirmsLabelsLabelMouseEntered
        waitConfirmsLabel.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_waitConfirmsLabelsLabelMouseEntered

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        closeOrders();
        dialogMenu.setEnabled(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    void setOrderView(String status) {
        if (!jLabel16.isVisible()) {
            jLabel16.setVisible(true);
        }
        if (jLabel17.isVisible()) {
            jLabel17.setVisible(false);
        }
        orderStatus.setText(status);
        orderStatus.setName(status);
        getOrders((int) Customer.get("idCustomer"));
        ordersPanel.revalidate();
        ordersPanel.repaint();
        int orderPanelHeight = 0;
        emptyViewport = new JViewport();
        waitConfirms.setViewport(emptyViewport);
        //waitConfirms.setSize(619, 640);
        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        //orderPanel.setBackground(new java.awt.Color(30,30,30));
        for (int i = 0; i < CustomerBills.size(); i++) {
            Map<String, Object> bill = CustomerBills.get(i);
            CustomerBillDetails = (List<Map<String, Object>>) bill.get("billDetails");
            Map<String, Object> billDetail0 = CustomerBillDetails.get(0);
            Map<String, Object> item = (Map<String, Object>) billDetail0.get("Item");
            Map<String, Object> productSize = (Map<String, Object>) item.get("ProductSize");
            if (((String) bill.get("statusPurchase")).equals(status)) {
                JPanel orderPanel = new JPanel();
                orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
                Order order = new Order();
                order.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                order.setQuantity(String.valueOf(billDetail0.get("quantityProduct")));
                order.setTextProductPrice(String.valueOf(item.get("price")));
                if ((int) productSize.get("idSize") == 1) {
                    order.setSize("Vừa");
                } else {
                    order.setSize("Lớn");
                }
                String toppingText = "";
                getToppings();
                List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) item.get("Topping");
                int count = 0;
                if (Topping != null) {
                    for (int j = 0; j < Topping.size(); j++) {
                        Map<String, Integer> topping = Topping.get(j);
                        int idTopping = topping.get("idTopping") - 1;
                        String quantityText = String.valueOf(topping.get("quantityTopping"));
                        count++;
                        toppingText += (String) Toppings.get(idTopping).get("nameTopping");
                        toppingText += (" x" + quantityText);

                        if (count != Topping.size()) {
                            toppingText += ", ";
                        }
                    }
                }
                order.setTextProductName(String.valueOf(productSize.get("nameProduct")));
                order.setImage(String.valueOf(productSize.get("imageProduct")));
                order.setTopping(toppingText);
                orderPanel.add(order);
                orderPanel.add(Box.createVerticalStrut(2));

                if (status == "Đã hủy") {
                    JPanel cancelReasonPanel = new JPanel();
                    JLabel cancelReasonLabel = new JLabel();
                    cancelReasonLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
                    cancelReasonLabel.setForeground(new java.awt.Color(146, 146, 148));
                    cancelReasonLabel.setText("Lý do hủy đơn: " + (String) bill.get("cancelReason"));
                    cancelReasonPanel.add(cancelReasonLabel);
                    cancelReasonPanel.setBackground(new java.awt.Color(255, 255, 255));
                    orderPanelHeight += 40;
                    orderPanel.add(cancelReasonPanel);
                    orderPanel.add(Box.createVerticalStrut(2));
                }

                JPanel morePanel = new JPanel();
                JLabel moreLabel = new JLabel();
                moreLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
                moreLabel.setForeground(new java.awt.Color(146, 146, 148));
                moreLabel.setText("Xem thêm sản phẩm");
                moreLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                morePanel.add(moreLabel);
                morePanel.setBackground(new java.awt.Color(255, 255, 255));
                moreLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        moreLabel.setForeground(new java.awt.Color(27, 43, 32));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        moreLabel.setForeground(new java.awt.Color(146, 146, 148)); // Restore original border on mouse exit
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String idBillString = (String) bill.get("idBill");
                        int idBill = Integer.parseInt(idBillString);
                        viewOrderDetail(idBill);
                    }
                });

                orderPanelHeight += 40;
                orderPanel.add(morePanel);
                orderPanel.add(Box.createVerticalStrut(2));
                //}
                JPanel totalPanel = new JPanel();
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                JLabel totalLabel = new JLabel();
                label1.setFont(new java.awt.Font("Segoe UI", 0, 14));
                label1.setForeground(new java.awt.Color(146, 146, 148));
                label1.setText(String.valueOf(bill.get("totalAmount")) + " Sản phẩm");
                label2.setFont(new java.awt.Font("Segoe UI", 1, 16));
                label2.setForeground(new java.awt.Color(0, 0, 0));
                label2.setText("Thành tiền: ");
                totalLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
                totalLabel.setForeground(new java.awt.Color(210, 154, 95));
                totalLabel.setText("₫" + String.valueOf(bill.get("totalPrice")));
                javax.swing.GroupLayout totalPanelLayout = new javax.swing.GroupLayout(totalPanel);
                totalPanel.setLayout(totalPanelLayout);
                totalPanelLayout.setHorizontalGroup(
                        totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(totalPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                                        .addComponent(label2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(1, 1, 1))
                );
                totalPanelLayout.setVerticalGroup(
                        totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(totalPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(label1)
                                                .addComponent(label2)
                                                .addComponent(totalLabel))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                totalPanel.setBackground(new java.awt.Color(255, 255, 255));
                orderPanel.add(totalPanel);
                orderPanel.add(Box.createVerticalStrut(2));

                JPanel cancelPanel = new JPanel();
                JLabel label3 = new JLabel();
                JLabel label4 = new JLabel();

                cancelPanel.setBackground(new java.awt.Color(255, 255, 255));
                cancelPanel.setPreferredSize(new java.awt.Dimension(597, 34));
                cancelPanel.setVerifyInputWhenFocusTarget(false);

                label4.setBackground(new java.awt.Color(255, 0, 51));
                label4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                label4.setForeground(new java.awt.Color(210, 154, 95));
                label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                if (status == "Chờ xác nhận") {
                    label4.setText("Hủy đơn");
                    label4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(255, 0, 51));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(210, 154, 95)); // Restore original border on mouse exit
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            notification.setAlwaysOnTop(true);
                            Warning warning = new Warning(notification, true, true);
                            warning.setTitle("Thông báo");
                            warning.setWarning("Bạn chắc chắn muốn tiếp tục?");
                            warning.setVisible(true);
                            if (warning.getConfirm()) {
                                cancelReason.setUndecorated(true);
                                cancelReason.setResizable(false);
                                cancelReason.setSize(621, 1344);
                                cancelReason.setLocationRelativeTo(null);
                                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"---- Chọn lý do hủy đơn ----", "Thời gian chờ quá lâu", "Muốn đổi hình thức thanh toán", "Muốn thay đổi địa chỉ nhận hàng", "Đổi ý không muốn mua nữa"}));
                                cancelReason.setVisible(true);
                                cancelReason.setBackground(new Color(0, 0, 0, 60));
                                cancelReason.setAlwaysOnTop(true);
                                jComboBox1.setName(label4.getName());
                            }
                        }
                    });
                } else if (status == "Đã hủy") {
                    label4.setText("Mua lại");
                    label4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(255, 0, 51));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(210, 154, 95)); // Restore original border on mouse exit
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            notification.setAlwaysOnTop(true);
                            Warning warning = new Warning(notification, true, true);
                            warning.setTitle("Thông báo");
                            warning.setWarning("Bạn chắc chắn muốn tiếp tục?");
                            warning.setVisible(true);
                            if (warning.getConfirm()) {
                                totalText1.setText("Tổng cộng (" + String.valueOf(bill.get("totalAmount")) + " món)");
                                total[1] = (int) bill.get("totalPrice");
                                int x = 100 - (int) bill.get("discount");
                                total[0] = (int) ((total[1] - 16000) * 100 / x);
                                voucherCost = (total[0] * (int) bill.get("discount") / 100);
                                voucherCost2.setText("-" + String.valueOf(voucherCost) + "₫");
                                totalCost1.setText(String.valueOf(total[0]) + "₫");
                                repurchaseCost.setText((new DecimalFormat("#,###").format(total[1])) + "₫");
                                finalIdVoucher = 0;
                                voucher1.removeAllItems();
                                voucher1.addItem("--- Chọn mã giảm ---");
                                getVoucher();
                                for (int i = 0; i < vouchers.size(); i++) {
                                    Map<String, Object> voucher = vouchers.get(i);
                                    voucher1.addItem((String) voucher.get("codeVoucher"));
                                    if (total[1] >= (int) voucher.get("toCost")) {
                                        voucher1.setSelectedIndex(i + 1);
                                        finalIdVoucher = (int) voucher.get("idVoucher");
                                    }
                                }
                                changePaymentMethod.setUndecorated(true);
                                changePaymentMethod.setResizable(false);
                                changePaymentMethod.setSize(621, 1344);
                                changePaymentMethod.setLocationRelativeTo(null);
                                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--- Chọn phương thước thanh toán ---", "Thanh toán khi nhận hàng", "Chuyển khoản ngân hàng"}));
                                changePaymentMethod.setVisible(true);
                                changePaymentMethod.setBackground(new Color(0, 0, 0, 60));
                                changePaymentMethod.setAlwaysOnTop(true);
                                jComboBox2.setName(label4.getName());
                                jLabel57.setName(String.valueOf(bill.get("totalPrice")));
                            }
                        }
                    });
                } else if (status == "Đã giao") {
                    label4.setText("Mua lại");
                    label4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(255, 0, 51));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            label4.setForeground(new java.awt.Color(210, 154, 95)); // Restore original border on mouse exit
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            notification.setAlwaysOnTop(true);
                            Warning warning = new Warning(notification, true, true);
                            warning.setTitle("Thông báo");
                            warning.setWarning("Bạn chắc chắn muốn tiếp tục?");
                            warning.setVisible(true);
                            if (warning.getConfirm()) {
                                totalText1.setText("Tổng cộng (" + String.valueOf(bill.get("totalAmount")) + " món)");
                                total[1] = (int) bill.get("totalPrice");
                                int x = 100 - (int) bill.get("discount");
                                total[0] = (int) ((total[1] - 16000) * 100 / x);
                                voucherCost = (total[0] * (int) bill.get("discount") / 100);
                                voucherCost2.setText("-" + String.valueOf(voucherCost) + "₫");
                                totalCost1.setText(String.valueOf(total[0]) + "₫");
                                repurchaseCost.setText((new DecimalFormat("#,###").format(total[1])) + "₫");
                                finalIdVoucher = 0;
                                voucher1.removeAllItems();getVoucher();
                                voucher1.addItem("--- Chọn mã giảm ---");
                                for (int i = 0; i < vouchers.size(); i++) {
                                    Map<String, Object> voucher = vouchers.get(i);
                                    voucher1.addItem((String) voucher.get("codeVoucher"));
                                    if (total[1] >= (int) voucher.get("toCost")) {
                                        voucher1.setSelectedIndex(i + 1);
                                        finalIdVoucher = (int) voucher.get("idVoucher");
                                    }
                                }
                                changePaymentMethod.setUndecorated(true);
                                changePaymentMethod.setResizable(false);
                                changePaymentMethod.setSize(621, 1344);
                                changePaymentMethod.setLocationRelativeTo(null);
                                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--- Chọn phương thước thanh toán ---", "Thanh toán khi nhận hàng", "Chuyển khoản ngân hàng"}));
                                changePaymentMethod.setVisible(true);
                                changePaymentMethod.setBackground(new Color(0, 0, 0, 60));
                                changePaymentMethod.setAlwaysOnTop(true);
                                jComboBox2.setName(label4.getName());
                                jLabel57.setName(String.valueOf(bill.get("totalPrice")));
                            }
                        }
                    });
                }
                label4.setName((String) bill.get("idBill"));
                label4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                label4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                label3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                label3.setForeground(new java.awt.Color(146, 146, 148));
                label3.setText((String) bill.get("dateOrder"));

                javax.swing.GroupLayout cancelPanelLayout = new javax.swing.GroupLayout(cancelPanel);
                cancelPanel.setLayout(cancelPanelLayout);
                cancelPanelLayout.setHorizontalGroup(
                        cancelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                                        .addComponent(label4)
                                        .addGap(34, 34, 34))
                );
                cancelPanelLayout.setVerticalGroup(
                        cancelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cancelPanelLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(cancelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(label4)
                                                .addComponent(label3))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                cancelPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                orderPanel.add(cancelPanel);
                orderPanel.add(Box.createVerticalStrut(2));
                orderPanelHeight += 211;
                orderPanel.add(Box.createVerticalStrut(4));
                ordersPanel.add(orderPanel);
            }
        }
        if (orderPanelHeight < 637) {
            ordersPanel.add(emptyPanel);
        }

        ordersPanel.setPreferredSize(new Dimension(619, orderPanelHeight));
        waitConfirms.setPreferredSize(new Dimension(621, 637));
        waitConfirms.setViewportView(ordersPanel);
    }

    void viewOrderDetail(int idBill) {
        if (jLabel16.isVisible()) {
            jLabel16.setVisible(false);
        }
        if (!jLabel17.isVisible()) {
            jLabel17.setVisible(true);
        }
        orderStatus.setText("Chi tiết đơn hàng");
        ordersPanel.revalidate();
        ordersPanel.repaint();
        int orderPanelHeight = 0;
        emptyViewport = new JViewport();
        waitConfirms.setViewport(emptyViewport);
        //waitConfirms.setSize(619, 640);
        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        //orderPanel.setBackground(new java.awt.Color(30,30,30));
        JPanel viewOrderDetailPanel = new JPanel();
        JPanel notePanel = new JPanel();
        JLabel noteLabel = new JLabel();
        for (int i = 0; i < CustomerBills.size(); i++) {
            Map<String, Object> bill = CustomerBills.get(i);
            String idBillString = (String) bill.get("idBill");
            int idBillInteger = Integer.parseInt(idBillString);

            if (idBillInteger == idBill) {
                JPanel deliveryAddressPanel = new JPanel();
                JLabel deliveryAddressLabel = new JLabel();
                deliveryAddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
                deliveryAddressLabel.setForeground(new java.awt.Color(146, 146, 148));
                deliveryAddressLabel.setText("Địa chỉ giao hàng: " + (String) bill.get("deliveryAddress"));
                deliveryAddressPanel.add(deliveryAddressLabel);
                deliveryAddressPanel.setBackground(new java.awt.Color(255, 255, 255));
                deliveryAddressPanel.add(Box.createVerticalStrut(2));
                deliveryAddressPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                ordersPanel.add(deliveryAddressPanel);
                if (String.valueOf(bill.get("statusPurchase")).equals("Đã hủy")) {
                    JPanel cancelReasonPanel = new JPanel();
                    JLabel cancelReasonLabel = new JLabel();
                    cancelReasonLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
                    cancelReasonLabel.setForeground(new java.awt.Color(146, 146, 148));
                    cancelReasonLabel.setText("Lý do hủy đơn: " + (String) bill.get("cancelReason"));
                    cancelReasonPanel.add(cancelReasonLabel);
                    cancelReasonPanel.setBackground(new java.awt.Color(255, 255, 255));
                    cancelReasonPanel.add(Box.createVerticalStrut(2));
                    cancelReasonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                    ordersPanel.add(cancelReasonPanel);
                }
                CustomerBillDetails = (List<Map<String, Object>>) bill.get("billDetails");
                for (int j = 0; j < CustomerBillDetails.size(); j++) {
                    Map<String, Object> billDetail = CustomerBillDetails.get(j);
                    Map<String, Object> item = (Map<String, Object>) billDetail.get("Item");
                    Map<String, Object> productSize = (Map<String, Object>) item.get("ProductSize");
                    JPanel orderPanel = new JPanel();
                    orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
                    Order order = new Order();
                    order.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                    order.setQuantity(String.valueOf(billDetail.get("quantityProduct")));
                    order.setTextProductPrice(String.valueOf(item.get("price")));
                    if ((int) productSize.get("idSize") == 1) {
                        order.setSize("Vừa");
                    } else {
                        order.setSize("Lớn");
                    }
                    String toppingText = "";
                    getToppings();
                    List<Map<String, Integer>> Topping = (List<Map<String, Integer>>) item.get("Topping");
                    int count = 0;
                    if (Topping != null) {
                        for (int k = 0; k < Topping.size(); k++) {
                            Map<String, Integer> topping = Topping.get(k);
                            int idTopping = topping.get("idTopping") - 1;
                            String quantityText = String.valueOf(topping.get("quantityTopping"));
                            count++;
                            toppingText += (String) Toppings.get(idTopping).get("nameTopping");
                            toppingText += (" x" + quantityText);

                            if (count != Topping.size()) {
                                toppingText += ", ";
                            }
                        }
                    }
                    order.setTextProductName(String.valueOf(productSize.get("nameProduct")));
                    order.setImage(String.valueOf(productSize.get("imageProduct")));
                    order.setTopping(toppingText);
                    orderPanel.add(order);
                    orderPanel.add(Box.createVerticalStrut(2));
                    ordersPanel.add(orderPanel);

                    JLabel label = new JLabel();
                    JLabel label0 = new JLabel();
                    JLabel label1 = new JLabel();
                    JLabel label2 = new JLabel();
                    JLabel label3 = new JLabel();
                    JLabel label4 = new JLabel();
                    JLabel label5 = new JLabel();
                    JLabel label6 = new JLabel();
                    JLabel label7 = new JLabel();
                    JLabel label8 = new JLabel();
                    JLabel label9 = new JLabel();
                    JLabel label10 = new JLabel();
                    JLabel label11 = new JLabel();
                    JLabel label12 = new JLabel();
                    JLabel label13 = new JLabel();
                    JLabel label14 = new JLabel();
                    JLabel label15 = new JLabel();
                    JSeparator separator = new JSeparator();
                    JSeparator separator0 = new JSeparator();
                    JSeparator separator1 = new JSeparator();

                    viewOrderDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

                    label.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label.setForeground(new java.awt.Color(146, 146, 148));
                    label.setText("Tổng tiền hàng");

                    label0.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label0.setForeground(new java.awt.Color(0, 0, 0));
                    label0.setText("Thành tiền");

                    label1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label1.setForeground(new java.awt.Color(210, 154, 95));
                    label1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label1.setText("₫" + String.valueOf((int) bill.get("totalPrice")));

                    label2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label2.setForeground(new java.awt.Color(146, 146, 148));
                    label2.setText("Phí vận chuyển");

                    label3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label3.setForeground(new java.awt.Color(146, 146, 148));
                    label3.setText("Giảm giá");

                    label4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label4.setForeground(new java.awt.Color(146, 146, 148));
                    label4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    int cost = (int) ((((int) bill.get("totalPrice") - 16000) * 100) / (100 - (int) bill.get("discount")));
                    label4.setText("₫" + String.valueOf(cost));

                    label5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label5.setForeground(new java.awt.Color(146, 146, 148));
                    label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label5.setText("₫16000");

                    label6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label6.setForeground(new java.awt.Color(146, 146, 148));
                    label6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    int voucherCost1 = ((cost * (int) bill.get("discount")) / 100);
                    label6.setText("- ₫" + String.valueOf(voucherCost1));

                    label7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label7.setForeground(new java.awt.Color(146, 146, 148));
                    label7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label7.setText((String) bill.get("paymentMethod"));

                    label8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label8.setForeground(new java.awt.Color(0, 0, 0));
                    label8.setText("Phương thức thanh toán");

                    label9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label9.setForeground(new java.awt.Color(0, 0, 0));
                    label9.setText("Mã hóa đơn");

                    label10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label10.setForeground(new java.awt.Color(0, 0, 0));
                    label10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label10.setText(String.valueOf(bill.get("idBill")));

                    label11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label11.setForeground(new java.awt.Color(146, 146, 148));
                    label11.setText("Thời gian đặt hàng");

                    label12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    label12.setForeground(new java.awt.Color(146, 146, 148));
                    label12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label12.setText(String.valueOf(bill.get("dateOrder")));

                    label13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
                    label13.setForeground(new java.awt.Color(210, 154, 95));
                    label13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label13.setName((String) bill.get("idBill"));
                    label13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    if (String.valueOf(bill.get("statusPurchase")).equals("Đã hủy") || String.valueOf(bill.get("statusPurchase")).equals("Đã giao")) {

                        label13.setText("Mua lại");
                        label13.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                label13.setForeground(new java.awt.Color(255, 0, 51));
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                label13.setForeground(new java.awt.Color(210, 154, 95)); // Restore original border on mouse exit
                            }

                            @Override
                            public void mouseClicked(MouseEvent e) {
                                notification.setAlwaysOnTop(true);
                                Warning warning = new Warning(notification, true, true);
                                warning.setTitle("Thông báo");
                                warning.setWarning("Bạn chắc chắn muốn tiếp tục?");
                                warning.setVisible(true);
                                if (warning.getConfirm()) {
                                    totalText1.setText("Tổng cộng (" + String.valueOf(bill.get("totalAmount")) + " món)");
                                    total[1] = (int) bill.get("totalPrice");
                                    int x = 100 - (int) bill.get("discount");
                                    total[0] = (int) ((total[1] - 16000) * 100 / x);
                                    voucherCost = (total[0] * (int) bill.get("discount") / 100);
                                    voucherCost2.setText("-" + String.valueOf(voucherCost) + "₫");
                                    totalCost1.setText(String.valueOf(total[0]) + "₫");
                                    repurchaseCost.setText((new DecimalFormat("#,###").format(total[1])) + "₫");
                                    finalIdVoucher = 0;
                                    voucher1.removeAllItems();getVoucher();
                                    voucher1.addItem("--- Chọn mã giảm ---");
                                    for (int i = 0; i < vouchers.size(); i++) {
                                        Map<String, Object> voucher = vouchers.get(i);
                                        voucher1.addItem((String) voucher.get("codeVoucher"));
                                        if (total[1] >= (int) voucher.get("toCost")) {
                                            voucher1.setSelectedIndex(i + 1);
                                            finalIdVoucher = (int) voucher.get("idVoucher");
                                        }
                                    }
                                    changePaymentMethod.setUndecorated(true);
                                    changePaymentMethod.setResizable(false);
                                    changePaymentMethod.setSize(621, 1344);
                                    changePaymentMethod.setLocationRelativeTo(null);
                                    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--- Chọn phương thước thanh toán ---", "Thanh toán khi nhận hàng", "Chuyển khoản ngân hàng"}));
                                    changePaymentMethod.setVisible(true);
                                    changePaymentMethod.setBackground(new Color(0, 0, 0, 60));
                                    changePaymentMethod.setAlwaysOnTop(true);
                                    jComboBox2.setName(label13.getName());
                                    jLabel57.setName(String.valueOf(bill.get("totalPrice")));
                                }
                            }
                        });
                    } else {
                        label13.setText("Hủy đơn");
                        label13.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                label13.setForeground(new java.awt.Color(255, 0, 51));
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                label13.setForeground(new java.awt.Color(210, 154, 95)); // Restore original border on mouse exit
                            }

                            @Override
                            public void mouseClicked(MouseEvent e) {
                                notification.setAlwaysOnTop(true);
                                Warning warning = new Warning(notification, true, true);
                                warning.setTitle("Thông báo");
                                warning.setWarning("Bạn chắc chắn muốn tiếp tục?");
                                warning.setVisible(true);
                                if (warning.getConfirm()) {
                                    cancelReason.setUndecorated(true);
                                    cancelReason.setResizable(false);
                                    cancelReason.setSize(621, 1344);
                                    cancelReason.setLocationRelativeTo(null);
                                    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"---- Chọn lý do hủy đơn ----", "Thời gian chờ quá lâu", "Muốn đổi hình thức thanh toán", "Muốn thay đổi địa chỉ nhận hàng", "Đổi ý không muốn mua nữa"}));
                                    cancelReason.setVisible(true);
                                    cancelReason.setBackground(new Color(0, 0, 0, 60));
                                    cancelReason.setAlwaysOnTop(true);
                                    jComboBox1.setName(label13.getName());
                                }
                            }
                        });
                    }

                    javax.swing.GroupLayout viewOrderDetailPanelLayout = new javax.swing.GroupLayout(viewOrderDetailPanel);
                    viewOrderDetailPanel.setLayout(viewOrderDetailPanelLayout);
                    viewOrderDetailPanelLayout.setHorizontalGroup(
                            viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewOrderDetailPanelLayout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(separator1)
                                                    .addComponent(separator)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewOrderDetailPanelLayout.createSequentialGroup()
                                                            .addComponent(label8)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(viewOrderDetailPanelLayout.createSequentialGroup()
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(label0)
                                                                    .addComponent(label))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewOrderDetailPanelLayout.createSequentialGroup()
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(label2)
                                                                    .addComponent(label3))
                                                            .addGap(349, 349, 349)
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                                                    .addGroup(viewOrderDetailPanelLayout.createSequentialGroup()
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(label9)
                                                                    .addComponent(label11))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(label12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                                                    .addComponent(label13, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                    .addComponent(separator0, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(20, 20, 20))
                    );
                    viewOrderDetailPanelLayout.setVerticalGroup(
                            viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewOrderDetailPanelLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label)
                                                    .addComponent(label4))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label2)
                                                    .addComponent(label5))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label3)
                                                    .addComponent(label6))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label0)
                                                    .addComponent(label1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label8)
                                                    .addComponent(label7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(separator0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label9)
                                                    .addComponent(label10))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(viewOrderDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label11)
                                                    .addComponent(label12))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label13)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }
                noteLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
                noteLabel.setForeground(new java.awt.Color(146, 146, 148));
                noteLabel.setText("Xem ghi chú");
                noteLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                noteLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        noteLabel.setForeground(new java.awt.Color(27, 43, 32));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        noteLabel.setForeground(new java.awt.Color(146, 146, 148)); // Restore original border on mouse exit
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!((String) bill.get("note")).equals("")) {
                            notification.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(notification, productDescription.getText(), "Mô tả sản phẩm", JOptionPane.PLAIN_MESSAGE);
                            Message message = new Message(notification, true);
                            message.setTitle("Ghi chú");
                            message.setContent((String) bill.get("note"));
                            message.setVisible(true);
                        } else {
                            notification.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(notification, productDescription.getText(), "Mô tả sản phẩm", JOptionPane.PLAIN_MESSAGE);
                            Message message = new Message(notification, true);
                            message.setTitle("Ghi chú");
                            message.setContent("Không có");
                            message.setVisible(true);
                        }
                    }
                });
                notePanel.add(noteLabel);
                notePanel.setBackground(new java.awt.Color(255, 255, 255));

                break;
            }

        }
        ordersPanel.add(viewOrderDetailPanel);
        notePanel.add(Box.createVerticalStrut(2));
        ordersPanel.add(notePanel);
        waitConfirms.setViewportView(ordersPanel);
    }

    private void waitConfirmsLabelsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitConfirmsLabelsLabelMouseClicked
        //getOrders((int) Customer.get("idCustomer"));
        dialogMenu.setEnabled(false);
        setDialogOrders();
        setOrderView("Chờ xác nhận");
        openOrders();
    }//GEN-LAST:event_waitConfirmsLabelsLabelMouseClicked

    private void waitOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waitOrderMouseClicked
        dialogMenu.setEnabled(false);
        setDialogOrders();
        setOrderView("Đã xác nhận");
        openOrders();
    }//GEN-LAST:event_waitOrderMouseClicked

    private void historyOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyOrderMouseClicked
        dialogMenu.setEnabled(false);
        setDialogOrders();
        setOrderView("Đã giao");
        openOrders();
    }//GEN-LAST:event_historyOrderMouseClicked

    private void cancelOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderMouseClicked
        dialogMenu.setEnabled(false);
        setDialogOrders();
        setOrderView("Đã hủy");
        openOrders();
    }//GEN-LAST:event_cancelOrderMouseClicked

    private void cancelOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderMouseEntered
        cancelOrder.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_cancelOrderMouseEntered

    private void cancelOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderMouseExited
        cancelOrder.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_cancelOrderMouseExited

    private void editBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseEntered
        editBtn.setForeground(new java.awt.Color(27, 43, 32));
    }//GEN-LAST:event_editBtnMouseEntered

    private void editBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseExited
        editBtn.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_editBtnMouseExited

    private void confirmBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmBtnMouseEntered
        confirmBtn.setForeground(new java.awt.Color(27, 43, 32));
    }//GEN-LAST:event_confirmBtnMouseEntered

    private void confirmBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmBtnMouseExited
        confirmBtn.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_confirmBtnMouseExited

    private void changePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePassMouseClicked
        dialogMenu.setEnabled(false);
        changePanelChangePassword();
        heightProfileMenu = 250;
        openProfileMenu();
    }//GEN-LAST:event_changePassMouseClicked

    private void changePassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePassMouseEntered
        changePass.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_changePassMouseEntered

    private void changePassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePassMouseExited
        changePass.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_changePassMouseExited

    private void fullNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameTextActionPerformed

    private void districtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtActionPerformed

        if (district.getSelectedItem().equals("Quận/Huyện")) {
            wards.setSelectedIndex(0);
            wards.setEnabled(false);
            return;
        } else {
            wards.setEnabled(true);
        }

        if (district.getSelectedItem().equals("Huyện Bình Chánh")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Thị trấn Tân Túc",
                "Xã An Phú Tây",
                "Xã Bình Chánh",
                "Xã Bình Hưng",
                "Xã Bình Lợi",
                "Xã Đa Phước",
                "Xã Hưng Long",
                "Xã Lê Minh Xuân",
                "Xã Phạm Văn Hai",
                "Xã Phong Phú",
                "Xã Qui Đức",
                "Xã Tân Kiên",
                "Xã Tân Nhựt",
                "Xã Tân Quý Tây",
                "Xã Vĩnh Lộc A",
                "Xã Vĩnh Lộc B"}));

        }

        if (district.getSelectedItem().equals("Huyện Cần Giờ")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Thị trấn Cần Thạnh",
                "Xã An Thới Đông",
                "Xã Bình Khánh",
                "Xã Long Hòa",
                "Xã Lý Nhơn",
                "Xã Tam Thôn Hiệp",
                "Xã Thạnh An"}));

        }

        if (district.getSelectedItem().equals("Huyện Củ Chi")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Thị trấn Củ Chi",
                "Xã An Nhơn Tây",
                "Xã An Phú",
                "Xã Bình Mỹ",
                "Xã Hòa Phú",
                "Xã Nhuận Đức",
                "Xã Phạm Văn Cội",
                "Xã Phú Hòa Đông",
                "Xã Phú Mỹ Hưng",
                "Xã Phước Hiệp",
                "Xã Phước Thạnh",
                "Xã Phước Vĩnh An",
                "Xã Tân An Hội",
                "Xã Tân Phú Trung",
                "Xã Tân Thạnh Đông",
                "Xã Tân Thạnh Tây",
                "Xã Tân Thông Hội",
                "Xã Thái Mỹ",
                "Xã Trung An",
                "Xã Trung Lập Thượng",
                "Xã Trung Lập Hạ"}));

        }

        if (district.getSelectedItem().equals("Huyện Hóc Môn")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Thị trấn Hóc Môn",
                "Xã Bà Điểm",
                "Xã Đông Thạnh",
                "Xã Nhị Bình",
                "Xã Tân Hiệp",
                "Xã Tân Thới Nhì",
                "Xã Tân Xuân",
                "Xã Thới Tam Thôn",
                "Xã Trung Chánh",
                "Xã Xuân Thới Đông",
                "Xã Xuân Thới Sơn",
                "Xã Xuân Thới Thượng"}));

        }

        if (district.getSelectedItem().equals("Huyện Nhà Bè")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Thị trấn Nhà Bè",
                "Xã Hiệp Phước",
                "Xã Long Thới",
                "Xã Nhơn Đức",
                "Xã Phú Xuân",
                "Xã Phước Kiển",
                "Xã Phước Lộc"}));

        }

        if (district.getSelectedItem().equals("Quận 1")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường Bến Nghé",
                "Phường Bến Thành",
                "Phường Cô Giang",
                "Phường Cầu Kho",
                "Phường Cầu Ông Lãnh",
                "Phường Đa Kao",
                "Phường Nguyễn Cư Trinh",
                "Phường Nguyễn Thái Bình",
                "Phường Phạm Ngũ Lão",
                "Phường Tân Định"}));

        }

        if (district.getSelectedItem().equals("Quận 10")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 2",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }
        if (district.getSelectedItem().equals("Quận 11")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 16",
                "Phường 2",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận 12")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường An Phú Đông",
                "Phường Đông Hưng Thuận",
                "Phường Hiệp Thành",
                "Phường Tân Chánh Hiệp",
                "Phường Tân Hưng Thuận",
                "Phường Tân Thới Hiệp",
                "Phường Tân Thới Nhất",
                "Phường Thạnh Lộc",
                "Phường Thạnh Xuân",
                "Phường Thới An",
                "Phường Trung Mỹ Tây"}));

        }

        if (district.getSelectedItem().equals("Quận 3")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 9",
                "Phường Võ Thị Sáu"}));

        }

        if (district.getSelectedItem().equals("Quận 4")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 16",
                "Phường 18",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 6",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận 5")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận 6")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận 7")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường Bình Thuận",
                "Phường Phú Mỹ",
                "Phường Phú Thuận",
                "Phường Tân Hưng",
                "Phường Tân Kiểng",
                "Phường Tân Phong",
                "Phường Tân Phú",
                "Phường Tân Quy",
                "Phường Tân Thuận Đông",
                "Phường Tân Thuận Tây"}));

        }

        if (district.getSelectedItem().equals("Quận 8")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 16",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận Bình Tân")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường An Lạc",
                "Phường An Lạc A",
                "Phường Bình Hưng Hòa",
                "Phường Bình Hưng Hòa A",
                "Phường Bình Hưng Hòa B",
                "Phường Bình Trị Đông",
                "Phường Bình Trị Đông A",
                "Phường Bình Trị Đông B",
                "Phường Tân Tạo",
                "Phường Tân Tạo A"}));

        }

        if (district.getSelectedItem().equals("Quận Bình Thạnh")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 17",
                "Phường 19",
                "Phường 2",
                "Phường 21",
                "Phường 22",
                "Phường 24",
                "Phường 25",
                "Phường 26",
                "Phường 27",
                "Phường 28",
                "Phường 3",
                "Phường 5",
                "Phường 6",
                "Phường 7"}));

        }

        if (district.getSelectedItem().equals("Quận Gò Vấp")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 16",
                "Phường 17",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận Phú Nhuận")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 13",
                "Phường 15",
                "Phường 17",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận Tân Bình")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường 1",
                "Phường 10",
                "Phường 11",
                "Phường 12",
                "Phường 13",
                "Phường 14",
                "Phường 15",
                "Phường 2",
                "Phường 3",
                "Phường 4",
                "Phường 5",
                "Phường 6",
                "Phường 7",
                "Phường 8",
                "Phường 9"}));

        }

        if (district.getSelectedItem().equals("Quận Tân Phú")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường Tân Sơn Nhì",
                "Phường Tây Thạnh",
                "Phường Sơn Kỳ",
                "Phường Tân Quý",
                "Phường Tân Thành",
                "Phường Phú Thọ Hòa",
                "Phường Phú Thạnh",
                "Phường Phú Trung",
                "Phường Hòa Thạnh",
                "Phường Hiệp Tân",
                "Phường Tân Thới Hòa"}));

        }

        if (district.getSelectedItem().equals("Thành Phố Thủ Đức")) {
            wards.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Phường/Xã",
                "Phường An Khánh",
                "Phường An Lợi Đông",
                "Phường An Phú",
                "Phường Bình Chiểu",
                "Phường Bình Thọ",
                "Phường Bình Trưng Đông",
                "Phường Bình Trưng Tây",
                "Phường Cát Lái",
                "Phường Hiệp Bình Chánh",
                "Phường Hiệp Bình Phước",
                "Phường Hiệp Phú",
                "Phường Linh Chiểu",
                "Phường Linh Đông",
                "Phường Linh Tây",
                "Phường Linh Trung",
                "Phường Linh Xuân",
                "Phường Long Bình",
                "Phường Long Phước",
                "Phường Long Thạnh Mỹ",
                "Phường Long Trường",
                "Phường Phú Hữu",
                "Phường Phước Bình",
                "Phường Phước Long A",
                "Phường Phước Long B",
                "Phường Tam Bình",
                "Phường Tam Phú",
                "Phường Tăng Nhơn Phú A",
                "Phường Tăng Nhơn Phú B",
                "Phường Tân Phú",
                "Phường Thảo Điền",
                "Phường Thạnh Mỹ Lợi",
                "Phường Thủ Thiêm",
                "Phường Trường Thạnh",
                "Phường Trường Thọ"}));

        }
    }//GEN-LAST:event_districtActionPerformed

    private void wardsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wardsMouseClicked

        if (!(district.getSelectedItem() == "Quận/Huyện")) {

        } else {
            notification.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(notification, "Chưa chọn Quận/Huyện!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Chưa chọn Quận/Huyện!!!");
            warning.setInvisibleOnTop();
            warning.setVisible(true);
        }
    }//GEN-LAST:event_wardsMouseClicked

    private void apartmentNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apartmentNumberFocusLost
        if (apartmentNumber.getText().equals("")) {
            apartmentNumber.setText("Số nhà");
        }
    }//GEN-LAST:event_apartmentNumberFocusLost

    private void apartmentNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apartmentNumberFocusGained
        if (apartmentNumber.getText().equals("Số nhà")) {
            apartmentNumber.setText("");
        }
    }//GEN-LAST:event_apartmentNumberFocusGained

    private void dayBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayBirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayBirthActionPerformed

    private void monthBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBirthActionPerformed
        String s = "Ngày";
        int x = 29;
        if (!"Ngày".equals(dayBirth.getSelectedItem())) {
            s = (String) dayBirth.getSelectedItem();
        }
        if ("02".equals(monthBirth.getSelectedItem())) {
            if (!"Năm".equals(yearBirth.getSelectedItem())) {
                x = namNhuan(Integer.parseInt((String) yearBirth.getSelectedItem())) ? 29 : 28;
            }
            dayBirth.removeAllItems();
            dayBirth.addItem("Ngày");

            for (int i = 1; i <= x; i++) {
                String dayStr = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                dayBirth.addItem(dayStr);
            }

        }
        var t31 = Arrays.asList("01", "03", "05", "07", "08", "10", "12");
        if (t31.contains(monthBirth.getSelectedItem())) {
            dayBirth.removeAllItems();
            dayBirth.addItem("Ngày");
            for (int i = 1; i <= (x = 31); i++) {
                String dayStr = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                dayBirth.addItem(dayStr);
            }
        }
        var t30 = Arrays.asList("04", "06", "09", "11");
        if (t30.contains(monthBirth.getSelectedItem())) {
            dayBirth.removeAllItems();
            dayBirth.addItem("Ngày");
            for (int i = 1; i <= (x = 30); i++) {
                String dayStr = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                dayBirth.addItem(dayStr);
            }
        }
        if (!"Ngày".equals(s) && Integer.parseInt(s) <= x)
            dayBirth.setSelectedItem(s);
    }//GEN-LAST:event_monthBirthActionPerformed

    private void monthBirthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_monthBirthFocusGained

    }//GEN-LAST:event_monthBirthFocusGained

    private void yearBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBirthActionPerformed
        String s = "Ngày";
        int x = 28;
        if (!"Ngày".equals(dayBirth.getSelectedItem())) {
            s = (String) dayBirth.getSelectedItem();
        }

        if ("02".equals(monthBirth.getSelectedItem())) {
            if (namNhuan(Integer.parseInt((String) yearBirth.getSelectedItem()))) {
                x = 29;
            }
            dayBirth.removeAllItems();
            dayBirth.addItem("Ngày");
            for (int i = 1; i <= x; i++) {
                String dayStr = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                dayBirth.addItem(dayStr);
            }
        }
        if (!"Ngày".equals(s) && Integer.parseInt(s) <= x)
            dayBirth.setSelectedItem(s);
    }//GEN-LAST:event_yearBirthActionPerformed

    private void confirmChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmChangePasswordMouseClicked
        char[] inputOldPassword = oldPassword.getPassword();
        char[] inputNewPassword = newPassword.getPassword();
        char[] inputNewPasswordConfirm = newPasswordConfirm.getPassword();
        String enteredOldPassword = new String(inputOldPassword);
        String enteredNewPassword = new String(inputNewPassword);
        String enteredNewPasswordConfirm = new String(inputNewPasswordConfirm);
        if (enteredOldPassword.equals("") || enteredOldPassword.equals("Nhập mật khẩu cũ")
                || enteredNewPassword.equals("") || enteredNewPassword.equals("Nhập mật khẩu mới")
                || enteredNewPasswordConfirm.equals("") || enteredNewPasswordConfirm.equals("Nhập lại mật khẩu mới")) {
            warningChangePassword.setText("Không được để trống thông tin!!!");
        } else {
            if (enteredNewPassword.length() < 6) {
                warningChangePassword.setText("Mật khẩu phải dài hơn 6 ký tự!!!");
            } else if (!enteredNewPassword.equals(enteredNewPasswordConfirm)) {
                warningChangePassword.setText("Mật khẩu xác nhận không giống!!!");
            } else if (!checkPassword((int) Customer.get("idCustomer"), enteredOldPassword)) {
                warningChangePassword.setText("Mật khẩu cũ không chính xác!!!");
            } else {
                closeProfileMenu();
                dialogMenu.setEnabled(true);
                heightProfileMenu = 350;
                changePassword((int) Customer.get("idCustomer"), enteredNewPasswordConfirm);
                notification.setAlwaysOnTop(true);
                Warning warning = new Warning(notification, true);
                warning.setTitle("Thông báo");
                warning.setColorWarning();
                warning.setWarning("Đổi mật khẩu!!!");
                warning.setVisible(true);
                invisibleFrame.setAlwaysOnTop(true);
            }
        }
    }//GEN-LAST:event_confirmChangePasswordMouseClicked

    private void confirmChangePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmChangePasswordMouseEntered
        confirmChangePassword.setForeground(new java.awt.Color(0, 255, 0));
    }//GEN-LAST:event_confirmChangePasswordMouseEntered

    private void confirmChangePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmChangePasswordMouseExited
        confirmChangePassword.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_confirmChangePasswordMouseExited

    private void showpassStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_showpassStateChanged

        char[] inputOldPassword = oldPassword.getPassword();
        char[] inputNewPassword = newPassword.getPassword();
        char[] inputNewPasswordConfirm = newPasswordConfirm.getPassword();
        String enteredOldPassword = new String(inputOldPassword);
        String enteredNewPassword = new String(inputNewPassword);
        String enteredNewPasswordConfirm = new String(inputNewPasswordConfirm);
        if (!enteredOldPassword.equals("Nhập mật khẩu cũ")) {
            if (showpass.isSelected()) {
                oldPassword.setEchoChar('\u0000');
            } else {
                oldPassword.setEchoChar('\u2022');
            }
        }
        if (!enteredNewPassword.equals("Nhập mật khẩu mới")) {
            if (showpass.isSelected()) {
                newPassword.setEchoChar('\u0000');
            } else {
                newPassword.setEchoChar('\u2022');
            }
        }
        if (!enteredNewPasswordConfirm.equals("Nhập lại mật khẩu mới")) {
            if (showpass.isSelected()) {
                newPasswordConfirm.setEchoChar('\u0000');
            } else {
                newPasswordConfirm.setEchoChar('\u2022');
            }
        }

    }//GEN-LAST:event_showpassStateChanged

    private void showpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpassMouseClicked

    }//GEN-LAST:event_showpassMouseClicked

    private void showpassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpassMouseExited

    }//GEN-LAST:event_showpassMouseExited

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showpassActionPerformed

    private void newPasswordConfirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPasswordConfirmFocusGained
        warningChangePassword.setText("");
        showpass.setSelected(false);
        char[] inputPassword = newPasswordConfirm.getPassword();
        String enteredPassword = new String(inputPassword);
        if (enteredPassword.equals("Nhập lại mật khẩu mới")) {
            newPasswordConfirm.setText("");
            newPasswordConfirm.setEchoChar('\u2022');
        } else {
            newPasswordConfirm.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_newPasswordConfirmFocusGained

    private void newPasswordConfirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPasswordConfirmFocusLost
        char[] inputPassword = newPasswordConfirm.getPassword();
        String enteredPassword = new String(inputPassword);

        if (enteredPassword.equals("")) {
            newPasswordConfirm.setEchoChar('\u0000');
            newPasswordConfirm.setText("Nhập lại mật khẩu mới");
        }
    }//GEN-LAST:event_newPasswordConfirmFocusLost

    private void newPasswordConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordConfirmActionPerformed

    private void newPasswordConfirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPasswordConfirmKeyPressed

    }//GEN-LAST:event_newPasswordConfirmKeyPressed

    private void newPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPasswordFocusGained
        showpass.setSelected(false);
        warningChangePassword.setText("");
        char[] inputPassword = newPassword.getPassword();
        String enteredPassword = new String(inputPassword);
        if (enteredPassword.equals("Nhập mật khẩu mới")) {
            newPassword.setText("");
            newPassword.setEchoChar('\u2022');
        } else {
            newPassword.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_newPasswordFocusGained

    private void newPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPasswordFocusLost
        char[] inputPassword = newPassword.getPassword();
        String enteredPassword = new String(inputPassword);

        if (enteredPassword.equals("")) {
            newPassword.setEchoChar('\u0000');
            newPassword.setText("Nhập mật khẩu mới");
        }
    }//GEN-LAST:event_newPasswordFocusLost

    private void newPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordActionPerformed

    private void newPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPasswordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordKeyPressed

    private void oldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_oldPasswordFocusGained
        showpass.setSelected(false);
        warningChangePassword.setText("");
        char[] inputPassword = oldPassword.getPassword();
        String enteredPassword = new String(inputPassword);
        if (enteredPassword.equals("Nhập mật khẩu cũ")) {
            oldPassword.setText("");
            oldPassword.setEchoChar('\u2022');
        } else {
            oldPassword.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_oldPasswordFocusGained

    private void oldPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_oldPasswordFocusLost
        char[] inputPassword = oldPassword.getPassword();
        String enteredPassword = new String(inputPassword);

        if (enteredPassword.equals("")) {
            oldPassword.setEchoChar('\u0000');
            oldPassword.setText("Nhập mật khẩu cũ");
        }
    }//GEN-LAST:event_oldPasswordFocusLost

    private void oldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldPasswordActionPerformed

    private void oldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oldPasswordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldPasswordKeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        closeProfileMenu();
        dialogMenu.setEnabled(true);
        heightProfileMenu = 350;
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        payment.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed

        finalPaymentMethodsSelected = "";
        if (paymentMethods.getSelectedItem().equals("--- Chọn phương thức thanh toán ---")) {
            notification.setAlwaysOnTop(true);
//            JOptionPane.showMessageDialog(notification, "Chưa chọn Quận/Huyện!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Chưa chọn Phương thước thanh toán!!!");
            warning.setInvisibleOnTop();
            warning.setVisible(true);
            return;
        }
        finalPaymentMethodsSelected = (String) paymentMethods.getSelectedItem();
        finalQuantity = 0;
        for (int i = 0; i < Glasses.size(); i++) {
            finalQuantity += (int) Glasses.get(i).get("quantityProduct");
        }
        finalNoteText = note.getText().substring(0, Math.min(note.getText().length(), 100));
        //int finalTotal = Integer.parseInt(totalPricePayment.getText().substring(0, totalPricePayment.getText().length() - 1));
//        for (int i = 0; i < Glasses.size(); i++) {
//            Map<String, Object> item = Glasses.get(i);
//            System.out.println(item);
//            if (i == Glasses.size() - 1) {
//                return;
//            }
//        }
        if (paymentMethods.getSelectedIndex() == 2) {
            jLabel47.setText("<html>Cần thanh toán: <span style='color:red;'>" + (new DecimalFormat("#,###").format(finalCost)) + "</span> VND</html>");
            QR.setUndecorated(true);
            QR.setResizable(false);
            QR.setSize(502, 630);
            QR.setLocationRelativeTo(null);
            invisibleFrame.setAlwaysOnTop(true);
            QR.setAlwaysOnTop(true);
            QR.setVisible(true);
            return;
        }
        createOrder(1, finalCost, finalQuantity, finalIdVoucher, "Chờ xác nhận", finalPaymentMethodsSelected, (String) Customer.get("addressCustomer"), finalNoteText, (int) Customer.get("idCustomer"), Glasses);
        payment.dispose();
        closeCart();
        //setAlwaysOnTop(true);
        emptyViewport = new JViewport();
        cartProducts.setViewport(emptyViewport);
        cartProductsPanel = new JPanel();
        cartProductsPanel.setLayout(new BoxLayout(cartProductsPanel, BoxLayout.Y_AXIS));
        cartProductsPanel.setBackground(new java.awt.Color(255, 255, 255));
//        waitConfirms.setViewport(emptyViewport);
//        //waitConfirms.setSize(619, 640);
//        ordersPanel = new JPanel();
//        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        numberItemCart[0] = 0;
        setQuantityCart(numberItemCart[0]);
        total[1] = 0;
        listCartProductsPanel.clear();
        Glasses.clear();
        idGlass[0] = getNewIdItemDetail();
        notification.setAlwaysOnTop(true);
        Warning warning = new Warning(notification, true);
        warning.setTitle("Thông báo");
        warning.setColorWarning();
        warning.setWarning("Đặt hàng thành công!!!");
        warning.setVisible(true);
        if (invisibleFrame.isVisible()) {
            invisibleFrame.dispose();
        }
//        if (warning.getConfirm()) {
//            if (invisibleFrame.isVisible()) {
//                invisibleFrame.dispose();
//            }
//        }
    }//GEN-LAST:event_payActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        setOrderView(orderStatus.getName());
    }//GEN-LAST:event_jLabel17MouseClicked

    private void noteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noteKeyPressed
        String text = note.getText();
        if (text.length() > 100) {
            note.setText(text.substring(0, Math.min(text.length(), 100)));
            notification.setAlwaysOnTop(true);
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Ghi chú không quá 100 ký tự!!!");
            warning.setInvisibleOnTop();
            warning.setVisible(true);
            return;
        }
    }//GEN-LAST:event_noteKeyPressed

    private void voucherComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherComboBoxActionPerformed

        if (voucherComboBox.getSelectedIndex() == 0) {
            voucherWarning.setText("");
            voucherCost = 0;
            finalCost = Integer.parseInt(totalCost.getText().substring(0, totalCost.getText().length() - 1))
                    + Integer.parseInt(deliveryChargesCost.getText().substring(0, deliveryChargesCost.getText().length() - 1))
                    - voucherCost;
            totalPricePayment.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
            voucherCost1.setText("-0₫");
            finalIdVoucher = 0;

        } else {
            for (int i = 0; i < voucherComboBox.getSelectedIndex(); i++) {
                Map<String, Object> voucher = vouchers.get(i);
                if (voucherComboBox.getSelectedItem().equals(voucher.get("codeVoucher"))) {
                    if (total[1] >= (int) voucher.get("toCost")) {
                        voucherCost = (int) (total[1] * (int) voucher.get("percentDiscount") / 100);
                        voucherWarning.setText("Giảm " + String.valueOf(voucher.get("percentDiscount")) + "% cho hóa đơn từ " + (new DecimalFormat("#,###").format((int) voucher.get("toCost"))) + "₫");
                        voucherCost1.setText("-" + String.valueOf(voucherCost) + "₫");
                        finalCost = Integer.parseInt(totalCost.getText().substring(0, totalCost.getText().length() - 1))
                                + Integer.parseInt(deliveryChargesCost.getText().substring(0, deliveryChargesCost.getText().length() - 1))
                                - voucherCost;
                        totalPricePayment.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
                        finalIdVoucher = (int) voucher.get("idVoucher");
                    } else {
                        finalIdVoucher = 0;
                        voucherWarning.setText("Chưa đủ điều kiện để sử dụng mã!");
                        voucherCost = 0;
                        voucherCost1.setText("-0₫");
                        finalCost = Integer.parseInt(totalCost.getText().substring(0, totalCost.getText().length() - 1))
                                + Integer.parseInt(deliveryChargesCost.getText().substring(0, deliveryChargesCost.getText().length() - 1))
                                - voucherCost;
                        totalPricePayment.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
                    }
                }
            }
        }
    }//GEN-LAST:event_voucherComboBoxActionPerformed

    private void invisibleFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invisibleFrameMouseClicked

    }//GEN-LAST:event_invisibleFrameMouseClicked

    private void jLabel41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseEntered
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
    }//GEN-LAST:event_jLabel41MouseEntered

    private void jLabel41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseExited
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jLabel41MouseExited

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        if (changePaymentMethod.isVisible()) {
            changePaymentMethod.dispose();
            QR.setAlwaysOnTop(false);
            QR.dispose();
            notification.setAlwaysOnTop(true);
            Warning warning1 = new Warning(notification, true);
            warning1.setTitle("Thông báo");
            warning1.setColorWarning();
            warning1.setWarning("Đặt hàng thành công!!!");
            warning1.setVisible(true);
            repurchase(Integer.parseInt(jComboBox2.getName()), (String) jComboBox2.getSelectedItem(), finalIdVoucher, finalCost);
            voucherCost = 0;
            finalCost = 0;
            finalIdVoucher = 0;
            total[1] = 0;
            total[0] = 0;
            if (invisibleFrame.isVisible()) {
                invisibleFrame.dispose();
            }
            invisibleFrame.setAlwaysOnTop(false);

            return;
        }
        QR.setAlwaysOnTop(false);
        QR.dispose();
        createOrder(1, finalCost, finalQuantity, finalIdVoucher, "Chờ xác nhận", finalPaymentMethodsSelected, (String) Customer.get("addressCustomer"), finalNoteText, (int) Customer.get("idCustomer"), Glasses);
        payment.dispose();
        closeCart();
        //setAlwaysOnTop(true);
        emptyViewport = new JViewport();
        cartProducts.setViewport(emptyViewport);
        cartProductsPanel = new JPanel();
        cartProductsPanel.setLayout(new BoxLayout(cartProductsPanel, BoxLayout.Y_AXIS));
        cartProductsPanel.setBackground(new java.awt.Color(255, 255, 255));
//        waitConfirms.setViewport(emptyViewport);
//        //waitConfirms.setSize(619, 640);
//        ordersPanel = new JPanel();
//        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        numberItemCart[0] = 0;
        setQuantityCart(numberItemCart[0]);
        total[1] = 0;
        listCartProductsPanel.clear();
        Glasses.clear();
        idGlass[0] = getNewIdItemDetail();
        notification.setAlwaysOnTop(true);
        Warning warning = new Warning(notification, true);
        warning.setTitle("Thông báo");
        warning.setColorWarning();
        warning.setWarning("Đặt hàng thành công!!!");
        warning.setVisible(true);
        if (invisibleFrame.isVisible()) {
            invisibleFrame.dispose();
        }
        invisibleFrame.setAlwaysOnTop(false);

    }//GEN-LAST:event_jLabel41MouseClicked

    private void introduceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_introduceMouseClicked

        introduceFrame.setUndecorated(true);
        introduceFrame.setResizable(false);
        introduceFrame.setAlwaysOnTop(true);
        introduceFrame.setLocationRelativeTo(null);
        introduceFrame.setBounds(458, 0, 621, 1080);
        introduceFrame.setVisible(true);

    }//GEN-LAST:event_introduceMouseClicked

    private void introduceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_introduceMouseEntered
        introduce.setForeground(new java.awt.Color(210, 154, 95));
    }//GEN-LAST:event_introduceMouseEntered

    private void introduceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_introduceMouseExited
        introduce.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_introduceMouseExited

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        introduceFrame.dispose();
    }//GEN-LAST:event_jLabel48MouseClicked

    private void continueBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtnMouseClicked

        if (jComboBox1.getSelectedIndex() == 0) {
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Chưa chọn lý do hủy đơn!!!");
            warning.setVisible(true);
        } else {
            cancelOrder(Integer.parseInt(jComboBox1.getName()), (int) Customer.get("idCustomer"), "Đã hủy", (String) jComboBox1.getSelectedItem());
            setOrderView("Chờ xác nhận");
            cancelReason.dispose();
            Warning warning1 = new Warning(notification, true);
            warning1.setTitle("Thông báo");
            warning1.setColorWarning();
            warning1.setWarning("Hủy đơn hàng thành công!!!");
            warning1.setVisible(true);
        }
    }//GEN-LAST:event_continueBtnMouseClicked

    private void continueBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtnMouseEntered
        continueBtn.setForeground(new Color(0, 150, 0));
    }//GEN-LAST:event_continueBtnMouseEntered

    private void continueBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtnMouseExited
        continueBtn.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_continueBtnMouseExited

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        cancelReason.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        cancel.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        cancel.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_cancelMouseExited

    private void continueBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtn1MouseClicked
        if (jComboBox2.getSelectedIndex() == 0) {
            Warning warning = new Warning(notification, true);
            warning.setTitle("Thông báo");
            warning.setWarning("Chưa chọn phương thức thanh toán!!!");
            warning.setVisible(true);
        } else if (jComboBox2.getSelectedIndex() == 2) {
            jLabel47.setText("<html>Cần thanh toán: <span style='color:red;'>" + (new DecimalFormat("#,###").format(finalCost)) + "</span> VND</html>");
            QR.setUndecorated(true);
            QR.setResizable(false);
            QR.setSize(502, 630);
            QR.setLocationRelativeTo(null);
            invisibleFrame.setAlwaysOnTop(true);
            QR.setAlwaysOnTop(true);
            QR.setVisible(true);
        } else {
            changePaymentMethod.dispose();
            notification.setAlwaysOnTop(true);
            Warning warning1 = new Warning(notification, true);
            warning1.setTitle("Thông báo");
            warning1.setColorWarning();
            warning1.setWarning("Đặt hàng thành công!!!");
            warning1.setVisible(true);
            repurchase(Integer.parseInt(jComboBox2.getName()), (String) jComboBox2.getSelectedItem(), finalIdVoucher, finalCost);
            voucherCost = 0;
            finalCost = 0;
            finalIdVoucher = 0;
            total[1] = 0;
            total[0] = 0;
        }
    }//GEN-LAST:event_continueBtn1MouseClicked

    private void continueBtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtn1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_continueBtn1MouseEntered

    private void continueBtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueBtn1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_continueBtn1MouseExited

    private void cancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseClicked
        changePaymentMethod.dispose();
    }//GEN-LAST:event_cancel1MouseClicked

    private void cancel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cancel1MouseEntered

    private void cancel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cancel1MouseExited

    private void invisibleFrame1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invisibleFrame1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_invisibleFrame1MouseClicked

    private void voucher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucher1ActionPerformed
        
        if (voucher1.getSelectedIndex() == 0) {
            voucherWarning1.setText("");
            finalIdVoucher = 0;
            voucherCost = (int) (total[0]);

            finalCost = total[0] + 16000;
            repurchaseCost.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
            voucherCost2.setText("-0₫");
        } else {
            for (int i = 0; i < voucher1.getSelectedIndex(); i++) {
                Map<String, Object> voucher = vouchers.get(i);
                if (voucher1.getSelectedItem().equals(voucher.get("codeVoucher"))) {
                    if (total[1] >= (int) voucher.get("toCost")) {
                        finalIdVoucher = (int) voucher.get("idVoucher");
                        int percentDiscount = (int) voucher.get("percentDiscount");
                        voucherCost = (int) (total[0] * percentDiscount / 100);
                        voucherWarning1.setText("Giảm " + String.valueOf(voucher.get("percentDiscount")) + "% cho hóa đơn từ " + (new DecimalFormat("#,###").format((int) voucher.get("toCost"))) + "₫");
                        voucherCost2.setText("-" + String.valueOf(voucherCost) + "₫");
                        finalCost = total[0] + 16000 - voucherCost;
                        repurchaseCost.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
                    } else {
                        finalIdVoucher = 0;
                        voucherWarning1.setText("Chưa đủ điều kiện để sử dụng mã!");
                        voucherCost = (int) (total[0]);
                        voucherCost2.setText("-0₫");
                        finalCost = total[0] + 16000;
                        repurchaseCost.setText((new DecimalFormat("#,###").format(finalCost)) + "₫");
                    }
                }
            }
        }
        
    }//GEN-LAST:event_voucher1ActionPerformed

    public static boolean namNhuan(int y) {
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            return true;
        } else {
            return false;
        }
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
            java.util.logging.Logger.getLogger(CustomerDisplay.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDisplay.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDisplay.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDisplay.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerDisplay customerDisplay = new CustomerDisplay(1);
                customerDisplay.setVisible(true);

            }

        });
    }

    public void changePanelWatch() {
        this.profile.removeAll();
        this.profile.add(this.watch);
        this.profile.repaint();
        this.profile.revalidate();
        getAccLogin((String) Customer.get("userName"), false);
        if (Customer.get("nameCustomer") != null) {
            fullNameProfile.setText(String.valueOf(Customer.get("nameCustomer")));
        }
        if (male.isSelected()) {
            genderProfile.setText("Nam");
        } else if (female.isSelected()) {
            genderProfile.setText("Nữ");
        } else if (otherGender.isSelected()) {
            genderProfile.setText("Khác");
        }
        if (!Customer.get("birthday").equals("")) {
            birthProfile.setText(String.valueOf(Customer.get("birthday")));
        }
        phoneProfile.setText(String.valueOf(Customer.get("numberPhoneCustomer")));
        String[] addressElement = String.valueOf(Customer.get("addressCustomer")).split(", ");
        apartmentNumberProfile.setText(addressElement[0]);
        districtProfile.setText(addressElement[2]);
        wardsProfile.setText(addressElement[1]);
    }

    public void changePanelEdit() {
        this.profile.removeAll();
        this.profile.add(this.edit);
        this.profile.repaint();
        this.profile.revalidate();
        if (!Customer.get("nameCustomer").equals("")) {
            fullNameText.setText((String) Customer.get("nameCustomer"));
        }
        if (!Customer.get("numberPhoneCustomer").equals("")) {
            phoneText.setText((String) Customer.get("numberPhoneCustomer"));
        }
        String[] addressElement = String.valueOf(Customer.get("addressCustomer")).split(", ");
        apartmentNumber.setText(addressElement[0]);
        district.setSelectedItem(addressElement[2]);
        wards.setSelectedItem(addressElement[1]);
        if (addressElement[2].equals("Quận/Huyện")) {
            wards.setEnabled(false);
        }
        if (!Customer.get("gender").equals("")) {
            int genderValue = (Integer) Customer.get("gender");
            if (genderValue == 1) {
                male.setSelected(true);
            } else if (genderValue == 2) {
                female.setSelected(true);
            } else if (genderValue == 3) {
                otherGender.setSelected(true);
            }
        }
        if (!Customer.get("birthday").equals("")) {
            String[] birthday = String.valueOf(Customer.get("birthday")).split("/");
            yearBirth.setSelectedItem(birthday[2]);
            monthBirth.setSelectedItem(birthday[1]);
            dayBirth.setSelectedItem(birthday[0]);
        }
    }

    public void changePanelChangePassword() {
        this.profile.removeAll();
        this.profile.add(this.changePassword);
        this.profile.repaint();
        this.profile.revalidate();

        warningChangePassword.setText("");

        oldPassword.setText("Nhập mật khẩu cũ");
        oldPassword.setEchoChar('\u0000');

        newPassword.setText("Nhập mật khẩu mới");
        newPassword.setEchoChar('\u0000');

        newPasswordConfirm.setText("Nhập lại mật khẩu mới");
        newPasswordConfirm.setEchoChar('\u0000');
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Layout;
    private javax.swing.JFrame QR;
    private static javax.swing.JButton add;
    private static javax.swing.JPanel addNewProduct;
    private javax.swing.JLabel address;
    private javax.swing.JLabel addressLable;
    private javax.swing.JLabel addressPayment;
    private javax.swing.JTextField apartmentNumber;
    private javax.swing.JLabel apartmentNumberProfile;
    private javax.swing.JLabel birth;
    private javax.swing.JLabel birth1;
    private javax.swing.JLabel birthLable;
    private javax.swing.JLabel birthProfile;
    private javax.swing.JLabel cancel;
    private javax.swing.JLabel cancel1;
    private javax.swing.JLabel cancelOrder;
    private javax.swing.JFrame cancelReason;
    private javax.swing.JFrame cart;
    private javax.swing.JLabel cartIcon;
    private static javax.swing.JPanel cartPanelLayout;
    private javax.swing.JScrollPane cartProducts;
    private javax.swing.JLabel changePass;
    private javax.swing.JPanel changePassword;
    private javax.swing.JFrame changePaymentMethod;
    private javax.swing.JLabel confirmBtn;
    private javax.swing.JLabel confirmChangePassword;
    private javax.swing.JLabel continueBtn;
    private javax.swing.JLabel continueBtn1;
    private javax.swing.JLabel customerName;
    private javax.swing.JComboBox<String> dayBirth;
    private javax.swing.JLabel deliveryChargesCost;
    private javax.swing.JLabel deliveryChargesCost1;
    private javax.swing.JLabel deliveryChargesText;
    private javax.swing.JLabel deliveryChargesText1;
    private javax.swing.JComboBox<String> district;
    private javax.swing.JLabel districtProfile;
    private javax.swing.JPanel edit;
    private javax.swing.JLabel editBtn;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JLabel exitMenu;
    private javax.swing.JRadioButton female;
    private javax.swing.JLabel fullName;
    private javax.swing.JLabel fullName1;
    private javax.swing.JLabel fullNameLable;
    private javax.swing.JLabel fullNameProfile;
    private javax.swing.JTextField fullNameText;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel gender1;
    private javax.swing.JLabel genderLable;
    private javax.swing.JLabel genderProfile;
    private javax.swing.JLabel historyOrder;
    private javax.swing.JLabel infomation;
    private javax.swing.JLabel introduce;
    private javax.swing.JFrame introduceFrame;
    public static javax.swing.JFrame invisibleFrame;
    public static javax.swing.JFrame invisibleFrame1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JRadioButton male;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel menuIcon;
    private javax.swing.JLabel minus;
    private javax.swing.JComboBox<String> monthBirth;
    private javax.swing.JScrollPane moreSelect;
    private javax.swing.JPanel moreSelectPanel;
    private javax.swing.JLabel nameAndPhonePayment;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JPasswordField newPasswordConfirm;
    private javax.swing.JTextArea note;
    private javax.swing.JPasswordField oldPassword;
    private static javax.swing.JButton order;
    private javax.swing.JLabel orderStatus;
    private javax.swing.JRadioButton otherGender;
    private static javax.swing.JButton pay;
    private javax.swing.JFrame payment;
    private javax.swing.JComboBox<String> paymentMethods;
    private static javax.swing.JPanel paymentPanelLayout;
    private javax.swing.JScrollPane paymentProducts;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel phoneLable;
    private javax.swing.JLabel phoneProfile;
    private javax.swing.JLabel phoneProfile5;
    private javax.swing.JTextField phoneText;
    private javax.swing.JLabel plus;
    private javax.swing.JLabel productDescription;
    private javax.swing.JLabel productDescriptionMore;
    private javax.swing.JLabel productImg;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPrice;
    private javax.swing.JScrollPane productsAll;
    private javax.swing.JTabbedPane productsLayout;
    private javax.swing.JPanel profile;
    private javax.swing.JLabel quantity;
    private javax.swing.JLabel quantityCart;
    private javax.swing.JLabel repurchaseCost;
    private javax.swing.JLabel searchIcon;
    private static javax.swing.JTextField searchText;
    private javax.swing.JFrame selectProduct;
    private javax.swing.JCheckBox showpass;
    private static javax.swing.JPanel thisProduct;
    private javax.swing.JLabel totalCost;
    private javax.swing.JLabel totalCost1;
    private javax.swing.JPanel totalPanel;
    private javax.swing.JLabel totalPriceCart;
    private javax.swing.JLabel totalPricePayment;
    private javax.swing.JLabel totalText;
    private javax.swing.JLabel totalText1;
    private javax.swing.JLabel trasuaTASU;
    private javax.swing.JPanel viewMoreOrder;
    private javax.swing.JComboBox<String> voucher1;
    private javax.swing.JComboBox<String> voucherComboBox;
    private javax.swing.JLabel voucherCost1;
    private javax.swing.JLabel voucherCost2;
    private javax.swing.JLabel voucherWarning;
    private javax.swing.JLabel voucherWarning1;
    private javax.swing.JScrollPane waitConfirms;
    private javax.swing.JLabel waitConfirmsLabel;
    private javax.swing.JPanel waitConfirmsMenu;
    private javax.swing.JLabel waitOrder;
    private javax.swing.JComboBox<String> wards;
    private javax.swing.JLabel wardsProfile;
    private javax.swing.JLabel warningChangePassword;
    private javax.swing.JPanel watch;
    private javax.swing.JComboBox<String> yearBirth;
    // End of variables declaration//GEN-END:variables
}
