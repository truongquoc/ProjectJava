package com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws Exception{
        try {
            ServerSocket socket = new ServerSocket(5217);
            System.out.println(("Waiting for Connection ... "));
            Socket soc = socket.accept();
            System.out.println("connected");
            ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
            while (true) {
                String number = (String) in.readObject();
                String newBin = convertBinary(Integer.parseInt(number));
                System.out.println("new bin"+newBin);
                if(checkReverse(newBin)) {
                    out.writeObject("success");
                   soc.close();
                   in.close();
                }
                out.writeObject("fail");
            }
        }catch (IOException ex) {
            System.out.println((ex));
        } finally {

        }
    }

    public static String convertBinary(int num){
        String newBinary= "";
        int binary[] = new int[8];
        int index = 0;
        while(num > 0 || index <8){
            binary[index++] = num%2;
            num = num/2;
        }
        for(int i = index-1;i >= 0;i--){
            newBinary+=binary[i];
        }
        return  newBinary;
    }

    public static boolean checkReverse(String msg) {
        String reverse = "";
        for(int i=msg.length()-1; i>=0; i--) {
            reverse+=msg.charAt(i);
        }
        if(reverse.equals(msg)) {
            return true;
        }
        return false;
    }
}
