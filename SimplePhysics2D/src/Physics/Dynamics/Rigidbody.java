package Physics.Dynamics;

import Math.*;
import Physics.Collision.Collider;

public class Rigidbody {
	public float mass;
	public float momentOfInertia;
	static int Index=0;
	RigidbodyType type;
	public Vector2 velocity;
	float AngularVelocity;
	
	
	public Vector2 acceleration;
	public float angularAcceleration;
	
	Vector2 netForce;
	float netTorque;
	public float gravityMultiplier=1f;
	Vector2 momentum;
	public Transform transform;
	public boolean isImmoveable;
	public Collider collider;
	
	
	public Rigidbody(float mass, float g_multiplier,Transform tr, boolean isImmoveable, Collider col)
	{
		type=RigidbodyType.BODY;
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
	public RigidbodyType getType()
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
		angularAcceleration=netTorque/momentOfInertia;
		acceleration.setMagnitude();
		ApplyVelocity(dt);
		
	}
	void ApplyVelocity(float dt)
	{
		velocity.addToSelf(acceleration.multiply(dt));
		AngularVelocity+=angularAcceleration*dt;
		velocity.setMagnitude();
		momentum=velocity.multiply(mass);
		ApplyDisplacement(dt);
	}
	void ApplyDisplacement(float dt)
	{
		transform.position.addToSelf(velocity.multiply(dt));
		transform.rotation+=(AngularVelocity*dt);
		//velocity=newPos.subtract(transform.position).multiply(1.0f/dt);
		//transform.position.replaceVector(newPos);
		//
		//System.out.println(newPos.repr());
		transform.position.setMagnitude();
		
		collider.Center=this.transform;
		
	}
	
}
