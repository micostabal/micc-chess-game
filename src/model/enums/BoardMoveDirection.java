package model.enums;

import java.util.HashMap;
import java.util.Map;

public enum BoardMoveDirection {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  private static Map<BoardMoveDirection, BoardMoveDirection> opposites = Map.of(
      UP, DOWN,
      DOWN, UP,
      LEFT, RIGHT,
      RIGHT, LEFT
  );

  public static BoardMoveDirection getOppositeDirection(BoardMoveDirection direction) {
    return opposites.get(direction);
  }

}
