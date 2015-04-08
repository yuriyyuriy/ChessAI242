package ChessGame;
import com.company.*;
import ChessGUI.*;
import com.company.PieceTypes.*;

import java.util.LinkedList;

/**
 * The chessgame is the controller class between the board (Model) and the gui (view)
 * It includes two player classes
 * Created by Yuriy on 2/12/2015.
 */
public class ChessGame {
    ChessPlayer Player_one;
    ChessPlayer Player_two;
    ChessGUI curGui;
    public ChessPiece [] BlackPieces;
    public ChessPiece [] WhitePieces;
    private King WhiteKing;
    private King BlackKing;
    private Board ChessBoard;

    private boolean validMove;
    private boolean undoTriggered;
    private boolean newGame;
    private boolean newCustom;
    private boolean stopGameLoop;

    Color currentColor=Color.White; // sets first color
    ChessPlayer curPlayer= Player_one;
    ChessPiece currentPiece;

    LinkedList<int []> movesQueue = new LinkedList<int []>();
    LinkedList<Boolean> responseQueue = new LinkedList<Boolean>();

    private int offset;
    private int spacing;
    public static void main(String [ ] args)
    {
        ChessGame currentGame= new ChessGame();
        currentGame.initializeChess();
    }
    public void initializeChess() { //initializes the game and setups loop
        Player_one= new ChessPlayer(Color.White);
        Player_two= new ChessPlayer(Color.Black);
        startNewGame(false);
        curGui= new ChessGUI();
        curGui.setModel(this);
        curGui.updatePieces(ChessBoard);
        curGui.launchGUI();
        offset= curGui.getOffset();
        spacing= curGui.getSpacing();
        gameLoop();
    }

    private void gameLoop(){

        while (!checkEndGame(currentColor)) { // check endgame conditions on each loop
            validMove=false;
            if (waitForMove()) {
                break; // waitForMove returns true on manual end game
            }
            tryMove();
            responseQueue.add(validMove);
        }

        stopGameLoop=false;

        if (newGame){
            newGame=false;
            startNewGame(true);
        }
        else {
            curGui.gameOver();
            startNewGame(true);
        }

    }

