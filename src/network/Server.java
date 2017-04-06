package network;

import game.Game;
import game.IGameEvents;
import game.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static network.ServerProtocol.*;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public class Server implements IGameEvents
{
    public static final int PORT = 5111;
    private static int clientCount = 0;

    private ServerSocket ss;
    private ClientThread[] clients;
    private ServerListenerThread serverListenerThread;

    private Game matchFour;

    public Server()
    {
        clients = new ClientThread[2];
        serverListenerThread = new ServerListenerThread();
    }

    public void startServer() throws IOException
    {
        ss = new ServerSocket(Server.PORT);
        serverListenerThread.start();
    }

    public void startGame()
    {
        if (clientCount == 2)
        {
            matchFour = new Game(clients[0], clients[1], this);

            try
            {
                for (int i = 0; i < clients.length; i++)
                {
                    clients[i].socket.sendData(Protocol.packInitializeBoard(matchFour.getColSize(), matchFour.getRowSize()));
                }

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }


        }
    }



    private void placePiece(String col, ClientThread clientThread)
    {
        if (matchFour == null)
            return;

        int colParsed = Integer.parseInt(col);
        matchFour.placePiece(clientThread, colParsed);
    }

    private void newPayer(ClientThread context, String name, String colorName)
    {
        context.setName(name);
        context.setColor(colorName);

        if (clientCount == 2)
            startGame();
    }


    private void handleProtocol(String data, ClientThread client)
    {
        String[] parsed = Protocol.unpack(data);

        switch (parsed[0])
        {
            case NEW_PLAYER :
                newPayer(client, parsed[1], parsed[2]);
            break;
//            case Protocol.GAME_START :
//                startGame();
//            break;
            case PLACE_PIECE :
                placePiece(parsed[1], client);
            break;

        }

    }

    private void receiveClient(Socket socket)
    {
        try
        {
            clients[clientCount] = new ClientThread(socket);
            new Thread(clients[clientCount]).start();
            clientCount++;

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


    @Override
    public void placementSuccess(Player player, int lastCol, int lastRow)
    {
        try
        {
            for (int i = 0; i < clients.length; i++)
            {
                clients[i].socket.sendData(Protocol.packPlacedPiece(player.getColor().hashCode()+"", lastCol, lastRow));
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void placementFailure(Player player)
    {
        try
        {
            clients[player.getId()].socket.sendData(Protocol.packFailedPlacement());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void wrongPlayer(Player player)
    {
        try
        {
            clients[player.getId()].socket.sendData(Protocol.packWrongPlayer());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void winner(Player player)
    {
        try
        {
            for (int i = 0; i < clients.length; i++)
            {
                clients[i].socket.sendData(Protocol.packWinner(player.getName()));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }



    private class ServerListenerThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                while (!ss.isClosed() && !(clients.length > 2))
                {
                    receiveClient(ss.accept());
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }

    private class ClientThread extends Player implements Runnable
    {
        private SimpleSocket socket;

        public ClientThread(Socket socket) throws IOException
        {
            this.socket = new SimpleSocket(socket);
        }


        @Override
        public void run()
        {
            try
            {
                // While a client is connected
                while (this.socket.getSocket().isConnected())
                {
                    // Listen for data from a client
                    String data = socket.receiveData();

                    handleProtocol(data, this);
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            } // Does the thread close if it reaches the end?
        }
    }



}
