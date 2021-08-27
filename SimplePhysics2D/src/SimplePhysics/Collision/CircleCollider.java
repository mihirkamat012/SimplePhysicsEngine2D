package SimplePhysics.Collision;


import SimplePhysics.Dynamics.Rigidbody;
import SimplePhysics.Math.*;

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
	public Collision test(Collider other)
	{
		return other.test(this);
	}
	public Collision test(CircleCollider other)
	{
		Collision c=new Collision();
		c.A=this.attachedRigidbody;
		c.B=other.attachedRigidbody;
		c.collisionNormal=c.B.transform.position.subtract(c.A.transform.position).normalized();
		c.depth=(c.A.transform.position.subtract(c.B.transform.position).getSqrMagnitude()-(this.radius+other.radius)*(this.radius+other.radius));
		//System.out.println(c.depth+" ");
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
	public Collision test(AABBCollider other) {
		
		Collision c=new Collision();
		c.A=other.attachedRigidbody;
		c.B=this.attachedRigidbody;
		Vector2 halfExtents=new Vector2(other.w*0.5f,other.h*0.5f);
		Vector2 diff=this.Center.position.subtract(other.Center.position);
		Vector2 clamped=Mathf.clamp(diff, halfExtents.not(), halfExtents);
		Vector2 closest=other.Center.position.add(clamped);
		Vector2 colVector=closest.subtract(this.Center.position);
		c.collisionNormal=colVector.normalized().not();
		if(colVector.magnitude<=this.radius)
		{
			c.depth=(-colVector.magnitude+this.radius)*2f;
			c.isColliding=true;
		}
		else
		{
			
			c.isColliding=false;
		}
		return c;
		// TODO Auto-generated method stub
		
	}
	//@Override
	/*public void drawCollider(Graphics g) {
		g.drawOval((int)this.attachedRigidbody.transform.position.X-(int)this.radius, 
				-(int)this.attachedRigidbody.transform.position.X-(int)this.radius, 
				2*(int)this.radius, 
				2*(int)this.radius);	
		
		// TODO Auto-generated method stub
		
	}*/
	
	
}