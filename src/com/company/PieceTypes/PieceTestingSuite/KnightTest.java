package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.Knight;
import com.company.PieceTypes.Pawn;
import junit.framework.TestCase;
import org.junit.Test;

public class KnightTest extends TestCase {
    @Test
    public void testKnight(){

        Board testBoard=  new Board();
        Knight testKnight= new Knight(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testKnight,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 2,3);
        Pawn testPawnTwo= new Pawn(testBoard, Color.Black, 5,6);
        Pawn testPawnThree= new Pawn(testBoard, Color.White, 2, 6);
        testBoard.setPieceLocation(testPawnOne,2,3);
        testBoard.setPieceLocation(testPawnTwo,5,6);
        testBoard.setPieceLocation(testPawnThree,2,6);
        boolean testsPassed= testValidMovesKnight(testKnight);
        testBoard.removePiece(4,4);
        testBoard.removePiece(2,3);
        testBoard.removePiece(5,6);
        testBoard.removePiece(2,6);
        assertTrue(testsPassed);
    }

    public boolean testValidMovesKnight(Knight currentKnight){

        if (!currentKnight.validMove(2,3)){ // test pawn capture
            return false;
        }
        if (!currentKnight.validMove(5,6)){ // test pawn capture 2
            return false;
        }
        if (!currentKnight.validMove(6,5)){ // test open move
            return false;
        }
        if (currentKnight.validMove(4,5)){ // test invalid move row
            return false;
        }
        if (currentKnight.validMove(2,6)){ // test invalid capture
            return false;
        }
        if (currentKnight.validMove(0,9)){ // test invalid out of bounds
            return false;
        }
        return true;

    }

}