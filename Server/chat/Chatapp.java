import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;


 public class Chatapp extends Frame implements ActionListener,Runnable
{
		 List l;
			Label nam;
		TextArea t;
		TextField t1,t2;
		Button b,b1;
		Socket s;
		static DataOutputStream dos;
		DataInputStream dis;
		static String name,data,msg,rece;
	public Chatapp(Socket s)
	{
		try
		{
		  
		 dos=new DataOutputStream(s.getOutputStream());
		 dis=new DataInputStream(s.getInputStream());
		
			//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));	
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		this.setVisible(true);
		this.setSize(400,400);
		setLayout(null);
		nam=new Label("Name");
		nam.setBounds(100,50,50,55);
		t2=new TextField(20);
		t2.setBounds(300,50,200,50);
		t=new TextArea(20,20);
		t.setBounds(100,100,200,200);
		l=new List(10);
		
		l.setBounds(400,100,80,200);
		t1=new TextField(30);
		t1.setBounds(100,500,200,30);
		b=new Button("Send");
		b1=new Button("SendName");
		b1.setBounds(550,50,100,50);
		b.setBackground(Color.red);
		b.setBounds(400,500,60,30);
		add(l);
		add(t);
		add(t1);
		add(t2);
		add(nam);
		add(b1);
		add(b);
		b.addActionListener(this);
		b1.addActionListener(this);
		addWindowListener(new Wh());
	}
	class Wh extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
	}
	 
	public static void messagesend(String nam)
	{
		name=nam;
		System.out.println("message send="+name);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		
	String str=ae.getActionCommand();
	
        if(str.equals("SendName"))
	{
		String str1=t2.getText();
		Chatapp.messagesend(str1);
		System.out.println("buttonsend name:"+str1);
		try
		{
			dos.writeUTF(str1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(str.equals("Send"))
	{
		msg = l.getSelectedItem();
		data=msg+":"+t1.getText()+"by"+name;
	
	
		try
		{
			Chatapp.input(this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
	} 
	}

	public static void main(String arg[])
	{
		try
		{
			
			
		
				
			
				Socket s=new Socket("localhost",5040);
				Chatapp c=new Chatapp(s);
				
				Thread t=new Thread(c);
				t.start();
				
				
				
	
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Face");
		}
	}
	 static void input(Chatapp c4)
	 {
		
		 try
		 {
			
			
				 String dat=c4.data;
				 dos.writeUTF(dat);
					
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }		 

		
	 		
				
	
	public void run()
	{
		int n;
		try
		{
			 
			while(true)
			{
				String msg=dis.readUTF();
				
				 n=msg.indexOf("::");
				String recei=msg.substring(n+2);
				
				if(recei.trim().equalsIgnoreCase("quit"))
				{
					dos.writeUTF(recei);
					break;
				}
				if(n!=-1)
				{
			
				t.append(msg+"\n");
					
				}
				else
				{
				l.add(msg);
				}
				msg="";
			}
		
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
	}
	
}