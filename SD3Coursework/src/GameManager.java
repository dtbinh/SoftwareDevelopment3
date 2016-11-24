import java.util.Random;
import java.util.Vector;


public class GameManager
{
		Vector<Ship> ships = new Vector<Ship>();
		private GameGrid grid = new GameGrid();
		
		
		
		GameManager()
		{
			// Set top grid to prevent entry.
			grid.SetTileCondition(0, 0, false);
			MasterShip playerShip = new MasterShip();
			ships.add(playerShip);
		
		}
		
		
		
		void Update()
		{	
			if(ships.size()>0)
			for(Ship ship : ships)
			{
				ship.MakeMove();
			}
			// Factory pattern.
			Random rand = new Random();
			int chance = rand.nextInt(3);
			// 33% chance to create new ship
			if (chance == 0)
			{
				Ship newShip = null;
				
				int spawn = rand.nextInt(3);
				if(spawn == 0)
				{
					newShip = ShipFactory.CreateShip("BattleCrusier");
				}
				else if(spawn == 1)
				{
					newShip = ShipFactory.CreateShip("BattleShooter");
				}
				else if(spawn == 2)
				{
					newShip = ShipFactory.CreateShip("BattleStar");
				}	
				ships.add(newShip);
			}
			
			// Update sky.
			grid.UpdateGrids(ships);
			
			// Check grids.
		}
		
		void Undo()
		{
			for(Ship ship : ships)
			{
				ship.UndoMove();
				
			}
		}
		
		void SaveGame()
		{
			
		}
		
		void LoadGame()
		{
			
		}

		public GameGrid GetGrid() {
			return grid;
		}

		public void SetGrid(GameGrid grid) {
			this.grid = grid;
		}


}
