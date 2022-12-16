/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import java.util.ArrayList;

import javax.swing.JCheckBox;

import duan1.controllers.user.PermissionController;
import duan1.models.user.PermissionModel;
import duan1.utils.Log;
import io.socket.client.Socket;
import java.awt.Component;

/**
 *
 * @author nhatsdevil
 */
public class PermissionEdit extends javax.swing.JFrame {
    Socket socket;
    private PermissionController permissionController = new PermissionController();
    private String editId = "";
    
    /**
     * Creates new form PermissionEdit
     */
    public PermissionEdit() {
        initComponents();
        init();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setEditData(PermissionModel data) {
        this.editId = data._id;
        this.txtName.setText(data.name);

        JCheckBox[][] checkbox = {
            {vBill, cBill, eBill, rBill},
            {vProduct, cProduct, eProduct, rProduct},
            {vDiscount, cDiscount, eDiscount, rDiscount},
            {vCustomer, cCustomer, eCustomer, rCustomer},
            {vStaff, cStaff, eStaff, rStaff},
            {vPerm, cPerm, ePerm, rPerm},
        };

        String[] perms = {"r", "c", "u", "d"};

        for(int x = 0; x < checkbox.length; x ++) {
            for(int i = 0; i < 4; i++) {
                JCheckBox checkb = checkbox[x][i];

                switch(x) {
                    case 0: //Bill
                        if(data.order != null && data.order.contains(perms[i])) checkb.setSelected(true);
                        break;
                    case 1: //Product
                        if(data.product != null && data.product.contains(perms[i])) checkb.setSelected(true);
                        break;
                    case 2: //Discount
                        if(data.discount != null && data.discount.contains(perms[i])) checkb.setSelected(true);
                        break;
                    case 3: //Customer
                        if(data.customer != null && data.customer.contains(perms[i])) checkb.setSelected(true);
                        break;
                    case 4: //Staff
                        if(data.staff != null && data.staff.contains(perms[i])) checkb.setSelected(true);
                        break;
                    case 5: //Perm
                        if(data.permission != null && data.permission.contains(perms[i])) checkb.setSelected(true);
                        break;
                    default: 
                        break;
                }
            }
        }
    }

    private void init() {
        this.setLocationRelativeTo(null);
    }

    private void handleSubmit() {
        try {
            PermissionModel permission = new PermissionModel();
            permission.name = txtName.getText();

            JCheckBox[][] checkbox = {
                {vBill, cBill, eBill, rBill},
                {vProduct, cProduct, eProduct, rProduct},
                {vDiscount, cDiscount, eDiscount, rDiscount},
                {vCustomer, cCustomer, eCustomer, rCustomer},
                {vStaff, cStaff, eStaff, rStaff},
                {vPerm, cPerm, ePerm, rPerm},
            };

            String[] perms = {"r", "c", "u", "d"};

            for(int x = 0; x < checkbox.length; x ++) {
                for(int i = 0; i < 4; i++) {
                    JCheckBox checkb = checkbox[x][i];
                    
                    if(checkb.isSelected()) {
                        switch(x) {
                            case 0: //Bill
                                permission.order += perms[i];
                                break;
                            case 1: //Product
                                permission.product += perms[i];
                                break;
                            case 2: //Discount
                                permission.discount += perms[i];
                                break;
                            case 3: //Customer
                                permission.customer += perms[i];
                                break;
                            case 4: //Staff
                                permission.staff += perms[i];
                                break;
                            case 5: //Perm
                                permission.permission += perms[i];
                                break;
                            default: 
                                break;
                        }
                    }
                }
            }

            if(this.editId.isEmpty()) { //Add new
                //Save to DB
                permissionController.add(permission);
    
                //*Emit to Socket */
                socket.emit("/permissions/add", permission.toDocument().toJson());
            }else{ //Update
                PermissionModel query = new PermissionModel();
                query._id = editId;

                //Save to DB
                permissionController.updateOne(query, permission);
    
                permission._id = editId;
                //*Emit to Socket */
                socket.emit("/permissions/update", permission.toDocument().toJson());
            }

            clearForm();
            this.dispose();
        }catch(Exception e) {
            Log.error(e);
        }
    }

    private void clearForm() {
        this.editId = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnExits = new duan1.components.Button();
        btnSave = new duan1.components.Button();
        rBill = new javax.swing.JCheckBox();
        vBill = new javax.swing.JCheckBox();
        cBill = new javax.swing.JCheckBox();
        eBill = new javax.swing.JCheckBox();
        vProduct = new javax.swing.JCheckBox();
        cProduct = new javax.swing.JCheckBox();
        eProduct = new javax.swing.JCheckBox();
        rProduct = new javax.swing.JCheckBox();
        vDiscount = new javax.swing.JCheckBox();
        cDiscount = new javax.swing.JCheckBox();
        eDiscount = new javax.swing.JCheckBox();
        rDiscount = new javax.swing.JCheckBox();
        vCustomer = new javax.swing.JCheckBox();
        cCustomer = new javax.swing.JCheckBox();
        eCustomer = new javax.swing.JCheckBox();
        rCustomer = new javax.swing.JCheckBox();
        cStaff = new javax.swing.JCheckBox();
        vStaff = new javax.swing.JCheckBox();
        eStaff = new javax.swing.JCheckBox();
        rStaff = new javax.swing.JCheckBox();
        vPerm = new javax.swing.JCheckBox();
        ePerm = new javax.swing.JCheckBox();
        rPerm = new javax.swing.JCheckBox();
        cPerm = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel6.setText("Thông Tin Cơ Bản");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel13.setText("Xoá");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 410, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 410, 10));

