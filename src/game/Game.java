package game;//

import java.awt.*;

//Created by DaMasterHam on 30-03-2017.
//
public class Game
{
    private Player p1;
    private Player p2;
    private IGameEvents gameEvents;

    public Game(Player p1, Player p2, IGameEvents gameEvents)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.gameEvents = gameEvents;
    }

    private void round(Player player, Board board)
    {
        int col = gameEvents.placePiece();

        if (board.placePiece(player.generatePiece(), col))
        {

        }
    }

    public void startGame()
    {

    }

    public static void main(String[] args)
    {
        Board board = new Board();

        Player p1 = new Player("Player1", Color.cyan);
        Player p2 = new Player("Player2", Color.ORANGE);


        pseudoRound(board, p1, 1);
        pseudoRound(board, p1, 1);
        pseudoRound(board, p1, 1);
        pseudoRound(board, p1, 1);

    }

    private static void pseudoRound(Board board, Player player, int col)
    {
        board.placePiece(player.generatePiece(), col);
        board.print();
        System.out.println(board.checkWin());
    }
}
