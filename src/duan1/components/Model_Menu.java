/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duan1.components;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author TAN PHAT
 */
public class Model_Menu {
     String icon;
    String name;
    MenuType type;

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource("/com/icon/"+icon+".png"));
    }

    public Model_Menu() {
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

   

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

}
