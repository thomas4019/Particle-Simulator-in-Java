class Program
{
	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;

	public static void main(String args[])
	{
		Particle[] particles = new Particle[10000];

		for(int i = 0; i < particles.length; i++)
		{
			particles[i] = new Particle((int)(Math.random()*WIDTH),(int)(Math.random()*HEIGHT),(int)(Math.random()*Integer.MAX_VALUE));
		}

		Vector2D.Rectangular last = new Vector2D.Rectangular(0,0);

		for(Particle p : particles)
		{
			p.dest = last;
			last = p.loc;
		}

		GameWindow window = new GameWindow(particles);

		while(true)
		{
			for(Particle p : particles)
				p.update();

			window.repaint();

			try
			{
				Thread.sleep(50);
			}
			catch(Exception e){}
		}
	}
}