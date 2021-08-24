package SimplePhysics.Dynamics;

import SimplePhysics.Collision.Collider;
import SimplePhysics.Math.*;

public class Rigidbody {
	public float mass;
	public float MOI;
	static int Index=0;
	RBType type;
	public Vector2 velocity;
	public float angularVelocity;
	
	
	public Vector2 acceleration;
	public float angularAcceleration;
	
	Vector2 netForce;
	float netTorque;
	public float gravityMultiplier=1f;
	Vector2 momentum;
	public Transform transform;
	public boolean isImmoveable;
	public Collider collider;
	public float c_res;
	
	/*public Rigidbody(float mass, float g_multiplier,Transform tr, boolean isImmoveable, Collider col)
	{
		type=RBType.CIRCLE;
		this.mass=mass;
		transform=tr;
		velocity=new Vector2();
		acceleration=new Vector2();
		gravityMultiplier=g_multiplier;
		netForce=new Vector2(0f,-9.81f*mass*gravityMultiplier);
		momentum=new Vector2();
		this.isImmoveable=isImmoveable;
		collider=col;
		collider.Center=this.transform;
		collider.setAttachedRigidbody(this);
		Index++;
		
	}*/
	public Rigidbody(float mass, float g_multiplier,float Restitution,Transform tr, boolean isImmoveable, Collider col,RBType Type)
	{
		c_res=Restitution;
		type=Type;
		this.mass=mass;
		transform=tr;
		velocity=new Vector2();
		acceleration=new Vector2();
		gravityMultiplier=g_multiplier;
		netForce=new Vector2(0f,-9.81f*mass*gravityMultiplier);
		momentum=new Vector2();
		this.isImmoveable=isImmoveable;
		collider=col;
		collider.Center=this.transform;
		collider.setAttachedRigidbody(this);
		Index++;
		
	}
	public RBType getType()
	{
		return type;
	}
	public void AddForce(Vector2 force,float dt)
	{
		netForce.addToSelf(force);
		ApplyNetForce(dt);
		netForce.addToSelf(force.not());
	}
	public void ApplyNetForce(float dt)
	{
		if(isImmoveable)
			return;
		acceleration=netForce.multiply(1.0f/mass);
		angularAcceleration=netTorque/MOI;
		acceleration.setMagnitude();
		ApplyVelocity(dt);
		
	}
	void ApplyVelocity(float dt)
	{
		velocity.addToSelf(acceleration.multiply(dt));
		angularVelocity+=angularAcceleration*dt;
		velocity.setMagnitude();
		momentum=velocity.multiply(mass);
		ApplyDisplacement(dt);
	}
	void ApplyDisplacement(float dt)
	{
		Vector2 newPos=transform.position.add(velocity.multiply(dt));
		transform.rotation+=(angularVelocity*dt);
		Vector2 dv=newPos.subtract(transform.position).multiply(1.0f/dt);
		transform.position.replaceVector(newPos);
		if(dv.magnitude<0.03f)
		{
			velocity=new Vector2();
		}
		else
		{
			velocity=dv;
		}
		//System.out.println(newPos.repr());
		transform.position.setMagnitude();
		
		collider.Center=this.transform;
		if(type==RBType.AABB)
		{
			//collider.getAsDynamicPlaneCollider().setCoords();
			//do nothing for now.
			 collider.getAsAABBCollider().setCoords();
		}
		
	}
	
}
