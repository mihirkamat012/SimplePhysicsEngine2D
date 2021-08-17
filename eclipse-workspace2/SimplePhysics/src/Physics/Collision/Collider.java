package Physics.Collision;

import Math.Transform;
import Physics.Dynamics.Rigidbody;

public abstract class Collider {
	public Transform Center;
	Rigidbody attachedRigidbody;
	public void setAttachedRigidbody(Rigidbody rb)
	{
		this.attachedRigidbody=rb;
	}
	public abstract Collision test(CircleCollider other);
	public abstract Collision test(PlaneCollider other); 
	public abstract Collision test(BoxCollider other);
	public CircleCollider getAsCircleCollider()
	{
		return (CircleCollider)this;
	}
	public BoxCollider getAsBoxCollider()
	{
		return (BoxCollider)this;
	}
	public PlaneCollider getAsPlaneCollider()
	{
		return (PlaneCollider)this;
	}
	
}
