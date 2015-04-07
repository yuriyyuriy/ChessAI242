package com.company;

/**
 * Created by Yuriy on 2/24/2015.
 */
public class ChessPlayer {
    Color curColor;
    int curScore;
    ChessPiece [][]lastMove;
    public ChessPlayer(Color newColor){
        curColor=newColor;
        curScore=0;
        lastMove=null;
    }
    public void wonGame(){
        curScore+=1;
        lastMove=null;
    }
    public int getScore(){
        return curScore;
    }
    public void setLastMove(ChessPiece[][] curBoard){
        lastMove= curBoard;
    }
    public ChessPiece[][] getLastMove(){
        return lastMove;
    }
}
