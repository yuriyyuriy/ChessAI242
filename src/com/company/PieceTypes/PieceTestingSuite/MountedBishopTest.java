package com.company.PieceTypes.PieceTestingSuite;

import com.company.Board;
import com.company.Color;
import com.company.PieceTypes.MountedBishop;
import com.company.PieceTypes.Pawn;
import junit.framework.TestCase;
import org.junit.Test;

public class MountedBishopTest extends TestCase {

    @Test
    public void testMountedBishop(){

        Board testBoard= new Board();
        MountedBishop testMountedBishop= new MountedBishop(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testMountedBishop,4,4);

        Pawn testPawnOne= new Pawn(testBoard, Color.Black, 5,5);
        Pawn testPawnTwo= new Pawn(testBoard, Color.Black, 5,2);
        Pawn testPawnThree= new Pawn(testBoard, Color.White, 1,1);
        testBoard.setPieceLocation(testPawnOne,5,5);
        testBoard.setPieceLocation(testPawnTwo,5,2);
        testBoard.setPieceLocation(testPawnThree,1,1);
        boolean testsPassed= testValidMovesMountedBishop(testMountedBishop);
        testBoard.removePiece(5,5);
        testBoard.removePiece(5,2);
        testBoard.removePiece(1,1);
        testBoard.removePiece(4,4);
        assertTrue(testsPassed);
    }

    public boolean testValidMovesMountedBishop(MountedBishop currentMountedBishop){

        if (!currentMountedBishop.validMove(5,5)){ // test valid diagonal pawn capture
            return false;
        }
        if (!currentMountedBishop.validMove(5,2)){ // test valid horse jump capture
            return false;
        }
        if (!currentMountedBishop.validMove(6,5)){ // test valid horse jump
            return false;
        }
        if (currentMountedBishop.validMove(0,8)) { // test out of bounds move
            return false;
        }
        if (currentMountedBishop.validMove(1,1)){  // test capture
            return false;
        }
        if (currentMountedBishop.validMove(4,7)){  // test invalid line move
            return false;
        }

        return true;

    }

}