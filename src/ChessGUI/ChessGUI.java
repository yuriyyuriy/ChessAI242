package ChessGUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import ChessGame.*;
import com.company.Board;
import com.company.ChessPiece;
import com.company.PieceTypes.*;


/**
 * Created by Yuriy on 2/19/2015.
 */
    public class ChessGUI implements ActionListener, MouseListener, MouseMotionListener {
        ChessGame curGame;
        JPanel testref;
        int offset;
        int panelDim;
        int chessBoardWidth=8;
        int chessBoardHeight=8;
        Dimension chessDimension= new Dimension(chessBoardWidth*60,chessBoardHeight*60);
        private JPanel chessBoard;
        private JLayeredPane chessLayers;
        private ImageIcon whiteKnightImage;
        private ImageIcon whiteRookImage;
        private ImageIcon whiteKingImage;
        private ImageIcon whiteQueenImage;
        private ImageIcon whitePawnImage;
        private ImageIcon whiteBishopImage;
        private ImageIcon whiteMountedBishopImage;
        private ImageIcon whiteMountedRookImage;
        private ImageIcon blackKnightImage;
        private ImageIcon blackRookImage;
        private ImageIcon blackKingImage;
        private ImageIcon blackQueenImage;
        private ImageIcon blackPawnImage;
        private ImageIcon blackBishopImage;
        private ImageIcon blackMountedBishopImage;
        private ImageIcon blackMountedRookImage;
        private Clip moveClick;
        private AudioInputStream audioIn;

        private JLabel curMovedPiece;
        private JPanel originalTile;
        public ChessGUI() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                //silently ignore
            }
            chessLayers = new JLayeredPane();
            chessLayers.addMouseListener(this);
            chessLayers.addMouseMotionListener(this);
            chessLayers.setMinimumSize(chessDimension);
            setSourceFiles();   // loads all the images
            initializeBoard();  // sets up board square images
         //   initializePieces(); // sets up piece images
        }
        public void launchGUI(){
            JFrame window = new JFrame("Yuriy Chess");
            window.setSize(63 * 8, 70 * 8); //hardcoded window size
            window.getContentPane().add(chessLayers);
            setUpMenu(window);
            window.setVisible(true);
            setDimensions();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

        /*private void initializeButton(JPanel myPanel) {
            JButton button = new JButton("Click me");
            button.addActionListener(this);
            myPanel.add(button, BorderLayout.SOUTH);
        }*/

        private void initializeBoard() {
            chessBoard = new JPanel();
            chessBoard.setName("chessBoard");
            chessLayers.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            chessBoard.setMinimumSize(chessDimension);
            chessBoard.setBorder(new LineBorder(java.awt.Color.BLACK));
            chessBoard.setBounds(0, 0, chessBoardWidth*60,chessBoardHeight*60);
            chessBoard.setLayout(new GridLayout(8,8));
            chessBoard.getLayout();
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {               // square subdivision
                    JPanel curPiece = new JPanel(new BorderLayout());
                    curPiece.setName("chessTile");
                    if ((x % 2 == 1 && y % 2 == 1)|| (x % 2 == 0 && y % 2 == 0)) {
                        curPiece.setBackground(new Color(209, 139, 71));
                    }
                    else {
                        curPiece.setBackground(new Color(255, 206, 158));
                    }
                    chessBoard.add(curPiece);
                }
            }
        }
        public void initializePieces(){
            JPanel curPiece;
            JLabel curLabel;

            curPiece = (JPanel)chessBoard.getComponent(0);
            curLabel= new JLabel();
            curLabel.setIcon(blackRookImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(1);
            curLabel= new JLabel();
            curLabel.setIcon(blackKnightImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(2);
            curLabel= new JLabel();
            curLabel.setIcon(blackBishopImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(3);
            curLabel= new JLabel();
            curLabel.setIcon(blackKingImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(4);
            curLabel= new JLabel();
            curLabel.setIcon(blackQueenImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(5);
            curLabel= new JLabel();
            curLabel.setIcon(blackBishopImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(6);
            curLabel= new JLabel();
            curLabel.setIcon(blackKnightImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(7);
            curLabel= new JLabel();
            curLabel.setIcon(blackRookImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(56);
            curLabel= new JLabel();
            curLabel.setIcon(whiteRookImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(57);
            curLabel= new JLabel();
            curLabel.setIcon(whiteKnightImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(58);
            curLabel= new JLabel();
            curLabel.setIcon(whiteBishopImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(59);
            curLabel= new JLabel();
            curLabel.setIcon(whiteKingImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(60);
            curLabel= new JLabel();
            curLabel.setIcon(whiteQueenImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(61);
            curLabel= new JLabel();
            curLabel.setIcon(whiteBishopImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(62);
            curLabel= new JLabel();
            curLabel.setIcon(whiteKnightImage);
            curPiece.add(curLabel);
            curPiece = (JPanel)chessBoard.getComponent(63);
            curLabel= new JLabel();
            curLabel.setIcon(whiteRookImage);
            curPiece.add(curLabel);
            for (int i=0; i<8; i++){
                curPiece=(JPanel)chessBoard.getComponent(8+i);
                curLabel= new JLabel();
                curLabel.setIcon(blackPawnImage);
                curPiece.add(curLabel);
                curPiece=(JPanel)chessBoard.getComponent(48+i);
                curLabel= new JLabel();
                curLabel.setIcon(whitePawnImage);
                curPiece.add(curLabel);
            }


        }
        private void setUpMenu(JFrame window) {
            JMenuBar menubar = new JMenuBar();
            JMenu options = new JMenu("Game");
            JMenuItem newGame = new JMenuItem("New Game");
            newGame.addActionListener(this);
            options.add(newGame);
            JMenuItem newCustom = new JMenuItem("New Custom");
            newCustom.addActionListener(this);
            options.add(newCustom);
            JMenuItem undoButton = new JMenuItem("Undo");
            undoButton.addActionListener(this);
            options.add(undoButton);
            JMenuItem curScore = new JMenuItem("Get Score");
            curScore.addActionListener(this);
            options.add(curScore);
            menubar.add(options);
            window.setJMenuBar(menubar);
        }
        private void setSourceFiles(){
            whiteKnightImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteKnight.png");
            whiteRookImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteRook.png");
            whiteKingImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteKing.png");
            whiteQueenImage= new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteQueen.png");
            whitePawnImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whitePawn.png");
            whiteBishopImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteBishop.png");
            whiteMountedBishopImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteMountedBishop.png");
            whiteMountedRookImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\whiteMountedRook.png");
            blackKnightImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackKnight.png");
            blackRookImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackRook.png");
            blackKingImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackKing.png");
            blackQueenImage= new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackQueen.png");
            blackPawnImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackPawn.png");
            blackBishopImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackBishop.png");
            blackMountedBishopImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackMountedBishop.png");
            blackMountedRookImage=  new ImageIcon("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\blackMountedRook.png");
            try {
                audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Yuriy\\IdeaProjects\\Assignment1.2\\src\\ChessGUI\\ChessPiecePNG\\ChessMove.wav"));
                moveClick = AudioSystem.getClip();
                moveClick.open(audioIn);
            }
            catch (Exception e){
                moveClick=null;
            }
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand()=="Undo"){
                curGame.undoTrigger();
            }
            else if (e.getActionCommand()=="New Game"){
                curGame.newGame();
            }
            else if (e.getActionCommand()=="New Custom"){
                curGame.newCustom();
            }
            else if (e.getActionCommand()=="Get Score"){
                int [] gameScore;
                gameScore= curGame.getScore();
                String curScore= "Player 1: "+Integer.toString(gameScore[0])+" Player 2: "+Integer.toString(gameScore[1]);
                JOptionPane.showMessageDialog(null,curScore,"Current Score",JOptionPane.WARNING_MESSAGE);
            }
        }

        public void mousePressed(MouseEvent e){
            curMovedPiece = null;
            Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

            if (c.getName()=="chessTile"){
                return;
            }
            else if (c instanceof JLabel){
                curMovedPiece=(JLabel)c;
                originalTile=(JPanel)curMovedPiece.getParent();
            }
            else{
                return;
            }

            /*Point parentLocation = c.getParent().getLocation();
            xAdjustment = parentLocation.x - e.getX();
            yAdjustment = parentLocation.y - e.getY();
            chessPiece = (JLabel)c;

            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());*/
            curMovedPiece.setLocation(e.getX()-30, e.getY()-30);
            chessLayers.add(curMovedPiece, JLayeredPane.DRAG_LAYER);
        }

        public void mouseClicked(MouseEvent e) {

        }
        public void mouseDragged(MouseEvent e){
            if (curMovedPiece == null) {
                return;
            }
            curMovedPiece.setLocation(e.getX()-30, e.getY()-30);
        }
        public void mouseReleased(MouseEvent e){
            if(curMovedPiece == null){
                return;
            }

            curMovedPiece.setVisible(false);
            Component newLoc =  chessBoard.findComponentAt(e.getX(), e.getY());
            if (newLoc==null){
                originalTile.add(curMovedPiece);
                curMovedPiece.setVisible(true);
                return;
            }
            Container newTile= (Container)newLoc;
            int [] moveSet= new int [4];
            moveSet[0]= originalTile.getX();
            moveSet[1]= originalTile.getY();
            moveSet[2]= newLoc.getX();
            moveSet[3]= newLoc.getY();

            if (newLoc.getName() == "chessTile") {
                if ((curGame.moveReceiver(moveSet))) {
                    newTile.add(curMovedPiece);
                    if (moveClick != null) {
                        moveClick.start();
                        moveClick.setFramePosition(0);
                    }
                    curMovedPiece.setVisible(true);
                    return;
                }
            } else if (newLoc instanceof JLabel) {
                JPanel parentTile = (JPanel) newLoc.getParent();
                moveSet[2]=parentTile.getX();
                moveSet[3]=parentTile.getY();
                if ((curGame.moveReceiver(moveSet))) {
                    parentTile.remove(0);
                    parentTile.add(curMovedPiece);
                    if (moveClick != null) {
                        moveClick.start();
                        moveClick.setFramePosition(0);
                    }
                    curMovedPiece.setVisible(true);
                    return;
                }
            }
            originalTile.add(curMovedPiece);
            curMovedPiece.setVisible(true);
            return;



        }
        public void mouseMoved(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e){

        }
        public void mouseExited(MouseEvent e) {

        }
        public void setDimensions(){
            testref= (JPanel)chessBoard.getComponent(0);
            offset= testref.getX();
            testref= (JPanel)chessBoard.getComponent(1);
            panelDim= testref.getX()-offset;
        }
        public int getOffset(){
            return offset;
        }
        public int getSpacing(){
            return panelDim;
        }
        public void setModel(ChessGame newModel){
            curGame=newModel;
        }
        public void gameOver(){
            JOptionPane.showMessageDialog(null, "Game Over!");
        }
        public void updatePieces(Board curBoard){
            int width= curBoard.getBoardWidth();
            int length= curBoard.getBoardLength();
            JPanel curPanel;
            JLabel curLabel;
            ChessPiece curPiece;
            for (int i=0; i< width; i++){
                for (int j=0; j< length; j++){
                    curPanel= (JPanel)chessBoard.getComponent(i*curBoard.getBoardWidth()+j);
                    if (curPanel.getComponentCount()>0) {
                        curPanel.remove(0);
                    }
                    curPiece= curBoard.getPiece(j,i);
                    if (curPiece!=null) {
                        curLabel = new JLabel();
                        if (curPiece.getColor() == com.company.Color.White) {
                            if (curPiece.getClass() == King.class) {
                                curLabel.setIcon(whiteKingImage);
                            }
                            if (curPiece.getClass() == Bishop.class) {
                                curLabel.setIcon(whiteBishopImage);
                            }
                            if (curPiece.getClass() == Knight.class) {
                                curLabel.setIcon(whiteKnightImage);
                            }
                            if (curPiece.getClass() == Rook.class) {
                                curLabel.setIcon(whiteRookImage);
                            }
                            if (curPiece.getClass() == Pawn.class) {
                                curLabel.setIcon(whitePawnImage);
                            }
                            if (curPiece.getClass() == Queen.class) {
                                curLabel.setIcon(whiteQueenImage);
                            }
                            if (curPiece.getClass() == MountedBishop.class){
                                curLabel.setIcon(whiteMountedBishopImage);
                            }
                            if (curPiece.getClass() == MountedRook.class){
                                curLabel.setIcon(whiteMountedRookImage);
                            }
                        } else {
                            if (curPiece.getClass() == King.class) {
                                curLabel.setIcon(blackKingImage);
                            }
                            if (curPiece.getClass() == Bishop.class) {
                                curLabel.setIcon(blackBishopImage);
                            }
                            if (curPiece.getClass() == Knight.class) {
                                curLabel.setIcon(blackKnightImage);
                            }
                            if (curPiece.getClass() == Rook.class) {
                                curLabel.setIcon(blackRookImage);
                            }
                            if (curPiece.getClass() == Pawn.class) {
                                curLabel.setIcon(blackPawnImage);
                            }
                            if (curPiece.getClass() == Queen.class) {
                                curLabel.setIcon(blackQueenImage);
                            }
                            if (curPiece.getClass() == MountedBishop.class){
                                curLabel.setIcon(blackMountedBishopImage);
                            }
                            if (curPiece.getClass() == MountedRook.class){
                                curLabel.setIcon(blackMountedRookImage);
                            }
                        }
                        curPanel.add(curLabel);
                    }

                }
            }
            chessBoard.revalidate();
            chessBoard.repaint();
        }
    }

