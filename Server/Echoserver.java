import java.io.*;
import java.net.*;

public class Echoserver
{
	public static void main(String args[])
	{
	  try
	{
		ServerSocket ss=new ServerSocket(4040);
		Socket cs=ss.accept();

		DataOutputStream dos=new DataOutputStream(cs.getOutputStream());
		DataInputStream dis=new DataInputStream(cs.getInputStream());

		String message;
		while(true)
		{
			message=dis.readUTF();
			dos.writeUTF("server::"+message.toUpperCase());
			if(message.trim().equalsIgnoreCase("quit"))
			{
			break;
			}
		}		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	}
}