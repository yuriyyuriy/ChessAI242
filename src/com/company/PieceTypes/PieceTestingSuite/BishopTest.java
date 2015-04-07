package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.Bishop;
import com.company.PieceTypes.Pawn;
import junit.framework.TestCase;
import org.junit.Test;

public class BishopTest extends TestCase {

    @Test
    public void testBishop(){

        Board testBoard=  new Board();
        Bishop testBishop= new Bishop(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testBishop,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 5,5);
        Pawn testPawnTwo= new Pawn(testBoard, Color.White, 5,3);
        Pawn testPawnThree= new Pawn(testBoard, Color.Black, 1,1);
        testBoard.setPieceLocation(testPawnOne,5,5);
        testBoard.setPieceLocation(testPawnTwo,5,3);
        testBoard.setPieceLocation(testPawnThree,1,1);
        boolean testsPassed= testValidMovesBishop(testBishop);
        testBoard.removePiece(5,5);
        testBoard.removePiece(5,3);
        testBoard.removePiece(1,1);
        testBoard.removePiece(4,4);
        assertTrue(testsPassed);
    }

    public boolean testValidMovesBishop(Bishop currentBishop){

        if (!currentBishop.validMove(5,5)){ // test valid diagonal pawn capture
            return false;
        }
        if (!currentBishop.validMove(1,1)){ // test valid diagonal pawn capture 2
            return false;
        }
        if (!currentBishop.validMove(1,7)){ // test valid diagonal move
            return false;
        }
        if (currentBishop.validMove(5,3)){ // test invalid pawn capture
            return false;
        }
        if (currentBishop.validMove(0,8)) { // test out of bounds move
            return false;
        }
        if (currentBishop.validMove(4,7)){  // test invalid move
            return false;
        }
        return true;

    }
}