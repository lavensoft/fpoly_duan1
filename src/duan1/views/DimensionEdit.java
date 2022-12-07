/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import duan1.controllers.product.DeviceConfigurationController;
import duan1.controllers.product.DimensionController;
import duan1.controllers.product.ProductController;
import duan1.models.product.DeviceConfigurationModel;
import duan1.models.product.DimensionConfigurationModel;
import duan1.models.product.DimensionModel;
import duan1.models.product.ProductModel;
import duan1.utils.Log;
import duan1.utils.NextImage;
import io.socket.client.Socket;

/**
 *
 * @author nhatsdevil
 */
public class DimensionEdit extends javax.swing.JFrame {
    private Socket socket;
    private File productImage;
    private String parentProduct;

    //* CONTROLLERS */
    private DeviceConfigurationController deviceConfigController = new DeviceConfigurationController();
    private DimensionController dimensionController = new DimensionController();
    private ProductController productController = new ProductController();

    //* DATA */
    private ArrayList<DeviceConfigurationModel> deviceConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> ramConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> romConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> pinConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> cameraConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> displayConfigs = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> simConfigs = new ArrayList<>();

    /**
     * Creates new form SanPhamEdit
     */
    public DimensionEdit() {
        initComponents();
        fetchData();
        init();
    }

    //* PUBLIC */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setParentProduct(String productId) {
        this.parentProduct = productId;
    }

    //* PRIVATE */
    private void init() {
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(810, 493));

        //* Set select value */
        selectRam.removeAllItems();
        selectRom.removeAllItems();
        selectPin.removeAllItems();
        selectCamera.removeAllItems();
        selectDisplay.removeAllItems();
        selectSim.removeAllItems();

        deviceConfigs.forEach(item -> {
            switch(item.key) {
                case "ram" : 
                    ramConfigs.add(item);
                    selectRam.addItem(item.value); 
                    break;
                case "rom" : 
                    romConfigs.add(item);
                    selectRom.addItem(item.value); 
                    break;
                case "pin" : 
                    pinConfigs.add(item);
                    selectPin.addItem(item.value); 
                    break;
                case "camera" :
                    cameraConfigs.add(item); 
                    selectCamera.addItem(item.value); 
                    break;
                case "display" : 
                    displayConfigs.add(item);
                    selectDisplay.addItem(item.value); 
                    break;
                case "sim" : 
                    simConfigs.add(item);
                    selectSim.addItem(item.value); 
                    break;
                default : 
                    break;
            }
        });
    }

    private void fetchData() {
        try {
            deviceConfigs = deviceConfigController.getAll();
        } catch (Exception e) {
            Log.error(e);
        }
    }

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

    private void submitDimension() {
        try {
            //Get parent product data
            ProductModel parentProductData = new ProductModel();
            parentProductData._id = parentProduct;
            parentProductData = productController.get(parentProductData);

            //Create dimension
            DimensionModel dimension = new DimensionModel();
            dimension.name = txtName.getText();
            dimension.description = txtDesc.getText();
            dimension.price = Double.parseDouble(txtPrice.getText());
            dimension.salePrice = 0.0;
            dimension.banner = productImage.getAbsolutePath();
            dimension.stocks = Integer.parseInt(txtStocks.getText());
            dimension.product = parentProduct;
            dimension.ram = ramConfigs.get(selectRam.getSelectedIndex())._id;
            dimension.rom = romConfigs.get(selectRom.getSelectedIndex())._id;
            dimension.pin = pinConfigs.get(selectPin.getSelectedIndex())._id;
            dimension.camera = cameraConfigs.get(selectCamera.getSelectedIndex())._id;
            dimension.display = displayConfigs.get(selectDisplay.getSelectedIndex())._id;
            dimension.sim = simConfigs.get(selectSim.getSelectedIndex())._id;
            dimension.manufacturer = parentProductData.manufacturer;
            dimension.releaseYear = parentProductData.releaseYear;

            dimensionController.add(dimension);

            //*Emit to Socket
            socket.emit("/products/dimension/add", dimension.toJson());

            this.dispose();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
            Log.error(e);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btnExit = new duan1.components.Button();
        btnAdd = new duan1.components.Button();
        jLabel14 = new javax.swing.JLabel();
        selectRom = new javax.swing.JComboBox<>();
        selectSim = new javax.swing.JComboBox<>();
        selectRam = new javax.swing.JComboBox<>();
        selectPin = new javax.swing.JComboBox<>();
        selectCamera = new javax.swing.JComboBox<>();
        selectDisplay = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jLabel2.setText("Thông Tin Cấu Hình");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel3.setText("ROM");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

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

        jLabel8.setText("Camera");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        jLabel9.setText("RAM");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel10.setText("Sim");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        jLabel11.setText("Pin");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 290, 350));

        jLabel13.setText("Tên thiết bị");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        btnExit.setText("Thoát");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, -1, -1));

        btnAdd.setBackground(new java.awt.Color(0, 122, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm Sản Phẩm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, -1, -1));

        jLabel14.setText("Màn hình");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        selectRom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 190, -1));

        selectSim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 190, -1));

        selectRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, -1));

        selectPin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 190, -1));

        selectCamera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 190, -1));

        selectDisplay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(selectDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 190, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStocksActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        this.uploadImage();
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        this.submitDimension();
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnExitMouseClicked

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
            java.util.logging.Logger.getLogger(DimensionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DimensionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DimensionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DimensionEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DimensionEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.Button btnAdd;
    private duan1.components.Button btnExit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JComboBox<String> selectCamera;
    private javax.swing.JComboBox<String> selectDisplay;
    private javax.swing.JComboBox<String> selectPin;
    private javax.swing.JComboBox<String> selectRam;
    private javax.swing.JComboBox<String> selectRom;
    private javax.swing.JComboBox<String> selectSim;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStocks;
    // End of variables declaration//GEN-END:variables
}
