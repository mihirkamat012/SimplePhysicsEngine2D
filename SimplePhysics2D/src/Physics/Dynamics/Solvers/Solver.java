package Physics.Dynamics.Solvers;

import java.util.ArrayList;

import Physics.Collision.Collision;

public abstract class Solver
{
	public abstract void solve(ArrayList<Collision> cols, float dt);
}