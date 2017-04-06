package network;//

//Created by DaMasterHam on 06-04-2017.
//
public enum ServerProtocol
{
    NEW_PLAYER("new_player");


    private String protocol;


    ServerProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getProtocol()
    {
        return protocol;
    }
}
