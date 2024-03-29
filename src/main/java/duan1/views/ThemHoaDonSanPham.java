/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import duan1.components.Cards;
import duan1.controllers.product.DeviceConfigurationController;
import duan1.controllers.product.DimensionController;
import duan1.controllers.product.DimensionPromotionController;
import duan1.controllers.product.ManufacturerController;
import duan1.controllers.product.ProductController;
import duan1.controllers.product.PromotionController;
import duan1.models.product.DeviceConfigurationModel;
import duan1.models.product.DimensionModel;
import duan1.models.product.DimensionPromotionModel;
import duan1.models.product.ManufacturerModel;
import duan1.models.product.ProductModel;
import duan1.models.product.PromotionModel;
import duan1.utils.Async;
import duan1.utils.Log;
import duan1.utils.NextImage;
import duan1.utils.Populate;
import duan1.utils.Text;
import duan1.utils.WrapLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.processing.Filer;
import javax.swing.JViewport;

import org.bson.Document;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author TAN PHAT
 */
public class ThemHoaDonSanPham extends javax.swing.JFrame {

    /**
     * Creates new form ThemHoaDonSanPham
     */

    Boolean searchWaiting = false;

    //* POPULATES */
    Populate<DeviceConfigurationModel> configPopulate = new Populate<>();
    Populate<ManufacturerModel> manufacturerPopulate = new Populate<>();

    //* CONTROLLERS */
    private DimensionController controller = new DimensionController();
    private DeviceConfigurationController deviceConfigController = new DeviceConfigurationController();
    private ManufacturerController manufacturerController = new ManufacturerController();
    private DimensionController dimensionController = new DimensionController();
    private DimensionPromotionController dimensionPromotionController = new DimensionPromotionController();
    private PromotionController promotionController = new PromotionController();
    
    //* DATA */
    private ArrayList<DimensionModel> arrProduct = new ArrayList<>();
    private ArrayList<DeviceConfigurationModel> deviceConfigs = new ArrayList<>();
    private ArrayList<ManufacturerModel> manufacturers = new ArrayList<>();

    //* VIEWS */
    private ProductFilter productFilterView = new ProductFilter();
    
    DecimalFormat vndFormat = new DecimalFormat("#,### đ");

    DimensionModel model = new DimensionModel();
    int index = 0;

    private ThemHoaDon hoaDonContext;

    public ThemHoaDonSanPham() {
        initComponents();
        this.setLocationRelativeTo(null);
        fetchData();
        drawcard();
        init();
    }

    //* PUBLIC */
    public void setHoaDonContext(ThemHoaDon hoaDonContext) {
        this.hoaDonContext = hoaDonContext;
    }

    //* PRIVATE */
    private void init() {
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane2.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);

