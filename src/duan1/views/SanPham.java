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

public class SanPham extends View{
    //* CONTROLLERS */
    private ProductController productController = new ProductController();
    private DimensionController dimensionController = new DimensionController();

    //* VARIABLES */
    private ArrayList<ProductModel> arrProduct = new ArrayList<>();
    // public ArrayList<JPanel> arr = new ArrayList<>();
    private ArrayList<DimensionModel> arrDimension = new ArrayList<>();
    private boolean _loadDimensions = false;
    private String _dimensionProduct = "";

    //* VIEWS */
    private SanPhamEdit sanPhamEditView = new SanPhamEdit();

    public SanPham() {
        initComponents();
        revalidateComponents();
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
        // Font btnFont = Config.FONT_IONICONS.deriveFont(15f);

        // btnThem.setFont(btnFont);
        // btnThem.setText("\uf102");

        //Header bar
        headerBar1.setTitle("Sản Phẩm");
    }

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;

        sanPhamEditView.setSocket(socket);
        initSocket();
    }
    
    
    void load(){
        try {
            arrProduct = productController.getAll();
            Collections.reverse(arrProduct); //Sort to newest
        } catch (Exception e) {
        }
    }

    //*SOCKET HANDLERS */
    @Override
    public void initSocket() {
        socket.on("/products/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                ProductModel product = new ProductModel();

                Document data = new Document();
                data = data.parse((String) args[0]);
                
                product.fromDocument(data);

                arrProduct.add(0, product);

                //Render card
                Cards card = addProductCard(product._id, product.banner, product.name, 0.0);

                PanelCard.add(card, 0);
                PanelCard.revalidate();
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

                //Render card
                Cards card = addProductCard(dimension._id, dimension.banner, dimension.name, 0.0);
                
                PanelCard.add(card, 0);
                PanelCard.revalidate();
            }
        });
    }

    private Cards addCard(
        String banner, 
        String name, 
        Double price) {
        Cards card = new Cards();
        card.setImg(banner);
        card.setName(name);
        card.setPrice(price);

        return card;
    }

    Cards addProductCard(String _id, String banner, String name, Double price) {

        Cards card = addCard(banner, name, 0.0);

        card.onClick(e -> {
            //* CLICK */
            _loadDimensions = true;
            _dimensionProduct = _id;

            //Redraw headerbar
            headerBar1.onBack(() -> {
                //Load product
                _loadDimensions = false;
                drawCard();

                //Clear back btn
                headerBar1.onBack(null);
            });

            //Load dimension
            DimensionModel query = new DimensionModel();
            query.product = _id;

            try {
                arrDimension = dimensionController.getAll(query);

                drawCard();
            } catch (Exception err) {
                Log.error(err);
            }

            return null;
        });

        card.onDelete(e -> {
            ProductModel query = new ProductModel();
            query._id = _id;

            try {
                productController.deleteOne(query);

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
        
        return card;
    }

    Cards addDimensionCard(String _id, String banner, String name, Double price) {
        Cards card = addCard(banner, name, price);

        card.onDelete(e -> {
            DimensionModel query = new DimensionModel();
            query._id = _id;

            try {
                dimensionController.deleteOne(query);

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

        return card;
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
                Cards card = addProductCard(data._id, data.banner, data.name, 0.0);
                PanelCard.add(card);
            });       
        }else{//DIMENSION
            arrDimension.forEach(data -> {
                Cards card = addDimensionCard(data._id, data.banner, data.name, 0.0);
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
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        headerBar1 = new duan1.components.HeaderBar();
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
        panelBoder1.setLayout(new java.awt.BorderLayout());

        panelBoder3.setBackground(new java.awt.Color(255, 255, 255));
        panelBoder3.setPreferredSize(new java.awt.Dimension(888, 64));
        panelBoder3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        jButton1.setText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelBoder3.add(jPanel4, java.awt.BorderLayout.LINE_END);
        panelBoder3.add(headerBar1, java.awt.BorderLayout.CENTER);

        panelBoder1.add(panelBoder3, java.awt.BorderLayout.PAGE_START);

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelCardLayout = new javax.swing.GroupLayout(PanelCard);
        PanelCard.setLayout(PanelCardLayout);
        PanelCardLayout.setHorizontalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
        );
        PanelCardLayout.setVerticalGroup(
            PanelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(PanelCard);

        panelBoder1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // new ThemSanPham(socket, _dimensionProduct).setVisible(true);
        sanPhamEditView.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private duan1.components.PanelBoder PanelCard;
    private duan1.components.HeaderBar headerBar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private duan1.components.Menu menu1;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder3;
    // End of variables declaration//GEN-END:variables
}
