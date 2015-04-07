package com.company.PieceTypes;

import com.company.Board;
import com.company.ChessPiece;
import com.company.Color;


/**
 * Created by Yuriy on 2/12/2015.
 */
public class King extends ChessPiece{
public King(Board currentBoard, Color currentColor, int xPos, int yPos) {
    curBoard = currentBoard;
    curColor = currentColor;
    curX= xPos;
    curY= yPos;
}

public boolean validMove(int newX, int newY){
        if (!curBoard.insideBoard(newX,newY)){
        return false;
        }
        ChessPiece potentialPiece;
        potentialPiece=curBoard.getPiece(newX, newY);
        if (potentialPiece!=null){
            if (potentialPiece.getColor()==curColor){
               return false;
            }
        }
        if ((newX==curX)&&(newY==(curY+1))||(newX==(curX+1))&&(newY==(curY+1))||(newX==(curX+1))&&(newY==curY)||
        (newX==curX)&&(newY==(curY-1))||(newX==(curX+1))&&(newY==(curY-1))||(newX==(curX-1))&&(newY==(curY-1))||
        (newX==(curX-1))&&(newY==(curY+1))||(newX==(curX-1))&&(newY==curY)){
           /* if (!curBoard.beingAttacked(newX, newY)){
                return true;
            }*/
            int oldX= curX;
            int oldY= curY;
            curBoard.movePiece(this, newX, newY);
            boolean validMove= !inCheck();
            curBoard.movePiece(this, oldX, oldY);
            if (potentialPiece!=null) {
                curBoard.movePiece(potentialPiece, newX, newY);
            }
            return validMove;
        }
        return false;
        }

        public boolean inCheck(){
            return curBoard.beingAttacked(curX, curY);
        }
/*public boolean canAttack(ChessPiece curPiece){
    if (curPiece==null){
        return false;
    }
    if (curColor==curPiece.getColor()){
        return false;
    }
    int xAttack= curPiece.getX();
    int yAttack= curPiece.getY();
    return (((xAttack==curX+1)&&(yAttack==curY+1))||((xAttack==curX-1)&&(yAttack==curY+1))||((xAttack==curX+1)&&(yAttack==curY-1))||((xAttack==curX-1)&&(yAttack==curY-1))
            ||((xAttack==curX)&&(yAttack==curY-1))||((xAttack==curX-1)&&(yAttack==curY))||((xAttack==curX+1)&&(yAttack==curY))||((xAttack==curX)&&(yAttack==curY+1)));

}*/
}