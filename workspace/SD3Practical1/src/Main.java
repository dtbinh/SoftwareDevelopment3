import javax.swing.JOptionPane;
import java.util.Random;
public class Main
{

	public static void main(String[] args)
	{
		// Winner of game.
		//Ball winner;
		
		BlackBall black = new BlackBall();
		WhiteBall white = new WhiteBall();
		
		Random gen = new Random();
		
		int num = gen.nextInt(2);
		if(num == 0)
		{
			PrintWinner(black.GetType());
		}
		else if (num == 1)
		{
			PrintWinner(white.GetType());
		}
		
	}

	public static void PrintWinner(String type)
	{
		  JOptionPane.showMessageDialog(null,type, "Winner", JOptionPane.INFORMATION_MESSAGE);
	}
	
}