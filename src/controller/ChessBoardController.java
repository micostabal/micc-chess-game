package controller;

import controller.strategies.ControllerStrategy;
import controller.strategies.NotSelectedStrategy;
import model.game.ChessGame;
import view.ChessBoardView;

public class ChessBoardController implements ControllerStrategy {
  ChessGame chessModel;
  ChessBoardView chessBoardView;
  ControllerStrategy controllerStrategy;

  public ChessBoardController() {
    setControllerStrategy(new NotSelectedStrategy(this));
  };

  public void setControllerStrategy(ControllerStrategy controllerStrategy) {
    this.controllerStrategy = controllerStrategy;
  }

  public void setChessModel(ChessGame chessBoardModel) {
    this.chessModel = chessBoardModel;
  }

  public void setChessBoardView(ChessBoardView chessBoardView) {
    this.chessBoardView = chessBoardView;
  }

  public void handleSquareClick(int vertical, int horizontal) {
    this.controllerStrategy.handleSquareClick(vertical, horizontal);
  }
}
