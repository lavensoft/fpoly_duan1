/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

import org.bson.Document;

import duan1.controllers.product.DeviceConfigurationController;
import duan1.controllers.product.ManufacturerController;
import duan1.controllers.product.ProductController;
import duan1.models.product.DeviceConfigurationModel;
import duan1.models.product.ManufacturerModel;
import duan1.utils.Log;

/**
 *
 * @author nhatsdevil
 */
public class ProductFilter extends javax.swing.JFrame {
    //* VARIABLES */
    private Function<Document, Void> onFilterEvent;

    //* DATA */
    private ArrayList<String> displays = new ArrayList<String>();
    private ArrayList<String> rams = new ArrayList<String>();
    private ArrayList<String> roms = new ArrayList<String>();
    private ArrayList<String> pins = new ArrayList<String>();
    private ArrayList<String> cameras = new ArrayList<String>();

    //* CONTROLLERS */
    private ManufacturerController manufacturerController = new ManufacturerController();
    private DeviceConfigurationController deviceConfigController = new DeviceConfigurationController();

    //* MODELS */
    private ArrayList<ManufacturerModel> manufacturers = new ArrayList<ManufacturerModel>();
    private ArrayList<DeviceConfigurationModel> deviceConfigs = new ArrayList<DeviceConfigurationModel>();

    /**
     * Creates new form ProductFilter
     */
    public ProductFilter() {
        initComponents();
        fetchData();
        init();
    }

    //* PUBLIC */
    public void onFilter(Function<Document, Void> func) {
        onFilterEvent = func;
    }

    //* PRIVATE */
    private void submitFilter() {
        String manufacturer = "all";
        String releaseYear = "all";
        Integer price = 0;
        String display = "all";
        String ram = "all";
        String rom = "all";
        String pin = "all";
        String camera = "all";
        Document filterValue = new Document();

        //Combo values
        Integer comboFacturerSelected = comboManufacturer.getSelectedIndex();
        Integer comboDisplaySelected = comboDisplay.getSelectedIndex();
        Integer comboRamSelected = comboRam.getSelectedIndex();
        Integer comboRomSelected = comboRom.getSelectedIndex();
        Integer comboPinSelected = comboPin.getSelectedIndex();
        Integer comboCameraSelected = comboCamera.getSelectedIndex();

        if(comboFacturerSelected != 0) manufacturer = manufacturers.get(comboFacturerSelected - 1)._id;
        if(comboReleaseYear.getSelectedIndex() != 0) releaseYear = (String) comboReleaseYear.getSelectedItem();
        price = comboPrice.getSelectedIndex();
        if(comboDisplaySelected != 0) display = displays.get(comboDisplaySelected - 1);
        if(comboRamSelected != 0) ram = rams.get(comboRamSelected - 1);
        if(comboRomSelected != 0) rom = roms.get(comboRomSelected - 1);
        if(comboPinSelected != 0) pin = pins.get(comboPinSelected - 1);
        if(comboCameraSelected != 0) camera = cameras.get(comboCameraSelected - 1);

        filterValue.put("manufacturer", manufacturer);
        filterValue.put("releaseYear", releaseYear);
        filterValue.put("price", price);
        filterValue.put("display", display);
        filterValue.put("ram", ram);
        filterValue.put("rom", rom);
        filterValue.put("pin", pin);
        filterValue.put("camera", camera);

        onFilterEvent.apply(filterValue);

        this.dispose();
    }

    private void init() {
        this.setLocationRelativeTo(null);

        //Load data to combo box
        manufacturers.forEach(item -> {
            comboManufacturer.addItem(item.title);
        });
        
        deviceConfigs.forEach(item -> {
            switch(item.key) {
                case "display":
                    displays.add(item._id);
                    comboDisplay.addItem(item.value);
                    break;
                case "ram":
                    rams.add(item._id);
                    comboRam.addItem(item.value);
                    break;
                case "rom":
                    roms.add(item._id);
                    comboRom.addItem(item.value);
                    break;
                case "pin":
                    pins.add(item._id);
                    comboPin.addItem(item.value);
                    break; 
                case "camera":
                    cameras.add(item._id);
                    comboCamera.addItem(item.value);
                    break;
                default: 
                    break;
            }
        });

        for(int i = Year.now().getValue(); i >= 2014; i--) {
            comboReleaseYear.addItem(String.valueOf(i));
        }
    }

    private void fetchData() {
        try {
            manufacturers = manufacturerController.getAll();
            deviceConfigs = deviceConfigController.getAll();
        }catch(Exception e) {
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

        jLabel3 = new javax.swing.JLabel();
        comboManufacturer = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        comboReleaseYear = new javax.swing.JComboBox<>();
        comboPrice = new javax.swing.JComboBox<>();
        comboDisplay = new javax.swing.JComboBox<>();
        comboRam = new javax.swing.JComboBox<>();
        comboRom = new javax.swing.JComboBox<>();
        comboPin = new javax.swing.JComboBox<>();
        comboCamera = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Hãng sản xuất:");

        comboManufacturer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel4.setText("Năm ra mắt:");

        jLabel5.setText("Giá");

        jLabel6.setText("Màn Hình");

        jLabel7.setText("RAM:");

        jLabel8.setText("ROM:");

        jLabel9.setText("Pin:");

        jLabel10.setText("Camera:");

        btnFilter.setFont(new java.awt.Font("Ionicons", 0, 14)); // NOI18N
        btnFilter.setText("  Lọc");
        btnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFilterMouseClicked(evt);
            }
        });
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        comboReleaseYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        comboPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trên 20.000.000", "Trên 10.000.000 dưới 20.000.000", "Dưới 10.000.000" }));

        comboDisplay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        comboRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        comboRom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        comboPin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        comboCamera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(45, 45, 45)
                        .addComponent(comboCamera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboManufacturer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboReleaseYear, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(74, 74, 74)
                        .addComponent(comboPrice, 0, 219, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(39, 39, 39)
                        .addComponent(comboDisplay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(62, 62, 62)
                        .addComponent(comboRam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(60, 60, 60)
                        .addComponent(comboRom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(72, 72, 72)
                        .addComponent(comboPin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboReleaseYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnFilter)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFilterMouseClicked
        this.submitFilter();
    }//GEN-LAST:event_btnFilterMouseClicked

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
            java.util.logging.Logger.getLogger(ProductFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductFilter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilter;
    private javax.swing.JComboBox<String> comboCamera;
    private javax.swing.JComboBox<String> comboDisplay;
    private javax.swing.JComboBox<String> comboManufacturer;
    private javax.swing.JComboBox<String> comboPin;
    private javax.swing.JComboBox<String> comboPrice;
    private javax.swing.JComboBox<String> comboRam;
    private javax.swing.JComboBox<String> comboReleaseYear;
    private javax.swing.JComboBox<String> comboRom;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
