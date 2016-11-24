	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

	import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
	public class GameWindow extends JFrame
	{
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private GameManager game;
		private JLabel[][] grid = new JLabel[4][4];
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					try
					{
						// Load game instance.
						GameWindow frame = new GameWindow();
						frame.setVisible(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		
		/**
		 * Create the frame.
		 */
		public GameWindow()
		{
			
			// Load game manager.
			game = new GameManager(); 
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 936, 456);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			
			for(Integer col =0; col < 4;col++)
			{
				for(Integer row =0; row <4; row++)
				{

					JLabel tile = new JLabel();
					tile.setLocation(col*64, row*64);
					tile.setText(col.toString()+","+row.toString());
					tile.setOpaque(true);
					tile.setBorder(new EmptyBorder(5,5,5,5));
					if(game.GetGrid().GetTile(col, row).IsAllowedToEnter() == true)
						tile.setBackground(Color.lightGray);
					else
						tile.setBackground(Color.white);
					grid[col][row] = tile;
					contentPane.add(grid[col][row]);
				}
				
			}
			JButton Move = new JButton("Move");
			Move.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					System.out.println(game.ships.size());
					game.Update();
					// Update Grid.
					
					for(int col =0; col <4;col++)
					{
						for(int row =0; row < 4; row++)
						{
							// Update text in grid.
							grid[col][row].setText(game.GetGrid().UpdateVisualGrid(col, row));

						}
					}
					
				}
			}	);
			contentPane.setLayout(null);
			contentPane.setLayout(new GridLayout(0, 2, 0, 0));
			contentPane.add(Move);
			
			JButton btnUndo = new JButton("Undo");
			btnUndo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					for(int col =0; col <4;col++)
					{
						for(int row =0; row < 4; row++)
						{
							// Update text in grid.
							grid[col][row].setText(game.GetGrid().UpdateVisualGrid(col, row));

						}
					}
				}
			});
			btnUndo.setToolTipText("you are a fool, undo your randomly generated move.");
			contentPane.add(btnUndo);
			
			JButton btnSave = new JButton("Save");
			contentPane.add(btnSave);
			
			JButton btnLoad = new JButton("Load");
			contentPane.add(btnLoad);
		}
		

	}
