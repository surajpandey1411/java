import java.awt.*;
import java.applet.Applet;

public class happyface extends Applet implements Runnable
{
	private int x, y, x1, y1;
	private boolean done;
	public void start() //sets starting postion and sets up thread
	{
		x=130;
		y=100;
		x1=210;
		y1=100;
		done=false;
		Thread t=new Thread(this);
		t.start();
}//end start
public void stop() //tells it when to stop
{
	done=true;
}//end stop
	public void paint(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillOval(90, 80, 200, 200);
		g.setColor(Color.black);
		g.fillOval(130, 120, 45, 45);
		g.fillOval(210, 120, 45, 45);
		//g.fillOval(150, 195, x, y);
		g.fillArc(160, 185, 60, 60, 0, -180);
		g.drawArc(123, y, 60, 60, 0, 180);
		g.drawArc(200, y1, 60, 60, 0, 180);
		g.setColor(Color.blue);
		g.fillOval(x, 123, 30, 30);
		g.fillOval(x1, 123, 30, 30);
	}
	public void run( )  //set up the running
	{
		int dx=3, dy=3;  //tells how far to go
		while(true)
		{
			for(int i=0; i<3; i++)
			{
				if(done) return;
				//x=x+1;
				//y=y+1;
				x+=dx;  //sets direction
				x1+=dx;
				y-=dy;
				y1-=dy;
				repaint();
				try
				{
					Thread.sleep(200);  //timer
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}//end for
			dx=-dx;  //get it to bounce
			dy=-dy;

		}//end while
}//end run

}
