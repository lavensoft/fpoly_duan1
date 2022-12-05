/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.views;

import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import org.bson.Document;

import duan1.components.DetailCard;
import duan1.config.Config;
import duan1.controllers.customer.CustomerController;
import duan1.controllers.order.OrderController;
import duan1.controllers.order.OrderDimensionController;
import duan1.models.customer.CustomerModel;
import duan1.models.order.OrderDimensionModel;
import duan1.models.order.OrderModel;
import duan1.models.product.DimensionModel;
import duan1.states.AppStates;
import duan1.utils.Async;
import duan1.utils.Log;
import duan1.utils.Momo;
import duan1.utils.PrintOrder;
import duan1.utils.WebBrowser;
import duan1.utils.WrapLayout;

/**
 *
 * @author TAN PHAT
 */
class Cart {
    public Integer count;
    public DimensionModel product;

    Cart(Integer count, DimensionModel product) {
        this.count = count;
        this.product = product;
    }
}

public class ThemHoaDon extends View {
    //* CONTROLLERS */
    CustomerController customerController = new CustomerController();
    OrderController orderController = new OrderController();
    OrderDimensionController orderDimensionController = new OrderDimensionController();

    //* VARIABLES */
    ArrayList<Cart> products = new ArrayList<>();
    ThemHoaDonSanPham addProductPopup = new ThemHoaDonSanPham();
    DecimalFormat vndFormat = new DecimalFormat("#,### đ");
    Double billPrice = 0.0;
    Boolean searchWaiting = false;
    String customerId;
    Double customerPoints = 0.0;
    OrderModel order;

    /**
     * Creates new form ThemHoaDon
     */
    public ThemHoaDon() {
        initComponents();
        init();
        vndFormat.setMaximumFractionDigits(8);
    }

    //* ADD PRODUCT TO BILL */
    public void addBillProduct(DimensionModel product, Integer count) {
        //Update data
        Cart productCart = new Cart(count, product);
        products.add(0, productCart);

        //Render to UI
        DetailCard detailCard = new DetailCard();
        String desc = "";

        detailCard.setName(product.name);
        desc = "x" + count.toString();

        if(product.price != null ) desc += " - " + vndFormat.format(product.price);

        detailCard.setDescription(desc);
        detailCard.setImg(product.banner);

        detailCard.onDelete(e -> {
            products.remove(productCart);
            productContainer.remove(detailCard);
            productContainer.revalidate();
            calculateBill();

            return null;
        });

        productContainer.add(detailCard, 0);
        productContainer.revalidate();
        calculateBill();
    }

    //* CALCULATE ORDER */
    private void calculateBill() {
        billPrice = 0.0;

        //Calculate Product
        for(Cart item : products) {
            Double productPrice = item.product.price;

            billPrice += productPrice * item.count;
        }

        //Render to ui
        lblTotal.setText(vndFormat.format(billPrice));
        lblDiscount.setText("0");
        lblBillTotal.setText(vndFormat.format(Math.round(billPrice * 1.1)));
    }

    //* CHECK ORDER PAYMENT */
    private void checkPayment() {
        try {
            OrderModel query = new OrderModel();
            query._id = order._id;

            order = orderController.get(query);

            if(order.paymentStatus.equals("success")) {
                btnAdd.setEnabled(false);
                btnAdd.setBackground(new Color(0, 122, 255));
                btnAdd.setForeground(new Color(255, 255, 255));
                // btnAdd.setBorder(BorderFactory.createEmptyBorder());
                btnAdd.setText("Đơn Đã Thanh Toán");
                JOptionPane.showMessageDialog(addProductPopup, "Đơn hàng đã được thanh toán!");
            }else{
                btnAdd.setBackground(new Color(255, 149, 0));
                btnAdd.setForeground(new Color(255, 255, 255));
                // btnAdd.setBorder(BorderFactory.createEmptyBorder());
                btnAdd.setText("Kiểm Tra Thanh Toán");
                JOptionPane.showMessageDialog(addProductPopup, "Đơn hàng chưa được thanh toán!");
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(addProductPopup, e.getMessage());
            Log.error(e);
        }
    }

    //* CREATE ORDER */
    private void submitOrder() {
        try {
            //* CREATE PAYMENT */
            Document momo = new Document();
            
            if(comboPayment.getSelectedIndex() == 0) {
                momo = Momo.create(billPrice, "");
                WebBrowser.open(momo.getString("payUrl"));
            } 

            //* CREATE CUSTOMER */
            if(customerId == null) {
                CustomerModel customer = new CustomerModel();
                customer.phone = txtPhone.getText();
                customer.name = txtName.getText();
    
                customerController.add(customer);
                customer = customerController.get(customer);
                customerId = customer._id;
            }

            //* UPDATE CUSOMTER DATA */
            CustomerModel customerUpdated = new CustomerModel();
            customerUpdated.name = txtName.getText();
            customerUpdated.points = customerPoints + 20;

            CustomerModel customerUpdateQuery = new CustomerModel();
            customerUpdateQuery._id = customerId;

            customerController.updateOne(customerUpdateQuery, customerUpdated);

            //* CREATE ORDER */
            OrderModel order = new OrderModel();
            order.author = AppStates.user.clientUser._id;
            order.customer = customerId;
            order.description = txtNote.getText();
            order.paymentMethod = Config.PAYMENT_METHODS[comboPayment.getSelectedIndex()];
            order.paymentStatus = comboPayment.getSelectedIndex() == 0 ? "pending" : "success";
            order.paymentOrderId = comboPayment.getSelectedIndex() == 0 ? momo.getString("orderId") : "";

            orderController.add(order);
            order = orderController.get(order);

            //* CREATE ORDER DIMENSIONS */
            for(Cart item : products) {
                OrderDimensionModel orderDimension = new OrderDimensionModel();
                orderDimension.order = order._id;
                orderDimension.product = item.product._id;
                orderDimension.count = item.count;
                orderDimension.discount = 0.0;

                orderDimensionController.add(orderDimension);
            }

            this.order = order;

            btnAdd.setText("Kiểm Tra Thanh Toán");
            JOptionPane.showMessageDialog(addProductPopup, "ORDER_CREATE_SUCCESSFULLY");
        }catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
            // Log.error(e);
        }
    }

