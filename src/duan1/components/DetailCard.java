/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.components;

import duan1.config.Config;
import duan1.utils.Log;

import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.util.function.Function;

import javax.swing.ImageIcon;

/**
 *
 * @author nhatsdevil
 */
public class DetailCard extends javax.swing.JPanel {
    private Function<Integer, Void> onDeleteFunc;

    /**
     * Creates new form DetailCard
     */
    public DetailCard() {
        initComponents();
        reinit();
    }

    private void reinit() {
        // Font iconFont = Config.FONT_IONICONS.deriveFont(20f);
        // deleteBtn.setFont(iconFont);
        // deleteBtn.setText("\uf4c5");
    }

    public void setImg(String imageurl) {
        try {
            URL urls = new URL(imageurl);
            banner.setIcon(ImageProduct(urls));
        } catch (Exception ex) {
            Log.error(ex);
        }
    }

    ImageIcon ImageProduct(URL src) {
        ImageIcon imacon = new ImageIcon(src);
        Image dadimage = imacon.getImage();
        Image modifiedDabImage = dadimage.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING);
        imacon = new ImageIcon(modifiedDabImage);
        
        return imacon;
    }

    public void setName(String name) {
        productNameLbl.setText(name);
    }

    public void setDescription(String desc) {
        countLbl.setText(desc);
    }

    //*EVENTS */
    public void onDelete(Function<Integer, Void> func) {
        this.onDeleteFunc = func;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        banner = new javax.swing.JLabel();
        productNameLbl = new javax.swing.JLabel();
        countLbl = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();

        banner.setBackground(new java.awt.Color(102, 102, 102));

        productNameLbl.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        productNameLbl.setText("Product Title");

        countLbl.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        countLbl.setText("x12 - 120.000");

        deleteBtn.setBackground(new java.awt.Color(255, 0, 51));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("T");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countLbl)
                    .addComponent(productNameLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(productNameLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(countLbl)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        this.onDeleteFunc.apply(0);
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JLabel countLbl;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel productNameLbl;
    // End of variables declaration//GEN-END:variables
}