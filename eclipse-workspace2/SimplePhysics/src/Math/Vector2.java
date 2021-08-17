package Math;
public class Vector2 {
	public float X;
	public float Y;
	public float magnitude;
	public Vector2(float x,float y)
	{
		X=x;
		Y=y;
		magnitude=(float)Math.sqrt(X*X+Y*Y);
	}
	public Vector2(Vector2 b)
	{
		X=b.X;
		Y=b.Y;
		magnitude=b.magnitude;
	}
	public void replaceVector(Vector2 repl)
	{
		X=repl.X;
		Y=repl.Y;
		magnitude=repl.getMagnitude();
	}
	public Vector2()
	{
		X=0;
		Y=0;
		magnitude=0f;
	}
	public void setMagnitude()
	{
		magnitude=(float)Math.sqrt(X*X+Y*Y);
	}
	public float getMagnitude()
	{
		return (float)Math.sqrt(X*X+Y*Y);
	}
	public float getSqrMagnitude()
	{
		return (X*X+Y*Y);
	}
	public Vector2 add(Vector2 b)
	{
		return new Vector2(X+b.X,Y+b.Y);
	}
	public void addToSelf(Vector2 b)
	{
		X+=b.X;
		Y+=b.Y;
	}
	public Vector2 subtract(Vector2 b)
	{
		return new Vector2(X-b.X,Y-b.Y);
	}
	public float dot(Vector2 b)
	{
		return X*b.X+Y*b.Y;
	}
	public Vector2 multiply(float f)
	{
		return new Vector2(X*f,Y*f);
	}
	public float crossMagnitude(Vector2 b)
	{
		return X*b.Y-Y*b.X;
	}
	public void reset()
	{
		X=0f;
		Y=0f;
	}
	public Vector2 not()
	{
		return new Vector2(-X,-Y);
	}
	public String repr()
	{
		return "<"+Float.toString(X)+","+Float.toString(Y)+">";
	}
	public Vector2 reflect(Vector2 normal)
	{
		
		//       Vector2 I=
		//magnitude*(I-2(N.I)N)
		//I.subtract(2(N.I)N)
		//I.subtract(	2 * N.dot(I) * N)
		//this.refreshMagnitude();
		return this.normalized().subtract(normal.normalized().multiply(2*normal.normalized().dot(this.normalized()))).multiply(this.magnitude);
	}
	public Vector2 normalized()
	{
		return new Vector2(X/magnitude,Y/magnitude);
	}
	
}
