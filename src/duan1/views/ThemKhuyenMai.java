/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import duan1.components.Cards;
import java.util.Collections;
import duan1.controllers.product.DimensionController;
import duan1.controllers.product.DimensionPromotionController;
import duan1.controllers.product.PromotionController;
import duan1.models.product.DimensionModel;
import duan1.models.product.DimensionPromotionModel;
import duan1.models.product.PromotionModel;
import duan1.utils.Async;
import duan1.utils.Log;
import duan1.utils.WrapLayout;
import io.socket.client.Socket;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

/**
 *
 * @author TAN PHAT
 */
class CartTable {

    public Integer count;
    public DimensionModel product;

    public CartTable(Integer count, DimensionModel product) {
        this.count = count;
        this.product = product;
    }

}

public class ThemKhuyenMai extends javax.swing.JFrame {

    /**
     * Creates new form ThemKhuyenMai
     */
    DimensionModel dimension = new DimensionModel();
    DimensionController controller = new DimensionController();
    //
    PromotionModel promotion = new PromotionModel();
    PromotionController promotionController = new PromotionController();
    DimensionPromotionController dimensionPromotionController = new DimensionPromotionController();
    //
    ArrayList<PromotionModel> arrPromotion = new ArrayList<>();
    ArrayList<DimensionModel> arrDimension = new ArrayList<>();
    ArrayList<DimensionModel> arrDimensionTable = new ArrayList<>(); // Array chứa sản phẩm thêm trong table
    DefaultTableModel model = new DefaultTableModel();

    private Boolean searchWaiting = false;

    //* VIEWS */
    private ProductFilter productFilterView = new ProductFilter();

    Socket socket;

    public ThemKhuyenMai() {
        initComponents();
        load();
        init();
        drawCard();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    void load() {
        try {
            arrDimension = controller.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ThemKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void init() {
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane2.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        this.setLocationRelativeTo(this);

        //Events
        productFilterView.onFilter(filter -> {
            filterProduct(filter);
            return null;
        });
    }

    void drawCard() {
        scollView.setLayout(new WrapLayout());
        scollView.removeAll();
        scollView.repaint();

        arrDimension.forEach(data -> {
            Cards card = new Cards();
            card.setImg(data.banner);
            card.setName(data.name);
            card.setPrice(0.0);
            scollView.add(card);

            card.onClick(e -> {
                model = (DefaultTableModel) tblSanPham.getModel();
                model.setRowCount(0);

                dimension.name = data.name;
                arrDimensionTable.add(data);
                arrDimensionTable.forEach(row -> {
                    model.addRow(new Object[]{row.name});
                });

                return null;
            });

        });

        scollView.revalidate();
    }

    void submitPromotion() {
        try {
            model = (DefaultTableModel) tblSanPham.getModel();

            promotion.title = txtMaKhuyenMai.getText();
            promotion.startDate = txtNgayKetThuc.getText();
            promotion.endDate = txtNgayKetThuc.getText();
            promotion.percent = Double.parseDouble(txtGiamGia.getText());
            promotion.points = Double.parseDouble(txtDiemThanhVien.getText());
            promotionController.add(promotion);
            
            //
            PromotionModel promotionModel = new PromotionModel();
            promotionModel = promotionController.get(promotion);

            for (DimensionModel data : arrDimensionTable) {
                DimensionPromotionModel DPM = new DimensionPromotionModel();

                DPM.dimension = data._id;
                DPM.promotion = promotionModel._id;

                dimensionPromotionController.add(DPM);
            }

            //*Emit to Socket
            socket.emit("/promotions/add", promotion.toJson());

            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(ThemKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void searchProduct() {
        try {
            if(txtSearch.getText().isBlank()) {
                arrDimension = controller.getAll();
            }else{
                arrDimension = controller.search(txtSearch.getText());
            }

            drawCard();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    private void filterProduct(Document filter) {
        try {
            ArrayList<DimensionModel> dimensions = new ArrayList<DimensionModel>();
            dimensions = controller.getAll();
            arrDimension.clear();

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

                arrDimension.add(dimension);
            }

            drawCard();
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

        panelBoder2 = new duan1.components.PanelBoder();
        panelBoder1 = new duan1.components.PanelBoder();
        btnFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scollView = new duan1.components.PanelBoder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        panelBoder4 = new duan1.components.PanelBoder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiemThanhVien = new javax.swing.JTextField();
        txtNgayBatDau = new javax.swing.JTextField();
        btnAdd = new duan1.components.Button();
        btnExit = new duan1.components.Button();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        javax.swing.GroupLayout panelBoder2Layout = new javax.swing.GroupLayout(panelBoder2);
        panelBoder2.setLayout(panelBoder2Layout);
        panelBoder2Layout.setHorizontalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelBoder2Layout.setVerticalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelBoder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFilter.setFont(new java.awt.Font("Ionicons", 0, 14)); // NOI18N
        btnFilter.setText("");
        btnFilter.setPreferredSize(new java.awt.Dimension(28, 28));
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        panelBoder1.add(btnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, 32));

        scollView.setBackground(new java.awt.Color(255, 255, 255));
        scollView.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scollView.setPreferredSize(new java.awt.Dimension(500, 718));

        javax.swing.GroupLayout scollViewLayout = new javax.swing.GroupLayout(scollView);
        scollView.setLayout(scollViewLayout);
        scollViewLayout.setHorizontalGroup(
            scollViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );
        scollViewLayout.setVerticalGroup(
            scollViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(scollView);

        panelBoder1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 71, -1, 680));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        panelBoder1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 429, 577, 262));

        panelBoder4.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        panelBoder4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm Khuyến Mãi");
        panelBoder4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 2, 335, 43));

        jLabel2.setText("Tên Khuyến Mãi");
        panelBoder4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 125, 30));
        panelBoder4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 45, 587, 10));

        jLabel3.setText("Ngày Bắt Đầu");
        panelBoder4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 125, 30));

        jLabel4.setText("Ngày Kết Thúc");
        panelBoder4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 125, 30));

        jLabel5.setText("Giảm Giá");
        panelBoder4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 125, 30));

        jLabel6.setText("Chọn Sản Phẩm");
        panelBoder4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 375, 125, 40));
        panelBoder4.add(txtMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 360, 29));
        panelBoder4.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 360, 29));
        panelBoder4.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 360, 29));

        jLabel7.setText("Điểm Thành Viên");
        panelBoder4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 125, 30));
        panelBoder4.add(txtDiemThanhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 360, 29));
        panelBoder4.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 360, 29));

        panelBoder1.add(panelBoder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 0, -1, 423));

        btnAdd.setBackground(new java.awt.Color(0, 122, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm Khuyến Mãi");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        panelBoder1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(956, 703, -1, -1));

        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        panelBoder1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 703, -1, -1));

        jLabel10.setFont(new java.awt.Font("Ionicons", 0, 20)); // NOI18N
        jLabel10.setText("");
        panelBoder1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 29));

        txtSearch.setPreferredSize(new java.awt.Dimension(78, 30));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        panelBoder1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 400, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.submitPromotion();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if(!searchWaiting) {
            Async.setTimeout(() -> {
                searchProduct();
                searchWaiting = false;
            }, 1000);
        }

        searchWaiting = true;
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        productFilterView.setVisible(true);
    }//GEN-LAST:event_btnFilterActionPerformed

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
            java.util.logging.Logger.getLogger(ThemKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemKhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.Button btnAdd;
    private duan1.components.Button btnExit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder2;
    private duan1.components.PanelBoder panelBoder4;
    private duan1.components.PanelBoder scollView;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiemThanhVien;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
