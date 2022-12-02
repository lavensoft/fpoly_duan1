/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.views;

import duan1.models.product.DimensionModel;
import duan1.models.product.ProductModel;
import duan1.utils.WrapLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TAN PHAT
 */
public class HoaDon extends javax.swing.JPanel {

    /**
     * Creates new form HoaDon
     */
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<DimensionModel> billProducts = new ArrayList<>();

    public HoaDon() {
        initComponents();
        setOpaque(false);
        reinit();
    }

    private void reinit() {
        billTotalPanel.setLayout(new WrapLayout());
        billTotalPanel.add(new JLabel("Test"));
        billTotalPanel.add(new JLabel("Test"));
        billTotalPanel.add(new JLabel("Test"));
        billTotalPanel.add(new JLabel("Test"));
        billTotalPanel.add(new JLabel("Test"));
    }

    public void loadTable() {
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        billProducts.forEach(data -> {
            model.addRow(new Object[]{data.name});
        });
    }

    public void addBillProduct(DimensionModel dimension) {
        billProducts.add(dimension);
        
        loadTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu1 = new duan1.components.Menu();
        panelBoder1 = new duan1.components.PanelBoder();
        panelBoder2 = new duan1.components.PanelBoder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        panelBoder3 = new duan1.components.PanelBoder();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        billTotalView = new javax.swing.JScrollPane();
        billTotalPanel = new duan1.components.PanelBoder();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        panelBoder2.setBackground(new java.awt.Color(153, 153, 153));
        panelBoder2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        ));
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout panelBoder2Layout = new javax.swing.GroupLayout(panelBoder2);
        panelBoder2.setLayout(panelBoder2Layout);
        panelBoder2Layout.setHorizontalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelBoder2Layout.setVerticalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        );

        panelBoder3.setBackground(new java.awt.Color(153, 153, 153));
        panelBoder3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Thanh Toán");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hóa Đơn");

        jLabel2.setText("Thành Tiền");

        billTotalPanel.setLayout(new java.awt.FlowLayout());
        billTotalView.setViewportView(billTotalPanel);

        javax.swing.GroupLayout panelBoder3Layout = new javax.swing.GroupLayout(panelBoder3);
        panelBoder3.setLayout(panelBoder3Layout);
        panelBoder3Layout.setHorizontalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addGroup(panelBoder3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoder3Layout.createSequentialGroup()
                .addGroup(panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(panelBoder3Layout.createSequentialGroup()
                        .addComponent(billTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1))
        );
        panelBoder3Layout.setVerticalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoder3Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(billTotalView))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hãng", "SamSung", "Apple", "Xiaomi", "Realmi", "Oppo" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá", " " }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ram" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rom" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText("Them");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");

        javax.swing.GroupLayout panelBoder1Layout = new javax.swing.GroupLayout(panelBoder1);
        panelBoder1.setLayout(panelBoder1Layout);
        panelBoder1Layout.setHorizontalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder1Layout.createSequentialGroup()
                .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBoder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoder1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBoder1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBoder1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(45, 45, 45)
                                .addComponent(jButton3)))
                        .addGap(18, 18, 18)))
                .addComponent(panelBoder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBoder1Layout.setVerticalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder1Layout.createSequentialGroup()
                .addComponent(panelBoder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(257, 257, 257))
            .addComponent(panelBoder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ThemHoaDonSanPham themHD = new ThemHoaDonSanPham();
        themHD.setHoaDonContext(this);
        themHD.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.PanelBoder billTotalPanel;
    private javax.swing.JScrollPane billTotalView;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private duan1.components.Menu menu1;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder2;
    private duan1.components.PanelBoder panelBoder3;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
