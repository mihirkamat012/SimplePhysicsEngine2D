package SimplePhysics.Collision;


import SimplePhysics.Dynamics.Rigidbody;
import SimplePhysics.Math.Transform;
import SimplePhysics.Math.Vector2;

public class PlaneCollider extends Collider {

	
	float Length;
	public Vector2 P1;
	public Vector2 P2;
	public PlaneCollider(Transform center,float length)
	{
		Center=center;
		Length=length;
		
		P1=center.position.add(new Vector2(-length,0f));
		P2=center.position.add(new Vector2(length,0f));
	}
	public void setAttachedRigidbody(Rigidbody rb)
	{
		this.attachedRigidbody=rb;
	}
	public Collider getAsCollider()
	{
		return (Collider)this;
	}
	public Rigidbody getAttachedRigidbody()
	{
	 return attachedRigidbody;
	}
	public Collision test(Collider other)
	{
		return other.test(this);
	}
	public Collision test(CircleCollider other) {
		Collision c=new Collision();
		c.A=this.attachedRigidbody;
		c.B=other.attachedRigidbody;
		//this is essentially a y=c line
		c.collisionNormal=new Vector2(0f,1f);
		c.depth=other.attachedRigidbody.transform.position.Y-this.Center.position.Y-other.radius;
		if(other.attachedRigidbody.transform.position.X>=P1.X&&other.attachedRigidbody.transform.position.X<=P2.X&&other.attachedRigidbody.transform.position.Y-this.Center.position.Y<=other.radius)
		{
			c.isColliding=true;
		}
		else
			c.isColliding=false;
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collision test(PlaneCollider other) {
		// No Plane-plane collisions
		Collision c=new Collision();
		c.isColliding=false;
		return c;
	}
	@Override
	public Collision test(AABBCollider other) {
		Collision c=new Collision();
		c.A=other.attachedRigidbody;
		c.B=this.attachedRigidbody;
		c.collisionNormal=new Vector2(0f,1f);
		
		if(other.minBoundsY.get()<=this.Center.position.Y)
		{
			
			c.depth=this.Center.position.Y-other.minBoundsY.get();
			System.out.println(c.depth);
			c.isColliding=true;
		}
		else
		{
			c.isColliding=false;
		}
		// TODO Auto-generated method stub
		return c;
	}
	
	
	
	
	/**
	public Collision test(BoxCollider other) {
		figuring this out...
		return null;
	}
	*/

}
