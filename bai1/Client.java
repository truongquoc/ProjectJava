package com;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
   public static void main(String[] args) throws Exception{
       Socket socket = new Socket(InetAddress.getLocalHost(), 5217);
       ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
       ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
       while(true) {
           System.out.println("Enter number");
           BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
           String message = keyboard.readLine();
           int number = Integer.parseInt(message);
           oos.writeObject(message);
           String res = (String) ois.readObject();
           if(res.equals("success")) {
               System.out.println("La so doi xung");
               break;
           }
       }
   }

}
