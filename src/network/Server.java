package network;

import game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public class Server
{
    private static int clientCount = 0;

    private ServerSocket ss;
    private ConnectedClient[] clients;

    private Game matchFour;

    public Server()
    {
        clients = new ConnectedClient[2];
    }

    public void startGame()
    {
        matchFour = new Game()
    }

    public void startServer() throws IOException
    {
        ss = new ServerSocket();
    }

    private void receiveClient(Socket socket)
    {
        clients[clientCount] = new ConnectedClient(socket);
    }

    private class ListenerThread extends Thread
    {
        @Override
        public void run()
        {

            while (!ss.isClosed())
            {
                receiveClient(ss.accept());
            }
        }
    }
}
