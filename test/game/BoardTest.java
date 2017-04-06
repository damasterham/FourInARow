//package game;
//
//import org.junit.jupiter.api.Test;
//
//import java.awt.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////
////Created by DaMasterHam on 03-04-2017.
////
//class BoardTest
//{
//    @Test
//    void generatePlayerPiece()
//    {
//        Player.clear();
//
//        Player p1 = new Player("N/A", Color.BLACK);
//
//        Piece piece = new Piece(0, p1, p1.getColor());
//        Piece playerGenerated = p1.generatePiece();
//
//        assertEquals(piece, playerGenerated);
//    }
//
//    @Test
//    void initializeBoard()
//    {
//    }
//
//    @Test
//    void placePiece()
//    {
//        Player.clear();
//        Player p1 = new Player("Blarb", Color.blue);
//        //Player p2 = new Player("Ken", Color.PINK);
//
//        Board board = new Board();
//        board.placePiece(p1.generatePiece(), 1);
//
//        assertEquals(new Piece(0, p1, p1.getColor()), board.pieceAt(1, 0));
//    }
//
//    @Test
//    void stack2Pieces()
//    {
//        Player.clear();
//        Player p1 = new Player("Blarb", Color.blue);
//        //Player p2 = new Player("Ken", Color.PINK);
//
//        Board board = new Board();
//        board.placePiece(p1.generatePiece(), 1);
//        board.placePiece(p1.generatePiece(), 1);
//
//        assertTrue(new Piece(0, p1, p1.getColor()).equals(board.pieceAt(1, 0)) &&
//                new Piece(1, p1, p1.getColor()).equals(board.pieceAt(1, 1)));
//
//    }
//
//    @Test
//    void cannotStack7Pieces()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//
//        Board board = new Board();
//        for (int i = 0; i < 6; i++) // Place six
//        {
//            board.placePiece(p1.generatePiece(), 1);
//        }
//
//        assertFalse(board.placePiece(p1.generatePiece(), 1));
//    }
//
//    @Test
//    void notWin3HorizontalFromEnd()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//
//        Board board = new Board();
//        for (int i = 0; i < 3; i++) // Place six
//        {
//            board.placePiece(p1.generatePiece(), i);
//        }
//
//        board.print();
//        assertFalse(board.checkWin());
//    }
//
//    @Test
//    void win4HorizontalFromEnd()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//
//        Board board = new Board();
//        for (int i = 0; i < 4; i++) // Place six
//        {
//            board.placePiece(p1.generatePiece(), i);
//        }
//
//        board.print();
//        assertTrue(board.checkWin());
//    }
//
//    @Test
//    void win4Vertical()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//
//        Board board = new Board();
//        for (int i = 0; i < 4; i++) // Place six
//        {
//            board.placePiece(p1.generatePiece(), 0);
//        }
//
//        board.print();
//        assertTrue(board.checkWin());
//    }
//
//    @Test
//    void win4Diagonal()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//        Player p2 = new Player("Wooples", Color.ORANGE);
//
//        Board board = new Board();
//        for (int i = 2; i >= 0; i--) // Place six
//        {
//            for (int j = 3 - i; j > 0 ; j--)
//            {
//                board.placePiece(p1.generatePiece(), i);
//            }
//        }
//
//        for (int i = 0; i < 4; i++)
//        {
//            board.placePiece(p2.generatePiece(), i);
//        }
//
//        board.print();
//        assertTrue(board.checkWin());
//    }
//
//    @Test
//    void win4DiagonalOposite()
//    {
//        Player p1 = new Player("Blarb", Color.blue);
//        Player p2 = new Player("Wooples", Color.ORANGE);
//
//        Board board = new Board();
//        for (int i = 0; i < 2; i++) // Place six
//        {
//            for (int j = 0; j < 3 - i ; j++)
//            {
//                board.placePiece(p1.generatePiece(), i);
//            }
//        }
//
//        for (int i = 0; i < 4; i++)
//        {
//            board.placePiece(p2.generatePiece(), i);
//        }
//
//        board.print();
//        assertTrue(board.checkWin());
//    }
//
//}