package gameclient;//

import com.sun.javafx.image.IntPixelGetter;
import game.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import network.Client;
import network.Server;
import sun.awt.windows.ThemeReader;
import sun.rmi.server.InactiveGroupException;

import java.io.IOException;

//Created by DaMasterHam on 06-04-2017.
//
public class FourInARow extends Application
{
    private int circleRadius = 10;
    private int posOffset = 15;
    private Client client;
    private Circle[][] board;
    private BorderPane gamePane;
    private Stage stage;

    private void connectToServer(String name, String color, String host, int port)
    {
        try
        {
            client = new Client(host, port, this);
            new Thread(client).start();
            client.newPlayer(name, color);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void placePiece(String colorHex, int col, int row)
    {
        Circle c = new Circle(10);
        c.setFill(Paint.valueOf(colorHex));
        c.setLayoutX(col*posOffset);
        c.setLayoutY(row*posOffset);

        board[col][row] = c;
    }

    public void initializeBoard(int col, int row)
    {
        board = new Circle[col][row];
        for (int i = 0; i < col; i++)
        {
            for (int j = 0; j < row; j++)
            {
                Circle c =  new Circle(10);
                c.setFill(Paint.valueOf("000000"));
                board[i][j] = c;
            }
        }

        gameScene();
    }

    public void renderBoard()
    {
        Platform.runLater(() ->
        {
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board[0].length; j++)
                {
                    Circle piece = board[i][j];
                    piece.setLayoutX(i * 2);
                    piece.setLayoutY(j * 2);

                    gamePane.getChildren().add(piece);

                }
            }
            this.stage.setScene(new Scene(gamePane));
            this.stage.show();
        }

        );

    }


    public void gameScene()
    {

        gamePane = new BorderPane();
     /*   Scene scene1 = new Scene(gamePane);
        Platform.runLater(() ->
        {
            this.stage.setScene(scene1);
            this.stage.show();
        }

        );*/
        renderBoard();
    }

    @Override
    public void start(Stage pStage) throws Exception
    {
        stage = pStage;
        TextField name = new TextField("Name");
        ColorPicker cp = new ColorPicker();
        TextField host = new TextField("localhost");
        TextField port = new TextField(Server.PORT+"");
        Button ok = new Button("Go");
        ok.setOnAction(e -> connectToServer(name.getText(),
                Integer.toHexString(cp.getValue().hashCode()),
                host.getText(),
                Integer.parseInt(port.getText())
        ));


        VBox topPane = new VBox();
        topPane.getChildren().addAll(
                new Label("Name"),
                name,
                new Label("Piece Color"),
                cp,
                new Label("Host IP"),
                host,
                new Label("Host Port"),
                port,
                ok
        );

//        Circle c = new Circle(100);
//        c.setFill(Paint.valueOf("000000"));

        BorderPane wrap = new BorderPane();
        wrap.setTop(topPane);
        //wrap.setCenter(c);


        Scene scene = new Scene(wrap);

        stage.setScene(scene);
        stage.show();

        Platform.setImplicitExit(false);
    }



}
