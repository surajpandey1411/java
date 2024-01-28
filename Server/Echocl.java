import java.io.*;
import java.net.*;

public class Echocl
{
	public static void main(String args[])
	{
	try
	{
		Socket cs=new Socket("localhost",4040);
		DataOutputStream dos=new DataOutputStream(cs.getOutputStream());
		DataInputStream dis=new DataInputStream(cs.getInputStream());
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String message;
		while(true)
		{
			message=br.readLine();
			dos.writeUTF(message);
			message=dis.readUTF();
			System.out.println(message);
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