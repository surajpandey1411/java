import java.io.*;
import java.net.*;

class Clienthandler implements Runnable
{
private Socket  Clientsocket;
Clienthandler(Socket Clientsocket)
{
    this.Clientsocket=Clientsocket;
}
public void run()
{
try
{
    DataOutputStream dos=new DataOutputStream(Clientsocket.getOutputStream());
    DataInputStream dis=new DataInputStream(Clientsocket.getInputStream());
    String message="";
    while(true)
    {
        message=dis.readUTF();
        if(message.trim().equalsIgnoreCase("trim"))
        break;
        else
            dos.writeUTF("server :"+message.toUpperCase());
        
    }
}
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}

}



public class Helloserver1
{
    public static void main(String args[])
    {
        try
        {
            ServerSocket ss=new ServerSocket(4040);
            while(true)
            {
                Socket Clientsock=ss.accept();
                Clienthandler ch=new Clienthandler(Clientsock);
                new Thread(ch).start();
            }
        }
        catch(Exception ex)
                {
                 ex.printStackTrace();
                }
    }
    }
    
