/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import duan1.config.*;
import duan1.controllers.user.UserController;
import duan1.models.user.UserModel;
import duan1.utils.SocketIO;
import io.socket.client.Socket;

/**
 *
 * @author TAN PHAT
 */
public class Main extends javax.swing.JFrame {
    SocketIO io = new SocketIO();
    Socket socket = io.socket;
    
    //*CONTROLLERS */
    private UserController userController = new UserController();

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
    }
    
    void closeAll(){
        PnKhachHang.setVisible(false);
        PnKhuyenMai.setVisible(false);
        PnNhanVien.setVisible(false);
        PnSanPham.setVisible(false);
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
        // sanPham2 = new duan1.views.SanPham(socket);
        khachHang1 = new duan1.views.KhachHang();
        // sanPham3 = new duan1.views.SanPham(socket);
        jLabel1 = new javax.swing.JLabel();
        panelBoder3 = new duan1.components.PanelBoder();
        panelBoder2 = new duan1.components.PanelBoder();
        jSeparator1 = new javax.swing.JSeparator();
        menu1 = new duan1.components.Menu();
        panelBoder5 = new duan1.components.PanelBoder();
        jLabel3 = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblKhuyenMai = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblNhanVien = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        menu3 = new duan1.components.Menu();
        PnSanPham = new duan1.views.SanPham(socket);
        PnNhanVien = new duan1.views.NhanVien();
        PnKhuyenMai = new duan1.views.KhuyenMai();
        PnKhachHang = new duan1.views.KhachHang();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout khachHang1Layout = new javax.swing.GroupLayout(khachHang1);
        khachHang1.setLayout(khachHang1Layout);
        khachHang1Layout.setHorizontalGroup(
            khachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        khachHang1Layout.setVerticalGroup(
            khachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout panelBoder3Layout = new javax.swing.GroupLayout(panelBoder3);
        panelBoder3.setLayout(panelBoder3Layout);
        panelBoder3Layout.setHorizontalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelBoder3Layout.setVerticalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelBoder2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBoder2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 222, -1, 160));
        panelBoder2.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, -10, -1, 580));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBoder5.setBackground(new java.awt.Color(160, 160, 160));
        panelBoder5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Hóa Đơn");
        panelBoder5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 240, 30));

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setText("Sản Phẩm");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });
        panelBoder5.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 240, 30));

        lblKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        lblKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhuyenMai.setText("Khuyến Mãi");
        lblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhuyenMaiMouseClicked(evt);
            }
        });
        panelBoder5.add(lblKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 240, 30));

        lblLogout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(255, 255, 255));
        lblLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogout.setText("Logout");
        lblLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });
        panelBoder5.add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 240, 30));
        panelBoder5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 250, 10));

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setText("Nhân Viên");
        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
        });
        panelBoder5.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 240, 30));

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachHang.setText("Khách Hàng");
        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseClicked(evt);
            }
        });
        panelBoder5.add(lblKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 240, 30));
        panelBoder5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 250, 10));
        panelBoder5.add(menu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        panelBoder5.add(PnSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 830, 720));
        panelBoder5.add(PnNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 830, 720));
        panelBoder5.add(PnKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 830, 720));
        panelBoder5.add(PnKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 830, 720));

        getContentPane().add(panelBoder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        try {
            UserController.logout();

            new Login().setVisible(true);
            this.dispose();
        } catch (BackingStoreException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void lblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseClicked
        // TODO add your handling code here:
        closeAll();
        PnKhachHang.setVisible(true);
    }//GEN-LAST:event_lblKhachHangMouseClicked

    private void lblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        closeAll();
        PnKhuyenMai.setVisible(true);
    }//GEN-LAST:event_lblKhuyenMaiMouseClicked

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        // TODO add your handling code here:
        closeAll();
        PnNhanVien.setVisible(true);
    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        // TODO add your handling code here:
        closeAll();
        PnSanPham.setVisible(true);
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        closeAll();

        //Check USER Login
        try {
            userController.checkLogin();
        }catch(Exception e) {
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_formWindowOpened

    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.views.KhachHang PnKhachHang;
    private duan1.views.KhuyenMai PnKhuyenMai;
    private duan1.views.NhanVien PnNhanVien;
    private duan1.views.SanPham PnSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private duan1.views.KhachHang khachHang1;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private duan1.components.Menu menu1;
    private duan1.components.Menu menu3;
    private duan1.components.PanelBoder panelBoder2;
    private duan1.components.PanelBoder panelBoder3;
    private duan1.components.PanelBoder panelBoder5;
    private duan1.views.SanPham sanPham2;
    private duan1.views.SanPham sanPham3;
    // End of variables declaration//GEN-END:variables
}
