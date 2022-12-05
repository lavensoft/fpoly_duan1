/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import duan1.utils.Log;
import duan1.utils.NextImage;
import io.socket.client.Socket;

/**
 *
 * @author nhatsdevil
 */
public class SanPhamEdit extends javax.swing.JFrame {
    private Socket socket;
    private File productImage;

    /**
     * Creates new form SanPhamEdit
     */
    public SanPhamEdit() {
        initComponents();
        init();
    }

    //* PUBLIC */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void init() {
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(810, 493));
    }

    //* PRIVATE */
    private void uploadImage() {
        try {
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.showOpenDialog(fileDialog);

            productImage = fileDialog.getSelectedFile();

            lblImage.setIcon(new NextImage().load(productImage.getAbsolutePath(), lblImage.getWidth(), lblImage.getHeight()));
        }catch(Exception e) {
            Log.error(e);
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtStocks = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtRom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtCamera = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        txtSim = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btnExit = new duan1.components.Button();
        btnAdd = new duan1.components.Button();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDisplay1 = new javax.swing.JTextField();
        selectDate = new javax.swing.JComboBox<>();
        selectManufacturer = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage.setBackground(new java.awt.Color(153, 153, 153));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("Thêm Ảnh");
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });
        getContentPane().add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 19, 128, 128));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel2.setText("Thông Tin Cơ Bản");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel3.setText("ROM");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        txtStocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStocksActionPerformed(evt);
            }
        });
        getContentPane().add(txtStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 130, -1));

        jLabel4.setText("Số lượng");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 280, -1));

        txtRom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRomActionPerformed(evt);
            }
        });
        getContentPane().add(txtRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 200, -1));

        jLabel5.setText("Giá");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel6.setText("Thông Tin Cơ Bản");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 400, 10));

        jLabel7.setText("Mô tả");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 130, -1));

        txtCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCameraActionPerformed(evt);
            }
        });
        getContentPane().add(txtCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 200, -1));

        jLabel8.setText("Camera");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, -1));

        jLabel9.setText("RAM");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel10.setText("Sim");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, -1, -1));

        txtRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRamActionPerformed(evt);
            }
        });
        getContentPane().add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, -1));

        txtSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSimActionPerformed(evt);
            }
        });
        getContentPane().add(txtSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 200, -1));

        jLabel11.setText("Pin");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinActionPerformed(evt);
            }
        });
        getContentPane().add(txtPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 190, -1));

        jLabel12.setText("Năm ra mắt");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, -1, -1));

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 290, 420));

        jLabel13.setText("Tên thiết bị");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        btnExit.setText("Thoát");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, -1, -1));

        btnAdd.setBackground(new java.awt.Color(0, 122, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm Sản Phẩm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, -1, -1));

        jLabel14.setText("Màn hình");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel15.setText("Hãng sản xuất");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        txtDisplay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisplay1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtDisplay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 190, -1));

        selectDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, 200, -1));

        selectManufacturer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectManufacturer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStocksActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRomActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCameraActionPerformed

    private void txtRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRamActionPerformed

    private void txtSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSimActionPerformed

    private void txtPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        this.uploadImage();
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnExitMouseClicked

    private void txtDisplay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisplay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisplay1ActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.Button btnAdd;
    private duan1.components.Button btnExit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JComboBox<String> selectDate;
    private javax.swing.JComboBox<String> selectManufacturer;
    private javax.swing.JTextField txtCamera;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtDisplay1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtSim;
    private javax.swing.JTextField txtStocks;
    // End of variables declaration//GEN-END:variables
}
