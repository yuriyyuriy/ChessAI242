package com.company;

/**
 * Created by Yuriy on 2/12/2015.
 */


public abstract class ChessPiece {
    protected int curX; // x location
    protected int curY; // y location
    protected Board curBoard; // reference to game board
    protected Color curColor; // color of the piece

    public boolean validMove(int newX, int newY) {
        return false;
    } // returns true if moving to (newX, newY)

    public Color getColor() {
        return curColor;
    } // getter for color

    public void setX(int newX) {
        curX = newX;
    }   // setter for x coordinate

    public void setY(int newY) {
        curY = newY;
    }   // setter for y coordinate

    public int getX() {
        return curX;
    }       // getter for x coordinate

    public int getY() {
        return curY;
    }       // getter for y coordinate
}





