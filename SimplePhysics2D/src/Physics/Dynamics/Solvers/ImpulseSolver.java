package Physics.Dynamics.Solvers;
import java.util.ArrayList;

import Math.Vector2;
import Physics.Collision.Collision;
public class ImpulseSolver extends Solver {
	public void solve(ArrayList<Collision> cols, float dt)
	{
		for(Collision col:cols)
		{
			if(!col.isColliding)
				continue;
			else {
			Vector2 rVelocity=col.B.velocity.subtract(col.A.velocity);//vel B relative to vel A
			float InvMassA=1.0f/col.A.mass;
			float InvMassB=1.0f/col.B.mass;
			//A is static in relative space
			//B is moving with velocity B-A
			//consider normal from A to B ()
			float s=col.collisionNormal.dot(rVelocity);
			if(s>=0)
				continue;
			float j=-(2*s)/(InvMassA+InvMassB);
			Vector2 impulse=col.collisionNormal.multiply(j);
			if(!col.A.isImmoveable)
			col.A.velocity.addToSelf(impulse.multiply(InvMassA).not());
			if(!col.B.isImmoveable)
			col.B.velocity.addToSelf(impulse.multiply(InvMassB));
			}
			//System.out.print(col.isColliding);
		}
		//System.out.println();
	}
}
