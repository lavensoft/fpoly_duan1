/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;
import duan1.utils.ShadowRenderer;
import java.awt.geom.Area;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import duan1.utils.NextImage;

/**
 *
 * @author TAN PHAT
 */


class PopClickListener extends MouseInputAdapter {
    Function<Integer, Void> _editEvent;
    Function<Integer, Void> _deleteEvent;

    PopClickListener(Function<Integer, Void> onEdit, Function<Integer, Void> onDelete) {
        _editEvent = onEdit;
        _deleteEvent = onDelete;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        ArrayList<Map<String, String>> menuItems = new ArrayList<Map<String, String>>();
        Map<String, Function<Integer, Void>> events = new HashMap<>();
        
        //*ADD ITEMS */
        Map<String, String> editItem = new HashMap<String, String>();
        editItem.put("title", "Chỉnh sửa");
        editItem.put("key", "edit");
        
        Map<String, String> deleteItem = new HashMap<String, String>();
        deleteItem.put("title", "Xoá");
        deleteItem.put("key", "delete");

        
        menuItems.add(editItem);
        menuItems.add(deleteItem);

        events.put("edit", _editEvent);
        events.put("delete", _deleteEvent);

        ContextMenu menu = new ContextMenu(menuItems, events);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

public class Cards extends javax.swing.JPanel {
    private Function<Integer, Void> _onClickFunc = null;
    private Function<Integer, Void> contextMenuEditEvent;
    private Function<Integer, Void> contextMenuDeleteEvent;
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    private Color shadowColor = new Color(170, 170, 170);
    public String _id;

    /**
     * Creates new form Cards
     */
    public Cards() {
        initComponents();
        setOpaque(false);
        init();
    }

    private void init() {
        createImageShadow();
    }

    private void addMouseListener() {
        PopClickListener contextMenuEvent = new PopClickListener(contextMenuEditEvent, contextMenuDeleteEvent);
        this.addMouseListener(contextMenuEvent);
    }

    public void onEdit(Function<Integer, Void> func) {
        contextMenuEditEvent = func;
        addMouseListener();
    }

    public void onDelete(Function<Integer, Void> func) {
        contextMenuDeleteEvent = func;
        addMouseListener();
    }

    public void onClick(Function<Integer, Void> func) {
        this._onClickFunc = func;
    }

    public void _onClick() {
        _onClickFunc.apply(0);
    }

    public void setImg(String imageurl) {
        try {
            ImgLable.setIcon(new NextImage().load(imageurl, 150, 130));
        } catch (Exception ex) {
            Logger.getLogger(Cards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setName(String name) {
        ProductTitle.setText(name);
    }

    public void setPrice(Double price) {
        // ProductPrice.setText(price.toString());
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            g2.drawImage(createShadow(), 0, 0, null);
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, 15, 15));
        g2.dispose();
        return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;

        //  Create Shadow Image
        g2.drawImage(imageShadow, 0, 0, null);

        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, 15, 15));
        g2.fill(area);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImgLable = new javax.swing.JLabel();
        ProductPrice = new javax.swing.JLabel();
        ProductTitle = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        ProductPrice.setText("Còn hàng");

        ProductTitle.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImgLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProductPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ImgLable, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ProductTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProductPrice)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        this.setBackground(new Color(64, 64, 64));
    }//GEN-LAST:event_formMouseEntered

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(SwingUtilities.isLeftMouseButton(evt)) _onClick();
    }//GEN-LAST:event_formMousePressed

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        this.setBackground(new Color(217, 217, 217));
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImgLable;
    private javax.swing.JLabel ProductPrice;
    private javax.swing.JLabel ProductTitle;
    // End of variables declaration//GEN-END:variables
}
