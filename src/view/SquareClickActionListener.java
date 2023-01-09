package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareClickActionListener implements ActionListener {
  private ChessBoard chessBoard;
  private int vertical;
  private int horizontal;

  public SquareClickActionListener(ChessBoard chessBoard, int vertical, int horizontal) {
    this.chessBoard=chessBoard;
    this.vertical=vertical;
    this.horizontal=horizontal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    chessBoard.handleSquareClick(vertical, horizontal);
  }
}
