package duan1.components;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu {
    JMenuItem menuItem;

    public ContextMenu() {
        menuItem = new JMenuItem("Click Me!");
        add(menuItem);
    }
}
