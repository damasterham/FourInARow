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
    private Color color;
    private String colorName;

    public Player()
    {
    }

    public Player(String name, String color)
    {
        id = count++;
        this.name = name;
        setColor(color);
    }

    public Player(String name, Color color)
    {
        id = count++;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
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
        this.color = Color.decode("0x"+color);
        //colorName = color;
    }

    public Piece generatePiece()
    {
        return new Piece(pieceCount++, this, color);
    }

    public static void clear()
    {
        count = 0;
        pieceCount = 0;
    }

}
