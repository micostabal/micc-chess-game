package controller.strategies;

public class InactiveStrategy implements ControllerStrategy {
  @Override
  public void handleSquareClick(int vertical, int horizontal) {
    System.out.println("Clicked Piece "+vertical+", "+horizontal+" in inactive mode");
  }
}
