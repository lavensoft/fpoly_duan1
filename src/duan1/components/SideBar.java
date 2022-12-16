/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duan1.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.bson.Document;

import duan1.config.Config;
import duan1.controllers.user.PermissionController;
import duan1.controllers.user.UserController;
import duan1.models.user.PermissionModel;
import duan1.models.user.UserModel;
import duan1.utils.Async;
import duan1.utils.Log;
import duan1.utils.NextImage;
import duan1.utils.WrapLayout;
import duan1.views.App;
import duan1.views.HoaDon;
import duan1.views.KhachHang;
import duan1.views.KhuyenMai;
import duan1.views.Login;
import duan1.views.NhanVien;
import duan1.views.Permission;
import duan1.views.SanPham;
import duan1.views.Staff;
import duan1.views.ThemHoaDon;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 *
 * @author nhatsdevil
 */

class SMenuItem <V> {
    public String title;
    public String icon;
    public Class<V> view;
    public Boolean isBreak;
    public String id;

    public SMenuItem() {
        
    }

    public SMenuItem(String title, String id, String icon, Class<V> view, Boolean isBreak) {
        this.title = title;
        this.icon = icon;
        this.view = view;
        this.isBreak = isBreak;
        this.id = id;
    }
}

public class SideBar extends javax.swing.JPanel {
    private ArrayList<SMenuItem> menuItems = new ArrayList<>();
    private UserController userController = new UserController();
    private App appContext;
    private Socket socket;
    PermissionModel perm = new PermissionModel();
    
    /**
     * Creates new form SideBar
     */
    public SideBar() {
        initComponents();
        initMenuItems();
        init();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        initSocket();
    }

