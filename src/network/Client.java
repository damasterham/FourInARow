package network;//

import java.io.IOException;
import java.net.Socket;

//Created by DaMasterHam on 06-04-2017.
//
public class Client implements Runnable
{
    private SimpleSocket socket;

    public Client(String host, int port) throws IOException
    {
        this.socket = new SimpleSocket(new Socket(host, port));
    }

    public Client(SimpleSocket socket)
    {
        this.socket = socket;
    }


    private void handleProtocol(String data)
    {
        String[] parsed = Protocol.unpack(data);

        switch (parsed[0])
        {
            case Protocol.
        }
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
