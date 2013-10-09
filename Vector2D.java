/*
Version: 1.1
*/
abstract class Vector2D implements java.io.Serializable,Cloneable
{
	public static final Vector2D ZERO = new Vector2D.Rectangular(0,0);

	public static final Vector2D WEST = new Vector2D.Rectangular(-1,0);
	public static final Vector2D EAST = new Vector2D.Rectangular(1,0);
	public static final Vector2D NORTH = new Vector2D.Rectangular(0,1);
	public static final Vector2D SOUTH = new Vector2D.Rectangular(0,-1);

	abstract double getX(); //Length
	abstract double getY(); //Height

	abstract void setX(double d);
	abstract void setY(double d);

	abstract double getAngle();
	abstract double getMagnitude();

	abstract void setAngle(double d);
	abstract void setMagnitude(double d);

	abstract void set(double x,double y);
	abstract void setPolar(double r,double angle);

	abstract Vector2D scale(double scalar);
	public abstract Vector2D clone();

	public static class Rectangular extends Vector2D
	{
		double x;
		double y;
		double z;

		Rectangular(double x,double y)
		{
			set(x,y);
		}

		public void set(Vector2D other)
		{
			set(other.getX(), other.getY());
		}

		public void set(double x,double y)
		{
			this.x = x;
			this.y = y;
		}

		public void setPolar(double r,double angle)
		{
			x = r * Math.cos(angle);
			y = r * Math.sin(angle);
		}

		public double getX()
		{
			return x;
		}

		public double getY()
		{
			return y;
		}

		public void setX(double x)
		{
			this.x = x;
		}

		public void setY(double y)
		{
			this.y = y;
		}

		public double getAngle()
		{
			return Math.atan2(y,x);
		}

		public double getMagnitude()
		{
			return Math.sqrt(x*x + y*y + z*z);
		}

		public void setAngle(double angle)
		{
			setPolar(getMagnitude(), angle);
		}

		public void setMagnitude(double r)
		{
			setPolar(r, getAngle());
		}

		public Vector2D scale(double scalar)
		{
			x *= scalar;
			y *= scalar;
			return this;
		}

		public void mod(Vector2D other)
		{
			x %= other.getX();
			y %= other.getY();
		}

		public Vector2D clone()
		{
			return new Rectangular(x,y);
		}

		public String toString()
		{
			return "Vector.Rectangular: (" + x + "," + y + ")";
		}
	}

	public static class Polar extends Vector2D
	{
		public static Polar UNIT = new Polar(1,0);

		double angle;
		double r;

		Polar(double r,double angle)
		{
			setPolar(r,angle);
		}

		public void set(Vector2D other)
		{
			setPolar(other.getMagnitude(), other.getAngle());
		}

		public void set(double x,double y)
		{
			r = Math.sqrt(x*x + y*y);
			angle = Math.atan2(y,x);
		}

		public void setPolar(double r,double angle)
		{
			this.r = r;
			this.angle = angle;
		}

		public double getX()
		{
			return r * Math.cos(angle);
		}

		public double getY()
		{
			return r * Math.sin(angle);
		}

		public void setX(double x)
		{
			set(x,getY());
		}

		public void setY(double y)
		{
			set(getX(),y);
		}

		public double getAngle()
		{
			return angle;
		}

		public double getMagnitude()
		{
			return r;
		}

		public void setAngle(double angle)
		{
			this.angle = angle;
		}

		public void setMagnitude(double r)
		{
			this.r = r;
		}

		public Vector2D scale(double scalar)
		{
			r *= scalar;
			return this;
		}

		public Vector2D clone()
		{
			return new Polar(r,angle);
		}

		public String toString()
		{
			return "Vector.Polar: (" + r + "," + angle + ")";
		}
	}

	public void add(Vector2D other)
	{
		set(getX() + other.getX(),getY() + other.getY());
	}

	public void subtract(Vector2D other)
	{
		set(getX() - other.getX(),getY() - other.getY());
	}

	public void rotate(double theta)
	{
		setPolar(getMagnitude(), getAngle() + theta);
	}

	public boolean equals(Object o)
	{
		return o instanceof Vector2D && ((Vector2D)o).getX() == getX() && ((Vector2D)o).getY() == getY();
	}

	public static double distanceSquared(Vector2D v1,Vector2D v2)
	{
		return (v1.getX() - v2.getX()) * (v1.getX() - v2.getX()) + (v1.getY() - v2.getY()) * (v1.getY() - v2.getY());
	}

	public static double distance(Vector2D v1,Vector2D v2)
	{
		return Math.sqrt(distanceSquared(v1,v2));
	}

	public static Vector2D random()
	{
		return new Vector2D.Rectangular(Math.random()*2-1,Math.random()*2-1);
	}

	public static Vector2D sum(Vector2D a,Vector2D b)
	{
		return new Vector2D.Rectangular(a.getX() + b.getX(), a.getY() + b.getY());
	}

	public static Vector2D difference(Vector2D a,Vector2D b)
	{
		return new Vector2D.Rectangular(a.getX() - b.getX(), a.getY() - b.getY());
	}
}