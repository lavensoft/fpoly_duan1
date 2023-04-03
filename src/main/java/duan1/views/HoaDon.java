/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.views;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import duan1.controllers.customer.CustomerController;
import duan1.controllers.order.OrderController;
import duan1.controllers.user.UserController;
import duan1.models.customer.CustomerModel;
import duan1.models.order.OrderModel;
import duan1.models.user.UserModel;
import duan1.utils.Log;
import duan1.utils.Populate;

/**
 *
 * @author nhatsdevil
 */
public class HoaDon extends View {
    //* VARIABLES */

    //* POPULATES */
    private Populate<CustomerModel> customerPopulate = new Populate<>();
    private Populate<UserModel> userPopulate = new Populate<>();

    //* CONTROLLERS */
    private OrderController orderController = new OrderController();
    private CustomerController customerController = new CustomerController();
    private UserController userController = new UserController();

    //* DATA */
    private ArrayList<OrderModel> orders = new ArrayList<>();
    private ArrayList<CustomerModel> customers = new ArrayList<>();
    private ArrayList<UserModel> users = new ArrayList<>();

    /**
     * Creates new form HoaDon
     */
    public HoaDon() {
        initComponents();
        fetchData();
        init();
    }

    //* PUBLIC */
    
    //* PRIVATE */
    private void init() {
        headerBar1.setTitle("Đơn Hàng");

        //TABLE
        String[] columnNames = {"Khách hàng", "Ngày mua hàng", "Loại thanh toán", "Trạng thái thanh toán", "Người tạo", "Ghi chú"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        orders.forEach(item -> {
            model.addRow(new Object[]{
                customerPopulate.find(item.customer, customers).name,
                item.dateCreated,
                item.paymentMethod,
                item.paymentStatus,
                userPopulate.find(item.author, users).name,
                item.description
            });
        });

        table.setModel(model);
    }

    private void fetchData() {
        try {
            users = userController.getAll();
            customers = customerController.getAll();
            orders = orderController.getAll();
        }catch(Exception e) {
            Log.error(e);
        }
    }

    private void handleShowInfo(java.awt.event.MouseEvent evt) {
        OrderModel order = orders.get(table.getSelectedRow());
        String authorName = userPopulate.find(order.author, users).name;
        String customerName = customerPopulate.find(order.customer, customers).name;

        //Render UI
        lblPhone.setText(authorName);
        lblName.setText(customerName);
        lblPoint.setText(order.dateCreated);
        lblTotal.setText(order.paymentStatus);
        lblDiscount.setText(order.paymentMethod);
        lblNote.setText(order.description);
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
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        headerBar1 = new duan1.components.HeaderBar();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblPoint = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblNote = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(819, 64));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setSize(new java.awt.Dimension(100, 64));

        btnAdd.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        btnAdd.setText("");
        btnAdd.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(headerBar1, java.awt.BorderLayout.LINE_START);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 100));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("THÔNG TIN KHÁCH HÀNG");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, -1, -1));

        jLabel3.setText("Người tạo:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 36, -1, -1));

        jLabel4.setText("Họ tên:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 71, -1, -1));

        jLabel5.setText("Thời gian tạo:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 106, -1, -1));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("THÔNG TIN ĐƠN HÀNG");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 146, -1, -1));

        jLabel8.setText("Trạng thái thanh toán:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 176, -1, -1));

        jLabel10.setText("Loại thanh toán:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 205, -1, -1));

        jLabel12.setText("Thuế VAT:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 234, -1, -1));

        jLabel13.setText("10%");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setText("TỔNG TIỀN:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel16.setText("GHI CHÚ:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        lblPhone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 36, 160, 20));

        lblPoint.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 106, 160, 20));

        lblName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 71, 160, 20));

        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 176, 160, 20));

        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 205, 160, 20));

        lblNote.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNote.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 331, 290, 180));

        add(jPanel3, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        appContext.navigate(ThemHoaDon.class);
    }//GEN-LAST:event_btnAddMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        handleShowInfo(evt);
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private duan1.components.HeaderBar headerBar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPoint;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}