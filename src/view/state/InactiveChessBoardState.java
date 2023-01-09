package view.state;

import view.ChessBoard;

public class InactiveChessBoardState implements ChessBoardState {

  @Override
  public void handleSquareClick(int vertical, int horizontal) {
    System.out.println("Inactive State: Clicked piece "+vertical+", "+horizontal);
  }
}
