package Math;
//added comment
public class Transform
{
	public Vector2 position;
	public float rotation;
	public Vector2 scale;
	public Transform(Vector2 position,float rotation,Vector2 scale)
	{
		this.position=position;
		this.rotation=rotation;
		this.scale=scale;
	}
	public Transform(Vector2 position,float rotation)
	{
		this.position=position;
		this.rotation=rotation;
		this.scale=new Vector2(1f/1.414f,1f/1.414f);
	}
	public Transform(Vector2 position)
	{
		this.position=position;
		this.rotation=0f;
		this.scale=new Vector2(1f/1.414f,1f/1.414f);
	}
	
	public Transform()
	{
		this.position=new Vector2();
		this.rotation=0f;
		this.scale=new Vector2();
	}
	
}