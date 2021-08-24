package SimplePhysics.Collision;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import SimplePhysics.Dynamics.RBType;
import SimplePhysics.Dynamics.Rigidbody;
import SimplePhysics.Math.Transform;
import SimplePhysics.Math.Vector2;

public abstract class Collider {
	public static Canvas canvas;
	public BufferStrategy bs;
	public Graphics g;
	public Transform Center;
	public static int rad;
	public Graphics gg;
	Rigidbody attachedRigidbody;
	public void setAttachedRigidbody(Rigidbody rb)
	{
		this.attachedRigidbody=rb;
	}
	public abstract Collision test(CircleCollider other);
	public abstract Collision test(PlaneCollider other); 
	public abstract Collision test(AABBCollider other);
	public void setCanvas(Canvas c)
	{
		canvas=c;
	}
	public static void setGraphics() {
		
	}
	public CircleCollider getAsCircleCollider()
	{
		return (CircleCollider)this;
	}
	public PlaneCollider getAsPlaneCollider()
	{
		return (PlaneCollider)this;
	}
	public AABBCollider getAsAABBCollider()
	{
		return (AABBCollider)this;
	}
	public void drawCollider(Graphics g)
	{
		
		if(attachedRigidbody.getType()==RBType.CIRCLE)
		{
			g.drawOval((int)attachedRigidbody.transform.position.X-(int)attachedRigidbody.collider.getAsCircleCollider().radius, 
					-(int)attachedRigidbody.transform.position.Y-(int)attachedRigidbody.collider.getAsCircleCollider().radius, 
					2*(int)attachedRigidbody.collider.getAsCircleCollider().radius, 
					2*(int)attachedRigidbody.collider.getAsCircleCollider().radius);
			//bs.show();
			//g.dispose();
			//System.out.println("p");
			//rb.collider.getAsCircleCollider().drawCollider(g);
		}
		
		else if(attachedRigidbody.getType()==RBType.PLANE)
		{
			
			g.drawLine((int)attachedRigidbody.collider.getAsPlaneCollider().P1.X, 
					-(int)attachedRigidbody.collider.getAsPlaneCollider().P1.Y, 
					(int)attachedRigidbody.collider.getAsPlaneCollider().P2.X, 
					-(int)attachedRigidbody.collider.getAsPlaneCollider().P2.Y);
			//bs.show();
			//g.dispose();
			//System.out.println("q");
			
		}
		else if(attachedRigidbody.getType()==RBType.AABB)
		{
			drawPolygon(attachedRigidbody.collider.getAsAABBCollider().Center.position,
					attachedRigidbody.collider.getAsAABBCollider().points,g);
			//bs.show();
			//g.dispose();
			//System.out.println("r");
		}
		else
			return;
	}
	public void drawPolygon(Vector2 center,Vector2[] points,Graphics g)
	{
		g.drawOval
		(
				(int)(center.X-Collider.rad*0.5f),
				-(int)(center.Y-Collider.rad*0.5f),
				Collider.rad,Collider.rad
		);
		
		for(int i=0;i<points.length;i++)
		{
			
			if(i==points.length-1)
			{
				
				g.drawLine(
						(int)points[i].X,
						-(int)points[i].Y,
						(int)points[0].X,
						-(int)points[0].Y);

			}
			else
			{
				
				g.drawLine(
						(int)points[i].X,
						-(int)points[i].Y, 
						(int)points[i+1].X, 
						-(int)points[i+1].Y
						);
			}
		}
		
	}
}
