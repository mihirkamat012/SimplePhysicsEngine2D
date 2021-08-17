package World;

import java.util.ArrayList;
import java.util.Iterator;

import Physics.Collision.CircleCollider;
import Physics.Collision.Collision;
import Physics.Collision.PlaneCollider;
import Physics.Dynamics.Rigidbody;
import Physics.Dynamics.RigidbodyType;
import Physics.Dynamics.Solvers.ImpulseSolver;
import Physics.Dynamics.Solvers.PositionSolver;
import Physics.Dynamics.Solvers.Solver;

public class World {
	float gravity;
	ArrayList<Rigidbody> Rigidbodies;
	ArrayList<Solver> Solvers;
	public World()
	{
		Rigidbodies=new ArrayList<Rigidbody>();
		Solvers=new ArrayList<Solver>();
		
	}
	public void addSolvers()
	{
		Solvers.add(new PositionSolver((.02f)));
		Solvers.add(new ImpulseSolver());
	}
	public ArrayList<Rigidbody> getRigidbodies()
	{
		return Rigidbodies;
	}
	public void AddRigidbodyToWorld(Rigidbody rb)
	{
		Rigidbodies.add(rb);
		
	}
	void RemoveRigidbodyFromWorld(Rigidbody rb)
	{
		
	}
	public void start(float dt)
	{
		
		while(true)
		{
			step(dt);
			try {
				Thread.sleep((long)(dt*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void step(float dt)
	{
		for(Rigidbody rb: Rigidbodies)
		{
			rb.ApplyNetForce(dt);
		}
	}
	public void resolveCollisions(float dt)
	{
		//Object a;
		ArrayList<Collision> cols=new ArrayList<Collision>();
		for(int i=0;i<Rigidbodies.size();i++)
		{
			for(int j=i+1;j<Rigidbodies.size();j++)
			{
				//detect collision
				//fill collision list
				if(Rigidbodies.get(i).equals(Rigidbodies.get(j)))
					break;
				if(Rigidbodies.get(i).getType()==RigidbodyType.BODY)
				{
					if(Rigidbodies.get(j).getType()==RigidbodyType.BODY)
					{
						cols.add(Rigidbodies.get(i).collider.test((CircleCollider)Rigidbodies.get(j).collider));
					}
					else
					{
						cols.add(Rigidbodies.get(i).collider.test((PlaneCollider)Rigidbodies.get(j).collider));
					}
				}
			}

		}
		for(Solver s:Solvers)
		{
			s.solve(cols,dt);//solve collisions
		}
		cols.clear();
		
	}

}
