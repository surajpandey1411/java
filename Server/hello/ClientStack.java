import java.io.*;
import java.net.*;
class ClientHandler implements Runnable 
{
	private Stack ClientStack;
	public void Run()
	{
		try
		{
			DataInputStream dis=new DataInputStream(ClientStack.getInputStream());
			DataOutputStream dos=new DataOutputStream(ClientStack.getOutputStream());
			String message;
			while(true)
			{
				dis.readUTF();
				if(message.trim().equalsIgnoreCase("quit"))
					break;
				dos.writeUTF("server::"+message.toUpperCase());
			}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
class Server
{
	public static void main(String arg[])
	{
		try
		{
			ServerSocket ss=new ServerSocket(4040);
			while(true)
			{
				Socket clientSock=ss.accpet();
				ClientHandler chl=new ClientHandler(clientSock);
				new Thread(chl).start();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
