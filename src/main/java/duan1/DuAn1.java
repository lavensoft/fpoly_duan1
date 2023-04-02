// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
//  */
// package duan1;

// import java.util.ArrayList;

// import org.bson.Document;

// import duan1.config.*;

// import duan1.models.product.ProductModel;
// import duan1.utils.HttpClient;
// import duan1.utils.Log;
// import duan1.views.Main;
// import java.io.*;
// import java.net.*;
// import duan1.utils.SocketIO;
// import io.socket.client.Socket;
// import io.socket.emitter.Emitter;
// import duan1.controllers.product.ProductController;

// public class DuAn1 {

//     public static void main(String[] args) throws UnknownHostException, IOException {
//         SocketIO io = new SocketIO();
//         Socket socket = io.socket;

//         System.out.println("SENDING");
//         socket.emit("user-join", "nhats");

//         socket.on("hello", new Emitter.Listener() {
//             @Override
//             public void call(Object... args) {
//                 System.out.println(args[0]);
//             }
//         });
//     }

// }
