package network;//

import java.io.IOException;
import java.util.Scanner;

//Created by DaMasterHam on 06-04-2017.
//
public class ServerApp
{
    private static Server server;
    private static Scanner in;
    private static boolean running;

    private static void command(String command)
    {
        String[] commandArr = command.toLowerCase().split(" ");

        switch (commandArr[0])
        {
            case "exit" : running = false;
            break;
            case "start-server" :
                try { server.startServer();
                    System.out.println("Started server");
                }
                catch (IOException ex)
                {ex.printStackTrace();}
            break;
            case "start-game" : server.startGame();
            break;

        }
    }

    public static void main(String[] args)
    {
        server = new Server();
        in = new Scanner(System.in);
        running = true;

        while (running)
        {
            if (in.hasNextLine())
            {
                command(in.nextLine());
            }
        }

    }
}







