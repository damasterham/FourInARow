package game;//

import java.awt.*;

//Created by DaMasterHam on 30-03-2017.
//
public class Player
{
    private static int count = 0;
    private static int pieceCount = 0;

    private int id;
    private String name;
    private String colorhex;
    private String colorName;

    public Player()
    {
    }

    /*public Player(String name, String color)
    {
        id = count++;
        this.name = name;
        setColor(color);
    }*/

    public Player(String name, String colorHex)
    {
        id = count++;
        this.name = name;
        this.colorhex = colorHex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return colorhex;
    }

//    public String getColorName()
//    {
//        return colorName;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color)
    {
        this.colorhex = color;
        //colorName = color;
    }

    public Piece generatePiece()
    {
        return new Piece(pieceCount++, this, colorhex);
    }

    public static void clear()
    {
        count = 0;
        pieceCount = 0;
    }

}
