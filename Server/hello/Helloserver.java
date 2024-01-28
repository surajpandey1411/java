import java.io.*;
import java.net.*;

public class Helloserver
{
	 public static void main(String args[])
	{
	try{
		ServerSocket ser1=new ServerSocket(4040);
		Socket cs=ser1.accept();
		DataOutputStream dos=new DataOutputStream(cs.getOutputStream());
		DataInputStream dis=new DataInputStream(cs.getInputStream());
		
		String message;
		while (true)
			{
			message=dis.readUTF();
			dos.writeUTF("Server: "+ message.toUpperCase());
			if(message.trim().equalsIgnoreCase("Quit"))
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