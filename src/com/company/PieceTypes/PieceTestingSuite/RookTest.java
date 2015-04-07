package com.company.PieceTypes.PieceTestingSuite;


import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.Pawn;
import com.company.PieceTypes.Rook;
import junit.framework.TestCase;
import org.junit.Test;

public class RookTest extends TestCase {

    @Test
    public void testRook(){

        Board testBoard= new Board();
        Rook testRook= new Rook(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testRook,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 4,3);
        Pawn testPawnTwo= new Pawn(testBoard, Color.White, 4,7);
        Pawn testPawnThree= new Pawn(testBoard, Color.Black, 0,4);
        Pawn testPawnFour= new Pawn(testBoard, Color.White, 6,4);
        testBoard.setPieceLocation(testPawnOne,4,3);
        testBoard.setPieceLocation(testPawnTwo,4,7);
        testBoard.setPieceLocation(testPawnThree,0,4);
        testBoard.setPieceLocation(testPawnFour,6,4);
        boolean testsPassed= testValidMovesRook(testRook);
        testBoard.removePiece(4,4);
        testBoard.removePiece(4,3);
        testBoard.removePiece(0,4);
        testBoard.removePiece(6,4);
        assert(testsPassed);
    }

    public boolean testValidMovesRook(Rook currentRook){

        if (!currentRook.validMove(4,3)){
            return false;
        }
        if (!currentRook.validMove(4,6)){
            return false;
        }
        if (!currentRook.validMove(5,4)){
            return false;
        }
        if (!currentRook.validMove(1,4)){
            return false;
        }
        if (currentRook.validMove(4,2)){
            return false;
        }
        if (currentRook.validMove(4,9)) {
            return false;
        }
        if (currentRook.validMove(6,4)){
            return false;
        }
        return true;

    }

}