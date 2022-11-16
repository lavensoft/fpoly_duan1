/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duan1.components;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author TAN PHAT
 */
public class ListMenu< E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    Object ob = model.getElementAt(index);
                    if (ob instanceof Model_Menu) {
                        Model_Menu menu = (Model_Menu) ob;
                        if (menu.getType() == Model_Menu.MenuType.MENU) {
                            selectedIndex = index;
                        }
                    } else {
                        selectedIndex = index;                        
                    }
                    repaint();
                }
            }
            
        });
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object ob, int index, boolean select, boolean cellfocus) {
                Model_Menu data;
                if (ob instanceof Model_Menu) {
                    data = (Model_Menu) ob;
                    
                } else {
                    data = new Model_Menu("", ob + "", Model_Menu.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(Model_Menu data) {
        model.addElement(data);
    }
    
}

