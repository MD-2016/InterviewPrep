package Chapter_7.Othello;

public class Test
{
	public static void main(String[] args)
	{
		Game game = Game.getInstance();
		game.getBoard().initialize();
		game.getBoard().printBoard();
		Automater automator = Automater.getInstance();
		while (!automator.isOver() && automator.playRandom()) {
		}
		automator.printScores();
	}
}
