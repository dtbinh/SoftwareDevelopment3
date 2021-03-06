import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

public abstract class Ship extends Thread  implements java.io.Serializable
{
	// Current position.
	private Point2D position;
	private MoveStrat movement;
	public Ship()
	{
		position = new Point(0,0);
		movement = new MoveStrat(new DefaultMovement());
	}
	
	public Point2D GetPosition()
	{
		return position;
	}

	public void SetPosition(Point2D pos)
	{
		position = pos;
	}
	
	public MoveStrat GetMovement()
	{
		return movement;
	}

	public void SetMovement(ShipMove ms)
	{
		movement = new MoveStrat(ms);
	}
	
		
	
	
	// Run thread.
	public void run()
	{
		// The delay and message is to prove that the threads are working.
		try
		{
			Thread.sleep(50);
			System.out.println(this.toString());
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		position = movement.ExecuteMove(GetPosition());
	}
	
	
}
