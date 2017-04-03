package game;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public interface IGameEvents
{
    void gameStart();
    int placePieceInColumn();
    void placementSuccess();
    void winner();
    void gameOver();

}