        //Events
        productFilterView.onFilter(filter -> {
            filterProduct(filter);
            return null;
        });
    }

    void fetchData() {
        try {
            deviceConfigs = deviceConfigController.getAll();
            manufacturers = manufacturerController.getAll();
            arrProduct = controller.getAll();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    void drawcard() {
        PanelCard.setLayout(new WrapLayout());
        PanelCard.removeAll();
        PanelCard.repaint();

        arrProduct.forEach(data -> {
            Cards card = new Cards();
            card.setImg(data.banner);
            card.setName(data.name);
            card.setPrice(0.0);
            PanelCard.add(card);

            card.onClick(e -> {
                this.model = data;
                
                //* UPDATE TO UI */
                try {
                    lblImage.setIcon(new NextImage().load(data.banner, lblImage.getWidth(), lblImage.getHeight()));
                } catch (Exception e1) {
                    Log.error(e1);
                }

                lblName.setText(data.name);
                lblPrice.setText(vndFormat.format(data.price));
                lblStocks.setText("HẾT HÀNG");
                if(data.stocks != null) lblStocks.setText(data.stocks > 0 ? "CÒN HÀNG" : "HẾT HÀNG");
                lblDesc.setText(data.description);

                lblRam.setText(configPopulate.find(data.ram, deviceConfigs).value);
                lblPin.setText(configPopulate.find(data.pin, deviceConfigs).value);
                lblDisplay.setText(configPopulate.find(data.display, deviceConfigs).value);
                lblManufacturer.setText(manufacturerPopulate.find(data.manufacturer, manufacturers).title);
                lblRom.setText(configPopulate.find(data.rom, deviceConfigs).value);
                lblCamera.setText(configPopulate.find(data.camera, deviceConfigs).value);
                lblSim.setText(configPopulate.find(data.sim, deviceConfigs).value);
                lblReleaseYear.setText(data.releaseYear);

                return null;

            });

        });

        PanelCard.revalidate();
    }

    void opacity() {
        // txtSanPham.setBackground(new Color(0, 0, 0, 1));
    }

    private void searchProduct() {
        try {
            if(txtSearch.getText().isBlank()) {
                arrProduct = dimensionController.getAll();
            }else{
                arrProduct = dimensionController.search(txtSearch.getText());
            }

            drawcard();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    private void filterProduct(Document filter) {
        try {
            ArrayList<DimensionModel> dimensions = new ArrayList<DimensionModel>();
            dimensions = dimensionController.getAll();
            arrProduct.clear();

            for(DimensionModel dimension : dimensions) {
                if(dimension.manufacturer == null || !filter.getString("manufacturer").equals("all") 
                && !dimension.manufacturer.equals(filter.getString("manufacturer"))) continue;

                if(dimension.releaseYear == null || !filter.getString("releaseYear").equals("all") 
                && !dimension.releaseYear.equals(filter.getString("releaseYear"))) continue;

                if(dimension.display == null || !filter.getString("display").equals("all") 
                && !dimension.display.equals(filter.getString("display"))) continue;

                if(dimension.ram == null || !filter.getString("ram").equals("all") 
                && !dimension.ram.equals(filter.getString("ram"))) continue;

                if(dimension.rom == null || !filter.getString("rom").equals("all") 
                && !dimension.rom.equals(filter.getString("rom"))) continue;

                if(dimension.pin == null || !filter.getString("pin").equals("all") 
                && !dimension.pin.equals(filter.getString("pin"))) continue;

                if(dimension.camera == null || !filter.getString("camera").equals("all") 
                && !dimension.camera.equals(filter.getString("camera"))) continue;

                if(filter.getInteger("price") != 0) {
                    if(filter.getInteger("price") == 1 && dimension.price < 20000000) continue;
                    if(filter.getInteger("price") == 2 && dimension.price < 10000000 && dimension.price >= 20000000) continue;
                    if(filter.getInteger("price") == 3 && dimension.price < 1000000) continue;
                }

                arrProduct.add(dimension);
            }

            drawcard();
        } catch (Exception e) {
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

        panelBoder1 = new duan1.components.PanelBoder();
        jTextField5 = new javax.swing.JTextField();
        panelBoder4 = new duan1.components.PanelBoder();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelCard = new duan1.components.PanelBoder();
        panelBoder2 = new duan1.components.PanelBoder();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblStocks = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblReleaseYear = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblRam = new javax.swing.JLabel();
        lblPin = new javax.swing.JLabel();
        lblDisplay = new javax.swing.JLabel();
        lblManufacturer = new javax.swing.JLabel();
        lblRom = new javax.swing.JLabel();
        lblCamera = new javax.swing.JLabel();
        lblSim = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        btnExit = new duan1.components.Button();
        btnAdd = new duan1.components.Button();

        panelBoder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelBoder1Layout = new javax.swing.GroupLayout(panelBoder1);
        panelBoder1.setLayout(panelBoder1Layout);
        panelBoder1Layout.setHorizontalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        panelBoder1Layout.setVerticalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBoder4.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelCardLayout = new javax.swing.GroupLayout(PanelCard);
        PanelCard.setLayout(PanelCardLayout);
        PanelCardLayout.setHorizontalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        PanelCardLayout.setVerticalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(PanelCard);

        panelBoder4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 606, 730));

        panelBoder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Cấu Hình");
        panelBoder2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 275, 42));
        panelBoder2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 430, 17));
        panelBoder2.add(lblStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 280, 25));

        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDesc.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panelBoder2.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 440, 90));
        panelBoder2.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 140, 140));

        lblPrice.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        panelBoder2.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 280, 25));

        lblName.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        panelBoder2.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 280, 25));
        panelBoder2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 54, 430, 17));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Thông Tin Sản Phẩm");
        panelBoder2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 275, 42));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Mô Tả");
        panelBoder2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 275, 42));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel16.setText("ROM:");
        panelBoder2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, -1, -1));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel17.setText("Năm sản xuất:");
        panelBoder2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, -1, -1));

        lblReleaseYear.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblReleaseYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, -1, -1));

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel19.setText("PIN:");
        panelBoder2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, -1, -1));

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel20.setText("Camera:");
        panelBoder2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, -1, -1));

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel21.setText("Màn hình:");
        panelBoder2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, -1, -1));

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel22.setText("Sim:");
        panelBoder2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, -1, -1));

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel23.setText("Hãng sản xuất:");
        panelBoder2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, -1, -1));

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel24.setText("RAM:");
        panelBoder2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        lblRam.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        lblPin.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, -1, -1));

        lblDisplay.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, -1, -1));

        lblManufacturer.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblManufacturer, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, -1, -1));

        lblRom.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, -1, -1));

        lblCamera.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, -1, -1));

        lblSim.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        panelBoder2.add(lblSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, -1, -1));

        panelBoder4.add(panelBoder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 0, -1, -1));

        txtSearch.setPreferredSize(new java.awt.Dimension(78, 30));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        panelBoder4.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 15, 525, -1));

        jLabel10.setFont(new java.awt.Font("Ionicons", 0, 20)); // NOI18N
        jLabel10.setText("");
        panelBoder4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 18, -1, 29));

        btnFilter.setFont(new java.awt.Font("Ionicons", 0, 14)); // NOI18N
        btnFilter.setText("");
        btnFilter.setPreferredSize(new java.awt.Dimension(28, 28));
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        panelBoder4.add(btnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 15, -1, 32));

        btnExit.setText("Thoát");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        panelBoder4.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 740, -1, -1));

        btnAdd.setBackground(new java.awt.Color(0, 122, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm Vào Đơn Hàng");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        panelBoder4.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 740, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder4, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        productFilterView.setVisible(true);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        try {
            //ADD PRODCT
            hoaDonContext.addBillProduct(model, 1);
            this.dispose();
       } catch (Exception ex) {
           Logger.getLogger(ThemHoaDonSanPham.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(!searchWaiting) {
            Async.setTimeout(() -> {
                searchProduct();
                searchWaiting = false;
            }, 1000);
        }

        searchWaiting = true;
    }//GEN-LAST:event_txtSearchKeyPressed

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
            java.util.logging.Logger.getLogger(ThemHoaDonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemHoaDonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemHoaDonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemHoaDonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemHoaDonSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.PanelBoder PanelCard;
    private duan1.components.Button btnAdd;
    private duan1.components.Button btnExit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblCamera;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblManufacturer;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPin;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblRam;
    private javax.swing.JLabel lblReleaseYear;
    private javax.swing.JLabel lblRom;
    private javax.swing.JLabel lblSim;
    private javax.swing.JLabel lblStocks;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder2;
    private duan1.components.PanelBoder panelBoder4;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
