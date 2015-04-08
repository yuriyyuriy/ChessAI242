package com.company;

/**
 * Created by Greg Pastorek on 4/8/2015.
 */
public class ChessAI {

    private Color color;
    private ChessPlayer player;
    private ChessPiece[] pieces;

    public ChessAI(ChessPlayer player_, ChessPiece[] pieces_) {
        player = player_;
        color = player.getColor();
        pieces = pieces_;
    }

    public int[] getMove() {
        return null;
    }


}
