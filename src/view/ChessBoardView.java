package view;

import controller.ChessBoardController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ChessBoardView {
  private final JPanel gui = new JPanel(new BorderLayout(3, 3));
  private JPanel chessBoard;
  private ChessBoardSquare[][] chessBoardSquares = new ChessBoardSquare[8][8];

  public ChessBoardView(ChessBoardController chessBoardController) {
    super();
    initializeGUI(chessBoardController);
  }

  public void initializeGUI (ChessBoardController chessBoardController) {
    gui.setBorder(new EmptyBorder(5, 5, 5, 5));
    chessBoard = new JPanel(new GridLayout(8, 8));
    chessBoard.setBorder(new LineBorder(Color.BLACK));

    for(int i=0;i<8;i++) {
      for(int j=0;j<8;j++) {
        ChessBoardSquare chessBoardSquare = new ChessBoardSquare(i, j);
        chessBoardSquare.initialize(chessBoardController);
        chessBoardSquares[i][j] = chessBoardSquare;
        chessBoard.add(chessBoardSquare);
      }
    }
    gui.add(chessBoard);
  }

  public JComponent getGui() {
    return gui;
  }
}
