import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Vector;


public class GameManager implements java.io.Serializable
{
		Vector<Ship> ships = new Vector<Ship>();
		private GameGrid grid = new GameGrid();
		Vector<GameGrid> pastGrids = new Vector<GameGrid>();
		Vector<Vector<Ship>> pastShips = new Vector<Vector<Ship>>();
		GameManager()
		{
			// Set top grid to prevent entry.
			grid.SetTileCondition(0, 0, false);
			MasterShip playerShip = new MasterShip();
			ships.add(playerShip);
			pastGrids.add(grid);
		}
		
		
		
		void MoveShips()
		{	
			// Command pattern.
			pastGrids.add(new GameGrid(grid));
			pastShips.add(new Vector<Ship>(ships));
			if(ships.size()>0)
			{
			for(Ship ship : ships)
			{
				ship.run();
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
			}
			// Check grids.
		}
		
		void Undo()
		{
			if(pastShips.size() > 1)
			{
				System.out.println(ships.toString());		
				ships = new Vector<Ship>();
				
				System.out.println(ships.toString());
				grid.UpdateGrids(ships);
			}
			
		}
		
		void SaveGame()
		{
			try
			{
				FileOutputStream fileOut =
		        new FileOutputStream("Game.ser");
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        out.writeObject(this);
		        out.close();
		        fileOut.close();
		        System.out.printf("Serialized data is saved in Game.ser");
		    }
			catch(IOException i)
			{
				i.printStackTrace();
		    }
		}
		
		
		static GameManager LoadGame()
		{
			GameManager gm = null;
		      try
		      {
		         FileInputStream fileIn = new FileInputStream("Game.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         gm = (GameManager) in.readObject();
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return gm;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("GameManager class not found");
		         c.printStackTrace();
		         return gm;
		      }
		      return gm;
		}

		public GameGrid GetGrid() {
			return grid;
		}

		public void SetGrid(GameGrid grid) {
			this.grid = grid;
		}


}