    //* PRINT ORDER */
    private void printOrder() {
        try {
            PrintOrder.createPDF();
        } catch (IOException e) {
            Log.error(e);
        } catch (Exception e) {
            Log.error(e);
        }
    }

    //* FIND CUSTOMER */
    private void loadCustomerData() {
        try {
            CustomerModel customer = new CustomerModel();
            customer.phone = txtPhone.getText();
            customer = customerController.get(customer);

            if(customer != null) {
                txtName.setText(customer.name);
                txtPhone.setText(customer.phone);
                lblPoint.setText(String.valueOf(customer.points));

                customerId = customer._id;
                if(customer.points != null) customerPoints = customer.points;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            Log.error(e);
        }
    }

    private void init() {
        productContainer.setLayout(new WrapLayout(1, 12, 24));
        headerBar1.setTitle("Tạo Đơn Hàng");
        headerBar1.onBack(() -> {
            this.appContext.navigate(HoaDon.class);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productContainer = new duan1.components.PanelBoder();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        lblPoint = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboPayment = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        lblBillTotal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnPrint = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        headerBar1 = new duan1.components.HeaderBar();
        jPanel3 = new javax.swing.JPanel();
        btnPrint2 = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(802, 690));
        setLayout(new java.awt.BorderLayout());

        productContainer.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout productContainerLayout = new javax.swing.GroupLayout(productContainer);
        productContainer.setLayout(productContainerLayout);
        productContainerLayout.setHorizontalGroup(
            productContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        productContainerLayout.setVerticalGroup(
            productContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(productContainer);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("THÔNG TIN KHÁCH HÀNG");

        jLabel3.setText("SĐT:");

        jLabel4.setText("Họ tên:");

        jLabel5.setText("Điểm thành viên:");

        txtPhone.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtPhoneInputMethodTextChanged(evt);
            }
        });
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });

        lblPoint.setText("POINT");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("THÔNG TIN ĐƠN HÀNG");

        jLabel8.setText("Tổng tiền:");

        lblTotal.setText("POINT");

        jLabel10.setText("Giảm giá:");

        lblDiscount.setText("POINT");

        jLabel12.setText("Thuế VAT:");

        jLabel13.setText("10%");

        jLabel14.setText("Thanh toán:");

        comboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chuyển Khoản", "Tiền Mặt" }));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setText("TỔNG TIỀN:");

        lblBillTotal.setText("POINT");

        jLabel17.setText("Ghi chú:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane2.setViewportView(txtNote);

        btnPrint.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        btnPrint.setText("");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnAdd.setText("Tạo Đơn");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPoint))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                            .addComponent(txtPhone)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(9, 9, 9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(12, 12, 12))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(lblDiscount))
                                    .addComponent(comboPayment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel17)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblBillTotal))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPoint)
                    .addComponent(jLabel5))
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDiscount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblBillTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 64));
        jPanel2.setPreferredSize(new java.awt.Dimension(975, 64));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(headerBar1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnPrint2.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        btnPrint2.setText("");
        btnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint2ActionPerformed(evt);
            }
        });

        btnAddProduct.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        btnAddProduct.setText("");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnPrint2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.LINE_END);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        this.printOrder();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint2ActionPerformed
        this.printOrder();
    }//GEN-LAST:event_btnPrint2ActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        addProductPopup.setHoaDonContext(this);
        addProductPopup.setVisible(true);
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(order == null) this.submitOrder();
        if(order != null && order.paymentStatus != "success") this.checkPayment();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtPhoneInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtPhoneInputMethodTextChanged
        
    }//GEN-LAST:event_txtPhoneInputMethodTextChanged

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed
        if(!searchWaiting) {
            Async.setTimeout(() -> {
                loadCustomerData();
                searchWaiting = false;
            }, 1000);
        }

        searchWaiting = true;
    }//GEN-LAST:event_txtPhoneKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint2;
    private javax.swing.JComboBox<String> comboPayment;
    private duan1.components.HeaderBar headerBar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBillTotal;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblPoint;
    private javax.swing.JLabel lblTotal;
    private duan1.components.PanelBoder productContainer;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
