package SimplePhysics.Math;

public class Mat2x2 {
	public float x1;
	public float y1;
	public float x2;
	public float y2;
	/**
	 * x1 y1
	 * x2 y2
	 *
	 */
	public Mat2x2()
	{
		x1=0;
		y1=0;
		x2=0;
		y2=0;
	}
	public Mat2x2(float X1,float Y1,float X2,float Y2)
	{
		x1=X1;
		y1=Y1;
		x2=X2;
		y2=Y2;
	}
	public Mat2x2(Mat2x2 other)
	{
		this.x1=other.x1;
		this.x2=other.x2;
		this.y1=other.y1;
		this.y2=other.y2;
	}
	public Mat2x2 add(Mat2x2 other)
	{
		return new Mat2x2
		(
			this.x1+other.x1,	this.y1+other.y1,
			this.x2+other.x2,	this.y2+other.y2
		);
	}
	//x1 y1 * X1 Y1 = x1X1+y1X2 x1Y1+y1Y2 
	//x2 y2	  X2 Y2	  x2X1+y2X2 x2Y1+y2Y2
	public Mat2x2 mul(Mat2x2 other)
	{
		return new Mat2x2
		(
			this.x1*other.x1+this.y1*other.x2, 	this.x1*other.y1+this.y1*other.y2,
			this.x2*other.x1+this.y2*other.x2, 	this.x2*other.y1+this.y2*other.y2
		);
				
	}
	public float determinant()
	{
		return (x1*y2-x2*y1);
	}
	public float invDeterminant()
	{
		return (1f/(x1*y2-x2*y1));
	}
	public Mat2x2 mul(float other)
	{
		return new Mat2x2
		(
			this.x1*other, 	this.y1*other,
			this.x2*other,	this.y2*other
		);
				
	}
	//		x1 y1 * x 
	//  	x2 y2	y
	public Vector2 mul(Vector2 other)
	{
		return new Vector2
		(
			this.x1*other.X+this.y1*other.Y,
			this.x2*other.X+this.y2*other.Y
		);
	}
	public Mat2x2 transpose()
	{
		return new Mat2x2
		(
			this.x1,this.x2,
			this.y1,this.y2
		);
	}
	/*
	 * a b		 d -b
	 * c d		-c  a		
	 *	
	 * x1 y1	 y2 -y1
	 * x2 y2	-x2  x1
	 */
	public Mat2x2 inverse()
	{
		return new Mat2x2
		(
			 y2,  -y1,
			-x2,   x1
			
		).mul(this.invDeterminant());
		
	}
	public static Mat2x2 identity()
	{
		return new Mat2x2
		(
			1,0,
			0,1
		);
	}
	public static Mat2x2 Rotation(float O)
	{
		return new Mat2x2
		(
				(float)Math.cos((double)O),-(float)Math.sin((double)O),
				(float)Math.sin((double)O), (float)Math.cos((double)O)
		);
	}
}
