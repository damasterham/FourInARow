package game;//

import java.awt.*;

//Created by DaMasterHam on 30-03-2017.
//
public class Game
{
    private Player p1;
    private Player p2;
    private Board board;
    private IGameEvents gameEvents;
    private boolean won;

    public Game(Player p1, Player p2, IGameEvents gameEvents)
    {
        this.p1 = p1;
        this.p2 = p2;
        board = new Board();
        this.gameEvents = gameEvents;
        won = false;
    }

    private void round(Player player)
    {
        int col = gameEvents.placePieceInColumn();

        if (board.placePiece(player.generatePiece(), col))
        {
            gameEvents.placementSuccess();
        }

        if (board.checkWin())
            gameEvents.winner();
    }

    public void startGame()
    {
        gameEvents.gameStart();
        while (!won)
        {
            round(p1);
            round(p2);
        }
        gameEvents.gameOver();
    }

    /*public static void main(String[] args)
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
    }*/
}
