package controller.strategies;

import controller.ChessBoardController;

public class NotSelectedStrategy implements ControllerStrategy {

  ChessBoardController controller;

  public NotSelectedStrategy(ChessBoardController controller) {
    this.controller=controller;
  }

  public void handleSquareClick(int vertical, int horizontal) {
    System.out.println("Clicked Piece "+vertical+", "+horizontal+" in NOT selected mode");
  }
}