    //*SOCKET HANDLERS */
    private void initSocket() {
        socket.on("/permissions/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });
        
        socket.on("/permissions/update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Document dPerm = new Document();
                dPerm = dPerm.parse((String) args[0]);

                PermissionModel tPerm = new PermissionModel();
                tPerm.fromDocument(dPerm);

                if(tPerm._id.equals(perm._id)) {
                    perm = tPerm;
                    initMenuItems();
                }
            }
        });
    };

    public void setAppContext(App context) {
        this.appContext = context;
    }

    private void init() {
        // backdrop.setIcon(new ImageIcon(getClass().getResource("/duan1/assets/images/background.jpeg")));
        background.setBackground(new Color(18, 19, 26));
        // backdrop.setBackground(new Color(255,255,255));
        // this.setBackground(new Color(18, 19, 26, 0));
        userNameLbl.setForeground(new Color(255, 255, 255));
        emailLbl.setForeground(new Color(255, 255, 255));
        userInfoContainer.setBackground(new Color(255, 255, 255, 50));
        menuItemsGroup.setBackground(new Color(0,0,0,0));
    }

    private void initMenuItems() {
        //Set app version
        appVersion.setText(Config.APP_VERSION);

        menuItems.clear();
        menuItemsGroup.removeAll();
        menuItemsGroup.revalidate();
        menuItemsGroup.repaint();
        menuItemsGroup.updateUI();
        this.revalidate();
        this.repaint();
        this.updateUI();
        menuItemsGroup.setLayout(new WrapLayout(0, 0, 12));

        //Set User Info
        try {
            UserModel userInfo = userController.checkLogin();
            perm._id = userInfo.permission;

            perm = new PermissionController().get(perm);
            
            userNameLbl.setText(userInfo.name);
            emailLbl.setText(userInfo.email);
            lblAvatar.setIcon(new NextImage().load(userInfo.avatar, 58, 58));
        } catch (Exception e1) {
            
        }
        
        //Create items
        menuItems.add(new SMenuItem<SideBarItem>("Bán Hàng", "", "", SideBarItem.class, true));
        if(perm.order != null && perm.order.contains("r")) menuItems.add(new SMenuItem<HoaDon>("Đơn Hàng", "", "\uf292", HoaDon.class, false));
        if(perm.product != null && perm.product.contains("r")) menuItems.add(new SMenuItem<SanPham>("Sản Phẩm", "", "\uf10e", SanPham.class, false));
        if(perm.discount != null && perm.discount.contains("r")) menuItems.add(new SMenuItem<KhuyenMai>("Khuyến Mãi", "", "\uf35b", KhuyenMai.class, false));
        if(perm.order != null && perm.order.contains("r")) // menuItems.add(new SMenuItem<ThemHoaDon>("Thống Kê", "\uf21c", ThemHoaDon.class, false));
        if(perm.customer != null && perm.customer.contains("r")) menuItems.add(new SMenuItem<KhachHang>("Khách Hàng", "", "\uf2d7", KhachHang.class, false));
        menuItems.add(new SMenuItem<SideBarItem>("Quản Lý", "", "", SideBarItem.class, true));
        if(perm.staff != null && perm.staff.contains("r")) menuItems.add(new SMenuItem<Staff>("Nhân Viên", "", "\uf345", Staff.class, false));
        if(perm.permission != null && perm.permission.contains("r")) menuItems.add(new SMenuItem<Permission>("Phân Quyền", "", "\uf377", Permission.class, false));
        menuItems.add(new SMenuItem<ThemHoaDon>("Đăng Xuất", "logout", "\uf4c7", ThemHoaDon.class, false));

        //Render to UI
        menuItems.forEach(item -> {
            if(item.isBreak) { //* BREAK TITLE */
                JLabel title = new JLabel(item.title.toUpperCase());
                title.setFont(new Font("SF Pro Text", Font.BOLD, 11));
                title.setForeground(new Color(255, 255, 255));

                menuItemsGroup.add(title);
            }else{ //* MENU ITEM */
                SideBarItem menuItem = new SideBarItem();
                menuItem.setTitle(item.title);
                menuItem.setIcon(item.icon);
                menuItem.setPreferredSize(new Dimension(218, 48));

                menuItem.onClick(e -> {
                    unActiveAllMenuItem();
                    menuItem.setActive(true);
                    
                    try {
                        if(item.id.equals("logout")) {
                            userController.logout();

                            new Login().setVisible(true);
                            appContext.dispose();
                        }else{
                            Async.setTimeout(() -> {
                                appContext.navigate(item.view);
                            }, 100);
                        }
                    }catch(Exception err) {
                        Log.error(err);
                    }

                    return null;
                });

                menuItemsGroup.add(menuItem);
            }
        });
        
        menuItemsGroup.revalidate();
        menuItemsGroup.repaint();
        menuItemsGroup.updateUI();
        this.revalidate();
        this.repaint();
        this.updateUI();
    }

    private void unActiveAllMenuItem() {
        List<Component> menuItems = new ArrayList<>();

        menuItems = Arrays.asList(menuItemsGroup.getComponents());

        menuItems.forEach(item -> {
            if(item.getClass().getSimpleName().equals("SideBarItem")) {
                SideBarItem menuItem = (SideBarItem) item;
                menuItem.setActive(false);
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

        menuItemsGroup = new javax.swing.JPanel();
        userInfoContainer = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        userNameLbl = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        appVersion = new javax.swing.JLabel();
        background = new javax.swing.JPanel();
        backdrop = new javax.swing.JLabel();

        setBackground(new java.awt.Color(18, 19, 26));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuItemsGroup.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout menuItemsGroupLayout = new javax.swing.GroupLayout(menuItemsGroup);
        menuItemsGroup.setLayout(menuItemsGroupLayout);
        menuItemsGroupLayout.setHorizontalGroup(
            menuItemsGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        menuItemsGroupLayout.setVerticalGroup(
            menuItemsGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(menuItemsGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 230, 600));

        userInfoContainer.setBackground(new java.awt.Color(255, 255, 255));
        userInfoContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userInfoContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        userInfoContainer.add(lblAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 58, 58));

        userNameLbl.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        userNameLbl.setText("USER NAME");
        userInfoContainer.add(userNameLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        emailLbl.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        emailLbl.setText("EMAIL");
        userInfoContainer.add(emailLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        add(userInfoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 70));

        appVersion.setFont(new java.awt.Font("Helvetica Neue", 1, 11)); // NOI18N
        appVersion.setForeground(new java.awt.Color(153, 153, 153));
        appVersion.setText("APP_VERSION");
        add(appVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, -1));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );

        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 780));
        add(backdrop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 780));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appVersion;
    private javax.swing.JLabel backdrop;
    private javax.swing.JPanel background;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JPanel menuItemsGroup;
    private javax.swing.JPanel userInfoContainer;
    private javax.swing.JLabel userNameLbl;
    // End of variables declaration//GEN-END:variables
}
