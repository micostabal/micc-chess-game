package view.state;

import view.ChessBoard;
import view.ChessBoardSquare;

import java.awt.*;

public class GameChessBoardState implements ChessBoardState {
  private ChessBoard chessBoard;

  public GameChessBoardState(ChessBoard constructorInputChessBoard) {
    chessBoard=constructorInputChessBoard;
  }

  public void handleSquareClick(int vertical, int horizontal) {
    ChessBoardSquare chessBoardSquare = chessBoard.getChessBoardSquare(vertical, horizontal);

    chessBoardSquare.setBackground(Color.RED);

    System.out.println("Game State: Clicked piece "+vertical+", "+horizontal);
  }

}
