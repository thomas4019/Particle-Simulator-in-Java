import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

class GameWindow extends JFrame implements MouseMotionListener,MouseListener
{
	MemoryImageSource source;
	Image image;

	Particle[] particles;

	GameWindow(Particle[] particles)
	{
		this.particles = particles;

		setSize(Program.WIDTH, Program.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);

		addMouseListener(this);
		addMouseMotionListener(this);

		Arrays.fill(Particle.data, 0xFF000000);

		source = new MemoryImageSource(Program.WIDTH,Program.HEIGHT,Particle.data,0,Program.WIDTH);
		source.setAnimated(true);
		source.setFullBufferUpdates(true);
		image = createImage(source);

		setVisible(true);
	}

	public void mouseMoved(MouseEvent e)
	{
		/*if(particles[0].dest == null)
		{
			for(Particle p : particles)
			{
				p.dest = new Vector2D.Rectangular(0,0);
			}
		}

		for(Particle p : particles)
			p.dest.set(e.getX() + Math.random()*5,e.getY() + Math.random()*5);*/

			particles[0].dest.set(e.getX() + Math.random()*5,e.getY() + Math.random()*5);
	}

	public void mouseDragged(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{

		if(e.getButton() == MouseEvent.BUTTON1)
		{
			for(Particle p : particles)
			{
				p.v = new Vector2D.Rectangular(e.getX() - p.loc.x, e.getY() - p.loc.y);
			}
		}
		else
		{
			for(Particle p : particles)
			{
				p.v = (Vector2D.Rectangular)Vector2D.random().scale(100);
			}
		}

	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void paint(Graphics g)
	{
		source.newPixels();
		g.drawImage(image,0,0,null);
		Arrays.fill(Particle.data, 0xFF000000);
	}
}