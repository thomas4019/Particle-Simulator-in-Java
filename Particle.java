import java.awt.*;
import java.awt.image.*;

class Particle
{
	static int MAX = Program.WIDTH * Program.HEIGHT;
	static int[] data = new int[MAX];
	static Vector2D a = Vector2D.NORTH.clone().scale(.1);

	Vector2D.Rectangular loc;
	Vector2D.Rectangular v;

	Vector2D.Rectangular dest;

	int color;

	static int speed = 10;

	Particle(int x,int y,int color)
	{
		loc = new Vector2D.Rectangular(x,y);
		v = (Vector2D.Rectangular)Vector2D.random().scale(100);
		v.setMagnitude(2 + Math.random()*3);
		this.color = color;
	}

	public void update()
	{
		if(dest != null)
		{
			a.set((dest.x - loc.x) + Math.random() -.5, (dest.y - loc.y) + Math.random() - .5);
			a.setMagnitude(speed < 10 ? 10 : speed);
			//v.scale(.01);
			//a.setMagnitude(1);
			v.scale(.1);
		}


		v.add(a);
		loc.add(v);
		//loc.add(Vector2D.random().scale(.3));

		if(loc.getX() > 2 && loc.getX() < Program.WIDTH-2)
		{
			if(loc.getY() > 2 && loc.getY() < Program.HEIGHT-2)
			{
				int v = (int)loc.getX() + ((int)loc.getY() * Program.WIDTH);
				add(v,0xFF222222);
				add(v + 1,0xFF111111);
				add(v - 1,0xFF111111);
				add(v + Program.WIDTH,0xFF111111);
				add(v - Program.WIDTH,0xFF111111);

				add(v + 1 + Program.WIDTH,0xFF070707);
				add(v + 1 - Program.WIDTH,0xFF070707);
				add(v - 1 + Program.WIDTH,0xFF070707);
				add(v - 1 - Program.WIDTH,0xFF070707);

				add(v + 1 + 2 * Program.WIDTH,0xFF030303);
				add(v + 1 - 2 * Program.WIDTH,0xFF030303);
				add(v - 1 + 2 * Program.WIDTH,0xFF030303);
				add(v - 1 - 2 * Program.WIDTH,0xFF030303);

				add(v + 2 + Program.WIDTH,0xFF030303);
				add(v + 2 - Program.WIDTH,0xFF030303);
				add(v - 2 + Program.WIDTH,0xFF030303);
				add(v - 2 - Program.WIDTH,0xFF030303);

				add(v + 2 * Program.WIDTH,0xFF030303);
				add(v - 2 * Program.WIDTH,0xFF030303);

				add(v + 2,0xFF030303);
				add(v - 2,0xFF030303);

				//data[(int)loc.getX() + Program.WIDTH + ((int)loc.getY() * Program.WIDTH)] = 0xFF333333;
				//data[(int)loc.getX() + 1 + ((int)loc.getY() * Program.WIDTH)] = 0xFF333333;
				//data[(int)loc.getX() + ((int)loc.getY() * Program.WIDTH)] = 0xFFFFFFFF;
				//data[(int)loc.getX() - 1 + ((int)loc.getY() * Program.WIDTH)] = 0xFF333333;
				//data[(int)loc.getX() - Program.WIDTH + ((int)loc.getY() * Program.WIDTH)] = 0xFF333333;
			}
			else
			{
				v.y *= -1;
			}
		}
		else
		{
			v.x *= -1;
		}
	}

	public void add(int v,int c)
	{
		int old = data[v];

		int red = Math.min(((old & 0x00FF0000) >> 16) + ((c & 0x00FF0000) >> 16),255);
		int green = Math.min(((old & 0x0000FF00) >> 8) + ((c & 0x0000FF00) >> 8),255);
		int blue = Math.min(((old & 0x000000FF)) + ((c & 0x000000FF)),255);

		//System.out.println(red + " " + green + " " + blue);

		data[v] = 0xFF000000 + (red << 16) + (green << 8) + blue;
	}

	public void show()
	{
		if(loc.getX() >= 0 && loc.getX() < Program.WIDTH && loc.getY() >= 0 && loc.getY() < Program.HEIGHT)
		{
			data[(int)loc.getX() + ((int)loc.getY() * Program.WIDTH)] = color;
		}
	}
}