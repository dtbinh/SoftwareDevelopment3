import java.util.Vector;


public class GameGrid
{
	// Game grid.
	private Tile[][] sky = new Tile[4][4];
	// Holds all the previous states the grid as been in.
	//private Vector<GameGrid> previousStates;
	
	GameGrid()
	{
		for(int i =0; i < 4; i++)
			for(int j =0; j<4;j++)
				sky[i][j] = new Tile();
	}
	
	GameGrid(Vector<Ship> ships)
	{	
	}
	
	
	void SetSky (Tile tiles[][])
	{
		sky = tiles;
		
	}
	void SetTileCondition(int x, int y, boolean condition)
	{
		if (x >-1 && y > -1 && x < 4 && y < 4)
			sky[0][0].SetAllowedToEnter(condition);
		
	}
	
	Tile[][] GetSky()
	{
		return sky;
	}
	Tile GetTile(int col, int row)
	{
		return sky[col][row];
	}
	
	String UpdateVisualGrid(int col, int row)
	{
		// OBSERVER PATTERN.
		Tile t = sky[col][row];
		// Displays text for the labels the now.
		String info = "No Ships here.";
		if(!t.IsAllowedToEnter())
		{
			info = "NO ENTRY";
			return info;
		}
		// Check for player ship.
		for(Ship s : t.GetCurrentShips())
		{
			if(s.getClass() == MasterShip.class)
			{
				info = "PlayerShip here";
				if(t.GetCurrentShips().size()>1)
				{
					info += "| Num enemies: ";
					info += t.GetCurrentShips().size()-1;
				}
				return info;
			}
		}
		// If there is no player ship display how many enemies are in the tile.
		if(t.GetCurrentShips().size() > 0)
		{
			info = "Num enemies: ";
			info += t.GetCurrentShips().size();
			return info;
		}
		
		return info;

	}
	
	Boolean UpdateGrids(Vector<Ship> ships)
	{
		//previousStates.add(this);
		
		// Better way but this works for now.
		for(int i =0; i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				sky[i][j].GetCurrentShips().clear();
			}
		}
		
		for(Ship ship : ships)
		{
			int x,y;
			x = (int)ship.GetPosition().getX();
			y = (int) ship.GetPosition().getY();
				sky[x][y].AddShip(ship);
		}
		return true;
	}
	
	// Returns true if game is over.
	boolean CheckGrids()
	{
		boolean lost = false;
		// x,y that has player ship.
		int x=0,y = 0;
		for(int i =0; i <4; i++)
		{
			for(int j=0;j<4;j++)
			{
				// Check if tile contains master ship.
				for(Ship ship : sky[i][j].GetCurrentShips())
				{
					if(ship.getClass() == MasterShip.class)
					{
						x = i;
						y = j;
						break;
					}
					
				}
			}
		}
		
		// Check how many ships in scene.
		// One ship destroy it.
		if(sky[x][y].GetCurrentShips().size()-1 == 1)
			for(Ship s : sky[x][y].GetCurrentShips())
				if(s.getClass().toString() != "MasterShip")
					s = null;
		// Two ships. Check ship mode.
		if(sky[x][y].GetCurrentShips().size()-1 == 2)
		{	for(Ship s : sky[x][y].GetCurrentShips())
			{
				if(s.getClass().toString() == "MasterShip")
				{
					MasterShip ps = (MasterShip)s;
					if(ps.GetMode() == true)
					{
						lost = true;
					}
				}
			}
			if(!lost)
			{
				
			}
			
		}
		
		
		// Only one ship, remove it.
		if(sky[x][y].GetCurrentShips().size()-1 == 1)
			for(Ship s : sky[x][y].GetCurrentShips())
				if(s.getClass().toString() != "MasterShip")
					s = null;
		
		
		return lost;
	}
}
