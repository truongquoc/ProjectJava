package bai2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter number: ");
            String msg = reader.readLine();
            sendData = msg.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, 9876);
            ds.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(receivePacket);
            String checkFib = new String(receivePacket.getData());
            if(checkFib.trim().equals("isFibonacci")) {
                System.out.println("Number is Fibonacci");
                break;
            }
           else {
                System.out.println("Number is not Fibonacci");
            }
        }
    }
}
