package SimplePhysics.Dynamics;

import SimplePhysics.Collision.Collider;
import SimplePhysics.Collision.PlaneCollider;
import SimplePhysics.Math.Transform;

public class RigidPlane extends Rigidbody{

	public RigidPlane(Transform tr, float length,RBType t) {
		
		
		super(Float.POSITIVE_INFINITY, 0f,1f, tr, true, new PlaneCollider(tr,length),t);
		//type=RBType.PLANE;
		// TODO Auto-generated constructor stub
	}
	public RBType getType()
	{
		return type;
	}

}
