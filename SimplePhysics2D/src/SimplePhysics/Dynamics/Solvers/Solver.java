package SimplePhysics.Dynamics.Solvers;

import java.util.ArrayList;

import SimplePhysics.Collision.Collision;

public abstract class Solver
{
	public abstract void solve(ArrayList<Collision> cols, float dt);
}