package SimplePhysics.Dynamics.Solvers;

import java.util.ArrayList;

import SimplePhysics.Collision.Collision;

public class PositionSolver extends Solver{

	float Resolution;
	public PositionSolver(float res)
	{
		Resolution=res;
	}
	public void solve(ArrayList<Collision> cols, float dt) {
		
		for(Collision col:cols)
		{
			if(col==null)
				continue;
			if(!col.isColliding)
				continue;
			else {
			if(col.A.isImmoveable)
			{
				col.B.transform.position.addToSelf(col.collisionNormal.multiply(-col.depth*Resolution));
				//System.out.println("aaa"+col.collisionNormal.repr());

			}
			else if(col.B.isImmoveable)
			{
				col.A.transform.position.addToSelf(col.collisionNormal.multiply(col.depth*Resolution));
				//System.out.println("bbb");
			}
			else if(!col.A.isImmoveable&&!col.B.isImmoveable)
			{
			col.A.transform.position.addToSelf(col.collisionNormal.multiply(-col.depth*Resolution*0.5f));
			col.B.transform.position.addToSelf(col.collisionNormal.multiply(col.depth*Resolution*0.5f));
			System.out.println(col.depth);
			}
			
			else
			{
				continue;
			}
			}
		}
	}

}
