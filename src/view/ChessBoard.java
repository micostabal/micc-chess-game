package view;

import view.state.ChessBoardState;
import view.state.ChessBoardStateType;
import view.state.GameChessBoardState;
import view.state.InactiveChessBoardState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class ChessBoard implements ChessBoardState {
  private final JPanel gui = new JPanel(new BorderLayout(3, 3));
  private JPanel chessBoard;
  private ChessBoardState chessBoardState;
  private ChessBoardSquare[][] chessBoardSquares = new ChessBoardSquare[8][8];
  private EnumMap<ChessBoardStateType, ChessBoardState> chessBoardStates;

  public ChessBoard() {
    super();
    initializeGUI();
    initializeStates();
  }

  public void initializeStates() {
    chessBoardStates= new EnumMap<>(Map.ofEntries(
      Map.entry(ChessBoardStateType.INACTIVE, new InactiveChessBoardState()),
      Map.entry(ChessBoardStateType.ACTIVE, new GameChessBoardState(this))
    ));
    chessBoardState=chessBoardStates.get(ChessBoardStateType.INACTIVE);
  }

  public void initializeGUI () {
    gui.setBorder(new EmptyBorder(5, 5, 5, 5));
    chessBoard = new JPanel(new GridLayout(8, 8));
    chessBoard.setBorder(new LineBorder(Color.BLACK));

    for(int i=0;i<8;i++) {
      for(int j=0;j<8;j++) {
        ChessBoardSquare chessBoardSquare = new ChessBoardSquare(i, j);
        chessBoardSquare.initialize(this);
        chessBoardSquares[i][j] = chessBoardSquare;
        chessBoard.add(chessBoardSquare);
      }
    }
    gui.add(chessBoard);
  }

  public void setChessBoardState(ChessBoardStateType stateType) {
    chessBoardState=chessBoardStates.get(stateType);
  }

  public void handleSquareClick(int vertical, int horizontal) {
    chessBoardState.handleSquareClick(vertical, horizontal);
  };

  public ChessBoardSquare getChessBoardSquare(int vertical, int horizontal) {
    return chessBoardSquares[vertical][horizontal];
  }

  public JComponent getGui() {
    return gui;
  }

  public JComponent getChessBoard() {
    return chessBoard;
  }
}
