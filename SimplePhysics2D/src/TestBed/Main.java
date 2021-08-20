package TestBed;
import java.awt.*;

import java.util.ArrayList;


import javax.swing.JFrame;

import SimplePhysics.Collision.AABBCollider;
import SimplePhysics.Collision.CircleCollider;

import SimplePhysics.Dynamics.RBType;
import SimplePhysics.Dynamics.RigidPlane;
import SimplePhysics.Dynamics.Rigidbody;
import SimplePhysics.Math.Transform;
import SimplePhysics.Math.Vector2;
import SimplePhysics.World.World;
class TestGraphics 
{
	Graphics a;
	float dt;
}
public class Main extends Canvas{
	static float timeStep=0.02f;
	int n=0;
	public Rigidbody forceBox;
	public void draw(Graphics g,ArrayList<Rigidbody> Rigidbodies, World w)
	{

		g.setClip(-50, 0, 1920, 1080);
		//System.out.println(g.getClip().getBounds().height);

		while(true)
		{
			for(Rigidbody rb:Rigidbodies)
			{
				/*if(rb.getType()==RBType.CIRCLE)
				{
					g.drawOval((int)rb.transform.position.X-(int)rb.collider.getAsCircleCollider().radius, 
							-(int)rb.transform.position.Y-(int)rb.collider.getAsCircleCollider().radius, 
							2*(int)rb.collider.getAsCircleCollider().radius, 
							2*(int)rb.collider.getAsCircleCollider().radius);
					//rb.collider.getAsCircleCollider().drawCollider(g);
				}
				else if(rb.getType()==RBType.PLANE)
				{
					g.drawLine((int)rb.collider.getAsPlaneCollider().P1.X, 
							-(int)rb.collider.getAsPlaneCollider().P1.Y, 
							(int)rb.collider.getAsPlaneCollider().P2.X, 
							-(int)rb.collider.getAsPlaneCollider().P2.Y);
					
				}*/
				rb.collider.drawCollider(g);
				
			}
			//Rigidbodies.get(1).AddForce(new Vector2(5f*Rigidbodies.get(0).mass,0f), Main.timeStep);
			//4System.out.println(Rigidbodies.get(0).transform.position.repr());

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
		Main m=new Main();
		Rigidbody rb=new Rigidbody(5f,10f,0.2f,new Transform(new Vector2(130f,0f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))),RBType.CIRCLE);
		//Rigidbody rb2=new Rigidbody(10f,10f,new Transform(new Vector2(100f,-200f)), false, new CircleCollider(20f,new Transform(new Vector2(20f,0f))),RBType.CIRCLE);
		Rigidbody rb3=new Rigidbody(5f,10f,0.5f,new Transform(new Vector2(120f,0f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))),RBType.CIRCLE);
		Rigidbody rb4=new Rigidbody(10f,10f,0.5f,new Transform(new Vector2(100f,-20f)), false, new CircleCollider(10f,new Transform(new Vector2(10f,0f))),RBType.CIRCLE);
		RigidPlane plane=new RigidPlane(new Transform(new Vector2(250f,-350f)),1000f,RBType.PLANE);
		Rigidbody box=new Rigidbody(10f,10f,0.7f,new Transform(new Vector2(100,-100)),false,new AABBCollider(100,100,new Transform(new Vector2(100,-100))),RBType.AABB);
		Rigidbody box2=new Rigidbody(10f,10f,0.9f,new Transform(new Vector2(200,-100)),false,new AABBCollider(50,50,new Transform(new Vector2(130,-200))),RBType.AABB);

		//Rigidbody box3=new Rigidbody(10f,10f,new Transform(new Vector2(100,-200)),false,new AABBCollider(100,100,new Transform(new Vector2(100,-100))),RBType.AABB);
		//Rigidbody box4=new Rigidbody(10f,10f,new Transform(new Vector2(200,-200)),false,new AABBCollider(50,50,new Transform(new Vector2(130,-200))),RBType.AABB);

		//Rigidbody dyn_plane=new Rigidbody(10f,10f,new Transform(new Vector2(100f,50f)),false,new DynamicPlaneCollider(new Transform(new Vector2(100f,50f)), 100f),RigidbodyType.DYN_PLANE);
		//RigidBox box=new RigidBox(10.0f,10.0f,new Transform(new Vector2(50f,60f),55f),false,new BoxCollider(new Transform(new Vector2(50f,60f),55f), 100, 100));
		
		w.AddRigidbodyToWorld(box2);
		w.AddRigidbodyToWorld(rb4);
		w.AddRigidbodyToWorld(box);
		//w.AddRigidbodyToWorld(box4);
		//w.AddRigidbodyToWorld(box3);
		
		w.AddRigidbodyToWorld(rb);
		//w.AddRigidbodyToWorld(rb2);
		w.AddRigidbodyToWorld(rb3);
		
		
		w.AddRigidbodyToWorld(plane);
		//w.AddRigidbodyToWorld(dyn_plane);
		//w.AddRigidbodyToWorld(box);
		w.addSolvers();
		
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
