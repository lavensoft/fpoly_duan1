/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.views;

import duan1.components.Cards;
import duan1.config.Config;
import duan1.controllers.product.DimensionController;
import duan1.controllers.product.ProductController;
import duan1.models.product.DimensionModel;
import duan1.models.product.ProductModel;
import duan1.utils.Log;
import duan1.utils.SocketIO;
import duan1.utils.WrapLayout;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.emitter.Emitter.Listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.Icon;

import org.bson.Document;

/**
 *
 * @author TAN PHAT
 */

public class SanPham extends javax.swing.JPanel {
    //* CONTROLLERS */
    private ProductController productController = new ProductController();
    private DimensionController dimensionController = new DimensionController();

    //* VARIABLES */
    private Socket socket;
    private ArrayList<ProductModel> arrProduct = new ArrayList<>();
    // public ArrayList<JPanel> arr = new ArrayList<>();
    private ArrayList<DimensionModel> arrDimension = new ArrayList<>();
    private boolean _loadDimensions = false;
    private String _dimensionProduct = "";

    public SanPham() {
        initComponents();
        revalidateComponents();
        setOpaque(false);
        load();
        drawCard();
    }

    void revalidateComponents() {
        //*CUSTOM INIT */
        //Header bar
        panelBoder3.setOpaque(true);
        panelBoder3.setBackground(new Color(0,0,0,50));

        //Add default scrollview padding
        PanelCard.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(200, 10, 10, 10), new EtchedBorder()));

        //Set button icon
        Font btnFont = Config.FONT_IONICONS.deriveFont(15f);

        btnThem.setFont(btnFont);
        btnThem.setText("\uf102");
    }

    void setSocket(Socket socket) {
        this.socket = socket;

        initSocket();
    }
    
    void drawCard(){
        // PanelCard.setLayout(new GridLayout(rows(), 4, 50, 15));
        PanelCard.setLayout(new WrapLayout(0, 24, 24));
        PanelCard.setSize(775, 455);
        PanelCard.setBorder(BorderFactory.createEmptyBorder());

        Log.info("RENDERING PRODUCT CARDS...", SanPham.class.getName());

        //Clear exsits cards
        PanelCard.removeAll();
        
        if(!_loadDimensions) { //PRIMARY
            arrProduct.forEach(data -> {
                Cards card = new Cards();
                card.setImg(data.banner);
                card.setName(data.name);
                card.setPrice(0.0);
                card.addContainerListener(null);
                
                //CARD CLICK
                card.onClick(e -> {
                    _loadDimensions = true;
                    _dimensionProduct = data._id;

                    Log.info("CLICKED ON PRODUCT: " + data._id, SanPham.class.getName());
    
                    DimensionModel query = new DimensionModel();
                    query.product = data._id;
    
                    try {
                        arrDimension = dimensionController.getAll(query);
    
                        drawCard();
                    } catch (Exception err) {
                        Log.error(err);
                    }

                    return null;
                });

                //CARD EDIT
                card.onEdit(e -> {
                    System.out.println("EDIT");
                    return null;
                });

                //CARD DELETE
                card.onDelete(e -> {
                    ProductModel query = new ProductModel();
                    query._id = data._id;

                    try {
                        productController.delete(query);

                        //Redraw UI
                        PanelCard.remove(card);
                        PanelCard.revalidate(); //Redraw component
                    } catch (Exception err) {
                        Log.error(err);
                    }
                    
                    return null;
                });

                //Render to UI
                card.setSize(new Dimension(150, 200));
                card.setBackground(new Color(217, 217, 217));
                PanelCard.add(card);
            });       
        }else{//DIMENSION
            arrDimension.forEach(data -> {
                Cards card = new Cards();
                card.setImg(data.banner);
                card.setName(data.name);
                card.setPrice(0.0);
                card.addContainerListener(null);

                //CARD DELETE
                card.onDelete(e -> {
                    DimensionModel query = new DimensionModel();
                    query._id = data._id;

                    try {
                        dimensionController.delete(query);

                        //Redraw UI
                        PanelCard.remove(card);
                        PanelCard.revalidate(); //Redraw component
                    } catch (Exception err) {
                        Log.error(err);
                    }
                    
                    return null;
                });

                //Render to UI
                card.setBackground(new Color(217, 217, 217));
                PanelCard.add(card);
            });         
        }

        Log.success("RENDERING PRODUCT CARDS SUCCESSFULLY", SanPham.class.getName());
    }
    
    
    // int rows(){
    //     int row = arrProduct.size() /4;
    //     if(row %2==0){
    //         return row;
    //     }
    //     else{
    //         return row+1;
    //     }
    // }
    
    
    void load(){
        try {
            arrProduct = productController.getAll();
            Collections.reverse(arrProduct); //Sort to newest
        } catch (Exception e) {
        }
    }

    //*SOCKET HANDLERS */
    void initSocket() {
        socket.on("/products/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                ProductModel product = new ProductModel();

                Document data = new Document();
                data = data.parse((String) args[0]);
                
                product.fromDocument(data);

                arrProduct.add(0, product);

                //Rerender Card
                drawCard();
            }
        });

        socket.on("/products/dimension/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                DimensionModel dimension = new DimensionModel();

                Document data = new Document();
                data = data.parse((String) args[0]);
                
                dimension.fromDocument(data);

                arrDimension.add(0, dimension);

                System.out.println(arrDimension.size());

                //Rerender Card
                drawCard();
            }
        });
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
        menu1 = new duan1.components.Menu();
        panelBoder1 = new duan1.components.PanelBoder();
        panelBoder3 = new duan1.components.PanelBoder();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelCard = new duan1.components.PanelBoder();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setOpaque(false);

        panelBoder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBoder3.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setText("T");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBoder3Layout = new javax.swing.GroupLayout(panelBoder3);
        panelBoder3.setLayout(panelBoder3Layout);
        panelBoder3Layout.setHorizontalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoder3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panelBoder3Layout.setVerticalGroup(
            panelBoder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        panelBoder1.add(panelBoder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelCardLayout = new javax.swing.GroupLayout(PanelCard);
        PanelCard.setLayout(PanelCardLayout);
        PanelCardLayout.setHorizontalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        PanelCardLayout.setVerticalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(PanelCard);

        panelBoder1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 806, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        new ThemSanPham(socket, _dimensionProduct).setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.PanelBoder PanelCard;
    private javax.swing.JButton btnThem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private duan1.components.Menu menu1;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder3;
    // End of variables declaration//GEN-END:variables
}