    private boolean waitForMove() {
    /* wait for new move */
        while(movesQueue.isEmpty()){
            try {
                Thread.sleep(50);
                if (stopGameLoop){
                    return true;
                }
                if (undoTriggered){
                    if (curPlayer.getLastMove()!=null){
                        ChessBoard.setPieceLayout(curPlayer.getLastMove());
                        ChessBoard.updatePieces();
                        curGui.updatePieces(ChessBoard);
                        Player_one.setLastMove(null);
                        Player_two.setLastMove(null);
                    }
                    undoTriggered=false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // busy wait
        return false;
    }

    private void makeAiMove() throws InterruptedException {
        Thread.sleep(500);
        int[] ai_move = null; // TODO - get AI move
        movesQueue.add(ai_move);
        /* TODO - ai mover returns piece and new location */

    }

    private void tryMove() {
        ChessPiece[][] curState= ChessBoard.getPieceLayout();
        int [] curLoc= movesQueue.poll();
        int xInitial= curLoc[0];
        int yInitial= curLoc[1];
        int xEnd= curLoc[2];
        int yEnd= curLoc[3];
        // lets assume an input is two locations x_initial, y_initial, x_end, y_end
        currentPiece = ChessBoard.getPiece(xInitial, yInitial); // gets current piece at location
        if (currentPiece!=null) {   // check if it exists
            if (currentPiece.getColor() == currentColor) { // makes sure it belongs to player
                if (currentPiece.validMove(xEnd, yEnd)) { // checks if move is valid
                    int oldX= currentPiece.getX();
                    int oldY= currentPiece.getY();
                    ChessPiece oldPiece= ChessBoard.getPiece(xEnd, yEnd);
                    boolean successMove=ChessBoard.movePiece(currentPiece, xEnd, yEnd); // checks if move is successful
                    if (successMove) {  // color switch assuming all else is possible
                        if (currentColor == Color.White) {
                            if (WhiteKing.inCheck()){
                                ChessBoard.movePiece(currentPiece, oldX, oldY);
                                if (oldPiece!=null) {
                                    ChessBoard.movePiece(oldPiece, xEnd, yEnd);
                                }
                            }
                            else
                            {
                                validMove=true;
                                curPlayer.setLastMove(curState);
                                currentColor = Color.Black;
                                curPlayer= Player_two;
                            }
                        } else {
                            if (BlackKing.inCheck()){
                                ChessBoard.movePiece(currentPiece, oldX, oldY);
                                if (oldPiece!=null) {
                                    ChessBoard.movePiece(oldPiece, xEnd, yEnd);
                                }
                            }
                            else
                            {
                                validMove=true;
                                curPlayer.setLastMove(curState);
                                currentColor = Color.White;
                                curPlayer= Player_one;
                            }
                        }
                    }
                }
            }
        }
    }

    public void setUpStandardPieces(){ // basic configuration for a standard chessgame
        BlackPieces[0]= new King(ChessBoard,Color.Black,3,0);
        ChessBoard.setPieceLocation(BlackPieces[0],3,0);
        BlackPieces[1]= new Queen(ChessBoard, Color.Black,4,0);
        ChessBoard.setPieceLocation(BlackPieces[1],4,0);
        BlackPieces[2]= new Rook(ChessBoard, Color.Black,0,0);
        ChessBoard.setPieceLocation(BlackPieces[2],0,0);
        BlackPieces[3]= new Rook(ChessBoard, Color.Black,7,0);
        ChessBoard.setPieceLocation(BlackPieces[3],7,0);
        BlackPieces[4]= new Knight(ChessBoard, Color.Black,1,0);
        ChessBoard.setPieceLocation(BlackPieces[4],1,0);
        BlackPieces[5]= new Knight(ChessBoard, Color.Black,6,0);
        ChessBoard.setPieceLocation(BlackPieces[5],6,0);
        BlackPieces[6]= new Bishop(ChessBoard, Color.Black,2,0);
        ChessBoard.setPieceLocation(BlackPieces[6],2,0);
        BlackPieces[7]= new Bishop(ChessBoard, Color.Black,5,0);
        ChessBoard.setPieceLocation(BlackPieces[7],5,0);

        WhitePieces[0]= new King(ChessBoard, Color.White,3,7);
        ChessBoard.setPieceLocation(WhitePieces[0],3,7);
        WhitePieces[1]= new Queen(ChessBoard,Color.White,4,7);
        ChessBoard.setPieceLocation(WhitePieces[1],4,7);
        WhitePieces[2]= new Rook(ChessBoard, Color.White,0,7);
        ChessBoard.setPieceLocation(WhitePieces[2],0,7);
        WhitePieces[3]= new Rook(ChessBoard, Color.White,7,7);
        ChessBoard.setPieceLocation(WhitePieces[3],7,7);
        WhitePieces[4]= new Knight(ChessBoard, Color.White,1,7);
        ChessBoard.setPieceLocation(WhitePieces[4],1,7);
        WhitePieces[5]= new Knight(ChessBoard, Color.White,6,7);
        ChessBoard.setPieceLocation(WhitePieces[5],6,7);
        WhitePieces[6]= new Bishop(ChessBoard, Color.White,2,7);
        ChessBoard.setPieceLocation(WhitePieces[6],2,7);
        WhitePieces[7]= new Bishop(ChessBoard, Color.White,5,7);
        ChessBoard.setPieceLocation(WhitePieces[7],5,7);

        for (int i=8; i<16; i++){
            BlackPieces[i] = new Pawn(ChessBoard, Color.Black, i-8, 1);
            ChessBoard.setPieceLocation(BlackPieces[i],i-8,1);
            WhitePieces[i] = new Pawn(ChessBoard, Color.White, i-8, 6);
            ChessBoard.setPieceLocation(WhitePieces[i],i-8,6);
        }
        ChessBoard.setInitialNumberPieces(16); // sets number of pieces for reference
    }
    public void setUpCustomPieces(){

        BlackPieces[0]= new King(ChessBoard,Color.Black,3,0);
        ChessBoard.setPieceLocation(BlackPieces[0],3,0);
        BlackPieces[1]= new Queen(ChessBoard, Color.Black,4,0);
        ChessBoard.setPieceLocation(BlackPieces[1],4,0);
        BlackPieces[2]= new MountedRook(ChessBoard, Color.Black,0,0);
        ChessBoard.setPieceLocation(BlackPieces[2],0,0);
        BlackPieces[3]= new MountedRook(ChessBoard, Color.Black,7,0);
        ChessBoard.setPieceLocation(BlackPieces[3],7,0);
        BlackPieces[4]= new Knight(ChessBoard, Color.Black,1,0);
        ChessBoard.setPieceLocation(BlackPieces[4],1,0);
        BlackPieces[5]= new Knight(ChessBoard, Color.Black,6,0);
        ChessBoard.setPieceLocation(BlackPieces[5],6,0);
        BlackPieces[6]= new MountedBishop(ChessBoard, Color.Black,2,0);
        ChessBoard.setPieceLocation(BlackPieces[6],2,0);
        BlackPieces[7]= new MountedBishop(ChessBoard, Color.Black,5,0);
        ChessBoard.setPieceLocation(BlackPieces[7],5,0);

        WhitePieces[0]= new King(ChessBoard, Color.White,3,7);
        ChessBoard.setPieceLocation(WhitePieces[0],3,7);
        WhitePieces[1]= new Queen(ChessBoard,Color.White,4,7);
        ChessBoard.setPieceLocation(WhitePieces[1],4,7);
        WhitePieces[2]= new MountedRook(ChessBoard, Color.White,0,7);
        ChessBoard.setPieceLocation(WhitePieces[2],0,7);
        WhitePieces[3]= new MountedRook(ChessBoard, Color.White,7,7);
        ChessBoard.setPieceLocation(WhitePieces[3],7,7);
        WhitePieces[4]= new Knight(ChessBoard, Color.White,1,7);
        ChessBoard.setPieceLocation(WhitePieces[4],1,7);
        WhitePieces[5]= new Knight(ChessBoard, Color.White,6,7);
        ChessBoard.setPieceLocation(WhitePieces[5],6,7);
        WhitePieces[6]= new MountedBishop(ChessBoard, Color.White,2,7);
        ChessBoard.setPieceLocation(WhitePieces[6],2,7);
        WhitePieces[7]= new MountedBishop(ChessBoard, Color.White,5,7);
        ChessBoard.setPieceLocation(WhitePieces[7],5,7);

        for (int i=8; i<16; i++){
            BlackPieces[i] = new Pawn(ChessBoard, Color.Black, i-8, 1);
            ChessBoard.setPieceLocation(BlackPieces[i],i-8,1);
            WhitePieces[i] = new Pawn(ChessBoard, Color.White, i-8, 6);
            ChessBoard.setPieceLocation(WhitePieces[i],i-8,6);
        }
        ChessBoard.setInitialNumberPieces(16); // sets number of pieces for reference

    }
    public boolean checkEndGame(Color curColor){ //checks endgame conditions
        if (curColor==Color.Black){
            if (ChessBoard.checkMate(BlackPieces[0])){
                setCheckMate(Color.White);
                return true;
            }
            else if (ChessBoard.checkStaleMate(WhitePieces[0])){
                return true;
            }
            return false;
        }
        else{
            if (ChessBoard.checkMate(BlackPieces[0])){
                setCheckMate(Color.Black);
                return true;
            }
            else if (ChessBoard.checkStaleMate(WhitePieces[0])){
                return true;
            }
            return false; //checks endgame for white
        }
    }
    public boolean moveReceiver(int [] possibleMove){
        int [] newMove=new int [4];
        newMove[0]= convertUnits(possibleMove[0]);
        newMove[1]= convertUnits(possibleMove[1]);
        newMove[2]= convertUnits(possibleMove[2]);
        newMove[3]= convertUnits(possibleMove[3]);
        movesQueue.add(newMove);

        while(responseQueue.isEmpty()){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // sleep, my child
        return responseQueue.poll();
    }
    private int convertUnits(int oldUnits){
        return (oldUnits-offset)/spacing;
    }
    public void setCheckMate(Color curColor){
        if(curColor==Color.White){
            Player_one.wonGame();
        }
        else{
            Player_two.wonGame();
        }
    }
    public void undoTrigger(){
        undoTriggered=true;
    }
    public ChessPiece[][] getLastUndo(Color curColor){
        ChessPlayer curPlayer;
        if (curColor==Color.White){
            curPlayer=Player_one;
        }
        else{
            curPlayer=Player_two;
        }
        return curPlayer.getLastMove();
    }
    public void newGame(){
        stopGameLoop=true;
        newGame=true;
    }
    public void newCustom(){
        stopGameLoop=true;
        newCustom=true;
    }
    public void buildBoard(){
        ChessBoard = new Board();
        BlackPieces= new ChessPiece[16];
        WhitePieces= new ChessPiece[16];
        undoTriggered=false;
        stopGameLoop=false;
        newGame= false;
        newCustom= false;
    }
    private void startNewGame(Boolean firstGame){
        buildBoard();
        setUpStandardPieces();
        WhiteKing= (King)WhitePieces[0];
        BlackKing= (King)BlackPieces[0];
        if (firstGame){
            curGui.updatePieces(ChessBoard);
            gameLoop();
        }
    }
    private void startNewCustom(){
        buildBoard();
        setUpCustomPieces();
        WhiteKing= (King)WhitePieces[0];
        BlackKing= (King)BlackPieces[0];
        curGui.updatePieces(ChessBoard);
        gameLoop();
    }
    public int [] getScore(){
        int [] curScore= new int [2];
        curScore[0]= Player_one.getScore();
        curScore[1]= Player_two.getScore();
        return curScore;
    }

}

