package network;//

import gameclient.FourInARow;
import sun.plugin.viewer.IExplorerPluginObject;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

import static network.ClientProtocol.*;

//Created by DaMasterHam on 06-04-2017.
//
public class Client implements Runnable
{
    private SimpleSocket socket;
    private FourInARow app;

    public Client(String host, int port, FourInARow app) throws IOException
    {
        this.socket = new SimpleSocket(new Socket(host, port));
        this.app = app;
    }

    public Client(SimpleSocket socket)
    {
        this.socket = socket;
    }


    public void newPlayer(String name, String colorName) throws IOException
    {
        socket.sendData(Protocol.packNewPlayer(name, colorName));
    }

    private void handleProtocol(String data)
    {
        String[] parsed = Protocol.unpack(data);

        switch (parsed[0])
        {
            case PLACE_FAIL :
                placementFailed();
                break;
            case PLACED_PIECE :
                placePiece(parsed[1], parsed[2], parsed[3]);
                break;
            case WRONG_PLAYER :
                waitYourTurn();
                break;
            case WINNER :
                gameOver(parsed[1]);
                break;
            case BOARD_INIT :
                initBoard(parsed[1], parsed[2]);
                break;
        }
    }


    private void initBoard(String col, String row)
    {
        app.initializeBoard(Integer.parseInt(col), Integer.parseInt(row));
        app.gameScene();
    }

    private void gameOver(String winner)
    {
    }

    private void waitYourTurn()
    {

        
    }

    private void placePiece(String colorHex, String col, String row)
    {
        app.placePiece(colorHex, Integer.parseInt(col), Integer.parseInt(row));
    }

    private void placementFailed()
    {
    }

    @Override
    public void run() // Listener
    {
        try
        {
            while (socket.getSocket().isConnected())
            {
                String data = socket.receiveData();

                handleProtocol(data);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
