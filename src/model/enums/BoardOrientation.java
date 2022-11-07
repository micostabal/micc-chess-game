package model.enums;

public enum BoardOrientation {
  UP,
  DOWN;

  public BoardOrientation getOppositeOrientation(BoardOrientation orientation) {
    return orientation==UP ? DOWN: UP;
  }
}
