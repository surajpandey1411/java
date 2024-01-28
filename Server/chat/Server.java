import java.net.*;
import java.io.*;
import java.util.Vector;

class Clienthandle implements Runnable
{
	private Socket clientsocket;
	DataOutputStream dos;

	String name1;
	String message="";
	String data,name2,sender,mess;
	Clienthandle(Socket clientsocket)
	{
		this.clientsocket=clientsocket;	
	}

	public void run()
	{
		try
		{
			DataInputStream dis=new DataInputStream(clientsocket.getInputStream());
			dos=new DataOutputStream(clientsocket.getOutputStream());
		
			int i=0,j;
			String name=dis.readUTF();
			this.name1=name;
			System.out.println("namere="+name1);
			Server.addinlist(this);

			while(true)
			{
			message=dis.readUTF();
			i=message.indexOf(":");
			j=message.indexOf("by");
			name2=message.substring(0,i);
			data=message.substring(i,j);
			sender=message.substring(j+2);
			mess=sender+":"+data;
			System.out.println("sender="+sender+"mess="+mess);
			if(message.trim().equalsIgnoreCase("quit"))
			{
			Server.remove(this);
				break;
			}
			Server.senddata(this);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occurs");
		}
	}
public void sendmessage(String msg)
	{
		try
	{
	dos.writeUTF(msg);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	public void send(String msge)
	{
		try
		{
		System.out.println(msge);
		dos.writeUTF(msge);

		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}
class Server
{
	static Vector v=new Vector();
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss=new ServerSocket(5040);
			System.out.println("server is waiting for client...");
			while(true)
			{
				Socket clientSock=ss.accept();
				Clienthandle ch=new Clienthandle(clientSock);
				
				Thread t=new Thread(ch);
				t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception occurs");
		}
	}
	static void addinlist(Clienthandle c3)
	{
		v.addElement(c3);
		System.out.println(c3.name1);
		for(int i=0;i<v.size();i++)
		{
			Clienthandle c1=(Clienthandle)v.elementAt(i);
			if(!c1.name1.equals(c3.name1))
			{
			c1.sendmessage(c3.name1);
			c3.sendmessage(c1.name1);
			}
			
		}
	}
	static void remove(Clienthandle c2)
	{
		for(int i=0;i<v.size();i++)
	{
	Clienthandle c=(Clienthandle)v.elementAt(i);
			if(c2.equals(c))
			{
				v.removeElement(c);
			}
		}
	}
	static void senddata(Clienthandle c3)
	{
		for(int i=0;i<v.size();i++)
		{
			Clienthandle c1=(Clienthandle)v.elementAt(i);
			if(c1.name1.equals(c3.name2))
			{
			//DataOutputStream op=new DataOuputStream(c1.clientsocket.getOutputStream());
			c1.send(c3.mess);
			
			}
		}
	}


}