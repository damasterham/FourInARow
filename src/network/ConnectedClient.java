package network;

import java.net.Socket;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public abstract class ConnectedClient
{
    private Socket socket;

    public ConnectedClient(Socket socket)
    {
        this.socket = socket;
    }


}
