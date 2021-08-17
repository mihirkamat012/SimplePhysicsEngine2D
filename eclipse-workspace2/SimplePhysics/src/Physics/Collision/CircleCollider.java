package Physics.Collision;

import Math.*;
import Physics.Dynamics.Rigidbody;

public class CircleCollider extends Collider{
	public float radius;
	
	public CircleCollider(float r,Transform center)
	{
		radius=r*center.scale.getMagnitude();
		Center=center;
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
	public Collision test(CircleCollider other)
	{
		Collision c=new Collision();
		c.A=this.attachedRigidbody;
		c.B=other.attachedRigidbody;
		c.collisionNormal=c.B.transform.position.subtract(c.A.transform.position).normalized();
		c.depth=(c.A.transform.position.subtract(c.B.transform.position).getSqrMagnitude()-(this.radius+other.radius)*(this.radius+other.radius));
		System.out.println(c.depth+" ");
		if(c.A.transform.position.subtract(c.B.transform.position).getSqrMagnitude()<=(this.radius+other.radius)*(this.radius+other.radius))
		{
			c.isColliding=true;
			//System.out.println("a"+c.isColliding+" "+c.A.transform.position.repr());
		}
		else
		{
			c.isColliding=false;
			//
			//System.out.println("a"+c.isColliding);

		}
		return c;
	}
	@Override
	public Collision test(PlaneCollider other) {
		Collision c=new Collision();
		c.A=other.attachedRigidbody;
		c.B=this.attachedRigidbody;
		//this is essentially a y=c line
		c.collisionNormal=new Vector2(0f,1f);
		c.depth=c.B.transform.position.Y-other.Center.position.Y-this.radius;
		if(c.B.transform.position.X>=other.P1.X&&
				c.B.transform.position.X<=other.P2.X&&
				c.B.transform.position.Y-other.Center.position.Y<=this.radius)
		{
			c.isColliding=true;
		}
		else
			c.isColliding=false;
		// TODO Auto-generated method stub
		return c;
		
	}
	@Override
	public Collision test(BoxCollider other) {
		// TODO Auto-generated method stub
		return null;
	}
}