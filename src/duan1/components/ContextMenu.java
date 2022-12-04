package duan1.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu {
    JMenuItem menuItem;

    public ContextMenu(ArrayList<Map<String, String>> items, Map<String, Function<Integer, Void>> events) {
        items.forEach(item -> {
            JMenuItem menuItem = new JMenuItem(item.get("title"));
            
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    events.get(item.get("key")).apply(0);
                }
            });
            
            add(menuItem);
        });
    }
}
