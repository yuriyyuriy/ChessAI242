package com.company.JUnit;

import com.company.*;
import com.company.PieceTypes.King;
import com.company.PieceTypes.Queen;
import junit.framework.TestCase;
import org.junit.Test;

public class ChessAITest extends TestCase {

    @Test
    public void testMovePiece_isValidMove() throws Exception {

        Board testBoard=  new Board();
        King whiteKing = new King(testBoard, Color.White,4,4);
        Queen blackQueen = new Queen(testBoard, Color.Black,4,4);
        testBoard.setPieceLocation(whiteKing,4,4);
        testBoard.setPieceLocation(blackQueen,7,5);

        ChessPlayer ai_player = new ChessPlayer(Color.White);
        ChessPiece[] ai_pieces = { whiteKing };
        ChessAI ai = new ChessAI(ai_player, ai_pieces);

        int[] move = ai.getMove();

        int src_x = move[0];
        int src_y = move[1];
        int dst_x = move[2];
        int dst_y = move[3];

        boolean movedKing = (src_x == 4 && src_y == 4);
        boolean validMove = whiteKing.validMove(dst_x, dst_y);

        assertEquals("AI move was valid.", true, movedKing && validMove);

    }

}