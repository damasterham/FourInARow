package game;//

import java.awt.*;

//Created by DaMasterHam on 30-03-2017.
//
public class Piece
{
    private int id;
    private Player player;
    private Color color;
    private char symbal;
    private int col;
    private int row;

    public Piece(int id, Player player, Color color)
    {
        this.id = id;
        this.player = player;
        this.color = color;
        symbal = player.getName().charAt(0);
    }

    public char getSymbal()
    {
        return symbal;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean hasSameOwner(Piece other)
    {
        return player.equals(other.player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (id != piece.id) return false;
        if (player != null ? !player.equals(piece.player) : piece.player != null) return false;
        return color != null ? color.equals(piece.color) : piece.color == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
