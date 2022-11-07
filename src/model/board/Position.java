package model.board;


import static model.constants.BoardConstants.INDEX_CHARACTERS;

public class Position {
  private char horizontal;
  private int vertical;

  public char getHorizontal() {
    return this.horizontal;
  }

  public int getVertical() {
    return this.vertical;
  }

  public Position(char horizontal, int vertical) {
    this.horizontal=horizontal;
    this.vertical=vertical;
  }

  @Override
  public boolean equals(Object o) {
    return this.horizontal==((Position)o).getHorizontal()
        && this.vertical== ((Position)o).getVertical();
  }

  @Override
  public int hashCode() {
    return this.vertical*10 + INDEX_CHARACTERS.indexOf(this.horizontal);
  }

  public static Position fromRawIndex(RawIndex rawIndex) {
    return new Position(
        INDEX_CHARACTERS.charAt(rawIndex.getHorizontal()),
        rawIndex.getVertical()
    );
  }


}
