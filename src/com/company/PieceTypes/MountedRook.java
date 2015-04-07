package com.company.PieceTypes;

import com.company.Board;
import com.company.ChessPiece;
import com.company.Color;

import java.util.ArrayList;

/**
 * Created by Yuriy on 2/19/2015.
 */
public class MountedRook extends ChessPiece {
    public MountedRook(Board currentBoard, Color currentColor, int xPos, int yPos){
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
        ArrayList openLines= curBoard.checkOpenLines(curX, curY);
        int [] currentCords= new int [2];
        currentCords[0]=newX;
        currentCords[1]=newY;
        for (Object locations : openLines) {
            int[] pieceLocations = (int[]) locations;
            if ((pieceLocations[0] == newX) && (pieceLocations[1] == newY)) {
                return true;
            }
        }
        ArrayList openLs = curBoard.checkWeirdLShapes(curX, curY);
        for (Object locations : openLs) {
            int[] pieceLocations = (int[]) locations;
            if ((pieceLocations[0] == newX) && (pieceLocations[1] == newY)) {
                return true;
            }
        }
        return false;
    }
}
