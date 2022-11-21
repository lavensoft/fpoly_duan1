/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duan1;

import java.util.ArrayList;

import org.bson.Document;

import duan1.config.*;

import duan1.models.product.ProductModel;
import duan1.utils.HttpClient;
import duan1.utils.Log;
import duan1.views.Main;
import java.io.*;
import java.net.*;
import java.net.Socket;

import duan1.controllers.product.ProductController;

public class DuAn1 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        BufferedReader inFromUser =
        new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes(); 
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
        new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence =
        new String(receivePacket.getData());

        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close(); 
    }

}
