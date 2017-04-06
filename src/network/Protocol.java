package network;

import game.Player;

import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Stream;

import static network.ClientProtocol.*;
import static network.ServerProtocol.*;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public class Protocol
{
/*
    public static final String NEW_PLAYER = "new_player";
    public static final String GAME_START = "game_start";
    public static final String GAME_WON = "game_won";
    public static final String GAME_LOST = "game_lost";
    public static final String TURN = "player_turn";
    public static final String PLACE_PIECE = "place_piece";
    public static final String PLACED_PIECE = "placed_piece";
    public static final String PLACE_FAIL = "placement_failure";
    public static final String WRONG_PLAYER = "wrong_player";
    public static final String WINNER = "winner";
*/

    private static final String delimiter = " ";


    public static String[] unpack(String data)
    {
        return data.split(delimiter);
    }

    private static String pack(Object... values)
    {
        String result = "";

        for (int i = 0; i < values.length; i++)
        {
            result += " " + values[i];
        }

        return result;
    }

    public static String packNewPlayer(String name, String colorName)
    {
        return NEW_PLAYER + pack(name, colorName);
    }

    public static String packPlacePiece(int col)
    {
        return PLACE_PIECE + pack(col);
    }

    public static String packPlacedPiece(String color, int lastCol, int lastRow)
    {
        return PLACED_PIECE + pack(color,lastCol,lastRow);
    }

    public static String packFailedPlacement()
    {
        return PLACE_FAIL;
    }

    public static String packWrongPlayer()
    {
        return WRONG_PLAYER;
    }

    public static String packWinner(String name)
    {
        return WINNER + pack(name);
    }

    public static String packInitializeBoard(int colSize, int rowSize)
    {
        return BOARD_INIT + pack(colSize, rowSize);
    }
}
