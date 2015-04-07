package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.Pawn;
import com.company.PieceTypes.Queen;
import junit.framework.TestCase;
import org.junit.Test;

public class QueenTest extends TestCase {

    @Test
    public void testQueen(){

        Board testBoard=  new Board();
        Queen testQueen= new Queen(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testQueen,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 4,3);
        Pawn testPawnTwo= new Pawn(testBoard, Color.White, 4,7);
        Pawn testPawnThree= new Pawn(testBoard, Color.Black, 1,7);
        Pawn testPawnFour= new Pawn(testBoard, Color.White, 0,0);
        testBoard.setPieceLocation(testPawnOne,4,3);
        testBoard.setPieceLocation(testPawnTwo,4,7);
        testBoard.setPieceLocation(testPawnThree,1,7);
        testBoard.setPieceLocation(testPawnFour,0,0);
        boolean testsPassed= testValidMovesQueen(testQueen);
        testBoard.removePiece(4,4);
        testBoard.removePiece(4,3);
        testBoard.removePiece(4,7);
        testBoard.removePiece(1,7);
        testBoard.removePiece(0,0);
        assert(testsPassed);
    }

    public boolean testValidMovesQueen(Queen currentQueen){

        if (!currentQueen.validMove(4,3)){ // test valid line pawn capture
            return false;
        }
        if (!currentQueen.validMove(4,6)){ // test valid line move
            return false;
        }
        if (!currentQueen.validMove(1,1)){ // test valid diagonal move
            return false;
        }
        if (!currentQueen.validMove(1,7)){ // test valid diagonal capture
            return false;
        }
        if (currentQueen.validMove(0,0)){ // test invalid pawn capture
            return false;
        }
        if (currentQueen.validMove(4,9)) { // test out of bounds move
            return false;
        }
        if (currentQueen.validMove(5,7)){  // test invalid move
            return false;
        }
        return true;

    }
}