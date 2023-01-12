package controller.strategies;

import controller.ChessBoardController;

public class SelectedStrategy implements ControllerStrategy {
  ChessBoardController controller;

  public SelectedStrategy(ChessBoardController controller) {
    this.controller=controller;
  }

  @Override
  public void handleSquareClick(int vertical, int horizontal) {
    System.out.println("Clicked Piece "+vertical+", "+horizontal+" in selected mode");
  }
}
