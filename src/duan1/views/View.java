/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duan1.views;

import io.socket.client.Socket;

/**
 *
 * @author nhatsdevil
 */
public class View extends javax.swing.JPanel {
    public Socket socket;
    public App appContext;

    /**
     * Creates new form NewJFrame
     */
    public View() {
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void setAppContext(App appContext) {
        this.appContext = appContext;
    }

    public void initSocket() {

    }
}
