package com.company;

import com.company.PieceTypes.King;
import com.company.PieceTypes.Pawn;

import java.util.ArrayList;


/**
 * Created by Yuriy on 2/12/2015.
 */



public class Board {

    private int BOARD_LENGTH = 8;
    private int BOARD_WIDTH = 8;
    private ChessPiece[][] pieceGridStatus; // tells what piece exists at that point
    int blackPiecesLeft;
    int whitePiecesLeft;

    public Board() {
        pieceGridStatus = new ChessPiece[BOARD_LENGTH][BOARD_WIDTH];
        for (int i = 0; i <BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                pieceGridStatus[i][j] = null;
            }
        }

    }
    public void setInitialNumberPieces(int numberPieces) {
        blackPiecesLeft = numberPieces;
        whitePiecesLeft = numberPieces;
    }
    public ChessPiece getPiece(int xLoc, int yLoc) { // returns piece at location if exists, null otherwise
        if (insideBoard(xLoc, yLoc)) {
            return pieceGridStatus[xLoc][yLoc];
        }
        return null;
    }
    public void removePiece(int xLoc, int yLoc) { // removes piece at location if exists
        ChessPiece aboutToBeRemoved = pieceGridStatus[xLoc][yLoc];
        if (aboutToBeRemoved == null) {
            return;
        }
        Color curColor = aboutToBeRemoved.getColor();
        if (curColor == Color.Black) {
            blackPiecesLeft--;
        }
        if (curColor == Color.White) {
            whitePiecesLeft--;
        }
        pieceGridStatus[xLoc][yLoc] = null;
    }
    public boolean occupiedSpot(int xLoc, int yLoc) { // tells whether spot is occupied
        if (!insideBoard(xLoc, yLoc))
            return false;
        if (pieceGridStatus[xLoc][yLoc] != null){
            return true;
        }
        return false;
    }
    public boolean setPieceLocation(ChessPiece curPiece, int xLoc, int yLoc) { // if location is valid, moves piece to location
        if (!insideBoard(xLoc, yLoc)) {
            return false;
        }
        if ((occupiedSpot(xLoc, yLoc))&&(getPiece(xLoc,yLoc)!=curPiece)) {
            if (pieceGridStatus[xLoc][yLoc].getColor() == curPiece.getColor()) {
                return false;
            }
            removePiece(xLoc, yLoc);

        }
        pieceGridStatus[xLoc][yLoc] = curPiece;
        curPiece.setX(xLoc);
        curPiece.setY(yLoc);
        return true;
    }
    public boolean movePiece(ChessPiece curPiece, int newX, int newY) {
        int oldX = curPiece.getX();
        int oldY = curPiece.getY();
        boolean moveSuccess = setPieceLocation(curPiece, newX, newY);
        if (moveSuccess) {
            if (!((oldX==newX)&&(oldY==newY))) {
                pieceGridStatus[oldX][oldY]=null;
            }
            return true;
        }
        return false;
    }
    public boolean insideBoard(int xLoc, int yLoc) { // checks whether location is within set bounds of board
        if ((xLoc < BOARD_WIDTH) && (yLoc < BOARD_LENGTH) && (xLoc >= 0) && (yLoc >= 0)) {
            return true;
        }
        return false;
    }
    public boolean beingAttacked(int xLoc, int yLoc) { // checks whether piece is being attacked by another piece
        return attackByDiagonal(xLoc, yLoc) || attackByLine(xLoc, yLoc) || attackByNeigh(xLoc, yLoc);
    }
    private boolean attackByDiagonal(int xLoc, int yLoc) { // checks if piece is being attacked by diagonal
        ArrayList diagonalAttacks = checkOpenDiagonals(xLoc, yLoc);
        ChessPiece possibleAttacker;
        for (Object locations : diagonalAttacks) {
            int[] pieceLocations = (int[]) locations;
            possibleAttacker = getPiece(pieceLocations[0], pieceLocations[1]);
            if (possibleAttacker != null) {
                if (KingVersusKing(possibleAttacker, pieceGridStatus[xLoc][yLoc])){
                    return false;
                }
                if (possibleAttacker.validMove(xLoc, yLoc)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean attackByLine(int xLoc, int yLoc) {
        ArrayList<int[]> lineAttacks = checkOpenLines(xLoc, yLoc);
        ChessPiece possibleAttacker;
        for (Object locations : lineAttacks) {
            int[] pieceLocations = (int[]) locations;
            possibleAttacker = getPiece(pieceLocations[0], pieceLocations[1]);
            if (possibleAttacker != null) {
                if (KingVersusKing(possibleAttacker, pieceGridStatus[xLoc][yLoc])){
                    return false;
                }
                if (possibleAttacker.validMove(xLoc, yLoc)) {
                    return true;
                }
            }
        }
        return false;
    } // tells if being attacked by a line
    private boolean attackByNeigh(int xLoc, int yLoc) {
        //man fuck these horses
        ArrayList LShapes = checkWeirdLShapes(xLoc, yLoc);
        ChessPiece possibleAttacker;
        for (Object locations : LShapes) {
            int[] pieceLocations = (int[]) locations;
            possibleAttacker = getPiece(pieceLocations[0], pieceLocations[1]);
            if (possibleAttacker != null) {
                if (possibleAttacker.validMove(xLoc, yLoc)) {
                    return true;
                }
            }
        }
        return false;
    } //tells if being attacked by a horse
    public int getBoardLength() {
        return BOARD_LENGTH;
    } // unused, returns board length
    public int getBoardWidth() {
        return BOARD_WIDTH;
    } // unused, returns board width
    public ArrayList checkOpenDiagonals(int xLoc, int yLoc) {
        int[] curLocation;
        ArrayList<int[]> openDiagonals = new ArrayList();
        for (int i = 1; (((xLoc + i) < BOARD_WIDTH) && ((yLoc + i) < BOARD_LENGTH)); i++) {
            curLocation = new int[2];
            curLocation[0] = xLoc + i;
            curLocation[1] = yLoc + i;
            openDiagonals.add(curLocation);

            if (occupiedSpot(xLoc + i, yLoc + i)) {
                break;
            }
        }
        for (int i = 1; (((xLoc - i) >= 0) && ((yLoc + i) < BOARD_LENGTH)); i++) {
            curLocation = new int[2];
            curLocation[0] = xLoc - i;
            curLocation[1] = yLoc + i;
            openDiagonals.add(curLocation);
            if (occupiedSpot(xLoc - i, yLoc + i)) {
                break;
            }
        }

        for (int i = 1; (((xLoc - i) >= 0) && ((yLoc - i) >= 0)); i++) {
            curLocation = new int[2];
            curLocation[0] = xLoc - i;
            curLocation[1] = yLoc - i;
            openDiagonals.add(curLocation);
            if (occupiedSpot(xLoc - i, yLoc - i)) {
                break;
            }
        }
        for (int i = 1; (((xLoc + i) < BOARD_WIDTH) && ((yLoc - i) >= 0)); i++) {
            curLocation = new int[2];
            curLocation[0] = xLoc + i;
            curLocation[1] = yLoc - i;
            openDiagonals.add(curLocation);
            if (occupiedSpot(xLoc + i, yLoc - i)) {
                break;
            }
        }
        return openDiagonals;
    } // returns all open diagonals+ last un-open corner on each one, for a particular location
    public ArrayList checkOpenLines(int xLoc, int yLoc) {
        ArrayList openLines = new ArrayList();
        int[] curLocation;
        CheckRight:
        for (int i = xLoc + 1; i < BOARD_WIDTH; i++) {
            curLocation = new int[2];
            curLocation[0] = i;
            curLocation[1] = yLoc;
            openLines.add(curLocation);
            if (occupiedSpot(i, yLoc)) {
                break CheckRight;
            }
        }
        CheckLeft:
        for (int i = xLoc - 1; i >= 0; i--) {
            curLocation = new int[2];
            curLocation[0] = i;
            curLocation[1] = yLoc;
            openLines.add(curLocation);
            if (occupiedSpot(i, yLoc)) {
                break CheckLeft;
            }
        }
        CheckUp:
        for (int i = yLoc + 1; i < BOARD_LENGTH; i++) {
            curLocation = new int[2];
            curLocation[0] = xLoc;
            curLocation[1] = i;
            openLines.add(curLocation);
            if (occupiedSpot(xLoc, i)) {
                break CheckUp;
            }
        }
        CheckDown:
        for (int i = yLoc - 1; i >= 0; i--) {
            curLocation = new int[2];
            curLocation[0] = xLoc;
            curLocation[1] = i;
            openLines.add(curLocation);
            if (occupiedSpot(xLoc, i)) {
                break CheckDown;
            }
        }
        return openLines;
    } // returns all open lines + last un-open spot on each one, for a particular location
    public ArrayList checkWeirdLShapes(int xLoc, int yLoc) {
        ArrayList openLs = new ArrayList();
        int[] curLocation;
        if (insideBoard(xLoc - 2, yLoc - 1)) {
            curLocation = new int[2];
            curLocation[0] = xLoc - 2;
            curLocation[1] = yLoc - 1;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc + 2, yLoc - 1)) {
            curLocation = new int[2];
            curLocation[0] = xLoc + 2;
            curLocation[1] = yLoc - 1;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc + 2, yLoc + 1)) {
            curLocation = new int[2];
            curLocation[0] = xLoc + 2;
            curLocation[1] = yLoc + 1;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc - 2, yLoc + 1)) {
            curLocation = new int[2];
            curLocation[0] = xLoc - 2;
            curLocation[1] = yLoc + 1;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc - 1, yLoc - 2)) {
            curLocation = new int[2];
            curLocation[0] = xLoc - 1;
            curLocation[1] = yLoc - 2;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc + 1, yLoc - 2)) {
            curLocation = new int[2];
            curLocation[0] = xLoc + 1;
            curLocation[1] = yLoc - 2;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc + 1, yLoc + 2)) {
            curLocation = new int[2];
            curLocation[0] = xLoc + 1;
            curLocation[1] = yLoc + 2;
            openLs.add(curLocation);
        }
        if (insideBoard(xLoc - 1, yLoc + 2)) {
            curLocation = new int[2];
            curLocation[0] = xLoc - 1;
            curLocation[1] = yLoc + 2;
            openLs.add(curLocation);
        }
        return openLs;
    } // returns all L shapes for a particular location
    public ArrayList checkPawnMoves(int xLoc, int yLoc, Color curColor) {
        ArrayList pawnMoves = new ArrayList();
        int direction;
        int[] curLocation;
        if (curColor == Color.Black) {
            direction = 1;
        } else {
            direction = -1;
        }
        if (insideBoard(xLoc, yLoc + direction)) {
            if (!occupiedSpot(xLoc, yLoc + direction)) {
                curLocation = new int[2];
                curLocation[0] = xLoc;
                curLocation[1] = yLoc + direction;
                pawnMoves.add(curLocation);
            }
        }
        if (insideBoard(xLoc + 1, yLoc + direction)) {
            if (occupiedSpot(xLoc + 1, yLoc + direction)) {
                curLocation = new int[2];
                curLocation[0] = xLoc + 1;
                curLocation[1] = yLoc + direction;
                pawnMoves.add(curLocation);
            }
        }
        if (insideBoard(xLoc - 1, yLoc + direction)) {
            if (occupiedSpot(xLoc - 1, yLoc + direction)) {
                curLocation = new int[2];
                curLocation[0] = xLoc - 1;
                curLocation[1] = yLoc + direction;
                pawnMoves.add(curLocation);
            }
        }
        return pawnMoves;
    } // returns possible pawn moves for a given color and pawn
    public boolean checkMate(ChessPiece curKing) {
        int curX = curKing.getX();
        int curY = curKing.getY();
        King currentKing = (King) curKing;
        if (!currentKing.inCheck()) {
            return false;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!((i == 0) && (j == 0))) {
                    if (curKing.validMove(curX + i, curY + j)) {
                        return false;
                    }
                }
            }
        }
        if (canIntercept((King) curKing)) {
            return false;
        }
        return true;
    } // returns true if checkmate
    public boolean checkStaleMate(ChessPiece curKing) {
        Color curColor = curKing.getColor();
        if (curColor == Color.Black) {
            if (blackPiecesLeft > 9) {
                return false;
            }
        } else {
            if (whitePiecesLeft > 9) {
                return false;
            }
        }
        int curX = curKing.getX();
        int curY = curKing.getY();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceGridStatus[i][j] != null) {
                    if ((pieceGridStatus[i][j].getClass() != King.class) && (pieceGridStatus[i][j].getClass() != Pawn.class) && (pieceGridStatus[i][j].getColor() == curKing.getColor())) {
                        return false;
                    }
                    if ((pieceGridStatus[i][j].getClass() == Pawn.class) && (pieceGridStatus[i][j].getColor() == curKing.getColor())) {
                        ArrayList pawnMoves = checkPawnMoves(pieceGridStatus[i][j].getX(), pieceGridStatus[i][j].getY(), pieceGridStatus[i][j].getColor());
                        if (pawnMoves.size() != 0) {
                            return false;
                        }
                    }
                }
            }
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!((i == 0) && (j == 0))) {
                    if (curKing.validMove(curX + i, curY + j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    } // returns false if stalemate
    public boolean canIntercept(King curKing) {
        int xPosKing = curKing.getX();
        int yPosKing = curKing.getY();
        Color curColor = curKing.getColor();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece possibleDefender = pieceGridStatus[i][j];
                if (possibleDefender != null) {
                    if ((possibleDefender.getColor() == curColor) && (possibleDefender.getClass() != King.class)) {
                        for (int m = -1; m < 2; m++) {
                            for (int n = -1; n < 2; n++) {

                                if ((possibleDefender.validMove(xPosKing + m, yPosKing + n))&&(m!=0)&&(n!=0)) {
                                    //hypothetical check
                                    ChessPiece ourDefender = pieceGridStatus[i][j];
                                    movePiece(ourDefender, xPosKing + m, yPosKing + n);
                                    if (!curKing.inCheck()) {
                                        movePiece(ourDefender, i, j);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    } // returns true if a piece can intercept a check
    public boolean KingVersusKing(ChessPiece attackingKing, ChessPiece defendingKing) {
        return (attackingKing.getClass()==King.class&&defendingKing.getClass()==King.class&&(Math.abs(attackingKing.getX()-defendingKing.getX())<3)&&(Math.abs(attackingKing.getY()-defendingKing.getY())<3));
    }

    public Board getDuplicate() throws Exception{
        Board duplicate;
        //try{
            duplicate=(Board)this.clone();
       // } catch (Exception e){
         //   duplicate=null;
        //}
        return duplicate;
    }
    public void updatePieces(){
        ChessPiece curPiece;
        for (int i=0; i< BOARD_WIDTH; i++){
            for (int j=0; j< BOARD_LENGTH; j++){
                curPiece= pieceGridStatus[i][j];
                if (curPiece!=null) {
                    curPiece.setX(i);
                    curPiece.setY(j);
                }
            }
        }
    }
    public ChessPiece [][] getPieceLayout(){
        ChessPiece[][] newArray = new ChessPiece[BOARD_WIDTH][BOARD_LENGTH];
        for (int i=0; i<BOARD_LENGTH; i++){
            System.arraycopy(pieceGridStatus[i], 0, newArray[i], 0, BOARD_WIDTH);
        }
        return newArray;
    }
    public void setPieceLayout(ChessPiece [][] newLayout){
        pieceGridStatus= newLayout;
    }
}
