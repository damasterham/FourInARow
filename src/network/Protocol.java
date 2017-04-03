package network;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by DaMasterHam on 03-04-2017.
 */
public abstract class Protocol
{
    private String header;
    private String body;
    private EventHandler eventHandler;

    public String getHeader()
    {
        return header;
    }

    public String getBody()
    {
        return body;
    }

    public void handle(Event event)
    {
        eventHandler.handle(event);
    }
}
