package model.board;

import model.enums.BoardMoveDirection;
import model.enums.BoardOrientation;

public class BoardDisplacement {
  private BoardMoveDirection moveDirection;
  private final int times;

  public BoardDisplacement(
      BoardMoveDirection moveDirection,
      int times
  ) {
    this.moveDirection=moveDirection;
    this.times=times;
  }

  public BoardDisplacement flipIfNecessary(BoardOrientation orientation) {
    this.moveDirection = orientation==BoardOrientation.UP
        ? this.moveDirection :
        BoardMoveDirection.getOppositeDirection(this.moveDirection);
    return this;
  }

  public BoardMoveDirection getMoveDirection() {
    return moveDirection;
  }

  public int getTimes() {
    return times;
  }
}
