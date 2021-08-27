package SimplePhysics.Collision;


import SimplePhysics.Math.Mathf;
import SimplePhysics.Math.Transform;
import SimplePhysics.Math.Vector2;
enum BoundsType
{
	X_MAX,
	Y_MAX,
	X_MIN,
	Y_MIN
}
class Bounds
{
	Vector2 P1;
	Vector2 P2;
	Vector2 Normal;
	BoundsType boundsType;
	public Bounds(Vector2 p1,Vector2 p2,BoundsType bt)
	{
		P1=p1;
		P2=p2;
		Normal=new Vector2(-P2.subtract(P1).normalized().Y,P2.subtract(P1).normalized().X);
		boundsType=bt;
	}
	public float get()
	{
		if(boundsType==BoundsType.X_MAX||boundsType==BoundsType.X_MIN)
		{
			return P1.add(P2).multiply(0.5f).X;
		}
		else if(boundsType==BoundsType.Y_MAX||boundsType==BoundsType.Y_MIN)
		{
			return P1.add(P2).multiply(0.5f).Y;
		}
		else
			return 0;
	}
}
public class AABBCollider extends Collider {

	Vector2 Width;
	Vector2 Height;
	Bounds minBoundsX;
	Bounds maxBoundsX;
	Bounds minBoundsY;
	Bounds maxBoundsY;
	public Vector2[] points;
	public float w;
	public float h;
	public AABBCollider(float width,float height,Transform center)
	{
		w=width;
		h=height;
		Width=new Vector2(width,0f);
		Height=new Vector2(0f,height);
		Center=center;
		points=new Vector2[4];
		//3 0
		//2 1
		//max X bounds=01
		//min y bounds=12
		//min x bounds=23
		//max y bounds=30

		points[0]=Center.position.add(Width.multiply(0.5f)).add(Height.multiply(0.5f));
		points[1]=Center.position.add(Width.multiply(0.5f)).add(Height.multiply(-0.5f));
		points[2]=Center.position.add(Width.multiply(-0.5f)).add(Height.multiply(-0.5f));
		points[3]=Center.position.add(Width.multiply(-0.5f)).add(Height.multiply(0.5f));
		maxBoundsX=new Bounds(points[0],points[1],BoundsType.X_MAX);
		minBoundsY=new Bounds(points[1],points[2],BoundsType.Y_MIN);
		minBoundsX=new Bounds(points[2],points[3],BoundsType.X_MIN);
		maxBoundsY=new Bounds(points[3],points[0],BoundsType.Y_MAX);
	}
	public void setCoords()
	{
		//System.out.println(maxBoundsY.get()+" "+minBoundsY.get());
		
		points[0]=Center.position.add(Width.multiply(0.5f)).add(Height.multiply(0.5f));
		points[1]=Center.position.add(Width.multiply(0.5f)).add(Height.multiply(-0.5f));
		points[2]=Center.position.add(Width.multiply(-0.5f)).add(Height.multiply(-0.5f));
		points[3]=Center.position.add(Width.multiply(-0.5f)).add(Height.multiply(0.5f));
		maxBoundsX=new Bounds(points[0],points[1],BoundsType.X_MAX);
		minBoundsY=new Bounds(points[1],points[2],BoundsType.Y_MIN);
		minBoundsX=new Bounds(points[2],points[3],BoundsType.X_MIN);
		maxBoundsY=new Bounds(points[3],points[0],BoundsType.Y_MAX);
	}
	public Collision test(CircleCollider other) {
		//System.out.println("aabb c");
		Collision c=new Collision();
		c.A=this.attachedRigidbody;
		c.B=other.attachedRigidbody;
		Vector2 halfExtents=new Vector2(this.w*0.5f,this.h*0.5f);
		Vector2 diff=other.Center.position.subtract(this.Center.position);
		Vector2 clamped=Mathf.clamp(diff, halfExtents.not(), halfExtents);
		Vector2 closest=this.Center.position.add(clamped);
		Vector2 colVector=closest.subtract(other.Center.position);
		c.collisionNormal=colVector.normalized().not();
		if(colVector.magnitude<=other.radius)
		{
			c.depth=-(-colVector.magnitude+other.radius)*2f;
			c.isColliding=true;
		}
		else
		{
			
			c.isColliding=false;
		}
		return c;
	}
	public Collision test(Collider other)
	{
		return other.test(this);
	}
	@Override
	public Collision test(PlaneCollider other) {
		//System.out.println("in plc method");
		Collision c=new Collision();
		c.A=this.attachedRigidbody;
		c.B=other.attachedRigidbody;
		c.collisionNormal=new Vector2(0f,-1f);
		
		if(this.minBoundsY.get()<=other.Center.position.Y)
		{
			
			c.depth=-other.Center.position.Y+this.minBoundsY.get();
			System.out.println(c.depth);
			c.isColliding=true;
		}
		else
		{
			c.isColliding=false;
		}
		// TODO Auto-generated method stub
		return c;
	}
	/**
	 * if(this.Center.position.X < other.Center.position.x + other.width &&
    this.Center.position.X + this.width > other.Center.position.x &&
    this.Center.position.Y < other.Center.position.y + other.height &&
    this.Center.position.X + this.height > other.Center.position.y)
{
    System.out.println("Collision Detected");
}
	 */
	public Collision test(AABBCollider other)
	{
		float permittance=0.1f;
		Collision c=new Collision();
		c.A=other.attachedRigidbody;//this box
		c.B=this.attachedRigidbody;//other box
		//maxx1 > minx2 && minx1 < maxx2 && maxy1 > miny2 && miny1 < maxy2
		
		if(this.maxBoundsX.get()>other.minBoundsX.get()&&
		this.minBoundsX.get()<other.maxBoundsX.get()&&
		this.maxBoundsY.get()>other.minBoundsY.get()&&
		this.minBoundsY.get()<other.maxBoundsY.get())
		{
			
			Vector2 D=other.Center.position.subtract(this.Center.position);
			float dy=Math.abs(Math.abs(D.Y)-(this.h+other.h)*0.5f);
			float dx=Math.abs(Math.abs(D.X)-(this.w+other.w)*0.5f);
			if(dx>=permittance&&dy<=permittance)
			{
				if(D.quad()==1||D.quad()==2)
				{
					System.out.println("aaa");
					c.collisionNormal=new Vector2(0f,-1f);
					//0 -1
					c.depth=(-D.Y+(this.h+other.h))*0.5f;
				}
				else
				{
					System.out.println("bbb");
					c.collisionNormal=new Vector2(0f,1f);
					//0 1
					c.depth=(D.Y-(this.h+other.h))*0.5f;
				}
				c.isColliding=true;
				System.out.println("z");
				
			}
			
			if(dy>=permittance&&dx<=permittance)
			{
				if(D.quad()==1||D.quad()==4)
				{
					//System.out.println("ccc");
					c.collisionNormal=new Vector2(-1f,0f);
					//-1 0
					c.depth=0.5f*(D.X-(this.w+other.w));
				}
				else
				{
					//System.out.println("ddd");
					c.collisionNormal=new Vector2(1f,0f);
					//1 0
					c.depth=0.5f*(D.X-(this.w+other.w));
				}
				
				
			}
			if(dy>=permittance&&dx>=permittance)
			{
				if(dy>=dx)
				{
					if(D.quad()==1||D.quad()==4)
					{
						System.out.println("ccc");
						c.collisionNormal=new Vector2(-1f,0f);
						//-1 0
						c.depth=0.5f*(D.X-(this.w+other.w));
					}
					else
					{
						System.out.println("ddd");
						c.collisionNormal=new Vector2(1f,0f);
						//1 0
						c.depth=0.5f*(D.X-(this.w+other.w));
					}
				}
				else if (dy<dx)
				{
					if(D.quad()==1||D.quad()==2)
					{
						System.out.println("aaa");
						c.collisionNormal=new Vector2(0f,-1f);
						//0 -1
						c.depth=(-D.Y+(this.h+other.h))*0.5f;
					}
					else
					{
						System.out.println("bbb");
						c.collisionNormal=new Vector2(0f,1f);
						//0 1
						c.depth=(D.Y-(this.h+other.h))*0.5f;
					}
					c.isColliding=true;
					System.out.println("z");
				}
				else
				{
					c.collisionNormal=new Vector2();
				}
				
			}
			c.isColliding=true;
			
		}
		else
		{
			c.collisionNormal=new Vector2();
			c.depth=0.0f;
			c.isColliding=false;
		}
		//finding collision normals
		return c;
	}
}
