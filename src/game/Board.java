package game;//

import java.util.Arrays;

//Created by DaMasterHam on 30-03-2017.
//
public class Board
{
    private int winCondition;

    private int horizontalCount;
    private int verticalCount;
    private int SENW; // (\)
    private int SWNE; // (/)

    // Column/Row   -   ergo(x/y)
    private Piece[][] matrix;
    private Piece lastPieceAdded;
    private int lastAddedCol;
    private int lastAddedRow;


    public Board()
    {
        this(7, 6, 4);
    }

    public Board(int columns, int rows, int winCondition)
    {
        matrix = new Piece[columns][rows];
        this.winCondition = winCondition -1;
        clearCount();
    }

    private void clearCount()
    {
        horizontalCount = 0;
        verticalCount = 0;
        SENW = 0;
        SWNE = 0;
    }

    private boolean withinBounds(int value, int min, int max)
    {
        return value >= min && value < max;
    }

    private boolean winCondition(int axisCount)
    {
        return axisCount >= winCondition;
    }

    // Increments the count of a direction
    private void incCount(Direction dir)
    {
        switch (dir)
        {
            case UP:verticalCount++;
            break;
            case DOWN:verticalCount++;
            break;
            case LEFT:horizontalCount++;
            break;
            case RIGHT:horizontalCount++;
            break;
            case UP_LEFT:SENW++;
            break;
            case UP_RIGHT:SWNE++;
            break;
            case DOWN_LEFT:SWNE++;
            break;
            case DOWN_RIGHT:SENW++;
            break;
        }
    }

    private void checkAdjacent(Piece piece, int col, int row, Direction dir)
    {
        // Gets adjacent matrix position
        int adjacentCol = dir.newCol(col);
        int adjacentRow = dir.newRow(row);

        Piece adjacent = null;

        // Finds the adjacent piece
        if (withinBounds(adjacentCol, 0, matrix.length)
                && withinBounds(adjacentRow, 0, matrix[col].length)
                && hasPiece(adjacentCol, adjacentRow))
            adjacent = pieceAt(adjacentCol, adjacentRow);

        // Is there a piece, and it is owned by the same player
        if (adjacent != null && adjacent.hasSameOwner(piece))
        {
            // If it is increment the axis the direction is heading
            incCount(dir);
            // Recursively search for the next adjacent in the same direction
            checkAdjacent(piece, adjacentCol, adjacentRow, dir);
        }
    }

    private void checkAdjacent(Piece piece, int col, int row)
    {
        // Iterate through all the directions
        for (Direction dir : Direction.values())
        {
            checkAdjacent(piece, col, row, dir);
        }
    }


    private void addPiece(Piece piece, int col, int row)
    {
        lastAddedCol = col;
        lastAddedRow = row;
        lastPieceAdded = piece;
        matrix[col][row] = piece;
    }

    private boolean placePiece(Piece piece, int col, int row)
    {
        int child = row - 1;
        // If child within bounds
        if (child < matrix[col].length && child >= 1)
        {
            // If child has piece, fill parent
            if (hasPiece(col, child))
            {
                addPiece(piece, col, row);
                return true;
            }
            else // Recursively dig deeper
                return placePiece(piece, col, child);
        }
        // If child out of bound, bottom has been reached and empty
        else if (!hasPiece(col, child))
        {
            addPiece(piece, col, child);
            return true;
        }
        else if (hasPiece(col, child)) // If bottom child is filled
        {
            addPiece(piece, col, row);
            return true;
        }

        return false;
    }

    public boolean placePiece(Piece piece, int col)
    {
        int row = matrix[col].length -1;

        // Check top of column
        if (matrix[col][row] != null)
            return false;

        return placePiece(piece, col, row);
    }

    public boolean checkWin()
    {
        // Check last placed piece
        checkAdjacent(lastPieceAdded, lastAddedCol, lastAddedRow);

        // Check if you have the win condition (Default 4 in a row)
        boolean win = winCondition(horizontalCount) || winCondition(verticalCount) || winCondition(SWNE) || winCondition(SENW);
        // Clear the count for next check
        clearCount();

        return win;

    }

    public boolean hasPiece(int col, int row)
    {

        return matrix[col][row] != null;
    }

    public Piece pieceAt(int col, int row)
    {
        return matrix[col][row];
    }

    public void print()
    {
        for (int j = matrix[0].length - 1; j >= 0; j--)
        {
            for (int i = 0; i < matrix.length; i++)
            {

                System.out.print("[" + (hasPiece(i, j) ? pieceAt(i, j).getSymbal() : '_') + "]");

            }
            System.out.println();
        }

        System.out.println();
    }
}