        jLabel14.setText("Tên phân quyền");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel15.setText("Phân quyền");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jLabel16.setText("Xem");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        jLabel17.setText("Tạo");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        jLabel18.setText("Sửa");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 130, 40, -1));

        jLabel19.setText("Đơn hàng");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel20.setText("Sản phẩm");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel21.setText("Khuyến mãi");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel22.setText("Khách hàng");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel23.setText("Nhân viên");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        btnExits.setText("Thoát");
        btnExits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitsMouseClicked(evt);
            }
        });
        jPanel1.add(btnExits, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, -1, -1));

        btnSave.setBackground(new java.awt.Color(0, 122, 255));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));
        jPanel1.add(rBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));
        jPanel1.add(vBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));
        jPanel1.add(cBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));
        jPanel1.add(eBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));
        jPanel1.add(vProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));
        jPanel1.add(cProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));
        jPanel1.add(eProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));
        jPanel1.add(rProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));
        jPanel1.add(vDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));
        jPanel1.add(cDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));
        jPanel1.add(eDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));
        jPanel1.add(rDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, -1, -1));
        jPanel1.add(vCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, -1));
        jPanel1.add(cCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, -1));
        jPanel1.add(eCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));
        jPanel1.add(rCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));
        jPanel1.add(cStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, -1, -1));
        jPanel1.add(vStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));
        jPanel1.add(eStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));
        jPanel1.add(rStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));
        jPanel1.add(vPerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));
        jPanel1.add(ePerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, -1, -1));
        jPanel1.add(rPerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, -1, -1));
        jPanel1.add(cPerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnExitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitsMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnExitsMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        this.handleSubmit();
    }//GEN-LAST:event_btnSaveMouseClicked

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
            java.util.logging.Logger.getLogger(PermissionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PermissionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PermissionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PermissionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PermissionEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.Button btnExits;
    private duan1.components.Button btnSave;
    private javax.swing.JCheckBox cBill;
    private javax.swing.JCheckBox cCustomer;
    private javax.swing.JCheckBox cDiscount;
    private javax.swing.JCheckBox cPerm;
    private javax.swing.JCheckBox cProduct;
    private javax.swing.JCheckBox cStaff;
    private javax.swing.JCheckBox eBill;
    private javax.swing.JCheckBox eCustomer;
    private javax.swing.JCheckBox eDiscount;
    private javax.swing.JCheckBox ePerm;
    private javax.swing.JCheckBox eProduct;
    private javax.swing.JCheckBox eStaff;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox rBill;
    private javax.swing.JCheckBox rCustomer;
    private javax.swing.JCheckBox rDiscount;
    private javax.swing.JCheckBox rPerm;
    private javax.swing.JCheckBox rProduct;
    private javax.swing.JCheckBox rStaff;
    private javax.swing.JTextField txtName;
    private javax.swing.JCheckBox vBill;
    private javax.swing.JCheckBox vCustomer;
    private javax.swing.JCheckBox vDiscount;
    private javax.swing.JCheckBox vPerm;
    private javax.swing.JCheckBox vProduct;
    private javax.swing.JCheckBox vStaff;
    // End of variables declaration//GEN-END:variables
}
