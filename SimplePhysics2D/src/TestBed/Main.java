package TestBed;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;

import Math.Transform;
import Math.Vector2;
import Physics.Collision.CircleCollider;
import Physics.Collision.Collider;
import Physics.Collision.PlaneCollider;
import Physics.Dynamics.RigidPlane;
import Physics.Dynamics.Rigidbody;
import Physics.Dynamics.RigidbodyType;
import World.World;
class TestGraphics 
{
	Graphics a;
	float dt;
}
public class Main extends Canvas{
	static float timeStep=0.02f;
	int n=0;
	public void draw(Graphics g,ArrayList<Rigidbody> Rigidbodies, World w)
	{

		g.setClip(-50, 0, 1920, 1080);
		System.out.println(g.getClip().getBounds().height);

		while(true)
		{
			for(Rigidbody rb:Rigidbodies)
			{
				if(rb.getType()==RigidbodyType.BODY)
				{
					g.drawOval((int)rb.transform.position.X-(int)rb.collider.getAsCircleCollider().radius, 
							-(int)rb.transform.position.Y-(int)rb.collider.getAsCircleCollider().radius, 
							2*(int)rb.collider.getAsCircleCollider().radius, 
							2*(int)rb.collider.getAsCircleCollider().radius);	
				}
				else if(rb.getType()==RigidbodyType.PLANE)
				{
					g.drawLine((int)rb.collider.getAsPlaneCollider().P1.X, 
							-(int)rb.collider.getAsPlaneCollider().P1.Y, 
							(int)rb.collider.getAsPlaneCollider().P2.X, 
							-(int)rb.collider.getAsPlaneCollider().P2.Y);
					
				}
			}
			try {
				Thread.sleep((long)(Main.timeStep*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.clearRect(0, 0, 1920, 1080);
			w.step(Main.timeStep);
			w.resolveCollisions(Main.timeStep);
			n++;
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		//comment
		World w=new World();
		Rigidbody rb=new Rigidbody(5f,10f,new Transform(new Vector2(80f,0f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))));
		Rigidbody rb2=new Rigidbody(10f,10f,new Transform(new Vector2(100f,-200f)), false, new CircleCollider(20f,new Transform(new Vector2(20f,0f))));
		Rigidbody rb3=new Rigidbody(5f,10f,new Transform(new Vector2(120f,0f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))));
		Rigidbody rb4=new Rigidbody(10f,10f,new Transform(new Vector2(100f,-20f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))));
		RigidPlane plane=new RigidPlane(new Transform(new Vector2(250f,-350f)),1000f);
		w.AddRigidbodyToWorld(rb);
		w.AddRigidbodyToWorld(rb2);
		w.AddRigidbodyToWorld(rb3);
		w.AddRigidbodyToWorld(rb4);
		w.AddRigidbodyToWorld(plane);
		w.addSolvers();
		Main m=new Main();
		m.setSize(1920, 1080);
		JFrame f=new JFrame();
		f.setSize(1920,1080);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.add(m);
		Graphics g=m.getGraphics().create();
		Graphics2D g2d=(Graphics2D)g;
		g2d.scale(2, 2);
		m.draw(g2d, w.getRigidbodies(),w);
		
	}

}
