package gameclient;//

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import network.Client;

//Created by DaMasterHam on 06-04-2017.
//
public class FourInARow extends Application
{
    private Client client;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        client = new Client();


        BorderPane pane = new BorderPane();


    }
}
