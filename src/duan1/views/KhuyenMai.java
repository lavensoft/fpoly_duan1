/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.views;

import duan1.components.Cards;
import duan1.controllers.product.DimensionController;
import duan1.controllers.product.DimensionPromotionController;
import duan1.controllers.product.PromotionController;
import duan1.models.product.DimensionModel;
import duan1.models.product.DimensionPromotionModel;
import duan1.models.product.PromotionModel;
import duan1.utils.Log;
import duan1.utils.NextImage;
import duan1.utils.WrapLayout;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

/**
 *
 * @author TAN PHAT
 */
public class KhuyenMai extends View {

    /**
     * Creates new form KhuyenMai
     */
    PromotionController controller = new PromotionController();
    ArrayList<PromotionModel> arrPromotion = new ArrayList<>();

    DimensionPromotionController dimPromotionController = new DimensionPromotionController();
    DimensionPromotionModel ModelDimenstion = new DimensionPromotionModel();
    DimensionController dimensionController = new DimensionController();
    ArrayList<DimensionModel> arrDimensionPromotion = new ArrayList<>();
    ArrayList<DimensionModel> dimensions = new ArrayList<>();

    DimensionModel ModelDimension = new DimensionModel();
    ArrayList<DimensionModel> arrDimension = new ArrayList<>();

    DefaultTableModel modelTable = new DefaultTableModel();

    private ThemKhuyenMai themKhuyenMaiView = new ThemKhuyenMai();
    private Socket socket;

    public KhuyenMai() {
        initComponents();
        init();
        load();
        loadTable();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        themKhuyenMaiView.setSocket(socket);

        initSocket();
    }

    private void init() {
        headerBar2.setTitle("Khuyến Mãi");
    }

    //*SOCKET HANDLERS */
    @Override
    public void initSocket() {
        socket.on("/promotions/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                PromotionModel promotion = new PromotionModel();
                promotion.fromDocument(new Document().parse((String) args[0]));

                System.out.println(promotion.toDocument());

                arrPromotion.add(0, promotion);

                //Render table
                loadTable();
            }
        });
    };

    void load() {
        try {
            arrPromotion = controller.getAll();
            dimensions = dimensionController.getAll();
        } catch (Exception ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTable() {
        modelTable = (DefaultTableModel) tblKhuyenMai.getModel();
        modelTable.setRowCount(0);

        arrPromotion.forEach(data -> {
            modelTable.addRow(new Object[]{
                data.title,
                data.startDate,
                data.endDate,
                data.percent,
                data.points
            });
        });
    }

    void showCard() {
        // modelTable = (DefaultTableModel) tblKhuyenMai.getModel();
        // String index = arrDimensionPromotion.get(tblKhuyenMai.getSelectedRow()).promotion;

        // //*FILTER DIMENSIONS */
        // arrDimension = new ArrayList<>();
        // arrDimensionPromotion.forEach(data -> {
        //     if (data.promotion.equals(index)) {
        //         arrDimension.add(data);
        //     }
        // });
        try {
            //* GET DIMENSION PROMOTION */
            DimensionPromotionModel query = new DimensionPromotionModel();
            query.promotion = arrPromotion.get(tblKhuyenMai.getSelectedRow())._id;
            
            ArrayList<DimensionPromotionModel> dimps = new ArrayList<>();
            dimps = dimPromotionController.getAll(query);

            arrDimension.clear();
            
            for(DimensionPromotionModel dim : dimps) {
                DimensionModel dimension = new DimensionModel();
                dimension._id = dim.dimension;
                dimension = dimensionController.get(dimension);

                arrDimension.add(dimension);
            }

            drawCard();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    private void drawCard() {
        scrollView.setLayout(new WrapLayout());
        scrollView.removeAll();
        scrollView.repaint();

        arrDimension.forEach(data -> {
            Cards card = new Cards();
            card.setImg(data.banner);
            card.setName(data.name);
            card.setPrice(0.0);
            scrollView.add(card);
        });

        scrollView.revalidate();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sideBarItem1 = new duan1.components.SideBarItem();
        header1 = new duan1.components.Header();
        sideBar1 = new duan1.components.SideBar();
        sideBar2 = new duan1.components.SideBar();
        panelBoder2 = new duan1.components.PanelBoder();
        panelBoder1 = new duan1.components.PanelBoder();
        headerBar2 = new duan1.components.HeaderBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scrollView = new javax.swing.JPanel();

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelBoder2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBoder2Layout = new javax.swing.GroupLayout(panelBoder2);
        panelBoder2.setLayout(panelBoder2Layout);
        panelBoder2Layout.setHorizontalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
        );
        panelBoder2Layout.setVerticalGroup(
            panelBoder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá", "Điểm Người Dùng"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setFont(new java.awt.Font("Ionicons", 0, 18)); // NOI18N
        btnThem.setText("");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout scrollViewLayout = new javax.swing.GroupLayout(scrollView);
        scrollView.setLayout(scrollViewLayout);
        scrollViewLayout.setHorizontalGroup(
            scrollViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        scrollViewLayout.setVerticalGroup(
            scrollViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(scrollView);

        javax.swing.GroupLayout panelBoder1Layout = new javax.swing.GroupLayout(panelBoder1);
        panelBoder1.setLayout(panelBoder1Layout);
        panelBoder1Layout.setHorizontalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder1Layout.createSequentialGroup()
                .addComponent(headerBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBoder1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
        );
        panelBoder1Layout.setVerticalGroup(
            panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoder1Layout.createSequentialGroup()
                .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(headerBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(panelBoder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBoder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        themKhuyenMaiView.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        showCard();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private duan1.components.Header header1;
    private duan1.components.HeaderBar headerBar2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private duan1.components.PanelBoder panelBoder1;
    private duan1.components.PanelBoder panelBoder2;
    private javax.swing.JPanel scrollView;
    private duan1.components.SideBar sideBar1;
    private duan1.components.SideBar sideBar2;
    private duan1.components.SideBarItem sideBarItem1;
    private javax.swing.JTable tblKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
