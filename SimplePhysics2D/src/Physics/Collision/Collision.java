package Physics.Collision;

import java.util.ArrayList;

import Math.*;
import Physics.Dynamics.Rigidbody;

public class Collision {
	public Rigidbody A;
	public Rigidbody B;
	public Vector2 collisionPoint;//for box collisions
	public Vector2 BinA;
	public Vector2 AinB;
	public Vector2 collisionNormal;//normal from A to B i.e centerB-CenterA.normalized() for circle
	public float depth;
	public boolean isColliding;
	//Vector2 diffABNormalized;
}

