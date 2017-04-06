package game;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public interface IGameEvents
{
    //void gameStart();

    //int placePieceInColumn();
    void placementSuccess(Player player, int lastCol, int lastRow);
    void placementFailure(Player player); //Should only happen, when trying to place at already filled column
    void wrongPlayer(Player player);
    void winner(Player player);

    //void gameOver();

}
