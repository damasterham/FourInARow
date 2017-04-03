package game;//

import java.awt.*;

//Created by DaMasterHam on 30-03-2017.
//
public class Game
{
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
