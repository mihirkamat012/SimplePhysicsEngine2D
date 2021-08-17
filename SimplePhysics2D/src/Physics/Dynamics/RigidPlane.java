package Physics.Dynamics;

import Math.Transform;
import Physics.Collision.Collider;
import Physics.Collision.PlaneCollider;

public class RigidPlane extends Rigidbody{

	public RigidPlane(Transform tr, float length) {
		
		
		super(Float.POSITIVE_INFINITY, 0f, tr, true, new PlaneCollider(tr,length));
		type=RigidbodyType.PLANE;
		// TODO Auto-generated constructor stub
	}
	public RigidbodyType getType()
	{
		return type;
	}

}
