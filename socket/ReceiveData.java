import java.io.*;
import java.net.*;
public class ReceiveData {
    public static void main(String args[]) throws Exception{
        DatagramSocket dsocket=new DatagramSocket(3000);
        byte[] buf=new byte[1024];
        DatagramPacket dpacket=new DatagramPacket(buf,1024);
        dsocket.receive(dpacket);
        String str=new String(dpacket.getData(),0,dpacket.getLength());
        System.out.println(str);
        dsocket.close();
    }
}