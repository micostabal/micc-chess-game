package model.board;

import static model.constants.BoardConstants.BOARD_DIMENSION;
import static model.constants.BoardConstants.INDEX_CHARACTERS;

public class RawIndex {
  private final int vertical;
  private final int horizontal;

  public RawIndex(int vertical, int horizontal) {
    this.vertical=vertical;
    this.horizontal=horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public boolean isValid() {
    return this.getVertical()>=BOARD_DIMENSION
        || this.getVertical()<0
        || this.getHorizontal()>=BOARD_DIMENSION
        || this.getHorizontal()<0;
  }

  public Position toPosition() {
    // Throws error if not valid!!
    return new Position(
        INDEX_CHARACTERS.charAt(this.horizontal),
        this.vertical
    );
  }
}
