package bai2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server is started");
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress inetAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String request = new String(receivePacket.getData());
            System.out.println("req"+request);
            int number = Integer.valueOf(request.trim());
            System.out.println("number"+ number);
            if(isFibonacci(number)) {
                System.out.println("fibonacci");
                DatagramPacket res = new DatagramPacket("isFibonacci".getBytes(), "isFibonacci".length(), inetAddress, port);
                serverSocket.send(res);
                break;
            } else {
                DatagramPacket resToUpper = new DatagramPacket("Not Fibonacci".getBytes(), "Not Fibonacci".length(), inetAddress, port);
                serverSocket.send(resToUpper);
                System.out.println("request"+request);
            }
        }
    }
    static boolean isFibonacci(int n)
    {
        return isPerfectSquare(5*n*n + 4) ||
                isPerfectSquare(5*n*n - 4);
    }
    static  boolean isPerfectSquare(int x)
    {
        int s = (int) Math.sqrt(x);
        return (s*s == x);
    }
}
