package SimplePhysics.Math;

public class Mathf
{
	public static float clamp(float value,float min,float max)
	{
		return Math.max(Math.min(value, max), min);
	}
	public static Vector2 clamp(Vector2 v,float min,float max)
	{
		return new Vector2(Mathf.clamp(v.X, min, max),Mathf.clamp(v.Y, min, max));
	}
	public static Vector2 clamp(Vector2 v,Vector2 min,Vector2 max)
	{
		return new Vector2(
				Mathf.clamp(v.X, min.X, max.X),
				Mathf.clamp(v.Y, min.Y, max.Y)
				);
	}
}