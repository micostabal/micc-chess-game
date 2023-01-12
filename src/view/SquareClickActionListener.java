package view;

import controller.ChessBoardController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareClickActionListener implements ActionListener {
  private ChessBoardController chessBoardController;
  private int vertical;
  private int horizontal;

  public SquareClickActionListener(ChessBoardController chessBoardController, int vertical, int horizontal) {
    this.chessBoardController=chessBoardController;
    this.vertical=vertical;
    this.horizontal=horizontal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    chessBoardController.handleSquareClick(vertical, horizontal);
  }
}
