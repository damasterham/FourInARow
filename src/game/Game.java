package game;//

//Created by DaMasterHam on 30-03-2017.
//
public class Game
{
    private Player p1;
    private Player p2;
    private Board board;
    private IGameEvents gameEvents;
    private boolean won;
    private int playerTurn;

    public Game(Player p1, Player p2, IGameEvents gameEvents)
    {
        this.p1 = p1;
        this.p2 = p2;
        board = new Board();
        this.gameEvents = gameEvents;
        won = false;
        playerTurn = p1.getId();
    }

    private int otherPlayer()
    {
        if (p1.getId() == playerTurn)
            return p2.getId();
        else if (p2.getId() == playerTurn)
            return p1.getId();

        return -1;
    }

    public void placePiece(Player player, int col)
    {
        if (won)
            return;

        if (player.getId() != playerTurn)
        {
            gameEvents.wrongPlayer(player);
            return;
        }
        //gameEvents.placePieceInColumn();

        if (board.placePiece(player.generatePiece(), col))
        {
            gameEvents.placementSuccess(player, board.getLastAddedCol(), board.getLastAddedRow());
            playerTurn = otherPlayer();
        }
        else
            gameEvents.placementFailure(player);

        if (board.checkWin())
            gameEvents.winner(player);
    }

//    public void startGame()
//    {
//        gameEvents.gameStart();
//        while (!won)
//        {
//            round(p1);
//            round(p2);
//        }
//        gameEvents.gameOver();
//    }

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
