import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;


public abstract class Ship
{
	// Path to image for graphic.
	private String filePath;
	// Previous positions.
	private Vector<Point2D> previousMoves = new Vector<Point2D>();
	// Current position.
	private Point2D position; 
	
	
	
	public Ship()
	{
		position = new Point(0,0);
	}
	
	public void MakeMove()
	{	
		// Add current position to list.
		previousMoves.add(position);
	//	Point2D prev = position;
		Random numGen = new Random();
		boolean valid = false;
		int x,y;

		// Check to ensure valid move - move to seperate method.
		while(valid == false)
		{
			valid = true;
			x = numGen.nextInt(3) -1;
			y = numGen.nextInt(3) -1;
			// Check if ship has moved.
			if(x == 0 && y == 0)
				valid = false;
			
			if (valid == true)
			{
				x += GetPosition().getX();
				y += GetPosition().getY();
			}
			// Check if ship is in tile range.
			if(x < 0 || x >3 || y < 0 || y >3)
				valid = false;
			// Check it is not moving to top left corner.
			if(x == 0 && y == 0)
				valid = false;
			
			// All positions valid. Set position.
			if (valid == true)
				position.setLocation(x,y);
		
		}
	}

	public void UndoMove()
	{
		
		SetPosition(previousMoves.lastElement());
		// Remove last move from list.
		previousMoves.remove(previousMoves.size()-1);
		
	}
	
	public String GetFilePath()
	{
		return filePath;
	}

	public void SetFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public Point2D GetPosition()
	{
		return position;
	}

	public void SetPosition(Point2D pos)
	{
		position = pos;
	}
	
	public Vector<Point2D> GetPreviousPositions()
	{
		return previousMoves;
	}

	public void SetPreviousMoves(Vector<Point2D> prev)
	{
		previousMoves = prev;
	}
	
}