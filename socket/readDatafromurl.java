import java.io.*;
import java.net.*;
public class readDatafromurl{ 
public static void main(String args[]) throws Exception {
    URL eradar=new URL("http://examradar.com/java-networking-network-basics-socket-overview");
    URLConnection urlconn=eradar.openConnection();
    InputStream stream=urlconn.getInputStream();
    int i;
    while((i=stream.read())!=-1){
        System.out.println((char)i);
    }
} 
}  

