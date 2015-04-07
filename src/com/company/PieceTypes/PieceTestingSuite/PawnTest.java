package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.Pawn;
import junit.framework.TestCase;
import org.junit.Test;

public class PawnTest extends TestCase {
    @Test
    public void testPawn(){

        Board testBoard=  new Board();
        Pawn testPawn= new Pawn(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testPawn,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 5,5);
        Pawn testPawnTwo= new Pawn(testBoard, Color.White, 3,5);
        testBoard.setPieceLocation(testPawnOne,5,5);
        testBoard.setPieceLocation(testPawnTwo,3,5);
        boolean testsPassed= testValidMovesPawn(testPawn);
        testBoard.removePiece(4,4);
        testBoard.removePiece(5,5);
        testBoard.removePiece(3,5);
        assert(testsPassed);
    }

    public boolean testValidMovesPawn(Pawn currentPawn){

        if (!currentPawn.validMove(5,5)){ // test pawn capture
            return false;
        }
        if (!currentPawn.validMove(4,5)){ // test pawn move forward
            return false;
        }
        if (currentPawn.validMove(4,3)){ // test invalid move back
            return false;
        }
        if (currentPawn.validMove(1,1)){ // test invalid other
            return false;
        }
        if (currentPawn.validMove(0,9)){ // test invalid out of bounds
            return false;
        }
        return true;

    }
}