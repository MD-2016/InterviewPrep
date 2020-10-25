package Chapter_7.Minesweeper;

import java.util.Scanner;

public class Game
{
    public enum GameState
    {
        WON, LOST, RUNNING;
    }

    private Board board;
    private int row;
    private int col;
    private int bombs;
    private GameState state;

    public Game(int r, int c, int b)
    {
        row = r;
        col = c;
        bombs = b;
        state = GameState.RUNNING;
    }

    public boolean initialize()
    {
        if(board == null)
        {
            board = new Board(row, col, bombs);
            board.printBoard(true);
            return true;
        }
        else
        {
            System.out.println("Game has already been initialized.");
            return false;
        }
    }

    public boolean start()
    {
        if(board == null)
        {
            initialize();
        }
        return playGame();
    }

    public void printGameState() {
        if (state == GameState.LOST) {
            board.printBoard(true);
            System.out.println("FAIL");
        } else if (state == GameState.WON) {
            board.printBoard(true);
            System.out.println("WIN");
        } else {
            System.out.println("Number remaining: " + board.getNumRemaining());
            board.printBoard(false);
        }

    }

    private boolean playGame()
    {
        Scanner scan = new Scanner(System.in);
        printGameState();

        while(state == GameState.RUNNING)
        {
            String input = scan.nextLine();
            if(input.equals("exit"))
            {
                scan.close();
                return false;
            }

            UserPlay play = UserPlay.fromString(input);
            if(play == null)
            {
                continue;
            }

            UserPlayResult res = board.playFlip(play);
            if(res.successfulMove())
            {
                state = res.getResultState();
            }
            else
            {
                System.out.println("Could not flip cell (" + play.getRow() + "," + play.getCol() + ").");
            }
            printGameState();
        }
        scan.close();
        return true;
    }

}
