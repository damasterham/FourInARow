package network;//

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Created by DaMasterHam on 06-04-2017.
//
public class SimpleSocket
{
    private Socket host;
    private DataInputStream in;
    private DataOutputStream out;

    public SimpleSocket(String hostname, int port) throws IOException
    {
        this(new Socket(hostname, port));
    }

    public SimpleSocket(Socket host) throws IOException
    {
        // Creates a new Protocol with implemented ProtocolActions
        this.host = host;
        initializeStreams();
    }

    public Socket getSocket()
    {
        return host;
    }

    private void initializeStreams() throws IOException
    {
        if (host != null)
        {
            in = new DataInputStream(this.host.getInputStream());
            out = new DataOutputStream(this.host.getOutputStream());
        }
    }

    public void sendData(String message) throws IOException
    {
        out.writeUTF(message);
        out.flush();
    }

    public String receiveData() throws IOException
    {
        return in.readUTF();
    }

    public void disconnect() throws IOException
    {
        if (!host.isClosed())
        {
            in.close();
            out.close();
            host.close();
        }
    }

}

