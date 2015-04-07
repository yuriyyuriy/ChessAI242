package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.King;
import com.company.PieceTypes.Pawn;
import junit.framework.TestCase;
import org.junit.Test;

public class KingTest extends TestCase {
    @Test
    public void testKing(){

        Board testBoard=  new Board();
        King testKing= new King(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testKing,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 4,5);
        Pawn testPawnTwo= new Pawn(testBoard, Color.Black, 6,6);
        Pawn testPawnThree= new Pawn(testBoard, Color.White, 3, 5);
        testBoard.setPieceLocation(testPawnOne,4,5);
        testBoard.setPieceLocation(testPawnTwo,6,6);
        testBoard.setPieceLocation(testPawnThree,3,5);
        boolean testsPassed = testValidMovesKing(testKing);
        testBoard.removePiece(4,4);
        testBoard.removePiece(4,5);
        testBoard.removePiece(6,6);
        testBoard.removePiece(3,5);
        assertTrue(testsPassed);
    }

    public boolean testValidMovesKing(King currentKing){

        if (!currentKing.validMove(4,5)){ // test pawn capture
            return false;
        }
        if (!currentKing.validMove(4,3)){ // test escape
            return false;
        }
        if (!currentKing.validMove(3,3)){ // test escape 2
            return false;
        }
        if (currentKing.validMove(5,5)){ // test self-check
            return false;
        }
        if (currentKing.validMove(3,5)){ // test invalid capture
            return false;
        }
        if (currentKing.validMove(2,2)){ // test invalid move
            return false;
        }
        return true;

    }
}