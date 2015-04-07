package com.company.PieceTypes;

import com.company.Board;
import com.company.ChessPiece;
import com.company.Color;

import java.util.ArrayList;

/**
 * Created by Yuriy on 2/12/2015.
 */
public class Pawn extends ChessPiece {
    public Pawn(Board currentBoard, Color currentColor, int xPos, int yPos){
        curBoard=currentBoard;
        curColor=currentColor;
        curX= xPos;
        curY= yPos;
    }
    public boolean validMove(int newX, int newY){
        if (!curBoard.insideBoard(newX,newY)){
            return false;
        }
        if ((newX==curX)&&(newY==curY)){
            return false;
        }
        ChessPiece potentialPiece=curBoard.getPiece(newX,newY);
        if (potentialPiece!=null){
            if (potentialPiece.getColor()==curColor){
                return false;
            }
        }
        ArrayList pawnMoves= curBoard.checkPawnMoves(curX, curY, curColor);
        int [] currentCords= new int [2];
        currentCords[0]=newX;
        currentCords[1]=newY;
        for (Object locations : pawnMoves) {
            int[] pieceLocations = (int[]) locations;
            if ((pieceLocations[0] == newX) && (pieceLocations[1] == newY)) {
                return true;
            }
        }
        return false;
    }
}
