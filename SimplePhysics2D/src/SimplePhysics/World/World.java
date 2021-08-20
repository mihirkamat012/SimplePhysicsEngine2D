package SimplePhysics.World;

import java.util.ArrayList;
import java.util.Iterator;

import SimplePhysics.Collision.AABBCollider;
import SimplePhysics.Collision.CircleCollider;
import SimplePhysics.Collision.Collision;
import SimplePhysics.Collision.PlaneCollider;
import SimplePhysics.Dynamics.RBType;
import SimplePhysics.Dynamics.Rigidbody;
import SimplePhysics.Dynamics.Solvers.ImpulseSolver;
import SimplePhysics.Dynamics.Solvers.PositionSolver;
import SimplePhysics.Dynamics.Solvers.Solver;

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
		Solvers.add(new PositionSolver((.5f)));
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
			for(int j=i+1
					;j<Rigidbodies.size();j++)
			{
				//detect collision
				//fill collision list
				//c p aabb
				//  p aabb
				//    aabb
				if(Rigidbodies.get(i).equals(Rigidbodies.get(j)))
					break;
				if(Rigidbodies.get(i).getType()==RBType.CIRCLE)
				{
					if(Rigidbodies.get(j).getType()==RBType.CIRCLE)
					{
						//System.out.println("c c");
						cols.add(Rigidbodies.get(i).collider.test((CircleCollider)Rigidbodies.get(j).collider));
					}
					else if(Rigidbodies.get(j).getType()==RBType.PLANE)
					{
						//System.out.println("c p");
						cols.add(Rigidbodies.get(i).collider.test((PlaneCollider)Rigidbodies.get(j).collider));
					}
					else if(Rigidbodies.get(j).getType()==RBType.AABB)
					{
						//System.out.println("c aabb");
						cols.add(Rigidbodies.get(i).collider.test((AABBCollider)Rigidbodies.get(j).collider));
					}
					
				}
				else if(Rigidbodies.get(i).getType()==RBType.PLANE)
				{
					if(Rigidbodies.get(j).getType()==RBType.AABB)
					{
						//System.out.println("p aabb");
						cols.add(Rigidbodies.get(i).collider.getAsPlaneCollider().test((AABBCollider)Rigidbodies.get(j).collider));
					}
					
				}
				else if(Rigidbodies.get(i).getType()==RBType.AABB)
				{
					if(Rigidbodies.get(j).getType()==RBType.PLANE)
					{
						//System.out.println("aabb p");
						cols.add(Rigidbodies.get(i).collider.getAsAABBCollider().test((PlaneCollider)Rigidbodies.get(j).collider));
					}
					if(Rigidbodies.get(j).getType()==RBType.AABB)
					{
						//System.out.println("aabb aabb");
						cols.add(Rigidbodies.get(i).collider.getAsAABBCollider().test((AABBCollider)Rigidbodies.get(j).collider));
					}
					if(Rigidbodies.get(j).getType()==RBType.CIRCLE)
					{
						//System.out.println("aabb aabb");
						cols.add(Rigidbodies.get(i).collider.getAsAABBCollider().test((CircleCollider)Rigidbodies.get(j).collider));
					}
					
				}
				else if(Rigidbodies.get(i).getType()==RBType.AABB)
				{
					if(Rigidbodies.get(j).getType()==RBType.AABB)
					{
						//System.out.println("aabb aabb");
						cols.add(Rigidbodies.get(i).collider.getAsAABBCollider().test((AABBCollider)Rigidbodies.get(j).collider));
					}
					
					
				}
				else
					continue;
				
			}

		}
		for(Solver s:Solvers)
		{
			s.solve(cols,dt);//solve collisions
		}
		cols.clear();
		
	}

}
