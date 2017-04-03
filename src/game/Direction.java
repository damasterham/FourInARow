package game;//

//Created by DaMasterHam on 03-04-2017.
//
public enum Direction
{
    RIGHT(1,0),
    UP_RIGHT(1,1),
    UP(0,1),
    UP_LEFT(-1,1),
    LEFT(-1,0),
    DOWN_LEFT(-1,-1),
    DOWN(0,-1),
    DOWN_RIGHT(1,-1);

    private int col;
    private int row;

    Direction(int col, int row)
    {
        this.col = col;
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    public int newCol(int col)
    {
        return col + this.col;
    }

    public int newRow(int row)
    {
        return row + this.row;
    }
}
