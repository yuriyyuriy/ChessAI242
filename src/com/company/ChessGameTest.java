package com.company;

import com.company.PieceTypes.*;
import junit.framework.TestCase;
import org.junit.Test;

public class ChessGameTest extends TestCase {

    @Test
    public void testEndGame(){
        Board testBoard= new Board();
        King testKing= new King(testBoard, Color.White,4,4);
        testBoard.setPieceLocation(testKing,4,4);
        boolean checkMateWorking= testCheckMate(testBoard, testKing);
        boolean staleMateWorking= testStaleMate(testBoard, testKing);

        boolean endGameWorking= checkMateWorking&&staleMateWorking;
        assertTrue(endGameWorking);
    }
    public boolean testCheckMate(Board curBoard, King curKing){
        Rook rook_one= new Rook(curBoard, Color.Black, 3, 0);
        Rook rook_two= new Rook(curBoard, Color.Black, 5, 7);
        Rook rook_three= new Rook(curBoard, Color.Black, 7, 3);
        Rook rook_four= new Rook(curBoard, Color.Black, 7, 4);
        curBoard.setPieceLocation(rook_one,3,0);
        curBoard.setPieceLocation(rook_two,5,7);
        curBoard.setPieceLocation(rook_three,7,3);
        curBoard.setPieceLocation(rook_four,7,4);

        boolean falseCheckMate=curBoard.checkMate(curKing);

        Queen queen_one= new Queen(curBoard, Color.Black, 4, 7);
        curBoard.setPieceLocation(queen_one, 4,7);

        boolean trueCheckMate= curBoard.checkMate(curKing);

        curBoard.removePiece(3,0);
        curBoard.removePiece(5,7);
        curBoard.removePiece(7,3);
        curBoard.removePiece(5,4);
        curBoard.removePiece(4,7);

        return ((!falseCheckMate)&&(trueCheckMate));
    }
    public boolean testStaleMate(Board curBoard, King curKing){
        Rook rook_one= new Rook(curBoard, Color.Black, 3, 0);
        Rook rook_two= new Rook(curBoard, Color.Black, 5, 7);
        Rook rook_three= new Rook(curBoard, Color.Black, 7, 3);
        Rook rook_four= new Rook(curBoard, Color.Black, 7, 4);
        curBoard.setPieceLocation(rook_one,3,0);
        curBoard.setPieceLocation(rook_two,5,7);
        curBoard.setPieceLocation(rook_three,7,3);
        curBoard.setPieceLocation(rook_four,5,4);

        boolean falseStaleMate=curBoard.checkMate(curKing);

        curBoard.removePiece(5,4);
        Rook rook_five= new Rook(curBoard, Color.Black, 7,5);
        curBoard.setPieceLocation(rook_five,7,5);

        boolean trueStaleMate= curBoard.checkMate(curKing);

        curBoard.removePiece(3,0);
        curBoard.removePiece(5,7);
        curBoard.removePiece(7,3);
        curBoard.removePiece(7,5);

        return ((!falseStaleMate)&&(trueStaleMate));

    }
}